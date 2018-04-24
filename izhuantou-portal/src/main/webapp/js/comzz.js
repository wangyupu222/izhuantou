// JavaScript Document

/*2016-9-23=======================================================================**/
 /*弹框JS内容*/
    jQuery(document).ready(function($){
      
		
		
		//================出借弹窗===================
        //出借详情页右侧小窗输入金额时下方显示计算结果
        $("#jine").keyup(function(){
        	var czjine = Number($('#jine').val());//输入的金额
        	var zerostr = $('#jine').val();
        	var sykt = Number($('#sykt em').text());//剩余可投
        	//alert(sykt);
        	if(zerostr!=""){
        		if(czjine==0){
            		$('#jine').val(100);
            		alert("请输入大于0的数字");
            		
            	}else if(czjine>sykt){
            		 $('#jine').val(sykt);
            		 czjine = Number($('#jine').val());//输入的金额
            	}
        	}
        	
        	
        	var cjqx = $('#data-cjqx').text();//出借期限
        	var yjnh = $('#data-yjnh').text();//预计年化(百分比)
        	var temp = (czjine*yjnh/12*cjqx/100).toFixed(2);//保留两位小数（预期收益=输入的金额*年化收益率/12*期限/100）
        	$('#calc-zonge').text(temp);   	
        	//新手标按天计算
        	var tempnew = (czjine*yjnh/365*cjqx/100).toFixed(2);//保留两位小数（预期收益=输入的金额*年化收益率/365天*期限/100）
        	$('#calc-zongenew').text(tempnew);  
        	});
        //标的详情页右侧的余额全投功能
        $('.touzinput span#yueall').click(function(){
        	var czjine= $('#zhyue em').text();//账户余额
        	var sykt = $('#sykt em').text();//剩余可投
        	var cjqx = $('#data-cjqx').text();//出借期限
        	var yjnh = $('#data-yjnh').text();//预计年化(百分比)
        	//如果余额大于剩余可投.余额全投变为剩余可投的全部金额
        	if(Number(czjine)>Number(sykt)){
        		$('#jine').val(sykt);
        	}else{
        		$('#jine').val(czjine);
        	}
        	
        	var jssr=$('#jine').val();	//余额全投后输入框的金额
        	//标按月计算
        	var temp = (Number(jssr)*yjnh/12*cjqx/100).toFixed(2);//保留两位小数（预期收益=输入的金额*年化收益率/12*期限/100）
        	$('#calc-zonge').text(temp); 
        	//新手标按天计算
        	var tempnew = (Number(jssr)*yjnh/365*cjqx/100).toFixed(2);//保留两位小数（预期收益=输入的金额*年化收益率/365天*期限/100）
        	$('#calc-zongenew').text(tempnew); 
        	
        })
		//打开窗口
        $('.cd-popup-trigger2').on('click', function(event){
            event.preventDefault();
            var czjine = $('#jine').val();//出借详情页获取右侧小窗输入的金额 
            var tanchu=true;
            //var cjqx = $('#data-cjqx').text();//出借期限
            //var yjnh = $('#data-yjnh').text();//预计年化
            var yqsy = $('#calc-zonge').text();//预期收益(上边输入金额时计算得到的)
            var yqsynew = $('#calc-zongenew').text();//新手的预期收益(上边输入金额时计算得到的)
            var zhye = Number($('#zhyue em').text());//账户余额
            if(zhye!=0){            	
	            if (czjine!=""){
	            	var num = Number($('#jine').val());//输入的金额
	            	var sykt = Number( $('#sykt em').text());//剩余可投
	            	if(num<100){
	            		if(sykt<100){
	            			
	            		}else{
	            		if(num==sykt){}
	            		else{
	            			$('#jine').val(100);
	            			tanchu=false;
	            			alert("最小可投金额不能少于100元！");
	            		}}
	            	}else{
	            		var xxx=parseInt(num/100);            		
	            		var yyy=num%100;
	            		var zzz=(xxx+1)*100;
	            		if(yyy>0){
	            			//tanchu=false;
	            			//$('#jine').val(zzz);
	            			//alert("投资金额必须是100的倍数！");
	            		}
	            	}
	            if(tanchu){
	            	$('.cd-popup2').addClass('is-visible1');
	            }
	            
	            //$(".dialog-addquxiao").hide()            
	    		$("#tk-cjjine").text(czjine);//将上面获取的金额放到立即出借弹框的出借金额
	    		$("#amount").val(czjine);
	    		$("#paymoney").text(czjine);
	    		
	    		
	    		//$("#tk-yjnh").text(yjnh);//出借弹框的预期年化
	    		//$("#tk-cjqx").text(cjqx);//出借弹框的出借期限
	    		$("#tk-yqsy").text(yqsy);//出借弹框的预期收益
	    		$("#tk-yqsynew").text(yqsynew);//出借弹框的预期收益
	    		
	    		
	            }else{
	            	alert("请输入金额");
	            }
            }else{
            	alert("余额为0，请充值！");
            }
        });
        //关闭窗口
        $('.cd-popup2').on('click', function(event){
            if( $(event.target).is('.cd-popup-close') || $(event.target).is('.cd-popup2') ) {
                event.preventDefault();
                $(this).removeClass('is-visible1');
            }
        });
		
		//确认关闭
		/*$('.cd-popup2').on('click', function(event){
            if( $(event.target).is('.colseok') || $(event.target).is('.cd-popup2') ) {
                event.preventDefault();
                $(this).removeClass('is-visible1');
            }
        });*/
		
        //ESC关闭
        $(document).keyup(function(event){
            if(event.which=='27'){
                $('.cd-popup2').removeClass('is-visible1');
            }
        });
		//================一键还清弹窗===================
		
	
    });
	

