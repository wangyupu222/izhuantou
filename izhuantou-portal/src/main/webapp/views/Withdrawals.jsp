<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/include/url.jsp" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>砖头网-提现</title>
<link rel="stylesheet" type="text/css" href="/css/style-common.css">
<link rel="stylesheet" type="text/css" href="/css/personalcenter.css">
<link rel="stylesheet" type="text/css" href="/css/preother.css?v=114">
<link rel="stylesheet" type="text/css" href="/css/CapitalDetailed.css">

<Link rel="shortcut icon" href="<%=pathUrl %>/images/zhuan.ico" />
<script type="text/javascript" src="/js/jquery-1.10.1.min.js"></script>
<script src="/js/com.js"></script>
<script language="javascript" src="/js/menu.js"></script>
<script src="/js/resubmit.js"></script>
<script src="/js/base.js"></script>
<script src="/js/paging.js"></script>
<!--日期时间-->
<script language="javascript" type="text/javascript" src="/My97DatePicker/WdatePicker.js"></script>
<style type="text/css">
.autorow{ position:relative;width:480px;}
/* .shouxuF{display:none} */
.tisxf{height:30px}
.shouxuF .divbox em{font-size:14px;color:#999;margin-left:20px;}
.divbox #sxf i{
 	color: #999;
    font-style: normal;
}
span.error {
    position:absolute;
    top:0px;
    left:360px;
    color: #C00;    
    line-height: 30px;
    display: block;
}
.yzm span.error {   
    left:360px;    
}
.tisxf span i{
    color: #f5a100;
    font-style: normal;
}

