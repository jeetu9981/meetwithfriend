<%@page import="com.meetnewfriend.entities.CommentEntity"%>
<%@page import="java.util.List"%>
<%@page import="com.meetnewfriend.entities.LikeEntity"%>
<%@page import="com.meetnewfriend.entities.PostEntity"%>
<%@page import="com.meetnewfriend.entities.RealFollowerEntity"%>
<%@page import="java.util.ArrayList"%>


<%@include file="navbar.jsp"%>
<%@include file="succorerror.jsp"%>

<head>
	<style>
		.box{
			  /* width: 300px; */
			  border: 2px solid black;
			 	padding: 10px;
			   margin: 20px; 
		}
	</style>
</head>


<%
	ArrayList<PostEntity> posts = (ArrayList<PostEntity>) request.getAttribute("posts");
	if (posts.size() > 0) {
%>

<div class="container mt-5 frontpage">
	<div class="row">
		<h1 class="text-center ">All Uploaded Posts</h1>
		<%
			for (PostEntity post : posts) {
		%>
		<div class="col-md-4"></div>
		<div class="col-md-3 mt-3 mb-5 box">
			<div class="row mt-2">
				<div class="col-md-3">
					<a href="/user/userprofile?userId=<%=post.getUser().getId()%>">
						<img alt="" src="../../images/<%=post.getUser().getImage()%>" style="border-radius: 100px" height="60" width="60">
					</a>
				</div>
				<div class="col-md-6 mt-3">
					<h6><%=post.getUser().getName()%></h6>
				</div>
				<div class="col-md-3"></div>
		</div>
		<b><hr></b>
			<div class="row">
				<img alt="" src="../../images/<%=post.getImage()%>" height="400">
			</div>
			<div class="row mt-3">
				<div class=col-md-6>
					<div class="row">
						<div class="col-md-4">
							<%
								List<LikeEntity> likes = post.getLikes();
								boolean status = true;
								if (likes.size() > 0) {
								for (LikeEntity l : likes) {
							%>
							<%
								if (l.getUser().getId() == (int) session.getAttribute("userId") && l.isStatus() == true) {
							%>
							<button type="submit"
								onClick="disLike(<%=l.getUser().getId()%>,<%=post.getId()%>,<%=l.getRealuser()%>)"
								> <i class="fa fa-heart" id="dislike<%=post.getId()%>"></i></button>
							<%
								status = false;
												}
											}
										}
										if (status) {
							%>
							<button href="" onClick="addLike(<%=post.getUser().getId()%>,<%=post.getId()%>)"
								><i
								class="fa fa-heart-o" id="addlike<%=post.getId()%>"></i></button>
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
			</div>
			<div class="row">
				<div class="col-md-4">
					<a class="btn btn-dark mt-1" data-bs-toggle="modal" onClick="getLikes(<%=post.getId()%>)"
						data-bs-target="#viewalllikes" href="" style="cursor: pointer">Liked
						:<span id="likecount<%=post.getId()%>"><%=post.getLikes().size()%></span>
					</a>
				</div>
				<div class="col-md-8">
					<a class="btn btn-dark mt-1" href="" onClick="getComments(<%=post.getId()%>)"
						data-bs-toggle="modal" data-bs-target="#viewcomments"
						style="cursor: pointer">View all <%=post.getComments().size()%>
						comments
					</a> <input hidden="true" id="vale" value="0">
				</div>
			</div>
		</div>
		<div class="col-md-4"></div>
		<%
			}
		%>
	</div>
</div>

<%
	} else {
%>
<div class="container mt-5">
	<div class="row">
		<div class="col-md-4"></div>
		<div class="col-md-4">
			<img alt="" src="../../images/noresult.png">
		</div>
		<div class="col-md-4"></div>
	</div>
</div>
<%
	}
%>

<%@include file="addcomments.jsp"%>
<%@include file="viewcomments.jsp"%>
<%@include file="viewlikes.jsp"%>

<script src="../../javascript/dashboard.js"></script>









