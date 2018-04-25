package com.izhuantou.portal.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 查看用户是否登陆过，未登录禁止访问一些特定页面 的过滤器
 * 
 * @author fucheng
 *
 */
public class BandingCareFilter implements Filter {

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
	    throws IOException, ServletException {
	HttpServletRequest request = (HttpServletRequest) servletRequest;
	HttpServletResponse response = (HttpServletResponse) servletResponse;
	String currentURL = request.getRequestURI();
	String targetURL = currentURL.substring(currentURL.indexOf("/", 1), currentURL.length());
	HttpSession session = request.getSession(false);
	// 判断用户是否进行了绑卡
	if (!"/portal/personal/addbankcard".equals(targetURL)) {
	    // 防止死循环
	    if (session == null || session.getAttribute("isCreate").equals("false")) {
		response.sendRedirect(request.getContextPath() + "/portal/personal/addbankcard");
		return;
	    }
	}
	// 继续向下执行
	chain.doFilter(request, response);

    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {

    }

}
