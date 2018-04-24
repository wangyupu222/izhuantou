<%@ page contentType="text/html; charset=UTF-8" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv=”X-UA-Compatible” content=”IE=edge″ />
<title>砖头网-环环投</title>
<link rel="stylesheet" type="text/css" href="/css/style-common.css">
<link rel="stylesheet" type="text/css" href="/css/project.css?v=115">

<link rel="stylesheet" href="/css/responsiveslides.css">
<Link rel="shortcut icon" href="/images/zhuan.ico" />
<script type="text/javascript" src="/js/jquery-1.10.1.min.js"></script>
<script src="/js/radialIndicator.js"></script>
<script src="/js/com.js"></script>
<script src="/js/paging.js"></script>
<style>
.page_div {
	margin-top: 20px;
	margin-bottom: 20px;
	font-size: 15px;
	font-family: "microsoft yahei";
	color: #666666;
	margin-right: 10px;
	padding-left: 20px;
	box-sizing: border-box;
}
/*
			 * 页数按钮样式
			 */
.page_div a {
	min-width: 30px;
	height: 28px;
	border: 1px solid #dce0e0 !important;
	text-align: center;
	margin: 0 4px;
	cursor: pointer;
	line-height: 28px;
	color: #666666;
	font-size: 13px;
	display: inline-block;
}

#firstPage, #lastPage {
	width: 50px;
	color: #0073A9;
	border: 1px solid #0073A9 !important;
}

#prePage, #nextPage {
	width: 70px;
	color: #0073A9;
	border: 1px solid #0073A9 !important;
}

.page_div .current {
	background-color: #0073A9;
	border-color: #0073A9;
	color: #FFFFFF;
}

.totalPages {
	margin: 0 10px;
}

