<%@ page contentType="text/html; charset=UTF-8" %>
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<title>砖头网-抵押产品上线了…</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<link rel="stylesheet" type="text/css" href="/css/style-common.css">
<link href="/css/normalize.css" rel="stylesheet" type="text/css">
<link href="/css/ZTYWrap/MortgageProduct.css" rel="stylesheet" type="text/css">
<Link rel="shortcut icon" href="/images/zhuan.ico" />
<script type="text/javascript" src="/js/jquery-1.10.1.min.js"></script>
<script src="/js/com.js?ver=114"></script>
</head>

<body>
<% try{ %>
<%@ include file="../header.jsp" %>
<div class="addbox" style="height:79px;display:none;"></div> 
<div class="Mortgage_banner">
    <img class="banner_tit01" src="/image/MortgageProduct/banner_tit01.png">
    <img class="banner_tit02" src="/image/MortgageProduct/banner_tit02.png">
</div>
<div class="Mortgage_con">
    <div class="Mortgage_main">
        <div class="box01">
            <div class="box01_tit"><span>房产抵押——安全放心</span></div>
            <div style="height: 20px;"></div>
            <p>爱房投是借款人以个人持有的房产作为抵押物，需要到房管局办理抵押登记手续进行借款的个人抵押贷款业务。</p>
            <div class="box01_bot">
                <div class="box">
                    <img src="/image/MortgageProduct/Shape-01.png">
                    <p>商品房、写字楼、别墅等具有流通价值房产产权清晰，易变现。</p>
                </div>
                <div class="box">
                    <img src="/image/MortgageProduct/Shape-02.png">
                    <p>充分利用大数据征信技术，综合评估其信用状况，必须无法院执行人诉讼案件。</p>
                </div>
                <div class="box">
                    <img src="/image/MortgageProduct/Shape-03.png">
                    <p>实地勘探，多维度评估房价，最终估价以标的实际情况为准。</p>
                </div>
                <div class="box">
                    <img src="/image/MortgageProduct/Shape-04.png">
                    <p>借款期限以3、6、12月为主，投资期限短，风险小。</p>
                </div>
                <div class="box box02">
                    <img src="/image/MortgageProduct/Shape-05.png">
                    <p>每一份债权都会有房管所确立对应的抵押登记手续，受法律保护。</p>
                </div>
                <div class="clear"></div>

            </div>
        </div>
        <div class="box01 box02">
            <div class="box01_tit"><span>七步模式——轻松便捷</span></div>
            <div style="height: 20px;"></div>
            <img src="/image/MortgageProduct/box_02Img.png">
        </div>
        <div class="box01 box03">
            <div class="box01_tit"><span>安全措施全力保障——值得托付</span></div>
            <div style="height: 20px;"></div>
            <img src="/image/MortgageProduct/box_03Img.png">
        </div>

        <div class="Mortgage_Btn">立即出借</div>

    </div>

</div>

<!--bottom common-->
<%@ include file="../footer.jsp" %>
<%}catch(Exception e){
	response.sendRedirect("error.jsp");
    return;  
} %>

<script>
    $(function(){
        var u = navigator.userAgent;
        if (u.indexOf('Android') > -1 || u.indexOf('Linux') > -1) {//安卓手机
            $("body").css("min-width","1160px");
        } else if (u.indexOf('iPhone') > -1) {//苹果手机
            $("body").css("min-width","1160px");
        } else if (u.indexOf('Windows Phone') > -1) {//winphone手机
            $("body").css("min-width","1160px");
        }
        
        $(window).scroll(function(){	       								
   		 if(getScrollTop()>80){
   			     $(".addbox").css("display","block");
   			}
   		else{
   			$(".addbox").css("display","none");
   			}		
   	});
   	if($(window).scrollTop() > 80){
   		$(".addbox").css("display","block");
   	}else{
   		$(".addbox").css("display","none");
   	}
    })
</script>
</body>

</html>