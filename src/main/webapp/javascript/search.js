function follow(id){
	console.log("ID : "+id);
	$.ajax({
		url:"/follower/followrequest?userId="+id,
		success : function(result){
			console.log(result)
			if(result=="success")
				location.reload();
		}
	});
}