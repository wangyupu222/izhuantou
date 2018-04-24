<%@ page contentType="text/html; charset=UTF-8" %>
<!doctype html>
<html><head>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta charset="utf-8">
<title>砖头网-上砖头儿，有赚头儿！</title>
<link rel="stylesheet" type="text/css" href="/css/style-common.css?ver=114">
<link rel="stylesheet" type="text/css" href="/css/confirm_lend.css">
<link rel="stylesheet" type="text/css" href="/css/comsty.css">
<link rel="stylesheet" type="text/css" href="/css/pedetails.css?ver=118">
<link rel="stylesheet" href="/css/responsiveslides.css">
<link rel="stylesheet" href="/css/zoom.css">
<Link rel="shortcut icon" href="/images/zhuan.ico" />
<script type="text/javascript" src="/js/jquery-1.8.3.min.js"></script>
<script src="/js/com.js?ver=116"></script>
<script src="/js/base.js"></script>
<!--<script src="/js/comjd.js"></script>-->
<script src="/js/resubmit.js"></script>
<script src="/js/paging.js"></script>
<!--[if lt IE 9]>
    <script src="/guide/css3-mediaqueries.js"></script>
<![endif]-->
<!--  下拉菜单 star -->
<%-- <link href="/css/jquery.searchableSelect.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/js/jquery.searchableSelect.js"></script> --%>
<!-- 下拉菜单结束 -->
<!--首页焦点图-->
<script type="text/javascript" src="/js/koala.min.1.5.js"></script>
<style type="text/css">
.cd-popup-container1{ width:700px;}/*充值框大小*/
.cd-popup-container2{ width:860px;margin: 40px auto;}/*出借框大小*/
.hide_liu{  width: 100%;  height: 500px;  text-align: center;  background:#fff url("images/xiangqing_liu.png")no-repeat center;  display: none;  }
.tishi_text{
padding: 12px 0px;
font-size: 15px;}
.tishi_text a:hover{
text-decoration:underline;
}
 .yuyue_tishi{position: relative;} 
.sylytext{
position: relative;
}
.yuyue_tishi span{cursor: pointer;text-decoration:underline;}
.yuyue_tishi span:hover{
color: #55b0fd;
}

.tishi_box {
    width: 450px;
    border: 1px solid #d0eef7;
    border-radius: 6px;
    position: absolute;
    left: -310px;
    top: 10px;
    z-index: 99;
    display: none;
    text-align:left;
    background-color: rgba(255, 255, 255, 1.00);
}
.tishi_wrap p {
    font-size: 14px;
}
.tishi_wrap h3 {
    font-size: 16px;
    color: #666;
    margin-bottom: 10px;
}
.tishi_wrap {
    width: 400px;
    margin: 0 auto;
    font-size: 16px;
    color: #666;
    margin-top: 15px;
    text-align:left;
    padding-bottom:25px;
}
.cd-popup-container1, .cd-popup-container2, .cd-popup-container4, .cd-popup-container5, .cd-popup-containerh{ width:875px;}
.cd-popup-containerh{width:1040px;}
.cd-popup-container2{width:1000px;}
.detailscon {
  overflow: visible;
}
.yuyue_btn{
    background-color: #1bbc9b;
}
.yuyue_btn:hover{
    background-color: #1aa88b;
}
#iframepage_new3{
min-height:200px;
}
.comneichujie .close{position: absolute;right:5px;}
.e_money_wrap{
float:left;
display:none;
margin-left:45px;
}
#e_money{
color:#ff7800;
}
.commit-2 span.spjine {
   text-align:right;
    width: 195px;
}
@media screen and (max-width:1980px){
body{
position: relative;
}
.cd-popuph{
position: absolute;
}
.cd-popuph .cd-popup-containerh{
    margin: 680px auto;
    margin-bottom:0px;
}
}
@media screen and (max-width:1366px){
#iframepage1{
height:360px;
}
}
@media screen and (max-width:1136px){
.hhbody{
 position: relative;
}
.hhbody .cd-popup1,.cd-popuph,.cd-popup2{
 position: absolute;
}
#iframepage1{
height:360px;
}
}
/* 加息 */
.qxbox .sp02 .add_new {
    font-size: 18px;
    color: #ff7800;
}
.qxbox .sp02 .add_new .add_sign {
    color: #666;
    font-size: 14px;
}
.leon_zhanbox .boxone span.sp01 em.add_new {
    font-size: 20px;
}
</style>

</head>
<body class="hhbody">
 <div class="HHyuyue_wrap">
