<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/include/url.jsp" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>找回登录密码</title>
<link rel="stylesheet" type="text/css" href="/css/style-common.css">
<link rel="stylesheet" type="text/css" href="/css/login.css">
<Link rel="shortcut icon" href="<%=pathUrl %>/images/zhuan.ico" />
<script type="text/javascript" src="/js/jquery-1.10.1.min.js"></script>
<script src="/js/com.js"></script>
<style>
.way01,.way02{
display:none;
}
.active{
display:block;
}
.pwdBtn,.mobileBtn{
    cursor: pointer;
}
</style>
<script>
function huoquyzm(){	
	var phoneNum = document.getElementById('phone_reg').value;	
	var yzm= document.getElementById('yzm').value;	
	  var oSign_val=$("#sign_val");
	  var smsValidateCode="smsValidateCode";
	  alert(1);
	if (phoneNum!=""){
		if(yzm!=""){
	//验证手机号是否存在
			$.ajax({
		        type:"POST",
		        dataType:"json",
		        url:"/portal/user/checksms",
		        data:{
		        	msmType:smsValidateCode,
		        	name:phoneNum,
		        	yzm:yzm
		        },   
		        success: function(result){
		            console.info(result);
		            //alert(result);
		            sends.send();
		            $('.m').attr("src","checkcode?x="+Math.random());
		        },
		        error:function(){
		          	oSign_val[0].innerHTML ="系统异常！";	        		            
		        }
		    });
		    
		}else{
			oSign_val[0].innerHTML ="请输入验证码！";
			$('.m').attr("src","checkcode?x="+Math.random());
		}
	}else{
		oSign_val[0].innerHTML ="请输入手机号！";
		$('.m').attr("src","checkcode?x="+Math.random());
	}
}
/* function nextPage1(){
	
	var phoneNum = document.getElementById('phone_reg').value;	
	var yzm= document.getElementById('yzm').value;	
	  var oSign_val=$("#sign_val");
	  var smsValidateCode="smsValidateCode";
	if (phoneNum!=""){
		if(yzm!=""){
	//验证手机号是否存在
			$.ajax({
		        type:"POST",
		        dataType:"json",
		        url:"/portal/user/updatePasswordOne",
		        data:{
		        	msmType:smsValidateCode,
		        	name:phoneNum,
		        	yzm:yzm
		        },   
		        success: function(result){
		            console.info(result);
		            //alert(result);
		            sends.send();
		            $('.m').attr("src","checkcode?x="+Math.random());
		        },
		        error:function(){
		          	oSign_val[0].innerHTML ="系统异常！";	        		            
		        }
		    });
		    
		}else{
			oSign_val[0].innerHTML ="请输入验证码！";
			$('.m').attr("src","checkcode?x="+Math.random());
		}
	}else{
		oSign_val[0].innerHTML ="请输入手机号！";
		$('.m').attr("src","checkcode?x="+Math.random());
	}
} */
	




//倒计时
var sends = {
		checked:1,
		send:function(){
				var numbers = /^1\d{10}$/;
				var val = $('#phone_reg').val().replace(/\s+/g,""); //获取输入手机号码
				
				if(numbers.test(val)){
					//alert(numbers.test(val));
					var time = 59;
					//$('.div-phone span').remove();
					function timeCountDown(){
						if(time==0){
							clearInterval(timer);
							$('a.hqyzm').removeClass('send1').html("获取验证码");
							$("a.hqyzm").attr("onclick","huoquyzm();");
							//$('a.hqyzm').removeAttr('onclick');
							sends.checked = 1;
							return true;
						}
						$('a.hqyzm').html(time+"s后再发送");
						$('a.hqyzm').removeAttr('onclick');
						time--;
						return false;
						sends.checked = 0;
					}
					$('a.hqyzm').addClass('send1');
					
					timeCountDown();
					var timer = setInterval(timeCountDown,1000);
				}
		}
	}
