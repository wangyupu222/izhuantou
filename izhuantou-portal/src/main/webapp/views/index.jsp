<%@ page contentType="text/html; charset=UTF-8" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="description" content="砖头网,专业互联网金融信息中介平台,专注为投资理财的出借用户和贷款用户提供优质的金融信息中介服务,安全可靠,值得信赖!上砖头儿,有赚头儿!"/>
<meta name="renderer" content="webkit">
<meta name="baidu-site-verification" content="VAsHj0VyG1" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>砖头网-上砖头儿，有赚头儿！</title>
<link rel="stylesheet" type="text/css" href="/css/style-common.css?v=114">
<link rel="stylesheet" type="text/css" href="/css/video-js.css?v=116">
<!-- 砖头狂欢  -->
 <link rel="stylesheet" type="text/css" href="/css/style6.css" />
<link rel="stylesheet" type="text/css" href="/css/style_new2.css?v=118"> 
<link rel="stylesheet" type="text/css" href="/css/index.css?v=135">
<!-- 砖头狂欢 end  -->
<Link rel="shortcut icon" href="/images/zhuan.ico" />
<script type="text/javascript" src="/js/jquery-1.10.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/vue/latest/vue.js"></script>
<script type="text/javascript" src='https://cdn.jsdelivr.net/vue.resource/1.0.3/vue-resource.min.js'></script>
<!-- <script type="text/javascript" src="/js/main.js?v=114"></script> -->
<!-- 砖头狂欢  -->
  <%-- <script type="text/javascript" src="/js/modernizr.custom.js"></script>--%>
<script type="text/javascript" src="/js/demo_new2.js?v=116"></script>  
<!-- 砖头狂欢 end -->
<%-- <script src="/js/d.js"></script> --%>
<!--[if lt IE 9]>
    <script src="/guide/css3-mediaqueries.js"></script>
