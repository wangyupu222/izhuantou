<%@ page contentType="text/html; charset=UTF-8" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>修改成功</title>
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

		<div class="forgetpwdtop"><span class="ttt">修改成功</span></div>
        <!-- <div class="forgetpwdtop" id="forgetpwdtop"><a  class="active"><span>1</span>手机号找回密码</a><a ><span>2</span>密保问题找回密码</a></div> -->
        <div class="clear"></div>      
        <div class="writeyhm_wrap">      
       
            <div class="writeyhmf03">
                <div class="row01"><span class="png right"></span><span class="txt">恭喜您！新密码已修改成功！<em>祝您出借开心！</em></span></div>
                <div class="clear"></div>
                <div class="nextbtn btn-a"><a href="login.jsp" >马上登录</a></div>
                
            </div>
       
        
              
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
