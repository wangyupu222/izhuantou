package com.izhuantou.fund.rpc.api;

import java.math.BigDecimal;

import com.izhuantou.damain.pay.PayCashPool;

/**
 * 投标接口类
 *
 * @author fucheng
 * @date 2018-03-12
 */

public interface ControlCashPool extends BaseService<PayCashPool> {

	/**
	 * 一次性手续费
	 * 
	 * @param strBusinessOID
	 * @param string
	 * @param onceProceduresMoney
	 */
	void onceProceduresMoney(String strBusinessOID, String string, BigDecimal onceProceduresMoney);

	/**
	 * 一对一完成投资
	 * 
	 * @param strBusinessOID
	 * @param string
	 * @param subtract
	 */
	void finishInvestment(String businessOID, String memberOID, BigDecimal money);
	
	/**
	 * 站岗资金结算修改调用方法
	 * @param businessOID
	 * @param memberOID
	 * @param money
	 * @param privilegeMoney
	 */
	void finishTransferReturnUnFreeze(String businessOID, String memberOID, BigDecimal money,BigDecimal privilegeMoney);
	
	
	/**
	 * 真正投资
	 * 
	 * @param businessOID
	 * @param outMemberOID
	 * @param inMemberOID
	 * @param money
	 */
	void realInvestment(String businessOID, String outMemberOID, String inMemberOID, BigDecimal money);
	
	
	/**
	 * 债转
	 * 
	 * @param businessOID
	 * @param memberOID
	 * @param outMemberOID
	 * @param principal
	 */
	void transferReturnCashPool(String businessOID, String outMemberOID, String inMemberOID, BigDecimal money);

	/**
	 * 冻结还款
	 * @param businessOID
	 * @param memberOID
	 * @param money
	 */
	void freezeRepay(String businessOID, String memberOID, BigDecimal money);
	/**
	 * 平台管理费
	 * @param businessOID
	 * @param memberOID
	 * @param money
	 */
	void manageMoney(String businessOID, String memberOID, BigDecimal money);
	/**
	 * 咨询费
	 * @param businessOID
	 * @param memberOID
	 * @param money
	 */
	void serviceMoney(String businessOID, String memberOID, BigDecimal money);
	/**
	 * 逾期罚息
	 * @param businessOID
	 * @param outmemberOID
	 * @param inmemberOID
	 * @param money
	 */
	void overdueInterest(String businessOID, String outmemberOID, String inmemberOID, BigDecimal money);
	/**
	 * 逾期管理
	 * @param businessOID
	 * @param memberOID
	 * @param money
	 */
	void overdueManage(String businessOID, String memberOID, BigDecimal money);
	/**
	 * 逾期违约金
	 * @param businessOID
	 * @param memberOID
	 * @param money
	 */
	void overduePenalty(String businessOID, String memberOID, BigDecimal money);
	/**
	 * 提前违约金
	 * @param businessOID
	 * @param memberOID
	 * @param money
	 */
	void earlyRepayPenalty(String businessOID, String memberOID, BigDecimal money);
	/**
	 * 冻结逾期
	 * @param businessOID
	 * @param memberOID
	 * @param money
	 */
	void freezeOverdue(String businessOID, String memberOID, BigDecimal money);
	/**
	 * 贴息
	 * @param businessOID
	 * @param memberOID
	 * @param money
	 */
	void discount(String businessOID, String memberOID, BigDecimal money);
	/**
	 * 增添剩余冻结金额，用于比较实际划拨金额与剩余冻结划拨金额 若实际大于剩余，则超出部分由平台划拨给出借用户
	 * @param businessOID
	 * @param outMemberOID
	 * @param inMemberOID
	 * @param money
	 * @param suplusFreezeMoney
	 */
	void repayNCPNew(String businessOID, String outMemberOID, String inMemberOID, BigDecimal money,
			BigDecimal suplusFreezeMoney);
	/**
	 *  加息
	 * @param businessOID
	 * @param memberOID
	 * @param money
	 */
	void help(String businessOID, String memberOID, BigDecimal money);
	
	/**
	 * 完成资金池还款
	 * @param businessOID
	 * @param memberOID
	 * @param money
	 */
	void finishRepayNCP(String businessOID, String memberOID, BigDecimal money);
	/**
	 * 增添剩余冻结金额，用于比较实际划拨金额与剩余冻结划拨金额 若实际大于剩余，则超出部分由平台划拨给出借用户
	 * @param businessOID
	 * @param outMemberOID
	 * @param inMemberOID
	 * @param money
	 * @param suplusFreezeMoney
	 */
	void repayNew(String businessOID, String outMemberOID, String inMemberOID, BigDecimal money,
			BigDecimal suplusFreezeMoney);
	/**
	 *  完成还款
	 * @param businessOID
	 * @param memberOID
	 * @param money
	 */
	void finishRepay(String businessOID, String memberOID, BigDecimal money);
	
	
}
