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
var myChart = echarts.init(document.getElementById('chartmain'));
var myChart1 = echarts.init(document.getElementById('chartmain1'));
			myChart.setOption(
				option = {
					title:{
						text:'已解决问题',
						left:"center",
					},
					tooltip:{
						show:true,
						trigger:'axis',				
					},
					legend:{
						data:['Σ原预估时间','Σ耗费时间'],
						selectedMode:false,
						x:'right',
						y:'top',
					},
					xAxis:[
						{
							name:'问题名称',
							show:true,
							type:'category',
							data:[],
						}
					],
					yAxis:[
						{
							name:"时间",
							type:'value',
						}
					],
					series:[
						{
							name:"Σ原预估时间",
							type:'bar',
							barGap: 0,
							barMaxWidth:40,
							data:[],
							itemStyle:{
								color:"#ccc",
							},
						},
						{
							name:"Σ耗费时间",
							type:'bar',
							barGap: 0,
							barMaxWidth:40,
							data:[],
							itemStyle:{
								color:"blue",
							},
						}
					],
				}
			);
			myChart1.setOption(
				option = {
					title:{
						text:'未解决问题',
						left:"center",
					},
					tooltip:{
						show:true,
						trigger:'axis',				
					},
					legend:{
						data:['Σ原预估时间','Σ耗费时间'],
						selectedMode:false,
						x:'right',
						y:'top',
					},
					xAxis:[
						{
							name:'问题名称',
							show:true,
							type:'category',
							data:[],
						}
					],
					yAxis:[
						{
							name:"时间",
							type:'value',
						}
					],
					series:[
						{
							name:"Σ原预估时间",
							type:'bar',
							barGap: 0,
							barMaxWidth:40,
							data:[],
							itemStyle:{
								color:"#ccc",
							},
						},
					],
				}
			);
$(document).ready(function(){
	$.ajax({
		type:"get",
		url:'http://localhost:8080/common/assignee',
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
				$("#down").find("li").click(function(){
					$("#input").val($(this).text());
					$("#down").css("display","none");
					project();
				});
				$("#input").click(function(){
					$("#pro_input").val(' ');
					fun();
				});
				$("#input").keydown(function(){
					$("#pro_input").val(' ');
					fun();
				});
				function fun(){
					var queryStr = $.trim($("#input").val());
					if(queryStr === ''){
						$("#down").css("display","block");
							$("#down").find("li").show();
						}else{
							$("#down").find("li").hide().filter(":contains('"+queryStr+"')").show();
						}
				}
		}

	});
})
function project(){
			var assignee = $("#input").val();
			$.ajax({
				type:"get",
				url:" http://localhost:8080/common/projectname",
				async:true,
				data:{
					assignee:assignee,
				},
				dataType:'json',
				success:function(result){
					var pro = [];
					pro = result.data['projectName'];
					$("#ul1").empty();
					for(var j=0;j<pro.length;j++){
						$('#ul1').append("<li>"+pro[j]+"</li>");
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
						$('#ul1').css('display', 'none'); //点击的不是div或其子元素
					});
						$("#ul1").find("li").click(function(){
							$("#pro_input").val($(this).text());
							$("#ul1").css("display","none");
							nameDate();
						});
						$("#pro_input").click(function(){
							$("#ul1").css("display","block");
							$("#ul1").find("li").show();
						});
						$("#pro_input").keydown(function(){
							$("#ul1").css("display","block");
							$("#ul1").find("li").show();
						});

				}
			});
}
//日期传给后台,将日期控制在7天内
function getDaysIndex(date) {
	var result = date.split("-");
	var year = parseInt(result[0]);
	var month = parseInt(result[1]);
	var day = parseInt(result[2]);
	var leapYears = getLeapYears(year - 1);
	var monthDays = [ 0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334 ];
	return (year - 1) * 365 + leapYears + monthDays[month - 1] + day + (month > 2 && isLeapYear(year) ? 1 : 0);
}
function isLeapYear(year) {
	return year % 400 == 0 || (year % 4 == 0 && year % 100 != 0);
}
function getLeapYears(year) {
	return Math.floor(year / 4) - Math.floor(year / 100) + Math.floor(year / 400);
}
$("#middle").find("input.timepicker-input").change(function(){
	if($("#startTime").val()!=''&&$("#endTime").val()!=''){
		var data1 = $("#startTime").val();
		var data2 = $("#endTime").val();
		var result = getDaysIndex(data2) - getDaysIndex(data1);
	if(result<7&&result>0){
		nameDate();
	}else{
		$("#box").css('display','block');//显示
		setTimeout(function(){//5秒后隐藏
		    $("#box").fadeOut();
		}, 2000);
	}
	}
})

function nameDate(){
	var myChart = echarts.init(document.getElementById('chartmain'));
	var myChart1 = echarts.init(document.getElementById('chartmain1'));
	var assignee = $("#input").val();
	var projectName=$("#pro_input").val();
	var startTime =$("#startTime").val();
	var endTime = $("#endTime").val();
	$('#sum').find('.for').html('');
	$('#sum').find('.expend').html('');
	if(startTime!=''&&endTime!=''&&assignee!=''&&projectName!=''){
					$.ajax({
							type:"post",
							url:'http://localhost:8080/person/issuetime',
							async:true,
							data:{
								assignee:assignee,
								projectName:projectName,
								startTime:startTime,
								endTime:endTime
							},
							dataType:'json',
							success:function(data){
								var res = data.data.length;
								var a = data.data;
								for(var i=0;i<res;i++){
									if(a[i].resolution === 1){
										var esitmate = a[i].sumOriginal;
										var cost = a[i].sumSpent;
										var box = "<p class='for'>"+esitmate+"</p>";
										$("#estimate").append(box);
										var box1 = "<p class='expend'>"+cost+"</p>";
										$("#cost").append(box1);
										myChart.setOption({        //加载数据图表
										        xAxis: {
										                data: a[i].issueKey
										                },
										                series: [
										                	{
										                    	data: a[i].originalEstimate,
										                	},
										                	{
										                		data:a[i].timeSpent,
										                	}
										                ],
										});
									}else if(a[i].resolution === 0){
										myChart1.setOption({        //加载数据图表
										        xAxis: {
										                data: a[i].issueKey
														},
										                series: [
											                {
											                    data: a[i].originalEstimate,
											                },
										                ],
										});
									}
								}
								
							},
							error : function(errorMsg) {  
							       alert("图表请求数据失败!");
						           myChart1.hideLoading();  
					            }  
					});
     }
}


