package com.izhuantou.fund.impl;

import org.springframework.stereotype.Service;

import com.izhuantou.damain.pay.PayCashPoolOperation;
import com.izhuantou.fund.api.ControlCashPoolOperation;

@Service("controlCashPoolOperation")
public class ControlCashPoolOperationImpl extends BaseServiceImpl<PayCashPoolOperation>
	implements ControlCashPoolOperation {

}
