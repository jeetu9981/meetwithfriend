<!-- Message of success or fail  start-->
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
		<div class="col-md-4"></div>
		<div class="col-md-4">
			<form onsubmit="return validate()" class="mt-5" action="/user/signin"
				method="post">
				<h1 class="text-center text-primary">Login Form</h1>
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

