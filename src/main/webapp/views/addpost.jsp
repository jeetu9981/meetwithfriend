<%@include file="navbar.jsp"%>

<div class="container mt-5">
	<div class="row">
		<h1 class="text-center text-primary">Add Post..</h1>
		<%@include file="succorerror.jsp"%>
		<div class="col-md-4"></div>
		<div class="col-md-4">
			<form class="mt-5" action="/post/addpost" method="post"
				enctype="multipart/form-data">

				<div class="mb-3 mt-5">
					<label for="exampleInputEmail1" class="form-label">Upload Post</label> <input type="file" class="form-control"
						id="email" aria-describedby="emailHelp" name="image1">
				</div>

				<div class="mb-3">
					<label for="exampleInputEmail1" class="form-label">Add Description</label> <input type="text" class="form-control"
						id="email" aria-describedby="emailHelp" name="description">
				</div>
				<button type="submit" class="btn btn-primary">Submit</button>
			</form>
		</div>
		<div class="col-md-4"></div>
	</div>
</div>
