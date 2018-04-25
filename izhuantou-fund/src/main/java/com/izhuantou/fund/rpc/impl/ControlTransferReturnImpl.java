package com.izhuantou.fund.rpc.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.izhuantou.damain.pay.PayTransferReturn;
import com.izhuantou.fund.rpc.api.ControlTransferReturn;

/**
 *
 * @author fucheng
 * @date 2018-03-07
 */
@Service("controlTransferReturn")
public class ControlTransferReturnImpl extends BaseServiceImpl<PayTransferReturn> implements ControlTransferReturn {

    @Override
    public List<PayTransferReturn> findByBusinessOID(String businessOID) {
	try {
	    PayTransferReturn transferReturn = new PayTransferReturn();
	    transferReturn.setBusinessOID(businessOID);
	    List<PayTransferReturn> list = this.queryListByWhere(transferReturn);
	    return list;
	} catch (Exception e) {
	    System.out.println(e.getMessage());
	    return null;
	}
    }

    @Override
    public List<PayTransferReturn> gainTransferReturnByBusinessOIDAndState(String businessOID, String investment) {
	try {
	    PayTransferReturn transferReturn = new PayTransferReturn();
	    transferReturn.setBusinessOID(businessOID);
	    transferReturn.setState(investment);
	    return this.queryListByWhere(transferReturn);
	} catch (Exception e) {
	    System.out.println(e.getMessage());
	    return null;
	}
    }

    @Override
    public List<PayTransferReturn> gainTransferReturnByDebitCreditNewOIDAndState(String strDebitCreditOIDNew,
	    String strState) {
	try {
	    PayTransferReturn transferReturn = new PayTransferReturn();
	    transferReturn.setDebitCreditOIDNew(strDebitCreditOIDNew);
	    transferReturn.setState(strState);
	    return this.queryListByWhere(transferReturn);
	} catch (Exception e) {

	    return null;
	}
    }

    @Override
    public List<PayTransferReturn> gainTransferReturnByDebitCreditOIDAndState(String strDebitCreditOID,
	    String strState) {
	try {
	    PayTransferReturn transferReturn = new PayTransferReturn();
	    transferReturn.setDebitCreditOID(strDebitCreditOID);
	    transferReturn.setState(strState);
	    return this.queryListByWhere(transferReturn);
	} catch (Exception e) {

	    return null;
	}
    }

}
