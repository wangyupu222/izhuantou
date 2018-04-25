<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/include/url.jsp" %>
<!doctype html>
<html><head>
<meta charset="utf-8">
<title>砖头网-上砖头儿，有赚头儿</title>
<link rel="stylesheet" type="text/css" href="/css/style-common.css">
<link rel="stylesheet" type="text/css" href="/css/confirm_lend.css">
<link rel="stylesheet" type="text/css" href="/css/comsty.css">
<link rel="stylesheet" type="text/css" href="/css/pedetails.css?v=114">
<link rel="stylesheet" href="/css/zoom.css">
<link rel="stylesheet" href="/css/responsiveslides.css">
<Link rel="shortcut icon" href="<%=pathUrl %>/images/zhuan.ico" />
<script type="text/javascript" src="/js/jquery-1.8.3.min.js"></script>
<script src="/js/com.js"></script>
<script src="/js/base.js"></script>
<script src="/js/resubmit.js"></script>
<script src="/js/paging.js"></script>

<%-- <script src="/js/comjd.js"></script> --%>
<!--首页焦点图-->
<script type="text/javascript" src="/js/koala.min.1.5.js"></script>
<script src="/js/radialIndicator.js"></script>
<style type="text/css">
.cd-popup-container1{ width:700px;}/*充值框大小*/
.cd-popup-container2{ width:860px;margin: 40px auto;}/*出借框大小*/
.hide_liu{  width: 100%;  height: 500px;  text-align: center;  background:#fff url("<%=pathUrl %>/images/xiangqing_liu.png")no-repeat center;  display: none;  }
.qxbox .sp02 .add_new{font-size:18px;color:#ff7800;}
.qxbox .sp02 .add_new .add_sign{color:#666;font-size:14px;}
.login_tishi{
width:100%;
text-align:center;
margin:50px 0px;
font-size:20px;
color:#666;
}
.login_tishi a{
color:#55b0fd;
}
.login_tishi a:hover{
text-decoration:underline;
}
.commit-2 span.spjine {
   text-align:right;
   width: 195px;
}
.e_money_wrap{
float:left;
display:none;
margin-left:45px;
}
.comneichujie .close{position: absolute;right:5px;}
.cd-popup-container1, .cd-popup-container2, .cd-popup-container4, .cd-popup-container5, .cd-popup-containerh{ width:875px;}
@media screen and (max-width:1980px){
body{
position: relative;
}
.cd-popuph{
position: absolute;
}
}
@media screen and (max-width:1136px){
.hhbody{
 position: relative;
}
.hhbody .cd-popup1,.cd-popuph,.cd-popup2{
 position: absolute;
}
}
</style>
</head>
<body>
<% try{ %>
<!--出借弹窗-->

<div class="cd-popup2">
<div class="cd-popup-container2">
<web:WidgetForm handleClassName="pageDiandianDetailsFormChujiesave" name="FormChujie" >
<input type="hidden" name="biddingOID" value='<%=request.getParameter("OID") %>' >
<input type="hidden" id="tabInx" name="tabInx" value='<%=request.getParameter("tabInx") %>' >
<!--确认出借-->
   <div class="comnei comneichujie">
       <a class="close cd-popup-close"></a>
       <p class="commit-1 forgetpwdtop">确认出借</p>
       <div class="yunull yunullchujie">
          	<div class="lend_title">
                <div class=""></div>
            </div>
            
            <div class="contentchujie lend_content">
            	<div class=" leon_zhanbox">
                	<div class="boxone boxone01">
                    	<span class="sp01 org"><em id="tk-yjnh"></em>%</span>
                        <span class="sp02">预期年化收益</span>
                    </div>
                    
                    <div class="boxone boxone02">
                    	<span class="sp01"><em id="tk-cjqx"></em>个月</span>
                        <span class="sp02">出借期限</span>
                    </div>
                    
                    <div class="boxone boxone03">
                    	<span class="sp01"><em id="tk-cjjine"></em><input type="hidden" name="amount" id="amount" value="">元</span>
                        <span class="sp02">出借金额</span>
                    </div>
                    
                    <div class="boxone">
                    	<span class="sp01"><em id="tk-yqsy"></em>元</span>
                        <span class="sp02">预期收益</span>
                    </div>
                    <input type="hidden" id="tqID" name="tqID" value="" />
                    <input type="hidden" id="mappingID" name="mappingID" value="" />
                </div>
               <div class="choose_btn"><h1>特权选择</h1><div class="tq_btn">当前无可用加息券</div><div class="e_money_wrap">预期额外收益：<span id="e_money"></span>元</div><div style="clear:both;"></div></div>
                <div class="tq_box">
                <div class="tq_box_top"><h1>可用加息券</h1><div>共<span class="oNumber_text"></span>种可用加息券</div><div style="clear:both;"></div></div>
                <div class="selectbox">
                    
                    <!-- <p><span>特权选择</span><span class="span-isuse"><input type="button" id="isuse" class="isuse"  value="取消特权选择"><label for="isuse" >取消选择</label></span> </p> -->
                    <div class="boxselct">

                         
                    </div>
                    
                </div>
                <div class="tq_btn_wrap">
                    <div class="ensure_btn">确认使用</div>
                    <div class="cancel_btn">不使用加息券</div>
                    <div style="clear:both;"></div>
                    </div> 
                </div>
               
                
            </div>
            
            
           
        </div>
        <p class="commit-2"><span>市场有风险，出借需谨慎</span><web:WidgetButton value="确认" customProperty="class='zcbtn_input confirmbtn' " property="class='colseok'"  /><!-- <a class="colseok" href="tzdetailsok.jsp">确认</a> --><span class="spjine">需支付￥<em id="paymoney"></em>元</span></p>
   </div>    
       <!--确认出借结束-->
    </web:WidgetForm>    
     <!--余额不足-->
   <div class="comnei comneichujie hide">
       <a class="close cd-popup-close"></a>
       <p class="commit-1"><span>余额不足</span></p>
       <p class="yebz">抱歉！您的余额不足，<a href="#">立即充值</a> </p>
        <p class="commit-2"><span>市场有风险，出借需谨慎</span><a class="colseok" href="#">确认</a></p>
   </div>    
       <!--余额不足结束-->   
        
     </div>
</div>
<!--出借弹窗结束-->
<!--===============================图片弹窗===================================-->
<!--图片弹窗-->

<div class="cd-popup3">
<div class="cd-popup-container3">
	<div class="comnei ">
        <a class="close cd-popup-close"></a>
        <p class="commit-1"><span>审核信息</span></p>
       	<div class="yunull yunullimg">
          	<img src="<%=pathUrl %>/images/zanwu.jpg" />
        </div> 
    </div>
        
     </div>
</div>
<!--图片弹窗结束-->


<div class="indexz">
<!--common header-->
<%@ include file="header.jsp" %>
<!--common end-->

<!--我要出借-->
<div class="main conheight">
	<div class="con smallnav">
    	<!-- <a>当前位置</a><span>:</span><a>我要出借</a>
        <span>&gt;</span><a>点点投</a><span>&gt;</span><a>标的详情</a> -->
    </div>
	
    <!--项目详情开始-->
    <div class="con detailscon">
    	<input id="limit_val" type="hidden" value="0" readonly="readonly" >
        <input id="switch_val" type="hidden" value="0" readonly="readonly" >
    	<div class="detailefts">
        	<div class="topdetail">
            	<div class="imgpro hide"><img src="<%=pathUrl %>/images/diantou.png" /></div>
            	<div class="xmtits">
            	<%if(session.getAttribute("userMobile") != null){ %>
				    <%-- <a class="text-xstext" href="/agreement_z.jsp?contractType=3" target="_blank">《点点投服务协议》</a>
					<a class="text-xstext" href="/agreement_z.jsp?contractType=6" target="_blank">《借款协议》</a>
					<a class="text-xstext" href="/agreement_z.jsp?contractType=7" target="_blank">《债权预回购协议》</a> --%>
				<% }else{ %>
				 <a class="text-xstext" href="agreement_z.jsp?contractType=3" target="_blank">《点点投服务协议》</a>
				<%}%>
            	
            	</div>
                
                <div class="huiback">
                	<div class="qxbox qx01"><span class="sp01">预期年化收益</span><span class="sp02 oreg"><em id="data-yjnh"></em>%</span></div>
                    
                    <div class="qxbox qx01"><span class="sp01">出借期限</span><span class="sp02"><em id="data-cjqx"></em>个月</span></div>
                  <%--   <div class="qxbox qx01"><span class="sp01">项目金额</span><span class="sp02 oreg"><em id="data-xmje"><%=request.getAttribute("xmze") %></em>元</span></div> --%>
                    <div class="qxbox qx01"><div id="box" class="box"></div><div class="jindu hide"></div><!-- <div class="prg-cont rad-prg" id="indicatorContainer5"></div> --></div>
                </div>
       			<div class="botbox">
                	<div class="botfindbox">
                	<div class="botfindleft"><span class="tit"><span style="width:100px;display: inline-block;">标的金额</span><span class="conter"><em id="data-xmje"></em>元</span></div>
                	<div class="botfindleft"><span class="tit"><span style="width:100px;display: inline-block;">保障方式</span><span class="conter">保理公司回购代偿</span></div>
                	<div style="clear:both;"></div>
                	</div>
                	<div class="botfindbox">
                	<div class="botfindleft"><span class="tit"><span style="width:100px;display: inline-block;">回款方式</span><span class="conter hkfsCon"></span></div>
                	<div class="botfindleft"><span class="tit"><span style="width:100px;display: inline-block;">最低出借额</span><span class="conter">100元起</span></div>
                	<div style="clear:both;"></div>
                	</div>
                	<div class="botfindbox">
                	<div class="botfindleft"><span class="tit"><span style="width:100px;display: inline-block;">计息方式</span><span class="conter">满标计息</span></div>
                	<div style="clear:both;"></div>
                	</div>
                   
                </div>         
            </div>
            <!--右侧小窗-->
        </div>
        <div class="detairights">
        	<div class="ljtzbox">
            	<div class="sylytext">标的剩余可投：<span id="sykt"><em></em>元</span></div>
               <div class="czzc">
               <label class="boxinput" id="zhyue">账户余额：
               
               <%if(session.getAttribute("userMobile") != null){ %>
				<em></em>元
				<% }else{ %>
				 登录后可查看余额
				<%}%>
               
                </label>
                <%if(session.getAttribute("userMobile") != null){ %>
				<a class="" href="Recharge_new.jsp">充值</a>
				<% }else{ %>
				<%-- <a class="" href="login.jsp">充值</a> --%>
				<%}%>
				<%-- <label class="boxinput">账户余额：<em><%=request.getAttribute("cashAmount") %>元</em></label>
                <a class="cd-popup-trigger1">充值</a> --%>
               </div>
               <div class="touzinput">
               		<input class="jine" id="jine" value="" type="text" placeholder="请输入出借金额…" txtype='FloatZ1000W' onKeyUp="this.value=this.value.replace(/[^\.\d]/g,'');this.value=this.value.replace('','');" />&nbsp;<span id="yueall">余额全投</span>
               </div>
               
                <!--<div class="shouyi">输入金额查看收益</div>-->
                <div class="czzc01">
                	预期收益：可获得<span id="calc-zonge">0.00</span>(元)
                </div>
                <div class="error_box"><div class="hide" id="hhproductStatus"></div></div>

	                <%if(session.getAttribute("userMobile") != null){ %>
					<div class="jineBtn"></div>
					<% }else{ %>
					<a class="caozuo" href="login.jsp">立即登录</a>
					<%}%>

                
                
                <!--<a class="caozuo" href="login.jsp">立即登录</a>-->
                <!-- <a class="caozuo tzjs cd-popup-trigger2" href="#" >立即出借</a> -->
               <div class="bottext">
               <%if(session.getAttribute("userMobile") != null){ %>
				<input type="checkbox" checked="checked"><a class="bottext_hou">我已阅读并同意<a class="blurac" href="agreement_z.jsp?contractType=3" target="_blank" >《点点投服务协议》</a>
				<%if("1".equals(request.getAttribute("isCWTB"))){ %>
				<a class="text-xstext text-xstext_new" href="agreement_z.jsp?contractType=7" target="_blank">《债权转让协议》</a>
				<%}else{%>
				<a class="text-xstext text-xstext_new" href="agreement_z.jsp?contractType=6" target="_blank">《借款协议》</a>
				<%}%>				
<a class="text-xstext text-xstext_new" href="agreement_z.jsp?contractType=8" target="_blank">《债权预回购协议》</a></a>
				<% }else{ %>
				 <a class="bottext_qian" href="register.jsp">还没有账号？立即注册</a> 
				<%}%>
               </div>
            </div>
          
        </div>
    </div>
    <!--项目详情结束-->
   
    <div class="con qhdetailxmcons">
    	 <div class="qhdetail">
            	<div class="ddt_tabtitcons tabtitcons">
                	<div class="active">项目介绍</div>
                    <div>出借记录</div>
                    <div>安全保障</div>
                    <div>常见问题</div>
                </div>
                <div class="neircon">
                	<div class="optabg">
                	<%if(session.getAttribute("userMobile") != null){ %>
                    	<div class="boxxqjs">
                        	<div class="title-p"><span></span>项目描述<a style="clear:both"></a></div>
                            <p class="p-content"></p>
                        </div>
                        <div class="boxxqjs hide">
                        	<div class="title-p"><span></span>借款用途<a style="clear:both"></a></div>
                            <p class="p-content"></p>
                        </div>
                        <div class="boxxqjs">
                        	<div class="title-p"><span></span>抵押物信息<a style="clear:both"></a></div>
                            <p class="p-content"></p>
                        </div>
                        <div class="boxxqjs">
                        	<div class="title-p loanTit"><span></span>借款人信息</div>
                            <div class="messagebox messagebox01">
                            	<dl>
                                	<dd>姓名：<span>
                                	                              	
                                	</span></dd>
                                    <dd>性别：<span>
                                                                   
                                    </span></dd>
                                    
                                </dl>
                                <dl>
                                <dd>年龄：<span>
                                                                      
                                    </span></dd>
                                    <dd>教育程度：<span>
                                    
                                    </span></dd>
                                    
                                </dl>
                                <dl>
                                <dd>身份证号：<span>
                                	                               	
                                	</span></dd>
                                    <dd>手机号：<span>
                                                                     
                                    </span></dd>
                                	
                                </dl>
                                <dl>
                                	<dd>户籍所在地：<span class="work_city">
                                    
                                    </span></dd>
                                    <dd>婚姻状况：<span>
                                    
                                    </span></dd>
                                    
                                </dl>
                            </div>
                            <div class="messagebox messagebox02 hide">
                            	<dl>
                                	<dd>借款企业：<span>
                                	                            	
                                	</span></dd>
                                    <dd>所属行业：<span>
                                                               
                                    </span></dd>
                                    
                                </dl>
                                <dl>
                                <dd>成立时间：<span>
                                                                   
                                    </span></dd>
                                    <dd>注册资本：<span>
                                    
                                    </span></dd>
                                    
                                </dl>
                                <dl>
                                <dd>企业地址：<span>
                                	                        	
                                	</span></dd>
                                    <dd>信用状况：<span>
                                                               
                                    </span></dd>
                                	
                                </dl>
                            </div>
                        </div>
                        
                        <div class="boxxqjs">
                        	<div class="title-p"><span></span>风控审核信息</div>
                            <div class="information_pilu">
                            <div class="btn_left"></div>
                            <div class="informationImg_main">
                            <div class="informationImg_wrap">
                            </div>
                            </div>
                            <div class="btn_right"></div>
                            <div class="clear"></div>
                            </div>
                        </div>
                       <%}else{ %>
                               <div class="login_tishi">请先<a href='login.jsp'>登录</a>查看详情信息</div>
                                <%} %> 
                        
                    </div>
                    <div class="optabg hide">
                  <table cellpadding="0" cellspacing="0" border="0" class="tabxq ">
                         <thead>
                        	<tr class="tr01">
                            	<td width="20%">序号</td> 
                                <td width="20%">出借人</td>
                                <td width="25%">出借金额</td>
                                <td width="25%">出借时间</td>                            
                            </tr>
                            </thead>
                            <tbody>
                            
                            </tbody>                                                      
                        </table>
                        <div id="page" class="page_div"></div>
                        <div class="hide_liu Diantz_liu"></div>  
                    </div>
                   <web:ContentLeaf leafName="SingleArticle" OID="da80654ac057fa8b00c30d73d50bbc1e" name="aqbz"></web:ContentLeaf>
                   	<div class="optabg hide">
                   	<div class="boxxqjs safe_baozhang">
                        	<div class="title-p"><span></span>完整全面的信息披露<a style="clear:both"></a></div>
                            <div class="safe_content"><h3>海量真实融资项目</h3><div> 全面信息披露与优质真实的融资项目，真诚相待每一位平台用户，保障平台信息完全透明。严格向监管者和社会公众履行告知义务，
将所应披露的信息真实、及时、完整的呈现，让平台用户更放心；<br>
在相关法律法规和平台运营规则的约束下，平台不会对融资项目的期限进行拆分，严格审核融资项目的真实性、合法性。</div></div>
                          
                    </div>
                    <div class="boxxqjs safe_baozhang">
                        	<div class="title-p"><span></span>质量保障服务专款<a style="clear:both"></a></div>
                            <div class="safe_content"><h3>保理公司竭力服务</h3><div> 质保服务专款与保理公司战略合作双保险，尽心尽责对待应收账款，保障出借人的根本利益。<br>
从业务开展之初即设立质量保障服务专款，最大程度降低出借人资金的逾期风险；平台合作的保理公司在配备法务保障的条件下，即使有逾期情况发生，也能提供相应的保理服务。</div></div>
                          
                    </div>
                    <div class="boxxqjs safe_baozhang">
                        	<div class="title-p"><span></span>媲美银行风控尽调<a style="clear:both"></a></div>
                            <div class="safe_content"><h3>三方资金稳健托管</h3><div>银行级风控与第三方资金托管，双管齐下的监管机制，保障资金免受威胁。<br>
仿效传统银行线下调查手段，力求达到完善的风控尽调，结合线上可高效开展工作的优势，将卓越风控团队的能力充分的展现出来；<br>
第三方资金托管是目前业内公认的合理安全模式，有效保证平台杜绝归集出借人资金的问题，真正意义的实现互联网金融中介平台的作用。 </div></div>
                          
                    </div>
                    <div class="boxxqjs safe_baozhang">
                        	<div class="title-p"><span></span>专业先进技术支持<a style="clear:both"></a></div>
                            <div class="safe_content"><h3>法律合规全程保障</h3><div> 专业技术支持与全程合法合规，赋予平台牢固的运营环境，保障用户基本权益。<br>
采用阿里云云服务ECS技术支持，骨干机房搭配独享带宽、云盾DDoS高防IP 系统等一系列专业技术手段来保障网站登录、支付相关数据安全性。<br>
严格依照国家相关法律法规设立平台，与盈科律师事务所达成战略合作关系，在履行《网络借贷信息中介机构业务活动管理暂行办法》相关义务和规定的同时，平台受合同法、民法通则等法律法规以及最高人民法院相关司法解释规范。</div></div>
                          
                    </div>
                    	<%-- <p> <web:WidgetTextOut name="aqbz" property="messages" /></p> --%>
                    </div>
                    <web:ContentLeaf leafName="SingleArticle" OID="da8187ccc057fa8b01ca8782dc4dd5ff" name="cjwt"></web:ContentLeaf>
                    <div class="optabg hide">
                    
                    <div class="boxxqjs safe_baozhang">
                        	<div class="title-p title_hei">1.什么是点点投？</div>
                            <div class="safe_content"><div>点点投是砖头网为出借人研发的一款散标类产品，该产品具有稳定、金额小，期限多样的特点，同时其债权信息的披露完整直观，该产品是独立借款人发布的单独借款需求，出借人可根据自己的风险承受能力及意愿自由选择不同风险等级的项目计划。
</div></div>
                          
                    </div>
                    <div class="boxxqjs safe_baozhang">
                        	<div class="title-p title_hei">2.点点投债权如何匹配？</div>
                            <div class="safe_content"><div>用户成功出借点点投计划后，系统将根据真实债权的实际情况自动完成一对一匹配。</div></div>
                          
                    </div>
                    <div class="boxxqjs safe_baozhang">
                        	<div class="title-p title_hei">3.点点投计息方式如何？回款方式如何？</a></div>
                            <div class="safe_content"><div>计息方式为满标后开始计息；回款方式为按月返息，到期还本。</div></div>
                          
                    </div>
                    <div class="boxxqjs safe_baozhang">
                        	<div class="title-p title_hei">4.如出现逾期情况，如何处理？</div>
                            <div class="safe_content"><div>当该项目下某个债权出现逾期等情况时，会由保理公司（鼎星耀阳）进行债权回购，确保出借人利益不受损失。</div></div>
                          
                    </div>
                    <div class="boxxqjs safe_baozhang">
                        	<div class="title-p title_hei">5.是否支持债权转让？限定条件是什么？</div>
                            <div class="safe_content"><div>支持债权转让；限定条件为：持有出借计划时长大于三个月（不含三个月），同时剩余期限需大于一个月（不含一个月）。</div></div>
                          
                    </div>
                    <div class="boxxqjs safe_baozhang">
                        	<div class="title-p title_hei">6.是否有其他安全保障机制？</div>
                            <div class="safe_content"><div>主要包括以下六大机制：<br>
（1）银行级风控：结合高效信审及实地审核，标准化风险定价，注重资产安全，有效控制风险。 <br>
（2）质保服务专款账户制度：设立质保服务专款账户，应对可能发生的逾期风险。尽心尽责对待应收账款，保障出借人的利益不受损失，降低出借人资金的逾期风险。<br>
（3）技术保障：业界领先的核心技术功能（包括用户交易体系、结算清算等底层功能），并通过分布式平台架构提供较高级别的技术安全保障。 <br>
（4）法律保障：砖头网平台及业务受法律保护，透明合规，强大的法务团队为砖头网用户提供法律支持及保障。 <br>
（5）隐私保障：通过全程加密、系统隔离、安全检测，提供全方位的隐私保障。<br>
（6）保理公司：与第三方保理公司（鼎星耀阳）合作，保障平台用户在平台产生的借贷交易。一旦产生逾期的应收账款，保理公司（鼎星耀阳）将对逾期应收账款进行回购，并将承担后续的回购债权及债务追偿工作。
</div></div>
                          
                    </div>
                    	<%-- <p><web:WidgetTextOut name="cjwt" property="messages" /></p> --%>
                    </div>
                   
                </div>
            </div>
    </div>
</div>

<!-----------------中间部分结束----------------> 
<!--bottom common-->
<%@ include file="footer.jsp" %>
</div>
<%}catch(Exception e){
	response.sendRedirect("error.jsp");
    return;  
} %>
</body>
<script src="/js/privilegeJS.js?ver=114"></script>
<script>
//原型进度条
function funJindu(){
			
			//var i = 88;
			var i =$(".jindu").text();
			var object = document.getElementById('box');
			
					function loadImg (data){
						
					var i = 0;
					setInterval(function(){
						i++
						if(i>data){
							
							i=data
						}
						var imgLeft = -(i*95)+'px';
						//alert(imgLeft);
						object.style.backgroundPosition = imgLeft+'\t'+'0px';
						object.innerHTML = i+'%';
					},5)
					
			}
			
		
			loadImg(i);
			if(i == 100){
						object.style.color='#679d28';
					}else{object.style.color='#ff7c2d';}
					}
//点击对钩事件
function changeimg(photoOID){	
	if(photoOID!=""){
		var imgurl="/p2p/cn/com/hoonsoft/servlet/ServletAction?handleClassName=fileDownload&OID="+photoOID;
	}else{
		var imgurl="<%=pathUrl %>/images/zanwu.jpg";
	}
	$('.cd-popup3 .yunullimg img').attr('src',imgurl);
}
</script>
<!--验证表单-->
<script src="/js/jquery.validate.js"></script>
<script src="/js/zoom_new.js"></script>
<script>
$(function(){
    //jquery.validate
	$("#FormChujie").validate({
		submitHandler: function(form) {
			//验证通过后 的js代码写在这里
			//alert(1);
			//事件只能点击一次
			var values = $("a[class='active']").find("input").val();
			$("#OID").attr("value",values);
			console.log(values);
				$(".confirmbtn").click(function(){
					$(this).attr("disabled","disabled");
				});
				validataForm("confirmbtn");
			form.submit();
			
		}
	})
	
})
$(function(){
    if($(".tr02").length<1){
        $(".Diantz_liu").css("display","block");
    }
    var Request = new Object();
	Request = GetRequest2();
	var OID=Request["OID"];
    
    $.ajax({
        type: "get",
        url: "/portal/detial/detailDDT/?OID="+OID,
        dataType: "json",
        success: function(result){
        	$(".xmtits").text(result.xmmc);
        	$(".lend_title div").text(result.xmmc);
        	$("#data-cjqx").text(result.cjqx);
        	$("#tk-cjqx").text(result.cjqx);
        	$("#data-yjnh").text(result.nhll);
        	$("#tk-yjnh").text(result.nhll);
        	$(".jindu").text(result.tzjd);
        	$("#data-xmje").text(result.xmze);
        	$(".hkfsCon").text(result.hkfs);
        	$("#sykt em").text((result.ktje).toFixed(2));
        	if(result.useMoney!=null){
        		$("#zhyue em").text((result.useMoney).toFixed(2));
        	}
        	var strBtn;
        	var script=document.createElement("script");  
        	script.type="text/javascript";
        	if(result.sykt==0 || parseInt(result.productStatus)==2 || parseInt(result.productStatus)==3 || parseInt(result.productStatus)==5){
        		strBtn='<a class="yimanbiao" >已满标</a>';
        	}else if(parseInt(result.productStatus)==10){
        		strBtn='<a class="caozuo tzjs" style="#ababab" href="#" >立即出借</a>';
        	}else{
        		strBtn='<a class="caozuo tzjs cd-popup-trigger2" href="#" >立即出借</a>';
        	}
        	script.src="/js/chujieTK_xian.js";
        	$(".jineBtn").append(strBtn);
        	document.getElementsByTagName('head')[0].appendChild(script);
        	funJindu();
        },
		error:function(result){    	
    	}
    });
    
    $.ajax({
        type: "get",
        url: "/portal/detial/detailExDDT/?OID="+OID,
        dataType: "json",
        success: function(result){
        	$(".p-content").text(result.loanuse)
        	if(result.style=='per'){
        		$(".messagebox02").remove();
        		$(".messagebox01 dl").eq(0).find('dd').eq(0).find('span').text(result.realName);
            	$(".messagebox01 dl").eq(0).find('dd').eq(1).find('span').text(result.gender);
            	
            	$(".messagebox01 dl").eq(1).find('dd').eq(0).find('span').text(result.age+'岁');
            	$(".messagebox01 dl").eq(1).find('dd').eq(1).find('span').text(result.education);
            	
            	$(".messagebox01 dl").eq(2).find('dd').eq(0).find('span').text(result.idcard);
            	$(".messagebox01 dl").eq(2).find('dd').eq(1).find('span').text(result.mobile);
            	
            	$(".messagebox01 dl").eq(3).find('dd').eq(0).find('span').text(result.hkszd);
            	$(".messagebox01 dl").eq(3).find('dd').eq(1).find('span').text(result.hyzk);
        	}else{
        		$(".messagebox01").remove();
        		$(".loanTit").html("<span></span>借款人信息");
        		$(".messagebox02 dl").eq(0).find('dd').eq(0).find('span').text(result.jkqy);
            	$(".messagebox02 dl").eq(0).find('dd').eq(1).find('span').text(result.sshy);
            	
            	$(".messagebox02 dl").eq(1).find('dd').eq(0).find('span').text(result.jkqy);
            	$(".messagebox02 dl").eq(1).find('dd').eq(1).find('span').text(result.sshy);
            	
            	$(".messagebox02 dl").eq(2).find('dd').eq(0).find('span').text(result.clsj);
            	$(".messagebox02 dl").eq(2).find('dd').eq(1).find('span').text(result.zczb);
            	
            	$(".messagebox02 dl").eq(3).find('dd').eq(0).find('span').text(result.qydz);
            	$(".messagebox02 dl").eq(3).find('dd').eq(1).find('span').text(result.xyzk);
            	
        	}
        	
        	
        	
        },
		error:function(result){    	
    	}
    });
    
    $.ajax({
        type: "get",
        url: "/portal/detial/auditDDTInfo/?OID="+OID,
        dataType: "json",
        success: function(result){
        	for(var i=0;i<result.length;i++){
        		var str='<div class="informationImg_box"><a href="/p2p/cn/com/hoonsoft/servlet/ServletAction?handleClassName=fileDownload&OID='+result[i].photoOID+'"><img src="/p2p/cn/com/hoonsoft/servlet/ServletAction?handleClassName=fileDownload&OID='+result[i].photoOID+'"><p class="psnr hide">'+result[i].nameCN+'</p></a></div>';
        		$(".informationImg_wrap").append(str);
        	}
        	$(".informationImg_wrap").append('<div class="clear"></div>');
        	
        	$("body").append('<script src="/js/zoom_new.js?v=110"><\/script>');
        },
		error:function(result){    	
    	}
    });
    
    $.ajax({
        type: "get",
        url: "/portal/detial/ddtlendRecord/1?OID="+OID,
        dataType: "json",
        success: function(result){
        	console.log(result);
        	for(var i=0;i<result.data.length;i++){
        		var str='<tr class="tr02"><td>'+(i+1)+'</td><td>'+result.data[i].name+'</td><td>'+result.data[i].money+'元</td><td>'+result.data[i].sj+'</td></tr>';
        		$(".tabxq tbody").append(str);
        	}
        	if(result.totalNumber<1){
    	        $(".Newtz_liu").css("display","block");
    	    }
        	if(result.totalNumber<=10){
        		$("#page").remove();
        	}else{
        		$("#page").paging({
            		totalPage: result.totalPage,
            		totalSize: result.totalNumber,
            		callback: function(num) {
            			$.ajax({
            		        type: "get",
            		        url: "/portal/detial/ddtlendRecord/"+num+"?OID="+OID,
            		        dataType: "json",
            		        success: function(result){
            		        	$(".tabxq tbody").find("tr").remove();
            		        	for(var i=0;i<result.data.length;i++){
            		        		var str='<tr class="tr02"><td>'+((num-1)*10+(i+1))+'</td><td>'+result.data[i].mobile+'</td><td>'+result.data[i].money+'元</td><td>'+result.data[i].sj+'</td></tr>';
            		        		$(".tabxq tbody").append(str);
            		        	}
            		        },
            				error:function(result){    	
            		    	}
            		    });
            		}
            	})
        	}
        	
        	
        },
		error:function(result){    	
    	}
    });
    
    
    /* 特权模块 */
    $.ajax({
        type: "get",
        url: "/portal/detial/ddtprivilege/?OID="+OID,
        dataType: "json",
        success: function(result){
        	for(var i=0;i<result.length;i++){
        		var str='<div class="redbag01"><div class="bg01 addxi"></div><div class="bg02 addxi"></div><div class="redbagimg"><div class="imggray">';
            	str+='<div class="textone"><div class="hbtop"><div class="leftboxhb"><span>'+result[i].privilegeRange+'</span>%</div>';
            	str+='<dl class="chrome_dl_tit"><dd>'+result[i].privilegeName+'</dd><dd>加息时间：<span class="date_jq">'+result[i].privilegeTerm+'</span>天</dd><dd>出借限额：'+result[i].lowAmount+'元</dd></dl>';
            	str+='</div><div class="hbbottom">有效期：'+result[i].ksDate+'至'+result[i].jsDate+'</div></div>';
            	str+='<img src="<%=pathUrl %>/images/addxi_new_pulple.png" width="169" height="69"><div class="active_click"><img class="tq_click_icon" src="<%=pathUrl %>/images/tq_click_icon.png"></div></div>';
            	str+='<input type="hidden" class="mapoid_old" name="OID" value="'+result[i].mappingOID+'" /><input type="hidden" class="tqoid_old" name="OID" value="'+result[i].privilegeOID+'" /><input type="hidden" class="tq_lowAmount" value="'+result[i].lowAmount+'" /><input type="hidden" class="tq_privilegeType" value="'+result[i].privilegeType+'" />';
            	str+='</div></div>';
            	$(".boxselct").append(str);
        	}
        	$(".boxselct").append('<div class="clear"></div>');
        	$("body").append('<script src="\/js\/privilegeJS.js?ver=115"><\/script>');
        },
		error:function(result){    	
    	}
    });
    
  //充值控制数值JS
    funcData.eChange.COM();
})



//配置错误提示的节点，默认为label，这里配置成 span （errorElement:'span'）
$.validator.setDefaults({
	errorElement:'span'
});

//配置通用的默认提示语
$.extend($.validator.messages, {
	required: '必填',
    equalTo: "两次输入不一致"
});

function GetRequest2() {
	  
	  var url = location.search; //获取url中"?"符后的字串
	   var theRequest = new Object();
	   if (url.indexOf("?") != -1) {
	      var str = url.substr(1);
	      strs = str.split("&");
	      //alert(strs);
	      for(var i = 0; i < strs.length; i ++) {
	         theRequest[strs[i].split("=")[0]]=(strs[i].split("=")[1]);
	      }
	   }
	   return theRequest;
	}


</script>
<script src="/js/chujieTK_xian.js"></script>
</html>
