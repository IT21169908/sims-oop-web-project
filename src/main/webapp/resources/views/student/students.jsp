
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0" />
<title>Preskool - Students</title>

<link rel="shortcut icon" href="assets/img/favicon.png" />

<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,500;0,600;0,700;1,400&amp;display=swap" />

<jsp:include page="/resources/views/student/components/styles.jsp" />
</head>
<body>
	<div class="main-wrapper">

		<jsp:include page="/resources/views/student/components/header.jsp" />
		<jsp:include page="/resources/views/student/components/sidebar.jsp" />

		<div class="page-wrapper">
			<div class="content container-fluid">
				<div class="page-header">
					<div class="row align-items-center">
						<div class="col">
							<h3 class="page-title">Students</h3>
							<ul class="breadcrumb">
								<li class="breadcrumb-item"><a href="index.html">Dashboard</a>
								</li>
							</ul>
						</div>
						<div class="col-auto text-end float-end ms-auto">
							<a href="#" class="btn btn-outline-primary me-2"><i
								class="fas fa-download"></i> Download</a> <a href="add-student.html"
								class="btn btn-primary"><i class="fas fa-plus"></i></a>
						</div>
					</div>
				</div>

				<div class="row">
					<div class="col-sm-12">
						<div class="card card-table">
							<div class="card-body">
								<div class="table-responsive">
									<table class="table table-hover table-center mb-0 datatable">
										<thead>
											<tr>
												<th>ID</th>
												<th>Name</th>
												<th>Class</th>
												<th>DOB</th>
												<th>Parent Name</th>
												<th>Mobile Number</th>
												<th>Address</th>
												<th class="text-end">Action</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${studentList}" var="student">
												<tr>${student.studentID}
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


			<jsp:include page="/resources/views/student/components/footer.jsp" />
		</div>
	</div>

	<jsp:include page="/resources/views/student/components/scripts.jsp" />
</body>
</html>
