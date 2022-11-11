<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0">
<title>Requests a Leave | SIMS</title>

<link rel="shortcut icon" href="/resources/images/favicon.png">

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
							<h3 class="page-title">Add Leave</h3>
							<ul class="breadcrumb">
								<li class="breadcrumb-item"><i class="fa fa-home"></i> <a href="/teacher/dashboard">Dashboard</a></li>
								<li class="breadcrumb-item active">Request Leave</li>
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
						<% request.getSession().removeAttribute("errors"); %>
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
								<form action="/teacher/leave-requests" method="POST">
									<input type="hidden" name="_method" value="post" />
									<div class="row">
										<div class="col-12">
											<h5 class="form-title">
												<span>Leave Information</span>
											</h5>
										</div>
										<div class="col-12 col-sm-6">
											<div class="form-group">
												<label>Leave Date</label> <input type="date" name="date"
													class="form-control past-disable" required>
											</div>
										</div>
										<div class="col-12 col-sm-6">
											<div class="form-group">
												<label>No of days</label> 
												<input type="number" name="days_count" class="form-control" required>
											</div>
										</div>
										<div class="col-12">
											<div class="form-group">
												<label>Reason</label>
												<textarea name="reason" class="form-control" required></textarea>
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
			<jsp:include page="/resources/views/teacher/components/footer.jsp" />

		</div>

	</div>


	<jsp:include page="/resources/views/teacher/components/scripts.jsp" />
	<script src="/resources/views/teacher/js/leave-manager.js"></script>

</body>

</html>