<%@ page contentType="text/html; charset=UTF-8" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>找回登录密码</title>
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
<div class="con forgetpwdcon">

		<div class="forgetpwdtop"><span class="ttt">找回登录密码</span></div>
        <!-- <div class="forgetpwdtop" id="forgetpwdtop"><a  class="active"><span>1</span>手机号找回密码</a><a ><span>2</span>密保问题找回密码</a></div> -->
        <div class="clear"></div>      
        <div class="writeyhm_wrap">
       
        <form   action="/portal/user/updatePasswordOneTwo" method="post" id="checkValidateProblem" >
           <%if("1".equals(session.getAttribute("unsetproblem").toString())){ %>
           <div class="writeyhm">
           		<input type="hidden" value='<%=session.getAttribute("userName").toString() %>' name="name">
                <div class="forgetpwd_inpt"><label>问题一：</label><em><%=session.getAttribute("questionOne").toString() %></em></div>
                <div class="forgetpwd_inpt"><label>答案一：</label><span><input type="text"  name="answerOne" id="answer1"  placeholder='请输入问题答案' required data-msg-required='请输入问题答案' /></span></div>
                <div class="forgetpwd_inpt"><label>问题二：</label><em><%=session.getAttribute("questionTwo").toString() %></em></div>
      			<div class="forgetpwd_inpt"><label>答案二：</label><span><input type="text"   name="answerTwo" id="answer2" placeholder='请输入问题答案' required data-msg-required='请输入问题答案'/></span></div>
                 <div style="margin-left:200px;margin-top:20px;color:#f00;font-size:13px;">${msg }</div>
                <div class="nextbtn"><button id="NextBtn" name="" type="submit" value="下一步">下一步</button></div>
            </div>
           <%}else{ %>
           <div class="unsetproblem">您尚未设置密保问题，请联系客服，或者通过<a href="forgetpwd01.jsp?way=1">手机号码</a>找回密码。</div>
           <%} %>
             
        </form>
        
              
        </div>
        
</div>



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
	$("#FormValidateProblem").validate({
		submitHandler: function(form) {
			//验证通过后 的js代码写在这里
			//alert(1);
			form.submit();
		}
	})
	
})
//保留页面值
$(function(){
    //jquery.validate
    var error = $("#error1 div").text();
    if(error != null && error!="" &&error!='null'){
    	if(error.indexOf("一") < 0){
    		$("#answer1").val('<%=request.getAttribute("answer1") %>');
    	}else if(error.indexOf("二") < 0){
    		$("#answer2").val('<%=request.getAttribute("answer2") %>');
    	}
    	
    }
	
})



//配置错误提示的节点，默认为label，这里配置成 span （errorElement:'span'）
$.validator.setDefaults({
	errorElement:'span'
});

//配置通用的默认提示语
$.extend($.validator.messages, {
	required: '必填',
    equalTo: "两次输入不一致"
});




//手机验证规则  
jQuery.validator.addMethod("mobile", function (value, element) {
    var mobile = /^1[3|4|5|7|8]\d{9}$/;
	return this.optional(element) || (mobile.test(value));
}, "手机格式不对");

//切换手机号和密保问题选项卡
/* $(function(){
    $(".forgetpwdtop  a").click(function(){
        var index=$(this).index();        
        $(this).addClass("active").siblings().removeClass("active");              
        $(".writeyhm").eq(index).css("display","block").parent().siblings().find(".writeyhm").css("display","none");
    })
}) */
</script>


<%}catch(Exception e){
	response.sendRedirect("error.jsp");
    return;  
} %>
</body>

</html>
