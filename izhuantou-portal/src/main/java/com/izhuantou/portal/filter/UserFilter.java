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
public class UserFilter implements Filter {

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
	    throws IOException, ServletException {
	HttpServletRequest request = (HttpServletRequest) servletRequest;
	HttpServletResponse response = (HttpServletResponse) servletResponse;
	// 获取根目录所对应的绝对路径
	String currentURL = request.getRequestURI();
	// 截取到当前文件名用于比较
	String targetURL = currentURL.substring(currentURL.indexOf("/", 1), currentURL.length());
	// 如果session不为空就返回该session，如果为空就返回null
	HttpSession session = request.getSession(false);
	if (!"/portal/user/login".equals(targetURL)) {
	    // 判断当前页面是否是重定向到登陆页面页面，如果是就不做session的判断，防止死循环
	    if (session == null || session.getAttribute("userMobile") == null) {
		response.sendRedirect(request.getContextPath() + "/portal/user/login");
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
