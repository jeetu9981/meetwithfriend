<%@page import="com.meetnewfriend.dto.SerachUserDto"%>
<%@page import="java.util.ArrayList"%>
<%@include file="navbar.jsp"%> 

<%
 	ArrayList<SerachUserDto> user = (ArrayList<SerachUserDto>) request.getAttribute("users");
	if(user!=null){
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
							<%if(user.get(i).getUser().getImage()!=null){ %><img alt="" src="../../images/<%=user.get(i).getUser().getImage()%>" height="100" width="100" style="border-radius: 800px"><%}else{ %>
					<img alt="" src="../../images/profile.png" height="100" width="100" style="border-radius: 800px"><%} %>
						<div>
							<label class="text-center text-dark">Name : <%=user.get(i).getUser().getUserName()%></label>
						</div>
						<%
							if(user.get(i).isFollowStatus())
							{
						%>		
								<h3><a href="/following/unfollow?following=<%=user.get(i).getUser().getId()%>">UnFollow</a></h3>
						<%
							}
							else if(user.get(i).isFollowBackStatus())
							{
						%>	
								<h3><a href="/follower/followback?userId=<%=user.get(i).getUser().getId()%>">Follow Back</a></h3>
						<% 
							}else if(user.get(i).isDeclineRequest()){
						%>
							<h3><a href="/follower/declinerequest?userId=<%=user.get(i).getUser().getId()%>">Decline Request</a></h3>
						<%
							}else{
						%>
								<h3><a href="/follower/followrequest?userId=<%=user.get(i).getUser().getId()%>">Follow</a></h3>
						<%
							}
						%>
					</div>
				</div>
			</div>
	
			<%
				}
			session.removeAttribute("user");
			%>
		</div>
	</div>
<%
	}else{
%>
	<h1 class="text-center mt-5">There is no user</h1>
<%} %>




