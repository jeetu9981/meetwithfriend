<%@page import="java.util.ArrayList"%>
<%@page import="com.meetnewfriend.entity.Following"%>
<%@page import="java.util.List"%>
<%@include file="navbar.jsp"%>
<%@include file="succorerror.jsp"%>
<%
	List<Following> followings=(ArrayList<Following>)request.getAttribute("followings");
	if(followings.size()>0){
%>

		<div class="container">
			<div class="row mt-5">
				<h1 class="text-center mt-5">All messages</h1>
				<%@include file="succorerror.jsp"%>
				<%
					for(Following f:followings)
					{
				%>
						<div class="col-md-4 mt-5">
							<%if(f.getFollowing().getImage()!=null)
								{ 
							%>
								<a onclick="getMessages(<%=f.getFollowing().getId()%>)" data-bs-toggle="modal" data-bs-target="#viewmessages" href=""><img src="../../images/<%=f.getFollowing().getImage()%>" height="100" widht="100" style="border-radius: 800px"></a>
							<%
								}
								else
								{ 
							%>
								<a onclick="getMessages(<%=f.getFollowing().getId()%>)" data-bs-toggle="modal" data-bs-target="#viewmessages" href=""><img src="../../images/profile.png" height="100" widht="100"  style="border-radius: 800px"></a>
							<%
								}	 
							%>
							<h6><%=f.getFollowing().getName() %></h6>
						</div>
				<%	
					}
				%>
			</div>
		</div>
		
<%
	}else{
%>
	<h1 class="text-center">There is no messages</h1>
<%
	}
%>

<%@include file="viewmessages.jsp" %>
<script src="../../javascript/message.js"></script>
