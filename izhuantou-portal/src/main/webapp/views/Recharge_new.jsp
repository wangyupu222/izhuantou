
<%@ page contentType="text/html; charset=UTF-8" %>
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
<Link rel="shortcut icon" href="/images/zhuan.ico" />
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
       <img src="/images/reminder_icon.png">
       <p>请您在新打开的第三方账户管理账户内完成充值<br>付款完成前请不要关闭此窗口<br>完成充值后请点击下面的按钮</p>
       <div class="prompt_btn"><a href="Recharge_new.jsp">完成操作</a></div>
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
									<li class="on"><span>网银充值</span><i class="ico2 ICOfS01_36"></i></li>

									<li><a href="Recharge_new2.jsp"><span>快捷充值</span><i class="ico2 ICOfS01_36"></i></a></li>

								</ul>
								<div class="wen_tishi"><img class="wen_iconImg" src="/images/wen_icon_c.png">
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
									<div class="IDyinhangkaListDiv">
										<label class="on"> <input type="checkbox" name="bankId"
											value="0801040000" checked="checked"> <img
											src="/images/card_01.jpg" alt="中行"> <i
											class="ico2 ICOfS01_37" style="display: inline;"></i>
										</label> <label style="text-align:center;"> <input type="checkbox" name="bankId"
											value="0801030000"> <img style="width: 150px;"
											src="/images/card_02.png" alt="工行"> <i
											class="ico2 ICOfS01_37"></i>
										</label> <label> <input type="checkbox" name="bankId"
											value="0801020000"> <img
											src="/images/card_03.jpg" alt="建行"> <i
											class="ico2 ICOfS01_37"></i>
										</label> <label> <input type="checkbox" name="bankId"
											value="0801050000"> <img
											src="/images/card_04.jpg" alt="交行"> <i
											class="ico2 ICOfS01_37"></i>
										</label> <label> <input type="checkbox" name="bankId"
											value="0803080000"> <img
											src="/images/card_05.jpg" alt="招行"> <i
											class="ico2 ICOfS01_37"></i>
										</label> 
										<%-- <label> <input type="checkbox" name="bankId"
											value="0803010000"> <img
											src="/images/card_06.jpg" alt="民生"> <i
											class="ico2 ICOfS01_37"></i>
										</label> --%>
										 <label> <input type="checkbox" name="bankId"
											value="0803050000"> <img
											src="/images/card_07.jpg" alt="兴业"> <i
											class="ico2 ICOfS01_37"></i>
										</label> 
										<%-- <label> <input type="checkbox" name="bankId"
											value="0803090000"> <img
											src="/images/card_08.jpg" alt="广发"> <i
											class="ico2 ICOfS01_37"></i>
										</label> --%>
										  <label> <input type="checkbox" name="bankId"
											value="0803060000"> <img
											src="/images/card_09.jpg" alt="中信"> <i
											class="ico2 ICOfS01_37"></i>
										</label> <label> <input type="checkbox" name="bankId"
											value="0803020000"> <img
											src="/images/card_10.jpg" alt="华夏"> <i
											class="ico2 ICOfS01_37"></i>
										</label> <label> <input type="checkbox" name="bankId"
											value="0803030000"> <img
											src="/images/card_11.jpg" alt="光大"> <i
											class="ico2 ICOfS01_37"></i>
										</label> <label class="IDyinhang_no_qiye"> <input
											type="checkbox" name="bankId" value="0803040000"> <img
											src="/images/card_12.jpg" alt="邮储"> <i
											class="ico2 ICOfS01_37"></i>
										</label> <label> <input type="checkbox" name="bankId"
											value="0803100000"> <img
											src="/images/card_13.jpg" alt="浦发"> <i
											class="ico2 ICOfS01_37"></i>
										</label> <label class="IDyinhang_yes_qiye"> <input
											type="checkbox" name="bankId" value="0804105840"> <img
											src="/images/card_14.jpg" alt="徽商银行">
											<i class="ico2 ICOfS01_37"></i>
										</label> <label class="IDyinhang_yes_qiye"> <input
											type="checkbox" name="bankId" value="0804031000"> <img
											src="/images/card_15.jpg" alt="天津银行">
											<i class="ico2 ICOfS01_37"></i>
										</label>
										<label class="IDyinhang_yes_qiye"> <input
											type="checkbox" name="bankId" value="0801000000"> <img
											src="/images/card_17.jpg" alt="邮政储蓄">
											<i class="ico2 ICOfS01_37"></i>
										</label>
										
										<div style="clear:both"></div>
									</div>
									<div class="IDzhanghuCZ03 ID_bomjiantou">
										<a href="javascript:;"
											class="btn c_chengI hide_noI IDzhanghuCZ03_ID01">查看更多充值银行<i
											class="ico2 ICOfS01_38"></i></a> <a href="javascript:;"
											class="btn c_chengI hide_noI IDzhanghuCZ03_ID02" style="display:none">点击折叠银行信息<i
											class="ico2 ICOfS01_39"></i></a>
									</div>
									<ul tag-func-id="4" cards="li" class="IDyinhangDataList">
										<li style="display: list-item;">
											<h3 class="h3Sty01">中国银行</h3>
											<table class="StyTable03 wB100">
												<tr>
													<th>卡种</th>
													<th>单笔限额(元)</th>
													<th>每日限额(元)</th>
													<th>必要条件</th>
												</tr>
												<tr>
													<td rowspan="6">借记卡</td>
													<td>5万</td>
													<td>5万</td>
													<td class="tAleft">一代ukey</td>
												</tr>
												<tr>
													<td>5万</td>
													<td>5万</td>
													<td class="tAleft">二代UKEY</td>
												</tr>
												<tr>
													<td>5万</td>
													<td>5万</td>
													<td class="tAleft">短信</td>
												</tr>
												<tr>
													<td>5万</td>
													<td>5万</td>
													<td class="tAleft">口令卡短信认证</td>
												</tr>
												<tr>
													<td>5万</td>
													<td>5万</td>
													<td class="tAleft">静态存量密码</td>
												</tr>
												<tr>
													<td>5万</td>
													<td>5万</td>
													<td class="tAleft">电子密码器/数字证书</td>
												</tr>
											</table>
										</li>
										<li>
											<h3 class="h3Sty01">中国农业银行</h3>
											<table class="StyTable03 wB100">
												<tr>
													<th>卡种</th>
													<th>单笔限额(元)</th>
													<th>每日限额(元)</th>
													<th>必要条件</th>
												</tr>
												<tr>
													<td rowspan="2">借记卡</td>
													<td>50万</td>
													<td>100万</td>
													<td class="tAleft">一代ukey</td>
												</tr>
												<tr>
													<td>100万</td>
													<td>500万</td>
													<td class="tAleft">二代UKEY</td>
												</tr>
												
											</table>
										</li>
										<li>
											<h3 class="h3Sty01">中国工商银行</h3>
											<table class="StyTable03 wB100">
												<tr>
													<th>卡种</th>
													<th>单笔限额(元)</th>
													<th>每日限额(元)</th>
													<th>必要条件</th>
												</tr>
												<tr>
													<td rowspan="5">借记卡</td>
													<td>300</td>
													<td>300</td>
													<td class="tAleft">静态存量密码</td>
												</tr>
												<tr>
													<td>500</td>
													<td>1000</td>
													<td class="tAleft">口令卡</td>
												</tr>
												<tr>
													<td>5万</td>
													<td>5万</td>
													<td class="tAleft">电子密码器/数字证书</td>
												</tr>
												<tr>
													<td>50万</td>
													<td>100万</td>
													<td class="tAleft">一代ukey</td>
												</tr>
												<tr>
													<td>100万</td>
													<td>500万</td>
													<td class="tAleft">二代UKEY</td>
												</tr>
											</table>
										</li>
										<li>
											<h3 class="h3Sty01">中国建设银行</h3>
											<table class="StyTable03 wB100">
												<tr>
													<th>卡种</th>
													<th>单笔限额(元)</th>
													<th>每日限额(元)</th>
													<th>必要条件</th>
												</tr>
												<tr>
													<td rowspan="4">借记卡</td>
													<td>5万</td>
													<td>10万</td>
													<td class="tAleft">一代ukey</td>
												</tr>
												<tr>
													<td>50万</td>
													<td>50万</td>
													<td class="tAleft">二代UKEY</td>
												</tr>
												<tr>
													<td>500</td>
													<td>500</td>
													<td class="tAleft">静态存量密码</td>
												</tr>
												<tr>
													<td>5000</td>
													<td>5000</td>
													<td class="tAleft">口令卡</td>
												</tr>
											</table>
										</li>
										<li>
											<h3 class="h3Sty01">招商银行</h3>
											<table class="StyTable03 wB100">
												<tr>
													<th>卡种</th>
													<th>单笔限额(元)</th>
													<th>每日限额(元)</th>
													<th>必要条件</th>
												</tr>
												<tr>
													<td rowspan="2">借记卡</td>
													<td>无限额</td>
													<td>无限额</td>
													<td class="tAleft">一代ukey</td>
												</tr>
												
												<tr>
													<td>5000</td>
													<td>5000</td>
													<td class="tAleft">静态存量密码</td>
												</tr>
											</table>
										</li>
										<!-- <li>
											<h3 class="h3Sty01">交通银行</h3>
											<table class="StyTable03 wB100">
												<tr>
													<th>卡种</th>
													<th>单笔限额(元)</th>
													<th>每日限额(元)</th>
													<th>必要条件</th>
												</tr>
												<tr>
													<td rowspan="2">借记卡</td>
													<td>5000</td>
													<td>5000</td>
													<td class="tAleft">口令卡</td>
												</tr>
												
												<tr>
													<td>100万</td>
													<td>100万</td>
													<td class="tAleft">一代ukey</td>
												</tr>
											</table>
										</li> -->
										
										<li>
											<h3 class="h3Sty01">中国民生银行</h3>
											<table class="StyTable03 wB100">
												<tr>
													<th>卡种</th>
													<th>单笔限额(元)</th>
													<th>每日限额(元)</th>
													<th>必要条件</th>
												</tr>
												<tr>
													<td rowspan="2">借记卡</td>
													<td>50万</td>
													<td>50万</td>
													<td class="tAleft">一代ukey</td>
												</tr>
												<tr>
													<td>5000</td>
													<td>5000</td>
													<td class="tAleft">短信</td>
												</tr>
											</table>
										</li>
										<!-- <li>
											<h3 class="h3Sty01">兴业银行</h3>
											<table class="StyTable03 wB100">
												<tr>
													<th>卡种</th>
													<th>单笔限额(元)</th>
													<th>每日限额(元)</th>
													<th>必要条件</th>
												</tr>
												<tr>
													<td rowspan="4">借记卡</td>
													<td>100万</td>
													<td>100万</td>
													<td class="tAleft">一代ukey</td>
												</tr>
												<tr>
													<td>100万</td>
													<td>100万</td>
													<td class="tAleft">二代UKEY</td>
												</tr>
												<tr>
													<td>5000</td>
													<td>5000</td>
													<td class="tAleft">短信</td>
												</tr>
												<tr>
													<td>5000</td>
													<td>5000</td>
													<td class="tAleft">口令卡</td>
												</tr>
											</table>
										</li> -->
										<li>
											<h3 class="h3Sty01">广发银行</h3>
											<table class="StyTable03 wB100">
												<tr>
													<th>卡种</th>
													<th>单笔限额(元)</th>
													<th>每日限额(元)</th>
													<th>必要条件</th>
												</tr>
												<tr>
													<td rowspan="2">借记卡</td>
													<td>5万</td>
													<td>5万</td>
													<td class="tAleft">口令卡</td>
												</tr>
												<tr>
													<td>100万</td>
													<td>100万</td>
													<td class="tAleft">一代ukey</td>
												</tr>
												
											</table>
										</li>
										<li>
											<h3 class="h3Sty01">中信银行</h3>
											<table class="StyTable03 wB100">
												<tr>
													<th>卡种</th>
													<th>单笔限额(元)</th>
													<th>每日限额(元)</th>
													<th>必要条件</th>
												</tr>
												<tr>
													<td rowspan="2">借记卡</td>
													<td>无限额</td>
													<td>无限额</td>
													<td class="tAleft">一代ukey</td>
												</tr>
												<tr>
											
													<td>1000</td>
													<td>5000</td>
													<td class="tAleft">口令卡</td>
												</tr>
											</table>
										</li>
										<li>
											<h3 class="h3Sty01">光大银行</h3>
											<table class="StyTable03 wB100">
												<tr>
													<th>卡种</th>
													<th>单笔限额(元)</th>
													<th>每日限额(元)</th>
													<th>必要条件</th>
												</tr>
												<tr>
													<td rowspan="3">借记卡</td>
													<td>50万</td>
													<td>50万</td>
													<td class="tAleft">一代ukey</td>
													
												</tr>
												<tr>
													<td>50万</td>
													<td>100万</td>
													<td class="tAleft">电子密码器/数字证书</td>
												</tr>
												<tr>
													<td>2万</td>
													<td>2万</td>
													<td class="tAleft">短信</td>
												</tr>
											</table>
										</li>
										<li>
											<h3 class="h3Sty01">华夏银行</h3>
											<table class="StyTable03 wB100">
												<tr>
													<th>卡种</th>
													<th>单笔限额(元)</th>
													<th>每日限额(元)</th>
													<th>必要条件</th>
												</tr>
												<tr>
													<td rowspan="1">借记卡</td>
													<td>50万</td>
													<td>50万</td>
													<td class="tAleft">一代ukey</td>
												</tr>
												
												
											</table>
										</li>
										<li>
											<h3 class="h3Sty01">浦发银行</h3>
											<table class="StyTable03 wB100">
												<tr>
													<th>卡种</th>
													<th>单笔限额(元)</th>
													<th>每日限额(元)</th>
													<th>必要条件</th>
												</tr>
												<tr>
													<td rowspan="2">借记卡</td>
													<td>无限额</td>
													<td>无限额</td>
													<td class="tAleft">一代ukey</td>
												</tr>
												
												<tr>
													<td>1万</td>
													<td>20万</td>
													<td class="tAleft">口令卡</td>
												</tr>
											</table>
										</li>
										<li>
											<h3 class="h3Sty01">平安银行</h3>
											<table class="StyTable03 wB100">
												<tr>
													<th>卡种</th>
													<th>单笔限额(元)</th>
													<th>每日限额(元)</th>
													<th>必要条件</th>
												</tr>
												<tr>
													<td rowspan="1">借记卡</td>
													<td>5万</td>
													<td>5万</td>
													<td class="tAleft">一代ukey</td>
												</tr>
												
											</table>
										</li>
										<li>
											<h3 class="h3Sty01">北京银行</h3>
											<table class="StyTable03 wB100">
												<tr>
													<th>卡种</th>
													<th>单笔限额(元)</th>
													<th>每日限额(元)</th>
													<th>必要条件</th>
												</tr>
												<tr>
													<td rowspan="2">借记卡</td>
													<td>100万</td>
													<td>100万</td>
													<td class="tAleft">一代ukey</td>
												</tr>
												
												<tr>
													<td>1000</td>
													<td>5000</td>
													<td class="tAleft">口令卡</td>
												</tr>
											</table>
										</li>
										
										<li>
											<h3 class="h3Sty01">邮政储蓄银行</h3>
											<table class="StyTable03 wB100">
												<tr>
													<th>卡种</th>
													<th>单笔限额(元)</th>
													<th>每日限额(元)</th>
													<th>必要条件</th>
												</tr>
												<tr>
													<td rowspan="3">借记卡</td>
													<td>20万</td>
													<td>20万</td>
													<td class="tAleft">口令卡</td>
												</tr>
												<tr>
													<td>200万</td>
													<td>200万</td>
													<td class="tAleft">一代ukey</td>
												</tr>
												<tr>
													<td>2万</td>
													<td>2万</td>
													<td class="tAleft">短信</td>
												</tr>
												
											</table>
										</li>
										<li>
											<h3 class="h3Sty01">渤海银行</h3>
											<table class="StyTable03 wB100">
												<tr>
													<th>卡种</th>
													<th>单笔限额(元)</th>
													<th>每日限额(元)</th>
													<th>必要条件</th>
												</tr>
												<tr>
													<td rowspan="2">借记卡</td>
													<td>2万</td>
													<td>10万</td>
													<td class="tAleft">口令卡</td>
												</tr>
												<tr>
													<td>2万</td>
													<td>10万</td>
													<td class="tAleft">电子密码器/数字证书</td>
												</tr>
												
											</table>
										</li>
										<li>
											<h3 class="h3Sty01">青岛银行</h3>
											<table class="StyTable03 wB100">
												<tr>
													<th>卡种</th>
													<th>单笔限额(元)</th>
													<th>每日限额(元)</th>
													<th>必要条件</th>
												</tr>
												<tr>
													<td rowspan="2">借记卡</td>
													<td>5万</td>
													<td>5万</td>
													<td class="tAleft">电子密码器/数字证书</td>
												</tr>
												<tr>
													<td>100万</td>
													<td>500万</td>
													<td class="tAleft">一代ukey</td>
												</tr>
											</table>
										</li>
										<li>
											<h3 class="h3Sty01">北京农商银行</h3>
											<table class="StyTable03 wB100">
												<tr>
													<th>卡种</th>
													<th>单笔限额(元)</th>
													<th>每日限额(元)</th>
													<th>必要条件</th>
												</tr>
												<tr>
													<td rowspan="1">借记卡</td>
													<td>10万</td>
													<td>10万</td>
													<td class="tAleft">电子密码器/数字证书</td>
												</tr>
											</table>
										</li>
										<li>
											<h3 class="h3Sty01">上海农商银行</h3>
											<table class="StyTable03 wB100">
												<tr>
													<th>卡种</th>
													<th>单笔限额(元)</th>
													<th>每日限额(元)</th>
													<th>必要条件</th>
												</tr>
												<tr>
													<td rowspan="2">借记卡</td>
													<td>50万</td>
													<td>100万</td>
													<td class="tAleft">一代ukey</td>
												</tr>
												
												<tr>
													<td>5000</td>
													<td>5000</td>
													<td class="tAleft">短信</td>
												</tr>
											</table>
										</li>
										<li>
											<h3 class="h3Sty01">重庆农商银行</h3>
											<table class="StyTable03 wB100">
												<tr>
													<th>卡种</th>
													<th>单笔限额(元)</th>
													<th>每日限额(元)</th>
													<th>必要条件</th>
												</tr>
												<tr>
													<td rowspan="2">借记卡</td>
													<td>50万</td>
													<td>50万</td>
													<td class="tAleft">一代ukey</td>
												</tr>
												
												<tr>
													<td>5万</td>
													<td>5万</td>
													<td class="tAleft">短信</td>
												</tr>
											</table>
										</li>
									</ul>
                                      <form action="/portal/cash/WYrecharge" method="post" name="FormRecharge" id="FormRecharge" target='_blank' >
									<div class="StyBt1px mB30">
										<h3 class="IDzhanghuTX_title">填写充值金额</h3>
										
										<ul class="IDzhanghuTX01 ID_wanyin">
											<li><span class="l">可用余额：</span>
												<div class="l">
													<span class="c_cheng IDketixianE"><b id="available">0.00</b><em>元</em></span>
												</div></li>
											<li style="display:none"><input type="text" name="card_id" id="card_id" autocomplete='off' readonly required value='0801040000' /><input type="text" name="type" id="type" autocomplete='off' readonly required value='1' /></li>
											<li><span class="l">充值金额：</span>
												<div class="l">
													<label class="pR StyIn-t01 L_ico"> <i
														class="ico2 ICOfS01_21"></i> <!-- <input type="text"
														id="rechargeAmt" placeholder="请输入充值金额"
														txtype="FloatZ1000W"> --><input type="text" id="money"  name="money" autocomplete='off' placeholder='请输入充值金额' txtype='FloatZ1000W'  required  data-rule-gt='true' data-gt='0'  maxlength='10'  /> <i>元</i> <span class="new_tishi">单笔充值金额不得少于100元</span>
													</label>
												</div></li>
												<li><span class="l">验证码：</span><div class="l"><label class="pR StyIn-t01 StyIn-t02 L_ico"><input type="text" name="yzm" id="yzm" autocomplete='off' required maxlength='4' /></label><span style="margin-left:10px;"><img width="100" height="30" src="/portal/user/checkcode"  class="m" alt="如果看不清,请点击我!" title="如果看不清,请点击我!""  onclick="this.setAttribute('src','/portal/user/checkcode?x='+Math.random());"></span></div></li>
											<li><span class="l">充值费用：</span>
												<div class="l">
													<span class="c_666 IDketixianE"><b id="sxf" style="display:none;">0.00</b></span><span
														class="IDketixianE_f12">已开启充值无忧服务，手续费用由砖头网为您支付</span>
												</div></li>
										</ul>
										<div class="autorow error" id="sign_val"><div align="left"><hoontag:Message /></div></div>
									</div>
									
									<div class="StyBt1px IDzhanghuTX02 hide">
											<button id="" name="" type="submit" value="确认充值" class="botton-1 confirmbtn lan">确认充值</button>
									</div>
									<div class="StyBt1px IDzhanghuTX02">
										<div  class="success_btn lan" onclick="huoquyzm()">确认充值</div>
									</div>
									</form>
									<dl class="IDzhanghuTX03">
										<dt>温馨提示</dt>
										<dd>1. 为了您的资金安全，您需要在充值之前开通托管账户，完成实名认证，并设置交易密码；</dd>
				<dd>2. 由于第三方支付限制及银行额度限制，单笔充值额度以各银行限额为准；<%-- <span class="card_tishi"><a href="<%=systemPathWeb  %>notice_details.jsp?OID=4a22abc03fa81f660114a8c40ec88bbb">查看支持的银行卡及限额</a></span>--%></dd> 
				<dd>3. 严禁利用充值功能进行信用卡套现、转账、洗钱等行为，一经发现，资金将退回原卡并封停账号30天；</dd>
				<dd>4. 判定为风险、可疑交易的资金将通过第三方支付平台退回原卡，到账时间以发卡行通知为准，由此可能产生的费用由个人承担；</dd>
				<dd>5. 港澳台用户需用绑定银行卡持网银充值，其他方式敬请期待；</dd>
				<dd>6. 以上最终解释权归砖头网所有。</dd>
									</dl>
								</li>
								
								 <%-- <li class="IDzhanghu_li hide" tag-func-id="4" tags="label"
									tag-event="click" auto-one="false">
									<iframe src='/Recharge_iframe.jsp' width="100%" height="1150" id="iframepage_new" name="iframe_new" frameborder="0" scrolling="no" marginheight="0" marginwidth="0"></iframe>
                       
								</li> --%> 
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
		//jquery.validate
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
      
      $.ajax({
        dataType:"json",
        url:"/portal/cash/cardmsg",
        success: function(result){
        	console.log(result);
        	$("#available").text((result.dataValue.useMoney).toFixed(2));
        },
        error:function(result){
        	alert("发生错误 ");
        }
    });
      
      
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
	        		        	alert("发生错误 ");
	        		        }
	        		    });
	        		}
	        	
	        	})
	        	
	        	
	        },
	        error:function(result){
	        	alert("发生错误 ");
	        }
	    });
      
	})

	//
	$(function() {
		if (window.addEventListener) {
			$('#money').bind(
					'input',
					function() {//给文本框绑定input事件
						if ($('#money').val() != "") {
							if($("#money").val()>0){
								$('.shouxuF').css("display", "block")
								/* $('#sxf')[0].innerHTML = ""
										+ ($('#money').val() * 0.0015).toFixed(2)
										+ ""; */
							}
						}else{
							/* $('#sxf')[0].innerHTML = "0.00"; */
						}
					});
		} else if (window.attachEvent) {
			$('#money').bind(
					'propertychange',
					function() {//给文本框绑定input事件
						if ($('#money').val() != "") {
							if($("#money").val()>0){
							$('.shouxuF').css("display", "block")
							/* $('#sxf2')[0].innerHTML = "";
										+ ($('#money').val() * 0.0015).toFixed(2)
										+ ""; */
							}
						}else{
							/* $('#sxf')[0].innerHTML = "0.00"; */
						}
					});
		}
		
		var url=location.href;
		//alert("url"+url)
		var lastStr=url.substring(url.length-1);
		if(lastStr==1){
			//alert($(".my_loantop a").eq(1).text());
			$(".my_loantop a").eq(1).addClass('active').siblings().removeClass('active');
			$('.loanrecord01').eq(1).show().siblings().hide();
		}

	});


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
