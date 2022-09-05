function search(){
	var searchvalue=$("#searchvalue").val();
	console.log("RESULT : "+searchvalue)
	$.ajax({
		url:"/user/searchuser?searchvalue="+searchvalue,
		success : function(result){
			if(result!=null){
				$(".frontpage").html("");
			    $("#fix").html(result);
			}
			else
				$("#fix").html("");
		}
	});
}