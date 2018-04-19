package com.izhuantou.dao.lend;

import com.izhuantou.damain.lend.ZCDInfo;

/**
 * 车抵债转信息
 * 
 * @author yangbosen
 *
 */
public interface ZCDInfoMpper {
    public ZCDInfo findByLoanNum(String LoanNum);
}