<![endif]-->
<script>
$(function(){
	var afterdata01=$(".afterdata01 em").text();
	var afterdata02=$(".afterdata02 em").text();
	var afterdata03=$(".afterdata03 em").text();
	var index=$(".afterdata");
    var aNew;
    var re = /([0-9]+\.[0-9]{2})[0-9]*/;
    $(".afterdata01 em").text(afterdata01.replace(re,"$1"));
    $(".afterdata02 em").text(afterdata02.replace(re,"$1"));
    $(".afterdata03 em").text(afterdata03.replace(re,"$1"));
    //$(".gong_title").eq(0).css("color","#e84148");
})
</script>
<script src="/js/com.js"></script>
<script type="text/javascript" src="/js/video.min.js"></script>
<script type="text/javascript" src="/js/videojs-ie8.min.js"></script>
<!--进度-->
<script src="/js/radialIndicator.js"></script>
<!--首页焦点图-->
<script src="/js/jquery.flexslider-min.js"></script>
<script type="text/javascript" src="/image/FirstAnniversary/jQuerySnow.js"></script> 
<script>
$(window).load(function() {
	$('.flexslider').flexslider({
		directionNav: false,
		pauseOnAction: false,
		manualControls: "aaa",
	});
	/* if("msDoNotTrack" in window.navigator){
		alert("a");
		$(".vjs-big-play-button").addClass("vjs-big-play-button_new");
	} */
	$('.flexslider .slides li').each(function(i){
		  var title = $(this).find('a').attr('data-val');
		  $('.flexslider .flex-control-nav li').eq(i).find('a').html(title);
		})
		 $(".flex-control-nav").css("display","block");
	if(navigator.userAgent.indexOf("MSIE 8.0")>0)  
    {  
		$(".vjs-big-play-button").addClass("vjs-big-play-button_new");
    }   
	
});

	
</script>
<style>
/* .activity_wrap{
width:100%;
height:0px;
z-index:100000;
display:none;
} */
canvas{
	width:100%;z-index:10;
}
[v-cloak] {
    display: none;
}
</style>
</head>
<body>
<% try{ %>
<!-- 砖头狂欢  -->
<%-- <div id="trigger-overlay" class="activity_btn"><a style="display: inline-block;width: 100%;"  href="/notice_details.jsp?OID=76633ec00a5dca711074b2cf438d6ed5"><img class="float_win01" src="/image/float_win.png"><a class="close_win"><img src="/image/close_small.png"></a></a></div>--%>
<!-- open/close -->
 <div class="cd-popup3 hide">
    <div class="cd-popup-container_new">
        <a href="notice_details.jsp?OID=84dbe2060a5dca715c9ce94a512552ab"><img src="http://39.106.161.33:8089/image/image/relocated_img.png"></a>
        <a href="#0" class="cd-popup-close"><img src="http://39.106.161.33:8089/image/image/close.png"></a>
    </div>
</div>  
 <div class="closing_down hide">
<a href="notice_details.jsp?OID=5a1bd9d20a5dca71274b3548a52cd257">
<img class="plan_img" src="http://39.106.161.33:8089/image/images/plan_img.png">
    <div class="clouds_img">
<img  src="/images/clouds_img.png">
    </div>
    <div class="closing_down_text"><p>停机公告</p><span>Stop notice</span></div>
    </a>
</div> 
 <div class="TopAnuuaWarp hide">
<a href="ZTYMain/TopAnnualStar_ZTY.jsp">
<img class="suspension_img" src="/image/FirstAnniversary/TOPAnnualStar/suspensionImg.png">
<img class="suspension_text" src="/image/FirstAnniversary/TOPAnnualStar/suspensionText.png">
</a>
</div>
<!-- 砖头狂欢  end -->
<%@ include file="header.jsp" %>
<!--banner-->
<div class="banner">
  <div class="flexslider">
    <ul class="slides">
          
    </ul>
  </div>
  <%if(session.getAttribute("userMobile") != null){ %>
  <!--登录后样式-->
  <div class="regglgn_out ">
  <div class="iferror"></div>
  <div  class="reglgn ">
	<div class="loginafter loginaftercon">
	<div>欢迎回来，</div>
	<div class="after-mes">尊敬的&nbsp; <%=session.getAttribute("userMobile")%>	
	</div>	
	</div>
	<div class="aftercon">
	<div class="afterdata afterdata01"><span>待收本金</span><span><em>0.00</em>元</span></div>
	<div class="afterdata afterdata02"><span>待收利息</span><span><em>0.00</em>元</span></div>
	<div class="afterdata afterdata03"><span>待收特权</span><span><em>0.00</em>元</span></div>

	<div class="zcbtn pera"><a href="/portal/personal/personalcenter">进入我的账户</a></div>
  </div>
  </div>
  </div>
  <% }else{ %>
   <!--登录/注册-->
  <div class="regglgn_out">
  <div class="iferror"><div align="center"></div></div>
  <div  class="reglgn">  
    <div class="regcon">
      <div class="regtab"  id="regtabLogin">
      <div class="regcon_top">砖头网预期年化收益</div>
      <div class="regcon_top regcon_content">最高<span>15.00%</span></div>
      <button class="sign_btn"><a href="/portal/user/register">注册即送9%加息好礼</a></button>
      <div class="login_tishi">已有账号，<span class="login_text"><a href="/portal/user/login">立即登录</a></span></div>
      <div class="regcon_bottom">市场有风险，出借需谨慎！</div>
      </div>
    </div>
  </div>
  </div>
 <%}%>
  
  
  
  
</div>
<div class="main"> 
  <!--统计信息-->
   <div class="con count">
  <web:IteratorPartPage name="allIndex" id="allIndex" rowSize="1">
   <div class="count01 activity_text">累计交易额<br><span class="data-num data-num01" data-num="number2" data-to="money" data-speed="1000" data-val=''></span>元</div>
    <div class="count01 count03">为用户赚取收益<br><span class="data-num data-num02" data-num="number" data-to="" data-speed="1000" data-val=''></span>元</div>
    <div class="count01 count02">注册用户数<br><span class="data-num data-num03" data-num="number1" data-to="" data-speed="1000" data-val=''></span>人</div> 
    <div class="count01 count03">安全运营时间<br><span class="data-num data-num04" data-num="number4" data-to='' data-speed="1000" data-val=''></span>天</div>
    
    </web:IteratorPartPage>
  </div>
  <div class="con disinfo">
    <div class="disinfo01 d01">
      <a href="/portal/page/security"><div class="div-span"> <span class="sp-1">完整全面信息披露</span> <span class="sp-2">海量真实融资项目</span> </div></a>
    </div>
    <div class="disinfo01 disinfo04 d02">
      <a href="/portal/page/security"><div class="div-span"> <span class="sp-1">质量保障服务专款</span> <span class="sp-2">保理公司竭力服务</span> </div></a>
    </div>
    <div class="disinfo01 disinfo03 d03">
      <a href="/portal/page/security"><div class="div-span"> <span class="sp-1">媲美银行风控尽调</span> <span class="sp-2">三方资金稳健托管</span> </div></a>
    </div>
    <div class="disinfo01 disinfo02 d04">
      <a href="/portal/page/security"><div class="div-span"> <span class="sp-1">专业先进技术支持</span> <span class="sp-2">法律合规全程保障</span> </div></a>
    </div>
    <div style="clear:both"></div>
  </div>
  
  <!-- 体验标start -->
  <div class=" con TYcon ">
  <div class="newshare">
    <div class="share_left"><div class="share_leftin"> <div class="finepro_leftin_L"><span class="t">体验标</span> <em>本金我出，收益您拿</em> <div class="finepro_leftin_R hide"><a href="/portal/lend/NewProjects"></a>
     </div></div> </div></div>
    <div class="share_right">
      <div class="bigtit" style="margin-left: 32px;">新手福利-体验标</div>
      <div class="newdatacon">
        <div class="newdata row1"><span><em>6.50</em>%</span><span><em>1</em>天</span><span><em id="tyjNum">0</em>人</span><span><em style="font-size: 20px;line-height: 37px;">一次性还本付息</em></span></div>
        <div class="newdata row2"><span>预期年化收益</span><span>出借期限</span><span>已体验人数</span><span>回款方式</span></div>
      </div>
      <div class="newjindubtn">
        <div class="jindu hide">
          <div class="jindubg">
            <div class="bluebox"></div>
          </div>
          <div class="jindupercent"><em>100</em>%</div>
        </div>
        <div class="lijitza indexnewprobtn"><a href="/portal/detial/tzdetailsnew">立即体验</a></div>
      </div>
    </div>
    <div style="clear:both"></div>
    </div>
  </div>
  <!-- 体验标end -->




  
  
  <!--新手专享-->
  <div class=" con newcome">   
   <div class="newshare new">
    <div class="share_left"><div class="share_leftin"><div class="finepro_leftin_L"> <span class="t">头笔赚</span> <em>新人福利、超高收益</em><div class="finepro_leftin_R"><a href="/portal/lend/NewProjects"></a>
     </div></div>  </div></div>
    <div class="share_right">
    <div id="Djs_new">
      
    </div>
    <div class="Yb_old" id="Yb_new">
      <div class="bigtit"></div>
      <div class="newdatacon">
        <div class="newdata row1"><span><em class="nhll">nhll</em>%</span><span><em class="cjqx">cjqx</em>天</span><span><em>100</em>元</span><span><em id="syje">syje</em>元</span></div>
        <div class="newdata row2"><span>预期年化收益</span><span>出借期限</span><span>最低出借额</span><span>剩余金额</span></div>
      </div>
      <div class="newjindubtn">
        <div class="jindu">
          <div class="jindubg">
            <div class="bluebox"></div>
          </div>
          <div class="jindupercent"><em>tzjd</em>%</div>
        </div>
          <div class="lijitza"></div>
      </div>
     </div>
    </div>
    <div style="clear:both"></div>
    </div>
  
  </div>
  
  
  
  
  <!--点点投产品-->
  <div id="DDfinedatacon" >
  <div class="con DDWarp DDLen02" >
   <div class="newshare">
    <div class="share_left"><div class="share_leftin"><div class="finepro_leftin_L"> <span class="t">点点投2</span> <em>优质散标、债权清晰</em> <div class="finepro_leftin_R"><a href="/portal/lend/DiandianProjects"></a>
     </div></div></div></div>
    <div class="share_right">
    <div class="share_right_box1">
    
      <div class="DDdjs">
      <div id="Djs_DD">
      <div class="bigtit"></div>
      <div class="newdatacon">
        <div class="newdata row1"><span class="tit01"><em></em>%</span><span class="tit01"><em></em>个月</span><span class="DjsDD_date"><em id="Countdown"></em></span></div>
        <div class="newdata row2"><span class="tit01">预期年化收益</span><span class="tit01">出借期限</span><span class="DjsDD_date">距离发标还剩</span></div>
      </div>
      
      </div>
    <div id="Yb_DD" style="display:none;">
    <div class="bigtit"></div>
      <div class="newdatacon">
        <div class="newdata row1"><span class="tit01"><em></em>%</span><span class="tit01"><em> </em>个月</span><span class="tit01">按月返息<br>到期还本</span><span><em id=""></em>元</span><span><em id=""></em>元</span></div>
        <div class="newdata row2"><span class="tit01">预期年化收益</span><span class="tit01">出借期限</span><span class="tit01">回款方式</span><span>最低出借额</span><span>剩余金额</span></div>
      </div>
      <div class="newjindubtn">
        <div class="jindu">
          <div class="jindubg">
            <div class="bluebox"></div>
          </div>
          <div class="jindupercent"><em></em>%</div>
        </div>
        <div class="lijitza" v-else><a href="'Diantzdetails?OID='">立即出借</a></div>
      </div>
    </div>
    
      </div>
      <div class="DDyb">
      <div class="bigtit"></div>
      <div class="newdatacon">
        <div class="newdata row1"><span class="tit01"><em></em>%</span><span class="tit01"><em></em>个月</span><span class="tit01">按月返息<br>到期还本</span><span><em id=""></em>元</span><span><em id=""></em>元</span></div>
        <div class="newdata row2"><span class="tit01">预期年化收益</span><span class="tit01">出借期限</span><span class="tit01">回款方式</span><span>最低出借额</span><span>剩余金额</span></div>
      </div>
      <div class="newjindubtn">
        <div class="jindu">
          <div class="jindubg">
            <div class="bluebox"></div>
          </div>
          <div class="jindupercent"><em></em>%</div>
        </div>
          <div class="lijitza"></div>
      </div>
      </div>
      </div>
      <div class="share_right_box2">
      <div class="DDdjs">
      <div id="Djs_DD">
      <div class="bigtit"></div>
      <div class="newdatacon">
        <div class="newdata row1"><span class="tit01"><em></em>%</span><span class="tit01"><em></em>个月</span><span class="DjsDD_date"><em id="Countdown"></em></span></div>
        <div class="newdata row2"><span class="tit01">预期年化收益</span><span class="tit01">出借期限</span><span class="DjsDD_date">距离发标还剩</span></div>
      </div>      
      </div>
      <div id="Yb_DD" style="display:none;">
    <div class="bigtit"></div>
      <div class="newdatacon">
        <div class="newdata row1"><span class="tit01"><em></em>%</span><span class="tit01"><em></em>个月</span><span class="tit01">按月返息<br>到期还本</span><span><em id=""></em>元</span><span><em id=""></em>元</span></div>
        <div class="newdata row2"><span class="tit01">预期年化收益</span><span class="tit01">出借期限</span><span class="tit01">回款方式</span><span>最低出借额</span><span>剩余金额</span></div>
      </div>
      <div class="newjindubtn">
        <div class="jindu">
          <div class="jindubg">
            <div class="bluebox"></div>
          </div>
          <div class="jindupercent"><em></em>%</div>
        </div>      
        <div class="lijitza" v-else><a href="Diantzdetails?OID=">立即出借</a></div>
      </div>
    </div>
      
      </div>
      <div class="DDyb">
      <div class="bigtit"></div>
      <div class="newdatacon">
        <div class="newdata row1"><span class="tit01"><em></em>%</span><span class="tit01"><em></em>个月</span><span class="tit01">按月返息<br>到期还本</span><span><em id=""></em>元</span><span><em id=""></em>元</span></div>
        <div class="newdata row2"><span class="tit01">预期年化收益</span><span class="tit01">出借期限</span><span class="tit01">回款方式</span><span>最低出借额</span><span>剩余金额</span></div>
      </div>
      <div class="newjindubtn">
        <div class="jindu">
          <div class="jindubg">
            <div class="bluebox"></div>
          </div>
          <div class="jindupercent"><em></em>%</div>
        </div>
          <div class="lijitza"></div>
      </div>
      </div>      
      </div>
    </div>
    <div style="clear:both"></div>
    </div>
  </div> 
  
<div class="DDLen01">
  <div class="con DDWarp DDdjs" >
   <div class="newshare">
    <div class="share_left share_leftOne"><div class="share_leftin"><div class="finepro_leftin_L"> <span class="t">点点投</span> <em>优质散标、债权清晰</em> <div class="finepro_leftin_R"><a href="/portal/lend/DiandianProjects"></a>
     </div></div></div></div>
    <div class="share_right">
    <div class="share_right_box1">
    <div id="Djs_DD">
      <div class="bigtit"></div>
      <div class="newdatacon">
        <div class="newdata row1"><span class="tit01"><em></em>%</span><span class="tit01"><em></em>个月</span><span class="DjsDD_date"><em id="Countdown"></em></span></div>
        <div class="newdata row2"><span class="tit01">预期年化收益</span><span class="tit01">出借期限</span><span class="DjsDD_date">距离发标还剩</span></div>
      </div>
      
      </div>
      <div id="Yb_DD" style="display:none;">
    <div class="bigtit"></div>
      <div class="newdatacon">
        <div class="newdata row1"><span class="tit01"><em></em>%</span><span class="tit01"><em></em>个月</span><span class="tit01">按月返息<br>到期还本</span><span><em id=""></em>元</span><span><em id=""></em>元</span></div>
        <div class="newdata row2"><span class="tit01">预期年化收益</span><span class="tit01">出借期限</span><span class="tit01">回款方式</span><span>最低出借额</span><span>剩余金额</span></div>
      </div>
      <div class="newjindubtn">
        <div class="jindu">
          <div class="jindubg">
            <div class="bluebox"></div>
          </div>
          <div class="jindupercent"><em></em>%</div>
        </div>
        <div class="lijitza"><a href="Diantzdetails?OID=">立即出借</a></div>
      </div>
    </div>
      </div>
    </div>
    <div style="clear:both"></div>
    </div>
  </div>
  <div class="con DDWarp DDyb">
   <div class="newshare">
    <div class="share_left share_leftOne"><div class="share_leftin"><div class="finepro_leftin_L"> <span class="t">点点投</span> <em>优质散标、债权清晰</em> <div class="finepro_leftin_R"><a href="/portal/lend/DianProjects"></a>
     </div></div></div></div>
    <div class="share_right">
    <div class="share_right_box1">
      <div class="bigtit"></div>
      <div class="newdatacon">
        <div class="newdata row1"><span class="tit01"><em></em>%</span><span class="tit01"><em></em>个月</span><span class="tit01">按月返息<br>到期还本</span><span><em id=""></em>元</span><span><em id=""></em>元</span></div>
        <div class="newdata row2"><span class="tit01">预期年化收益</span><span class="tit01">出借期限</span><span class="tit01">回款方式</span><span>最低出借额</span><span>剩余金额</span></div>
      </div>
      <div class="newjindubtn">
        <div class="jindu">
          <div class="jindubg">
            <div class="bluebox"></div>
          </div>
          <div class="jindupercent"><em></em>%</div>
        </div>
          <div class="lijitza" ></div>
      </div>
      </div>
    </div>
    <div style="clear:both"></div>
    </div>
  </div>
  </div>
  </div> 
  <!--点点投产品结束-->
  
  <!--优选标的-->
  <div class=" con finepro" id="HHtabright">
    <div class="finepro_left">
    <div class="finepro_leftin">
    <div class="finepro_leftin_L">
    <span class="t">环环投</span> <em>分散出借、自动匹配</em>
     <div class="finepro_leftin_R">
     <a href="/portal/lend/HuanhuanProjects"></a>
     </div>
     
     </div>
      <div class="promodule" style="display:none"> <a  > <span class="sp1">点点投</span> <span class="sp2">定投点点&nbsp;&nbsp;收益翻天</span> </a> <a class="active"> <span class="sp1">环环投</span> <span class="sp2">聚惠环环&nbsp;&nbsp;收益结缘</span> </a> <a  > <span class="sp1">转转投</span> <span class="sp2">专注债转&nbsp;&nbsp;收益无险</span> </a> </div>
    </div>
    <%-- <div class="brick_people"><img src="image/app_IndexUI/brick_people.png"></div> --%>   <!-- app上线ui改版  修改 -->
    </div>
    
    <div class="finepro_right">
    <!--环环投-->
    <!--环环投-->
    <div class="tabright hh " >
      <div class="finedatacon">
      <!-- 第一排 top -->
      <div class="hh_top_wrap">
      <div class="hh_top_wrap_L finedataHH01 ">
      <div class="HHdjs">
      <div id="Djs_wrap">
      <div class="recommendBox">
      <div class="borderimg"></div> 
      <div class="finetitle"></div>
      <div class="recommend_con">
      <div class="hhdata row01">
      <span><em></em>%
      </span>
      </div>
      <div class="hhdata row02">
      <span>预期年化收益</span>
      </div>
      <div class="hhdata_btm ">距离发标还剩</div>
      </div>
      <div class="HHbtn hide">
	          <em class="ktjeHH"></em>
	          <em class="productStatusHH"></em>
	  </div>
      <div class="cell row3">
	   <div class="Djs_Date" id="Countdown"></div>  
      </div>
      </div>
      </div>
      <div id="Yb_wrap">
      <div class="recommendBox">
      <div class="borderimg"></div> 
      <div class="finetitle"></div>
      <div class="recommend_con">
      <div class="hhdata row01">
      <span><em></em>%
      </span>
      </div>
      <div class="hhdata row02">
      <span>预期年化收益</span>
      </div>
      <div class="hhdata_btm"><span class="cjqx"></span><span class="hkfs"></span><span class="clear"></span></div>
      </div>
      <div class="HHbtn hide">
	          <em class="ktjeHH"></em>
	          <em class="productStatusHH"></em>
	  </div>
      <div class="cell row3">
      </div>
      </div>
      </div>
      
      </div>
      <div class="recommendBox HHyb">
      <div class="borderimg" ></div> 
      <div class="finetitle"></div>
      <div class="recommend_con">
      <div class="hhdata row01">
      <span><em></em>%
      </span>
      </div>
      <div class="hhdata row02">
      <span>预期年化收益</span>
      </div>
      <div class="hhdata_btm"><span class="cjqx"></span><span class="hkfs"></span><span class="clear"></span></div>
      </div>
      <div class="HHbtn hide">
	          <em class="ktjeHH"></em>
	          <em class="productStatusHH"></em>
	  </div>
      <div class="cell row3" >
      </div>
      </div>
        <div class="recommendBox recommendBox02 hide">
        <img src="/images/dual_system/HHbrick_liu.png">
        <div class="ingtext">优质标的正在路上，敬请期待吧！</div>
        </div>
      </div>
      <div class="hh_top_wrap_L hh_top_wrap_R finedataHH02">
      <div class="HHdjs">
      <div id="Djs_wrap">
      <div class="recommendBox">
      <div class="borderimg"></div> 
      <div class="finetitle"></div>
      <div class="recommend_con">
      <div class="hhdata row01">
      <span><em></em>%
      </span>
      </div>
      <div class="hhdata row02">
      <span>预期年化收益</span>
      </div>
      <div class="hhdata_btm ">距离发标还剩</div>
      </div>
      <div class="HHbtn hide">
	          <em class="ktjeHH"></em>
	          <em class="productStatusHH"></em>
	  </div>
      <div class="cell row3">
	   <div class="Djs_Date" id="Countdown"></div>  
      </div>
      </div>
      </div>
      <div id="Yb_wrap">
      <div class="recommendBox">
      <div class="borderimg"></div> 
      <div class="finetitle"></div>
      <div class="recommend_con">
      <div class="hhdata row01">
      <span><em></em>%
      </span>
      </div>
      <div class="hhdata row02">
      <span>预期年化收益</span>
      </div>
      <div class="hhdata_btm"><span class="cjqx">个月</span><span class="hkfs"></span><span class="clear"></span></div>
      </div>
      <div class="HHbtn hide">
	          <em class="ktjeHH"></em>
	          <em class="productStatusHH"></em>
	  </div>
      <div class="cell row3">
      </div>
      </div>
      </div>
      
      </div>
      <div class="recommendBox HHyb" >
      <div class="borderimg" ></div> 
      <div class="finetitle"></div>
      <div class="recommend_con">
      <div class="hhdata row01">
      <span><em></em>%
      </span>
      </div>
      <div class="hhdata row02">
      <span>预期年化收益</span>
      </div>
      <div class="hhdata_btm"><span class="cjqx">个月</span><span class="hkfs"></span><span class="clear"></span></div>
      </div>
      <div class="HHbtn hide">
	          <em class="ktjeHH"></em>
	          <em class="productStatusHH"></em>
	  </div>
      <div class="cell row3" >  
      </div>
      </div>
        <div class="recommendBox recommendBox02 hide">
        <img src="/images/dual_system/HHbrick_liu.png">
        <div class="ingtext">优质标的正在路上，敬请期待吧！</div>
        </div>
      </div>
        
      <div class="clear"></div>
      </div>
       <!-- 第二排 bottom -->
      <div class="hh_top_wrap hh_btm_wrap">
      <div class="hh_top_wrap_L finedataHH03">
      <div class="HHdjs">
      <div id="Djs_wrap">
      <div class="recommendBox">
      <div class="borderimg"></div> 
      <div class="finetitle"></div>
      <div class="recommend_con">
      <div class="hhdata row01">
      <span><em></em>%
      </span>
      </div>
      <div class="hhdata row02">
      <span>预期年化收益</span>
      </div>
      <div class="hhdata_btm ">距离发标还剩</div>
      </div>
      <div class="HHbtn hide">
	          <em class="ktjeHH"></em>
	          <em class="productStatusHH"></em>
	  </div>
      <div class="cell row3">
	   <div class="Djs_Date" id="Countdown"></div>  
      </div>
      </div>
      </div>
      <div id="Yb_wrap">
      <div class="recommendBox">
      <div class="borderimg"></div> 
      <div class="finetitle"></div>
      <div class="recommend_con">
      <div class="hhdata row01">
      <span><em></em>%
      </span>
      </div>
      <div class="hhdata row02">
      <span>预期年化收益</span>
      </div>
      <div class="hhdata_btm"><span class="cjqx"></span><span class="hkfs"></span><span class="clear"></span></div>
      </div>
      <div class="HHbtn hide">
	          <em class="ktjeHH"></em>
	          <em class="productStatusHH"></em>
	  </div>
      <div class="cell row3">           
      </div>
      </div>
      </div>
      </div>
      <div class="recommendBox HHyb">
      <div class="borderimg"></div> 
      <div class="finetitle"></div>
      <div class="recommend_con">
      <div class="hhdata row01">
      <span><em></em>%
      </span>
      </div>
      <div class="hhdata row02">
      <span>预期年化收益</span>
      </div>
      <div class="hhdata_btm"><span class="cjqx"></span><span class="hkfs"></span><span class="clear"></span></div>
      </div>
      <div class="HHbtn hide">
	          <em class="ktjeHH"></em>
	          <em class="productStatusHH"></em>
	  </div>
      <div class="cell row3">          
      </div>
      </div>
        <div class="recommendBox recommendBox02 hide">
        <img src="/images/dual_system/HHbrick_liu.png">
        <div class="ingtext">优质标的正在路上，敬请期待吧！</div>
        </div>
      </div>
      <div class="hh_top_wrap_L hh_top_wrap_R finedataHH04">
      <div class="HHdjs">
      <div id="Djs_wrap">
      <div class="recommendBox">
      <div class="borderimg"></div> 
      <div class="finetitle"></div>
      <div class="recommend_con">
      <div class="hhdata row01">
      <span><em></em>%
      </span>
      </div>
      <div class="hhdata row02">
      <span>预期年化收益</span>
      </div>
      <div class="hhdata_btm ">距离发标还剩</div>
      </div>
      <div class="HHbtn hide">
	          <em class="ktjeHH"></em>
	          <em class="productStatusHH"></em>
	  </div>
      <div class="cell row3">
	   <div class="Djs_Date" id="Countdown"></div>  
      </div>
      </div>
      </div>
      <div id="Yb_wrap">
      <div class="recommendBox">
      <div class="borderimg"></div> 
      <div class="finetitle"></div>
      <div class="recommend_con">
      <div class="hhdata row01">
      <span><em></em>%
      </span>
      </div>
      <div class="hhdata row02">
      <span>预期年化收益</span>
      </div>
      <div class="hhdata_btm"><span class="cjqx"></span><span class="hkfs"></span><span class="clear"></span></div>
      </div>
      <div class="HHbtn hide">
	          <em class="ktjeHH"></em>
	          <em class="productStatusHH"></em>
	  </div>
      <div class="cell row3"></div>
      </div>
      </div>
      </div>
      <div class="recommendBox HHyb">
      <div class="borderimg"></div> 
      <div class="finetitle"></div>
      <div class="recommend_con">
      <div class="hhdata row01">
      <span><em></em>%
      </span>
      </div>
      <div class="hhdata row02">
      <span>预期年化收益</span>
      </div>
      <div class="hhdata_btm"><span class="cjqx"></span><span class="hkfs"></span><span class="clear"></span></div>
      </div>
      <div class="HHbtn hide">
	          <em class="ktjeHH"></em>
	          <em class="productStatusHH"></em>
	  </div>
      <div class="cell row3"></div>
      </div>
        <div class="recommendBox recommendBox02 hide">
        <img src="/images/dual_system/HHbrick_liu.png">
        <div class="ingtext">优质标的正在路上，敬请期待吧！</div>
        </div>
      </div>
      <div class="clear"></div>
      </div>
       
      </div>      
    </div>
    <!--转转投-->

    </div>
  </div>
  <!-- 新闻动态 ************************************ 平台公告 -->
       <div class="con News_wrap">
      <div class="meiti_left mei_left_new">
    <web:ContentLeafChildPartPage name="content" OID="797bc7270a5dca7108cbd4b22bc846cd" order="order by addDateTime desc"/>
   
   
   <div class="meiti_box">
   <div class="meiti_top "><div class="title_top">答疑入口</div><div style="clear:both;"></div></div>
   <div class="new_img">
   <a href="ZTYMain/P2PpunishWork_ZTY"><img style="width:338px;" src="/image/P2Ppunish_ZTY/questions_entrance.jpg"></a>
   
   </div>
  
   <div style="clear:both;"></div>
   </div>
   
   </div>
   <div class="meiti_left mei_left_new">
   <div class="meiti_box">
   <div class="meiti_top"><div class="title_top">媒体报道</div><div class="tilte_sign"><a href="/portal/page/mediaNews">更多 》</a></div><div style="clear:both;"></div></div>
   <div class="new_list">
   <ul>
   
    </ul>
   </div>
   <div style="clear:both;"></div>
   </div>
   </div>
   <div class="meiti_right">
   <div class="meiti_box">
   <div class="meiti_top"><div class="title_top">平台公告</div><div class="tilte_sign"><a href="/portal/page/notice">更多 》</a></div><div style="clear:both;"></div></div>
   <div class="new_list2">
   <ul>
   
    </ul>
   </div>
   <div style="clear:both;"></div>
   </div>
   </div>
   <div style="clear:both;"></div>
  </div>       
  
</div>

<div class="cooperate_wrap">
  <div class="cooperate_tit">合作伙伴</div>
  <div class="cooperate_con">
  <div class="cooperate_main">
  <div class="cooperate_box cooperate_box01"><a target="_blank" href="https://www.aliyun.com/"></a></div>
  <div class="cooperate_box cooperate_box02"><a target="_blank" href="https://www.tsign.cn/"></a></div>
  <div class="cooperate_box cooperate_box03"><a target="_blank" href="https://www.fuiou.com/"></a></div>
  <div class="cooperate_box cooperate_box04"><a target="_blank" href="https://www.bangcle.com/"></a></div>
  <div class="clear"></div>
  </div>
  </div>
  </div>
<!-----------------中间部分结束----------------> 
<%@ include file="footer.jsp" %>
<%}catch(Exception e){
	response.sendRedirect("error.jsp");
    return;  
} %>
</body>
<script>
//进度 百分比
	$('#indicatorContainer1').radialIndicator({
        radius: 25,
		barColor: '#3bbce6',
        barWidth: 4,
        initValue: 75,
        roundCorner: true,
        percentage: true,
		
    });
	$('#indicatorContainer2').radialIndicator({
        radius: 25,
		barColor: '#cccccc',
        barWidth: 4,
        initValue: 100,
        roundCorner: true,
        percentage: true,
		
    });
	$('#indicatorContainer3').radialIndicator({
        radius: 25,
		barColor: '#cccccc',
        barWidth: 4,
        initValue: 100,
        roundCorner: true,
        percentage: true,
		
    });
	//   aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa

	
	
  //数字滚动
    function numRoll(obj,num){
        obj.each(function (i,d){
            if(parseInt($(d).html()) < parseInt($(d).attr('data-val'))){
                $(d).html(parseInt($(d).html()) + num);
            }else{
                obj.each(function (i,d){
                    $(d).html($(d).attr('data-val'));
                })
                clearInterval(obj.t);
            }
        })
    }; 
