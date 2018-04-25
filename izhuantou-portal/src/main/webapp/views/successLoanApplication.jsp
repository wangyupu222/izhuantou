<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/include/url.jsp" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>砖头网-操作成功</title>
<link rel="stylesheet" type="text/css" href="/css/style-common.css">
<link rel="stylesheet" type="text/css" href="/css/comsty.css">
<link rel="stylesheet" type="text/css" href="/css/debitone.css"/>
<Link rel="shortcut icon" href="<%=pathUrl %>/images/zhuan.ico" />
<script type="text/javascript" src="/js/jquery-1.10.1.min.js"></script>
<script src="/js/grzx.js"></script>
<script src="/js/com.js"></script>
<script type="text/javascript">
    var t=5;
    function countDown(){
    var time = document.getElementById("time");
    t--;
    time.innerHTML=t;
    if (t<=0){
        location.href="LoanApplication.jsp?type=2";
        clearInterval(inter);
    };
    }
    var inter = setInterval("countDown()",1000);
</script>
 
 
 
 
</head>

<body>
<% try{ %>
<%@ include file="header.jsp" %>

<div class="main">
    <div class="con smallnav">
        <a href="#">首页</a><span>&gt;</span><a href="ztloan.html">操作成功</a>
       
    </div>
</div>
<!--资料上传成功-->
<div class="main mainscok minhh">
    <div class="con jksucess">
        <div class="succ">
        <div class="scok-p"><span></span>恭喜您！操作成功！</div>
        <!-- <div class="bott-p">感谢您的申请，我们会在1-3个工作日对您的信息进行审核，请您耐心等待！</div> -->
        
        <div class="btn"><a href="LoanApplication.jsp?type=2" class="btn-a">我的申请记录</a><span class="timetxt"><span id="time">5</span>秒后自动跳转，<a >点击立刻跳转。</a></span></div>
        </div>
    </div>
</div>
<!--资料上传成功结束-->        


<%@ include file="footer.jsp" %>

<%}catch(Exception e){
	response.sendRedirect("error.jsp");
    return;  
} %>
</body>
</html>
