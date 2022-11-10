$(function () {

	console.log('REGISTRATION MANAGER JS LOADED')

	let user_table = null;
	if ($("#users").length) {
		user_table = $("#users").DataTable();
	}


	$(document).on("click", ".delete-user", function () {
		let user_id = $(this).data('user');
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
		}).then(function (response) {
			response.value &&
				$.ajax({
					type: "DELETE",
					url: "/admin/users",
					data: JSON.stringify({
						user_id,
					}),
					contentType: "application/json",
					success: function (response) {
						console.log(response);
						if (response.status == "deleted") {
							user_table.rows("#user-" + user_id)
								.remove()
								.draw();
							Toast.fire({
								icon: 'success',
								title: 'User has been deleted.'
							})
						}
						else console.error(response.message);
					},
				});

		});
	});
	
});