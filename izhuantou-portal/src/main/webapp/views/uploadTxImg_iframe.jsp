<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/include/url.jsp" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>砖头网</title>
<link rel="stylesheet" type="text/css" href="/css/style-common.css">
<link rel="stylesheet" type="text/css" href="/css/personalcenter.css">
<!--<link rel="stylesheet" href="css/jquery.onoff.css" media="screen" />-->

<!--日历-->
<link rel="stylesheet" href="/css/style.css">
<!--必要样式-->
<link rel="stylesheet" href="/css/fullcalendar.css">
<link rel="stylesheet" href="/css/fullcalendar.print.css" media='print'>
<!--日历-->
<script type="text/javascript" src="/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="/js/fullcalendar.min.js"></script>
<script type="text/javascript" src="/js/script.js"></script>

<script src="/js/pic.js"></script>
<script src="/js/com.js"></script>
<script src="/js/gundong.js"></script>
<style type="text/css">
*{margin:0;padding:0;list-style-type:none;}
a,img{border:0;}
/*body{font:12px/180% Arial, Helvetica, sans-serif, "新宋体";}*/
.container{width:100%;margin:0 auto;}
.changetxbtn{    position: relative;}
.changetxbtn_zhao{    position: absolute;width: 307px;height: 40px;left:50%;top:0px;margin-left:-153.5px;z-index:10;background-color: rgba(0, 0, 0, 0.48);display:none;color:#fff;background-color:#ababab;text-align:center;line-height:40px;}
</style>

</head>
<body style="width:100%;min-width:100%; background:#fff;">
<% try{ %>
<form action="/portal/personal/updHeadPic" name="FormUploadImg" id='FormUploadImg' method="post" enctype='multipart/form-data' > 
<input type="hidden" name="OID" value="<%=request.getParameter("loanOID") %>" />
 <div class="changetxbox" >
          
            	<div class="changetx01" id="preview">
                <img src="<%=pathUrl %>/images/web_head_icon.png" id="imghead" >
                </div>
                 
              	<div class="changetxt">
              	<input type="file" id="selectImg" name="file" class="picimgnow" onchange="validate_img();preview(this);"><br>
              	支持100*100px图片   图片格式为JPGE、JPG、PNG、不超过500kb</div>
        </div>
        <div class="changetxbtn">
        <div class="changetxbtn_zhao">确认</div>
        <button id="" name="" type="submit" value="确认" class="inpsub uploadok">确认</button>
        </div>
</form>
<%}catch(Exception e){
	response.sendRedirect("error.jsp");
    return;  
} %>
</body>
<script type="text/javascript"> 
function validate_img(){
	var file = document.getElementById("selectImg").value;  
	var maxsize = 1*1024*500;//500kb  
	var errMsg = "上传的附件文件不能超过500kb";  
	if(!/\.(jpg|jpeg|png|JPG|PNG)$/.test(file)){
	 alert("图片类型必须是.jpeg,jpg,png中的一种")
/* 	  $("#error").html("图片类型必须是.jpeg,jpg,png中的一种!");  */
	          $(".changetxbtn_zhao").css("display","block");
	          return false;
	}else{
		$(".changetxbtn_zhao").css("display","none");
	}	
	var obj_file = document.getElementById("selectImg");  
    filesize = obj_file.files[0].size; 
    if(filesize>maxsize){  
       alert(errMsg);
       $(".changetxbtn_zhao").css("display","block");
       return false;  
   }else{
	   $(".changetxbtn_zhao").css("display","none");
   } 
    
	        
}
function preview(file)
{
    var prevDiv = document.getElementById('preview');
    if (file.files && file.files[0])
    {
        var reader = new FileReader();
        reader.onload = function(evt){
            //prevDiv.innerHTML = '<img src="' + evt.target.result + '" />';
            var url=evt.target.result;
            //alert(url);
            $('#imghead').attr("src",url);
        }
        reader.readAsDataURL(file.files[0]);
    }

}

</script> 



</html>
