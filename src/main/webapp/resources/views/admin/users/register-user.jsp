<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0">
<title>Register User - Admin Dashboard</title>
<link rel="shortcut icon" href="/resources/assets/img/favicon.png">

<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,500;0,600;0,700;1,400&amp;display=swap">

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
							<h3 class="page-title">New User</h3>
							<ul class="breadcrumb">
								<li class="breadcrumb-item"><i class="fa fa-home"></i> <a href="/admin/dashboard">Dashboard</a></li>
								<li class="breadcrumb-item active">Register User</li>
							</ul>
						</div>
					</div>
				</div>


				<!-- PAGE CONTENTS -->

				<div class="row">
					<c:if test="${ errors != null }">
						<div class="d-flex justify-content-center w-100 mt-2">
							<div class="alert alert-danger py-1 w-100" role="alert">
								<span>${errors[0]}</span>
							</div>
						</div>
						<%
						request.getSession().removeAttribute("errors");
						%>
					</c:if>
					<c:if test="${ success != null }">
						<div class="d-flex justify-content-center w-100 mt-2">
							<div class="alert alert-success py-1 w-100" role="alert">
								<span>${success}</span>
							</div>
						</div>
						<%
						request.getSession().removeAttribute("success");
						%>
					</c:if>
					<div class="col-sm-12">
						<div class="card">
							<div class="card-body">
								<form action="/admin/users" method="POST">
									<input type="hidden" name="_method" value="post" />
									<div class="row">
										<div class="col-12">
											<h5 class="form-title">
												<span>User Information</span>
											</h5>
										</div>
										<div class="col-12 col-sm-6">
											<div class="form-group">
												<label>Full Name</label> <input type="text" name="name"
													class="form-control" required>
											</div>
										</div>
										<div class="col-12 col-sm-6">
											<div class="form-group">
												<label>Gender</label> <select class="form-control select"
													name="gender" required>
													<option value="">Select Gender</option>
													<option value="male">Male</option>
													<option value="female">Female</option>
												</select>
											</div>
										</div>
										<div class="col-12 col-sm-6">
											<div class="form-group">
												<label>Date of Birth</label>
												<div>
													<input type="date" name="dob" class="form-control" required>
												</div>
											</div>
										</div>
										<div class="col-12 col-sm-6">
											<div class="form-group">
												<label>Mobile Number</label> <input type="text"
													name="mobile_number" class="mobile_number_sl form-control"
													required>
											</div>
										</div>
										<div class="col-12 col-sm-6">
											<div class="form-group">
												<label>Email</label> <input type="email" name="email"
													class="form-control" required>
											</div>
										</div>
										<div class="col-12 col-sm-6">
											<div class="form-group">
												<label>NIC</label> <input type="text" name="nic"
													class="nic_mask form-control" required>
											</div>
										</div>
										<div class="col-12 col-sm-6">
											<div class="form-group">
												<label>Password</label> <input type="password" id="password"
													name="password" class="form-control" required
													autocomplete="new-password">
											</div>
										</div>
										<div class="col-12 col-sm-6">
											<div class="form-group">
												<label>Confirm Password</label> <input type="password"
													id="confirm_password" name="confirm-password"
													class="form-control" required autocomplete="new-password">
											</div>
										</div>
										<div class="col-12 col-sm-6">
											<div class="form-group">
												<label>Role</label> <select class="form-control select"
													name="type" required>
													<option value="">Select user type</option>
													<option value="student">Student</option>
													<option value="teacher">Teacher</option>
												</select>
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

	<script type="text/javascript">
		let password = document.getElementById("password"), confirm_password = document
				.getElementById("confirm_password");

		function validatePassword() {
			if (password.value != confirm_password.value) {
				confirm_password.setCustomValidity("Passwords Don't Match");
			} else {
				confirm_password.setCustomValidity('');
			}
		}

		password.onchange = validatePassword;
		confirm_password.onkeyup = validatePassword;
	</script>
</body>

</html>
