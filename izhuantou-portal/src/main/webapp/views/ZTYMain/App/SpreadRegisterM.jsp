<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/include/url.jsp" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>砖头网-上砖头儿，有赚头儿！</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0,maximum-scale=1.0, user-scalable=no"/>
<link href="/css/normalize.css" type="text/css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="/css/login.css">
<link rel="stylesheet" type="text/css" href="/css/ZTYWrap/ActivityRegisterM.css">
<Link rel="shortcut icon" href="<%=pathUrl %>/images/zhuan.ico" />
<script type="text/javascript" src="/js/jquery-1.10.1.min.js"></script>
    
<script>
function huoquyzm(){	
	var phoneNum = document.getElementById('phone_reg').value;	
	var yzm= document.getElementById('yzm').value;	
	  var oSign_val=$("#sign_val");
	if (phoneNum!=""){
		if(yzm!=""){
			
		
		//var data = {
		        //"handleClassName":"pageRegisterFormRegisterdjGetYzm","phone":phoneNum
	//	    };
	//验证手机号是否存在
		
  	 $.ajax({
		        type:"POST",
		        dataType:"json",
		        url:"/p2p/cn/com/hoonsoft/servlet/ServletAction?handleClassName=registVerification&phone_reg="+phoneNum+"&yzm="+yzm,
		        data:"{}",
		        success: function(result){
		   		if(result[0].error){
		   		    oSign_val[0].innerHTML = result[0].error;
		   		$('.m').attr("src","/p2p/cn/com/hoonsoft/servlet/ServletValidateCode?"+Math.random());
	        		}else{
	        		    $.ajax({
	        		        type:"POST",
	        		        dataType:"text",
	        		        url:"/p2p/cn/com/hoonsoft/servlet/ServletSMSValidateCode?name=smsValidateCode&mobile="+phoneNum+"&yzm="+yzm,
	        		        data:{},
	        		        
	        		        success: function(result){
	        		            console.info(result);
	        		            //alert(result);
	        		            
	        		              
	        		        },
	        		        error:function(){
	        		          	oSign_val[0].innerHTML ="系统异常！";	        		            
	        		        }
	        		    });
	        		    sends.send();
	        		    $('.m').attr("src","/p2p/cn/com/hoonsoft/servlet/ServletValidateCode?"+Math.random());
	        		}
		        },
		        error:function(result){
		        	$('.m').attr("src","/p2p/cn/com/hoonsoft/servlet/ServletValidateCode?"+Math.random());
		        }
		    });
		}else{
			oSign_val[0].innerHTML ="请输入验证码！";
			$('.m').attr("src","/p2p/cn/com/hoonsoft/servlet/ServletValidateCode?"+Math.random());
		}
	}else{
		oSign_val[0].innerHTML ="请输入手机号！";
		$('.m').attr("src","/p2p/cn/com/hoonsoft/servlet/ServletValidateCode?"+Math.random());
	}

}
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

	<div class="wrap">
		<img class="APPregisterBanner" src="<%=pathUrl %>/image/registerActive/SpreadRegister/APPregisterBanner.png?v=114">
		<div class="FormContent">
			<form handleClassName="SpreadRegistWeb" name="FormRegister">
				<input type="hidden" value="wapRegister" readonly="readonly" name="register" />
				<input type="hidden" value="mojifen" readonly="readonly" name="Channel"/>
				<input type="hidden" value="Spread" readonly="readonly" name="typeVal"/>
				<div class="regist_inpt phone urog-form-role">
					<input type="text" name="phone_reg" id="phone_reg" placeholder="请输入您的手机号码" required="" data-rule-mobile="true" onfocus="this.placeholder=''" onblur="this.placeholder='请输入您的手机号码'">
					<img src="<%=pathUrl %>/image/close_icon.png" class="urog-regclose close_btn" data-close="phone_reg" />
				</div>
				<div class="yzm_wrap">
				<div class="regist_inpt yzm short short2">
					<input type="text" name="yzm" id="yzm" autocomplete="off" placeholder="请输入验证码" required="" minlength="4" maxlength="4" onfocus="this.placeholder=''" onblur="this.placeholder='请输入验证码'">
				</div>
				<a href="#" class="num_yzm"><img width="100%" height="42" src="${systemPath}/cn/com/hoonsoft/servlet/ServletValidateCode" class="m" alt="如果看不清,请点击我!" title="如果看不清,请点击我!" "  onclick="this.src='${systemPath}/cn/com/hoonsoft/servlet/ServletValidateCode?' + Math.random();"></a>
				</div>
				<div class="yzm_wrap smsYzm_wrap">
				<div class="regist_inpt yzm short sms_yzm ">
					<input type="text" name="yzm_reg" id="yzm_reg" placeholder="请输入短信验证码" required="" onfocus="this.placeholder=''" onblur="this.placeholder='请输入短信验证码'">
				</div>
				<a class="hqyzm hqyzminp" onclick="huoquyzm()">获取验证码</a>
				</div>
				
				<div class="regist_inpt pwd pwd_short urog-form-role">
					<input type="password" name="password_reg" id="password_reg" placeholder="请输入8~20位字符密码" required="" data-rule-password="true" minlength="8" maxlength="20" onfocus="this.placeholder=''" onblur="this.placeholder='请输入8~20位字符密码'">
					<img src="<%=pathUrl %>/image/close_icon.png" class="urog-regclose close_btn" data-close="password_reg" /><i class="eye_show"><img src="<%=pathUrl %>/images/eye_close.png"></i>
				</div>
				<div class="regist_inpt yqm urog-form-role">
					<input type="text" name="yqm_reg" id="yqm_reg" placeholder="请输入推荐人手机号" onfocus="this.placeholder=''" onblur="this.placeholder='请输入推荐人手机号'">
					<img src="<%=pathUrl %>/image/close_icon.png" class="urog-regclose close_btn" data-close="yqm_reg" />
				</div>
				<div class="regist_check xieyi_text">
					<label class="checklabel checklabel01"><input type="checkbox" checked="checked" required="" data-msg-required="必选" name="isAgree" id="isAgree" value="ok">我已阅读并同意</label> <a href="agreement_z.jsp?contractType=1&biddingType=qt" target="_blank" class="ra">《砖头网注册协议》</a>
				</div>
				<div align="center"></div>
				<div class="register_validate" id="sign_val">
					<hoontag:Message name="registerErro" />
				</div>
				<div class="zcbtn">
					<button id="register2" name="register2" type="submit" value="注册即送加息好礼" class="zcbtn_input">注册即送加息好礼</button>
				</div>
				
			</form>
		</div>
		<img class="APPregisterCon" src="<%=pathUrl %>/image/registerActive/SpreadRegister/APPregisterCon01.jpg?v=114">
		<img class="APPregisterCon" src="<%=pathUrl %>/image/registerActive/APPregisterCon02.jpg">
		<img class="APPregisterCon" src="<%=pathUrl %>/image/registerActive/APPregisterCon03.jpg">
		<div class="APPregisterBtn"><a href="#">立即注册</a></div>
	</div>

