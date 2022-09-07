<%@page import="com.meetnewfriend.entity.User"%>
<%@include file="navbar.jsp"%>

<%
	User user=(User)request.getAttribute("user");
%>

<div class="container mt-5">
	<div class="row">
		<h1 class="text-center text-primary">Update Your Profile</h1>
		<%@include file="succorerror.jsp"%>
		<div class="col-md-4"></div>
		<div class="col-md-4">
			<form class="mt-5" action="/user/updateuser" method="post"
				enctype="multipart/form-data">
				<input type="hidden" value="<%=user.getId()%>" name="id">
				<div class="mb-3">
					<label for="exampleInputEmail1" class="form-label">Add Your Name</label> 
					<input type="text" class="form-control" id="email" aria-describedby="emailHelp" name="name" value="<%=user.getName() %>">
				</div>
				
				<div class="mb-3">
					<label for="exampleInputEmail1" class="form-label">Add Your Email</label> 
					<input type="email" class="form-control"
						id="email" aria-describedby="emailHelp" name="email" value="<%=user.getEmail() %>">
				</div>
				
				<div class="mb-3 mt-5">
					<label for="exampleInputEmail1" class="form-label">Upload
						your profile image address</label> <input type="file" class="form-control"
						id="email" aria-describedby="emailHelp" name="image1">
				</div>

				<div class="mb-3">
					<label for="exampleInputEmail1" class="form-label">Add Your Favourite Songs</label>
					<%
						if(user.getFavouriteSongs()!=null)
						{
					%> 
							<input type="text" class="form-control" id="email" aria-describedby="emailHelp" name="favouriteSongs" value="<%=user.getFavouriteSongs() %>">
					<%	
						}
						else
						{ 
					%>
							 <input type="text" class="form-control" id="email" aria-describedby="emailHelp" name="favouriteSongs">
					<%
						} 
					%>
				</div>

				<div class="mb-3">
					<label for="exampleInputEmail1" class="form-label">Add Your Favourite Places</label> 
					<%
						if(user.getFavouritePlaces()!=null)
						{
					%> 
							<input type="text" class="form-control" id="email" aria-describedby="emailHelp" name="favouritePlaces" value="<%=user.getFavouritePlaces() %>">
					<%	
						}
						else
						{ 
					%>
							 <input type="text" class="form-control" id="email" aria-describedby="emailHelp" name="favouritePlaces">
					<%
						} 
					%>
				</div>

				<div class="mb-3">
					<label for="exampleInputEmail1" class="form-label">Add Your Favourite Books </label> 
					<%
						if(user.getFavouritBooks()!=null)
						{
					%> 
							<input type="text" class="form-control" id="email" aria-describedby="emailHelp" name="favouritBooks" value="<%=user.getFavouritBooks()%>">
					<%	
						}
						else
						{ 
					%>
							 <input type="text" class="form-control" id="email" aria-describedby="emailHelp" name="favouritBooks">
					<%
						} 
					%>
				</div>
				<button type="submit" class="btn btn-primary">Submit</button>
			</form>
		</div>
		<div class="col-md-4"></div>
	</div>
</div>
