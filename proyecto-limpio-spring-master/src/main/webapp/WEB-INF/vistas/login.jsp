<%@ page language="java" contentType="text/html;charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Bienvenido al Registro</title>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
</head>
<body>

	<%@include file="headerSinLogin.jsp" %>
	<div class="container mb-5">
		<div id="loginbox" style="margin-top: 50px;"
			class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
			<form:form action="validar-login" method="POST"
				modelAttribute="usuario">
				<h3 class="form-signin-heading">Iniciar Sesión</h3>
				<hr class="colorgraph">
				<br>
				
				<label for="email" class="h5">Email</label>
				<form:input path="email" id="email" type="email"
					class="form-control" />
					<br>
				<label for="password" class="h5">Contraseña</label>
				<form:input path="password" type="password" id="password"
					class="form-control" />

				<button class="btn btn-lg btn-danger mt-4" Type="Submit" />Login</button>
				<a href="home" class="btn btn-lg btn-danger mt-4">Atrás</a>
			</form:form>

			<c:if test="${not empty error}">
				<h4>
					<span class="text-danger">${error}</span>
				</h4>
				<br>
			</c:if>
		</div>
	</div>
	
	<!-- Footer -->
		<%@include file="footer.jsp" %>

	<!-- Placed at the end of the document so the pages load faster -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script>
		window.jQuery
				|| document
						.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')
	</script>
	<script src="js/bootstrap.min.js" type="text/javascript"></script>

</body>
</html>