.totalPages span, .totalSize span {
	color: #0073A9;
	margin: 0 5px;
}
</style>
</head>
<body>
<%
	try {
%>
<!--common header-->
<%@ include file="header.jsp" %>
<!--common end-->
<!--我要出借-->
<div class="main conheight">
	<div class="con projectscon">
    	<div class="projectscon_tit"><img src="/images/dual_system/hhtProject_tit.png"></div>
        
        <div class="bottomlist bottomlist01">
        	
            <div class="sylsleft">
        	<table class="tab01" cellpadding="0" cellspacing="0" >
        	<thead>
            	<tr class="tr01">
                	<td width="22.6%">名称</td>
                    <div class="sortdiv hide"><%=request.getParameter("sortp") %></div>
                    
                    <%if("nhll2desc".equals(request.getParameter("sortp"))) {%>
                    <td width="13.6%"><a href="HuanhuanProjects.jsp?sortp=nhll2asc">预期年化收益&nbsp;&nbsp;<em id="sortnhll"><img src="/images/dual_system/triangle_bottom.png"></em></a></td>
                    <%}else{ %>
                    <td width="13.6%"><a href="HuanhuanProjects.jsp?sortp=nhll2desc">预期年化收益&nbsp;&nbsp;<em id="sortnhll"><img src="/images/dual_system/triangle_top.png"></em></a></td>
                    <%} %>
                    
                    <%if("cjqx2desc".equals(request.getParameter("sortp"))) {%>
                    <td width="13.6%"><a href="HuanhuanProjects.jsp?sortp=cjqx2asc">出借期限&nbsp;&nbsp<em id="sortcjqx"><img src="/images/dual_system/triangle_bottom.png"></em></a></td>
                    <%}else{ %>
                    <td width="13.6%"><a href="HuanhuanProjects.jsp?sortp=cjqx2desc">出借期限&nbsp;&nbsp<em id="sortcjqx"><img src="/images/dual_system/triangle_top.png"></em></a></td>
                    <%} %>
                    
                    <%if("hkfs2desc".equals(request.getParameter("sortp"))) {%>
                    <td width="16.6%"><a href="HuanhuanProjects.jsp?sortp=hkfs2asc">回款方式&nbsp;&nbsp;<em id="sorthkfs"><img src="/images/dual_system/triangle_bottom.png"></em></a></td>
                    <%}else{ %>
                    <td width="16.6%"><a href="HuanhuanProjects.jsp?sortp=hkfs2desc">回款方式&nbsp;&nbsp;<em id="sorthkfs"><img src="/images/dual_system/triangle_top.png"></em></a></td>
                    <%} %>
                    
                   <%--  <%if("xmze2desc".equals(request.getParameter("sortp"))) {%>
                    <td width="16.6%"><a href="HuanhuanProjects.jsp?sortp=xmze2asc">项目总额&nbsp;&nbsp;<em id="sortxmze">↓</em></a></td>
                    <%}else{ %> --%>
                    <td width="16.6%"><a href="javascript:;" style="cursor:text;">最低出借额&nbsp;&nbsp;<em id="sortxmze"></em></a></td>
                    <%-- <%} %> --%>
                    
                    <%if("xmze2desc".equals(request.getParameter("sortp"))) {%>
                    <td class="hide" width="0%"><a href="HuanhuanProjects.jsp?sortp=xmze2asc">剩余可投&nbsp;&nbsp;<em id="sortxmze"><img src="/images/dual_system/triangle_bottom.png"></em></a></td>
                    <%}else{ %>
                    <td class="hide" width="0%"><a href="HuanhuanProjects.jsp?sortp=xmze2desc">剩余可投&nbsp;&nbsp;<em id="sortxmze"><img src="/images/dual_system/triangle_top.png"></em></a></td>
                    <%} %>                   
                    <td width="16.6%"></td>
                </tr>
                </thead>
                <tbody>
                
                </tbody>
            </table>
            <div id="page" class="page_div"></div>
             <div class="hide_liu huanhuan_liu"></div>
   <!--         <div class="hide_liu"></div> -->
            
        </div>

    </div>
</div>

<!-----------------中间部分结束----------------> 
<!--bottom common-->
<%@ include file="footer.jsp" %>
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
        url:"/portal/lend/displayHHT/1?sortp="+sortp,
        success: function(result){
        	console.log(result.data);
        	for(var i=0;i<result.data.length;i++){
        		console.log(result.data[i].OID);
        		if(result.data[i].szds==1){
        			
        		}else{
        		var str = "<tr class='tr02'>";
        		if(result.data[i].jxother==0 || result.data[i].jxother==null){
        			str+="<td class=\"tit\"><a class=\"a01\" style=\"width: auto;\" href='/portal/detial/HHtzdetails?OID="+result.data[i].OID+"'>"+result.data[i].xmmc+"</a></td>";
        		}else{
        			str+="<td class=\"tit\"><a class=\"a01\" style=\"width: auto;\" href='/portal/detial/HHtzdetails?OID="+result.data[i].OID+"'>"+result.data[i].xmmc+"</a><div class=\"Addnewsh\"></div></td>";
        		}
        		
        		if(result.data[i].jxother==0 || result.data[i].jxother==null){
        			str+="<td>"+(result.data[i].nhll).toFixed(2)+"%</td>";
        		}else{
        			str+="<td>"+(result.data[i].nhll).toFixed(2)+"%<span style=\"color:#ff6e0a; \">+"+result.data[i].jxother+"%</span></td>";
        		}
        		str+="<td>"+result.data[i].cjqx+"个月</td><td>"+result.data[i].hkfs+"</td><td>"+100+"元</td>";
        		var productStatus=result.data[i].productStatus;
        		var sykt=result.data[i].sykt;
        		if(productStatus==10){
        			if(sykt<=0){
        				str+="<td ><a class=\"lan tingbiao\"  href='/portal/detial/HHtzdetails?OID="+result.data[i].oid+"'>预约出借</a></td>";
        			}else{
        				str+="<td ><a class=\"lan tingbiao\"  href='/portal/detial/HHtzdetails?OID="+result.data[i].oid+"'>立即出借</a></td>";
        			}
        		}else{
        			if(sykt<=0){
        				str+="<td ><a class=\"lan yuyue\"  href='/portal/detial/HHtzdetails?OID="+result.data[i].oid+"'>预约出借</a></td>";
        			}else{
        				str+="<td ><a class=\"lan \"  href='/portal/detial/HHtzdetails?OID="+result.data[i].oid+"'>立即出借</a></td>";
        			}
        		}
        		str += "</tr>";
        		$(".tab01 tbody").append(str);
        		
        	}
        	}
        	if(result.totalNumber<1){
    	        $(".huanhuan_liu").css("display","block");
    	    }
        	$("#page").paging({
        		totalPage: result.totalPage,
        		totalSize: result.totalNumber,
        		callback: function(num) {
        			$.ajax({
        		        dataType:"json",
        		        url:"/portal/lend/displayHHT/"+num+"?sortp=",
        		        success: function(result){
        		        	console.log(result.data);
        		        	$(".tab01 tbody tr").remove();
        		        	for(var i=0;i<result.data.length;i++){
        		        		if(result.data[i].szds==1){
        		        			
        		        		}else{
        		        		var str = "<tr class='tr02'>";
        		        		if(result.data[i].jxother==0 || result.data[i].jxother==null){
        		        			str+="<td class=\"tit\"><a class=\"a01\" style=\"width: auto;\" href='/portal/detial/HHtzdetails?OID="+result.data[i].OID+"'>"+result.data[i].xmmc+"</a></td>";
        		        		}else{
        		        			str+="<td class=\"tit\"><a class=\"a01\" style=\"width: auto;\" href='/portal/detial/HHtzdetails?OID="+result.data[i].OID+"'>"+result.data[i].xmmc+"</a><div class=\"Addnewsh\"></div></td>";
        		        		}
        		        		
        		        		if(result.data[i].jxother==0 || result.data[i].jxother==null){
        		        			str+="<td>"+(result.data[i].nhll).toFixed(2)+"%</td>";
        		        		}else{
        		        			str+="<td>"+(result.data[i].nhll).toFixed(2)+"%<span style=\"color:#ff6e0a; \">+"+result.data[i].jxother+"%</span></td>";
        		        		}
        		        		str+="<td>"+result.data[i].cjqx+"个月</td><td>"+result.data[i].hkfs+"</td><td>"+100+"元</td>";
        		        		var productStatus=result.data[i].productStatus;
        		        		var sykt=result.data[i].sykt;
        		        		if(productStatus==10){
        		        			if(sykt<=0){
        		        				str+="<td ><a class=\"lan tingbiao\"  href='/portal/detial/HHtzdetails?OID="+result.data[i].oid+"'>预约出借</a></td>";
        		        			}else{
        		        				str+="<td ><a class=\"lan tingbiao\"  href='/portal/detial/HHtzdetails?OID="+result.data[i].oid+"'>立即出借</a></td>";
        		        			}
        		        		}else{
        		        			if(sykt<=0){
        		        				str+="<td ><a class=\"lan yuyue\"  href='/portal/detial/HHtzdetails?OID="+result.data[i].oid+"'>预约出借</a></td>";
        		        			}else{
        		        				str+="<td ><a class=\"lan \"  href='/portal/detial/HHtzdetails?OID="+result.data[i].oid+"'>立即出借</a></td>";
        		        			}
        		        		}
        		        		str += "</tr>";
        		        		$(".tab01 tbody").append(str);
        		        	}
        		        	}
        		        },
        		        error:function(result){
        		        	alert("发生错误 ");
        		        }
        		    });
        		}
        	})
        },
        error:function(result){
        	alert("发生错误 ");
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
<%}catch(Exception e){
	response.sendRedirect("error.jsp");
    return;  
} %>
</body>


</html>
