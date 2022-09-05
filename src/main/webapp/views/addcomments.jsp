
<div class="modal fade" id="exampleModal" tabindex="-1"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">Add Comment</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<form action="/comment/addcomment" action="post">
				<div class="modal-body">

					<input type="text" placeholder="Enter Comment" name="comment">
					<input type="text" hidden="true" id="commentUser" value=""
						name="commentUser"> <input type="text" hidden="true"
						id="postId" value="" name="postId">
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">Close</button>
					<button onClick="addComment()" type="submit" class="btn btn-primary">Comment</button>
				</div>
			</form>
		</div>
	</div>
</div>
