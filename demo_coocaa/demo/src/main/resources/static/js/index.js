//侧边栏
$(function(){
	$('.img').click(function(){
		if($(this).find('img').attr('src')=="/img/open.png"){
			$(this).find('img').attr('src',"/img/close.png");
			$('#nav').animate({left:'+=70px'})
		}else{
			$(this).find('img').attr('src',"/img/open.png");
			$('#nav').animate({left:'-=70px'})
		}
	});
})
$("#people").click(function(){
	window.location.href = "/person";
})
$("#project").click(function(){
	window.location.href = "/project";
})
