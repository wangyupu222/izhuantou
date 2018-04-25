<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/include/url.jsp" %>
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<title>砖头网-手绘签名</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<link href="/css/normalize.css" rel="stylesheet" type="text/css">
<link href="/E-signatures/E-signatures.css" rel="stylesheet" type="text/css">
<Link rel="shortcut icon" href="<%=pathUrl %>/images/zhuan.ico" />
</head>

<body>
<% try{ %>
<div class="zeng_msgbox"><span class="axc-success"><span class="gtl_ico_succ"></span>采用成功<span class="gtl_end"></span></span></div>
<div class="top">
			<h3 class="sign_tit">绘制手写签名</h3>
		</div>
		<div class="draw" id="draw">
		    <div class="sig" id="sig">
		    	<canvas id="canvas" width="100" height="100">Your browser doesn't support signing</canvas>
		    </div>

            <div class="draw_btnWrap">
            <form action="" name="FormLogin"  >
                <div class="content_wrap" style="display:none;"><span id="close"><em>手绘图片的Base64:</em><textarea name="EsignaturesImg" id="sealData" readonly="readonly" style="width:200px;height:80px;"></textarea><div class="clear"></div></span></div>
                <div class="color_wrap">
					<div style="text-align: right;">
                    <span>颜色:</span>
                    <p class="color select" style="background-color: red;"></p>
                    <p class="color" style="background-color: blue;"></p>
                    <p class="color" style="background-color: black;"></p>
                	</div>
				</div>
				<div class="BtnWrap">
					<div class="clearBtn" id="clear">清除</div>
					<div class="clearBtn dataBtn" id="data">采用</div>
					<div class="clearBtn submitBtn" id="validateBtn">提交</div>
					<div class="clearBtn submitBtn" style="display:none;"><web:WidgetButton value="提交" property="submitBtn" customProperty=" class='dlbtn'" /></div>
					<div class="clear"></div>
				</div>
                <div class="clear"></div>
                </form>
            </div>

		    <!-- <p>
		        <button id="clear">清除</button>
		    </p> -->
		</div>


<script type="text/javascript" src="/E-signatures/jquery-2.1.1.js"></script>
<script type="text/javascript" src="/E-signatures/signature_pad.min.js"></script>
<script type="text/javascript" src="/E-signatures/jquery.ui.touch-punch.min.js"></script>
<script type="text/javascript">
			function changeSize(){
			    if(window.innerHeight>window.innerWidth){
			        $('#sig').css("min-height",((window.innerHeight/2)-200));
			        $("#canvas").attr({"height":((window.innerHeight/2)-200),"width":window.innerWidth});
			    }else{
			        $('#sig').css("min-height",(window.innerHeight-200));
			        $("#canvas").attr({"height":(window.innerHeight-200),"width":window.innerWidth});
			        $('html,body').animate({scrollTop:0},'slow');
			    }
			    var canvasX = $("#canvas")[0];
			    signaturePad = new SignaturePad(canvasX,{
			        dotSize : 0 ,
			        velocityFilterWeight :1,
			        minWidth: 6.0,
			        maxWidth: 7.2,
			        penColor: "rgb(255,0,0)",
			        
					backgroundColor:"rgba(0,0,0,0)"
			    });
			}
			/*if(isWeiXin()){
				  var p = document.getElementsByTagName('p');
			        p[0].innerHTML = window.navigator.userAgent;
			        $("#weixin").show();
			        $("#text").hide();
			    }else{
			        $("#weixin").hide();
			    }
			        if(window.innerWidth>window.innerHeight){
			        window.screen.lockOrientation = screen.lockOrientation ||screen.mozLockOrientation||screen.msLockOrientation||screen.webkitLockOrientation
			        window.screen.lockOrientation(["landscape-primary","landscape-secondary"]);
			    }*/
			function isWeiXin(){
			    var ua = window.navigator.userAgent.toLowerCase();
			    if(ua.match(/MicroMessenger/i) == 'micromessenger'){
			        return true;
			    }else{
			        return false;
			    }
			}
			
			//判断手机横竖屏状态：
			/* function screen_position(){
			    if(window.orientation==180||window.orientation==0){
			        $("#text").show();
			        $("#draw").hide();
			    }
			    if(window.orientation==90||window.orientation==-90){
			        alert("请在白色区域进行手绘签名。");
			        $("#text,#weixin").hide();
			        $("#draw").show();
			        $('#sig').css("min-height",(window.innerHeight-45)).signature({thickness: 10});
			        $("#canvas").css("height",(window.innerHeight-45));
			        data = $('#sig').signature('toDATA');
			    }
			}
			window.addEventListener("onorientationchange" in window ? "orientationchange" : "resize", screen_position, false);
			 */
			function CloseWebPage(){
			    if (navigator.userAgent.indexOf("MSIE") > 0) {
			        if (navigator.userAgent.indexOf("MSIE 6.0") > 0) {
			            window.opener = null;
			            window.close();
			        } else {
			            window.open('', '_top');
			            window.top.close();
			        } 
			    }
			    else if (navigator.userAgent.indexOf("Firefox") > 0) {
			        window.location.href = 'about:blank ';
			    }else {
			        window.opener = null;
			        window.open('', '_self', '');
			        window.close();
			    }
			}
		
		    $(function(){
		        changeSize();
		        $(window).resize(function(){
		            changeSize();
		        });
		        $('#clear').click(function() {
		            signaturePad.clear();
		            $("#sealData").val("");
		        });
		        $('.color').click(function() {
				    var canvasX = $("#canvas")[0];
				    signaturePad = new SignaturePad(canvasX,{
				    	dotSize : 0 ,
				        velocityFilterWeight :1,
				        minWidth: 6.0,
				        maxWidth: 7.2,
				        penColor: $(this).css("background-color"),
				        backgroundColor:"rgb(255,255,255)"
				    });
				    $('.color').removeClass("select");
				    $(this).addClass("select");
		        });
		        $('#data').click(function() {
		        	if(signaturePad.isEmpty()){
		        		alert("画布为空，请先手绘签名再提交！");
		        	}else{
		            	var canvas = document.getElementById("canvas");
						//获取图片的Base64，包含头信息data:image/png;base64,
		                var canvasData = canvas.toDataURL("image/png");
		                /* canvasData = encodeURIComponent(encodeURIComponent(canvasData));    */
						//alert(canvasData);
						//去掉图片Base64中的头信息data:image/png;base64,直接可作为sealData使用
						var image_base64= canvasData.split(",")[1];
						//alert(image_base64);
						document.getElementById("sealData").value=image_base64;
						//此处提交您的手写签名数据canvasData
						//alert("手绘签名已采用成功");
						$(".zeng_msgbox").show().delay(2500).fadeOut();
		        	}
		        });
		        
		        $("#close").click(function(){
					if(isWeiXin()){
						WeixinJSBridge.call('closeWindow');
					}else{
						CloseWebPage();
					}
		        });
		        /* 修改为提交前提示验证是否正确   */
		        $("#validateBtn").click(function(){
		        	var sealData=$("#sealData").val();
		        	if(sealData!=null && sealData!=""){
		        		var r=confirm("提交前确认签名无误？");
		        		  if (r==true)
		        		    {
		        			  $("#submitBtn").click();
		        		    }
		        	}else{
		        		alert("请采用签名后提交")
		        	}
		        })
		    	
		        
		    })
		    $().ready(function() {   
});  
		</script>

<%}catch(Exception e){
	response.sendRedirect("error.jsp");
    return;  
} %>
</body>

</html>