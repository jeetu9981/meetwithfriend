<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"><link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
	crossorigin="anonymous"></script>

<script src="https://code.jquery.com/jquery-1.12.4.min.js"
	integrity="sha256-ZosEbRLbNQzLpnKIkEdrPv7lOy9C27hHQ+Xp8a4MxAQ="
	crossorigin="anonymous"></script>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body style="background-color: #F5F5F5">
	<nav class="navbar navbar-expand-lg navbar-primary bg-dark">
		<div class="container-fluid">
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<%
						if (session.getAttribute("userId") == null) {
					%>
					<li class="nav-item"><a class="nav-link"
						href="/views/signin.jsp">Signin</a></li>

					<li class="nav-item"><a class="nav-link"
						href="/views/home.jsp">Signup</a></li>
					<%
						} else {
					%>
					<li class="nav-item"><a class="nav-link"
						href="/user/dashboard">UserName : <%=session.getAttribute("userName")%></a></li>

					<li class="nav-item"><a class="nav-link" href="/user/logout">Logout</a></li>

					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
						role="button" data-bs-toggle="dropdown" aria-expanded="false">
							Notifictaion </a>
						<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
							<li><a class="dropdown-item" href="/user/profile">Profile</a></li>
							<li><a class="dropdown-item" href="../follower/checkrequest">Friend Request</a></li>
							<li><a class="dropdown-item" href="#">Comments And Likes</a></li>
						</ul></li>

				</ul>
				<form class="d-flex" action="/user/searchuser" method="post">
					<input class="form-control me-2" type="search" placeholder="Search"
						aria-label="Search" name="serachvalue">
					<button class="btn btn-outline-success" type="submit">Search</button>
				</form>
				<%
					}
				%>
			</div>
		</div>
	</nav>
</body>
</html>