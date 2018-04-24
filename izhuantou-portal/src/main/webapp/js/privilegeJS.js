$(function(){
	//   特权模块 
    var oNum_text;
    $(".cd-popup-trigger2").click(function(){
    	var amount=Number($("#jine").val());
    	$(".tq_lowAmount").each(function(){
    	var tq_lowAmount=Number($(this).val());
    	if(amount<tq_lowAmount){
    		$(this).parent(".redbagimg").parent(".redbag01").addClass("hide");
    		//alert($(this).parent(".imggray").parent(".redbagimg").parent(".redbag01").index());
    	}else{
    		$(this).parent(".redbagimg").parent(".redbag01").removeClass("hide");
    	}
    	})
    	var oNum_wrap=$(".redbag01").length;
        oNum_text=oNum_wrap-$(".redbag01.hide").length;
        if(oNum_text>0){
        	$(".tq_btn").addClass("tq_btn_click");
        	$(".tq_btn").text("当前有"+oNum_text+"种加息券可选择 ");
        	$(".oNumber_text").text(oNum_text);
        }
        $(".tq_btn.tq_btn_click").click(function(){
        	$(".tq_box").css("display","block");
        })
    })
   

    $(".redbag01").click(function(){
    	$(this).addClass('active').siblings().removeClass('active');
    	//$(this).find(".redbagimg .imggray .active_click").css("display","block");
    	var OtqOid=$(this).find(".redbagimg").find(".tqoid_old").val();
    	var oMappingOID=$(this).find(".redbagimg").find(".mapoid_old").val();
    	//alert(oMappingOID);
    	$("#tqID").val(OtqOid);
    	$("#mappingID").val(oMappingOID);
    	$(".ensure_btn").addClass("ensure_btn_click");
    	var oInterest=$(this).find(".redbagimg .imggray .textone .hbtop").find(".leftboxhb").text();
    	var oName=$(this).find(".redbagimg .imggray .textone .hbtop").find(".chrome_dl_tit dd").eq(0).text();
    	$(".tq_btn").text("已选择："+oName);
    	if($(".tq_privilegeType").val()==1){
    	var interest_new=Number($(this).find(".leftboxhb span").text()/100);
    	var money_new=Number($("#tk-cjjine").text());
    	var date_jq=Number($(this).find(".date_jq").text());
    	var over_money=money_new*interest_new/365*date_jq;
    	$("#e_money").text(over_money.toFixed(2));
    	$(".e_money_wrap").css("display","block");
    	}else{
    		$("#e_money").text($(this).find(".leftboxhb span").text());
    		$(".e_money_wrap").css("display","block");
    	}
    	
    	})
    	$(".ensure_btn_click").live("click",function(){
    		$(".tq_box").css("display","none");
    	});
    	
    	$(".tzjs caozuo")
    	$(".cancel_btn").click(function(){
    		$("#tqID").val("");
    		$("#mappingID").val("");
    		$(".redbag01").removeClass("active");
    		$(".ensure_btn").removeClass("ensure_btn_click");
    		$(".tq_btn").text("当前有"+oNum_text+"种加息券可选择");
    		$(".tq_box").css("display","none");
    		$(".e_money_wrap").css("display","none");
    		})
    		
    	
    	
    	
    	var isChrome = navigator.userAgent.toLowerCase().match(/chrome/) != null;
if (isChrome) {
        $(".hbtop dl").addClass("chrome_dl");
        $(".hbbottom").addClass("chrome_hbbottom");
}
    
	//  特权模块end 
})

function decimal(num,v){
var vv = Math.pow(10,v);
return Math.round(num*vv)/vv;
}