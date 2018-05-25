<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/include/url.jsp" %>
<!doctype html>
<html><head>
<meta charset="utf-8">
<title>砖头网-体验标</title>
<link rel="stylesheet" type="text/css" href="/css/style-common.css">
<link rel="stylesheet" type="text/css" href="/css/comsty.css">
<link rel="stylesheet" type="text/css" href="/css/pedetails.css">
<link rel="stylesheet" href="/css/responsiveslides.css">
<Link rel="shortcut icon" href="<%=pathUrl %>/images/zhuan.ico" />
<script type="text/javascript" src="/js/jquery-1.10.1.min.js"></script>
<script type="text/javascript">
$(function () {
	// Slideshow 
	var fyzh=$("#fyzh").val();
	if(fyzh==0){
		//alert("aa");
		$('#tiyan_btn_new').on('click', function(event){
            event.preventDefault();
            $('.cd-popup2').addClass('hide');
            //$(".dialog-addquxiao").hide()
        });
		$("#tiyan_btn_new").click(function(){
			window.location.href="addbankcard.jsp?type=2";
		})	
	}
	
});

</script>


<script src="/js/com.js"></script>
<script src="/js/chujieTKtyj_xian.js"></script> 
<%-- <script src="/js/chujieTK.js"></script> --%>
<!--<script src="/js/comjd.js"></script>-->
<!--首页焦点图-->
<script type="text/javascript" src="/js/koala.min.1.5.js"></script>
<script type="text/javascript" src="/js/responsiveslides.min.js"></script>
<script type="text/javascript">
$(function () {
	// Slideshow 
	$("#slider").responsiveSlides({
		auto: true,
		pager: false,
		nav: true,
		speed: 500,
		timeout:4000,
		pager: true, 
		pauseControls: true,
		namespace: "callbacks"
	});
});

