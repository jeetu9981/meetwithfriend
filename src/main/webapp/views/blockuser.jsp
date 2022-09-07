<%@page import="com.meetnewfriend.entity.Block"%>
<%@page import="java.util.ArrayList"%>
<%@include file="navbar.jsp"%>

<%
 	ArrayList<Block> user = (ArrayList<Block>) request.getAttribute("users");
	if(user.size()>0){
 %>

	<div class="container mt-5">
		<div class="row mb-5">
			<h1 class="mt-5 text-center">Block Users</h1>
			<%
				for (int i = 0; i < user.size(); i++) {
			%>
	
			<div class="col-md-3">
				<div class="row mt-5">
					<%@include file="succorerror.jsp"%>
					<div class="col-md-6">
							<%if(user.get(i).getBlockUser().getImage()!=null){ %><img alt="" src="../../images/<%=user.get(i).getBlockUser().getImage()%>" height="100" width="100" style="border-radius: 800px"><%}else{ %>
					<img alt="" src="../../images/profile.png" height="100" width="100" style="border-radius: 800px"><%} %>
						<div>
							<label class="text-center text-dark">Name : <%=user.get(i).getBlockUser().getUserName()%></label>
						</div>
						<h3><a href="/block/unblock?userId=<%=user.get(i).getBlockUser().getId()%>">UnBlock</a></h3>
					</div>
				</div>
			</div>
	
			<%
				}
			%>
		</div>
	</div>
<%
	}
	else{
%>
		<h1 class="text-center mt-5">There is no user</h1>
<%
	}
%>	
