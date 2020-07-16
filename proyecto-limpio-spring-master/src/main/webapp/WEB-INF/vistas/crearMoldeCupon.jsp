<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Crear Molde</title>
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
	<%@include file="header.jsp"%>

	<h3 class="text-center mt-5">Cupon de Descuento</h3>

	<section class="container -sm p-4">
	<p class="lead">Usted va a crear un Molde de un Cupon De Descuento</p>
	<p>El sistema otorgará aleatoriamente un cupon al cliente, en base a las compras que realizó en una semana.</p>
		<form:form action="agregarMoldeCuponValidacion" method="POST"
			modelAttribute="molde">
			<div class="form-group">
				<label for="molde">Ingrese el valor del Cupon</label>
				<form:input path="valor" id="valor" type="number"
					class="form-control" required="required" />
			</div>
			<a href="home" class="btn btn-outline-danger mb-5">Atrás</a>
			<button type="submit" class="btn btn-danger mb-5">Crear</button>
		</form:form>

	</section>
	
	<!-- Footer -->
	<%@include file="footer.jsp"%>

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