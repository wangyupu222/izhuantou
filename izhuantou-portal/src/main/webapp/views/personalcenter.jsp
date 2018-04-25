<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/include/url.jsp" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>砖头网</title>
<link rel="stylesheet" type="text/css" href="/css/style-common.css">
<link rel="stylesheet" type="text/css" href="/css/personalcenter.css?v=115">
<!--<link rel="stylesheet" href="css/jquery.onoff.css" media="screen" />-->
<Link rel="shortcut icon" href="<%=pathUrl %>/images/zhuan.ico" />
<!--日历-->
<link rel="stylesheet" href="/css/style.css">
<!--必要样式-->
<link rel="stylesheet" href="/css/fullcalendar.css">
<link rel="stylesheet" href="/css/fullcalendar.print.css" media='print'>


<!--<script type="text/javascript" src="js/jquery-1.10.1.min.js"></script>-->
<!--日历-->
<script type="text/javascript" src="/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="/js/fullcalendar.min.js"></script>
 <script type="text/javascript" src="/js/script.js"></script> 
<script>
$(function(){
	var availablemoney=$("#availablemoney").text();
	var principalMoney=$("#principalMoney").text();
	var interestMoney=$("#interestMoney").text();
	var zczzMoney=$(".y-data span").text();
    var re = /([0-9]+\.[0-9]{2})[0-9]*/;
    $("#availablemoney").text(availablemoney.replace(re,"$1"));
    $("#principalMoney").text(principalMoney.replace(re,"$1"));
    $("#interestMoney").text(interestMoney.replace(re,"$1"));
    $(".y-data span").text(zczzMoney.replace(re,"$1"));
})
</script>
<script src="/js/pic.js"></script>
<!--<script src="js/jquery.onoff.min.js"></script>-->
<script src="/js/com.js"></script>
<script src="/js/Chart.js"></script>
<script src="/js/rili_script.js"></script>
<script language="javascript" src="/js/menu.js"></script>
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries-->
<!--[if lt IE 9]><script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script><script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script><![endif]-->


