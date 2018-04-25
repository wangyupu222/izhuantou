<%@ page contentType="text/html; charset=UTF-8" %>
<!--common header-->
<div class="headerbg">
<div class="main top">
<form action="/portal/user/exit">
<div class="con">
<%if(session.getAttribute("userMobile") != null && !"".equals(session.getAttribute("userMobile"))){ %>
<div class="top_left "><span class="welcome">您好，</span><span class="zh"><%=session.getAttribute("userMobile")%></span><button id="" name="" type="submit" value="" class="orgcolor">【安全退出】</button><span class="hfphone">客服电话：400-900-9677</span></div>
<% }else{ %>
<div class="top_left "><span class="welcome">您好，欢迎登录砖头网！</span><a href="/portal/user/login">【登录】</a><a href="/portal/user/register" class="orgcolor">【立即注册】</a><span class="hfphone">客服电话：400-900-9677</span></div>
<%}%>
<div style="display:none;"><img src="<%=pathUrl %>/images/QQcard_logo.jpg"></div>
<div class="E-mailWrap"><span class="E-mailTit">投诉&建议：</span><a href="mailto:service@izhuantou.com">service@izhuantou.com</a></div><div class="top_right"> <a class="app-a" href="/portal/ZTYMain/APP_Online">手机客户端</a> <a href="/portal/page/aboutus?OID=a13702c03fa8056b00abf22527d84c22" >关于我们</a><a href="/portal/page/guide">新手引导</a><a href="/portal/page/helpmore">帮助中心</a>
</div>
</div>
</form>
</div>
<div class="main header">
  <div class="con headercon">
    <div class="headerleft"><a href="/portal/user/index"><img src="<%=pathUrl %>/images/logo.png"></a></div>
    <div class="headerright">
        <div id="navigation">
            <ul class="navcsr">
            <li><a href="/portal/user/index" >首页</a></li>
            <li ><a class="<%if((request.getRequestURL().toString().indexOf("projects")>0) || (request.getRequestURL().toString().indexOf("NewProjects")>0) || (request.getRequestURL().toString().indexOf("DiandianProjects")>0) || (request.getRequestURL().toString().indexOf("HuanhuanProjects")>0) || (request.getRequestURL().toString().indexOf("ZhuanzhuanProjects")>0)){%>active<%}%>" href="/portal/lend/projects">我要出借<em class="jt"></em></a>
                <ul>
                	<li><a href='/portal/lend/NewProjects'>头笔赚</a></li>
                    <li><a href='/portal/lend/DiandianProjects'>点点投</a></li>
                    <li><a href='/portal/lend/HuanhuanProjects'>环环投</a></li>
                    <li><a href='/portal/lend/ZhuanzhuanProjects'>转转投</a></li>
                </ul>
            </li>
              <li ><a class="<%if((request.getRequestURL().toString().indexOf("ztloandetails")>0) || (request.getRequestURL().toString().indexOf("wyjd07")>0)){%>active<%}%>" href="/portal/detial/ztloan.jsp">我要借款</a></li>
              <li style="display:none;"><a class="<%if((request.getRequestURL().toString().indexOf("CreditorProjects")>0) ){%>active<%}%>" href="CreditorProjects">债转专区</a></li>
              <li><a href="/portal/page/security">安全保障</a></li>
              <li><a class="<%if((request.getRequestURL().toString().indexOf("CapitalDetailed")>0) || (request.getRequestURL().toString().indexOf("Recharge")>0) || (request.getRequestURL().toString().indexOf("Withdrawals")>0) || (request.getRequestURL().toString().indexOf("Standardlist")>0) || (request.getRequestURL().toString().indexOf("Creditorbaglist")>0) 
	|| (request.getRequestURL().toString().indexOf("Ztandardlist")>0)
	|| (request.getRequestURL().toString().indexOf("Newlist")>0)
	|| (request.getRequestURL().toString().indexOf("Creditorlist")>0)
	|| (request.getRequestURL().toString().indexOf("LoanApplication")>0)
	|| (request.getRequestURL().toString().indexOf("BorrowingRecords")>0)
	|| (request.getRequestURL().toString().indexOf("Repayment")>0)
	|| (request.getRequestURL().toString().indexOf("Recharge_new")>0)
	|| (request.getRequestURI().toString().indexOf("Recharge_new2")>0)
	|| (request.getRequestURL().toString().indexOf("MyPrivilege01")>0)
	|| (request.getRequestURL().toString().indexOf("MyPrivilege02")>0)
	|| (request.getRequestURL().toString().indexOf("MyPrivilege")>0)
	|| (request.getRequestURL().toString().indexOf("Invitefriends")>0)
	|| (request.getRequestURL().toString().indexOf("TYJbaglist")>0)
	|| (request.getRequestURL().toString().indexOf("Securitypre")>0)
	|| (request.getRequestURL().toString().indexOf("MyCard")>0)
	|| (request.getRequestURL().toString().indexOf("addbankcard")>0)
	|| (request.getRequestURL().toString().indexOf("MessageNotification")>0)
	|| (request.getRequestURL().toString().indexOf("MessageHistoryNotification")>0)){%>active<%}%>" href="/portal/personal/personalcenter">个人中心</a><!-- <div class="yd">1</div> --></li>
            </ul>
        </div>
      
    </div>
   <div class="clear"></div>
  </div>
</div>

</div>
<script>
//鼠标移上箭头消失，移走显示
$('head').append('<link href="/css/style-commonNew.css?v=120" rel="stylesheet" type="text/css" />');
$(function(){
	$('#navigation>ul>li:nth-child(2),.jt').hover(function(){
		$(this).find("ul").slideDown(500,"bounceOut");
	
	},function(){
		$(this).find("ul").stop().slideUp(500);	
	});
	$('#navigation>ul>li:nth-child(2)>a,#navigation>ul>li>ul').mouseover(function(){
		//alert(1);
		$('.jt').hide();
	
	}).mouseout(function(){
		$('.jt').show();	
	});	
	
})	
</script>
<!-- <script>

window.onload=function(){
    getNowFormatDate()
    setInterval(getNowFormatDate,100);
}
function getString(v){
    if (v<10){
        return "0"+v;
    }else
        return v;
}
function getNowFormatDate() {
    var oDiv=document.getElementById("data_new");
    var  time = xhr.getResponseHeader("Date");
    var date = new Date(time);
    var seperator1 = "-";
    var seperator2 = ":";
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }

    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
            + " " + getString(date.getHours()) + seperator2 + getString(date.getMinutes())
            + getString(seperator2 + date.getSeconds());
    oDiv.innerHTML=currentdate;
}

</script> -->

<!-- 服务器time -->




<script type="text/javascript" src="/js/move.js"></script>
<!--common end-->