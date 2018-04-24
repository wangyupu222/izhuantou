<%@ page contentType="text/html; charset=UTF-8"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<!-- <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" /> -->
<meta name="format-detection" content="telephone=no" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<title>砖头网</title>
<Link rel="shortcut icon" href="/images/zhuan.ico" />
<link rel="stylesheet" type="text/css" href="css/style-common.css">
<script type="text/javascript" src="/js/jquery-1.10.1.min.js"></script>
<style>
body {
 width:92%; max-width:1170px; min-width:auto; margin: 0 auto;
	font-size: 14px;
	font-family: "Microsoft yahei";
}
.agree_top img{width:100%;}
.con1090 {
	/* width: 1090px; */
	margin: 0 auto;
}

.agree_top {
	margin-top: 20px;
}

.bianhao {
	text-align: right;
	margin-right: 10px;
	color: #464646;
	font: 16px/40px "Microsoft yahei";
	
}

.titltsub {
	text-align: center;
	font: 30px/100px "Microsoft yahei";
	color: #464646;
	border-bottom: 1px dashed #e5e5e5;
}

.xieyicon {
	text-indent: 2em;
	padding: 10px;
	font: 16px/28px "Microsoft yahei";
}

.xieyicon p {
	margin-top: 10px;
}

.downloadfile a {
	display: block;
	width: 220px;
	height: 52px;
	margin: 30px auto;
	font: 18px/52px "Microsoft yahei";
	background: #55b0fd;
	color: #fff;
	text-align: center;
	border-radius: 10px;
}

.downloadfile a:hover {
	background: #3291f0;
}
</style>
</head>
<body>
<% try{ %>
	<div class="main con1090">
		<div class="agree_top">
			<img src="/images/agree_03.png" />
		</div>
		<div class="titltsub">
			
		</div>
		<div class="xieyicon">
			
		</div>
	</div>
	<%}catch(Exception e){
	response.sendRedirect("error.jsp");
    return;  
} %>
</body>
<script>
$(function(){
	$.ajax({
        dataType:"json",
        url:'/portal/personal/agreement?type=<%=request.getParameter("contractType")%>',	
        success: function(result){
        	console.log(result);
        	$(".titltsub").text(result.dataValue.nameCN);
        	$(".xieyicon").html(result.dataValue.content);
        	
        },
        error:function(result){
        	console.log("发生错误 ");
        }
    });
})
</script>

</html>
