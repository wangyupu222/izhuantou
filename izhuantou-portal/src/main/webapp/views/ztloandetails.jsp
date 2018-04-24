<%@ page contentType="text/html; charset=UTF-8" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>砖头网-我要借款</title>
<link rel="stylesheet" type="text/css" href="/css/style-common.css">
<link rel="stylesheet" type="text/css" href="/css/index.css">
<link rel="stylesheet" type="text/css" href="/css/loan.css">
<Link rel="shortcut icon" href="/images/zhuan.ico" />
<script type="text/javascript" src="/js/jquery-1.10.1.min.js"></script>
<script src="/js/com_cj.js"></script>
<script src="/js/resubmit.js"></script>

<style>


.fn-vhid{ visibility:hidden;}
/*.fn-tinput{ width:240px; height:30px; line-height:30px\9; border:#ddd solid 1px; background-color:transparent; padding:0 6px;}
textarea {border:#ddd solid 1px; background-color:transparent; width:60%}
.fn-tinput:focus,textarea:focus{ border-color:#c00;}
.bl-form label{ float:left; width:100px; text-align:right; padding-top:6px;}
.bl-form .controls label{ width:auto;}
.bl-form li{ margin-top:20px; overflow:hidden;}
em{ color:#C00;}*/
/*.dfinput:focus,textarea:focus{ border:1px solid #c00;}*/
.submit_btn{background-color: #4fa6fd;
    width: 282px;
    border-radius: 5px;
    height: 40px;
    border: 0px;
    font: bold 18px/40px "微软雅黑";
    color: #FFF;
    text-align: center;
    margin-left: 80px;
    cursor: pointer;}
.dfinput.yue span.erro{ float:right; display:block;}
span.error{ float:left; color:#C00; padding-left:15px; line-height:40px; display:block; }
span.error[for="s_county"]{float: right;margin-right: 196px;}
.debitfill_new{ border: none;}
.debitfill_new ul{width: 615px;overflow: hidden;margin-left: 239px;}
.debitfill_new ul li{
    width: 582px;
    overflow: hidden;
    margin-top: 20px;
    position: relative;
    text-align:left;
}
.debitfill ul li .hqyzm_new.loanhqyzm.send1{
    background: #b1aeae;
    cursor: no-drop;
}
.submit_btn_new{
    background-color: #4fa6fd;
    width: 282px;
    border-radius: 5px;
    height: 40px;
    border: 0px;
    font: bold 18px/40px "微软雅黑";
    color: #FFF;
    text-align: center;
    margin-left: 80px;
    cursor: pointer;
}
.jdpcons {
    margin-top: 0px;
}
</style>
<script>
function huoquyzm(){	
	var phoneNum = document.getElementById('phone').value;	
	var yzm= document.getElementById('Yzm').value;
	var smsValidateCode="smsLoanApplication";
	var oSign_name = $("#name");
	var oSign_card = $("#card");
	var oSign_val=$("#sign_val");
	if(oSign_name!=""){
		if (/^[\u4E00-\u9FFF]+$/.test(oSign_name.val())== true) {
			if(oSign_card!=""){
	if ((/^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$/.test(oSign_card.val())!=false) || (/^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/.test(oSign_card.val())!=false)) {
	  if (phoneNum!=""){
		  if (/^1(3|4|5|7|8)\d{9}$/.test(phoneNum) == true) { 
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
		        	console.info(result);
		            sends.send();
		            $('.yzmsz').attr("src","/portal/user/checkcode?x="+Math.random());
		        },
		        error:function(result){
		        	oSign_val[0].innerHTML ="系统异常！";
		        }
		    });
			
		}else{
			oSign_val[0].innerHTML ="请输入验证码！";
			$('.yzmsz').attr("src","/portal/user/checkcode?x="+Math.random());
		}
		  }else{
				oSign_val[0].innerHTML ="手机号码格式不正确！";
				$('.yzmsz').attr("src","/portal/user/checkcode?x="+Math.random());
			}
	}else{
		oSign_val[0].innerHTML ="请输入手机号！";
		$('.yzmsz').attr("src","/portal/user/checkcode?x="+Math.random());
	}
	}else{
		oSign_val[0].innerHTML ="身份证号码不正确！";
		$('.yzmsz').attr("src","/portal/user/checkcode?x="+Math.random());
	}
			}else{
				oSign_val[0].innerHTML ="";
				$('.yzmsz').attr("src","/portal/user/checkcode?x="+Math.random());
			}
		}else{
			oSign_val[0].innerHTML ="姓名格式不正确！";
			$('.yzmsz').attr("src","/portal/user/checkcode?x="+Math.random());
		}
	}else{
		oSign_val[0].innerHTML ="";
		$('.yzmsz').attr("src","/portal/user/checkcode?x="+Math.random());
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
							$('a.hqyzm_new').removeClass('send1').html("获取验证码");
							$("a.hqyzm_new").attr("onclick","huoquyzm();");
							//$('a.hqyzm_new').removeAttr('onclick');
							sends.checked = 1;
							return true;
						}
						$('a.hqyzm_new').html(time+"s后再发送");
						$('a.hqyzm_new').removeAttr('onclick');
						time--;
						return false;
						sends.checked = 0;
					}
					$('a.hqyzm_new').addClass('send1');
					
					timeCountDown();
					var timer = setInterval(timeCountDown,1000);
				}
		}
	}
