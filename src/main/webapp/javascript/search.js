function follow(id){
	$.ajax({
		url:"/follower/followrequest?userId="+id,
		success : function(result){
			if(result=="success")
				location.reload();
		}
	});
}

function mutualFriends(id){
	$.ajax({
		url:"/following/mutualfriends?userId="+id,
		success : function(result){
			var s="";
			var comments=result;
			for(var i=0;i<comments.length;i++)
			{
				if(comments[i].image!=null){
					s=s+'<div class="row">'
					+'<div class="col-md-4">'
					+'<img src="../../images/'+comments[i].image+'" height="50" width="50" style="border-radius: 800px">'
					+'</div>'
					+'<div class="col-md-4">'+comments[i].name+'</div>'
					+'</div>'
				}else{
					s=s+'<div class="row">'
					+'<div class="col-md-4">'
					+'<img src="../../images/profile.png" height="50" width="50" style="border-radius: 800px">'
					+'</div>'
					+'<div class="col-md-4">'+comments[i].name+'</div>'
					+'</div>'
				}
			}
			$("#mutualfri").html(s);
		}
	});
}
