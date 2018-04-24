<%@ page contentType="text/html; charset=UTF-8" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>我要借贷04</title>
<link rel="stylesheet" type="text/css" href="/css/style-common.css">
<link rel="stylesheet" type="text/css" href="/css/comsty.css">
<link rel="stylesheet" type="text/css" href="/css/debitone.css"/>
<Link rel="shortcut icon" href="/images/zhuan.ico" />
<script type="text/javascript" src="/js/jquery-1.10.1.min.js"></script>
<script src="/js/grzx.js"></script>
<script src="/js/com.js"></script>
<style>
.successok{ width:300px; margin:0 auto;}
/*确认关闭*/
.okok a{ display:block; width:300px; text-align:center; height:40px; line-height:40px; background:#55b0fd; border-radius:5px; color:#fff; margin-top:60px;}
.jksucess{ border:0;}
</style>
</head>

<body style="width:100%;min-width:100%; background:#fff;">
<% try{ %>
 <div class="main">
    <div class="jksucess">
        <div class="successok">
        <div class="scok-p"><span></span>恭喜您！操作成功！</div>
        <div class="okok"><a id="backpre" style="cursor: pointer;"  >确定</a></div>
        </div>
    </div>
</div>


<%}catch(Exception e){
	response.sendRedirect("error.jsp");
    return;  
} %>
</body>
<script>
$(function(){
		window.parent.location.reload();
})
</script>
</html>
