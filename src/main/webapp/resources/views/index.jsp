<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
	<!DOCTYPE html>
	<html>

	<head>
		<meta charset="ISO-8859-1">
		<meta http-equiv="X-UA-Compatible" content="IE=edge" />
		<!-- Mobile Metas -->
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
		<!-- Site Metas -->
		<meta name="keywords" content="" />
		<meta name="description" content="" />
		<meta name="author" content="" />

		<title>School of Happy Valley</title>


		<!-- progress barstle -->
		<link rel="stylesheet" href="/resources/css/landing-page/css-circular-prog-bar.css">
		<!-- fonts style -->
		<link href="https://fonts.googleapis.com/css?family=Poppins:400,700&display=swap" rel="stylesheet">
		<!-- font wesome stylesheet -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
		<!-- bootstrap core css -->
		<link rel="stylesheet" type="text/css" href="/resources/css/landing-page/bootstrap.css" />
		<!-- Custom styles for this template -->
		<link href="/resources/css/landing-page/landing-page.css" rel="stylesheet" />
		<!-- responsive style -->
		<link href="/resources/css/landing-page/responsive.css" rel="stylesheet" />

		<link rel="stylesheet" href="/resources/css/landing-page/css-circular-prog-bar.css">

	</head>

	<body>
		<div class="top_container">
			<!-- header section strats -->
			<header class="header_section">
				<div class="container">
					<nav class="navbar navbar-expand-lg custom_nav-container ">
						<a class="navbar-brand" href="index.html"> <img src="/resources/images/landing-page/logo.png"
								alt=""> <span> School of Happy
								Valley </span>
						</a>
						<button class="navbar-toggler" type="button" data-toggle="collapse"
							data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
							aria-expanded="false" aria-label="Toggle navigation">
							<span class="navbar-toggler-icon"></span>
						</button>
					</nav>
				</div>
			</header>
			<section class="hero_section ">
				<div class="hero-container container">
					<div class="hero_detail-box">
						<h3>
							Welcome to <br> Best Educations
						</h3>
						<h1>school</h1>
						<p>There are many variations of passages of Lorem Ipsum
							available, but the majority have suffered alteration in some form,
							by injected humour, or randomised</p>
						<div class="hero_btn-continer">
							<a href="/login" class="call_to-btn btn_white-border"> <span>
									Login </span> <img src="/resources/images/landing-page/right-arrow.png" alt="">
							</a>
						</div>
					</div>
					<div class="hero_img-container">
						<div>
							<img src="/resources/images/landing-page/hero.png" alt="" class="img-fluid">
						</div>
					</div>
				</div>
			</section>
		</div>
		<!-- end header section -->

		<!-- about section -->
		<section class="about_section layout_padding">
			<div class="container">
				<h2 class="main-heading ">About School</h2>
				<p class="text-center">There are many variations of passages of
					Lorem Ipsum available, but the majority hThere are many variations
					of passages of Lorem Ipsum available, but the majority h</p>
				<div class="about_img-box ">
					<img src="/resources/images/landing-page/kids.jpg" alt="" class="img-fluid w-100">
				</div>
				<div class="d-flex justify-content-center mt-5">
					<a href="" class="call_to-btn  "> <span> Read More </span> <img
							src="/resources/images/landing-page/right-arrow.png" alt="">
					</a>
				</div>
			</div>
		</section>
		<!-- about section -->

		<!-- contact section -->
		<section class="contact_section layout_padding-bottom">
			<div class="container">

				<h2 class="main-heading">Contact Now</h2>
				<p class="text-center">reprehenderit in voluptate velit esse
					cillum dolore eu fugiat nulla</p>
				<div class="">
					<div class="contact_section-container">
						<div class="row">
							<div class="col-md-6 mx-auto">
								<div class="contact-form">
									<form action="">
										<div>
											<input type="text" placeholder="Name">
										</div>
										<div>
											<input type="text" placeholder="Phone Number">
										</div>
										<div>
											<input type="email" placeholder="Email">
										</div>
										<div>
											<input type="text" placeholder="Message" class="input_message">
										</div>
										<div class="d-flex justify-content-center">
											<button type="submit" class="btn_on-hover">Send</button>
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>

			</div>
		</section>


		<!-- end contact section -->

		<!-- admission section -->
		<section class="admission_section ">
			<div class="container-fluid position-relative">
				<div class="row h-100">
					<div id="map" class="h-100 w-100 "></div>
					<div class="container">
						<div class="admission_container position-absolute">
							<div class="admission_img-box">
								<img src="/resources/images/landing-page/kidss.jpg" alt="">
							</div>
							<div class="admission_detail">
								<h3>Apply for Admission</h3>
								<p class="mt-3 mb-4">There are many variations of passages of
									Lorem Ipsum available, but the majority h</p>
								<div class="">
									<a href="" class="admission_btn btn_on-hover"> Read More </a>
								</div>

							</div>
						</div>
					</div>
				</div>
			</div>
		</section>
		<!-- admission section -->

		<!-- footer section -->
		<section class="container-fluid footer_section">
			<p>Copyright � 2022 SIMS OOP Module.</p>
		</section>
		<!-- footer section -->

		<script type="text/javascript" src="/resources/js/landing-page/jquery-3.4.1.min.js"></script>
		<script type="text/javascript" src="/resources/js/landing-page/bootstrap.js"></script>

		<script>
			// This example adds a marker to indicate the position of Bondi Beach in Sydney,
			// Australia.
			function initMap() {
				var map = new google.maps.Map(document.getElementById('map'), {
					zoom: 11,
					center: {
						lat: 40.645037,
						lng: -73.880224
					},
				});

				var image = '/resources/images/landing-page/maps-and-flags.png';
				var beachMarker = new google.maps.Marker({
					position: {
						lat: 40.645037,
						lng: -73.880224
					},
					map: map,
					icon: image
				});
			}
		</script>
		<!-- google map js -->
		<script
			src="https://maps.googleapis.com/maps/api/js?key=AIzaSyA8eaHt9Dh5H57Zh0xVTqxVdBFCvFMqFjQ&callback=initMap">
			</script>
		<!-- end google map js -->
	</body>

	</html>