<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/url.jsp"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>砖头网-借款记录</title>
<link rel="stylesheet" type="text/css" href="/css/style-common.css">
<link rel="stylesheet" type="text/css" href="/css/personalcenter.css">
<link rel="stylesheet" type="text/css" href="/css/CapitalDetailed.css">
<Link rel="shortcut icon" href="<%=pathUrl %>/images/zhuan.ico" />
<script type="text/javascript" src="/js/jquery-1.10.1.min.js"></script>
<script src="/js/com.js"></script>
<script language="javascript" src="/js/menu.js"></script>
<!--日期时间-->
<script language="javascript" type="text/javascript"
	src="/My97DatePicker/WdatePicker.js"></script>
<script language="javascript" type="text/javascript"
	src="/js/layer.min.js"></script>
<style>
.rowbox06 span.shortspan {
	width: 12.6%;
}

.rowbox06 span.longspan {
	width: 17.6%;
}

.rowbox06 li.shortspan {
	width: 12.6%;
}

.rowbox06 li.longspan {
	width: 17.6%;
}

.rowbox06 span.subject {
	
}

.rowbox06 li.yuqi {
	color: #f00;
}

.rowbox06 li.zchk {
	color: #04b277;
}

.hide_liu {
	width: 100%;
	height: 500px;
	text-align: center;
	background: #fff url("<%=pathUrl %>/images/JKjilu.jpg");
	background-repeat: no-repeat;
	background-position: center center;
	display: none;
}
</style>
</head>
<body>
	<% try{ %>
	<div class="cd-popup2">
		<div class="cd-popup-container2">
			<!--立即还款-->
			<div class="comnei comneichujie">
				<a class="close cd-popup-close"></a>
				<p class="commit-1">
					<span>立即还款</span>
				</p>
				<div class="yunull yunullchujie">
					<div class="boxtitle">
						<img src="<%=pathUrl %>/images/chujie.png" width="65" height="53">
						<div class="titlebt">
							<a href="Diantzdetails.jsp">i房贷—房产周转贷款</a>
						</div>
						<div class="titlebt">
							<a href="#">ZT20160309</a>
						</div>
					</div>

					<div class="contentchujie">

						<div class="ljhkbox">
							<div class="ljhkboxone">
								<span class="sp01"><em>18</em>%</span> <span class="sp02">借款利率</span>
							</div>

							<div class="ljhkboxone">
								<span class="sp01"><em>12</em>个月</span> <span class="sp02">借款期限</span>
							</div>

							<div class="ljhkboxone">
								<span class="sp01"><em>150000</em>元</span> <span class="sp02">借款金额</span>
							</div>

							<div class="ljhkboxone">
								<span class="sp01 blue"><em>1863</em>元</span> <span class="sp02">应还金额</span>
							</div>
						</div>
						<div class="zhye">
							<span class="sp1">账户余额</span><span class="sp2"><em>633</em>元</span>
						</div>




					</div>



				</div>
				<p class="commit-2">
					<!--<span>理财有风险，投资需谨慎</span>-->
					<a class="colseok" href="tzdetailsok.jsp">确认</a><span
						class="spjineyue">账户余额不足，请<a href="Recharge.jsp" class="cz">充值</a></span>
				</p>
			</div>
			<!--确认出借结束-->

			<!--余额不足-->
			<div class="comnei comneichujie hide">
				<a class="close cd-popup-close"></a>
				<p class="commit-1">
					<span>余额不足</span>
				</p>
				<p class="yebz">
					抱歉！您的余额不足，<a href="#">立即充值</a>
				</p>
				<p class="commit-2">
					<span>理财有风险，投资需谨慎</span><a class="colseok" href="#">确认</a>
				</p>
			</div>
			<!--余额不足结束-->

		</div>
	</div>

	<!--协议列表弹框-->
	<div class="cd-popup1">
		<div class="cd-popup-container1">
			<!--充值-->
			<div class="comnei ">
				<a class="close cd-popup-close"></a>
				<p class="commit-1">
					<span>协议列表</span>
				</p>
				<iframe src="/portal/mylend/AgreementList_iframe.jsp?DOID=&biOID="
					width="99%" height="160" id="iframepage" name="iframe"
					frameborder="0" scrolling="no" marginheight="0" marginwidth="0"></iframe>
			</div>



		</div>
	</div>

	<!--common header-->
	<%@ include file="header.jsp"%>
	<!--common end-->
	<div class="main minhh">
		<div class="con personcon">
			<div class="smallnav">
				<!-- <a >当前位置</a><span>:</span><a>个人中心</a><span>&gt;</span><a >我的借款</a><span>&gt;</span><a >借款记录</a> -->
			</div>
			<div class="con xqdetial">
				<%@ include file="left_percenter.jsp"%>

				<div class="xqdeleft my_loan">
					<div class="my_loantop">
						<a href="#" class="active">借款记录</a><span class="L1">&nbsp;</span>
					</div>
					<div class="clear"></div>
					<div>
						<!--出借记录-->
						<div class="loanrecord01">
							<div class="loanrecord">
								<div>
									<div class="mytzcon">
										<div class="rowbox06 tit JKjilu">
											<span class="longspan">借款标题</span> <span>借款金额(元)</span> <span
												class="shortspan">借款利率</span> <span class="longspan">借款时间</span>
											<span>下个还款日</span> <span class="shortspan">状态</span> <span
												class="shortspan">协议</span>
										</div>
										<div class="listItem"></div>
										<div class="hide_liu JKjilu_liu"></div>
										<!--分页-->
										<web:PartPage2 name="dtocListJKRecordsCollection"
											customProperty="class=\"page\""
											customSelectProperty="class=\"active\"" />
										<!--分页结束-->
										<!-- <div class="nonemore">没有更多记录</div> -->
									</div>
								</div>




							</div>
						</div>
					</div>

					<!--历史充值-->
					<div class="hide"></div>

					<div class="clear"></div>
				</div>

			</div>
		</div>
	</div>
	<!-----------------中间部分结束---------------->
	<!--bottom common-->
	<%@ include file="footer.jsp"%>

	<%}catch(Exception e){
	response.sendRedirect("error.jsp");
    return;  
} %>

