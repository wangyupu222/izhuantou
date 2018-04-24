<%@ page contentType="text/html; charset=UTF-8" %>
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<title>砖头网-种瓜得瓜，种钱得钱</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<link rel="stylesheet" type="text/css" href="/css/style-common.css">
<link href="/css/normalize.css" rel="stylesheet" type="text/css">
<link href="/css/ZTYWrap/ForMoney_ZTY.css" rel="stylesheet" type="text/css">
<Link rel="shortcut icon" href="/images/zhuan.ico" />
<script type="text/javascript" src="/js/jquery-1.10.1.min.js"></script>
<script src="/js/com.js?ver=114"></script>
<style>
.footerbg {
 border-top: none;
 margin-top: 0px;
}
</style>
<script>
$(function(){
if("msDoNotTrack" in window.navigator){
    $(".goldBox span").css("opacity","1");
}
})
</script>
</head>

<body>
<% try{ %>
<%@ include file="../header.jsp" %>
<div class="addbox" style="height:79px;display:none;"></div> 
<div class="ForMonyWrap">
    <div class="ForMonyMain">
        <div class="ForMonytit">
            <div class="date-active">活动时间：2017年12月12日0时-12月31日24时</div>
        </div>
        <div class="ForMony-Con">
            <div class="Activity-tit"><p>活动规则：活动期间，个人累计出借整月盈或季皆盈达到以下标准，均可获得现金奖励。</p></div>
            <div class="MoneyWrap">
                <div class="MoneyTop"><span class="boxOne">出借金额(整月盈)</span><span class="MoneyNum01">20万</span><span class="MoneyNum02">35万</span><span class="MoneyNum03">50万</span><span class="MoneyNum04">100万</span><span class="MoneyNum05">300万</span></div>
                <div class="MoneyTop MoneyBtm"><span class="boxOne">出借金额(季皆盈)</span><span class="MoneyNum01">8万</span><span class="MoneyNum02">15万</span><span class="MoneyNum03">25万</span><span class="MoneyNum04">50万</span><span class="MoneyNum05">100万</span></div>
            </div>
            <div class="GoldWrap">
                <div class="Goldtit">奖励金</div>
                <div class="goldBox goldBox01"><span><em>688</em>元</span></div>
                <div class="goldBox goldBox02"><span><em>1588</em>元</span></div>
                <div class="goldBox goldBox03"><span><em>2588</em>元</span></div>
                <div class="goldBox goldBox04"><span><em>5888</em>元</span></div>
                <div class="goldBox goldBox05"><span><em>12888</em>元</span></div>
                <div class="clear"></div>
            </div>
            <div class="ExampleWrap">
                <div class="ExampleMain">
                    <h3>举个栗子</h3>
                    <p>新新在12月12日出借砖头网整月盈100万元，吴大志出借季皆盈8万元，那么活动结束后，新新将获得<em>5888</em>元现金奖励，吴大志将获得<em>688</em>元现金奖励。</p>
                </div>

            </div>
            <div class="Announcements">
                <div class="AnnouncementsMain">
                    <h3>注意事项</h3>
                    <p>1、活动时间：2017年12月12日0时-12月31日24时；<br>
                        2、本次活动可使用加息券；<br>
                        3、同一ID按累计最高出借金额获取1次奖励；<br>
                        4、本次活动现金奖励将直接达到您在砖头网绑定的银行卡账户中，活动结束后3个工作日内砖头网客服会和您联系，确认银<br>
                        <span></span>行卡号，现金将在活动结束后5个工作日内进行发放，若客服在活动结束后3个工作日内不能与您取得联系，则奖励作废不<br>
                        <span></span>予补发；<br>
                        5、砖头网禁止恶意刷奖，若发现任何作弊或非法手段获得奖励的，一经核实，将取消奖励资格；<br>
                        6、如有任何疑问，请联系砖头网客服进行咨询，以客服回复为准；<br>
                        7、本活动最终解释权归爱砖头（天津）网络科技有限公司所有。</p>
                </div>

            </div>

        </div>
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
            $("body").css("min-width","1170px");
            $(".RaiseFight_box01").css("margin-top","375px");
        } else if (u.indexOf('iPhone') > -1) {//苹果手机
            $("body").css("min-width","1170px");
            $(".RaiseFight_box01").css("margin-top","375px");
        } else if (u.indexOf('Windows Phone') > -1) {//winphone手机
            $("body").css("min-width","1170px");
            $(".RaiseFight_box01").css("margin-top","375px");
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