package com.izhuantou.fund.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.izhuantou.common.tool.ToolDateTime;
import com.izhuantou.common.tool.ToolsDatas;
import com.izhuantou.common.utils.DateUtils;
import com.izhuantou.damain.pay.PayTransferReturn;
import com.izhuantou.damain.user.MemberMemberPrivilege;
import com.izhuantou.damain.webp2p.WebP2pLoanProductRateInfo;
import com.izhuantou.damain.webp2p.WebP2pNoviceBiddingRuning;
import com.izhuantou.damain.webp2p.WebP2pProductRateInfo;
import com.izhuantou.dao.user.MemberMemberPrivilegeMapper;
import com.izhuantou.dao.webp2p.WebP2pLoanProductRateInfoMapper;
import com.izhuantou.dao.webp2p.WebP2pNoviceBiddingRuningMapper;
import com.izhuantou.dao.webp2p.WebP2pProductRateInfoMapper;
import com.izhuantou.fund.api.ControlDebitCredit;
import com.izhuantou.fund.api.ControlMemberAgreement;
import com.izhuantou.fund.api.ControlNoviceBidding;
import com.izhuantou.fund.api.ControlTransferReturn;
import com.izhuantou.third.api.ControlPayService;

/**
 * 新手标的相关实现类
 *
 * @author fucheng
 * @date 2018-03-06
 */
