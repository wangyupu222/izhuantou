package com.izhuantou.fund.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.izhuantou.common.utils.StringUtil;
import com.izhuantou.damain.pay.PayCashPool;
import com.izhuantou.damain.pay.PayCashPoolOperation;
import com.izhuantou.damain.pay.PayCustomer;
import com.izhuantou.damain.pay.PayCustomerBusiness;
import com.izhuantou.damain.webp2p.WebP2pNoviceBiddingRuning;
import com.izhuantou.damain.webp2p.WebP2pPackageBiddingMainRuning;
import com.izhuantou.damain.webp2p.WebP2pProductRateInfo;
import com.izhuantou.dao.pay.PayCashPoolMapper;
import com.izhuantou.dao.pay.PayCashPoolOperationMapper;
import com.izhuantou.dao.pay.PayCustomerBusinessMapper;
import com.izhuantou.dao.pay.PayCustomerMapper;
import com.izhuantou.dao.webp2p.WebP2pNoviceBiddingRuningMapper;
import com.izhuantou.dao.webp2p.WebP2pPackageBiddingMainRuningMapper;
import com.izhuantou.dao.webp2p.WebP2pProductRateInfoMapper;
import com.izhuantou.fund.api.ControlCashPool;
import com.izhuantou.fund.api.ControlCustomerBusiness;
import com.izhuantou.third.api.ControlPayService;

@Service("controlCashPool")
public class ControlCashPoolImpl extends BaseServiceImpl<PayCashPool> implements ControlCashPool {

    @Autowired
    private ControlPayService controlPay;

    @Autowired
    private ControlCustomerBusiness controlCustomerBusiness;
    @Autowired
    private PayCashPoolMapper payCashPoolDao;
    @Autowired
    private PayCashPoolOperationMapper paycashPoolOperationMapper;
    @Autowired
    private PayCustomerMapper customerMapper;
    @Autowired
    private PayCustomerBusinessMapper customerBusinessMapper;
    @Autowired
    private WebP2pNoviceBiddingRuningMapper noviceBiddingRuningMapper;
    @Autowired
    private WebP2pPackageBiddingMainRuningMapper packageBiddingMainRuningMapper;
    @Autowired
    private WebP2pProductRateInfoMapper productRateInfoMapper;

    @Override
    public void onceProceduresMoney(String businessOID, String memberOID, BigDecimal money) {
	try {
	    // TODO 需要调富友 未完成
	    /*
	     * controlPay.transferFreezeToFreeze(memberOID, "manageAccount",
	     * money); controlPay.unFreeze("manageAccount", money);
	     */

	    PayCashPoolOperation operation = new PayCashPoolOperation();
	    operation.setOID(StringUtil.getUUID());
	    operation.setBusinessOID(businessOID);
	    operation.setOutMemberOID(memberOID);
	    operation.setInMemberOID("manageAccount");
	    operation.setContent("缴纳一次性手续费");
	    operation.setMoney(money);
	    int rows = paycashPoolOperationMapper.saveCashPoolOperation(operation);

	    PayCustomerBusiness customerBusiness = new PayCustomerBusiness();
	    String bOID = StringUtil.getUUID();
	    customerBusiness.setOID(bOID);
	    customerBusiness.setMoney(money);
	    customerBusiness.setContent("用户借款时应扣除的手续费");
	    customerBusiness.setState("已完成");
	    customerBusiness.setType("借款一次性手续费");
	    customerBusiness.setMemberOID(memberOID);
	    PayCustomer customer = customerMapper.findByMemberOID(memberOID);
	    customerBusiness.setCustomerOID(customer.getOID());
	    int brows = customerBusinessMapper.insertCustomerBussiness(customerBusiness);

	} catch (Exception e) {
	    System.out.println(e.getMessage());
	}

    }

