$("#nav").find("li:eq(0)").click(function(){
	window.location.href = "/index";
});
$("#nav").find("li:eq(1)").click(function(){
	window.location.href = "/person";
});
$("#nav").find("li:eq(2)").click(function(){
	window.location.href = "/project";
});
$("#banner").find("p:eq(0)").click(function(){
	window.location.href = "/index";
});
$("#banner").find("p:eq(1)").click(function(){
	window.location.href = "/project";
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
var myChart2 = echarts.init(document.getElementById('chartmain'));
			myChart2.setOption(
				option2 = {
					title:{
						text:'人员效率比较',
						left:"center"
					},
					tooltip:{
						show:true,
						trigger:'axis',				
					},
					legend:{
						name:'效率',
						selectedMode:false,
						x:'right',
						y:'top',
					},
					xAxis:[
						{
							name:"工作人员",
							show:true,
							type:'category',
							axisLabel:{
								interval:0,
							},
							data:[],
						}
					],
					yAxis:[
						{
							name:"工作效率",
							type:'value',
						}
					],
					series:[
						{
							name:"效率",
							type:'bar',
							barMaxWidth:40,
							data:[],
							itemStyle:{
								color:"#ccc",
							},
							markLine:{
								data:[
									{
										name:'标准线',
										yAxis:'',
									}
								]
								
							},
							label: {
							      normal: {
							          show: true,
							          position: 'top',
							          textStyle: {
							            color: 'black'
							          }
							      }
							},
						}
					],
				}
			);
$('#pro_input').click(function(){
	$("#ul1").css("display","block");
	$("input[name='check']").change(function(){
		var result='';
        $("input[name='check']:checked").each(function(){    
            result += $(this).val()+'、';
	    });
	    if(result!=""){
	        result=result.substring(0,result.lastIndexOf('、')); 
    	}
	    $("#pro_input").val(result);
	})
})
//模糊搜索
$(document).ready(function(){
	$.ajax({
		type:"get",
		url:"http://120.78.67.238:8080/common/projectname",
		async:true,
		dataType:'json',
		success:function(data){
			var temp = [];
			temp = data.data['projectName'];
			for(var j=0;j<temp.length;j++){
				$('#down').append('<li>'+temp[j]+'</li>');
			}
			$(document).bind('click', function(e) {
				var e = e || window.event; //浏览器兼容性 
				var elem = e.target || e.srcElement;
				while (elem) { //循环判断至跟节点，防止点击的是div子元素 
					if (elem.id && elem.id == 'project') {
						return;
					}
					elem = elem.parentNode;
				}
				$('#down').css('display', 'none'); //点击的不是div或其子元素 
			});
				$("li").click(function(){
					$("#input").val($(this).text());
					$("#down").css("display","none");
					nameValue();
				});
				$("#input").click(function(){
					fun();
				});
				$("#input").keydown(function(){
					fun();
				});
				function fun(){
					var queryStr = $.trim($("#input").val());
					if(queryStr === ''){
						$("#down").css("display","block");
							$("li").show();
						}else{
							$("li").hide().filter(":contains('"+queryStr+"')").show();
						}
				}
				
		}
	})
})
function nameValue(){
			var projectName = $("#input").val();
			$.ajax({
				type:"get",
				url:" http://120.78.67.238:8080/common/assignee",
				async:true,
				data:{
					projectName:projectName,
				},
				dataType:'json',
				success:function(result){
					var name = [];
					name = result.data['assignee'];
					$("#ul1").empty();
					for(var j=0;j<name.length;j++){
						$('#ul1').append("<li><input type='checkbox' name='check' value='"+name[j]+"'>"+name[j]+"</li>");
					}
					$(document).bind('click', function(e) {
						var e = e || window.event; //浏览器兼容性 
						var elem = e.target || e.srcElement;
						while (elem) { //循环判断至跟节点，防止点击的是div子元素 
							if (elem.id && elem.id == 'name') {
								return;
							}
							elem = elem.parentNode;
						}
						$('#ul1').css('display', 'none'); //点击的不是div或其子元素 
					});
						$('#pro_input').click(function(){
							$("#ul1").css("display","block");
							$("input[name='check']").change(function(){
								var val='';
						        $("input[name='check']:checked").each(function(){    
						            val += $(this).val()+'、';
							    });
							    if(val!=""){
							        val=val.substring(0,val.lastIndexOf('、')); 
						    	}
							    $("#pro_input").val(val);
							    nameDate();
							})
						})
						
				}
			});
}
$("#middle").find("input.timepicker-input").change(function(){
		nameDate();
})
function nameDate(){
	var myChart1 = echarts.init(document.getElementById('chartmain'));
	var projectName = $("#input").val();
	var assignee = $("#pro_input").val();
	var arr = assignee.split("、").join(',');
	var str = arr.substring(0,arr.length);
	var startTime = $("#startTime").val();
	var endTime = $("#endTime").val();
	if(endTime!=''&&assignee!=''&&projectName!=''&&startTime!=''){
					$.ajax({
							type:"post",
							url:"http://120.78.67.238:8080/project/memberefficiency",
							async:true,
							data:{
								projectName:projectName,
								assignee:str,
								startTime:startTime,
								endTime:endTime
							},
							dataType:'json',
							success:function(data){
									myChart1.setOption({        //加载数据图表
									        xAxis: {
									                data: data.data['assignee']
										            },
									                series: [{
									                    data: data.data['efficiency'],
									                    markLine:{
									                    	data:[{
									                    		name:'标准线',
									                    		yAxis:data.data['averageEfficiency'],
									                    	}]
									                    },// 根据名字对应到相应的系列
									                }],
									                
									})
							},
							error : function(errorMsg) {  
							       alert("图表请求数据失败!");
						           myChart1.hideLoading();  
					            }  
					});
     }
}