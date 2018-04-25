<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/include/url.jsp" %>
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<title>砖头网-加薪大作战</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<link rel="stylesheet" type="text/css" href="/css/style-common.css">
<Link rel="shortcut icon" href="<%=pathUrl %>/images/zhuan.ico" />
<script type="text/javascript" src="/js/jquery-1.10.1.min.js"></script>
<script src="/js/com.js?ver=114"></script>
<style>
.footerbg {
 border-top: none;
 margin-top: 0px;
}
.p2pPunishWork img{
width:100%;
display:block;
}
</style>
</head>

<body>
<% try{ %>
<%@ include file="../header.jsp" %>
<div class="addbox" style="height:79px;display:none;"></div>
<div class="p2pPunishWork">
<img src="<%=pathUrl %>/image/P2Ppunish_ZTY/P2Ppunish_banner.jpg">
<img src="<%=pathUrl %>/image/P2Ppunish_ZTY/P2PpunishBox01.jpg">
<img src="<%=pathUrl %>/image/P2Ppunish_ZTY/P2PpunishBox02.jpg">
<img src="<%=pathUrl %>/image/P2Ppunish_ZTY/P2PpunishBox03.jpg">
<img src="<%=pathUrl %>/image/P2Ppunish_ZTY/P2PpunishBox04.jpg">
<div style="height:80px;"></div>
</div> 


<!--bottom common-->
<%@ include file="../footer.jsp" %>
<%}catch(Exception e){
	response.sendRedirect("error.jsp");
    return;  
} %>

<script>
    $(function(){
    	var u = navigator.userAgent;
        if (u.indexOf('Android') > -1 || u.indexOf('Linux') > -1) {//安卓手机
            $("body").css("min-width","1170px");
            $(".RaiseFight_box01").css("margin-top","375px");
        } else if (u.indexOf('iPhone') > -1) {//苹果手机
            $("body").css("min-width","1170px");
            $(".RaiseFight_box01").css("margin-top","375px");
        } else if (u.indexOf('Windows Phone') > -1) {//winphone手机
            $("body").css("min-width","1170px");
            $(".RaiseFight_box01").css("margin-top","375px");
        }
        
        $(window).scroll(function(){	       								
   		 if(getScrollTop()>80){
   			     $(".addbox").css("display","block");
   			}
   		else{
   			$(".addbox").css("display","none");
   			}		
   	});
   	if($(window).scrollTop() > 80){
   		$(".addbox").css("display","block");
   	}else{
   		$(".addbox").css("display","none");
   	}
    })
</script>
</body>

</html>