<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/include/url.jsp" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>砖头网-还款</title>
<link rel="stylesheet" type="text/css" href="/css/style-common.css">
<link rel="stylesheet" type="text/css" href="/css/personalcenter.css">
<link rel="stylesheet" type="text/css" href="/css/CapitalDetailed.css">
<Link rel="shortcut icon" href="<%=pathUrl %>/images/zhuan.ico" />
<!--<link rel="stylesheet" href="/css/jquery.onoff.css" media="screen" />-->
<script type="text/javascript" src="/js/jquery-1.10.1.min.js"></script>
<!--<script src="/js/jquery.onoff.min.js"></script>-->
<script src="/guide/css3-mediaqueries.js"></script> <!-- aaaaa -->
<!--[if lt IE 9]>
    <script src="/guide/css3-mediaqueries.js"></script>
<![endif]-->
<script src="/js/com.js"></script>
<script language="javascript" src="/js/menu.js"></script>

<!--日期时间-->
<script language="javascript" type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
<style>
.hide_liu{  width: 100%;  height: 500px;  text-align: center;  background:#fff url("<%=pathUrl %>/images/Capital_liu.jpg"); background-repeat: no-repeat; background-position:center center; display: none;  }
.cd-popup-container1, .cd-popup-container2, .cd-popup-container4, .cd-popup-container5, .cd-popup-container6, .cd-popup-containerh{ width:853px;}
.commit-2 span.spjineyue{display:none;}
.commit-2 span.balance_y {
    width: 250px;
}
.contentchujie{margin-top: -48px;}

.rowbox03.value td a{ font-size:14px; background:#55b0fd; width:70%; margin:0 auto; height:28px; line-height:28px; margin-top:7px; border-radius:5px;text-align:center;color:#fff; /* border:1px solid #f00;*/}
.rowbox03.value td a:hover{ background:#3291f0;color:#fff;}
.rowbox03.value td a.yuqi{background:#f00;}
.rowbox03.value td a.yuqi:hover{ background:#df0505;}
.commit-2 .questBtn {
    display: block;
    line-height: 30px;
    margin-top: 10px;
    margin-right: 20px;
    float: right;
    width: 134px;
    height: 30px;
    border-radius: 5px;
    background-color: #ABABAB;
    text-align: center;
    color: #fff;
    font-size: 14px;
    border: 0;
    padding-left:0px;
}
</style>

</head>
<body>
<% try{ %>
<div class="cd-popup6">
<div class="cd-popup-container6">
 <div class="comnei comneichujie">
       <a class="close cd-popup-close"></a>
       <p class="commit-1"><span>按期还款</span></p>
      <form action="pageRepaymentFormljhksave" name="Formljhk" id="Formljhk"  >
<div class="yunull yunullchujie">
          	<div class="boxtitle">
            	<img src="<%=pathUrl %>/images/chujie.png" width="65" height="53">
                <div class="titlebt"><web:WidgetTextOut name="Formljhk" property="biaoti"  /></div>
                <input type="hidden" id="planOID" name="planOID" value=""></a></div>
            </div>
            
            <div class="contentchujie applyzqzrcon">
                <div class="yjhqitems"><label>借款金额</label><span><em><web:WidgetTextOut name="Formljhk" property="jkje"  /></em>元</span></div>
                <div class="yjhqitems"><label>借款利率</label><span><em><web:WidgetTextOut name="Formljhk" property="jkll"  /></em>%</span></div>
                <div class="yjhqitems"><label>借款期限</label><span><em><web:WidgetTextOut name="Formljhk" property="jkqx"  /></em>月</span></div>
                <div class="yjhqitems"><label>借款时间</label><span ><em><web:WidgetTextOut name="Formljhk" property="jktime"  format="yyyy-MM-dd"/></em></span></div>
                <div class="yjhqitems"><label>费用组成</label><span class="orgcolorem">应还金额：<em><web:WidgetTextOut name="Formljhk" property="yhje2"  /></em>元</span><br>
                </div>
             </div>
         
        <p class="commit-2"><span class="balance_y">账户余额：<em id="balance"><%=request.getAttribute("availablemoney") %></em>元<span class="spjineyue" style="border-right:none;"><a onclick="chongzi()"  href="Recharge_new.jsp"  class="cz">充值</a></span></span><button id="" name="" type="submit" value="确认" class="colseok">确认</button><span class="questBtn" style="display:none;">余额不足</span><span class="spjine">总计：<em id="repaymoney"><web:WidgetTextOut name="Formljhk" property="yhje2" /></em>元</span></p>
       </form>
       
       
        
   </div> 
        
</div>
</div>

<!--逾期还款-->
<div class="cd-popup1">
<div class="cd-popup-container1">

   <div class="comnei comneichujie">
       <a class="close cd-popup-close"></a>
       <p class="commit-1"><span>逾期还款</span></p>
      <form action="" name="Formljhk"  id="Formyqhk" >
<div class="yunull yunullchujie">
          	<div class="boxtitle">
            	<img src="<%=pathUrl %>/images/chujie.png" width="65" height="53">
                <div class="titlebt"><web:WidgetTextOut name="Formljhk" property="biaoti"  /></div>
                <input type="hidden" id="planOID" name="planOID" value=""></a></div>
            </div>
            
            <div class="contentchujie applyzqzrcon">
                <div class="yjhqitems"><label>借款金额</label><span><em><web:WidgetTextOut name="Formljhk" property="jkje"  /></em>元</span></div>
                <div class="yjhqitems"><label>借款利率</label><span><em><web:WidgetTextOut name="Formljhk" property="jkll"  /></em>%</span></div>
                <div class="yjhqitems"><label>借款期限</label><span><em><web:WidgetTextOut name="Formljhk" property="jkqx"  /></em>月</span></div>
                <div class="yjhqitems"><label>借款时间</label><span ><em><web:WidgetTextOut name="Formljhk" property="jktime"  format="yyyy-MM-dd"/></em></span></div>
                <div class="yjhqitems"><label>费用组成</label><span class="orgcolorem">逾期本金：<em><web:WidgetTextOut name="Formljhk" property="yhje"  /></em>元</span><br>
                <label>&nbsp;</label><span class="orgcolorem">逾期利息：<em><web:WidgetTextOut name="Formljhk" property="yqlx" format="####.00"  /></em>元</span><br>
                <label>&nbsp;</label><span class="orgcolorem">咨询服务费：<em><web:WidgetTextOut name="Formljhk" property="zxfuf" format="####.00"  /></em>元</span><br>
                <label>&nbsp;</label><span class="orgcolorem">平台管理费：<em><web:WidgetTextOut name="Formljhk" property="ptglf" format="####.00"  /></em>元</span><br>
                <label>&nbsp;</label><span class="orgcolorem">逾期违约金：<em><web:WidgetTextOut name="Formljhk" property="yqwyj" format="####.00"  /></em>元</span><br>
                <label>&nbsp;</label><span class="orgcolorem">逾期管理费：<em><web:WidgetTextOut name="Formljhk" property="yqglf" format="####.00"  /></em>元</span><br>
                <label>&nbsp;</label><span class="orgcolorem">逾期罚息：<em><web:WidgetTextOut name="Formljhk" property="yqfx" format="####.00"  /></em>元</span><br>
                </div>
             </div>
         
        <p class="commit-2"><span class="balance_y">账户余额：<em id="balance"><%=request.getAttribute("availablemoney") %></em>元<span class="spjineyue" style="border-right:none;"><a onclick="chongzi()"  href="Recharge_new.jsp"  class="cz">充值</a></span></span><button id="" name="" type="submit" value="确认">确认</button><span class="questBtn" style="display:none;">余额不足</span><span class="spjine">总计：<em id="repaymoney"><web:WidgetTextOut name="Formljhk" property="oncepunish"  format="####.00"  /></em>元</span></p>
       </form>
       
       
        
   </div>    
     
       
 
     </div>
</div>

<!--一键还清-->
<div class="cd-popup4">
<div class="cd-popup-container4">

   <div class="comnei comneichujie">
       <a class="close cd-popup-close"></a>
       <p class="commit-1"><span>提前结清</span></p>
      <%--  <iframe scrolling="no" frameborder="0" class="ifmbox" width="99%" height="500px" src="yjhk.jsp"></iframe> --%>
      <form action="" name="Formyjjq"  id="Formyjjq" >
<div class="yunull yunullchujie">
          	<div class="boxtitle">
            	<img src="<%=pathUrl %>/images/chujie.png" width="65" height="53">
                <div class="titlebt"><web:WidgetTextOut name="Formyjjq" property="biaoti"  /></div>
                <%-- <div class="titlebt"><a href="#"><web:WidgetTextOut name="Formyjjq" property="businessBM"  /> --%>
                <input type="hidden" id="businessOID" name="businessOID" value=""></a></div>
            </div>
            
            <div class="contentchujie applyzqzrcon">
                <div class="yjhqitems"><label>借款金额</label><span><em><web:WidgetTextOut name="Formyjjq" property="jkje"  /></em>元</span></div>
                <div class="yjhqitems"><label>借款利率</label><span><em><web:WidgetTextOut name="Formyjjq" property="jkll"  /></em>%</span></div>
                <div class="yjhqitems"><label>借款期限</label><span><em><web:WidgetTextOut name="Formyjjq" property="jkqx"  /></em>月</span></div>
                <div class="yjhqitems"><label>借款时间</label><span ><em><web:WidgetTextOut name="Formyjjq" property="jksj"  format="yyyy-MM-dd"/></em></span></div>
               
                <div class="yjhqitems">
                <label>费用组成</label><span class="orgcolorem">应还本金：<em><web:WidgetTextOut name="Formyjjq" property="yhbj2"  /></em>元</span><br>
                <label>&nbsp;</label><span class="orgcolorem">当期利息：<em><web:WidgetTextOut name="Formyjjq" property="yhlx" format="####.00"  /></em>元</span><br>
                <label>&nbsp;</label><span class="orgcolorem">咨询服务费：<em><web:WidgetTextOut name="Formyjjq" property="zxsxf" format="####.00"  /></em>元</span><br>
                <label>&nbsp;</label><span class="orgcolorem">平台管理费：<em><web:WidgetTextOut name="Formyjjq" property="ptglf" format="####.00"  /></em>元</span><br>
                <label>&nbsp;</label><span class="orgcolorem">违约金：<em><web:WidgetTextOut name="Formyjjq" property="wyj" format="####.00"  /></em>元</span><br>
                <label>&nbsp;</label><span class="orgcolorem">罚息：<em><web:WidgetTextOut name="Formyjjq" property="fx" format="####.00"  /></em>元</span><br>
                </div>
             
             </div>
         
        <p class="commit-2"><span class="balance_y">账户余额：<em id="balance"><%=request.getAttribute("availablemoney") %></em>元 <span class="spjineyue" style="border-right:none;"><a onclick="chongzi()"  href="Recharge_new.jsp"  class="cz">充值</a></span></span><button id="" name="" type="submit" value="确认">确认</button><span class="questBtn" style="display:none;">余额不足</span><span class="spjine">总计：<em id="repaymoney"><web:WidgetTextOut name="Formyjjq" property="oncepunish"  format="####.00"  /></em>元</span></p>
       </form>
       
       
        
   </div>    
     
       
 
     </div>
</div>


<div class="indexz">

<!--common header-->
<%@ include file="header.jsp" %>
<!--common end-->
<div class="main minhh">
	<div class="con personcon">
    	<div class="smallnav">
    	<a >当前位置</a><span>:</span><a>个人中心</a><span>&gt;</span><a >我的借款</a><span>&gt;</span><a >还款</a>
        </div>
        <div class="con xqdetial">
    	<%@ include file="left_percenter.jsp" %>
        
    	<div class="xqdeleft my_loan">
        	<div class="my_loantop"><a href="#" class="active">还款</a><span class="L1">&nbsp;</span></div>
            <div class="clear"></div>
            <div>
            <!--出借记录-->
            <div class="loanrecord01">
            <div class="loanrecord">
            <div class="mytzcon">
                	
					 <table width="100%" cellpadding="0" cellspacing="0" align="center">
					 <thead>
					 <tr class="rowbox03 tit HKjilu">
					 <td width="17.2%">借款标题</td>
					 <td width="14.2%">借款金额(元)</td>
					 <td width="12.2%">借款利率</td>
					 <td width="14.2%">到期费用(元)</td>
					 <td width="16.2%">下个还款日</td>
					 <td width="13.0%"></td>
					 <td width="13.0%"></td>
					 </tr>
					 </thead>
					 <tbody>
					 
					 </tbody>
					 
					 </table>
				  	
				  	
                 	<div id="time2" class="hide"></div>
                   <div class="hide_liu HKjilu_liu"></div>	
                </div>
            </div>
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

</div>
<script>
$(function(){
	ajax();
	
})
	 
   function ajax(option){
     var xhr = null;
     if(window.XMLHttpRequest){
       xhr = new window.XMLHttpRequest();
     }else{ // ie
       xhr = new ActiveObject("Microsoft")
     }
     // 通过get的方式请求当前文件
     xhr.open("get","/");
     xhr.send(null);
     // 监听请求状态变化
     xhr.onreadystatechange = function(){
       var time = null,
           curDate = null;
       if(xhr.readyState===2){
         // 获取响应头里的时间戳
         time = xhr.getResponseHeader("Date");
         console.log(xhr.getAllResponseHeaders())
         curDate = new Date(time);
         /* document.getElementById("time2").innerHTML = curDate.getFullYear()+"-"+(curDate.getMonth()+1)+"-"+curDate.getDate();  */
       }
        var time_new_y=curDate.getFullYear();
    	var time_new_m=curDate.getMonth()+1;
	console.log("aaa"+time_new_m);
    	var time_new_d=curDate.getDate();
    	var time_old=$(".time_old").text();
    	var arr = time_old.split("-");
     	var time_old_y=arr[0];
     	var time_old_m=arr[1];
     	var time_old_d=arr[2];
     	var cha_time_m=Number(time_old_m)-Number(time_new_m);
     	if(time_new_y==time_old_y){
     	if((Number(time_old_m)>Number(time_new_m) && Number(time_old_d)>=Number(time_new_d)) || (cha_time_m>1 && Number(time_old_d)<Number(time_new_d)) ){
     		//alert("a");
     		$(".cd-popup6").css("display","none");
     		$(".cd-popup4").css("display","none");
     		$(".confirmbtn2").removeClass("cd-popup-trigger6");
     		$(".confirmbtn3").removeClass("cd-popup-trigger4");
     		$(".confirmbtn2,.confirmbtn3").css({"background":"#ababab","pointer-events":"none"});
     		
     	}
     	}else if(Number(time_old_y)>Number(time_new_y)){
     		if((Number(time_old_m)<Number(time_new_m) && Number(time_old_d)>=Number(time_new_d)) || (Math.abs(cha_time_m)<11 && Number(time_old_d)<Number(time_new_d)) ){
         		//alert("a");
         		$(".cd-popup6").css("display","none");
         		$(".cd-popup4").css("display","none");
         		$(".confirmbtn2").removeClass("cd-popup-trigger6");
         		$(".confirmbtn3").removeClass("cd-popup-trigger4");
         		$(".confirmbtn2,.confirmbtn3").css({"background":"#ababab","pointer-events":"none"});
         		
         	}
     	}
     	
     }
    
 	//alert(time_new_m);
   }
</script>
<script>
$(function(){
	$.ajax({
        type: "post",
        url: "/portal/detial/islogin",
        dataType: "json",
        success: function(result){
        	var result=result.dataValue;
        	$("#Formljhk #balance").text(result);
        	$("#Formyjjq #balance").text(result);
        	$("#Formyqhk #balance").text(result);
                		        	
        },
		error:function(result){ 
    	}
    });
    $.ajax({
        type: "post",
        url: "/portal/personal/repaymentBack",
        dataType: "json",
        success: function(result){
        	var result=result.dataValue;
        	if(result.length<1){
        		$(".HKjilu_liu").css("display","block");
        	}
        	for(var i=0;i<result.length;i++){
        		var jkmc=result[i].businessName;
        		jkmc=jkmc.substring(0,3);
        		var str='<tr class="rowbox03 value"><td>'+result[i].businessName+'</td><td>'+(result[i].money).toFixed(2)+'</td><td>'+(result[i].debitRate).toFixed(2)+'%</td><td>'+(result[i].allmoney).toFixed(2)+'</td><td class="time_old">'+result[i].nextSJ+'</td>';
        		if(result[i].yuqiflag=='yuqi'){
        			if(jkmc=='IFT'){
        				str+='<td></td>';
        			}else{
        				str+='<td> <a href="#"  class="yuqi confirmbtn1">逾期还款</a> </td>';
        			}
        		}else{
        			str+='<td> <a href="#"  class="cd-popup-trigger6  confirmbtn2" >还款</a> </td>';
        		}
        		if(result[i].yuqiflag=='yuqi'){
        			str+='<td></td>';
        		}else{
        			if(jkmc=='IFT'){
        				str+='<td></td>';
        			}else{
        				str+='<td> <a href="#" class="cd-popup-trigger4 confirmbtn3" >提前结清</a> </td>';
        			}
        		}
        		str+='</tr>';
        		$(".mytzcon tbody").append(str);
        		
        		$("#Formljhk .titlebt").text(result[i].businessName);
        		$("#Formljhk #planOID").text(result[i].planOID);
        		$("#Formljhk .yjhqitems").eq(0).find("em").text((result[i].money).toFixed(2));
        		$("#Formljhk .yjhqitems").eq(1).find("em").text((result[i].debitRate).toFixed(2));
        		$("#Formljhk .yjhqitems").eq(2).find("em").text(result[i].term);
        		$("#Formljhk .yjhqitems").eq(3).find("em").text(result[i].loanSJ);
        		$("#Formljhk .yjhqitems").eq(4).find("em").text((result[i].yhje).toFixed(2));
        		$("#Formljhk #repaymoney").text((result[i].yhje).toFixed(2));
        		// 提前
        		$("#Formyjjq .titlebt").text(result[i].businessName);
        		$("#Formyjjq #planOID").text(result[i].businessOID);
        		$("#Formyjjq .yjhqitems").eq(0).find("em").text(result[i].money);
        		$("#Formyjjq .yjhqitems").eq(1).find("em").text((result[i].debitRate).toFixed(2));
        		$("#Formyjjq .yjhqitems").eq(2).find("em").text(result[i].term);
        		$("#Formyjjq .yjhqitems").eq(3).find("em").text(result[i].loanSJ);
        		$("#Formyjjq .orgcolorem").eq(0).find("em").text((result[i].yhbj).toFixed(2));
        		$("#Formyjjq .orgcolorem").eq(1).find("em").text((result[i].interestMoney).toFixed(2));
        		$("#Formyjjq .orgcolorem").eq(2).find("em").text((result[i].zxsxf).toFixed(2));
        		$("#Formyjjq .orgcolorem").eq(3).find("em").text((result[i].ptglf).toFixed(2));
        		$("#Formyjjq .orgcolorem").eq(4).find("em").text((result[i].wyj).toFixed(2));
        		$("#Formyjjq .orgcolorem").eq(5).find("em").text((result[i].yqfx).toFixed(2));
        		$("#Formyjjq #repaymoney").text((result[i].oncePay).toFixed(2));
        		
        		// 逾期
        		$("#Formyqhk .titlebt").text(result[i].businessName);
        		$("#Formyqhk #planOID").text(result[i].businessOID);
        		$("#Formyqhk .yjhqitems").eq(0).find("em").text((result[i].money).toFixed(2));
        		$("#Formyqhk .yjhqitems").eq(1).find("em").text((result[i].debitRate).toFixed(2));
        		$("#Formyqhk .yjhqitems").eq(2).find("em").text(result[i].term);
        		$("#Formyqhk .yjhqitems").eq(3).find("em").text(result[i].loanSJ);
        		$("#Formyqhk .orgcolorem").eq(0).find("em").text((result[i].yhje).toFixed(2));
        		$("#Formyqhk .orgcolorem").eq(1).find("em").text((result[i].yqlx).toFixed(2));
        		$("#Formyqhk .orgcolorem").eq(2).find("em").text((result[i].zxsxf).toFixed(2));
        		$("#Formyqhk .orgcolorem").eq(3).find("em").text((result[i].ptglf).toFixed(2));
        		$("#Formyqhk .orgcolorem").eq(4).find("em").text((result[i].yqwyj).toFixed(2));
        		$("#Formyqhk .orgcolorem").eq(5).find("em").text((result[i].yqglf).toFixed(2));
        		$("#Formyqhk .orgcolorem").eq(6).find("em").text((result[i].yqfx).toFixed(2));
        		$("#Formyqhk #repaymoney").text((result[i].oncePay).toFixed(2));
        		
        	}
        	
                		        	
        },
		error:function(result){    	
    	}
    });
    
});
$(function(){
	$(".commit-2").each(function(){
		var repaymoney = $(this).find("#repaymoney").text();//应还金额
    	var balance = $(this).find("#balance").text();//账户余额    	
    	if(Number(balance)<Number(repaymoney)){
    		$(this).find("span.spjineyue").css("display","block");
    		$(this).find("button").css({"background":"#ababab","pointer-events":"none"});
    		$(this).find("button").text("余额不足");
    		$(this).find("button").css("display","none").siblings(".questBtn").css("display","block");
    	}  
	});
    	  	
    })
    //
    	$(".confirmbtn1,.confirmbtn2,.confirmbtn3").click(function(){
					$(this).attr("disabled","disabled");
				});
				
//逾期还款打开窗口
$(document).on('click','.confirmbtn1', function(event){
    event.preventDefault();
    $('.cd-popup1').addClass('is-visible1');
    //$(".dialog-addquxiao").hide()
});

//打开窗口
$(document).on('click','.cd-popup-trigger6',function(event){
    event.preventDefault();
    $('.cd-popup6').addClass('is-visible1');
    //$(".dialog-addquxiao").hide()
});

//打开窗口
$(document).on('click','.cd-popup-trigger4', function(event){
    event.preventDefault();
    $('.cd-popup4').addClass('is-visible1');
    //$(".dialog-addquxiao").hide()
});
</script>


<%}catch(Exception e){
	response.sendRedirect("error.jsp");
    return;  
} %>

</body>

  <!-- <script>
    jQuery(function($) {
	  $('input[type="checkbox"]').onoff();
	});
   
  </script> 
<script src="/js/tabmenu.js"></script>-->

</html>
