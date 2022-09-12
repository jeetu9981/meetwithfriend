<div class="modal fade" id="viewmessages" tabindex="-1"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">Chat</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
			<div id="messages"></div>
					<hr>
					<form action="/message/addmessage" method="post">
					<input type="hidden" id="userId" name="recieverId">
						<input name="message" type="text" id="message">
						<input type="submit">
					</form>
					
				<div class="modal-body"></div>
			</div>
		</div>
	</div>
</div>