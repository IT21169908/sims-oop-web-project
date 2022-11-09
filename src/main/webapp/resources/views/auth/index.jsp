<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

<head>
	<meta charset="ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no" />
	<meta name="description" content="">
	<meta name="keywords" content="">
	<meta name="author" content="">
	<title>Sign in | School Information Management System</title>
	<!-- Favicon -->
	<link href="/resources/images/favicon.png" rel="icon" />

	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css">
	
    <jsp:include page="/resources/views/auth/components/styles.jsp" />
</head>

<body>

<!-- ==================================================== -->
<!-- If the user logged in then redirect back to the page -->
${user != null ? "<script>history.back()</script>" : ""}
<!-- ==================================================== -->

	<div class="container" id="container">
	
		<div class="form-container sign-in-container">
			<form action="/login" method="POST">
				<div class="header">Sign In</div>
				<div class="social__media__container">
					<a href="https://www.google.com/" target="_blank" class="social google">
						<i class="fa-brands fa-google"></i>
					</a>
					<a href="https://www.facebook.com/" target="_blank" class="social facebook">
						<i class="fa-brands fa-facebook"></i>
					</a>
				</div>
				<span class="under__social">
					<a href="#" class="link signup-link"><small>or sign in with account</small></a>
				</span>
				
				<c:if test="${ errors != null }">
					<div class="d-flex justify-content-center w-100 mt-2">
						<div class="alert alert-danger mb-0 py-1 w-100" role="alert">
						  <span>${errors[0]}</span>
						</div>
					</div>
				</c:if>

				<div class="button-input-group">
					<div class="group input-group">
						<input type="email" name="email" value="${email}" placeholder="Email" required/>
					</div>
					<div class="group input-group">
						<input type="password" name="password" value="${password}" placeholder="Password" pattern=".{8,}" required/>
					</div>
					<div class="alert-text signup__alert">
						<span class="help__text">At least 8 character</span>
					</div>
					<div class="form-link forgot">
						<a href="#" class="login-link">Forgot your password?</a>
					</div>
					<div class="group button-group">
						<button class="signin-btn" type="submit" name="submit">Sign in</button>
					</div>
				</div>
			</form>
		</div>

		<div class="overlay-container">
			<div class="overlay">
				<div class="overlay-panel overlay-right text-dark">
					<div class="logo">
						<img src="./resources/images/school-logo.png">
					</div>
					<br>
					<p>Enter your login credentials and log in to SIMS.</p>
					<p>Please contact the School Registration Manager or Class Teacher if you do not have login access.</p>
					<footer>
						<p><small>School Information Management System</small></p>
					</footer>
				</div>
			</div>
		</div>

	</div>
	
    <jsp:include page="/resources/views/auth/components/footer.jsp" />

	<jsp:include page="/resources/views/auth/components/scripts.jsp" />
 
</body>
</html>

