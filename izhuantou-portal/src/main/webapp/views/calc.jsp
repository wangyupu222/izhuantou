<%@ page contentType="text/html; charset=UTF-8" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>砖头网-收益计算器</title>
<link rel="stylesheet" type="text/css" href="/css/style-common.css">
<link rel="stylesheet" type="text/css" href="/css/calc.css?ver=115">
<Link rel="shortcut icon" href="/images/zhuan.ico" />

<script type="text/javascript" src="js/jquery-1.10.1.min.js"></script>
<script src="js/radialIndicator.js"></script>
<script src="js/com.js"></script>

</head>
<body>
<% try{ %>
<!--common header-->
<%@ include file="header.jsp" %>
<!--common end-->
<!--我要出借-->
<div class="main conheight">
<div class="con projectscon">
    	<div class="con smallnav"><a href="#">当前位置</a><span>:</span><a>收益计算器</a> </div>
       	
        <div class="calcbg">
        	<div class="calatop">
            
            <ul>
            <li class="fir"><label>出借金额</label><input type="text" class="" placeholder="请输入出借金额"  onkeyup="value=value.replace(/[^\d]/g,'')"></li>
            <li><label>还款方式</label><select class="xialakuang" id="xialakuang"><option value="1">按月付息</option><option value="2"  selected = "selected">一次性还本付息</option><option value="3">等本等息</option></select></li>
            <li><a class="calc-a">开始计算</a></li>
            </ul>
            
            <div class="clear"></div>
            <!-- 滑块 -->
            <div class="cjqx">出借期限</div>
            <div class="project-screening project-screening01">
			    <div class="project-screening-yellow"></div>
			    <div class="select-1-yellow"></div>
			    <div class="screening-select select-1 current"><a href="javascript:void(0)">6&nbsp;&nbsp;月</a><span class="new new1"></span></div>
			    <div class="screening-select select-2" selval="4,6"><a href="javascript:void(0)" selval="4,6">12&nbsp;&nbsp;月</a><span class="new new2"></span>
			    </div>
			    <div class="screening-select select-3" selval="6,7"><a href="javascript:void(0)" selval="6,7">24&nbsp;&nbsp;月</a><span class="new new3"></span>
			    </div>
			    
			</div>
            <div class="project-screening project-screening02" style="display:block">
			    <div class="project-screening-yellow"></div>
			    <div class="select-1-yellow"></div>
			    <div class="screening-select select-1 current"><a href="javascript:void(0)">1&nbsp;&nbsp;月</a><span class="new new1"></span></div>
			    <div class="screening-select select-2" selval="4,6"><a href="javascript:void(0)" selval="4,6">3&nbsp;&nbsp;月</a><span class="new new2"></span>
			    </div>
			    <div class="screening-select select-3" selval="6,7"><a href="javascript:void(0)" selval="6,7">6&nbsp;&nbsp;月</a><span class="new new3"></span>
			    </div>
			    <div class="screening-select select-4" selval="7,10"><a href="javascript:void(0)" selval="7,10">12&nbsp;&nbsp;月</a><span class="new new4"></span>
			    </div>
			    <div class="screening-select select-5" selval="10,-1"><a href="javascript:void(0)"  selval="10,-1">24&nbsp;&nbsp;月</a><span class="new new5"></span></div>
			  
			</div>
            <div class="project-screening project-screening03">
			    <div class="project-screening-yellow"></div>
			    <div class="select-1-yellow"></div>
			    <div class="screening-select select-1 current"><a href="javascript:void(0)">3&nbsp;&nbsp;月</a><span class="new new1"></span></div>
			    <div class="screening-select select-2" selval="4,6"><a href="javascript:void(0)" selval="4,6">6&nbsp;&nbsp;月</a><span class="new new2"></span>
			    </div>
			    <div class="screening-select select-3" selval="6,7"><a href="javascript:void(0)" selval="6,7">9&nbsp;&nbsp;月</a><span class="new new3"></span>
			    </div>
			    <div class="screening-select select-4" selval="7,10"><a href="javascript:void(0)" selval="7,10">12&nbsp;&nbsp;月</a><span class="new new4"></span>
			    </div>
			    
			</div>
			
            
            
            </div>
            <!--计算结果-->
            <div class="calaresult">
            <div class="res-t">计算结果<span>（收益仅供参考，实际收益以最终结果为准！）</span></div>
            <ul>
            <li><span>预期收益</span><div class="divmoney" id="yqsy"><em>0</em>元</div></li>
            <li><span>本息合计</span><div class="divmoney" id="bxhj"><em>0</em>元</div></li>
            <li><span>月收本息</span><div class="divmoney" id="ysbx"><em>0</em>元</div></li>
            </ul>
            </div>
            <div class="mingxi">
            <div class="res-t">回款明细：</div>
            <div class="mingxicon">
            <table width="100%" cellpadding="0" cellspacing="0" id="paymentDetails">
            <tbody>
            <tr class="mxtit">
            <td  width="25%">月份</td>
            <td>本金</td>
            <td>利息</td>
            <td width="25%">合计</td>
            </tr>

            </tbody></table>
            
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

<script type="text/javascript">
$(function(){
	

    var sel=$("#xialakuang");
    var arr=new Array(5);
    var oNew=$(".new");
    var oMonth=["9.00%","10.00%","12.00%"];
    var oMonth2=["7.20%","8.00%","9.50%","11.00%","14.00%"];
    var oMonth3=["9.80%","10.80%","11.80%","12.80%"];
    var index;
    var _parent =  $(".project-screening02");
    /* ----------------------------- */
    
    
    index=$("#xialakuang option:selected").val();
    
        if(index==1){
            arr=oMonth
            $(".project-screening").eq(0).css("display","block").siblings(".project-screening").css("display","none");
            var _parent =  $(".project-screening01");
            _parent.find(".new")[0].innerHTML=arr[0];
            _parent.find(".new").eq(0).show().parent().siblings().find(".new").hide();
        }else if(index==2){
            arr=oMonth2;
            $(".project-screening").eq(1).css("display","block").siblings(".project-screening").css("display","none");
            var _parent =  $(".project-screening02");
            _parent.find(".new")[0].innerHTML=arr[0];
            _parent.find(".new").eq(0).show().parent().siblings(".screening-select").find(".new").hide();
        }else if(index==3){
            arr=oMonth3;
            $(".project-screening").eq(2).css("display","block").siblings(".project-screening").css("display","none");
            var _parent =  $(".project-screening03");
            _parent.find(".new")[0].innerHTML=arr[0];
            _parent.find(".new").eq(0).show().parent().siblings(".screening-select").find(".new").hide();
        }
        //alert(arr);
        
        
    
    /* ------------------------------ */
    $("#xialakuang").change(function(){
       index=$("#xialakuang option:selected").val();
        if(index==1){
            arr=oMonth
            $(".project-screening01 .select-1").click();
            $(".project-screening").eq(0).css("display","block").siblings(".project-screening").css("display","none");
            $(".project-screening01").find(".new")[0].innerHTML=arr[0];
            $(".project-screening01").find(".new").eq(0).show().parent().siblings().find(".new").hide();
            for (var i=0;i<arr.length;i++){
            	$(".project-screening01").find(".new").eq(i).innerHTML=arr[i];
                
            }
        }else if(index==2){
            arr=oMonth2;
            $(".project-screening02 .select-1").click();
            $(".project-screening").eq(1).css("display","block").siblings(".project-screening").css("display","none");
            $(".project-screening02").find(".new")[0].innerHTML=arr[0];
           //alert($(".project-screening02").find(".new")[0].text());
           _parent.find(".new").eq(0).show().parent().siblings().find(".new").hide();
           for (var i=0;i<arr.length;i++){
        	   $(".project-screening02").find(".new").eq(i).innerHTML=arr[i];
               
           }
        }else if(index==3){
            arr=oMonth3;
            //alert(arr)
            $(".project-screening").eq(2).css("display","block").siblings(".project-screening").css("display","none");
            $(".project-screening03 .select-1").click();
            $(".project-screening03").find(".new")[0].innerHTML=arr[0];
            $(".project-screening03").find(".new").eq(0).show().parent().siblings().find(".new").hide();
            for (var i=0;i<arr.length;i++){
            	$(".project-screening02").find(".new").eq(i).innerHTML=arr[i];
                
            }
        }
        
    })
    
    	
    	$(".select-1,.select-2,.select-3,.select-4,.select-5,.select-6").bind("click",function () {
    		//alert("a");
            index=$("#xialakuang option:selected").val();
           if(index==1){
               arr=oMonth
           }else if(index==2){
               arr=oMonth2;
           }else if(index==3){
               arr=oMonth3;
           }
           var _parent = $(this);
           var new_i=($(this).index())-2;
           $(this).find(".new")[0].innerHTML=arr[new_i];
           $(this).find(".new").show().parent().siblings().find(".new").hide();
           var _postX = _parent.position().left;
           _parent.siblings(".screening-select").removeClass("current");
           _parent.addClass("current");
           _parent.siblings(".project-screening-yellow").animate({width: _postX}, 300);
           _parent.siblings(".select-1-yellow").animate({left: _postX - 5}, 300);
           _parent.prevAll(".screening-select").css("background", "none");
           _parent.nextAll().removeAttr("style");
       });
    
     $(".calc-a").click(function () {
    	

     		$('#paymentDetails tr:not(:first)').remove();
       	 //月份
       	 var month;
       	var interest;
     		if(index==1){
     			month = $(".project-screening01 .current a").text().replace("月","");
     			interest = $(".project-screening01 .current span").text().replace("%","");
            }else if(index==2){
            	month = $(".project-screening02 .current a").text().replace("月","");
            	interest = $(".project-screening02 .current span").text().replace("%","");
            }else if(index==3){
            	month = $(".project-screening03 .current a").text().replace("月","");
            	interest = $(".project-screening03 .current span").text().replace("%","");
            }
       	
       	 //alert(month);
       	 //利息
       	
       	 //本金
    	 var principal =$("*.fir input").val();
       	 
       	 if(principal != ""){
           	 index=$("#xialakuang option:selected").val();
           	 //计算利息
           	var allInterest =  (principal*(interest/100)/12)*month;
           	 //计算所有本息
           	var allMoney = parseFloat(principal)+parseFloat(allInterest);
           	$("#yqsy em").text(allInterest.toFixed(2));
           	$("#bxhj em").text(allMoney.toFixed(2));
           	if(index ==1){
           		$("#ysbx em").text((allInterest/month).toFixed(2));
           		for(var q=1;q<parseInt(month)+1;q++){
           			if(q==parseInt(month)){
           				//最后一个月应还本息
           				var total = parseFloat(principal)+parseFloat(allInterest/month);
           				$("#paymentDetails").append("<tr><td>"+q+"</td><td>"+(parseFloat(principal)).toFixed(2)+"</td><td>"+(allInterest/month).toFixed(2)+"</td><td>"+total.toFixed(2)+"</td></tr>")
           			}else{
           				$("#paymentDetails").append("<tr><td>"+q+"</td><td>0.00</td><td>"+(allInterest/month).toFixed(2)+"</td><td>"+(allInterest/month).toFixed(2)+"</td></tr>")
           			}
           			
           		}	
           	}else if(index ==2){
               	$("#ysbx em").text(0);
               	for(var q=1;q<parseInt(month)+1;q++){
               		if(q==parseInt(month)){
           				$("#paymentDetails").append("<tr><td>"+q+"</td><td>"+(parseFloat(principal)).toFixed(2)+"</td><td>"+allInterest.toFixed(2)+"</td><td>"+allMoney.toFixed(2)+"</td></tr>")
           			}else{
           			$("#paymentDetails").append("<tr><td>"+q+"</td><td>0.00</td><td>0.00</td><td>0.00</td></tr>")}
           		}
           	}else if(index ==3){
               	$("#ysbx em").text((allMoney/month).toFixed(2));
               	for(var q=1;q<parseInt(month)+1;q++){
           				$("#paymentDetails").append("<tr><td>"+q+"</td><td>"+(principal/month).toFixed(2)+"</td><td>"+(allInterest/month).toFixed(2)+"</td><td>"+(allMoney/month).toFixed(2)+"</td></tr>")
           		}
           	}
        	 
       	 }


    
    	
    	
    	 
     })
})
</script>
</html>
