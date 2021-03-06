<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/include/url.jsp" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv=”X-UA-Compatible” content=”IE=edge″ />
<title>砖头网-点点投</title>
<link rel="stylesheet" type="text/css" href="/css/style-common.css">
<link rel="stylesheet" type="text/css" href="/css/project.css">
<Link rel="shortcut icon" href="<%=pathUrl %>/images/zhuan.ico" />
<link rel="stylesheet" href="/css/responsiveslides.css">

<script type="text/javascript" src="/js/jquery-1.10.1.min.js"></script>
<script src="/js/radialIndicator.js"></script>
<script src="/js/com.js"></script>
<script src="/js/paging.js"></script>
</head>
<body>
<% try{ %>
<!--common header-->
<%@ include file="header.jsp" %>
<!--common end-->



<!--我要出借-->
<div class="main conheight">
	<div class="con projectscon">
    	<div class="projectscon_tit"><img src="<%=pathUrl %>/images/dual_system/ddtProject_tit.png"></div>
        <div class="bottomlist bottomlist01">
        	
            <div class="sylsleft">
        	<table class="tab01" id="tblSort" sortCol="-1" cellpadding="0" cellspacing="0" >
        	<thead>
            	<tr class="tr01">
                	<td width="20.6%">名称</td>
                    <div class="sortdiv hide"><%=request.getParameter("sortp") %></div>
                    
                    <%if("nhll2desc".equals(request.getParameter("sortp"))) {%>
                    <td width="10.3%"><a href="DiandianProjects.jsp?sortp=nhll2asc">预期年化收益&nbsp;&nbsp;<em id="sortnhll"><img src="<%=pathUrl %>/images/dual_system/triangle_bottom.png"></em></a></td>
                    <%}else{ %>
                    <td width="10.3%"><a href="DiandianProjects.jsp?sortp=nhll2desc">预期年化收益&nbsp;&nbsp;<em id="sortnhll"><img src="<%=pathUrl %>/images/dual_system/triangle_top.png"></em></a></td>
                    <%} %>
                    
                    <%if("cjqx2desc".equals(request.getParameter("sortp"))) {%>
                    <td width="10.3%"><a href="DiandianProjects.jsp?sortp=cjqx2asc">出借期限&nbsp;&nbsp<em id="sortcjqx"><img src="<%=pathUrl %>/images/dual_system/triangle_bottom.png"></em></a></td>
                    <%}else{ %>
                    <td width="10.3%"><a href="DiandianProjects.jsp?sortp=cjqx2desc">出借期限&nbsp;&nbsp<em id="sortcjqx"><img src="<%=pathUrl %>/images/dual_system/triangle_top.png"></em></a></td>
                    <%} %>
                    
                    <%if("hkfs2desc".equals(request.getParameter("sortp"))) {%>
                    <td width="12.3%"><a href="DiandianProjects.jsp?sortp=hkfs2asc">回款方式&nbsp;&nbsp;<em id="sorthkfs"><img src="<%=pathUrl %>/images/dual_system/triangle_bottom.png"></em></a></td>
                    <%}else{ %>
                    <td width="12.3%"><a href="DiandianProjects.jsp?sortp=hkfs2desc">回款方式&nbsp;&nbsp;<em id="sorthkfs"><img src="<%=pathUrl %>/images/dual_system/triangle_top.png"></em></a></td>
                    <%} %>
                    
                    <%if("xmze2desc".equals(request.getParameter("sortp"))) {%>
                    <td width="12.3%"><a href="DiandianProjects.jsp?sortp=xmze2asc">标的金额&nbsp;&nbsp;<em id="sortxmze"><img src="<%=pathUrl %>/images/dual_system/triangle_bottom.png"></em></a></td>
                    <%}else{ %> 
                    <td width="12.3%"><a href="DiandianProjects.jsp?sortp=xmze2desc">标的金额&nbsp;&nbsp;<em id="sortxmze"><img src="<%=pathUrl %>/images/dual_system/triangle_top.png"></em></a></td>
                     <%} %> 
                     
                     <%if("sykt2desc".equals(request.getParameter("sortp"))) {%>
                    <td width="12.3%"><a href="DiandianProjects.jsp?sortp=sykt2asc">剩余金额&nbsp;&nbsp;<em id="syktxmze"><img src="<%=pathUrl %>/images/dual_system/triangle_bottom.png"></em></a></td>
                    <%}else{ %> 
                    <td width="12.3%"><a href="DiandianProjects.jsp?sortp=sykt2desc">剩余金额&nbsp;&nbsp;<em id="syktxmze"><img src="<%=pathUrl %>/images/dual_system/triangle_top.png"></em></a></td>
                     <%} %> 
                    <td width="8.3%"><a href="javascript:;" style="cursor:text;">进度&nbsp;&nbsp;<em id="sortxmze"></em></a></td>
                    <td class="hide">tblSort</td>
                    <td width="13.3%"></td>
                </tr>
               </thead>
               <tbody>
                
                </tbody>
            </table>
            <div id="page" class="page_div"></div>
             <div class="hide_liu diandian_liu"></div>
            
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
<%-- <script type="text/javascript" src="js/sort.js"></script> --%>
<script>
$('#indicatorContainer5').radialIndicator({
        barColor: '#4fa6fd',
        barWidth: 4,
        initValue: 90,
        roundCorner: true,
        percentage: true,
		
    });
$('#indicatorContainer4').radialIndicator({
        barColor: '#4fa6fd',
        barWidth: 4,
        initValue: 100,
        roundCorner: true,
        percentage: true,
		
    });
$('#indicatorContainer3').radialIndicator({
        barColor: '#4fa6fd',
        barWidth: 4,
        initValue: 100,
        roundCorner: true,
        percentage: true,
		
    });
	
$('#indicatorContainer2').radialIndicator({
        barColor: '#4fa6fd',
        barWidth: 4,
        initValue: 90,
        roundCorner: true,
        percentage: true,
		
    });
	$('#indicatorContainer1').radialIndicator({
        barColor: '#4fa6fd',
        barWidth: 4,
        initValue: 90,
        roundCorner: true,
        percentage: true,
		
    });
	$('#indicatorContainer6').radialIndicator({
        barColor: '#4fa6fd',
        barWidth: 4,
        initValue: 90,
        roundCorner: true,
        percentage: true,
		
    });
$('#indicatorContainer7').radialIndicator({
        barColor: '#4fa6fd',
        barWidth: 4,
        initValue: 90,
        roundCorner: true,
        percentage: true,
		
    });	

$(function(){
    var oHeight=$(window).height();
	$(".projectscon").css({"min-height":oHeight-450});
})
$(window).resize(function(){
	var oHeight=$(window).height();
	$(".projectscon").css({"min-height":oHeight-450});
});
</script>

<script>
$(function(){
	var Request = new Object();
	Request = GetRequest2();
	var sortp=Request["sortp"];
	if(sortp==undefined){
		sortp='';
	}
	$.ajax({
        dataType:"json",
        url:"/portal/lend/displayDDT/1?sortp="+sortp,
        success: function(result){
        	var result=result.dataValue;
        	for(var i=0;i<result.data.length;i++){
        		console.log(result.data[i].sykt);
        		if(result.data[i].szds==1){
        			
        		}else{
        		var str = "<tr class='tr02'>";
        		str+="<td ><a class=\"a01\" href='Newtzdetails.jsp?OID='"+result.data[i].OID+">"+result.data[i].xmmc+"</a></td><td>"+(result.data[i].nhll).toFixed(2)+"%</td>";
        		str+="<td>"+result.data[i].cjqx+"个月</td><td>"+result.data[i].hkfs+"</td><td>"+result.data[i].xmze+"</td><td>"+(result.data[i].sykt).toFixed(2)+"</td><td>"+(result.data[i].jd).toFixed(2)+"%</td>";
        		var productStatus=result.data[i].productStatus;
        		if(productStatus==10){
        			str+="<td ><a class=\"lan manbiao\" href='Newtzdetails.jsp?OID="+result.data[i].OID+"'>立即出借</a></td>";
        		}else if(productStatus==2 || productStatus==3 || productStatus==5){
        			str+="<td ><a class=\"lan manbiao\" href='Newtzdetails.jsp?OID="+result.data[i].OID+"'>已满标</a></td>";
        		}else{
        			str+="<td ><a class=\"lan\" href='Newtzdetails.jsp?OID="+result.data[i].OID+"'>立即出借</a></td>";
        		}
        		str += "</tr>";
        		$(".tab01 tbody").append(str);
        		
        	}
        	}
        	if(result.totalNumber<1){
    	        $(".diandian_liu").css("display","block");
    	    }
        	if(result.totalNumber<10){
    	    	$("#page").remove();
    	    }
        	$("#page").paging({
        		totalPage: result.totalPage,
        		totalSize: result.totalNumber,
        		callback: function(num) {
        			$.ajax({
        		        dataType:"json",
        		        url:"/portal/lend/displayDDT/"+num+"?sortp=",
        		        success: function(result){
        		        	var result=result.dataValue;
        		        	$(".tab01 tbody tr").remove();
        		        	for(var i=0;i<result.data.length;i++){
        		        		console.log(result.data[i].OID);
        		        		if(result.data[i].szds==1){
        		        			
        		        		}else{
        		        		var str = "<tr class='tr02'>";
        		        		str+="<td ><a class=\"a01\" href='Newtzdetails.jsp?OID='"+result.data[i].OID+">"+result.data[i].xmmc+"</a></td><td>"+(result.data[i].nhll).toFixed(2)+"%</td>";
        		        		str+="<td>"+result.data[i].cjqx+"个月</td><td>"+result.data[i].hkfs+"</td><td>"+result.data[i].xmze+"</td><td>"+(result.data[i].sykt).toFixed(2)+"</td><td>"+(result.data[i].jd).toFixed(2)+"%</td>";
        		        		var productStatus=result.data[i].productStatus;
        		        		if(productStatus==10){
        		        			str+="<td ><a class=\"lan manbiao\" href='Newtzdetails.jsp?OID="+result.data[i].OID+"'>立即出借</a></td>";
        		        		}else if(productStatus==2 || productStatus==3 || productStatus==5){
        		        			str+="<td ><a class=\"lan manbiao\" href='Newtzdetails.jsp?OID="+result.data[i].OID+"'>已满标</a></td>";
        		        		}else{
        		        			str+="<td ><a class=\"lan\" href='Newtzdetails.jsp?OID="+result.data[i].OID+"'>立即出借</a></td>";
        		        		}
        		        		str += "</tr>";
        		        		$(".tab01 tbody").append(str);
        		        		
        		        	}
        		        	}
        		        },
        		        error:function(result){
        		        	console.log("发生错误 ");
        		        }
        		    });
        		}
        	})
        },
        error:function(result){
        	console.log("发生错误 ");
        }
    });
	
	
	    
})

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