</body>
<script src="/js/tabmenu.js"></script>
<script>
$(function(){
    
    $.ajax({
        type: "post",
        url: "/portal/loan/myLoanRecord",
        dataType: "json",
        success: function(result){
        	var result=result.dataValue;
        	console.log(result)
        	if(result.data<1){
        		$(".JKjilu_liu").css("display","block");
        	}
        	for(var i=0;i<result.data.length;i++){
        		var loanDay=result.data[i].loanDay;
        		var nextDay=result.data[i].nextDay;
        		if(loanDay==null || loanDay==''){
        			loanDay='';
        		}
        		if(nextDay==null || nextDay==''){
        			nextDay='';
        		}
        		
        		var str='<ul class="rowbox06 value"><li class="subject longspan">'+result.data[i].loanNumber+'</li><li>'+result.data[i].loanAmount+'</li><li class="shortspan">'+(result.data[i].yearRate).toFixed(2)+'%</li><li class="longspan">'+loanDay+'</li><li class="shortspan">'+nextDay+'</li>';
        		if(result.data[i].nextDay!=null && result.data[i].nextDay!=''){

        		if(result.data[i].space>=1){
        			str+='<li class="yuqi">逾期</li>';
        			
        		}else{
        			if(result.data[i].isFK!=null && result.data[i].isFK!=''){
        				if(result.data[i].isFK=='0'){
        					str+='<li class="zchk">已满标</li>';
        				}else if(result.data[i].isFK=='1'){
        					str+='<li class="zchk">已放款</li>';
        				}
        			}else{
        				str+='<li class="zchk">正常还款</li>';
        			}
        			
        		}
        		}else if(result.data[i].loanDay!=null && result.data[i].loanDay!=''){
        			str+='<li class="zchk">已结清</li>';
        		}else{
        			str+='<li class="zchk">待满标</li>';
        		}
        		str+='<li class="shortspan"><a class="cd-popup-trigger1" href="#" target="_self" onclick="xiyieOID(\'jk\',\''+result.data[i].businessOID+'\',\''+result.data[i].loanNumber+'\',\''+result.xyType+'\')" >查看协议</a></li></ul>';
        		$(".listItem").append(str);
        	}
        	        		        	
        },
		error:function(result){    	
    	}
    });
    $(document).on('click','.cd-popup-trigger1', function(event){
        event.preventDefault();
        $('.cd-popup1').addClass('is-visible1');
    });
    
 //   alert($(".chi_you").next().find(".ddtit").length);
})
function xiyieOID(type,biOID,loanNumber,xyType){
	 var iframeurl ="/portal/mylend/AgreementList_iframe.jsp?type="+type+"&biOID="+biOID+"&loanNumber="+loanNumber+"&xyType="+xyType;
		$("#iframepage").attr("src",iframeurl);
 }
</script>
</html>
