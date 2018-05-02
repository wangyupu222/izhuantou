<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/include/url.jsp" %>
<!doctype html>
<html><head>
<meta charset="utf-8">
<title>砖头网-出借成功</title>
<link rel="stylesheet" type="text/css" href="/css/style-common.css">
<link rel="stylesheet" type="text/css" href="/css/comsty.css">
<link rel="stylesheet" type="text/css" href="/css/pedetails.css">
<link rel="stylesheet" href="/css/responsiveslides.css">
<Link rel="shortcut icon" href="<%=pathUrl %>/images/zhuan.ico" />
<script type="text/javascript" src="/js/jquery-1.10.1.min.js"></script>
<script src="/js/com.js"></script>
<!--<script src="/js/comjd.js"></script>-->
<!--首页焦点图-->
<script type="text/javascript" src="/js/koala.min.1.5.js"></script>

</head>
<body>
<% try{ %>
<!--common header-->
<%@ include file="header.jsp" %>
<!--common end-->

<!--我要出借-->
<div class="main conheight">
	<div class="con smallnav">
    </div>
	
    <div class="con tzcgok">    
    	<div class="tzimg">恭喜您！操作成功！</div>
        <div class="butlink">
        	<a href="/portal/lend/projects">查看其他产品</a>
            <a href="/portal/personal/personalcenter">进入个人中心</a>
        </div>
    </div>
    
</div>    

<!-----------------中间部分结束----------------> 
<!--bottom common-->
<%@ include file="footer.jsp" %>
<%}catch(Exception e){
	response.sendRedirect("error.jsp");
    return;  
} %>
</body>

</html>
