$(function () {

	console.log('SUBJECT MANAGER JS LOADED')

	let subject_table = null;
	if ($("#subjects").length) {
		subject_table = $("#subjects").DataTable();
	}


	$(document).on("click", ".delete-subject", function () {
		let subject_id = $(this).data('subject');
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
					url: "/admin/subjects",
					data: JSON.stringify({
						subject_id,
					}),
					contentType: "application/json",
					success: function (response) {
						console.log(response);
						if (response.status == "deleted") {
							subject_table.rows("#subject-" + subject_id)
								.remove()
								.draw();
							Toast.fire({
								icon: 'success',
								title: 'Your subject has been deleted.'
							})
						}
						else console.error(response.message);
					},
				});

		});
	});
	
});