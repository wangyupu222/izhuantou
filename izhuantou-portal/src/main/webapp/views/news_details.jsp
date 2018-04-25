<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/include/url.jsp" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>砖头网-砖头动态</title>
<link rel="stylesheet" type="text/css" href="/css/style-common.css">
<link rel="stylesheet" type="text/css" href="/css/aboutus.css">
<Link rel="shortcut icon" href="<%=pathUrl %>/images/zhuan.ico" />
<script type="text/javascript" src="/js/jquery-1.10.1.min.js"></script>
<script src="/js/com.js"></script>
</head>
<body>
<% try{ %>
<%@ include file="header.jsp" %>

<!--中间部分开始-->
<div class="con smallnav">
    	<a >当前位置</a><span>:</span><a >关于我们</a><span>&gt;</span><a >砖头动态</a><span>&gt;</span><a>详情</a>
    </div>
<div class="con aboutcon">
<%@ include file="left_aboutus.jsp" %>
<div class="about_right">
	<div class="about_right_title">砖头动态
            <div class="shangjiantou"></div>
    </div>
	<div class="about_rightcon">
        <div class="about_rightcon">
    	<div class="subject"></div>
        <div class="fabuinfo">发布时间：</div>
    	<div class="mes"></div>
        <div class="meidvcon">
            <a href="news.jsp">&lt;&lt;返回</a>
        </div>
    </div>
    </div>
</div>
</div>

<!-----------------中间部分结束----------------> 
<%@ include file="footer.jsp" %>
<%}catch(Exception e){
	response.sendRedirect("error.jsp");
    return;  
} %>
<script>

$(function(){
	var Request = new Object();
	Request = GetRequest2();
	var sortp=Request["OID"];
	$.ajax({
        type: "post",
        url: "/portal/page/getToutaiodetails",
        dataType: "json",
        data:{
        	OID:sortp
        },
        success: function(result){
        	var item=result.dataValue;
        	$(".subject").text(item.titleName);
        	$(".fabuinfo").text(item.addDateTime);
        	$(".mes").html(item.messages);
                		        	
        },
		error:function(result){    	
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
</body>

</html>
