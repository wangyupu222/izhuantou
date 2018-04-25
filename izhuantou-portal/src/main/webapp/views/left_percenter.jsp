﻿<%@ page contentType="text/html; charset=UTF-8" %>
<%
String url=request.getRequestURL().toString();
String url2=url.substring(url.indexOf("views"),url.length());
String url3=url2.substring(url2.indexOf("/"),url2.length()-4);
%>
<div class="localurl hide " id="localurl"><%=url3 %></div>
<div class="xqderight aboutcon centerleft">
<script type="text/javascript">
$(function(){
	/* Banner  */
	$.ajax({
        type: "get",
        url: "/portal/personal/num",
        dataType: "json",
        success: function(result){
        	$(".accview-msg-num").text(result.countMessage)
        	console.log(result);
        	
        },
		error:function(result){    	
    	}
    });
});
</script>
        	<!--<div class="menuDivTop"><a href="personalcenter"><span>我的鼎治金融平台</span></a></div>-->
        <div class="financial-cal-menu">
        
        <!--5-->
            <div class="financial-cal-menu-item menu05">
                <span class="financial-cal-menu-title  " data-type="financial-cal-menu-title">用户中心<em class="ver-ico-url "></em></span>
                <div class="financial-cal-menu-list " data-type="financial-cal-menu-listof" >
                    <a href="/portal/personal/personalcenter" class="<%if(request.getRequestURL().toString().indexOf("personalcenter")>0){%>active<%}%>"><span><strong><i></i></strong>中心首页</span></a>
                    <a href="/portal/personal/Securitypre" class="<%if(request.getRequestURL().toString().indexOf("Securitypre")>0){%>active<%}%>"><span><strong><i></i></strong>安全中心</span></a>
                    <%-- <a href="MyCard" class="<%if(request.getRequestURL().toString().indexOf("MyCard")>0 || request.getRequestURL().toString().indexOf("addbankcard")>0){%>active<%}%>"><span><strong><i></i></strong>我的银行卡</span></a> --%>
                     <a href="/portal/personal/MessageNotification" class="<%if(request.getRequestURL().toString().indexOf("MessageNotification")>0 || request.getRequestURL().toString().indexOf("MessageHistoryNotification")>0){%>active<%}%>"><span><strong><i></i></strong>我的消息</span>
                    <em class="accview-msg-num"><%=request.getAttribute("countMessage")%></em>   </a>          
                </div>
            </div>
            <!--5-end-->
        
            <!--item1-->
            <div class="financial-cal-menu-item menu01">
                <span class="financial-cal-menu-title " data-type="financial-cal-menu-title">我的资金<em class="ver-ico-url"></em></span>
                <div class="financial-cal-menu-list  " data-type="financial-cal-menu-listof" >
                    <%-- <a href="personalcenter" class="<%if(request.getRequestURL().toString().indexOf("personalcenter")>0){%>active<%}%>"><span><strong><i></i></strong>资金总览</span></a> --%>                    
                    <a href="/portal/cash/CapitalDetailed" class="<%if(request.getRequestURL().toString().indexOf("CapitalDetailed")>0){%>active<%}%>"><span><strong><i></i></strong>资金流水</span></a>
                    <a href="/portal/cash/MyCard" class="<%if(request.getRequestURL().toString().indexOf("MyCard")>0 || request.getRequestURL().toString().indexOf("addbankcard")>0){%>active<%}%>"><span><strong><i></i></strong>银行卡</span></a>
                    <a href="/portal/cash/Recharge_new" class="<%if(request.getRequestURL().toString().indexOf("Recharge_new")>0 || request.getRequestURL().toString().indexOf("Recharge_new2")>0){%>active<%}%>"><span><strong><i></i></strong>充值</span></a>
                    <a href="/portal/cash/Withdrawals" class="<%if(request.getRequestURL().toString().indexOf("Withdrawals")>0){%>active<%}%>"><span><strong><i></i></strong>提现</span></a>
                </div>
            </div>
            <!--item1-end-->
            <!--2-->
            <div class="financial-cal-menu-item menu02">
                <span class="financial-cal-menu-title " data-type="financial-cal-menu-title">我的出借<em class="ver-ico-url"></em></span>
                <div class="financial-cal-menu-list " data-type="financial-cal-menu-listof" >
                   <a href="/portal/mylend/Newlist" class="<%if(request.getRequestURL().toString().indexOf("Newlist")>0){%>active<%}%>"><span><strong><i></i></strong>头笔赚</span></a>
                    <a href="/portal/mylend/Standardlist" class="<%if(request.getRequestURL().toString().indexOf("Standardlist")>0){%>active<%}%>"><span><strong><i></i></strong>点点投</span></a>
                    <a href="/portal/mylend/Creditorbaglist" class="<%if(request.getRequestURL().toString().indexOf("Creditorbaglist")>0){%>active<%}%>"><span><strong><i></i></strong>环环投</span></a>
                    <a href="/portal/mylend/Ztandardlist" class="<%if(request.getRequestURL().toString().indexOf("Ztandardlist")>0){%>active<%}%>"><span><strong><i></i></strong>转转投</span></a>
                    <a href="/portal/mylend/Creditorlist" class="<%if(request.getRequestURL().toString().indexOf("Creditorlist")>0){%>active<%}%>"><span><strong><i></i></strong>债权转让</span></a>                    
                  
                </div>
            </div>
            <!--2-end-->
            <!--3-->
            <div class="financial-cal-menu-item menu03">
                <span class="financial-cal-menu-title " data-type="financial-cal-menu-title">我的借款<em class="ver-ico-url"></em></span>
                <div class="financial-cal-menu-list " data-type="financial-cal-menu-listof" >
                    <a href="/portal/personal/LoanApplication?type=1" class="<%if(request.getRequestURL().toString().indexOf("LoanApplication")>0){%>active<%}%>"><span><strong><i></i></strong>借款审核</span></a>
                    <a href="/portal/personal/BorrowingRecords?hkstate=1" class="<%if(request.getRequestURL().toString().indexOf("BorrowingRecords")>0){%>active<%}%>"><span><strong><i></i></strong>借款记录</span></a>
                    <a href="/portal/personal/Repayment" class="<%if(request.getRequestURL().toString().indexOf("Repayment")>0){%>active<%}%>"><span><strong><i></i></strong>还款</span></a>
                    
                </div>  
            </div>
            <!--3-end-->
            <!--4-->
            <div class="financial-cal-menu-item menu04">
                <span class="financial-cal-menu-title  " data-type="financial-cal-menu-title">我的特权<em class="ver-ico-url"></em></span>
                <div class="financial-cal-menu-list " data-type="financial-cal-menu-listof" >
                    <a href="/portal/mytq/MyPrivilege01" class="<%if(request.getRequestURL().toString().indexOf("MyPrivilege01")>0){%>active<%}%>"><span><strong><i></i></strong>红包</span></a>
                    <a href="/portal/mytq/MyPrivilege02" class="<%if(request.getRequestURL().toString().indexOf("MyPrivilege02")>0){%>active<%}%>"><span><strong><i></i></strong>加息券</span></a>
					<a href="/portal/mytq/TYJbaglist" class="<%if(request.getRequestURL().toString().indexOf("TYJbaglist")>0){%>active<%}%>"><span><strong><i></i></strong>体验金</span></a>
                    <%-- <a href="MyPrivilege" class="<%if(request.getRequestURL().toString().indexOf("MyPrivilege")>0){%>active<%}%>"><span><strong><i></i></strong>理财金</span></a> --%>
                    <a href="/portal/mytq/Invitefriends" class="<%if(request.getRequestURL().toString().indexOf("Invitefriends")>0){%>active<%}%>"><span><strong><i></i></strong>邀请好友</span></a>
                    
                </div>
            </div>
            <!--4-end-->
        </div>
            
        </div>