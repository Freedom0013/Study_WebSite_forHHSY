(function( $ ) {

    //Function to animate slider captions
    function doAnimations( elems ) {
        //Cache the animationend event in a variable
        var animEndEv = 'webkitAnimationEnd animationend';

        elems.each(function () {
            var $this = $(this),
                $animationType = $this.data('animation');
            $this.css('animation-delay',$this.data('delay')).addClass($animationType).one(animEndEv, function () {
                $this.removeClass($animationType).removeClass('inv');
            });
        });
    }

    //Variables on page load
    var $myCarousel = $('#featured-slider'),
        $firstAnimatingElems = $myCarousel.find('.item:first').find("[data-animation ^= 'animated']");

    //Initialize carousel
    $myCarousel.carousel();

    //Animate captions in first slide on page load
    doAnimations($firstAnimatingElems);

    //Pause carousel
    $myCarousel.carousel('pause');


    //Other slides to be animated on carousel slide event
    $myCarousel.on('slide.bs.carousel', function (e) {
        var $animatingElems = $(e.relatedTarget).find("[data-animation ^= 'animated']");
        doAnimations($animatingElems);
    });

    $myCarousel.on('slid.bs.carousel', function (e) {
        $('.slide-cat, .slide-btn, .slide-title, .slide-excerpt').addClass('inv');
    });

})(jQuery);

$(document).ready( function() {
    new WOW().init();

    $('#featureCarousel').carousel({
        interval:   4000
    });

    var clickEvent = false;
    $('#featureCarousel').on('click', '.nav a', function() {
        clickEvent = true;
        $('.nav li').removeClass('active');
        $(this).parent().addClass('active');
    }).on('slid.bs.carousel', function(e) {
        if(!clickEvent) {
            var count = $('#featureCarousel .nav').children().length -1;
            var current = $('#featureCarousel .nav li.active');
            current.removeClass('active').next().addClass('active');
            var id = parseInt(current.data('slide-to'));

            if(count == id) {
                $('#featureCarousel .nav li').first().addClass('active');
            }
        }
        clickEvent = false;
    });

    $("#main-nav-container").sticky({topSpacing:0});


    $('.navbar-eden a[href*=#]:not([href=#]):not([rel=crs])').click(function() {
        if (location.pathname.replace(/^\//,'') == this.pathname.replace(/^\//,'') && location.hostname == this.hostname) {
            var target = $(this.hash);
            target = target.length ? target : $('[name=' + this.hash.slice(1) +']');
            if (target.length) {
                $('html,body').animate({
                    scrollTop: target.offset().top
                }, 1000);
                return false;
            }
        }
    });


});