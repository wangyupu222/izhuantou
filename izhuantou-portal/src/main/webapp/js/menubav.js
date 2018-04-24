// JavaScript Document
$(function(){
	$('.menuDiv h3').next('ul').children('li').height(0);
	$('.menuDiv h3').next('ul').children('li').css('border-bottom','0px');
	var i=1;
	$('.menuDiv h3').click(function(){
		if(i==1){
			$(this).next('ul').children('li').animate({height:"40px"});
			$(this).next('ul').children('li').css('border-bottom','1px solid #e2e2e2;');
			i+=1;
			}else{
				$(this).next('ul').children('li').animate({height:"0px"});
				$(this).next('ul').children('li').css('border-bottom','0px');
				i-=1;
				}
		//alert('ok');
		
		
		})
	})