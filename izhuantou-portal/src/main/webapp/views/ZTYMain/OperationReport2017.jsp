<%@ page contentType="text/html; charset=UTF-8" %>
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<title>砖头网-2017年年度运营报告</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<link rel="stylesheet" type="text/css" href="/css/style-common.css">
<link href="/css/normalize.css" rel="stylesheet" type="text/css">
<link href="/css/OperationCss/OperationReportCss.css" rel="stylesheet" type="text/css">
<Link rel="shortcut icon" href="/images/zhuan.ico" />
<script type="text/javascript" src="/js/jquery-1.10.1.min.js"></script>
<script>
$(function(){
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
<script src="/js/com.js?ver=114"></script>
<style>

</style>
</head>

<body class="OperationReport2017">
<% try{ %>
<%@ include file="../header.jsp" %>
<div class="addbox" style="height:79px;display:none;"></div>
<div class="ReportBanner">
    <img src="/image/OperationReport2017/banner.jpg">
</div>
<div class="ReportModule">
    <div class="Report_tit"></div>
    <p>截止至2017年12月31日</p>
    <div class="Report_con">
    <div class="TradingDataWrap">
    <div class="TradingDataBox">
    <img src="/image/OperationReport2017/TraingDataImg01.jpg">
    <p>累计交易额<span>337,615,309</span>元</p> 
    </div>
    <div class="TradingDataBox">
    <img src="/image/OperationReport2017/TraingDataImg02.jpg">
    <p>交易总笔数<span>8,433</span>笔</p> 
    </div>
    <div class="TradingDataBox TradingDataBox02">
    <img src="/image/OperationReport2017/TraingDataImg03.jpg">
    <p>为用户赚取利益<span>1,869,500</span>元</p> 
    </div>
    <div class="TradingDataBox">
    <img src="/image/OperationReport2017/TraingDataImg04.jpg">
    <p>项目总数<span>1,583</span>个</p> 
    </div>
    <div class="TradingDataBox">
    <img src="/image/OperationReport2017/TraingDataImg05.jpg">
    <p>累计用户数<span>36,725</span>人</p> 
    </div>
    <div class="TradingDataBox TradingDataBox02">
    <img src="/image/OperationReport2017/TraingDataImg06.jpg">
    <p>活跃用户数<span>3,952</span>人</p> 
    </div>
    <div class="clear"></div>
    </div>
    
    </div>
</div>
<div class="ReportModule ReportModule02">
    <div class="Report_tit"></div>
    <div class="Report_con">
    <div class="InvestmentWrap">
    <div class="InvestmentBox">
    <div class="Investmentcontent">
    <p>累计出借人数</p>
    <h3><span>3,952</span>人</h3>
    </div>
    </div>
    <div class="InvestmentBox InvestmentBox02">
    <div class="Investmentcontent">
    <p>累计交易金额</p>
    <h3><span>337,615,309</span>元</h3>
    </div>
    </div>
    <div class="InvestmentBox InvestmentBox03">
    <div class="Investmentcontent">
    <p>人均累计交易金额</p>
    <h3><span>85,429</span>元/人</h3>
    </div>
    </div>
    <div class="InvestmentBox InvestmentBox04" >
    <div class="Investmentcontent">
    <p>笔均累计交易金额</p>
    <h3><span>40,035</span>元/笔</h3>
    </div>
    </div>
    <div class="clear"></div> 
    </div>
    
    
    </div>
    <p>男性出借人数略高于女性，出借金额却低于女性，出借人数占比50.73%，出借金额占比48.79%</p>
    <div class="Report_con"><img src="/image/OperationReport2017/data03.jpg"></div>
    <p>出借人集中在80后，出借人数占比37.4%，出借金额占比39.75%</p>
    <div class="Report_con"><img src="/image/OperationReport2017/data04.jpg"></div>
    <p>出借人数TOP10区域</p>
    <div class="Report_con"><img src="/image/OperationReport2017/data05.jpg"></div>
    <p>出借星座排行榜</p>
    <div class="Report_con"><img src="/image/OperationReport2017/data06.jpg"></div>
    <p>2017年度累计出借金额TOP10</p>
    <div class="Report_con"><img src="/image/OperationReport2017/data07.jpg"></div>
</div>
<div class="ReportModule ReportModule03">
    <div class="Report_tit"></div>
    <div class="Report_con">
    <div class="InvestmentWrap LoanWrap">
    <div class="InvestmentBox">
    <div class="Investmentcontent">
    <p>累计借款人数</p>
    <h3><span>1,582</span>人</h3>
    </div>
    </div>
    <div class="InvestmentBox InvestmentBox02">
    <div class="Investmentcontent">
    <p>累计交易金额</p>
    <h3><span>337,615,309</span>元</h3>
    </div>
    </div>
    <div class="InvestmentBox InvestmentBox03">
    <div class="Investmentcontent">
    <p>人均累计借款金额</p>
    <h3><span>73,979</span>元/人</h3>
    </div>
    </div>
    <div class="InvestmentBox InvestmentBox04" >
    <div class="Investmentcontent">
    <p>笔均累计借款金额</p>
    <h3><span>73,932</span>元/笔</h3>
    </div>
    </div>
    <div class="clear"></div> 
    </div>
    </div>
    <p>男性借款人远远多于女性借款人，人数占比71.18%，借款金额占比71.09%</p>
    <div class="Report_con Report_con02"><img src="/image/OperationReport2017/data09.jpg"></div>
    <p>借款人年龄集中在70、80后，借款人数占比70.42%，借款金额占比71.56%</p>
    <div class="Report_con Report_con02"><img src="/image/OperationReport2017/data10.jpg"></div>

</div>

<div class="ReportModule ReportModule04">
    <div class="Report_tit"></div>
    <div class="Report_con">
    <div class="ProgectDataWrap">
    <div class="ProgectDataBox">
   <img src="/image/OperationReport2017/PlatformDataImg01.jpg">
    <p>待还本金<span>83,675,191</span>元</p> 
    </div>
    <div class="ProgectDataBox">
   <img src="/image/OperationReport2017/PlatformDataImg02.jpg">
    <p>逾期本金<span>554,056</span>万元</p> 
    </div>
    <div class="ProgectDataBox ProgectDataBox02">
   <img src="/image/OperationReport2017/PlatformDataImg03.jpg">
    <p>项目逾期率<span>0.82</span>%</p> 
    </div>
    <div class="ProgectDataBox ProgectDataBox03">
   <img src="/image/OperationReport2017/PlatformDataImg04.jpg">
    <p>金额逾期率<span>0.66</span>%</p> 
    </div>
    <div class="clear"></div>
    
    </div>
    </div>
    <p>砖头网主要以小额借款为主，单笔借款金额在10万以下的借款个数占比93.24%，借款金额占比83.34%</p>
    <div class="Report_con Report_con02"><img src="/image/OperationReport2017/data11.jpg"></div>
    <p>借款项目多为12个月期及以下产品，借款个数占比70.06%，借款金额占比62.8%</p>
    <div class="Report_con Report_con02"><img src="/image/OperationReport2017/data12.jpg"></div>
    <div class="Report_con Report_con03"><img src="/image/OperationReport2017/data13.jpg"></div>
</div>


<div class="ReportModule ReportModule05">
    <div class="Report_tit"></div>
    <div class="Report_con">
        <div class="activeBox">
            <div class="activeBox_s"><img src="/image/OperationReport2017/active01.jpg">
                <div class="active_jiaoT"></div>
                <div class="active_jiaoB"></div>
            </div>
            <h3>砖头直播季</h3>
            <p>4月、5月</p>
        </div>
        <div class="activeBox">
            <div class="activeBox_s"><img src="/image/OperationReport2017/active02.jpg">
                <div class="active_jiaoT"></div>
                <div class="active_jiaoB"></div>
            </div>
            <h3>砖头狂欢日</h3>
            <p>6月6日</p>
        </div>
        <div class="activeBox activeBox02">
            <div class="activeBox_s"><img src="/image/OperationReport2017/active03.jpg">
                <div class="active_jiaoT"></div>
                <div class="active_jiaoB"></div>
            </div>
            <h3>你和你的钱  都需要安全感</h3>
            <p>7月24-31日</p>
        </div>
        <div class="activeBox">
            <div class="activeBox_s"><img src="/image/OperationReport2017/active04.jpg">
                <div class="active_jiaoT"></div>
                <div class="active_jiaoB"></div>
            </div>
            <h3>高温暴雨难耐  砖头网帮你“解解暑”</h3>
            <p>7月12-17日</p>
        </div>
        <div class="activeBox">
            <div class="activeBox_s"><img src="/image/OperationReport2017/active05.jpg">
                <div class="active_jiaoT"></div>
                <div class="active_jiaoB"></div>
            </div>
            <h3>全民返现金  惊喜等着你</h3>
            <p>7月6-16日</p>
        </div>
        <div class="activeBox activeBox02">
            <div class="activeBox_s"><img src="/image/OperationReport2017/active06.jpg">
                <div class="active_jiaoT"></div>
                <div class="active_jiaoB"></div>
            </div>
            <h3>砖头陪你过七夕  单身狗拒绝孤单</h3>
            <p>8月14-23日</p>
        </div>

        <div class="activeBox">
            <div class="activeBox_s"><img src="/image/OperationReport2017/active07.jpg">
                <div class="active_jiaoT"></div>
                <div class="active_jiaoB"></div>
            </div>
            <h3>你体验  我买单</h3>
            <p>8月1-15日</p>
        </div>
        <div class="activeBox">
            <div class="activeBox_s"><img src="/image/OperationReport2017/active08.jpg">
                <div class="active_jiaoT"></div>
                <div class="active_jiaoB"></div>
            </div>
            <h3>交易额破亿  实力送现金</h3>
            <p>8月21日</p>
        </div>
        <div class="activeBox activeBox02">
            <div class="activeBox_s"><img src="/image/OperationReport2017/active09.jpg">
                <div class="active_jiaoT"></div>
                <div class="active_jiaoB"></div>
            </div>
            <h3>开学第一课  看谁投资多</h3>
            <p>7月24-31日</p>
        </div>
        <div class="activeBox">
            <div class="activeBox_s"><img src="/image/OperationReport2017/active10.jpg">
                <div class="active_jiaoT"></div>
                <div class="active_jiaoB"></div>
            </div>
            <h3>欢迎你到我的新家来做客</h3>
            <p>9月20、21日</p>
        </div>
        <div class="activeBox">
            <div class="activeBox_s"><img src="/image/OperationReport2017/active11.jpg">
                <div class="active_jiaoT"></div>
                <div class="active_jiaoB"></div>
            </div>
            <h3>新客专享  福利叠加</h3>
            <p>9月14日-10月15日</p>
        </div>
        <div class="activeBox activeBox02">
            <div class="activeBox_s"><img src="/image/OperationReport2017/active12.jpg">
                <div class="active_jiaoT"></div>
                <div class="active_jiaoB"></div>
            </div>
            <h3>月圆圆，钱滚滚！</h3>
            <p>9月19-29日</p>
        </div>
        <div class="activeBox">
            <div class="activeBox_s"><img src="/image/OperationReport2017/active13.png">
                <div class="active_jiaoT"></div>
                <div class="active_jiaoB"></div>
            </div>
            <h3>十一“横”行出游  玩转结点卡位战</h3>
            <p>10月1-8日</p>
        </div>
        <div class="activeBox">
            <div class="activeBox_s"><img src="/image/OperationReport2017/active14.png">
                <div class="active_jiaoT"></div>
                <div class="active_jiaoB"></div>
            </div>
            <h3>十月投资竞额赛 “i”赚“疯”了</h3>
            <p>10月1-31日</p>
        </div>
        <div class="activeBox activeBox02">
            <div class="activeBox_s"><img src="/image/OperationReport2017/active15.png">
                <div class="active_jiaoT"></div>
                <div class="active_jiaoB"></div>
            </div>
            <h3>砖头网送你1.1倍出借积分</h3>
            <p>10月16日</p>
        </div>
        <div class="activeBox">
            <div class="activeBox_s"><img src="/image/OperationReport2017/active16.png">
                <div class="active_jiaoT"></div>
                <div class="active_jiaoB"></div>
            </div>
            <h3>新客专享 18888元体验金</h3>
            <p>10月23日</p>
        </div>
        <div class="activeBox">
            <div class="activeBox_s"><img src="/image/OperationReport2017/active17.png">
                <div class="active_jiaoT"></div>
                <div class="active_jiaoB"></div>
            </div>
            <h3>加薪大作战</h3>
            <p>11月1-7日</p>
        </div>
        <div class="activeBox activeBox02">
            <div class="activeBox_s"><img src="/image/OperationReport2017/active18.png">
                <div class="active_jiaoT"></div>
                <div class="active_jiaoB"></div>
            </div>
            <h3>连环福利连环送  11.11砖头助你买买买</h3>
            <p>11月8-11日</p>
        </div>
        <div class="activeBox">
            <div class="activeBox_s"><img src="/image/OperationReport2017/active19.png">
                <div class="active_jiaoT"></div>
                <div class="active_jiaoB"></div>
            </div>
            <h3>“连环福利”逆袭归来</h3>
            <p>11月15-24日</p>
        </div>
        <div class="activeBox">
            <div class="activeBox_s"><img src="/image/OperationReport2017/active20.png">
                <div class="active_jiaoT"></div>
                <div class="active_jiaoB"></div>
            </div>
            <h3>3亿即将抵达战场  砖头团战READY GO</h3>
            <p>11月15-30日</p>
        </div>
        <div class="activeBox activeBox02">
            <div class="activeBox_s"><img src="/image/OperationReport2017/active21.png">
                <div class="active_jiaoT"></div>
                <div class="active_jiaoB"></div>
            </div>
            <h3>年末送福利  加息总动员</h3>
            <p>11月30日</p>
        </div>
        <div class="activeBox">
            <div class="activeBox_s"><img src="/image/OperationReport2017/active22.png">
                <div class="active_jiaoT"></div>
                <div class="active_jiaoB"></div>
            </div>
            <h3>迎3亿全民大联欢  瓜分万元现金</h3>
            <p>12月5-11日</p>
        </div>
        <div class="activeBox">
            <div class="activeBox_s"><img src="/image/OperationReport2017/active23.png">
                <div class="active_jiaoT"></div>
                <div class="active_jiaoB"></div>
            </div>
            <h3>种瓜得瓜  种钱生钱</h3>
            <p>12月12-31日</p>
        </div>
        <div class="activeBox activeBox02">
            <div class="activeBox_s"><img src="/image/OperationReport2017/active24.png">
                <div class="active_jiaoT"></div>
                <div class="active_jiaoB"></div>
            </div>
            <h3>圣诞惊喜从天而降  投资即享</h3>
            <p>12月25-27日</p>
        </div>
        
        <div class="clear"></div>
    </div>

</div>


<div class="ReportModule ReportModule06">
    <div class="Report_tit"></div>
    <div class="Report_con"><img src="/image/OperationReport2017/data14.jpg"></div>
</div>

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

    

        /* $(window).bind("scroll", function(event){
            $(".point").each(function(){
                if($(window).scrollTop() > ($(this).offset().top-$(this).outerHeight()/0.7)){$(this).addClass("modelShow")}
            });
        }); */


})
</script>
<!--bottom common-->
<%@ include file="../footer.jsp" %>
<%}catch(Exception e){
	response.sendRedirect("error.jsp");
    return;  
} %>
</body>
</html>