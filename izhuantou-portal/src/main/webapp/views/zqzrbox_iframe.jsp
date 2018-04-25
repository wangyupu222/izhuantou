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
<style>
.applyitems {
    width: 560px;
}
.applyzqzrcon{
    min-height: 340px;
}
.applyzqzrcon {
    padding-bottom: 0px;
}
.commit-2 span.spjine {
    width: 292px;
    color:#666;
}
.commit-2 span.spjine {
    color: #666;
    position: relative;
}
#isagree{
    margin-right:10px;
}
.commit-2 a:hover {
    background-color: #f2f2f2;
}
.spjine span.error{position: absolute;
left: -39px;
top: -2px;}
</style>

</head>
<body style="width:100%;min-width:100%;background-color:transparent;">
<% try{ %>
<form action="/portal/mylend/saveZRMemage" method="post" name="FormZQZRBox" id="FormZQZRBox" >
<input type="hidden"  id="businessOID" name="businessOID" value="<%=request.getParameter("businessOID") %>" />
<input type="hidden" id="PaydebitOID" name="PaydebitOID" value="<%=request.getParameter("PaydebitOID") %>" />

<input type="hidden" id="xmmc" name="xmmc" value="" />
<input type="hidden" id="cjje" name="cjje" value="" />
<input type="hidden" id="nhll" name="nhll" value="" />
<input type="hidden" id="cjsjDate" name="cjsjDate" value="" />
<input type="hidden" id="xssyqs" name="xssyqs" value="" />
<input type="hidden" id="tatalTerm" name="tatalTerm" value="" />
<input type="hidden" id="zrsybx" name="zrsybx" value="" />
<input type="hidden" id="zrsybj" name="zrsybj" value="" />
<input type="hidden" id="zrsylx" name="zrsylx" value="" />
<input type="hidden" id="zrsxf" name="zrsxf" value="" />
<input type="hidden" id="biddingType" name="biddingType" value="" />
<input type="hidden" id="jg" name="jg" value="" />
<div class="yunull yunullchujie">
          	<div class="boxtitle">
            	<img src="<%=pathUrl %>/images/chujie.png" width="65" height="53">
                <div class="titlebt"><a href="javascript:;"></a></div>
                <div class="titlebt"><a href="#"></a></div>
            </div>
            
            <div class="contentchujie applyzqzrcon">
                <div class="applyitems"><label>出借金额</label><span><em></em>元</span></div>
                <div class="applyitems"><label>预期年化</label><span><em></em>%</span></div>
                <div class="applyitems"><label>出借时间</label><span><em></em></span></div>
                <div class="applyitems"><label>到期时间</label><span><em></em></span></div>
                <div class="applyitems"><label>手续费</label><span class=""><em></em>元<em class="shlvText" style="color: #666;margin-left: 10px;"></em></span></div>
                <div class="applyitems"><label>转让价格</label><span class="sredcolor"><em><web:WidgetTextOut name="FormZQZRBox" property="zrsybx"  /></em>元（<web:WidgetTextOut name="FormZQZRBox" property="zrsybj"  />元本金+<web:WidgetTextOut name="FormZQZRBox" property="zrsylx"  />元利息-<web:WidgetTextOut name="FormZQZRBox" property="zrsxf"  />元手续费）</span></div>
                
                <div class="applyitems">*支付利息仅为当天计算结果，实际支付请以最终结果为准。</div>
            </div>
            
            <p class="commit-2"><button id="" name="" type="submit" value="确认转让" class="colseok">确认转让</button><span class="spjine"><input type="checkbox" checked="checked" required="" data-msg-required="必选" name="isagree" id="isagree" value="ok"><label for="isagree">我已阅读并同意<a class="xy-a" href="agreement_z.jsp?contractType=7&biddingType=qt" target="_blank" >《债权转让协议》</a></label><!--<input name="" type="checkbox" id="isagree">  --></span></p>
            
        </div>
</form>
<%}catch(Exception e){
	response.sendRedirect("error.jsp");
    return;  
} %>
</body>


