// JavaScript Document

/*2016-9-23=======================================================================**/
 /*弹框JS内容*/
    jQuery(document).ready(function($){		
		
		//================出借弹窗===================
        //出借详情页右侧小窗输入金额时下方显示计算结果
    	/*$(".tzjs").before("<div class='hide' id='hhproductStatus'></div>");*/
        $("#jine").keyup(function(){
        	var czjine = Number($('#jine').val());//输入的金额
        	var zerostr = $('#jine').val();
        	var sykt = Number($('#sykt em').text());//剩余可投
        	var user_money= $('#zhyue em').text();  //账户余额
        	//alert(sykt);
        	if(zerostr!=""){
        		if(sykt==0 || sykt<18888){
        			$('#jine').val(0);
        		}else{
        			if(czjine==0){
            			if(sykt==0){
            				$('#jine').val(0);
            			}else{
            				$('#jine').val(18888);
            				czjine = Number($('#jine').val());
            			}
            				
                	}else if(czjine>sykt){
                		 $('#jine').val(18888);
                		 czjine = Number($('#jine').val());//输入的金额
                	}else{
                		$('#jine').val(18888);
               		 	czjine = Number($('#jine').val());//输入的金额
                	}
        		}
        	}
        	
        	
        	var cjqx = $('#data-cjqx').text();//出借期限
        	var yjnh = $('#data-yjnh').text();//预计年化(百分比)   	
        	//新手标按天计算
        	var tempnew = (czjine*yjnh/365*cjqx/100).toFixed(2);//保留两位小数（预期收益=输入的金额*年化收益率/365天*期限/100）
        	$('#calc-zongenew').text(tempnew);  
        	});
        //标的详情页右侧的余额全投功能
        $('.touzinput span#yueall').click(function(){
        	var czjine= $('#zhyue em').text();//账户余额
        	czjine= $.trim(czjine);
        	var sykt = $('#sykt em').text();//剩余可投
        	var cjqx = $('#data-cjqx').text();//出借期限
        	var yjnh = $('#data-yjnh').text();//预计年化(百分比)
        	//var monthORday = $("#monthORday").val();//按年算还是天算(天是365,年是12)
        	//alert(monthORday);
        	//如果余额大于剩余可投.余额全投变为剩余可投的全部金额
        	if(sykt==0 ||sykt<18888 ){
				$('#jine').val(0);
			}else{
				$('#jine').val(18888);
			}     	
        	var jssr=$('#jine').val();//余额全投后输入框的金额	   	
        	//新手标按天计算
        	var tempnew = (Number(jssr)*yjnh/365*cjqx/100).toFixed(2);//保留两位小数（预期收益=输入的金额*年化收益率/365天*期限/100）
        	$('#calc-zongenew').text(tempnew); 
        	
        })
		//打开窗口
        $('.cd-popup-trigger2').on('click', function(event){
            event.preventDefault();
            var czjine = $('#jine').val();//出借详情页获取右侧小窗输入的金额 
            var tanchu=true;
            var yqsy = $('#calc-zonge').text();//预期收益(上边输入金额时计算得到的)
            var yqsynew = $('#calc-zongenew').text();//新手的预期收益(上边输入金额时计算得到的)
            var zhye = Number($('#zhyue em').text());//账户余额
            var shengyu_tk = Number($('#sykt em').text());//剩余可投
            shengyu_tk = $.trim(shengyu_tk);           	
	            if (czjine!=""){
	            	var num = Number($('#jine').val());//输入的金额
	            	var sykt = Number( $('#sykt em').text());//剩余可投
	            	if(num<18888){
	            		tanchu=false;
            			$("#hhproductStatus").text("最小可投金额不能少于18888元！");
    	        		$("#hhproductStatus").css("display","block");
	            	}else if(num>18888){
	            		tanchu=false;
	            		$("#hhproductStatus").text("最小可投金额不能大于18888元！");
		        		$("#hhproductStatus").css("display","block");
	            	}
	            if(tanchu){
	            	$('.cd-popup2').addClass('is-visible1');
	            	$("#hhproductStatus").text("");
	            	$("#hhproductStatus").css("display","none");
	            }
	            }else{
	            	$("#hhproductStatus").text("请输入金额");
	        		$("#hhproductStatus").css("display","block");
	            	//alert("请输入金额");
	            }
            
        });
        //关闭窗口
        $('.cd-popup2').on('click', function(event){
            if( $(event.target).is('.cd-popup-close') || $(event.target).is('.cd-popup2') ) {
                event.preventDefault();
                $(this).removeClass('is-visible1');
                window.location.reload();
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





