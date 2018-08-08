$("#nav").find("li:eq(0)").click(function(){
	window.location.href = "/index";
});
$("#nav").find("li:eq(2)").click(function(){
	window.location.href = "/project";
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
$("#one").click(function(){
	window.location.href = "/efficiency";
});
$("#two").click(function(){
	window.location.href = "/workload";
});
$("#three").click(function(){
	window.location.href = "/task_graph";
});
$("#four").click(function(){
	window.location.href = "/remainder_workReport";
});
$("#people_all").click(function(){
	window.location.href ="/comparison_diagram";
});
