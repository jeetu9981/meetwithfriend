<%@page import="com.meetnewfriend.entity.Story"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.meetnewfriend.dto.DashboardDto"%>
<div class="modal fade" id="addstory" tabindex="-1"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">My Story</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<%	
					DashboardDto dashboard1 = (DashboardDto) request.getAttribute("posts");
					ArrayList<Story> myStory = (ArrayList<Story>) dashboard1.getMyStory();
					if(myStory.size()>0){
				%>
					<div class="container">
						<%
							for(int i=0;i<myStory.size();i++){
						%>
								<div class="row">
									<img src="../../images/<%=myStory.get(i).getStory() %>" height="300" width="200">
									<h3><%=myStory.get(i).getDescription() %></h3>
									<a data-bs-toggle="modal"
									data-bs-target="#seestoryusers" href=""><h3>Seen : <%=myStory.get(i).getStorySeen().size() %></h3></a>
								</div>
						<%
							}
						%>
					</div>
				<%} %>
					<div class="container mt-5">
						<div class="row">
							<form action="/story/addstory" method="post" enctype="multipart/form-data">

									<div class="mb-3 mt-5">
										<label for="exampleInputEmail1" class="form-label">Upload Story</label> <input type="file"
											class="form-control" id="email" aria-describedby="emailHelp"
											name="image1">
									</div>

									<div class="mb-3">
										<label for="exampleInputEmail1" class="form-label">Add Description</label> <input type="text"
											class="form-control" id="email" aria-describedby="emailHelp"
											name="description">
									</div>

									
									<button type="submit" class="btn btn-primary">Add Story</button>
								</form>
						</div>
					</div>

				<div class="modal-body"></div>
			</div>
		</div>
	</div>
</div>


