<%@page import="com.meetnewfriend.entity.Story"%>
<%@page import="com.meetnewfriend.dto.DashboardDto"%>
<%@page import="java.util.ArrayList"%>
<div class="modal fade" id="seestoryusers" tabindex="-1"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">Seen Users</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<%
					DashboardDto d = (DashboardDto) request.getAttribute("posts");
					ArrayList<Story> story = (ArrayList<Story>) d.getMyStory();
					if(story.size()>0){
						for (int i = 0; i < story.get(0).getStorySeen().size(); i++) 
						{
				%>
						<div class="row">
							<div class="col-md-4">
								<%
									if (story.get(0).getStorySeen().get(i).getUser().getImage() != null) 
									{
								%>
										<img
										src="../../images/<%=story.get(0).getStorySeen().get(i).getUser().getImage()%>"
										height="50" width="50" style="border-radius: 800px">
								<%
									} 
									else 
									{
								%>
										<img src="../../images/profile.png" height="50" width="50"
											style="border-radius: 800px">
								<%
									}
								%>
							</div>
							<div class="col-md-4">
								<h6><%=story.get(0).getStorySeen().get(i).getUser().getName()%></h6>
							</div>
						</div>
				<%
						}
					}
				%>
				<div class="modal-body"></div>
			</div>
		</div>
	</div>
</div>
