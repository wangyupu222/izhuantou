<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/include/url.jsp" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>404</title>
<link rel="stylesheet" type="text/css" href="/css/style-common.css">
<Link rel="shortcut icon" href="<%=pathUrl %>/images/zhuan.ico" />
<script type="text/javascript" src="/js/jquery-1.10.1.min.js"></script>
<script src="js/com.js"></script>
<style>
.wrapH{
min-height:535px;
background-color:#f0f4f7;
}
.contentMain{
width:580px;
margin:0 auto;
padding-top:168px;
}
.contentMain img{
display:block;
float:left;
}
.contentMain .conLeft{
float:right;
text-align:left;
}
.contentMain .conLeft h3{
    font-style: normal;
    font-size: 28px;
    color: #FB8A37;
    margin-bottom:10px;
    font-weight: normal;
}
.contentMain .conLeft em{
    font-style: normal;
    color: #999999;
    font-size: 14px;
}
.contentMain .conLeft p{
	font-size: 14px;
	color:#000;
	margin-top:10px;
	line-height: 20px;
}
.splitHr{
height:2px;
width:100%;
border-bottom:1px dotted #999;
margin-top:20px;
}
.LinkWrap{
color:#333;
font-size:14px;
margin-top: 24px;
}
.LinkWrap a{
	width:60px;
	height:30px;
	color:#fff;
	line-height:30px;
	text-align:center;
	background-color:#1284c5;
	display: inline-block;
	border-radius: 4px;
	margin-left: 10px;
}
.footerbg {
    border-top:none;
    margin-top: 0px;
}
</style>
</head>
<body>
<% try{ %>
<!--common header-->
<%@ include file="header.jsp" %>
<!--common end-->
<!--我要出借-->
<div class="main wrapH">
<div class="con">
<div class="contentMain">
<img src="<%=pathUrl %>/images/404img.png">
<div class="conLeft">
<h3>抱歉，页面无法访问......</h3>
<em>可能因为：</em>
<p>网址有错误 &gt; <em>请检查地址是否完整或存在多余字符</em><br>网址已失效 &gt; <em>可能页面已删除，活动已下线等</em></p>
<div class="splitHr"></div>
<div class="LinkWrap">您可以：<a href="/portal/user/index">去首页</a></div>
</div>
<div class="clear"></div>
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
