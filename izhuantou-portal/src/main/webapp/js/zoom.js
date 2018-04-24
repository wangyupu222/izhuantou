(function($) {
	$('body').append('<div id="zoom"><a class="close"></a><a href="#previous" class="previous"></a><a href="#next" class="next"></a><div class="content loading"></div></div>');

	var zoom = $('#zoom').hide(),
	    zoomContent = $('#zoom .content'),
	    zoomedIn = false,
	    openedImage = null,
	    windowWidth = $(window).width(),
	    windowHeight = $(window).height();
		
	function open(event) {
		if (event)
			event.preventDefault();
		var link = $(this),
		    src = link.attr('href');
		if (!src)
			return;
		var image = $(new Image()).hide();
		$('#zoom .previous, #zoom .next').show();
		if (link.hasClass('zoom'))
			$('#zoom .previous, #zoom .next').hide();
		if (!zoomedIn) {
			zoomedIn = true;
			zoom.show();
			$('body').addClass('zoomed');
		}
		zoomContent.html(image).delay(500).addClass('loading');
		image.load(render).attr('src', src);
		openedImage = link;
		
		function render() {
			var image = $(this),
			    borderWidth = parseInt(zoomContent.css('borderLeftWidth')),
			    maxImageWidth = (windowWidth - (borderWidth * 2))*0.8,
			    maxImageHeight = (windowHeight - (borderWidth * 2))*0.8,
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
				marginTop: -(image.height() / 2) - borderWidth,
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
		var prev = openedImage.parent('.box_new').parent(".thbox_new").prev(".thbox_new");
		//alert(prev.length);
		if (prev.length == 0){

			prev = $('.boxshehe .thbox_new:nth-last-child(1)');
		}
		prev.find('a').trigger('click');
	}
	
	function openNext() {
		var next = openedImage.parent('.box_new').parent(".thbox_new").next(".thbox_new");
		//alert(next.length);
		if (next.length == 0){
			//alert("a");
			next = $('.boxshehe .thbox_new:nth-child(2)');
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
		
		$('#zoom .close').on('click', close);
		$('#zoom .previous').on('click', openPrevious);
		$('#zoom .next').on('click', openNext);
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

		if ($('.boxshehe .box_new a').length == 1)
			$('.boxshehe .box_new a')[0].addClass('zoom');
		$('.zoom, .boxshehe .box_new a').on('click', open);
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
