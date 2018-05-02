<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/include/url.jsp" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>出错了</title>
<link rel="stylesheet" type="text/css" href="/css/style-common.css">
<link rel="stylesheet" type="text/css" href="/css/comsty.css">
<link rel="stylesheet" type="text/css" href="/css/debitone.css"/>
<Link rel="shortcut icon" href="<%=pathUrl %>/images/zhuan.ico" />
<script type="text/javascript" src="/js/jquery-1.10.1.min.js"></script>
<script src="/js/grzx.js"></script>
<script src="/js/com.js"></script>
 <script type="text/javascript">
    var t=3;
    function countDown(){
    var time = document.getElementById("time");
    t--;
    time.innerHTML=t;
    if (t<=0){
    	window.history.go(-1);
        clearInterval(inter);
    };
    }
    var inter = setInterval("countDown()",1000); 
</script> 
</head>

<body>
<%@ include file="header.jsp" %>

<div class="main">
    <div class="con smallnav">
        <a href="#">信息提示页面</a>
    </div>
</div>
<!--资料上传成功-->
<div class="main mainscok minhh">
    <div class="con jksucess">
        <div class="succ">
        <div class="scok-p scok-error"><span></span>异常信息提示<hoontag:Message /></div>        
        <div class="btn"><a onclick="window.history.back();" class="btn-a">返回上一页</a><span class="timetxt"><span id="time">5</span>秒后自动跳转首页，<a >点击立刻跳转。</a></span></div>
        </div>
    </div>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>
