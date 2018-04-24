<%@ page contentType="text/html; charset=UTF-8"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>砖头网-我的出借</title>
<link rel="stylesheet" type="text/css"
	href="/css/style-common.css">
<link rel="stylesheet" type="text/css"
	href="/css/personalcenter.css">
<link rel="stylesheet" type="text/css"
	href="/css/preother.css">
<Link rel="shortcut icon" href="/images/zhuan.ico" />
<script type="text/javascript"
	src="/js/jquery-1.10.1.min.js"></script>

<script src="/js/com.js"></script>
<script language="javascript" src="/js/menu.js"></script>
<!--<script src="/js/1-6-10.esl.js"></script>-->
<style>
table.centerzqzr tr.rowbox08{ height:55px; line-height:55px; font-size:14px;}
table.centerzqzr td{ text-align: center; border-bottom:1px solid #eaeaea;}
table.centerzqzr td.transfer_ht a{ color:#55b0fd;}
table.centerzqzr td.transfer_tates a{ display:block; width:80px; height:30px; line-height:30px; text-align:center; margin:auto; margin-top:6px; border-radius:3px; background-color:#eeeeee; color:#fff; }
table.centerzqzr td.transfer_tates a.active{background:#55b0fd; color:#fff; cursor:pointer;}
table.centerzqzr td.transfer_tates a:hover{ background:#3291f0;}
.zhuang_tk span{
    display: inline-block;
    width: 87px;
    height: 28px;
    background-color: #55b0fd;
    text-align: center;
    margin: 0 auto;
    margin-left: 54px;
    line-height: 28px;
    border-radius: 5px;
    color:#fff;
}
.cd-popup-container1{
width:612px;
margin:165px auto;
}
tr.rowbox02 td {
    border-bottom: none;
}
.hide_liu {
	width: 100%;
	height: 500px;
	text-align: center;
	background: #fff url("images/zhuanrang.jpg") no-repeat center;
	display: none;
}
</style>
</head>
<body>
<!--     体验金提示弹出框      -->
<div class="cd-popup1 Tyj_popup">
<div class="cd-popup-container1">
<!--协议列表-->
	<div class="comnei ">
        <a class="close cd-popup-close"></a>
        <p class="commit-1">体验金</p>
        <div class="ty_conter">
        <div class="conter_text">您需要出借一次非新手类产品以获得该收益，完成出借后，收益会在出借到期时一并到账。</div>
        <div class="conter_btn"><a href="projects.jsp">好，去出借</a></div>
        <div class="conter_zhu hide" style="display:none;">注：完成出借后，该收益会在出借到期时一并到账</div>
        </div>
    </div>

       
        
     </div>
</div>

<% try{ %>
	<div class="indexz">
		<!--common header-->
		<%@ include file="header.jsp"%>
		<!--common end-->
		<div class="main minhh">
			<div class="con personcon">
				<div class="smallnav">
					<a>当前位置</a><span>:</span><a>个人中心</a><span>&gt;</span><a>我的特权</a><span>&gt;</span><a>体验金</a>
				</div>
				<div class="con xqdetial">
					<%@ include file="left_percenter.jsp"%>

					<div class="xqdeleft my_loan">
						<div class="my_loantop">
							<a href="#" class="active">体验金</a><span class="L1">&nbsp;</span>
						</div>
						<div class="clear"></div>
						<div class="loanrecord">

						  <div class="loanrecord01">
						  <div class="tyj_conMain">
						  <div class="tyj_Capital">
						  <div class="tyj_CapitalTop">
						  <div class="tyMoney">可用体验余额:<span>0.00</span>元</div>
						  <div class="tyMoneyList">
						  <div class="MoneyList_con">
						  <%-- <div class="MoneyList_Box"><h3><%=request.getAttribute("tyje")%>元</h3><p>体验中的金额</p></div>
						  <div class="MoneyList_Box"><h3><%=request.getAttribute("dssy")%>元</h3><p>待收收益</p></div>
						  <div class="MoneyList_Box MoneyList_Box2"><h3><%=request.getAttribute("yssy")%>元</h3><p>已收收益</p></div>
						  <div class="clear"></div> --%>
						  <table class="TYMoney_list" width="100%" cellpadding="0" cellspacing="0" align="center">
                    <tr class="rowbox02 tit">
                    <td width="25%">体验中的金额</td>
                    <td width="50%">待收收益</td>
                    <td width="25%">已收收益</td>
                    </tr>
                    <tr class="rowbox02 value">
                    <td>0.00元</td>
                    <td>0.00元</td>
                    <td>0.00元</td>             
                    </tr>
                    
                    </table>
						  </div>
						  </div>
						  </div>
						  
						  <div class="Start_experience"><a href="/portal/detial/tzdetailsnew.jsp">立即体验</a></div>
						  
						  </div>
						  <div class="cutOFF hide"></div>
		<!-- 	*********************** 体验记录   *****************************         -->
		<div class="TYrecordMain">
		<div class="TYrecord_tit">体验记录：</div>
		<div class="TYrecord_con">
		<table class="TYrecord_list" width="100%" cellpadding="0" cellspacing="0" align="center">
                    <thead>
                    <tr class="rowbox02 tit">
                    <td width="20%">金额</td>
                    <td width="20%">体验时间</td>
                    <td width="20%">到期时间</td>
                    <td width="20%">预期收益</td>
                    <td width="20%">状态</td>
                    </tr>
                    </thead>
                    <tbody>
                    </tbody>
                    
                    </table>
		</div>
		</div>
		<!-- 	*********************** 体验记录end   *****************************         -->
		
		<div class="cutOFF cutOFF02 hide"></div>
		<div class="TYJkindly_reminder">
		<h3>温馨提示:</h3>
		<p>1、体验金出借后产生的收益归用户所有，体验金的本金部分由平台收回。<br>
			2、如需将体验金收益提现，用户需进行一次正常的出借行为（不包含新手类产品），待出借期满后，体验金收益与出借收益一并到账。<br>
			3、最终解释权归砖头网所有，如有疑问请联系客服400-900-9677。</p>
		</div>
		
						  </div>		
						  </div>
						<div class="clear"></div>
					</div>

				</div>
			</div>
		</div>
		<!-----------------中间部分结束---------------->
		<!--bottom common-->
		<%@ include file="footer.jsp"%>


	</div>
<%}catch(Exception e){
	response.sendRedirect("error.jsp");
    return;  
} %>
</body>

<script src="/js/tabmenu.js"></script>
<script>
$(function(){
	
	$(document).on('click','.tixian_btn',function(event){
        event.preventDefault();
        $('.cd-popup1').addClass('is-visible1');
        //$(".dialog-addquxiao").hide()
    });
	
	$.ajax({
        dataType:"json",
        url:"/portal/mytq/experience",	
        success: function(result){
        	console.log(result);
        	$(".tyMoney span").text(result.dataValue.kyye);
        	$(".TYMoney_list .rowbox02.value").find("td").eq(0).text(result.dataValue.tyje+"元");
        	$(".TYMoney_list .rowbox02.value").find("td").eq(1).text(result.dataValue.dssy+"元");
        	$(".TYMoney_list .rowbox02.value").find("td").eq(2).text(result.dataValue.yssy+"元");
        	if(result.dataValue.istyj=='true' || result.dataValue.iscj=='true'){
        		$(".Start_experience").css("background-color","#ababab");
        		$(".Start_experience").append('<a href="tzdetailsnew.jsp">立即体验</a>')
        	}else if(result.dataValue.istyj=='false' || result.dataValue.iscj=='false'){
        		$(".Start_experience").append('<a href="tzdetailsnew.jsp">立即体验</a>')
        	}else if(result.dataValue.istyj=='new' || result.dataValue.iscj=='new'){
        		$(".Start_experience").css("background-color","#ababab");
        		$(".Start_experience").append('<a href="tzdetailsnew.jsp">立即体验</a>')
        		
        	}
        	if(result.dataValue.istyj=='true'){
        		var str='<tr class="rowbox02 value"><td>18888元</td><td>'+result.dataValue.tysj+'</td><td>'+result.dataValue.dqsj+'</td><td>'+result.dataValue.tyjsy+'元</td>';
        		if(result.dataValue.isdq=='true'){
        			if(result.dataValue.isjs=='true'){
        			str+='<td class="zhuang_tk">已完成</td>';
        			}else if(result.dataValue.isjs=='false'  && result.dataValue.isxs=='false'){
        				str+='<td class="zhuang_tk TYJzhuang_tk" style="color:#ff8a01;">收益回款中<i><img src="/images/dual_system/TYJwen_icon.png"><div class="tyj_tishibox"><p>待产品出借期满后，体验金收益与出借收益一并到账，其体验记录状态将由“收益回款中”变为“已完成”。</p></div></i></td>';
        			}else{
        				str+='<td class="zhuang_tk"><span class="tixian_btn">提现</span></td>';
        			}
        			
        		}else{
        			str+='<td class="zhuang_tk ">体验中</td>';
        		}
        		str+='</tr>';
        		$(".TYrecord_list tbody").append(str);
        	}
        	
        	
        	
        },
        error:function(result){
        	console.log("发生错误 ");
        }
    });
	
})

</script>
</html>
