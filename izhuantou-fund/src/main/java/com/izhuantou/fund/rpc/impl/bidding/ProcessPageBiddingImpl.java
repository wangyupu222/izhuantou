package com.izhuantou.fund.rpc.impl.bidding;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.izhuantou.common.utils.StringUtil;
import com.izhuantou.damain.lend.PlanContent;
import com.izhuantou.damain.pay.PayCustomerTyj;
import com.izhuantou.damain.user.MemberMember;
import com.izhuantou.damain.vo.bidding.BiddingDTO;
import com.izhuantou.damain.webp2p.WebP2pNoviceBiddingRuning;
import com.izhuantou.damain.webp2p.WebP2pPackageBiddingMainRuning;
import com.izhuantou.dao.PropPrivilegeMapper;
import com.izhuantou.dao.lend.GainNumXSMapper;
import com.izhuantou.dao.lend.PlanContentMapper;
import com.izhuantou.dao.pay.PayCustomerTyjMapper;
import com.izhuantou.dao.user.MemberMemberMapper;
import com.izhuantou.dao.webp2p.WebP2pNoviceBiddingRuningMapper;
import com.izhuantou.dao.webp2p.WebP2pPackageBiddingMainRuningMapper;
import com.izhuantou.fund.rpc.api.ControlPrivilegeSpecialPS;
import com.izhuantou.fund.rpc.api.bidding.NoviceBidding;
import com.izhuantou.fund.rpc.api.bidding.PackageBiddingMainRuning;
import com.izhuantou.fund.rpc.api.bidding.ProcessPageBidding;

/**
 * 投标相关接口的实现类
 *
 * @author fucheng
 * @date 2018-03-06
 */
@Service("processPageBidding")
public class ProcessPageBiddingImpl implements ProcessPageBidding {
	private static final Logger logger = LoggerFactory.getLogger(ProcessPageBiddingImpl.class);
	@Autowired
	private NoviceBidding controlNoviceBidding;
	@Autowired
	private PackageBiddingMainRuning controlPackageBiddingMainRuning;
	@Autowired
	private WebP2pNoviceBiddingRuningMapper noviceBiddingRuningMapper;
	@Autowired
	private WebP2pPackageBiddingMainRuningMapper packageBiddingMainRuningMapper;
	@Autowired
	private MemberMemberMapper memberMemberMapper;
	@Autowired
	private PropPrivilegeMapper propPrivilegeMapper;
	@Autowired
	private PayCustomerTyjMapper payCustomerTyjMapper;
	@Autowired
	private GainNumXSMapper gainNumXSMapper;
	@Autowired
	private PlanContentMapper planContentMapper;
	@Autowired
	private ControlPrivilegeSpecialPS controlPrivilegeSpecialPS;

	@Override
	public String ProcessPageNewChujiesave(BiddingDTO biddto) {
		try {
			if (biddto != null) {
				// 判断是否可以投新手标
				Integer totalNumber = gainNumXSMapper.findByMemberOID(biddto.getMemberOID());
				if (totalNumber > 0) {
					// 不能投新手标
					return "已投过新手标";
				}
				// TODO 读取redis
				String res = "";
				WebP2pNoviceBiddingRuning noviceBid = this.noviceBiddingRuningMapper.findByOID(biddto.getBiddingOID());
				if (noviceBid != null && "1".equals(noviceBid.getProductStatus())) {
					res = this.controlNoviceBidding.bidNoviceBidding(biddto);
					if (StringUtils.isNotEmpty(res) && "1".equals(res)) {
						logger.info("======新手出借成功========");
						// 将参数存入redis
						return res;
					} else {
						logger.info("======新手出借失败========" + res);
						return res;
					}
				}
			}
		} catch (Exception e) {
			logger.error("ProcessPageNewChujiesave(BiddingDTO biddto)" + e.getMessage());
		}
		return null;
	}

	@Override
	public String ProcessPageHHChujiesave(BiddingDTO biddto) {
		try {
			String result = "";
			if (biddto != null) {
				// 环环
				WebP2pPackageBiddingMainRuning mainrun = packageBiddingMainRuningMapper
						.findByOID(biddto.getBiddingOID());
				if (mainrun != null && "1".equals(mainrun.getProductStatus())) {
					if (StringUtil.isNotEmpty(biddto.getRedOID()) && StringUtil.isEmpty(biddto.getTqOID())) {
						result = controlPackageBiddingMainRuning.bidPackageBiddingRed(biddto);
					} else if (StringUtil.isNotEmpty(biddto.getRedOID()) && StringUtil.isNotEmpty(biddto.getTqOID())){
						result = controlPackageBiddingMainRuning.bidPackageBiddingRedAndPrivilege(biddto);
						if (StringUtils.isNotEmpty(result) && "1".equals(result)) {
							// 获取线程状态
							String className = "cn.com.hoonsoft.WebP2P.PrivilegeMemberReturnSend";
							PlanContent planContent = planContentMapper.selectByClassName(className);
							if (planContent != null) {
								if ("start".equals(planContent.getState())) {
									controlPrivilegeSpecialPS.savePrivilegePs(biddto.getMemberOID(), biddto.getAmount(),
											biddto.getBiddingOID());
								}
							}
							logger.info("======环环投出借成功=====");
							return result;
						}
					} else {
						result = controlPackageBiddingMainRuning.bidPackageBiddingDifferent(biddto);
						if (StringUtils.isNotEmpty(result) && "1".equals(result)) {
							// 获取线程状态
							String className = "cn.com.hoonsoft.WebP2P.PrivilegeMemberReturnSend";
							PlanContent planContent = planContentMapper.selectByClassName(className);
							if (planContent != null) {
								if ("start".equals(planContent.getState())) {
									controlPrivilegeSpecialPS.savePrivilegePs(biddto.getMemberOID(), biddto.getAmount(),
											biddto.getBiddingOID());
								}
							}
							logger.info("======环环投出借成功=====");
							return result;
						}
					}
				} else {
					// 提示信息
					logger.info("======环环投出借失败=====");
				}
			}
			return result;
		} catch (Exception e) {
			logger.error("ProcessPageHHChujiesave(BiddingDTO biddto)" + e.getMessage());
			return null;
		}
	}

	@Override
	public String ProcessPageDDChujiesave(BiddingDTO biddto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String ProcessPageZZChujiesave(BiddingDTO biddto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer processPageTYJsave(String memberOID) {
		// 获取用户信息
		MemberMember memberMember = memberMemberMapper.findUserByOID(memberOID);
		// 获取体验金的信息
		Map<String, String> privilege = propPrivilegeMapper.findPrivilege();
		BigDecimal tyjPrice = new BigDecimal(privilege.get("TYJSY"));
		// 保存体验标信息
		if (memberMember != null) {
			PayCustomerTyj payCustomerTyj = new PayCustomerTyj();
			payCustomerTyj.setOID(StringUtil.getUUID());
			payCustomerTyj.setMemberOID(memberOID);
			payCustomerTyj.setMobile(memberMember.getName());
			payCustomerTyj.setNameCN(memberMember.getNameCN());
			payCustomerTyj.setTyjPrice(tyjPrice);
			payCustomerTyj.setIsUsed("0");
			payCustomerTyj.setAddDateTime(new Date());
			Integer row = payCustomerTyjMapper.save(payCustomerTyj);
			return row;
		} else {
			return 0;
		}
	}
}