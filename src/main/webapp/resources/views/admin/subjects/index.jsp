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
<title>Manage Subjects</title>

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
							<h3 class="page-title">Subjects List</h3>
							<ul class="breadcrumb">
								<li class="breadcrumb-item"><i class="fa fa-home"></i> <a href="/admin/dashboard">Dashboard</a></li>
								<li class="breadcrumb-item active">Subjects List</li>
							</ul>
						</div>
                        <div class="col-auto text-end float-end ms-auto">
                            <a href="#" class="btn btn-outline-primary me-2">
                                <i class="fas fa-download"></i>&emsp;Download
                            </a>
                            <a href="/admin/subjects/add" class="btn btn-primary"><i class="fas fa-plus"></i></a>
                        </div>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-12">
						<div class="card card-table">
							<div class="card-body">
								<div class="table-responsive">
									<table class="table table-hover table-center mb-0" id="subjects">
										<thead>
											<tr>
												<th>ID</th>
												<th>CODE</th>
												<th>TITLE</th>
												<th>CREATED AT</th>
												<th>UPDATED AT</th>
												<th>ACTION</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="subject" items="${subjectList}">
												<tr id="subject-${subject.id}">
													<td>${subject.id}</td>
													<td>${subject.code}</td>
													<td>${subject.title}</td>
													<td>${subject.created_at}</td>
													<td>${subject.updated_at}</td>
													<td>
														<a href="/admin/subjects/edit?subject=${subject.id}" title="Edit Subject"
															class="btn btn-sm bg-success-light me-2"> 
															<i class="fas fa-pen"></i>
														</a>
														<a href="#" title="Delete Subject" data-subject="${subject.id}"
															class="btn btn-sm bg-danger-light delete-subject" >
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

			<jsp:include page="/resources/views/admin/components/footer.jsp" />

		</div>

	</div>


	<jsp:include page="/resources/views/admin/components/scripts.jsp" />
	<script src="/resources/views/admin/js/subject-manager.js"></script>

</body>

</html>