    @Override
    public void finishInvestment(String businessOID, String memberOID, BigDecimal money) {
	try {

	    // controlPay.unFreeze(memberOID, money);
	    PayCashPoolOperation cashPoolOperation = new PayCashPoolOperation();
	    cashPoolOperation.setOID(StringUtil.getUUID());
	    cashPoolOperation.setBusinessOID(businessOID);
	    cashPoolOperation.setOutMemberOID(memberOID);
	    cashPoolOperation.setMoney(money);
	    cashPoolOperation.setContent("投资完成，解冻资金");
	    int rows = paycashPoolOperationMapper.saveCashPoolOperation(cashPoolOperation);

	    PayCustomerBusiness customerBusiness = new PayCustomerBusiness();
	    customerBusiness.setMoney(money);
	    customerBusiness.setContent("用户借款成功实际到账金额");
	    customerBusiness.setState("已完成");
	    customerBusiness.setType("借款到账");
	    customerBusiness.setMemberOID(memberOID);
	    PayCustomer customer = customerMapper.findByMemberOID(memberOID);
	    customerBusiness.setCustomerOID(customer.getOID());
	    String bOID = StringUtil.getUUID();
	    customerBusiness.setOID(bOID);
	    int brows = customerBusinessMapper.insertCustomerBussiness(customerBusiness);

	} catch (Exception e) {
	    System.out.println(e.getMessage());
	}

    }

    @Override
    public void finishTransferReturnUnFreeze(String businessOID, String memberOID, BigDecimal money,
	    BigDecimal privilegeMoney) {
	try {
	    PayCashPoolOperation dto = new PayCashPoolOperation();
	    dto.setBusinessOID(businessOID);
	    dto.setInMemberOID(memberOID);
	    if (privilegeMoney == null) {
		dto.setMoney(money);
	    } else {
		dto.setMoney(money.subtract(privilegeMoney));
	    }
	    dto.setContent("债转完成");
	    dto.setOID(StringUtil.getUUID());
	    int rows = paycashPoolOperationMapper.saveCashPoolOperation(dto);

	    PayCustomerBusiness customerBusiness = new PayCustomerBusiness();
	    customerBusiness.setMoney(money);
	    customerBusiness.setContent("债转成功实际到账金额");
	    customerBusiness.setState("已完成");
	    customerBusiness.setType("债权转让");
	    customerBusiness.setMemberOID(memberOID);
	    PayCustomer customer = customerMapper.findByMemberOID(memberOID);
	    customerBusiness.setCustomerOID(customer.getOID());
	    String bOID = StringUtil.getUUID();
	    customerBusiness.setOID(bOID);
	    int brows = customerBusinessMapper.insertCustomerBussiness(customerBusiness);

	} catch (Exception e) {
	    System.out.println(e.getMessage());
	}

    }

