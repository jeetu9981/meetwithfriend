
<%@page import="com.meetnewfriend.dto.ProfileDto"%>
<%@page import="com.meetnewfriend.entity.Following"%>
<%@page import="com.meetnewfriend.entity.Follower"%>
<%@page import="com.meetnewfriend.entity.RealFollower"%>
<%@page import="com.meetnewfriend.entity.User"%>
<%@page import="java.util.List"%>
<%@page import="com.meetnewfriend.entity.Post"%>
<%@page import="java.util.ArrayList"%>

<%
	ProfileDto profile=(ProfileDto) request.getAttribute("profile");
	User user = (User) profile.getUser();
%>

<%@include file="navbar.jsp"%>
<%@include file="succorerror.jsp"%>
<div class="container">
	<div class="row">
		<div class="col-md-6">
			<h1 class="text-center mt-5 text-primary">User Profile</h1>
			<div class="row mt-5">
				<div class="col-md-4">
						<%
							if(user.getImage()!=null){
						%><img alt="" src="../../images/<%=user.getImage()%>" height="200" width="200" style="border-radius: 800px"><%
							}else{
						%>
				<img alt="" src="../../images/profile.png" height="200" width="200" style="border-radius: 800px"><%
					}
				%>
					<div>
						<label class="text-center text-dark">Name : <%=user.getName()%></label>
					</div>
				</div>
				<div class="col-md-4">
					<a href="" data-bs-toggle="modal" data-bs-target="#exampleModal"><h3>
							Followers :<span> <%
 	if (profile.getCountFollowers()>0) {
 %><%=profile.getCountFollowers()%></span>
							<%
								} else {
							%>0<%
								}
							%>
						</h3></a> <a href="" data-bs-toggle="modal" data-bs-target="#exampleModal1"><h3>
							Following :<span> <%
 	if (profile.getCountFollowing()>0) {
 %><%=profile.getCountFollowing()%></span>
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
						Favourite Songs :<b> <%
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
			<h1 class="mt-5 text-center">All Upload Posts</h1>
			<%
				ArrayList<Post> posts = (ArrayList<Post>) profile.getPosts();
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
								<a href="" class="mt-2" onClick="getComments(<%=posts.get(i).getId()%>)" class="mt-2" data-bs-toggle="modal"
											data-bs-target="#viewcomments">view all <%=posts.get(i).getComments().size()%></a>
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




<%@include file="viewcomments.jsp"%>
<script src="../../javascript/dashboard.js"></script>



	<%
		ArrayList<RealFollower> followers = (ArrayList<RealFollower>) profile.getFollowers();
		ArrayList<Following> following = (ArrayList<Following>) profile.getFollowings();
	%>

	<!-- Modal -->
	<div class="modal fade" id="exampleModal" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">All Followers</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>

				<%
					for (RealFollower f : followers) {
				%>

				<div class="modal-body">
					<div class="row">
						<div class="col-md-4">
								<%
									if(f.getFollower().getImage()!=null){
								%><img alt="" src="../../images/<%=f.getFollower().getImage()%>" height="50" width="50" style="border-radius: 800px"><%
									}else{
								%>
				<img alt="" src="../../images/profile.png" height="50" width="50" style="border-radius: 800px"><%
					}
				%>
						</div>
						<div class="col-md-4"><%=f.getFollower().getName()%></div>
						<div class="col-md-4">
							<a href="/follower/followrequest?userId=<%=f.getFollower().getId()%>">Follow</a>
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
					<h5 class="modal-title" id="exampleModalLabel">All Following</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>

				<%
					for (Following f : following) {
				%>

				<div class="modal-body">
					<div class="row">
						<div class="col-md-4">
							<%if(f.getFollowing().getImage()!=null){ %><img alt="" src="../../images/<%=f.getFollowing().getImage()%>" height="50" width="50" style="border-radius: 800px"><%}else{ %>
				<img alt="" src="../../images/profile.png" height="50" width="50" style="border-radius: 800px"><%} %>
						</div>
						<div class="col-md-4"><%=f.getFollowing().getName()%></div>
						<div class="col-md-4">
							<a href="/follower/followrequest?userId=<%=f.getFollowing().getId()%>">Follow</a>
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