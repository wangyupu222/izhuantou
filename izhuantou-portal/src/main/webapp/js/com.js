// JavaScript Document
//首页标的切换
$(function(){
	$.ajax({
        type: "get",
        url: "/portal/page/getflink",
        dataType: "json",
        success: function(result){
        	for(var i=0;i<result.dataValue.length;i++){
        		var str="<li><a href='"+result.dataValue[i].linkUrl+"' target='_blank'>"+result.dataValue[i].name+"</a></li>";
        		$("#linkf ul").append(str);
        	}
        },
		error:function(result){    	
    	}
    });
	
	
	$('.promodule a').mouseover(function(){
		$(this).addClass('active').siblings().removeClass('active');
		$('.tabright').eq($('.promodule a').index(this)).show().siblings().hide();
		})
	});

//导航切换样式
$(document).ready(function(){
	$(".navcsr a").each(function(){

	$this = $(this);
	
	if($this[0].href==String(window.location)){
		$this.addClass("active");
	
	}
});
});


//首页媒体切换
$(function(){
	$('.leftnav .tabs').click(function(){
		$(this).addClass('active').siblings().removeClass('active');
		$('.pocon').eq($('.leftnav .tabs').index(this)).show().siblings().hide();
		})
	});
//右侧蓝色滑动
$(function(){
	var i=1;
	$('.linkus').click(function(){
		
		if(i==1){
			$('.blueright').animate({right:'0px'},500);
			var ss =$(this).children('.sptext').text('>>');
			i+=1;
			}
		else{
			$('.blueright').animate({right:'-234px'},500);
			var ss =$(this).children('.sptext').text('<<');
			i-=1;
			}	
		
		})
	})
//顶部APP客户端鼠标放上显示二维码
$(function(){
	$('.top_right .app-a').mouseover(function(){
		$('.appclient').show();	
	}).mouseout(function(){
		$('.appclient').hide();	
	})
})	
//我要出借页列表面排序点击箭头变换
/* $(function(){	
	 var sortdiv =$(".sortdiv").text();
	 switch (sortdiv){
	 case "null"://默认箭头向上
		 $("#sortnhll").text("↑");
		 $("#sortcjqx").text("↑");
		 $("#sorthkfs").text("↑");
		 $("#sortxmze").text("↑");
		 break;
	 case "nhll2desc"://年华利率降序
		 $("#sortnhll").text("↓");
		 break;
	 case "nhll2dasc"://年华利率升序
		 $("#sortnhll").text("↑");
		 break;
		 
	 case "cjqx2desc"://出借期限降序
		 $("#sortcjqx").text("↓");
		 break;
	 case "cjqx2asc"://出借期限升序
		 $("#sortcjqx").text("↑");
		 break;
		 
	 case "hkfs2desc"://还款方式降序
		 $("#sorthkfs").text("↓");
		 break;
	 case "hkfs2asc"://还款方式升序
		 $("#sorthkfs").text("↑");
		 break;
		 
	 case "xmze2desc"://项目总额降序
		 $("#sortxmze").text("↓");
		 break;
	 case "xmze2asc"://项目总额升序
		 $("#sortxmze").text("↑");
		 break;
	 }
	
})*/

/*$(function(){
	var i=$("tr.sort td a em").index();
	$("tr.sort td em").click(function(){
		alert(i);	
	});
	
	
})*/

//活动页面 抽奖左侧切换
$(function(){
	$('.cj_left .left_top a').click(function(){
		$(this).addClass('active').siblings().removeClass('active');
		$('.left_info').eq($('.cj_left .left_top a').index(this)).show().siblings().hide();
	})
})

$(function(){
	$('.zqjf_tit a').click(function(){
		$(this).addClass('active').siblings().removeClass('active');
		$('.qd').eq($('.zqjf_tit a').index(this)).show().siblings().hide();
	})
})

//我要出借筛选样式
$(function(){
	$('.tabpro a').click(function(){
		$(this).addClass('active').siblings().removeClass('active');
		$('.lcdl').eq($('.tabpro a').index(this)).show().siblings().hide();
		});
	$('.sxqx a').click(function(){
		$(this).addClass('active').siblings().removeClass('active');
		})	
	});
//新手底部切换
$(function(){
	$('.tabox div').click(function(){
		$(this).addClass('active').siblings().removeClass('active');
		$('.picimg').eq($('.tabox div').index(this)).show().siblings().hide();
		})
	})
	
					
//出借详情切换
$(function(){

	$('.tabtitcons div').click(function(){
		$(this).addClass('active').siblings().removeClass('active');
		$('.optabg').eq($('.tabtitcons div').index(this)).show().siblings().hide();
		//alert("aaa:"+$('.tabtitcons div').index(this));
		
		
			
		});
		
		
	});

//账户
$(function(){
	$('#clickbox').click();
	});
	
$(function(){
	$('.dl-ziliao dt a').click(function(){
		$(this).next().click();
		})
	})
	
