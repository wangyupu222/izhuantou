<%@ page contentType="text/html; charset=UTF-8" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title><%if("a13702c03fa8056b00abf22527d84c22".equals(request.getParameter("OID"))) {%> 
    	砖头网-平台介绍
    	<%}else if("a13861a43fa8056b0046da1a3a070023".equals(request.getParameter("OID"))) {%>
    	砖头网-团队介绍
    	<%}else if("a13a2d553fa8056b004e16602fb0ce3d".equals(request.getParameter("OID"))) {%>
    	砖头网-发展历程
    	<%}else if("a13a5ace3fa8056b0169d3bedfa99da6".equals(request.getParameter("OID"))) {%>
    	砖头网-企业资质
    	<%}else if("a13a838f3fa8056b00990ef3037789c1".equals(request.getParameter("OID"))) {%>
    	砖头网-安全保障
    	<%}else if("a13aafbb3fa8056b013bfb122618e26c".equals(request.getParameter("OID"))) {%>
    	砖头网-运营数据
    	<%}else if("a1412b813fa8056b0158b0aeb4425a72".equals(request.getParameter("OID"))) {%>
    	砖头网-联系我们
    	<%-- <%}else if("f96acee93fa8057801be6c1ebcab5adc".equals(request.getParameter("OID"))) {%>
    	隐私策略 --%>
    	<%}else if("f96b5d143fa80578001cad5284a3b8f9".equals(request.getParameter("OID"))) {%>
    	砖头网-法律声明
    	<%}else if("f96bb8cf3fa80578009ae40bd834fae4".equals(request.getParameter("OID"))) {%>
    	砖头网-政策法规
    	<%}%></title>
<link rel="stylesheet" type="text/css" href="/css/style-common.css">
<link rel="stylesheet" type="text/css" href="/css/aboutus.css?v=118">
<Link rel="shortcut icon" href="/images/zhuan.ico" />
<script type="text/javascript" src="/js/jquery-1.10.1.min.js"></script>
<%-- <script src="/js/jquery.zoomImgRollover.js" type="text/javascript"></script> --%>
<script type="text/javascript" src="/js/team_introduce.js"></script>
<script src="/js/com.js"></script>
<!-- 联系我们 百度地图 -->
<%if(request.getParameter("OID").equals("a1412b813fa8056b0158b0aeb4425a72")) {%>
<link rel="stylesheet" href="https://api.map.baidu.com/library/SearchInfoWindow/1.5/src/SearchInfoWindow_min.css" />
<script type="text/javascript" src="https://api.map.baidu.com/api?v=2.0&ak=2.0&ak=3qkFf2G2rUbWKsNYmc2dDvL7"></script>
<script type="text/javascript" src="https://api.map.baidu.com/library/SearchInfoWindow/1.5/src/SearchInfoWindow_min.js"></script>
<%}%>
<!-- 运营数据折线图  柱状图 -->
<%if(request.getParameter("OID").equals("a13aafbb3fa8056b013bfb122618e26c")) {%>
<script type="text/javascript" src="/js/echarts.common.min.js"></script>
<link href="/css/zhexian_index.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="/js/echarts3.js"></script>
<script type="text/javascript" src="/js/move.js"></script>
<script>
$(function(){
	if("msDoNotTrack" in window.navigator){
    	$(".content_left,.content_right").css("opacity","1");
    }
})
</script>
<%}%>
<!-- 团队介绍 -->
<%if(request.getParameter("OID").equals("a13861a43fa8056b0046da1a3a070023")) {%>
<%-- <link type="text/css" rel="stylesheet" href="/css/aboutus_tx.css"> --%>
<%}%>

