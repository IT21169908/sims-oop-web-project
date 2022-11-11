<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0">
<title>Create Period | Admin Dashboard</title>

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
							<h3 class="page-title">Add Period</h3>
							<ul class="breadcrumb">
								<li class="breadcrumb-item"><i class="fa fa-home"></i> <a
									href="/admin/dashboard">Dashboard</a></li>
								<li class="breadcrumb-item active">Add Period</li>
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
								<form action="/admin/periods/create" method="POST">
									<input type="hidden" name="_method" value="post" />
									<div class="row">
										<div class="col-12">
											<h5 class="form-title">
												<span>Period Information</span>
											</h5>
										</div>
										<div class="col-12 col-sm-6">
											<div class="form-group">
												<label>Title</label> <input type="text" name="title"
													class="form-control" required>
											</div>
										</div>
										<div class="col-12 col-sm-6">
											<div class="form-group">
												<label>Subject</label><br> <select required
													id="subject" placeholder="Select subject"
													class="form-control" name="subject_id">
													<option value="">Select Subject</option>
													<c:forEach var="subject" items="${subjectList}"
														varStatus="loop">
														<option value="${subject.id}">${subject.title}</option>
													</c:forEach>
												</select>
											</div>
										</div>
										<div class="col-12 col-sm-6">
											<div class="form-group">
												<label>Grade</label><br> <select
													placeholder="Select Grade" required class="form-control"
													name="grade_id">
													<option value="">Select Grade</option>
													<c:forEach var="grade" items="${gradeList}"
														varStatus="loop">
														<option value="${grade.id}">${grade.title}</option>
													</c:forEach>
												</select>
											</div>
										</div>
										<div class="col-12 col-sm-6">
											<div class="form-group">
												<label>Teacher</label><br> <select
													placeholder="Select teacher" id="teachers" required
													class="form-control" name="teacher_id">
													<option value="">Select teacher</option>
													<c:forEach var="teacher" items="${subjectTeachersList}"
														varStatus="loop">
														<option value="${teacher.id}">${teacher.name}</option>
													</c:forEach>
												</select>
											</div>
										</div>
										<div class="col-12 col-sm-6">
											<div class="form-group">
												<label>Start Time</label> <input type="time"
													name="start_time" class="form-control" required>
											</div>
										</div>

										<div class="col-12 col-sm-6">
											<div class="form-group">
												<label>End Time</label> <input type="time" name="end_time"
													class="form-control" required>
											</div>
										</div>
										<div class="col-12 col-sm-6">
											<div class="form-group">
												<label>Week Day</label><br> <select
													placeholder="Select Day" id="day" required
													class="form-control" name="day">
													<option value="">Select Day</option>
													<option value="monday">Monday</option>
													<option value="tuesday">Tuesday</option>
													<option value="wednesday">Wednesday</option>
													<option value="thursday">Thursday</option>
													<option value="friday">Friday</option>

												</select>
											</div>
										</div>
										<div class="col-12 col-sm-6">
											<div class="form-group">
												<label>Start Date</label> <input type="date"
													name="start_date" class="form-control" required>
											</div>
										</div>

										<div class="col-12 col-sm-6">
											<div class="form-group">
												<label>End Date</label> <input type="date" name="end_date"
													class="form-control" required>
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
	<script src="/resources/views/admin/js/period-manager.js"></script>

</body>

</html>