</script>
<script type="text/javascript">
(function($){

	$.fn.myScroll = function(options){
	//默认配置
	var defaults = {
		speed:40,  //滚动速度,值越大速度越慢
		rowHeight:32 //每行的高度
	};
	
	var opts = $.extend({}, defaults, options),intId = [];
	
	function marquee(obj, step){
	
		obj.find("ul").animate({
			marginTop: '-=1'
		},0,function(){
				var s = Math.abs(parseInt($(this).css("margin-top")));
				if(s >= step){
					$(this).find("li").slice(0, 1).appendTo($(this));
					$(this).css("margin-top", 0);
				}
			});
		}
		
		this.each(function(i){
			var sh = opts["rowHeight"],speed = opts["speed"],_this = $(this);
			intId[i] = setInterval(function(){
				if(_this.find("ul").height()<=_this.height()){
					clearInterval(intId[i]);
				}else{
					marquee(_this, sh);
				}
			}, speed);

			_this.hover(function(){
				clearInterval(intId[i]);
			},function(){
				intId[i] = setInterval(function(){
					if(_this.find("ul").height()<=_this.height()){
						clearInterval(intId[i]);
					}else{
						marquee(_this, sh);
					}
				}, speed);
			});
		
		});

	}

})(jQuery);

$(window).load(function() {

	$(".new_list").myScroll({
		speed:40,
		rowHeight:33
	});
	
});
</script>
<script>
var _hmt = _hmt || [];
(function() {
  var hm = document.createElement("script");
  hm.src = "https://hm.baidu.com/hm.js?23b38889d6af60a6a5181196d7b0d859";
  var s = document.getElementsByTagName("script")[0]; 
  s.parentNode.insertBefore(hm, s);
})();
</script>
<!--验证表单-->
<script src="/js/jquery.validate.js"></script>
<%-- <script src="/js/activity_index.js"></script> --%>
<!-- 砖头狂欢  -->
<script type="text/javascript" src="/js/classie.js"></script>
<!-- 砖头狂欢 end -->
<script type="text/javascript" src="/js/indexPorts.js"></script>
<script>
//首页所有投资进度
$(".jindupercent").each(function(){
	var thisjindu = $(this).children("em").text()+"%";
	//var thisjindu = "50%";
	//alert(thisjindu);
	var activejindu = $(this).prev(".jindubg").children(".bluebox");
	//activejindu.css("width",thisjindu);
	activejindu.animate({width:thisjindu});
	
	
});
$(window).load(function() {
	/* Banner  */
	$.ajax({
        type: "get",
        url: "/portal/user/threeNumber",
        dataType: "json",
        success: function(result){
        		$(".afterdata01 em").text((result.dataValue.cybj).toFixed(2));
        		$(".afterdata02 em").text((result.dataValue.yjsy).toFixed(2));
        		$(".afterdata03 em").text((result.dataValue.tqsyds).toFixed(2));
        },
		error:function(result){    	
    	}
    });
	$.ajax({
        type: "get",
        url: "/portal/page/getbanner",
        dataType: "json",
        success: function(result){
        		for(var i=0;i<result.dataValue.length;i++){
        			var str="<li style='background: url(https://www.izhuantou.com/p2p/cn/com/hoonsoft/servlet/ServletAction?handleClassName=fileDownload&OID="+result.dataValue[i].oid+") 50% 0 no-repeat;'><a href='"+result.dataValue[i].jumpurl+"' data-val='"+result.dataValue[i].name+"'> </a></li>";  
        			$(".slides").append(str);
        		}
        },
		error:function(result){    	
    	}
    });
	
	/*  头笔赚  */
	$.ajax({
        type: "POST",//请求方式
        url: "/portal/index/touBiZhuan",//地址，就是json文件的请求路径
        dataType: "json",//数据类型可以为 text xml json  script  jsonp
        success: function(result){//返回的参数就是 action里面所有的有get和set方法的参数
        	$(".newcome .bigtit").text(result.biddingName);
        	$(".newcome .nhll").text(result.rate);
        	$(".newcome .cjqx").text(result.days);
        	$(".newcome #syje").text(result.sy);
        	$(".newcome .jindupercent em").text(result.process);
        	var strBtn="";
        	if(result.productStatus==10){
                strBtn="<a style=\"background:#ababab;\" href='/portal/detial/Newtzdetails.jsp?OID="+result.OID+"'>立即出借</a>";
            }else if(result.productStatus==2 || result.productStatus==3 || result.productStatus==5){
            	strBtn="<a style=\"background:#ababab;\" href='/portal/detial/Newtzdetails.jsp?OID="+result.OID+"'>已满标</a>";
            }else {
            	strBtn="<a href='/portal/detial/Newtzdetails.jsp?OID="+result.OID+"'>立即出借</a>";
            }
        	$(".newcome .lijitza").append(strBtn);
        	if((result.szds==1) && (result.dsTime>0)){
        	    var NewDjs="<div class=\"bigtit\">"+result.biddingName+"</div>\n" +
        	        "      <div class=\"newdatacon\">\n" +
        	        "        <div class=\"newdata row1\"><span><em>"+result.rate+"</em>%</span><span><em>"+result.days+"</em>天</span><span class=\"DjsNew_date\"><em id=\"Countdown\">"+result.sj+"</em></span></div>\n" +
        	        "        <div class=\"newdata row2\"><span>预期年化收益</span><span>出借期限</span><span class=\"DjsNew_date\">距离发标还剩</span></div>\n" +
        	        "      </div>";
        	        $("#Djs_new").append(NewDjs);
        	    $(".Yb_old").attr("id","Yb_new");
        	    (function(){
        	          var tid = setInterval(function () {
        	          var oTimebox = $(".newcome #Djs_new #Countdown");
        	          var syTime = oTimebox.text();
        	          var totalSec = getTotalSecond(syTime) - 1;
        	          //alert(syTime);
        	          if (totalSec >= 0) {
        	              oTimebox.text(getNewSyTime(totalSec));
        	          } else {
        	              clearInterval(tid);
        	              // window.location.reload();
        	              $(".newcome #Djs_new").css("display","none").siblings(".newcome #Yb_new").css("display","block");
        	              $(".newcome #Yb_new .lijitza a").css("background","#55b0fd");
        	          }
        	      	}, 1000);
        	        })();
        	}else{
        		$(".Yb_old").attr("id","");
        	}
        },
		error:function(result){    	
    	}
    });
	
	
	/* new Vue({
	    el: '#HHtabright',
	    data:{
	    	gridData:'',
	      },
	      mounted: function() {
              this.$nextTick(function () {
                  this.$http.get('/portal/index/HHTou').then(function(result) {
                      console.log(result.data);
                      this.gridData = result.data;
                      //$("#HHtabright").css("opacity","1");
                  })
              })
          },
	     
	
	}); */
});

