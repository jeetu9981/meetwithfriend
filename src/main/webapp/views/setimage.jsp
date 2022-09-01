
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



<%@include file="navbar.jsp"%>

<div class="container mt-5">
	<div class="row">
		<h1 class="text-center text-primary">Fill Some Details..</h1>
		<div class="col-md-4"></div>
		<div class="col-md-4">
			<form class="mt-5" action="/user/setimage" method="post"
				enctype="multipart/form-data">

				<div class="mb-3 mt-5">
					<label for="exampleInputEmail1" class="form-label">Upload
						your profile image address</label> <input type="file" class="form-control"
						id="email" aria-describedby="emailHelp" name="image">
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
			</form>
		</div>
		<div class="col-md-4"></div>
	</div>
</div>
