<%@page import="com.meetnewfriend.dto.ProfileDto"%>
<%@page import="com.meetnewfriend.entity.RealFollower"%>
<%@page import="java.util.ArrayList"%>


<%
	ProfileDto profile2=(ProfileDto)request.getAttribute("profile");
	ArrayList<RealFollower> followers = (ArrayList<RealFollower>) profile2.getFollowers();
%>

<!-- Modal -->
	<div class="modal fade" id="exampleModal" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">All Followers</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>

				<%
					for (RealFollower f : followers) {
				%>

				<div class="modal-body">
					<div class="row">
						<div class="col-md-4">
								<%if(f.getFollower().getImage()!=null){ %><img alt="" src="../../images/<%=f.getFollower().getImage()%>" height="50" width="50" style="border-radius: 800px"><%}else{ %>
				<img alt="" src="../../images/profile.png" height="50" width="50" style="border-radius: 800px"><%} %>
						</div>
						<div class="col-md-4"><%=f.getFollower().getName()%></div>
					</div>
				</div>
				<%
					}
				%>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>