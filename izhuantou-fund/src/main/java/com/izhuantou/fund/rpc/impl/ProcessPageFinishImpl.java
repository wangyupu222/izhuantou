package com.izhuantou.fund.rpc.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.izhuantou.common.tool.ToolDateTime;
import com.izhuantou.common.utils.StringUtil;
import com.izhuantou.damain.pay.PayTransferReturn;
import com.izhuantou.damain.vo.bidding.BiddingDTO;
import com.izhuantou.damain.webp2p.WebP2pLoanProductRateInfo;
import com.izhuantou.damain.webp2p.WebP2pPackageBiddingMainContentRuning;
import com.izhuantou.damain.webp2p.WebP2pPackageBiddingMainRuning;
import com.izhuantou.damain.webp2p.WebP2pProductRateInfo;
import com.izhuantou.dao.pay.PayDebitCreditMapper;
import com.izhuantou.dao.pay.PayTransferReturnMapper;
import com.izhuantou.dao.webp2p.WebP2pLoanProductRateInfoMapper;
import com.izhuantou.dao.webp2p.WebP2pPackageBiddingMainContentRuningMapper;
import com.izhuantou.dao.webp2p.WebP2pPackageBiddingMainRuningMapper;
import com.izhuantou.dao.webp2p.WebP2pProductRateInfoMapper;
import com.izhuantou.fund.rpc.api.ControlDebitCredit;
import com.izhuantou.fund.rpc.api.ProcessPageFinish;
import com.izhuantou.third.rpc.api.ControlPayService;
import com.izhuantou.third.rpc.api.memberagrement.MemberMemberAgreementService;

@Service("processPageFinish")
public class ProcessPageFinishImpl implements ProcessPageFinish {
	private static final Logger logger = LoggerFactory.getLogger(ProcessPageFinishImpl.class);
	@Autowired
	private ControlDebitCredit controlDebitCredit;
	@Autowired
	private PayTransferReturnMapper transferReturnMapper;
	@Autowired
	private PayDebitCreditMapper debitCreditMapper;
	@Autowired
	private MemberMemberAgreementService memberMemberAgreementService;
	@Autowired
	private ControlPayService controPayService;
	@Autowired
	private WebP2pPackageBiddingMainRuningMapper packageBiddingMainRuningMapper;
	@Autowired
	private WebP2pProductRateInfoMapper productRateInfoMapper;
	@Autowired
	private WebP2pPackageBiddingMainContentRuningMapper packageBiddingMainContentRuningMapper;
	@Autowired
	private WebP2pLoanProductRateInfoMapper loanProductRateInfoMapper;

	@Override
	public String noviceBiddingFinish(String biddingOID) {
		try {
			// 调用满标流程
			Date beginDate = ToolDateTime.gainDate();
			controlDebitCredit.finishDebitCreditNEW(biddingOID, beginDate);
			// 满标后生成借款协议
			memberMemberAgreementService.gainMemberxsJKAgreement("6", biddingOID);
			// 满标后生成逾期债权预回购协议
			memberMemberAgreementService.gainmemberzqhgXSAgreement("8", biddingOID);
		} catch (Exception e) {
			logger.error("noviceBiddingFinish(String biddingOID)" + e.getMessage());
		}
		return null;
	}

	@Override
	public String noviceZzBiddingFinish(BiddingDTO biddto) {
		try {
			biddto.setBeginDate(new Date());
			biddto.setIsTZorCW("XS");
			biddto.setTzType("XS");
			controlDebitCredit.finishTransferReturnNEW(biddto);
			List<PayTransferReturn> reList = transferReturnMapper.findByBusinenssOID(biddto.getBiddingOID());
			// 取最后一条 按时间
			String debitOID = "";
			debitOID = reList.get(reList.size() - 1).getDebitCreditOID();

			String outMeber = debitCreditMapper.queryByPKOid(debitOID).getOutMemberOID();
			// 此处钱数为标的 的金额 不是个人投资的金额
			BigDecimal bdje = biddto.getAmount();
			controPayService.unFreeze(outMeber, bdje);
			// 财务标的满标后生成债权转让协议
			memberMemberAgreementService.gainmemberTbzZZTZQZRAgreement("7", biddto.getBiddingOID());
			// 生成债权预回购协议l
			memberMemberAgreementService.gainmemberxszqhgZZTAgreement("8", biddto.getBiddingOID());
		} catch (Exception e) {
			logger.error("noviceZzBiddingFinish(BiddingDTO biddto)" + e.getMessage());
		}
		return null;
	}

