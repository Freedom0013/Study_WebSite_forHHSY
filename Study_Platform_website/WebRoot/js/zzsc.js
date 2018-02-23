$(document).ready(function(){
	var $liCur = $(".nav ul li.cur"),
	  curP = $liCur.position().left,
	  curW = $liCur.outerWidth(true),
	  $slider = $(".curBg"),
	  $navBox = $(".nav");
	 $targetEle = $(".nav ul li a"),
	$slider.animate({
	  "left":curP,
	  "width":curW
	});
	$targetEle.mouseenter(function () {
	  var $_parent = $(this).parent(),
		_width = $_parent.outerWidth(true),
		posL = $_parent.position().left;
	  $slider.stop(true, true).animate({
		"left":posL,
		"width":_width
	  }, "fast");
	});
	$navBox.mouseleave(function (cur, wid) {
	  cur = curP;
	  wid = curW;
	  $slider.stop(true, true).animate({
		"left":cur,
		"width":wid
	  }, "fast");
	});
})
