(function() {
jQuery(document).ready(function($){
	$(".close_win img").click(function(){
		//alert("aa");
		$(".activity_btn").fadeOut(600);
	})
		
		//打开窗口
        /*$('.activity_btn .float_win').on('click', function(event){
            event.preventDefault();
            $("#trigger-overlay").fadeOut(500);
            $('.cd-popup3').addClass('is-visible3');
            //$(".dialog-addquxiao").hide()
        });*/
        //关闭窗口
        $('.cd-popup3').on('click', function(event){
            if( $(event.target).is('.cd-popup-close img') || $(event.target).is('.cd-popup3') ) {
                event.preventDefault();
                $("#trigger-overlay").fadeIn(200);
                $(this).removeClass('is-visible3');
                //alert("a")
            }
        });
        //ESC关闭
        $(document).keyup(function(event){
            if(event.which=='27'){
            	$("#trigger-overlay").fadeIn(200);
                $('.cd-popup3').removeClass('is-visible3');
            }
        });
        
      //关闭窗口
        $('.cd-popup_HX').on('click', function(event){
            if( $(event.target).is('.cd-popup-close img') || $(event.target).is('.cd-popup_HX') ) {
                event.preventDefault();
                $("#trigger-overlay").fadeIn(200);
                $(this).removeClass('is-visible1');
            }
        });
        //ESC关闭
        $(document).keyup(function(event){
            if(event.which=='27'){
            	$("#trigger-overlay").fadeIn(200);
                $('.cd-popup_HX').removeClass('is-visible1');
            }
        });

        /*  存储cooking  */
		var activity_text=$(".activity_text span").attr('data-val');
		//alert(activity_text);
		var oHeight=$(window).height();
		//alert(oHeight)
		//$(".activity_wrap").css("height",oHeight);
		//alert(activity_text);
		if(activity_text>=5000){
		var res_new=document.cookie.indexOf("SYcookie3=");
		//alert(document.cookie);
		//alert("当前cookies="+"("+res_new+")");
		//判断是否来过
		if(res_new==-1){
			var url=location.href;
			//alert("url"+url)
			var lastStr=url.substring(url.length-13);
			if(lastStr!="ServletAction"){
				//$('.activity_btn .float_win').click();
				$(".cd-popup3").addClass("is-visible3");
				$("#trigger-overlay").fadeOut(500);
			}
			var oDate=new Date();
			oDate.setDate(oDate.getDate()+30);
			document.cookie="SYcookie3=admin;expires="+oDate;
		}
		
		}
		
		
    });
	
	
	
})();