<!--验证表单-->
<script src="/js/jquery.validate.js"></script>
<script>
$(function(){
    //jquery.validate
	$("#FormZQZRBox").validate({
		submitHandler: function(form) {
			//验证通过后 的js代码写在这里
			form.submit();
		}
	})
	
})
	var Request = new Object();
	Request = GetRequest2();
	var PaydebitOID=Request["PaydebitOID"];
	var businessOID=Request["businessOID"];
	$.ajax({
	        type: "get",//请求方式
	        url: "/portal/mylend/zqzrSq",//地址，就是json文件的请求路径
	        dataType: "json",//数据类型可以为 text xml json  script  jsonp
	        data:{
	        	PaydebitOID:PaydebitOID,
	        	businessOID:businessOID
	        },
	        success: function(result){//返回的参数就是 action里面所有的有get和set方法的参数
	        console.log(result);
	        //资金流水留白
	       $("#xmmc").val(result.dataValue.xmmc);
	       $("#cjje").val(result.dataValue.cjje);
	       $("#nhll").val(result.dataValue.nhll);
	       $("#cjsjDate").val(result.dataValue.cjsjDate);
	       $("#xssyqs").val(result.dataValue.xssyqs);
	       $("#tatalTerm").val(result.dataValue.tatalTerm);
	       $("#zrsybx").val(result.dataValue.zrsybx);
	       $("#zrsybj").val(result.dataValue.zrsybj);
	       $("#zrsylx").val(result.dataValue.zrsylx);
	       $("#zrsxf").val(result.dataValue.zrsxf);
	       $("#biddingType").val(result.dataValue.biddingType);
	       $("#jg").val(result.dataValue.jg);
	       $(".titlebt").eq(0).find("a").text(result.dataValue.xmmc);
	       $(".titlebt").eq(1).find("a").text(result.dataValue.htbh);
	       $(".applyitems").eq(0).find("em").text(result.dataValue.cjje);
	       $(".applyitems").eq(1).find("em").text((result.dataValue.nhll).toFixed(2));
	       $(".applyitems").eq(2).find("em").text(result.dataValue.cjsjDate);
	       $(".applyitems").eq(3).find("em").text(result.dataValue.dqsj);
	       $(".applyitems").eq(4).find("em").text((result.dataValue.zrsxf).toFixed(2));
	       $(".shlvText").text("(费率："+(result.dataValue.shlv).toFixed(2)+"%)");
	       $(".sredcolor").html("<em>"+(result.dataValue.zrsybx).toFixed(2)+"</em>元（"+(result.dataValue.zrsybj).toFixed(2)+"元本金+"+(result.dataValue.zrsylx).toFixed(2)+"元利息-"+(result.dataValue.zrsxf).toFixed(2)+"元手续费）");

	       
	       
	       
	        },
			error:function(result){
	    	}
	    });
		
			$(document).on('click','.cd-popup-trigger5', function(event){
			
            event.preventDefault();
            $('.cd-popup5').addClass('is-visible1');
            //$(".dialog-addquxiao").hide()
        });




//配置错误提示的节点，默认为label，这里配置成 span （errorElement:'span'）
$.validator.setDefaults({
	errorElement:'span'
});

//配置通用的默认提示语
$.extend($.validator.messages, {
	required: '必填',
    equalTo: "两次输入不一致"
});


function GetRequest2() {
	  
	  var url = location.search; //获取url中"?"符后的字串
	   var theRequest = new Object();
	   if (url.indexOf("?") != -1) {
	      var str = url.substr(1);
	      strs = str.split("&");
	      //alert(strs);
	      for(var i = 0; i < strs.length; i ++) {
	         theRequest[strs[i].split("=")[0]]=(strs[i].split("=")[1]);
	      }
	   }
	   return theRequest;
	}



</script>
</html>
