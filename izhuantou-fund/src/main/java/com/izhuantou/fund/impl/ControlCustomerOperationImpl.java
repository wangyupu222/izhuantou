package com.izhuantou.fund.impl;

import org.springframework.stereotype.Service;

import com.izhuantou.damain.pay.PayCustomerOperation;
import com.izhuantou.fund.api.ControlCustomerOperation;

@Service("controlCustomerOperation")
public class ControlCustomerOperationImpl extends BaseServiceImpl<PayCustomerOperation>
	implements ControlCustomerOperation {

}