@Service("controlNoviceBidding")
public class ControlNoviceBiddingImpl extends BaseServiceImpl<WebP2pNoviceBiddingRuning>
	implements ControlNoviceBidding {
    @Autowired
    private ControlDebitCredit controlDebitCredit;
    @Autowired
    private ControlTransferReturn controlTransferReturn;
    @Autowired
    private ControlPayService controlPay;
    @Autowired
    private MemberMemberPrivilegeMapper memberMemberPrivilegeDao;
    @Autowired
    private ControlMemberAgreement controlMemberAgreement;
    @Autowired
    private WebP2pNoviceBiddingRuningMapper noviceBiddingRuningMapper;
    @Autowired
    private WebP2pProductRateInfoMapper ProductRateInfoMapper;
    @Autowired
    private WebP2pLoanProductRateInfoMapper loanProductRateInfoMapper;

    @Override
    public Integer bidNomalBidding(Map<String, Object> map) {
	int r = 0;
	try {
	    // 确认用户的输入金额有没有超过头笔赚出借上限
	    if (((BigDecimal) map.get("amount")).compareTo(new BigDecimal(10000)) > 0) {
		// 表示用户输入金额大于投标上限
		System.out.println("投标失败,出借金额超过头笔赚产品出借上限，请重新输入出借金额");
	    } else {
		// 表示可以投标
		WebP2pNoviceBiddingRuning noviceBid = this.noviceBiddingRuningMapper
			.findByOID((String) map.get("biddingOID"));
		if (noviceBid != null) {
		    // TODO 在此处增加一个 noviceBid.getProductStatus()==1或者2;为磕头标还是已满标
		    WebP2pProductRateInfo productRate = this.ProductRateInfoMapper
			    .findByOID(noviceBid.getProductRateInfoID());
		    WebP2pLoanProductRateInfo productRateInfo = this.loanProductRateInfoMapper
			    .findByOID(noviceBid.getLoanProductRateInfoID());
		    // 表示可以投标
		    if (noviceBid.getBiddingType().equals("0")) {
			// 普通标的
			Map<String, Object> resultMap = null;
			if (StringUtils.isBlank((String) map.get("tqOID"))) {
			    // 表示没有特权OID
			    map.put("creditRate", productRateInfo.getYearRate().toString());
			    map.put("creditType", productRate.getRepaymentType());
			    // 此返回数据需要保存表中 还需要保存发送的消息
			    resultMap = this.controlDebitCredit.addDebitCreditNew(map);

			} else {
			    // TODO 有特权OID的情况下操作 2018/3/21 Jasen
			    MemberMemberPrivilege tq = memberMemberPrivilegeDao
				    .findByOIDAndsatate((String) map.get("tqOID"));
			    // DTO tq =
			    // dbControlMemberPrivilege.findByOIDAndsatate(tqOID);
			    BigDecimal privilegePrincipal = tq.getTqAmount();
			    BigDecimal privilegeInterest = tq.getTqll();
			    map.put("creditRate", productRateInfo.getYearRate().toString());
			    map.put("creditType", productRate.getRepaymentType());
			    map.put("privilegePrincipal", privilegePrincipal);
			    map.put("privilegeInterest", privilegeInterest);
			    // 增加借贷
			    resultMap = controlDebitCredit.addDebitCreditNew(map);
			    MemberMemberPrivilege dto = memberMemberPrivilegeDao.findByOID((String) map.get("tqOID"));
			    dto.setState("1");
			    dto.setBiddingName((String) map.get("biddingOID"));
			    // 修改状态
			    memberMemberPrivilegeDao.updState(dto);
			    // --------------------------------------------
			}
			// TODO 生成服务协议 2018/3/21 Jasen
			// 出借成功后生成服务协议
			controlMemberAgreement.gainMemberXSAgreement((String) map.get("memberOID"), "2",
				(String) map.get("biddingOID"), (String) resultMap.get("OID"));

			// 开始修改标的金额
			BigDecimal bdje = noviceBid.getBiddingAmount(); // 标的总金额
			BigDecimal ytje = noviceBid.getHoldingAmount(); // 已投金额
			BigDecimal newytje = ytje.add((BigDecimal) map.get("amount"));
			noviceBid.setHoldingAmount(newytje); // 修改标的可投金额
			if (bdje.compareTo(newytje) == 0) {
			    // 满标
			    noviceBid.setProductStatus("2");
			    noviceBid.setLoanDay(ToolDateTime.gainDate());
			    Integer number = productRateInfo.getTerm();
			    String eday = ToolsDatas.gainPlusAndReduceDay(ToolDateTime.gainDate().toString(), number,
				    0);
			    Date endday = DateUtils.getDate(eday);
			    noviceBid.setEndDay(endday);
			    // 调用满标流程
			    Date sqlDate = ToolDateTime.gainDate();
			    controlDebitCredit.finishDebitCreditNEW((String) map.get("biddingOID"), sqlDate);
			    // 满标后生成借款协议
			    // TODO 生成满标协议
			    // 满标后生成逾期债权预回购协议
			    // TODO 生成逾期债权预回购协议

			} else {
			    // 表未满，继续投
			}
			Integer n = noviceBiddingRuningMapper.updateNoviceBiddingRuningByOID(noviceBid);
			r = 1;
		    } else if (noviceBid.getBiddingType().equals("1")) {
			// 调用债转流程
			// businessOID
			// 财务债转新建标的OID;debitCreditOID
			// 借贷关系表OID;
			// outMemberOID 受让人OID; 11
			Date date = new Date();
			if (StringUtils.isBlank((String) map.get("tqOID"))) {
			    map.put("businessOID", (String) noviceBid.getOID());
			    map.put("debitCreditOID", (String) noviceBid.getDebitCreditOID());
			    map.put("outMemberOID", map.get("memberOID"));
			    /*
			     * map.put("yearRate",
			     * productRateInfo.getYearRate().toString());
			     * map.put("repaymentType", (String)
			     * productRate.getRepaymentType());
			     */
			    map.put("beginDate", noviceBid.getLoanDay().toString());
			    map.put("transferReturnDate", date);
			    map.put("isTZorCW", "XS");
			    map.put("creditRate", productRateInfo.getYearRate().toString());
			    map.put("creditType", productRate.getRepaymentType());
			    controlDebitCredit.addTransferReturnZZ(map);
			} else {
			    // 暂无特权标的
			    // TODO 特权标的的添加 2018/3/21 Jasen
			    MemberMemberPrivilege tq = memberMemberPrivilegeDao
				    .findByOIDAndsatate((String) map.get("tqOID"));

			    // DTO tq =
			    // dbControlMemberPrivilege.findByOIDAndsatate(tqOID);
			    BigDecimal privilegePrincipal = (BigDecimal) tq.getTqAmount();
			    BigDecimal privilegeInterest = (BigDecimal) tq.getTqll();
			    map.put("creditRate", productRateInfo.getYearRate().toString());
			    map.put("creditType", productRate.getRepaymentType());
			    map.put("privilegePrincipal", privilegePrincipal);
			    map.put("privilegeInterest", privilegeInterest);
			    map.put("businessOID", noviceBid.getOID());
			    map.put("debitCreditOID", noviceBid.getDebitCreditOID());
			    map.put("outMemberOID", map.get("memberOID"));
			    map.put("transferReturnDate", date);

			    controlDebitCredit.addTransferReturn(map);

			    MemberMemberPrivilege dto = memberMemberPrivilegeDao.findByOID((String) map.get("tqOID"));
			    dto.setState("1");
			    dto.setBiddingName((String) map.get("biddingOID"));
			    // 修改状态
			    memberMemberPrivilegeDao.updState(dto);
			    // -----------------------------------
			}

			List<PayTransferReturn> transferReturn = controlTransferReturn
				.findByBusinessOID((String) map.get("biddingOID"));
			// TODO 生成新手标协议未做
			BigDecimal bdje = (BigDecimal) noviceBid.getBiddingAmount();// 标的金额
			BigDecimal ytje = (BigDecimal) noviceBid.getHoldingAmount();// 已投金额
			BigDecimal newytje = ytje.add((BigDecimal) map.get("amount"));
			noviceBid.setHoldingAmount(newytje); // 更新标的已投金额
			if (bdje.compareTo(newytje) == 0) { // 满标了
			    // 满标了
			    noviceBid.setProductStatus("2");
			    noviceBid.setLoanDay(ToolDateTime.gainDate());
			    // 调用满标流程
			    Map<String, Object> typeMap = new HashMap<String, Object>();
			    typeMap.put("businessOID", noviceBid.getOID());
			    typeMap.put("beginDate", date);
			    typeMap.put("isTZorCW", "XS");
			    typeMap.put("tzType", "XS");
			    typeMap.put("ly", map.get("laiyuan"));
			    controlDebitCredit.finishTransferReturnNEW(typeMap);
			    List<PayTransferReturn> d = this.controlTransferReturn
				    .findByBusinessOID((String) map.get("biddingOID"));
			    // String debitOID = (String) d.getdebitCreditOID
			    // String outMeber = (String)
			    // dbControlDebitCredit.findByOID(debitOID).get("outMemberOID");

			    // this.controlPay.unFreeze(null, bdje); //该方法调用了富友
			    // 暂不可用
			    // 财务标的满标后生成债权转让协议
			    // TODO 生成协议
			    // 生成债权预回购协议l
			    // TODO 生成协议
			} else {
			    // 未满标，接着投
			}
			Integer n = noviceBiddingRuningMapper.updateNoviceBiddingRuningByOID(noviceBid);
			r = 1;
		    } else {
			System.out.println("======对应标的类型不匹配========");
		    }
		}
	    }

	} catch (Exception e) {
	    System.out.println(e.getMessage());
	}
	return r;
    }

}