<div class="HHyuyue_box">
<img class="close_icon_01" src="/images/close_icon_01.png">
<div class="HHyuyue_main">
<div class="HHyuyue_left">
<div class="HHyuyue_leftBox">
<img src="/images/yuyueti_icon.png">
<p>若“标的剩余可投”金额低于您的出借需求金额，您可以在完成剩余可投金额的出借之后，利用“预约出借” 功能，继续出借。注：“预约出借”功能在完成全部剩余可投金额出借后，才会开启。</p>
<div style="clear:both;"></div>
</div>
<div class="HHyuyue_leftBox">
<img style="margin-top: 12px;" src="/images/yuyueti_icon02.png">
<p>例：我要投10万元，“标的剩余可投”仅为43200元了，不能投了？不，先按<span><img class="ljcj_btn_icon" src="/images/ljcj_btn_icon.png"></span>43200元， 再 <span><img class="ljcj_btn_icon" src="/images/yycj_btn_icon.png"></span>56800元，需求＆收益两不误！</p>
<div style="clear:both;"></div>
</div>
<div class="HHyuyue_right">
<img src="/images/yuyue_break.png">
</div>
<div style="clear:both;"></div>
</div>


</div>
<%-- <p>如果当前“标的剩余可投额度”不能满足您的出借金额需求，您可以先行出借当前标的剩余可投额度，再使用预约出借功能，来满足您的出借金额需求。</p>
例：如您本次想要出借10万元，但“标的剩余可投额度”只有4万元，您可<span><img class="ljcj_btn_icon" src="/images/ljcj_btn_icon.png"></span>4万元，再<span><img class="ljcj_btn_icon" src="/images/yycj_btn_icon.png"></span>剩余的6万元，这样既能满足您的需求，也不会影响您的收益。 --%>
</div>
</div>
<div class="show_hhyu_box"><img class="show_hhyu" src="/images/Loan-notes.png"></div> 
<% try{ %>
<hoontag:TagAction handleClassName="pageHuanhuanDetails" />
<!-- 环环投详情页面部分标详情弹出框 -->


<div class="cd-popuph">
<div class="cd-popup-containerh">

<!--子标的详情-->
<div class="close-cl"><a class="close cd-popup-close"></a></div>
<div class="comnei ">
<iframe src="/portal/detial/hhDetails_iframe_new.jsp" class="zqzrifram" width="100%" height="780" id="iframepage_hht" name="iframe" frameborder="0" scrolling="no" marginheight="0" marginwidth="0" style="margin-top:0;"></iframe> 
  </div>
    <!--充值结束-->

       
    
       
        
     </div>
</div>

<!--===============================出借弹窗===================================-->
<!--出借弹窗-->

<div class="cd-popup2">
<div class="cd-popup-container2">
<form action="" method="post" name="FormChujie" id="FormChujie" >
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
                	<span class="sp01 org"></span>                   	
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
                <div class="channel_disbursement hide"><h1>支付渠道</h1><div class="cg_wrap"><div>华兴银行存管</div><div class="fy_channel">富友账户存管</div></div><div class="chanel_icon"><div class="channel_tishi">该出借产品支持富友支付渠道</div></div><div class="clear"></div></div>
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
        <p class="commit-2"><span>市场有风险，出借需谨慎</span><button id="class='colseok'" name="class='colseok'" type="submit" value="确认" class="zcbtn_input confirmbtn">确认</button><!-- <a class="colseok" href="tzdetailsok.jsp">确认</a> --><span class="spjine">需支付￥<em id="paymoney"></em>元</span></p>
   </div>    
       <!--确认出借结束-->
   </form>    
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
<!--充值-->
	<div class="comnei ">
        <a class="close cd-popup-close"></a>
        <p class="commit-1"><span>审核信息</span></p>
       	<div class="yunull yunullimg">
          	<img src="/images/xinss.jpg" />
        </div> 
    </div>

       <!--充值结束-->
       
       <!--充值成功-->
   <div class="comnei hide">
       <a class="close cd-popup-close"></a>
       <p class="commit-1"><span>充值</span></p>
       <div class="yunull">
          	<div class="imgczcg"><img src="/images/czok.png" width="313" height="269"></div>
            <div class="textmes01">恭喜！充值成功</div>
            <div class="textmes02"><span>5</span>秒后自动跳转至出借页面</div>
           
        </div>
        <p class="commit-2"><span>理财有风险，投资需谨慎</span><a class="colseok" href="#">确认</a></p>
   </div>    
       <!--充值成功结束-->
       
        
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
    	
    </div>
	
    <!--项目详情开始-->
    <div class="con detailscon">
    	
    	<div class="detailefts">
        	<div class="topdetail">
            	<div class="imgpro hide"><img src="/images/hhttyb.png" /></div>
            	<div class="xmtits">
            	<%if(session.getAttribute("userMobile") != null){ %>
				<% }else{ %>
				 <a class="text-xstext" href="agreement_z.jsp?contractType=4" target="_blank">《环环投服务协议》</a>
				<%}%>
				</div>
                <div class="huiback">
                	<%-- <div class="qxbox qx01 jxnhll hide"><span class="sp01">预期年化收益</span>
                	<span class="sp02 oreg"><em ><%=request.getAttribute("nhll")%></em>%+<em class="add_new"><%=request.getAttribute("JXother")%><em class="add_sign">%</em></em></span><span class="sp02 oreg" style="display:none;"><em id="data-yjnh"><%=request.getAttribute("allnhll") %></em>%</span>
                	</div> --%>
                	<div class="qxbox qx01 qxhh ybnhll"><span class="sp01">预期年化收益</span>
                	<span class="sp02 oreg"><em id="data-yjnh"></em>%</span>
                	</div>
                	
                 	
                    <div class="qxbox qx01 qx02"><span class="sp01">出借期限</span><span class="sp02 oreg"><em id="data-cjqx"></em>个月</span></div>
                    <%-- <div class="qxbox qx01"><span class="sp01">共分散出借于</span><span class="sp02 oreg"><em><%=request.getAttribute("zbsl") %></em>标</span></div> --%>
                </div>
       			<div class="botbox">
                	<div class="botfindbox">
                	<div class="botfindleft"><span class="tit"><span style="width:100px;display: inline-block;">共分散于</span><span class="conter zbslCon"><em></em>标</span></div>
                	<div class="botfindleft"><span class="tit"><span style="width:100px;display: inline-block;">保障方式</span><span class="conter">保理公司回购代偿</span></div>
                	<div style="clear:both;"></div>
                	</div>
                	<div class="botfindbox">
                	<div class="botfindleft"><span class="tit"><span style="width:100px;display: inline-block;">回款方式</span><span class="conter hkfsCon"></span></div>
                	<div class="botfindleft"><span class="tit"><span style="width:100px;display: inline-block;">最低出借额</span><span class="conter">100元</span></div>
                	<div style="clear:both;"></div>
                	</div>
                	<div class="botfindbox">
                	<div class="botfindleft"><span class="tit"><span style="width:100px;display: inline-block;">计息方式</span><span class="conter">当日计息</span></div>
                	<div style="clear:both;"></div>
                	</div>
                   
                </div>            
            </div>
            <!--右侧小窗-->
        </div>
        <div class="detairights">
        <input id="limit_val" type="hidden" value="6000.00" readonly="readonly" >
        <input id="switch_val" type="hidden" value="0" readonly="readonly" >
        	<div class="ljtzbox">
	            <div class="sylytext yuyue_tishi">当前可进行<span class=" yuyuetishi_text">预约出借</span>
	            <div class="tishi_box">
								<div class="tishi_wrap" style="line-height: normal;">
								<h3>什么是预约出借？</h1>
								<p style="text-indent:2em;">预约出借，是砖头网针对环环投用户特别推出的一项省心、安全的预约功能。</p>
<p style="text-indent:2em;margin-top:10px;">当环环投计划中暂无标的时，预约出借功能即时开启。出借用户可以根据自己的出借偏好，选择不同出借时间的环环投出借计划，进行预约出借，待所选计划发布新的优质标的时，系统会按出借用户预约出借时间进行自动进行债权匹配，将出借用户资金分散至计划中的各个标的中。此功能可避免用户资金站岗，无须实时监控标的发放状态，同时，从预约出借开始，出借资金即可计息，从而增加用户收益。</p>
								</div>
								</div>
	            </div>
            	<div class="sylytext syktMain hide">标的剩余可投：<span id="sykt"><em></em>元</span></div>
               <div class="czzc">
               <label class="boxinput" id="zhyue">账户余额：<%if(session.getAttribute("userMobile") != null){ %>
				<em></em>元
				<% }else{ %>
				 登录后可查看余额
				<%}%></label>
                <%if(session.getAttribute("userMobile") != null){%>
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
				<input type="checkbox" checked="checked"><a class="bottext_hou">我已阅读并同意<a class="blurac" href="agreement_z.jsp?contractType=4" target="_blank" >《环环投服务协议》</a><a class="text-xstext text-xstext_new" href="agreement_zmortgage.jsp?contractType=16" target="_blank">《借款协议》</a>
					<a class="text-xstext text-xstext_new" href="agreement_zmortgage.jsp?contractType=18" target="_blank">《债权预回购协议》</a></a>
				<% }else{ %>
				 <a class="bottext_qian" href="register.jsp">还没有账号？立即注册</a> 
				<%}%>
               </div>
               <%-- <%if(request.getAttribute("userMobile") != null){ 预约出借%>
               <div class="yuyue_tishi hide"><span class="yuyuetishi_text">什么是预约出借？</span>
               <div class="tishi_box">
								<div class="tishi_wrap">
								<h3>什么是预约出借？</h1>
								<p style="text-indent:2em;">预约出借，是砖头网针对环环投用户特别推出的一项省心、安全的预约功能。</p>
<p style="text-indent:2em;margin-top:10px;">当环环投计划中暂无标的时，预约出借功能即时开启。出借用户可以根据自己的出借偏好，选择不同出借时间的环环投出借计划，进行预约出借，待所选计划发布新的优质标的时，系统会按出借用户预约出借时间进行自动进行债权匹配，将出借用户资金分散至计划中的各个标的中。此功能可避免用户资金站岗，无须实时监控标的发放状态，同时，从预约出借开始，出借资金即可计息，从而增加用户收益。</p>
								</div>
								</div>
               </div>
               <% }else{ %>
				<a class="" href="login.jsp">充值</a>
				<%}%> --%>
            </div>
          
        </div>
        <div style="clear:both;"></div>
    </div>
    <!--项目详情结束-->
    
    <div class="con qhdetailxmcons">
    	 <div class="qhdetail">
            	<div class="hht_tabtitcons tabtitcons">
                	<div class="active">项目介绍</div>
                    <div>出借记录</div>
                    <div>安全保障</div>
                    <div>常见问题</div>
                </div>
                <div class="neircon">
                	<div class="optabg ">
                    	<div class="boxxqjs">
                        	<div class="title-p"><span></span>计划描述</div>
                            <p class="p-content">环环投是砖头网推出的债权包出借计划，在出借人决定并确定出借后，系统实时将出借金额分散匹配至具有真实债权的标的中，由于环环投是一款风险高度分散的出借计划，所以环环投具有风险分散，收益稳定的特点，是一款给予用户超省心的出借体验的计划。

</p>

							<div class="title-p"><span></span>获得收益</div>
                            <p class="p-content">出借当日开始计息，付息日次日收到利息，直至计划到期、完成债权转让退出。到账收益会根据约定收益获得。

</p>
							<div class="title-p"><span></span>如何退出</div>
                            <p class="p-content">主动退出：通过债权转让退出，资金返至您的账户"可用余额"。</p>

                        </div>
                        <div class="boxxqjs boxxqjs2">
                        	<div class="title-p"><span></span>标的组成</div>
                        	 <%if(session.getAttribute("userMobile") != null){ %>
                           	<table class="tabhuan" cellpadding="0" cellspacing="0">
                           	<thead>
                            	<tr>
                                	<th>名称</th>
                                    <th>借款利率</th>
                                    <th>借款金额</th>
                                    <th>状态</th>
                                    <th></th>
                                </tr>
                                </thead>
                                <tbody>
                                 
                                </tbody>
                            </table>
                            <div id="page" class="page_div pagebing"></div>
                             <%}else{ %>
                            <table class="tabhuan" cellpadding="0" cellspacing="0">
                            	<tr>
                                	<th>名称</th>
                                    <th>借款利率</th>
                                    <th>借款金额</th>
                                    <th>状态</th>
                                    <th></th>
                                </tr>
                               
                                <tr>
                                  <td class="tishi_text" colspan='5' style="text-align:center;">请先<a href='login.jsp'>登录</a>查看详情信息</td>
                                  
                                </tr>
                                
                            </table>
                            <%} %>
                            </div>
                        
                        
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
                        <div id="page" class="page_div pageLend"></div>
                        <div class="hide_liu HHtz_liu"></div>
                    </div>
                   
                   <web:ContentLeaf leafName="SingleArticle" OID="da80654ac057fa8b00c30d73d50bbc1e" name="aqbz"></web:ContentLeaf>
                   	<div class="optabg hide">
                    	<div class="boxxqjs safe_baozhang">
                        	<div class="title-p"><span></span>完整全面的信息披露<a style="clear:both"></a></div>
                            <div class="safe_content"><h3>海量真实融资项目</h3><div> 全面信息披露与优质真实的融资项目，真诚相待每一位平台用户，保障平台信息完全透明。严格向监管者和社会公众履行告知义务，将所应披露的信息真实、及时、完整的呈现，让平台用户更放心；<br>
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
采用阿里云云服务ECS技术支持，骨干机房搭配独享带宽、云盾DDoS高防IP系统等一系列专业技术手段来保障网站登录、支付相关数据安全性。<br>
严格依照国家相关法律法规设立平台，与盈科律师事务所达成战略合作关系，在履行《网络借贷信息中介机构业务活动管理暂行办法》相关义务和规定的同时，平台受合同法、民法通则等法律法规以及最高人民法院相关司法解释规范。</div></div>
                          
                    </div>
                    </div>
                    <web:ContentLeaf leafName="SingleArticle" OID="da8187ccc057fa8b01ca8782dc4dd5ff" name="cjwt"></web:ContentLeaf>
                    
                    	
                    <div class="optabg hide">
                    <div class="boxxqjs safe_baozhang">
                        	<div class="title-p title_hei">1.什么是环环投？</div>
                            <div class="safe_content"><div>环环投是砖头网将多个优质标的，通过智能系统集合而成的一款出借计划，该计划将出借资金分散，降低了可能存在的坏账风险，同时收益直观，兼顾了出借资金时的灵活性和安全性，通过该计划用户可以省时省心的将资金同时出借给多位借款人，实现轻松出借。
</div></div>
                          
                    </div>
                    	<!-- <div class="boxxqjs safe_baozhang">
                        	<div class="title-p title_hei">2.环环投到期前是否可以债权转让？</div>
                            <div class="safe_content"><div>环环投到期前可以债权转让，如债权转让，需支付债权转让手续费，手续费为转让本金千分之二。</div></div>
                          
                    </div> -->
                    <div class="boxxqjs safe_baozhang">
                        	<div class="title-p title_hei">2.环环投债权如何匹配？</a></div>
                            <div class="safe_content"><div>用户成功出借环环投计划后，系统将根据真实债权的实际情况自动为其分散匹配，完成分散出借，有效降低风险。</div></div>
                          
                    </div>
                    <div class="boxxqjs safe_baozhang">
                        	<div class="title-p title_hei">3.环环投计息方式如何？</a></div>
                            <div class="safe_content"><div>确认出借或预约出借成功后，当日开始计算利息。</div></div>
                          
                    </div>
                    <div class="boxxqjs safe_baozhang">
                        	<div class="title-p title_hei">4.如出现逾期情况，如何处理？</div>
                            <div class="safe_content"><div>当该项目下某个债权出现逾期等情况时，将由保理公司（鼎星耀阳）进行债权回购，确保出借人利益不受损失。</div></div>
                          
                    </div>
                    <div class="boxxqjs safe_baozhang">
                        	<div class="title-p title_hei">5.环环投成功预约出借后，收到两条富友推送的出账短信是为什么？并且出账金额与实际出借金额不符是为什么？</a></div>
                            <div class="safe_content"><div>因出借时为预约出借状态，尚未匹配债权，所以会收到富友推送的两条短信消息：收到第一条富友推送的出账短信提醒是告知用户，系统已自动为其匹配到真实债权部分时的出账金额（但只推送第一条匹配债权出账资金情况）；第二条富友推送的出账短信提醒是告知用户，当日出借总金额。</div></div>
                          
                    </div>
                    <div class="boxxqjs safe_baozhang">
                        	<div class="title-p title_hei">6.是否支持债权转让？限定条件是什么？</div>
                            <div class="safe_content"><div>支持债权转让；限定条件为：持有出借计划时长大于三个月（不含三个月），同时剩余期限需大于一个月（不含一个月）。</div></div>
                          
                    </div>
                    <div class="boxxqjs safe_baozhang">
                        	<div class="title-p title_hei">7.是否有其他安全保障机制？</div>
                            <div class="safe_content"><div> 
主要包括以下七大机制： <br>
（1）分散出借：创新技术，帮助出借人将资金持续、自动地分散到多个贷款标的，有效降低风险。 <br>
（2）银行级风控：结合高效信审及实地审核，标准化风险定价，注重资产安全，有效控制风险。 <br>
（3）质保服务专款账户制度：设立质保服务专款账户，应对可能发生的逾期风险。尽心尽责对待应收账款，保障出借人的利益不受损失，降低出借人资金的逾期风险。<br>
（4）技术保障：业界领先的核心技术功能（包括用户交易体系、结算清算等底层功能），并通过分布式平台架构提供较高级别的技术安全保障。<br> 
（5）法律保障：砖头网平台及业务受法律保护，透明合规，强大的法务团队为砖头网用户提供法律支持及保障。 <br>
（6）隐私保障：通过全程加密、系统隔离、安全检测，提供全方位的隐私保障。<br>
（7）保理公司：与第三方保理公司（鼎星耀阳）合作，保障平台用户在平台产生的借贷交易。一旦产生逾期的应收账款，保理公司（鼎星耀阳）将对逾期应收账款进行回购，并将承担后续的回购债权及债务追偿工作。
</div></div>
                          
                    </div>
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
<!--验证表单-->
<script src="/js/jquery.validate.js"></script>
<script src="/js/card_tishi2.js?ver=113"></script>

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
					//alert("aaa");
					$(this).attr("disabled","disabled");
				});
			
			form.submit();
			return validataForm("confirmbtn");
			
		}
	})
	//$(".select_privileges").searchableSelect();
