
   <script src="/resources/assets/js/jquery-3.6.0.min.js"></script>
   
   <script src="/resources/assets/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
   
   <script src="/resources/assets/js/feather.min.js"></script>
   
   <script src="/resources/assets/plugins/slimscroll/jquery.slimscroll.min.js"></script>
   
   
   <script src="/resources/assets/js/jquery.maskedinput.min.js"></script>
   
   <script src="/resources/assets/plugins/sweetalert/sweetalert2.all.min.js"></script>
   
   <script src="/resources/assets/plugins/apexchart/apexcharts.min.js"></script>
   <script src="/resources/assets/plugins/apexchart/chart-data.js"></script>
   
   
   <script src="/resources/assets/plugins/simple-calendar/jquery.simple-calendar.js"></script>
   <script src="/resources/assets/js/calander.js"></script>

   <script src="/resources/assets/js/circle-progress.min.js"></script>
   
 	<script src="/resources/assets/plugins/datatables/datatables.min.js"></script>
 	
   <script src="/resources/assets/js/script.js"></script>

   <script src="/resources/assets/plugins/select2-bootstrap/js/select2.min.js"></script>
	<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/js/select2.min.js"></script> -->
   
   <script type="text/javascript">
	   const Toast = Swal.mixin({
		   toast: true,
		   position: 'top-end',
		   showConfirmButton: false,
		   timer: 3000,
		   timerProgressBar: true,
		   didOpen: (toast) => {
		     toast.addEventListener('mouseenter', Swal.stopTimer)
		     toast.addEventListener('mouseleave', Swal.resumeTimer)
		   }
		 });
	   
	  	// Assign Teacher multi select
		$(function () {
		  $("select").each(function () {
		    $(this).select2({
		      theme: "bootstrap4",
		      width: "100%",
		      placeholder: $(this).attr("placeholder"),
		      allowClear: Boolean($(this).data("allow-clear"))
		    });
		  });
		});
	   
	</script>
	
	