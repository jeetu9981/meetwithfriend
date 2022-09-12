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
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark" style="text:white">
		<div class="container-fluid">
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<%
						if (session.getAttribute("userId") == null) {
					%>
					<li class="nav-item"><a class="nav-link"
						href="/views/signin.jsp">Signin</a></li>

					<li class="nav-item"><a class="nav-link"
						href="/">Signup</a></li>
					<%
						} else {
					%>
					<li class="nav-item"><a class="nav-link	"
						href="/user/dashboard">UserName : <%=session.getAttribute("userName")%></a></li>
					<li class="nav-item"><a class="nav-link" href="/user/profile">Profile</a></li>
					<li class="nav-item"><a class="nav-link" href="../follower/checkrequest">Friend Request</a></li>
					<li class="nav-item"><a class="nav-link" href="../block/getAllBlocks">Block User</a></li>
					<li class="nav-item"><a class="nav-link" href="/message/getmessages">All messages</a></li>	

					<li class="nav-item"><a class="nav-link" href="/user/logout">Logout</a></li>

				</ul>
				<form class="d-flex" action="/user/searchuser" method="get">
					<input id="searchvalue" class="form-control me-2 " type="search" placeholder="Search"
						aria-label="Search" name="serachvalue">
					<button class="btn btn-outline-success" type="submit">Search</button>
				</form>
				<%
					}
				%>
			</div>
		</div>
	</nav>
	<div id="fix"></div>
</body>
</html>


<!-- <script src="../../javascript/search.js"></script> -->