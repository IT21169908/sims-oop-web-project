$(function () {

	console.log('GRADE MANAGER JS LOADED')

	let grade_table = null;
	if ($("#grades").length) {
		grade_table = $("#grade").DataTable();
	}


	$(document).on("click", ".delete-grade", function () {
		let grade_id = $(this).data('grade');
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
					url: "/admin/grades",
					data: JSON.stringify({
						grade_id,
					}),
					contentType: "application/json",
					success: function (response) {
						console.log(response);
						if (response.status == "deleted") {
							grade_table.rows("#grade-" + grade_id)
								.remove()
								.draw();
							Toast.fire({
								icon: 'success',
								title: 'Your grade has been deleted.'
							})
						}
						else console.error(response.message);
					},
				});

		});
	});
	
});