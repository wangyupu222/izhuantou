<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/url.jsp" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>砖头网-我的出借</title>
<link rel="stylesheet" type="text/css"
	href="/css/style-common.css">
<link rel="stylesheet" type="text/css"
	href="/css/personalcenter.css">
<link rel="stylesheet" type="text/css"
	href="/css/preother.css">
<Link rel="shortcut icon" href="<%=pathUrl %>/images/zhuan.ico" />
<script type="text/javascript"
	src="/js/jquery-1.10.1.min.js"></script>

<script src="/js/com.js"></script>
<script language="javascript" src="/js/menu.js"></script>
<script src="/js/paging.js"></script>
<!--<script src="/js/1-6-10.esl.js"></script>-->
<style>
table.centerzqzr tr.rowbox08{ height:55px; line-height:55px; font-size:14px;}
/* .rowbox08 span{ float:left; width:12.5%; text-align:center; }
.rowbox08.tit{ background:#f3f7fb; height:55px; line-height:55px;}
.rowbox08.value{ border-bottom:1px solid #eaeaea;}
.rowbox08.value a{white-space:nowrap; overflow:hidden; text-overflow:ellipsis; display:block;}
 */
table.centerzqzr td{ text-align: center; border-bottom:1px solid #eaeaea;}

table.centerzqzr td.transfer_ht a{ color:#55b0fd;}
table.centerzqzr td.transfer_tates a{ display:block; width:80px; height:30px; line-height:30px; text-align:center; margin:auto; margin-top:6px; border-radius:3px; background-color:#eeeeee; color:#fff; }
table.centerzqzr td.transfer_tates a.active{background:#55b0fd; color:#fff; cursor:pointer;}
table.centerzqzr td.transfer_tates a:hover{ background:#3291f0;}
table.hhttable tr td{ text-align: center;border-bottom:1px solid #eeeeee;font-size:14px;}
table.hhttable tr td.ddtit a{ display:block;  line-height:20px; margin-top:5px; color:#55b0fd;}
.hide_liu {
	width: 100%;
	height: 500px;
	text-align: center;
	background: #fff url("images/zhuanrang.jpg") no-repeat center;
	display: none;
}
.cd-popup-container5, .cd-popup-container6, .cd-popup-containerh {
    width: 780px;
    margin: 40px auto;
    height: 617px;
    border-radius: 5px;
    background: rgb(52, 52, 52);
    background: rgba(52, 52, 52, 0.0);
    text-align: center;
    box-shadow: 0 0 20px rgba(0, 0, 0, 0.0);
    margin-bottom:0px;
}
.transfer_ht{position: relative;}
.transfer_ht em{    position: absolute;
    right: 15px;
    top: -2px;}
.transfer_ht em img{
        width: 18px;
    }
.transfer_ht em:hover .shen_tishi{
        display:block;
    }
.shen_tishi{
position: absolute;
left:-176px;
top:48px;
width:174px;
min-height:55px;
background-color: #8595af;
border-radius: 10px;
color:#fff;
padding: 10px 13px;
font-size: 12px;
text-align: left;
line-height: 18px;
z-index:9;
display:none;
}
.shen_tishi img{
position: absolute;
left:160px;
top:-28px;
width:48px !important;
}
.fenge{width:90%;height:130px;border-top: 1px solid #eaeaea;margin:0 auto;margin-top: 60px;padding:15px 10px;font-size:14px;color:#666;line-height: 25px;}
.fenge h3{font-size:16px;font-weight: normal;margin-bottom:5px;}
</style>
</head>
<body>
<% try{ %>
	<div class="cd-popup5">
		<div class="cd-popup-container5">

			<!--申请债权转让-->
			<div class="comnei comneichujie">
				<a class="close cd-popup-close"></a>
				<p class="commit-1">
					<span>申请债权转让</span>
				</p>
				<iframe src="zqzrbox_iframe.jsp?PaydebitOID=&businessOID="
					class="zqzrifram" width="99.6%" height="510" id="iframepage"
					name="iframe" frameborder="0" scrolling="no" marginheight="0"
					marginwidth="0"></iframe>
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

	<div class="indexz">
		<!--common header-->
		<%@ include file="header.jsp"%>
		<!--common end-->
		<div class="main minhh">
			<div class="con personcon">
				<div class="smallnav">
					<a>当前位置</a><span>:</span><a>个人中心</a><span>&gt;</span><a>我的出借</a><span>&gt;</span><a>债权转让</a>
				</div>
				<div class="con xqdetial">
					<%@ include file="left_percenter.jsp"%>

					<div class="xqdeleft my_loan">
						<div class="my_loantop">
							<a href="#" class="active">申请债权转让</a><a href="#">转让记录</a><span class="L2">&nbsp;</span>
						</div>
						<div class="clear"></div>
						<div class="loanrecord">

							<div class="loanrecord01">
								<div>
								<!--可转让债权-->
									<div class="tabopa kezhuanrang">																	
										<table class="hhttable List_val centerzqzr" width="100%" algin="center" cellspacing="0" cellpadding="0">
										<thead>
										<tr class="loanrow tit ">
										<td style="width:183px">项目名称</td>
                 						<td style="width:85px">预期年化利率</td>
                 						<td style="width:100px">出借金额(元)</td>
                 						<td style="width:92px">出借时间</td>
                 						<td style="width:92px">到期时间</td>
                 						<td style="width:100px">应收金额(元)</td>
                 						<td style="width:100px">已收金额(元)</td>
                 						<td style="width:100px">待收金额(元)</td>
                 						<td style="width:95px">状态</td>
                 						<td></td>
										</tr>
										</thead>
										<tbody>
										</tbody>
										</table>
										<div class="hide_liu rowbox01_liu"></div>
                 						<!-- 分页 -->
                 						<div id="page" class="page_div pageCY"></div>
									</div>

									<!--已转让债权-->
									<div class="tabopa hide Yzhuanrang">
												
										<table class="hhttable " width="100%" algin="center" cellspacing="0" cellpadding="0">
										<thead>
										<tr class="loanrow tit">
										<td style="width:220px">项目名称</td>
										<td style="width:110px">预期年化利率</td>
										<td style="width:130px">出借金额(元)</td>
										<td style="width:120px">出借时间</td>
										<td style="width:120px">手续费(元)</td>
										<td style="width:130px">转让价格(元)</td>
										<td style="width:117px">状态</td>
										</tr>
										</thead>
										<tbody>
										</tbody>
										</table>
										<div class="hide_liu rowbox03_liu"></div>
										<div id="page" class="page_div pageYC"></div>
										<!-- 分页 -->

									</div>
									

								</div>
								<div class="fenge">
								<h3>温馨提示：</h3>
								<p>1、只有持有超过3个月，同时所剩期数大于1个月时，才可申请债权转让。<br>
									2、审核未通过的债权将不可再次申请债权转让。<br>
									3、债权转让后，您所使用的特权将失效，详情可参见相关协议。<br>
									4、如有疑问，请联系客服400-900-9677。</p>
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
		<%@ include file="footer.jsp"%>


	</div>
<%}catch(Exception e){
	response.sendRedirect("error.jsp");
    return;  
} %>
</body>

<script src="/js/tabmenu.js"></script>
<script>
	function passon(PaydebitOID, businessOID) {
		//alert(1);
		var imgurl = "zqzrbox_iframe.jsp?PaydebitOID=" + PaydebitOID
				+ "&businessOID=" + businessOID;
		$('.zqzrifram').attr('src', imgurl);
		//alert(imgurl);

	}
	$(function() {
		if ($(".kezhuanrang").find(".List_val").find(".loanrow.value").length < 1) {
			$(".rowbox01_liu").css("display", "block");
		}
		if ($(".zhangrangZ").next().find("td").length < 1) {
			$(".rowbox02_liu").css("display", "block");
		}
		if ($(".Yzhuanrang").find(".List_val").find(".loanrow.value").length < 1) {
			$(".rowbox03_liu").css("display", "block");
		}
		
		$.ajax({
	        type: "post",//请求方式
	        url: "/portal/mylend/zqzrList",//地址，就是json文件的请求路径
	        dataType: "json",//数据类型可以为 text xml json  script  jsonp
	        data:{
	        	currentPage:1
	        },
	        success: function(result){//返回的参数就是 action里面所有的有get和set方法的参数
	        	console.log(result);
	        //资金流水留白
	        var resultList=result.dataValue.zqzqResult;
	        if(result.dataValue.pageController.totalNumber<1){
	        	$(".rowbox01_liu").css("display","block");
	        }
	        if(result.dataValue.pageController.totalNumber<=10){
	        	$(".pageCY").remove();
	        }
	        
	        for(var i=0;i<resultList.length;i++){
	        	var str='<tr class="loanrow value"><td ><a title="'+resultList[i].xmmc+'">'+resultList[i].xmmc+'</a></td><td >'+(resultList[i].nhll).toFixed(2)+'%</td><td >'+(resultList[i].cjje).toFixed(2)+'</td><td >'+resultList[i].cjsjDate+'</td><td >'+resultList[i].dqsj+'</td><td >'+(resultList[i].ysbx).toFixed(2)+'</td><td >'+(resultList[i].ysje).toFixed(2)+'</td><td class="transfer_ht" >'+(resultList[i].dsje).toFixed(2)+'</td><td class="transfer_tates">';
	        	if(resultList[i].isAgree=='false'){
	        		str+='<a class="cd-popup-trigger5 active" onclick="passon(\''+resultList[i].paydebitOID+'\',\''+resultList[i].businessOID+'\')">申请</a>';
	        	}else if(resultList[i].isAgree=='0'){
	        		str+='<span style="color:#fc812c;width:100%;">审核中</span>';
	        	}else if(resultList[i].isAgree=='1'){
	        		str+='<span style="color:#fc812c;width:100%;">处理中</span>';
	        	}
	        	str+='</td></tr>';
	        	$(".kezhuanrang tbody").append(str);
	        }
	        $(".pageCY").paging({
				totalPage: result.dataValue.pageController.totalPage,
				totalSize: result.dataValue.pageController.totalNumber,
				callback: function(num) {
					$.ajax({
	                    type: "post",//请求方式
	                    url: "/portal/mylend/zqzrList",//地址，就是json文件的请求路径
	                    dataType: "json",//数据类型可以为 text xml json  script  jsonp
	                    data:{
	                    	currentPage:num
	                    },
	                    success: function(result){//返回的参数就是 action里面所有的有get和set方法的参数
	                    	$(".kezhuanrang tbody tr").remove();
	                    	for(var i=0;i<resultList.length;i++){
	            	        	var str='<tr class="loanrow value"><td ><a title="'+resultList[i].xmmc+'">'+resultList[i].xmmc+'</a></td><td >'+(resultList[i].nhll).toFixed(2)+'%</td><td >'+(resultList[i].cjje).toFixed(2)+'</td><td >'+resultList[i].cjsjDate+'</td><td >'+resultList[i].dqsj+'</td><td >'+(resultList[i].ysbx).toFixed(2)+'</td><td >'+(resultList[i].ysje).toFixed(2)+'</td><td class="transfer_ht" >'+(resultList[i].dsje).toFixed(2)+'</td><td class="transfer_tates">';
	            	        	if(resultList[i].isAgree=='false'){
	            	        		str+='<a class="cd-popup-trigger5 active" onclick="passon(\''+resultList[i].paydebitOID+'\',\''+resultList[i].businessOID+'\')">申请</a>';
	            	        	}else if(resultList[i].isAgree=='0'){
	            	        		str+='<span style="color:#fc812c;width:100%;">审核中</span>';
	            	        	}else if(resultList[i].isAgree=='1'){
	            	        		str+='<span style="color:#fc812c;width:100%;">处理中</span>';
	            	        	}
	            	        	str+='</td></tr>';
	            	        	$(".kezhuanrang tbody").append(str);
	            	        }
	                    }    			            	            	
	                	});
				}
			})
	        
	        
	        },
			error:function(result){
	    	}
	    });
		
		$.ajax({
	        type: "get",//请求方式
	        url: "/portal/mylend/zqzrWCList",//地址，就是json文件的请求路径
	        dataType: "json",//数据类型可以为 text xml json  script  jsonp
	        success: function(result){//返回的参数就是 action里面所有的有get和set方法的参数
	        console.log(result);
	        //资金流水留白
	        var resultList=result.dataValue.zqzqResult;
	        if(result.dataValue.pageController.totalNumber<1){
	        	$(".rowbox03_liu").css("display","block");
	        }
	        if(result.dataValue.pageController.totalNumber<=10){
	        	$(".pageYC").remove();
	        }
	        for(var i=0;i<resultList.length;i++){
	        	var str='<tr class="loanrow value"><td><a title="'+resultList[i].xmmc+'">'+resultList[i].xmmc+'</a></td><td>'+(resultList[i].nhll).toFixed(2)+'%</td><td>'+(resultList[i].cjje).toFixed(2)+'</td><td class="cjsj_date">'+resultList[i].cjsj+'</td><td>'+resultList[i].sxf+'</td><td>'+(resultList[i].zrje).toFixed(2)+'</td>';
	        	if(resultList[i].isAgree=='2'){
	        		str+='<td class="transfer_ht" style="width:117px;color:#f00;"><a style="color:#f00;">审核失败<em><img class="tishi_i" src="/image/zhaizhuan_i.png"><div class="shen_tishi"><img src="/image/zhaizhuan_shan.png">审核失败原因：<br>'+resultList[i].approvalRemark+'</div></em></a></td>';
	        	}else if(resultList[i].isAgree=='3'){
	        		str+='<td class="transfer_ht" style="width:117px"><a>已完成</a></td>';
	        	}
	        	str+='</tr>'
	        	$(".Yzhuanrang tbody").append(str);
	        }
	        $(".pageYC").paging({
				totalPage: result.dataValue.pageController.totalPage,
				totalSize: result.dataValue.pageController.totalNumber,
				callback: function(num) {
					$.ajax({
	                    type: "post",//请求方式
	                    url: "/portal/mylend/zqzrWCList",//地址，就是json文件的请求路径
	                    dataType: "json",//数据类型可以为 text xml json  script  jsonp
	                    data:{
	                    	currentPage:num
	                    },
	                    success: function(result){//返回的参数就是 action里面所有的有get和set方法的参数
	                    	console.log(result);
	                    	$(".Yzhuanrang tbody tr").remove();
	                    	for(var i=0;i<resultList.length;i++){
	            	        	var str='<tr class="loanrow value"><td><a title="'+resultList[i].xmmc+'">'+resultList[i].xmmc+'</a></td><td>'+(resultList[i].nhll).toFixed(2)+'%</td><td>'+(resultList[i].cjje).toFixed(2)+'</td><td class="cjsj_date">'+resultList[i].cjsj+'</td><td>'+resultList[i].sxf+'</td><td>'+(resultList[i].zrje).toFixed(2)+'</td>';
	            	        	if(resultList[i].isAgree=='2'){
	            	        		str+='<td class="transfer_ht" style="width:117px;color:#f00;"><a style="color:#f00;">审核失败<em><img class="tishi_i" src="/image/zhaizhuan_i.png"><div class="shen_tishi"><img src="/image/zhaizhuan_shan.png">审核失败原因：<br>'+resultList[i].approvalRemark+'</div></em></a></td>';
	            	        	}else if(resultList[i].isAgree=='3'){
	            	        		str+='<td class="transfer_ht" style="width:117px"><a>已完成</a></td>';
	            	        	}
	            	        	str+='</tr>'
	            	        	$(".Yzhuanrang tbody").append(str);
	            	        }
	                    	
	                    }    			            	            	
	                	});
				}
			})
	        
	        
	        },
			error:function(result){
	    	}
	    });
		
			$(document).on('click','.cd-popup-trigger5', function(event){
			
            event.preventDefault();
            $('.cd-popup5').addClass('is-visible1');
            //$(".dialog-addquxiao").hide()
        });
		
	})
</script>
</html>
