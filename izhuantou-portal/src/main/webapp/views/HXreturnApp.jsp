<%@ page contentType="text/html; charset=UTF-8" %>
<!doctype html>
<html>
<head>

<title>成功</title>
<style>
body{ background-color:#fff !important;}
 /*返回成功页面*/
    .rigthfh{ min-height:500px;max-width:640px;  margin:auto; min-width:300px;position: relative; }
    .rightout{ width:90%; margin:0px 4%; margin:auto; margin-top:10%; margin-bottom:10%;}
    .right_fh{min-height:56px; text-align:center; color:#666; font:16px/16px "微软雅黑";}
    .right_fh img{width:56px;}
    .error_fh{ min-height:56px;margin-top:30px; margin-left:50px; padding-left:70px;  color:red; font:16px/56px "微软雅黑";}
	.dlk{width:80%;margin:0px auto;}
	.dlk a{text-decoration:none; outline:none;display:block; width:70%; font-size:14px; height:40px; background-color:#32b7fc; color:#fff; border-radius:12px; line-height:40px; text-align:center;margin:0 auto;-webkit-box-shadow: 0px 3px 2px rgba(50, 183, 252, 0.7);   
 -moz-box-shadow: 0px 3px 2px rgba(50, 183, 252, 0.7);   
 box-shadow: 0px 3px 2px rgba(50, 183, 252, 0.7);}
 .phone_wrap{width:46%;height:60px;outline:none; position: absolute;font-size:12px;text-align:center;bottom:8%;left:50%;margin-left:-22%;border-color:#999;-webkit-box-shadow:inset 0 0 10px #32b7fc; border-radius:35px; 
  -moz-box-shadow:inset 0 0 10px #32b7fc;  
  box-shadow:inset 0 0 10px #32b7fc; }
  .phone_wrap p{
  padding:0;
  margin:5px 0px;
  margin-top:10px;
  color:#32b7fc;
  }
  .phone_wrap span{color:#666;}
</style>
<script type="text/javascript" src="/js/jquery-1.10.1.min.js"></script>
</head>

<body>
<% try{ %>
<div class="rigthfh">
        <div class="rightout">
            <div class="right_fh"><img src="/images/app_recharge_icon.jpg">
            <p>您的操作已受理</p>
            <p>稍后返回个人中心即可查看操作结果</p>
            <%-- <p>您的账户余额：<span><%=request.getAttribute("availablemoney")%></span>元</p> --%>
            </div>
        </div>
        <div class="dlk dlkerror"><a class="backPersonalCenter"  href="javascript:void(0);">返回用户中心</a> <div style="clear:both;"></div></div>
    <div class="phone_wrap ServiceTel" style="display:none;"><p>客服电话：400-900-9677</p><span>（周一至周五：9:00-18:00）</span></div>
    </div>
    
<%}catch(Exception e){
	response.sendRedirect("error.jsp");
    return;  
} %>
</body>
<script>


window.onload = function () {
	var oHeight=$(window).height();
	$(".rigthfh").css("height",oHeight);
	
	var u = navigator.userAgent;
	if (u.indexOf('Android') > -1 || u.indexOf('Linux') > -1) {//安卓手机
	//alert("安卓手机");
	$(".backPersonalCenter").click(function(){
		back2Center();
	})
	$(".continuereCharge").click(function(){
		continueRecharge();
	})
	$(".ServiceTel").click(function(){
		makeCall();
	})
	
	// window.location.href = "mobile/index.html";
	} else if (u.indexOf('iPhone') > -1) {//苹果手机
	// window.location.href = "mobile/index.html";
	//alert("苹果手机");
	$(".backPersonalCenter").click(function(){
		backPersonalCenter();
	})
	$(".continuereCharge").click(function(){
		continuereCharge();
	})
	$(".ServiceTel").click(function(){
		ServiceTel();
	})
	} else if (u.indexOf('Windows Phone') > -1) {//winphone手机
	//alert("winphone手机");
	// window.location.href = "mobile/index.html";
	}
		
	}
	
	
	/* iphone oc */
function backPersonalCenter() {
    location.href = "rrcc://backPersonalCenter"
}
function continuereCharge() {
    location.href = "rrcc://continuereCharge"
}
function ServiceTel() {
    location.href = "rrcc://ServiceTel"
}


/* android */
	
	function back2Center() {
		javascript:android.back2Center();
	}
	function continueRecharge() {
	    javascript:android.continueRecharge();
	}
	function makeCall() {
		javascript:android.makeCall();
	}
</script>
</html>