//个人中心的理财计算器
$(function(){
	$('.calc_top a').click(function(){
		$(this).addClass('active').siblings().removeClass('active');
		$('.calc_info').eq($('.calc_top a').index(this)).show().siblings().hide();
	})
})



//个人中心借款管理
$(function(){
	
	$('.my_loantop a').click(function(){
		$(this).addClass('active').siblings().removeClass('active');
		$('.loanrecord01').eq($('.my_loantop a').index(this)).show().siblings().hide();
	})
})
//6-28	安全信息
// JavaScript Document

/*    修改安全中心        */
$(function(){
	$(".slide_down").click(function(){
		$(this).parent().parent().find(".hide_list").toggle().parent().siblings().find(".hide_list").hide();

	})

})



$(function(){
	
		
		$(".tixian-1").click(function(){
			
		var text=$(".tixian-1").text();
	//alert(text);
		if(text=="修改"){
			var text=$(".tixian-1").text("取消修改");
			$('#xia-4').hide();
			$('#xia-3').show();
			}
		 else if(text=="取消修改"){
			 var text=$(".tixian-1").text("修改");
			 $('#xia-4').hide();
			$('#xia-3').hide();
			 }
		});
	});

$(function(){
			
		
		$(".tixian-2").click(function(){
		
		var text=$(".tixian-2").text();
	//alert(text);
		if(text=="找回"){
			var text=$(".tixian-2").text("取消找回");
			$('#xia-3').hide();
			$('#xia-4').show();
			
			}
		 else if(text=="取消找回"){
			 var text=$(".tixian-2").text("找回");
			$('#xia-3').hide();
			$('#xia-4').hide();
			
			 }
		});
	});
$(function(){
	
		
		$(".mibao-1").click(function(){
			
		var text=$(".mibao-1").text();
	//alert(text);
		if(text=="修改"){
			var text=$(".mibao-1").text("取消修改");
			$('#xia-6').show();
			$('#xia-5').hide();
			}
		 else if(text=="取消修改"){
			 var text=$(".mibao-1").text("修改");
			 $('#xia-6').hide();
			$('#xia-5').hide();
			 }
		});
	});

$(function(){
	//
	//alert(text);
	$(".phone-1").click(function(){
		var text=$(".phone-1").text();
	//alert(text);
		if(text=="修改"){
			var text=$(".phone-1").text("取消修改");
			$('#xia-2').show();
			}
		 else if(text=="取消修改"){
			 var text=$(".phone-1").text("修改");
			$('#xia-2').hide();
			 }
		});
		/**登录*/
		$(".pwdz-1").click(function(){
		var text=$(".pwdz-1").text();
	//alert(text);
		if(text=="修改"){
			var text=$(".pwdz-1").text("取消修改");
			$('#xia-1').show();
			}
		 else if(text=="取消修改"){
			 var text=$(".pwdz-1").text("修改");
			$('#xia-1').hide();
			 }
		});
		/**邮箱*/
		$('.pwdz-yx').click(function(){
			//alert('/');
			var text= $('.pwdz-yx').text();
			if(text=="修改"){
			var text=$(".pwdz-yx").text("取消修改");
			$('#xia-yx').show();
			}
		 else if(text=="取消修改"){
			 var text=$(".pwdz-yx").text("修改");
			$('#xia-yx').hide();
			 }
			})
		
		/**提现*/
		$(".tixian-1").click(function(){
		var text=$(".tixian-1").text();
	//alert(text);
		if(text=="设置"){
			var text=$(".tixian-1").text("取消设置");
			$('#xia-3').show();
			}
		 else if(text=="取消设置"){
			 var text=$(".tixian-1").text("设置");
			$('#xia-3').hide();
			 }
		});
	
	});

/*$(function(){
	$('.ul-le li').click(function(){
		$(this).addClass('active').siblings().removeClass('active');
		})
	})	*/
	
/*	
$(function(){
$(".li-jobs").click(function(){
$(".li-jobs div").addClass("jobs-con hide");
$(this).find("div").removeClass();
$(this).find("div").addClass("jobs-con ");

});
})*/

$(function(){
	$('.li-po-1').click();

})

function fqrun(){
	window.location.href="setok.html";
	
	}
	
$(function(){
	$('.ophu .atab').click(function(){
		$(this).addClass('active').siblings().removeClass('active');
		$('.opkab').eq($('.ophu .atab').index(this)).show().siblings().hide();
		})
	})	
$(function(){
	$(document).on('click','.zk-a',function(){
		var text = $(this).text();
		//alert(text);
		if(text=="查看"){
			$(this).text("收起");
			//$(this).prev().removeClass('active');
			$(this).parents('.boxrow').addClass('active');
			var f =$(this).parents('.boxrowtit');
			//alert(f);
			f.children('.cell02').removeClass('active');
			
			}
		else if(text=="收起"){
			$(this).text("查看");
			$(this).parents('.boxrow').removeClass('active');
			}	
		
		
		})
	})	;
	
	
