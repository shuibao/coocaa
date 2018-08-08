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
var myChart = echarts.init(document.getElementById('chartmain'));	
		myChart.setOption(
			option = {
				title:{
					text:"剩余工作报告",
					left:'center',
				},
				tooltip:{
					trigger: 'axis'
				},
				grid:{
					left:'3%',
					right:'15%',
					bottom:"10%",
					containLabel:true,
				},
				legend:{
					data:['Σ原预估时间','Σ剩余时间'],
					selectedMode:false,
					right: 10,
        			top: 20,
				},
				xAxis:{
					name:'未解决问题',
					type: 'category',
					data:[],
					axisLabel:{
						interval:0,
					}
				},
				yAxis:{
					name:'时间',
					type:"value",
				},
				series:[
					{
						name:'Σ原预估时间',
						type:'bar',
						barGap: 0,   //bar之间的间距
						data:[],
						barMaxWidth:30,
						itemStyle:{
								color:"#999",
						},
					},
					{
						name:'Σ剩余时间',
						type:'bar',
						barGap: 0,
						barMaxWidth:30,
						data:[],
						itemStyle:{
								color:"#ccc",
						},
					}
				]
			}
		);
$(document).ready(function(){
	$.ajax({
		type:"get",
		url:"http://120.78.67.238:8080/common/assignee",
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
	});
})

//向后台发送数据
$("#middle").find("input.timepicker-input").change(function(){
	nameDate();
})
function nameDate(){
	var myChart1 = echarts.init(document.getElementById('chartmain'));
	var startTime =$("#startTime").val();
	var endTime = $("#endTime").val();
	var assignee = $("#input").val();
	$("#surplus").find(".num").html(" ");
	if(startTime!=''&&endTime!=''&&assignee!=''){
					$.ajax({
							type:"post",
							url:"http://120.78.67.238:8080/person/remaintime",
							async:true,
							data:{
								assignee:assignee,
								startTime:startTime,
								endTime:endTime
							},
							dataType:'json',
							success:function(data){
								var surplus = data.data['allRemainingTime'];
								var box = "<p class='num'>"+surplus+"</p>";
								$("#surplus").append(box);
									myChart1.setOption({ //加载数据图表
											//allRemainingTime
									        xAxis: {
									                data: data.data['issueKey']
									                },
									                series: [
										                {
										                    data: data.data['allOriginalEstimate'],
										                },
										                {
										                	data: data.data['allRemainingEstimate'],
										                }
									                ],
									});
							},
							error : function(errorMsg) {  
							       alert("图表请求数据失败!");
						           myChart1.hideLoading();  
					            }  
					});
     }
}