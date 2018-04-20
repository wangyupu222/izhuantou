package com.izhuantou.fund.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.izhuantou.dao.TestMapper;
import com.izhuantou.fund.api.FundServiceTest;

@Service("fundServiceTest")
public class FundServiceTestImpl implements FundServiceTest {

    @Autowired
    private TestMapper testMapper;

    public String queryDate() {
	System.err.println("fund service");
	return "通过dubbo接口获取fund服务,查询到的数据库时间为 ------>" + testMapper.queryData();
    }

}
