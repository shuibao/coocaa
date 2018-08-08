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
var myChart = echarts.init(document.getElementById('chartmain'));
			myChart.setOption(
				option = {
					title:{
						text:'人员效率比较',
						left:"center",
					},
					tooltip:{
						show:true,
						trigger:'axis',				
					},
					gird:{
						left:'3%',
						right:'15%',
						bottom:"10%",
						containLabel:true,
					},
					legend:{
						name:'效率',
						selectedMode:false,
						x:'right',
						y:'top',
					},
					xAxis:[
						{
							name:'项目名称',
							show:true,
							type:'category',
							data:[],
						}
					],
					yAxis:[
						{
							name:"人均效率",
							type:'value',
						}
					],
					series:[
						{
							name:"人均效率",
							type:'bar',
							barMaxWidth:40,
							data:[],
							itemStyle:{
								color:"#ccc",
							},
						}
					],
				}
			);
//项目名
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
				    nameDate();
				})
			})
		}
	})
})

$("#middle").find("input.timepicker-input").change(function(){
		nameDate();
})
function nameDate(){
	var myChart1 = echarts.init(document.getElementById('chartmain'));
	var projectName = $("#pro_input").val();
	var arr = projectName.split('、').join(',');
	var str = arr.substring(0,arr.length);
	var startTime = $("#startTime").val();
	var endTime = $("#endTime").val();
	if(startTime!=''&&endTime!=''&&projectName!=''){
					$.ajax({
							type:"post",
							url:"http://120.78.67.238:8080/project/efficiency",
							async:true,
							data:{
								projectName:str,
								startTime:startTime,
								endTime:endTime
							},
							dataType:'json',
							success:function(data){
									myChart1.setOption({        //加载数据图表
									        xAxis: {
									                data: data.data['projectName']
										            },
									                series: [{
									                    data: data.data['efficiency'],
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