</script>
</head>
<body>
<% try{ %>
<!--common header-->
<%@ include file="header.jsp" %>
<!--common end-->

<!--中间部分开始-->

<div class="con forgetpwdcon">

		<div class="way01 active">
		<div class="forgetpwdtop"><span class="ttt">找回登录密码</span><span class="rrr">通过<a class="pwdBtn">密保问题</a>找回密码</span></div>
        <!-- <div class="forgetpwdtop" id="forgetpwdtop"><a  class="active"><span>1</span>手机号找回密码</a><a ><span>2</span>密保问题找回密码</a></div> -->
        <div class="clear"></div>      
        <div class="writeyhm_wrap">
        <form action="/portal/user/updatePasswordOne" method="post" id="FormValidateCode"  >
        <input name="updway" type="hidden" value="1" />
            <div class="writeyhm">
                <div class="forgetpwd_inpt"><label>手机号码：</label><span class="phone_Password"><input type="text" name="name" id="name" placeholder='请输入手机号' required data-rule-mobile='true' data-msg-required='请输入手机号' data-msg-mobile='请输入正确格式' maxlength='11'  /></span></div>
                <div class="forgetpwd_inpt short"><label>图形验证码：</label><span><input type="text" name="yzm" id="yzm" placeholder='请输入图形验证码' required data-msg-required='请输入图形验证码' maxlength='4' />
                <img class="yzmsz" width="100" height="38" src="checkcode"  class="m" alt="如果看不清,请点击我!" title="如果看不清,请点击我!"  onclick="this.setAttribute('src','checkcode?x='+Math.random());"></span>
                </div>
                <div class="forgetpwd_inpt "><label>短信验证码：</label><span>
                <input type="text" name="msm" id="msm" placeholder='请输入短信验证码' required data-msg-required='请输入短信验证码' maxlength='6' />
                <a href="#" class="hqyzm" onclick="huoquyzm()">获取短信验证码</a></span>
                <div class="register_validate register_new" id="sign_val"> <hoontag:Message name="registerErro"/>${msg}</div></div>
                
                <div class="nextbtn"><button id="NextBtn" name="" type="submit" value="下一步">下一步</button></div> 
            </div>
            <div class="wenxints">温馨提示：请保持手机通畅以便于接收短信验证码。若您无法接收短信，请联系客服400-900-9677</div>
        </form>      
        </div>
        </div>        
		<!-- 密保问题 -->
        <div class="way02">
		<div class="forgetpwdtop"><span class="ttt">找回登录密码</span><span class="rrr">通过<a class="mobileBtn">手机号码</a>找回密码</span></div>
        <!-- <div class="forgetpwdtop" id="forgetpwdtop"><a  class="active"><span>1</span>手机号找回密码</a><a ><span>2</span>密保问题找回密码</a></div> -->
        <div class="clear"></div>      
        <div class="writeyhm_wrap">
        <form  action="/portal/user/forgetpwd0102" method="post" id="FormValidateCode">
        <input name="updway" type="hidden" value="2" />
            <div class="writeyhm">
                <div class="forgetpwd_inpt"><label>手机号码：</label><span class="phone_Password"><input type="text" name="name" id="phone_reg" placeholder='请输入手机号' required data-rule-mobile='true' data-msg-required='请输入手机号' data-msg-mobile='请输入正确格式'  /></span></div>
                <div class="forgetpwd_inpt short"><label>验证码：</label><span><input type="text" name="yzm" id="yzm_reg" placeholder='请输入验证码' required data-msg-required='请输入验证码' maxlength='4' />
                <img class="yzmsz" width="100" height="38" src="checkcode"  class="m" alt="如果看不清,请点击我!" title="如果看不清,请点击我!""  onclick="this.setAttribute('src','checkcode?x='+Math.random());"></span>
                <div style="margin-left:200px;margin-top:20px;color:#f00;font-size:13px;">${msg }</div></div>
                <div class="nextbtn"><button id="NextBtn" name="" type="submit" value="下一步">下一步</button></div>
            </div>
        </form>      
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
	$("#FormValidateCode").validate({
		submitHandler: function(form) {
			//验证通过后 的js代码写在这里
			//alert(1);
			form.submit();
		}
	})
})

$(".pwdBtn").on('click',function(){
	$(".way01").removeClass("active").siblings(".way02").addClass("active");
})
$(".mobileBtn").on('click',function(){
	$(".way02").removeClass("active").siblings(".way01").addClass("active");
})

//保留验证之后的值
$(function(){
	var phone='<%=request.getAttribute("phone_reg") %>';
	if(phone != "" && phone!=null&& phone != 'null'){
		$(".phone_Password input").val(phone);
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
