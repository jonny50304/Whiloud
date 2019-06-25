(function($) {




	skel.breakpoints({
		xlarge:	'(max-width: 1680px)',
		large:	'(max-width: 1280px)',
		medium:	'(max-width: 980px)',
		small:	'(max-width: 736px)',
		xsmall:	'(max-width: 480px)'
	});

	$(function() {

		var	$window = $(window),
			$body = $('body'),
			$header = $('#header'),
			$banner = $('#banner');
		// Disable animations/transitions until the page has loaded.
			$body.addClass('is-loading');
			$window.on('load', function() {
				window.setTimeout(function() {
					$body.removeClass('is-loading');
				}, 100);
			});
		// Header.
			if (skel.vars.IEVersion < 9)
				$header.removeClass('alt');
			if ($banner.length > 0
				&&	$header.hasClass('alt')) {
				$window.on('resize', function() { $window.trigger('scroll'); });
			}
		// Banner.
			if ($banner.length > 0) {				
				// Video check.
					var video = $banner.data('video');
					if (video)
						$window.on('load.banner', function() {
							// Disable banner load event (so it doesn't fire again).
								$window.off('load.banner');
							// Append video if supported.

							$banner.append('<video autoplay loop muted ><source src="images/New-jumbo.mp4"  /></video>');
						});
			}
	});
})(jQuery);