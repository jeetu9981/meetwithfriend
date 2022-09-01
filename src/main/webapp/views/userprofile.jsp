
<%@page import="com.meetnewfriend.entities.FollowingEntity"%>
<%@page import="com.meetnewfriend.entities.FollowerEntity"%>
<%@page import="com.meetnewfriend.entities.RealFollowerEntity"%>
<%@page import="com.meetnewfriend.entities.UserEntity"%>
<%@page import="java.util.List"%>
<%@page import="com.meetnewfriend.entities.PostEntity"%>
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

<%
	UserEntity user = (UserEntity) request.getAttribute("user");
%>

<%@include file="navbar.jsp"%>
<div class="container">
	<div class="row">
		<div class="col-md-6">
			<h1 class="text-center mt-5 text-primary">User Profile</h1>
			<div class="row mt-5">
				<div class="col-md-4">
					<img alt="" src="../../images/<%=user.getImage()%>" height="100"
						width="100" style="border-radius: 800px">
					<div>
						<label class="text-center text-dark">Name : <%=user.getName()%></label>
					</div>
				</div>
				<div class="col-md-4">
					<a href="" data-bs-toggle="modal" data-bs-target="#exampleModal"><h3>
							Followers :<span> <%
 	if (request.getAttribute("countFollower") != null) {
 %><%=request.getAttribute("countFollower")%></span>
							<%
								} else {
							%>0<%
								}
							%>
						</h3></a> <a href="" data-bs-toggle="modal" data-bs-target="#exampleModal1"><h3>
							Following :<span> <%
 	if (request.getAttribute("countFollowing") != null) {
 %><%=request.getAttribute("countFollowing")%></span>
							<%
								} else {
							%>0<%
								}
							%>
						</h3></a>
				</div>
			</div>
			<img alt="" src="">
		</div>
		<div class="col-md-6 mt-5">
			<div class="row">
				<h1 class="text-center text-primary">Personal Details</h1>
				<div class="col-md-3"></div>
				<div class="col-md-6 mt-5">
					<h6>
						Favourite Books : <b> <%
 	if (user.getFavouritBooks() != null) {
 %><%=user.getFavouritBooks()%> <%
 	}
 %>
						</b>
					</h6>
					<h6>
						Favourite Places :<b> <%
 	if (user.getFavouritePlaces() != null) {
 %> <%=user.getFavouritePlaces()%> <%
 	}
 %>
						</b>
					</h6>
					<h6>
						Favourite Songss :<b> <%
 	if (user.getFavouriteSongs() != null) {
 %> <%=user.getFavouriteSongs()%> <%
 	}
 %>
						</b>
					</h6>
				</div>
				<div class="col-md-3"></div>

			</div>
		</div>
	</div>

	<hr>

	<div class="container mt-5">
		<div class="row mb-5">
			<h1 class="mt-5 text-center">All Uplaod Posts</h1>
			<%
				ArrayList<PostEntity> posts = (ArrayList<PostEntity>) request.getAttribute("allposts");
				for (int i = 0; i < posts.size(); i++) {
			%>
			<div class="col-md-3 mt-4">
				<div class="card" style="width: 15rem;">
					<img height="300" width="300"
						src="../../images/<%=posts.get(i).getImage()%>"
						class="card-img-top">
					<ul class="list-group list-group-flush">
						<h6>
							Description :
							<%=posts.get(i).getDescription()%>
						</h6>
						<div class="row">
							<div class="col-md-1"></div>
							<div class="col-md-5">
								<a href="" class="mt-2">Delete</a>
							</div>
							<div class="col-md-5">
								<a href="" class="mt-2">Comments</a>
							</div>
							<div class="col-md-1"></div>
						</div>

					</ul>
				</div>
			</div>
			<%
				}
			%>
		</div>
	</div>








	<%
		ArrayList<RealFollowerEntity> followers = (ArrayList<RealFollowerEntity>) request.getAttribute("followers");
		ArrayList<FollowingEntity> following = (ArrayList<FollowingEntity>) request.getAttribute("followings");
	%>

	<!-- Modal -->
	<div class="modal fade" id="exampleModal" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>

				<%
					for (RealFollowerEntity f : followers) {
				%>

				<div class="modal-body">
					<div class="row">
						<div class="col-md-4">
							<img alt="" src="../../images/<%=f.getFollower().getImage()%>"
								height="50" width="50" style="border-radius: 800px">
						</div>
						<div class="col-md-4"><%=f.getFollower().getName()%></div>
						<div class="col-md-4">
							<a href="/follower/followrequest?userId=<%=f.getUser_id()%>">Follow</a>
						</div>
					</div>
				</div>
				<%
					}
				%>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>


	<!-- Modal -->
	<div class="modal fade" id="exampleModal1" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>

				<%
					for (FollowingEntity f : following) {
				%>

				<div class="modal-body">
					<div class="row">
						<div class="col-md-4">
							<img alt="" src="../../images/<%=f.getFollowing().getImage()%>"
								height="50" width="50" style="border-radius: 800px">
						</div>
						<div class="col-md-4"><%=f.getFollowing().getName()%></div>
						<div class="col-md-4">
							<a href="/follower/followrequest?userId=<%=f.getUser_id()%>">Follow</a>
						</div>
					</div>
				</div>
				<%
					}
				%>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>