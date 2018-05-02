package com.izhuantou.fund.rpc.impl;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.izhuantou.common.tool.ToolDateTime;
import com.izhuantou.damain.pay.PayCashPool;
import com.izhuantou.damain.pay.PayPrivilege;
import com.izhuantou.damain.pay.PayPrivilegeMemberMapping;
import com.izhuantou.damain.pay.PayPrivilegeSpecialps;
import com.izhuantou.damain.vo.bidding.BiddingDTO;
import com.izhuantou.damain.webp2p.WebP2pLoanProductRateInfo;
import com.izhuantou.damain.webp2p.WebP2pPackageBiddingMainContentRuning;
import com.izhuantou.damain.webp2p.WebP2pPackageBiddingMainRuning;
import com.izhuantou.damain.webp2p.WebP2pProductRateInfo;
import com.izhuantou.dao.pay.PayPrivilegeMapper;
import com.izhuantou.dao.pay.PayPrivilegeMemberMappingMapper;
import com.izhuantou.dao.pay.PayPrivilegeSpecialpsMapper;
import com.izhuantou.dao.webp2p.WebP2pLoanProductRateInfoMapper;
import com.izhuantou.dao.webp2p.WebP2pPackageBiddingMainContentRuningMapper;
import com.izhuantou.dao.webp2p.WebP2pPackageBiddingMainRuningMapper;
import com.izhuantou.dao.webp2p.WebP2pProductRateInfoMapper;
import com.izhuantou.fund.rpc.api.ControlDebitCredit;
import com.izhuantou.fund.rpc.api.ControlPackageBiddingMainRuning;
import com.izhuantou.fund.rpc.api.ControlPackageMainContentMapping;
import com.izhuantou.fund.rpc.api.ControlPrivilegeMemberMapping;
import com.izhuantou.service.api.user.MemberMemberAgreementService;
import com.izhuantou.third.rpc.api.ControlPayService;

