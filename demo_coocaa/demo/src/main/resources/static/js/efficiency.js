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
	window.location.href = "/person";
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
//图表
var myChart1 = echarts.init(document.getElementById('chartmain'));
			myChart1.setOption(
				option1 = {
					title:{
						text:'工作效率',
						textStyle:{
							color:'#000000',
						},
						left:'center',
					},
					tooltip:{
						show:true,
						trigger:'axis',
						axisPointer:{
							show:false,
						}
					},
					gird:{
						left:'3%',
						right:'15%',
						bottom:"10%",
						containLabel:true,
					},
					legend:{
						name:'工作效率',
						selectedMode:false,
						right:20,
						top:10,
					},
					xAxis:[
						{
							name:'时间',
							show:true,
							type:'category',
							data:[],//'1月','2月','3月','4月','5月'
							axisLabel:{
								interval: 0,
								fontStyle:'italic',
								fontSize:'13',
								lineHeight:40,
							}
     					}
					],
					yAxis:[
						{
							name:'工作效率',
							type:'value',
						}
					],
					series:[
						{
							name:"工作效率",
							type:'bar',
							data:[],//2.0, 4.9, 7.0, 23.2, 25.6
							barMaxWidth:60,
							itemStyle: {
							        normal: {
							            color: new echarts.graphic.LinearGradient(
							                0, 0, 0, 1,
							                [
							                    {offset: 0, color: '#FFCC66'},
							                    {offset: 0.5, color: '#FFCC33'},
							                    {offset: 1, color: '#FFCC00'}
							                ]
							            )
							        }
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
							markLine:{
								data:[
									{
										name:'标准线',
										yAxis:'',
										lineStyle:{
											color:'rgba(128, 128, 128, 0.5)'
										},
									}
								]
							},
						}
					],
				}
			);


//模糊搜索
$(document).ready(function(){
	$.ajax({
		type:"get",
		// url:"http://localhost:8080/common/assignee",
        url:"http://localhost:8080/common/assignee",
		async:true,
		dataType:'json',
		success:function(data){
			var temp = [];
			temp = data.data['assignee'];
			for(var j=0;j<temp.length;j++){
				$('#down').append('<li>'+temp[j]+'</li>');
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
				$('#down').css('display', 'none'); //点击的不是div或其子元素 
			});
				$("li").click(function(){
					$("#input").val($(this).text());
					$("#down").css("display","none");
					nameDate();
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

//向后台发送数据
$("#middle").find("input.timepicker-input").change(function(){
		nameDate();
//	$("#input").focus(function(){  
//		$(this).attr("data-oval",$(this).val()); //将当前值存入自定义属性  
//		}).blur(function(){  
//			var oldVal=($(this).attr("data-oval")); //获取原值  
//			var newVal=($(this).val()); //获取当前值  
//			if (oldVal!=newVal){
//				
//			}
//		})
})
function nameDate(){
	var myChart1 = echarts.init(document.getElementById('chartmain'));
	var endTime = $("#startTime").val();
	var assignee = $("#input").val();
	if(endTime!=''&&assignee!=''){
					$.ajax({
							type:"post",
							url:"http://localhost:8080/person/efficiency",
							async:true,
							data:{
								assignee:assignee,
								endTime:endTime
							},
							dataType:'json',
							success:function(data){
									myChart1.setOption({        //加载数据图表
									        xAxis: {
									                data: data.data['week']
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