.hide_liu{  width: 100%;  height: 500px;  text-align: center;  background:#fff url("images/Withdraw_liu.jpg"); background-repeat: no-repeat; background-position:center center; display: none;  }
.autorow02{width:736px;}
.autorow02 .tixian_tishi{float:right;color:#999;font-size:14px;margin-right:180px;}
.autorow.yzm{width:620px;}
.cardyonglist{
margin:0;
}
.IDzhanghuTX01{
float:left;
margin-left:50px;
}
.card01{
margin-top:0px;
}
.box-quan {
    margin-top: 0px;
}
.card_box{
margin-bottom:40px;
}
.autorow02 .tixian_all{
display: inline-block;
width:76px;
height:30px;
color:#fff;
background-color:#55b0fd;
margin-left: 10px;
text-align:center;
cursor: pointer;
border-radius: 4px;
}
.autorow02 .tixian_all:hover{
background-color:#53a6ec;
}
.success_btn{
width: 298px;
margin: 0 auto;
height: 44px;
border: 0;
background: #55b0fd;
border-radius: 5px;
color: #fff;
font: 16px/36px "微软雅黑";
border: 0;
line-height:44px;
cursor: pointer;

}
#sign_val{
 color:#f00;
}
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
		        	console.log(result);
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
       <p>请您在新打开的第三方账户管理账户内完成提现<br>交易完成前请不要关闭此窗口<br>完成提现后请点击下面的按钮</p>
       <div class="prompt_btn"><a href="Withdrawals.jsp">完成操作</a></div>
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
    	<a >当前位置</a><span>:</span><a >个人中心</a><span>&gt;</span><a >我的资金</a><span>&gt;</span><a >提现</a>
        </div>
        <div class="con xqdetial">
    	<%@ include file="left_percenter.jsp" %>
        
    	<div class="xqdeleft my_loan">
        	<div class="my_loantop"><a href="#" class="active">账户提现</a><a href="#">提现记录</a><span class="L2">&nbsp;</span></div>
            <div class="clear"></div>
            <div>
            <!--账户提现-->
            <div class="loanrecord01">
            <form action="/portal/cash/withdrawalmonry" method="post" name="FormWithdrawals" id="FormWithdrawals" target='_blank'  >
            <div class="card_box"> 
            <ul style="float:left;" class="IDzhanghuTX01 ID_kuaijie">
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
                
												</li>
										</ul>
            <div style="clear:both;"></div>
            </div>
            <div class="autovalue ">
            <div class="autorow autorow01"><label>可提现金额：</label><span><em id="yue_money"></em>元</span><span>（免费提现额度为：<em id="FreeAmount">1000.00</em>元）</span>
            <span class="limit_tishi"><img src="<%=pathUrl %>/images/dual_system/question-mark.png"><div class="limit-module"><p>免费提现额度为用户参与出借的本金及其利息、特权收益之和。<br>充值但没有参与出借的部分需要收取手续费。</p></div></span>
            </div>
            <div class="autorow autorow02"><label>提现金额：</label><input type="text" name="money" id="money" autocomplete='off' placeholder='提现金额不得少于100元' txtype='FloatZ1000W'	required  data-rule-gt='true' data-gt='0'  maxlength='10' /><span class="tixian_all">全部提现</span><span style="clear:both;"></span></div>
            <div class="autorow yzm"><label>验证码：</label><input type="text" name="yzm" id="yzm" autocomplete='off' required minlength='4' maxlength='4'" /><span><img width="76" height="30" src="/portal/user/checkcode"  class="m" alt="如果看不清,请点击我!" title="如果看不清,请点击我!""  onclick="this.setAttribute('src','/portal/user/checkcode?x='+Math.random());"></span></div>
            <div class="autorow tisxf"><div class="shouxuF"><label>提现费用：</label><div class="divbox"><span ><i class="SXF-Amount">0.00</i>元</span>
            <span class="expenses_tishi"><img src="<%=pathUrl %>/images/dual_system/question-mark.png"><div class="expenses-module"><p>扣除手续费=（提现金额-免费提现额度）*0.3%，由第三方富友支付收取</p></div></span>
            </div></div></div>
            <div class="autorow"><label>到账金额：</label><div class="divbox"><span><em id="sxf">0.00<i>元</i></em></span></div></div>
           <div class="autorow error" id="sign_val"><div align="center"><hoontag:Message /></div></div>
            <div class="autorowbtn hide"><button id="" name="" type="submit" value="申请提现" class="botton-1 confirmbtn">申请提现</button></div>
            <div class="autorowbtn"><div  class="success_btn lan" onclick="huoquyzm()">申请提现</div></div>
            </div>
            </form>
            <div class="wxts">
            	<div class="wxtstit"><%-- <img src="<%=pathUrl %>/images/light.png"> --%><span>温馨提示</span></div>
                <div class="clear"></div>
                <div class="wxtscon">
                1. 为了您的资金安全，您需要在充值之前开通第三方资金托管账户，完成实名认证，并设置交易密码；<br/>
				2. 由于第三方支付富友及银行额度限制，提现额度以各银行限额为准，详情请咨询您的发卡行；<br/>
				3. 收到您的提现申请后，富友支付将在T+1日内（双休日或法定节假日顺延）处理完您的提现申请，请您合理安排提现时间并注意查收；<br/>
				4. 如果提现金额没有及时到账，请联系砖头网客服400-900-9677；<br/>
				5. 以上最终解释权归砖头网所有。<br/>
                </div>
            </div>
            </div>
            
            <!--提现记录-->
            <div class="loanrecord01 hide">
            
             <!--<div class="zjclass"><span>提现状态</span><a href="#" class="active">全部</a><a href="#">提现成功</a><a href="#">处理中</a><a href="#">已失败</a></div>-->
            <!--<div class="timefw"><span>时间范围</span><span><input id="d5221"  type="text" onFocus="var d5222=$dp.$('d5222');WdatePicker({onpicked:function(){d5222.focus();},maxDate:'#F{$dp.$D(\'d5222\')}'})"/>
至
<input id="d5222"  type="text" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'d5221\')}'})"/></span><a href="#" onclick="">最近7天</a><a href="#">最近30天</a><a href="#">最近90天</a><a href="#">最近1年</a></div>-->
            <div class="loanrecord">
            	<div class="mytzcon">
            		<div class="rowbox04 tit"><span>提现时间</span><span>提现单号</span><span>提现金额</span><!-- <span>提现手续费</span> --><span>状态</span></div>
                	<div class="hide_liu Withdraw_liu"></div>
                </div>
                <!-- <div class="nonemore">没有更多记录</div> -->
            	
            	<!--分页-->
            	<div id="page" class="page_div"></div>
                <!--分页结束-->
            </div>
            </div>
            </div>
            
            <!--历史充值-->
            <div class="hide">
          
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
<!--<script src="/js/tabmenu.js"></script>-->
<!--验证表单-->
<script src="/js/jquery.validate.js"></script>
<script>
$(function(){
    //jquery.validate
	$("#FormWithdrawals").validate({
		submitHandler: function(form) {
			//验证通过后 的js代码写在这里
			//事件只能点击一次
				/* $(".confirmbtn").click(function(){
					$(this).attr("disabled","disabled");
				}); */
				validataForm("confirmbtn");
			form.submit();
		}
	})
	
	$(".tixian_all").click(function(){
		var oMoney=$("#yue_money").text();
		var Oinput = Number($('#money').val());//输入的金额
		var FreeAmount=Number($('#FreeAmount').text());
		var SXFAmount=(oMoney-FreeAmount)*0.003;
		if(SXFAmount>0 && SXFAmount<0.01){
			 SXFAmount=0.01;
		 }
		$("#money").val(oMoney);
		if(oMoney<100){
			$(".shouxuF").find(".divbox .SXF-Amount").text("0.00");
			$('#sxf')[0].innerHTML ="不可提现 ";
		}else{
			if(oMoney>1000000){
				$('#money').val(1000000);
          		 Oinput = Number($('#money').val());
          		SXFAmount=(1000000-FreeAmount)*0.003;
          		if(SXFAmount>0 && SXFAmount<0.01){
           		 SXFAmount=0.01;
           	 	}
			}
			if(oMoney>FreeAmount){
		   		 $(".shouxuF").find(".divbox .SXF-Amount").text(SXFAmount.toFixed(2));
		   		 $('#sxf')[0].innerHTML =($('#money').val()-SXFAmount).toFixed(2)+"<em style='color:#999;'>元</em>";
		   	 }else{
		   		 $(".shouxuF").find(".divbox .SXF-Amount").text("0.00");
		   		 $('#sxf')[0].innerHTML =($('#money').val()-0).toFixed(2)+"<em style='color:#999;'>元</em>";
		   	 }
		}
		/* if(oMoney>=2){
			$('#sxf')[0].innerHTML =($('#money').val()-2).toFixed(2)+"元";
		}else{
			$('#sxf')[0].innerHTML ="不可提现 ";
		} */
		
	})
	//充值控制数值JS
    funcData.eChange.COM();	
})

