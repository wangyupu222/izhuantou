<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/include/url.jsp" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>砖头网-我的消息</title>
<link rel="stylesheet" type="text/css" href="/css/style-common.css">
<link rel="stylesheet" type="text/css" href="/css/personalcenter.css">
<link rel="stylesheet" type="text/css" href="/css/CapitalDetailed.css">
<Link rel="shortcut icon" href="<%=pathUrl %>/images/zhuan.ico" />
<script type="text/javascript" src="/js/jquery-1.10.1.min.js"></script>
<script src="/js/com.js"></script>
<script language="javascript" src="/js/menu.js"></script>
<script src="/js/paging.js"></script>
<style>
.hide_liu{  width: 100%;  height: 500px;  text-align: center;  background:#fff url("images/Message_liu.jpg")no-repeat center;  display: none;  }
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
    	<a >当前位置</a><span>:</span><a >个人中心</a><span>&gt;</span><a >用户中心</a><span>&gt;</span><a >我的消息</a>
        </div>
        <div class="con xqdetial">
	<%@ include file="left_percenter.jsp" %> 
        
    	<div class="xqdeleft my_loan">
        	<div class="my_loantop"><a href="#" class="active">我的消息</a><span class="L1">&nbsp;</span></div>
            <div class="clear"></div>
            <div>
            <div class="loanrecord01">
            <div class="optab">
                <div class="hkz">
                <div class="msgbox">
                <div class="msgbox-status"><a class="active" href="MessageNotification.jsp">未读消息</a><a href="MessageHistoryNotification.jsp" >已读消息</a></div>
                <div class="msgbox-count"><span>共0条消息,其中0条未读</span></div>
                </div>
                <div class="boxrow box-top"><span class="cell02">消息主题</span><span class="cell03">发送人</span><span class="cell04">发送时间</span><span></span></div>             
                <div id="MessageWrap">
				
				</div>
                 <div class="hide_liu Message_liu"></div>
                 <!--分页-->
                 <div id="page" class="page_div"></div>
				 <!--分页结束-->
                      
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
//资金流水留白
$(function(){
	
	$.ajax({
        dataType:"json",
        url:"/portal/personal/message",
        data:{
        	currentpage:1
        	},		
        success: function(result){
        	console.log(result);
        	for(var i=0;i<result.data.length;i++){
        		var str="<div class=\"boxrow\"><div class=\"boxrowtit\"><span class=\"cell02\">"+result.data[i].title+"</span><span class=\"cell03\">"+result.data[i].sendUser+"</span><span class=\"cell04\">"+result.data[i].sendDateTime+"</span><span><a href='/portal/personal/insert?OID="+result.data[i].OID+"' class=\"zk-a01\">我知道了</a><a class=\"zk-a\">查看</a></span></div><p>"+result.data[i].content+"</p></div>";
        		$("#MessageWrap").append(str);
        	}
        	if(result.totalNumber<1){
    	        $(".Message_liu").css("display","block");
    	    }
        	$("#page").paging({
        		totalPage: result.totalPage,
        		totalSize: result.totalNumber,
        		callback: function(num) {
        			$.ajax({
        		        dataType:"json",
        		        url:"/portal/personal/message",
        		        data:{
        		        	currentpage:num
        		        },		
        		        success: function(result){
        		        	console.log(result)
        		        	$("#MessageWrap .boxrow").remove();
        		        	for(var i=0;i<result.data.length;i++){
        		        		var str="<div class=\"boxrow\"><div class=\"boxrowtit\"><span class=\"cell02\">"+result.data[i].title+"</span><span class=\"cell03\">"+result.data[i].sendUser+"</span><span class=\"cell04\">"+result.data[i].sendDateTime+"</span><span><a href='/portal/personal/insert?OID="+result.data[i].OID+"' class=\"zk-a01\">我知道了</a><a class=\"zk-a\">查看</a></span></div><p>"+result.data[i].content+"</p></div>";
        		        		$("#MessageWrap").append(str);
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
	
	$.ajax({
        dataType:"json",
        url:"/portal/personal/num",
        success: function(result){
        		var str="共"+result.countMessageAll+"条消息,其中"+result.countMessage+"条未读";
        		$(".msgbox-count span").text(str);
        },
        error:function(result){
        	console.log("发生错误 ");
        }
    });
	
})
</script>
</html>
