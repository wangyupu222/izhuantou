package com.izhuantou.portal.filter;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.izhuantou.common.constant.Constant;
import com.izhuantou.common.utils.StringUtil;

public class RemoteLoginFilter implements Filter {

	/**
	 * 用户和Session绑定关系
	 */
	public static final Map<String, HttpSession> USER_SESSION = new HashMap<String, HttpSession>();
	/**
	 * seeionId和用户的绑定关系
	 */
	public static final Map<String, String> SESSIONID_USER = new HashMap<String, String>();

	public static final Map<String, Date> SESSION_DATE = new HashMap<String, Date>();

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpReq = (HttpServletRequest) req;
		HttpServletResponse httpRes = (HttpServletResponse) res;
		HttpSession session = httpReq.getSession();
		if (session.getAttribute(Constant.MEMBER_OID) != null && session.getAttribute(Constant.USER_MOBLIE) != null) {
			String memberOID = (String) session.getAttribute(Constant.MEMBER_OID);
			String name = (String) session.getAttribute(Constant.USER_MOBLIE);
			if (StringUtil.isNotEmpty(memberOID) && StringUtil.isNotEmpty(name)) {
				if (USER_SESSION.get(name) == null) {
					if (USER_SESSION.size() > 100) {
						USER_SESSION.clear();
						SESSIONID_USER.clear();
						SESSION_DATE.clear();
					}
					USER_SESSION.put(name, session);
					SESSIONID_USER.put(session.getId(), name);
					SESSION_DATE.put(session.getId(), new Date());

				} else {
					HttpSession oldSession = USER_SESSION.get(name);
					String oldSessionId = oldSession.getId();
					Date oldDate = SESSION_DATE.get(oldSessionId);
					long old = oldDate.getTime();
					long ne = new Date().getTime();
					int space = (int) ((ne - old) / 1000 / 60);
					if (space < 30) {
						if (oldSessionId.equals(session.getId())) {
							String oldname = SESSIONID_USER.get(oldSessionId);
							if (!oldname.equals(name)) {
								USER_SESSION.put(name, session);
								SESSIONID_USER.put(session.getId(), name);
							}
							SESSION_DATE.put(session.getId(), new Date());
						} else {
							oldSession.removeAttribute(Constant.MEMBER_OID);
							oldSession.removeAttribute(Constant.USER_MOBLIE);
							oldSession.setAttribute(Constant.FORCE_LOGOUT, "您的账号在其他设备登录，已被迫下线");
							USER_SESSION.put(name, session);
							SESSIONID_USER.put(session.getId(), name);
							SESSION_DATE.put(session.getId(), new Date());
						}
					} else {
						USER_SESSION.clear();
						SESSIONID_USER.clear();
						SESSION_DATE.clear();
					}
				}

			}

		}
		chain.doFilter(httpReq, httpRes);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
