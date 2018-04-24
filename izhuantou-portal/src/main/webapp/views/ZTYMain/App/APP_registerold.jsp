<%@ page contentType="text/html; charset=UTF-8" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0,maximum-scale=1.0, user-scalable=no"/>
<link rel="stylesheet" type="text/css" href="/css/appcommon.css">
<link href="/css/normalize.css" type="text/css" rel="stylesheet">
<link href="/css/appcommon.css" type="text/css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="/css/login.css">
<Link rel="shortcut icon" href="/images/zhuan.ico" />
<script type="text/javascript" src="/js/jquery-1.10.1.min.js"></script>
    <style>
    	img{vertical-align: middle;}
        .wrap{ margin:0 auto; max-width:640px; min-width:300px;position:relative;}
        .wrap img.APPregisterBanner,.wrap img.APPregisterCon{width:100%;display:block;}
        #FormRegister{overflow:hidden;width:85%;margin:0 auto;padding-top: 10px;}
        .regist_inpt{border:none;height:45px;padding-left: 45px;margin-top: 15px;border-radius: 8px;}
        #FormRegister input{outline:none;}
        .regist_inpt input {height: 44px;line-height: 44px;font-size:14px;}
        .FormContent{background-color:#ffdd89;}
        .regist_inpt.phone { width:99%;background: #fff url(//images/phone.png) no-repeat 10px center !important;}
        .regist_inpt.yzm {width: 65%;}
        .yzm_wrap{width:99%;position:relative;}
        .num_yzm{width:30%;margin-left;right:0px;top:0px;text-align:right;}
        .num_yzm{max-width:135px;}
        .regist_inpt.pwd_short input {width: 82%;}
        .smsYzm_wrap{}
        .smsYzm_wrap .regist_inpt.yzm {width: 54%;}
        .regist_inpt.short input {width: 92%;}
        .hqyzminp {width:41%;position: absolute;background: #55b0fd;color: #fff;padding: 0px 0px;margin-left: 0px;text-align: center;float: initial;margin-top: 0px;border-radius: 8px;font-size: 14px;cursor: pointer;right:0px;top:0px;height:45px;line-height:45px;margin-right: 0px;}
    	.regist_inpt.pwd {width:99%;background: #fff url(/images/pwd.png) no-repeat 10px center;}
    	.regist_inpt.yqm {width:99%; background: #fff url(/images/yzm.png) no-repeat 10px center;}
    	.zcbtn button {width: 99%;margin: 0 auto;}
    	.zcbtn{margin-bottom:38px;}
    </style>
    <script>
function huoquyzm(){	
	var phoneNum = document.getElementById('phone_reg').value;	
	var yzm= document.getElementById('yzm').value;
	var oSign_paw = $(".pwd input");
	  var oSign_val=$("#sign_val");
	if (phoneNum!=""){
		if (/^1(3|4|5|7|8)\d{9}$/.test(phoneNum) == true) {
			if (oSign_paw.val()!="") {
				if (/^\w*(?=\w*\d)(?=\w*[a-zA-Z])\w{8,20}$/.test(oSign_paw.val()) == true) {
		if(yzm!=""){
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
					oSign_val[0].innerHTML ="请输入8~20位字符,仅支持字母和数字！";
					$('.m').attr("src","/p2p/cn/com/hoonsoft/servlet/ServletValidateCode?"+Math.random());
				}
			}else{
				oSign_val[0].innerHTML ="请输入密码！";
				$('.m').attr("src","/p2p/cn/com/hoonsoft/servlet/ServletValidateCode?"+Math.random());
			}
		}else{
			oSign_val[0].innerHTML ="手机请输入正确的手机号！";
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
	<div class="wrap">
		<img class="APPregisterBanner" src="/image/registerActive/APPregisterBanner.jpg">
		<div class="FormContent">
			<form handleClassName="regist" name="FormRegister">
				<input type="hidden" value="注册" readonly="readonly" name="register" />
				<div class="regist_inpt phone urog-form-role">
					<input type="text" name="phone_reg" id="phone_reg" placeholder="请输入您的手机号码" required="" data-rule-mobile="true" onfocus="this.placeholder=''" onblur="this.placeholder='请输入您的手机号码'">
					<img src="/image/close_icon.png" class="urog-regclose close_btn" data-close="phone_reg" />
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
					<img src="/image/close_icon.png" class="urog-regclose close_btn" data-close="password_reg" /><i class="eye_show"><img src="/images/eye_close.png"></i>
				</div>
				<div class="regist_inpt yqm urog-form-role">
					<input type="text" name="yqm_reg" id="yqm_reg" placeholder="请输入推荐人手机号" onfocus="this.placeholder=''" onblur="this.placeholder='请输入推荐人手机号'">
					<img src="/image/close_icon.png" class="urog-regclose close_btn" data-close="yqm_reg" />
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
		<img class="APPregisterCon" src="/image/registerActive/APPregisterCon.jpg">

	</div>
</body> 
</html>