    @Override
    public PayCashPool investment(Map<String, Object> typeMap) {
	PayCashPool dtoCashPool = null;
	try {
	    // dbControlCashPool
	    // dbControlCashPoolOperation
	    // controlPay
	    // controlPackageBiddingMainRuning
	    // controlProductRateInfo

	    if (typeMap.get("privilegePrincipal") == null) {
		controlPay.freeze((String) typeMap.get("outMemberOID"), (BigDecimal) typeMap.get("money"));
	    }
	    WebP2pPackageBiddingMainRuning maindto = packageBiddingMainRuningMapper
		    .findByOID((String) typeMap.get("businessOID"));

	    WebP2pProductRateInfo productInfodto = productRateInfoMapper.findByOID(maindto.getProductRateInfoID());

	    dtoCashPool = new PayCashPool();
	    dtoCashPool.setOID(StringUtil.getUUID());
	    dtoCashPool.setBusinessOID((String) typeMap.get("businessOID"));
	    dtoCashPool.setMemberOID((String) typeMap.get("outMemberOID"));
	    dtoCashPool.setMoney((BigDecimal) typeMap.get("money"));
	    dtoCashPool.setInterest(new BigDecimal("0"));
	    dtoCashPool.setPrincipalMoney((BigDecimal) typeMap.get("money"));
	    dtoCashPool.setInterestMoney((BigDecimal) typeMap.get("interest"));
	    dtoCashPool.setCreditRate((BigDecimal) typeMap.get("creditRate"));
	    dtoCashPool.setCreditType((String) typeMap.get("creditType"));
	    dtoCashPool.setReturnCycle((String) typeMap.get("returnCycle"));
	    dtoCashPool.setTotalInterestMoney(((BigDecimal) typeMap.get("interest"))
		    .multiply(new BigDecimal((String.valueOf(typeMap.get("returnNumber")))))
		    .setScale(2, BigDecimal.ROUND_HALF_EVEN));
	    dtoCashPool.setPrivilegePrincipal(null);
	    dtoCashPool.setPrivilegeInterest(null);
	    dtoCashPool.setTqOID((String) typeMap.get("tqOID"));
	    dtoCashPool.setState("investment");
	    dtoCashPool.setReturnNumber((Integer) typeMap.get("returnNumber"));
	    dtoCashPool.setReturnSurplusNumber((Integer) typeMap.get("returnNumber"));
	    dtoCashPool.setStartDateTime((Timestamp) typeMap.get("beginDate"));
	    dtoCashPool.setEndDateTime((Timestamp) typeMap.get("endDate"));
	    dtoCashPool.setLaiyuan((String) typeMap.get("ly"));// 数据来源 App/Web
	    // 额外加息 terry
	    BigDecimal zero = new BigDecimal(0);
	    if (null != maindto.getJXother() && ((BigDecimal) maindto.getJXother()).compareTo(zero) > 0) {
		BigDecimal lx = (BigDecimal) maindto.getJXother();
		double qishu = (double) productInfodto.getProductTerm();
		dtoCashPool.setJXother(lx);
		BigDecimal creditRateTemp = lx.divide(new BigDecimal("12"), 8, BigDecimal.ROUND_HALF_EVEN)
			.divide(new BigDecimal("100"), 8, BigDecimal.ROUND_HALF_EVEN);
		BigDecimal JXinterest = ((BigDecimal) typeMap.get("money")).multiply(creditRateTemp)
			.multiply(new BigDecimal(qishu)).setScale(2, BigDecimal.ROUND_HALF_EVEN);
		dtoCashPool.setJXother(lx);
		dtoCashPool.setJXinterest(JXinterest);
	    } else {
		dtoCashPool.setJXother(new BigDecimal("0"));
	    }
	    // dtoCashPool = dbControlCashPool.save(dtoCashPool);
	    payCashPoolDao.savePayCashPool(dtoCashPool);

	    PayCashPoolOperation dto = new PayCashPoolOperation();
	    dto.setBusinessOID((String) typeMap.get("businessOID"));
	    dto.setOutMemberOID((String) typeMap.get("outMemberOID"));
	    dto.setMoney((BigDecimal) typeMap.get("money"));
	    dto.setContent("进行投资，冻结资金，准备投资");
	    dto.setLaiyuan((String) typeMap.get("ly"));

	    dto.setOID(StringUtil.getUUID());
	    int rows = paycashPoolOperationMapper.saveCashPoolOperation(dto);

	    // TODO 消息系统
	    /*
	     * DTBServiceAuth dtbServiceAuth = new DTBServiceAuth();
	     * dtbServiceAuth.addUserOID(outMemberOID);
	     * ServiceFactory.instanceMessageService(this).sendMessage("出借",
	     * "您已经出借环环投，出借金额为" + money + "元", "系统", dtbServiceAuth, null);
	     * DTBServiceAuth dtbServiceAuth1 = new DTBServiceAuth();
	     * controlCustomer = (ControlCustomer)
	     * this.gainInstance(controlCustomer, ControlCustomer.class);
	     * dtbServiceAuth1.addUserOID((String)
	     * controlCustomer.gainCustomerByMemberOID(outMemberOID).get("name")
	     * );
	     * ServiceFactory.instanceMessageService(this).sendSMSMessage("出借",
	     * "【砖头网】您已经出借环环投，出借金额为" + money + "元", null, "系统", dtbServiceAuth1,
	     * null);
	     */
	} catch (Exception e) {
	    System.out.println(e.getMessage());
	}
	return dtoCashPool;

    }

    @Override
    public List<PayCashPool> gainCashPoolByBusinessOID(String strBusinessOID) {
	try {
	    return this.payCashPoolDao.findPayCashPoolByBusiness(strBusinessOID);
	} catch (Exception e) {
	    System.out.println(e.getMessage());
	    return null;
	}
    }

