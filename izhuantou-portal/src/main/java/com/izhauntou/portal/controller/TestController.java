package com.izhauntou.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.izhuantou.fund.api.FundServiceTest;
import com.izhuantou.service.api.ServiceTest;

@Controller
@RequestMapping("demo")
public class TestController {

    @Autowired
    private ServiceTest serviceTest;
    @Autowired
    private FundServiceTest fundServiceTest;

    @RequestMapping(value = "/print")
    @ResponseBody
    public String queryMenuByUserName() {

	System.err.println("我在portal的controller中调用的Service为 " + serviceTest.getNumber());
	System.err.println("我在controller中调用fundService,获取到的数据库时间为  =========>" + fundServiceTest.queryDate());
	return "goodBay";
    }
}
