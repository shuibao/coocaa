//$("input[name='all']").change(function(){
//		if($(this).is(":checked")){
//					$("[name='check']").prop("checked",true);
//					$("input[name='check']:checked").each(function(){    
//          			result += $(this).val()+'、';
//	   				 });	
//				}else{
//					$("[name='check']").prop("checked",false);
//				}
//		 $("#input").val(result);
//})

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
	})
})