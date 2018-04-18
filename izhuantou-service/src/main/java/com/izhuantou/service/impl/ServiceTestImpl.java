package com.izhuantou.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.izhuantou.dao.TestMapper;
import com.izhuantou.service.api.ServiceTest;

@Service
public class ServiceTestImpl implements ServiceTest {

    @Autowired
    private TestMapper testMapper;

    @Override
    public String getNumber() {
	System.err.println("service");
	return " I'm service" + "Dao获取的数据为" + testMapper.queryData();
    }

}
