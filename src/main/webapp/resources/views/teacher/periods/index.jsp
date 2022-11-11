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
<title>Manage Periods | Teacher</title>

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
													<td>${period.start_time}</td>
													<td>${period.end_time}</td>
													<td>${period.day}</td>
													<td>${period.start_date}</td>
													<td>${period.end_date}</td>
													<td>${period.created_at}</td>
													<td>${period.updated_at}</td>
													<td>-</td>
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
	<script>
		let period_table = null;
		if ($("#periods").length) {
			period_table = $("#periods").DataTable();
		}
	</script>

</body>

</html>