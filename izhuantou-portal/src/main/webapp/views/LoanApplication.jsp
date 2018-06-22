<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/include/url.jsp" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>砖头网-借款审核</title>
<link rel="stylesheet" type="text/css" href="/css/style-common.css">
<link rel="stylesheet" type="text/css" href="/css/personalcenter.css">
<link rel="stylesheet" type="text/css" href="/css/preother.css">
<Link rel="shortcut icon" href="<%=pathUrl %>/images/zhuan.ico" />
<!--<link rel="stylesheet" href="/css/jquery.onoff.css" media="screen" />-->
<script type="text/javascript" src="/js/jquery-1.10.1.min.js"></script>
<!--<script src="/js/jquery.onoff.min.js"></script>-->
<script src="/js/com.js"></script>
<script language="javascript" src="/js/menu.js"></script>
<script src="/js/resubmit.js"></script>
<script src="/js/paging.js"></script>
<style>
.dfinput.yue span.erro{ float:right; display:block;}
span.error{ float:left; color:#C00; padding:0 15px; display:block; line-height: 40px; }
.hide_liu{  width: 100%;  height: 500px;  text-align: center;  background:#fff url("<%=pathUrl %>/images/JKshenqing.jpg"); background-repeat: no-repeat; background-position:center center; display: none;  }
.cd-popup-container1, .cd-popup-container2, .cd-popup-container4{width:856px;margin: 8% auto;}

.tu_yzm {
    position: absolute;
    top: 0;
    left: 391px;
}
.E_signaturesBtn{
font-size:14px; background:#55b0fd; width:54%; margin:0 auto; height:30px; line-height:30px; margin-top:7px; border-radius:5px;text-align:center;color:#fff;display: inline-block;
}
</style>

</head>


<script>
function openblank(){
	window.open('/portal/cash/authorization?memberOID=<%=session.getAttribute("memberOID") %>');
	
}

function prolog(){
	$(".comneione").hide();
	$(".comneitwo").show();
	
}

</script>
<script>
function huoquyzm(){	
	var phoneNum = document.getElementById('phone').value;	
	var yzm= document.getElementById('Yzm').value;	
	var smsValidateCode="smsLoanApplication";
	  var oSign_val=$("#sign_val");
	if (phoneNum!=""){
		if(yzm!=""){
			
		
		//var data = {
		        //"handleClassName":"pageRegisterFormRegisterdjGetYzm","phone":phoneNum
	//	    };
	//验证手机号是否存在
		
  	 $.ajax({
		        type:"POST",
		        dataType:"json",
		        url:"/portal/user/checksms",
		        data:{
		        	msmType:smsValidateCode,
		        	name:phoneNum,
		        	yzm:yzm
		        }, 
		        success: function(result){
		        	if(result.status==2){
		            	oSign_val[0].innerHTML =result.message;
		            }else{
			            sends.send();
		            }
		            $('.yzmsz').attr("src","/portal/user/checkcode?x="+Math.random());
		        },
		        error:function(result){
		        	oSign_val[0].innerHTML ="系统异常！";
		        }
		    });
		}else{
			oSign_val[0].innerHTML ="请输入验证码！";
			$('.yzmsz').attr("src","/portal/user/checkcode?x="+Math.random());
		}
	}else{
		oSign_val[0].innerHTML ="请输入手机号！";
		$('.yzmsz').attr("src","/portal/user/checkcode?x="+Math.random());
	}

}
//倒计时
var sends = {
		checked:1,
		send:function(){
				var numbers = /^1\d{10}$/;
				var val = $('#phone').val().replace(/\s+/g,""); //获取输入手机号码
				
				if(numbers.test(val)){
					//alert(numbers.test(val));
					var time = 59;
					//$('.div-phone span').remove();
					function timeCountDown(){
						if(time==0){
							clearInterval(timer);
							$('a.hqyzm').removeClass('send1').html("获取验证码");
							$("a.hqyzm").attr("onclick","huoquyzm();");
							//$('a.hqyzm').removeAttr('onclick');
							sends.checked = 1;
							return true;
						}
						$('a.hqyzm').html(time+"s后再发送");
						$('a.hqyzm').removeAttr('onclick');
						time--;
						return false;
						sends.checked = 0;
					}
					$('a.hqyzm').addClass('send1');
					
					timeCountDown();
					var timer = setInterval(timeCountDown,1000);
				}
		}
	}
	
	$(function(){
		var oUserName=$(".oUserName").val();
		var oUsercard=$(".oUsercard").val();
		var oUserphone=$(".oUserphone").val();
		if(oUserName=="null"){
			$("#name").val("");
		}else{
			$("#name").val(oUserName);
		}
		if(oUsercard=="null"){
			$("#card").val("");
		}else{
			$("#card").val(oUsercard);
		}
		if(oUserphone=="null"){
			$("#phone").val("");
		}else{
			$("#phone").val(oUserphone);
		}
		
		
	})
</script>

<body>
<% try{ %>
<div class="cd-popup1">
<div class="cd-popup-container1">
<!--充值-->
	<div class="comnei ">
        <a class="close cd-popup-close"></a>
        <p class="commit-1"><span>修改</span></p>
        <iframe src="LoanApplication_iframe.jsp?loanOID=" class="LoanApplication_iframe" width="100%" height="550" id="iframepage" name="iframe" frameborder="0" scrolling="no" marginheight="0" marginwidth="0"></iframe>
    </div>
    <!--充值结束-->
        
     </div>
</div>
<div class="cd-popupjk">
<div class="cd-popup-containerjk">
	<div class="comnei comneione">
        <a class="close cd-popup-close"></a>
        <p class="commit-1"><span>授权开通委托划扣</span></p>
        
        <div class="boxconsq">
        	<div class="titlesq">请在新打开的页面完成授权</div>
        	<div class="consqbut">
        		<a class="active cd-popup-close" href="javascript:void(0);" >授权成功</a>
        		<a href="javascript:void(0);" onclick="prolog();" >遇到问题</a>
        	</div>
        	<div class="bzsq">注：委托交易功能为富友提供，请按照富友的规则进行操作，砖头网
      对此拥有最终解释权</div>
        </div>
       
    </div>
    
    <div class="comnei comneitwo hide">
        <a class="close cd-popup-close"></a>
        <p class="commit-2"></p>
        <div class="phonetitlesq">请拨打客服电话 400-900-9677</div>
        	
    </div>
    
   </div>
</div>

<!--common header-->
<%@ include file="header.jsp" %>
<!--common end-->
<div class="main minhh">
	<div class="con personcon">
    	<div class="smallnav">
        <!-- <a >当前位置</a><span>:</span><a>个人中心</a><span>&gt;</span><a >我的借款</a><span>&gt;</span><a >借款审核</a> -->
        </div>
        <div class="con xqdetial">
    	<%@ include file="left_percenter.jsp" %>
        
    	<div class="xqdeleft invitecon">             
            <%if("1".equals(request.getParameter("type")) || "1".equals(request.getAttribute("type"))) {%>
            <!-- 全国城市三级联动 -->
<script  src="/js/area.js" type="text/javascript"></script>
<script type="text/javascript">_init_area();</script>
            <div class="my_loantop2"><a href="LoanApplication.jsp?type=1" class="active">借款审核</a><a href="LoanApplication.jsp?type=2" >借款审核记录</a><span class="L2">&nbsp;</span></div>
            <div class="debitfill tabopa ">               
            <div class="sqwt"><span></span>您尚未进行委托交易授权，请您先<a onclick="openblank();" href="javascript:void(0);"  target="_blank" class="cd-popup-triggerjk">授权</a>，再进行相关操作！</div>
        	<ul>  
        	<input class="oUserName" type="hidden" value='<%=request.getAttribute("userName")%>' />  
        	<input class="oUsercard" type="hidden" value='<%=request.getAttribute("idCard")%>' />  
        	<input class="oUserphone" type="hidden" value='<%=request.getAttribute("mobile")%>' />    	   	 
        	 <form action="/portal/loan/loanAudit" method="post"  name="FormApply" id="FormApply"  >
            	<li><label>申请人</label><input type="text" name="name" id="name" class="dfinput valid" readonly required data-msg-required="请输入申请人"></li>
                <li><label>身份证号</label><input type="text" name="card" id="card" class="dfinput valid" readonly="" required="" data-rule-card="true" data-msg-required="请输入身份证号" data-msg-card="请输入正确格式"></li>
            	<li><label>手机号码</label><input type="text" name="phone" id="phone" class="dfinput valid" readonly="" required="" data-rule-mobile="true" data-msg-required="请输入手机号" data-msg-mobile="请输入正确格式"></li>
            	<li><label>图形验证</label><input type="text" name="Yzm" id="Yzm" class="dfinput dfinput2" required="" data-msg-required="请输入短信验证码" maxlength="6"><img class="yzmsz tu_yzm" width="100" height="41" src="/portal/user/checkcode"  class="m" alt="如果看不清,请点击我!" title="如果看不清,请点击我!" onclick="this.setAttribute('src','/portal/user/checkcode?x='+Math.random());"></li>
            	<li><label>短信验证</label><input type="text" name="mesYzm" id="mesYzm" class="dfinput" required="" data-msg-required="请输入短信验证码" maxlength="6"><a href="#" class="hqyzm loanhqyzm" onclick="huoquyzm()">获取短信验证码</a></li>
            	<li><label>借款金额</label><input type="text" name="money" id="money" class="dfinput yue" required="" data-msg-required="请输入借贷金额" data-rule-gt="true" data-gt="0" data-rule-lt="true" data-lt="1000000" data-rule-pinteger="true" maxlength="10"></li>
                <li><label>借款期限</label>
                <select class="dfinput" required="" data-msg-required="请选择借款期限" id="loanTerm" name="loanTerm">
										<option value="">---选择期限---</option>
										<option value="3">3个月</option>
										<option value="6">6个月</option>
										<option value="9">9个月</option>
										<option value="12">12个月</option>
										<option value="15">15个月</option>
										<option value="18">18个月</option>
										<option value="24">24个月</option>
										<option value="36">36个月</option>
								</select></li>
                <li><label>产品类型</label>
                <select class="dfinput" required="" data-msg-required="请选择产品类型" id="proType" name="loanYT">
										<option value="">---选择菜单---</option>
										<option value="IXD">i薪贷</option>
										<option value="IBD">i保单</option>
										<option value="IYD">i意贷</option>
										<option value="IFD">i房贷</option>
										<option value="i房抵">i房抵</option>
										<option value="i车抵">i车抵</option>
								</select></li>
                <li><label>所在城市</label>
                <%-- <web:WidgetSelectOne  name="FormApply" property="city" customProperty="class='dfinput' required" /> --%>
                <select id="s_province" name="s_province" class='dfinput select01' ></select>  
			  	<select id="s_city" name="s_city" class='dfinput select02'></select>  
			  	<select id="s_county" name="s_county" class='dfinput select02' required data-msg-required='请选择所在城市'></select>
                
                </li>
                
            <!--<li><label>备注</label><textarea type="text" id="textarea" class="dfinput dfinput-text" ></textarea></li>-->
              <!--	<li><label>验证码</label><input type="text" class="dfinput yz"/><div class="yzimg"><img src="<%=pathUrl %>/images/jdyz.jpg"  /></div><a class="ya" href="#">换一张</a></li>-->
               <li class="single_ctrl"><input type="checkbox" required="" data-msg-required="请勾选协议" name="isAgree" id="isAgree" value="1"><label for="isAgree" class="isAgree-style">我已阅读并同意 </label><a href="agreement_z.jsp?contractType=9" target="_blank" >《借款咨询服务协议》 </a>
<a href="agreement_z.jsp?contractType=6&biddingType=qt" target="_blank" >《砖头网借款协议》 </a>
<a href="agreement_z.jsp?contractType=8&biddingType=qt" target="_blank" >《砖头网债权预回购协议》</a></li>
               
               
               <div align="left" style="color:#f00;margin-left:80px;" id="sign_val" >${msg}</div>
               <%-- <div class="register_validate register_new" id="sign_val"><hoontag:Message name="registerErro"/></div></div> --%>
               
               <li><button id="" name="" type="submit" value="立即提交" class="debit-but">立即提交</button></li>
               </form>
            </ul>
        </div>
            <%}else{%>
            <div class="my_loantop2"><a href="LoanApplication.jsp?type=1" >借款审核</a><a href="LoanApplication.jsp?type=2" class="active">借款审核记录</a><span class="L2">&nbsp;</span></div>
            <div class="loanrecord01 tabopa ">                 
                    <table width="100%" cellpadding="0" cellspacing="0" align="center" class="applayrecord">
                    <thead>
                    <tr class="rowbox06 tit JKshenqing">
                    <td width="12.5%">姓名</td>
                    <td width="17.5%">申请时间</td>
                    <td width="15.5%">产品类型</td>
                    <td width="14.5%">借款金额</td>
                    <td width="14%">期限（月）</td>
                    <td width="12%">状态</td>
                    <td width="14%">电子签名</td>
                    </tr>
                    </thead>
                    <tbody>
                    
                    </tbody>
                    </table>
                    <!--分页-->
            		<div id="page" class="page_div pageCY"></div>
               		 <!--分页结束-->
                    <div class="hide_liu JKshenqing_liu"></div>
                    
          
       </div>
            <%} %>
            
        
        
      
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



<!--验证表单-->
<script src="/js/jquery.validate.js"></script>

<script>
function passonOID(loanOID){	
	//alert(1);
	var imgurl = "LoanApplication_iframe.jsp?loanOID="+loanOID;
	$('.LoanApplication_iframe').attr('src',imgurl);
	//alert(imgurl);


}


$(function(){
    //jquery.validate
	$("#FormApply").validate({
		submitHandler: function(form) {
			//验证通过后 的js代码写在这里
			form.submit();
			return validataForm("debit-but");
		}
	})
	
	
	$.ajax({
        type: "post",
        url: "/portal/loan/findInfo",
        dataType: "json",
        success: function(result){
        	$("#name").val(result.dataValue.nameCN);
        	$("#card").val(result.dataValue.cardNO);
        	$("#phone").val(result.dataValue.name);
        	if(result.dataValue.entrustRecharge=='01'){
        		$(".sqwt").remove();
        	}
                		        	
        },
		error:function(result){    	
    	}
    });
    
	$.ajax({
        type: "post",
        url: "/portal/loan/lendprocess/1",
        dataType: "json",
        success: function(result){
        	if(result.dataValue.totalNumber<1){
        		$(".JKshenqing_liu").css("display","block");
        	}
        	if(result.dataValue.totalNumber<=10){
        		$("#page").remove();
        	}
        	var dataItems=result.dataValue.data;
        	for(var i=0;i<dataItems.length;i++){
        		str='<tr class="rowbox06"><td>'+dataItems[i].userName+'</td><td>'+dataItems[i].addDateTime+'</td><td>'+dataItems[i].loanYT+'</td><td>'+dataItems[i].loanAmount+'</td><td>'+dataItems[i].loanTerm+'</td>';
        		if(dataItems[i].first=='1'){
        			if(dataItems[i].second=='1' || dataItems[i].second=='11' || dataItems[i].second=='12' || dataItems[i].second=='13'){
        				str+='<td >审核通过</td><td ><a class="E_signaturesBtn" style="background:#ababab;" href="javascript:;">获取签名</a></td>';
        			}else if(dataItems[i].second=='2'){
        				str+=' <td ><a class="loanstates cd-popup-trigger1" onclick="passonOID(\''+dataItems[i].oid+'\')">修改</a></td>';
        				if(dataItems[i].handBase!='' && dataItems[i].handBase!=null){
        					str+='<td ><a class="E_signaturesBtn" style="background:#ababab;" href="javascript:;">获取签名</a></td>';
        				}else{
        					str+='<td ><a class="E_signaturesBtn"  href="/portal/loan/E_signatures">获取签名</a></td>';
        				}
        			}else{
        				str+='<td >审核中</td>';
        				if(dataItems[i].handBase!='' && dataItems[i].handBase!=null){
        					str+='<td ><a class="E_signaturesBtn" style="background:#ababab;" href="javascript:;">获取签名</a></td>';
        				}else{
        					str+='<td ><a class="E_signaturesBtn"  href="/portal/loan/E_signatures">获取签名</a></td>';
        				}
        			}
        			
        		}else if(dataItems[i].first=='2'){
        			str+=' <td ><a class="loanstates cd-popup-trigger1" onclick="passonOID(\''+dataItems[i].oid+'\')">修改</a></td>';
        			if(dataItems[i].handBase!='' && dataItems[i].handBase!=null){
    					str+='<td ><a class="E_signaturesBtn" style="background:#ababab;" href="javascript:;">获取签名</a></td>';
    				}else{
    					str+='<td ><a class="E_signaturesBtn"  href="/portal/loan/E_signatures">获取签名</a></td>';
    				}
        			
        		}else if(dataItems[i].first=='4'){
        			str+='<td>审核通过</td><td ><a class="E_signaturesBtn" style="background:#ababab;" href="javascript:;">获取签名</a></td>';	
        		}else if(dataItems[i].first=='3'){
        			str+='<td >审核中</td>';
        			if(dataItems[i].handBase!='' && dataItems[i].handBase!=null){
    					str+='<td ><a class="E_signaturesBtn" style="background:#ababab;" href="javascript:;">获取签名</a></td>';
    				}else{
    					str+='<td ><a class="E_signaturesBtn"  href="/portal/loan/E_signatures">获取签名</a></td>';
    				}
        		}else{
        			str+=' <td ><a class="loanstates cd-popup-trigger1" onclick="passonOID(\''+dataItems[i].oid+'\')">修改</a></td>';
        			if(dataItems[i].handBase!='' && dataItems[i].handBase!=null){
    					str+='<td ><a class="E_signaturesBtn" style="background:#ababab;" href="javascript:;">获取签名</a></td>';
    				}else{
    					str+='<td ><a class="E_signaturesBtn"  href="/portal/loan/E_signatures">获取签名</a></td>';
    				}
        		}
        		str+='</tr>';
        		$(".applayrecord tbody").append(str);
        	}
        	
        	$(".pageCY").paging({
    			totalPage: result.dataValue.totalPage,
    			totalSize: result.dataValue.totalNumber,
    			callback: function(num) {
    				$.ajax({
    			        type: "post",
    			        url: "/portal/loan/lendprocess/"+num,
    			        dataType: "json",
    			        success: function(result){
    			        	$(".applayrecord tbody tr").remove(str);
    			        	var dataItems=result.dataValue.data;
    			        	for(var i=0;i<dataItems.length;i++){
    			        		str='<tr class="rowbox06"><td>'+dataItems[i].userName+'</td><td>'+dataItems[i].applyTime+'</td><td>'+dataItems[i].loanYT+'</td><td>'+dataItems[i].loanAmount+'</td><td>'+dataItems[i].loanTerm+'</td>';
    			        		if(dataItems[i].first=='1'){
    			        			if(dataItems[i].second=='1' || dataItems[i].second=='11' || dataItems[i].second=='12' || dataItems[i].second=='13'){
    			        				str+='<td >审核通过</td><td ><a class="E_signaturesBtn" style="background:#ababab;" href="javascript:;">获取签名</a></td>';
    			        			}else if(dataItems[i].second=='2'){
    			        				str+=' <td ><a class="loanstates cd-popup-trigger1" onclick="passonOID(\''+dataItems[i].oid+'\')">修改</a></td>';
    			        				if(dataItems[i].handBase!='' && dataItems[i].handBase!=null){
    			        					str+='<td ><a class="E_signaturesBtn" style="background:#ababab;" href="javascript:;">获取签名</a></td>';
    			        				}else{
    			        					str+='<td ><a class="E_signaturesBtn"  href="E_signatures.jsp">获取签名</a></td>';
    			        				}
    			        			}else{
    			        				str+='<td >审核中</td>';
    			        				if(dataItems[i].handBase!='' && dataItems[i].handBase!=null){
    			        					str+='<td ><a class="E_signaturesBtn" style="background:#ababab;" href="javascript:;">获取签名</a></td>';
    			        				}else{
    			        					str+='<td ><a class="E_signaturesBtn"  href="E_signatures.jsp">获取签名</a></td>';
    			        				}
    			        			}
    			        			
    			        		}else if(dataItems[i].first=='2'){
    			        			str+=' <td ><a class="loanstates cd-popup-trigger1" onclick="passonOID(\''+dataItems[i].oid+'\')">修改</a></td>';
    			        			if(dataItems[i].handBase!='' && dataItems[i].handBase!=null){
    			    					str+='<td ><a class="E_signaturesBtn" style="background:#ababab;" href="javascript:;">获取签名</a></td>';
    			    				}else{
    			    					str+='<td ><a class="E_signaturesBtn"  href="E_signatures.jsp">获取签名</a></td>';
    			    				}
    			        			
    			        		}else if(dataItems[i].first=='4'){
    			        			str+='<td>审核通过</td><td ><a class="E_signaturesBtn" style="background:#ababab;" href="javascript:;">获取签名</a></td>';	
    			        		}else if(dataItems[i].first=='3'){
    			        			str+='<td >审核中</td>';
    			        			if(dataItems[i].handBase!='' && dataItems[i].handBase!=null){
    			    					str+='<td ><a class="E_signaturesBtn" style="background:#ababab;" href="javascript:;">获取签名</a></td>';
    			    				}else{
    			    					str+='<td ><a class="E_signaturesBtn"  href="E_signatures.jsp">获取签名</a></td>';
    			    				}
    			        		}else{
    			        			str+=' <td ><a class="loanstates cd-popup-trigger1" onclick="passonOID(\''+dataItems[i].oid+'\')">修改</a></td>';
    			        			if(dataItems[i].handBase!='' && dataItems[i].handBase!=null){
    			    					str+='<td ><a class="E_signaturesBtn" style="background:#ababab;" href="javascript:;">获取签名</a></td>';
    			    				}else{
    			    					str+='<td ><a class="E_signaturesBtn"  href="E_signatures.jsp">获取签名</a></td>';
    			    				}
    			        		}
    			        		str+='</tr>';
    			        		$(".applayrecord tbody").append(str);
    			        	}    		        	
    			        },
    					error:function(result){    	
    			    	}
    			    });
    			}
    		})
                		        	
        },
		error:function(result){    	
    	}
    });
		
})

 $(document).on('click','.cd-popup-trigger1', function(event){
            event.preventDefault();
            $('.cd-popup1').addClass('is-visible1');
            //$(".dialog-addquxiao").hide()
        });


//配置错误提示的节点，默认为label，这里配置成 span （errorElement:'span'）
$.validator.setDefaults({
	errorElement:'span'
});

//配置通用的默认提示语
$.extend($.validator.messages, {
	required: '必填',
    equalTo: "请再次输入相同的值"
});


//邮箱 
jQuery.validator.addMethod("mail", function (value, element) {
	var mail = /^[a-z0-9._%-]+@([a-z0-9-]+\.)+[a-z]{2,4}$/;
	return this.optional(element) || (mail.test(value));
}, "邮箱格式不对");

//电话验证规则
jQuery.validator.addMethod("phone", function (value, element) {
    var phone = /^0\d{2,3}-\d{7,8}$/;
    return this.optional(element) || (phone.test(value));
}, "电话格式如：0371-68787027");

//区号验证规则  
jQuery.validator.addMethod("ac", function (value, element) {
    var ac = /^0\d{2,3}$/;
    return this.optional(element) || (ac.test(value));
}, "区号如：010或0371");

//无区号电话验证规则  
jQuery.validator.addMethod("noactel", function (value, element) {
    var noactel = /^\d{7,8}$/;
    return this.optional(element) || (noactel.test(value));
}, "电话格式如：68787027");

//手机验证规则  
jQuery.validator.addMethod("mobile", function (value, element) {
    var mobile = /^1[3|4|5|6|7|8|9]\d{9}$/;
	return this.optional(element) || (mobile.test(value));
}, "手机格式不对");

//邮箱或手机验证规则  
jQuery.validator.addMethod("mm", function (value, element) {
    var mm = /^[a-z0-9._%-]+@([a-z0-9-]+\.)+[a-z]{2,4}$|^1[3|4|5|6|7|8|9]\d{9}$/;
	return this.optional(element) || (mm.test(value));
}, "格式不对");

//电话或手机验证规则  
jQuery.validator.addMethod("tm", function (value, element) {
    var tm=/(^1[3|4|5|6|7|8|9]\d{9}$)|(^\d{3,4}-\d{7,8}$)|(^\d{7,8}$)|(^\d{3,4}-\d{7,8}-\d{1,4}$)|(^\d{7,8}-\d{1,4}$)/;
    return this.optional(element) || (tm.test(value));
}, "格式不对");

//年龄
jQuery.validator.addMethod("age", function(value, element) {   
	var age = /^(?:[1-9][0-9]?|1[01][0-9]|120)$/;
	return this.optional(element) || (age.test(value));
}, "不能超过120岁"); 
///// 20-60   /^([2-5]\d)|60$/

//传真
jQuery.validator.addMethod("fax",function(value,element){
    var fax = /^(\d{3,4})?[-]?\d{7,8}$/;
    return this.optional(element) || (fax.test(value));
},"传真格式如：0371-68787027");

//验证当前值和目标val的值相等 相等返回为 false
jQuery.validator.addMethod("equalTo2",function(value, element){
    var returnVal = true;
    var id = $(element).attr("data-rule-equalto2");
    var targetVal = $(id).val();
    if(value === targetVal){
        returnVal = false;
    }
    return returnVal;
},"不能和原始密码相同");

//大于指定数
jQuery.validator.addMethod("gt",function(value, element){
    var returnVal = false;
    var gt = $(element).data("gt");
    if(value > gt && value != ""){
        returnVal = true;
    }
    return returnVal;
},"请输入大于0的正确数字");
//小于指定数
jQuery.validator.addMethod("lt",function(value, element){
    var returnVal = false;
    var lt = $(element).data("lt");
    if(value <= lt && value != ""){
        returnVal = true;
    }
    return returnVal;
},"请输入小于1000000的正确数字");
//汉字
jQuery.validator.addMethod("chinese", function (value, element) {
    var chinese = /^[\u4E00-\u9FFF]+$/;
    return this.optional(element) || (chinese.test(value));
}, "格式不对");
//只能输入正整数
jQuery.validator.addMethod("pinteger", function (value, element) {
    var pinteger = /^[0-9]*[1-9][0-9]*$/;
    return this.optional(element) || (pinteger.test(value));
}, "不允许输入小数 ");

//指定数字的整数倍
jQuery.validator.addMethod("times", function (value, element) {
    var returnVal = true;
    var base=$(element).attr('data-rule-times');
    if(value%base!=0){
        returnVal=false;
    }
    return returnVal;
}, "必须是发布赏金的整数倍");

//身份证
jQuery.validator.addMethod("card", function (value, element) {
    var isIDCard1=/^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$/;//(15位)
    var isIDCard2=/^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/;//(18位)
    
    return this.optional(element) || (isIDCard1.test(value)) || (isIDCard2.test(value));
}, "格式不对");

$(function(){    
 //   alert($(".chi_you").next().find(".ddtit").length);
 $("#money").keyup(function(){
     var oMoney=Number($('#money').val());
     if(oMoney>1000000){
    	 $('#money').val(1000000);
     }
 })
})



</script>
</html>
