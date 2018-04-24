<%@ page contentType="text/html; charset=UTF-8" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>砖头网</title>
<link rel="stylesheet" type="text/css" href="/css/style-common.css">
<link rel="stylesheet" type="text/css" href="/css/comsty.css">
<link rel="stylesheet" type="text/css" href="/css/pedetails.css">
<link rel="stylesheet" href="/css/responsiveslides.css">
<Link rel="shortcut icon" href="/images/zhuan.ico" />
<script type="text/javascript" src="/js/jquery-1.10.1.min.js"></script>
<script src="/js/com.js"></script>
<!--<script src="/js/comjd.js"></script>-->
<script src="/js/resubmit.js"></script>
<style type="text/css">
.cd-popup-container1{ width:700px;}/*充值框大小*/
.cd-popup-container2{ width:860px;margin: 40px auto;}/*出借框大小*/
.hide_liu{  width: 100%;  height: 500px;  text-align: center;  background:#fff url("/images/xiangqing_liu.png")no-repeat center;  display: none;  }

.cd-popup-container1, .cd-popup-container2, .cd-popup-container4, .cd-popup-container5, .cd-popup-containerh{ width:875px;}
</style>
</head>
<body style="width:100%;min-width:100%; background:#fff;">
<% try{ %>

<table cellpadding="0" cellspacing="0" border="0" class="tabxq ">
                        	<tr class="tr01">
                            	<td width="20%">序号</td> 
                                <td width="20%" >出借人</td>
                                <td width="25%">出借金额</td>
                                <td width="25%">出借时间</td>
                               
                            </tr>
                            <tr class="tr02">

                            	<td></td>
                                <td></td>
                                <td>元</td>
                                <td></td>                            
                            </tr>
                        </table>
                        <div class="hide_liu HHtz_liu"></div>
                        

<%}catch(Exception e){
	response.sendRedirect("error.jsp");
    return;  
} %>
</body>
<script>


$(function(){
    if($(".tr02").length<1){
        $(".HHtz_liu").css("display","block");
    }
})

</script>
</html>