$(function(){
	$('.tabjk a').mouseover(function(){
		
		$('.optab').eq($('.tabjk a').index(this)).show().siblings().hide();
		})
	})	

//我的红包
$(function(){
	var i=4;
	//$('.hbtab a.active').append('<span>'+i+'</span>');
	$('.hbtab a').click(function(){
		$(this).addClass('active').siblings().removeClass('active');
		//$(this).append('<span>'+i+'</span>');
		//$(this).siblings().children('span').remove();
		$('.box-quan').eq($('.hbtab a').index(this)).show().siblings().hide();
		})
	});
$(function(){
	$('.tithb a').click(function(){
		$(this).addClass('active').siblings().removeClass('active');
		$('.jxqs').eq($('.tithb a').index(this)).show().siblings().hide();
		$('.redbag').eq($('.tithb a').index(this)).show().siblings().hide();
		
		})
	})
	$(function(){
	$('.hbtabcar a').click(function(){
		$(this).addClass('active').siblings().removeClass('active');
		$('.box-quan').eq($('.hbtabcar a').index(this)).show().siblings().hide();
		})
	})	;
	
$(function(){
	$('.carbot .sp02').click(function(){
		alert("确认解绑吗？请致电客服：4008008888");
		})
	})	
	
//首页抽奖
$(function(){
	$('.close').click(function(){
		$(this).parents('.bicj').hide();
		$(this).parents('.bicj').next().show();
		});
	$('.smacj').click(function(){
		$(this).hide();
		$(this).siblings().show();
		})
	$('.downleft a').click(function(){
		$('.consdown').eq($('.downleft a').index(this)).show().siblings().hide();
		})		
	})	;

//输入金额看收益
$(function(){
	$('.jine').blur(function(){
		var aa = $('.jine').val();
		if(aa==''){
			$('.shouyi').text('输入金额查看收益');
			}else{
			$('.shouyi').text('到期利息XX元');	
				}
	
		})
	
	})
//自动投标hover
$(function(){
	$('.zdtb a').hover(function(){
		$(this).children('.tztext').show();
		},function(){
			$(this).children('.tztext').hide();
			})
	})	
	
/**出借窗口**/
$(function(){
	$('#sx').click(function(){
		$('.div-b').show();
		$('.indexz').css({'opacity':'0.3'});
		});
	$("#close").click(function (){
		$("#boxy").hide();
		
		$('.indexz').css({'opacity':'1'});	
		//alert("恭喜您，出借成功！");
		
});
	});	
function commontz(){
						
	
	  
	$('.indexz').css({'opacity':'0.3'});
	$("#boxy").fadeIn();	
						
	};	
/**借贷*/
$(function(){
	$('.navjd div').mouseover(function(){
		$(this).addClass('active').siblings().removeClass('active');
		$('.picjdcon').eq($('.navjd div').index(this)).show().siblings().hide();
		})
	})	

function shenq(){
	window.location="wyjd07.html";
	}	
	
	// JavaScript Document
//顶部APP客户端鼠标放上显示二维码
$(function(){
	$('.top_right .app-a').mouseover(function(){
		$('.appclient').show();	
	}).mouseout(function(){
		$('.appclient').hide();	
	})
})
//登录注册切换
$(function(){
	$('.regtop a').click(function(){
		$(this).addClass('noactive').siblings().removeClass('noactive');
		$('.regtab').eq($('.regtop a').index(this)).show().siblings().hide();
		})
	});
/*头条新闻图片鼠标移上显示标题*/
$(document).ready(function(){
 $(".img_text").css('height','0px;');
 $(".ttnewtit").hover(function(){
  //$(".latest_text",this).slideToggle(300);
  $(this).children(".img_text").stop(true).animate({height:'40px'},200);
  
 },function(){
		$(this).children('.img_text').stop(true).animate({'height':'0px'},200);
			});

});
//首页右侧悬浮 单独小图效果
//在线客服,计算收益,app二维码,微信二维码,QQ二维码
$(function(){
	$('.jsq,.online,.app,.weixin,.qq,.weibo').mouseover(function(){
		if($(this).is('.jsq')){
			$('.jsq img').attr('src','http://img.izhuantou.com/images/xf01.png');
		}
		if($(this).is('.online')){
			$('.online img').attr('src','http://img.izhuantou.com/images/xf002.png');
		}
		if($(this).is('.weibo')){
			$('.weibo img').attr('src','http://img.izhuantou.com/images/xf005.png');
		}
		if($(this).is('.app')){
			$('.app img.xf03').attr('src','http://img.izhuantou.com/images/xf03_hover.png');
			$('.appimg').show();
		}
		if($(this).is('.weixin')){
			$('.weixin img.xf04').attr('src','http://img.izhuantou.com/images/xf004.png');
			$('.weixinimg').show();
		}
		if($(this).is('.qq')){
			//$('.qqimg').show();
			$('.qq img').attr('src','http://img.izhuantou.com/images/xf006.png?v=114');
		}
		
	}).mouseout(function(){
		if($(this).is('.jsq')){
			$('.jsq img').attr('src','http://img.izhuantou.com/images/xf01ac.png');
		}
		if($(this).is('.online')){
			$('.online img').attr('src','http://img.izhuantou.com/images/xf02.png');
		}
		if($(this).is('.weibo')){
			$('.weibo img').attr('src','http://img.izhuantou.com/images/xf05.png');
		}
		if($(this).is('.app')){
			$('.app img.xf03').attr('src','http://img.izhuantou.com/images/xf03.png');
			$('.appimg').hide();
		}
		if($(this).is('.weixin')){
			$('.weixin img.xf04').attr('src','http://img.izhuantou.com/images/xf04.png');
			$('.weixinimg').hide();
		}
		if($(this).is('.qq')){
			//$('.qqimg').hide();
			$('.qq img').attr('src','http://img.izhuantou.com/images/xf06.png');
		}
		
		
		
	})
})
/* 新手标提示 */

