<%@ page language="java" import="java.util.*" errorPage="" %>
<%@ page language="java" import="java.math.BigDecimal" errorPage="" %>
<%@ taglib uri="http://www.hoonsoft.com.cn/hoontag/content" prefix="web"%>
<%@ taglib uri="http://www.hoonsoft.com.cn/hoontag" prefix="hoontag" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" import="cn.com.hoonsoft.environment.EnvironmentSystem" %>

<%
String systemPath = EnvironmentSystem.contextPath;
String systemPathWeb = EnvironmentSystem.contextPath + "/web/";
pageContext.setAttribute("systemPath", systemPath);
pageContext.setAttribute("systemPathWeb", systemPathWeb);
%>