$(window).load(function() {
	$.ajax({
        type: "get",//请求方式
        url: "/portal/index/fourNum",//地址，就是json文件的请求路径
        dataType: "json",//数据类型可以为 text xml json  script  jsonp
        success: function(result){//返回的参数就是 action里面所有的有get和set方法的参数
        $(".data-num01").attr("data-val",result.money1);
        $(".data-num01").text(result.money1);
        $(".data-num02").attr("data-val",result.insertMoney1);
        $(".data-num02").text(result.insertMoney1);
        $(".data-num03").attr("data-val",result.member1);
        $(".data-num03").text(result.member1);
        $(".data-num04").attr("data-val",result.sumDay1);
        $(".data-num04").text(result.sumDay1);
        },
		error:function(result){
    	}
    });
});
$(window).load(function() {
	$.ajax({
        type: "get",//请求方式
        url: "/portal/index/TYMoney",//地址，就是json文件的请求路径
        dataType: "json",//数据类型可以为 text xml json  script  jsonp
        success: function(result){//返回的参数就是 action里面所有的有get和set方法的参数
        $("#tyjNum").text(result.experienceMember);
        },
		error:function(result){
    	}
    });
});

 $("#syje").each(function(){
	var syje = $(this).text();
	//alert(syje);
	if(syje==0){
		$(this).parents().parents().next(".newjindubtn").find(".lijitza").addClass("indexnewprobtn");
		$(this).parents().parents().next(".newjindubtn").find(".lijitza a").text("已满标");
		//$(this).parents(".jindu").nextAll().children(".lijitza a").removeAttr("href");//不可点击		
	} 
 });