$(function(){
	var new_judge=$("#new_judge").val();
	if(new_judge>0){
		$("#new_tou").addClass("yimanbiao");
		$("#new_tou").addClass("tishi_new");
		$("#new_tou").removeClass("cd-popup-trigger2");
		$("#new_tou").removeClass("tzjs");
		$("#new_tou").removeClass("caozuo");
		//$("#new_tou").attr("href","javascript:;")
	}
	$('.tishi_new').on('click', function(event){
        event.preventDefault();
        $('.cd-popup6').addClass('is-visible1');
        //$(".dialog-addquxiao").hide()
    });
	$(".tishi_btn").on('click',function(event){
		event.preventDefault();
		$('.cd-popup6').removeClass('is-visible1');
	})
	
	
})


/* 环环投预约出借提示 */
$(function(){
	var oWidth=($(window).width());
	$(window).resize(function() { 
	oWidth=($(window).width()); 
		
	});
	
	$(".close_icon_01").click(function(){
		$(".HHyuyue_wrap").animate({"left":"-"+oWidth},800,function(){
			$(".HHyuyue_box").hide(300);
			$(".show_hhyu_box").show(400);
		});
		
	})
	$(".show_hhyu").click(function(){
		$(".show_hhyu_box").hide(300);
		$(".HHyuyue_wrap").css("display","block");
		setTimeout(function () {
			$(".HHyuyue_box").show(300);
			$(".HHyuyue_wrap").animate({"left":0},800,function(){

			});

	    },300);
		
	})
	
	
})

/* 新手标提示结束 */
//在线客服
/*$(function(){
	$('.jsq').mouseover(function(){
		$('.jsq img').attr('src','images/xf01.png');
	}).mouseout(function(){
		$('.jsq img').attr('src','images/xf01ac.png');
	})
})
$(function(){
	$('.online').mouseover(function(){
		$('.online img').attr('src','images/xf002.png');
	}).mouseout(function(){
		$('.online img').attr('src','images/xf02.png');
	})
})*/
//微信二维码
/*$(function(){
	$('.weixin').mouseover(function(){
		$('.weixinimg').show();
	}).mouseout(function(){
		$('.weixinimg').hide();
	})
})*/
//QQ二维码
/*$(function(){
	$('.qq').mouseover(function(){
		$('.qqimg').show();
	}).mouseout(function(){
		$('.qqimg').hide();
	})
})*/


//返回顶部
$(document).ready(function(){
	$(".return_top").click(function(){
		$("html,body").animate({"scrollTop":0},1000);
	});
})


$(window).scroll(function(){
// alert("hello");	
							
		 if(getScrollTop()>80){
				 
			     $(".header").addClass("fixed");
				 
			
			}
		else{
			
			
			$(".header").removeClass("fixed");
			
			}		
	});   
function getScrollTop(){   
    	var scrollTop=0;   
   		 if(document.documentElement&&document.documentElement.scrollTop){   
        	scrollTop=document.documentElement.scrollTop;   
  	   }else if(document.body){   
         scrollTop=document.body.scrollTop;   
       }   
          return scrollTop;   
       }


//首页底部qq 微信
//QQ二维码


$(function(){
	$('.weibos').mouseover(function(){
		//$('.qqsimg').show();
		$('.weibos img').attr('src','http://img.izhuantou.com/images/xl01.png');
	}).mouseout(function(){
		//$('.qqsimg').hide();
		$('.weibos img').attr('src','http://img.izhuantou.com/images/xl.png');
	})
})
$(function(){
	$('.qqs').mouseover(function(){
		//$('.qqsimg').show();
		$('.qqs img').attr('src','http://img.izhuantou.com/images/qq01.png');
	}).mouseout(function(){
		//$('.qqsimg').hide();
		$('.qqs img').attr('src','http://img.izhuantou.com/images/qq.png');
	})
})
$(function(){
	$('.weixins').mouseover(function(){
		$('.weixins img.wxs').attr('src','http://img.izhuantou.com/images/wx01.png');
		$('.weixinsimg').show();
	}).mouseout(function(){
		$('.weixins img.wxs').attr('src','http://img.izhuantou.com/images/wx.png');
		$('.weixinsimg').hide();
	})
})



	
//个人中心借款账户
$(function(){
	$('.zczong a').click(function(){
		$(this).addClass('active').siblings().removeClass('active');
		$('.opzhcon').eq($('.zczong a').index(this)).show().siblings().hide();
		})
	})