	@Override
	public void packageBiddingMainRuningMatching(String ly) {
		try {
			List<WebP2pPackageBiddingMainRuning> dcallBM = packageBiddingMainRuningMapper.findAll();
			long startTime = System.currentTimeMillis();
			System.err.println("biddingMainRuningMatching(String ly))"+startTime);
			for (WebP2pPackageBiddingMainRuning dtoBiddingMain : dcallBM) {
				List<List> biddingInvestListBus = new LinkedList<List>();
				String mainBiddingOID = dtoBiddingMain.getOID();
				String productRateInfoID = dtoBiddingMain.getProductRateInfoID();
				BigDecimal bdlcash = this.controlDebitCredit.gainCashPoolCash(mainBiddingOID);
				BigDecimal bdzero = new BigDecimal("0");
				WebP2pProductRateInfo dtoProductRateInfoID = productRateInfoMapper.findByOID(productRateInfoID);
				String strfullstrategy = dtoProductRateInfoID.getFullstrategy();
				if (StringUtil.isNotEmpty(strfullstrategy)) {
					String[] sortRule = strfullstrategy.split("/");
					for (int i = 0; i < sortRule.length; i++) {
						LinkedList list = new LinkedList();
						list.add(Double.valueOf(sortRule[i]));
						biddingInvestListBus.add(list);
					}
				}
				List<WebP2pPackageBiddingMainContentRuning> dtocRealBidding = packageBiddingMainContentRuningMapper
						.findRealBiddingByMainOID(mainBiddingOID);
				for (WebP2pPackageBiddingMainContentRuning dtoRealBidding : dtocRealBidding) {
					String loanProductRateInfoID = dtoRealBidding.getLoanProductRateInfoID();
					WebP2pLoanProductRateInfo dtoLoanProductRateInfoID = loanProductRateInfoMapper
							.findByOID(loanProductRateInfoID);
					Double loanTerm = Double.valueOf(dtoLoanProductRateInfoID.getTerm());
					for (int i = 0; i < biddingInvestListBus.size(); i++) {
						List list = biddingInvestListBus.get(i);
						Double ruleterm = (Double) list.get(0);
						if (ruleterm.compareTo(loanTerm) == 0) {
							list.add(dtoRealBidding);
							break;
						}
					}
				}
				for (int i = 0; i < biddingInvestListBus.size() && bdlcash.compareTo(bdzero) != 0; i++) {
					List list = biddingInvestListBus.get(i);
					for (int j = 1; j < list.size() && bdlcash.compareTo(bdzero) != 0; j++) {
						WebP2pPackageBiddingMainContentRuning dtoInvest = (WebP2pPackageBiddingMainContentRuning) list
								.get(j);
						boolean isYZDG = false;
						String realBiddingOID = dtoInvest.getOID();
						String financetransfertype = dtoInvest.getFinancetransfertype();
						WebP2pPackageBiddingMainContentRuning temp = packageBiddingMainContentRuningMapper
								.findByOID(realBiddingOID);
						String loanTittle = temp.getLoanNumber().substring(0, 3);
						if (loanTittle.equals("ICD")) {
							isYZDG = true;
						}

						BigDecimal holdingAmount = dtoInvest.getHoldingAmount();
						BigDecimal biddingAmount = dtoInvest.getBiddingAmount();
						BigDecimal MayInvestAmount = biddingAmount.subtract(holdingAmount);
						if ("2".equals(dtoInvest.getProductStatus())) {
							continue;// 满标跳出此次投资
						}
						if ("normal".equals(financetransfertype)) {
							if (bdlcash.compareTo(MayInvestAmount) >= 0) {
								controlDebitCredit.finishInvestment(mainBiddingOID, realBiddingOID, MayInvestAmount,
										ly);
								Date beginDate = ToolDateTime.gainDate();
								controlDebitCredit.finishDebitCredit(realBiddingOID, beginDate);
								bdlcash = bdlcash.subtract(MayInvestAmount);
								dtoInvest.setHoldingAmount(biddingAmount);
								dtoInvest.setProductStatus("2");
								dtoInvest.setBiddingType("1");
								dtoInvest.setLoanDay(ToolDateTime.gainDate().toString());
								packageBiddingMainContentRuningMapper.updatePackageBiddingMainContentRuning(dtoInvest);
								if (isYZDG) {
									memberMemberAgreementService.gainMemberHHTNRBJKAgreementDifferent("16",
											realBiddingOID);
									memberMemberAgreementService.gainmemberzqhgHHTAgreementDifferent("18",
											realBiddingOID);
								} else {
									this.memberMemberAgreementService.gainMemberHHTNRBJKAgreement("6", realBiddingOID);
									this.memberMemberAgreementService.gainmemberzqhgHHTAgreement("8", realBiddingOID);
								}
							} else {
								this.controlDebitCredit.finishInvestment(mainBiddingOID, realBiddingOID, bdlcash, ly);
								holdingAmount = holdingAmount.add(bdlcash);
								bdlcash = bdlcash.subtract(bdlcash);
								dtoInvest.setHoldingAmount(holdingAmount);
								this.packageBiddingMainContentRuningMapper
										.updatePackageBiddingMainContentRuning(dtoInvest);

							}

						} else if ("financetransfer".equals(financetransfertype)) {
							String debitCreditOID = dtoInvest.getDebitCreditOID();
							if (bdlcash.compareTo(MayInvestAmount) >= 0) {
								Date nowDateTime = ToolDateTime.gainDate();
								this.controlDebitCredit.addCashPoolTransferReturnNEW(realBiddingOID, debitCreditOID,
										mainBiddingOID, MayInvestAmount, nowDateTime, ly);
								this.controlDebitCredit.finishTransferReturnNEW(realBiddingOID, nowDateTime, "HH", "HH",
										ly);
								bdlcash = bdlcash.subtract(MayInvestAmount);
								dtoInvest.setHoldingAmount(biddingAmount);
								dtoInvest.setProductStatus("2");
								dtoInvest.setBiddingType("1");
								dtoInvest.setLoanDay(ToolDateTime.gainDate().toString());
								this.packageBiddingMainContentRuningMapper
										.updatePackageBiddingMainContentRuning(dtoInvest);

								if (isYZDG) {
									this.memberMemberAgreementService.gainmemberzqhgHHTAgreementDifferent("18",
											realBiddingOID);
									this.memberMemberAgreementService.gainmemberHHTZQZRCHUJIEAgreement("17",
											realBiddingOID);
								} else {
									this.memberMemberAgreementService.gainmemberzqhgHHTAgreement("8", realBiddingOID);
									this.memberMemberAgreementService.gainmemberHHTZQZRCHUJIEAgreement("7",
											realBiddingOID);
								}
								controPayService.unFreeze(temp.getMemberOID(), temp.getBiddingAmount());
							} else {
								Date nowDateTime = ToolDateTime.gainDate();

								controlDebitCredit.addCashPoolTransferReturnNEW(realBiddingOID, debitCreditOID,
										mainBiddingOID, bdlcash, nowDateTime, ly);

								holdingAmount = holdingAmount.add(bdlcash);
								bdlcash = bdlcash.subtract(bdlcash);
								dtoInvest.setHoldingAmount(holdingAmount);

								this.packageBiddingMainContentRuningMapper
										.updatePackageBiddingMainContentRuning(dtoInvest);
							}
						} else if ("Usertransfer".equals(financetransfertype)) {
							// 财务满标债权资金池投标
							String debitCreditOID = (String) dtoInvest.getDebitCreditOID();
							if (bdlcash.compareTo(MayInvestAmount) >= 0) {
								// 满标
								Date nowDateTime = ToolDateTime.gainDate();
								this.controlDebitCredit.addCashPoolUserTransferReturnNEW(realBiddingOID, debitCreditOID,
										mainBiddingOID, MayInvestAmount, nowDateTime, ly);
								this.controlDebitCredit.finishTransferReturnNEW(realBiddingOID, nowDateTime, "HH", "HH",
										ly);
								bdlcash = bdlcash.subtract(MayInvestAmount);
								dtoInvest.setHoldingAmount(biddingAmount);
								dtoInvest.setProductStatus("2");
								dtoInvest.setBiddingType("1");
								dtoInvest.setLoanDay(ToolDateTime.gainDate().toString());
								this.packageBiddingMainContentRuningMapper
										.updatePackageBiddingMainContentRuning(dtoInvest);
								memberMemberAgreementService.gainmemberzqhgHHTAgreement("8", realBiddingOID);
								memberMemberAgreementService.gainmemberHHTZQZRCHUJIEAgreement("7", realBiddingOID);
							} else {
								Date nowDateTime = ToolDateTime.gainDate();
								controlDebitCredit.addCashPoolUserTransferReturnNEW(realBiddingOID, debitCreditOID,
										mainBiddingOID, bdlcash, nowDateTime, ly);
								holdingAmount = holdingAmount.add(bdlcash);
								bdlcash = bdlcash.subtract(bdlcash);
								dtoInvest.setHoldingAmount(holdingAmount);
								this.packageBiddingMainContentRuningMapper
										.updatePackageBiddingMainContentRuning(dtoInvest);
							}
						}
					}
				}
			}
			long endTime =System.currentTimeMillis();
			System.out.println("站岗资金投标总耗时"+(endTime-startTime));
		} catch (Exception e) {
			logger.error("biddingMainRuningMatching(String ly)站岗资金投标" + e.getMessage());
		}
	}

}
