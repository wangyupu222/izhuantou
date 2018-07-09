package com.izhuantou.fund.rpc.impl.bidding;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.izhuantou.common.redismq.MessageType;
import com.izhuantou.common.tool.ToolDateTime;
import com.izhuantou.common.utils.JsonUtil;
import com.izhuantou.common.utils.RedisUtils;
import com.izhuantou.common.utils.StringUtil;
import com.izhuantou.damain.pay.PayPrivilege;
import com.izhuantou.damain.pay.PayPrivilegeMemberMapping;
import com.izhuantou.damain.pay.PayPrivilegeSpecialps;
import com.izhuantou.damain.red.RedPacketMemberMapping;
import com.izhuantou.damain.vo.bidding.BiddingDTO;
import com.izhuantou.damain.webp2p.WebP2pPackageBiddingMainRuning;
import com.izhuantou.damain.webp2p.WebP2pProductRateInfo;
import com.izhuantou.dao.pay.PayPrivilegeMapper;
import com.izhuantou.dao.pay.PayPrivilegeMemberMappingMapper;
import com.izhuantou.dao.pay.PayPrivilegeSpecialpsMapper;
import com.izhuantou.dao.red.RedPacketMemberMappingMapper;
import com.izhuantou.dao.webp2p.WebP2pPackageBiddingMainRuningMapper;
import com.izhuantou.dao.webp2p.WebP2pProductRateInfoMapper;
import com.izhuantou.fund.rpc.api.bidding.BiddingCashFreeze;
import com.izhuantou.fund.rpc.api.bidding.PackageBiddingMainRuning;
import com.izhuantou.fund.rpc.impl.BaseServiceImpl;
import com.koomii.redismq.Message;
import com.koomii.redismq.RedisMQMessageSender;

