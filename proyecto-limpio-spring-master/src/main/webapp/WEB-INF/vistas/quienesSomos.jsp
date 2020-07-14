<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>¿Quiénes Somos?</title>
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

	<h3 class="display-4">¿Quiénes Somos?</h3>

	<section class="container -sm p-4">
		<hr>
		<h2>Recomida!</h2>
		
		<p>Somos un equipo dedicado a la salud y a la comida, queriendo
			ayudar a cada vez más personas a desentenderse del problema de elegir
			qué comer en cada momento del día.</p>

		<p>Gran parte de la población en la actualidad pone en segundo
			plano lo sano y nutritivo y lo reemplaza por lo rico y rápido.</p>

		<div class="alert alert-danger" role="alert">
			<h4 class="alert-heading">¡Contamos con los mejores
				nutricionistas del país!</h4>
		</div>

		<h4 class="leader">Objetivo</h4>
		<p>En Recomida intentamos junto a un grupo de especialistas en
			nutrición y un team de chefs, que la comida ya no sea un peso, sino
			que sea lo que te ayude a mejorar tu vida y tu cuerpo en tan solo un
			click.</p>
			
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