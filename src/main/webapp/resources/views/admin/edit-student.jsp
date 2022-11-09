<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0">
	
	<title>User Edit - Admin Dashboard</title>
	
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
					<div class="row align-items-center">
						<div class="col">
							<h3 class="page-title">Edit Students</h3>
							<ul class="breadcrumb">
								<li class="breadcrumb-item"><a href="students.html">Students</a></li>
								<li class="breadcrumb-item active">Edit Students</li>
							</ul>
						</div>
					</div>
				</div>

				<div class="row">
					<div class="col-sm-12">
						<div class="card">
							<div class="card-body">
								<form>
									<div class="row">
										<div class="col-12">
											<h5 class="form-title">
												<span>User Information</span>
											</h5>
										</div>
										<div class="col-12 col-sm-6">
											<div class="form-group">
												<label>Full Name</label> 
												<input type="text" class="form-control" value="${ editUser.name }">
											</div>
										</div>
										 
										<div class="col-12 col-sm-6">
											<div class="form-group">
												<label>User Id</label> 
												<input type="text" class="form-control" value="${ editUser.id }">
											</div>
										</div>
										<div class="col-12 col-sm-6">
											<div class="form-group">
												<label>Gender</label> <select class="form-control select">
													<option>Female</option>
													<option>Select Gender</option>
													<option>Male</option>
													<option>Others</option>
												</select>
											</div>
										</div>
										<div class="col-12 col-sm-6">
											<div class="form-group">
												<label>Date of Birth</label>
												<div>
													<input type="text" class="form-control" value="26 Apr 1994">
												</div>
											</div>
										</div>
										<div class="col-12 col-sm-6">
											<div class="form-group">
												<label>Class</label> <input type="text" class="form-control"
													value="10">
											</div>
										</div>
										<div class="col-12 col-sm-6">
											<div class="form-group">
												<label>Religion</label> <input type="text"
													class="form-control" value="Religion">
											</div>
										</div>
										<div class="col-12 col-sm-6">
											<div class="form-group">
												<label>Joining Date</label>
												<div>
													<input type="text" class="form-control" value="4 Jan 2002">
												</div>
											</div>
										</div>
										<div class="col-12 col-sm-6">
											<div class="form-group">
												<label>Mobile Number</label> <input type="text"
													class="form-control" value="077 3499 9959">
											</div>
										</div>
										<div class="col-12 col-sm-6">
											<div class="form-group">
												<label>Admission Number</label> <input type="text"
													class="form-control" value="PRE1252">
											</div>
										</div>
										<div class="col-12 col-sm-6">
											<div class="form-group">
												<label>Section</label> <input type="text"
													class="form-control" value="B">
											</div>
										</div>
										<div class="col-12 col-sm-6">
											<div class="form-group">
												<label>Student Image</label> <input type="file"
													class="form-control">
											</div>
										</div>
										<div class="col-12">
											<h5 class="form-title">
												<span>Parent Information</span>
											</h5>
										</div>
										<div class="col-12 col-sm-6">
											<div class="form-group">
												<label>Father's Name</label> <input type="text"
													class="form-control" value="Stephen Marley">
											</div>
										</div>
										<div class="col-12 col-sm-6">
											<div class="form-group">
												<label>Father's Occupation</label> <input type="text"
													class="form-control" value="Technician">
											</div>
										</div>
										<div class="col-12 col-sm-6">
											<div class="form-group">
												<label>Father's Mobile</label> <input type="text"
													class="form-control" value="	402 221 7523">
											</div>
										</div>
										<div class="col-12 col-sm-6">
											<div class="form-group">
												<label>Father's Email</label> <input type="email"
													class="form-control" value="stephenmarley@gmail.com">
											</div>
										</div>
										<div class="col-12 col-sm-6">
											<div class="form-group">
												<label>Mother's Name</label> <input type="text"
													class="form-control" value="Cleary Wong">
											</div>
										</div>
										<div class="col-12 col-sm-6">
											<div class="form-group">
												<label>Mother's Occupation</label> <input type="text"
													class="form-control" value="Home Maker">
											</div>
										</div>
										<div class="col-12 col-sm-6">
											<div class="form-group">
												<label>Mother's Mobile</label> <input type="text"
													class="form-control" value="026 7318 4366">
											</div>
										</div>
										<div class="col-12 col-sm-6">
											<div class="form-group">
												<label>Mother's Email</label> <input type="email"
													class="form-control" value="clearywong@gmail.com">
											</div>
										</div>
										<div class="col-12 col-sm-6">
											<div class="form-group">
												<label>Present Address</label>
												<textarea class="form-control">86 Lamphey Road, Thelnetham</textarea>
											</div>
										</div>
										<div class="col-12 col-sm-6">
											<div class="form-group">
												<label>Permanent Address</label>
												<textarea class="form-control">86 Lamphey Road, Thelnetham</textarea>
											</div>
										</div>
										<div class="col-12">
											<button type="submit" class="btn btn-primary">Submit</button>
										</div>
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