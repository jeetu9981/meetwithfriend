

	function disLike(likeUser,postId,realUser){
		$.ajax({
			url:"http://localhost:8080/like/dislike?likeUser="+likeUser+"&postId="+postId+"&realUser="+realUser,
			success : function(result){
				 var likecount=$("#likecount"+postId).text();

				likecount=parseInt(likecount)-1;
				console.log(likecount);
				$("#likcount"+postId).html(likecount);
				
				$("#dislike"+postId).removeClass("fa fa-heart");
				 $("#dislike"+postId).addClass("fa fa-heart-o");
				 location.reload();
				 
			}
		});
	}
	
	
	function addLike(likeUser,postId){
		$.ajax({
			url:"http://localhost:8080/like/addlike?likeUser="+likeUser+"&postId="+postId,
			success : function(result){
				 var likecount=$("#likecount"+postId).text();
				
				likecount=parseInt(likecount)+1;
				$("#likcount"+postId).html(likecount);
				
					$("#addlike"+postId).removeClass("fa fa-heart-o");
				 $("#addlike"+postId).addClass("fa fa-heart");
				 location.reload();
				 
			}
		});
	}
	


	function getData(commentUser,postId){
		$("#commentUser").val(commentUser);
		$("#postId").val(postId);
	}
	
	function getComments(postId){
		$.ajax({
			url:"http://localhost:8080/post/getPostComment?postId="+postId,
					success:function(result){
						var s="";
						var comments=result.comments;
						for(var i=0;i<comments.length;i++)
						{
							console.log("/images/"+comments[i].user.image);
							s=s+'<div class="row">'
							+'<div class="col-md-4">'
							+'<img src="../../images/'+comments[i].user.image+'" height="50" width="50" style="border-radius: 800px">'
							+'</div>'
							+'<div class="col-md-4">'+comments[i].user.name+'</div>'
							+'<div class="col-md-4">'+comments[i].comment+'</div>'
							+'</div>'
						}
						$("#modalBody").html(s);
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
							console.log(comments[i])
							s=s+'<div class="row">'
							+'<div class="col-md-4">'
							+'<img src="../../images/'+comments[i].user.image+'" height="50" width="50" style="border-radius: 800px">'
							+'</div>'
							+'<div class="col-md-4">'+comments[i].user.name+'</div>'
							+'</div>'
						}
						$("#likeModalBody").html(s);
					} 
		});
	}
