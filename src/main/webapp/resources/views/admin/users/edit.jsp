<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0">

<title>User Edit - Admin Dashboard</title>

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
					<div class="row align-items-center">
						<div class="col">
							<h3 class="page-title">Edit User</h3>
							<ul class="breadcrumb">
								<li class="breadcrumb-item"><i class="fa fa-home"></i> <a href="/admin/dashboard">Dashboard</a></li>
								<li class="breadcrumb-item"><a href="/admin/users">Users</a></li>
								<li class="breadcrumb-item active">Edit user</li>
							</ul>
						</div>
					</div>
				</div>

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
									<input type="hidden" name="_method" value="put" />
									<div class="row">
										<div class="col-12">
											<h5 class="form-title">
												<span>User Information</span>
											</h5>
										</div>
										<div class="col-12 col-sm-6">
											<div class="form-group">
												<label>Registration Number</label> <input type="text"
													class="form-control" value="${ editUser.id }" disabled>
												<input type="hidden" name="id" value="${ editUser.id }">
											</div>
										</div>
										<div class="col-12 col-sm-6">
											<div class="form-group">
												<label>Full Name</label> <input type="text" name="name"
													class="form-control" value="${ editUser.name }" required>
											</div>
										</div>

										<div class="col-12 col-sm-6">
											<div class="form-group">
												<label>Gender</label> <select class="form-control select"
													name="gender" required>
													<option value="">Select Gender</option>
													<option value="male" ${ editUser.gender == "male" ? "selected" : "" }>Male</option>
													<option value="female" ${ editUser.gender == "female" ? "selected" : "" }>Female</option>
												</select>
											</div>
										</div>
										<div class="col-12 col-sm-6">
											<div class="form-group">
												<label>Date of Birth</label>
												<div>
													<input type="date" name="dob" class="form-control" value="${ editUser.dob }" required>
												</div>
											</div>
										</div>
										<div class="col-12 col-sm-6">
											<div class="form-group">
												<label>Mobile Number</label> 
												<input type="text" name="mobile_number" class="mobile_number_sl form-control" value="${ editUser.mobile_number }">
											</div>
										</div>
										<div class="col-12 col-sm-6">
											<div class="form-group">
												<label>Joining Date</label>
												<div>
													<input type="datetime-local" class="form-control" value="${ editUser.created_at }" disabled>
												</div>
											</div>
										</div>
										<c:if test='${editUser.type == "student" }'>
											<div class="col-12">
												<h5 class="form-title">
													<span>Parent Information</span>
												</h5>
											</div>
											<div class="col-12 col-sm-6">
												<div class="form-group">
													<label>Father's Name</label> 
													<input type="text" name="parent_name" class="form-control" value="Stephen Marley">
												</div>
											</div>

											<div class="col-12 col-sm-6">
												<div class="form-group">
													<label>Permanent Address</label>
													<textarea class="form-control" name="address">86 Lamphey Road, Thelnetham</textarea>
												</div>
											</div>
										</c:if>

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