<style type="text/css">
*{margin:0;padding:0;list-style-type:none;}
a,img{border:0;}
/*body{font:12px/180% Arial, Helvetica, sans-serif, "新宋体";}*/
.container{width:100%;margin:0 auto;}
.mytzcon{ width:95%;}
#canvas{behavior: url(/js/ie-css3.htc);}
.rowbox03 .div-text.short{width:13.2%;}
.rowbox03 .div-text.long{width:17.2%;}
.hide_liu{  width: 100%;  height: 500px;  text-align: center;  background:#fff url("<%=pathUrl %>/images/xiangqing_liu.png"); background-repeat: no-repeat; background-position:center center; display: none;  }
.personal_jie_liu{background:#fff url("images/JKjilu.jpg")no-repeat center;}
td.yuqi {
    color: #f00;
}
.tyj_tishi{
margin-bottom:30px;
}
.tyj_tishi p{
line-height:26px;
text-indent:2em;
}

td.zchk {
    color: #04b277;
}
.cd-popup-container1{
width:28%;
margin:165px auto;
}
.zhuang_tk span{
    display: inline-block;
    width: 78px;
    height: 30px;
    background-color: #55b0fd;
    text-align: center;
    margin: 0 auto;
    margin-left: 54px;
    line-height: 30px;
    border-radius: 5px;
    color:#fff;
}
</style>

</head>
<body>
<%-- <% try{ %> --%>
<hoontag:TagAction handleClassName="isLogin" />

<hoontag:TagAction handleClassName="pagePersonalcenter" />

<!--弹框-->
<div class="cd-popup5">
<div class="cd-popup-container5">
<!--修改头像-->
<form name="" action="" method="">
   <div class="comnei ">
       <a class="close cd-popup-close"></a>
       <p class="commit-1"><span>修改头像</span></p>
      <iframe src="portal/personal/uploadTxImg_iframe.jsp" width="100%" height="400" id="iframepage" name="iframe" frameborder="0" scrolling="no" marginheight="0" marginwidth="0"></iframe>
   </div>  
</form>  
       <!--修改头像结束-->
     
        
     </div>
</div>
<!--协议列表弹框-->
<div class="cd-popup6">
<div class="cd-popup-container1">
<!--协议列表-->
	<div class="comnei ">
        <a class="close cd-popup-close"></a>
        <p class="commit-1"><span>协议列表</span></p>
        <iframe src="/AgreementList_iframe.jsp?DOID=&biOID=" width="99%" height="160" id="iframepage2" name="iframe" frameborder="0" scrolling="no" marginheight="0" marginwidth="0"></iframe>
    </div>

       
        
     </div>
</div>


<!--     体验金提示弹出框      -->
<div class="cd-popup1">
<div class="cd-popup-container1">
<!--协议列表-->
	<div class="comnei ">
        <a class="close cd-popup-close"></a>
        <p class="commit-1"><span>体验金</span></p>
        <div class="ty_conter">
        <div class="conter_text">您需要进行一次出借以获得该收益，我们为您精心准备了很多好产品，快去看看吧</div>
        <div class="conter_btn"><a href="/projects.jsp?selType=all&startRate=0&&endRate=100&startTerm=0&endTerm=100">好，去出借</a></div>
        <div class="conter_zhu">注：完成出借后，该收益会在出借到期时一并到账</div>
        </div>
    </div>

       
        
     </div>
</div>

<!--common header-->
<%@ include file="header.jsp" %>
<!--common end-->
<div class="main minhh">
	<div class="con personcon">
    	<div class="smallnav">
    	<a>当前位置</a><span>:</span><a >个人中心</a><span>&gt;</span><a>用户中心</a><span>&gt;</span><a>中心首页</a>
         <%-- <div class="gdbox"><span class="sp01"></span><label>完善信息:</label>
      <div id="scroll2" style="overflow:hidden ;height:40px; line-height:39px;"> <a href="/Securitypre.jsp" >您尚未开通富友账户并设置交易密码，请点击这里完善信息</a></div></div> --%>
        </div>
       
        <div class="con xqdetial">
        <%@ include file="left_percenter.jsp" %>
    	
        
    	<div class="xqdeleft">
        	<div class="yhtop">
            	<div class="tx01" ><a  class="cd-popup-trigger5"><img id="imgTx" src="<%=pathUrl %>/images/web_head_icon.png" onclick="passonOID('<%=request.getAttribute("memberOID")%>')"></a></div>
            	<% 
            	String picOID = (String)request.getAttribute("picOID");
            	if((!"".equals(picOID)) && picOID!=null){ %>
            	  <script type="text/javascript">
            	  $(function(){
            		  $('#imgTx').attr("src",'<%=request.getAttribute("imgSrc")%><%=request.getAttribute("picOID")%>');
            		  $('#imgTx').height(98); 
            		  $('#imgTx').width(98); 
            		})
            	  </script>
            	<%} %>
            	
            	<div class="yhmid">
                	<div class="username"><%=session.getAttribute("userMobile")%></div>
                    <div class="username02"><span class="jindu"><span class="blue"></span><span class="info_fin">资料完整度：20%</span></span><span class="yu_e">可用余额：<label>0.00元</label></span></div>
                    <div class="rz"><a href="Securitypre.jsp"><span class="rz01 active" ></span></a><a href="Securitypre.jsp"><span class="rz02 "></span></a><a href="Securitypre.jsp"><span class="rz03 "></span></a><a href="Securitypre.jsp"><span class="rz04 "></span></a><!--<span style=" font-size:10px; color:#666;">点击灰色的图标去完善资料</span>--></div>
                </div> 
                <div class="yhright">
                <div class="recharge_btn">
                <a href="/portal/cash/Recharge_new" >充值</a>
                </div>
                <div class="withdrawals_btn">
                <a href="/portal/cash/Withdrawals">提现</a>
                </div>
                </div>
            </div>
           
            <div class="zhzc" style="min-height: 470px;">
            	<div class="zczong">
                	<a href="#" class="active">出借账户</a><a href="#">借款账户</a>
                </div>
                
                 <div>
                <div class="opzhcon">
            	<div class="zhzc_top">
            	<div class="danwei">单位:(元)</div>
    			<div class="yh">
                <canvas id="canvas" height="200" width="200"></canvas>
                <div class="y-data">当前资产总值<br/><span></span></div>
                </div>    
    			<!--中间data-->
    			<div class="middata">
                	<span class="sp1"><em></em>可用余额<label id="availablemoney"></label></span>
                     <%-- <span class="sp2"><em></em>冻结金额<label id="frozenmoney"><%=request.getAttribute("frozenmoney")%></label></span> --%>
                    <span class="sp3"><em></em>待收本金<label id="principalMoney"></label></span>
                    <span class="sp4"><em></em>待收利息<label id="interestMoney"></label></span>
                   <span class="sp5"><em></em>待收特权<label id="tqsyds"></label></span>
                </div>
                <script>				
				
                </script>
    			<div class="box-rightpre hide">
                	<dl>
                    	<dd class="dd01"><label ></label><div><span>0</span>个红包，共<span>0</span>元<a href="MyPrivilege01.jsp" class="active">立即使用</a></div></dd>
                        <dd class="dd02"><label ></label><div><span>0</span>元理财金<a href="MyPrivilege.jsp">立即使用</a></div></dd>
                        <dd class="dd03"><label></label><div><span>0</span>张加息券，加息<span>0%</span><a href="MyPrivilege02.jsp">立即使用</a></div></dd>
                        <dd class="dd04"><label></label><div>您已邀请<span></span>个好友<a href="Invitefriends.jsp">立即邀请</a></div></dd>
                    </dl>
                </div>
                
                </div>
<div style="color:#777;padding-left:56px;">*如果您的资产明细中没有数据，请等待出借标的满标，即可查看。</div>
                 <!--万年历-->
                <div class="wannianli " style="display:none;">
                <div class="subtit"><span class="subtitbot">待收回款日历</span></div>
                <!--<label>日期</label><input type="text" value="2015-10-10"  id="J-xl"/>-->
                <iframe src="iframe.jsp" width="100%" height="600" id="iframepage" name="iframe" frameborder="0" scrolling="no" marginheight="0" marginwidth="0"></iframe>  
				</div>
                       
                
                <!--<div class="mytz"><span class="mytzsub">出借记录</span><span class="yzje">已赚总金额  0.00元</span></div>-->
                
                     
                
                <div class="mytzcon hide">
                
                
                <div class="subtit"><span class="subtitbot">出借记录</span></div>
                	<%-- <div class="rowbox02 tit"><span>项目名称</span><span>出借金额</span><span>出借周期</span><span>年化收益率</span><span>到期时间</span></div>
                    <web:IteratorPartPage name="dtocListchujieCollection" id="List" rowSize="15">
                    <div class="rowbox02 value"><span><a title="aa" ><web:WidgetTextOut name="List" property="businessName"  /></a></span><span><web:WidgetTextOut name="List" property="money"  /></span><span>&nbsp;<web:WidgetTextOut name="List" property="returnNumber"  /></span><span><web:WidgetTextOut name="List" property="creditRate"  /></span><span><web:WidgetTextOut name="List" property="returnDate"  /></span></div>
                    </web:IteratorPartPage> --%>
                    <table width="100%" cellpadding="0" cellspacing="0" align="center">
                    <tr class="rowbox02 tit">
                    <td width="20%">项目名称</td>
                    <td width="20%">出借金额</td>
                    <td width="20%">出借周期</td>
                    <td width="20%">年化收益率</td>
                    <td width="20%">到期时间</td>
                    </tr>
                    <web:IteratorPartPage name="dtocListchujieCollection" id="List" rowSize="10">
                    <tr class="rowbox02 value">
                    <td><a title="aa" ><web:WidgetTextOut name="List" property="businessName"  /></a></td>
                    <td><web:WidgetTextOut name="List" property="money"  /></td>
                    <td><web:WidgetTextOut name="List" property="returnNumber"  /></td>
                    <td><web:WidgetTextOut name="List" property="creditRate"  />%</td>
                    <td><web:WidgetTextOut name="List" property="returndate" format="yyyy-MM-dd HH:mm:ss" /></td>
                    </tr>
                    </web:IteratorPartPage>
                    </table>
                    
                    
                    <div class="hide_liu personal_liu"></div>
                </div>
                
               </div>
            
               <div class="opzhcon hide">
                
                <div class="mytz"><!-- <span class="mytzsub">我的借款</span> --><span class="yzje"></span></div>
                <div class="mytzcon">                     
                    <table class="lendTable" width="100%" cellpadding="0" cellspacing="0" align="center">
                    <thead>
                    <tr class="rowbox03 tit">
                    <td width="14.2%">借款标题</td>
                    <td width="13.2%">借款金额（元）</td>
                    <td width="10.2%">借款利率</td>
                    <td width="12.8%">借款时间</td>
                    <td width="12.8%">到期时间</td>
                    <td width="12.8%">下个还款日</td>
                    <td width="10.2%">状态</td>
                    <td width="14.2%">协议</td>
                    </tr>
                    </thead>
                    <tbody>
                    </tbody>
                    </table>
                     
                     <div class="hide_liu personal_jie_liu"></div>
                    
                    <!-- <div class="rowbox03 value"> <div class="div-text">借款标题</div><div class="div-text">0.00元</div><div class="div-text">12个月</div><div class="div-text">10%</div><div class="div-text">2016-02-20  15:30</div><div class="div-text">2017-02-21 15:30</div><div class="div-text"><a href="#">还款</a></div>
                     <div class="div-text01"><input type="checkbox" checked class="zdinput" /></div></div>-->
                     
                   <!-- <div class="rowbox03 value">没有交易记录 <span>售权出借</span><span>0.00元</span><span>0.00元</span><span>0.00元</span><span><a href="">查看</a></span></div>-->
                   
                </div>
                
               </div> 
                
            </div>
            
            
      		</div>
            
            
            
            
            <div class="clear"></div>
        </div>
        
    </div>
    </div>
</div>
<!-----------------中间部分结束----------------> 
<!--bottom common-->
<%@ include file="footer.jsp" %>

<!--  <script>
    jQuery(function($) {
	  $('input[type="checkbox"]').onoff();
	});
  </script>-->
<%--   <%}catch(Exception e){
	response.sendRedirect("error.jsp");
    return;  
} %> --%>
</body>

<!--<script src="js/menubav.js"></script>-->
<!--<script src="js/tabmenu.js"></script>-->
<!-- <script src="js/gundong.js"></script> -->
<script type="text/javascript">
$(function(){
    if($(".rowbox02.value").length<1){
        $(".personal_liu").css("display","block");
    }
    
    $.ajax({
        type: "post",
        url: "/portal/personal/findPersonalIndex",
        dataType: "json",
        success: function(result){
        	console.log(result);
        	$(".yu_e label").text(result.dataValue.availablemoney+"元");
        	$(".info_fin").text("资料完整度："+result.dataValue.dataIntegrity);
        	$(".username02 span.blue").css("width",result.dataValue.dataIntegrity);
        	var mibaoTrue =result.dataValue.mibaoTrue;
        	var idCardTrue =result.dataValue.idCardTrue;
            if( mibaoTrue != null && mibaoTrue!="" && mibaoTrue!="null"){
                $(".rz04").addClass(" active");
            }
            if( idCardTrue != null && idCardTrue!="" && idCardTrue!="null"){
                $(".rz03").addClass(" active");
                $(".rz02").addClass(" active");
            }
            $(".y-data span").text((result.dataValue.availablemoney+result.dataValue.cybj+result.dataValue.tqsyds+result.dataValue.yjsy).toFixed(2));
            $("#availablemoney").text((result.dataValue.availablemoney).toFixed(2));
            $("#principalMoney").text((result.dataValue.cybj).toFixed(2));
            $("#interestMoney").text((result.dataValue.yjsy).toFixed(2));
            $("#tqsyds").text((result.dataValue.tqsyds).toFixed(2));
            
            var availablemoney=Number((result.dataValue.availablemoney).toFixed(2));
			var principalMoney=Number((result.dataValue.cybj).toFixed(2));
			var interestMoney=Number((result.dataValue.yjsy).toFixed(2));	
			var tqsyds=Number((result.dataValue.tqsyds).toFixed(2));
			if(availablemoney==0  && principalMoney==0 && interestMoney==0 && tqsyds==0){
				 var doughnutData = [
				                     {
				                         value:90,
				                         color:"#fde72e"
				                     },
				                     //{
				                      //   value : 90,
				                      //   color : "#ff6e0a"
				                     //},
				                     {
				                         value : 90,
				                         color : "#4ec04e"
				                     },{
				                         value : 90,
				                         color : "#429c42"
				                     },
				                   	{
				                         value : 90,
				                         color : "#ff6e0a"
				                     },
				                 ];
			}else{
				var doughnutData = [
				                    {
				                        value:availablemoney,
				                        color:"#fde72e"
				                    },
				                    
				                    {
				                        value : principalMoney,
				                        color : "#4ec04e"
				                    },{
				                        value : interestMoney,
				                        color : "#429c42"
				                    },
				                    {
				                    	value : tqsyds,
				                         color : "#ff6e0a"
				                     },
				                ];
			}
            
            var myDoughnut = new Chart(document.getElementById("canvas").getContext("2d")).Doughnut(doughnutData);
        },
		error:function(result){    	
    	}
    });
    
    $.ajax({
        type: "post",
        url: "/portal/personal/PersonalLean",
        dataType: "json",
        success: function(result){
        	console.log(result);
        	if(result.dataValue.length<1){
        		$(".personal_jie_liu").css("display","block");
        	}else{
        		$(".yzje").text("待还本金 :"+result.dataValue[0].totalLoanMoney+"元");
        		var DateFlag=result.dataValue[0].dateFlag;
        		var str='<tr class="rowbox03 value"><td >'+result.dataValue[0].loanNumber+'</td><td >'+result.dataValue[0].loanAmount+'</td><td >'+result.dataValue[0].debitRate+'%</td><td >'+result.dataValue[0].addDateTime+'</td><td >'+result.dataValue[0].endDateTime+'</td><td>'+result.dataValue[0].repayDate+'</td>';
        		if(DateFlag>=3){
        			str+='<td class="yuqi">逾期</td>';
        		}else{
        			str+='<td class="zchk">正常还款</td>';
        		}
        		str+='<td><a class="protocolbtn" href="#" target="_blank" onclick="xiyieOID(\'jk\',\''+result.dataValue[0].businessOID+'\',\''+result.dataValue[0].loanNumber+'\')" >查看协议</a></td></tr>';
        		$(".lendTable tbody").append(str);
        	}
        	

        },
		error:function(result){    	
    	}
    });
    
})
function passonOID(loanOID){	
	var imgurl = "/portal/personal/uploadTxImg_iframe?loanOID="+loanOID;
	$('#iframepage').attr('src',imgurl);
	//alert(imgurl);


}
//资料显示
$(function(){
	
     var pay_new=$(".pay_new").val();
    if(pay_new==0){
    	$(".zhuang_tk").text("使用中");
    }else{
    	$(".zhuang_tk").append("<span class='tixian_btn'>提现</span>");
    } 
    
    
    $('.tixian_btn').on('click', function(event){
        event.preventDefault();
        $('.cd-popup1').addClass('is-visible1');
        //$(".dialog-addquxiao").hide()
    });
})



 if (!document.addEventListener) {
    // IE6~IE8
    document.write('<script type="text/javascript" src="/js/excanvas.js"><\/script>');	
} 

function xiyieOID(type,biOID,loanNumber){
	 var iframeurl ="AgreementList_iframe.jsp?type="+type+"&biOID="+biOID+"&loanNumber="+loanNumber;
		$("#iframepage2").attr("src",iframeurl);
}
</script>
<!--<script src="js/simplecalendar.js" type="text/javascript"></script>-->


</html>
