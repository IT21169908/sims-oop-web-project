<!DOCTYPE html>
<html lang="en">

<head>
   <meta charset="utf-8">
   <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0">
   <title>Preskool - Dashboard</title>

   <link rel="shortcut icon" href="/resources/assets/img/favicon.png">

   <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,500;0,600;0,700;1,400&amp;display=swap">

<!--    <link rel="stylesheet" href="/resources/assets/plugins/bootstrap/css/bootstrap.min.css">

   <link rel="stylesheet" href="/resources/assets/plugins/fontawesome/css/fontawesome.min.css">
   <link rel="stylesheet" href="/resources/assets/plugins/fontawesome/css/all.min.css">

   <link rel="stylesheet" href="/resources/assets/css/style.css"> -->
   
   <jsp:include page="/resources/views/admin/components/styles.jsp" />
</head>

<body>

   <div class="main-wrapper">
 
 	<jsp:include page="/resources/views/admin/components/header.jsp" />
 	<jsp:include page="/resources/views/admin/components/sidebar.jsp" />
 
      <div class="page-wrapper">
         <div class="content container-fluid">

            <div class="page-header">
               <div class="row">
                  <div class="col-sm-12">
                     <h3 class="page-title">Welcome Admin!</h3>
                     <ul class="breadcrumb">
                        <li class="breadcrumb-item active">Dashboard</li>
                     </ul>
                  </div>
               </div>
            </div>


            <div class="row">
               <div class="col-xl-3 col-sm-6 col-12 d-flex">
                  <div class="card bg-one w-100">
                     <div class="card-body">
                        <div class="db-widgets d-flex justify-content-between align-items-center">
                           <div class="db-icon">
                              <i class="fas fa-user-graduate"></i>
                           </div>
                           <div class="db-info">
                              <h3>50055</h3>
                              <h6>Students</h6>
                           </div>
                        </div>
                     </div>
                  </div>
               </div>
               <div class="col-xl-3 col-sm-6 col-12 d-flex">
                  <div class="card bg-two w-100">
                     <div class="card-body">
                        <div class="db-widgets d-flex justify-content-between align-items-center">
                           <div class="db-icon">
                              <i class="fas fa-crown"></i>
                           </div>
                           <div class="db-info">
                              <h3>50+</h3>
                              <h6>Awards</h6>
                           </div>
                        </div>
                     </div>
                  </div>
               </div>
               <div class="col-xl-3 col-sm-6 col-12 d-flex">
                  <div class="card bg-three w-100">
                     <div class="card-body">
                        <div class="db-widgets d-flex justify-content-between align-items-center">
                           <div class="db-icon">
                              <i class="fas fa-building"></i>
                           </div>
                           <div class="db-info">
                              <h3>30+</h3>
                              <h6>Department</h6>
                           </div>
                        </div>
                     </div>
                  </div>
               </div>
               <div class="col-xl-3 col-sm-6 col-12 d-flex">
                  <div class="card bg-four w-100">
                     <div class="card-body">
                        <div class="db-widgets d-flex justify-content-between align-items-center">
                           <div class="db-icon">
                              <i class="fas fa-file-invoice-dollar"></i>
                           </div>
                           <div class="db-info">
                              <h3>$505</h3>
                              <h6>Revenue</h6>
                           </div>
                        </div>
                     </div>
                  </div>
               </div>
            </div>
 
         </div>

		<jsp:include page="/resources/views/admin/components/footer.jsp" />

      </div>

   </div>


<!--    <script src="/resources/assets/js/jquery-3.6.0.min.js"></script>

   <script src="/resources/assets/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>

   <script src="/resources/assets/js/feather.min.js"></script>

   <script src="/resources/assets/plugins/slimscroll/jquery.slimscroll.min.js"></script>

   <script src="/resources/assets/plugins/apexchart/apexcharts.min.js"></script>
   <script src="/resources/assets/plugins/apexchart/chart-data.js"></script>

   <script src="/resources/assets/js/script.js"></script> -->
   
   
	<jsp:include page="/resources/views/admin/components/scripts.jsp" />
</body>

</html>