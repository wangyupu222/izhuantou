<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/include/url.jsp" %>
<!doctype html>
<html><head>
<meta charset="utf-8">
<title>砖头网-上砖头儿，有赚头儿！</title>
<link rel="stylesheet" type="text/css" href="/css/style-common.css">
<link rel="stylesheet" type="text/css" href="/css/comsty.css">
<link rel="stylesheet" type="text/css" href="/css/confirm_lend.css">
<link rel="stylesheet" type="text/css" href="/css/pedetails.css">
<link rel="stylesheet" href="/css/responsiveslides.css">
<Link rel="shortcut icon" href="<%=pathUrl %>/images/zhuan.ico" />
<script type="text/javascript" src="/js/jquery-1.10.1.min.js"></script>
<script src="/js/com.js"></script>
<script src="/js/comzz.js"></script>
<script src="/js/base.js"></script>
<%-- <script src="/js/comjd.js"></script> --%>
<script src="/js/resubmit.js"></script>
<script src="/js/paging.js"></script>
<!--首页焦点图-->
<script type="text/javascript" src="/js/koala.min.1.5.js"></script>
<script src="/js/radialIndicator.js"></script>
<style type="text/css">
.cd-popup-container1{ width:700px;}/*充值框大小*/
.cd-popup-container2{ width:860px; margin: 40px auto;}/*出借框大小*/
.hide_liu{  width: 100%;  height: 500px;  text-align: center;  background:#fff url("<%=pathUrl %>/images/xiangqing_liu.png")no-repeat center;  display: none;  }
.cd-popup-container1, .cd-popup-container2, .cd-popup-container4, .cd-popup-container5, .cd-popup-containerh{ width:875px;}
.botfindbox{line-height:25px;}
.messagebox dl {
    margin-left: 30%;
}
.yuan_btn{width:250px;hieght:42px;background-color:#55b0fd;margin:0 auto;text-align:center;line-height:42px;border-radius:4px;margin-top: 50px;}
.yuan_btn a{width:100%;height:100%;color:#fff;display:inline-block;cursor: pointer;}
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
    	<a>当前位置</a><span>:</span><a>我要出借</a>
        <span>&gt;</span><a>转转投</a><span>&gt;</span><a>标的详情</a>
    </div>
	
    <!--项目详情开始-->
    <div class="con detailscon">
    	
    	<div class="detailefts">
        	<div class="topdetail">
            	<div class="imgpro"><img src="<%=pathUrl %>/images/zhuanbiao.png" /></div>
            	<div class="xmtits">
            	
            	<%if(session.getAttribute("userMobile") != null){ %>
				<% }else{ %>
				 <a class="text-xstext" href="agreement_z.jsp?contractType=5" target="_blank">《转转投服务协议》</a>
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
                	<div class="botfindleft"><span class="tit"><span style="width:100px;display: inline-block;">转让价格</span><span class="conter hkfsCon"><em></em>元</span></div>
                	<%-- <div class="botfindleft"><span class="tit"><span style="width:100px;display: inline-block;">标的金额</span><span class="conter"><em id="data-xmje"><%=request.getAttribute("xmze") %></em>元</span></div> --%>
                	<div class="botfindleft"><span class="tit"><span style="width:100px;display: inline-block;">保障方式</span><span class="conter">保理公司回购代偿</span></div>
                	<div style="clear:both;"></div>
                	</div>
                	<div class="botfindbox">
                	<div class="botfindleft"><span class="tit"><span style="width:100px;display: inline-block;">回款方式</span><span class="conter">一次性还本付息</span></div>
                	<div class="botfindleft"><span class="tit"><span style="width:100px;display: inline-block;">最低出借额</span><span class="conter">100元</span></span><br />
                	                         <span style="color:#999;font-size:9px;">* 剩余可承接债权不足100元时，需出借所有剩余额度</span>
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
               		<input class="jine" id="jine" value="" type="text" placeholder="100元起，且以100元的倍数递增" txtype='FloatZ1000W' onKeyUp="this.value=this.value.replace(/[^\.\d]/g,'');this.value=this.value.replace('.','');" />&nbsp;<span id="yueall">余额全投</span>
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
				<input type="checkbox" checked="checked"><a class="bottext_hou">我已阅读并同意<a class="blurac" href="agreement_z.jsp?contractType=5" target="_blank" >《转转投服务协议》</a><a class="text-xstext text-xstext_new" href="agreement_z.jsp?contractType=7" target="_blank">《债权转让协议》</a>
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
            	<div class="zzt2_tabtitcons tabtitcons">
                	<div class="active">标的介绍</div>
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
                                	<dd>原标年化：<span></span></dd>
                                    <dd>当前代收利息：<span></span></dd> 
                                </dl>
                                
                            </div>
                            <input type='hidden' value='' />
                            <div class="yuan_btn"><a  href='Diantzdetails.jsp?OID=' target="_blank">查看原标信息</a></div>
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
采用阿里云云服务ECS技术支持，骨干机房搭配独享带宽、云盾DDoS高防IP系统等一系列专业技术手段来保障网站登录、支付相关数据安全性。<br>
严格依照国家相关法律法规设立平台，与盈科律师事务所达成战略合作关系，在履行《网络借贷信息中介机构业务活动管理暂行办法》相关义务和规定的同时，平台受合同法、民法通则等法律法规以及最高人民法院相关司法解释规范。</div></div>
                          
                    </div>
                    </div>
                    <web:ContentLeaf leafName="SingleArticle" OID="da8187ccc057fa8b01ca8782dc4dd5ff" name="cjwt"></web:ContentLeaf>
                    <div class="optabg hide">
                    <div class="boxxqjs safe_baozhang">
                        	<div class="title-p title_hei">1.什么是转转投？</a></div>
                            <div class="safe_content"><div> 
   转转投是砖头网针对债权转让标的，进行专业再审后发布至平台的一款债权类出借计划，该计划信息披露完整，真实透明，同时具备多种出借期限可供选择，通过该计划出借人可以清晰的了解到原始标的信息及目前标的还款进度。
</div></div>
                          
                    </div>
                    	<div class="boxxqjs safe_baozhang">
                        	<div class="title-p title_hei">2.是否收取手续费？ 收取费用是多少？</div>
                            <div class="safe_content"><div> 是的，需要收取手续费。如债权转让，需支付债权转让手续费，手续费为转让本金千分之二。</div></div>
                          
                    </div>
                    <div class="boxxqjs safe_baozhang">
                        	<div class="title-p title_hei">3.债权转让的金额是如何得出的？</a></div>
                            <div class="safe_content"><div> 
   （价格等于债权转让日的剩余未还本金金额与当日该笔债权的应计利息之和。应计利息采用“实际天数法”规则来计算，即：应计利息=（转让日-上期还款日）*当期利息金额/（本期还款日-上期还款日）</div></div>
                          
                    </div>
                    <div class="boxxqjs safe_baozhang">
                        	<div class="title-p title_hei">4. 什么是双重安全保障？</div>
                            <div class="safe_content"><div> 
  如借款项目出现逾期，该计划自动发挥其作用：<br>
第一重：根据约定向出借人支付逾期借款本金和利息并受让对应逾期债权。<br>
第二重：平台的质保服务专款账户保障出借人利益。</div></div>
                          
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
			//事件只能点击一次
				var values = $("a[class='active']").find("input").val();
			$("#OID").attr("value",values);
			console.log(values);
				$(".confirmbtn").click(function(){
					$(this).attr("disabled","disabled");
				});
			form.submit();
			return validataForm("confirmbtn");
		}
	})
	
})

