package com.izhuantou.data.rpc.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.izhuantou.dao.TestMapper;
import com.izhuantou.data.rpc.api.DataServiceTest;

public class DataServiceTestImpl implements DataServiceTest {

    @Autowired
    private TestMapper testMapper;

    @Override
    public String queryDate() {
	return "我是从dataService 获取的数据库时间为==========>" + testMapper.queryData();
    }

}