</head>
<body>
<% try{ %>
<%@ include file="header.jsp" %>

<!--中间部分开始-->
<div class="con smallnav">
    	<a >当前位置</a><span>:</span><a >关于我们</a><span>&gt;</span>
    	<a >
    	<%if("a13702c03fa8056b00abf22527d84c22".equals(request.getParameter("OID"))) {%> 
    	平台介绍
    	<%}else if("a13861a43fa8056b0046da1a3a070023".equals(request.getParameter("OID"))) {%>
    	团队介绍
    	<%}else if("a13a2d553fa8056b004e16602fb0ce3d".equals(request.getParameter("OID"))) {%>
    	发展历程
    	<%}else if("a13a5ace3fa8056b0169d3bedfa99da6".equals(request.getParameter("OID"))) {%>
    	企业资质
    	<%}else if("a13a838f3fa8056b00990ef3037789c1".equals(request.getParameter("OID"))) {%>
    	安全保障
    	<%}else if("a13aafbb3fa8056b013bfb122618e26c".equals(request.getParameter("OID"))) {%>
    	运营数据
    	<%}else if("a1412b813fa8056b0158b0aeb4425a72".equals(request.getParameter("OID"))) {%>
    	联系我们
    	<%-- <%}else if("f96acee93fa8057801be6c1ebcab5adc".equals(request.getParameter("OID"))) {%>
    	隐私策略 --%>
    	<%}else if("f96b5d143fa80578001cad5284a3b8f9".equals(request.getParameter("OID"))) {%>
    	法律声明
    	<%}else if("f96bb8cf3fa80578009ae40bd834fae4".equals(request.getParameter("OID"))) {%>
    	政策法规
    	<%}%>
    	</a>
    </div>
<div class="con aboutcon">
<%@ include file="left_aboutus.jsp" %>


<%-- <div class="about_left">
	<div class="<%if("a13702c03fa8056b00abf22527d84c22".equals(request.getParameter("OID"))) {%>aboutmenu active<%}else{ %>aboutmenu<%}%>" ><a href="aboutus.jsp?OID=a13702c03fa8056b00abf22527d84c22">公司简介</a></div>
    <div class="<%if("a13861a43fa8056b0046da1a3a070023".equals(request.getParameter("OID"))) {%>aboutmenu active<%}else{ %>aboutmenu<%}%>"><a href="aboutus.jsp?OID=a13861a43fa8056b0046da1a3a070023">团队介绍</a></div>
    <div class="<%if("a13a2d553fa8056b004e16602fb0ce3d".equals(request.getParameter("OID"))) {%>aboutmenu active<%}else{ %>aboutmenu<%}%>"><a href="aboutus.jsp?OID=a13a2d553fa8056b004e16602fb0ce3d">发展历程</a></div>
    <div class="<%if("a13a5ace3fa8056b0169d3bedfa99da6".equals(request.getParameter("OID"))) {%>aboutmenu active<%}else{ %>aboutmenu<%}%>"><a href="aboutus.jsp?OID=a13a5ace3fa8056b0169d3bedfa99da6">企业资质</a></div>
    <div class="<%if("a13a838f3fa8056b00990ef3037789c1".equals(request.getParameter("OID"))) {%>aboutmenu active<%}else{ %>aboutmenu<%}%>"><a href="aboutus.jsp?OID=a13a838f3fa8056b00990ef3037789c1">安全保障</a></div>
    <div class="<%if("a13aafbb3fa8056b013bfb122618e26c".equals(request.getParameter("OID"))) {%>aboutmenu active<%}else{ %>aboutmenu<%}%>"><a href="aboutus.jsp?OID=a13aafbb3fa8056b013bfb122618e26c">运营数据</a></div>
    <div class="aboutmenu"><a href="notice.jsp">平台公告</a></div>
    <div class="<%if("a1412b813fa8056b0158b0aeb4425a72".equals(request.getParameter("OID"))) {%>aboutmenu active<%}else{ %>aboutmenu<%}%>"><a href="aboutus.jsp?OID=a1412b813fa8056b0158b0aeb4425a72">联系我们</a></div>
    <div class="aboutmenu"><a href="news.jsp">新闻动态</a></div>
    <div class="<%if("f96acee93fa8057801be6c1ebcab5adc".equals(request.getParameter("OID"))) {%>aboutmenu active<%}else{ %>aboutmenu<%}%>"><a href="aboutus.jsp?OID=f96acee93fa8057801be6c1ebcab5adc">隐私策略</a></div>
	<div class="<%if("f96b5d143fa80578001cad5284a3b8f9".equals(request.getParameter("OID"))) {%>aboutmenu active<%}else{ %>aboutmenu<%}%>"><a href="aboutus.jsp?OID=f96b5d143fa80578001cad5284a3b8f9">法律声明</a></div>
	<div class="<%if("f96bb8cf3fa80578009ae40bd834fae4".equals(request.getParameter("OID"))) {%>aboutmenu active<%}else{ %>aboutmenu<%}%>"><a href="aboutus.jsp?OID=f96bb8cf3fa80578009ae40bd834fae4">政策法规</a></div>
	<!-- <div class="mobileapp">
    <img src="images/app.jpg">
    <div class="mobiltxt">随时随地手机理财</div>
    </div> -->
</div> --%>

<!-- 平台介绍 -->
<%if(request.getParameter("OID").equals("a13702c03fa8056b00abf22527d84c22")) {%>
 <div class="about_right" style="min-height: 275px">
        <div class="about_right_title">平台介绍
            <div class="shangjiantou"></div>
        </div>
        <div class="about_right_content">
            <div style="text-indent:2em">
                砖头网（izhuantou.com），是国内新兴的互联网金融信息中介平台。砖头网运用互联网+及多维数据分析等手段，立足于天津本土发展，放眼于环渤海经济带，致力于为中国城镇新青年阶层打造绿色互联网金融信息中介服务平台。
            </div>
            <h3></h3>
            <div style="text-indent:2em">
                砖头网在充分分析行业现状并严格遵守合规原则的基础上，由一群社会精英阶层的年轻人开始筹建、设计、运营及维护，网站深耕用户需求，推出各种灵活多变的产品，去适应当下社会高速发展的进程，同时以专业、专注、专属的态度，结合有个性、有意思、更具人情味儿的互动方式，与用户分享自己的品牌理念。
            </div>

        </div>
        <!--<div class="about_rightcon">
        </div>-->
    </div>
    <div class="about_right" style="min-height: 580px;">
        <div class="about_right_title">平台办公环境
            <div class="shangjiantou"></div>
        </div>
        <div class="about_right_content">
            <div class="about_right_bottom environment_wrap">
            <div class="environment_top">
            <div class="environment_top_left01">
            <div class="environment_top_left_T"><img class="testimg" src="/image/Group_environment/Group_environment01.png"></div>
            <div class="environment_top_left_B"><img class="testimg" src="/image/Group_environment/Group_environment02.png"></div>
            </div>
            <div class="environment_top_left02">
            <img class="testimg" src="/image/Group_environment/Group_environment03.png">
            </div>
            <div class="environment_top_left03">
            <img class="testimg" src="/image/Group_environment/Group_environment04.png">
            </div>
            <div class="environment_top_left04">
            <img class="testimg" src="/image/Group_environment/Group_environment05.png">
            </div>
            
            </div>
            
            <div class="environment_bottom">
            <div class="environment_bottom_left01">
            <img class="testimg" src="/image/Group_environment/Group_environment06.png">
            </div>
            <div class="environment_bottom_left02">
            <div class="environment_bottom_left_T"><img class="testimg" src="/image/Group_environment/Group_environment07.png"></div>
            <div class="environment_bottom_left_B"><img class="testimg" src="/image/Group_environment/Group_environment08.png"></div>
            </div>
            <div class="environment_bottom_left03">
            <img class="testimg" src="/image/Group_environment/Group_environment09.png">
            </div>
            
            </div>
                <!-- <div class="right_bottom_left">
                    <div class="left_box1"><img class="testimg" src="/images/environment_img_03.png"></div>
                    <div class="left_box2"><img class="testimg" src="/images/environment_img_14.png"></div>
                </div>
                <div class="right_bottom_left">
                    <div class="left_box3"><img class="testimg" src="/images/environment_img_05.png"></div>
                    <div class="left_box4"><img class="testimg" src="/images/environment_img_16.png"></div>
                </div>
                <div class="right_bottom_right">
                    <div class="bottom_right_top"><img class="testimg" src="/images/environment_img_07.png"></div>
                    <div class="bottom_right_bottom">
                        <div class="left_box5"><img class="testimg" src="/images/environment_img_11.png"></div>
                        <div class="bottom_right_bottom_wrap">
                            <div class="left_box6"><img class="testimg" src="/images/environment_img_13.png"></div>
                            <div class="left_box7"><img class="testimg" src="/images/environment_img_18.png"></div>
                        </div>
                        <div style="clear: left"></div>
                    </div>

                </div> -->
                <div style="clear: left"></div>
            </div>


        </div>
        <!--<div class="about_rightcon">
        </div>-->
    </div>

<!-- 团队介绍 -->
<%}else if(request.getParameter("OID").equals("a13861a43fa8056b0046da1a3a070023")){%>
<div class="about_right">
        <div class="about_right_title">团队介绍
            <div class="shangjiantou"></div>
        </div>
        <div class="about_right_content">
            <div style="border-bottom: 1px dotted #bfbfbf ">
                <div class="team_top">
                    <div class="team_top_img1"><img src="/images/team_title.jpg"></div>
                </div>
            </div>
        </div>
        <!--领导模块介绍        -->

        <div class="about_right_content">
            <div >
                <div class="team_people team_people1">
                    <div class="team_wrap_top">
                        <div class="team_wrap_mian">
                            <div class="team_wrap_box" style="display: block;z-index: 2">
                                <div class="team_wrap_img"><img src="/images/1wangboss-1.jpg"></div>
                                <div class="team_wrap_text">
                                    <div class="team_wrap_text_top">
                                        <h3>王晨松 Simon</h3>砖头网董事长兼总裁<br>&nbsp;
                                    </div>
                                    <div class="team_wrap_text_bottom" style="text-indent: 1em">
                                        10年以上的互联网企业管理经验；7年的小微企业融资贷款行业经验。自2014年开始充分运用自身优势融入互联网+的大趋势中，建立天津美丰嘉德资产管理有限公司后转为集团化运营，时任集团董事长兼总裁。2015年末起，集结互联网及金融行业经营人才，成立爱砖头（天津）网络科技有限公司，倾力打造旗下品牌：砖头网。
                                    </div>

                                </div>
                            </div>
                            <div class="team_wrap_box">
                                <div class="team_wrap_img"><img src="/images/2xiaoboss-1.jpg"></div>
                                <div class="team_wrap_text">
                                    <div class="team_wrap_text_top">
                                        <h3>萧振良 Winks</h3>砖头网副总裁<br>&nbsp;
                                    </div>
                                    <div class="team_wrap_text_bottom" style="text-indent: 1em">
                                        近二十年的金融行业管理工作经验。具有非常丰富的授信审批管控专业知识及经验，在小额互联网金融借贷平台的筹建和运营有着很强的管理能力，同时在企业贷款系统的建立方面有着独特的管理思维方式，成功的为多家企业搭建适合其自身发展且完善的借贷运营模式。
                                    </div>

                                </div>
                            </div>
                            <div class="team_wrap_box">
                                <div class="team_wrap_img"><img src="/images/3Vivian-2.jpg"></div>
                                <div class="team_wrap_text">
                                    <div class="team_wrap_text_top">
                                        <h3>林琳 Vivian</h3>砖头网运营总监<br>&nbsp;
                                    </div>
                                    <div class="team_wrap_text_bottom" style="text-indent: 1em">
                                       近10年外企和上市公司工作经验，其中5年以上金融行业工作经历中积累了大量的金融专业知识与运营工作经验；此外4年的运营项目管理经验，主要涉及整体项目的人员构建、平台营销运营的推进、产品开发/迭代更的统筹把控、品牌推广策略的制定实施监审、客服体系搭建及管理，这些经验的积累使其形成了高效、简洁的工作风格
                                    </div>

                                </div>
                            </div>
                            <div class="team_wrap_box">
                                <div class="team_wrap_img"><img src="/images/Ricky_boss.jpg"></div>
                                <div class="team_wrap_text">
                                    <div class="team_wrap_text_top">
                                        <h3>苏友祺 Ricky</h3>砖头网风控总监<br>&nbsp;
                                    </div>
                                    <div class="team_wrap_text_bottom" style="text-indent: 1em">
                                       从业至今已经累积了15年的金融行业经验，曾经在台湾消费金融快速发展之际，协助公司在两年内将市场占有率升至业内第一，期间积累了大量的风控经验，对信贷行业有着极其敏锐的前瞻性，在参与多次专案中得到了优秀的成绩和业内的肯定。
                                    </div>
                                </div>
                            </div>
                            <div class="jiantou_wrap">
                                <div class="team_left">
                                </div>
                                <div class="team_right">
                                </div>
                            </div>
                        </div>

                        <div style="clear: both"></div>
                    </div>
                    <div class="team_wrap_bottom team_wrap_bottom2">
                        <div class="team_bottom_box">
                            <div class="team_bottom_box_img1"><img src="/images/1wangboss-1.jpg"></div>

                        </div>
                        <div class="team_bottom_box">
                            <div class="team_bottom_box_img1"><img src="/images/2xiaoboss-1.jpg"></div>

                        </div>
                        <div class="team_bottom_box">
                            <div class="team_bottom_box_img1"><img src="/images/3Vivian-2.jpg"></div>
                        </div>
                        <div class="team_bottom_box" style="margin-right: 0px">
                            <div class="team_bottom_box_img1"><img src="/images/Ricky_boss.jpg"></div>
                        </div>
                        <div style="clear: both"></div>
                    </div>
                </div>

            </div>
            <!--团队介绍模块-->
            <div style=" ">
                <div class="team_people team_people2">
                    <div class="team_wrap_top">
                        <div class="team_wrap_mian">
                        <div class="team_wrap_box" style="display: block;z-index: 2">
                                <div class="team_wrap_img"><img src="/images/fuwu.jpg"></div>
                                <div class="team_wrap_text">
                                    <div class="team_wrap_text_top">
                                        <br>
                                        <h3>法务团队</h3>
                                    </div>
                                    <div class="team_wrap_text_bottom" style="text-indent: 1em">
                                        在法律面前人人平等，在砖头网的法务团队面前，每位用户的权益也同样是平等的，同时，为了保证平台能一直在合规的道路上勇往直前，砖头网特别聘请业内资深的法务大咖，来防范并解决法律法规方面的各种问题。
                                    </div>

                                 </div>
                            </div>
                            <div class="team_wrap_box">
                                <div class="team_wrap_img"><img src="/images/fengkong.jpg"></div>
                                <div class="team_wrap_text">
                                    <div class="team_wrap_text_top">
                                        <br>
                                        <h3>风控团队</h3>
                                    </div>
                                    <div class="team_wrap_text_bottom" style="text-indent: 1em">
                                        风控团队在大量的待审信息面前总是保持绝对的冷静，每当主动为客户规避了风险后，他们不会忙于庆祝，只会默默地准备迎接新的挑战，之所如此泰然自若，是因为他们除了专业和专注以外，还有一颗对风险桀骜不驯的内心。
                                    </div>

                                 </div>
                            </div>
                            <div class="team_wrap_box">
                                <div class="team_wrap_img"><img src="/images/team_1.jpg"></div>
                                <div class="team_wrap_text">
                                    <div class="team_wrap_text_top">
                                        <br>
                                        <h3>品牌创意团队</h3>
                                    </div>
                                    <div class="team_wrap_text_bottom" style="text-indent: 1em">
                                        品牌承载的是人们对于平台价值的认可，但这种价值不是人人都能知晓的，所以对于自身价值的传播，砖头网非常乐于聘请资深精英，来打造最具品质的品牌创意部。
                                    </div>

                                 </div>
                            </div>
                            
                            <div class="team_wrap_box">
                                <div class="team_wrap_img"><img src="/images/team_2.jpg"></div>
                                <div class="team_wrap_text">
                                    <div class="team_wrap_text_top">
                                        <br>
                                        <h3>运营团队</h3>
                                    </div>
                                    <div class="team_wrap_text_bottom" style="text-indent: 1em">
                                        对于砖头网来说，运营是造就平台辉煌的关键要素之一，所以砖头网的运营人员不仅是运营大咖更具备金融业从业背景，因为只有他们最能体会互金用户的所思所想。
                                    </div>

                                </div>
                            </div>
                            <div class="team_wrap_box">
                                <div class="team_wrap_img"><img src="/images/team_3.jpg"></div>
                                <div class="team_wrap_text">
                                    <div class="team_wrap_text_top">
                                        <br>
                                        <h3>市场营销团队</h3>
                                    </div>
                                    <div class="team_wrap_text_bottom" style="text-indent: 1em">
                                        在经典商管课程中，市场营销是非常重要的课程之一，正是因为市场营销能将用户需求转化为服务价值，所以砖头网聘请了两位资深人士来分别管理市场与营销，以将用户需求价值最大化。
                                    </div>

                                </div>
                            </div>
                            <div class="team_wrap_box">
                                <div class="team_wrap_img"><img src="/images/team_4.jpg"></div>
                                <div class="team_wrap_text">
                                    <div class="team_wrap_text_top">
                                        <br>
                                        <h3>产品团队</h3>
                                    </div>
                                    <div class="team_wrap_text_bottom" style="text-indent: 1em">
                                        网站的构建逻辑和呈现效果是一项专业性很强的工作，产品团队成员在各自经历了多年的历练和成长之后，终于汇聚到了砖头网，在他们相得益彰的配合之下，砖头网的各项操作和展示才能如此的行云流水。
                                    </div>

                                </div>
                            </div>
                            <div class="team_wrap_box">
                                <div class="team_wrap_img"><img src="/images/team_5.jpg"></div>
                                <div class="team_wrap_text">
                                    <div class="team_wrap_text_top">
                                        <br>
                                        <h3>技术团队</h3>
                                    </div>
                                    <div class="team_wrap_text_bottom" style="text-indent: 1em">
                                        在网络信息时代，网络技术既深奥又常见，这种技术可以有效抵御入侵，还可以保障用户操作安全，砖头网为了实现这一目标，特别聘请了业绩卓著同时有着资深从业背景的技术人员打造砖头网网络技术团队。
                                    </div>

                                </div>
                            </div>
                            <div class="team_wrap_box">
                                <div class="team_wrap_img"><img src="/images/team_6.jpg"></div>
                                <div class="team_wrap_text">
                                    <div class="team_wrap_text_top">
                                        <br>
                                        <h3>数据运营团队</h3>
                                    </div>
                                    <div class="team_wrap_text_bottom" style="text-indent: 1em">
                                        对于一家专注金融业务的公司来说，数据的分析维护和运营有着异常重要的作用，砖头网的数据运营团队从国内知名集团公司中聘请过来，为网站的前进方向与决策规划提供重要的参考标准。
                                    </div>

                                </div>
                            </div>
                            <div class="team_wrap_box">
                                <div class="team_wrap_img"><img src="/images/team_7.jpg"></div>
                                <div class="team_wrap_text">
                                    <div class="team_wrap_text_top">
                                        <br>
                                        <h3>设计团队</h3>
                                    </div>
                                    <div class="team_wrap_text_bottom" style="text-indent: 1em">
                                        优秀设计人员分为两种，一种为天赋异禀，一种为发愤图强，砖头网的设计团队中这两种优秀人员全部具备，他们在各自擅长的方面多有建树，在聚集到砖头网旗下之后，依然坚持为用户赏心悦目的事业而奋斗着。
                                    </div>

                                </div>
                            </div>
                            <div class="team_wrap_box">
                                <div class="team_wrap_img"><img src="/images/team_8.jpg"></div>
                                <div class="team_wrap_text">
                                    <div class="team_wrap_text_top">
                                        <br>
                                        <h3>客服团队</h3>
                                    </div>
                                    <div class="team_wrap_text_bottom" style="text-indent: 1em">
                                        每个平台都需要这样的客服人员，有专业的解答能力，专属的服务态度，有专注的敬业精神，砖头网的客服即是这样，作为业内为数不多的资深客服团队，客服部从能力到心理都做好了优质服务的准备。
                                    </div>

                                </div>
                            </div>
                            <div class="jiantou_wrap">
                                <div class="team_left">
                                </div>
                                <div class="team_right">
                                </div>
                            </div>
                        </div>

                        <div style="clear: both"></div>
                    </div>
                    <div class="team_wrap_bottom team_wrap_bottom2">
                    <div class="team_bottom_box">
                            <div class="team_bottom_box_img1"><img src="/images/fuwu.jpg"></div>

                        </div>
                        <div class="team_bottom_box">
                            <div class="team_bottom_box_img1"><img src="/images/fengkong.jpg"></div>

                        </div>
                        <div class="team_bottom_box">
                            <div class="team_bottom_box_img1"><img src="/images/team_1.jpg"></div>

                        </div>
                        <div class="team_bottom_box" style="margin-right: 0px">
                            <div class="team_bottom_box_img1"><img src="/images/team_2.jpg"></div>

                        </div>
                        <div class="team_bottom_box">
                            <div class="team_bottom_box_img1"><img src="/images/team_3.jpg"></div>

                        </div>
                        <div class="team_bottom_box" >
                            <div class="team_bottom_box_img1"><img src="/images/team_4.jpg"></div>

                        </div>
                        <div class="team_bottom_box">
                            <div class="team_bottom_box_img1"><img src="/images/team_5.jpg"></div>

                        </div>
                        <div class="team_bottom_box" style="margin-right: 0px">
                            <div class="team_bottom_box_img1"><img src="/images/team_6.jpg"></div>

                        </div>
                        <div class="team_bottom_box">
                            <div class="team_bottom_box_img1"><img src="/images/team_7.jpg"></div>

                        </div>
                        <div class="team_bottom_box" >
                            <div class="team_bottom_box_img1"><img src="/images/team_8.jpg"></div>

                        </div>
                        <div style="clear: both"></div>
                    </div>
                </div>

            </div>
            <!--<div style="border-bottom: 1px dotted #bfbfbf ">
                <div class="team_people team_people3">
                    <div class="team_wrap_top">
                        <div class="team_wrap_mian">
                            <div class="team_wrap_box" style="display: block;z-index: 2">
                                <div class="team_wrap_img"><img src="/images/11Pitt-1.jpg"></div>
                                <div class="team_wrap_text">
                                    <div class="team_wrap_text_top">
                                        <h3>张帅 Pitt</h3>项目运营主管<br>个人座右铭：爱一件事需要理由吗？不需要！
                                    </div>
                                    <div class="team_wrap_text_bottom" style="text-indent: 1em">
                                        考量一个人的成熟度不仅仅是年龄，而是对待一件事物时所体现的责任感，在完善产品的日子里，
                                        Pitt在研究产品组合方面所表现出的担当与生活中表现出的气质并不相符，
                                        这也是其年龄无法目测的原因，当问其原因时Pitt说：
                                        爱一件事需要理由吗？不需要！
                                    </div>

                                </div>
                            </div>
                            <div class="team_wrap_box">
                                <div class="team_wrap_img"><img src="/images/12Holy-1.jpg"></div>
                                <div class="team_wrap_text">
                                    <div class="team_wrap_text_top">
                                        <h3>盛洁  Holy</h3>VI设计师<br>个人座右铭：喜欢的事自然可以坚持,不喜欢的事怎么也长久不了
                                    </div>
                                    <div class="team_wrap_text_bottom" style="text-indent: 1em">
                                        从萝莉时期开始，Holy 就喜欢在各种墙上涂鸦，在多年之后，
                                        Holy已经可以将这种天赋灵活自如的运用到工作中了，
                                        用其自己的话说：“就像我做铲屎官一样，喜欢的事
                                        自然可以坚持,不喜欢的事怎么也长久不了 ”
                                    </div>

                                </div>
                            </div>
                            <div class="team_wrap_box">
                                <div class="team_wrap_img"><img src="/images/13Jeff-1.jpg"></div>
                                <div class="team_wrap_text">
                                    <div class="team_wrap_text_top">
                                        <h3>尹津 Jeff</h3>客户服务中心主管<br>个人座右铭：精诚所至，金石为开
                                    </div>
                                    <div class="team_wrap_text_bottom" style="text-indent: 1em">
                                        作为平台与用户的直接对话者，Jeff一直遵循的原则是“精诚所至，
                                        金石为开”，你在平台上的各种奇葩问题都可以直接向他提出，
                                        因为在网站建设期间，Jeff为每个问题都准备了
                                        不止一种的解决方法。
                                    </div>

                                </div>
                            </div>
                            <div class="team_wrap_box">
                                <div class="team_wrap_img"><img src="/images/14Oscar-1.jpg"></div>
                                <div class="team_wrap_text">
                                    <div class="team_wrap_text_top">
                                        <h3>邓迪 Oscar</h3>商务拓展负责人<br>个人座右铭：I can i up ，不can我学习
                                    </div>
                                    <div class="team_wrap_text_bottom" style="text-indent: 1em">
                                        不喜欢总用年龄说事，年纪小又怎样？！
                                        才干才是评定的标准！Oscar具备标准的80后外表，
                                        实则内心犯二的大90后！曾经的专业运动员，
                                        也会用着专业的运动精神去做任何事！
                                    </div>

                                </div>
                            </div>
                            <div class="jiantou_wrap">
                                <div class="team_left">
                                </div>
                                <div class="team_right">
                                </div>
                            </div>
                        </div>

                        <div style="clear: both"></div>
                    </div>
                    <div class="team_wrap_bottom team_wrap_bottom3">
                        <div class="team_bottom_box">
                            <div class="team_bottom_box_img1"><img src="/images/11Pitt-1.jpg"></div>
                            <div class="team_bottom_box_img2"><img src="/images/11Pitt-2.jpg"></div>
                        </div>
                        <div class="team_bottom_box">
                            <div class="team_bottom_box_img1"><img src="/images/12Holy-1.jpg"></div>
                            <div class="team_bottom_box_img2"><img src="/images/12Holy-2.jpg"></div>
                        </div>
                        <div class="team_bottom_box">
                            <div class="team_bottom_box_img1"><img src="/images/13Jeff-1.jpg"></div>
                            <div class="team_bottom_box_img2"><img src="/images/13Jeff-2.jpg"></div>
                        </div>
                        <div class="team_bottom_box" style="margin-right: 0px">
                            <div class="team_bottom_box_img1"><img src="/images/14Oscar-1.jpg"></div>
                            <div class="team_bottom_box_img2"><img src="/images/14Oscar-2.jpg"></div>
                        </div>
                    </div>
                </div>

            </div>
            <div style="margin-top: 50px ">
                <div class="team_people team_people4">
                    <div class="team_wrap_top">
                        <div class="team_wrap_mian">
                            <div class="team_wrap_box" style="display: block;z-index: 2">
                                <div class="team_wrap_img"><img src="/images/15Milo-1.jpg"></div>
                                <div class="team_wrap_text">
                                    <div class="team_wrap_text_top">
                                        <h3>李旭超 Milo</h3>前端工程师<br>个人座右铭：最简单的事是坚持，最难的事还是坚持。
                                    </div>
                                    <div class="team_wrap_text_bottom" style="text-indent: 1em">
                                        低调的呆萌小哥，最善于用自己的冷幽默助推欢乐的氛围达到顶点，
                                        可在工作中，Milo认真负责的态度与之前判若两人，
                                        他最擅长的事就是用代码将设计师的想法转化成可触的现实。
                                    </div>

                                </div>
                            </div>
                            <div class="team_wrap_box">
                                <div class="team_wrap_img"><img src="/images/18Vivi-1.jpg"></div>
                                <div class="team_wrap_text">
                                    <div class="team_wrap_text_top">
                                        <h3>赵伟 Vivi</h3>数据清算专员<br>个人座右铭：只要能解决的就都不是事儿。
                                    </div>
                                    <div class="team_wrap_text_bottom" style="text-indent: 1em">
                                        在解决问题的时候Vivi总能处变不惊，用在常年用理性面对一切中Vivi
                                        练就出了独立坚韧的个性，而这种个性造就了Vivi
                                        对数据的敏锐直觉和超级大脑，也就是说数据清算
                                        对她来说已经是与生俱来的能力了。
                                    </div>

                                </div>
                            </div>
                            <div class="team_wrap_box">
                                <div class="team_wrap_img"><img src="/images/17Irene-1.jpg"></div>
                                <div class="team_wrap_text">
                                    <div class="team_wrap_text_top">
                                        <h3>高悦 Irene</h3>项目运营专员<br>个人座右铭：生活不止眼前的苟且还有吃吃吃和买买买。
                                    </div>
                                    <div class="team_wrap_text_bottom" style="text-indent: 1em">
                                        Irene是非典型性的摩羯座，她的创意与热情常年爆棚，总是能给周围的人带来意外惊喜，
                                        如果你能体会到平台活动的热力四射，那一定是Irene在努力的结果，
                                        即使在工作之余Irene也能将她的创意在烘焙上提现的淋漓尽致。
                                    </div>

                                </div>
                            </div>
                            <div class="team_wrap_box">
                                <div class="team_wrap_img"><img src="/images/16Eva-1.jpg"></div>
                                <div class="team_wrap_text">
                                    <div class="team_wrap_text_top">
                                        <h3>李潇宇 Eva</h3>助理设计师<br>个人座右铭：好饭不嫌晚，总能等到我爱吃的那碗。
                                    </div>
                                    <div class="team_wrap_text_bottom" style="text-indent: 1em">
                                        大部分时间Eva的身上流露出的气息是简单质朴的，只有在两种情况下她的天蝎座敏感、
                                        激进并专注的本性会占据灵魂的顶点，一种是在投入工作的情况下，
                                        另一种是在投入料理的世界中。
                                    </div>

                                </div>
                            </div>
                            <div class="jiantou_wrap">
                                <div class="team_left">
                                </div>
                                <div class="team_right">
                                </div>
                            </div>
                        </div>

                        <div style="clear: both"></div>
                    </div>
                    <div class="team_wrap_bottom team_wrap_bottom2">
                        <div class="team_bottom_box">
                            <div class="team_bottom_box_img1"><img src="/images/15Milo-1.jpg"></div>
                            <div class="team_bottom_box_img2"><img src="/images/15Milo-2.jpg"></div>
                        </div>
                        <div class="team_bottom_box">
                            <div class="team_bottom_box_img1"><img src="/images/18Vivi-1.jpg"></div>
                            <div class="team_bottom_box_img2"><img src="/images/18Vivi-2.jpg"></div>
                        </div>
                        <div class="team_bottom_box">
                            <div class="team_bottom_box_img1"><img src="/images/17Irene-1.jpg"></div>
                            <div class="team_bottom_box_img2"><img src="/images/17Irene-2.jpg"></div>
                        </div>
                        <div class="team_bottom_box" style="margin-right: 0px">
                            <div class="team_bottom_box_img1"><img src="/images/16Eva-1.jpg"></div>
                            <div class="team_bottom_box_img2"><img src="/images/16Eva-2.jpg"></div>
                        </div>
                    </div>
                </div>

            </div>
            <div style=" ">
                <div class="team_people team_people5">
                    <div class="team_wrap_top">
                        <div class="team_wrap_mian">
                            <div class="team_wrap_box" style="display: block;z-index: 2">
                                <div class="team_wrap_img"><img src="/images/19Jane-1.jpg"></div>
                                <div class="team_wrap_text">
                                    <div class="team_wrap_text_top">
                                        <h3>候敏 Jane</h3>行政事务专员<br>个人座右铭：努力就有回报。
                                    </div>
                                    <div class="team_wrap_text_bottom" style="text-indent: 1em">
                                        在每天繁杂琐碎的事务中Jane总能保持绝对的耐心与微笑，即使是刻不容缓的紧急事务，
                                        Jane也能从容不破的向前推进，而且在原则问题上她
                                        的执行力也总能招致爱钻空子的人侧目，除非你是一只可爱的萌宠。
                                    </div>

                                </div>
                            </div>
                            <div class="team_wrap_box">
                                <div class="team_wrap_img"><img src="/images/20Owen-1.jpg"></div>
                                <div class="team_wrap_text">
                                    <div class="team_wrap_text_top">
                                        <h3>万东文 Owen</h3>新媒体运营专员<br>个人座右铭：坚持就有希望。
                                    </div>
                                    <div class="team_wrap_text_bottom" style="text-indent: 1em">
                                        “原谅我这一生放荡不羁爱自由”，射手座的血液流淌在Owen身体中，自由对于他有着致命的吸引力，但在职场中他
                                        用职业人强大的基因克制住了内心的欲望，与此同时，
                                        多才多艺的也造就了Owen在新媒体运营上的伟业，而且他
                                        的职业气质也体现在了他的暖男性格中。
                                    </div>

                                </div>
                            </div>
                            <div class="team_wrap_box">
                                <div class="team_wrap_img"><img src="/images/21Michelle-1.jpg"></div>
                                <div class="team_wrap_text">
                                    <div class="team_wrap_text_top">
                                        <h3>聂文君 Michelle</h3>客服专员<br>个人座右铭：天下万物的来和去，都有他的时间。
                                    </div>
                                    <div class="team_wrap_text_bottom" style="text-indent: 1em">
                                        每个网站都需要这样一位客服人员，有专业的解答能力，专属的服务态度，
                                        有专注的敬业精神，Michelle即是这样，作为平台资深的客服专员，
                                        她责无旁贷的等待着你的提问。
                                    </div>

                                </div>
                            </div>
                           &lt;!&ndash; <div class="team_wrap_box">
                                <div class="team_wrap_img"><img src="/images/10Tommy-1.jpg"></div>
                                <div class="team_wrap_text">
                                    <div class="team_wrap_text_top">
                                        <h3>郭东岳 Tommy</h3>创意文案<br>个人座右铭：成功的含义不在于得到什么,而是在于你从那个奋斗的起点走了多远.
                                    </div>
                                    <div class="team_wrap_text_bottom" style="text-indent: 1em">
                                        作为一名低调的观察者，Tommy坚信写作与观察是一门相辅相成的艺术。为了能够让自己的文字更深入人心，
                                        他愿意花更多的时间去观察世界，并沉浸于文字堆砌带来的快感。
                                        但天秤座的性格容易让他深深的陷入纠结的沼泽中。
                                    </div>

                                </div>
                            </div>&ndash;&gt;
                            <div class="jiantou_wrap">
                                <div class="team_left">
                                </div>
                                <div class="team_right">
                                </div>
                            </div>
                        </div>

                        <div style="clear: both"></div>
                    </div>
                    <div class="team_wrap_bottom team_wrap_bottom2">
                        <div class="team_bottom_box">
                            <div class="team_bottom_box_img1"><img src="/images/19Jane-1.jpg"></div>
                            <div class="team_bottom_box_img2"><img src="/images/19Jane-2.jpg"></div>
                        </div>
                        <div class="team_bottom_box">
                            <div class="team_bottom_box_img1"><img src="/images/20Owen-1.jpg"></div>
                            <div class="team_bottom_box_img2"><img src="/images/20Owen-2.jpg"></div>
                        </div>
                        <div class="team_bottom_box">
                            <div class="team_bottom_box_img1"><img src="/images/21Michelle-1.jpg"></div>
                            <div class="team_bottom_box_img2"><img src="/images/21Michelle-2.jpg"></div>
                        </div>
                        &lt;!&ndash;<div class="team_bottom_box" style="margin-right: 0px">
                            <div class="team_bottom_box_img1"><img src="/images/10Tommy-1.jpg"></div>
                            <div class="team_bottom_box_img2"><img src="/images/10Tommy-2.jpg"></div>
                        </div>&ndash;&gt;
                    </div>
                </div>

            </div>-->
        </div>

    </div>
    
    
    


    

    <script type="text/javascript" charset="utf-8" src="/js/aboutus.js"></script>
<!-- 发展历程 -->
<%}else if(request.getParameter("OID").equals("a13a2d553fa8056b004e16602fb0ce3d")){%>


<div class="about_right develop_about">
        <div class="about_right_title">发展历程

            <div class="shangjiantou"></div></div>
        <div class="develop_wrap">
        <div class="about_right_content" style="padding: 15px 20px;font-size: 14px;">
            <div class="develop_main">
                <div class="develop_box point modelShow">
                    <div class="content_left">
                       <h3>砖头网项目正式启动</h3>
                    </div>
                    <div class="content_right">
                      <h3> 爱砖头（天津）网络科技<br>有限公司成立</h3>
         <p>砖头网荣获中国金融风云<br>榜评审委员会颁发的“中<br>国最值得信赖金融服务机<br>构”奖项</p>
         <p>Mr. Brick家族，互联网<br>金融行业“逗逼理财一家<br>人”人物卡通形象诞生</p>
                    </div>
                    <div style="clear: both"></div>
                </div>
                <div class="develop_box02 point modelShow">
                    <div class="content_left">
                      <h3>  砖头网于京津冀网贷行<br>业（2016）年会荣获<br>“2016年互联网金融最<br>佳企业奖”奖项</h3>
		  <p>3日 砖头网Web端进行<br>开放性测试</p>
                    </div>
                    <div class="content_right">
                        <h3>砖头网核心团队成员组建完成</h3>
                        
                        
                    </div>
                    <div style="clear: both"></div>
                </div>
                <div class="develop_box01 point modelShow">
                    <div class="content_right">
                    <h3>砖头网荣获2017亚洲品牌论<br>坛年会颁发的“中国最具投<br>资价值互联网金融品牌”和<br>“中国金融管理年度创新人<br>物”两项大奖</h3>
		<p>12日 开放性测试期间，累计<br>交易额突破1000万元</p>
		<p>25日 砖头网受邀参加金融科<br>技技术安全共同体成立大会</p>
                        
                    </div>
                    <div style="clear: both"></div>
                </div>
                <div class="develop_box03 point modelShow">
                    <div class="content_left">
                     <h3> “砖头狂欢日”邀请监<br>审员到平台总部参观并<br>进行直播，在线观看人<br>数达6.7万人</h3>
						<p>3日 砖头网开放性测试<br>期间，累计交易额突破<br>2000万元</p>
						<div style="margin-top:110px;"><h3>1日 砖头网手机客户端<br>（砖头网APP）正式上<br>线运行</h3>
		<p>15日 砖头网APP、PC<br>端小额投融资综合服务<br>管理系统 分别获得国家<br>计算机软件著作权登记<br>证书</p>
		<p>21日，砖头网累计交易<br>额突破1亿元</p></div>
                    </div>
                    <div class="content_right">
                      <h3> 砖头网荣获2017第六届中国<br>财经峰会颁发的“2017最具成<br>长价值奖”</h3>
		<p>23日 砖头网开放性测试期间，<br>累计交易额突破5000万元</p>
		<p>24日 砖头网携手梆梆安全，<br>打造了移动应用加固保护计划</p>
		<p>27日 砖头网开放性测试期间，<br>累计交易额突破6000万元</p>
		<div style="margin-top:168px;"><h3>18日 砖头网迁入新址</h3></div>
                    </div>
                    <div style="clear: both"></div>
                </div>

            </div>

        </div>
        </div>
    </div>
    <div style="clear: both"></div>
</div>
<div style="clear: both"></div>
<script>
    $(function(){
        
        $(window).bind("scroll", function(event){
            $(".point").each(function(){
                if($(window).scrollTop() > ($(this).offset().top-$(this).outerHeight()/0.5)){$(this).addClass("modelShow")}
            });
        });

    })
</script>
    
<!-- 企业资质 -->
<%}else if(request.getParameter("OID").equals("a13a5ace3fa8056b0169d3bedfa99da6")){%>
<div class="about_right">
        <div class="about_right_title">企业资质
            <div class="shangjiantou"></div>
        </div>
        <div class="about_right_content">
            <div class="aptitude_wrap">
                <!-- <div class="aptitude_box">
                    <div class="aptitude_box_img"><img class="testimg" src="/images/zizhi_9.jpg"></div>
                    <div class="aptitude_box_text">
                        常年法律顾问
                    </div>

                </div> -->
                <div class="aptitude_box" >
                    <div class="aptitude_box_img"><img class="testimg" src="/images/zizhi_13.jpg"></div>
                    <div class="aptitude_box_text">
         中国最具投资价值互联网金融品牌
                    </div>

                </div>
                <div class="aptitude_box" >
                    <div class="aptitude_box_img"><img class="testimg" src="/images/zizhi_12.jpg"></div>
                    <div class="aptitude_box_text">
          中国金融管理年度创新人物
                    </div>

                </div>
                <div class="aptitude_box" style="margin-right: 0px">
                    <div class="aptitude_box_img"><img class="testimg" src="/images/zizhi_10.jpg"></div>
                    <div class="aptitude_box_text">
          计算机软件著作权登记证书
                    </div>

                </div>
                <div style="clear: both"></div>
            </div>
            <div class="aptitude_wrap">
                
                <div class="aptitude_box">
                    <div class="aptitude_box_img"><img class="testimg" src="/images/zizhi_11.jpg"></div>
                    <div class="aptitude_box_text">
          2016年互联网金融最佳企业奖
                    </div>

                </div>
                
            
                <div class="aptitude_box" >
                    <div class="aptitude_box_img"><img class="testimg" src="/images/zizhi_2.jpg"></div>
                    <div class="aptitude_box_text">
             中国互联网金融行业新领军人物
                    </div>

                </div>
                <div class="aptitude_box" style="margin-right: 0px">
                    <div class="aptitude_box_img"><img class="testimg" src="/images/zizhi_1.jpg"></div>
                    <div class="aptitude_box_text">
            中国最值得信赖金融服务机构
                    </div>

                </div>
                <div style="clear: both"></div>
            </div>
            <!-- <div class="aptitude_wrap">
                
                <div class="aptitude_box" >
                    <div class="aptitude_box_img" ><img class="testimg" src="/images/zizhi_4.jpg"></div>
                    <div class="aptitude_box_text">
     2016中国互联网金融创新活力企业
                    </div>

                </div>
                
                
            
                 <div class="aptitude_box" style="margin-right: 0px">
                    <div class="aptitude_box_img"><img class="testimg" src="/images/zizhi_5.jpg"></div>
                    <div class="aptitude_box_text">
      中国金融理财行业信用AAA级单位
                    </div>

                </div>
                <div style="clear: both"></div>
            </div>
            <div class="aptitude_box">
                <div class="aptitude_box" style="margin-right: 0px">
                    <div class="aptitude_box_img"><img class="testimg" src="/images/zizhi_3.jpg"></div>
                    <div class="aptitude_box_text">
        前海股权交易中心挂牌上市企业
                    </div>

                </div>
                <div style="clear: both"></div>
            </div> -->
                

        </div>
    </div>
<!-- 安全保障 -->
<%}else if(request.getParameter("OID").equals("a13a838f3fa8056b00990ef3037789c1")){%>
<web:ContentLeaf leafName="SingleArticle" name="content"></web:ContentLeaf>
<div class="about_right">
	<div class="about_right_title">安全保障
            <div class="shangjiantou"></div>
        </div>
	<div class="about_right_content">
	 <div class="f_banner"><img src="/images/safe_banner.jpg"></div>
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
                            <div class="txt_img"><img src="/images/safe_img1.jpg"></div>
                        </div>
                        <div style="clear: both"></div>
                    </div>
                </div>
                <div class="even">
                    <div class="con_row ">
                        <div class="row_left">
                            <div class="txt_img"><img src="/images/safe_img2.jpg"></div>
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
                            <div class="txt_img"><img src="/images/safe_img3.jpg"></div>
                        </div>
                        <div style="clear: both"></div>
                    </div>
                </div>
                <div class="even">
                    <div class="con_row ">
                        <div class="row_left">
                            <div class="txt_img txt_img4"><img src="/images/safe_img4.png"></div>
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
            </div>
	
	</div>
</div>
<!-- 运营数据 -->
<%}else if(request.getParameter("OID").equals("a13aafbb3fa8056b013bfb122618e26c")){%>

<hoontag:TagAction handleClassName="pageIndex" />
<div class="about_right">
        <div class="about_right_title">运营数据
        <div class="shangjiantou"></div>
        </div>
        <div class="about_right_content">
            <div style="border-bottom: 1px dotted #bfbfbf ">
            <web:IteratorPartPage name="allIndex" id="allIndex" rowSize="1">
                <div class="operative_box1">
                    <!-- <div class="operative_title">平台业务数据</div> -->
                    <div class="low_list_title">
                        <span class="low_title_dian"></span>
                                                                        平台业务数据
                    </div>
                    <div class="operative_box1_con">
                        <div class="operative_con_kuai">
                            <div class="con_kuai_img kuai_img1"></div>
                            <div class="con_kuai_text">
                                <span><web:WidgetTextOut name="allIndex" property="member"  />人</span><br>
                                注册用户总人数
                            </div>
                        </div>
                        <div class="operative_con_kuai ">
                            <div class="con_kuai_img kuai_img2"></div>
                            <div class="con_kuai_text">
                                <span><web:WidgetTextOut name="allIndex" property="money"  />万元</span><br>
                                累计成交金额
                            </div>
                        </div>
                        <div class="operative_con_kuai " style="margin-right: 0px">
                            <div class="con_kuai_img kuai_img3"></div>
                            <div class="con_kuai_text">
                                <span><web:WidgetTextOut name="allIndex" property="interestMoney"  />万元</span><br>
                                累计赚取收益
                            </div>
                        </div>

                        <div style="clear: both"></div>


                        <div class="operative_box1_con2">
                            <div class="operative_con_kuai ">
                                <div class="con_kuai_img kuai_img4"></div>
                                <div class="con_kuai_text">
                                    <span><web:WidgetTextOut name="allIndex" property="trannum"  />笔</span><br>
                                    累计成交笔数
                                </div>
                            </div>
                            <div class="operative_con_kuai ">
                                <div class="con_kuai_img kuai_img6"></div>
                                <div class="con_kuai_text">
                                    <span><web:WidgetTextOut name="allIndex" property="tranmoney"  />元</span><br>
                                    人均出借金额
                                </div>
                            </div>
                            <div class="operative_con_kuai ">
                                <div class="con_kuai_img kuai_img5"></div>
                                <div class="con_kuai_text">
                                    <span><web:WidgetTextOut name="allIndex" property="loannum"  />人</span><br>
                                    累计借款人数
                                </div>
                            </div>
                            <div class="operative_con_kuai " style="margin-right: 0px">
                                <div class="con_kuai_img kuai_img6"></div>
                                <div class="con_kuai_text">
                                    <span><web:WidgetTextOut name="allIndex" property="loanmoney"  />元</span><br>
                                    人均借款金额
                                </div>
                            </div>
                            <div style="clear: both"></div>
</web:IteratorPartPage>
                        </div>
                    </div>
                </div>
            </div>

        </div>

        <!--//折线图-->

        <div class="about_right_content">
            <div style="border-bottom: 1px dotted #bfbfbf ">
                <div class="operative_box1">
                <div class="low_list_title">
                        <span class="low_title_dian"></span>
                                                                        综合指数趋势图
                    </div>
                    <!-- <div class="operative_title">平台业务数据</div> -->
                    <div class="zhe_content">
                        <!--折线图模块-->
                        <div class="DA_wz">
                            <span>指数类别</span>
                            <select class="IDzhishuType">
                                <option value="1">促成出借金额</option>
                                <option value="2">出借人数</option>
                                <option value="3">出借笔数</option>
                                <option value="4">平均利率</option>
                                <option value="5">标的数量</option>
                            </select>
                            <i class="DA_tu"></i>
                            <label class="hide">
                                <span>指数周期</span>
                                <select class="IDzhouqiType">
                                    <option value="1">天</option>
                                    <option value="2">周</option>
                                    <option value="3">月</option>
                                    <option value="4">季</option>
                                    <option value="5">年</option>
                                </select>
                                <i class="DA_tu"></i>
                            </label>
                        </div>
                        <h4 class="DA_wz1"><div>累计促成出借金额趋势图</div><span>（万元）</span><span class="r">（日期）</span></h4>
                        <div class="DA_nr" id="myDA_nr" style="width:100%; height:450px; position:relative; top:-50px;"><!--<img src="/image/tu_01.jpg" alt=""/>--></div>


                    </div>
                </div>
            </div>

        </div>
<div class="clear"></div>
        <div class="about_right_content">
            <div style="border-bottom: 1px dotted #bfbfbf ">
                <div class="operative_box1">
                    <div class="operative_title">出借用户分析</div>
                    <div class="tubaio_wrap">
                        <div class="tubiao_box1"><div id="main01" style="width: 120%;height:270px;"></div></div>
                        <div class="tubiao_box2"><div id="main02" style="width: 100%;height:270px;"></div></div>
                        <div class="clear"></div>
                    </div>
                </div>
            </div>

        </div>
        <div style="clear: both"></div>
    </div>


<!-- 联系我们 -->
<%}else if(request.getParameter("OID").equals("a1412b813fa8056b0158b0aeb4425a72")){%>
<div class="about_right">
        <div class="about_right_title">联系我们
            <div class="shangjiantou"></div>
        </div>
        <div class="about_right_content">
            <div style="border-bottom: 1px dotted #bfbfbf ">
                <div class="daidu_map" id="allmap">
                    <script type="text/javascript">
                        // 百度地图API功能
                        var map = new BMap.Map("allmap");    // 创建Map实例
                        map.centerAndZoom(new BMap.Point(116.404, 40), 12);  // 初始化地图,设置中心点坐标和地图级别
                        map.addControl(new BMap.MapTypeControl());   //添加地图类型控件
                        map.setCurrentCity("天津");          // 设置地图显示的城市 此项是必须设置的
                      //  map.enableScrollWheelZoom(false);     //开启鼠标滚轮缩放
                        //var poi = new BMap.Point(117.171761,39.119769);
                        var poi = new BMap.Point(117.187226,39.14557);
                        map.centerAndZoom(poi, 16);
                        var content = '<div style="margin:0;line-height:20px;padding:2px;">' +
                                '<img src="../img/baidu.jpg" alt="" style="float:right;zoom:1;overflow:hidden;width:100px;height:100px;margin-left:3px;"/>' +
                                '地址：天津市南开区城厢中路鼓楼南城街8号楼6门2楼。<br/>电话：400-900-9677<br/>简介：爱砖头（天津）网络科技有限公司，位于天津市南开区城厢中路鼓楼南城街8号楼6门2楼。' +
                                '</div>';

                        //创建检索信息窗口对象
                        var searchInfoWindow = null;
                        searchInfoWindow = new BMapLib.SearchInfoWindow(map, content, {
                            title  : "爱砖头（天津）网络科技有限公司",      //标题
                            width  : 340,             //宽度
                            height : 125,              //高度
                            panel  : "panel",         //检索结果面板
                            enableAutoPan : true,     //自动平移
                            searchTypes   :[
                                BMAPLIB_TAB_SEARCH,   //周边检索
                                BMAPLIB_TAB_TO_HERE,  //到这里去
                                BMAPLIB_TAB_FROM_HERE //从这里出发
                            ]
                        });
                        var marker = new BMap.Marker(poi); //创建marker对象
                        marker.enableDragging(); //marker可拖拽
                        marker.addEventListener("click", function(e){
                            searchInfoWindow.open(marker);
                        })
                        marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
                        map.addOverlay(marker); //在地图中添加marker
                    </script>
                </div>

            </div>
            <div class="contact_content">
                <div class="contact_us_left">
                <div class="contact_us_tit"><h3>客户服务</h3><p>CUSTOMER SERVICE</p></div>
                <div class="contact_us_con">
                <h4>如果使用中您有疑问可通过以下方式联系我们</h4>
                <p><span style="display:block;line-height:28px;margin-top:10px;">公司地址：天津市南开区城厢中路鼓楼南城街<br>8号楼6门2楼</span>
					服务时间：周一至周五 9:00 - 18:00<br>
					客服电话：400-900-9677<br>
					官方Q群：415715256<br>
					客服邮箱：service@izhuantou.com<br>
					邮政编码：300192</p>
                </div>
                <div class="contace_er">
                <div class="er_box"><img src="/images/er_icon1.png"><p>微信公众号</p></div>
                <div class="er_box"><img src="/images/weibo_icon2.png"><p>新浪官博</p></div>
                </div>
                </div>
                <div class="contact_us_right">
                <div class="contact_us_tit contact_us_tit02"><h3>商务合作</h3><p>BUSINESS COOPERATION</p></div>
                <div class="contact_us_con contact_us_con02">
                <h4>如有业务资源合作意向，可标注在邮件中并简要描述，<br>我们会尽快与您联系</h4>
                <p>商务邮箱：brand@izhuantou.com</p>
                </div>
                <div class="us_right_two">
                <div class="contact_us_tit contact_us_tit03"><h3>品牌合作</h3><p>BRAND PARTNERSHIP</p></div>
                <div class="contact_us_con contact_us_con02">
                <h4>如有媒体展会，论坛会议合作意向，可标注在邮件中并<br>简要描述，我们会尽快与您联系</h4>
                <p>品牌邮箱：brand@izhuantou.com</p>
                </div>
               </div>
                </div>
               <div class="clear"></div>
                
                
            </div>
        </div>

    </div>
<!-- 法律声明 -->
<%}else if(request.getParameter("OID").equals("f96b5d143fa80578001cad5284a3b8f9")){%>
<web:ContentLeaf leafName="SingleArticle" name="content"></web:ContentLeaf>
<div class="about_right">
        <div class="about_right_title">法律声明
            <div class="shangjiantou"></div>
        </div>
        <div class="about_right_content">
            <div style="border-bottom: 1px dotted #bfbfbf;margin-top: 20px ">
                <div class="low_box">
                    <div class="low_list_title">
                        <span class="low_title_dian"></span>
                        网站声明:
                    </div>
                    <div class="law_list_content">
                        “砖头网”网站（www.izhuantou.com，以下简称“本网站”）是爱砖头（天津）网络科技有限公司（以下简称“我公司”）为用户（以下简称“您”）提供多种出借计划的互联网金融信息中介服务平台，本声明为本网站及有关网站（包括但不限于砖头网等可通过本网站提供服务的其他网站，具体以本网站展示为准，以下统称为“本网站”）使用的条款，凡浏览或注册使用本网站的用户，均表示完全理解并接受以下条款。我公司保留对本声明包含的条款、条件和说明变更的权利。变更自公布时生效，您继续使用本网站，则视为您接受我们条款的随时更改。您应当经常浏览本网站声明，以了解本网站规则的变化。我们将依据本声明来处理您向我们提供的个人信息，请您务必仔细阅读。
                    </div>
                </div>
            </div>
        </div>

        <div class="about_right_content">
            <div style="border-bottom: 1px dotted #bfbfbf ">
                <div class="low_box">
                    <div class="low_list_title">
                        <span class="low_title_dian"></span>
                        知识产权声明:
                    </div>
                    <div class="law_list_content">
                        1.除本网站明确指明外，本网站的结构、网页设计、文字、图像、平台架构和其它信息等所有内容，其著作权均为我公司所有,任何他人或他方不得复制、引用、窃取和公开发表与本网站相关的网站程序或者以其它方式进行非法使用。<br>
                        2.本网站上属于我公司著作权的提供给客户下载的软件或文档，任何他人或他方只可以以本网站允许的方式下载或使用，不得超出许可范围进行复制和其它商业用途使用。对于非我公司所有由本网站提供下载地址的软件或文档，本网站仅为方便您的参考浏览而提供相关链接，使用者对该软件或文档的使用应遵守软件或文档所有者规定的使用。<br>
                        3.本网站自主设计、编写、制作的内容和图像等的著作权均属于我公司所有，他人或他方如需转载或以其它方式使用，必须取得我公司的书面许可。<br>
                        4.对于任何违反国家有关法律法规，不遵守本网站声明，不经本网站同意，擅自使用本网站内容并不注明出处或非法使用的行为，我公司保留采取法律措施，追究其责任的权利。<br>
                    </div>
                </div>
            </div>
        </div>

        <div class="about_right_content">
            <div style="border-bottom: 1px dotted #bfbfbf ">
                <div class="low_box">
                    <div class="low_list_title">
                        <span class="low_title_dian"></span>
                        免责声明：
                    </div>
                    <div class="law_list_content">
                        1.在法律规定的情况下，如司法机关、监管机构及其他相关机构提出要求，我公司有可能提供您的相关信息。<br>
                        2.由于您将用户密码告知他人或与他人共享注册账户，由此导致的任何个人资料和客户信息的泄露及财产的损失。<br>
                        3.任何由于黑客攻击、计算机病毒侵入或发作、政府管制、电信故障或上述列举以外的不可抗力而造成的暂时性关闭等影响网络正常经营之不可抗力而造成的个人资料泄露、丢失、被盗用或被篡改等。<br>
                        4.您应该了解并认可，通过本网站及工作人员获知的咨询或建议，不构成任何保证，仅作为参考信息提供给您，您应自行作出交易决策并就此等决策承担责任；本网站展示的交易信息系由第三方提供或者由本网站或相关服务商通过合法渠道自交易相对方或第三方处获取，本网站将客观如实的进行披露和告知，用户应自行判断信息的真实性、自主参与交易并承担交易风险。因您在本网站发生的交易行为而产生的全部交易纠纷应由您与交易相对方协商解决，本网站不对交易相关的风险（包括但不限于宏观经济风险、政策风险、交易相对方违约风险、利率风险等）承担任何法律责任。
                    </div>
                </div>
            </div>
        </div>

        <div class="about_right_content">
            <div style="margin-bottom: 200px">
                <div class="low_box">
                    <div class="low_list_title">
                        <span class="low_title_dian"></span>
                        隐私条款：
                    </div>
                    <div class="law_list_content">
                        1.当您在本网站进行用户注册登记时，须按照本网站的要求提供注册信，您同意注册视为同意本网站使用您提交的信息。<br>
                        2.用户可在注册时选择信息公开的范围和状态。对于用户选择公开的信息，本网站的其他用户可以浏览或使用，用户同意不因此公开或使用而追究本网站责任。<br>
                        3.为更好的提供服务，本网站需对客户资料进行分类、整理、分析、评级等处理并在网站呈现，您同意注册视为同意本网站对您信息的上述使用。<br>
                        4.本网站建议您及时对您的信息进行更新，以便体现您最新的状况并获得更好的服务。<br>
                        5.您在本网站注册，即视为您同意我公司向您注册时填写的手机号码、电子邮箱发送与您交易行为及我公司服务相关的信息或电子邮件。
                    </div>
                </div>
            </div>
        </div>

    </div>
    <div style="clear: both"></div>
<!-- 政策法规 -->
<%}else if(request.getParameter("OID").equals("f96bb8cf3fa80578009ae40bd834fae4")){%>
<div class="about_right">
        <div class="about_right_title">政策法规
            <div class="shangjiantou"></div>
        </div>
        <div class="about_right_content">
            <div style="border-bottom: 1px dotted #bfbfbf;margin-top: 20px ">
                <div class="low_box">
                    <div class="low_list_title">
                        <span class="low_title_dian"></span>
                        关于在砖头网平台上网络借贷关系的合法性：
                    </div>
                    <div class="law_list_content">
                        1.《中华人民共和国合同法》第196条规定“借款合同是借款人向贷款人借款，到期返还借款并支付利息的合同”，允许普通民事主体之间发生借贷关系，并认定借款人还本付息的合理性。<br>
                        2.砖头网平台上的网络借贷关系在借款的渠道和方式上进行了创新，出借人与借款人的借贷行为原则上属于民事主体之间的借贷，因此符合《合同法》的相关规定，受到法律保护。
                    </div>
                </div>
            </div>
        </div>

        <div class="about_right_content">
            <div style="border-bottom: 1px dotted #bfbfbf ">
                <div class="low_box">
                    <div class="low_list_title">
                        <span class="low_title_dian"></span>
                        关于砖头网提供居间撮合服务的合法性：
                    </div>
                    <div class="law_list_content">
                        1.根据《合同法》第23章关于"居间合同"的规定，特别是第424条规定的"居间合同是居间人向委托人报告订立合同的机会或者提供订立合同的媒介服务，委托人支付报酬的合同"，砖头网做为互联网金融信息中介服务平台，为民间借贷提供居间服务，为借贷双方提供联系、介绍服务，促成借贷双方形成借贷关系的行为有明确的法律依据。
                    </div>
                </div>
            </div>
        </div>

        <div class="about_right_content">
            <div style="border-bottom: 1px dotted #bfbfbf ">
                <div class="low_box">
                    <div class="low_list_title">
                        <span class="low_title_dian"></span>
                        关于电子合同的合法性及可执行性：
                    </div>
                    <div class="law_list_content">
                        1.《合同法》第10条规定：“当事人订立合同，有书面形式、口头形式和其他形式。”<br>
                        2.《合同法》第11条规定：“书面形式是指合同书、信件和数据电文（包括电报、电传、传真、电子数据交换和电子邮件）等可以有形地表现所载内容的形式。”<br>
                        3.《中华人民共和国电子签名法》规定：“民事活动中的合同或者其他文件、单证等文书，当事人可以约定使用或者不使用电子签名、数据电文。”“当事人约定使用电子签名、数据电文的文书，不得仅因为其采用电子签名、数据电文的形式而否定其法律效力。”因此，可靠的电子合同与纸质合同具有同等的法律效力，明确肯定了符合条件的电子签名与手写签名或盖章具有同等的效力。砖头网采取实名认证用户在网上点击确认的方式签署电子合同，该等电子合同符合《中华人民共和国合同法》规定的合同成立、生效的要件，为合法有效合同。<br>

                    </div>
                </div>
            </div>
        </div>

        <div class="about_right_content">
            <div style="margin-bottom: 200px">
                <div class="low_box">
                    <div class="low_list_title">
                        <span class="low_title_dian"></span>
                        关于出借人在砖头网平台获得的出借收益的合法性：
                    </div>
                    <div class="law_list_content">
                        1.《合同法》第211条规定：“自然人之间的借款合同约定支付利息的，借款的利率不得违反国家有关限制借款利率的规定”。<br>
                        2.《最高人民法院关于审理民间借贷案件适用法律若干问题的规定》第二十六条规定：“借贷双方约定的利率未超过年利率24%,出借人请求借款人按照约定的利率支付利息的,人民法院应予支持。借贷双方约定的利率超过年利率36%,超过部分的利息约定无效。借款人请求出借人返还已支付的超过年利率36%部分的利息的,人民法院应予支持。” 砖头网平台审批后披露的标的的出借收益率低于24.00%，在法律允许的范围内，为合法利息收益，受到法律保护。

                    </div>
                </div>
            </div>
        </div>

    </div>
<%}%>


<%-- <web:ContentLeaf leafName="SingleArticle" name="content"></web:ContentLeaf>
<div class="about_right">
	<div class="about_rightcon"><web:WidgetTextOut name="content" property="messages" /></div>
</div> --%>
</div>








<!-----------------中间部分结束----------------> 
<%@ include file="footer.jsp" %>

<!-- 运营数据折线图 -->
<script>
    $(function(){
        /*滚动图片*/
        $(".testimg").zoomImgRollover();
//        $(".IDmortgageData2").cxScroll({'auto':false});
//        funcData.sLeft();
        $.fn.extend({
            setTextFn:function(data,option){
                var addOb=null,hasList=this.next().is('[set-text-done]');
                switch(this.attr('set-text-type')){
                    case 'for' :
                        if(option&&(!option.del)){addOb=hasList?this.nextAll('[set-text-done]').last():this;}else{ this.nextAll('[set-text-done]').remove(); addOb=this; }
                        var txt=this.show().prop("outerHTML"),newTxt='',sL=txt.search('&lt;')>-1?'&lt;':'<',sR=txt.search('&gt;')>-1?'&gt;':'>';
                        this.hide();
                        for( var i=0; i<data.length; i++){
                            newTxt+=txt.replace('set-text-type','set-text-done');
                            for( var k in data[i]){ newTxt=newTxt.replace(sL+'-$'+k+'$-'+sR,data[i][k]); }
                        }
                        addOb.after(newTxt);
                        break;
                }
                option&&option.callback&&option.callback(this);
            }
        });

    })
    var dataAll = [
        [
            {value:'46',itemStyle:{normal:{color:'#79abec'},emphasis:{color:'#93bbef'}}},
            {value:'54',itemStyle:{normal:{color:'#a7cf9b'},emphasis:{color:'#a7cf9b'}}}
        ],
        [
            {value:'15',itemStyle:{normal:{color:'#79abec'},emphasis:{color:'#93bbef'}}},
            {value:'33',itemStyle:{normal:{color:'#a7cf9b'},emphasis:{color:'#a7cf9b'}}},
            {value:'17',itemStyle:{normal:{color:'#edbd49'},emphasis:{color:'#edbd49'}}},
            {value:'35',itemStyle:{normal:{color:'#c594d9'},emphasis:{color:'#c594d9'}}}
        ],
        [
            {value:'59',itemStyle:{normal:{color:'#93bbef'},emphasis:{color:'#93bbef'}}},
            {value:'41',itemStyle:{normal:{color:'#a7cf9b'},emphasis:{color:'#a7cf9b'}}},
        ],
        [
            {value:'0',itemStyle:{normal:{color:'#93bbef'},emphasis:{color:'#93bbef'}}},
            {value:'100',itemStyle:{normal:{color:'#a7cf9b'},emphasis:{color:'#a7cf9b'}}},
        ]
    ];
    option = {
        title: {
            //text: 'Anscombe\'s quartet',
            x: 'center',
            y: 0
        },
        grid: [
            {x: '5%', y: '10%', width: '38%', height: '300px'},
            {x2: '2%', y: '10%', width: '48%', height: '300px;'},

        ],
        tooltip : {
            trigger: 'axis',
            axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            },
            formatter: function (params) {
                var tar = params[0];
                return tar.name + '<br/>' + tar.seriesName + ' : ' + tar.value+'%';
            },
            /*trigger: 'item',
             formatter: "{a}:{d}%"*/
        },
        xAxis: [
            {
                gridIndex: 0,
                data:['男性占比','女性占比'],
                axisLabel: {
                    show: true,
                    textStyle: {
                        fontSize:'14'
                    }
                }},
            {
                gridIndex: 1,
                data:['18-25','26-35','36-45','45岁以上'],
                axisLabel: {
                    show: true,
                    textStyle: {
                        fontSize:'14'
                    }
                }
            }
        ],
        yAxis: [
            {gridIndex: 0, type : 'value', max: 100,axisLabel : {formatter : '{value} %'}},
            {gridIndex: 1, type : 'value', max: 100,axisLabel : {formatter : '{value} %'}},
        ],

        series: [
            {
                name: '百分比值',
                type: 'bar',
                barWidth:'70',
                label: {
                    normal: {
                        show: true,
                        position: 'top',
                        formatter:'{c} %',
                        textStyle:{
                            fontSize:18

                        }

                    }
                },
                xAxisIndex: [0],
                yAxisIndex: [0],
                data: dataAll[0]
            },
            {
                name: '百分比值',
                type: 'bar',
                barWidth:'70',
                label: {
                    normal: {
                        show: true,
                        position: 'top',
                        formatter:'{c} %',
                        textStyle:{
                            fontSize:18

                        }

                    }
                },
                xAxisIndex: [1],
                yAxisIndex: [1],
                data: dataAll[1]
            },
            {
                name: '访问来源',
                type: 'pie',
                radius :120,
                center: ['14%', '82%'],
                xAxisIndex: [2],
                yAxisIndex: [2],
                data: dataAll[2],
                label: {
                    normal: {
                        show: true,
                        position: 'inside',
                        formatter:'{c} %',
                        textStyle:{
                            fontSize:24

                        }
                    }
                }
            },
            {
                name: '访问来源',
                type: 'pie',
                radius :120,
                center: ['67%', '82%'],
                xAxisIndex: [3],
                yAxisIndex: [3],
                data: dataAll[3],
                label: {
                    normal: {
                        show: true,
                        position: 'inside',
                        formatter:'{c} %',
                        textStyle:{
                            fontSize:24

                        }
                    }
                }
            }
        ]
    };

    function timeDataSet(){
        var Da=new Date(),y=Da.getFullYear(),m=Da.getMonth()+1,d=Da.getDate()-1,timeData=[],mList=",1,3,5,7,8,10,12,";
        for(var i=0; i<30;i++){
            if(d==0){
                m=m==1?12:m-1;
                d=(mList.search(','+m+',')>=0)?31:(m!=2?30:(y%4==0?29:28));
                timeData.push(m+'-'+d+'');
                d--;
            }else{
                timeData.push(m+'-'+d+'');
                d--;
            }
        }
        return timeData.reverse();
    }

    var timeData =[];
    option1 = {
        tooltip: {
            trigger: 'axis',
            formatter: function (params) {
                return '日期：'+params[0].name + '<br/>'
                        + params[0].seriesName.substring(0,2) + ' : ' + params[0].value + params[0].seriesName.substring(2,params[0].seriesName.length) + '<br/>';
            },
            axisPointer: {
                animation: false
            }
        },
        xAxis : [
            {
                type : 'category',
                boundaryGap : false,
                axisLine: {onZero: true},
                data: timeData
            }
        ],
        yAxis : [
            {
                //name : '流量(m^3/s)',
                type : 'value',
                //min:100,
                max : 500
            }
        ],
        series : [
            {
                name:'金额万元',
                type:'line',
                symbolSize: 8,
                symbol:'path://#79abec',
                hoverAnimation: false,
                itemStyle:{
                    normal:{
                        lineStyle:{
                            color:'#79abec'
                        }
                    }
                },
                data:[
                    //10,20,90,10,20,90,10,20,90,10,20,90,10,20,90,10,20,90,10,20,90,10,20,90,10,20,90,10,20,90,12
                ]
            }
        ]
    };
    function SetOption(ob,oData){
        var w=window;
        w[ob.myChart].clear();
        w[ob.option].xAxis[0].data=oData.xData || timeDataSet();
        w[ob.option].series[0].data=oData.data;
        var max=0,min=10000000000000,ar=oData.data;
        for(var i=0;i<ar.length;i++){
            max=max<parseFloat(ar[i])?parseFloat(ar[i]):max;
            min=min>parseFloat(ar[i])?parseFloat(ar[i]):min;
        }
        var chazhi=max-min,
                is100=(parseInt(max/100)>=1),
                is10=(parseInt(max/10)>=1),
                is1=(parseInt(max)>=1),
                is01=(parseInt(max*10)>=1),stepNum=is100?100:is10?10:is1?1:0.1;
        var stepTo=max+chazhi/5,stepSt=0;
        stepNum=stepNum/max>0.2?stepNum/10:stepNum;
        stepTo=stepTo%stepNum==0?(stepTo+stepNum):(parseInt(stepTo/stepNum)+1)*stepNum;
        stepTo=parseInt(stepTo)+1;
        stepSt=0;//((min==0)||(parseInt(min/stepNum)-1<0))?0:min%stepNum==0?(min-stepNum):(parseInt(min/stepNum)-1)*stepNum;
        if(stepTo<5){ w[ob.option].yAxis[0].splitNumber=stepTo; }
        w[ob.option].yAxis[0].max=stepTo;
        w[ob.option].yAxis[0].min=stepSt;
        w[ob.option].series[0].name=oData.name+oData.dw;
        $('.DA_wz1 span').eq(0).html('('+oData.dw+')');
        $('.DA_wz1 div').html(oData.title);
        w[ob.myChart] = echarts.init(document.getElementById(ob.id));
        w[ob.myChart].setOption(w[ob.option]);
    }
    //SetOption({myChart:'myChart2',option:'option1',id:'myDA_nr'},{data:[500,1020,100,700,1000,652,748,105,990,332,449,752,737,769,125,444,315,954,102,481,963,150,842,371,951,458,752,211,654,454,524]})
    $(function(){
        myChart2 = echarts.init(document.getElementById('myDA_nr'));
        //myChart2.setOption(option1);
        $('.IDzhishuType').change(function(){
            var v=parseInt($(this).val());
            switch(v){
                case 1:
                    SetOption({myChart:'myChart2',option:'option1',id:'myDA_nr'},{title:'促成出借金额趋势图',name:'金额',dw:'万元',data:eval('[225.65, 235.45, 454.43, 581.82, 359.45, 659.31, 387.33, 216.97, 174.69, 839.57, 802.71, 609.01, 375.17, 592.00, 206.39, 210.17, 407.20, 1038.13, 874.52, 370.48, 539.66, 279.11, 187.21, 874.22, 922.60, 674.38, 515.07, 702.95, 224.99, 263.67]')});
                    break;
                case 2:
                    SetOption({myChart:'myChart2',option:'option1',id:'myDA_nr'},{title:'出借人数趋势图',name:'人数',dw:'人',data:eval('[189, 181, 197, 240, 207, 355, 333, 305, 237, 399, 236, 312, 284, 343, 155, 267, 293, 237, 400, 284, 310, 385, 217, 425, 303, 298, 136, 259, 108, 126]')});
                    break;
                case 3:
                    SetOption({myChart:'myChart2',option:'option1',id:'myDA_nr'},{title:'出借笔数趋势图',name:'笔数',dw:'笔',data:eval('[203, 194, 222, 279, 233, 395, 351, 314, 243, 421, 262, 348, 308, 375, 172, 278, 327, 264, 417, 309, 452, 406, 228, 507, 348, 311, 167, 305, 113, 135]')});
                    break;
                case 4:
                    SetOption({myChart:'myChart2',option:'option1',id:'myDA_nr'},{title:'平均利率趋势图',name:'利率',dw:'%',data:eval('[4, 5, 5, 6, 4, 6, 6, 4, 5, 7, 4, 6, 4, 5, 4, 6, 5, 7, 5, 5, 6, 4, 4, 5, 5, 6, 6, 5, 5, 5]')});
                    break;
                case 5:
                    SetOption({myChart:'myChart2',option:'option1',id:'myDA_nr'},{title:'标的数量趋势图',name:'个数',dw:'个',data:eval('[3, 8, 7, 7, 4, 10, 12, 3, 4, 5, 4, 10, 5, 11, 3, 4, 6, 7, 10, 9, 5, 4, 3, 22, 10, 6, 9, 13, 3, 5]')});
                    break;
            }
        }).change();
        $('.IDzhishuType').setInit({title:'<b><em sel-id-val="true" sel-id-text="true" value="<$Val$>"><$Text$></em><i class="DA_tu"></i></b>'});
    })

