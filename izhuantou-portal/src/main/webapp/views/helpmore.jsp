<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/include/url.jsp" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>砖头网-帮助中心</title>
<link rel="stylesheet" type="text/css" href="/css/style-common.css">
<link rel="stylesheet" type="text/css" href="/css/helpmore.css">
<Link rel="shortcut icon" href="<%=pathUrl %>/images/zhuan.ico" />
<script type="text/javascript" src="/js/jquery-1.10.1.min.js"></script>
<script src="/js/slides.min.jquery.js"></script>
<script src="/js/com.js"></script>
<script src="/js/paging.js"></script>

<script type="text/javascript">
		$(function(){
			$('#slides').slides({
				effect: 'slide'
			});
			
		})
		
</script>

</head>
<body>
<% try{ %>
<%@ include file="header.jsp" %>
<!--中间部分开始-->
<div class="main conheight">
	<div class="con smallnav">
    </div>
	
    <div class="con xqdetial">
    	<div class="xqderight aboutcon">
        	<div class="wdzh">
				<div class="menuDiv01"  > 
				 	<ul  class="ul-le">
                        <li class=" <% if("97df2dc73fa805780112698199e072eb".equals(request.getParameter("questionGroupOID"))  ){%>act active<%}else{ %>act<%}%>"  <% if((request.getParameter("questionGroupOID"))==null){%> id="clickjy"  <%}else{} %>><span></span><a href="/portal/page/helpmore?questionGroupOID=97df2dc73fa805780112698199e072eb" >平台问题</a></li>
                        <li class=" <% if("3dbd5e600a5dca717bc8ae1032d586c1".equals(request.getParameter("questionGroupOID"))  ){%>act active<%}else{ %>act<%}%>"  <% if((request.getParameter("questionGroupOID"))==null){%> id="clickjy"  <%}else{} %>><span></span><a href="/portal/page/helpmore?questionGroupOID=3dbd5e600a5dca717bc8ae1032d586c1" >操作问题</a></li>
                        <li class=" <% if("a25fa8653fa805780061a114a048d9ea".equals(request.getParameter("questionGroupOID"))  ){%>act active<%}else{ %>act<%}%>"  <% if((request.getParameter("questionGroupOID"))==null){%> id="clickjy"  <%}else{} %>><span></span><a href="/portal/page/helpmore?questionGroupOID=a25fa8653fa805780061a114a048d9ea" >出借计划问题</a></li>
                        <li class=" <% if("2301d6fb0a5dca710819483a5bffa1cb".equals(request.getParameter("questionGroupOID"))  ){%>act active<%}else{ %>act<%}%>"  <% if((request.getParameter("questionGroupOID"))==null){%> id="clickjy"  <%}else{} %>><span></span><a href="/portal/page/helpmore?questionGroupOID=2301d6fb0a5dca710819483a5bffa1cb" >借款问题</a></li>
                        <li class=" <% if("2305a8390a5dca715387abf84775d9e5".equals(request.getParameter("questionGroupOID"))  ){%>act active<%}else{ %>act<%}%>"  <% if((request.getParameter("questionGroupOID"))==null){%> id="clickjy"  <%}else{} %>><span></span><a href="/portal/page/helpmore?questionGroupOID=2305a8390a5dca715387abf84775d9e5" >出借问题</a></li>
                        <li class=" <% if("2306912b0a5dca716c2e006637edfa94".equals(request.getParameter("questionGroupOID"))  ){%>act active<%}else{ %>act<%}%>"  <% if((request.getParameter("questionGroupOID"))==null){%> id="clickjy"  <%}else{} %>><span></span><a href="/portal/page/helpmore?questionGroupOID=2306912b0a5dca716c2e006637edfa94" >其他常用问题</a></li>
                    </ul>		
                </div>
           </div>
        </div>
        
    	<div class="xqdeleft">
        	<div class="about-bottom">	
        	<p class="mission"><span></span></p> 
            <div class="helpWrap">
            
            </div>
            <div id="page" class="page_div"></div>        	
            </div>
      		
        <!--分页-->
  		<web:PartPage2 name="question" customProperty = "class=\"page\""  customSelectProperty="class=\"active\""/>
        <!--分页结束-->
        	<div class="clear"></div>
        </div>
        
    </div>
</div>

<!-----------------中间部分结束----------------> 
<%@ include file="footer.jsp" %>
<script src="/js/tabmenu.js"></script>
<script>
$(function(){
	var Request = new Object();
	Request = GetRequest2();
	var parentOID=Request["questionGroupOID"];
	if(parentOID==undefined){
		parentOID='97df2dc73fa805780112698199e072eb';
	}
	$.ajax({
        type: "post",//请求方式
        url: "/portal/page/gethellp",//地址，就是json文件的请求路径
        dataType: "json",//数据类型可以为 text xml json  script  jsonp
        data:{
        	parentOID:parentOID,
        	currentPage:1
        },
        success: function(result){//返回的参数就是 action里面所有的有get和set方法的参数
        	console.log(result.dataValue.ProblemsList);
        var items=result.dataValue.ProblemsList;
        var pageItems=result.dataValue.Pagination;
        for(var i=0;i<items.length;i++){
        	var str='<div class="menuDiv"><h3 class="h3 h3-1 li-jobs"><a class="a-u" href="javascript:;">'+items[i].name+'</a><a class="a-1 " href="#"></a></h3>';
        	str+='<ul><li><div class="jobs-con ">'+items[i].problemsContent+'</div></li></ul></div>';
        	$(".helpWrap").append(str);
        	
        }
        $(".menuDiv01 li").each(function(){
        	if($(this).hasClass("active")){
        		$(".mission span").text($(this).find("a").text());
        	}
        })
        if(pageItems.totalNumber<=12){
        	$("#page").remove();
        }else{
        	$("#page").paging({
        		totalPage: pageItems.totalPage,
        		totalSize: pageItems.totalNumber,
        		callback: function(num) {
        			$.ajax({
        		        type: "post",//请求方式
        		        url: "/portal/page/gethellp",//地址，就是json文件的请求路径
        		        dataType: "json",//数据类型可以为 text xml json  script  jsonp
        		        data:{
        		        	parentOID:parentOID,
        		        	currentPage:num
        		        },
        		        success: function(result){//返回的参数就是 action里面所有的有get和set方法的参数
        		        	console.log(result.dataValue);
        		        $(".helpWrap .menuDiv").remove();
        		        var items=result.dataValue.ProblemsList;
        		        for(var i=0;i<items.length;i++){
        		        	var str='<div class="menuDiv"><h3 class="h3 h3-1 li-jobs"><a class="a-u" href="javascript:;">'+items[i].name+'</a><a class="a-1 " href="#"></a></h3>';
        		        	str+='<ul><li><div class="jobs-con ">'+items[i].problemsContent+'</div></li></ul></div>';
        		        	$(".helpWrap").append(str);
        		        	
        		        }
        		        },
        				error:function(result){
        					console.log("发生错误 ");
        		    	}
        		    });
        		}
            })
        }
        
        
        },
		error:function(result){
			console.log("发生错误 ");
    	}
    });
	
	$(document).on("click",".menuDiv .li-jobs",function(){
		if($(this).hasClass("active")){
			$(this).removeClass("active");
			$(this).next("ul").hide(1000);
		}else{
			$(this).addClass("active");
			$(this).next("ul").show(1000);
			$(this).parent(".menuDiv").siblings(".menuDiv").find(".li-jobs").removeClass("active");
			$(this).parent(".menuDiv").siblings(".menuDiv").find("ul").hide(1000);
		}
		
		
	})
});
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
