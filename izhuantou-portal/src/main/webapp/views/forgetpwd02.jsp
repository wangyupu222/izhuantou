<%@ page contentType="text/html; charset=UTF-8" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>重置密码</title>
<link rel="stylesheet" type="text/css" href="/css/style-common.css">
<link rel="stylesheet" type="text/css" href="/css/login.css">
<Link rel="shortcut icon" href="/images/zhuan.ico" />
<script type="text/javascript" src="/js/jquery-1.10.1.min.js"></script>
<script src="/js/com.js"></script>
</head>
<body>
<% try{ %>
<!--common header-->
<%@ include file="header.jsp" %>
<!--common end-->

<!--中间部分开始-->
<form  action="/portal/user/updatePasswordTwo" method="post" id="updatePassword"  name="FormupdatePassword"  >
<div class="con forgetpwdcon">
	<div class="forgetpwdtop"><span class="ttt">重置密码</span></div>
	 <!-- <div class="forgetpwdtop"><a  ><span>1</span>填写账户名</a><a  class="active"><span>2</span>重置密码</a></div> -->
     <div class="clear"></div>
     <div class="writeyhm">
     	<input type="hidden" value='<%=session.getAttribute("userName").toString() %>' name="name">
     	<div class="forgetpwd_inpt"><label>请输入新密码：</label><span><input type="password" name="password" id="newPassword" placeholder='请输入8~20位字符密码' required data-msg-minlength='请输入最少8位' data-msg-password='密码必须由数字和字母组成'  minlength='8' maxlength='20'  /></span></div>
        <div class="forgetpwd_inpt short"><label>请确认新密码：</label><span><input type="password" name="password2" id="password_reg" placeholder='请输入8~20位字符密码' required equalTo='#newPassword' /></span></div>
        <div style="margin-left:200px;margin-top:20px;color:#f00;font-size:13px;">${msg }</div>
        <div class="nextbtn"><button id="NextBtn" name="" type="submit" value="修改密码">修改密码</button></div>
     </div>
</div>
</form>


<!-----------------中间部分结束----------------> 
<!--bottom common-->
<%@ include file="footer.jsp" %>

<script>
//滑动验证码
$(function(){
	$('.huadimg').mouseover(function(){
		$('.huadimgyz').show();
	}).mouseout(function(){
		$('.huadimgyz').hide();
	})
})
</script>
<script src="/js/jquery.validate.js"></script>
<script>
$(function(){
    //jquery.validate
	$("#FormupdatePassword").validate({
		submitHandler: function(form) {
			//验证通过后 的js代码写在这里
			//alert(1);
			form.submit();
		}
	})
	
})





//配置错误提示的节点，默认为label，这里配置成 span （errorElement:'span'）
$.validator.setDefaults({
	errorElement:'span'
});

//配置通用的默认提示语
$.extend($.validator.messages, {
	required: '新密码不能为空',
    equalTo: "两次输入不一致"
});




//手机验证规则  
jQuery.validator.addMethod("mobile", function (value, element) {
    var mobile = /^1[3|4|5|7|8]\d{9}$/;
	return this.optional(element) || (mobile.test(value));
}, "手机格式不对");

//手密码验证规则
jQuery.validator.addMethod("password", function (value, element) {
    var password = /^\w*(?=\w*\d)(?=\w*[a-zA-Z])\w*$/;
	return this.optional(element) || (password.test(value));
}, "密码只能是数字和字母");

</script>
<%}catch(Exception e){
	response.sendRedirect("error.jsp");
    return;  
} %>
</body>

</html>
