
$(function() {
	 var oIcon=$(".ver-ico-url");
	 var oHide=$(".financial-cal-menu-list");
    var t = $("span[data-type='financial-cal-menu-title']");
/*    alert($(".financial-cal-menu-list a.active").length);*/
    if($(".financial-cal-menu-list").find("a").hasClass("active")){
        //alert($(".financial-cal-menu-list").find(".active").text());
    	$(".financial-cal-menu-list").find(".active").parent().parent().find(".financial-cal-menu-title").find("em").addClass("current").parent().siblings().find(".financial-cal-menu-title").find("em").removeClass("current");
    	//$(".financial-cal-menu-list").find(".active").parent().parent().find(".financial-cal-menu-title").addClass("active").sibling().removeClass("active");
    	
    }
    t.on("click",
    function() {
        //var a = $(this);
        /*$(this).find("em").toggleClass("current");		
        $(this).next("div").slideToggle();
       
        $(this).addClass("active");
        $(this).parents(".financial-cal-menu-item").prevAll().children("span.financial-cal-menu-title").removeClass("active");
        $(this).parents(".financial-cal-menu-item").nextAll().children("span.financial-cal-menu-title").removeClass("active");
        $(this).parents(".financial-cal-menu-item").children("div.financial-cal-menu-list").addClass("active");
        $(this).parents(".financial-cal-menu-item").prevAll().children("div.financial-cal-menu-list").slideUp("normal");
        $(this).parents(".financial-cal-menu-item").nextAll().children("div.financial-cal-menu-list").slideUp("normal");*/
        
    	
        var index=$(this).parent().index();
           //alert(index);

           if (oIcon.eq(index).hasClass("current")){
               oHide.stop(false,true).slideUp();
               oIcon.eq(index).removeClass("current");
           }else {        	  
               oHide.eq(index).stop(false,true).slideDown();
               oIcon.removeClass("current");
               $(this).parent().siblings().find(".financial-cal-menu-list").slideUp();
               oIcon.eq(index).addClass("current")
           }
           $(this).addClass("active").parent().siblings().find(".financial-cal-menu-title").removeClass("active");
      	 $(this).next(".financial-cal-menu-list").addClass("active");
    	
    	
    	
    }),
    t.on("mouseenter",
    function() {
        $(this).addClass("hover")
		//console.info($(this));
    }),
    t.on("mouseleave",
    function() {
        $(this).removeClass("hover")
    });
    
    
  
});


//个人中心左侧 当前样式
$(document).ready(function(){
	
	//alert(String(window.location));
	 var localurl = $(".localurl").text();
	 //alert(localurl);
	 //localurl = localurl.text();
	 //var localurl = String(window.location);
	 //alrt(12);
	$(".financial-cal-menu-list a").each(function(){
	$this = $(this);	
	//如果是资金明细页面 加样式
	/*if(String(window.location).indexOf("CapitalDetailed.jsp") > 0 )
	{
		$(".financial-cal-menu-item.menu01 .financial-cal-menu-title").addClass("active");
		$(".financial-cal-menu-item.menu01 .financial-cal-menu-list").addClass("active");//当前栏目展开
	}*/
	//如果是出借记录页面 加样式
	/*if(String(window.location).indexOf("BorrowingRecords.jsp") > 0 )
	{
		$(".financial-cal-menu-item.menu03 .financial-cal-menu-title").addClass("active");
		$(".financial-cal-menu-item.menu03 .financial-cal-menu-list").addClass("active");//当前栏目展开
	}*/
	//如果是注册资金账户页面(原添加银行卡页面,该页面同属于我的账户项),给我的账户加样式
	if(String(window.location).indexOf("addbankcard") > 0 )
	{
		//alert(String(window.location));
		$(".financial-cal-menu-item.menu01 .financial-cal-menu-title").addClass("active");
		$(".financial-cal-menu-item.menu01 .financial-cal-menu-list").addClass("active");//当前栏目展开
	}
	//如果没有注册资金帐户点击充值或其他页面自动跳转到addbankcard.jsp页面的样式
	if(localurl.indexOf("addbankcard") > 0){
		
	$(".financial-cal-menu-item.menu01 .financial-cal-menu-title").addClass("active");
	$(".financial-cal-menu-item.menu01 .financial-cal-menu-list").addClass("active");//当前栏目展开
	}
	//我的消息
	if(String(window.location).indexOf("MessageHistoryNotification") > 0 )
	{
		$(".financial-cal-menu-item.menu05 .financial-cal-menu-title").addClass("active");
		$(".financial-cal-menu-item.menu05 .financial-cal-menu-list").addClass("active");//当前栏目展开
	}
	if(String(window.location).indexOf("Recharge_new") > 0 )
	{
		$(".financial-cal-menu-item.menu01 .financial-cal-menu-title").addClass("active");
		$(".financial-cal-menu-item.menu01 .financial-cal-menu-list").addClass("active");//当前栏目展开
	}
	//个人中心借款申请
	/*if(String(window.location).indexOf("LoanApplication.jsp") > 0 )
	{
		$(".financial-cal-menu-item.menu03 .financial-cal-menu-title").addClass("active");
		$(".financial-cal-menu-item.menu03 .financial-cal-menu-list").addClass("active");//当前栏目展开
	}*/
	//alert($this[0].href);
	//if($this[0].href==String(window.location)){
	//if($this[0].href==localurl){
	if($this[0].href.indexOf(localurl)>0){		
		//alert(localurl+"==="+String(window.location)+"==="+$this[0].href);
		$this.addClass("active");
		//$(".financial-cal-menu-item.menu01 .financial-cal-menu-list'").removeClass("active");//移除个人中心-资金总览默认样式
		//$(".financial-cal-menu-item.menu05 .financial-cal-menu-title").removeClass("active");//移除个人中心-资金总览默认样式
		
		$this.parents(".financial-cal-menu-list").addClass("active");//当前栏目展开
		$this.parents(".financial-cal-menu-item").children('.financial-cal-menu-title').addClass("active");//给当前组一级栏目加active的样式
	}
});
});