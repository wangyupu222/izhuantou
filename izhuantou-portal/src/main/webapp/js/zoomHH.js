(function($) {
	

	var zoom = $('#zoom',parent.document).hide(),
	    zoomContent = $('#zoom .content',parent.document),
	    zoomedIn = false,
	    openedImage = null,
	    windowWidth = $(window).width(),
	    windowHeight = $(window).height();
		
	function open01(event) {
		if (event)
			event.preventDefault();
		var link = $(this),
		    src = link.attr('href');
		var con_tit=link.find(".psnr").text();
		//alert(link.find(".psnr").text());
		if (!src)
			return;
		var image = $(new Image()).hide();
		$('#zoom .previous, #zoom .next',parent.document).show();
		if (link.hasClass('zoom'))
			$('#zoom .previous, #zoom .next',parent.document).hide();
		if (!zoomedIn) {
			zoomedIn = true;
			zoom.show();
			$('body',parent.document).addClass('zoomed');
		}
		zoomContent.html(image).delay(500).addClass('loading');
		image.load(render).attr('src', src);
		openedImage = link;
		$("#zoom .con_tit",parent.document).text(con_tit);
		
		function render() {
			var image = $(this),
			    borderWidth = parseInt(zoomContent.css('borderLeftWidth')),
			    maxImageWidth = (windowWidth - (borderWidth * 2))*0.8,
			    maxImageHeight = (windowHeight - (borderWidth * 2))*0.7,
			    imageWidth = (image.width()),
			    imageHeight = (image.height());
			if (imageWidth == zoomContent.width() && imageWidth <= maxImageWidth && imageHeight == zoomContent.height() && imageHeight <= maxImageHeight) {
					show(image);
					return;
			}
			if (imageWidth > maxImageWidth || imageHeight > maxImageHeight) {
				var desiredHeight = maxImageHeight < imageHeight ? maxImageHeight : imageHeight,
				    desiredWidth  = maxImageWidth  < imageWidth  ? maxImageWidth  : imageWidth;
				if ( desiredHeight / imageHeight <= desiredWidth / imageWidth ) {
					image.width(imageWidth * desiredHeight / imageHeight);
					image.height(desiredHeight);
				} else {
					image.width(desiredWidth);
					image.height(imageHeight * desiredWidth / imageWidth);
				}
			}
			zoomContent.animate({
				width: image.width(),
				height: image.height(),
				marginTop: -((image.height()+80) / 2) - borderWidth,
				marginLeft: -(image.width() / 2) - borderWidth
			}, 200, function() {
				show(image);
			});

			function show(image) {
				image.show();
				zoomContent.removeClass('loading');
			}
		}
	}
	
	function openPrevious() {
		var prev = openedImage.parent('.informationImg_box').prev(".informationImg_box");
		//alert(prev.length);
		if (prev.length == 0){

			prev = $('.informationImg_wrap .informationImg_box:nth-last-child(1)');
			//alert(prev.length);
		}
		prev.find('a').trigger('click');
	}
	
	function openNext() {
		var next = openedImage.parent('.informationImg_box').next(".informationImg_box");
		//alert(next.length);
		if (next.length == 0){
			//alert("a");
			//alert(next.length);
			next = $('.informationImg_wrap .informationImg_box:nth-of-type(1)');
		}
			
		next.find('a').trigger('click');
	}
		
	function close(event) {
		if (event)
			event.preventDefault();
		zoomedIn = false;
		openedImage = null;
		zoom.hide();
		$('body').removeClass('zoomed');
		zoomContent.empty();
	}
	
	function changeImageDimensions() {
		windowWidth = $(window).width();
		windowHeight = $(window).height();
	}
	
	(function bindNavigation() {
		zoom.on('click', function(event) {
			event.preventDefault();
			if ($(event.target).attr('id') == 'zoom')
				close();
		});
		
		$('#zoom .close',parent.document).on('click', close);
		$('#zoom .previous',parent.document).on('click', openPrevious);
		$('#zoom .next',parent.document).on('click', openNext);
		$(document).keydown(function(event) {
			if (!openedImage) return;
			if (event.which == 38 || event.which == 40)
				event.preventDefault();
			if (event.which == 27)
				close();
			if (event.which == 37 && !openedImage.hasClass('zoom'))
				openPrevious();
			if (event.which == 39 && !openedImage.hasClass('zoom'))
				openNext();
		});

		if ($('.boxshehe .box_new a',parent.document).length == 1)
			$('.boxshehe .box_new a',parent.document)[0].addClass('zoom');
		//$('.informationImg_wrap .informationImg_box a').on('click', open);
		//$("#iframepage_hht").contents().find(".informationImg_wrap .informationImg_box a");
		 $(".informationImg_wrap .informationImg_box a").on('click',open01);	
	})();

	(function bindChangeImageDimensions() {
		$(window).on('resize', changeImageDimensions);
	})();

	(function bindScrollControl() {
		$(window).on('mousewheel DOMMouseScroll', function(event) {
			if (!openedImage)
				return;
			event.stopPropagation();
			event.preventDefault();
			event.cancelBubble = false;
		});
	})();
})(jQuery);
/*$html = $("<div class='informationImg_hover'></div>");
$(".informationImg_box").click(function(){
	$(this).append($html).siblings(".informationImg_box").remove($html);
})*/
/*$html2 = $("<div class='informationImg_hover2'></div>");
$(".informationImg_box").hover(function(){
	$(this).append($html2).siblings(".informationImg_box").remove($html2);
	$(".informationImg_box").find(".informationImg_hover2").css("display","block");
},function(){
	$(".informationImg_box").remove($html2);
	$(".informationImg_box").find(".informationImg_hover2").css("display","none");
})*/
var n=$(".informationImg_box").length;
var oWidth=$(".informationImg_box")[0].offsetWidth+8;
if(n<=4){
	$(".btn_right").addClass("disabled_right");
	$(".btn_left").addClass("disabled_left");
}
if(parseInt($(".informationImg_wrap").css("left"))<parseInt(oWidth)){
	$(".btn_left").addClass("disabled_left");
}
$(".btn_right").click(function(){

	
	$(".informationImg_wrap").stop(false,true).animate({"left":"-="+oWidth},800);
	$(".btn_left").removeClass("disabled_left");
	var oLeft=$(".informationImg_wrap").css("left");
	//alert(parseInt(oLeft));
	if(Math.abs(parseInt(oLeft))>=oWidth*n-oWidth*5){
		$(".btn_right").addClass("disabled_right");
	}
})
$(".btn_left").click(function(){
	$(".informationImg_wrap").stop(false,true).animate({"left":"+="+oWidth},800);
	$(".btn_right").removeClass("disabled_right");
	var oLeft=$(".informationImg_wrap").css("left");
	//alert(parseInt(oLeft));
	if(Math.abs(parseInt(oLeft))<=oWidth){
		$(".btn_left").addClass("disabled_left");
	}
})