<%@include file="navbar.jsp"%>
<%@include file="succorerror.jsp"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<div class="container mt-5">
	<div class="row">
		<div class="col-md-4"></div>
		<div class="col-md-4">
			<h1 class="text-center text-primary">Registration Form</h1>
			<form onsubmit="return validate()" class="mt-5" action="/user/signup"
				method="get">
				<div class="mb-3">
					<label for="exampleInputPassword1" class="form-label">Name</label>
					<input type="text" class="form-control" id="name" name="name">
					<small id="nameField" style="color: red"></small>
				</div>
				<div class="mb-3">
					<label for="exampleInputEmail1" class="form-label">Email
						address</label> <input type="email" class="form-control" id="email"
						aria-describedby="emailHelp" name="email"> <small
						id="emailField" style="color: red"></small>
				</div>
				<div class="mb-3">
					<label for="exampleInputPassword1" class="form-label">Password</label>
					<input type="password" class="form-control" id="password"
						name="password"> <small id="passwordField"
						style="color: red"></small>
				</div>
				
				<div class="mb-3">
					<input type="hidden" value="<%=request.getAttribute("hidden")%>">
					<img src="data:image/png;base64,${capImage}">
				</div>
				
				<div class="mb-3">
					<label for="exampleInputPassword1" class="form-label">Enter Captcha</label>
					<input type="password" class="form-control" id="password"
						name="captcha">
				</div>

				<button type="submit" class="btn btn-primary">Submit</button>
			</form>
		</div>
		<div class="col-md-4"></div>
	</div>
</div>

<script>
	function validate() {
		var flag = false;
		var exp = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;

		let name = $("#name").val();
		if (name == "" || name == undefined) {
			$("#nameField").html("Please enter valid name...");
			flag = false;
		} else {
			$("#nameField").html("");
			flag = true;
		}

		let email = $("#email").val();
		if (email == "" || email == undefined) {
			$("#emailField").html("Please enter valid email...");
			flag = false;
		} else if (exp.test(email) == false) {
			$("#emailField")
					.html(
							"Email Invalid ,email must contain(@ or .) example(example@gmail.com)");
			flag = false;
		} else {
			$("#emailField").html("");
			flag = true;
		}

		let password = $("#password").val();
		if (password == "" || password == undefined) {
			$("#passwordField").html("Please enter valid password...");
			flag = false;
		} else {
			$("#passwordField").html("");
			flag = true;
		}
		return flag;
	}
</script>