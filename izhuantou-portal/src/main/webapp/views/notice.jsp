<%@ page contentType="text/html; charset=UTF-8" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>砖头网-平台公告</title>
<link rel="stylesheet" type="text/css" href="/css/style-common.css">
<link rel="stylesheet" type="text/css" href="/css/aboutus.css">
<Link rel="shortcut icon" href="/images/zhuan.ico" />
<script type="text/javascript" src="/js/jquery-1.10.1.min.js"></script>
<script src="/js/com.js"></script>
<script src="/js/paging.js"></script>
</head>
<body>
<% try{ %>
<%@ include file="header.jsp" %>

<!--中间部分开始-->
<div class="con smallnav">
    	<a >当前位置</a><span>:</span><a >关于我们</a><span>&gt;</span><a >平台公告</a>
    </div>
<div class="con aboutcon">
<%@ include file="left_aboutus.jsp" %>
<div class="about_right">
	<div class="about_right_title">平台公告
            <div class="shangjiantou"></div>
    </div>
	<div class="about_rightcon">
    
<dl class="servicelist">
<!--平台公告  -->

</dl>
<div id="page" class="page_div"></div>
</div>
</div>

<!-----------------中间部分结束----------------> 
<%@ include file="footer.jsp" %>
<%}catch(Exception e){
	response.sendRedirect("error.jsp");
    return;  
} %>
<script src="/js/TextLength.js"></script>
<script>
$(function(){
	$.ajax({
        type: "post",
        url: "/portal/page/getnotice",
        data:{currentPage:1},
        dataType: "json",
        success: function(result){
        	console.log(result.dataValue);
        	var item=result.dataValue.noticeList;
        	for(var i=0;i<item.length;i++){
        		var str="<dd><div class='newsimg'><a class='a01' href='/portal/page/notice_details?OID="+item[i].oid+"' ><img src='https://www.izhuantou.com/p2p/cn/com/hoonsoft/servlet/ServletAction?handleClassName=fileDownload&OID="+item[i].imgOID+"'></a></div><div class='newssubject'><div class='newscon'>";
        			str+="<div class='newssubject'><a href='/portal/page/notice_details?OID="+item[i].oid+"' class='a01'>"+item[i].titleName+"</a><a  class='a02'>"+item[i].addTime+"</a></div><div class='clear'></div>";
        			str+="<div class='newsmessg'><div class='newsmessgText' displayLendth='160'>"+item[i].messages+"</div><a class='seeall' href='/portal/page/notice_details?OID="+item[i].oid+"'>&nbsp;[全文]</a></div></div></dd>";
        			$(".servicelist").append(str);
        	}
        	$(".newsmessgText").each(function(){
        		$(this).displayPart();
        	})
        	
        	$("#page").paging({
        		totalPage: result.dataValue.Pagination.totalPage,
        		totalSize: result.dataValue.Pagination.totalNumber,
        		callback: function(num) {
        			$.ajax({
        		        type: "post",
        		        url: "/portal/page/getnotice",
        		        data:{currentPage:num},
        		        dataType: "json",
        		        success: function(result){
        		        	$(".servicelist dd").remove();
        		        	var item=result.dataValue.noticeList;
        		        	for(var i=0;i<item.length;i++){
        		        		var str="<dd><div class='newsimg'><a class='a01' href='/portal/page/notice_details?OID="+item[i].oid+"' ><img src='https://www.izhuantou.com/p2p/cn/com/hoonsoft/servlet/ServletAction?handleClassName=fileDownload&OID="+item[i].imgOID+"'></a></div><div class='newssubject'><div class='newscon'>";
        		        			str+="<div class='newssubject'><a href='/portal/page/notice_details?OID="+item[i].oid+"' class='a01'>"+item[i].titleName+"</a><a  class='a02'>"+item[i].addTime+"</a></div><div class='clear'></div>";
        		        			str+="<div class='newsmessg'><div class='newsmessgText' displayLendth='160'>"+item[i].messages+"</div><a class='seeall' href='/portal/page/notice_details?OID="+item[i].oid+"'>&nbsp;[全文]</a></div></div></dd>";
        		        			$(".servicelist").append(str);
        		        	}
        		        	$(".newsmessgText").each(function(){
        		        		$(this).displayPart();
        		        	})
        		                		        	
        		        },
        				error:function(result){    	
        		    	}
        		    });
        			
        		}
        	})
        	
        },
		error:function(result){    	
    	}
    });
})
</script>
</body>

</html>