@Service("ControlPackageBiddingMainRuning")
public class ControlPackageBiddingMainRuningImpl extends BaseServiceImpl<WebP2pPackageBiddingMainRuning>
	implements ControlPackageBiddingMainRuning {
    private static final Logger logger = LoggerFactory.getLogger(ControlPackageBiddingMainRuningImpl.class);
    @Autowired
    private ControlDebitCredit controlDebitCredit;
    @Autowired
    private ControlPackageMainContentMapping controlPackageMainContentMapping;
    @Autowired
    private ControlPrivilegeMemberMapping controlPrivilegeMemberMapping;
    @Autowired
    private ControlPayService controlPay;
    @Autowired
    private PayPrivilegeMapper privilegeMapper;
    @Autowired
    private PayPrivilegeMemberMappingMapper privilegeMemberMappingMapper;
    @Autowired
    private PayPrivilegeSpecialpsMapper privilegeSpecialPSMapper;
    @Autowired
    private WebP2pLoanProductRateInfoMapper loanProductRateInfoMapper;
    @Autowired
    private WebP2pPackageBiddingMainContentRuningMapper packageBiddingMainContentRuningMapper;
    @Autowired
    private WebP2pPackageBiddingMainRuningMapper packageBiddingMainRuningMapper;
    @Autowired
    private WebP2pProductRateInfoMapper productRateInfoMapper;
    @Autowired
    private MemberMemberAgreementService memberAgreementService;

    @Override
    public String bidPackageBiddingDifferent(BiddingDTO biddto) {
	try {
	    if (biddto != null) {
		WebP2pPackageBiddingMainRuning pbm = packageBiddingMainRuningMapper.findByOID(biddto.getBiddingOID());
		WebP2pProductRateInfo pdri = productRateInfoMapper.findByOID(pbm.getProductRateInfoID());
		// 团标产品投标
		PayCashPool aDto = new PayCashPool();
		BigDecimal privilegePrincipal = new BigDecimal(0);
		BigDecimal privilegeInterest = new BigDecimal(0);
		if (StringUtils.isEmpty(biddto.getTqOID())) {

		    biddto.setBeginDate(ToolDateTime.gainDate());
		    aDto = controlDebitCredit.investment(biddto);
		} else {
		    PayPrivilege tq = privilegeMapper.findByOID(biddto.getTqOID());
		    if (tq != null) {
			if (tq.getPrivilegeType() != null && "1".equals(tq.getPrivilegeType())) {
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
		    aDto = controlDebitCredit.investment(biddto);
		}

		memberAgreementService.gainMemberHHTAgreement(biddto.getMemberOID(), "4", biddto.getBiddingOID(),
			aDto.getOID());
		BigDecimal ytje = pbm.getHoldingAmount();// 已投金额
		BigDecimal newytje = ytje.add(biddto.getAmount());
		pbm.setHoldingAmount(newytje);
		// 保存信息
		packageBiddingMainRuningMapper.updatePackageBiddingMainRuning(pbm);

		// TODO 内部为修改
		this.cashPoolCashInvestmentDifferent(biddto.getLaiyuan());

		// -------------------------------
		if (biddto != null && StringUtils.isNotEmpty(biddto.getTqOID())) {
		    // 如果特权不为空，则需要更改特权使用状态
		    if (StringUtils.isNotEmpty(biddto.getMappingOID())) {
			// 传入mappingOID回来，因app旧版本问题，如果mappOID为null，需要按tqOID该更改特权状态
			PayPrivilegeMemberMapping mapping = privilegeMemberMappingMapper
				.findByOID(biddto.getMappingOID());
			mapping.setIsUsed("1");
			privilegeMemberMappingMapper.updatePrivilegeMemberMapping(mapping);
			if (mapping != null && StringUtils.isNotEmpty(mapping.getPsOID())) {
			    // 如果psOID存在，说明是万能权，需要更改万能权特殊说明表
			    PayPrivilegeSpecialps ps = privilegeSpecialPSMapper.findByOID(mapping.getPsOID());
			    ps.setProductInfoOID(pdri.getOID());
			    ps.setJXDay((int) (pdri.getProductTerm() * 30));
			    ps.setIsUsed("1");
			    privilegeSpecialPSMapper.updatePrivilegeSpecialPS(ps);
			}
		    } else {
			List<PayPrivilegeMemberMapping> d = this.controlPrivilegeMemberMapping
				.gainByTqOIDAndMemebrOID(biddto.getTqOID(), biddto.getMemberOID());
			for (PayPrivilegeMemberMapping dto : d) {
			    dto.setIsUsed("1");
			    privilegeMemberMappingMapper.updatePrivilegeMemberMapping(dto);
			}
		    }
		}
		// **************产品限额 terry*****************
		BigDecimal zero = new BigDecimal(0);
		BigDecimal xe = (BigDecimal) pbm.getSxAmount();
		BigDecimal yt = (BigDecimal) pbm.getHoldingAmount();
		if (xe.compareTo(zero) != 0) {
		    // 如果已投大于限额将产品停标
		    if (yt.compareTo(xe) >= 0) {
			pbm.setProductStatus("10");
			packageBiddingMainRuningMapper.updatePackageBiddingMainRuning(pbm);
		    }
		}
		return "1";
	    }
	    return null;
	} catch (Exception e) {
	    logger.error("投标失败" + e.getMessage());
	    return null;
	}

    }

    /**
     * 资金池投资内容标的
     * 
     * @param ly(来源)
     */
    private void cashPoolCashInvestmentDifferent(String ly) {
	try {
	    // controlPackageBiddingMainContentRuning
	    // 赋值更改 terry，原先赋值方法测试出错
	    List<WebP2pPackageBiddingMainRuning> dcallBM = new ArrayList<WebP2pPackageBiddingMainRuning>();
	    dcallBM = packageBiddingMainRuningMapper.findAll();
	    if (dcallBM != null && dcallBM.size() > 0) {
		for (WebP2pPackageBiddingMainRuning dtoBiddingMain : dcallBM) {
		    List<List> biddingInvestListBus = new LinkedList<List>();
		    String mainBiddingOID = dtoBiddingMain.getOID();
		    String productRateInfoID = dtoBiddingMain.getProductRateInfoID();
		    BigDecimal bdlcash = this.controlDebitCredit.gainCashPoolCash(mainBiddingOID);
		    BigDecimal bdzero = new BigDecimal("0");
		    WebP2pProductRateInfo dtoProductRateInfoID = productRateInfoMapper.findByOID(productRateInfoID);

		    String strfullstrategy = dtoProductRateInfoID.getFullstrategy();
		    if (StringUtils.isNotEmpty(strfullstrategy)) {
			String[] sortRule = strfullstrategy.split("/");
			for (int i = 0; i < sortRule.length; i++) {
			    LinkedList list = new LinkedList();
			    list.add(Double.valueOf(sortRule[i]));
			    biddingInvestListBus.add(list);

			}
		    }
		    List<WebP2pPackageBiddingMainContentRuning> dtocRealBidding = this.controlPackageMainContentMapping
			    .gainRealBiddingByMainOID(mainBiddingOID);
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
		    // 排列完成开始投资
		    for (int i = 0; i < biddingInvestListBus.size() && bdlcash.compareTo(bdzero) != 0; i++) {
			List list = biddingInvestListBus.get(i);
			for (int j = 1; j < list.size() && bdlcash.compareTo(bdzero) != 0; j++) {
			    WebP2pPackageBiddingMainContentRuning dtoInvest = (WebP2pPackageBiddingMainContentRuning) list
				    .get(j);
			    boolean isYZDG = false;
			    String loanTittle = "";
			    String realBiddingOID = dtoInvest.getOID();
			    String financetransfertype = dtoInvest.getFinancetransfertype();
			    // --------根据标的OID查询标的编号--------
			    WebP2pPackageBiddingMainContentRuning temp = packageBiddingMainContentRuningMapper
				    .findByOID(realBiddingOID);

			    loanTittle = ((String) temp.getLoanNumber()).substring(0, 3);
			    if (loanTittle.equals("ICD")) {
				isYZDG = true;
			    }
			    // true为普通标的false财务债转
			    BigDecimal holdingAmount = dtoInvest.getHoldingAmount();
			    BigDecimal biddingAmount = dtoInvest.getApplyAmount();
			    BigDecimal MayInvestAmount = biddingAmount.subtract(holdingAmount);

			    if ("2".equals(dtoInvest.getProductStatus())) {
				continue;// 满标跳出此次投资
			    }
			    if ("normal".equals(financetransfertype)) {
				if (bdlcash.compareTo(MayInvestAmount) >= 0) {
				    // 满标
				    controlDebitCredit.finishInvestment(mainBiddingOID, realBiddingOID, MayInvestAmount,
					    ly);
				    Date beginDate = ToolDateTime.gainDate();
				    controlDebitCredit.finishDebitCredit(realBiddingOID, beginDate);
				    bdlcash = bdlcash.subtract(MayInvestAmount);
				    dtoInvest.setHoldingAmount(biddingAmount);
				    dtoInvest.setProductStatus("2");

				    dtoInvest.setBiddingType("1");
				    dtoInvest.setLoanDay(ToolDateTime.gainDate().toString());

				    packageBiddingMainContentRuningMapper
					    .updatePackageBiddingMainContentRuning(dtoInvest);

				    if (isYZDG) {
					// TODO 协议 jasen 2018/3/22 ？？？
					memberAgreementService.gainMemberHHTNRBJKAgreementDifferent("16",
						realBiddingOID);
					memberAgreementService.gainmemberzqhgHHTAgreementDifferent("18",
						realBiddingOID);
				    } else {
					this.memberAgreementService.gainMemberHHTNRBJKAgreement("6", realBiddingOID);
					this.memberAgreementService.gainmemberzqhgHHTAgreement("8", realBiddingOID);
				    }

				} else {
				    this.controlDebitCredit.finishInvestment(mainBiddingOID, realBiddingOID, bdlcash,
					    ly);
				    holdingAmount = holdingAmount.add(bdlcash);
				    bdlcash = bdlcash.subtract(bdlcash);
				    dtoInvest.setHoldingAmount(holdingAmount);
				    this.packageBiddingMainContentRuningMapper
					    .updatePackageBiddingMainContentRuning(dtoInvest);
				}

			    } else if ("financetransfer".equals(financetransfertype)) {
				// 财务满标债权资金池投标
				String debitCreditOID = (String) dtoInvest.getDebitCreditOID();
				if (bdlcash.compareTo(MayInvestAmount) >= 0) {
				    // 满标
				    Date nowDateTime = ToolDateTime.gainDate();
				    this.controlDebitCredit.addCashPoolTransferReturnNEW(realBiddingOID, debitCreditOID,
					    mainBiddingOID, MayInvestAmount, nowDateTime, ly);
				    this.controlDebitCredit.finishTransferReturnNEW(realBiddingOID, nowDateTime, "HH",
					    "HH", ly);

				    bdlcash = bdlcash.subtract(MayInvestAmount);
				    dtoInvest.setHoldingAmount(biddingAmount);
				    dtoInvest.setProductStatus("2");
				    dtoInvest.setBiddingType("1");
				    dtoInvest.setLoanDay(ToolDateTime.gainDate().toString());
				    this.packageBiddingMainContentRuningMapper
					    .updatePackageBiddingMainContentRuning(dtoInvest);

				    if (isYZDG) {
					// TODO 协议
					this.memberAgreementService.gainmemberzqhgHHTAgreementDifferent("18",
						realBiddingOID);
					this.memberAgreementService.gainmemberHHTZQZRCHUJIEAgreement("17",
						realBiddingOID);
				    } else {
					// TODO 协议
					this.memberAgreementService.gainmemberzqhgHHTAgreement("8", realBiddingOID);
					this.memberAgreementService.gainmemberHHTZQZRCHUJIEAgreement("7",
						realBiddingOID);
				    }

				    controlPay.unFreeze(temp.getMemberOID(), temp.getBiddingAmount());
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
				    this.controlDebitCredit.addCashPoolUserTransferReturnNEW(realBiddingOID,
					    debitCreditOID, mainBiddingOID, MayInvestAmount, nowDateTime, ly);
				    this.controlDebitCredit.finishTransferReturnNEW(realBiddingOID, nowDateTime, "HH",
					    "HH", ly);
				    bdlcash = bdlcash.subtract(MayInvestAmount);
				    dtoInvest.setHoldingAmount(biddingAmount);
				    dtoInvest.setProductStatus("2");
				    dtoInvest.setBiddingType("1");
				    dtoInvest.setLoanDay(ToolDateTime.gainDate().toString());
				    this.packageBiddingMainContentRuningMapper
					    .updatePackageBiddingMainContentRuning(dtoInvest);
				    // TODO 协议
				    memberAgreementService.gainmemberzqhgHHTAgreement("8", realBiddingOID);
				    memberAgreementService.gainmemberHHTZQZRCHUJIEAgreement("7", realBiddingOID);
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
	    }
	} catch (Exception e) {
	    logger.error("站岗资金投标" + e.getMessage());
	}
    }
}