@Service("packageBiddingMainRuning")
public class PackageBiddingMainRuningImpl extends BaseServiceImpl<WebP2pPackageBiddingMainRuning>
		implements PackageBiddingMainRuning {
	private static final Logger logger = LoggerFactory.getLogger(PackageBiddingMainRuningImpl.class);
	@Autowired
	private BiddingCashFreeze controlDebitCredit;
	@Autowired
	private PayPrivilegeMapper privilegeMapper;
	@Autowired
	private PayPrivilegeMemberMappingMapper privilegeMemberMappingMapper;
	@Autowired
	private PayPrivilegeSpecialpsMapper privilegeSpecialPSMapper;
	@Autowired
	private WebP2pPackageBiddingMainRuningMapper packageBiddingMainRuningMapper;
	@Autowired
	private WebP2pProductRateInfoMapper productRateInfoMapper;
	@Autowired
	private RedisMQMessageSender messageSender;
	@Autowired
	private RedPacketMemberMappingMapper redPacketMemberMappingMapper;
	@Autowired
	private RedisUtils redisUtils;

	@Override
	public String bidPackageBiddingDifferent(BiddingDTO biddto) {
		try {
			String result = "";
			if (biddto != null) {
				// 从reids中扣减可投金额
				if (StringUtil.isEmpty(biddto.getTqOID())) {
					biddto.setBeginDate(ToolDateTime.gainDate());
					// 将bitdto存入redis
					result = controlDebitCredit.investment(biddto);
					String biddingOID = biddto.getBiddingOID();
					BigDecimal bidMoney = biddto.getAmount();
					if (StringUtil.isNotEmpty(biddingOID)) {
						Map<String, String> remap = redisUtils.hgetAll(biddingOID);
						String strHoldingAmount = remap.get("holdingAmount");
						String strSxAmount = remap.get("sxAmount");
						BigDecimal holdingAmount = new BigDecimal(strHoldingAmount);
						BigDecimal sxAmount = new BigDecimal(strSxAmount);

						if (sxAmount.compareTo(new BigDecimal("0")) == 0) {
							BigDecimal nowMoney = bidMoney.add(holdingAmount);
							remap.put("holdingAmount", nowMoney.toString());
							redisUtils.hmset(biddingOID, remap);
						} else {
							BigDecimal nowMoney = bidMoney.add(holdingAmount);
							if (nowMoney.compareTo(sxAmount) <= 0) {
								remap.put("holdingAmount", nowMoney.toString());
								redisUtils.hmset(biddingOID, remap);
							} else {
								return "投资金额达投标上限";
							}
						}
						WebP2pPackageBiddingMainRuning pbm = packageBiddingMainRuningMapper
								.findByOID(biddto.getBiddingOID());
						WebP2pProductRateInfo pdri = productRateInfoMapper.findByOID(pbm.getProductRateInfoID());
						// 团标产品投标
						BigDecimal privilegePrincipal = new BigDecimal(0);
						BigDecimal privilegeInterest = new BigDecimal(0);
						if (StringUtil.isEmpty(biddto.getTqOID())) {
							biddto.setBeginDate(ToolDateTime.gainDate());

							result = controlDebitCredit.investment(biddto);

							if (StringUtil.isNotEmpty(result) && "1".equals(result)) {
								// 富有冻结成功 将环环标的数据放到redis队列中
								String biddtoStr = JsonUtil.toStr(biddto);
								logger.info("发送的信息为" + biddtoStr);
								Long mqResult = messageSender
										.put(new Message(MessageType.HH_BIDDING_MESSAGE, biddtoStr));
								if (mqResult != null) {
									logger.info("消息发送成功");
								} else {
									logger.info("消息发送失败");
								}
							} else {
								// 富有冻结失败,则加上可投金额。
								Map<String, String> retumap = redisUtils.hgetAll(biddingOID);
								BigDecimal strhoAmount = new BigDecimal(retumap.get("holdingAmount"));
								BigDecimal nowMoney = strhoAmount.subtract(bidMoney);
								remap.put("holdingAmount", nowMoney.toString());
								redisUtils.hmset(biddingOID, remap);
							}
						} else {
							PayPrivilege tq = privilegeMapper.findByOID(biddto.getTqOID());
							if (tq != null) {
								if (StringUtil.isNotEmpty(tq.getPrivilegeType()) && "1".equals(tq.getPrivilegeType())) {
									// 加息
									privilegeInterest = tq.getPrivilegeRange();
								} else if (tq.getPrivilegeType() != null && "0".equals(tq.getPrivilegeType())) {
									// 红包
									privilegePrincipal = tq.getPrivilegeRange();
								} else {
									logger.info("特权投标失败");
									return null;
								}
							}
							biddto.setPrivilegeInterest(privilegeInterest);
							biddto.setPrivilegePrincipal(privilegePrincipal);
							biddto.setBeginDate(ToolDateTime.gainDate());
							result = controlDebitCredit.investment(biddto);
							if (StringUtil.isNotEmpty(result) && "1".equals(result)) {
								// 将环环标的数据放到redis队列中
								String biddtoStr = JsonUtil.toStr(biddto);
								System.err.println("发送的信息为" + biddtoStr);
								Long mqResult = messageSender
										.put(new Message(MessageType.HH_BIDDING_MESSAGE, biddtoStr));
								if (mqResult != null) {
									System.out.println("消息发送成功");
								} else {
									System.out.println("消息发送失败");
								}
							} else {
								// 富有冻结失败,则加上可投金额。
								Map<String, String> retumap = redisUtils.hgetAll(biddingOID);
								BigDecimal strhoAmount = new BigDecimal(retumap.get("holdingAmount"));
								BigDecimal nowMoney = strhoAmount.subtract(bidMoney);
								remap.put("holdingAmount", nowMoney.toString());
								redisUtils.hmset(biddingOID, remap);
							}
						}
						if (StringUtil.isNotEmpty(result) && "1".equals(result)) {
							if (StringUtil.isNotEmpty(biddto.getTqOID())) {
								// 如果特权不为空，则需要更改特权使用状态
								if (StringUtil.isNotEmpty(biddto.getMappingOID())) {
									PayPrivilegeMemberMapping mapping = privilegeMemberMappingMapper
											.findByOID(biddto.getMappingOID());
									mapping.setIsUsed("1");
									privilegeMemberMappingMapper.updatePrivilegeMemberMapping(mapping);
									if (StringUtil.isNotEmpty(mapping.getPsOID())) {
										// 如果psOID存在，说明是万能权，需要更改万能权特殊说明表
										PayPrivilegeSpecialps ps = privilegeSpecialPSMapper
												.findByOID(mapping.getPsOID());
										ps.setProductInfoOID(pdri.getOID());
										ps.setJXDay((int) (pdri.getProductTerm() * 30));
										ps.setIsUsed("1");
										privilegeSpecialPSMapper.updatePrivilegeSpecialPS(ps);
									}
								} else {
									// 如果用户同一特权有多张劵，把该特权劵都改为已使用
									List<PayPrivilegeMemberMapping> d = privilegeMemberMappingMapper
											.findByCondition(biddto.getTqOID(), biddto.getMemberOID());
									for (PayPrivilegeMemberMapping dto : d) {
										dto.setIsUsed("1");
										privilegeMemberMappingMapper.updatePrivilegeMemberMapping(dto);
									}
								}
							}
						}
					}
				}
			}
			return result;
		} catch (Exception e) {
			logger.error("投标失败" + e.getMessage());
			return null;
		}

	}

	@Override
	public String bidPackageBiddingRed(BiddingDTO biddto) {
		try {
			String result = "";
			if (biddto != null) {
				// 从reids中扣减可投金额
				// 团标产品投标
				List<String> redOidList = new ArrayList<String>();
				String[] redArray = biddto.getRedOID().split(",");
				BigDecimal allRedAmount = new BigDecimal(0);
				for (String redOid : redArray) {
					redOidList.add(redOid);
					RedPacketMemberMapping redPacketMemberMapping = redPacketMemberMappingMapper.selectByOid(redOid);
					BigDecimal redAmount = redPacketMemberMapping.getRedAmount();
					allRedAmount = allRedAmount.add(redAmount);// 累加所有红包金额
				}
				if (allRedAmount.compareTo(new BigDecimal(0)) > 0) {
					biddto.setAllRedAmount(allRedAmount);
				}
				result = controlDebitCredit.investmentRed(biddto);
				if ("1".equals(result)) {
					if (redOidList != null && redOidList.size() > 0) {
						// 修改红包状态为已使用
						redPacketMemberMappingMapper.updateIsUsed(redOidList);
					}
				}
				if ("1".equals(result)) {
					// 富有冻结成功 将环环标的数据放到redis队列中
					String biddtoStr = JsonUtil.toStr(biddto);
					System.err.println("发送的信息为" + biddtoStr);
					Long mqResult = messageSender.put(new Message(MessageType.HH_RED_BIDDING_MESSAGE, biddtoStr));
					if (mqResult != null) {
						System.out.println("消息发送成功");
					} else {
						System.out.println("消息发送失败");
					}
				} else {
					// 富有冻结失败,则加上可投金额。
				}
			}
			return result;
		} catch (Exception e) {
			logger.error("投标失败" + e.getMessage());
			return null;
		}
	}

	@Override
	public String bidPackageBiddingRedAndPrivilege(BiddingDTO biddto) {
		try {
			String result = "";
			if (biddto != null) {
				// 从reids中扣减可投金额
				WebP2pPackageBiddingMainRuning pbm = packageBiddingMainRuningMapper.findByOID(biddto.getBiddingOID());
				WebP2pProductRateInfo pdri = productRateInfoMapper.findByOID(pbm.getProductRateInfoID());
				// 团标产品投标
				BigDecimal privilegePrincipal = new BigDecimal(0);
				BigDecimal privilegeInterest = new BigDecimal(0);
				List<String> redOidList = new ArrayList<String>();
				if (StringUtil.isEmpty(biddto.getRedOID()) || StringUtil.isEmpty(biddto.getTqOID())) {
					biddto.setBeginDate(ToolDateTime.gainDate());
					// 将bitdto存入redis
					result = controlDebitCredit.investment(biddto);
				} else {
					// 计算红包金额
					String[] redArray = biddto.getRedOID().split(",");
					BigDecimal allRedAmount = new BigDecimal(0);
					for (String redOid : redArray) {
						redOidList.add(redOid);
						RedPacketMemberMapping redPacketMemberMapping = redPacketMemberMappingMapper
								.selectByOid(redOid);
						BigDecimal redAmount = redPacketMemberMapping.getRedAmount();
						allRedAmount = allRedAmount.add(redAmount);// 累加所有红包金额
					}
					if (allRedAmount.compareTo(new BigDecimal(0)) > 0) {
						biddto.setAllRedAmount(allRedAmount);
					}
					// 查询加息券利率
					PayPrivilege tq = privilegeMapper.findByOID(biddto.getTqOID());
					if (tq != null) {
						if (StringUtil.isNotEmpty(tq.getPrivilegeType()) && "1".equals(tq.getPrivilegeType())) {
							// 加息
							privilegeInterest = tq.getPrivilegeRange();
						} else if (tq.getPrivilegeType() != null && "0".equals(tq.getPrivilegeType())) {
							// 红包
							privilegePrincipal = tq.getPrivilegeRange();
						} else {
							logger.info("特权投标失败");
							return null;
						}
					}
					biddto.setPrivilegeInterest(privilegeInterest);
					biddto.setPrivilegePrincipal(privilegePrincipal);
					biddto.setBeginDate(ToolDateTime.gainDate());
					result = controlDebitCredit.investmentRedAndPrivilege(biddto);
					if (StringUtil.isNotEmpty(result) && "1".equals(result)) {
						// 将环环标的数据放到redis队列中
						String biddtoStr = JsonUtil.toStr(biddto);
						System.err.println("发送的信息为" + biddtoStr);
						Long mqResult = messageSender
								.put(new Message(MessageType.HH_RED_PRIVILEGE_BIDDING_MESSAGE, biddtoStr));
						if (mqResult != null) {
							System.out.println("消息发送成功");
						} else {
							System.out.println("消息发送失败");
						}
					}
				}
				// 修改红包使用状态
				if ("1".equals(result)) {
					if (redOidList != null && redOidList.size() > 0) {
						// 修改红包状态为已使用
						redPacketMemberMappingMapper.updateIsUsed(redOidList);
					}
				}
				// 修改特权使用状态
				if (StringUtil.isNotEmpty(result) && "1".equals(result)) {
					if (StringUtil.isNotEmpty(biddto.getTqOID())) {
						// 如果特权不为空，则需要更改特权使用状态
						if (StringUtil.isNotEmpty(biddto.getMappingOID())) {
							/**
							 * 更新 传入mappingOID回来，因app旧版本问题，如果mappOID为null，
							 * 需要按tqOID该更改特权状态
							 */
							PayPrivilegeMemberMapping mapping = privilegeMemberMappingMapper
									.findByOID(biddto.getMappingOID());
							mapping.setIsUsed("1");
							privilegeMemberMappingMapper.updatePrivilegeMemberMapping(mapping);
							if (StringUtil.isNotEmpty(mapping.getPsOID())) {
								// 如果psOID存在，说明是万能权，需要更改万能权特殊说明表
								PayPrivilegeSpecialps ps = privilegeSpecialPSMapper.findByOID(mapping.getPsOID());
								ps.setProductInfoOID(pdri.getOID());
								ps.setJXDay((int) (pdri.getProductTerm() * 30));
								ps.setIsUsed("1");
								privilegeSpecialPSMapper.updatePrivilegeSpecialPS(ps);
							}
						} else {
							// 如果用户同一特权有多张劵，把该特权劵都改为已使用
							List<PayPrivilegeMemberMapping> d = privilegeMemberMappingMapper
									.findByCondition(biddto.getTqOID(), biddto.getMemberOID());
							for (PayPrivilegeMemberMapping dto : d) {
								dto.setIsUsed("1");
								privilegeMemberMappingMapper.updatePrivilegeMemberMapping(dto);
							}
						}
					}
				}
			}
			return result;
		} catch (Exception e) {
			logger.error("投标失败" + e.getMessage());
			return null;
		}
	}

}
