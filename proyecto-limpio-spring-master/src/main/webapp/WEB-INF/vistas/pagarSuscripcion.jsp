<%@ page language="java" contentType="text/html;charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">

<title>Recomida!</title>
</head>
<body>
<%@include file="header.jsp" %>
	<section class="container mt-5 mb-5 clearfix">
		<c:if test="${empty msj}">
			<h1 class="display-4">Gracias por adquirir una suscripción ${tipo}!</h1>
			<p class="h5">Ya podes disfrutar de todos los beneficios que te ofrecemos en <span class="text-danger">Recomida</span>.
			Gracias por elegirnos, buen provecho!</p>
			<img src="img/gracias.jpg" class="w-50 mx-auto d-block my-5" style="opacity:0.7">
		
		<a class="btn btn-danger btn-block" href="interno" role="button">Volver al home</a>
		</c:if>
		
		<c:if test="${not empty msj}">
			<h1 class="display-4">${msj}</h1>
			<img src="img/sorry.png" class="w-50 mx-auto d-block" style="opacity:0.7">
			<a class="btn btn-danger btn-block" href="interno" role="button">Volver al home</a>
		</c:if>
	</section>

	<%@include file="footer.jsp" %>

	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
</body>
</html>