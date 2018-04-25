<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/include/url.jsp" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>砖头网</title>
<link rel="stylesheet" type="text/css" href="/css/style-common.css">
<link rel="stylesheet" type="text/css" href="/css/personalcenter.css">
<link rel="stylesheet" type="text/css" href="/css/preother.css">
<Link rel="shortcut icon" href="<%=pathUrl %>/images/zhuan.ico" />
<script type="text/javascript" src="/js/jquery-1.10.1.min.js"></script>
<script src="/js/com.js"></script>
<script language="javascript" src="/js/menu.js"></script>
<!--<script src="/js/1-6-10.esl.js"></script>-->
<style>
.hide_liu{  width: 100%;  height: 500px;  text-align: center;  background:#fff url("images/hide_liu.jpg"); background-repeat: no-repeat; background-position:center center; display: none;  }
.cd-popup-container1, .cd-popup-container2, .cd-popup-container4, .cd-popup-container5, .cd-popup-container6, .cd-popup-containerh{width:500px;padding: 10px 5px;}
.xiyilist{text-align: left; line-height: 40px; padding: 20px;}
.xiyilist a{color:#55b0fd;}
a.transfer_ht {color:#04b277;}
table.ddttable tr.loanrow.loanrow01 td {
    width: 16.6%;
}
table.ddttable tr.loanrow.loanrow01 td a.cd-popup-trigger1 {
    display: block;
    line-height: 20px;
    margin-top: -20px;
    color: #55b0fd;
}
</style>
</head>
<body>
<% try{ %> 
<!--弹框-->
<div class="cd-popup1">
<div class="cd-popup-container1">
<!--协议内容-->
	<div class="comnei ">
        <a class="close cd-popup-close"></a>
        <p class="commit-1"><span>协议列表</span></p>
        <iframe src="AgreementList_iframe.jsp?DOID=&biOID=" width="99%" height="160" id="iframepage" name="iframe" frameborder="0" scrolling="no" marginheight="0" marginwidth="0"></iframe>
        <!-- <div class="agreementc">
          <div class="downloadagree"><a href="#">下载</a></div>
          <div class="ag_content">
          协议内容测试协议内容测试协议内容测试协议内容测试协议内容测试协议内容测试协议内容测试协议内容测试协议内容测试协议内容测试协议内容测试协议内容测试协议内容测试协议内容测试协议内容测试协议内容测试协议内容测试协议内容测试协议内容测试协议内容测试协议内容测试协议内容测试协议内容测试协议内容测试协议内容测试协议内容测试协议内容测试协议内容测试协议内容测试协议内容测试协议内容测试协议内容测试协议内容测试协议内容测试协议内容测试协议内容测试协议内容测试协议内容测试协议内容测试协议内容测试协议内容测试协议内容测试协议内容测试协议内容测试协议内容测试
          
          </div>
           <input type="button" class="inpsub colseok" value="确认"  />
        </div> --> 
    </div>
       <!--协议内容结束-->
       
        
     </div>
</div>

<!--common header-->
<%@ include file="header.jsp" %>
<!--common end-->
<div class="main minhh">
	<div class="con personcon">
    	<div class="smallnav">
    	<a >当前位置</a><span>:</span><a>个人中心</a><span>&gt;</span><a >我的出借</a><span>&gt;</span><a >转转投</a>
        </div>
        <div class="con xqdetial">
    	<%@ include file="left_percenter.jsp" %>
        
    	<div class="xqdeleft my_loan">        
        	<div class="my_loantop"><a href="#" class="active">持有中</a><a href="#">已完成</a><span class="L2">&nbsp;</span></div>
            <div class="clear"></div>
            <div class="loanrecord">
            	
            	<div class="loanrecord01">
                	<div>
                    
                    <div class="tabopa">
                
                <table class="ddttable" >
                <thead>
                 <tr class="loanrow tit chi_you">
                 <td style="width:18.2%">名称</td>
                 <td style="width:12.2%">出借金额(元)</td>
                 <td style="width:9.0%">预期年化利率</td>
                 <td style="width:10.2%">出借时间</td>
                 <td style="width:10.2%">计息时间</td>
                 <td style="width:10.2%">到期时间</td>
                 <td style="width:10.0%">下个回款日</td>
                 <td style="width:11.0%">应收本息</td>
                 <td style="width:9.0%">状态</td>
                 </tr>
                 </thead>
                 <tbody>
                 </tbody>
                 </table>
                 <div class="hide_liu loanrow_liu"></div> 
                 
                
                
                    </div>
                    <div class="tabopa hide">
                  
                        <table class="ddttable" >
                        <thead>
                 <tr class="loanrow loanrow01 tit jilu">
                 <td class="ddtit">名称</td><td>出借金额(元)</td><td>预期年化利率</td><td>出借时间</td><td>到期时间</td><td>最终结算时间</td>
                 </tr>
                 </thead>
                 <tbody>
                 </tbody>
                 
                 </table>
                    
                    
                    <div class="hide_liu loanrow01_liu"></div>
                    <!--分页-->
               
                <!--分页结束-->
                    </div>
                    
                     </div>
                     <!-- <div class="nonemore">没有更多记录</div> -->
                
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

<script>
$(function(){
    if($(".chi_you").next().find(".ddtit").length<1){
        $(".loanrow_liu").css("display","block");
    }
    if($(".jilu").next().find("td").length<1){
        $(".loanrow01_liu").css("display","block");
    }
 //   alert($(".chi_you").next().find(".ddtit").length);
})
function xiyieOID(type,DOID,biOID,productStatus){
	 var iframeurl ="AgreementList_iframe.jsp?type="+type+"&DOID="+DOID+"&biOID="+biOID+"&productStatus="+productStatus;
		$("#iframepage").attr("src",iframeurl);
 }
</script>
</html>
