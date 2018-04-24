<%@ page contentType="text/html; charset=UTF-8" %>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>砖头网-修改成功</title>
<link rel="stylesheet" type="text/css" href="/css/style-common.css" />
<link rel="stylesheet" type="text/css" href="/css/comsty.css" />
<link rel="stylesheet" type="text/css" href="/css/debitone.css"/>
<link rel="shortcut icon" href="/images/zhuan.ico" />
<script type="text/javascript" src="/js/jquery-1.10.1.min.js"></script>
<script type="text/javascript">
    var t=3;
    function countDown(){
    var time = document.getElementById("time");
    t--;
    time.innerHTML=t;
    if (t<=0){
    	window.location='/portal/user/login';     
        clearInterval(inter);
    };
    }
    function countDown2(){
    	window.location='/portal/user/login';
    	clearInterval('countDown()'); 
    }
    var inter = setInterval("countDown()",1000);  
</script>
<style>
.jksucess  .scok-p{
    margin-top: 180px;
}
.btn{
margin-top: 50px;
}
.btn a{
    cursor: pointer;
}
</style>
</head>
<body >
<% try{ %>      
<%@ include file="header.jsp" %>
<!--资料上传成功-->
<div class="main mainscok minhh">
<div class="con jksucess">

        <div class="succ">
        <div class="scok-p"><span></span>登录密码修改成功，请重新登录</div>        
        <div class="btn"><a onclick="countDown2();" class="btn-a">立即登录</a><span class="timetxt"><span id="time">3</span>秒后自动跳转</span></div>
        </div>
    </div>
</div> 
        

<%@ include file="footer.jsp" %>        

<%}catch(Exception e){
	response.sendRedirect("error.jsp");
    return;  
} %> 
</body>
</html>