</script>

<!-- 运营数据柱状图 -->
<script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var myChart01 = echarts.init(document.getElementById('main01'));
		var myChart02 = echarts.init(document.getElementById('main02'));
        // 指定图表的配置项和数据
        var option01 = {
        	    title : {
        			show:true,
        			x: 'center',
        			y: 'top',
        	        text: '男女比例是多少？',
        			textStyle: {
        	            fontSize: 16,
        	            fontWeight: 'normal',
        	            color: '#333'          // 主标题文字颜色
        	        },
        			subtextStyle: {
        	            color: '#aaa'          // 副标题文字颜色
        	        },
        	        //subtext: '副标题测试信息'
        	    },
        	    tooltip : {
        	        trigger: 'axis'
        	    },
        	 
        	    calculable : true,
        	    xAxis : [
        	        {
        	            type : 'category',
        	            data : ['男性占比','女性占比']
        	        }
        	    ],
        	    yAxis : [
        	        {
        	            type : 'value',
        				axisLabel: {
			                  show: true,
			                  interval: 'auto',
			                  formatter: '{value} %'
		                }
        	            
        	        }
        	    ],
        		
        	    series : [
        	        {        	          
        			    name:'男女比例',
        	            type:'bar',
        	            barCategoryGap : '62%',//柱子间距
        				itemStyle: {
        					normal:{
        						color:'#6df7a4',
        					},
        				},
        				label:{
						    normal:{
						        show:true,						        
						        position:'top',
						        formatter:'{c} %'
						    }
						},
						
        	            data:[ 
        	               {
	        	                value:46,
	        	                itemStyle:{
	        	                  normal:{color:'#55b0fd'}
	        	              	}
        	              }, 
        	              {
        	                   value:80,
        	                   itemStyle:{
        	                   normal:{color:'#4ec04e'}
        	                   }
        	                }, 
        	              ],
        	            
        	        },
        	    ]

					
				};
		//02(右侧)
		// 指定图表的配置项和数据
        var option02 = {
        	    title : {
        			show:true,
        			x: 'center',
        			y: 'top',
        	        text: '年龄比例是多少？',
        			textStyle: {
        	            fontSize: 16,
        	            fontWeight: 'normal',
        	            color: '#333'          // 主标题文字颜色
        	        },
        			subtextStyle: {
        	            color: '#aaa'          // 副标题文字颜色
        	        },
        	        //subtext: '副标题测试信息'
        	    },
        	    tooltip : {
        	        trigger: 'axis'
        	    },
        	 
        	    calculable : true,
        	    xAxis : [
        	        {
        	            type : 'category',
        	            data : ['18-25','26-35','36-45','45以上']
        	        }
        	    ],
        	    yAxis : [
        	        {
        	            type : 'value',
        				axisLabel: {
			                  show: true,
			                  max : 100,
			                  interval: 20,
			                  formatter: '{value} %'
		                }
        	            
        	        }
        	    ],
        		
        	    series : [
        	        {        	          
        			    name:'年龄比例',
        	            type:'bar',
        	            barCategoryGap : '40%',//柱子间距
        				itemStyle: {
        					normal:{
        						color:'#6df7a4',
        					},
        				},
        				label:{
						    normal:{
						        show:true,						        
						        position:'top',
						        formatter:'{c} %'
						    }
						},
        	            data:[ 
        	               {
	        	                value:15,
	        	                itemStyle:{
	        	                  normal:{color:'#55b0fd'}
	        	              	}
        	              }, 
        	              {
        	                   value:30,
        	                   itemStyle:{
        	                   normal:{color:'#4ec04e'}
        	                   }
        	                }, 
        	                {
         	                   value:17,
         	                   itemStyle:{
         	                   normal:{color:'#ff6e0a'}
         	                   }
         	                }, 
         	               {
         	                   value:22,
         	                   itemStyle:{
         	                   normal:{color:'#429c42'}
         	                   }
         	                }, 
         	                
        	              ],
        	            
        	        },
        	    ]

					
				};
				
        // 使用刚指定的配置项和数据显示图表。
        myChart01.setOption(option01);
		myChart02.setOption(option02);
    </script>

<%}catch(Exception e){
	response.sendRedirect("error.jsp");
    return;  
} %>
</body>

</html>
