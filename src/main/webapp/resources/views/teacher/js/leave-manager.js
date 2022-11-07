$(function() {

	console.log('LEAVE MANAGER JS LOADED')

	let leave_table = null;
	if ($("#leave-requests").length) {
		leave_table = $("#leave-requests").DataTable();
	}


	$(document).on("click", ".delete-leave", function() {
		let leave_id = $(this).data('leave');
		Swal.fire({
			title: "Are you sure?",
			text: "You won't be able to revert this!",
			icon: "warning",
			showCancelButton: !0,
			confirmButtonColor: "#3085d6",
			cancelButtonColor: "#d33",
			confirmButtonText: "Yes, delete it!",
			customClass: {
				confirmButton: "btn btn-primary",
				cancelButton: "btn btn-danger ml-1",
			},
			buttonsStyling: !1
		}).then(function(response) {
			response.value &&
				$.ajax({
					type: "DELETE",
					url: "/teacher/leave-requests",
					data: JSON.stringify({
						leave_id,
					}),
					contentType: "application/json",
					success: function(response) {
						console.log(response);
						if (response.status == "deleted") {
							leave_table.rows("#leave-" + leave_id)
								.remove()
								.draw();
							Toast.fire({
								icon: 'success',
								title: 'Your file has been deleted.'
							})
						}
						else console.error(response.message);
					},
				});

		})
	})
})