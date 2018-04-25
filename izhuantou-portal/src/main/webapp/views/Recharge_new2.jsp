<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/include/url.jsp" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>砖头网</title>
<link rel="stylesheet" type="text/css"
	href="/css/style-common.css">
<link rel="stylesheet" type="text/css"
	href="/css/personalcenter.css">
	<link rel="stylesheet" type="text/css" href="/css/preother.css?v=114">
<link rel="stylesheet" type="text/css"
	href="/css/CapitalDetailed.css">
<link rel="stylesheet" type="text/css"
	href="/css/base.css">
<Link rel="shortcut icon" href="<%=pathUrl %>/images/zhuan.ico" />
<script type="text/javascript"
	src="/js/jquery-1.10.1.min.js"></script>
<script src="/js/com.js"></script>
<script language="javascript" src="/js/menu.js"></script>
<script src="/js/resubmit.js"></script>
<script src="/js/base.js"></script>
<script src="/js/paging.js"></script>
<!--日期时间-->
<script language="javascript" type="text/javascript"
	src="/My97DatePicker/WdatePicker.js"></script>
<style type="text/css">
.autorow {
	position: relative;
	width: 400px;
}

.shouxuF {
	display: none
}

.sxf {
	height: 30px
}

span.error {
	position: absolute;
	top: 0px;
	left: 305px;
	width:350px;
	color: #C00;
	line-height: 30px;
	display: block;
}

.yzm span.error {
	left: 390px;
}
.StyIn-t01 span.error {
    left: 435px;
}
.StyIn-t02 span.error {
    left: 305px;
}
.hide_liu {
	width: 100%;
	height: 500px;
	text-align: center;
	background: #fff url("images/Recharge_liu.jpg") no-repeat center;
	display: none;
}

.card_tishi a {
	color: #55b0fd;
	margin-left: 10px;
}

.card_tishi a:hover {
	text-decoration: underline;
}
.IDzhanghuCZ01 li a{
    color:#333;
    display: inline-block;
    width: 100%;
    height: 100%;
}
.IDzhanghuTX01{
   position: relative;
}
.success_btn{
line-height:40px;
}
#sign_val{
 color:#f00;
}
.IDketixianE_f12{line-height: 40px;}
</style>

<script>
function huoquyzm(){	
	var phoneNum = document.getElementById('money').value;	
	var yzm= document.getElementById('yzm').value;	
	  var oSign_val=$("#sign_val");
	  var cd_popup1=$(".cd-popup2");
	if (phoneNum!=""){
		if(phoneNum>=100){
		if(yzm!=""){

  	 $.ajax({
		        type:"POST",
		        async:false,
		        dataType:"json",
		        url:"/portal/cash/checkValidateCode",
		        data:{yzm:yzm},
		        success: function(result){
		        	if(result==0){
			   		    oSign_val[0].innerHTML = '验证码不正确';
			   		 $('.m').attr("src","/portal/user/checkcode?x="+Math.random());
			   		
		        		}else{
		        			$(".confirmbtn").click();
		        			oSign_val[0].innerHTML ="";
		        			cd_popup1.addClass("is-visible1");
		        		}
		        	//jquery.validate
		    		
		        },
		        error:function(result){
		        	$('.m').attr("src","/portal/user/checkcode?x="+Math.random());
		        }
		    });
		}else{
			oSign_val[0].innerHTML ="请输入验证码！";
			$('.m').attr("src","/portal/user/checkcode?x="+Math.random());
		}
		}else{
			oSign_val[0].innerHTML ="";
			$('.m').attr("src","/portal/user/checkcode?x="+Math.random());
		}
	}else{
		oSign_val[0].innerHTML ="请输入金额";
		$('.m').attr("src","/portal/user/checkcode?x="+Math.random());
	}

}

