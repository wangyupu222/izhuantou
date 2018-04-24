<%@ page contentType="text/html; charset=UTF-8" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>砖头网-红包</title>
<link rel="stylesheet" type="text/css" href="/css/style-common.css">
<link rel="stylesheet" type="text/css" href="/css/personalcenter.css">
<link rel="stylesheet" type="text/css" href="/css/preother.css?=v114">
<Link rel="shortcut icon" href="/images/zhuan.ico" />
<script type="text/javascript" src="/js/jquery-1.10.1.min.js"></script>

<script src="/js/com.js"></script>
<script language="javascript" src="/js/menu.js"></script>
<style>
.hide_liu{  width: 100%;  height: 400px;  text-align: center;  background:#fff url("/images/h_brand_new_liu_new.png"); background-repeat: no-repeat; background-position:center center; display: none;  }
.h_lose_liu{background:#fff url("/images/h_lose_efficacy_liu_new.png");background-repeat: no-repeat; background-position:center center;}
.h_used_liu{background:#fff url("/images/h_used_liu_new.png");background-repeat: no-repeat; background-position:center center;}
.hbcon{min-height:570px;}
.xqdeleft{padding-bottom:0;}
</style>
</head>
<body>
<% try{ %>
<!--common header-->
<%@ include file="header.jsp" %>
<!--common end-->
<div class="main minhh">
	<div class="con personcon">
    	<div class="smallnav">
    	<!-- <a >当前位置</a><span>:</span><a >个人中心</a><span>&gt;</span><a >我的特权</a><span>&gt;</span><a >红包</a> -->
        </div>
        <div class="con xqdetial">
    	<%@ include file="left_percenter.jsp" %>
        
    	<div class="xqdeleft">
        	<!-- <div class="hbtab">
            	<a class="active" href="MyPrivilege01.jsp">我的红包</a>
            </div> -->
            <div class="my_loantop"><a href="#" class="active">我的红包</a><span class="L1">&nbsp;</span></div>
            
            <div class="clear"></div>
            <div class="hbcon">
            	<div class="box-quan">
                	<!--<div class="tithb">获取红包</div>
                    <div class="box-left">
                    	
                        <div class="hbpic"></div>
                        <div class="hbpic"></div>
                        <div class="hbpic hbpic01">更多红包获取方式敬请期待+</div>
                        <div class="clear"></div>
                    </div>-->
                    <div class="tithb">
                    	<!--<span>红包状态：</span>-->
                        <a class="active">未使用</a><a>已使用</a><a>已过期</a>
                        <div class="clear"></div>
                   </div>
                   <div class="consquan">
                   		<div class="redbag brand_new">
                           	
                             <div class="hide_liu loanrow_liu"></div>
                       </div>
                          <div class="redbag clse_efficacy hide">                       
                             
                            <div class="hide_liu h_used_liu"></div>
                        </div>
                        
                        
                        <div class="redbag clse_efficacy hide">
                       
                           
                            <div class="hide_liu h_lose_liu"></div>
                        </div>
                        
                   </div>
                </div>
                
                
                </div>
                <%-- <div class="wxts">
            	<div class="wxtstit"><img src="/images/light.png"><span>温馨提示</span></div>
                <div class="clear"></div>
                <div class="wxtscon">特别提醒：
                <br/>请勿使用他人身份证进行实名认证并且充值，否则将导致无法提现
				<br/>用户须知：
				<br/>1.平台只支持绑定1张银行卡（不支持绑定信用卡），用户今后在快捷充值与提现时，可以使用这张银行卡
                <br/>2.请用户尽量填写较大的银行(如农行、工行、建行、中国银行等)，避免填写那些比较少见的银行（如农村信用社、平安银行、恒丰银行等）。 否则提现资金很容易会被退票。
                <br/>3. 如果选择其它银行，请在选择其它银行后填写该银行的名称。
                <br/>4. 请您填写完整联系方式，以便遇到问题时，工作人员可以及时联系到您。
                </div>
            </div> --%>
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

<script src="/js/tabmenu.js"></script>
<script>
$(function(){
    if($(".brand_new").find(".redbag01").length<1){
        $(".loanrow_liu").css("display","block");
    }
    if($(".clse_efficacy").find(".redbag01").length<1){
        $(".h_lose_liu").css("display","block");
    }
    if($(".used").find(".redbag01").length<1){
        $(".h_used_liu").css("display","block");
    }
}) 
</script>
</html>