//   留白
$(function(){
    if($(".tr02").length<1){
    	$(".Zhuantz_liu").css("display","block");
    }
    var Request = new Object();
	Request = GetRequest2();
	var OID=Request["OID"];
    
    $.ajax({
        type: "get",
        url: "/portal/detial/detailZZT/?OID="+OID,
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
        	$(".hkfsCon").text(result.zbsl);
        	$("#sykt em").text((result.ktje).toFixed(2));
        	if(result.availablemoney!=null){
        		$("#zhyue em").text((result.availablemoney).toFixed(2));
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
        	
        	$(".messagebox dd").eq(0).find("span").text(result.zrName);
        	$(".messagebox dd").eq(1).find("span").text((result.yuanMoney).toFixed(2)+"元");
        	$(".messagebox dd").eq(2).find("span").text(result.dsbj+"元");
        	$(".messagebox dd").eq(3).find("span").text((result.ybnh).toFixed(2)+"%");
        	$(".messagebox dd").eq(4).find("span").text((result.dqds_interest).toFixed(2)+"元");
        	$(".yuan_btn a").attr("href","/portal/detial/Diantzdetails?OID="+result.biOID);
        },
		error:function(result){    	
    	}
    });
    
    $.ajax({
        type: "get",
        url: "/portal/detial/zztlendRecord/1?OID="+OID,
        dataType: "json",
        success: function(result){
        	console.log(result);
        	for(var i=0;i<result.data.length;i++){
        		var str='<tr class="tr02"><td>'+(i+1)+'</td><td>'+result.data[i].name+'</td><td>'+result.data[i].money+'元</td><td>'+result.data[i].sj+'</td></tr>';
        		$(".tabxq tbody").append(str);
        	}
        	if(result.totalNumber<1){
    	        $(".Diantz_liu").css("display","block");
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
            		        url: "/portal/detial/zztlendRecord/"+num+"?OID="+OID,
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
</html>
