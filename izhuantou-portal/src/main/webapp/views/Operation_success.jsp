<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/include/url.jsp" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>砖头网-操作成功</title>
<link rel="stylesheet" type="text/css" href="/css/style-common.css">
<link rel="stylesheet" type="text/css" href="/css/debitone.css"/>
<link rel="shortcut icon" href="<%=pathUrl %>/images/zhuan.ico" />
<script type="text/javascript" src="/js/jquery-1.8.3.min.js"></script>
<style>
body{background-color:#fff;}
.jksucess{margin-top:100px;}
.successok{ width:720px; margin:0 auto;}
.scok-p{text-align:center;}
.scok-p div{text-align:center;}
.tishi_text{margin-top:35px;font-size:30px;}
.okok{text-align: center;margin-top:55px;}
.exclamatory{margin:0 auto;    width: 151px;}
.exclamatory img{display: inline-block;float:left;margin-top: 12px;}
.exclamatory em{float: right;
    font-weight: bold;
    font-size: 24px;
    color: #fea956;
    display: inline-block;}
.tishi_text_new{    line-height: 36px;width: 435px;text-align: left;margin: 0 auto;color: #666;margin-top:15px;    font-size: 18px;}
#backpre{width:228px;height:42px;background-color:#55b1fe;text-align:center;border-radius: 5px;line-height:42px;font-size:14px;color:#fff;display: inline-block;cursor: pointer;}
#backpre:hover{background-color:#53a9f1;}
</style>
</head>

<body>
<% try{ %> 
<div class="main mainscok minhh">
<div class="con jksucess">

        <div class="successok">
        <div class="scok-p"><div><img src="<%=pathUrl %>/images/operation_icon.png"></div>
        <div class="tishi_text" >操作完成，请关闭窗口，并在砖头网查看操作结果</div>
        <div class="exclamatory"><img src="<%=pathUrl %>/images/exclamatory_mark_i.jpg"><em>温馨提示</em><div class="clear"></div></div>
        <p class="tishi_text_new">由于网络的影响，您账户余额可能会延迟显示，如果<br>20分钟内还未解决，请及时联系客服400-900-9677。</p>
        </div>
        <div class="okok"><a id="backpre" onclick="closeWebPage();" >关闭该窗口</a></div>
        </div>
    </div>
</div> 
<%}catch(Exception e){
	response.sendRedirect("error.jsp");
    return;  
} %>
<script>
/* function CloseWebPage(){
	 if (navigator.userAgent.indexOf("MSIE") > 0) {
	  if (navigator.userAgent.indexOf("MSIE 6.0") > 0) {
	   window.opener = null;
	   window.close();
	  } else {
	   window.open('', '_top');
	   window.top.close();
	  }
	 }
	 else if (navigator.userAgent.indexOf("Firefox") > 0) {
	  window.location.href = 'about:blank ';
	 } else {
	  window.opener = null;
	  window.open('', '_self', '');
	  window.close();
	 }
	} */

	function closeChrome(){    
		var userAgent = navigator.userAgent;
		if (userAgent.indexOf("Firefox") != -1 || userAgent.indexOf("Chrome") !=-1) {
		    location.href="about:blank";
		} else {
		    window.opener = null;
		    window.open('', '_self');
		}
		window.close();
	}    

	
function closeWebPage(){  
	if (navigator.userAgent.indexOf("MSIE") > 0) {//close IE  
		  if (navigator.userAgent.indexOf("MSIE 6.0") > 0) {  
		   window.opener = null;  
		   window.close();  
		  } else {  
		   window.open('', '_top');  
		   window.top.close();  
		  }  
		 }  
		 else if (navigator.userAgent.indexOf("Firefox") > 0) {//close firefox  
		  window.location.href = 'about:blank ';  
		 } else {//close chrome;It is effective when it is only one.  
		  window.opener = null;  
		  window.open('', '_self');  
		  window.close();  
		 }  
	}  
</script>
</body>
</html>
