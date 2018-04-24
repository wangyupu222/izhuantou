$(function(){
	$('.wen_tishi').each(function () {
			var distance = 10;
			var time = 250;
			var hideDelay = 300;

			var hideDelayTimer = null;

			var beingShown = false;
			var shown = false;
			var trigger = $('.wen_iconImg', this);
			var info = $('.tishi_box', this).css('opacity', 0);


			$([trigger.get(0), info.get(0)]).mouseover(function () {
				if (hideDelayTimer) clearTimeout(hideDelayTimer);
				if (beingShown || shown) {
					// don't trigger the animation again
					return;
				} else {
					// reset position of info box
					beingShown = true;

					info.css({
						top: 10,
						left: -400,
						display: 'block'
					}).animate({
						left: '-=' + distance + 'px',
						opacity: 1
					}, time, 'swing', function() {
						beingShown = false;
						shown = true;
					});
				}

				return false;
			}).mouseout(function () {
				if (hideDelayTimer) clearTimeout(hideDelayTimer);
				hideDelayTimer = setTimeout(function () {
					hideDelayTimer = null;
					info.animate({
						left: '-=' + distance + 'px',
						opacity: 0
					}, time, 'swing', function () {
						shown = false;
						info.css('display', 'none');
					});

				}, hideDelay);

				return false;
			});
		});
})