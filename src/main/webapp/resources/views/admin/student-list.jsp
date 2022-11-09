<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0">
<title>Users - Admin Dashboard</title>

<link rel="shortcut icon" href="/resources/assets/img/favicon.png">

<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,500;0,600;0,700;1,400&amp;display=swap">

<link rel="stylesheet"
	href="/resources/assets/plugins/bootstrap/css/bootstrap.min.css">

<link rel="stylesheet"
	href="/resources/assets/plugins/fontawesome/css/fontawesome.min.css">
<link rel="stylesheet"
	href="/resources/assets/plugins/fontawesome/css/all.min.css">

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
												<th>NAME</th>
												<th>EMAIL</th>
												<th>NIC</th>
												<th>ROLE</th>
												<th>EMAIL VERIFIED</th> 
												<th>CREATED AT</th>
												<th>UPDATED AT</th>
												<th>ACTION</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="user" items="${userList}">
												<tr id="user-${user.id}">
													<td>${user.id}</td>
													<td>${user.name}</td>
													<td>${user.email}</td>
													<td>${user.nic}</td>
													<td>${user.type}</td>
													<td>${user.email_verified}</td> 
													<td>${user.created_at}</td>
													<td>${user.updated_at}</td>
													<td><a
														href="/admin/users/edit?user=${user.id}"
														class="btn btn-sm bg-success-light me-2"> <i
															class="fas fa-pen"></i>
													</a> <a href="#"
														class="btn btn-sm bg-danger-light delete-user"
														data-user="${user.id}"> <i class="fas fa-trash"></i>
													</a></td>
												</tr>
											</c:forEach>
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
				<p>Copyright © 2020 Dreamguys.</p>
			</footer>

		</div>

	</div>


	<script src="/resources/assets/js/jquery-3.6.0.min.js"></script>

	<script
		src="/resources/assets/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>

	<script src="/resources/assets/js/feather.min.js"></script>

	<script
		src="/resources/assets/plugins/slimscroll/jquery.slimscroll.min.js"></script>

	<script src="/resources/assets/plugins/apexchart/apexcharts.min.js"></script>
	<script src="/resources/assets/plugins/apexchart/chart-data.js"></script>

	<script src="/resources/assets/js/script.js"></script>
</body>

</html>
