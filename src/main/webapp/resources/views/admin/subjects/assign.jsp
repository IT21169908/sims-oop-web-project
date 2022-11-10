<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0">
<title>Manage Subjects | SIMS</title>

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
							<h3 class="page-title">Assign Teachers</h3>
							<ul class="breadcrumb">
								<li class="breadcrumb-item"><i class="fa fa-home"></i> <a
									href="/admin/dashboard">Dashboard</a></li>
								<li class="breadcrumb-item"><a
									href="/admin/subjects-assign">Subject List</a></li>
								<li class="breadcrumb-item active">Assign Teachers</li>
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
								<form action="/admin/subjects-assign" method="POST">
									<input type="hidden" name="_method" value="post" /> <input
										type="hidden" name="subject" value="${subject.id }" />
									<div class="row">
										<div class="col-12">
											<h5 class="form-title">
												<span>Subject Information</span>
											</h5>
										</div>
										<div class="col-12 col-sm-6">
											<div class="form-group">
												<label>Subject Code</label> <input type="text"
													value="${subject.code }" name="subject_code"
													class="form-control" disabled required>
											</div>
										</div>
										<div class="col-12 col-sm-6">
											<div class="form-group">
												<label>Subject Title</label> <input type="text"
													value="${subject.title }" name="subject_title"
													class="form-control" disabled required>
											</div>
										</div>
										<div class="col-12 col-sm-6">
											<div class="form-group">
												<label>Assign Teacher</label><br> <select multiple
													placeholder="Select name" data-allow-clear="1"
													class="form-control select2" name="selected_teacher">
													<c:forEach var="teacher" items="${teachersList}"
														varStatus="loop">
														<%-- <c:if test="${ !(fn:contains(subjectTeachersIdsList, teacher.id)) }">
															<option value="${teacher.id}">${teacher.name}</option>
														</c:if>
														<c:if test="${ (fn:contains(subjectTeachersIdsList, teacher.id)) }">
															<option value="${teacher.id}" selected>${teacher.name}</option>
														</c:if> --%>
														<option value="${teacher.id}"
															${ (fn:contains(subjectTeachersIdsList, teacher.id)) ? "selected" : "" }>
															${teacher.name}</option>
													</c:forEach>
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
	<script src="/resources/views/admin/js/subject-manager.js"></script>

</body>

</html>