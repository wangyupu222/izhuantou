package com.izhuantou.fund.rpc.impl.bidding;

import java.math.BigDecimal;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.izhuantou.damain.user.MemberMemberPrivilege;
import com.izhuantou.damain.vo.bidding.BiddingDTO;
import com.izhuantou.damain.webp2p.WebP2pLoanProductRateInfo;
import com.izhuantou.damain.webp2p.WebP2pNoviceBiddingRuning;
import com.izhuantou.damain.webp2p.WebP2pProductRateInfo;
import com.izhuantou.dao.user.MemberMemberPrivilegeMapper;
import com.izhuantou.dao.webp2p.WebP2pLoanProductRateInfoMapper;
import com.izhuantou.dao.webp2p.WebP2pNoviceBiddingRuningMapper;
import com.izhuantou.dao.webp2p.WebP2pProductRateInfoMapper;
import com.izhuantou.fund.rpc.api.bidding.BiddingCashFreeze;
import com.izhuantou.fund.rpc.api.bidding.NoviceBidding;
import com.izhuantou.fund.rpc.impl.BaseServiceImpl;

/**
 * 新手标的相关实现类
 *
 * @author fucheng
 * @date 2018-03-06
 */
@Service("noviceBidding")
public class NoviceBiddingImpl extends BaseServiceImpl<WebP2pNoviceBiddingRuning>
		implements NoviceBidding {
	private static final Logger logger = LoggerFactory.getLogger(NoviceBiddingImpl.class);

	@Autowired
	private BiddingCashFreeze controlDebitCredit;
	@Autowired
	private MemberMemberPrivilegeMapper memberMemberPrivilegeDao;
	@Autowired
	private WebP2pNoviceBiddingRuningMapper noviceBiddingRuningMapper;
	@Autowired
	private WebP2pProductRateInfoMapper ProductRateInfoMapper;
	@Autowired
	private WebP2pLoanProductRateInfoMapper loanProductRateInfoMapper;

	@Override
	public String bidNoviceBidding(BiddingDTO biddto) {
		try {
			// 确认用户的输入金额有没有超过头笔赚出借上限
			if (biddto != null) {
				if (biddto.getAmount().compareTo(new BigDecimal(10000)) > 0) {
					// 表示用户输入金额大于投标上限
					logger.info("投标失败,出借金额超过头笔赚产品出借上限，请重新输入出借金额");
					return null;
				} else {
					String result ="";
					// 表示可以投标
					WebP2pNoviceBiddingRuning noviceBid = this.noviceBiddingRuningMapper
							.findByOID(biddto.getBiddingOID());
					if (noviceBid != null) {
						WebP2pProductRateInfo productRate = this.ProductRateInfoMapper
								.findByOID(noviceBid.getProductRateInfoID());
						WebP2pLoanProductRateInfo productRateInfo = this.loanProductRateInfoMapper
								.findByOID(noviceBid.getLoanProductRateInfoID());
						// 表示可以投标
						if (noviceBid.getBiddingType().equals("0")) {
							// 普通标的
							if (StringUtils.isEmpty(biddto.getTqOID())) {
								biddto.setCreditRate(productRateInfo.getYearRate());
								biddto.setCreditType(productRate.getRepaymentType());
								// 投标完成后需要期数有借款期数 biddto需要放入 redis
								biddto.setTerm(productRateInfo.getTerm());
								
								result = this.controlDebitCredit.addDebitCreditNew(biddto);
								return result;
							} else {
								MemberMemberPrivilege tq = memberMemberPrivilegeDao.findByOID(biddto.getTqOID());
								if (tq != null) {
									if ("0".equals(tq.getState())) {
										BigDecimal privilegePrincipal = tq.getTqAmount();
										BigDecimal privilegeInterest = tq.getTqll();
										biddto.setCreditRate(productRateInfo.getYearRate());
										biddto.setCreditType(productRate.getRepaymentType());
										biddto.setPrivilegeInterest(privilegeInterest);
										biddto.setPrivilegePrincipal(privilegePrincipal);
										// 投标完成后需要期数有借款期数
										biddto.setTerm(productRateInfo.getTerm());
										
										result = controlDebitCredit.addDebitCreditNew(biddto);
										if(StringUtils.isNotEmpty(result)&&"1".equals(result)){
											tq.setState("1");
											tq.setBiddingName(biddto.getBiddingOID());
											// 修改状态
											memberMemberPrivilegeDao.updState(tq);
										}
									}
								} else {
									logger.info("特权不存在");
								}
							}
							return result;
						} else if (noviceBid.getBiddingType().equals("1")) {
							if (StringUtils.isEmpty(biddto.getTqOID())) {
								biddto.setDebitCreditOID(noviceBid.getDebitCreditOID());
								biddto.setOutMemberOID(noviceBid.getMemberOID());
								biddto.setBeginDate(noviceBid.getLoanDay());
								biddto.setTransferReturnDate(new Date());
								biddto.setIsTZorCW("XS");
								biddto.setCreditRate(productRateInfo.getYearRate());
								biddto.setCreditType(productRate.getRepaymentType());
								result=controlDebitCredit.addTransferReturnZZ(biddto);
							} else {
								MemberMemberPrivilege tq = memberMemberPrivilegeDao.findByOID(biddto.getTqOID());
								if (tq != null) {
									if ("0".equals(tq.getTqOID())) {
										BigDecimal privilegePrincipal = tq.getTqAmount();
										BigDecimal privilegeInterest = tq.getTqll();
										biddto.setCreditRate(productRateInfo.getYearRate());
										biddto.setCreditType(productRate.getRepaymentType());
										biddto.setPrivilegePrincipal(privilegePrincipal);
										biddto.setPrivilegeInterest(privilegeInterest);
										biddto.setDebitCreditOID(noviceBid.getDebitCreditOID());
										biddto.setOutMemberOID(biddto.getMemberOID());
										biddto.setTransferReturnDate(new Date());
										result=controlDebitCredit.addTransferReturn(biddto);
										if(StringUtils.isNotEmpty(result)&&"1".equals(result)){
											tq.setState("1");
											tq.setBiddingName(biddto.getBiddingOID());
											// 修改状态
											memberMemberPrivilegeDao.updState(tq);
										}
									}
								}
							}			
							return result;
						} else {
							logger.info("======对应标的类型不匹配========");
						}
					}
				}
			}
			logger.info("======投标失败=======");
			return null;
		} catch (Exception e) {
			logger.error("bidNoviceBidding(BiddingDTO biddto)" + e.getMessage());
			return null;
		}

	}

	
	
	
}
