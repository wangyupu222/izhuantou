package com.izhauntou.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.izhuantou.service.api.ServiceTest;

@Controller
@RequestMapping("demo")
public class TestController {

    @Autowired
    private ServiceTest serviceTest;

    @RequestMapping(value = "/print", method = RequestMethod.POST)
    @ResponseBody
    public String queryMenuByUserName() {

	System.err.println("我在portal的controller中调用的Service为 " + serviceTest.getNumber());
	return "goodBay";
    }
}
