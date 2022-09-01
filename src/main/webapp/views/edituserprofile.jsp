<%@include file="navbar.jsp"%>



<div class="container mt-5">
	<div class="row">
		<h1 class="text-center text-primary">Update Your Profile</h1>
		<div class="col-md-4"></div>
		<div class="col-md-4">
			<form class="mt-5" action="/user/updateuser" method="post"
				enctype="multipart/form-data">
				
				<div class="mb-3">
					<label for="exampleInputEmail1" class="form-label">Add Your Name</label> 
					<input type="text" class="form-control" id="email" aria-describedby="emailHelp" name="name">
				</div>
				
				<div class="mb-3">
					<label for="exampleInputEmail1" class="form-label">Add Your Email</label> 
					<input type="email" class="form-control"
						id="email" aria-describedby="emailHelp" name="email">
				</div>
				
				<div class="mb-3 mt-5">
					<label for="exampleInputEmail1" class="form-label">Upload
						your profile image address</label> <input type="file" class="form-control"
						id="email" aria-describedby="emailHelp" name="image">
				</div>

				<div class="mb-3">
					<label for="exampleInputEmail1" class="form-label">Add Your
						Favourite Songs address</label> <input type="text" class="form-control"
						id="email" aria-describedby="emailHelp" name="songs">
				</div>

				<div class="mb-3">
					<label for="exampleInputEmail1" class="form-label">Add Your
						Favourite Places address</label> <input type="text" class="form-control"
						id="email" aria-describedby="emailHelp" name="places">
				</div>

				<div class="mb-3">
					<label for="exampleInputEmail1" class="form-label">Add Your
						Favourite Books address</label> <input type="text" class="form-control"
						id="email" aria-describedby="emailHelp" name="books">
				</div>
				<button type="submit" class="btn btn-primary">Submit</button>
			</form>
		</div>
		<div class="col-md-4"></div>
	</div>
</div>
