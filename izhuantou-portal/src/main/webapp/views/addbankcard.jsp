<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/include/url.jsp" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>砖头网-开通托管账户</title>
<link rel="stylesheet" type="text/css" href="/css/style-common.css">
<link rel="stylesheet" type="text/css" href="/css/personalcenter.css">
<link rel="stylesheet" type="text/css" href="/css/preother.css">
<Link rel="shortcut icon" href="<%=pathUrl %>/images/zhuan.ico" />
<script type="text/javascript" src="/js/jquery-1.10.1.min.js"></script>

<script src="/js/com.js"></script>
<script language="javascript" src="/js/menu.js"></script>
<script> 

function huoquyzm(){	
	var phoneNum = document.getElementById('phone').value;	
	var yzm= document.getElementById('tuxing_Yzm').value;
	var smsValidateCode="smsAddCard";
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
					oSign_val[0].innerHTML ="请输入手机号！";
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
							$('a.hqyzm').removeClass('send1').html("获取短信验证码");
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
$(function(){
	var oUserPhone=$("#uesr_phone").val();
	if(oUserPhone=="null"){
		$("#phone").val("");
	}else{
		$("#phone").val(oUserPhone);
	}	
	
})
</script>

<script>


$(function(){
	$('.cd-popup-triggerjk').click();
	var url=location.href;
	//alert("url"+url)
	var lastStr=url.substring(url.length-1);
	//alert(lastStr);
	if(lastStr==2){
		$(".tiyan_new").removeClass("hide");
	}
})

</script>
<style type="text/css">
.box-yhk .inpu03 {
    width: 225px;
}
.card_tishi a{color:#55b0fd;margin-left: 10px;}
.card_tishi a:hover{text-decoration:underline;}
.IDyinhangDataList li {
display: none;
}
.h3Sty01 {
font-size: 14px;
line-height: 20px;
margin: 27px 48px 14px;
color: #000;
}
.StyTable03 {
font-size: 14px;
text-align: center;
}
.wB100 {
width: 60%;
margin-left: 48px;
}
table {
border-collapse: collapse;
border-spacing: 0;
}
.StyTable03 {
font-size: 14px;
text-align: center;
}
table {
border-collapse: collapse;
border-spacing: 0;
}
.StyTable03 th {
background-color: #F66E58;
color: #fff;
line-height: 30px;
}
.StyTable03 td {
border: 1px solid #E7E7E7;
padding: 8px;
line-height: 24px;
color: #000;
}
.limit_text{
 margin-left:35px;
 color:#666;
 font-weight: normal;
}
.cd-popup-containerjk, .cd-popup-containercar {
    width: 439px;
   
}
.cd-popup-containercar .commit-1, .cd-popup-containerjk .commit-1 {
    height: 45px;
}
</style>
</head>
<body>
<% try{ %>
<a class="cd-popup-triggerjk hide"></a>
<% if("2".equals(request.getAttribute("type"))) {%>
<div class="cd-popupcar">
<div class="cd-popup-containercar">
	<div class="comnei comneione">
        <a class="close cd-popup-close"></a>
        <p class="commit-1" ><span></span></p>
        
        <div class="boxconsq">
        	<div class="titlesq01">开通富友账户，即可立即使用18888体验金。</div>
        	<div class="consqbut01">
        		<a class="active cd-popup-close" href="javascript:void(0);" >立即开通</a>
        		
        	</div>
        	
        </div>
    </div>
   </div>
</div>
<%} %>

<div class="tiyan_new cd-popupcar is-visible1 hide">
<div class="cd-popup-containercar">
	<div class="comnei comneione">
        <a class="close cd-popup-close"></a>
        <p class="commit-1"><span></span></p>
        
        <div class="boxconsq">
        	<div class="titlesq01">开通富友账户，即可立即使用18888体验金。</div>
        	<div class="consqbut01">
        		<a class="active cd-popup-close" href="javascript:void(0);" >立即开通</a>
        		
        	</div>
        	
        </div>
    </div>
   </div>
</div>

<!--common header-->
<%@ include file="header.jsp" %>
<!--common end-->
<div class="main minhh">
	<div class="con personcon">
    	<div class="smallnav">
    	<a >当前位置</a><span>:</span><a >个人中心</a><span>&gt;</span><a >我的资金</a><span>&gt;</span><a >银行卡</a>
        </div>
        <input id="uesr_phone" type="hidden" value='<%=session.getAttribute("userMobile") %>' />
        <div class="con xqdetial">
    	<%@ include file="left_percenter.jsp" %>
        
    	<div class="xqdeleft">
        	<!-- <div class="hbtabcar">
            	<a class="active">我的银行卡</a>	
            </div> -->
            <div class="my_loantop"><a class="active">托管账户开户</a><span class="L1">&nbsp;</span></div>
            <div class="clear"></div>
            <div class="hbcon">
            <form action="/portal/cash/insertCustomer"  method="post" name="FormRegisterUCA" id="FormRegisterUCA" >
			<div class="cardtit" ><div class="titp">身份认证</div></div>   
            <div class="tishis">为了您的账户安全，请填写真实身份信息，认证成功后身份信息将无法修改。</div>
            <div class="cardcon">
            	<dl class="yhkxx" >
                    	<dd>
                        	<div class="box-yhk">
                            	<label>真实姓名：</label>
                            	<input type="text" name="cardName" id="cardName" placeholder="请输入真实姓名" class="inpu inpu01" required data-msg-required="姓名不能为空" data-rule-idname="true" data-msg-idname="请输入真实姓名">                               
                            	<div class="clear"></div>
                            </div>
                        </dd>
                        <dd>
                        	<div class="box-yhk">
                            	<label>身份证号码：</label>
                            	<input type="text" name="idCard" id="idCard" placeholder="请输入身份证号码" class="inpu inpu01" required data-msg-required="身份证号码不能为空" data-rule-card="true" data-msg-card="请输入正确格式">                                
                            	<div class="clear"></div>
                            </div>
                        </dd>
                </dl>
            </div>   
            
            <div class="cardtit" ><div class="titp">银行卡绑定</div></div>   
            <div class="tishis">用于充值和提现，请您确认银行卡和开户名和您的身份一致。为了方便您使用快捷充值，请使用与注册手机号码绑定的银行卡进行开户。</div>	
            	
             <div class="cardcon">
            	<dl class="yhkxx" >
            			<dd>
            			<ul tag-func-id="4" cards="li" class="IDyinhangDataList">
            		
                 	<div class="cardyong">
               <!--     工商 -->
                     <li >
											<h3 class="h3Sty01">快捷充值限额<span class="limit_text">*因富友限额不定期更新，请以富友公示为准</span></h3>
											<table class="StyTable03 wB100">
												<tr>
													<th>卡种</th>
													<th>单笔限额(元)</th>
													<th>每日限额(元)</th>
													<th>每月限额(元)</th>
												</tr>
												<tr>
													<td>借记卡</td>
													<td>5万</td>
													<td>5万</td>
													<td class="tAleft">100万</td>
												</tr>
												
											</table>
										</li>
                    
                   <!-- 农业 -->
                     <li >
											<h3 class="h3Sty01">快捷充值限额<span class="limit_text">*因富友限额不定期更新，请以富友公示为准</span></h3>
											<table class="StyTable03 wB100">
												<tr>
													<th>卡种</th>
													<th>单笔限额(元)</th>
													<th>每日限额(元)</th>
													<th>每月限额(元)</th>
												</tr>
												<tr>
													<td>借记卡</td>
													<td>2万</td>
													<td>10万</td>
													<td class="tAleft">100万</td>
												</tr>
											</table>
										</li>	
                  <!-- 中国银行 -->
                     <li >
											<h3 class="h3Sty01">快捷充值限额<span class="limit_text">*因富友限额不定期更新，请以富友公示为准</span></h3>
											<table class="StyTable03 wB100">
												<tr>
													<th>卡种</th>
													<th>单笔限额(元)</th>
													<th>每日限额(元)</th>
													<th>每月限额(元)</th>
												</tr>
												<tr>
													<td>借记卡</td>
													<td>5万</td>
													<td>10万</td>
													<td class="tAleft">100万</td>
												</tr>
												
											</table>
										</li>
					<!-- 建设银行 -->                    
                    <li >
											<h3 class="h3Sty01">快捷充值限额<span class="limit_text">*因富友限额不定期更新，请以富友公示为准</span></h3>
											<table class="StyTable03 wB100">
												<tr>
													<th>卡种</th>
													<th>单笔限额(元)</th>
													<th>每日限额(元)</th>
													<th>每月限额(元)</th>
												</tr>
												<tr>
													<td>借记卡</td>
													<td>5万</td>
													<td>10万</td>
													<td class="tAleft">100万</td>
												</tr>
												
											</table>
										</li>
					<!-- 国家邮政局邮政储汇局 -->
                    <li >
											<h3 class="h3Sty01">快捷充值限额<span class="limit_text">*因富友限额不定期更新，请以富友公示为准</span></h3>
											<table class="StyTable03 wB100">
												<tr>
													<th>卡种</th>
													<th>单笔限额(元)</th>
													<th>每日限额(元)</th>
													<th>每月限额(元)</th>
												</tr>
												<tr>
													<td>借记卡</td>
													<td>5万</td>
													<td>20万</td>
													<td class="tAleft">100万</td>
												</tr>
												
											</table>
										</li>										
                  <!-- 光大 -->
                  <li >
											<h3 class="h3Sty01">快捷充值限额<span class="limit_text">*因富友限额不定期更新，请以富友公示为准</span></h3>
											<table class="StyTable03 wB100">
												<tr>
													<th>卡种</th>
													<th>单笔限额(元)</th>
													<th>每日限额(元)</th>
													<th>每月限额(元)</th>
												</tr>
												<tr>
													<td>借记卡</td>
													<td>5万</td>
													<td>5万</td>
													<td class="tAleft">100万</td>
													
												</tr>
											</table>
										</li>
                  <!-- 中信 -->
                  <li >
											<h3 class="h3Sty01">快捷充值限额<span class="limit_text">*因富友限额不定期更新，请以富友公示为准</span></h3>
											<table class="StyTable03 wB100">
												<tr>
													<th>卡种</th>
													<th>单笔限额(元)</th>
													<th>每日限额(元)</th>
													<th>每月限额(元)</th>
												</tr>
												<tr>
													<td>借记卡</td>
													<td>1000</td>
													<td>1000</td>
													<td class="tAleft">2000</td>
												</tr>
											
											</table>
										</li>
                  <!-- 华夏 -->
                  <li >
											<h3 class="h3Sty01">快捷充值限额<span class="limit_text">*因富友限额不定期更新，请以富友公示为准</span></h3>
											<table class="StyTable03 wB100">
												<tr>
													<th>卡种</th>
													<th>单笔限额(元)</th>
													<th>每日限额(元)</th>
													<th>每月限额(元)</th>
												</tr>
												<tr>
													<td>借记卡</td>
													<td>10万</td>
													<td>20万</td>
													<td class="tAleft">100万</td>
												</tr>	
											</table>
										</li>
                  <!-- 民生 -->
                  <li >
											<h3 class="h3Sty01">快捷充值限额<span class="limit_text">*因富友限额不定期更新，请以富友公示为准</span></h3>
											<table class="StyTable03 wB100">
												<tr>
													<th>卡种</th>
													<th>单笔限额(元)</th>
													<th>每日限额(元)</th>
													<th>每月限额(元)</th>
												</tr>
												<tr>
													<td>借记卡</td>
													<td>10万</td>
													<td>20万</td>
													<td class="tAleft">100万</td>
												</tr>
											</table>
										</li>
                    <!-- 广东发展银行 -->
                    <li >
											<h3 class="h3Sty01">快捷充值限额<span class="limit_text">*因富友限额不定期更新，请以富友公示为准</span></h3>
											<table class="StyTable03 wB100">
												<tr>
													<th>卡种</th>
													<th>单笔限额(元)</th>
													<th>每日限额(元)</th>
													<th>每月限额(元)</th>
												</tr>
												<tr>
													<td rowspan="2">借记卡</td>
													<td>10万</td>
													<td>20万</td>
													<td class="tAleft">100万</td>
												</tr>
												
											</table>
										</li> 
                   <!-- 平安银行股份有限公司 -->
                   <li >
											<h3 class="h3Sty01">快捷充值限额<span class="limit_text">*因富友限额不定期更新，请以富友公示为准</span></h3>
											<table class="StyTable03 wB100">
												<tr>
													<th>卡种</th>
													<th>单笔限额(元)</th>
													<th>每日限额(元)</th>
													<th>每月限额(元)</th>
												</tr>
												<tr>
													<td>借记卡</td>
													<td>10万</td>
													<td>20万</td>
													<td class="tAleft">100万</td>
												</tr>
												
											</table>
										</li>
                    <!-- 招商银行 -->
                    <li >
											<h3 class="h3Sty01">快捷充值限额<span class="limit_text">*因富友限额不定期更新，请以富友公示为准</span></h3>
											<table class="StyTable03 wB100">
												<tr>
													<th>卡种</th>
													<th>单笔限额(元)</th>
													<th>每日限额(元)</th>
													<th>每月限额(元)</th>
												</tr>
												<tr>
													<td>借记卡</td>
													<td>5万</td>
													<td>5万</td>
													<td class="tAleft">100万</td>
												</tr>
											</table>
										</li>
                  <!-- 兴业银行 -->
                  <li >
											<h3 class="h3Sty01">快捷充值限额<span class="limit_text">*因富友限额不定期更新，请以富友公示为准</span></h3>
											<table class="StyTable03 wB100">
												<tr>
													<th>卡种</th>
													<th>单笔限额(元)</th>
													<th>每日限额(元)</th>
													<th>每月限额(元)</th>
												</tr>
												<tr>
													<td rowspan="4">借记卡</td>
													<td>5万</td>
													<td>5万</td>
													<td class="tAleft">100万</td>
												</tr>
												
											</table>
										</li>
                    <!-- 上海浦东发展银行 -->
                    <li >
											<h3 class="h3Sty01">快捷充值限额<span class="limit_text">*因富友限额不定期更新，请以富友公示为准</span></h3>
											<table class="StyTable03 wB100">
												<tr>
													<th>卡种</th>
													<th>单笔限额(元)</th>
													<th>每日限额(元)</th>
													<th>每月限额(元)</th>
												</tr>
												<tr>
													<td rowspan="2">借记卡</td>
													<td>5万</td>
													<td>20万</td>
													<td class="tAleft">100万</td>
												</tr>
											</table>
										</li>                  
               <!--    交通银行 -->
               <li >
											<h3 class="h3Sty01">快捷充值限额<span class="limit_text">*因富友限额不定期更新，请以富友公示为准</span></h3>
											<table class="StyTable03 wB100">
												<tr>
													<th>卡种</th>
													<th>单笔限额(元)</th>
													<th>每日限额(元)</th>
													<th>每月限额(元)</th>
												</tr>
												<tr>
													<td rowspan="2">借记卡</td>
													<td>5万</td>
													<td>5万</td>
													<td class="tAleft">100万</td>
												</tr>
												
											</table>
										</li>
                    </div>
                    </ul>
            			</dd>
                    	<dd>
                        	<div class="box-yhk">
                            	<label>银行名称：</label>
                            	<select class="inpu" required data-msg-required="请填写该选项" id="bindBank" name="bindBank"> <option value="">---请选择---</option> <option value="0102">中国工商银行</option> <option value="0103">中国农业银行</option> <option value="0104">中国银行</option> <option value="0105">中国建设银行</option> <option value="0403">中国邮政储蓄银行</option> <option value="0303">中国光大银行</option> <option value="0302">中信银行</option> <option value="0304">华夏银行</option> <option value="0305">中国民生银行</option> <option value="0306">广发银行</option> <option value="0307">平安银行</option> <option value="0308">招商银行</option> <option value="0309">兴业银行</option> <option value="0310">浦发银行</option> <option value="0301">交通银行</option></select>                             
                            	<div class="clear"></div>
                            </div>
                        </dd>
                        <dd class="hide" style="margin-top: 0px;">
                        	<div class="box-yhk">
                            	<label>&nbsp;</label>
                            	<span class="card_tishi"><a href="notice_details.jsp?OID=4a22abc03fa81f660114a8c40ec88bbb">查看支持的银行卡及限额详情</a></span>                               
                               
                            	<div class="clear"></div>
                            </div>
                           
                        </dd>
                        <dd style="margin-top: 20px;">
                        	<div class="box-yhk">
                            	<label>开户行地区：</label>
                            	<%-- <web:WidgetSelectOne  name="FormRegisterUCA" property="city"  customProperty="class='inpu' required data-msg-required='请选择开户行地区' "/> --%>
                            	 <%-- <web:WidgetSelectOne  name="FormRegisterUCA" property="city"  customProperty="class='inpu' required "/> --%> 
                            	 <!-- <select id="s_province" name="s_province" class='inpuselect inpu01select' ></select>  
			  					<select id="s_city" name="s_city" class='inpuselect inpu01select'></select>                                    
                            	 -->
                            	 <select id="pro" class='inpuselect inpu01select'  onchange="changecity()">  
									<option>--请选择--</option> 
									<option>北京市</option>
									<option>天津市</option>
									<option>上海市</option>
									<option>重庆市</option>
									<option>浙江省</option>
									<option>云南省</option>
									<option>新疆维吾尔自治区</option>
									<option>西藏自治区</option>
									<option>四川省</option>
									<option>陕西省</option>
									<option>山西省</option>
									<option>山东省</option>
									<option>青海省</option>
									<option>宁夏回族自治区</option>
									<option>内蒙古自治区</option>
									<option>辽宁省</option>
									<option>境外</option>
									<option>江西省</option>
									<option>江苏省</option>
									<option>吉林省</option>
									<option>湖南省</option>
									<option>湖北省</option>
									<option>黑龙江省</option>
									<option>河南省</option>
									<option>河北省</option>
									<option>海南省</option>
									<option>贵州省</option>
									<option>广西壮族自治区</option>
									<option>广东省</option>
									<option>甘肃省</option>
									<option>福建省</option>
									<option>安徽省</option>
 
								 </select>
								 <select class='inpuselect inpu01select' name="city" id="city">  
								 	<option>--请选择--</option> 
								 </select>
                            	
                            	<div class="clear"></div>
                            </div>
                        </dd>
                        
                        
                        <dd>
                        	<div class="box-yhk">
                            	<label>银行卡号：</label>
                            	<input type="text" name="bankNumber" id="bankNumber" class="inpu inpu01" required data-msg-required="银行卡号码不能为空" data-rule-bankcard="true" maxlength="19">                            
                            	<div class="clear"></div>
                            </div>
                        </dd>
                        <dd style='width: 735px;'>
                        	<div class="box-yhk">
                            	<label>设置交易密码：</label>
                            	<input type="password" name="withdrawalsPwd" id="withdrawalsPwd" class="inpu inpu01" required data-msg-required="交易密码不能为空" data-rule-pwd="true" data-msg-pwd="请输入不少于8位由数字和字母组成的密码">                               
                            	<div class="clear"></div>
                            </div>
                        </dd>
                        <dd>
                        	<div class="box-yhk">
                            	<label>确认交易密码：</label>
                            	<input type="password" name="rewithdrawalsPwd" id="rewithdrawalsPwd" class="inpu inpu01" required data-msg-required="确认交易密码不能为空" equalto="#withdrawalsPwd">                                
                            	<div class="clear"></div>
                            </div>
                        </dd>
                        <dd>
                        	<div class="box-yhk">
                            	<label>银行预留手机号：</label>
                            	<input type="text" name="phone" id="phone" class="inpu inpu01" readonly required data-msg-required="手机号不能为空" data-rule-mobile="true" data-msg-mobile="请输入正确格式">                                                             	
                            	<div class="clear"></div>
                            </div>                            
                        </dd>
                        <dd>
                        	<div class="box-yhk">
                            	<label>图形验证：</label>
                            	<input type="text" name="tuxing_Yzm" id="tuxing_Yzm" class="inpu inpu01 inpu02 inpu03" required data-msg-required="验证码不能为空" maxlength="6">                               
                                	<img class="yzmsz" width="100" height="32" src="/portal/user/checkcode"  class="m" alt="如果看不清,请点击我!" title="如果看不清,请点击我!"  onclick="this.setAttribute('src','/portal/user/checkcode?x='+Math.random());">
                            	<div class="clear"></div>
                            </div>
                           
                        </dd>
                        <dd>
                        	<div class="box-yhk">
                            	<label>短信验证：</label>
                            	<input type="text" name="msm" id="mesYzm" class="inpu inpu01 inpu02" required data-msg-required="验证码不能为空" maxlength="6">                               
                                <a class="hqyzm" onclick="huoquyzm()">获取短信验证码</a>	
                            	<div class="clear"></div>
                            </div>
                           
                        </dd>
                        <div align="left" style="color:#f00;margin-top:20px; margin-left:132px;" id="sign_val">${msg}</div>
                       
                        <dd>
                        	<div class="box-yhk">
                            	<label></label>
                            	<button id="" name="" type="submit" value="确认" class="botton-1">确认</button>                               
                               
                            	<div class="clear"></div>
                            </div>
                           
                        </dd>
                        
                </dl>
            </div> 
					
            	
            	
            	
                </form>
              	<div class="clear"></div>
        </div>
        
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

<script src="/js/tabmenu.js"></script>
<!--验证表单-->
<script src="/js/jquery.validate.js"></script>
<script>
$(function(){
    //jquery.validate
	$("#FormRegisterUCA").validate({
		submitHandler: function(form) {
			//验证通过后 的js代码写在这里
			//alert(1);
			form.submit();
		}
	})
	$("#bindBank").change(function(){
		//alert($('#bindBank option:selected').val());
		var index=$("#bindBank ").get(0).selectedIndex-1;
		$(".cardyong li").eq(index).css("display","block").siblings("li").css("display","none");
		if(index<0){
			$(".cardyong li").css("display","none");
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

/*-------------$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$-------------*/





//手机验证规则  
jQuery.validator.addMethod("mobile", function (value, element) {
    var mobile = /^1[3|4|5|7|8]\d{9}$/;
	return this.optional(element) || (mobile.test(value));
}, "手机格式不对");

//交易密码验证规则  
jQuery.validator.addMethod("pwd", function (value, element) {
    var pwd = /^(?!([a-zA-Z]+|\d+)$)[a-zA-Z\d]{8,}$/;
	return this.optional(element) || (pwd.test(value));
}, "请输入不少于8位由数字和字母组成的密码");

//银行卡号验证规则
jQuery.validator.addMethod("bankcard", function (value, element) {
    var bankcard = /^(\d{16}|\d{17}|\d{18}|\d{19})$/;
	return this.optional(element) || (bankcard.test(value));
}, "请输入16-19位的银行卡号");

//身份证
jQuery.validator.addMethod("card", function (value, element) {
    var isIDCard1=/^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$/;//(15位)
    var isIDCard2=/^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/;//(18位)
    
    return this.optional(element) || (isIDCard1.test(value)) || (isIDCard2.test(value));
}, "格式不对");

//姓名验证规则  
jQuery.validator.addMethod("idname", function (value, element) {
    var idname = /^[\u4E00-\u9FFF]{2,4}$/;
	return this.optional(element) || (idname.test(value));
	
}, "请输入真实姓名"); 

</script>
<!-- 全国城市三级联动 -->
<script  src="/js/areacard.js" type="text/javascript"></script>
<script type="text/javascript">_init_area();</script>
</html>
