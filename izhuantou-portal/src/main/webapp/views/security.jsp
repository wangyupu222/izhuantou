<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/include/url.jsp" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>砖头网-安全保障</title>
<link rel="stylesheet" type="text/css" href="/css/style-common.css">
<link rel="stylesheet" type="text/css" href="/css/security.css">
<Link rel="shortcut icon" href="<%=pathUrl %>/images/zhuan.ico" />
<script type="text/javascript" src="/js/jquery-1.10.1.min.js"></script>
<script src="/js/com.js"></script>
</head>
<body>
<% try{ %>
<%@ include file="header.jsp" %>

<!--中间部分开始-->
    <div class="f_banner"><img src="<%=pathUrl %>/images/safe_banner.jpg"></div>
    <div class="aqbz aqbz_con">
    <div class="odd">
    <div class="con_row ">
    <div class="row_left">
    <div class="left_tit">完整全面信息披露</div>
    <div class="left_tit2">海量真实融资项目</div>
    <div class="left_txt">全面信息披露与优质真实的融资项目，真诚相待每一位平台用户，保障平台信息完全透明。严格向监管者和社会公众履行告知义务，将所应披露的信息真实、及时、完整的呈现，让平台用户更放心；<br>
    在相关法律法规和平台运营规则的约束下，平台不会对融资项目的期限进行拆分，严格审核融资项目的真实性、合法性。</div>
    </div>
    <div class="row_right">
    <div class="txt_img"><img src="<%=pathUrl %>/images/safe_img1.jpg"></div>
    </div>
    <div style="clear: both"></div>
    </div>
    </div>
    <div class="even">
    <div class="con_row ">
    <div class="row_left">
    <div class="txt_img"><img src="<%=pathUrl %>/images/safe_img2.jpg"></div>
    </div>
    <div class="row_right">
    <div class="left_tit">质量保障服务专款</div>
    <div style="clear: both"></div>
    <div class="left_tit2">保理公司竭力服务</div>
    <div style="clear: both"></div>
    <div class="left_txt">质保服务专款与保理公司战略合作双保险，尽心尽责对待应收账款，保障出借人的根本利益。<br>
    从业务开展之初即设立质量保障服务专款，最大程度降低出借人资金的逾期风险；<br>
    平台合作的保理公司在配备法务保障的条件下，即使有逾期情况发生，也能提供相应的保理服务。</div>
    </div>
    <div style="clear: both"></div>
    </div>
    </div>
    <div class="odd">
    <div class="con_row ">
    <div class="row_left">
    <div class="left_tit">媲美银行风控尽调</div>
    <div class="left_tit2">三方资金稳健托管</div>
    <div class="left_txt">银行级风控与第三方资金托管，双管齐下的监管机制，保障资金免受威胁。<br>
    仿效传统银行线下调查手段，力求达到完善的风控尽调，结合线上可高效开展工作的优势，将卓越风控团队的能力充分的展现出来；<br>
    第三方资金托管是目前业内公认的合理安全模式，有效保证平台杜绝归集出借人资金的问题，真正意义的实现互联网金融中介平台的作用。</div>
    </div>
    <div class="row_right">
    <div class="txt_img"><img src="<%=pathUrl %>/images/safe_img3.jpg"></div>
    </div>
    <div style="clear: both"></div>
    </div>
    </div>
    <div class="even">
    <div class="con_row ">
    <div class="row_left">
    <div class="txt_img txt_img4"><img src="<%=pathUrl %>/images/safe_img4.png"></div>
    </div>
    <div class="row_right">
    <div class="left_tit">专业先进技术支持</div>
    <div style="clear: both"></div>
    <div class="left_tit2">法律合规全程保障</div>
    <div style="clear: both"></div>
    <div class="left_txt">专业技术支持与全程合法合规，赋予平台牢固的运营环境，保障用户基本权益。<br>
    采用阿里云云服务ECS技术支持，骨干机房搭配独享带宽、云盾DDoS高防IP系统等一系列专业技术手段来保障网站登录、支付相关数据安全性。<br>
    严格依照国家相关法律法规设立平台，与盈科律师事务所达成战略合作关系，在履行《网络借贷信息中介机构业务活动管理暂行办法》相关义务和规定的同时，平台受合同法、民法通则等法律法规以及最高人民法院相关司法解释规范。</div>
    </div>
    <div style="clear: both"></div>
    </div>
    </div>

    <div class="ljtz"><a href="projects.jsp">立即出借</a></div>

    </div>





<!-----------------中间部分结束----------------> 
<%@ include file="footer.jsp" %>
<%}catch(Exception e){
	response.sendRedirect("error.jsp");
    return;  
} %>
</body>

</html>
