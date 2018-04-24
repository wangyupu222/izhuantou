<%@ page contentType="text/html; charset=UTF-8" %>
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<title>砖头网-9%加息你来我就送！！！</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<link rel="stylesheet" type="text/css" href="/css/style-common.css">
<link href="/css/normalize.css" rel="stylesheet" type="text/css">
<link href="/css/New_privileges.css?v=114" rel="stylesheet" type="text/css">
<Link rel="shortcut icon" href="/images/zhuan.ico" />
<script type="text/javascript" src="/js/jquery-1.10.1.min.js"></script>
<script>
$(function(){
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
	var navigatorName = "Microsoft Internet Explorer"; 
	if( navigator.appName == navigatorName ){ 
	$(".fireworks_wrap").css("display","none");
	}
})
</script>
<script src="/js/com.js?ver=114"></script>
</head>

<body>
<% try{ %>
<%@ include file="header.jsp" %>
<div class="addbox" style="height:79px;display:none;"></div>
<div class="fireworks_wrap" style="position: absolute;top: 120px;width: 100%;display:none;">
<div class="fireworks_icon firework1"></div>
<div class="fireworks_icon firework2"></div>
<div class="fireworks_icon firework3"></div>
<div class="fireworks_icon firework4"></div>
<div class="fireworks_icon firework5"></div>
<div class="fireworks_icon firework6"></div>
<div class="fireworks_icon firework7"></div>
<div class="fireworks_icon firework8"></div>
<div class="fireworks_icon firework9"></div>
<div class="fireworks_icon firework10"></div>
<div class="fireworks_icon firework11"></div>
<div class="fireworks_icon firework12"></div>
</div>
<div class="pricileges_wrap">
    <div class="pricileges_box01">
        <div class="register_title">新手福利</div>
        <div class="register_text">2017年6月29日 9:00起，注册砖头网，即可获得9%加息券奖励</div>
        <div class="register_liu">
            <img src="/image/New_privileges/privilege_liu.png?v=114">

        </div>
    </div>

    <div class="pricileges_box01 pricileges_box02">
        <div class="register_title">加息福利</div>
        <div class="register_text">注册成功，9%加息券奖励飞入您的口袋</div>
        <div class="welfare_wrap">
            <div class="welfare_main">
            <div class="hongbaobox01">
                <div class="hongbao01"></div>
                <p>（限投整月盈产品）</p>
            </div>
            <div class="hongbaobox01 hongbaobox02">
                <div class="hongbao01 hongbao02"></div>
                <p>（限投季皆盈产品）</p>
            </div>
            <div class="hongbaobox01 hongbaobox03">
                <div class="hongbao01 hongbao03"></div>
                <p>（限投半年盈产品）</p>
            </div>
                <div style="clear: both"></div>
            </div>

        </div>
    </div>

    <div class="pricileges_box01 pricileges_box02">
        <div class="register_title">福利入口</div>
        <div class="ticket_wrap">
            <div class="ticket_main">
            <div class="ticket_box">
                <a href='/portal/detial/Newtzdetails.jsp?OID=387a9a52f5d3f6e2370cc1e98318c0c6'>
                    <img src="/image/New_privileges/tbz_ticket.png?v=114">
                </a>
            </div>
            <div class="ticket_box">
             <a href="/portal/detial/HHtzdetails.jsp?OID=5625bf60f5d3f6e25c7551f4907a60cf">
                    <img src="/image/New_privileges/zyy_ticket.png?v=114">
             </a>  
            </div>
                <div style="clear: both"></div>
            </div>
            <div class="ticket_main">
            <div class="ticket_box">            
                <a href="/portal/detial/HHtzdetails.jsp?OID=4be8915bf5d3f6e22fde711a48a075c1">
                    <img src="/image/New_privileges/jjy_ticket.png?v=114">
                </a>
            </div>
            <div class="ticket_box">
             	<a href="/portal/detial/HHtzdetails.jsp?OID=4d6ab4c20a5dca71550b94a83a6c1e05">
                    <img src="/image/New_privileges/bny_ticket.png?v=114">
                </a>  
            </div>
                </div>
<div style="clear: both;"></div>
        </div>

    </div>

    <div class="pricileges_box01 pricileges_box02">
        <div class="register_title">活动规则</div>
        <div class="activity_rules">
            （1）  9%的加息分为3张券：2%+3%+4%；<br>
            （2）  2%的加息券仅限于整月盈1月期产品使用，加息30天；<br>
            （3）  3%的加息券仅限于季皆盈3月期产品使用，加息90天；<br>
            （4）  4%的加息券仅限于半年盈6月期产品使用，加息180天；<br>
            （5）  加息券出借即可使用；<br>
            （6）  加息券有效时间为7个自然日；<br>
            （7）  加息奖励于出借期满回款时一次性发送到您的账户中；<br>     
            （8）  本活动最终解释权归砖头网所有。
        </div>
<div class="register_btn"><a href="register.jsp">点我注册</a></div>

    </div>


</div>    

<!--bottom common-->
<%@ include file="footer.jsp" %>
<%}catch(Exception e){
	response.sendRedirect("error.jsp");
    return;  
} %>
</body>
<script>
$(function(){
	/*  头笔赚  */
	$.ajax({
        type: "POST",//请求方式
        url: "/portal/index/touBiZhuan",//地址，就是json文件的请求路径
        dataType: "json",//数据类型可以为 text xml json  script  jsonp
        success: function(result){//返回的参数就是 action里面所有的有get和set方法的参数
        	if(result.oid!=null && result.oid!=''){
        		$(".ticket_box").eq(0).find("a").attr("href","/portal/detial/Newtzdetails.jsp?OID="+result.oid);
        	}
        	
        },
		error:function(result){    	
    	}
    });
	/*  环环投  */
	$.ajax({
        type: "POST",//请求方式
        url: "/portal/page/gethhOIDAndTerm",//地址，就是json文件的请求路径
        dataType: "json",//数据类型可以为 text xml json  script  jsonp
        success: function(result){//返回的参数就是 action里面所有的有get和set方法的参数
        	console.log(result);
        	if(result.dataValue.one!=null && result.dataValue.one!=''){
        		$(".ticket_box").eq(1).find("a").attr("href","/portal/detial/HHtzdetails.jsp?OID="+result.dataValue.one);
        	}
        	if(result.dataValue.three!=null && result.dataValue.three!=''){
        		$(".ticket_box").eq(2).find("a").attr("href","/portal/detial/HHtzdetails.jsp?OID="+result.dataValue.three);
        	}
        	if(result.dataValue.six!=null && result.dataValue.six!=''){
        		$(".ticket_box").eq(3).find("a").attr("href","/portal/detial/HHtzdetails.jsp?OID="+result.dataValue.six);
        	}
        	
        },
		error:function(result){    	
    	}
    });
})
</script>
</html>