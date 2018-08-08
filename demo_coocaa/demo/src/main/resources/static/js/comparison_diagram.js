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
						text:'效率比较',
						left:'center',
					},
					tooltip:{
						show:true,
						trigger:'axis',	
						axisPointer:{
							show:false,
						}
					},
					grid:{
						left:'3%',
						right:'15%',
						bottom:"10%",
						containLabel:true,
					},
					legend:{
						name:'工作效率',
						selectedMode:false,
						right: 10,
        				top: 20,
					},
					xAxis:[
						{
							name:'工作人员',
							show:true,
							type:'category',
							data:[],
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
							data:[],
							barMaxWidth:30,
							itemStyle:{
								color:"#0066FF",
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
		// url:"http://120.78.67.238:8080/common/assignee",
        url:"http://120.78.67.238:8080/common/assignee",
		async:true,
		dataType:'json',
		success:function(data){
			var temp = [];
			temp = data.data['assignee'];
			for(var j=0;j<temp.length;j++){
				$('#ul1').append("<li><input type='checkbox' name='check' value='"+temp[j]+"'>"+temp[j]+"</li>");
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
			$('#input').click(function(){
				$("#ul1").css("display","block");
				$("input[name='check']").change(function(){
					var result='';
			        $("input[name='check']:checked").each(function(){    
			            result += $(this).val()+'、';
				    });
				    if(result!=""){
				        result=result.substring(0,result.lastIndexOf('、')); 
			    	}
				    $("#input").val(result);
				    nameDate();
				})
			})
		}
	})
})
//实现复选框

//日期传给后台
$("#middle").find("input.timepicker-input").change(function(){
		nameDate();
})

function nameDate(){
	var myChart1 = echarts.init(document.getElementById('chartmain'));
	var assignee = $("#input").val();
	var arr = assignee.split("、").join(',');
	var str = arr.substring(0,arr.length);
	var startTime =$("#startTime").val();
	var endTime = $("#endTime").val();
	if(startTime!=''&&endTime!=''&&assignee!=''){
					$.ajax({
							type:"post",
							url:"http://120.78.67.238:8080/person/compare",
							async:true,
							data:{
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
									});
							},
							error : function(errorMsg) {  
							       alert("图表请求数据失败!");
						           myChart1.hideLoading();  
					            }  
					});
     }
}