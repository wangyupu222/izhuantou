<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/include/url.jsp" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>砖头网</title>
<link rel="stylesheet" type="text/css" href="css/style-common.css">
<link rel="stylesheet" type="text/css" href="/css/personalcenter.css">
<link r/e="text/css" href="/css/CapitalDetailed.css">
<link rel="stylesheet" href="/css/zoom.css">
<Link rel="shortcut icon" href="<%=pathUrl %>/images/zhuan.ico" />
<script type="text/javascript" src="/js/jquery-1.10.1.min.js"></script>
<script src="/js/com.js"></script>
<script language="javascript" src="/js/menu.js"></script>
<!--日期时间-->
<script language="javascript" type="text/javascript" src="/My97DatePicker/WdatePicker.js"></script>
<style type="text/css">


/**详情**/

.boxxqjs{ overflow:hidden;  width:99%; margin:auto; margin-top:40px;}
.title-p { font-size:16px; color:#489ae3; line-height:30px; overflow:hidden;}
.title-p span{ display: inline-block;background-color:#489ae3;  height:17px; width:6px; background-color:"#489ae3"; float:left; margin-top:7px; margin-right:9px;!important;}
.boxxqjs p.p-content{ font-size:14px; line-height:22px; margin:10px auto; width:97%; }
.messagebox{ overflow:hidden;}
.messagebox dl{  float:left; margin-left:5%; text-align:left; font-size:14px; line-height:30px; color:#333333; margin-top:15px;}
.messagebox dl:first-child{ margin-left:18px;}
.messagebox dl .work_city{width: 135px;line-height: 12px;display: inline-block;overflow: hidden;white-space: nowrap;text-overflow: ellipsis;}
.boxshehe{  width:96%; font-size:14px; margin:auto; margin-top:20px;margin-bottom:20px; border-top:1px solid #e5e5e5; border-left:1px solid #e5e5e5;}
.boxshehe .th div{ background-color:#55b0fd; border-right:1px solid #e5e5e5; color:#fff;}
.thbox{ height:38px; overflow:hidden; line-height:38px; text-align:center;border-bottom:1px solid #e5e5e5;}
.thbox div{ font-size:14px; color:#333333; border-right:1px solid #e5e5e5; float:left; }
.thbox div.box01{ width:10%; height:38px;	}
.thbox div.box02{ width:80%;}
.thbox div.box03{ width:9.7%; cursor:pointer;}
.yunullimg { padding:20px 0px;}
.yunullimg img{ width:80%; margin:0px auto;}
.hhdetail{ width:95%; margin:0 auto;}
.bingtitle{ font-size: 20px;}
.commit-1{ margin: 0 auto;border-bottom:0; height: 30px;}
.commit-1 span{width: auto;font: 14px/20px "微软雅黑";}
.xyjk{margin: 0px 5px;}
.comtop-t{width:95%;  }
.media_body .tabhhdetail{margin: 0px auto;}
.media_body .tabhhdetail tr td{ line-height: 25px;}
.media_body .boxxqjs {margin-top: 4px;}
.media_body .messagebox dl {line-height: 26px;margin-top: 0px;}
</style>
</head>
<body style="width:100%;min-width:100%; background:#fff;">
<% try{ %>
		
		<div class="comtop-t">
		  <a href="/agreement_z.jsp?contractType=6" target="_blank" class="xyjk">《协议模板》</a>
		 
		<p class="commit-1"><span>借款编号：</span></p>
		</div>
		<div class="hhdetail">
          <div class="bingtitle"></div>
          <table class="tabhhdetail" border="0" cellpadding="0" cellspacing="0">
          	<tr>
          		<td>元</td>
          		<td>/个月</td>
          		<%-- <td><%=request.getAttribute("repaymentType") %></td> --%>
          		<td>等本等息</td>
          		<td></td>
          		<td>%</td>
          	</tr>
          	<tr>
          		<td>借款总额</td>
          		<td>还款进度</td>
          		<td>还款方式</td>
          		<td>还款状态</td>
          		<td>借款利率</td>
          	</tr>
          </table>
          
          <div class="boxxqjs">
                        	<div class="title-p"><span></span>标的详情</div>
                            <p class="p-content"><%=request.getAttribute("loanuse") %> 
</p>
                        </div>
                        
                        <div class="boxxqjs">
                        	<div class="title-p"><span></span>借款人信息</div>
                            <div class="messagebox">
                            	<dl>
                                	<dd>姓名：<span>
                                	                            	
                                	</span></dd>
                                    <dd>性别：<span>
                                    <dd>年龄：<span>
                                                                    
                                    </span></dd>
                                    <dd>民族：<span>                               
                                    </span></dd>
                                </dl>
                                <dl>
                                	<dd>身份证号：<span>
                                	                              	
                                	</span></dd>
                                    <dd>手机号：<span>
                                                                     
                                    </span></dd>
                                    <dd>教育程度：<span>
                                    
                                    </span></dd>
                                    <dd>户籍所在地：<span class="work_city">
                                    
                                    </span></dd>
                                </dl>
                                <dl>
                                	<dd>工作城市：<span class="work_city">
                                	
                                	</span></dd>
                                    <dd>工作年限：<span>
                                   
                                    </span></dd>
                                    <dd>职务：<span>
                                    
                                    </span></dd>
                                    <dd>公司性质：<span>                                     
                                    </span></dd>
                                </dl>
                                <dl>
                                	
                                    <dd>所属行业：<span>
                                    
                                    </span></dd>
                                    <dd>个人收入：<span>
                                   
                                    </span></dd>
                                    <dd>有无购车：<span>
                                    
                                    </span></dd>
                                    <dd>有无购房：<span>
                                    
                                    </span></dd>
                                </dl>
                            </div>
                        </div>
                        
          
        </div> 

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
	$("#FormRecharge").validate({
		submitHandler: function(form) {
			//验证通过后 的js代码写在这里
			form.submit();
		}
	})
	
	/* var w= parent.GetParentWindowHight();
	   if(w<=1366){
		   $("body").addClass("media_body");  
	    
	   } */
		
})




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
    var mobile = /^1[3|4|5|7|8]\d{9}$/;
	return this.optional(element) || (mobile.test(value));
}, "手机格式不对");

//邮箱或手机验证规则  
jQuery.validator.addMethod("mm", function (value, element) {
    var mm = /^[a-z0-9._%-]+@([a-z0-9-]+\.)+[a-z]{2,4}$|^1[3|4|5|7|8]\d{9}$/;
	return this.optional(element) || (mm.test(value));
}, "格式不对");

//电话或手机验证规则  
jQuery.validator.addMethod("tm", function (value, element) {
    var tm=/(^1[3|4|5|7|8]\d{9}$)|(^\d{3,4}-\d{7,8}$)|(^\d{7,8}$)|(^\d{3,4}-\d{7,8}-\d{1,4}$)|(^\d{7,8}-\d{1,4}$)/;
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
},"请输入小于1500000的正确数字");

//汉字
jQuery.validator.addMethod("chinese", function (value, element) {
    var chinese = /^[\u4E00-\u9FFF]+$/;
    return this.optional(element) || (chinese.test(value));
}, "格式不对");

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







</script>
</html>
