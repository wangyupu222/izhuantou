<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/include/url.jsp" %> 
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>砖头网</title>
<link rel="stylesheet" type="text/css" href="/css/style-common.css">
<link rel="stylesheet" type="text/css" href="/css/personalcenter.css">
<%-- <link rel="stylesheet" type="text/css" href="/css/preother.css"> --%>
<link rel="stylesheet" type="text/css" href="/css/Loaniframe.css">

<Link rel="shortcut icon" href="<%=pathUrl %>/images/zhuan.ico" />
<!--<link rel="stylesheet" href="/css/jquery.onoff.css" media="screen" />-->
<script type="text/javascript" src="/js/jquery-1.10.1.min.js"></script>
<!--<script src="/js/jquery.onoff.min.js"></script>-->
<script src="/js/com.js"></script>
<script language="javascript" src="/js/menu.js"></script>
<script src="/js/resubmit.js"></script>
<style>
.dfinput.yue span.erro{ float:right; display:block;}
span.error{ float:left; color:#C00; padding-left:10px; display:block;  font:12px/30px "Microsoft yahei"; }
.tu_yzm{position: absolute;
    top: 0;
    left: 292px;
    height: 32px;}
</style>
</head>
<script>
function huoquyzm(){	
	var phoneNum = document.getElementById('phone').value;	
	var yzm= document.getElementById('Yzm').value;
	var smsValidateCode="smsLoanApplication";
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
		        url:"/portal/user/checksms",
		        data:{
		        	msmType:smsValidateCode,
		        	name:phoneNum,
		        	yzm:yzm
		        }, 
		        success: function(result){
		        	if(result.status==2){
		            	oSign_val[0].innerHTML =result.message;
		            }else{
			            sends.send();
		            }
		            $('.yzmsz2').attr("src","/portal/user/checkcode?x="+Math.random());
		        },
		        error:function(result){
		        	oSign_val[0].innerHTML ="系统异常！";
		        }
		    });
		}else{
			oSign_val[0].innerHTML ="请输入验证码！";
			$('.yzmsz2').attr("src","/portal/user/checkcode?x="+Math.random());
		}
	}else{
		oSign_val[0].innerHTML ="请输入手机号！";
		$('.yzmsz2').attr("src","/portal/user/checkcode?x="+Math.random());
	}

}
//倒计时
var sends = {
		checked:1,
		send:function(){
				var numbers = /^1\d{10}$/;
				var val = $('#phone').val().replace(/\s+/g,""); //获取输入手机号码
				
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
<body style="width:100%;min-width:100%; background:#fff;">
<% try{ %>
<div class="yunull">
          	 <div class="debitfill tabopa">
        	<ul>
        	       	 
        	 <form action="/portal/loan/updloanAudit" method="post" name="FormApply" id="FormApply" target='_parent' >
        	 <input type="hidden" name="loanOID" value="<%=request.getParameter("loanOID") %>" />
        	 <%-- <%=request.getParameter("loanOID") %> --%>
            	<li><label>申请人</label><input type="text" name="name" id="name" value="" class="dfinput" readonly required data-msg-required="请输入申请人"></li>
                <li><label>身份证号</label><input type="text" name="card" id="card" value="" class="dfinput" readonly required data-rule-card="true" data-msg-required="请输入身份证号" data-msg-card="请输入正确格式"></li>
            	<li><label>手机号码</label><input type="text" name="phone" id="phone" value="" class="dfinput" readonly required data-rule-mobile="true" data-msg-required="请输入手机号" data-msg-mobile="请输入正确格式"></li>
            	<li><label>图形验证</label><input type="text" name="Yzm" id="Yzm" class="dfinput " required data-msg-required="请输入图形验证码" maxlength="6"><img class="yzmsz2 tu_yzm" width="100" height="41" src="/portal/user/checkcode"  class="m" alt="如果看不清,请点击我!" title="如果看不清,请点击我!"  onclick="this.setAttribute('src','/portal/user/checkcode?x='+Math.random());"></li>
            	<li><label>短信验证</label><input type="text" name="mesYzm" id="mesYzm" class="dfinput" required data-msg-required="请输入短信验证码" maxlength="6"><a href="#" class="hqyzm loanhqyzm" onclick="huoquyzm()">获取短信验证码</a></li>
            	<li><label>借款金额</label><input type="text" name="money" id="money" value="" class="dfinput yue" required="" data-msg-required="请输入借贷金额" data-rule-gt="true" data-gt="0" data-rule-lt="true" data-lt="1500000" data-rule-pinteger="true" maxlength="10"></li>
                <li><label>借款期限</label><select class="dfinput" required="" data-msg-required="请选择借款期限" id="loanTerm" name="loanTerm"> <option value="">---选择期限---</option> <option value="3">3个月</option> <option value="6">6个月</option> <option value="9">9个月</option> <option value="12">12个月</option> <option value="15">15个月</option> <option value="18">18个月</option> <option value="24">24个月</option> <option value="36">36个月</option></select></li>
                <li><label>产品类型</label><select class="dfinput" required="" data-msg-required="请选择产品类型" id="proType" name="loanYT"> <option value="">---选择菜单---</option> <option value="IXD">i薪贷</option> <option value="IBD">i保单</option> <option value="IYD">i意贷</option> <option value="IFD">i房贷</option> <option value="i房抵">i房抵</option> <option value="i车抵">i车抵</option></select></li>
                <li><label>所在城市</label>
                <select id="s_province" name="s_province" class='dfinput select01'  ></select>  
			  	<select id="s_city" name="s_city" class='dfinput select02'></select>  
			  	<select id="s_county" name="s_county" class='dfinput select02' required data-msg-required='请选择所在城市'></select>
                
                </li>
                
            <!--<li><label>备注</label><textarea type="text" id="textarea" class="dfinput dfinput-text" ></textarea></li>-->
              <!--	<li><label>验证码</label><input type="text" class="dfinput yz"/><div class="yzimg"><img src="<%=pathUrl %>/images/jdyz.jpg"  /></div><a class="ya" href="#">换一张</a></li>-->
               <li class="single_ctrl"><input type="checkbox" required="" data-msg-required="请勾选协议" name="isAgree" id="isAgree" value="1"><label for="isAgree" class="isAgree-style">我已阅读并同意</label><a href="agreement_z.jsp?contractType=9" target="_blank" >《借款咨询服务协议》 </a><a href="/agreement_z.jsp?contractType=6&biddingType=qt" target="_blank" >《砖头网借款协议》 </a><a href="agreement_z.jsp?contractType=8&biddingType=qt" target="_blank" >《砖头网债权预回购协议》</a></li>
               <div align="left" style="margin-left:80px;color:#f00;" id="sign_val">${msg}</div>
               <%-- <div align="left" style="color:#f00;margin-left:80px;" id="sign_val" ><hoontag:Message name="LoanAppl registerErro"/></div> --%>
               <li><button id="" name="" type="submit" value="立即提交" class="debit-but">立即提交</button></li>
               </form>
            </ul>
        </div>
           <%-- <web:WidgetButton value="确认" customProperty="class='inpsub '" property=""  /> --%>
        </div> 
 
<%}catch(Exception e){
	response.sendRedirect("error.jsp");
    return;  
} %>
</body>
<!-- 全国城市三级联动 -->
<script  src="/js/area.js" type="text/javascript"></script>
<script type="text/javascript">_init_area();</script>
<script>
                var province=$("#s_province").find('option:selected')
                province.text("北京市");
                //alert(province.text());
                //$("#s_province").val("北京市");
                //$("#s_province").append("<option  value='111'>111</option>");
                //$("#s_province").find("option[text='北京市']").attr("selected",true);
                </script>
<!--验证表单-->
<script src="/js/jquery.validate.js"></script>
<script>
$(function(){
    //jquery.validate
	$("#FormApply").validate({
		submitHandler: function(form) {
			//验证通过后 的js代码写在这里
			form.submit();
			return validataForm("debit-but");
		}
	})
	var Request = new Object();
	Request = GetRequest2();
	var loanOID=Request["loanOID"];
	$.ajax({
        type: "post",
        url: "/portal/loan/findoidInfo?OID="+loanOID,
        dataType: "json",
        success: function(result){
        	console.log(result);
        	if(result.dataValue!=null){
        	$("#name").val(result.dataValue.userName);
        	$("#card").val(result.dataValue.idCard);
        	$("#phone").val(result.dataValue.mobile);
        	$("#money").val(result.dataValue.loanAmount);
        	}
        },
		error:function(result){    	
    	}
    });
		
})

function GetRequest2() {
	  
	  var url = location.search; //获取url中"?"符后的字串
	   var theRequest = new Object();
	   if (url.indexOf("?") != -1) {
	      var str = url.substr(1);
	      strs = str.split("&");
	      //alert(strs);
	      for(var i = 0; i < strs.length; i ++) {
	         theRequest[strs[i].split("=")[0]]=(strs[i].split("=")[1]);
	      }
	   }
	   return theRequest;
	}


//配置错误提示的节点，默认为label，这里配置成 span （errorElement:'span'）
$.validator.setDefaults({
	errorElement:'span'
});

//配置通用的默认提示语
$.extend($.validator.messages, {
	required: '必填',
    equalTo: "请再次输入相同的值"
});


//邮箱 
jQuery.validator.addMethod("mail", function (value, element) {
	var mail = /^[a-z0-9._%-]+@([a-z0-9-]+\.)+[a-z]{2,4}$/;
	return this.optional(element) || (mail.test(value));
}, "邮箱格式不对");

//电话验证规则
jQuery.validator.addMethod("phone", function (value, element) {
    var phone = /^0\d{2,3}-\d{7,8}$/;
    return this.optional(element) || (phone.test(value));
}, "电话格式如：0371-68787027");

//区号验证规则  
jQuery.validator.addMethod("ac", function (value, element) {
    var ac = /^0\d{2,3}$/;
    return this.optional(element) || (ac.test(value));
}, "区号如：010或0371");

//无区号电话验证规则  
jQuery.validator.addMethod("noactel", function (value, element) {
    var noactel = /^\d{7,8}$/;
    return this.optional(element) || (noactel.test(value));
}, "电话格式如：68787027");

//手机验证规则  
jQuery.validator.addMethod("mobile", function (value, element) {
    var mobile = /^1[3|4|5|6|7|8|9]\d{9}$/;
	return this.optional(element) || (mobile.test(value));
}, "手机格式不对");

//邮箱或手机验证规则  
jQuery.validator.addMethod("mm", function (value, element) {
    var mm = /^[a-z0-9._%-]+@([a-z0-9-]+\.)+[a-z]{2,4}$|^1[3|4|5|6|7|8|9]\d{9}$/;
	return this.optional(element) || (mm.test(value));
}, "格式不对");

//电话或手机验证规则  
jQuery.validator.addMethod("tm", function (value, element) {
    var tm=/(^1[3|4|5|6|7|8|9]\d{9}$)|(^\d{3,4}-\d{7,8}$)|(^\d{7,8}$)|(^\d{3,4}-\d{7,8}-\d{1,4}$)|(^\d{7,8}-\d{1,4}$)/;
    return this.optional(element) || (tm.test(value));
}, "格式不对");

//年龄
jQuery.validator.addMethod("age", function(value, element) {   
	var age = /^(?:[1-9][0-9]?|1[01][0-9]|120)$/;
	return this.optional(element) || (age.test(value));
}, "不能超过120岁"); 
///// 20-60   /^([2-5]\d)|60$/

//传真
jQuery.validator.addMethod("fax",function(value,element){
    var fax = /^(\d{3,4})?[-]?\d{7,8}$/;
    return this.optional(element) || (fax.test(value));
},"传真格式如：0371-68787027");

//验证当前值和目标val的值相等 相等返回为 false
jQuery.validator.addMethod("equalTo2",function(value, element){
    var returnVal = true;
    var id = $(element).attr("data-rule-equalto2");
    var targetVal = $(id).val();
    if(value === targetVal){
        returnVal = false;
    }
    return returnVal;
},"不能和原始密码相同");

//大于指定数
jQuery.validator.addMethod("gt",function(value, element){
    var returnVal = false;
    var gt = $(element).data("gt");
    if(value > gt && value != ""){
        returnVal = true;
    }
    return returnVal;
},"请输入大于0的正确数字");
//小于指定数
jQuery.validator.addMethod("lt",function(value, element){
    var returnVal = false;
    var lt = $(element).data("lt");
    if(value <= lt && value != ""){
        returnVal = true;
    }
    return returnVal;
},"请输入小于1500000的正确数字");

//汉字
jQuery.validator.addMethod("chinese", function (value, element) {
    var chinese = /^[\u4E00-\u9FFF]+$/;
    return this.optional(element) || (chinese.test(value));
}, "格式不对");
//只能输入正整数
jQuery.validator.addMethod("pinteger", function (value, element) {
    var pinteger = /^[0-9]*[1-9][0-9]*$/;
    return this.optional(element) || (pinteger.test(value));
}, "不允许输入小数 ");

//指定数字的整数倍
jQuery.validator.addMethod("times", function (value, element) {
    var returnVal = true;
    var base=$(element).attr('data-rule-times');
    if(value%base!=0){
        returnVal=false;
    }
    return returnVal;
}, "必须是发布赏金的整数倍");

//身份证
jQuery.validator.addMethod("card", function (value, element) {
    var isIDCard1=/^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$/;//(15位)
    var isIDCard2=/^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/;//(18位)

    return this.optional(element) || (isIDCard1.test(value)) || (isIDCard2.test(value));
}, "格式不对");







</script>
</html>
