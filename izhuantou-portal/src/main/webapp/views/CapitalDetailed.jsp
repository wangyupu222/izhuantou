<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/include/url.jsp" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>砖头网-资金流水</title>
<link rel="stylesheet" type="text/css" href="/css/style-common.css">
<link rel="stylesheet" type="text/css" href="/css/personalcenter.css">
<link rel="stylesheet" type="text/css" href="/css/CapitalDetailed.css">
<Link rel="shortcut icon" href="<%=pathUrl %>/images/zhuan.ico" />
<script type="text/javascript" src="/js/jquery-1.10.1.min.js"></script>
<script src="/js/com.js"></script>
<script language="javascript" src="/js/menu.js"></script>
<script src="/js/paging.js"></script>
<!--日期时间-->
<script language="javascript" type="text/javascript" src="/My97DatePicker/WdatePicker.js"></script>
<style>
.hide_liu{  width: 100%;  height: 500px;  text-align: center;  background:#fff url("images/Capital_liu.jpg"); background-repeat: no-repeat; background-position:center center; display: none;  }
</style>
</head>
<body>
<% try{ %>
<!--common header-->
<%@ include file="header.jsp" %>
<!--common end-->
<div class="main minhh">
	<div class="con personcon">
    	<div class="smallnav">
    	<!-- <a >当前位置</a><span>:</span><a >个人中心</a><span>&gt;</span><a >我的资金</a><span>&gt;</span><a >资金流水</a> -->
        </div>
        <div class="con xqdetial">
    	<%@ include file="left_percenter.jsp" %>
       
    	<div class="xqdeleft my_loan">
        	<div class="my_loantop"><a href="#" class="active">资金流水</a><span class="L1">&nbsp;</span></div>
            <div class="clear"></div>
            
           <form id="FormCapitalDetailed" name="FormCapitalDetailed" action="CapitalDetailed.jsp?transactionType=<%=request.getParameter("transactionType") %>" method="post"  >            
           <input type="hidden" name="transactionType" value="<%=request.getParameter("transactionType") %>"  />
            <div class="start01 hide" ><%=request.getParameter("startDate") %></div>
            <div class="end01 hide" ><%=request.getParameter("endDate") %></div>
            <%-- <div class="zjclass"><span>资金类型</span><a href="/CapitalDetailed.jsp?transactionType=all"  class="<%if(request.getParameter("transactionType").equals("all")){ %>active <%} %>">全部</a><a href="/CapitalDetailed.jsp?transactionType=1"  class="<%if(request.getParameter("transactionType").equals("1")){ %>active<%} %>" >充值</a><a href="/CapitalDetailed.jsp?transactionType=2" class="<%if(request.getParameter("transactionType").equals("2")){ %>active<%} %>" >提现</a><a href="/CapitalDetailed.jsp?transactionType=3" class="<%if(request.getParameter("transactionType").equals("3")){ %>active<%} %>" >出借</a><a href="/CapitalDetailed.jsp?transactionType=4" class="<%if(request.getParameter("transactionType").equals("4")){ %>active<%} %>" >回款</a><a href="/CapitalDetailed.jsp?transactionType=5" class="<%if(request.getParameter("transactionType").equals("5")){ %>active<%} %>" >转让</a></div> --%>
       		<div class="timefw"><span>时间范围</span><span><input id="d5221" name="startDate" readonly="readonly"  type="text"  onFocus="var d5222=$dp.$('d5222');WdatePicker({onpicked:function(){d5222.focus();},maxDate:'#F{$dp.$D(\'d5222\')}'})"/>
至 <input id="d5222" name="endDate" readonly="readonly" type="text"  onFocus="WdatePicker({minDate:'#F{$dp.$D(\'d5221\')}'})"/> </span><span><input class="searchbtn" type="button" onclick="setFormVlues();" value="查询"></span><!-- <a href="#" onclick="">最近7天</a><a href="#">最近30天</a><a href="#">最近90天</a><a href="#">最近1年</a> -->
</div> 
			 </form>
			 
            <div class="loanrecord">
            	<div class="mytzcon">
                     <table width="90%" cellpadding="0" cellspacing="0" align="center">
                     <thead>
                     <tr class="rowbox04 tit">
                     <td width="25%">时间</td>
                     <td width="25%">类型</td>
                     <td width="25%">金额（元）</td>
                     <td width="25%">状态</td>
                     </tr>
                     </thead>
                     <tbody>
                     
                     </tbody>
                     </table>
                     <div class="hide_liu Capital_liu"></div>
                     
                     
                     
                </div>
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

<script>

$(function(){
	$.ajax({
        type: "get",//请求方式
        url: "/portal/cash/cashDetial/1?strDate=&endDate=",//地址，就是json文件的请求路径
        dataType: "json",//数据类型可以为 text xml json  script  jsonp
        success: function(result){//返回的参数就是 action里面所有的有get和set方法的参数
        console.log(result);
        //资金流水留白
        if(result.dataValue.totalNumber<1){
        	$(".Capital_liu").css("display","block");
        }
        if(result.dataValue.totalNumber<=10){
        	$("#page").remove();
        }
        for(var i=0;i<result.dataValue.data.length;i++){
        	var str='<tr class="rowbox04 value"><td>'+result.dataValue.data[i].sj+'</td><td>'+result.dataValue.data[i].type+'</td><td class="chz">'+(result.dataValue.data[i].money).toFixed(2)+'</td><td>'+result.dataValue.data[i].state+'</td></tr>';
        	$(".mytzcon tbody").append(str);
        }
        var strDate=$("#d5221").val();
    	var endDate=$("#d5222").val();
        $("#page").paging({
			totalPage: result.dataValue.totalPage,
			totalSize: result.dataValue.totalNumber,
			callback: function(num) {
				$.ajax({
                    type: "get",//请求方式
                    url: "/portal/cash/cashDetial/"+num+"?strDate="+strDate+"&endDate="+endDate,//地址，就是json文件的请求路径
                    dataType: "json",//数据类型可以为 text xml json  script  jsonp
                    success: function(result){//返回的参数就是 action里面所有的有get和set方法的参数
                    	console.log(result);
                    	$(".mytzcon tbody tr").remove();
                    	for(var i=0;i<result.dataValue.data.length;i++){
                        	var str='<tr class="rowbox04 value"><td>'+result.dataValue.data[i].sj+'</td><td>'+result.dataValue.data[i].type+'</td><td class="chz">'+(result.dataValue.data[i].money).toFixed(2)+'</td><td>'+result.dataValue.data[i].state+'</td></tr>';
                        	$(".mytzcon tbody").append(str);
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
//显示查询时间
$(function(){
	var start01 = $(".start01").text();
	var end01 = $(".end01").text();
	if(start01!="null" && end01!="null")
		{
		$("#d5221").val(start01);
		$("#d5222").val(end01);
		}else{
			$("#d5221").val();
			$("#d5222").val();
		}
    
})
//根据时间范围查询
function setFormVlues(){
	//$("#FormCapitalDetailed").submit();
	var strDate=$("#d5221").val();
	var endDate=$("#d5222").val();
	$.ajax({
        type: "get",//请求方式
        url: "/portal/cash/cashDetial/1?strDate="+strDate+"&endDate="+endDate,//地址，就是json文件的请求路径
        dataType: "json",//数据类型可以为 text xml json  script  jsonp
        success: function(result){//返回的参数就是 action里面所有的有get和set方法的参数
        console.log(result);
        //资金流水留白
        if(result.dataValue.totalNumber<1){
        	$(".Capital_liu").css("display","block");
        }
        if(result.dataValue.totalNumber<=10){
        	$("#page").addClass("hide");
        }else{
        	$("#page").removeClass("hide");
        }
        $(".mytzcon tbody tr").remove();
        for(var i=0;i<result.dataValue.data.length;i++){
        	var str='<tr class="rowbox04 value"><td>'+result.dataValue.data[i].sj+'</td><td>'+result.dataValue.data[i].type+'</td><td class="chz">'+(result.dataValue.data[i].money).toFixed(2)+'</td><td>'+result.dataValue.data[i].state+'</td></tr>';
        	$(".mytzcon tbody").append(str);
        }
        
        $("#page").paging({
			totalPage: result.dataValue.totalPage,
			totalSize: result.dataValue.totalNumber,
			callback: function(num) {
				$.ajax({
                    type: "get",//请求方式
                    url: "/portal/cash/cashDetial/"+num+"?strDate="+strDate+"&endDate="+endDate,//地址，就是json文件的请求路径
                    dataType: "json",//数据类型可以为 text xml json  script  jsonp
                    success: function(result){//返回的参数就是 action里面所有的有get和set方法的参数
                    	console.log(result);
                    	$(".mytzcon tbody tr").remove();
                    	for(var i=0;i<result.dataValue.data.length;i++){
                        	var str='<tr class="rowbox04 value"><td>'+result.dataValue.data[i].sj+'</td><td>'+result.dataValue.data[i].type+'</td><td class="chz">'+(result.dataValue.data[i].money).toFixed(2)+'</td><td>'+result.dataValue.data[i].state+'</td></tr>';
                        	$(".mytzcon tbody").append(str);
                        }
                    }    			            	            	
                	});
			}
		})
        },
		error:function(result){
    	}
    });
}
</script>
<%}catch(Exception e){
	response.sendRedirect("error.jsp");
    return;  
} %>
</body>
</html>