<script>
//滑动验证码
$(function(){
	$('.huadimg').mouseover(function(){
		$('.huadimgyz').show();
	}).mouseout(function(){
		$('.huadimgyz').hide();
	})
	
	
	$(".eye_show").click(function(){
		if($(".pwd_short input").attr("type")=="password"){
			$(".pwd_short input").attr("type","text");
			$(".eye_show img").attr("src","<%=pathUrl %>/images/eye_open.png");
		}else if($(".pwd_short input").attr("type")=="text"){
			$(".pwd_short input").attr("type","password");
			$(".eye_show img").attr("src","<%=pathUrl %>/images/eye_close.png");
		}
	})
})
</script>

<%}catch(Exception e){
	response.sendRedirect("error.jsp");
    return;  
} %>

</body>
<!--验证表单-->
<script src="/js/jquery.validate.js"></script>
<script>
$(function(){
    //jquery.validate
	$("#FormRegister").validate({
		submitHandler: function(form) {
			//验证通过后 的js代码写在这里
			form.submit();
		}
	})
	
	
	
	var oSign_ph = $(".phone input");
    var oSign_paw = $(".pwd input");
    var oSign_yzm = $(".sms_yzm input");
    var onum_yzm = $(".short2 input");
    var oSign_tjr = $(".yqm input");
    var oSign_val=$("#sign_val");
    $(".hqyzm").click(function () {
        var isright = true;
        var str = "";
        if (!oSign_ph.val()) {
            str += "请输入手机号！";
            isright = false;
        }  else if (/^1(3|4|5|7|8)\d{9}$/.test(oSign_ph.val()) == false) {
            str += "请输入正确的手机号！";
            isright = false;
        }else if (!onum_yzm.val()) {
            str += "请输入图形验证码";
            isright = false;
        } 
        if (isright == true) {
            oSign_val[0].innerHTML = "";
            //alert("恭喜你，注册成功！");

        } else {
            oSign_val[0].innerHTML = str;
        }
    })

    $(".zcbtn").click(function () {
        var isright = true;
        var str = "";
        if (!oSign_ph.val()) {
            str += "请输入手机号！";
            isright = false;
        }  else if (/^1(3|4|5|7|8)\d{9}$/.test(oSign_ph.val()) == false) {
            str += "请输入正确的手机号！";
            isright = false;
        }else if (!onum_yzm.val()) {
            str += "请输入图形验证码";
            isright = false;
        } else if (!oSign_yzm.val()) {
            str += "请输入短信验证码";
            isright = false;
        } else if (!oSign_paw.val()) {
            str += "请输入密码！";
            isright = false;
        } else if (/^\w*(?=\w*\d)(?=\w*[a-zA-Z])\w{8,20}$/.test(oSign_paw.val()) == false) {
            str += "请输入8~20位字符密码，仅支持字母和数字！";
            isright = false;
        } 
        if (isright == true) {
            oSign_val[0].innerHTML = "";
            //alert("恭喜你，注册成功！");

        } else {
            oSign_val[0].innerHTML = str;
        }
    })
  //登录注册保留值

	var state = <%=request.getAttribute("state") %>;
if(state != "" && state!=null){
	var phone='<%=request.getAttribute("phone") %>';
	var password='<%=request.getAttribute("password") %>';
		if(phone != "" && phone!=null&& phone != 'null'){
			oSign_ph.val(phone);
		}
		if(password != "" && password!=null&& password != 'null'){
			oSign_paw.val(password);
		}
	
}
})

