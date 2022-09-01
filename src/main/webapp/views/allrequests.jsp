<%@page import="com.meetnewfriend.entities.FollowerEntity"%>
<%@page import="java.util.ArrayList"%>
<%@include file="navbar.jsp"%>



<%
	ArrayList<FollowerEntity> user=(ArrayList<FollowerEntity>) request.getAttribute("request");
	if(user.size()>0){
		System.out.println("SIZE : "+user.size());
%>

	<div class="container mt-5">
		<div class="row">
		<h1 class="text-center text-primary">Friend Requests</h1>
			<%
				for (int i = 0; i < user.size(); i++) {
			%>
	
			 <div class="col-md-3">
				<div class="row mt-5">
					<div class="col-md-6">
						<img alt="" src="../../images/<%=user.get(i).getSendUserRequest().getImage()%>" height="100"
							width="100" style="border-radius: 800px">
						<div>
							<label class="text-center text-dark">Name : <%=user.get(i).getSendUserRequest().getName()%></label>
						</div>
						<h6><%if(user.get(i).getAccept()==false){%><a href="/follower/acceptrequest?userId=<%=user.get(i).getSendUserRequest().getId()%>">Confirm</a><%} %></h6>
						<h6><%if(user.get(i).getAccept()==false){%><a href="/follower/declinerequest?userId=<%=user.get(i).getSendUserRequest().getId()%>">Decline</a><%} %></h6>
						<h6><a href="/follower/followback?userId=<%=user.get(i).getSendUserRequest().getId()%>">Follow Back</a></h6>
					</div>
				</div>
			</div>
	
			<%
				}
			%>
		</div>
	</div>
<%}else{%>
	<h1>There is no request</h1>
<%} %>