</script>
</head>
<body>
<% try{ %>
<div class="cd-popup2 cd-popup-tishi">
<div class="cd-popup-container1">
<!--温馨提示-->
	<div class="comnei ">
        <p class="commit-1">温馨提示</p>
       <div class="prompt_content">
       <img src="<%=pathUrl %>/images/reminder_icon.png">
       <p>请您在新打开的第三方账户管理账户内完成充值<br>付款完成前请不要关闭此窗口<br>完成充值后请点击下面的按钮</p>
       <div class="prompt_btn"><a href="Recharge_new2.jsp">完成操作</a></div>
       <div class="prompt_tishi">（由于富友及网络影响，您的账户金额可能会延迟显示，请稍后查看即可）</div>
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
    	<a >当前位置</a><span>:</span><a >个人中心</a><span>&gt;</span><a >我的资金</a><span>&gt;</span><a >充值</a>
        </div>
        <div class="con xqdetial">
    	<%@ include file="left_percenter.jsp" %>
        
    	<div class="xqdeleft my_loan">
        	<div class="my_loantop"><a href="#" class="active">充值</a><a href="#" >充值记录</a><span class="L2">&nbsp;</span></div>
            <div class="clear"></div>
            
            <div>
            <!--账户充值-->
             <div class="loanrecord01">
            <dl class="recharge_main">
						<dd class="TAGjiegou5" style="display: block;">
							<div class="StyBt1px">
								<ul class="IDzhanghuCZ01" tag-func-id="1" tags="li"
									tag-event="click">
									<li><a href="Recharge_new.jsp"><span>网银充值</span><i class="ico2 ICOfS01_36"></i></a></li>

									<li class="on"><span>快捷充值</span><i class="ico2 ICOfS01_36"></i></li>

								</ul>
								<div class="wen_tishi"><img class="wen_iconImg" src="<%=pathUrl %>/images/wen_icon_c.png">
								<div class="tishi_box">
								<div class="tishi_wrap">
								<h3>1、什么是快捷充值？</h1>
								<p style="text-indent:2em;">快捷充值，是富友提供的一种快速简便的支付方式。用户不需要开通网银，只需要绑定银行卡，开通富友账户，即可享受安全快捷的资金充值和提现服务。</p>
								</div>
								<div class="tishi_wrap">
								<h3>2、什么是网银支付？</h1>
								<p style="text-indent:2em;">网银支付，是富友提供的一种传统大额的支付方式。用户使用已开通网银功能的任意银行卡，在富友支持的银行网银进行操作，即可享受超高额度的资金充值和提现服务。</p>
								</div>
								<div class="tishi_wrap" style="margin-bottom:25px;">
								<h3>3、我适合哪种支付方式？</h1>
								<p style="text-indent:2em;">快捷支付方式具有流程简便，操作简单特点，适合出借金额较低的用户；网银支付方式可支持较高的交易额度，适合有较大支付金额需求的用户。用户可按照自身出借金额的情况自由选择。</p>
								</div>
								</div>
								</div>
								<div style="clear:both;"></div>
							</div>
							<ul id="bankUi" class="IDzhanghuCZ02 IDgerenzhanghu cl"
								tag-func-id="1" cards=".IDzhanghu_li">
								<!-- IDqiyezhanghu 或 IDqiyezhanghu 两个样式切换个人用户和企业用户 -->
								 <li class="IDzhanghu_li" tag-func-id="4" tags="label"
									tag-event="click" auto-one="false">
									<div class="StyBt1px mB30">
										<ul class="IDzhanghuTX01 ID_kuaijie">
											<li class="cl pB30">
												<div class="box-quan">
            	<div class="cardyong">
                    <div class="cardyonglist">
                    <div class="card01"><span class="sp-img">
                    
                    </span></div>
                    <div class="divcardname"><span class="sp-name"></span></div>
                    <div class="divcardnum"><span class="sp-num"></span></div>
                    </div>                    
                    </div> 
                    
                     
                </div>
                <hoontag:TagAction handleClassName="memberInfo" />
                <div class="card_tishi">请在富友页面确认<span></span>是否为该银行卡预留手机号</div>
                <div style="position: absolute;left:110px;bottom:-46px;">*因富友限额不定期更新，请以富友公示为准</div>
												</li>
										</ul>
            	<ul tag-func-id="4" cards="li" class="IDyinhangDataList">
            		
                 	<div class="cardyong">
                     
               <!--     工商 -->
                     <li class='0102' style="display:none">
											<h3 class="h3Sty01">中国工商银行</h3>
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
                    
                   
                    <!-- 建设银行 -->                    
                    <li class='0105' style="display:none">
											<h3 class="h3Sty01">中国建设银行</h3>
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
                   
                     
                   <!-- 农业 -->
                     <li class='0103' style="display:none">
											<h3 class="h3Sty01">中国农业银行</h3>
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
                     <li class='0104' style="display:none">
											<h3 class="h3Sty01">中国银行</h3>
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
                    
                  
                    
                  <!-- 光大 -->
                  <li class='0303' style="display:none">
											<h3 class="h3Sty01">光大银行</h3>
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
                  <li class='0302' style="display:none">
											<h3 class="h3Sty01">中信银行</h3>
											<table class="StyTable03 wB100">
												<tr>
													<th>卡种</th>
													<th>单笔限额(元)</th>
													<th>每日限额(元)</th>
													<th>每月限额(元)</th>
												</tr>
												<tr>
													<td>借记卡</td>
													<td>1万</td>
													<td>2万</td>
													<td class="tAleft">2万</td>
												</tr>
											
											</table>
										</li>
                     
                
                    
                  <!-- 华夏 -->
                  <li class='0304' style="display:none">
											<h3 class="h3Sty01">华夏银行</h3>
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
                  <li class='0305' style="display:none">
											<h3 class="h3Sty01">中国民生银行</h3>
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
                    <li class='0306' style="display:none">
											<h3 class="h3Sty01">广发银行</h3>
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
                   <li class='0307' style="display:none">
											<h3 class="h3Sty01">平安银行</h3>
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
                    <li class='0308' style="display:none">
											<h3 class="h3Sty01">招商银行</h3>
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
                  <li class='0309' style="display:none">
											<h3 class="h3Sty01">兴业银行</h3>
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
                    <li class='0310' style="display:none">
											<h3 class="h3Sty01">浦发银行</h3>
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
               <li class='0301' style="display:none">
											<h3 class="h3Sty01">交通银行</h3>
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
                       
                 <!-- 徽商银行 -->
                   
                
                  
                    <!-- 国家邮政局邮政储汇局 -->
                    <li class='0403' style="display:none">
											<h3 class="h3Sty01">邮政储蓄银行</h3>
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
                    </div>
                    </ul>                  

            	<!-- 银行卡限额结束 -->
										<div id="kuaijiechongzhiXE"></div>
										<div class="StyBt1px mB30">
										<h3 class="IDzhanghuTX_title">填写充值金额</h3>
										<form action="/portal/cash/WYrecharge" method="post" name="FormRecharge" id="FormRecharge"  target='_blank' >
										<ul class="IDzhanghuTX01 ID_wanyin">
											<li><span class="l">可用余额：</span>
												<div class="l">
													<span class="c_cheng IDketixianE"><b id="available"><web:WidgetTextOut name="FormRecharge" property="dismoney"  /></b><em>元</em></span>
												</div></li>
												<li style="display:none"><input type="text" name="type" id="type" autocomplete='off' readonly required value='2' /></li>
											<li><span class="l">充值金额：</span>
												<div class="l">
													<label class="pR StyIn-t01 L_ico"> <i
														class="ico2 ICOfS01_21"></i><input type="text" name="money"  id="money" autocomplete='off' txtype='FloatZ1000W' placeholder='请输入充值金额' required  data-rule-gt='true' data-gt='0'  maxlength='10'  /> <i>元</i> <span class="new_tishi">单笔充值金额不得少于100元</span>
													</label>
												</div></li>
												<li><span class="l">验证码：</span><div class="l"><label class="pR StyIn-t01 StyIn-t02 L_ico"><input type="text" name="yzm" id="yzm" autocomplete='off' required maxlength='4' /></label><span style="margin-left:10px;"><img width="100" height="30" src="/portal/user/checkcode"  class="m" alt="如果看不清,请点击我!" title="如果看不清,请点击我!""  onclick="this.setAttribute('src','/portal/user/checkcode?x='+Math.random());"></span></div></li>
											<li><span class="l">充值费用：</span>
												<div class="l">
													<span class="c_666 IDketixianE "><b id="sxf2" style="display:none;">0.00</b></span><span
														class="IDketixianE_f12">已开启充值无忧服务，手续费用由砖头网为您支付</span>
												</div></li>
										</ul>
										<div class="autorow error" id="sign_val"><div align="left"><hoontag:Message /></div></div>
									</div>
									</div>
									
									<div class="StyBt1px IDzhanghuTX02 hide">
										<button id="" name="" type="submit" value="确认充值" class="botton-1 confirmbtn lan" target="_blank">确认充值</button>
									</div>
									<div class="StyBt1px IDzhanghuTX02">
										<div  class="success_btn lan" onclick="huoquyzm()">确认充值</div>
									</div>
									</form>
									<dl class="IDzhanghuTX03">
										<dt>温馨提示</dt>
										<dd>1. 为了您的资金安全，您需要在充值之前开通托管账户，完成实名认证，并设置交易密码；</dd>
				<dd>2. 由于第三方支付限制及银行额度限制，单笔充值额度以各银行限额为准；<%-- <span class="card_tishi"><a href="notice_details.jsp?OID=4a22abc03fa81f660114a8c40ec88bbb">查看支持的银行卡及限额</a></span> --%></dd>
				<dd>3. 严禁利用充值功能进行信用卡套现、转账、洗钱等行为，一经发现，资金将退回原卡并封停账号30天；</dd>
				<dd>4. 判定为风险、可疑交易的资金将通过第三方支付平台退回原卡，到账时间以发卡行通知为准，由此可能产生的费用由个人承担；</dd>
				<dd>5. 港澳台用户需用绑定银行卡持网银充值，其他方式敬请期待；</dd>
				<dd>6. 以上最终解释权归砖头网所有。</dd>
									</dl>
                       
								</li> 
							</ul>
						</dd>
					
					</dl>
            
            </div>             
            <!--交易记录-->
            <div class="loanrecord01 hide">
            <div class="loanrecord">
            	<div class="mytzcon">
            		<div class="rowbox04 tit"><span>充值时间</span><span>充值单号</span><span>充值金额</span><span>状态</span></div>
	            	
                	<div class="hide_liu Recharge_liu"></div>
                </div>
                
                <!-- <div class="nonemore">没有更多记录</div> -->
            	
            	<!--分页-->
            	<div id="page" class="page_div"></div>
                <!--分页结束-->
            </div>
            </div>
            </div>
            
<%}catch(Exception e){
	response.sendRedirect("error.jsp");
    return;  
} %>
					



					<!--历史充值-->
					<div class="hide"></div>

					<div class="clear"></div>
				</div>

			</div>
		</div>
	</div>
	<!-----------------中间部分结束---------------->
	<!--bottom common-->
	<%@ include file="footer.jsp"%>

