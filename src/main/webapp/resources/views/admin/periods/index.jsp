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
<title>Manage Periods | Admin</title>

<link rel="shortcut icon" href="/resources/images/favicon.png">

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
							<h3 class="page-title">Manage Periods</h3>
							<ul class="breadcrumb">
								<li class="breadcrumb-item"><i class="fa fa-home"></i> <a
									href="/admin/dashboard">Dashboard</a></li>
								<li class="breadcrumb-item active">Periods</li>
							</ul>
						</div>
						<div class="col-auto text-end float-end ms-auto">
							<a href="/admin/periods/create" class="btn btn-primary"><i
								class="fas fa-plus"></i></a>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-12">
						<div class="card card-table">
							<div class="card-body">
								<div class="table-responsive">
									<table class="table table-hover table-center mb-0"
										id="periods">
										<thead>
											<tr>
												<th>ID</th> 
												<th>TITLE</th>
												<th>SUBJECT</th>
												<th>GRADE</th>
												<th>TEACHER</th>
												<th>START TIME</th>
												<th>END TIME</th>
												<th>DAY</th>
												<th>START DATE</th>
												<th>END DATE</th>
												<th>CREATED AT</th>
												<th>UPDATED AT</th>
												<th>ACTION</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="period" items="${periodList}">
												<tr id="period-${period.id}">
													<td>${period.id}</td> 
													<td>${period.title}</td>
													<td>${ period.subject_title }</td>
													<td>${period.grade_title}</td>
													<td>${period.teacher_name}</td>
													<td>${period.start_time}</td>
													<td>${period.end_time}</td>
													<td>${period.day}</td>
													<td>${period.start_date}</td>
													<td>${period.end_date}</td>
													<td>${period.created_at}</td>
													<td>${period.updated_at}</td>
													<td><a
														href="/admin/periods/edit?period=${period.id}"
														title="Edit period"
														class="btn btn-sm bg-success-light me-2"> <i
															class="fas fa-pen"></i>
													</a> <a href="#" title="Delete period"
														data-period="${period.id}"
														class="btn btn-sm bg-danger-light delete-period"> <i
															class="fas fa-trash"></i>
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
			</div>

			<jsp:include page="/resources/views/admin/components/footer.jsp" />

		</div>

	</div>


	<jsp:include page="/resources/views/admin/components/scripts.jsp" />
	<script src="/resources/views/admin/js/period-manager.js"></script>

</body>

</html>