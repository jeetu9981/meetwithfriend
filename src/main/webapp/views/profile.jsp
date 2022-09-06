
<%@page import="com.meetnewfriend.dto.ProfileDto"%>
<%@page import="com.meetnewfriend.entity.Comment"%>
<%@page import="com.meetnewfriend.entity.Following"%>
<%@page import="com.meetnewfriend.entity.RealFollower"%>
<%@page import="com.meetnewfriend.entity.User"%>
<%@page import="java.util.List"%>
<%@page import="com.meetnewfriend.entity.Post"%>
<%@page import="java.util.ArrayList"%>

<%
	ProfileDto profile = (ProfileDto) request.getAttribute("profile");
	ArrayList<Post> posts=(ArrayList<Post>)profile.getPosts();
	User user = (User) profile.getUser();
%>

<%@include file="navbar.jsp"%>
<%@include file="succorerror.jsp"%>
<div class="container mt-5">
	<div class="row">
		<div class="col-md-2"></div>
		<div class="col-md-4">
			<h1 class="mx-2 mt-5 text-danger">User Profile</h1>
			<%
				if(user.getImage()!=null){
			%><img alt="" src="../../images/<%=user.getImage()%>" height="200" width="200" style="border-radius: 800px"><%
				}else{
			%>
				<img alt="" src="../../images/profile.png" height="200" width="200" style="border-radius: 800px"><%
					}
				%>
			<div>
				<h5 class="mx-4 text-dark"><b>Name : </b><%=user.getName()%></h5>
			</div>
		</div>
	
		<div class="col-md-4">
			<h1 class="text-center text-danger mt-5">Personal Details</h1>
			
			<div class="row mx-5 mt-3">
				<h6><b>Favourite Books :</b>  <%
  	if (user.getFavouritBooks() != null) {
  %><%=user.getFavouritBooks()%> <%
 	}
 %></h6>
			</div>
			<div class="row mx-5 mt-3">
				<h6><b>Favourite Places : </b> <%
 	if (user.getFavouritePlaces()!= null) {
 %><%=user.getFavouritePlaces()%> <%
 	}
 %></h6>
			</div>
			<div class="row mx-5 mt-3">
				<h6><b>Favourite Songs : </b> <%
 	if (user.getFavouriteSongs() != null) {
 %><%=user.getFavouriteSongs()%> <%
 	}
 %></h6>
			</div>
			
		</div>
		<div class="col-md-2"></div>
	</div>
	
	<div class="row mt-24">
		<div class="col-md-1"></div>
		<div class="col-md-2 mt-3">
			<a href="" class="btn btn-dark" data-bs-toggle="modal" data-bs-target="#exampleModal">Followers :<span> 
				<%
 					if (profile.getCountFollowers() >0) 
 				 					 				{
 				%>
	 					<%=profile.getCountFollowers()%></span>
				<%
					}
							 				else 
							 				{
				%>
						0
				<%
					}
				%>
			</a>
		</div>
		
		<div class="col-md-3 mt-3">
			<a href="" class="btn btn-dark" data-bs-toggle="modal" data-bs-target="#exampleModal1">Following :<span> 
				<%
 					if (profile.getCountFollowing() >0) 
 				 					 				{
 				%>
	 					<%=profile.getCountFollowing()%></span>
				<%
					}
							 				else 
							 				{
				%>
						0
				<%
					}
				%>
			</a>
		</div>
	</div>
	
	<hr>
	<h3 class="text-center">Details Edit</h3>
	<div class="row mt-5">
		<div class="col-md-4"></div>
		<div class="col-md-3">
			<a class="btn btn-dark" href="../post/addpost">Add Post</a>
		</div>
		<div class="col-md-4">
			<a class="btn btn-dark" href="/user/edituserprofile?userId=<%=user.getId()%>">Edit Profile</a>
		</div>
	</div>
	<hr>
</div>

<div class="container mt-5 my-5">
	<h1 class="text-center">Upload Posts</h1>
	<%
		if (posts.size() > 0) 
		{
			for (int i = 0; i < posts.size(); i++) 
			{
	%>
				<div class="col-md-3 mt-4">
					<div class="card" style="width: 15rem;">
						<img height="300" width="300" src="../../images/<%=posts.get(i).getImage()%>" class="card-img-top">
						<ul class="list-group list-group-flush">
							<h6>Description : <%=posts.get(i).getDescription()%></h6>
							<div class="row">
								<div class="col-md-1"></div>
								<div class="col-md-5">
									<a onclick="deletePost(<%=posts.get(i).getId()%>)" href="" class="mt-2">Delete</a>
								</div>
								
								<div class="col-md-5">
									<a href="" data-bs-toggle="modal" onClick="getLikes(<%=posts.get(i).getId()%>)" data-bs-target="#viewalllikes" class="mt-2">Liked By : <%=posts.get(i).getLikes().size()%></a>
								</div>
								
								<div class="col-md-1"></div>
							</div>
							
							<div class="row mt-2">
								<div class="col-md-2"></div>
									<div class="col-md-8">
										<a href="" onClick="getComments(<%=posts.get(i).getId()%>)" class="mt-2" data-bs-toggle="modal"
											data-bs-target="#viewcomments">view all <%=posts.get(i).getComments().size()%> comments</a>
									</div>
								</div>
						</ul>
					</div>
				</div>
	<%
		}
		} 
		
		else 
		{
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
</div>

<%@include file="viewfollowing.jsp"%>
<%@include file="viewfollowers.jsp"%>
<%@include file="viewcomments.jsp"%>
<%@include file="viewlikes.jsp"%>

<script src="../../javascript/dashboard.js"></script>
<script src="../../javascript/profile.js"></script>