//优选标的的进度为100时按钮为灰色已满表
$(".ktjepercent .ktje").each(function(){
	var ktje = $(this).text();	
	if(ktje==0){
		$(this).parents().parents().next(".row3").find("a").addClass("manbiao");
		$(this).parents().parents().next(".row3").find("a").text("已满标");
		
		
	}
});
 
/* //优选标的的进度为100时按钮为灰色已满表
$(".jindupercent .jd").each(function(){
	var thisjindu = $(this).text();	
	if(thisjindu=="100"){
		$(this).parents(".cell").nextAll().children(".cell.row3 a").addClass("manbiao");
		$(this).parents(".cell").nextAll().children(".cell.row3 a").text("已满标");
		//$(this).parents(".cell").nextAll().children(".cell.row3 a").removeAttr("href");//不可点击
		//$(this).parents(".finedata").children(".cell.row3 a").addClass("manbiao");
		//$(this).parents(".finedata").children(".cell.row3 a").removeAttr("href");
		
	}
}); */
//环环投剩余可投金额为0时显示预约出借，状态10时为停标背景变灰色
/* $(".HHbtn").each(function(){	
	var ktjeHH = Number($(this).children("em.ktjeHH").text());
	var productStatusHH = $(this).children("em.productStatusHH").text();	
	if(productStatusHH=="10"){//停标状态		
		$(this).next(".cell.row3").children("a").addClass("tingbiao");
		if(ktjeHH<=0){//可投金额小于等于0 预约出借
			$(this).next(".cell.row3").children("a").text("预约出借");
		}else{
			$(this).next(".cell.row3").children("a").text("立即出借");
		}
	}else{//非停标状态
		if(ktjeHH<=0){
			$(this).next(".cell.row3").children("a").addClass("yuyue");
			$(this).next(".cell.row3").children("a").text("预约出借");
		}else{
			$(this).next(".cell.row3").children("a").text("立即出借");
		}
	}
	
}) */


