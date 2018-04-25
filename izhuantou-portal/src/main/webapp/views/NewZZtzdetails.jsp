<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/include/url.jsp" %>
<!doctype html>
<html><head>
<meta charset="utf-8">
<title>砖头网-上砖头儿，有赚头儿！</title>
<link rel="stylesheet" type="text/css" href="/css/style-common.css">
<link rel="stylesheet" type="text/css" href="/css/confirm_lend.css">
<link rel="stylesheet" type="text/css" href="/css/comsty.css">
<link rel="stylesheet" type="text/css" href="/css/pedetails.css?v=114">
<link rel="stylesheet" href="/css/responsiveslides.css">
<Link rel="shortcut icon" href="<%=pathUrl %>/images/zhuan.ico" />
<script type="text/javascript" src="/js/jquery-1.8.3.min.js"></script>
<script src="/js/com.js"></script>
<script src="/js/base.js"></script>
<%-- <script src="/js/comjd.js"></script> --%>
<script src="/js/resubmit.js"></script>
<script type="text/javascript" src="/js/demo_new2.js?v=116"></script>
<script src="/js/paging.js"></script>
<style type="text/css">
.cd-popup-container1{ width:700px;}/*充值框大小*/
.cd-popup-container2{ width:860px;margin: 40px auto;}/*出借框大小*/
.hide_liu{  width: 100%;  height: 500px;  text-align: center;  background:#fff url("images/xiangqing_liu.png")no-repeat center;  display: none;  }
.cd-popup6 .cd-popup-container1 {
    width: 470px;margin:185px auto;
}
.cd-popup6 .commit-1 span{
width:100%;text-align: center;color:#666;font-size:18px;padding-left:0px;
}
.cd-popup6 .tishi_text{
font-size:16px;color:#666;margin:0 auto;width:70%;text-align: left;
}
.yuan_btn{width:250px;hieght:42px;background-color:#55b0fd;margin:0 auto;text-align:center;line-height:42px;border-radius:4px;margin-top: 50px;}
.yuan_btn a{width:100%;height:100%;color:#fff;display:inline-block;cursor: pointer;}
.cd-popup6 .yunull{padding:30px 0px 30px 0px;}
.cd-popup6 .tishi_btn{margin:0 auto;background-color: #489ae3;color:#fff;font-size:18px;text-align:center;line-height:48px;width:300px;height:48px;    margin-top: 30px;border-radius: 5px;cursor: pointer;}
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
.close{position: absolute;right:5px;}
.cd-popup-container1, .cd-popup-container2, .cd-popup-container4, .cd-popup-container5, .cd-popup-containerh{ width:875px;}
.cd-popup-container2{width:1000px;}
.commit-2 span.spjine {
    text-align: right;
    width: 195px;
}
</style>
</head>
<body>
<% try{ %>
<!-- 新手提示框 -->

<div class="cd-popup6">
<div class="cd-popup-container1">
<!--协议列表-->
	<div class="comnei ">
        
        <p class="commit-1"><span>无法出借头笔赚</span></p>
        <div class="yunull">
            <!-- <div class="tishi_text">抱歉，您已有过出借行为，不能再出借头笔赚看看其他优质的出借计划吧。</div> -->
            <div class="tishi_text">抱歉，您已出借过头笔赚，请查看其它计划。</div>
            <div class="tishi_btn">我知道了</div>
           
        </div>
    </div>

       
        
     </div>
</div>

<!--出借弹窗-->

<div class="cd-popup2">
<div class="cd-popup-container2">
<form action="" name="FormChujie" id="FormChujie">
<input type="hidden" name="biddingOID" value='<%=request.getParameter("OID") %>' >

<!--确认出借-->
   <div class="comnei comneichujie">
       <a class="close cd-popup-close"></a>
       <p class="commit-1 forgetpwdtop">确认出借</p>
       <div class="yunull yunullchujie">
          	<div class="lend_title">
                <div class=""></div>
            </div>
            
            <div class="contentchujie lend_content">
            	<div class="leon_zhanbox">
                	<div class="boxone">
                    	<span class="sp01 org"><em id="tk-yjnh">15</em>%</span>
                        <span class="sp02">预期预期年化</span>
                    </div>
                    
                    <div class="boxone">
                    	<span class="sp01"><em id="tk-cjqx">15</em>天</span>
                        <span class="sp02">出借期限</span>
                    </div>
                    
                    <div class="boxone">
                    	<span class="sp01"><em id="tk-cjjine"></em><input type="hidden" name="amount" id="amount" value="">元</span>
                        <span class="sp02">出借金额</span>
                    </div>
                    
                    <div class="boxone">
                    	<span class="sp01"><em id="tk-yqsynew"></em>元</span>
                        <span class="sp02">预期收益</span>
                    </div>
                    <!-- <input type="hidden" id="tqID" name="tqID" value="" /> -->
                </div>
                <div class="channel_disbursement hide"><h1>支付渠道</h1><div class="cg_wrap"><div>华兴银行存管</div><div class="fy_channel">富友账户存管</div></div><div class="chanel_icon"><div class="channel_tishi">该出借产品支持富友支付渠道</div></div><div class="clear"></div></div>
                <div class="choose_btn"><h1>特权选择</h1><div class="tq_btn">当前无可用加息券</div><div style="clear:both;"></div></div>
                <div class="tq_box">
                <div class="tq_box_top"><h1>可用加息券</h1><div>共<span class="oNumber_text">9</span>种可用加息券</div><div style="clear:both;"></div></div>
                <div class="selectbox">
                    
                    <!-- <p><span>特权选择</span><span class="span-isuse"><input type="button" id="isuse" class="isuse"  value="取消特权选择"><label for="isuse" >取消选择</label></span> </p> -->
                    <div class="boxselct">
                         
                        <div style="clear:both;"></div>
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
        <p class="commit-2"><span>市场有风险，出借需谨慎</span><button id="class='colseok'" name="class='colseok'" type="submit" value="确认" class="zcbtn_input confirmbtn">确认</button><span class="spjine">需支付￥<em id="paymoney"></em>元</span></p>
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
    	
    </div>
	
    <!--项目详情开始-->
    <div class="con detailscon">
    	
    	<div class="detailefts">
        	<div class="topdetail">
            	<div class="imgpro hide"><img src="<%=pathUrl %>/images/newtyb.png" /></div>
            	<div class="xmtits">
            	
            	<%if(session.getAttribute("userMobile") != null){ %>
				<% }else{ %>
				 <a class="text-xstext" href="agreement_z.jsp?contractType=2" target="_blank">《头笔赚服务协议》</a>
				<%}%>
				</div>
                <div class="huiback">
                	<div class="qxbox qx01 qxnew"><span class="sp01">预期年化收益</span><span class="sp02 oreg"><em class="nhll"></em>%+<em class="add_new"><em class="add_sign">%</em></em></span><em id="data-yjnh" class="hide"></em></div>
                    
                    <div class="qxbox qx01 qx02"><span class="sp01">出借期限</span><span class="sp02"><em id="data-cjqx"><%-- <%=request.getAttribute("cjqx") %> --%>15</em>天</span></div>
                  <%--   <div class="qxbox qx01"><span class="sp01">项目金额</span><span class="sp02 oreg"><em id="data-xmje"><%=request.getAttribute("xmze") %></em>元</span></div> --%>
                    <div class="qxbox qx01"><div id="box" class="box"></div><div class="jindu hide"></div><!-- <div class="prg-cont rad-prg" id="indicatorContainer5"></div> --></div>
                </div>
       			<div class="botbox">
                	<div class="botfindbox">
                	<div class="botfindleft"><span class="tit"><span style="width:100px;display: inline-block;">转让价格</span><span class="conter"><em id="data-xmje"></em>元</span></div>
                	<div class="botfindleft"><span class="tit"><span style="width:100px;display: inline-block;">保障方式</span><span class="conter">保理公司回购代偿</span></div>
                	<div style="clear:both;"></div>
                	</div>
                	<div class="botfindbox">
                	<div class="botfindleft"><span class="tit"><span style="width:100px;display: inline-block;">回款方式</span><span class="conter hkfsCon"></span></div>
                	<div class="botfindleft" style="width: 330px;"><span class="tit"><span style="width:100px;display: inline-block;">出借范围</span><span class="conter">出借金额为100元~10000元之间</span></span><br />
                	                         <span style="color:#999;font-size:10px;">*当剩余可投不足10000元时，可用「余额全投」出借剩余金额</span>
                	                      </div>
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
				<%-- <em><%=request.getAttribute("availablemoney") %></em>元 --%>
				<% }else{ %>
				 登录后可查看余额
				<%}%>
               
                </label>
                <%if(session.getAttribute("userMobile") != null){%>
                <a class="" href="Recharge_new.jsp">充值</a>
				<%}else{ %>
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
                	预期收益：可获得<span id="calc-zongenew">0.00</span>(元)
                </div>
                <div class="error_box"><div class="hide" id="hhproductStatus"></div></div>
                <input id="new_judge" type="hidden" value="">
                <input id="new_judge2" type="hidden" value="">
                
	                <%if(session.getAttribute("userMobile") != null){%> 
					<div class="jineBtn"></div>	
					<% }else{ %>
					<a class="caozuo" href="login.jsp">立即登录</a>
					<%}%>
               
                
                
                <!--<a class="caozuo" href="login.jsp">立即登录</a>-->
                <!-- <a class="caozuo tzjs cd-popup-trigger2" href="#" >立即出借</a> -->
               <div class="bottext">
               <%if(session.getAttribute("userMobile") != null){ %>
				<input type="checkbox" checked="checked"><a class="bottext_hou">我已阅读并同意<a class="blurac" href="agreement_z.jsp?contractType=2" target="_blank" >《头笔赚服务协议》</a>
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
            	<div class="tbz_tabtitcons tabtitcons">
                	<div class="active">项目介绍</div>
                    <div>出借记录</div>
                    <div>安全保障</div>
                    <div>常见问题</div>
                </div>
                <div class="neircon">
                	<div class="optabg">
                	<%if(session.getAttribute("userMobile") != null){ %>
                    	<div class="boxxqjs">
                        	<div class="title-p"><span></span>转让说明</div>
                            <p class="p-content">债权转让，是指出借人因个人原因急需资金，在砖头网出借的借款标的的债权转让给其他用户。债权转让能提高出借人资金的流动性，当用户需要流动资金时，可以通过出售你名下拥有的、符合砖头网相应规则及条件的债权给其他出借人，从而完成债权转让，获得流动资金。

</p>

							<div class="zhaiZ_js hide">
							<div class="jindu_text"><span>发起债权转让</span><span style="margin-left: 205px;">等待债转满标</span><span style="margin-left: 200px;">债权转让成功</span><span style="margin-left:200px">资金全部到账</span></div>
							<div class="jindu_z"></div>
							</div>
							
                            

                        </div>
                        <div class="boxxqjs">
                        	<div class="title-p"><span></span>转让标的信息</div>
                           	<div class="messagebox">
                            	<dl>
                                	<dd>转让人：<span></span></dd>
                                    <dd>原标金额：<span></span></dd>
                                    <dd>待收本金：<span></span></dd> 
                                </dl>
                                <dl>
                                	<dd>原标借款利率：<span></span></dd>
                                    <dd>当前待收利息：<span></span></dd>
                                    
                                </dl>
                                
                            </div>
                            <input type='hidden' value='' />
                           
                           <div class="yuan_btn"><a  href='Newtzdetails.jsp?OID=' target="_blank">查看原标信息</a></div>              
                           
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
                        <div class="hide_liu Newtz_liu"></div>
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
采用阿里云云服务ECS技术支持，骨干机房搭配独享带宽、云盾DDoS高防IP 系统等一系列专业技术手段来保障网站登录、支付相关数据安全性。<br>
严格依照国家相关法律法规设立平台，与盈科律师事务所达成战略合作关系，在履行《网络借贷信息中介机构业务活动管理暂行办法》相关义务和规定的同时，平台受合同法、民法通则等法律法规以及最高人民法院相关司法解释规范。</div></div>
                          
                    </div>
                    </div>
                    <web:ContentLeaf leafName="SingleArticle" OID="da8187ccc057fa8b01ca8782dc4dd5ff" name="cjwt"></web:ContentLeaf>
                    <div class="optabg hide">
                    <div class="boxxqjs safe_baozhang">
                        	<div class="title-p title_hei">1.什么是头笔赚？</a></div>
                            <div class="safe_content"><div> 
 头笔赚是砖头网研发的一款适合新手出借的出借计划，该计划周期短、利息高，是由第三方保理公司无偿担保的安全真实的散标标的，通过该计划用户可以切身感受到真实可靠的出借计划收益效果。
</div></div>
                          
                    </div>
                    	<div class="boxxqjs safe_baozhang">
                        	<div class="title-p title_hei">2.是否支持随时债权转让？ </div>
                            <div class="safe_content"><div> 头笔赚（新手标）不支持随时债权转让，随时债权转让是需满足3个月以上的投标期限的标的方可实现。</div></div>
                          
                    </div>
                    <div class="boxxqjs safe_baozhang">
                        	<div class="title-p title_hei">3.头笔赚是否安全？</a></div>
                            <div class="safe_content"><div> 
头笔赚（新手标）以借款人的真实借款需求为宗旨，对每笔借款进行严格筛选。确保您出借的每笔债权真实有效。</div></div>
                          
                    </div>
                    <div class="boxxqjs safe_baozhang">
                        	<div class="title-p title_hei">4.头笔赚投资金额是否有上限限制？单笔单次首投金额最低是多少？</div>
                            <div class="safe_content"><div> 
  头笔赚（新手标）单笔出借金额不得高于10000元；单次单笔最低出借金额100元，剩余可承接债权不足100元时，需出借所有剩余额度。</div></div>
                          
                    </div>
                    <div class="boxxqjs safe_baozhang">
                        	<div class="title-p title_hei">5.什么是年化利率？</div>
                            <div class="safe_content"><div> 
年化利率是通过产品的固有收益率折现到全年的利率。</div></div>
                          
                    </div>
                    <div class="boxxqjs safe_baozhang">
                        	<div class="title-p title_hei">6.按照期限、年化利率如何计算我的收益？</div>
                            <div class="safe_content"><div> 
 举例说明：出借10000元，期限15天，年化利率15%，预计到期可得收益金额：<br>
10000/365*15*0.15=61.6元；同时您可使用网站上的计算器功能进行准确计算。

</div></div>
                          
                    </div>
                    <div class="boxxqjs safe_baozhang">
                        	<div class="title-p title_hei">7.我什么时候会收到利息？15天出借期限结束后，我该如何拿回本金？</div>
                            <div class="safe_content"><div> 
满标后开始计算利息，15天出借期满，出借本金及利息一并返还账户。</div></div>
                          
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
<script>
$(function(){
    //jquery.validate
	$("#FormChujie").validate({
		submitHandler: function(form) {
			//验证通过后 的js代码写在这里
			//alert(1);
			
				var values = $("a[class='active']").find("input").val();
			$("#OID").attr("value",values);
			console.log(values);
			//事件只能点击一次
				$(".confirmbtn").click(function(){
					$(this).attr("disabled","disabled");
				});
			
			form.submit();
			return validataForm("confirmbtn");
			
		}
	})
	//充值控制数值JS
    funcData.eChange.COM();
})

//   留白
$(function(){
    if($(".tr02").length<1){
        $(".Newtz_liu").css("display","block");
    }
    
    var hhproductStatus=$("#hhproductStatus").text();
    if(Number(hhproductStatus)==10){		
		$(".caozuo.tzjs").text("立即出借");
		$(".caozuo.tzjs").css({"background":"#ababab","cursor":"auto"});
		$(".caozuo.tzjs").removeAttr("href");
		$(".caozuo.tzjs").removeClass("cd-popup-trigger2");	
	}else{
	    	 $(".caozuo.tzjs").text("立即出借"); 
	    	 $(".yuyue_tishi").addClass("hide");
	    
	}
    
    var Request = new Object();
	Request = GetRequest2();
	var OID=Request["OID"];
    
    $.ajax({
        type: "get",
        url: "/portal/detial/detialTBZ/?OID="+OID,
        dataType: "json",
        success: function(result){
        	var result=result.dataValue;
        	$(".xmtits").text(result.xmmc);
        	$(".lend_title div").text(result.xmmc);
        	$(".oreg .nhll").text(result.nhll);
        	$(".oreg .add_new").html(result.fjll+'<em class="add_sign">%</em>');
        	$("#data-yjnh").text(result.nhlltotal);
        	$(".jindu").text(result.tzjd);
        	$("#data-xmje").text(result.zbsl);
        	$(".hkfsCon").text(result.hkfs);
        	$("#sykt em").text((result.sykt).toFixed(2));
        	if(result.availablemoney!=null){
        		$("#zhyue em").text((result.availablemoney).toFixed(2));
        	}
        	$("#new_judge").val(result.isnew);
        	var strBtn;
        	if(result.tzjd==100){
        		strBtn='<a class="yimanbiao" >已满标</a>';
        	}else{
        		if(result.productStatus==10){
        			strBtn='<a id="new_tou" class="tzjs yimanbiao">立即出借</a>';	
            	}else{
            		if(result.isnew==0){
            			strBtn='<a id="new_tou" class="caozuo tzjs cd-popup-trigger2">立即出借</a>';	
                	}else{
                		strBtn='<a id="new_tou" class="yimanbiao tishi_new">立即出借</a>';	
                	}
            	}
        	}
        	$(".jineBtn").append(strBtn);
        	$("body").append('<script src="\/js\/chujieTK_new_xian.js"><\/script>');
        	funJindu();
        },
		error:function(result){    	
    	}
    });
    
    $.ajax({
        type: "get",
        url: "/portal/detial/detialmark/?OID="+OID,
        dataType: "json",
        success: function(result){
        	var result=result.dataValue;
        	$(".messagebox dd").eq(0).find("span").text(result.zrr);
        	$(".messagebox dd").eq(1).find("span").text((result.ybze).toFixed(2)+"元");
        	$(".messagebox dd").eq(2).find("span").text(result.dsbj+"元");
        	$(".messagebox dd").eq(3).find("span").text((result.ybjkll).toFixed(2)+"%");
        	$(".messagebox dd").eq(4).find("span").text((result.dqdslx).toFixed(2)+"元");
        	$(".yuan_btn a").attr("href","/portal/detial/Newtzdetails/?OID="+result.OID);
        	
        	
        },
		error:function(result){    	
    	}
    });
    
    $.ajax({
        type: "get",
        url: "/portal/detial/lendRecord/1?OID="+OID,
        dataType: "json",
        success: function(result){
        	var result=result.dataValue;
        	for(var i=0;i<result.data.length;i++){
        		var str='<tr class="tr02"><td>'+(i+1)+'</td><td>'+result.data[i].mobile+'</td><td>'+(result.data[i].money).toFixed(2)+'元</td><td>'+result.data[i].sj+'</td></tr>';
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
            		        url: "/portal/detial/lendRecord/"+num+"?OID="+OID,
            		        dataType: "json",
            		        success: function(result){
            		        	var result=result.dataValue;
            		        	$(".tabxq tbody").find("tr").remove();
            		        	for(var i=0;i<result.data.length;i++){
            		        		var str='<tr class="tr02"><td>'+(((num-1)*10+(i+1)))+'</td><td>'+result.data[i].mobile+'</td><td>'+(result.data[i].money).toFixed(2)+'元</td><td>'+result.data[i].sj+'</td></tr>';
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
//下面是一些常用的验证规则扩展

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

/* $(function(){
	iniTab();
}); */

</script>

</html>
