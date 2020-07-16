<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Recomida | Tus restricciones</title>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
<style>
ul {
	list-style-type: none;
}

li {
	display: inline-block;
	text-align: center;
}

input[type="checkbox"][id^="cb"] {
	display: none;
}

label {
	border: 1px solid #fff;
	padding: 10px;
	display: block;
	position: relative;
	margin: 10px;
	-webkit-touch-callout: none;
	-webkit-user-select: none;
	-khtml-user-select: none;
	-moz-user-select: none;
	-ms-user-select: none;
	user-select: none;
}

label::before {
	background-color: white;
	color: white;
	content: "";
	display: block;
	border-radius: 50%;
	border: 1px solid grey;
	position: absolute;
	top: -5px;
	left: -5px;
	width: 25px;
	height: 25px;
	text-align: center;
	line-height: 28px;
	transition-duration: 0.4s;
	transform: scale(0);
}

label img {
	height: 300px;
	width: 300px;
	transition-duration: 0.2s;
	transform-origin: 50% 50%;
}

:checked+label {
	border-color: #ddd;
}

:checked+label::before {
	content: "";
	background-color: red;
	border-color: red;
	transform: scale(1);
}

:checked+label img {
	transform: scale(0.9);
	box-shadow: 0 0 5px #333;
	z-index: -1;
}
</style>
</head>
<body>
	<%@include file="header.jsp" %>

	<div class="container p-4 mb-5 mt-5">
		<h3>Usuario: ${usuario.email}</h3>
		<ul>
			<c:forEach items="${restricciones}" var="restriccion">
				<li>${restriccion.nombre}<label for="${restriccion.id}"><img
						src="img/${restriccion.nombre}.jpg" /></label>
				</li>
			</c:forEach>
		</ul>
		<a class="btn btn-outline-danger" href="home">Ir a home</a>
		<a class="btn btn-danger" href="seleccionarRestricciones">Modificar restricciones</a>

	</div>

	<!-- Footer -->
			<%@include file="footer.jsp" %>

	<!-- Placed at the end of the document so the pages load faster -->
	
</body>
</html>