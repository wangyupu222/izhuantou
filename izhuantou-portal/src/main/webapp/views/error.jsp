<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true" %>
    <%@ include file="/include/url.jsp" %>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<link rel="stylesheet" type="text/css" href="/css/style-common.css">
<Link rel="shortcut icon" href="<%=pathUrl %>/images/zhuan.ico" />

<script type="text/javascript" src="/js/jquery-1.10.1.min.js"></script>
<script src="/js/com.js"></script>
<title>sorry~页面不见咯...</title>
<style>
.headerright {
    width:auto;
}
</style> 
</head> 
<body> 
<div class="headerbg">
<div class="main header">
  <div class="con headercon">
    <div class="headerleft"><a href="/portal/user/index"><img src="<%=pathUrl %>/images/logo.png"></a></div>
    <div class="headerright">
        <div id="navigation">
            <ul class="navcsr">
            <li><a href="/portal/user/index" >首页</a></li>
            <li><a href="/portal/lend/projects">我要出借</a>
            </li>
              <li ><a href="/portal/detial/ztloan">我要借款</a></li>
              <li><a href="/portal/page/security">安全保障</a></li>
              <li><a href="/portal/personal/personalcenter">个人中心</a></li>
            </ul>
        </div>
      
    </div>
   <div class="clear"></div>
  </div>
</div>

</div>
<script>	
</script>
<%-- <script type="text/javascript" src="js/move.js"></script> --%>
<div class="error_wrap">
<div class="error_mian">
<img src="<%=pathUrl %>/images/error_img.png">
<%-- <div class="error_btn"><a href="index.jsp" ></a></div> --%>
</div>

</div>

</body> 
</html>