/* 	$('.cd-popup2').on('click', function(event){
    if( $(event.target).is('.colseok') || $(event.target).is('.cd-popup2') ) {
        event.preventDefault();
        $(this).removeClass('is-visible1');
    }
}); */
//充值控制数值JS
    funcData.eChange.COM();
})
//   留白
$(function(){
    if($(".tr02").length<1){
        $(".HHtz_liu").css("display","block");
    }
    setIframeHeight(document.getElementById('iframepage_new3'));
    
    /* 预约出借提示 */
    var oWidth=($(window).width());
	$(window).resize(function() { 
	oWidth=($(window).width()); 	
	});
    
    // var res=document.cookie.substr(18,5); 
    var res_new=document.cookie.indexOf("HHcookie=");
	//alert("当前cookies="+"("+res_new+")");
	//判断是否来过
	if(res_new==-1){
		$(".HHyuyue_wrap").show(500);
		$(".HHyuyue_box").css("display","block");
		$(".show_hhyu_box").css("display","none");
		document.cookie="HHcookie=hhmin";
	}else{
		$(".HHyuyue_wrap").animate({"left":"-"+oWidth},800,function(){
			$(".HHyuyue_box").hide(300);
			$(".show_hhyu_box").show(400);
		});
	} 
})

function setIframeHeight(iframe) {
	if (iframe) {
	var iframeWin = iframe.contentWindow || iframe.contentDocument.parentWindow;
	if (iframeWin.document.body) {
	iframe.height = iframeWin.document.documentElement.scrollHeight || iframeWin.document.body.scrollHeight;
	}
	}
	};
	function GetParentWindowHight()
	{
		var oHeight=$(window).height();
		var oWidth=$(window).width();
		return oWidth;
	}
