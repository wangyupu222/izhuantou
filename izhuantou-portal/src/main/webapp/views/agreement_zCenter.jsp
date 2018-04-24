<%@ page contentType="text/html; charset=UTF-8"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>砖头网</title>
<Link rel="shortcut icon" href="/images/zhuan.ico" />
<link rel="stylesheet" type="text/css" href="css/style-common.css">
<script type="text/javascript" src="/js/pdfobject.js"></script>
<script type="text/javascript" src="/js/jquery-1.8.3.min.js"></script>
<style>
body {
padding: 20px; width:92%; min-width:auto;
	font-size: 14px;
	font-family: "Microsoft yahei";
	position: relative;
}
.agreement_wrap{
max-width: 1170px;
    margin: 0 auto;
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
	margin: 15px auto;
	font: 18px/52px "Microsoft yahei";
	background: #55b0fd;
	color: #fff;
	text-align: center;
	border-radius: 10px;
}

.downloadfile a:hover {
	background: #3291f0;
}
.pdfPic{width:100%;display:block;}
.downloadfileBtn{width:100%;background: rgba(191, 191, 191, 0.72);position: fixed;top:0px;left:0px;}
</style>
</head>
<body>
<div class="agreement_wrap">
<% try{ %>
	<div class="main con1090">
		<div class="agree_top">
			<img src="images/agree_03.png" />
		</div>
		<div class="titltsub">
			<web:WidgetTextOut name="Agreement" property="nameCN" />
		</div>
		<div class="xieyicon">
			<web:WidgetTextOut name="Agreement" property="content" />
		</div>
		 <div class="downloadfile hide">
			<a href="#">下载PDF文件</a>
		</div>
	</div>
	<%}catch(Exception e){
	response.sendRedirect("error.jsp");
    return;  
} %>
</div>
</body>
<script>
$(function(){
	$.ajax({
        type: "post",//请求方式
        url: "/portal/personal/memberAgreement?type=<%=request.getParameter("type")%>&biOID=<%=request.getParameter("biOID")%>",//地址，就是json文件的请求路径
        dataType: "json",//数据类型可以为 text xml json  script  jsonp
        data:{
        	DOID:<%=request.getParameter("dOID")%>,
        	biOID:'<%=request.getParameter("biOID")%>',
        	type:<%=request.getParameter("type")%>,
        	CashPoolOID:'<%=request.getParameter("CashPoolOID")%>',
        	loanNumber:'<%=request.getParameter("loanNumber")%>'
        },
        success: function(result){//返回的参数就是 action里面所有的有get和set方法的参数
        	console.log(result);
       
        },
		error:function(result){
    	}
    });
})
</script>
</html>
