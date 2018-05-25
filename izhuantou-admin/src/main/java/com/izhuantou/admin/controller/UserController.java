package com.izhuantou.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.izhuantou.damain.ManagerUser;

@Controller
@RequestMapping("user")
public class UserController {

    // 登录方法
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(ManagerUser user) {
	/*
	 * // 基于shiro框架实现认证操作 Subject subject = SecurityUtils.getSubject(); //
	 * 参数封装,密码可能要进行加密操作 UsernamePasswordToken token = new
	 * UsernamePasswordToken(user.getUserName(), user.getPassWard()); //
	 * realm认证 try { subject.login(token); ManagerUser principalUser =
	 * (ManagerUser) subject.getPrincipal(); // principalUser放入到Session中
	 * return "SUCCESS"; } catch (Exception e) { return "fail"; }
	 */

	return "index";

    }
}
