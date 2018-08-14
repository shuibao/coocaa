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
//人员工作量
var myChart = echarts.init(document.getElementById('chart1'));	
		myChart.setOption(
			option = {
				title:{
					text:"人员工作量",
					left:'center',
				},
				tooltip:{
					trigger: 'axis'
				},
				
				legend:{
					data:['负责','完成'],
					x:'right',
				    y:'top',
				},
				xAxis:{
					name:'人员',
					type: 'category',
					axisLabel:{
						interval:0,
						rotate:10
					},
					data:[],
				},
				yAxis:{
					name:'工作量',
					type:"value",
				},
				series:[
					{
						name:'负责',
						type:'bar',
						barGap: 0,
						data:[],
						barMaxWidth:40,
					},
					{
						name:'完成',
						type:'bar',
						barGap: 0,
						barMaxWidth:40,
						data:[],
					}
				]
			}
		);
//员工工作完成量
var myChart1 = echarts.init(document.getElementById('chart2'));	
		myChart1.setOption(
			option1 = {
				title:{
					text:'人员负责占比',
					left:"center",
				},
				tooltip:{
					trigger:'item',
				},
				legend:{
					data:[],
					orient:'vertical',
					right:'0px',
					top:'20px'
				},
				series:[
					{
						name:'负责',
						type:'pie',
						hoverAnimation:false,
					    radius : '55%',
            			center: ['50%', '60%'],
						data:[
				           
        				],
					}
				],
			}
		);
$(document).ready(function(){
	$.ajax({
		type:"get",
		url:"http://localhost:8080/common/projectname",
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
$("#middle").find("input.timepicker-input").change(function(){
		nameDate();
})
function nameDate(){
	var myChart = echarts.init(document.getElementById('chart1'));
	var myChart1 = echarts.init(document.getElementById('chart2'));
	var projectName = $("#input").val();
	var startTime =$("#startTime").val();
	var endTime = $("#endTime").val();
	if(startTime!=''&&endTime!=''&&projectName!=''){
					$.ajax({
							type:"post",
							url:"http://localhost:8080/project/assigneestory",
							async:true,
							data:{
								projectName:projectName,
								startTime:startTime,
								endTime:endTime
							},
							dataType:'json',
							success:function(data){
								var percent = data.data['allStoryPoint'];
								var name = data.data['assignee'];
								var arr = [];
	                            for(var i=0;i<name.length;i++){
	                            	arr.push({
				                           name : name[i],
				                           value :percent[i]
				                    })
	                            }
									myChart.setOption({        //加载数据图表
									        xAxis: {
									                data: data.data['assignee']
									                },
									                series: [
										                {
										                    data: data.data['allStoryPoint'],
										                },
										                {
										                	 data: data.data['fixStoryPoint'],
										                }
									                ],
									});
									myChart1.setOption({
										legend:{
											data:data.data['assignee']
										},
										series:[{
											data:arr,
										}]
									});
							},
							error : function(errorMsg) {  
							       alert("图表请求数据失败!");
						           myChart1.hideLoading();  
					            }  
					});
     }
}
