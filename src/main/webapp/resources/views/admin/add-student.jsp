<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0">
	<title>Register User - Admin Dashboard</title>
	<link rel="shortcut icon" href="/resources/assets/img/favicon.png">
	
	<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,500;0,600;0,700;1,400&amp;display=swap">
	
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


				<!-- PAGE CONTENTS -->

				<div class="row">
					<div class="col-sm-12">
						<div class="card">
							<div class="card-body">
								<form>
									<div class="row">
										<div class="col-12">
											<h5 class="form-title">
												<span>Student Information</span>
											</h5>
										</div>
										<div class="col-12 col-sm-6">
											<div class="form-group">
												<label>First Name</label> <input type="text"
													class="form-control">
											</div>
										</div>
										<div class="col-12 col-sm-6">
											<div class="form-group">
												<label>Last Name</label> <input type="text"
													class="form-control">
											</div>
										</div>
										<div class="col-12 col-sm-6">
											<div class="form-group">
												<label>Student Id</label> <input type="text"
													class="form-control">
											</div>
										</div>
										<div class="col-12 col-sm-6">
											<div class="form-group">
												<label>Gender</label> <select class="form-control select">
													<option>Select Gender</option>
													<option>Female</option>
													<option>Male</option>
													<option>Others</option>
												</select>
											</div>
										</div>
										<div class="col-12 col-sm-6">
											<div class="form-group">
												<label>Date of Birth</label>
												<div>
													<input type="date" class="form-control">
												</div>
											</div>
										</div>
										<div class="col-12 col-sm-6">
											<div class="form-group">
												<label>Class</label> <input type="text" class="form-control">
											</div>
										</div>
										<div class="col-12 col-sm-6">
											<div class="form-group">
												<label>Religion</label> <input type="text"
													class="form-control">
											</div>
										</div>
										<div class="col-12 col-sm-6">
											<div class="form-group">
												<label>Joining Date</label>
												<div>
													<input type="date" class="form-control">
												</div>
											</div>
										</div>
										<div class="col-12 col-sm-6">
											<div class="form-group">
												<label>Mobile Number</label> <input type="text"
													class="form-control">
											</div>
										</div>
										<div class="col-12 col-sm-6">
											<div class="form-group">
												<label>Admission Number</label> <input type="text"
													class="form-control">
											</div>
										</div>
										<div class="col-12 col-sm-6">
											<div class="form-group">
												<label>Section</label> <input type="text"
													class="form-control">
											</div>
										</div>
										<div class="col-12 col-sm-6">
											<div class="form-group">
												<label>Student Image</label> <input type="file"
													class="form-control">
											</div>

											<!-- END PAGE CONTENTS -->

										</div>
										<jsp:include
											page="/resources/views/admin/components/footer.jsp" />
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
			<jsp:include page="/resources/views/admin/components/footer.jsp" />
		</div>
	</div>

	<jsp:include page="/resources/views/admin/components/scripts.jsp" />
</body>

</html>
