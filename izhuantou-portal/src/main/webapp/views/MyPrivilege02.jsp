<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/include/url.jsp" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>砖头网-加息券</title>
<link rel="stylesheet" type="text/css" href="/css/style-common.css">
<link rel="stylesheet" type="text/css" href="/css/personalcenter.css">
<link rel="stylesheet" type="text/css" href="/css/preother.css?v=118">
<Link rel="shortcut icon" href="<%=pathUrl %>/images/zhuan.ico" />
<script type="text/javascript" src="/js/jquery-1.10.1.min.js"></script>
<script src="/js/com.js"></script>
<script language="javascript" src="/js/menu.js"></script>
<script src="/js/paging.js"></script>
<style>
.hide_liu{  width: 100%;  height: 400px;  text-align: center;  background:#fff url("<%=pathUrl %>/images/j_brand_new_liu_new.png"); background-repeat: no-repeat; background-position:center center; display: none;  }
.j_lose_liu{background:#fff url("<%=pathUrl %>/images/j_lose_efficacy_liu_new.png");background-repeat: no-repeat; background-position:center center;}
.j_used_liu{background:#fff url("<%=pathUrl %>/images/j_used_liu_new.png");background-repeat: no-repeat; background-position:center center;}
.hbcon{min-height:665px;}
.xqdeleft{padding-bottom:0;}
#iframepage_new{
min-height:580px;
}
.privilegeName {
    margin-bottom: 0px; 
}
.hbcon {
    padding: 10px 22px;
}
/* 加息券取消分页 */
.consquan{
	height: 626px;
    overflow-y: auto;
    overflow-x: hidden;
    margin-bottom: 70px;
}
.redbag{
	min-height:590px;
}
.tithbPri a {
    cursor: pointer;
    display: block;
    float: left;
    margin-left: 10px;
    padding: 0px 10px;
}
.tithbPri a.active {
    background-color: #55b0fd;
    border-radius: 15px;
    color: #fff;
    font: 14px/30px "微软雅黑";
}
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
    	<!-- <a >当前位置</a><span>:</span><a >个人中心</a><span>&gt;</span><a >我的特权</a><span>&gt;</span><a >加息券</a> -->
        </div>
        <div class="con xqdetial">
    	<%@ include file="left_percenter.jsp" %>
        
    	<div class="xqdeleft">
        	<!-- <div class="hbtab">
            	<a class="active" href="MyPrivilege01.jsp">加息券</a>
            </div> -->
            <div class="my_loantop"><a href="#" class="active">加息券</a><span class="L1">&nbsp;</span></div>
            
            <div class="clear"></div>
            <div class="hbcon">
            	<div class="box-quan">
                	<!--<div class="tithb">获取红包</div>
                    <div class="box-left">
                    	
                        <div class="hbpic"></div>
                        <div class="hbpic"></div>
                        <div class="hbpic hbpic01">更多红包获取方式敬请期待+</div>
                        <div class="clear"></div>
                    </div>-->
                    <div class="tithbPri">
                    	<!--<span>红包状态：</span>-->
                        <a class="active">未使用</a><a>已使用</a><a>已过期</a>
                        <div class="clear"></div>
                   </div>
                   <div class="consquan">
                   		<div class="redbag brand_new">                           
                          <div class="PrivilegeWrap">
                          
                          </div>
                            <!--分页-->
            				<div id="page" class="page_div pageWSY"></div>
                			<!--分页结束-->
                            <div class="hide_liu j_brand_new_liu"></div>
                            <%-- <web:PartPage2 name="dtocListMyPrivilegeJXQCollection" customProperty = "class=\"page\""  customSelectProperty="class=\"active\""/> --%>
                            
                        </div>
                        <div class="redbag clse_efficacy brand_YSY hide">
                            <div class="PrivilegeWrap">
                          
                          </div>
                             <!--分页-->
            				<div id="page" class="page_div pageYSY"></div>
                			<!--分页结束-->
                            <div class="hide_liu j_used_liu"></div>
                        </div>
                        <div class="redbag clse_efficacy brand_YGQ hide">
                        <div class="PrivilegeWrap">
                          
                          </div>
                            <!--分页-->
            				<div id="page" class="page_div pageYGQ"></div>
                			<!--分页结束-->
                             
                            <div class="hide_liu j_lose_liu"></div>
                        </div>
                       
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
    
    $.ajax({
        dataType:"json",
        url:"/portal/mytq/canUseJxq",	
        data:{
        	currentPage:1
        },
        success: function(result){
        	console.log(result)
        	if(result.dataValue.totalNumber<1){
        		 $(".j_brand_new_liu").css("display","block");
        	}
        	if(result.dataValue.totalNumber<=9){
       		 $(".pageWSY").remove();
       		}
        	var dataItems=result.dataValue.data;
        	for(var i=0;i<dataItems.length;i++){  
        		var str='<div class="redbag01"><div class="bg01 addxi"></div><div class="bg02 addxi"></div><div class="redbagimg"><div class="imggray"><div class="textone"><div class="hbtop">';
        		str+='<div class="leftboxhb">'+(dataItems[i].privilegeRange).toFixed(2)+'<span>%</span></div><dl><dd class="privilegeName">'+dataItems[i].privilegeName+'</dd>';
                if(dataItems[i].useTerm==0){
                	str+='<dd>加息时间：同产品期限</dd>';
                }else{
                	str+='<dd>加息时间：'+dataItems[i].useTerm+'天</dd>';
                } 
                str+='<dd>起投金额：'+(dataItems[i].lowAmount).toFixed(2)+'元</dd></dl></div><div class="hbbottom">有效期'+dataItems[i].startDate+'至'+dataItems[i].endDate+'</div></div>';
                str+='<img src="<%=pathUrl %>/images/addxi_new_pulple.png" width="265" height="134"><div class="hovergray"><a href="#"><div class="hovergray_wrap"><h3>使用规则：</h3><p>'+dataItems[i].ruleIntroduce+'</p></div></a></div></div></div></div>';
                $(".brand_new .PrivilegeWrap").append(str);
        	}
        	$(".brand_new .PrivilegeWrap").append('<div style="clear:both;"></div>');
        	
        	$("#page").paging({
        		totalPage: result.dataValue.totalPage,
        		totalSize: result.dataValue.totalNumber,
        		callback: function(num) {
        			$.ajax({
        		        dataType:"json",
        		        url:"/portal/mytq/canUseJxq",
        		        data:{
        		        	currentpage:num
        		        },		
        		        success: function(result){
        		        	$(".brand_new .PrivilegeWrap .redbag01").remove();
        		        	var dataItems=result.dataValue.data;
        		        	for(var i=0;i<dataItems.length;i++){  
        		        		var str='<div class="redbag01"><div class="bg01 addxi"></div><div class="bg02 addxi"></div><div class="redbagimg"><div class="imggray"><div class="textone"><div class="hbtop">';
        		        		str+='<div class="leftboxhb">'+(dataItems[i].privilegeRange).toFixed(2)+'<span>%</span></div><dl><dd class="privilegeName">'+dataItems[i].privilegeName+'</dd>';
        		                if(dataItems[i].useTerm==0){
        		                	str+='<dd>加息时间：同产品期限</dd>';
        		                }else{
        		                	str+='<dd>加息时间：'+dataItems[i].useTerm+'天</dd>';
        		                } 
        		                str+='<dd>起投金额：'+(dataItems[i].lowAmount).toFixed(2)+'元</dd></dl></div><div class="hbbottom">有效期'+dataItems[i].startDate+'至'+dataItems[i].endDate+'</div></div>';
        		                str+='<img src="<%=pathUrl %>/images/addxi_new_pulple.png" width="265" height="134"><div class="hovergray"><a href="#"><div class="hovergray_wrap"><h3>使用规则：</h3><p>'+dataItems[i].ruleIntroduce+'</p></div></a></div></div></div></div>';
        		                $(".brand_new .PrivilegeWrap").append(str);
        		        	}
        		        	$(".brand_new .PrivilegeWrap").append('<div style="clear:both;"></div>');
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
        url:"/portal/mytq/isUseJxq",	
        data:{
        	currentPage:1
        },
        success: function(result){
        	console.log(result)
        	if(result.dataValue.totalNumber<1){
        		 $(".j_used_liu").css("display","block");
        	}
        	if(result.dataValue.totalNumber<=9){
       		 $(".pageYSY").remove();
       		}
        	var dataItems=result.dataValue.data;
        	for(var i=0;i<dataItems.length;i++){  
        		var str='<div class="redbag01 redbag03"><div class="bg01 grayaddxi"></div><div class="bg02 grayaddxi"></div><div class="redbagimg"><div class="imggray"><div class="textone"><div class="hbtop hbtopgray">';
        		str+='<div class="leftboxhb">'+(dataItems[i].privilegeRange).toFixed(2)+'<span>%</span></div><dl><dd class="privilegeName">'+dataItems[i].privilegeName+'</dd>';
                if(dataItems[i].useTerm==0){
                	str+='<dd>加息时间：同产品期限</dd>';
                }else{
                	str+='<dd>加息时间：'+dataItems[i].useTerm+'天</dd>';
                } 
                str+='<dd>起投金额：'+(dataItems[i].lowAmount).toFixed(2)+'元</dd></dl></div><div class="hbbottom">有效期'+dataItems[i].startDate+'至'+dataItems[i].endDate+'</div></div>';
                str+='<img src="<%=pathUrl %>/images/tq_used_img.png" width="265" height="134"></div></div></div>';
                $(".brand_YSY .PrivilegeWrap").append(str);
        	}
        	$(".brand_YSY .PrivilegeWrap").append('<div style="clear:both;"></div>');
        	
        	$(".pageYSY").paging({
        		totalPage: result.dataValue.totalPage,
        		totalSize: result.dataValue.totalNumber,
        		callback: function(num) {
        			$.ajax({
        		        dataType:"json",
        		        url:"/portal/mytq/isUseJxq",
        		        data:{
        		        	currentpage:num
        		        },		
        		        success: function(result){
        		        	$(".brand_YSY .PrivilegeWrap .redbag01").remove();
        		        	var dataItems=result.dataValue.data;
        		        	for(var i=0;i<dataItems.length;i++){  
        		        		var str='<div class="redbag01 redbag03"><div class="bg01 grayaddxi"></div><div class="bg02 grayaddxi"></div><div class="redbagimg"><div class="imggray"><div class="textone"><div class="hbtop hbtopgray">';
        		        		str+='<div class="leftboxhb">'+(dataItems[i].privilegeRange).toFixed(2)+'<span>%</span></div><dl><dd class="privilegeName">'+dataItems[i].privilegeName+'</dd>';
        		                if(dataItems[i].useTerm==0){
        		                	str+='<dd>加息时间：同产品期限</dd>';
        		                }else{
        		                	str+='<dd>加息时间：'+dataItems[i].useTerm+'天</dd>';
        		                } 
        		                str+='<dd>起投金额：'+(dataItems[i].lowAmount).toFixed(2)+'元</dd></dl></div><div class="hbbottom">有效期'+dataItems[i].startDate+'至'+dataItems[i].endDate+'</div></div>';
        		                str+='<img src="<%=pathUrl %>/images/tq_used_img.png" width="265" height="134"></div></div></div>';
        		                $(".brand_YSY .PrivilegeWrap").append(str);
        		        	}
        		        	$(".brand_YSY .PrivilegeWrap").append('<div style="clear:both;"></div>');
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
        url:"/portal/mytq/GqJxq",	
        data:{
        	currentPage:1
        },
        success: function(result){
        	console.log(result)
        	if(result.dataValue.totalNumber<1){
        		$(".j_lose_liu").css("display","block");
        	}
        	if(result.dataValue.totalNumber<=9){
       		 $(".pageYGQ").remove();
       		}
        	var dataItems=result.dataValue.data;
        	for(var i=0;i<dataItems.length;i++){  
        		var str='<div class="redbag01 redbag02"><div class="bg01 grayaddxi"></div><div class="bg02 grayaddxi"></div><div class="redbagimg"><div class="imggray"><div class="textone"><div class="hbtop hbtopgray">';
        		str+='<div class="leftboxhb">'+(dataItems[i].privilegeRange).toFixed(2)+'<span>%</span></div><dl><dd class="privilegeName">'+dataItems[i].privilegeName+'</dd>';
                if(dataItems[i].useTerm==0){
                	str+='<dd>加息时间：同产品期限</dd>';
                }else{
                	str+='<dd>加息时间：'+dataItems[i].useTerm+'天</dd>';
                } 
                str+='<dd>起投金额：'+(dataItems[i].lowAmount).toFixed(2)+'元</dd></dl></div><div class="hbbottom">有效期'+dataItems[i].startDate+'至'+dataItems[i].endDate+'</div></div>';
                str+='<img src="<%=pathUrl %>/images/tq_expire_img.png" width="265" height="134"></div></div></div>';
                $(".brand_YGQ .PrivilegeWrap").append(str);
        	}
        	$(".brand_YGQ .PrivilegeWrap").append('<div style="clear:both;"></div>');
        	
        	$(".pageYGQ").paging({
        		totalPage: result.dataValue.totalPage,
        		totalSize: result.dataValue.totalNumber,
        		callback: function(num) {
        			$.ajax({
        		        dataType:"json",
        		        url:"/portal/mytq/GqJxq",
        		        data:{
        		        	currentpage:num
        		        },		
        		        success: function(result){
        		        	$(".brand_YGQ .PrivilegeWrap .redbag01").remove();
        		        	var dataItems=result.dataValue.data;
        		        	for(var i=0;i<dataItems.length;i++){  
        		        		var str='<div class="redbag01 redbag02"><div class="bg01 grayaddxi"></div><div class="bg02 grayaddxi"></div><div class="redbagimg"><div class="imggray"><div class="textone"><div class="hbtop hbtopgray">';
        		        		str+='<div class="leftboxhb">'+(dataItems[i].privilegeRange).toFixed(2)+'<span>%</span></div><dl><dd class="privilegeName">'+dataItems[i].privilegeName+'</dd>';
        		                if(dataItems[i].useTerm==0){
        		                	str+='<dd>加息时间：同产品期限</dd>';
        		                }else{
        		                	str+='<dd>加息时间：'+dataItems[i].useTerm+'天</dd>';
        		                } 
        		                str+='<dd>起投金额：'+(dataItems[i].lowAmount).toFixed(2)+'元</dd></dl></div><div class="hbbottom">有效期'+dataItems[i].startDate+'至'+dataItems[i].endDate+'</div></div>';
        		                str+='<img src="<%=pathUrl %>/images/tq_expire_img.png" width="265" height="134"></div></div></div>';
        		                $(".brand_YGQ .PrivilegeWrap").append(str);
        		        	}
        		        	$(".brand_YGQ .PrivilegeWrap").append('<div style="clear:both;"></div>');
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
$('.tithbPri a').click(function(){
		$(this).addClass('active').siblings().removeClass('active');
		$('.consquan').find(".redbag").addClass("hide");
		$('.consquan').find(".redbag").eq($(this).index()).removeClass("hide");	
})
</script>
</html>