//我的账户资金明细&借款记录
$(function(){
	$(".zjclass a").click(function(){
		$(this).addClass("active").siblings().removeClass('active');
		$('.mytzcon').eq($('.zjclass a').index(this)).show().siblings().hide();
	})
})	

function truep(){
	$("#boxy").hide();
		
		$('.indexz').css({'opacity':'1'});	
		//alert("恭喜您，出借成功！");
	
	}
//散标出借列表
$(function(){
	/* 个人中心-我的出借环环投选项卡问题  */
		/*$('.huanhuan_loantop a').click(function(){
			$(this).addClass('active').siblings().removeClass('active');
			$('.tabopa').eq($('.my_loantop a').index(this)).show().siblings().hide();
		
		 添加锚点 
		$("#tabInx").attr("value",$('.my_loantop a').index(this));
		var url=location.href;
		//alert("url"+url)
		var lastStr=url.substring(url.length-1);
		//alert("lastStr:"+lastStr);
		if(lastStr=="&"){
			url=url.substring(0,url.length-2);
		}
		else{
			url=url.substring(0,url.length-1);
		}
		
		//alert("url2"+url)
		
		url=url+$('.my_loantop a').index(this);
		//alert("url3"+url)
		window.location.href=url;
		
	})*/
	/* 个人中心-我的出借点点投选项卡问题  */
	/*$('.diandian_loantop a').click(function(){
		$(this).addClass('active').siblings().removeClass('active');
		$('.tabopa').eq($('.my_loantop a').index(this)).show().siblings().hide();
		
		 添加锚点 
		$("#tabInx").attr("value",$('.my_loantop a').index(this));
		var url=location.href;
		//alert("url"+url)
		var lastStr=url.substring(url.length-1);
		//alert("lastStr:"+lastStr);
		if(lastStr=="&"){
			url=url.substring(0,url.length-2);
		}
		else{
			url=url.substring(0,url.length-1);
		}
		
		//alert("url2"+url)
		
		url=url+$('.my_loantop a').index(this);
		//alert("url3"+url)
		window.location.href=url;
		
	})*/
	
	$('.my_loantop a').click(function(){
		$(this).addClass('active').siblings().removeClass('active');
		$('.tabopa').eq($('.my_loantop a').index(this)).show().siblings().hide();
		})
		
	})	
	
	
//修改头像
function picimg(){
	$('.picimgnow').click();
	}	

//验证手机号

$(function(){
	var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/; 
	//var pnone= $(".phone").val();
	$("input.phoneinp").blur(function(){
		if(!myreg.test($(".phoneinp").val())) 
{ 
	
   $(this).val(null);
   $(this).addClass('active');
   $(this).attr('placeholder','请输入正确的手机号');
   $(this).next().show();
    return false; 
} else{
	$(this).removeClass('active');
	 $(this).next().hide();
	}
		
		})

	})	
	
$(function(){
	$('.inpbox').click(function(){
		$(this).addClass('active').siblings().removeClass('active');
		})
		$('.regist_inpt').click(function(){
		$(this).addClass('active').siblings().removeClass('active');
		})
		
		$(".regist_inpt input").focus(function(){
       $(this).parent().addClass("active");
    })
    $(".regist_inpt input").blur(function(){
       $(this).parent().removeClass("active");
    })
    
    $(".inpbox input").focus(function(){
       $(this).parent().addClass("active");
    })
    $(".inpbox input").blur(function(){
       $(this).parent().removeClass("active");
    })
		
    
	})	
	
	


	
//折价转让

$(function(){
	$('.zejia').click(function(){
		
		$('.commit').show();
		$('.indexz').css({'opacity':'0.65'});
		});
		
	$('.listzjzr input').click(function(){
		$(this).addClass('active');
		});
	$('.listzjzr input').blur(function(){
		$(this).removeClass('active');
		})		
	})	
	
/**一键结清**/
$(function(){
	$('.yjjq a').click(function(){
		$('.commit').show();
		$('.indexz').css({'opacity':'0.65'});
		})
	})	


