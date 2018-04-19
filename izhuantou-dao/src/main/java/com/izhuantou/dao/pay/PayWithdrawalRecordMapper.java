package com.izhuantou.dao.pay;

import com.github.abel533.mapper.Mapper;
import com.izhuantou.damain.pay.PayWithdrawalRecord;

/**
 * 客户提现相关记录
 * 
 * @author dear
 * @version 1.0
 */
public interface PayWithdrawalRecordMapper extends Mapper<PayWithdrawalRecord> {
    /**
     * 保存记录
     * 
     * @return
     */
    public int saveWithdrawalrecord(PayWithdrawalRecord withdrawalRecord);

    /**
     * 根据流水号 requestID查看提现的记录
     * 
     * @param requestID
     * @return
     */
    public PayWithdrawalRecord findWithdeawalByRequestID(String requestID);

}
