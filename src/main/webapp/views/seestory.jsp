<div class="modal fade" id="seestory" tabindex="-1"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">Story</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<div class="row">
					<div class="col-md-8" id="setImage"></div>
				</div>
				<div class="row mt-3">
					<form action="/message/addmessage" method="post">
						<input type="hidden" id="userId" name="recieverId"> <input
							name="message" type="text" id="message" placeholder="Reply"> 
							<input type="submit">
					</form>
				</div>
				<div class="modal-body"></div>
			</div>

			<div class="modal-footer">
				<button type="button" class="btn btn-secondary"
					data-bs-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>