<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/include/url.jsp" %>
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<title>砖头网-砖头手机理财,赚钱信手拈来</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<link rel="stylesheet" type="text/css" href="/css/style-common.css">
<link href="/css/normalize.css" rel="stylesheet" type="text/css">
<link href="/css/App_Online.css?ver=114" rel="stylesheet" type="text/css">
<Link rel="shortcut icon" href="<%=pathUrl %>/images/zhuan.ico" />
<script type="text/javascript" src="/js/jquery-1.10.1.min.js"></script>
<script src="/js/com.js?ver=114"></script>
<script>

</script>
</head>

<body>
<% try{ %>
<%@ include file="header.jsp" %>

<div class="app_banner">
    <div class="banner_wrap">
        <div class="left">
        <p>砖头网手机客户端正式上线</p>
        <h3>砖头手机理财<br>赚钱信手拈来</h3>
            <div class="down_btn">
                <div class="down_ios"><a target="_blank" href="https://itunes.apple.com/us/app/%E7%A0%96%E5%A4%B4%E7%BD%91/id1252526385?l=zh&ls=1&mt=8"><span>iPhone版下载</span></a></div>
                <div class="down_ios down_android"><a target="_blank" href="http://sj.qq.com/myapp/detail.htm?apkName=com.izhuantou.bricknet"><span>Android版下载</span></a></div>
            </div>
            <div class="QR_Code">
            </div>
        </div>
        <div class="clear"></div>

    </div>

</div>
<div class="safe_content">
    <div class="safe_box">
        <div class="safe_top">
            <div class="safe_text">
                <h3>随心</h3>
                <p>操作细腻随你心意</p>
            </div>
        </div>
        <div class="safe_bottom">
            <p>优化体验操作流程，界面友好更省心</p>
            Optimize the experience operation process,<br>
            friendly interface, more worry
        </div>
    </div>
    <div class="safe_box">
        <div class="safe_top safe_top02">
            <div class="safe_text">
                <h3>随性</h3>
                <p>赚多赚少由你掌控</p>
            </div>
        </div>
        <div class="safe_bottom">
            <p>优质标的突出显示，不错过任何收益</p>
            Premium labels are highlighted <br>without any gains
        </div>
    </div>
    <div class="safe_box safe_box3">
        <div class="safe_top safe_to3">
            <div class="safe_text">
                <h3>随行</h3>
                <p>走到哪里投到哪里</p>
            </div>
        </div>
        <div class="safe_bottom">
            <p>不受时间空间限制，不再有抢标尴尬</p>
            Not limited by time and space,  <br>no more embarrassing
        </div>
    </div>
<div class="clear"></div>
</div>
<div class="phone_wrap">
    <div class="phone_left">
        <div class="phone_main">
            <img style="opacity: 1;filter:alpha(opacity=1);z-index: 2" src="<%=pathUrl %>/image/APP_Online/iphone01.png">
            <img src="<%=pathUrl %>/image/APP_Online/iphone02.png">
            <img src="<%=pathUrl %>/image/APP_Online/iphone03.png">
            <img src="<%=pathUrl %>/image/APP_Online/iphone04.png">
        </div>

    </div>
    <div class="phone_right">
        <div><img src="<%=pathUrl %>/image/APP_Online/new_desing.png?v=114"></div>
        <div class="phone_tit"><img src="<%=pathUrl %>/image/APP_Online/index_img.png?v=115"></div>
        <div class="phone_tit" style="margin-left: 1px;"><img src="<%=pathUrl %>/image/APP_Online/lend_img.png?v=114"></div>
        <div class="phone_tit" style="margin-left: 8px;"><img src="<%=pathUrl %>/image/APP_Online/find_img.png?v=114"></div>
        <div class="phone_tit" style="margin-left: 6px;"><img src="<%=pathUrl %>/image/APP_Online/Account_img.png?v=114"></div>

    </div>
    <div class="clear"></div>

</div>
<div class="bottom_wrap">
    <div class="bottom_main">
        <div class="text">
        <p>用砖头网APP理财<br>把赚钱的事交给我们，你只负责收益就好</p>
            <div class="fen"></div>
            <span>APP MONEY WITH IZHUANTOU<br>GIVE US THE MONEY, AND YOU ARE ONLY RESPONSIBLE <br>FOR THE GAINS</span>
        </div>
        <div class="bottom_btn">
            <div class="btn_box"><a target="_blank" href="https://itunes.apple.com/us/app/%E7%A0%96%E5%A4%B4%E7%BD%91/id1252526385?l=zh&ls=1&mt=8"><img src="<%=pathUrl %>/image/APP_Online/ios_downImg.png?v=114"></a></div>
            <div class="btn_box"><a target="_blank" href="http://sj.qq.com/myapp/detail.htm?apkName=com.izhuantou.bricknet"><img src="<%=pathUrl %>/image/APP_Online/and_downImg.png?v=114"></a></div>
            <div class="clear"></div>
        </div>

    </div>

</div>

<!--bottom common-->
<%@ include file="footer.jsp" %>
<%}catch(Exception e){
	response.sendRedirect("error.jsp");
    return;  
} %>
</body>
<script>
    $(function(){
        var  n =0;
        var timer=setInterval(function(){
            n++;
            if (n>3){
                n=0;
            }
            $(".phone_main img").eq(n).css({"opacity":"1","filter":"alpha(opacity=1)","z-index":"2"}).siblings().css({"opacity":"0","filter":"alpha(opacity=0)","z-index":"1"});
        },3000);
        var u = navigator.userAgent;
        if (u.indexOf('Android') > -1 || u.indexOf('Linux') > -1) {//安卓手机
            $("body").css("min-width","1160px");
        } else if (u.indexOf('iPhone') > -1) {//苹果手机
            $("body").css("min-width","1160px");
        } else if (u.indexOf('Windows Phone') > -1) {//winphone手机
            $("body").css("min-width","1160px");
        }
    })
</script>
</html>