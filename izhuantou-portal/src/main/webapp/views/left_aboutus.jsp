<%@ page contentType="text/html; charset=UTF-8" %>

<div class="about_left">

	<div class="<%if("a13702c03fa8056b00abf22527d84c22".equals(request.getParameter("OID"))) {%>aboutmenu active<%}else{ %>aboutmenu<%}%>" ><a href="aboutus.jsp?OID=a13702c03fa8056b00abf22527d84c22">平台介绍</a></div>
    <%-- <div class="<%if("a13861a43fa8056b0046da1a3a070023".equals(request.getParameter("OID"))) {%>aboutmenu active<%}else{ %>aboutmenu<%}%>"><a href="aboutus.jsp?OID=a13861a43fa8056b0046da1a3a070023">团队介绍</a></div> --%>
    <div class="<%if("a13a2d553fa8056b004e16602fb0ce3d".equals(request.getParameter("OID"))) {%>aboutmenu active<%}else{ %>aboutmenu<%}%>"><a href="aboutus.jsp?OID=a13a2d553fa8056b004e16602fb0ce3d">发展历程</a></div>
    <div class="<%if("a13a5ace3fa8056b0169d3bedfa99da6".equals(request.getParameter("OID"))) {%>aboutmenu active<%}else{ %>aboutmenu<%}%>"><a href="aboutus.jsp?OID=a13a5ace3fa8056b0169d3bedfa99da6">企业资质</a></div>
    <div class="<%if("a13a838f3fa8056b00990ef3037789c1".equals(request.getParameter("OID"))) {%>aboutmenu active<%}else{ %>aboutmenu<%}%>"><a href="aboutus.jsp?OID=a13a838f3fa8056b00990ef3037789c1">安全保障</a></div>
    <%-- <div class="<%if("a13aafbb3fa8056b013bfb122618e26c".equals(request.getParameter("OID"))) {%>aboutmenu active<%}else{ %>aboutmenu<%}%>"><a href="aboutus.jsp?OID=a13aafbb3fa8056b013bfb122618e26c">运营数据</a></div> --%>
    <div class="aboutmenu <%if(request.getRequestURL().toString().indexOf("notice.jsp")>0 || request.getRequestURL().toString().indexOf("notice_details.jsp")>0){%>active<%}%>"><a href="notice.jsp">平台公告</a></div>
    <div class="<%if("a1412b813fa8056b0158b0aeb4425a72".equals(request.getParameter("OID"))) {%>aboutmenu active<%}else{ %>aboutmenu<%}%>"><a href="aboutus.jsp?OID=a1412b813fa8056b0158b0aeb4425a72">联系我们</a></div>
    <div class="aboutmenu <%if(request.getRequestURI().toString().indexOf("news.jsp")>0 || request.getRequestURL().toString().indexOf("news_details.jsp")>0){%>active<%}%>"><a href="news.jsp">砖头动态</a></div>
    <div class="aboutmenu <%if(request.getRequestURI().toString().indexOf("mediaNews.jsp")>0 || request.getRequestURL().toString().indexOf("mediaNews_details.jsp")>0){%>active<%}%>"><a href="mediaNews.jsp">媒体报道</a></div>
    <%-- <div class="<%if("f96acee93fa8057801be6c1ebcab5adc".equals(request.getParameter("OID"))) {%>aboutmenu active<%}else{ %>aboutmenu<%}%>"><a href="aboutus.jsp?OID=f96acee93fa8057801be6c1ebcab5adc">隐私策略</a></div> --%>
	<div class="<%if("f96b5d143fa80578001cad5284a3b8f9".equals(request.getParameter("OID"))) {%>aboutmenu active<%}else{ %>aboutmenu<%}%>"><a href="aboutus.jsp?OID=f96b5d143fa80578001cad5284a3b8f9">法律声明</a></div>
	<div class="<%if("f96bb8cf3fa80578009ae40bd834fae4".equals(request.getParameter("OID"))) {%>aboutmenu active<%}else{ %>aboutmenu<%}%>"><a href="aboutus.jsp?OID=f96bb8cf3fa80578009ae40bd834fae4">政策法规</a></div>
	<!-- <div class="mobileapp">
    <img src="images/app.jpg">
    <div class="mobiltxt">随时随地手机理财</div>
    </div> -->
    
</div>