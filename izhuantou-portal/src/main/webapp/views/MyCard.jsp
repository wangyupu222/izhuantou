<%@ page contentType="text/html; charset=UTF-8" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>砖头网</title>
<link rel="stylesheet" type="text/css" href="/css/style-common.css">
<link rel="stylesheet" type="text/css" href="/css/personalcenter.css">
<link rel="stylesheet" type="text/css" href="/css/preother.css">
<Link rel="shortcut icon" href="/images/zhuan.ico" />
<script type="text/javascript" src="/js/jquery-1.10.1.min.js"></script>
<script src="/js/paging.js"></script>
<script src="/js/com.js"></script>
<script language="javascript" src="/js/menu.js"></script>
<style>
.wxts{margin-top:25px;}
.hbcon{padding: 10px 0px;min-height: 350px;}
.wxtscon span{color:#55b0fd;}
.cardyong {
    float: left;
}
.comnei{
    position: relative;
}
.close {
    position: absolute;
    right: 10px;
    top:10px;
}
</style>
</head>
<body>
<% try{ %>
<div class="tiyan_new cd-popupcar">
<div class="cd-popup-containercar ReplaceWrap">
	<div class="comnei comneione">
        <a class="close cd-popup-close"></a>
        
        <div class="boxconsq ReplaceMain">
        <h3>换卡须知</h3>
        	<div class="titlesq01">1、请提交<span class="textRed">真实</span>身份信息及更换的银行卡信息，并确认银行卡信息与您身份信息一致；<br>
				2、新银行卡的银行实际预留手机号务必与平台注册手机号<span class="textRed">保持一致</span>；<br>
				3、上传身份材料时，请选择<span class="textblack">【未签约用户】</span>，上传材料应清晰且无遮挡，图片为jpg<br>
  				   格式<br>
				4、需上传材料：持卡人与身份证、新银行卡的合照；身份证原件正反面照；绑定新<br>
    			 银行卡正面照；解绑原银行卡正面照；<br>
   				（如原银行卡丢失，仍需上传原银行卡的挂失证明或原银行卡近期流水单）；<br>
				5、申请换卡审核过程中，<span class="textblack">请勿进行充值、提现操作，可以进行正常出借</span>
</div>
        	<div class="consqbut01">
        		<a class="active ReplaceBtn" href="/cn/com/hoonsoft/servlet/ServletAction?handleClassName=ReplaceCardAccount" >点我换卡</a>
        		
        	</div>
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
    	<!-- <a >当前位置</a><span>:</span><a >个人中心</a><span>&gt;</span><a >我的资金</a><span>&gt;</span><a >银行卡</a> -->
        </div>
        <div class="con xqdetial">
    	<%@ include file="left_percenter.jsp" %>
        
    	<div class="xqdeleft">
        	<!--<div class="hbtabcar"><a class="active">我的银行卡</a><a>账户明细</a></div>-->
        	<div class="my_loantop"><a href="#" class="active">银行卡</a><span class="L1">&nbsp;</span></div>
            
            <div class="clear"></div>
            <div class="hbcon">
            	<div class="box-quan">
            	<web:IteratorPartPage name="dtocListMyCardCollection" id="List" rowSize="10">
            		
                 	<div class="cardyong">
                    <div class="cardyonglist">
                    <div class="card01"><span class="sp-img">
                    
                     <%
                    String bankCode=(String)request.getAttribute("bankCode");
                    if("0102".equals(bankCode)){
                    %>
                    <!-- 工商 -->
                    <img id="cardImage" src="/images/card_gs.png">
                    <% }else if("0105".equals(bankCode)){%>                    
                    <!-- 建行 -->
                    <img id="cardImage" src="/images/card_jh.png">
                    <% }else if("0103".equals(bankCode)){%>
                    <!-- 农行 -->
                    <img id="cardImage" src="/images/card_ny.png">
                    <% }else if("0104".equals(bankCode)){%>
                    <!-- 中国 -->
                    <img id="cardImage" src="/images/card_zg.png">
                    <% }else if("0303".equals(bankCode)){%>
                    <!-- 光大 -->
                    <img id="cardImage" src="/images/card_gd.png">
                    <% }else if("0302".equals(bankCode)){%>
                    <!-- 中信 -->
                    <img id="cardImage" src="/images/card_zx.png">
                    <% }else if("0304".equals(bankCode)){%>
                    <!-- 华夏 -->
                    <img id="cardImage" src="/images/card_hx.png">
                    <% }else if("0305".equals(bankCode)){%>                    
                    <!-- 民生 -->
                    <img id="cardImage" src="/images/card_ms.png">
                    <% }else if("0306".equals(bankCode)){%>
                    <!-- 广发 -->
                    <img id="cardImage" src="/images/card_gf.png">
                    <% }else if("0307".equals(bankCode)){%>
                    <!-- 平安 -->
                    <img id="cardImage" src="/images/card_pa.png">
                    <% }else if("0308".equals(bankCode)){%>
                    <!-- 招商 -->
                    <img id="cardImage" src="/images/card_zs.png">
                    <% }else if("0309".equals(bankCode)){%>
                    <!-- 兴业 -->
                    <img id="cardImage" src="/images/card_xy.png">
                    <% }else if("0310".equals(bankCode)){%>
                    <!-- 上海浦东发展银行 -->
                    <img id="cardImage" src="/images/card_pf.png">
                    <% }else if("0301".equals(bankCode)){%>
                    <!-- 交通银行 -->
                    <img id="cardImage" src="/images/card_jt.png">
                    <% }else if("0319".equals(bankCode)){%>
                    <!-- 徽商银行 -->
                    <img id="cardImage" src="/images/card_hs.png">
                    <% }else if("0403".equals(bankCode)){%>
                    <!-- 邮政储蓄 -->
                    <img id="cardImage" src="/images/card_yz.png">
                    
                    <%} %>
                    </span></div>
                    <div class="divcardname"><span class="sp-name"><web:WidgetTextOut name="List" property="nameCN"  /></span></div>
                    <div class="divcardnum"><span class="sp-num"><web:WidgetTextOut name="List" property="bankNumber"  /></span></div>
                    </div>
                     <!-- <div class="sqbg"><a target="_blank" href="#">申请变更</a></div> --> 
                    <!--变更流程-->
                    
                    </div>
                    </web:IteratorPartPage> 
                    <div class="transaction_wrap hide">
                    <div class="transaction_pwd"><a href="/cn/com/hoonsoft/servlet/ServletAction?handleClassName=ReplaceCardAccount" >更换银行卡</a></div>
                    </div> 
                    <div class="clear"></div>                  
                    <div class="Replace_text">点此<a id="ReplaceCardId">更换银行卡</a>，如有疑问，请联系砖头网客服400-900-9677。</div>
                     <div class="sqlc" style="border-top:none;"><%-- <img src="/images/sqlc.png"> --%></div>
                     
                </div>
                <div class="clear"></div>
            </div>
            
            <div class="wxts" style="display:none;">
                <div class="clear"></div>
                <div class="wxtscon">
                                       如有疑问，请联系砖头网客服<span>400-900-9677</span>，或咨询微信公众号： <span>izhuantou</span> ，或发送邮件至<span>service@izhuantou.com</span><br>
                </div>
            </div>
            <div class="ReplaceRecord hide">
            <h3>换卡申请记录</h3>
						<table class="ReplaceTable">
						<thead>
								<tr>
									<td>序号</td>
									<td>申请时间</td>
									<td>处理结果</td>
									<td>备注</td>
								</tr>
								</thead>
								<tbody>
								
							</tbody>
						</table>
						<!--分页-->
            			<div id="page" class="page_div"></div>
                		<!--分页结束-->
					</div>
            <div class="clear"></div>
        </div>
        
    </div>
    </div>
</div>
<!-----------------中间部分结束----------------> 
<!--bottom common-->
<%@ include file="footer.jsp" %>

<%}catch(Exception e){
	response.sendRedirect("error.jsp");
    return;  
} %>
</body>
<script>
$(function(){
	$.ajax({
        type: "get",
        url: "/portal/cash/myCashmessage",
        dataType: "json",
        success: function(result){
        var CodeStr='';
        if(result.dataValue.bankCode=='0102'){
        	CodeStr='<img id="cardImage" src="/images/card_gs.png">';
        }else if(result.dataValue.bankCode=='0105'){
        	CodeStr='<img id="cardImage" src="/images/card_jh.png">';
        }else if(result.dataValue.bankCode=='0103'){
        	CodeStr='<img id="cardImage" src="/images/card_ny.png">';
        }else if(result.dataValue.bankCode=='0104'){
        	CodeStr='<img id="cardImage" src="/images/card_zg.png">';
        }else if(result.dataValue.bankCode=='0303'){
        	CodeStr='<img id="cardImage" src="/images/card_gd.png">';
        }else if(result.dataValue.bankCode=='0302'){
        	CodeStr='<img id="cardImage" src="/images/card_zx.png">';
        }else if(result.dataValue.bankCode=='0304'){
        	CodeStr='<img id="cardImage" src="/images/card_hx.png">';
        }else if(result.dataValue.bankCode=='0305'){
        	CodeStr='<img id="cardImage" src="/images/card_ms.png">';
        }else if(result.dataValue.bankCode=='0306'){
        	CodeStr='<img id="cardImage" src="/images/card_gf.png">';
        }else if(result.dataValue.bankCode=='0307'){
        	CodeStr='<img id="cardImage" src="/images/card_pa.png">';
        }else if(result.dataValue.bankCode=='0308'){
        	CodeStr='<img id="cardImage" src="/images/card_zs.png">';
        }else if(result.dataValue.bankCode=='0309'){
        	CodeStr='<img id="cardImage" src="/images/card_xy.png">';
        }else if(result.dataValue.bankCode=='0310'){
        	CodeStr='<img id="cardImage" src="/images/card_pf.png">';
        }else if(result.dataValue.bankCode=='0301'){
        	CodeStr='<img id="cardImage" src="/images/card_jt.png">';
        }else if(result.dataValue.bankCode=='0319'){
        	CodeStr='<img id="cardImage" src="/images/card_hs.png">';
        }else if(result.dataValue.bankCode=='0403'){
        	CodeStr='<img id="cardImage" src="/images/card_yz.png">';
        }
        $(".sp-img").append(CodeStr);
        $(".sp-name").append(result.dataValue.nameCN);
        $(".sp-num").append(result.dataValue.bankNumber);  
        },
		error:function(result){
    	}
    });
	
	$.ajax({
        type: "get",
        url: "/portal/cash/myCash",
        dataType: "json",
        success: function(result){
        if(result.dataValue.changeCardInfo=='true'){
        	$(".ReplaceRecord").removeClass("hide");
        }else{
        	$(".ReplaceRecord").remove("hide");
        }
        var changCardList=result.dataValue.changCardList;
        for(var i=0;i<changCardList.length;i++){
        	var str='<tr><td>'+(i+1)+'</td><td>'+changCardList[i].addDateTime+'</td>';
        	if(changCardList[i].successStatus=='0000' && changCardList[i].requestStatus=='0000'){
        		str+='<td style="color:#55b0fd;">提交申请</td>';
        		$("#ReplaceCardId").css({"pointer-events":"none","color":"#eaeaea"});
        	}else if(changCardList[i].successStatus=='0000' && changCardList[i].requestStatus=='1111'){
        		str+='<td style="color:#ff7701;">待审核</td>';
        		$("#ReplaceCardId").css({"pointer-events":"none","color":"#eaeaea"});
        	}else if(changCardList[i].successStatus=='0000' && changCardList[i].requestStatus=='2222'){
        		str+='<td style="color:#e63f47;">提交申请失败</td>';
        	}else if(changCardList[i].successStatus=='3333' && changCardList[i].requestStatus=='0000'){
        		str+='<td style="color:#e63f47;">提交申请失败</td>';
        	}else if(changCardList[i].successStatus=='1111' && changCardList[i].requestStatus=='1111'){
        		str+='<td style="color:#53ac02;">已通过</td>';
        	}else if(changCardList[i].successStatus=='1111' && changCardList[i].requestStatus=='0000'){
        		str+='<td style="color:#53ac02;">已通过</td>';
        	}else if(changCardList[i].successStatus=='2222' && changCardList[i].requestStatus=='1111'){
        		str+='<td style="color:#e63f47;">未通过</td>';
        	}else if(changCardList[i].successStatus=='2222' && changCardList[i].requestStatus=='0000'){
        		str+='<td style="color:#e63f47;">未通过</td>';
        	}
        	var remark='';
        	if(changCardList[i].remark==null || changCardList[i].remark=='null'){
        		remark='';
        	}else{
        		remark=changCardList[i].remark;
        	}
        	str+='<td>'+remark+'</td></tr>';
        	$(".ReplaceTable tbody").append(str);
        }
        if(result.dataValue.Pagination.totalNumber<=5){
        	$("#page").remove();
        }
        
        $("#page").paging({
			totalPage: result.dataValue.Pagination.totalPage,
			totalSize: result.dataValue.Pagination.totalNumber,
			callback: function(num) {
				$.ajax({
                    type: "get",//请求方式
                    url: "/portal/cash/myCash",//地址，就是json文件的请求路径
                    dataType: "json",
                    data:{
                    	currentPage:num
                    },
                    success: function(result){//返回的参数就是 action里面所有的有get和set方法的参数
                    	console.log(result)
                    	$(".ReplaceTable tbody tr").remove();
                    	var changCardList=result.dataValue.changCardList;
                    	for(var i=0;i<changCardList.length;i++){
                        	var str='<tr><td>'+((num-1)*5+(i+1))+'</td><td>'+changCardList[i].addDateTime+'</td>';
                        	if(changCardList[i].successStatus=='0000' && changCardList[i].requestStatus=='0000'){
                        		str+='<td style="color:#55b0fd;">提交申请</td>';
                        		$("#ReplaceCardId").css({"pointer-events":"none","color":"#eaeaea"});
                        	}else if(changCardList[i].successStatus=='0000' && changCardList[i].requestStatus=='1111'){
                        		str+='<td style="color:#ff7701;">待审核</td>';
                        		$("#ReplaceCardId").css({"pointer-events":"none","color":"#eaeaea"});
                        	}else if(changCardList[i].successStatus=='0000' && changCardList[i].requestStatus=='2222'){
                        		str+='<td style="color:#e63f47;">提交申请失败</td>';
                        	}else if(changCardList[i].successStatus=='3333' && changCardList[i].requestStatus=='0000'){
                        		str+='<td style="color:#e63f47;">提交申请失败</td>';
                        	}else if(changCardList[i].successStatus=='1111' && changCardList[i].requestStatus=='1111'){
                        		str+='<td style="color:#53ac02;">已通过</td>';
                        	}else if(changCardList[i].successStatus=='1111' && changCardList[i].requestStatus=='0000'){
                        		str+='<td style="color:#53ac02;">已通过</td>';
                        	}else if(changCardList[i].successStatus=='2222' && changCardList[i].requestStatus=='1111'){
                        		str+='<td style="color:#e63f47;">未通过</td>';
                        	}else if(changCardList[i].successStatus=='2222' && changCardList[i].requestStatus=='0000'){
                        		str+='<td style="color:#e63f47;">未通过</td>';
                        	}
                        	var remark='';
                        	if(changCardList[i].remark==null || changCardList[i].remark=='null'){
                        		remark='';
                        	}else{
                        		remark=changCardList[i].remark;
                        	}
                        	str+='<td>'+remark+'</td></tr>';
                        	
                        	$(".ReplaceTable tbody").append(str);
                        }
                    }    			            	            	
                	});
			}
		})
        
        
            
        },
		error:function(result){
    	}
    });
	
})
$("#ReplaceCardId").click(function(){
    $(".cd-popupcar").addClass('is-visible1');
    
})
</script>
<script src="/js/tabmenu.js"></script>

</html>
