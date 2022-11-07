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
<title>Manage Leave Requests</title>

<link rel="shortcut icon" href="/resources/assets/img/favicon.png">

<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,500;0,600;0,700;1,400&amp;display=swap">

<jsp:include page="/resources/views/teacher/components/styles.jsp" />
</head>

<body>

	<div class="main-wrapper">

		<jsp:include page="/resources/views/teacher/components/header.jsp" />
		<jsp:include page="/resources/views/teacher/components/sidebar.jsp" />

		<div class="page-wrapper">
			<div class="content container-fluid">
				<div class="page-header">
					<div class="row align-items-center">
						<div class="col">
							<h3 class="page-title">My Leave List</h3>
							<ul class="breadcrumb">
								<li class="breadcrumb-item"><a
									href="teacher/manage-leave-requests">Manage Leaves</a></li>
								<li class="breadcrumb-item active">My Leave List</li>
							</ul>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-12">
						<div class="card card-table">
							<div class="card-body">
								<div class="table-responsive">
									<table class="table table-hover table-center mb-0" id="leave-requests">
										<thead>
											<tr>
												<th>ID</th>
												<th>DATE</th>
												<th>NO OF DAYS</th>
												<th>REASON</th>
												<th>CANCEL REASON</th>
												<th>STATUS</th>
												<th>REQUESTED AT</th>
												<th>CREATED AT</th>
												<th>UPDATED AT</th>
												<th>ACTION</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="leave" items="${LeaveRequestList}">
												<tr id="leave-${leave.id}">
													<td>${leave.id}</td>
													<td>${leave.date}</td>
													<td>${leave.days_count}</td>
													<td>${leave.reason}</td>
													<td>${leave.cancel_reason}</td>
													<td>${leave.status}</td>
													<td>${leave.requested_at}</td>
													<td>${leave.created_at}</td>
													<td>${leave.updated_at}</td>
													<td>
														<a href="/teacher/leave-requests/edit?leave=${leave.id}" class="btn btn-sm bg-success-light me-2"> 
															<i class="fas fa-pen"></i>
														</a>
														<a href="#" class="btn btn-sm bg-danger-light delete-leave" data-leave="${leave.id}">
															<i class="fas fa-trash"></i>
														</a>
													</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>

			<jsp:include page="/resources/views/teacher/components/footer.jsp" />

		</div>

	</div>


	<jsp:include page="/resources/views/teacher/components/scripts.jsp" />
	<script src="/resources/views/teacher/js/leave-manager.js"></script>

</body>

</html>