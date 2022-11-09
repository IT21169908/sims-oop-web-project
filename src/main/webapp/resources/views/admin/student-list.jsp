<!DOCTYPE html>
<html lang="en">

<head>
   <meta charset="utf-8">
   <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0">
   <title>Preskool - Dashboard</title>

   <link rel="shortcut icon" href="/resources/images/favicon.png">

   <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,500;0,600;0,700;1,400&amp;display=swap">

   <link rel="stylesheet" href="/resources/assets/plugins/bootstrap/css/bootstrap.min.css">

   <link rel="stylesheet" href="/resources/assets/plugins/fontawesome/css/fontawesome.min.css">
   <link rel="stylesheet" href="/resources/assets/plugins/fontawesome/css/all.min.css">

   <link rel="stylesheet" href="/resources/assets/css/style.css">
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


         	<!-- PAGE CONTENTS -->
                <div class="row">
                    <div class="col-sm-12">
                        <div class="card card-table">
                            <div class="card-body">
                                <div class="table-responsive">
                                    <table class="table table-hover table-center mb-0 datatable">
                                        <thead>
                                            <tr>
                                                <th>ID</th>
                                                <th>Name</th>
                                                <th>Class</th>
                                                <th>DOB</th>
                                                <th>Parent Name</th>
                                                <th>Mobile Number</th>
                                                <th>Address</th>
                                                <th class="text-end">Action</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        <th class="text-end">Action</th>
                                        </tr>
										</thead>
										<tbody>
										<tr>
										<td class="text-end">
										<div class="actions">
										<a href="edit-student.html" class="btn btn-sm bg-success-light me-2">
										<i class="fas fa-pen"></i>
										</a>
										<a href="#" class="btn btn-sm bg-danger-light">
										<i class="fas fa-trash"></i>
										</a>
										</div>
										</td>
										</tr>
                                        </tbody>
                                       </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
         	<!-- END PAGE CONTENTS -->
 
         </div>

         <footer>
            <p>Copyright � 2020 Dreamguys.</p>
         </footer>

      </div>

   </div>


   <script src="/resources/assets/js/jquery-3.6.0.min.js"></script>

   <script src="/resources/assets/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>

   <script src="/resources/assets/js/feather.min.js"></script>

   <script src="/resources/assets/plugins/slimscroll/jquery.slimscroll.min.js"></script>

   <script src="/resources/assets/plugins/apexchart/apexcharts.min.js"></script>
   <script src="/resources/assets/plugins/apexchart/chart-data.js"></script>

   <script src="/resources/assets/js/script.js"></script>
</body>

</html>
