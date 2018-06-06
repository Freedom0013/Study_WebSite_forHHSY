$(document).ready(function () {
    /*** Retina Image Loader ***/
    if ($.fn.unveil) {
        $("img").unveil();
    }

    /**** Scroller ****/
    if ($.fn.niceScroll) {
        var mainScroller = $("html").niceScroll({
            zindex: 999999,
            boxzoom: true,
            cursoropacitymin: 0.5,
            cursoropacitymax: 0.8,
            cursorwidth: "10px",
            cursorborder: "0px solid",
            autohidemode: false
        });
    }

    /**** Carousel for Testominals ****/
    if ($.fn.owlCarousel) {
        $("#testomonials").owlCarousel({
            singleItem: true
        });
    }

    /**** Mobile Side Menu ****/
    if ($.fn.waypoint) {
        var $head = $('#ha-header');
        $('.ha-waypoint').each(function (i) {
            var $el = $(this),
						animClassDown = $el.data('animateDown'),
						animClassUp = $el.data('animateUp');

            $el.waypoint(function (direction) {
                if (direction === 'down' && animClassDown) {
                    $head.attr('class', 'ha-header ' + animClassDown);
                }
                else if (direction === 'up' && animClassUp) {
                    $head.attr('class', 'ha-header ' + animClassUp);
                }
            }, { offset: '100%' });
        });
    }
    /**** Revolution Slider ****/
    if ($.fn.revolution) {
        revapi = $('#home').revolution(
	{
	    delay: 15000,
	    startwidth: 1170,
	    startheight: 500,
	    hideThumbs: 10,
	    fullWidth: "off",
	    fullScreen: "on",
	    navigationType: "none",
	    fullScreenOffsetContainer: "",
	    touchenabled: "on",
	    videoJsPath: "assets/plugins/rs-plugin/videojs/"
	});

    }


    /**** Appear JS ****/
    if ($.fn.appear) {
        $('[data-ride="animated"]').appear();
        if (!$('html').hasClass('ie no-ie10')) {
            $('[data-ride="animated"]').addClass('appear');
            $('[data-ride="animated"]').on('appear', function () {
                var $el = $(this), $ani = ($el.data('animation') || 'fadeIn'), $delay;
                if (!$el.hasClass('animated')) {
                    $delay = $el.data('delay') || 0;
                    setTimeout(function () {
                        $el.removeClass('appear').addClass($ani + " animated");
                    }, $delay);
                }
            });
        };
        $('.number-animator').appear();
        $('.number-animator').on('appear', function () {
            $(this).animateNumbers($(this).attr("data-value"), true, parseInt($(this).attr("data-animation-duration")));
        });

        $('.animated-progress-bar').appear();
        $('.animated-progress-bar').on('appear', function () {
            $(this).css('width', $(this).attr("data-percentage"));
        });
    }

    /**** Animate Numbers ****/
    if ($.fn.animateNumbers) {
        $('.animate-number').each(function () {
            $(this).animateNumbers($(this).attr("data-value"), true, parseInt($(this).attr("data-animation-duration")));
        })
    }

    $('.animate-progress-bar').each(function () {
        $(this).css('width', $(this).attr("data-percentage"));

    })

    if ($("#thumbs").length > 0) {
        var $container = $('#thumbs');
        $container.isotope({
            filter: '*',
            animationOptions: {
                duration: 750,
                easing: 'linear',
                queue: false
            }
        });

        $(window).resize(function () {
            var $container = $('#thumbs');
            $container.isotope({
                itemSelector: '.item',
                animationOptions: {
                    duration: 250,
                    easing: 'linear',
                    queue: false
                }
            });
        });


        // filter items when filter link is clicked
        $('#portfolio-nav a, #gallery-nav a').click(function () {
            var selector = $(this).attr('data-filter');
            $container.isotope({ filter: selector });

            $("#portfolio-nav li, #gallery-nav li").removeClass("current");
            $(this).closest("li").addClass("current");

            return false;
        });
    }



    $(".portfolio-grid ul li").hover(function () {
        var imgHeight = $(this).find("img").height();
        $(this).find(".portfolio-image-wrapper").height(imgHeight);

    });
	
	$('#button-send').click(function(event){
		$('#button-send').html('Sending E-Mail...');
		event.preventDefault();		
		$.ajax({
			type: 'POST',
			url: 'send_form_email.php',
			data: $('#contact_form').serialize(),
			success: function(html) {
				if(html.success == '1')
				{
					$('#button-send').html('Send');
					$('#success').show();
				}
				else
				{
					$('#button-send').html('Send');
					$('#error').show();
				}					
			},
			error: function(){
				$('#button-send').html('Send');
				$('#error').show();
			}
		});
		
	});

});	


	