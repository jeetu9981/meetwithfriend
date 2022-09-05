<%@page import="com.meetnewfriend.entities.UserEntity"%>
<%@include file="navbar.jsp"%>

<%UserEntity user=(UserEntity)request.getAttribute("user"); %>

<div class="container mt-5">
	<div class="row">
		<h1 class="text-center text-primary">Update Your Profile</h1>
		<div class="col-md-4"></div>
		<div class="col-md-4">
			<form class="mt-5" action="/user/updateuser" method="post"
				enctype="multipart/form-data">
				
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
						id="email" aria-describedby="emailHelp" name="image">
				</div>

				<div class="mb-3">
					<label for="exampleInputEmail1" class="form-label">Add Your Favourite Songs</label>
					<%
						if(user.getFavouriteSongs()!=null)
						{
					%> 
							<input type="text" class="form-control" id="email" aria-describedby="emailHelp" name="songs" value="<%=user.getFavouriteSongs() %>">
					<%	
						}
						else
						{ 
					%>
							 <input type="text" class="form-control" id="email" aria-describedby="emailHelp" name="songs">
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
							<input type="text" class="form-control" id="email" aria-describedby="emailHelp" name="places" value="<%=user.getFavouritePlaces() %>">
					<%	
						}
						else
						{ 
					%>
							 <input type="text" class="form-control" id="email" aria-describedby="emailHelp" name="places">
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
							<input type="text" class="form-control" id="email" aria-describedby="emailHelp" name="books" value="<%=user.getFavouritBooks()%>">
					<%	
						}
						else
						{ 
					%>
							 <input type="text" class="form-control" id="email" aria-describedby="emailHelp" name="books">
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