//可投金额小于等于0的时候按钮变为预约出借
$(function(){
	
	
	
	var Request = new Object();
	Request = GetRequest2();
	var OID=Request["OID"];
    
    $.ajax({
        type: "get",
        url: "/portal/detial/detialHHT/?OID="+OID,
        dataType: "json",
        success: function(result){
        	console.log(result);
        	$(".xmtits").text(result.xmmc);
        	$(".lend_title div").text(result.xmmc);
        	if(result.jx==true){
        		$(".jxnhll .sp02 em").eq(0).text(result.nhll);
        		$(".jxnhll .sp02 .add_new").text(result.jxother);
        		$(".jxnhll .sp02 #data-yjnh").text(result.nhll+result.jxother);
        		
        		var jxStr='<em id="tk-yjnh">'+result.nhll+'<\/em>%+<em class="add_new">'+result.jxother+'<\/em>%';
        	}else{
        		$(".ybnhll .sp02 #data-yjnh").text(result.nhll);
        		var jxStr='<em id="tk-yjnh">'+result.nhll+'<\/em>%';
        	}
        	$(".lend_content .boxone01 .sp01").html(jxStr);
        	$("#data-cjqx").text(result.cjqx);
        	$("#tk-cjqx").text(result.cjqx);
        	$(".hkfsCon").text(result.repaymentType);
        	$(".zbslCon em").text(result.zbsl);
        	
        	if(result.useMoney!='null' || result.useMoney!=''){
        		$("#zhyue em").text(result.useMoney);
        	}else{  
        		
        	}
        	
        	var strBtn;
        	var script=document.createElement("script");  
        	script.type="text/javascript"; 
        	if(result.ktje<=0){
        		$(".yuyue_tishi").removeClass("hide");
        		$(".syktMain").remove();
        		if(result.productStatus==10){
        			strBtn='<a class="caozuo tzjs yuyue_btn" href="#" style="background: rgb(171, 171, 171); cursor: auto;" >预约出借</a>';
        		}else{
        			strBtn='<a class="tzjs caozuo cd-popup-trigger2 yuyue_btn" href="#" >预约出借</a>';
        		}
        		script.src="/js/chujieTKhhyuyue_xian.js";
        	}else{
        		$(".syktMain").removeClass("hide");
        		$(".yuyue_tishi").remove();
        		$(".syktMain #sykt em").text((result.ktje).toFixed(2));
        		if(result.productStatus==10){
        			strBtn='<a class="caozuo tzjs" href="#" style="background: rgb(171, 171, 171); cursor: auto;" >立即出借</a>';
        		}else{
        			strBtn='<a class="caozuo tzjs cd-popup-trigger2" href="#" >立即出借</a>';
        		}
        		script.src="/js/chujieTK_xian.js"; 
        		
        	}
        	$(".jineBtn").append(strBtn);
        	document.getElementsByTagName('head')[0].appendChild(script);
        	
        },
		error:function(result){    	
    	}
    });
    
    /* 特权模块 */
    $.ajax({
        type: "get",
        url: "/portal/detial/hhtprivilege/?OID="+OID,
        dataType: "json",
        success: function(result){
        	console.log(result);
        	for(var i=0;i<result.length;i++){
        		var str='<div class="redbag01"><div class="bg01 addxi"></div><div class="bg02 addxi"></div><div class="redbagimg"><div class="imggray">';
            	str+='<div class="textone"><div class="hbtop"><div class="leftboxhb"><span>'+result[i].privilegeRange+'</span>%</div>';
            	str+='<dl class="chrome_dl_tit"><dd>'+result[i].privilegeName+'</dd><dd>加息时间：<span class="date_jq">'+result[i].privilegeTerm+'</span>天</dd><dd>出借限额：'+result[i].lowAmount+'元</dd></dl>';
            	str+='</div><div class="hbbottom">有效期：'+result[i].ksDate+'至'+result[i].jsDate+'</div></div>';
            	str+='<img src="/images/addxi_new_pulple.png" width="169" height="69"><div class="active_click"><img class="tq_click_icon" src="/images/tq_click_icon.png"></div></div>';
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
	
    
    
    <%if(session.getAttribute("userMobile") != null){ %>
    $.ajax({
        type: "get",
        url: "/portal/detial/hhtTagComponent/1?OID="+OID,
        dataType: "json",
        success: function(result){
        	console.log(result);
        	for(var i=0;i<result.data.length;i++){
        		var syqs=result.data[i].syqs;
        		var jkmc=result.data[i].jkmc;
        		var jkmcStr01 = jkmc.substring(0, 3);
        		var jkmcStr02 = jkmc.substring(0, 5);
        		if(result.data[i].syqs=='null' || result.data[i].syqs==''){
        			syqs=0;
        		}
        		var str='<tr><td>'+result.data[i].jkmc+'</td><td>'+result.data[i].jkll+'%</td><td>'+result.data[i].jkje+'</td><td class="hide" id="zzOID"></td><td>'+syqs+'\/'+result.data[i].term+'已收到<td>';
        		if("ICD"==jkmcStr01 || "Z-ICD"==jkmcStr02){
        			str+='<a class="" onclick="window.changevalueICD(\''+result.data[i].oid+'\',\''+result.data[i].syqs+'\',\''+result.data[i].term+'\',\''+result.data[i].debitCreditOID+'\')">详情</a>';
        		}else{
        		str+='<a class="" onclick="window.changevalue(\''+result.data[i].oid+'\',\''+result.data[i].syqs+'\',\''+result.data[i].term+'\',\''+result.data[i].debitCreditOID+'\')">详情</a>';
        		}
        		str+='</td></tr>';
        		$(".tabhuan tbody").append(str);
        	}
        	if(result.totleNum<=10){
        		$(".pagebing").remove();
        	}else{
        		$(".pagebing").paging({
            		totalPage: result.totalPage,
            		totalSize: result.totleNum,
            		callback: function(num) {
            			$.ajax({
            		        type: "get",
            		        url: "/portal/detial/hhtTagComponent/"+num+"?OID="+OID,
            		        dataType: "json",
            		        success: function(result){
            		        	console.log(result);
            		        	$(".tabhuan tbody").find("tr").remove();
            		        	for(var i=0;i<result.data.length;i++){
            		        		var syqs=result.data[i].syqs;
            		        		var jkmc=result.data[i].jkmc;
            		        		var jkmcStr01 = jkmc.substring(0, 3);
            		        		var jkmcStr02 = jkmc.substring(0, 5);
            		        		if(result.data[i].syqs=='null' || result.data[i].syqs==''){
            		        			syqs=0;
            		        		}
            		        		var str='<tr><td>'+result.data[i].jkmc+'</td><td>'+result.data[i].jkll+'%</td><td>'+result.data[i].jkje+'</td><td class="hide" id="zzOID"></td><td>'+syqs+'\/'+result.data[i].term+'已收到<td>';
            		        		if("ICD"==jkmcStr01 || "Z-ICD"==jkmcStr02){
            		        			str+='<a class="" onclick="window.changevalueICD(\''+result.data[i].oid+'\',\''+result.data[i].syqs+'\',\''+result.data[i].term+'\',\''+result.data[i].debitCreditOID+'\')">详情</a>';
            		        		}else{
            		        		str+='<a class="" onclick="window.changevalue(\''+result.data[i].oid+'\',\''+result.data[i].syqs+'\',\''+result.data[i].term+'\',\''+result.data[i].debitCreditOID+'\')">详情</a>';
            		        		}
            		        		str+='</td></tr>';
            		        		$(".tabhuan tbody").append(str);
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
    <%}%>
    
    $.ajax({
        type: "get",
        url: "/portal/detial/hhtLendRecord/1?OID="+OID,
        dataType: "json",
        success: function(result){
        	console.log(result);
        	for(var i=0;i<result.data.length;i++){
        		var str='<tr class="tr02"><td>'+(i+1)+'</td><td>'+result.data[i].name+'</td><td>'+(result.data[i].principalMoney).toFixed(2)+'元</td><td>'+result.data[i].sj+'</td></tr>';
        		$(".tabxq tbody").append(str);
        	}
        	if(result.totalNumber<1){
    	        $(".HHtz_liu").css("display","block");
    	    }
        	if(result.totalNumber<=10){
        		$(".pageLend").remove();
        	}else{
        		$(".pageLend").paging({
            		totalPage: result.totalPage,
            		totalSize: result.totalNumber,
            		callback: function(num) {
            			$.ajax({
            		        type: "get",
            		        url: "/portal/detial/hhtLendRecord/"+num+"?OID="+OID,
            		        dataType: "json",
            		        success: function(result){
            		        	$(".tabxq tbody").find("tr").remove();
            		        	for(var i=0;i<result.data.length;i++){
            		        		var str='<tr class="tr02"><td>'+((num-1)*10+(i+1))+'</td><td>'+result.data[i].name+'</td><td>'+(result.data[i].principalMoney).toFixed(2)+'元</td><td>'+result.data[i].sj+'</td></tr>';
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


//部分标详情传值
function changevalue(biddingOID,syqs,zqs,zzOID){	
	if(zzOID=="" || zzOID=='null'){
		var imgurl = "/portal/detial/hhDetails_iframe.jsp?biddingOID=" +biddingOID+"&syqs="+syqs+"&zqs="+zqs;
	}else{
		var imgurl = "/portal/detial/hhDetails_iframe_Z.jsp?biddingOID=" +biddingOID+"&syqs="+syqs+"&zqs="+zqs;
	}
	$('.zqzrifram').attr('src', imgurl);

	setTimeout(function () { 
				$('.cd-popuph').addClass('is-visible1');
		    },200);
        //$(".dialog-addquxiao").hide()
        
}
function changevalueICD(biddingOID,syqs,zqs,zzOID){	
	if(zzOID=="" || zzOID=='null'){
		var imgurl = "/portal/detial/hhDetails_iframe_CD.jsp?biddingOID=" + biddingOID+"&syqs="+syqs+"&zqs="+zqs;
	}else{
		var imgurl = "/portal/detial/hhDetails_iframe_ZCD.jsp?biddingOID=" + biddingOID+"&syqs="+syqs+"&zqs="+zqs+"&zzOID="+zzOID;
	}
	$('.zqzrifram').attr('src', imgurl);

	setTimeout(function () { 
				$('.cd-popuph').addClass('is-visible1');
		    },200);
        //$(".dialog-addquxiao").hide()
        
}

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
<script type="text/javascript">
//出借详情切换
$(function(){
	$('body',parent.document).append('<div id="zoom"><a class="close"></a><a href="#previous" class="previous"></a><a href="#next" class="next"></a><div><div class="content loading"></div><h3 class="con_tit">身份证</h3></div></div>');
	})
</script>
</html>
