<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0">
<title>Teacher Dashboard | SIMS</title>

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
					<div class="row">
						<div class="col-sm-12">
							<h3 class="page-title">Welcome ${user.name }!</h3>
							<ul class="breadcrumb">
								<li class="breadcrumb-item"><a href="index.html">Dashboard</a></li>
								<li class="breadcrumb-item active">Teacher Dashboard</li>
							</ul>
						</div>
					</div>
				</div>


				<div class="row">
					<div class="col-xl-3 col-sm-6 col-12 d-flex">
						<div class="card bg-five w-100">
							<div class="card-body">
								<div
									class="db-widgets d-flex justify-content-between align-items-center">
									<div class="db-icon">
										<i class="fas fa-chalkboard"></i>
									</div>
									<div class="db-info">
										<h3>04/06</h3>
										<h6>Total Classes</h6>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-xl-3 col-sm-6 col-12 d-flex">
						<div class="card bg-six w-100">
							<div class="card-body">
								<div
									class="db-widgets d-flex justify-content-between align-items-center">
									<div class="db-icon">
										<i class="fas fa-user-graduate"></i>
									</div>
									<div class="db-info">
										<h3>40/60</h3>
										<h6>Total Students</h6>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-xl-3 col-sm-6 col-12 d-flex">
						<div class="card bg-seven w-100">
							<div class="card-body">
								<div
									class="db-widgets d-flex justify-content-between align-items-center">
									<div class="db-icon">
										<i class="fas fa-book-open"></i>
									</div>
									<div class="db-info">
										<h3>30/50</h3>
										<h6>Total Lessons</h6>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-xl-3 col-sm-6 col-12 d-flex">
						<div class="card bg-eight w-100">
							<div class="card-body">
								<div
									class="db-widgets d-flex justify-content-between align-items-center">
									<div class="db-icon">
										<i class="fas fa-clock"></i>
									</div>
									<div class="db-info">
										<h3>15/20</h3>
										<h6>Total Hours</h6>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>


				<div class="row">
					<div class="col-12 col-lg-12">
						<div class="row">
							<div class="col-12 d-flex">
								<div class="card flex-fill">
									<div class="card-header">
										<div class="row align-items-center">
											<div class="col-6">
												<h5 class="card-title">Upcoming Lesson</h5>
											</div>
											<div class="col-6">
												<span class="float-end view-link"><a href="#">View
														All Courses</a></span>
											</div>
										</div>
									</div>
									<div class="pt-3 pb-3">
										<div class="table-responsive lesson">
											<table class="table table-center">
												<tbody>
													<tr>
														<td>
															<div class="date">
																<b>Aug 4, Tuesday</b>
																<p>2.30pm - 3.30pm (60min)</p>
															</div>
														</td>
														<td>
															<div class="date">
																<b>Lessons 30</b>
																<p>3.1 Ipsuum dolor</p>
															</div>
														</td>
														<td><a href="#">Confirmed</a></td>
														<td><button type="submit" class="btn btn-info">Reschedule</button></td>
													</tr>
													<tr>
														<td>
															<div class="date">
																<b>Aug 5, Wednesday</b>
																<p>3.00pm - 4.30pm (90min)</p>
															</div>
														</td>
														<td>
															<div class="date">
																<b>Lessons 31</b>
																<p>3.2 Ipsuum dolor</p>
															</div>
														</td>
														<td><a href="#">Confirmed</a></td>
														<td><button type="submit" class="btn btn-info">Reschedule</button></td>
													</tr>
													<tr>
														<td>
															<div class="date">
																<b>Aug 6, Thursday</b>
																<p>11.00am - 12.00pm (20min)</p>
															</div>
														</td>
														<td>
															<div class="date">
																<b>Lessons 32</b>
																<p>3.3 Ipsuum dolor</p>
															</div>
														</td>
														<td><a href="#">Confirmed</a></td>
														<td><button type="submit" class="btn btn-info">Reschedule</button></td>
													</tr>
												</tbody>
											</table>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-12 col-lg-12 d-flex">
						<div class="card flex-fill">
							<div class="card-header">
								<div class="row align-items-center">
									<div class="col-12">
										<h5 class="card-title">Calendar</h5>
									</div>
								</div>
							</div>
							<div class="card-body">
								<div id="calendar-doctor" class="calendar-container"></div>
								<div class="calendar-info calendar-info1">
									<div class="calendar-details">
										<p>09 am</p>
										<h6
											class="calendar-blue d-flex justify-content-between align-items-center">
											Fermentum <span>09am - 10pm</span>
										</h6>
									</div>
									<div class="calendar-details">
										<p>10 am</p>
										<h6
											class="calendar-violet d-flex justify-content-between align-items-center">
											Pharetra et <span>10am - 11am</span>
										</h6>
									</div>
									<div class="calendar-details">
										<p>11 am</p>
										<h6
											class="calendar-red d-flex justify-content-between align-items-center">
											Break <span>11am - 11.30am</span>
										</h6>
									</div>
									<div class="calendar-details">
										<p>12 pm</p>
										<h6
											class="calendar-orange d-flex justify-content-between align-items-center">
											Massa <span>11.30am - 12.00pm</span>
										</h6>
									</div>
									<div class="calendar-details">
										<p>09 am</p>
										<h6
											class="calendar-blue d-flex justify-content-between align-items-center">
											Fermentum <span>09am - 10pm</span>
										</h6>
									</div>
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

</body>

</html>