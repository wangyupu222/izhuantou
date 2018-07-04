package com.izhuantou.portal.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.izhuantou.common.utils.RedisUtils;
import com.izhuantou.service.api.ServiceTest;

@Controller
@RequestMapping("demo")
public class TestController {

    @Autowired
    private ServiceTest serviceTest;

    @Autowired
    private RedisUtils redisUtils;
    
    @RequestMapping(value = "/print")
    @ResponseBody
    public String queryMenuByUserName() {

	System.err.println("我在portal的 controller中调用的Service为 " + serviceTest.getNumber());

	return "goodBay";
    }
    
    @RequestMapping(value = "/findOne")
    @ResponseBody
    public String findOne(String key){
    	String value=redisUtils.get(key);
    	return value;
    }
    
    @RequestMapping(value = "/setOne")
    @ResponseBody
    public void setOne(String key,String value){
    	redisUtils.set(key, value);
    }
    
    @RequestMapping(value = "/del")
    @ResponseBody
    public void del(String key){
    	redisUtils.del(key);
    }
    
    @RequestMapping(value = "/hget")
    @ResponseBody
    public String hget(String key,String field){
    	String value=redisUtils.hget(key, field);
    	return value;
    }
    
    @RequestMapping(value = "/hmset")
    @ResponseBody
    public void hmset(String key,String field1,String field2){
    	Map<String, String> map=new HashMap<String,String>();
    	map.put(field1, "123");
    	map.put(field2, "456");
    	redisUtils.hmset(key, map);
    }
    @RequestMapping(value = "/hmgetAll")
    @ResponseBody
    public Object hmgetAll(String key){
    	
    	 Map<String, String> map=redisUtils.hgetAll(key);
    	
    	return map;
    }
}
