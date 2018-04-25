<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/include/url.jsp" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv=”X-UA-Compatible” content=”IE=edge″ />
<title>砖头网-我要出借</title>
<link rel="stylesheet" type="text/css" href="/css/style-common.css">
<link rel="stylesheet" type="text/css" href="/css/project.css?v=115">
<Link rel="shortcut icon" href="<%=pathUrl %>/images/zhuan.ico" />
<link rel="stylesheet" href="/css/responsiveslides.css">

<script type="text/javascript" src="/js/jquery-1.10.1.min.js"></script>
<script src="/js/radialIndicator.js"></script>
<script src="/js/com.js"></script>
</head>
<body>
<% try{ %>
<!--common header-->
<%@ include file="header.jsp" %>
<!--common end-->

<!--我要出借-->
<div class="main conheight">
	<div class="con projectscon">
    	<div class="project_wrap">
    	<div class="project_main">
    	<div class="project_box project_box_NEW">
    	<div class="project_pad">
    	<h1>头笔赚</h1>
    	<p style="min-height: 78px;">头笔赚是为新注册用户提供的短期限，高收益的出借产品。新手用户可通过出借这一专享项目，体验在砖头网出借的乐趣。</p>
    	<div class="project_money">
    	<!-- <div class="project_icon"></div>
    	<div class="project_text"></div> -->
    	<div class="project_text project_text01">
    	<img class="project_icon" src="<%=pathUrl %>/images/pro_time.png" />
    	<h3>出借期限</h3>
    	<span><i>15</i>天</span>
    	</div>
    	<div class="project_text project_text02">
    	<img class="project_icon" src="<%=pathUrl %>/images/pro_money.png" />
    	<h3>预期年化</h3>
    	<span><i>15.00</i>%</span>
    	</div>
    	<div class="project_text project_text03">
    	<img class="project_icon" src="<%=pathUrl %>/images/pro_touzi.png" />
    	<h3>最低出借</h3>
    	<span><i>100</i>元起</span>
    	</div>
    	<div style="clear:both;"></div>
    	</div>
    	<div class="bidding_btn "><a href="/portal/lend/NewProjects.jsp">查看详情</a></div>
    	</div>
    	</div>
    	
    	<div class="project_box project_box_AFT project_box_R">
    	<div class="project_pad">
    	<h1>环环投</h1>
    	<p style="min-height: 78px;">环环投产品采用分散出借的方式，将资金匹配至多个优质借款标的中，期限灵活，流动性高，还可预约出借，让您收益最大化。</p>
    	<div class="project_money">
    	<!-- <div class="project_icon"></div>
    	<div class="project_text"></div> -->
    	<div class="project_text project_text01">
    	<img class="project_icon" src="<%=pathUrl %>/images/pro_time.png" />
    	<h3>出借期限</h3>
    	<span><i>1~24</i>个月</span>
    	</div>
    	<div class="project_text project_text02" style="width:124px;margin-left:70px;">
    	<img class="project_icon" src="<%=pathUrl %>/images/pro_money.png" />
    	<h3>预期年化</h3>
    	<span><i>7.20</i>%<i>~14.00</i>%</span>
    	</div>
    	<div class="project_text project_text03" style="margin-left:90px;">
    	<img class="project_icon" src="<%=pathUrl %>/images/pro_touzi.png" />
    	<h3>最低出借</h3>
    	<span><i>100</i>元起</span>
    	</div>
    	<div style="clear:both;"></div>
    	</div>
    	<div class="bidding_btn "><a href="/portal/lend/HuanhuanProjects.jsp">查看详情</a></div>
    	</div>
    	
    	</div>
    	<div style="clear:both;"></div>
    	</div>
    	<!-- 第二排 -->
    	<div class="project_main">
    	<div class="project_box project_box_HH ">
    	<div class="project_pad">
    	<h1>点点投</h1>
    	<p style="min-height: 78px;">点点投系列产品是砖头网为出借人精心推荐的抵押类标的，每个标的均有实物抵押，还款能力更进一步，让您更安心。</p>
    	<div class="project_money">
    	<div class="project_text project_text01">
    	<img class="project_icon" src="<%=pathUrl %>/images/pro_time.png" />
    	<h3>出借期限</h3>
    	<span><i>3</i>个月</span>
    	</div>
    	<div class="project_text project_text02">
    	<img class="project_icon" src="<%=pathUrl %>/images/pro_money.png" />
    	<h3>预期年化</h3>
    	<span><i>7.20</i>%</span>
    	</div>
    	<div class="project_text project_text03">
    	<img class="project_icon" src="<%=pathUrl %>/images/pro_touzi.png" />
    	<h3>最低出借</h3>
    	<span><i>100</i>元起</span>
    	</div>
    	<div style="clear:both;"></div>
    	</div>
    	<div class="bidding_btn "><a href="/portal/lend/DiandianProjects.jsp">查看详情</a></div>
    	</div>
    	</div>
    	<div class="project_box project_box_ZZ project_box_R">
    	<div class="project_pad">
    	<h1>转转投</h1>
    	<p style="min-height: 78px;">转转投系列产品是出借人因自身原因，将持有的点点投进行债权转让后产生的债转标的，该计划具有资金流动性自由灵活的特点。</p>
    	<div class="project_money">
    	<!-- <div class="project_icon"></div>
    	<div class="project_text"></div> -->
    	<div class="project_text project_text01">
    	<img class="project_icon" src="<%=pathUrl %>/images/pro_time.png" />
    	<h3>出借期限</h3>
    	<span><i>3~12</i>个月</span>
    	</div>
    	<div class="project_text project_text02">
    	<img class="project_icon" src="<%=pathUrl %>/images/pro_money.png" />
    	<h3>预期年化</h3>
    	<span><i>7.20</i>%</span>
    	</div>
    	<div class="project_text project_text03">
    	<img class="project_icon" src="<%=pathUrl %>/images/pro_touzi.png" />
    	<h3>最低出借</h3>
    	<span><i>100</i>元起</span>
    	</div>
    	<div style="clear:both;"></div>
    	</div>
    	<div class="bidding_btn "><a href="/portal/lend/ZhuanzhuanProjects.jsp">查看详情</a></div>
    	</div>
    	</div>
    	<div style="clear:both;"></div>
    	</div>
    	</div>
    </div>
</div>

<!-----------------中间部分结束----------------> 
<!--bottom common-->
<%@ include file="footer.jsp" %>

</body>

<%}catch(Exception e){
	response.sendRedirect("error.jsp");
    return;  
} %>

</html>
