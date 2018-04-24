<%@ page contentType="text/html; charset=UTF-8" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>星星榜Top20</title>
<link href="/css/normalize.css" type="text/css" rel="stylesheet">

    <style>
        .wrap img{width:100%;display:block;}
        .bottom{position:relative;}
        .bottom p{position:absolute;top:9%;right:6%;font-size:14px;color:#fbe6e1;}
    </style>
</head> 
<body> 
<div class="wrap">
<img src="/image/FirstAnniversary/TOPAnnualStar/TopAnnualAppImg01.png?v=114">
<img id="tableImg" src="/image/FirstAnniversary/TOPAnnualStar/TopAnnualAppImg02.png?v=114">
<div class="bottom">
<img src="/image/FirstAnniversary/TOPAnnualStar/TopAnnualAppImg03.png">
</div>

</div>
<script type="text/javascript" src="/js/jquery-1.10.1.min.js"></script>
<script>
$(function(){
	$("#tableImg").attr('src', "/image/FirstAnniversary/TOPAnnualStar/TopAnnualAppImg02.png?v="+ Math.random());
})
</script>
</body> 
</html>