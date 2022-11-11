$(function() {

	console.log('PERIOD MANAGER JS LOADED')

	let period_table = null;
	if ($("#periods").length) {
		period_table = $("#periods").DataTable();
	}

	$(document).on("change", "#subject", function() {
		let subject_id = $(this).val();
		$.ajax({
			type: "GET",
			url: "/admin/periods?subject_id=" + subject_id,
			contentType: "application/json",
			success: function(response) {
				$("#teachers").empty()
				response.map(teacher => {
					$("#teachers").append(`<option value="${teacher.teacher_id}">${teacher.teacher_name}</option>`)
				})
			},
		});
	})


	$(document).on("click", ".delete-period", function() {
		let period_id = $(this).data('period');
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
					url: "/admin/periods",
					data: JSON.stringify({
						period_id,
					}),
					contentType: "application/json",
					success: function(response) {
						console.log(response);
						if (response.status == "deleted") {
							period_table.rows("#period-" + period_id)
								.remove()
								.draw();
							Toast.fire({
								icon: 'success',
								title: 'Your period has been deleted.'
							})
						}
						else console.error(response.message);
					},
				});

		});
	});

});