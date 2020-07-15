<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Detalle Moldes Cupon</title>
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

	<h1 class="text-center mb-4">Molde Cupon: ${molde.id}</h1>

	<section class="container -sm">
		<h3 class="blockquote">Información</h3>
		<hr>
		<ul>
			<li>
				<h5>Valor: $ ${molde.valor}</h5>
			</li>
			<li>
				<h5>Estado:</h5> <c:if test="${molde.estado == true}">
					<h4 class="text-success">HABILITADO</h4>
				</c:if> <c:if test="${molde.estado == false}">
					<h4 class="text-danger">DESHABILITADO</h4>
				</c:if>
			</li>
		</ul>

		<h5 class="blockquote text-center">Modifique el Estado del Cupon</h5>
		<form action="actualizarMoldeCupon" method="POST">
			<div class="input-group mb-3">
				<div class="input-group-prepend">
					<input name="id" type="hidden" value="${molde.id}">
				</div>
				<select class="custom-select" id="inputGroupSelect01" name="estado"
					required="required">
					<option>HABILITADO</option>
					<option>DESHABILITADO</option>
				</select>
				<button class="btn btn-outline-success">Actualizar</button>
			</div>
		</form>

		<a href="moldes" class="btn btn-danger mt-3 mb-3">Atrás</a>
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
</html>