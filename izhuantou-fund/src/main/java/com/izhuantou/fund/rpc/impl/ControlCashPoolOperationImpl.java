package com.izhuantou.fund.rpc.impl;

import org.springframework.stereotype.Service;

import com.izhuantou.damain.pay.PayCashPoolOperation;
import com.izhuantou.fund.rpc.api.ControlCashPoolOperation;

@Service("controlCashPoolOperation")
public class ControlCashPoolOperationImpl extends BaseServiceImpl<PayCashPoolOperation>
	implements ControlCashPoolOperation {

}
