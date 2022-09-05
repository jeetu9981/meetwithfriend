<!-- Modal -->
	<%@page import="com.meetnewfriend.entities.FollowingEntity"%>
<%@page import="java.util.ArrayList"%>
<%ArrayList<FollowingEntity> following = (ArrayList<FollowingEntity>) request.getAttribute("followings"); %>
	<div class="modal fade" id="exampleModal1" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">All Follwing</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>

				<%
					for (FollowingEntity f : following) {
				%>

				<div class="modal-body">
					<div class="row">
						<div class="col-md-4">
							<img alt="" src="../../images/<%=f.getFollowing().getImage()%>"
								height="50" width="50" style="border-radius: 800px">
						</div>
						<div class="col-md-4"><%=f.getFollowing().getName()%></div>
						<div class="col-md-4">
							<a
								href="/following/unfollow?following=<%=f.getFollowing().getId()%>">Unfollow</a>
						</div>
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
