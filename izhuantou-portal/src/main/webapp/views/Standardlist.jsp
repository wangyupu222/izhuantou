<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/include/url.jsp" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>砖头网</title>
<link rel="stylesheet" type="text/css" href="/css/style-common.css">
<link rel="stylesheet" type="text/css" href="/css/personalcenter.css">
<link rel="stylesheet" type="text/css" href="/css/preother.css">
<Link rel="shortcut icon" href="<%=pathUrl %>/images/zhuan.ico" />
<script type="text/javascript" src="/js/jquery-1.10.1.min.js"></script>
<script src="/js/com.js"></script>
<script language="javascript" src="/js/menu.js"></script>
<!--<script src="/js/1-6-10.esl.js"></script>-->
<script src="/js/paging.js"></script>
<style>

.hide_liu{  width: 100%;  height: 500px;  text-align: center;  background:#fff url("images/hide_liu.jpg"); background-repeat: no-repeat; background-position:center center; display: none;  }
.cd-popup-container1, .cd-popup-container2, .cd-popup-container4, .cd-popup-container5, .cd-popup-container6, .cd-popup-containerh{width:500px;padding: 10px 5px;}
.xiyilist{text-align: left; line-height: 40px; padding: 20px;}
.xiyilist a{color:#55b0fd;}
a.transfer_ht {color:#04b277;}
table.ddttable tr.loanrow.loanrow01 td {
    width: 16.6%;
}
table.ddttable tr.loanrow.loanrow01 td a.ddt_trigger1 {
    display: block;
    line-height: 20px;
    margin-top: -20px;
    color: #55b0fd;
}
.manbiao_ti{
position: absolute;
right:50px;
top:-31px;
width:230px;
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
table.hhttable tr td{ text-align: center;border-bottom:1px solid #eeeeee;font-size:14px;}
table.hhttable tr td.ddtit a{ display:block;  line-height:20px; margin-top:5px; color:#55b0fd;}
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
table.hhttable tr.loanrow.loanrow01 td.tit02 a {
    display: block;
    line-height: 20px;
    margin-top: -20px;
    color: #55b0fd;
}
</style>
</head>
<body> 
<!--弹框-->
<div class="cd-popup6">
<div class="cd-popup-container1">
<!--充值-->
	<div class="comnei ">
        <a class="close cd-popup-close"></a>
        <p class="commit-1"><span>协议列表</span></p>
        <iframe src="AgreementList_iframe.jsp?DOID=&biOID=" width="99%" height="160" id="iframepage" name="iframe" frameborder="0" scrolling="no" marginheight="0" marginwidth="0"></iframe>
    </div>

   <div class="comnei hide">
       <a class="close cd-popup-close"></a>
       <p class="commit-1"><span>充值</span></p>
       <div class="yunull">
          	<div class="imgczcg"><img src="<%=pathUrl %>/images/czok.png" width="313" height="269"></div>
            <div class="textmes01">恭喜！充值成功</div>
            <div class="textmes02"><span>5</span>秒后自动跳转至出借页面</div>
           
        </div>
        <p class="commit-2"><span>理财有风险，投资需谨慎</span><a class="colseok" href="#">确认</a></p>
   </div>    
       <!--充值成功结束-->
       
        
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
    	<a >当前位置</a><span>:</span><a>个人中心</a><span>&gt;</span><a >我的出借</a><span>&gt;</span><a >点点投</a>
    	<input type="hidden" id="tabInx" name="tabInx" value='<%=request.getParameter("tabInx") %>' >
        </div>
        <div class="con xqdetial">
    	<%@ include file="left_percenter.jsp" %>
        
    	<div class="xqdeleft my_loan">        
        	<div class="my_loantop diandian_loantop"><a href="#" class="active">持有中</a><a href="#">已完成</a><span class="L2">&nbsp;</span></div>
            <div class="clear"></div>
            <div class="loanrecord">
            	
            	<div class="loanrecord01">
                	<div>
                    
                    <div class="tabopa chi_you" style="position: relative;">                    
                 <div class="manbiao_ti">*标的满标后，即可显示完整信息。</div>
                 <table class="hhttable hhtCY" width="100%" algin="center" cellspacing="0" cellpadding="0">
                 <thead>
                 <tr class="loanrow tit ">
                 <td style="width:194px">名称</td>
                 <td style="width:100px">出借金额(元)</td>
                 <td style="width:85px">预期年化利率</td>
                 <td style="width:92px">出借时间</td>
                 <td style="width:92px">计息时间</td>
                 <td style="width:92px">到期时间</td>
                 <td style="width:92px">下个回款日</td>
                 <td style="width:100px">应收本息</td>
                 <td style="width:100px">状态</td>
                 </tr>
                 </thead>
                 <tbody>
                 </tbody>
                 </table>
                 <div class="hide_liu loanrow_liu"></div> 
                 <!--分页-->
            	<div id="page" class="page_div pageCY"></div>
                <!--分页结束-->
                    </div>
                    
                    <div class="tabopa hide jilu">
                   	<table class="hhttable hhtWC" width="100%" algin="center" cellspacing="0" cellpadding="0">
                   	<thead>
                 <tr class="loanrow loanrow01 tit">
                 <td class="ddtit" style="width:250px;">名称</td><td style="width:150px;">出借金额(元)</td><td style="width:127px;">预期年化利率</td><td  style="width:140px;">出借时间</td><td  style="width:140px;">到期时间</td><td  style="width:140px;">最终结算时间</td><td></td>
                 </tr>
                 </thead>
                 <tbody>
                 </tbody>
                 </table>
                 <div class="hide_liu loanrow01_liu"></div> 
                   <!--分页-->
            	<div id="page" class="page_div pageYC"></div>
	               <!--分页结束-->
                    </div>
                    
                    </div>
                     <!-- <div class="nonemore">没有更多记录</div> -->
               
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


</body>

<script>
 $(function(){
    if($(".chi_you").find(".List_val").find(".loanrow.loanrowList").length<1){
        $(".loanrow_liu").css("display","block");
    }
    if($(".jilu").find(".List_val").find(".loanrow.loanrowList").length<1){
        $(".loanrow01_liu").css("display","block");
    }

    $.ajax({
        type: "post",//请求方式
        url: "/portal/mylend/ddtList",//地址，就是json文件的请求路径
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
        
        for(var i=0;i<result.dataValue.ddtList.length;i++){
        	var str='<tr class="loanrow loanrowList"><td class="ddtit"><a href="Newtzdetails.jsp?OID='+result.dataValue.ddtList[i].oid+'">'+result.dataValue.ddtList[i].xmmc+'</a><a href="#" class="cd-popup-trigger1 ddt_trigger1 look_btn" onclick="xiyieOID(\'3\',\''+result.dataValue.ddtList[i].doid+'\',\''+result.dataValue.ddtList[i].biOID+'\',\''+result.dataValue.ddtList[i].hkzt+'\',\''+result.dataValue.ddtList[i].fullscale+'\')" >查看协议</a>';
        	if(result.dataValue.ddtList[i].judeg!=1){
        		str+='<a class="look_btn tq_btn" onclick="seedetails_new(\'\',\'\',\'\',\'\',\'\')">查看特权</a>';
        	}else{
        	str+='<a class="look_btn tq_btn" onclick="seedetails_new(\''+(result.dataValue.ddtList[i].tqsy).toFixed(2)+'\',\''+result.dataValue.ddtList[i].privilegeName+'\',\''+(result.dataValue.ddtList[i].privilegeRange).toFixed(2)+'\',\''+result.dataValue.ddtList[i].privilegeTerm+'\',\''+result.dataValue.ddtList[i].judeg+'\')">查看特权</a>';
        	}
        	str+='</td><td style="width:12.2%">'+result.dataValue.ddtList[i].cjje+'</td><td id="nhll">'+result.dataValue.ddtList[i].nhll+'%</td><td class="ddtit" >'+result.dataValue.ddtList[i].cjsjTime+'</td>';
        	if(result.dataValue.ddtList[i].hkzt=='wmb'){
        		str+='<td></td><td></td><td></td><td></td><td></td>';
        	}else{
        		str+='<td>'+result.dataValue.ddtList[i].jxsj+'</td><td id="syqs">'+result.dataValue.ddtList[i].dqsj+'</td><td>'+result.dataValue.ddtList[i].xghkr+'</td><td>'+result.dataValue.ddtList[i].ysbx+'</td>';
        		if(result.dataValue.ddtList[i].hkzt=='zzhkz'){
        			str+='<td><a class="transfer_ht">正在回款中</a></td></tr>';
        		}else if(result.dataValue.ddtList[i].hkzt=='yq'){
        			str+='<td><a class="transfer_ht">逾期</a></td></tr>';
        		}
        		
        	}
        	$(".hhtCY tbody").append(str);
        }
        $(".pageCY").paging({
			totalPage: result.dataValue.pageController.totalPage,
			totalSize: result.dataValue.pageController.totalNumber,
			callback: function(num) {
				$.ajax({
                    type: "post",//请求方式
                    url: "/portal/mylend/ddtList",//地址，就是json文件的请求路径
                    dataType: "json",//数据类型可以为 text xml json  script  jsonp
                    data:{
                    	currentPage:num
                    },
                    success: function(result){//返回的参数就是 action里面所有的有get和set方法的参数
                    	console.log(result);
                    	$(".hhtCY tbody tr").remove();
                    	for(var i=0;i<result.dataValue.ddtList.length;i++){
                        	var str='<tr class="loanrow loanrowList"><td class="ddtit"><a href="Newtzdetails.jsp?OID='+result.dataValue.ddtList[i].oid+'">'+result.dataValue.ddtList[i].xmmc+'</a><a href="#" class="cd-popup-trigger1 ddt_trigger1 look_btn" onclick="xiyieOID(\'3\',\''+result.dataValue.ddtList[i].doid+'\',\''+result.dataValue.ddtList[i].biOID+'\',\''+result.dataValue.ddtList[i].hkzt+'\',\''+result.dataValue.ddtList[i].fullscale+'\')" >查看协议</a>';
                        	if(result.dataValue.ddtList[i].judeg!=1){
                        		str+='<a class="look_btn tq_btn" onclick="seedetails_new(\'\',\'\',\'\',\'\',\'\')">查看特权</a>';
                        	}else{
                        	str+='<a class="look_btn tq_btn" onclick="seedetails_new(\''+(result.dataValue.ddtList[i].tqsy).toFixed(2)+'\',\''+result.dataValue.ddtList[i].privilegeName+'\',\''+(result.dataValue.ddtList[i].privilegeRange).toFixed(2)+'\',\''+result.dataValue.ddtList[i].privilegeTerm+'\',\''+result.dataValue.ddtList[i].judeg+'\')">查看特权</a>';
                        	}
                        	str+='</td><td style="width:12.2%">'+result.dataValue.ddtList[i].cjje+'</td><td id="nhll">'+result.dataValue.ddtList[i].nhll+'%</td><td class="ddtit" >'+result.dataValue.ddtList[i].cjsjTime+'</td>';
                        	if(result.dataValue.ddtList[i].hkzt=='wmb'){
                        		str+='<td></td><td></td><td></td><td></td><td></td>';
                        	}else{
                        		str+='<td>'+result.dataValue.ddtList[i].jxsj+'</td><td id="syqs">'+result.dataValue.ddtList[i].dqsj+'</td><td>'+result.dataValue.ddtList[i].xghkr+'</td><td>'+result.dataValue.ddtList[i].ysbx+'</td>';
                        		if(result.dataValue.ddtList[i].hkzt=='zzhkz'){
                        			str+='<td><a class="transfer_ht">正在回款中</a></td></tr>';
                        		}else if(result.dataValue.ddtList[i].hkzt=='yq'){
                        			str+='<td><a class="transfer_ht">逾期</a></td></tr>';
                        		}
                        		
                        	}
                        	$(".hhtCY tbody").append(str);
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
        url: "/portal/mylend/ddtWCList",//地址，就是json文件的请求路径
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
        for(var i=0;i<result.dataValue.ddtWclist.length;i++){
        	var str='<tr class="loanrow loanrow01 loanrowList"><td class="tit02" style="width:250px;">'+result.dataValue.ddtWclist[i].xmmc+'<a href="#" class="cd-popup-trigger1 ddt_trigger1" onclick="xiyieOID(\'3\',\''+result.dataValue.ddtWclist[i].doid+'\',\''+result.dataValue.ddtWclist[i].biOID+'\',\''+result.dataValue.ddtWclist[i].hkzt+'\',\''+result.dataValue.ddtWclist[i].fullscale+'\')" >查看协议</a></td>';
        	str+='<td>'+result.dataValue.ddtWclist[i].cjje+'</td><td id="nhll">'+result.dataValue.ddtWclist[i].nhll+'%</td><td class="ddtit">'+result.dataValue.ddtWclist[i].cjsjTime+'</td><td class="cjsj_date2">'+result.dataValue.ddtWclist[i].dqsj+'</td><td>'+result.dataValue.ddtWclist[i].dqsj+'</td></tr>';
        	$(".hhtWC tbody").append(str);
        }
        $(".pageYC").paging({
			totalPage: result.dataValue.pageController.totalPage,
			totalSize: result.dataValue.pageController.totalNumber,
			callback: function(num) {
				$.ajax({
                    type: "get",//请求方式
                    url: "/portal/mylend/ddtWCList",//地址，就是json文件的请求路径
                    dataType: "json",//数据类型可以为 text xml json  script  jsonp
                    success: function(result){//返回的参数就是 action里面所有的有get和set方法的参数
                    	console.log(result);
                    	$(".hhtWC tbody tr").remove();
                    	var str='<tr class="loanrow loanrow01 loanrowList"><td class="tit02" style="width:250px;">'+result.dataValue.ddtWclist[i].xmmc+'<a href="#" class="cd-popup-trigger1 ddt_trigger1" onclick="xiyieOID(\'3\',\''+result.dataValue.ddtWclist[i].doid+'\',\''+result.dataValue.ddtWclist[i].biOID+'\',\''+result.dataValue.ddtWclist[i].hkzt+'\',\''+result.dataValue.ddtWclist[i].fullscale+'\')" >查看协议</a></td>';
                    	str+='<td>'+result.dataValue.ddtWclist[i].cjje+'</td><td id="nhll">'+result.dataValue.ddtWclist[i].nhll+'%</td><td class="ddtit">'+result.dataValue.ddtWclist[i].cjsjTime+'</td><td class="cjsj_date2">'+result.dataValue.ddtWclist[i].dqsj+'</td><td>'+result.dataValue.ddtWclist[i].dqsj+'</td></tr>';
                    	$(".hhtWC tbody").append(str);
                    }    			            	            	
                	});
			}
		})
        
        
        },
		error:function(result){
    	}
    });
    
    
}) 
//   点点投投协议打开窗口
        $(document).on('click','.ddt_trigger1',function(event){
            event.preventDefault();
            $('.cd-popup6').addClass('is-visible1');
            //$(".dialog-addquxiao").hide()
        });
function xiyieOID(type,DOID,biOID,productStatus,fullscale){
	 var iframeurl ="AgreementList_iframe.jsp?type="+type+"&DOID="+DOID+"&biOID="+biOID+"&productStatus="+productStatus+"&fullscale="+fullscale;
		$("#iframepage").attr("src",iframeurl);
 }
 function seedetails_new(money,tq_title,tq_jxcs,tq_syts,judeg){
	 $('.cd-popup5').addClass('is-visible1');
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
