function getComments(postId){
		$.ajax({
			url:"http://localhost:8080/post/getPostComment?postId="+postId,
					success:function(result){
						var s="";
						var comments=result.comments;
						console.log(comments)
						for(var i=0;i<comments.length;i++)
						{
							if(comments[i].user.image!=null){
								s=s+'<div class="row">'
								+'<div class="col-md-4">'
								+'<img src="../../images/'+comments[i].user.image+'" height="50" width="50" style="border-radius: 800px">'
								+'</div>'
								+'<div class="col-md-4">'+comments[i].user.name+'</div>'
								+'<div class="col-md-4">'+comments[i].comment+'</div>'
								+'</div>'
							}
							else{
								s=s+'<div class="row">'
								+'<div class="col-md-4">'
								+'<img src="../../images/profile.png" height="50" width="50" style="border-radius: 800px">'
								+'</div>'
								+'<div class="col-md-4">'+comments[i].user.name+'</div>'
								+'<div class="col-md-4">'+comments[i].comment+'</div>'
								+'</div>'
							}
						}
						$("#modalBody").html(s);
					} 
		});
	}
	
	function deletePost(postId){
		$.ajax({
			url:"http://localhost:8080/post/deletepost?postId="+postId,
					success:function(result){
						location.reload();
					} 
		});
	}
	
	function getLikes(postId){
		$.ajax({
			url:"http://localhost:8080/post/getPostComment?postId="+postId,
					success:function(result){
						console.log(result)
						var s="";
						var comments=result.likes;
						for(var i=0;i<comments.length;i++)
						{
							if(comments[i].user.image!=null){
								s=s+'<div class="row">'
								+'<div class="col-md-4">'
								+'<img src="../../images/'+comments[i].user.image+'" height="50" width="50" style="border-radius: 800px">'
								+'</div>'
								+'<div class="col-md-4">'+comments[i].user.name+'</div>'
								+'</div>'
							}else{
								s=s+'<div class="row">'
								+'<div class="col-md-4">'
								+'<img src="../../images/profile.png" height="50" width="50" style="border-radius: 800px">'
								+'</div>'
								+'<div class="col-md-4">'+comments[i].user.name+'</div>'
								+'</div>'
							}
						}
						$("#likeModalBody").html(s);
					} 
		});
	}