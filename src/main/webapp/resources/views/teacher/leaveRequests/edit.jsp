<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
							<h3 class="page-title">Edit Leave</h3>
							<ul class="breadcrumb">
								<li class="breadcrumb-item"><i class="fa fa-home"></i> <a href="/teacher/dashboard">Dashboard</a></li>
								<li class="breadcrumb-item"><a href="/teacher/leave-requests">My Leave List</a></li>
								<li class="breadcrumb-item active">Edit Leave</li>
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
								<form action="/teacher/leave-requests" method="POST">
									<input type="hidden" name="_method" value="put" />
									<input type="hidden" name="leave" value="${leaveRequest.id }" />
									<div class="row">
										<div class="col-12">
											<h5 class="form-title">
												<span>Leave Information</span>
											</h5>
										</div>
										<div class="col-12 col-sm-6">
											<div class="form-group">
												<label>Leave Date</label> 
												<input type="date"  value="${leaveRequest.date }" name="date" class="past-disable form-control" required>
											</div>
										</div>
										<div class="col-12 col-sm-6">
											<div class="form-group">
												<label>No of days</label> 
												<input type="number" value="${leaveRequest.days_count }" name="days_count" class="form-control" required>
											</div>
										</div>
										<div class="col-12">
											<div class="form-group">
												<label>Reason</label>
												<textarea name="reason" class="form-control" required>${leaveRequest.reason }</textarea>
											</div>
										</div>
										<div class="col-12">
											<div class="form-group">
												<label>Cancel Reason</label>
												<textarea name="cancel_reason" class="form-control">${leaveRequest.cancel_reason }</textarea>
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