</script>
</head>
<body>
<% try{ %>
<%@ include file="header.jsp" %> 
<!--借款模块 i薪贷 i抵押 i保单 i房贷 i意贷-->
<div class="main jdpart">
	<div class="con ">
    	<div class="hgcon">
             <div class="smallnav">
                <!-- <a href="#">我要借款</a><span>:</span><a href="#">借款详情</a> -->
                <%-- <%if(request.getParameter("OID").equals("b00109ab3fa805780084c164a4175dfe")){%>
            	 <a >当前位置</a><span>:</span><a >我要借款</a><span>&gt;</span><a >i意贷</a>
            	<%}else if(request.getParameter("OID").equals("afffe0d13fa805780045aefe2d2dea06")){%>
            	 <a >当前位置</a><span>:</span><a >我要借款</a><span>&gt;</span><a >i薪贷</a>
            	<%}else if(request.getParameter("OID").equals("b000c0093fa8057801cdd66986dc70b7")){%>
            	<a >当前位置</a><span>:</span><a >我要借款</a><span>&gt;</span><a >i房贷</a>
            	<%}else if(request.getParameter("OID").equals("b000e1223fa805780174f34f25da921c")){%>
            	<a >当前位置</a><span>:</span><a >我要借款</a><span>&gt;</span><a >i保单</a>
            	<%}else if(request.getParameter("OID").equals("b00049513fa80578002f968bc2febe97")){%>
            	<a >当前位置</a><span>:</span><a >我要借款</a><span>&gt;</span><a >i抵押</a>
            	<%}%> --%>
            </div>	
        </div>
        <web:ContentLeaf leafName="ixindai" name="content" ></web:ContentLeaf>
        <div class="jdtitcon">
        	<div class="leftdai">
            	<div class="jdbt"><span></span> <web:WidgetTextOut name="content" property="title" /></div>
           		 <p class="p-titdai"><web:WidgetTextOut name="content" property="miaoshu" /></p>
				<dl class="dai-dl">
                	<dd><label>申请人:</label><div class="jobject">
                    <web:WidgetTextOut name="content" property="jobject" />
                    </div></dd>
                   
                    <dd><label>借款期限:</label><div class="term"></div></dd>
                	<dd><label>申请额度:</label><div class="quota"></div></dd>
                    
                </dl>
            </div>
            <div class="rightdai">
            
            	<%if(request.getParameter("OID").equals("b00109ab3fa805780084c164a4175dfe")){%>
            	<img src="/images/iyd.png" width="430px" >
            	<%}else if(request.getParameter("OID").equals("afffe0d13fa805780045aefe2d2dea06")){%>
            	<img src="/images/ixd.png" width="430px" >
            	<%}else if(request.getParameter("OID").equals("b000c0093fa8057801cdd66986dc70b7")){%>
            	<img src="/images/ifd.png" width="430px" >
            	<%}else if(request.getParameter("OID").equals("b000e1223fa805780174f34f25da921c")){%>
            	<img src="/images/ibd.png" width="430px" >
            	<%}else if(request.getParameter("OID").equals("b00049513fa80578002f968bc2febe97")){%>
            	<img src="/images/idy.png" width="430px">
            	<%}else if(request.getParameter("OID").equals("8f45539c0a5dca713ea557825ee495e0")){%>
            	<img src="/images/loanMain_New/icd.png" width="430px">
            	<%}%>
            	
            	
            </div>
        </div>
        
       <div class="jdtitcon01">
        	<div class="jdbt jdbt01"><span></span>轻松4步，简单借款</div> 
            <div class="debitend">
                <div class="tude">
                    <div class="df01"><img src="/images/wenj01.png"></div>
                    <div class="df01"><img src="/images/wenj02.png"></div>
                    <div class="df01"><img src="/images/wenj03.png"></div>
                    <div class="df01"><img src="/images/wenj04.png"></div>
                    <div style="clear:both"></div>
                    
                </div>
            </div>
       </div>
        <form action="/portal/detial/FormMyLend" method="post" name="FormApply" id="FormApply" >
        <%
         Object OID=request.getParameter("OID");
         if(OID==null)
        	 OID=request.getAttribute("OID");
        
        %>
        <input type="hidden" value="<%=OID %>" name="OID" />
       <div class="jdtitcon01"> 
        <p class="sxtj"><span>填写借款申请</span></p>
       <div class="op"> 
       
        <div class="debitfill">
        	<ul>
            	 <li><label>产品类型</label><input type="text" name="purpose" id="purpose" class='dfinput' readonly required /></li> 
            	<%-- <li><label>产品类型</label><web:WidgetText   name="FormApply" property="purpose" customProperty="class='dfinput' value='<%if(request.getAttribute("OID").equals("afffe0d13fa805780045aefe2d2dea06")){%>i薪贷<%}%>' required " /></li>
            	  --%><li><label>申请人</label><input type="text" name="name" id="name" class='dfinput' required data-msg-required='不能为空' data-rule-chinese='true' /></li>
            	<li><label>身份证号</label><input type="text" name="card" id="card" class='dfinput' required data-msg-required='不能为空' data-rule-card='true'  data-msg-card='请输入正确格式' /></li>
            	<li><label>手机号码</label><input type="text" name="phone" id="phone" class='dfinput' required data-rule-mobile='true' data-msg-required='请输入手机号' data-msg-mobile='请输入正确格式'/></li>
            	<li><label>图形验证</label><input type="text" name="Yzm" id="Yzm" class='dfinput dfinput2' required data-msg-required='请输入短信验证码' maxlength='6'/><img class="yzmsz tu_yzm" width="100" height="41" src="/portal/user/checkcode"  class="m" alt="如果看不清,请点击我!" title="如果看不清,请点击我!"  onclick="this.setAttribute('src','/portal/user/checkcode?x='+Math.random());"></li>
            	<li><label>短信验证</label><input type="text" name="mesYzm" id="mesYzm" class='dfinput' required data-msg-required='请输入短信验证码' maxlength='6'/><a href="javascript:;" class="hqyzm_new loanhqyzm" onclick="huoquyzm()">获取短信验证码</a></li>
            	<li><label>借款金额</label><input type="text" name="money" id="money"  class='dfinput yue' required data-msg-required='不能为空' data-rule-gt='true' data-gt='0' data-rule-lt='true' data-lt='1000000' data-rule-pinteger='true'  maxlength='7'/></li>
                <li><label>借款期限</label>
                <select class="dfinput dfinput_qx" required id="loanTerm" name="loanTerm"> <option value="">---选择期限---</option> <option value="3">3个月</option> <option value="6">6个月</option> <option value="9">9个月</option> <option value="12">12个月</option> <option value="15">15个月</option> <option value="18">18个月</option> <option value="24">24个月</option> <option value="36">36个月</option></select>
                </li>
                <li><label>所在城市</label>
               <%--  <web:WidgetSelectOne  name="FormApply" property="city" customProperty="class='dfinput' required" /> --%>
                <select id="s_province" name="s_province" class='dfinput select01' ></select>  
			  	<select id="s_city" name="s_city" class='dfinput select02'></select>  
			  	<select id="s_county" name="s_county" class='dfinput select02' required></select>
                </li> 
                <li style="line-height:40px;"><label></label><div id="sign_val" style="color:#f00;">${msg}</div></li>
                <li>
                <button id="" name="" type="submit" value="提交" class="debit-but">提交</button>
                </li>
            </ul>
        </div>
        </div>
     </div>
    </form>
    
    </div>
</div>


<!--注册领红包-->

<!-----------------中间部分结束----------------> 
<%@ include file="footer.jsp" %>
<%}catch(Exception e){
	response.sendRedirect("error.jsp");
    return;  
} %>
</body>
<!-- 全国城市三级联动 -->
<script  src="/js/area.js" type="text/javascript"></script>
<script type="text/javascript">_init_area();</script>

