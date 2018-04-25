<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/include/url.jsp" %>
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<title>砖头网-星星榜TOP20</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<link rel="stylesheet" type="text/css" href="/css/style-common.css">
<link href="/css/normalize.css" rel="stylesheet" type="text/css">
<link href="/css/ZTYWrap/FirstAnniversary.css?v=117" rel="stylesheet" type="text/css">
<Link rel="shortcut icon" href="<%=pathUrl %>/images/zhuan.ico" />
<script type="text/javascript" src="/js/jquery-1.10.1.min.js"></script>
<script src="/js/com.js?ver=114"></script>
</head>
<style>
.footerbg {
 border-top: none;
 margin-top: 0px;
}
</style>
<body>
<% try{ %>
<%@ include file="../header.jsp" %>
<div class="addbox" style="height:79px;display:none;"></div> 
<div class="AnniversaryWrap TopAnnualWrap">
<div class="TopAnnualMain">
<div class="TopAnnualBanner">
<img src="<%=pathUrl %>/image/FirstAnniversary/TOPAnnualStar/TopAnnualBanner.png">
</div>
<div class="welfareCon">
<img class="Tit01" src="<%=pathUrl %>/image/FirstAnniversary/TOPAnnualStar/TopAnnualTit.png?v=114">
<img class="TableImg01" src="<%=pathUrl %>/image/FirstAnniversary/TOPAnnualStar/TopTable.png">
</div>
</div>
    

</div>
<script>
$(function(){
	$(".TableImg01").attr('src', "<%=pathUrl %>/image/FirstAnniversary/TOPAnnualStar/TopTable.png?v="+ Math.random());
})
</script>

<!--bottom common-->
<%@ include file="../footer.jsp" %>
<%}catch(Exception e){
	response.sendRedirect("error.jsp");
    return;  
} %>
</body>

</html>