package com.izhuantou.fund.api;

import java.util.List;

import com.izhuantou.damain.pay.PayTransferReturn;

/**
 *
 * @author fucheng
 * @date 2018-03-07
 */
public interface ControlTransferReturn extends BaseService<PayTransferReturn> {

    public List<PayTransferReturn> findByBusinessOID(String businessOID);

    /**
     * 根据businessOID 和 investment 获取列表
     * 
     * @param businessOID
     * @param investment
     * @return
     */
    public List<PayTransferReturn> gainTransferReturnByBusinessOIDAndState(String businessOID, String investment);

    public List<PayTransferReturn> gainTransferReturnByDebitCreditNewOIDAndState(String strDebitCreditOIDNew,
	    String strState);

    public List<PayTransferReturn> gainTransferReturnByDebitCreditOIDAndState(String strDebitCreditOID,
	    String strState);

}
