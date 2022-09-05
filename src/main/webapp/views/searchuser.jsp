<%@page import="com.meetnewfriend.entities.UserEntity"%>
<%@page import="java.util.ArrayList"%>
<%@include file="navbar.jsp"%> 

<%
	ArrayList<UserEntity> user = (ArrayList<UserEntity>) request.getAttribute("users");
%>

<div class="container mt-5">
	<div class="row mb-5">
		<h1 class="mt-5 text-center">Search Users</h1>
		<%
			for (int i = 0; i < user.size(); i++) {
		%>

		<div class="col-md-3">
			<div class="row mt-5">
				<div class="col-md-6">
					<img alt="" src="../../images/<%=user.get(i).getImage()%>" height="100"
						width="100" style="border-radius: 800px">
					<div>
						<label class="text-center text-dark">Name : <%=user.get(i).getName()%></label>
					</div>
					<h3><a href="/follower/followrequest?userId=<%=user.get(i).getId()%>">Follow</a></h3>
				</div>
			</div>
		</div>

		<%
			}
		%>
	</div>
</div>