    @Override
    public List<PayCashPool> gainCashPoolByBusinessOIDAndHaveMoney(String strBusinessOID) {
	try {
	    return this.payCashPoolDao.findByBusinessOIDAndHaveMoney(strBusinessOID);
	} catch (Exception e) {
	    System.out.println(e.getMessage());
	    return null;
	}
    }

    @Override
    public void realInvestment(String businessOID, String outMemberOID, String inMemberOID, BigDecimal money) {
	try {
	    // controlPay.transferFreezeToFreeze(outMemberOID, inMemberOID,
	    // money);
	    PayCashPoolOperation dto = new PayCashPoolOperation();
	    dto.setBusinessOID(businessOID);
	    dto.setOutMemberOID(outMemberOID);
	    dto.setInMemberOID(inMemberOID);
	    dto.setMoney(money);
	    dto.setContent("进行投资，划拨冻结");
	    dto.setOID(StringUtil.getUUID());
	    paycashPoolOperationMapper.saveCashPoolOperation(dto);
	} catch (Exception e) {
	    System.out.println(e.getMessage());
	}

    }

    @Override
    public void transferReturnCashPool(String businessOID, String outMemberOID, String inMemberOID, BigDecimal money) {
	try {

	    // this.controlPay.transferFreezeToFreeze(outMemberOID, inMemberOID,
	    // money);
	    PayCashPoolOperation dto = new PayCashPoolOperation();
	    dto.setBusinessOID(businessOID);
	    dto.setOutMemberOID(outMemberOID);
	    dto.setInMemberOID(inMemberOID);
	    dto.setMoney(money);
	    dto.setContent("债转冻结");
	    dto.setOID(StringUtil.getUUID());
	    paycashPoolOperationMapper.saveCashPoolOperation(dto);
	    if ((this.noviceBiddingRuningMapper.findByOID(businessOID)) != null) {
		// 用户业务操作记录
		this.controlCustomerBusiness.saveCustomerBusiness("出借", money, "用户出借的金额", outMemberOID);
		// TODO 发送消息，消息系统
		/*
		 * DTBServiceAuth dtbServiceAuth = new DTBServiceAuth();
		 * dtbServiceAuth.addUserOID(outMemberOID);
		 * ServiceFactory.instanceMessageService(this).sendMessage("出借",
		 * "您已经出借头笔赚，出借金额为" + money + "元", "系统", dtbServiceAuth, null);
		 * DTBServiceAuth dtbServiceAuth1 = new DTBServiceAuth();
		 * controlCustomer = (ControlCustomer)
		 * this.gainInstance(controlCustomer, ControlCustomer.class);
		 * dtbServiceAuth1.addUserOID((String)
		 * controlCustomer.gainCustomerByMemberOID(outMemberOID).get(
		 * "name"));
		 * ServiceFactory.instanceMessageService(this).sendSMSMessage(
		 * "出借", "【砖头网】您已经出借头笔赚，出借金额为" + money + "元", null, "系统",
		 * dtbServiceAuth1, null);
		 */
	    }
	} catch (Exception e) {
	    System.out.println(e.getMessage());
	}
    }

    @Override
    public void transferReturn(String businessOID, String outMemberOID, String inMemberOID, BigDecimal money) {
	try {
	    // controlPay.transferFreeze(outMemberOID, inMemberOID, money);
	    PayCashPoolOperation operation = new PayCashPoolOperation();
	    operation.setBusinessOID(businessOID);
	    operation.setOutMemberOID(outMemberOID);
	    operation.setInMemberOID(inMemberOID);
	    operation.setMoney(money);
	    operation.setContent("债转冻结");
	    operation.setOID(StringUtil.getUUID());
	    int pw = paycashPoolOperationMapper.saveCashPoolOperation(operation);

	    PayCustomer customer = customerMapper.findByMemberOID(outMemberOID);

	    WebP2pNoviceBiddingRuning novice = noviceBiddingRuningMapper.findByOID(businessOID);
	    if (novice != null) {
		PayCustomerBusiness customerBusiness = new PayCustomerBusiness();
		customerBusiness.setContent("用户出借的金额");
		customerBusiness.setMoney(money);
		customerBusiness.setState("已完成");
		customerBusiness.setType("出借");
		customerBusiness.setMemberOID(outMemberOID);
		customerBusiness.setCustomerOID(customer.getOID());
		customerBusiness.setOID(StringUtil.getUUID());
		customerBusinessMapper.insertCustomerBussiness(customerBusiness);
		/*
		 * 发短信的暂时不写
		 * ServiceFactory.instanceMessageService(this).sendMessage("出借",
		 * "您已经出借头笔赚，出借金额为" + money + "元", "系统", dtbServiceAuth, null);
		 * DTBServiceAuth dtbServiceAuth1 = new DTBServiceAuth();
		 * controlCustomer = (ControlCustomer)
		 * this.gainInstance(controlCustomer, ControlCustomer.class);
		 * dtbServiceAuth1.addUserOID((String)
		 * controlCustomer.gainCustomerByMemberOID(outMemberOID).get(
		 * "name"));
		 * ServiceFactory.instanceMessageService(this).sendSMSMessage(
		 * "出借", "【砖头网】您已经出借头笔赚，出借金额为" + money + "元", null, "系统",
		 * dtbServiceAuth1, null);
		 */
	    }
	} catch (Exception e) {
	    System.out.println(e.getMessage());
	}
    }

