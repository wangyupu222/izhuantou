<%@ page contentType="text/html; charset=UTF-8" %>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv=”X-UA-Compatible” content=”IE=edge″ />
<meta name="description" content="新手指引" />
<meta name="keywords"	content="新手指引" />
<title>砖头网-新手引导</title>
<link rel="stylesheet" type="text/css" href="/css/style-common.css?v=114">
<link rel="stylesheet" type="text/css" href="/guide/guide_new/guide.css">
<link rel="stylesheet" type="text/css" href="/guide/guide_new/round_hover.css">
<Link rel="shortcut icon" href="/images/zhuan.ico" />
</head>
<style>
.header{border-bottom: 1px solid #ddd; position: relative;z-index: 16;background:white;}
		.header .cnt{width: 1200px;height: 42px;position: relative;}
		.header .left{float: left;width:420px;height: 40px;}
		.header .left p{float: left;height: 100%;font-size: 14px;line-height: 42px;color: #595757;margin-right: 5px;}
		.header .left a{float: left;width: 30px;height: 100%;margin: 0 5px;}
		.wx,.qq,.sina{background-image: url("/images/homepage/tb01.png");background-repeat: no-repeat;}
		.wx{background-position: 1px 10px;}
		.qq{background-position: 1px -58px;}
		.sina{background-position: 1px -125px;}
		.app-a {
   		 background-position-x: 20px;
			}
			.top_right a.app-a{
			padding-left: 40px;
			}
		
		.top{border-bottom:1px solid #ccc; height:40px; line-height:40px; background-color:#fff;  }
		.top_left{ float:left; font-size:14px;  color:#141414;}
		.top_left a{color:#141414;}
		.top_left a + a{ color:#ff9934;}
		<%-- .top_left span{ background:url(..//images/shape.png) no-repeat left center; padding-left:24px; margin-left:20px;} --%>
		.top_mid{ float:left; }
		.top_mid span{ display:block; float:left; border:1px solid #e7e7e7;width:26px; height:26px; line-height:24px; border-radius:26px; margin-top:6px; margin-left:10px;}
		.top_mid span img{ margin-left:7px;}
		.top_right{ /*width:530px; */float:right; height:25px; line-height:25px;}
		.top_right a{ /*display:block;*/ float:left; border-left:1px solid #ccc;padding:0 18px; color:#333333; font-size:12px;}
		.top_right a:first-child{border-left:0;}
		.top_right a.active{ border:1px solid #3e8aff; border-top:0px;}
</style>
<body >
<% try{ %> 
<div class="login" id="login"></div>
        <div class="header">        
     <div class="main top">
 <form action="/portal/user/exit" >
<div class="con">
<%if(request.getAttribute("userMobile") != null){ %>
<div class="top_left "><span class="welcome">您好，</span><span class="zh"><%=request.getAttribute("userMobile")%></span><web:WidgetButton value="【安全退出】" customProperty="class='orgcolor' " property=""  /><span class="hfphone">客服电话：400-900-9677</span></div>
<% }else{ %>
<div class="top_left "><span class="welcome">您好，欢迎登录砖头网！</span><a href="/portal/user/login">【登录】</a><a href="/portal/user/register" class="orgcolor">【免费注册】</a><span class="hfphone">客服电话：400-900-9677</span></div>
<%}%>
<div class="E-mailWrap"><span class="E-mailTit">投诉&建议：</span><a href="mailto:service@izhuantou.com">service@izhuantou.com</a></div>
<div class="top_right"> <a href="/portal/user/index" >首页</a><a class="app-a" href="/portal/ZTYMain/APP_Online">手机客户端<%-- <img src="images/jiantou.png"><div class="appclient"><img src="images/app.jpg"><div class="app-txt">APP移动端</div></div> --%></a><%--<a href="projects" >我要出借</a> --%><a href="/portal/page/aboutus?OID=a13702c03fa8056b00abf22527d84c22" >关于我们</a><a href="/portal/page/helpmore">帮助中心</a>
</div>
</div>
</form>
</div>
    </div>
<!--       内容开始            -->

<div class="guide_wrap">
    <div class="guide_mian">
        <div class="guide_box">
            <div class="guide_titBox">
            <div class="module_top module_tit01">我有什么福利？</div>
            </div>
            <div class="module_bottom">
                <img class="brick01" src="/image/NoviceBoot/brick01.png">
                <div class="guideTit"><img  src="/image/NoviceBoot/guideTit.png"><a class="linkone" href="/portal/user/register"></a><a class="linktwo" href="/portal/detial/tzdetailsnew"></a><a class="linkthree" href="/portal/page/New_privileges"></a></div>
                <img class="xinxin01" src="/image/NoviceBoot/xinxin04.png">
                <div class="clear"></div>
            </div>
        </div>
        <div class="guide_box guide_box02">
            <div class="zhanwei"></div>
            <div class="module_top module_tit02">如何选择适合我的产品？</div>
            <div class="module_bottom point">
                <img class="xinxin02" src="/image/NoviceBoot/xinxin01.png">
                <div class="module02_text ">
                    <div class="module_yuan">
                    <a href="/portal/detial/Newtzdetails?OID=">
                        <h3>头笔赚</h3>
                        <p>优质<span>高收益</span>产品综合年化15.00%出借期限15天</p>
                    </a>
                    </div>
                    <div class="tbz_yuan"><a href="/portal/detial/Newtzdetails?OID="><img src="/image/NoviceBoot/tbz_yuan.png"></a></div>
                    <div class="text">头笔赚是一款散标标的，其周期短、利息高，让你头一笔
                        出借就能感受到砖头网真实可靠的出借收益效果</div>
                        <div class="clear"></div>
                </div>
                <img class="brick02" src="/image/NoviceBoot/brick02.png">
                <div class="clear"></div>
            </div>
        </div>
        <div class="guide_box guide_box03">
            <div class="zhanwei"></div>
            <div class="module_bottom point">
                <div class="module03_text">
                    环环投让每次收益都环环相扣，该计划将出借资金极度分散，
                    兼顾了出借资金的灵活性和安全性，是一款超省心的出借计划。
                </div>
                <div class="charts_wrap">
                    <div id="tipZone">
                        <div id="base1" class="part effective1 active">
                            <div class="bing">
                                <div class="description">
                                    <h3>整月盈</h3>
                                    <p>综合年化：7.20%出借期限：30天</p>
                                </div>
                            </div>
                        </div>
                        <div id="base2" class="part effective2" >
                            <div class="bing">
                                <div class="description">
                                    <h3>季皆盈</h3>
                                    <p>综合年化：8.00%出借期限：90天</p>
                                </div>
                            </div>
                        </div>
                        <div id="base3" class="part effective3">
                            <div class="bing">
                                <div class="description">
                                    <h3>半年盈</h3>
                                    <p>综合年化：9.50%出借期限：180天</p>
                                </div>
                            </div>
                        </div>
                        <div id="base4"class="part effective4" >
                            <div class="bing">
                                <div class="description">
                                    <h3>年年盈</h3>
                                    <p>综合年化：11.00%出借期限：12个月</p>
                                </div>
                            </div>
                        </div>
                        <div id="base5" class="part effective5" >
                            <div class="bing">
                                <div class="description">
                                    <h3>连年盈</h3>
                                    <p>综合年化：14.00%出借期限：24个月</p>
                                </div>
                            </div>
                        </div>
                        <div id="base6" class="inner"  >
                            <div class="core">
                            <a href="/portal/lend/HuanhuanProjects">
                                <h3>环环投</h3>
                                <p>出借<span>当日计息</span><br>的稳健产品</p>
                            </a>
                            </div>
                        </div>
                    </div>
                    <div class="csstransforms">
                        <div class="cn-wrapper opened-nav" id="cn-wrapper">
                            <ul>
                                <li class="ln_active"><a href="/portal/lend/HuanhuanProjects"></a></li>
                                <li class="ln_active"><a href="/portal/lend/HuanhuanProjects"></a></li>
                                <li class="zy_active"><a href="/portal/lend/HuanhuanProjects"></a></li>
                                <li class="zy_active"><a href="/portal/lend/HuanhuanProjects"></a></li>
                                <li class="jj_active"><a href="/portal/lend/HuanhuanProjects"></a></li>
                                <li class="jj_active"><a href="/portal/lend/HuanhuanProjects"></a></li>
                                <li class="bn_active"><a href="/portal/lend/HuanhuanProjects"></a></li>
                                <li class="bn_active"><a href="/portal/lend/HuanhuanProjects"></a></li>
                                <li class="nn_active"><a href="/portal/lend/HuanhuanProjects"></a></li>
                                <li class="nn_active"><a href="/portal/lend/HuanhuanProjects"></a></li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="charts_ieImg"><a href="/portal/lend/HuanhuanProjects"><img src="/image/NoviceBoot/charts_ieImg.png"></a></div>
                <div class="clear"></div>
            </div>
        </div>

        <div class="guide_box guide_box04 point">
            <div class="zhanwei"></div>
            <div class="module_top module_tit04">赶紧算算我能赚多少！</div>
            <div class="module_bottom">
                <img class="xinxin03" src="/image/NoviceBoot/xinxin02.png">
                <div class="calculatorImg"><a href="/portal/page/calc"><img src="/image/NoviceBoot/calculatorImg.png"></a></div>
                <div class="calculator_icon"><a href="/portal/page/calc"></a></div>
                <div class="clear"></div>
            </div>
        </div>
        <div class="guide_box guide_box05 point">
            <div class="zhanwei"></div>
            <div class="module_top module_tit05">那么我的钱借给了谁呢？</div>
            <div class="module_bottom">
                <img class="xinxin04" src="/image/NoviceBoot/xinxin03.png">
                <img class="flow_Img" src="/image/NoviceBoot/flow_Img.png">
                <img class="brick03" src="/image/NoviceBoot/brick03.png">
                <div class="clear"></div>
            </div>
        </div>

        <div class="guide_box guide_box06">
            <div class="zhanwei"></div>
            <div class="module_top module_tit05">等不及了  上砖头儿  有赚头儿</div>
            <div class="module_bottom">
                <img class="registerTv" src="/image/NoviceBoot/registerTv.png">
                <div class="registerBtn"><a href="/portal/user/register">立即注册</a></div>
            </div>
        </div>


    </div>
</div>

<!--      内容结束          -->
<script src="/guide/jquery-1.8.3.min.js"></script>  
<script>
$(function(){
	
	 if("msDoNotTrack" in window.navigator){
         $(".brick01,.xinxin01,.module_tit02,.module02_text,.xinxin02,.brick02,.module_tit04,.xinxin03,.module_tit05,.xinxin04,.brick03,.flow_Img").css("opacity","1");
     }
	
    var u = navigator.userAgent;
    if (u.indexOf('Android') > -1 || u.indexOf('Linux') > -1) {//安卓手机
        $("body").css("min-width","1250px");
    } else if (u.indexOf('iPhone') > -1) {//苹果手机
        $("body").css("min-width","1250px");
    } else if (u.indexOf('Windows Phone') > -1) {//winphone手机
        $("body").css("min-width","1250px");
    }


    $(window).bind("scroll", function(event){
        $(".point").each(function(){
            if($(window).scrollTop() > ($(this).offset().top-$(this).outerHeight()/0.7)){$(this).addClass("modelShow")}
        });
    });
    $(".ln_active").hover(function(){
        $("#base5").addClass("active").siblings(".part").removeClass("active");
    })
    $(".zy_active").hover(function(){
        $("#base1").addClass("active").siblings(".part").removeClass("active");
    })
    $(".jj_active").hover(function(){
        $("#base2").addClass("active").siblings(".part").removeClass("active");
    })
    $(".bn_active").hover(function(){
        $("#base3").addClass("active").siblings(".part").removeClass("active");
    })
    $(".nn_active").hover(function(){
        $("#base4").addClass("active").siblings(".part").removeClass("active");
    })
    
    if(navigator.appName == "Microsoft Internet Explorer" && navigator.appVersion .split(";")[1].replace(/[ ]/g,"")=="MSIE6.0") 
    { 
     $(".charts_wrap").css("display","none").siblings(".charts_ieImg").css("display","block");
     $(".module_yuan").css("display","none").siblings(".tbz_yuan").css("display","block");
    } 
    else if(navigator.appName == "Microsoft Internet Explorer" && navigator.appVersion .split(";")[1].replace(/[ ]/g,"")=="MSIE7.0") 
    { 
    $(".charts_wrap").css("display","none").siblings(".charts_ieImg").css("display","block");
    $(".module_yuan").css("display","none").siblings(".tbz_yuan").css("display","block");
    } 
    else if(navigator.appName == "Microsoft Internet Explorer" && navigator.appVersion .split(";")[1].replace(/[ ]/g,"")=="MSIE8.0") 
    { 
    $(".charts_wrap").css("display","none").siblings(".charts_ieImg").css("display","block");
    $(".module_yuan").css("display","none").siblings(".tbz_yuan").css("display","block");
    } 
     


})
</script>


<%}catch(Exception e){
	response.sendRedirect("error.jsp");
    return;  
} %>
<script>
$(function(){
	/*  头笔赚  */
	$.ajax({
        type: "POST",//请求方式
        url: "/portal/index/touBiZhuan",//地址，就是json文件的请求路径
        dataType: "json",//数据类型可以为 text xml json  script  jsonp
        success: function(result){//返回的参数就是 action里面所有的有get和set方法的参数
        	console.log(result);
        	$(".module_yuan a").attr('href','/portal/detial/Newtzdetails?OID='+result.oid);
        	$(".tbz_yuan a").attr('href','/portal/detial/Newtzdetails?OID='+result.oid);
        },
		error:function(result){    	
    	}
    });
})
</script>
</body>
</html>