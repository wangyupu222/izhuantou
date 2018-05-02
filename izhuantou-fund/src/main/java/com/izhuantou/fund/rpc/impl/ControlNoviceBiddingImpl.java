package com.izhuantou.fund.rpc.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.izhuantou.common.tool.ToolDateTime;
import com.izhuantou.common.tool.ToolsDatas;
import com.izhuantou.common.utils.DateUtils;
import com.izhuantou.damain.pay.PayDebitCredit;
import com.izhuantou.damain.pay.PayTransferReturn;
import com.izhuantou.damain.user.MemberMemberPrivilege;
import com.izhuantou.damain.vo.bidding.BiddingDTO;
import com.izhuantou.damain.webp2p.WebP2pLoanProductRateInfo;
import com.izhuantou.damain.webp2p.WebP2pNoviceBiddingRuning;
import com.izhuantou.damain.webp2p.WebP2pProductRateInfo;
import com.izhuantou.dao.pay.PayDebitCreditMapper;
import com.izhuantou.dao.user.MemberMemberPrivilegeMapper;
import com.izhuantou.dao.webp2p.WebP2pLoanProductRateInfoMapper;
import com.izhuantou.dao.webp2p.WebP2pNoviceBiddingRuningMapper;
import com.izhuantou.dao.webp2p.WebP2pProductRateInfoMapper;
import com.izhuantou.fund.rpc.api.ControlDebitCredit;
import com.izhuantou.fund.rpc.api.ControlNoviceBidding;
import com.izhuantou.fund.rpc.api.ControlTransferReturn;
import com.izhuantou.service.api.user.MemberMemberAgreementService;
import com.izhuantou.third.rpc.api.ControlPayService;

/**
 * 新手标的相关实现类
 *
 * @author fucheng
 * @date 2018-03-06
 */
