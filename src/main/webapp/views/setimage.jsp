<%@include file="navbar.jsp"%>
<%@include file="succorerror.jsp"%>
<div class="container mt-5">
	<div class="row">
		<h1 class="text-center text-primary">Complete Your Profile..</h1>
		<div class="col-md-4"></div>
		<div class="col-md-4">
			<form class="mt-5" action="/user/setimage" method="post"
				enctype="multipart/form-data">

				<div class="mb-3 mt-5">
					<label for="exampleInputEmail1" class="form-label">Upload
						your profile image address</label> <input type="file" class="form-control"
						id="email" aria-describedby="emailHelp" name="image1">
				</div>

				<div class="mb-3">
					<label for="exampleInputEmail1" class="form-label">Add Your
						Favourite Songs address</label> <input type="text" class="form-control"
						id="email" aria-describedby="emailHelp" name="favouriteSongs">
				</div>

				<div class="mb-3">
					<label for="exampleInputEmail1" class="form-label">Add Your
						Favourite Places address</label> <input type="text" class="form-control"
						id="email" aria-describedby="emailHelp" name="favouritePlaces">
				</div>

				<div class="mb-3">
					<label for="exampleInputEmail1" class="form-label">Add Your
						Favourite Books address</label> <input type="text" class="form-control"
						id="email" aria-describedby="emailHelp" name="favouritBooks">
				</div>
				<button type="submit" class="btn btn-primary">Submit</button>
				<button type="submit" class="btn btn-primary">Skip</button>
			</form>
		</div>
		<div class="col-md-4"></div>
	</div>
</div>
