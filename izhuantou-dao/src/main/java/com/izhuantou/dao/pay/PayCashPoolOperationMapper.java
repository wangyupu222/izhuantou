package com.izhuantou.dao.pay;

import java.util.List;

import com.github.abel533.mapper.Mapper;
import com.izhuantou.damain.pay.PayCashPoolOperation;

/**
 * 资金池操作记录 显示资金操作状态，如冻结金额
 * 
 * @author yangbosen
 * @version 1.0
 */
public interface PayCashPoolOperationMapper extends Mapper<PayCashPoolOperation> {
    public List<PayCashPoolOperation> findByBusinessOID(String OID);

    /**
     * 插入一条资金池记录
     * 
     * @param operation
     * @return
     */
    public int saveCashPoolOperation(PayCashPoolOperation operation);

}