//资金流水留白
$(function(){
	$.ajax({
        dataType:"json",
        url:"/portal/cash/cardmsg",
        success: function(result){
        	console.log(result);
        	$("#available").text((result.dataValue.useMoney).toFixed(2));
        	$("#yue_money").text((result.dataValue.useMoney).toFixed(2));
        	$("#FreeAmount").text((result.dataValue.freeline).toFixed(2));
        	$(".card_tishi span").text(result.dataValue.mobile);
        },
        error:function(result){
        	console.log("发生错误 ");
        }
    });
    	$.ajax({
	        dataType:"json",
	        url:"/portal/cash/recharge",
	        data:{currentpage:1,
	        	state:'withdrawals'},
	        success: function(result){
	        	console.log(result);
	        	var Pagination=result.dataValue.Pagination;
	        	var RechargeList=result.dataValue.RechargeList;
	        	
	        	if(Pagination.totalNumber<=0){
	        		$(".Withdraw_liu").css("display","block");
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
	        		        	state:'withdrawals'},
	        		        success: function(result){
	        		        	console.log(result);
	        		        	$(".mytzcon .rowbox04.value").remove();
	        		        	var RechargeList=result.dataValue.RechargeList;
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
        
    
    var url=location.href;
	//alert("url"+url)
	var lastStr=url.substring(url.length-1);
	if(lastStr==1){
		//alert($(".my_loantop a").eq(1).text());
		$(".my_loantop a").eq(1).addClass('active').siblings().removeClass('active');
		$('.loanrecord01').eq(1).show().siblings().hide();
	}
})

$(function () {
	/* if(window.addEventListener){  
		$('#money').bind('input', function () {//给文本框绑定input事件
	        if ($('#money').val() != "") {
	        	$('.shouxuF').css("display","block")
	        	if($('#money').val()>=100)
	        		{
	        	
	        		$('#sxf')[0].innerHTML =($('#money').val()-2).toFixed(2)+"元";
	        		
	        		}else
	        			{
	        			$('#sxf')[0].innerHTML ='';
	        			}
	        }
	    }); 
    }else if(window.attachEvent){   
    	$('#money').bind('propertychange', function () {//给文本框绑定input事件
            if ($('#money').val() != "") {
            	$('.shouxuF').css("display","block")
            	if($('#money').val()>=100)
            		{
            		$('#sxf')[0].innerHTML =($('#money').val()-2).toFixed(2)+"元";
            		
            		}else
            			{
            			$('#sxf')[0].innerHTML ='';
            			}
            }
        });  
    } */
	
	
	$("#money").keyup(function(){
		var Oinput = Number($('#money').val());//输入的金额
    	var input_val = $('#money').val();
		var FreeAmount=Number($('#FreeAmount').text());
    	var zhye = Number($('.autorow01 #yue_money').text());//剩余可投
    	//alert(sykt);
    	if(input_val!=""){
    		if(Oinput<100){
        		/*  $(".tixian_tishi").css("display","block"); */
        		if(Oinput>zhye){
        			$('#money').val(zhye);
           		 Oinput = Number($('#money').val());
        		}
        	}else if(Oinput>zhye){
        		 $('#money').val(zhye);
        		 Oinput = Number($('#money').val());//输入的金额
        	}
    		if(Oinput>100){
        		/* $(".tixian_tishi").css("display","none"); */
    			if(Oinput>1000000){     //  2017-10-04 修改，增加提现单笔不得超过100万元 
        			$('#money').val(1000000);
           		 Oinput = Number($('#money').val());
        		}
        	}
    	}
    	
    	if ($('#money').val() != "") {
        	$('.shouxuF').css("display","block");
        	 var SXFAmount=(Oinput-FreeAmount)*0.003;
        	 if(SXFAmount>0 && SXFAmount<0.01){
        		 SXFAmount=0.01;
        	 }
        	 if($('#money').val()>=100){
        		 if(Oinput>FreeAmount){
            		 $(".shouxuF").find(".divbox .SXF-Amount").text(SXFAmount.toFixed(2));
            		 $('#sxf')[0].innerHTML =($('#money').val()-SXFAmount).toFixed(2)+"<em style='color:#999;'>元</em>";
            	 }else{
            		 $(".shouxuF").find(".divbox .SXF-Amount").text("0.00");
            		 $('#sxf')[0].innerHTML =($('#money').val()-0).toFixed(2)+"<em style='color:#999;'>元</em>";
            	 }	
        		}else
        			{
        			$('#sxf')[0].innerHTML ='0.00<em style="color:#999;">元</em>';
        			}
        }else{
        	$('#sxf')[0].innerHTML ='0.00<em style="color:#999;">元</em>';
        	$(".shouxuF").find(".divbox .SXF-Amount").text("0.00");
        }
		
		
	})
    
});

$(function(){
	$.ajax({
        type: "post",
        url: "/portal/cash/myCashmessage",
        dataType: "json",
        success: function(result){
        console.log(result.dataValue);
        var CodeStr='';
        if(result.dataValue.bankCode=='0102'){
        	CodeStr='<img id="cardImage" src="<%=pathUrl %>/images/card_gs.png">';
        }else if(result.dataValue.bankCode=='0105'){
        	CodeStr='<img id="cardImage" src="<%=pathUrl %>/images/card_jh.png">';
        }else if(result.dataValue.bankCode=='0103'){
        	CodeStr='<img id="cardImage" src="<%=pathUrl %>/images/card_ny.png">';
        }else if(result.dataValue.bankCode=='0104'){
        	CodeStr='<img id="cardImage" src="<%=pathUrl %>/images/card_zg.png">';
        }else if(result.dataValue.bankCode=='0303'){
        	CodeStr='<img id="cardImage" src="<%=pathUrl %>/images/card_gd.png">';
        }else if(result.dataValue.bankCode=='0302'){
        	CodeStr='<img id="cardImage" src="<%=pathUrl %>/images/card_zx.png">';
        }else if(result.dataValue.bankCode=='0304'){
        	CodeStr='<img id="cardImage" src="<%=pathUrl %>/images/card_hx.png">';
        }else if(result.dataValue.bankCode=='0305'){
        	CodeStr='<img id="cardImage" src="<%=pathUrl %>/images/card_ms.png">';
        }else if(result.dataValue.bankCode=='0306'){
        	CodeStr='<img id="cardImage" src="<%=pathUrl %>/images/card_gf.png">';
        }else if(result.dataValue.bankCode=='0307'){
        	CodeStr='<img id="cardImage" src="<%=pathUrl %>/images/card_pa.png">';
        }else if(result.dataValue.bankCode=='0308'){
        	CodeStr='<img id="cardImage" src="<%=pathUrl %>/images/card_zs.png">';
        }else if(result.dataValue.bankCode=='0309'){
        	CodeStr='<img id="cardImage" src="<%=pathUrl %>/images/card_xy.png">';
        }else if(result.dataValue.bankCode=='0310'){
        	CodeStr='<img id="cardImage" src="<%=pathUrl %>/images/card_pf.png">';
        }else if(result.dataValue.bankCode=='0301'){
        	CodeStr='<img id="cardImage" src="<%=pathUrl %>/images/card_jt.png">';
        }else if(result.dataValue.bankCode=='0319'){
        	CodeStr='<img id="cardImage" src="<%=pathUrl %>/images/card_hs.png">';
        }else if(result.dataValue.bankCode=='0403'){
        	CodeStr='<img id="cardImage" src="<%=pathUrl %>/images/card_yz.png">';
        }
        $(".sp-img").append(CodeStr);
        $(".sp-name").append(result.dataValue.nameCN);
        $(".sp-num").append(result.dataValue.bankNumber);      
            
        },
		error:function(result){
    	}
    });
})

Number.prototype.toFixed=function (d) { 
    var s=this+""; 
    if(!d)d=0; 
    if(s.indexOf(".")==-1)s+="."; 
    s+=new Array(d+1).join("0"); 
    if(new RegExp("^(-|\\+)?(\\d+(\\.\\d{0,"+(d+1)+"})?)\\d*$").test(s)){
        var s="0"+RegExp.$2,pm=RegExp.$1,a=RegExp.$3.length,b=true;
        if(a==d+2){
            a=s.match(/\d/g); 
            if(parseInt(a[a.length-1])>4){
                for(var i=a.length-2;i>=0;i--){
                    a[i]=parseInt(a[i])+1;
                    if(a[i]==10){
                        a[i]=0;
                        b=i!=1;
                    }else break;
                }
            }
            s=a.join("").replace(new RegExp("(\\d+)(\\d{"+d+"})\\d$"),"$1.$2");

        }if(b)s=s.substr(1); 
        return (pm+s).replace(/\.$/,"");
    }return this+"";

};

//配置错误提示的节点，默认为label，这里配置成 span （errorElement:'span'）
$.validator.setDefaults({
	errorElement:'span'
});

//配置通用的默认提示语
$.extend($.validator.messages, {
	required: '必填',
    equalTo: "请再次输入相同的值",
    maxlength: $.validator.format(""),//Please enter no more than {0} characters.
    minlength: $.validator.format("验证码不能少于4位"),
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
	var gt = /^([1-9]{1}\d{2,})+(\.[0-9]{1,2})?$/;
	return this.optional(element) || (gt.test(value));
}, "请输入大于100的正确数字");
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
