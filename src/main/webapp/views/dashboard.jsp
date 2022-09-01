<%@page import="com.meetnewfriend.entities.CommentEntity"%>
<%@page import="java.util.List"%>
<%@page import="com.meetnewfriend.entities.LikeEntity"%>
<%@page import="com.meetnewfriend.entities.PostEntity"%>
<%@page import="com.meetnewfriend.entities.RealFollowerEntity"%>
<%@page import="java.util.ArrayList"%>
<%
	if (session.getAttribute("succMsg") != null) {
%>
<input type="hidden" id="msg"
	value="<%=session.getAttribute("succMsg")%>">
<script type="text/javascript">
	var m = document.getElementById("msg");
	alert(m.defaultValue)
</script>
<%
	session.removeAttribute("succMsg");
	}
%>

<%
	if (session.getAttribute("failMsg") != null) {
%>
<input type="hidden" id="msg"
	value="<%=request.getAttribute("failMsg")%>">
<script type="text/javascript">
	var m = document.getElementById("msg");
	alert(m.defaultValue)
</script>
<%
	}
%>
<!-- Message of success or fail  End-->

<%@include file="navbar.jsp"%>

<%
	ArrayList<PostEntity> posts = (ArrayList<PostEntity>) request.getAttribute("posts");
%>

<div class="container mt-5">
	<div class="row">
		<h1 class="text-center text-primary">All Posts</h1>
		<%
			for (PostEntity post : posts) {
		%>
		<div class="col-md-4"></div>
		<div class="col-md-3 mt-5 mb-5">
			<div class="row">
				<div class="col-md-3">
					<a href="/user/userprofile?userId=<%=post.getUser().getId()%>"><img
						alt="" src="../../images/<%=post.getUser().getImage()%>"
						style="border-radius: 100px" height="60" width="60"></a>
				</div>
				<div class="col-md-1">
					<h6><%=post.getUser().getName()%></h6>
				</div>
				<div class="col-md-6"></div>
			</div>

			<div class="row">
				<img alt="" src="../../images/<%=post.getImage()%>" height="400">
			</div>
			<div class="row mt-3">
				<div class=col-md-6>
					<div class="row">
						<div class="col-md-2">
							<%
								List<LikeEntity> likes = post.getLikes();
									if (likes.size() > 0) {
										for (LikeEntity l : likes) {
							%>
							<%
								if (l.getRealuser() == (int) session.getAttribute("userId") && l.isStatus() == true) {
							%>
							<a
								href="/like/dislike?likeUser=<%=post.getUser().getId()%>&postId=<%=post.getId()%>"><i
								class="fa fa-heart"></i></a>
							<%
								break;
											} else if (true) {
							%>
							<a
								href="/like/addlike?likeUser=<%=post.getUser().getId()%>&postId=<%=post.getId()%>"><i
								class="fa fa-heart-o"></i></a>
							<%
								break;
											}
							%>

							<%
								}
									} else {
							%>
							<a
								href="/like/addlike?likeUser=<%=post.getUser().getId()%>&postId=<%=post.getId()%>"><i
								class="fa fa-heart-o"></i></a>
							<%
								}
							%>
						</div>
						<div class="col-md-2">
							<a
								onClick="getData(<%=post.getUser().getId()%>,<%=post.getId()%>)"
								href="" data-bs-toggle="modal" data-bs-target="#exampleModal"
								id="btn"><i class="fa fa-comment"></i></a>
						</div>
					</div>

				</div>
				<div class="col-md-6">
					<button class="getComments">ViewComments</button>
					<input hidden="true" id="vale" value="0">

					<div class="t" style="display: none">
						<%
							int count = 1;
								for (CommentEntity comment : post.getComments()) {
						%>
						<p><%=count++%>)<%=comment.getUser().getEmail()%>
							:
							<%=comment.getComment()%></p>
						<%
							}
						%>
					</div>

				</div>

			</div>
		</div>
		<div class="col-md-4"></div>
		<%
			}
		%>
	</div>
</div>


<div class="modal fade" id="exampleModal" tabindex="-1"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">Add Comment</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<form action="/comment/addcomment" action="post">
				<div class="modal-body">

					<input type="text" placeholder="Enter Comment" name="comment">
					<input type="text" hidden="true" id="commentUser" value=""
						name="commentUser"> <input type="text" hidden="true"
						id="postId" value="" name="postId">
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">Close</button>
					<button type="submit" class="btn btn-primary">Comment</button>
				</div>
			</form>
		</div>
	</div>
</div>


<script>
	function getData(commentUser,postId){
		$("#commentUser").val(commentUser);
		$("#postId").val(postId);
	}
	
	 $(document).ready(function(){ 
		  $(".getComments").click(function(){
			  var v=$("#vale").val();
			  if(v==1){
				  $(".t").hide();
				  $("#vale").val("0");
			  }else{
				  $(".t").show();
				  $("#vale").val("1");
			  }
		    
		  });
		 });
</script>













