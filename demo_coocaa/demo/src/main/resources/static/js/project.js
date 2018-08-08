$("#nav").find("li:eq(0)").click(function(){
	window.location.href = "/index";
});
$("#nav").find("li:eq(1)").click(function(){
	window.location.href = "/person";
});
$("#banner").find("p:eq(0)").click(function(){
	window.location.href = "/index";
});
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
$("#two").click(function(){
	window.location.href = "/person_workload";
});
$("#three").click(function(){
	window.location.href = "/person_efficiency";
});
$("#four").click(function(){
	window.location.href = "/team_efficiency";
});
$("#five").click(function(){
	window.location.href = "/team_capacity";
});