    @Override
    public void discount(String businessOID, String memberOID, BigDecimal money) {
	// controlPay.transferFreeze("discountAccount", memberOID, money);
	PayCashPoolOperation cashPoolOperation = new PayCashPoolOperation();
	cashPoolOperation.setBusinessOID(businessOID);
	cashPoolOperation.setOutMemberOID("discountAccount");
	cashPoolOperation.setInMemberOID(memberOID);
	cashPoolOperation.setMoney(money);
	cashPoolOperation.setContent("贴息完成");
	cashPoolOperation.setOID(StringUtil.getUUID());
	paycashPoolOperationMapper.saveCashPoolOperation(cashPoolOperation);
    }

    @Override
    public void investmentNew(String businessOID, String outMemberOID, String inMemberOID, BigDecimal money) {
	try {
	    // controlPay.transferFreeze(outMemberOID, inMemberOID, money);
	    PayCashPoolOperation cashPoolOperation = new PayCashPoolOperation();
	    cashPoolOperation.setBusinessOID(businessOID);
	    cashPoolOperation.setOutMemberOID(outMemberOID);
	    cashPoolOperation.setInMemberOID(inMemberOID);
	    cashPoolOperation.setMoney(money);
	    cashPoolOperation.setContent("进行投资，划拨冻结");
	    cashPoolOperation.setOID(StringUtil.getUUID());
	    paycashPoolOperationMapper.saveCashPoolOperation(cashPoolOperation);

	    PayCustomer customer = customerMapper.findByMemberOID(outMemberOID);
	    PayCustomerBusiness customerBusiness = new PayCustomerBusiness();
	    customerBusiness.setContent("用户出借的金额");
	    customerBusiness.setMoney(money);
	    customerBusiness.setState("已完成");
	    customerBusiness.setType("出借");
	    customerBusiness.setMemberOID(outMemberOID);
	    customerBusiness.setCustomerOID(customer.getOID());
	    customerBusiness.setOID(StringUtil.getUUID());
	    customerBusinessMapper.insertCustomerBussiness(customerBusiness);

	    /*
	     * DTBServiceAuth dtbServiceAuth = new DTBServiceAuth();
	     * dtbServiceAuth.addUserOID(outMemberOID);
	     * ServiceFactory.instanceMessageService(this).sendMessage("出借",
	     * "您已经出借头笔赚，出借金额为" + money + "元", "系统", dtbServiceAuth, null);
	     * DTBServiceAuth dtbServiceAuth1 = new DTBServiceAuth();
	     * controlCustomer = (ControlCustomer)
	     * this.gainInstance(controlCustomer, ControlCustomer.class);
	     * dtbServiceAuth1.addUserOID((String)
	     * controlCustomer.gainCustomerByMemberOID(outMemberOID).get("name")
	     * );
	     * ServiceFactory.instanceMessageService(this).sendSMSMessage("出借",
	     * "【砖头网】您已经出借头笔赚，出借金额为" + money + "元", null, "系统", dtbServiceAuth1,
	     * null);
	     */
	} catch (Exception e) {
	    System.out.println(e.getMessage());
	}

    }

}