<!--验证表单-->
<script src="/js/jquery.validate.js"></script>
<script>
$(function(){
    //jquery.validate
	$("#FormApply").validate({
		submitHandler: function(form) {
			//验证通过后 的js代码写在这里
			validataForm("submit_btn");
			form.submit();
			
		}
	})
	
	var Request = new Object();
	Request = GetRequest2();
	var Oid_num=Request["OID"];
	var oMoney=$("#money").val();
	if(Oid_num=='b00109ab3fa805780084c164a4175dfe'){
		$("#purpose").val("i意贷");
		$("#money").keyup(function(){
			if($("#money").val()>1000000){
				$("#money").val("1000000");	
			}
		})	
		
	}else if(Oid_num=='afffe0d13fa805780045aefe2d2dea06'){
		$("#purpose").val("i薪贷");
		$("#money").keyup(function(){
			if($("#money").val()>120000){
				$("#money").val("120000");
			}
		})	
	}else if(Oid_num=='b000c0093fa8057801cdd66986dc70b7'){
		$("#purpose").val("i房贷");
		$("#money").keyup(function(){
			if($("#money").val()>200000){
				$("#money").val("200000");
			}
		})
		
	}else if(Oid_num=='b000e1223fa805780174f34f25da921c'){
		$("#purpose").val("i保单");
		$("#money").keyup(function(){
			if($("#money").val()>30000){
				$("#money").val("30000");
			}
		})	
	}else if(Oid_num=='b00049513fa80578002f968bc2febe97'){
		$("#money").keyup(function(){
			if($("#money").val()>1000000){
				$("#money").val("1000000");
			}
		})
		$("#purpose").val("i房抵");
	}else if(Oid_num=='8f45539c0a5dca713ea557825ee495e0'){
		$("#purpose").val("i车抵");
	}
	
	$.ajax({
        type: "post",
        url: "/portal/detial/pageLoan?OID="+Oid_num,
        dataType: "json",
        success: function(result){
        	console.log(result);
        	$(".jdbt").html("<span></span>"+result.title);
        	$(".p-titdai").text(result.miaoshu);
        	$(".jobject").html(result.jobject);
        	$(".term").text(result.term);
        	$(".quota").text(result.quota);
        	       		        	
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
    var mobile = /^1[3|4|5|7|8]\d{9}$/;
	return this.optional(element) || (mobile.test(value));
}, "手机格式不对");

//邮箱或手机验证规则  
jQuery.validator.addMethod("mm", function (value, element) {
    var mm = /^[a-z0-9._%-]+@([a-z0-9-]+\.)+[a-z]{2,4}$|^1[3|4|5|7|8]\d{9}$/;
	return this.optional(element) || (mm.test(value));
}, "格式不对");

//电话或手机验证规则  
jQuery.validator.addMethod("tm", function (value, element) {
    var tm=/(^1[3|4|5|7|8]\d{9}$)|(^\d{3,4}-\d{7,8}$)|(^\d{7,8}$)|(^\d{3,4}-\d{7,8}-\d{1,4}$)|(^\d{7,8}-\d{1,4}$)/;
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
},"请输入小于1000000的正确数字");
//只能输入正整数
jQuery.validator.addMethod("pinteger", function (value, element) {
    var pinteger = /^[0-9]*[1-9][0-9]*$/;
    return this.optional(element) || (pinteger.test(value));
}, "不允许输入小数 ");

//汉字
jQuery.validator.addMethod("chinese", function (value, element) {
    var chinese = /^[\u4E00-\u9FFF]+$/;
    return this.optional(element) || (chinese.test(value));
}, "只能输入中文");

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
