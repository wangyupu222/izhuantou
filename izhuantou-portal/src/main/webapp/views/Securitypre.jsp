<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/include/url.jsp" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>砖头网-安全中心</title>
<link rel="stylesheet" type="text/css" href="/css/style-common.css">
<link rel="stylesheet" type="text/css" href="/css/personalcenter.css">
<link rel="stylesheet" type="text/css" href="/css/zhglaq.css">
<Link rel="shortcut icon" href="<%=pathUrl %>/images/zhuan.ico" />
<script type="text/javascript" src="/js/jquery-1.10.1.min.js"></script>
<script src="/js/com.js"></script>
<script src="/js/resubmit.js"></script>
<script language="javascript" src="/js/menu.js"></script>
<style>
.my_loantop{ background: #fff;}
</style>
<script>
function huoquyzm(){	
	//var phoneNum = document.getElementById('phone_reg').value;
	var phoneNum = $("#zhmmphone").text();
	//alert(phoneNum);
	var data = {
	        //"handleClassName":"pageRegisterFormRegisterdjGetYzm","phone":phoneNum
	    };
	    $.ajax({
	        type:"POST",
	        dataType:"text",
	        url:"/p2p/cn/com/hoonsoft/servlet/ServletSMSValidateCode?mobile="+phoneNum,
	        data:data,
	        success: function(result){
	            console.info(result);
	            //alert(result);
	        },
	        error:function(){
	            alert("异常!!!");
	            
	        }
	    });
	
	//倒计时
	sends.send();
	
}
//倒计时
var sends = {
		checked:1,
		send:function(){
				var numbers = /^1\d{10}$/;
				//var val = $('#phone_reg').val().replace(/\s+/g,""); //获取输入手机号码
				var val = $("#zhmmphone").text();
				
				if(numbers.test(val)){
					//alert(numbers.test(val));
					var time = 59;
					
					//$('.div-phone span').remove();
					function timeCountDown(){
						
						if(time==0){
							clearInterval(timer);
							$('a.hqyzm').removeClass('send1').html("短信验证码");
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
<!--弹框-->
<div class="cd-popup1">
<div class="cd-popup-container1">
<!--协议内容-->
	<div class="comnei ">
        <!-- <a class="close cd-popup-close"></a>
        <p class="commit-1"><span>实名认证</span></p> -->
        
        <div class="agreementc">
          
          <div class="renzheng">
                         开通富有账户，完成实名认证
          
          </div>
          <a href="#" class="inpsub colseok">立即开通</a>
          <!-- <input type="button" class="inpsub colseok" value="立即开通"  />-->
        </div> 
    </div>
       <!--协议内容结束-->
       
        
     </div>
</div>
<!--common header-->
<%@ include file="header.jsp" %>
<!--common end-->
<div class="main minhh">
	<div class="con personcon">
    	<div class="smallnav">
    	<a >当前位置</a><span>:</span><a >个人中心</a><span>&gt;</span><a >用户中心</a><span>&gt;</span><a>安全中心</a>
        </div>
    <div class="con xqdetial">
	<%@ include file="left_percenter.jsp" %>
    <div class="xqdeleft">
    <div class="my_loantop"><a href="#" class="active">安全中心</a><span class="L1">&nbsp;</span></div>

    <div class="clear" style="clear: both"></div>

    <div class="cw-rtop">
    <!-- <p class="p-t1"><span>安全信息</span><div class="clear"></div></p> -->
    <div class="cw-rxg">
    <ul>
    <li class="iscrea"><div class="cw_wrap"><img id="iscreatefyImg" class="sp_img" src="<%=pathUrl %>/images/warn_icon.png" /><span class="sp-2">实名认证</span>    
        <span class="sp-3"><span class="wei_new">未认证</span><span style="margin-top: 2px">绑定银行卡，完成实名认证</span></span>
   <a class="ljrz-1" href="addbankcard.jsp">立即认证</a>
         
    <div class="clear" style="clear: both"></div></div></li>
    <!--认证成功-->
    <!--<li><div class="cw_wrap"><img class="sp_img" src="images/right_icon.png"><span class="sp-2">实名认证</span><span class="sp-3">王**（120***********4432）身份证不允许修改、更换或注销</span></div></li>-->
    <li>
    
    <div class="cw_wrap"><img class="sp_img" src="<%=pathUrl %>/images/right_icon.png"><span class="sp-2">手机认证</span><span class="sp-3 phoneSet"></span><div class="clear" style="clear: both"></div></div></li>
    <li><div class="cw_wrap"><img class="sp_img" src="<%=pathUrl %>/images/right_icon.png"><span class="sp-2">登录密码</span><span class="sp-3">已设置</span><a class="ljrz-1 slide_down ljrz-2">重置</a><div class="clear" style="clear: both"></div></div>
    <div style="clear: both"></div>
    <div class="hide_list" id="resetPassword1">
    <div class="down_wrap">
    <!-- 修改登录密码-->
    <form action="/portal/personal/updataPassword" id="FormUpdateLoginPassword" >
    <div class="down_box">
    <span class="title_text">请输入原密码：</span><input type="password" id="password"  name="password" class='pas_text' required data-msg-required='旧密码不能为空' data-rule-password='true' />
    <!-- <input class="pas_text" type="password"><em class="validate">旧密码不能为空 or 旧密码输入错误</em> --> 
    </div>
    <div class="down_box">
    <span class="title_text">请输入新密码：</span><input type="password" id="passwordnew" name="password2" class='pas_text' required data-msg-required='新密码不能为空'  data-rule-password='true' data-msg-minlength='请输入最小8位' data-msg-password='密码必须由数字和字母组成'  minlength='8' maxlength='20' />
    <!-- <input class="pas_text" type="password" ><em class="validate">新密码不能为空</em> -->
    </div>
    <div class="down_box">
    <span class="title_text">请再输入新密码：</span><input type="password" id="passwordnewd" name="password3" class='pas_text' required data-msg-required='确认新密码不能为空' equalTo='#passwordnew' />
    <!-- <input class="pas_text" type="password" ><em class="validate">确认新密码不能为空</em> -->
    </div>
    <div id="error1" class="error">${msg}</div>
    <div class="down_box">
   <div class="down_btn"><button id="" name="" type="submit" value="确认修改" class="xiugx">确认修改</button></div><!-- <em class="validate">两次输入密码不一致 or 旧密码新密码不能相同</em> -->
    </div>
    <div style="clear: both"></div>
    </form>
    </div>

    </div>
    </li>
    <!-- 修改交易密码-->
    <li class='questPwd'><div class="cw_wrap"><img id="iscreatefyImg2" class="sp_img" src="<%=pathUrl %>/images/warn_icon.png"><span class="sp-2">交易密码</span>
         <span class="sp-3"><span class="wei_new">未设置</span><span style="margin-top: 2px">您必须设置交易密码才可以进行出借和借款</span></span><a class="ljrz-1 ljrz-new" href="MyCard.jsp">立即设置</a>
         <a class="ljrz-1 ljrz-2 hide" href="/portal/cash/updatePayPassword" target="_blank">重置</a>
         <script type="text/javascript">
          $("#iscreatefyImg2").attr("src","<%=pathUrl %>/images/right_icon.png");	
         </script>
    
   <div class="clear" style="clear: both"></div></div>
    </li>
    <!--设置密保问题-->
    <li><div class="cw_wrap"><img id="questionImg" class="sp_img" src="<%=pathUrl %>/images/warn_icon.png">
    <span class="sp-2">密保问题</span><span class="sp-3 pwdSet">
        <span class="wei_new">未设置</span> <span style="margin-top: 2px">密保问题可以提高密码安全性</span></span><a class="ljrz-1 slide_down">立即设置</a>
        
<div class="clear" style="clear: both"></div></div>
    <div class="hide_list"  id="resetPassword3">
    <div class="down_wrap">
    
    <form action="/portal/personal/insertTwoQuestion" method="post" name="FormSaveMiBaoProblem" id='FormSaveMiBaoProblem'>
    <div class="down_box">
    <span class="title_text">问题一：</span><select id="problem1" name="questionOne" class="pas_select" required data-msg-required='请选择问题一'><option value="">--选择问题--</option>
    <option value="1">您最喜欢的历史人物的姓名是？</option>  
    <option value="2">您家的地址是？</option>
    <option value="3">您配偶的姓名是？</option>
    <option value="4">您小学班主任的姓名是？</option>
    <option value="5">您的第一次旅行目的地是哪里？</option>
    <option value="6">您暗恋的第一个人姓名是？</option>
    <option value="7">您大学的宿舍号是？</option>
    <option value="8">对您影响最大的人的姓名是？</option>
    <option value="9">您的第一个宠物叫什么？</option>
    <option value="10">您的工号是？</option>
    
    </select>
    </div>
    <div class="down_box">
    <span class="title_text">答案一：</span><input type="text" id="answer1" name="answerOne" class='pas_text' required data-msg-required='答案一不能为空' data-rule-answer='true' /><!-- <em class="validate">不能包含特殊字符，只能由字母数字汉字组成</em> -->
    </div>
    <div class="down_box">
    <span class="title_text">问题二：</span><select id="problem2" name="questionTwo"  class="pas_select" required data-msg-required='请选择问题二' data-rule-questionSame='true' ><option value="">--选择问题--</option>
    <option value="1">您最喜欢的历史人物的姓名是？</option>
    <option value="2">您家的地址是？</option>
    <option value="3">您配偶的姓名是？</option>
    <option value="4">您小学班主任的姓名是？</option>
    <option value="5">您的第一次旅行目的地是哪里？</option>
    <option value="6">您暗恋的第一个人姓名是？</option>
    <option value="7">您大学的宿舍号是？</option>
    <option value="8">对您影响最大的人的姓名是？</option>
    <option value="9">您的第一个宠物叫什么？</option>
    <option value="10">您的工号是？</option>
	</select>
    </div>
    <div class="down_box">
    <span class="title_text">答案二：</span><input type="text" id="answer2" name="answerTwo" class='pas_text' required data-msg-required='答案二不能为空' data-rule-answer='true' data-rule-answerSame='true'  /><!-- <em class="validate">不能包含特殊字符，只能由字母数字汉字组成</em> -->
    </div>
    <div id="error1" class="error"><hoontag:Message /></div>
    <div class="down_box">
    <div class="down_btn"><button id="" name="" type="submit" value="确认" class="xiugx mibao_xiugx">确认</button>
    </div>
    </div>
    <div style="clear: both"></div>
    </form>
    </div>

    </div>
    </li>
    </ul>
    </div>
    </div>
    <div class="clear"></div>
    </div>

    </div>

    </div>
</div>
<!-----------------中间部分结束----------------> 
<!--bottom common-->
<%@ include file="footer.jsp" %>

<%}catch(Exception e){
	response.sendRedirect("error.jsp");
    return;  
} %>
</body>
<!--  <script>
    jQuery(function($) {
	  $('input[type="checkbox"]').onoff();
	});
  </script> -->
<!--<script src="/js/tabmenu.js"></script>-->
<!--验证表单-->
<script src="/js/jquery.validate.js"></script>
<script>
//修改登录密码
$(function(){
    //jquery.validate
	$("#FormUpdateLoginPassword").validate({
		submitHandler: function(form) {
			//验证通过后 的js代码写在这里
			//alert(1);
			form.submit();
			return validataForm("xiugx");
		}
	})
	
	$.ajax({
        type: "get",//请求方式
        url: "/portal/personal/security",//地址，就是json文件的请求路径
        dataType: "json",//数据类型可以为 text xml json  script  jsonp
        success: function(result){//返回的参数就是 action里面所有的有get和set方法的参数
        console.log(result.dataValue);
        if(result.dataValue.iscreatefy==1){
        	$(".iscrea .sp-3").html(result.dataValue.nameCN);
        	$(".iscrea .ljrz-1").addClass("hide");
        	$("#iscreatefyImg").attr("src","<%=pathUrl %>/images/right_icon.png");	
        	$(".questPwd .sp-3").html("已设置");
        	$(".questPwd .ljrz-1").addClass("hide");
        	$(".ljrz-new").addClass("hide");
        	$(".ljrz-2").removeClass("hide");
        	$("#iscreatefyImg2").attr("src","<%=pathUrl %>/images/right_icon.png");
        }
        $(".phoneSet").text(result.dataValue.mobile+"（暂不支持手机号码更换）");
        if(result.dataValue.question==1){
        	$(".pwdSet").html("已设置");
        	$(".pwdSet").next(".slide_down").addClass("hide");
        	$("#questionImg").attr("src","<%=pathUrl %>/images/right_icon.png");
        }
        
        },
		error:function(result){
    	}
    });
	
})
//设置交易密码
$(function(){
    //jquery.validate
	$("#FormUpdateJiaoyiPassword").validate({
		submitHandler: function(form) {
			//验证通过后 的js代码写在这里
			//alert(1);
			form.submit();
		}
	})
	
})
//设置密保问题
$(function(){
    //jquery.validate
           
	$("#FormSaveMiBaoProblem").validate({
		submitHandler: function(form) {
			//验证通过后 的js代码写在这里
			//alert(1);
			form.submit();
			return validataForm("mibao_xiugx");
		}
	})
	
})
//重置密码页面返回展开页面
  $(function(){
      //jquery.validate
      var error1 = $('#error1').text() ;
      var info=<%=request.getAttribute("info") %>;
      if(error1!=null && "" !=error1){
    		  $('#resetPassword1').css("display","block");
    	
      	
      }
  })



//配置错误提示的节点，默认为label，这里配置成 span （errorElement:'span'）
$.validator.setDefaults({
	errorElement:'em'
});

//配置通用的默认提示语
$.extend($.validator.messages, {
	required: '此项为必填项',
    equalTo: "请再次输入相同的值"
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
}, "密码必须由数字和字母组成");
//答案验证规则
jQuery.validator.addMethod("answer", function (value, element) {
    var answer = /^[\u4E00-\u9FA5A-Za-z0-9_]+$/;    
	return this.optional(element) || (answer.test(value));
}, "不能包含特殊字符，只能由字母数字汉字组成");

//答案验证规则
jQuery.validator.addMethod("answerSame", function (value, element) {
	if($("#answer1").val() ==$("#answer2").val() ){
		return false;
	}else{
		return true;
	}
}, "两次答案不能相同");

//答案验证规则
jQuery.validator.addMethod("questionSame", function (value, element) {
	if($("[name=questionOne]").val() ==$("[name=questionTwo]").val() ){
		return false;
	}else{
		return true;
	}
}, "两次问题不能相同");
</script>
</html>
