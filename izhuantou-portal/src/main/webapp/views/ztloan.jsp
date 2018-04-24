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
<script src="/js/com.js"></script>

</head>
<body>
<% try{ %>
<%@ include file="header.jsp" %> 
<div class="addbox" style="height:79px;display:none;"></div>
<!--借款模块 i薪贷 i抵押 i保单 i房贷 i意贷-->
<div class="main jdpart">
	<div class="con jdpcons">
    	<div class="navwrap">
    	<div class="navMain">
    	<div class="nav01 active"><img src="/images/loanMain_New/credit_atv.png"></div>
        <div class="nav02"><img src="/images/loanMain_New/mortgage.png"></div>
    	</div>        	
        </div>
        <div class="loanObj">
        <div class="loanModul loanCredit">
        <div class="CreditWrap">
        <div class="loanBox">
        <h3>i意贷</h3>
        <p>i意贷专注服务于市内六区、新四区及滨海新区，正常经营一年及以上的企业或商户，具有放款额度高，手续简便等特点。</p>
        <img class="loanImg loanIYDimg" src="/images/loanMain_New/loan_IYDimg.png">
        <div class="loan_Btn"><a href="ztloandetails.jsp?OID=b00109ab3fa805780084c164a4175dfe">了解详情</a></div>
        </div>
        <div class="loanBox">
        <h3>i薪贷</h3>
        <p>薪贷针对受薪人士或自雇人士提供的小额贷款项目，具有申请便捷，审核迅速的特点。</p>
        <img class="loanImg loanIXDimg" src="/images/loanMain_New/loan_IXDimg.png">
        <div class="loan_Btn"><a href="ztloandetails.jsp?OID=afffe0d13fa805780045aefe2d2dea06">了解详情</a></div>
        </div>
        <div class="loanBox">
        <h3>i房贷</h3>
        <p>i房贷服务于市内六区、新四区及滨海/贷款的申请人，具有审核通过率高，放款及时的特点。</p>
        <img class="loanImg loanIFDimg" src="/images/loanMain_New/loan_IFDimg.png">
        <div class="loan_Btn"><a href="ztloandetails.jsp?OID=b000c0093fa8057801cdd66986dc70b7">了解详情</a></div>
        </div>
        <div class="loanBox loanBox02">
        <h3>i保单</h3>
        <p>i保单服务于本市或长期居住在本市的外地户籍人员，并有现金价值保单的投保人，具有审核门槛低，审批迅速的特点。</p>
        <img class="loanImg loanIBDimg" src="/images/loanMain_New/loan_IBDimg.png">
        <div class="loan_Btn"><a href="ztloandetails.jsp?OID=b000e1223fa805780174f34f25da921c">了解详情</a></div>
        </div>
        <div class="clear"></div>
        </div>
        <div class="CreditBtn">
        <div class="CreditBtn_L disabled_left">&lt;</div>
        <div class="CreditBtn_R">&gt;</div>
        <div class="clear"></div>
        </div>
        <div class="Credit_nav"><span class="active"></span><span class="Credit_nav02"></span></div>
        </div>
        <div class="loanModul loanMortgage hide">
        <div class="loanMortgage_warp">
        <div class="loanBox">
        <h3>i房抵</h3>
        <p>服务于持自有住房、商住两用公寓的小微企业或企业主，具有审核通过率高，放款及时的特点。</p>
        <img class="loanImg loanIFangDimg" src="/images/loanMain_New/loan_IFangDimg.png">
        <div class="loan_Btn"><a href="ztloandetails.jsp?OID=b00049513fa80578002f968bc2febe97">了解详情</a></div>
        </div>
        <div class="loanBox">
        <h3>i车抵</h3>
        <p>服务于与指定物流公司具有合作关系的借款人，具有借款周期长，借款估值更合理的特点。</p>
        <img class="loanImg loanICDimg" src="/images/loanMain_New/loan_ICDimg.png">
        <div class="loan_Btn"><a href="ztloandetails.jsp?OID=8f45539c0a5dca713ea557825ee495e0">了解详情</a></div>
        </div>
        <div class="loanBox loanBoxLiu loanBox02">
        
        </div>
        </div>
        </div>
        </div>
        
    </div>
</div>


<!--注册领红包-->

<!-----------------中间部分结束----------------> 
<%@ include file="footer.jsp" %>
<%}catch(Exception e){
	response.sendRedirect("error.jsp");
    return;  
} %>
<script>
$(function(){
	$(".navMain div").click(function(){
		$(this).addClass('active').siblings().removeClass('active');
		$('.loanModul').eq($(this).index()).show().siblings(".loanModul").hide();
		if($(".navMain .nav01").hasClass("active")){
			$(".navMain .nav01").find("img").attr("src","/images/loanMain_New/credit_atv.png");
		}else{
			$(".navMain .nav01").find("img").attr("src","/images/loanMain_New/credit.png");
		}
		if($(".navMain .nav02").hasClass("active")){
			$(".navMain .nav02").find("img").attr("src","/images/loanMain_New/mortgage_atv.png");
		}else{
			$(".navMain .nav02").find("img").attr("src","/images/loanMain_New/mortgage.png");
		}
	})
	var n=1;
	$(".CreditBtn .CreditBtn_R").click(function(){
		$(".CreditWrap").stop(false,true).animate({"left":"-394px"},800);
		$(".CreditBtn_R").addClass("disabled_left").siblings(".CreditBtn_L").removeClass("disabled_left");
		$(".Credit_nav span").eq(1).addClass('active').siblings(".Credit_nav span").removeClass('active');
	})
	$(".CreditBtn .CreditBtn_L").click(function(){
		$(".CreditWrap").stop(false,true).animate({"left":"0px"},800);
		$(".CreditBtn_L").addClass("disabled_left").siblings(".CreditBtn_R").removeClass("disabled_left");
		$(".Credit_nav span").eq(0).addClass('active').siblings(".Credit_nav span").removeClass('active');
	})
	$(window).scroll(function(){
		 if(getScrollTop()>50){
			 	 $(".header").addClass("fixed");
			     $(".addbox").css("display","block");
			}
		else{
			$(".header").removeClass("fixed");
			$(".addbox").css("display","none");
			}		
	});
	if($(window).scrollTop() > 50){
		$(".addbox").css("display","block");
	}else{
		$(".addbox").css("display","none");
	}
})

</script>
</body>

</html>