/* 平台公告  */
$(window).load(function() {
	$.ajax({
        type: "get",//请求方式
        url: "/portal/page/getmediaNewsname",//地址，就是json文件的请求路径
        dataType: "json",//数据类型可以为 text xml json  script  jsonp
        success: function(result){//返回的参数就是 action里面所有的有get和set方法的参数
        for(var i=0;i<6;i++){
        	var str="<li class='newssubject'><a title='"+result.dataValue[i].titleName+"' class='a01' href='/portal/page/mediaNews_details?OID="+result.dataValue[i].oid+"' >"+result.dataValue[i].titleName+"</a></li>";
        	$(".new_list ul").append(str);
        }
        },
		error:function(result){
    	}
    });
	$.ajax({
        type: "get",//请求方式
        url: "/portal/page/getnoticename",//地址，就是json文件的请求路径
        dataType: "json",//数据类型可以为 text xml json  script  jsonp
        success: function(result){//返回的参数就是 action里面所有的有get和set方法的参数
        for(var i=0;i<6;i++){
        	var str="<li class='newssubject'><a title='"+result.dataValue[i].titleName+"' href='/portal/page/notice_details?OID="+result.dataValue[i].oid+"' class='a01'><span class='gong_title'>"+result.dataValue[i].titleName+"</span><span class='gong_data'>"+result.dataValue[i].addTime+"</span><div style='clear:both;'></div></a></li>";
        	$(".new_list2 ul").append(str);
        }
        },
		error:function(result){
    	}
    });
});

</script>

</html>