//配置错误提示的节点，默认为label，这里配置成 span （errorElement:'span'）
$.validator.setDefaults({
	errorElement:'span'
});

//配置通用的默认提示语
$.extend($.validator.messages, {
	required: '',//必填
    equalTo: "",//两次输入不一致
    maxlength: $.validator.format(""),//Please enter no more than {0} characters.
    minlength: $.validator.format(""),//Please enter at least {0} characters.
});




//手机验证规则  
jQuery.validator.addMethod("mobile", function (value, element) {
    var mobile = /^1[3|4|5|7|8]\d{9}$/;
	return this.optional(element) || (mobile.test(value));
}, "");//手机格式不对

//密码验证规则
jQuery.validator.addMethod("password", function (value, element) {
    var password = /^\w*(?=\w*\d)(?=\w*[a-zA-Z])\w*$/;
	return this.optional(element) || (password.test(value));
}, "");//密码只能是数字和字母


</script>
<script>
        $(function() {
            $(".urog-form-role").click(function(event){
                //得到点击事件的dom结构
                var click_node = getEventTrigger(event);
                //得到点击close图片的data-close
                //data-close的值是input框的id
                var clickName = click_node.getAttribute("data-close");
                //得到点击事件的属性（INPUT）
                var clickInput = click_node.nodeName;
                //判断点击的是close图片，则清空相应id输入框的值，并隐藏该close图片
                if(clickName=='phone_reg' || clickName=='password_reg' || clickName=='yqm_reg'){
                    $("#"+clickName).val(null);
                    $("#"+clickName).siblings(".urog-regclose").css("display","none");
                }
                //判断点击事件的元素是input框
                if(clickInput=="INPUT"){
                    //获取INPUT的id
                    var next_closeId = click_node.id;
                    //alert(next_closeId);
                    //绑定获取焦点和输入值-函数
                    $("#"+next_closeId).bind("focus keyup",function(){
                        //判断当前输入框的值不为空时
                        if($("#"+next_closeId).val()!=''){
                            $(".urog-regclose").css("display","none");
                            //当前输入框的下一个同袍节点样式显示
                            $("#"+next_closeId).siblings(".urog-regclose").css({"display":"block","opacity":"1"});
                        }else{
                        	$(".urog-regclose").css("display","none");
                        }

                    })
                }
            })
           $(".urog-form-role input").blur(function(){
            	//alert("aa");
            	$(".urog-regclose").css("opacity","0");
            })
            //得到点击事件的dom结构
            function getEventTrigger(event)
            {
                //ie 火狐 event兼容
                //参考资料： http://www.cnblogs.com/quanhai/archive/2010/04/20/1716149.html
                event = event? event: window.event
                var x = event.srcElement ? event.srcElement:event.target;
                return x;
            }
        })
    </script>
</html>
