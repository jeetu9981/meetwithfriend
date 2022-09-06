<%@page import="com.meetnewfriend.entity.User"%>
<%@page import="java.util.ArrayList"%>
<%@include file="navbar.jsp"%> 

<%
 	ArrayList<User> user = (ArrayList<User>) request.getAttribute("users");
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
						<%if(user.get(i).getImage()!=null){ %><img alt="" src="../../images/<%=user.get(i).getImage()%>" height="200" width="200" style="border-radius: 800px"><%}else{ %>
				<img alt="" src="../../images/profile.png" height="100" width="100" style="border-radius: 800px"><%} %>
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