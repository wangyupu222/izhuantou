package com.izhuantou.fund.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.izhuantou.dao.TestMapper;
import com.izhuantou.fund.api.FundServiceTest;
import com.izhuantou.third.api.ControlPayService;

@Service("fundServiceTest")
public class FundServiceTestImpl implements FundServiceTest {

    @Autowired
    private TestMapper testMapper;
    @Autowired
    private ControlPayService controlPayService;

    public String queryDate() {
	System.err.println("fund service");
	return "通过dubbo接口获取fund服务,查询到的数据库时间为 ------>" + testMapper.queryData() + "从third系统调用的时间为:"
		+ controlPayService.queryDate();
    }

}