//底部APP客户端
$(function(){
	/*$('.b_android').mouseover(function(){
		$('.fot_erweima img').attr('src','images/app.jpg');
	}).mouseout(function(){
		$('.fot_erweima img').attr('src','images/app.jpg');
	}),*/
	$('.b_iphone').mouseover(function(){
		$('.fot_erweima img').attr('src','images/app02.jpg');
		$('.threeleft').addClass('iphone');
	}).mouseout(function(){
		$('.fot_erweima img').attr('src','images/app.jpg');
		$('.threeleft').removeClass('iphone');
	})
})
/*2016-9-23=======================================================================**/
 /*弹框JS内容*/
    jQuery(document).ready(function($){
      
		
		//打开窗口
        $('.cd-popup-trigger1').on('click', function(event){
            event.preventDefault();
            $('.cd-popup1').addClass('is-visible1');
            //$(".dialog-addquxiao").hide()
        });
        $('.searchbtn').on('click', function(event){
            event.preventDefault();
            $('.cd-popup1').addClass('is-visible1');
            //$(".dialog-addquxiao").hide()
        });
        //关闭窗口
        $('.cd-popup1').on('click', function(event){
            if( $(event.target).is('.cd-popup-close') || $(event.target).is('.cd-popup1') ) {
                event.preventDefault();
                $(this).removeClass('is-visible1');
                parent.location.reload();//刷新父亲对象（用于框架）
            }
        });
		
		//确认关闭
		$('.cd-popup1').on('click', function(event){
            if( $(event.target).is('.colseok') || $(event.target).is('.cd-popup1') ) {
                event.preventDefault();
                $(this).removeClass('is-visible1');
                
            }
        });
		
        //ESC关闭
        $(document).keyup(function(event){
            if(event.which=='27'){
                $('.cd-popup1').removeClass('is-visible1');
                //parent.location.reload();
                //window.location.reload();
            }
        });
		
		//================出借弹窗===================
        //参见chujieTK.js
		//================一键还清弹窗===================
        
        $('#tiyan_btn').on('click', function(event){
            event.preventDefault();
            $('.cd-popup4').addClass('is-visible1');
            //$(".dialog-addquxiao").hide()
        });
		//打开窗口
        $('.cd-popup-trigger4').on('click', function(event){
            event.preventDefault();
            $('.cd-popup4').addClass('is-visible1');
            //$(".dialog-addquxiao").hide()
        });
        //关闭窗口
        $('.cd-popup4').on('click', function(event){
            if( $(event.target).is('.cd-popup-close') || $(event.target).is('.cd-popup2') ) {
                event.preventDefault();
                $(this).removeClass('is-visible1');
            }
        });
		//体验关闭窗口
        $('.tq_btn').on('click', function(event){
            event.preventDefault();
            $('.cd-popup5').addClass('is-visible1');
            //$(".dialog-addquxiao").hide()
        });
        
        
        $('.cd-popup_new').on('click', function(event){
            if( $(event.target).is('.cd-popup-close') || $(event.target).is('.cd-popup4') ) {
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
        	//================立即还款弹窗===================
        //   协议打开窗口
        $('.protocolbtn').on('click', function(event){
            event.preventDefault();
            $('.cd-popup6').addClass('is-visible1');
            //$(".dialog-addquxiao").hide()
        });
   //   环环投协议打开窗口
        $('.hht_trigger').on('click', function(event){
            event.preventDefault();
            $('.cd-popup6').addClass('is-visible1');
            //$(".dialog-addquxiao").hide()
        });
        
   //   头笔赚协议打开窗口
        $('.new_trigger').on('click', function(event){
            event.preventDefault();
            $('.cd-popup6').addClass('is-visible1');
            //$(".dialog-addquxiao").hide()
        });
   //   点点投投协议打开窗口
        $('.ddt_trigger1').on('click', function(event){
            event.preventDefault();
            $('.cd-popup6').addClass('is-visible1');
            //$(".dialog-addquxiao").hide()
        });
        
        //逾期还款打开窗口
        $('.confirmbtn1').on('click', function(event){
            event.preventDefault();
            $('.cd-popup1').addClass('is-visible1');
            //$(".dialog-addquxiao").hide()
        });
        
		//打开窗口
        $('.cd-popup-trigger6').on('click', function(event){
            event.preventDefault();
            $('.cd-popup6').addClass('is-visible1');
            //$(".dialog-addquxiao").hide()
        });
        //关闭窗口
        $('.cd-popup6').on('click', function(event){
            if( $(event.target).is('.cd-popup-close') || $(event.target).is('.cd-popup6') ) {
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
                $('.cd-popup6').removeClass('is-visible1');
            }
        });
        
		// 体验标出借窗口
        
        $('#tiyan_btn').on('click', function(event){
            event.preventDefault();
            $('.cd-popup2').addClass('is-visible1');
            //$(".dialog-addquxiao").hide()
        });
        
		//================图片窗口===================
		
		//打开窗口
        $('.cd-popup-trigger3').on('click', function(event){
			
            event.preventDefault();
            $('.cd-popup3').addClass('is-visible1');
            //$(".dialog-addquxiao").hide()
        });
        //关闭窗口
        $('.cd-popup3').on('click', function(event){
            if( $(event.target).is('.cd-popup-close') || $(event.target).is('.cd-popup3') ) {
                event.preventDefault();
                $(this).removeClass('is-visible1');
                //parent.location.reload();
                //window.location.reload();
            }
        });
		
		
        //ESC关闭
        $(document).keyup(function(event){
            if(event.which=='27'){
                $('.cd-popup3').removeClass('is-visible1');
            }
        });
        //===========================个人中心上传头像====================================
      	
		//打开窗口
        $('.cd-popup-trigger5').on('click', function(event){
			
            event.preventDefault();
            $('.cd-popup5').addClass('is-visible1');
            //$(".dialog-addquxiao").hide()
        });
        //关闭窗口
        $('.cd-popup5').on('click', function(event){
            if( $(event.target).is('.cd-popup-close') || $(event.target).is('.cd-popup5') ) {
                event.preventDefault();
                $(this).removeClass('is-visible1');
                //parent.location.reload();
                //window.location.reload();
            }
        });
		
		
        //ESC关闭
        $(document).keyup(function(event){
            if(event.which=='27'){
                $('.cd-popup5').removeClass('is-visible1');
            }
        });
        
        
      //================环环投详情页部分标详情窗口===================

      //打开窗口
      $('.cd-popup-triggerh').on('click', function(event){
      	
          event.preventDefault();
          $('.cd-popuph').addClass('is-visible1');
          //$(".dialog-addquxiao").hide()
      });
      //关闭窗口
      $('.cd-popuph').on('click', function(event){
          if( $(event.target).is('.cd-popup-close') || $(event.target).is('.cd-popuph') ) {
              event.preventDefault();
              $(this).removeClass('is-visible1');
              //parent.location.reload();
              //window.location.reload();
          }
      });
		
      
    //================借款申请弹窗===================

      //打开窗口
      $('.cd-popup-triggerjk').on('click', function(event){
      	
          event.preventDefault();
          $('.cd-popupjk').addClass('is-visible1');
          //$(".dialog-addquxiao").hide()
      });
      //关闭窗口
      $('.cd-popupjk').on('click', function(event){
          if( $(event.target).is('.cd-popup-close') || $(event.target).is('.cd-popupjk') ) {
              event.preventDefault();
              $(this).removeClass('is-visible1');
              //parent.location.reload();
              //window.location.reload();
          }
      });
      
      
    //关闭窗口
      $('.cd-popupjk').on('click', function(event){
          if( $(event.target).is('.cd-popup-close') || $(event.target).is('.cd-popupjk') ) {
              event.preventDefault();
              $(this).removeClass('is-visible1');
              //parent.location.reload();
             window.location.reload();
          }
      });
      
    
    //================银行卡弹窗===================

      //打开窗口
      $('.cd-popup-triggerjk').on('click', function(event){
      	
          event.preventDefault();
          $('.cd-popupcar').addClass('is-visible1');
          //$(".dialog-addquxiao").hide()
      });
      //关闭窗口
      $('.cd-popupcar').on('click', function(event){
          if( $(event.target).is('.cd-popup-close') || $(event.target).is('.cd-popupcar') ) {
              event.preventDefault();
              $(this).removeClass('is-visible1');
              //parent.location.reload();
              //window.location.reload();
          }
      });
      
      //充值页面选项
      
		
      
    });





//ESC关闭
$(document).keyup(function(event){
    if(event.which=='27'){
        $('.cd-popuph').removeClass('is-visible1');
    }
});


	
//特权选择
$(function(){
	var flag=0;
	//var hasChk = $('#checkbox').is(':checked');	
	$('.boxselct a').click(function(){					
		/*if(flag==0){
			//$(".isuse").attr("checked",true);
			$(".isuse").click();
			flag+=1;
		}*/		
		$(this).addClass('active').siblings().removeClass('active');		
		
		})
		
})

$(function(){
	$(".isuse").click(function(){
		$('.boxselct a').removeClass("active");
		$(".boxselct a").find("input[type=hidden]").remove();//移除所有
		/*var hasChk = $('#checkbox').is('checked');
		if(hasChk){
		    已选中 
			//$(".isuse").trigger('click');
			//$(".isuse").removeAttr("checked");
		}else{ 
			
		    
			未选中 

		}*/
	})
	
})

/*function mycheckbox() { 
	var falg = 0; 
	$("input.isuse:checkbox").each(function () { 
	if ($(this).attr("checked")) { 
	falg += 1; 
	} 
	}) 
	if (falg > 0) 
	return true; 
	else 
	return false; 
	}*/ 
/*$(function(){
	$(".isuse").click(function(){
		var hasChk = $('#checkbox').is(':checked');
		if(hasChk){
		    已选中 
			
		}else{ 
			$('.boxselct a').removeClass("active");
			$(".boxselct a").find("input[type=hidden]").remove();//移除所有
		    var i=1;
			未选中 

		}
		
	})
	
})*/

//选择某一特权就追加隐藏域元素
$(function(){	
	$(".boxselct a").click(function(){
		var tqOID = $(this).find(".privilegeoid").text();		
		if($(this).children("input[type=hidden]").hasClass("selecttq")){			
		}else{
			$(".boxselct a").find("input[type=hidden]").remove();//追加之前先移除所有,确保每次只显示一条
			$(this).append('<input class="selecttq" type="hidden" name="OID" value="'+tqOID+'" />');}
	})
})
	

	
 //立即还款按钮body宽空空控制
	
	$(function(){
    var heightif =$('.ifmbox').height();
	//alert(widthif);	
	$('.bodybox').height(heightif);
	});	

//消息中心 全选功能
function check_all(obj, cName) {
	var checkboxs = document.getElementsByName(cName);
	for ( var i = 0; i < checkboxs.length; i+=1) {
		checkboxs[i].checked = obj.checked;
	}
}

//  消息提示
$(function(){
	var num=$("#msg-num").text();
	//alert(num);
	if(num>0){
		$("#msg-num").show();
	}else{
		$("#msg-num").hide();
	}
})

$(function(){
	$('.msgbox-status a').click(function(){
		$(this).addClass('active').siblings().removeClass('active');
		
	})
})
/*团队介绍*/
$(function(){
	$('.team_top img.pic1').mouseover(function(){
		//$(this).animate({opacity:0},1e3)});
		$(this).animate({opacity:0},1e3);
	}).mouseout(function(){
		$(this).animate({opacity:1},1e3);
	})
})

/* 错误提示页面 */
$(function(){
	var oHeight=$(window).height();
	$(".error_wrap").css({"height":oHeight-80})
})
$(window).resize(function(){
	var oHeight=$(window).height();
	$(".error_wrap").css({"height":oHeight-80})
});
/* 环环投  */

$(function(){
	var oHeight=$(window).height();
	$(".projectscon").css({"min-height":oHeight-450})
})
$(window).resize(function(){
	var oHeight=$(window).height();
	$(".projectscon").css({"min-height":oHeight-450})
});
function GetQueryString(name)
{
     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
     var r = window.location.search.substr(1).match(reg);
     if(r!=null)return  unescape(r[2]); return null;
}
function changeURLPar(destiny, par, par_value)
{

var pattern = par+'=([^&]*)';

var replaceText = par+'='+par_value;

if (destiny.match(pattern))

{

var tmp = '/\\'+par+'=[^&]*/';

tmp = destiny.replace(eval(tmp), replaceText);

return (tmp);

}

else

{

if (destiny.match('[\?]'))

{

return destiny+'&'+ replaceText;

}

else

{

return destiny+'?'+replaceText;

}

}

return destiny+'\n'+par+'\n'+par_value;

}


//sda

/* 个人中心 我的出借选项卡问题 */
function iniTab2(){
	 var tabVlue=0;
	 var url = window.location.href;
	 var lastStr=url.substring(url.length-1);
	 tabVlue=$("#tabInx").val();
	 //alert("bbb:"+tabVlue); 
		 $(".my_loantop a").eq(tabVlue).addClass('active').siblings().removeClass('active');
			$('.tabopa').eq(tabVlue).show().siblings().hide();
			/*if(url.indexOf("dtocListChujieRecordsCollectionPageNo") >= 0 && lastStr=="&" && tabVlue==1 ) { 
				  //alert(url);
				//var scroll_top=$(document).scrollTop();
				//var offset_top=$('.tabtitcons').offset().top
				//$(document).scrollTop(offset_top-168);
				//alert(offset_top);
				}else if(url.indexOf("dtocListChujieRecordsCollectionPageNo") >= 0 && lastStr=="&" && tabVlue==0){
					//var scroll_top=$(document).scrollTop();
					//var offset_top=$('.boxxqjs2').offset().top
					//$(document).scrollTop(offset_top-168);
				}*/
	}




$(function(){

    //判断浏览器是否支持placeholder属性
    supportPlaceholder='placeholder'in document.createElement('input'),
            //alert(supportPlaceholder);

            placeholder=function(input){

                var text = input.attr('placeholder'),
                        defaultValue = input.defaultValue;

                if(!defaultValue){

                    input.val(text).addClass("phcolor");
                }

                input.focus(function(){

                    if(input.val() == text){

                        $(this).val("");
                    }
                });


                input.blur(function(){

                    if(input.val() == ""){

                        $(this).val(text).addClass("phcolor");
                    }
                });

                //输入的字符不为灰色
                input.keydown(function(){

                    $(this).removeClass("phcolor");
                });
            };

    //当浏览器不支持placeholder属性时，调用placeholder函数
    if(!supportPlaceholder){

        $('input').each(function(){

            text = $(this).attr("placeholder");

            if($(this).attr("type") == "text"){

                placeholder($(this));
            }
        });
    }

});

/*  中心首页  */
$(function(){
	var TimeDate=$(".TimeDate");
	now = new Date(),hour = now.getHours() 
	if(hour < 6){TimeDate.text("凌晨好！")} 
	else if (hour < 9){TimeDate.text("早上好！")} 
	else if (hour < 12){TimeDate.text("上午好！")} 
	else if (hour < 14){TimeDate.text("中午好！")} 
	else if (hour < 17){TimeDate.text("下午好！")} 
	else if (hour < 19){TimeDate.text("晚上好！")} 
	else {TimeDate.text("夜里好！")} 
	
})