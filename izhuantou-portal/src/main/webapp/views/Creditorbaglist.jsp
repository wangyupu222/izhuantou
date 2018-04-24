<%@ page contentType="text/html; charset=UTF-8" %>

<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>砖头网-我的出借</title>
<link rel="stylesheet" type="text/css" href="/css/style-common.css">
<link rel="stylesheet" type="text/css" href="/css/personalcenter.css">
<link rel="stylesheet" type="text/css" href="/css/preother.css">
<Link rel="shortcut icon" href="/images/zhuan.ico" />
<script type="text/javascript" src="/js/jquery-1.10.1.min.js"></script>
<script src="/js/com.js?ver=114"></script>
<script language="javascript" src="/js/menu.js"></script>
<script src="/js/1-6-10.esl.js"></script>
<script src="/js/paging.js"></script>

<style>
.hide_liu{  width: 100%;  height: 500px;  text-align: center;  background:#fff url("images/hide_liu.jpg"); background-repeat: no-repeat; background-position:center center; display: none;  }
table.hhttable tr td{ text-align: center;border-bottom:1px solid #eeeeee;font-size:14px;}
table.hhttable tr td.ddtit a{ display:block;  line-height:20px; margin-top:5px; color:#55b0fd;}
a.transfer_ht {color:#04b277;}
.cd-popup-container1, .cd-popup-container2, .cd-popup-container4, .cd-popup-container5, .cd-popup-container6, .cd-popup-containerh{width:857px;}
.cd-popup-container1{width:900px;}
/* .commit-1 span{ width:200px;font-size:14px;  }
.commit-1 span a{color:#55b0fd;} */

.cd-popup5 .cd-popup-container1{width:500px;padding: 10px 5px;}
/* table.ddttable tr.loanrow.loanrow01 td {
    width: 15.8%;
}
table.ddttable tr.loanrow.loanrow02 td {
    width: 16.2%;
} */
table.hhttable tr.loanrow.loanrow01 td.tit02 a {
    display: block;
    line-height: 20px;
    margin-top: -20px;
    color: #55b0fd;
}
.loanrecord{
position: relative;
}
.xqdeleft{
position: relative;
}
.manbiao_ti_new{
position: absolute;
right:50px;
top:56px;
width:650px;
height:28px;
background-color:#d1d1d1;
text-align:center;
color:#fff;
border-radius: 15px;
font-size:12px;
line-height:28px;
}
.look_btn{
float:left;
margin-left: 22px;
cursor: pointer;
}
.tq_content{
height:170px;
text-align:left;
padding-left:18px;
line-height:30px;
color:#666;
overflow: hidden;
}
.tq_content_tit{
margin-top:25px;
}
.tq_content_tishi{
height:170px;
text-align:center;
color:#666;
overflow: hidden;
}
.tq_content_tishi .tishi_text{
margin-top:70px;
font-size:18px;
}

</style>
</head>
<body>
<% try{ %>
<!--弹框-->
<div class="cd-popup6">
<div class="cd-popup-container1">
<!--查看详情-->
	<div class="comnei ">
        <a class="close cd-popup-close"></a>
        <p class="commit-1"><span>查看详情</span></p>
        <iframe src="/Creditorbaglist_iframe.jsp?type=&OID=" class="zqzrifram " width="99%" height="500" id="iframepage" name="iframe" frameborder="0" scrolling="no" marginheight="0" marginwidth="0" style="margin-top:0;"></iframe>
    </div>
        
     </div>
</div>
<div class="cd-popup5">
<div class="cd-popup-container1">
<!--查看详情-->
	<div class="comnei ">
        <a class="close cd-popup-close"></a>
        <p class="commit-1"><span>查看特权</span></p>
        <div class="tq_content hide">
        <div class="tq_content_tit">特权名称：<span></span></div>
        <div class="tq_content_jxcs">收益参数：加息<span></span>%(年化收益率)</div>
        <div class="tq_content_jsts">收益天数：<span></span>天</div>
        <div class="tq_content_syts">额外收益：到期结算后获得<span></span>元</div>
        </div>
        <div class="tq_content_tishi">
        <div class="tishi_text">您本次出借未使用特权</div>
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
    	<a >当前位置</a><span>:</span><a>个人中心</a><span>&gt;</span><a >我的出借</a><span>&gt;</span><a >环环投</a>
    	<input type="hidden" id="tabInx" name="tabInx" value='<%=request.getParameter("tabInx") %>' >
        </div>
        <div class="con xqdetial">
    	<%@ include file="left_percenter.jsp" %>
        
    	<div class="xqdeleft my_loan">        
        	<div class="my_loantop huanhuan_loantop"><a href="#" class="active">持有中</a><a href="#">已完成</a><span class="L2">&nbsp;</span></div>
            <div class="clear"></div>
            <div class="manbiao_ti_new">*如点击“查看详情”无标的详细信息，表示您的资金正在预约出借中，与资产匹配后即可查看，不会影响您的收益。</div>
            <div class="loanrecord">
            	
            	<div class="loanrecord01">
                	<div>
                   <div class="tabopa loanrow_cy">
                 <table class="hhttable hhtchiyou" width="100%" algin="center" cellspacing="0" cellpadding="0">
                 <thead>
                 <tr class="loanrow tit chi_you">
                 <td style="width:194px">名称</td>
                 <td style="width:100px">出借金额(元)</td>
                 <td style="width:105px">预期年化利率</td>
                 <td style="width:90px">出借时间</td>
                 <td style="width:90px">计息时间</td>
                 <td style="width:90px">到期时间</td>
                 <td style="width:90px">下个回款日</td>
                 <td style="width:100px">应收本息</td>
                 <td style="width:88px">状态</td>
                 <td></td>
                 </tr>
                 </thead>
                 <tbody>
                 
                 </tbody>
                 </table>
                 <!--分页-->
            	<div id="page" class="page_div pageCY"></div>
                <!--分页结束-->
                 <div class="hide_liu loanrow_liu"></div>
                    </div>
                    <div class="tabopa hide loanrow_jl">
                    <!--出借记录 -->                   
                      <table class="hhttable " width="100%" algin="center" cellspacing="0" cellpadding="0">
                       <thead>
                 <tr class="loanrow loanrow01 tit jilu">
                 <td class="ddtit" style="width:250px;">名称</td><td style="width:150px;">出借金额(元)</td><td style="width:127px;">预期年化利率</td><td style="width:140px;">出借时间</td><td style="width:140px;">到期时间</td><td style="width:140px;">结算时间</td><td ></td>
                 </tr>
                 </thead>
                 <tbody>
                 
                 </tbody>
                 </table>
                 <!--分页-->
            	<div id="page" class="page_div pageYC"></div>
                <!--分页结束-->
                 <div class="hide_liu loanrow01_liu"></div>
                 
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

<%}catch(Exception e){
	response.sendRedirect("error.jsp");
    return;  
} %>
</body>

<script src="/js/tabmenu.js"></script>

<script>

$(function(){
    if($(".loanrow_cy").find(".List_val").find(".loanrow.loanrowList").length<1){
        $(".loanrow_liu").css("display","block");
    }
    if($(".loanrow_jl").find(".List_val").find(".loanrow.loanrowList").length<1){
        $(".loanrow01_liu").css("display","block");
    }
    
    $.ajax({
        type: "post",//请求方式
        url: "/portal/mylend/hhhavingList",//地址，就是json文件的请求路径
        dataType: "json",//数据类型可以为 text xml json  script  jsonp
        data:{
        	currentPage:1
        },
        success: function(result){//返回的参数就是 action里面所有的有get和set方法的参数
        	console.log(result);
        //资金流水留白
        if(result.dataValue.pageController.totalNumber<1){
        	$(".loanrow_liu").css("display","block");
        }
        if(result.dataValue.pageController.totalNumber<=10){
        	$(".pageCY").remove();
        }
        
        for(var i=0;i<result.dataValue.resultList.length;i++){
        	var str='<tr class="loanrow loanrowList"><td class="ddtit"><a href="HHtzdetails.jsp?OID='+result.dataValue.resultList[i].OID+'">'+result.dataValue.resultList[i].xmmc+'</a><div><a href="#" class="cd-popup-trigger1 hht_trigger look_btn" onclick="seedetails(\'4\',\''+result.dataValue.resultList[i].OID+'\',\''+result.dataValue.resultList[i].cashPoolOID+'\')">查看详情</a>';
        	if(result.dataValue.resultList[i].judeg!=1){
        		str+='<a class="look_btn tq_btn" onclick="seedetails_new(\'\',\'\',\'\',\'\',\'\')">查看特权</a>';
        	}else{
        	str+='<a class="look_btn tq_btn" onclick="seedetails_new(\''+(result.dataValue.resultList[i].tqsy).toFixed(2)+'\',\''+result.dataValue.resultList[i].privilegeName+'\',\''+(result.dataValue.resultList[i].privilegeRange).toFixed(2)+'\',\''+result.dataValue.resultList[i].privilegeTerm+'\',\''+result.dataValue.resultList[i].judeg+'\')">查看特权</a>';
        	}
        	str+='<div style="clear:both;"></div></div></td><td>'+result.dataValue.resultList[i].cjje+'</td><td id="nhll">'+result.dataValue.resultList[i].nhll+'%</td><td class="ddtit">'+result.dataValue.resultList[i].cjsjTime+'</td><td class="ddtit">'+result.dataValue.resultList[i].cjsjTime+'</td><td class="cjsj_date2">'+result.dataValue.resultList[i].dqsj+'</td><td class="cjsj_date">'+result.dataValue.resultList[i].xghkr+'</td><td  class="transfer_tates">'+result.dataValue.resultList[i].ysbx+'</td><td><a class="transfer_ht" >'+result.dataValue.resultList[i].hkzt+'</a></td></tr>';
        	$(".hhtchiyou tbody").append(str);
        }
        $(".pageCY").paging({
			totalPage: result.dataValue.pageController.totalPage,
			totalSize: result.dataValue.pageController.totalNumber,
			callback: function(num) {
				$.ajax({
                    type: "post",//请求方式
                    url: "/portal/mylend/hhhavingList",//地址，就是json文件的请求路径
                    dataType: "json",//数据类型可以为 text xml json  script  jsonp
                    data:{
                    	currentPage:num
                    },
                    success: function(result){//返回的参数就是 action里面所有的有get和set方法的参数
                    	console.log(result);
                    	$(".hhtchiyou tbody tr").remove();
                    	for(var i=0;i<result.dataValue.resultList.length;i++){
                    		var str='<tr class="loanrow loanrowList"><td class="ddtit"><a href="HHtzdetails.jsp?OID='+result.dataValue.resultList[i].OID+'">'+result.dataValue.resultList[i].xmmc+'</a><div><a href="#" class="cd-popup-trigger1 hht_trigger look_btn" onclick="seedetails(\'4\',\''+result.dataValue.resultList[i].OID+'\',\''+result.dataValue.resultList[i].cashPoolOID+'\')">查看详情</a>';
                        	if(result.dataValue.resultList[i].judeg!=1){
                        		str+='<a class="look_btn tq_btn" onclick="seedetails_new(\'\',\'\',\'\',\'\',\'\')">查看特权</a>';
                        	}else{
                        	str+='<a class="look_btn tq_btn" onclick="seedetails_new(\''+(result.dataValue.resultList[i].tqsy).toFixed(2)+'\',\''+result.dataValue.resultList[i].privilegeName+'\',\''+(result.dataValue.resultList[i].privilegeRange).toFixed(2)+'\',\''+result.dataValue.resultList[i].privilegeTerm+'\',\''+result.dataValue.resultList[i].judeg+'\')">查看特权</a>';
                        	}
                        	str+='<div style="clear:both;"></div></div></td><td>'+result.dataValue.resultList[i].cjje+'</td><td id="nhll">'+result.dataValue.resultList[i].nhll+'%</td><td class="ddtit">'+result.dataValue.resultList[i].cjsjTime+'</td><td class="ddtit">'+result.dataValue.resultList[i].cjsjTime+'</td><td class="cjsj_date2">'+result.dataValue.resultList[i].dqsj+'</td><td class="cjsj_date">'+result.dataValue.resultList[i].xghkr+'</td><td  class="transfer_tates">'+result.dataValue.resultList[i].ysbx+'</td><td><a class="transfer_ht" >'+result.dataValue.resultList[i].hkzt+'</a></td></tr>';
                        	$(".hhtchiyou tbody").append(str);;
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
        url: "/portal/mylend/hhWCList",//地址，就是json文件的请求路径
        dataType: "json",//数据类型可以为 text xml json  script  jsonp
        success: function(result){//返回的参数就是 action里面所有的有get和set方法的参数
        console.log(result);
        //资金流水留白
        if(result.dataValue.pageController.totalNumber<1){
        	$(".loanrow01_liu").css("display","block");
        }
        if(result.dataValue.pageController.totalNumber<=10){
        	$(".pageYC").remove();
        }
        for(var i=0;i<result.dataValue.resultList.length;i++){
        	var str='<tr class="loanrow loanrow01 loanrowList"><td class="tit02" style="width:250px;">'+result.dataValue.resultList[i].xmmc+'<a href="#" class="cd-popup-trigger1 hht_trigger" onclick="seedetails(\'4\',\''+result.dataValue.resultList[i].OID+'\',\''+result.dataValue.resultList[i].cashPoolOID+'\')">查看详情</a></td>';
        	str+='<td>'+result.dataValue.resultList[i].cjje+'</td><td id="nhll">'+result.dataValue.resultList[i].nhll+'%</td><td class="ddtit">'+result.dataValue.resultList[i].cjsjTime+'</td><td class="cjsj_date2">'+result.dataValue.resultList[i].dqsj+'</td><td>'+result.dataValue.resultList[i].timejssj+'</td></tr>';
        	$(".loanrow_jl tbody").append(str);
        }
        $(".pageYC").paging({
			totalPage: result.dataValue.pageController.totalPage,
			totalSize: result.dataValue.pageController.totalNumber,
			callback: function(num) {
				$.ajax({
                    type: "post",//请求方式
                    url: "/portal/mylend/hhWCList",//地址，就是json文件的请求路径
                    dataType: "json",//数据类型可以为 text xml json  script  jsonp
                    data:{
                    	currentPage:num
                    },
                    success: function(result){//返回的参数就是 action里面所有的有get和set方法的参数
                    	console.log(result);
                    	$(".hhtchiyou tbody tr").remove();
                    	for(var i=0;i<result.dataValue.resultList.length;i++){
                        	var str='<tr class="loanrow loanrow01 loanrowList"><td class="tit02" style="width:250px;">'+result.dataValue.resultList[i].xmmc+'<a href="#" class="cd-popup-trigger1 hht_trigger" onclick="seedetails(\'4\',\''+result.dataValue.resultList[i].OID+'\',\''+result.dataValue.resultList[i].cashPoolOID+'\')">查看详情</a></td>';
                        	str+='<td>'+result.dataValue.resultList[i].cjje+'</td><td id="nhll">'+result.dataValue.resultList[i].nhll+'%</td><td class="ddtit">'+result.dataValue.resultList[i].cjsjTime+'</td><td class="cjsj_date2">'+result.dataValue.resultList[i].dqsj+'</td><td>'+result.dataValue.resultList[i].timejssj+'</td></tr>';
                        	$(".loanrow_jl tbody").append(str);
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

function seedetails(type,OID,CashPoolOID){
	var iframeurl ="Creditorbaglist_iframe.jsp?type="+type+"&biOID="+OID+"&CashPoolOID="+CashPoolOID;
	$(".zqzrifram").attr("src",iframeurl);
	$('.hht_trigger').on('click', function(event){
        event.preventDefault();
        $('.cd-popup6').addClass('is-visible1');
        //$(".dialog-addquxiao").hide()
    });
}
function seedetails_new(money,tq_title,tq_jxcs,tq_syts,judeg){
	$('.tq_btn').on('click', function(event){
        event.preventDefault();
        $('.cd-popup5').addClass('is-visible1');
        //$(".dialog-addquxiao").hide()
    });
	 if(judeg==0){
		$(".tq_content_tishi").removeClass("hide");
		$(".tq_content").addClass("hide");
		
	}else{
		$(".tq_content_tishi").addClass("hide");
		$(".tq_content").removeClass("hide");
		$(".tq_content_tit span").text(tq_title);
		$(".tq_content_jxcs span").text(tq_jxcs);
		$(".tq_content_jsts span").text(tq_syts);
		$(".tq_content_syts span").text(money);
	} 	
}
</script>
</html>
