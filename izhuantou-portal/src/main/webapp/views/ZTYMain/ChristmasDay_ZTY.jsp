<%@ page contentType="text/html; charset=UTF-8" %>
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<title>砖头网-圣诞惊喜，从天而降</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<link rel="stylesheet" type="text/css" href="/css/style-common.css">
<link href="/css/normalize.css" rel="stylesheet" type="text/css">
<link href="/css/ZTYWrap/ChristmasDay_ZTY.css" rel="stylesheet" type="text/css">
<Link rel="shortcut icon" href="/images/zhuan.ico" />
<script type="text/javascript" src="/js/jquery-1.10.1.min.js"></script>
<script src="/js/com.js?ver=114"></script>
</head>
<style>
.footerbg {
 border-top: none;
 margin-top: 0px;
}
</style>
<body>
<% try{ %>
<%@ include file="../header.jsp" %>
<div class="addbox" style="height:79px;display:none;"></div> 
<div class="ChristmasDayWrap">
    <div class="ChristmasDayMain">
        <div class="moduleBoxOne">
            <img class="ChristmasDay-tit" src="/image/ChristmasDay/ChristmasDay-tit01.png">
            <div class="introduceText">有钱也要有面儿，<br>圣诞节到了，<br>送她的礼物准备好了吗？<br>没准备不要紧，我们为你准备好了！<br>圣诞节，在砖头网，<br>手有多快，礼物就有多大！</div>
            <div class="ruleText">★ 圣诞活动期间，砖头网共为您准备【圣诞礼物】9份，每日8时、12时、20时三个时段，<span>首位出借季皆盈≥3万元的出借用户可【随机】<br>获得一份【圣诞礼物】</span>！且，以日计算，<span>每日三个获奖者中，出借金额最高的者可获礼品选择权</span>（任意选择一份未送出的礼品）！<br>
                【圣诞礼物】如下：</div>
            <img class="gift01" src="/image/ChristmasDay/gift01.png">
            <div class="ruleText ruleTextTwo">★ 此外，全天出借季皆盈产品单笔金额≥3万元的出借用户，可获得Chrome Hearts巧克力一盒。</div>
            <img class="gift02" src="/image/ChristmasDay/gift02.png">
        </div>
        <div class="moduleBoxTwo">
            <img class="ChristmasDay-tit" src="/image/ChristmasDay/ChristmasDay-tit02.png">
            <div class="attentionText">
                1、活动时间：2017年12月25日0:00-27日24:00。<br>
                2、若每日最高获奖者出借金额一致，则按时间顺序，第1位【圣诞礼物】获奖者享有礼品选择权。<br>
                3、若同一时间节点有多位用户同时出借，则【圣诞礼物】获奖者以前端显示顺序为准（时间顺序）。<br>
                4、若截止到本时段结束无满足条件客户，则本时段【圣诞礼物】礼品作废（有效时段为8:00-8:59、12:00-12:59、20:00-20:59）。<br>
                5、Chrome Hearts巧克力礼品每ID限领取一份，且不与【圣诞礼物】奖励同享，数量有限，先到先得。<br>
                6、砖头网客服将于活动结束后3个工作日内联系获奖客户，按时间顺序联系每日最高获奖用户选择【圣诞礼物】，其余礼品随机发 <br>          	放。若客服在3个工作日内未能联系到获奖客户则奖品作废，不予补发。您也可以通过砖头网客服个人微信号【izhuantou-family】
                联系我们。<br>
                7、官方公告将于活动结束后第4个工作日发布获奖名单及所获【圣诞礼物】。<br>
                8、实物奖品将于活动结束后7个工作日内寄出，现金奖励将直接返还至您砖头网绑定银行卡中。<br>
                9、此活动获奖出借用户，可与“种瓜得瓜 种钱生钱”活动福利叠加享受。<br>
                10、砖头网禁止恶意刷奖，若发现任何作弊或非法手段获得奖励的，一经核实，将取消奖励资格。<br>
                11、如有任何疑问，请联系砖头网客服进行咨询，以客服回复为准。<br>
                12、本活动最终解释权归爱砖头（天津）网络科技有限公司所有。
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