</body>
<!--<script src="/js/tabmenu.js"></script>-->
<!--验证表单-->
<script src="/js/jquery.validate.js"></script>
<script src="/js/card_tishi.js"></script>
<script>
	$(function() {
		$("#FormRecharge").validate({
			submitHandler : function(form) {
				validataForm("confirmbtn");
				form.submit();
			}
		})
		
		//充值控制数值JS
	    funcData.eChange.COM(); 
		
      $(".IDyinhangkaListDiv label").click(function(){
    	  var index=$(this).index();
    	  $(this).addClass("on").siblings().removeClass('on');
    	  $(this).find("i").show().parent().siblings().find("i").hide();
    	  var id_card=$(this).find("input").val();
    	  $("#card_id").val(id_card);
    	  $(".IDyinhangDataList li").eq(index).show().siblings().hide();
    	  //alert(index);
      })
      $(".IDzhanghuCZ03 .IDzhanghuCZ03_ID01").click(function(){
    	  $(".IDyinhangkaListDiv").animate({"height":"255px"});
    	  $(this).hide();
    	  $(".IDzhanghuCZ03_ID02").show();
      })
      $(".IDzhanghuCZ03 .IDzhanghuCZ03_ID02").click(function(){
    	  $(".IDyinhangkaListDiv").animate({"height":"180px"});
    	  $(this).hide();
    	  $(".IDzhanghuCZ03_ID01").show();
      })
      $(".IDzhanghuCZ01 li").click(function(){
    	  var index=$(this).index();
    	  $(this).addClass("on").siblings().removeClass("on");
    	  $(".IDzhanghu_li").eq(index).removeClass("hide").siblings().addClass("hide");
      })
      
	})
	  $.ajax({
        dataType:"json",
        url:"/portal/cash/cardmsg",
        success: function(result){
        	console.log(result);
        	$("#available").text((result.dataValue.useMoney).toFixed(2));
        	$(".card_tishi span").text(result.dataValue.mobile);
        },
        error:function(result){
        	console.log("发生错误 ");
        }
    });
	//资金流水留白
	$(function() {
		$.ajax({
	        dataType:"json",
	        url:"/portal/cash/recharge",
	        data:{currentpage:1,
	        	state:'recharge'},
	        success: function(result){
	        	console.log(result);
	        	var Pagination=result.dataValue.Pagination;
	        	var RechargeList=result.dataValue.RechargeList;
	        	
	        	if(Pagination.totalNumber<=0){
	        		$(".Recharge_liu").css("display", "block");
	        	}else{
	        		for(var i=0;i<RechargeList.length;i++){
		        		var ListStr='<div class="rowbox04 value"><span>'+RechargeList[i].addDateTime+'</span><span>'+RechargeList[i].requestID+'</span><span>'+(RechargeList[i].money).toFixed(2)+'</span><span>'+RechargeList[i].content+'</span></div>';
		        		$(".mytzcon").append(ListStr);
	        		}
	        		
	        	}
	        	if(Pagination.totalNumber<=12){
	        		$("#page").remove();
	        	}
	        	
	        	$("#page").paging({
	        		totalPage: Pagination.totalPage,
	        		totalSize: Pagination.totalNumber,
	        		callback: function(num) {
	        			$.ajax({
	        		        dataType:"json",
	        		        url:"/portal/cash/recharge",
	        		        data:{currentpage:num,
	        		        	state:'recharge'},
	        		        success: function(result){
	        		        	console.log(result);
	        		        	var RechargeList=result.dataValue.RechargeList;
	        		        	$(".mytzcon .rowbox04.value").remove();
	        		        		for(var i=0;i<RechargeList.length;i++){
	        			        		var ListStr='<div class="rowbox04 value"><span>'+RechargeList[i].addDateTime+'</span><span>'+RechargeList[i].requestID+'</span><span>'+(RechargeList[i].money).toFixed(2)+'</span><span>'+RechargeList[i].content+'</span></div>';
	        			        		$(".mytzcon").append(ListStr);
	        		        		}
	        		        },
	        		        error:function(result){
	        		        	console.log("发生错误 ");
	        		        }
	        		    });
	        		}
	        	
	        	})
	        	
	        	
	        },
	        error:function(result){
	        	console.log("发生错误 ");
	        }
	    });
	})
	//
	//
	$(function() {
		if (window.addEventListener) {
			$('#money').bind(
					'input',
					function() {//给文本框绑定input事件
						if ($('#money').val() != "") {
							if($("#money").val()>0){
								$('.shouxuF').css("display", "block")
								/* $('#sxf2')[0].innerHTML = "";
										+ ($('#money').val() * 0.0015).toFixed(2)
										+ ""; */
							}
						}else{
							/* $('#sxf2')[0].innerHTML = "0.00"; */
						}
					});
		} else if (window.attachEvent) {
			$('#money').bind(
					'propertychange',
					function() {//给文本框绑定input事件
						if ($('#money').val() != "") {
							if($("#money").val()>0){
							$('.shouxuF').css("display", "block")
							/* $('#sxf2')[0].innerHTML = ""
									+ ($('#money').val() * 0.0015).toFixed(2)
									+ ""; */
									
							}
						}else{
							/* $('#sxf2')[0].innerHTML = "0.00"; */
						}
					});
		}

	});
	
	$(function(){
		$.ajax({
	        type: "get",
	        url: "/portal/cash/myCashmessage",
	        dataType: "json",
	        success: function(result){
	        console.log(result.dataValue);
	        var CodeStr='';
	        if(result.dataValue.bankCode=='0102'){
	        	CodeStr='<img id="cardImage" src="<%=pathUrl %>/images/card_gs.png">';
	        	$(".0102").css("display","block");
	        }else if(result.dataValue.bankCode=='0105'){
	        	CodeStr='<img id="cardImage" src="<%=pathUrl %>/images/card_jh.png">';
	        	$(".0105").css("display","block");
	        }else if(result.dataValue.bankCode=='0103'){
	        	CodeStr='<img id="cardImage" src="<%=pathUrl %>/images/card_ny.png">';
	        	$(".0103").css("display","block");
	        }else if(result.dataValue.bankCode=='0104'){
	        	CodeStr='<img id="cardImage" src="<%=pathUrl %>/images/card_zg.png">';
	        	$(".0104").css("display","block");
	        }else if(result.dataValue.bankCode=='0303'){
	        	CodeStr='<img id="cardImage" src="<%=pathUrl %>/images/card_gd.png">';
	        	$(".0303").css("display","block");
	        }else if(result.dataValue.bankCode=='0302'){
	        	CodeStr='<img id="cardImage" src="<%=pathUrl %>/images/card_zx.png">';
	        	$(".0302").css("display","block");
	        }else if(result.dataValue.bankCode=='0304'){
	        	CodeStr='<img id="cardImage" src="<%=pathUrl %>/images/card_hx.png">';
	        	$(".0304").css("display","block");
	        }else if(result.dataValue.bankCode=='0305'){
	        	CodeStr='<img id="cardImage" src="<%=pathUrl %>/images/card_ms.png">';
	        	$(".0305").css("display","block");
	        }else if(result.dataValue.bankCode=='0306'){
	        	CodeStr='<img id="cardImage" src="<%=pathUrl %>/images/card_gf.png">';
	        	$(".0306").css("display","block");
	        }else if(result.dataValue.bankCode=='0307'){
	        	CodeStr='<img id="cardImage" src="<%=pathUrl %>/images/card_pa.png">';
	        	$(".0307").css("display","block");
	        }else if(result.dataValue.bankCode=='0308'){
	        	CodeStr='<img id="cardImage" src="<%=pathUrl %>/images/card_zs.png">';
	        	$(".0308").css("display","block");
	        }else if(result.dataValue.bankCode=='0309'){
	        	CodeStr='<img id="cardImage" src="<%=pathUrl %>/images/card_xy.png">';
	        	$(".0309").css("display","block");
	        }else if(result.dataValue.bankCode=='0310'){
	        	CodeStr='<img id="cardImage" src="<%=pathUrl %>/images/card_pf.png">';
	        	$(".0310").css("display","block");
	        }else if(result.dataValue.bankCode=='0301'){
	        	CodeStr='<img id="cardImage" src="<%=pathUrl %>/images/card_jt.png">';
	        	$(".0301").css("display","block");
	        }else if(result.dataValue.bankCode=='0319'){
	        	CodeStr='<img id="cardImage" src="<%=pathUrl %>/images/card_hs.png">';
	        	$(".0319").css("display","block");
	        }else if(result.dataValue.bankCode=='0403'){
	        	CodeStr='<img id="cardImage" src="<%=pathUrl %>/images/card_yz.png">';
	        	$(".0403").css("display","block");
	        }
	        $(".sp-img").append(CodeStr);
	        $(".sp-name").append(result.dataValue.nameCN);
	        $(".sp-num").append(result.dataValue.bankNumber);      
	            
	        },
			error:function(result){
	    	}
	    });
	})


	//配置错误提示的节点，默认为label，这里配置成 span （errorElement:'span'）
	$.validator.setDefaults({
		errorElement : 'span'
	});

	//配置通用的默认提示语
	$.extend($.validator.messages, {
		required : '必填',
		equalTo : "请再次输入相同的值"
	});


	//邮箱 
	jQuery.validator.addMethod("mail", function(value, element) {
		var mail = /^[a-z0-9._%-]+@([a-z0-9-]+\.)+[a-z]{2,4}$/;
		return this.optional(element) || (mail.test(value));
	}, "邮箱格式不对");

	//电话验证规则
	jQuery.validator.addMethod("phone", function(value, element) {
		var phone = /^0\d{2,3}-\d{7,8}$/;
		return this.optional(element) || (phone.test(value));
	}, "电话格式如：0371-68787027");

	//区号验证规则  
	jQuery.validator.addMethod("ac", function(value, element) {
		var ac = /^0\d{2,3}$/;
		return this.optional(element) || (ac.test(value));
	}, "区号如：010或0371");

	//无区号电话验证规则  
	jQuery.validator.addMethod("noactel", function(value, element) {
		var noactel = /^\d{7,8}$/;
		return this.optional(element) || (noactel.test(value));
	}, "电话格式如：68787027");

	//手机验证规则  
	jQuery.validator.addMethod("mobile", function(value, element) {
		var mobile = /^1[3|4|5|7|8]\d{9}$/;
		return this.optional(element) || (mobile.test(value));
	}, "手机格式不对");

	//邮箱或手机验证规则  
	jQuery.validator
			.addMethod(
					"mm",
					function(value, element) {
						var mm = /^[a-z0-9._%-]+@([a-z0-9-]+\.)+[a-z]{2,4}$|^1[3|4|5|7|8]\d{9}$/;
						return this.optional(element) || (mm.test(value));
					}, "格式不对");

	//电话或手机验证规则  
	jQuery.validator
			.addMethod(
					"tm",
					function(value, element) {
						var tm = /(^1[3|4|5|7|8]\d{9}$)|(^\d{3,4}-\d{7,8}$)|(^\d{7,8}$)|(^\d{3,4}-\d{7,8}-\d{1,4}$)|(^\d{7,8}-\d{1,4}$)/;
						return this.optional(element) || (tm.test(value));
					}, "格式不对");

	//年龄
	jQuery.validator.addMethod("age", function(value, element) {
		var age = /^(?:[1-9][0-9]?|1[01][0-9]|120)$/;
		return this.optional(element) || (age.test(value));
	}, "不能超过120岁");
	///// 20-60   /^([2-5]\d)|60$/

	//传真
	jQuery.validator.addMethod("fax", function(value, element) {
		var fax = /^(\d{3,4})?[-]?\d{7,8}$/;
		return this.optional(element) || (fax.test(value));
	}, "传真格式如：0371-68787027");

	//验证当前值和目标val的值相等 相等返回为 false
	jQuery.validator.addMethod("equalTo2", function(value, element) {
		var returnVal = true;
		var id = $(element).attr("data-rule-equalto2");
		var targetVal = $(id).val();
		if (value === targetVal) {
			returnVal = false;
		}
		return returnVal;
	}, "不能和原始密码相同");

	//大于指定数
	jQuery.validator.addMethod("gt", function(value, element) {
		var returnVal = false;
		var gt = /^([1-9]{1}\d{2,})+(\.[0-9]{1,2})?$/;
		return this.optional(element) || (gt.test(value));
	}, "请输入大于100的正确数字");
	//小于指定数
	jQuery.validator.addMethod("lt", function(value, element) {
		var returnVal = false;
		var lt = $(element).data("lt");
		if (value <= lt && value != "") {
			returnVal = true;
		}
		return returnVal;
	}, "请输入小于1500000的正确数字");

	//汉字
	jQuery.validator.addMethod("chinese", function(value, element) {
		var chinese = /^[\u4E00-\u9FFF]+$/;
		return this.optional(element) || (chinese.test(value));
	}, "格式不对");

	//指定数字的整数倍
	jQuery.validator.addMethod("times", function(value, element) {
		var returnVal = true;
		var base = $(element).attr('data-rule-times');
		if (value % base != 0) {
			returnVal = false;
		}
		return returnVal;
	}, "必须是发布赏金的整数倍");

	//身份证
	jQuery.validator
			.addMethod(
					"card",
					function(value, element) {
						var isIDCard1 = /^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$/;//(15位)
						var isIDCard2 = /^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/;//(18位)

						return this.optional(element)
								|| (isIDCard1.test(value))
								|| (isIDCard2.test(value));
					}, "格式不对");
</script>
</html>