@Service("controlNoviceBidding")
public class ControlNoviceBiddingImpl extends BaseServiceImpl<WebP2pNoviceBiddingRuning>
	implements ControlNoviceBidding {
    private static final Logger logger = LoggerFactory.getLogger(ControlNoviceBiddingImpl.class);

    @Autowired
    private ControlDebitCredit controlDebitCredit;
    @Autowired
    private ControlTransferReturn controlTransferReturn;
    @Autowired
    private MemberMemberPrivilegeMapper memberMemberPrivilegeDao;
    @Autowired
    private WebP2pNoviceBiddingRuningMapper noviceBiddingRuningMapper;
    @Autowired
    private WebP2pProductRateInfoMapper ProductRateInfoMapper;
    @Autowired
    private WebP2pLoanProductRateInfoMapper loanProductRateInfoMapper;
    @Autowired
    private PayDebitCreditMapper debitCreditMapper;
    @Autowired
    private MemberMemberAgreementService memberMemberAgreementService;
    @Autowired
    private ControlPayService controPayService;

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
			    PayDebitCredit debitCredit = new PayDebitCredit();
			    if (StringUtils.isEmpty(biddto.getTqOID())) {
				biddto.setCreditRate(productRateInfo.getYearRate());
				biddto.setCreditType(productRate.getRepaymentType());
				// 此返回数据需要保存表中 还需要保存发送的消息
				debitCredit = this.controlDebitCredit.addDebitCreditNew(biddto);

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
					debitCredit = controlDebitCredit.addDebitCreditNew(biddto);
					tq.setState("1");
					tq.setBiddingName(biddto.getBiddingOID());
					// 修改状态
					memberMemberPrivilegeDao.updState(tq);
				    }
				} else {
				    logger.info("特权不存在");
				}
			    }
			    // 出借成功后生成服务协议
			    memberMemberAgreementService.gainMemberXSAgreement(biddto.getMemberOID(), "2",
				    biddto.getBiddingOID(), debitCredit.getOID());

			    // 开始修改标的金额
			    BigDecimal bdje = noviceBid.getBiddingAmount(); // 标的总金额
			    BigDecimal ytje = noviceBid.getHoldingAmount(); // 已投金额
			    BigDecimal newytje = ytje.add(biddto.getAmount());
			    noviceBid.setHoldingAmount(newytje); // 修改标的可投金额
			    if (bdje.compareTo(newytje) == 0) {
				// 满标
				noviceBid.setProductStatus("2");
				noviceBid.setLoanDay(ToolDateTime.gainDate());
				Integer number = productRateInfo.getTerm();
				String eday = ToolsDatas.gainPlusAndReduceDay(ToolDateTime.gainDate().toString(),
					number, 0);
				Date endday = DateUtils.getDate(eday);
				noviceBid.setEndDay(endday);
				// 调用满标流程
				Date sqlDate = ToolDateTime.gainDate();
				controlDebitCredit.finishDebitCreditNEW(biddto.getBiddingOID(), sqlDate);
				// 满标后生成借款协议
				memberMemberAgreementService.gainMemberxsJKAgreement("6", biddto.getBiddingOID());
				// 满标后生成逾期债权预回购协议
				memberMemberAgreementService.gainmemberzqhgXSAgreement("8", biddto.getBiddingOID());

			    }
			    Integer n = noviceBiddingRuningMapper.updateNoviceBiddingRuningByOID(noviceBid);
			    return "1";
			} else if (noviceBid.getBiddingType().equals("1")) {
			    Date date = new Date();
			    if (StringUtils.isEmpty(biddto.getTqOID())) {

				biddto.setDebitCreditOID(noviceBid.getDebitCreditOID());
				biddto.setOutMemberOID(noviceBid.getMemberOID());
				biddto.setBeginDate(noviceBid.getLoanDay());
				biddto.setTransferReturnDate(new Date());
				biddto.setIsTZorCW("XS");
				biddto.setCreditRate(productRateInfo.getYearRate());
				biddto.setCreditType(productRate.getRepaymentType());
				controlDebitCredit.addTransferReturnZZ(biddto);
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
					controlDebitCredit.addTransferReturn(biddto);
					tq.setState("1");
					tq.setBiddingName(biddto.getBiddingOID());
					// 修改状态
					memberMemberPrivilegeDao.updState(tq);
				    }
				}
			    }
			    BigDecimal bdje = noviceBid.getBiddingAmount();// 标的金额
			    BigDecimal ytje = noviceBid.getHoldingAmount();// 已投金额
			    BigDecimal newytje = ytje.add(biddto.getAmount());
			    noviceBid.setHoldingAmount(newytje); // 更新标的已投金额
			    if (bdje.compareTo(newytje) == 0) { // 满标了
				// 满标了
				noviceBid.setProductStatus("2");
				noviceBid.setLoanDay(ToolDateTime.gainDate());

				biddto.setBeginDate(new Date());
				biddto.setIsTZorCW("XS");
				biddto.setTzType("XS");
				controlDebitCredit.finishTransferReturnNEW(biddto);
				List<PayTransferReturn> reList = this.controlTransferReturn
					.findByBusinessOID(biddto.getBiddingOID());
				// TODO 迭代取出后
				String outMeber = "";
				for (PayTransferReturn re : reList) {
				    String debitOID = re.getDebitCreditOID();
				    outMeber = debitCreditMapper.queryByPKOid(debitOID).getOutMemberOID();
				}
				controPayService.unFreeze(outMeber, bdje);
				// 财务标的满标后生成债权转让协议
				memberMemberAgreementService.gainmemberTbzZZTZQZRAgreement("7", biddto.getBiddingOID());
				// 生成债权预回购协议l
				memberMemberAgreementService.gainmemberxszqhgZZTAgreement("8", biddto.getBiddingOID());

			    }
			    Integer n = noviceBiddingRuningMapper.updateNoviceBiddingRuningByOID(noviceBid);
			    return "1";
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
