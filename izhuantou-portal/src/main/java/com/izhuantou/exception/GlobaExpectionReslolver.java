package com.izhuantou.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * 全局异常处理
 *
 * @author fucheng
 * @date 2018-03-23
 */

public class GlobaExpectionReslolver implements HandlerExceptionResolver {

    private static Logger log = Logger.getLogger(GlobaExpectionReslolver.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object arg2,
	    Exception e) {
	// 写日志
	log.error("运行时期的异常", e);
	// 给后台管理系统发送信息，以便维护人员及时处理问题
	System.out.println(e.getMessage());
	// 返回错误页面
	ModelAndView modelAndView = new ModelAndView();
	modelAndView.setViewName("/error");
	modelAndView.addObject("message", "你得网络异常请稍候重试");
	return modelAndView;
    }

}
