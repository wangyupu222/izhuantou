package com.izhuantou.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 通用页面跳转
 * 
 * @author fucheng
 * @dete 2018-05-21
 *
 */

@Controller
@RequestMapping("page")
public class PageController {
    @RequestMapping(value = "{pageName}", method = RequestMethod.GET)
    public String index(@PathVariable String pageName) {
	return pageName;
    }
}