</script>
<style>
/* .czzc {
    overflow: hidden;
    width: 96%;
    text-align: center;
    margin-top:25px;
} */
/* .czzc .boxinput {

    color: #999;
    font-size: 24px;
    line-height:55px;
} */
.caozuo{
margin-top:20px;
}
.register_btn{
width:100%;
text-align:center;
font-size:14px;

}
.ljtzbox {
    position: relative;
}
.register_btn a{
text-decoration:underline;
color:#999;
}
.keyong_money{
font-size:36px;
color:#ff6e0a;
}
.tzjs{
margin-top:20px;
}
.tiyan_img{
    position: absolute;
    top: -2px;
    left: -1px;
    /* display:none; */
}
.cd-popup6 .cd-popup-container1 {
    width: 470px;margin:185px auto;
}
.cd-popup6 .commit-1 span{
width:100%;text-align: center;color:#666;font-size:18px;padding-left:0px;
}
.cd-popup6 .tishi_text{
font-size:16px;color:#666;margin:0 auto;width:70%;text-align: left;
}
.yimanbiao {
    background-color: #ababab;  
}
.yimanbiao:hover{
 background-color: #ababab; 
}
.cd-popup6 .tishi_btn{margin:0 auto;background-color: #489ae3;color:#fff;font-size:18px;text-align:center;line-height:48px;width:300px;height:48px;    margin-top: 30px;border-radius: 5px;cursor: pointer;}
.qxbox .sp02 .add_new{font-size:18px;color:#ff7800;}
.qxbox .sp02 .add_new .add_sign{color:#666;font-size:14px;}
.detairights {height: 331px;}
.botbox {min-height: 100px;}
.sylytext{margin-top:25px;}
.login_text{text-align:center;margin-top:40px;font-size:24px;color:#999;margin-bottom:20px;}
</style>
</head>
<body>
<% try{ %>
<!-- 体验提示 -->
<div class="cd-popup6" style="display:none;">
<div class="cd-popup-container1">
<!--协议列表-->
	<div class="comnei ">
        
        <p class="commit-1"><span>无法出借体验标</span></p>
        <div class="yunull">
            <div class="tishi_text">抱歉，您已有过出借行为，不能再出借体验标，看看其他优质的出借计划吧。</div>
            <div class="tishi_btn">我知道了</div>
           
        </div>
    </div>

       
        
     </div>
</div>

<!--出借弹窗-->

<div class="cd-popup2 cd-popup_new">
<div class="cd-popup-container2">
<form handleClassName="pageTyProDetailsFormChujiesave" name="FormChujie"  >
<input type="hidden" name="biddingOID" value='<%=request.getParameter("OID") %>' >
   <div class="comnei comneichujie">
       <a class="close cd-popup-close"></a>
       <p class="commit-1"><span>确认出借</span></p>
       <div class="yunull yunullchujie">
          	<div class="boxtitle">
            	<img src="<%=pathUrl %>/images/chujie.png" width="65" height="53">
                <div class="titlebt">体验标</div>
                
            </div>
            
            <div class="contentchujie">
            	<div class="zhanbox">
                	<div class="boxone">
                    	<span class="sp01 org"><em id="tk-yjnh">6.50</em>%</span>
                        <span class="sp02">预期年化</span>
                    </div>
                    
                    <div class="boxone">
                    	<span class="sp01"><em id="tk-cjqx">1</em>天</span>
                        <span class="sp02">出借期限</span>
                    </div>
                    
                    <div class="boxone">
                    	<!-- <span class="sp01"><em id="tk-cjjine"></em>元</span> -->
                    	<span class="sp01" id="new_money"><em>18888</em>元</span>
                    	<input type="hidden" name="amount" id="amount" value="">
                        <span class="sp02">出借金额</span>
                    </div>
                    
                    <div class="boxone">
                    	<!-- <span class="sp01"><em id="tk-yqsy"></em>元</span> -->
                    	<span class="sp01"><em>3.36</em>元</span>
                        <span class="sp02">预期收益</span>
                    </div>
                </div>
                
                <div class="selectbox hide">
                    <p><span>特权选择</span><span class="span-isuse"><input type="button" id="isuse" class="isuse"  value="取消特权选择"><!-- <label for="isuse" >取消选择</label> --></span></p>
                    <div class="boxselct hide">
                        <a>不使用特权</a>
                        <a>1000元红包</a>
                        <a>500元红包</a>
                        <a>100元红包</a>
                        <a>50元红包</a>
                        <a>10元红包</a>
                        <a class="active">1%加息券</a>
                        <a>2%加息券</a>
                        <a>5%加息券</a>
                        <a>10%加息券</a>
                    </div>
                    <div class="hide">
                    <p>理财金</p>
                    <div class="kyje"><web:WidgetText name="FormChujie" property="lcjmoney" customProperty="" /><span>可用金额为1000元</span></div>
                	</div>
                	<label class="labox" style="display:none"><web:WidgetCheckbox  name="FormChujie" property="isAgree" value="" customProperty="checked='checked' required data-msg-required='必选'"/>&nbsp;&nbsp;<a href="#">我同意《出借咨询及管理服务协议》</a></label>
                </div>
                
            </div>
            
            
           
        </div>
        <%-- <p class="commit-2"><span class="hide">市场有风险，出借需谨慎</span><web:WidgetButton value="确认" customProperty="class='zcbtn_input' " property="class='colseok'"  /><span class="spjine hide">需支付￥<em id="paymoney"></em>元</span></p> --%>
   <p class="commit-2"><web:WidgetButton value="确认" customProperty="class='zcbtn_input confirmbtn' " property="class='colseok'"  /></p>
   </div>    
       <!--确认出借结束-->
</form>   
       <!--余额不足-->
   <div class="comnei comneichujie hide">
       <a class="close cd-popup-close"></a>
       <p class="commit-1"><span>余额不足</span></p>
       <p class="yebz">抱歉！您的余额不足，<a href="#">立即充值</a> </p>
        <p class="commit-2"><span>理财有风险，投资需谨慎</span><a class="colseok" href="#">确认</a></p>
   </div>    
       <!--余额不足结束-->
        
     </div>
</div>
<!--出借弹窗结束-->


<div class="indexz">
<!--common header-->
<%@ include file="header.jsp" %>
<!--common end-->

<!--我要出借-->
<div class="main conheight">
	<div class="con smallnav">
    	
    </div>
	<input id="fyzh" type="hidden" value='<%=request.getAttribute("fyzh")%>' />
    <!--项目详情开始-->
    <div class="con detailscon">
    	
    	<div class="detailefts">
        	<div class="topdetail">
            	<div class="imgpro"><img src="<%=pathUrl %>/images/newtyb2.png" /></div>
            	<%-- <div class="xmtits"><%=request.getAttribute("xmmc")%> </div> --%>
            	<div class="xmtits" style="padding-left:127px;">新手福利-体验标 </div>
                <div class="huiback">
                	<div class="qxbox qx01"><span class="sp01">预期年化收益率</span><span class="sp02 oreg"><em id="data-yjnh">6.50</em>%</span><em class="hide"><%=request.getAttribute("nhlltotal") %></em></div>
                    <%-- <div class="qxbox qx01"><span class="sp01">还款方式</span><span class="sp02"><%=request.getAttribute("hkfs") %></span></div> --%>
                    
                    <div class="qxbox qx01"><span class="sp01">出借期限</span><span class="sp02 oreg"><em id="data-cjqx">1</em>天</span></div>
                    <div class="qxbox qx01"><span class="sp01">已体验人数</span><span class="sp02 oreg"><em id="tyNum"></em>人</span></div>
                </div>
       			<div class="botbox">
                	<div class="botfindbox">
                	<div class="botfindleft"><span class="tit"><span style="width:100px;display: inline-block;">回款方式</span><span class="conter">一次性还本付息</span></div>
                	<div style="clear:both;"></div>
                	</div>
                	<div class="botfindbox">
                	<div class="botfindleft" style="width: 366px;"><span class="tit"><span style="display: inline-block;color: #;color: #ff7800;">温馨提示：已出借过其他产品的用户，将不可出借体验标。</span></div>
                	<div style="clear:both;"></div>
                	</div>
                   
                </div>  
                 
            </div>
            <!--右侧小窗-->
        </div>
        <input type="hidden" value='<%="0".equals(request.getAttribute("runTure"))%>' />

        <div class="detairights">
        	<div class="ljtzbox">
            	<div class="sylytext">体验金余额：
            	<%if(session.getAttribute("userMobile") != null){ %>
				<span id="sykt"><em>0.00</em>元</span>
               <div class="czzc">
               
               </div>
               <div class="touzinput">
               		<input class="jine" id="jine" value="" type="text" placeholder="请输入出借金额…" txtype='FloatZ1000W' onKeyUp="this.value=this.value.replace(/[^\.\d]/g,'');this.value=this.value.replace('','');" />&nbsp;<span id="yueall">余额全投</span>
               </div>
                <div class="czzc01">
                	预期收益：可获得<span id="calc-zongenew">0.00</span>(元)
                </div>
                <% }else{ %>
				 <p class="login_text">登录后方可查看</p>
				<%}%></div>
                <div class="error_box"><div class="hide" id="hhproductStatus"></div></div>
				<%if(session.getAttribute("userMobile") != null){ %>
                 <div class="jineBtn"></div>
				<% }else{ %>
				<a class="caozuo" href="/portal/user/login">立即登录</a>
				<div class="register_btn"><a  href="register.jsp">还没有账号？立即注册</a></div>
				<%}%>
            </div>
          
        </div>
    </div>
    <!--项目详情结束-->
    
    <div class="con qhdetailxmcons">
    	 <div class="qhdetail">
            	<div class="tabtitcons">
                	<div class="active">体验标介绍</div>
                    <!-- <div>出借记录</div> -->
                </div>
                <div class="neircon">
                	<div class="optabg">
                    	<div class="newxsbiao">
                        	<div class="imgfl flzc"><img src="<%=pathUrl %>/images/tznews01.jpg" width="198" height="201"></div>
                            <dl class="flzc">
                            	<dt>什么是体验标</dt>
                                <dd>体验标是砖头网为第一次接触平台的出借用户量身定做的一款体验项目，砖头网打造这个产品的目的，就是为了给刚刚注册我们平台的用户提供快速了解平台业务的通道，同时让用户体验到获得资金收益的快感。</dd>
                 
                            </dl>
                        </div>
                        
                        <div class="newxsbiao">
                        	<dl class="flzc">
                            	<dt>什么时候可以获取收益</dt>
                                <dd>您在砖头网平台出借体验标之后，根据您出借的体验标项目的出借期限和实际计息日期来计算您的收益到账日期，通常标的时间较短，会让您快速体验出借与获得收益的快感。</dd>
                            </dl>
                        	<div class="imgfl flzc"><img src="<%=pathUrl %>/images/tznews02.jpg" width="198" height="201"></div>	
                        </div>
                        
                        <div class="newxsbiao">
                        	<div class="imgfl flzc"><img src="<%=pathUrl %>/images/tznews03.jpg" width="198" height="201"></div>
                            <dl class="flzc">
                            	<dt>如何获取体验金</dt>
                                <dd>用户在注册砖头网平台之后，即可获得砖头网提供的18888元新手体验金。</dd>
                            </dl>
                        </div>
                        
                         <div class="newxsbiao">
                        	<dl class="flzc">
                            	<dt>收益如何提现</dt>
                                <dd>如果您满足以下条件，即可申请提现：</dd>
                                <dd>1、完成认证并开通资金账户；<br>2、您出借一次非新手类产品，体验标收益会在您这次出借完成后，随本息一并回款至您的账户中。</dd>          
                            </dl>
                        	<div class="imgfl flzc"><img src="<%=pathUrl %>/images/tznews04.jpg" width="198" height="201"></div>	
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
<script type="text/javascript" src="/js/gundong.js"></script>

<!--验证表单-->
<script src="/js/jquery.validate.js"></script>
<script>
$(function(){
    //jquery.validate
	$("#FormChujie").validate({
		submitHandler: function(form) {
			//验证通过后 的js代码写在这里
			//alert(1);
			$(".confirmbtn").css({"background":"#ababab","pointer-events":"none"});
			form.submit();
		}
	})
	
})
$(function(){
	$('#tiyan_none').on('click', function(event){
		//alert("a")
        event.preventDefault();
        $('.cd-popup6').addClass("is-visible1");
        //$(".dialog-addquxiao").hide()
    });
	
	$(".tishi_btn").on('click',function(event){
		event.preventDefault();
		$('.cd-popup6').removeClass('is-visible1');
	})
	
	
	$.ajax({
        type: "get",
        url: "/portal/detial/isTyjUse",
        dataType: "json",
        success: function(result){
        	console.log(result)
        	var result=result.dataValue;
        	$("#tyNum").text(result.count);
        	
        	var strBtn;
        	if(result.runTrue=='0'){
        		if(result.yfs==0){
        			$("#sykt em").text("18888.00");
        			strBtn='<a id="tiyan_btn_new" class="caozuo tzjs cd-popup-trigger2" href="#" >立即体验</a>';
        		}else{
        			$("#sykt em").text("0.00");
        			strBtn='<a id="" class="caozuo tzjs yimanbiao" href="javascript:;" >立即体验</a>';
        		}
        		
        	}else{
        		$("#sykt em").text("0.00");
        		strBtn='<a class="caozuo tzjs yimanbiao" href="javascript:;" >立即体验</a>';
        	}
        	$(".jineBtn").append(strBtn);
        	$("body").append('<script src="\/js\/chujieTKtyj_xian.js"><\/script>');
        	      	
        	
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
$(function(){
	var new_money=$("#new_money em").text();
	$("#amount").val(new_money);
})





</script>
</html>
