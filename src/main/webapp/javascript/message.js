function getMessages(id){
	console.log("ID : "+id)
	$("#userId").val(id);
	$.ajax({
		url:"/message/getSingleUserMessages?recieverId="+id,
		success:function(result){
			var size;
			if(result.sender.length>=result.reciever.length)
				size=result.sender.length;
			else
				size=result.reciever.length;
			var s="";
			console.log(result)
			for(var i=0;i<size;i++){
				
				if(result.reciever.length>=i+1){
					s=s+'<div class="container">'
						+'<div class="row">'
						+'<div class="col-md-4"><h6 style="color:green">'+result.reciever[i].name+'</h6></div>'
						+'<div class="col-md-4"><h6 style="color:green">'+result.reciever[i].message+'</h6></div>'
						+'</div>'
						+'</div>';
				}
				if(result.sender.length>=i+1){
					s=s+'<div class="container">'
						+'<div class="row">'
						+'<div class="col-md-4"><h6 style="color:red">'+result.sender[i].name+'</h6></div>'
						+'<div class="col-md-4"><h6 style="color:red">'+result.sender[i].message+'</h6></div>'
						+'</div>'
						+'</div>';	
				}
			}
			$("#messages").html(s);
		}
	});
}

function addMessage(userId){
	var m=$("#message").val();
	var senderId=$("#userId").val();
	console.log(m);
	$.ajax({
		url:"/message/addmessage?message="+m+"&senderId="+senderId,
		method:"post",
		success:function(result){
			if(result=="ok"){
				location.reload;
			}
		}
	});
}
