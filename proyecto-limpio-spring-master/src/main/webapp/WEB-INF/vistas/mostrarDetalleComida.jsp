<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Detalle Comida</title>
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

<%@include file="header.jsp" %>

	<section>
		<p>Detalle de la Comida</p>
		<h3>Comida: ${comida.nombre}</h3>
		<h3>Descripción: ${comida.descripcion}</h3>
		<h3>Calorías: ${comida.calorias}</h3>
		<h3>Precio: ${comida.precio}</h3>
		<h3>Horario: ${comida.tipoHorario}</h3>
		<br>
		<h1>Comidas más vistas</h1>
		<c:forEach items="${comidasVistas}" var="comida1">
			<ul>
				<li>${comida1.nombre}</li>
			</ul>
		</c:forEach>
		<br>
		<h1>Comidas más Pedidas</h1>
		<c:forEach items="${comidasPedidas}" var="comida2">
			<ul>
				<li>${comida2.nombre}</li>
			</ul>
		</c:forEach>
		<br>
	</section>

	<!-- Footer -->
			<%@include file="footer.jsp" %>

	<!-- Placed at the end of the document so the pages load faster -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script>
		window.jQuery
				|| document
						.write('_$tag_______________________________________________$tag_____')
	</script>
	<script src="js/bootstrap.min.js" type="text/javascript"></script>

</body>
</html>