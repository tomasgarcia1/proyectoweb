<%@ page language="java" contentType="text/html;charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Agregar Restriccion</title>
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

	<div class="container mt-5 mb-3">
		<h3>Seleccione comida</h3>
	</div>
	<div class="container mb-4">
		<form:form action="seleccionarRestriccionDeComida" method="POST"
			modelAttribute="comida">

			<h6>Podes ver las restricciones que ya posee cada comida clickeando en el botón con su nombre</h6>
			<c:forEach items="${comidas}" var="c">
			<div class="btn-group dropup">
			  <button type="button" class="btn btn-danger dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
			    ${c.nombre}
			  </button>
			  <div class="dropdown-menu">
			  	<c:forEach items="${c.restricciones}" var="res">
					<a class="dropdown-item">${res.nombre}</a>
				</c:forEach>
			  </div>
			</div>
			</c:forEach>

			
			<div class="form-group mt-4">
				<label for="th" class="h6">Comida a modificar</label>
				<form:select path="id" class="form-control" required="required">
					<c:forEach items="${comidas}" var="th">
						<option value="${th.id}">${th.nombre}</option>
					</c:forEach>
				</form:select>
			</div>

			<button type="submit" class="btn btn-danger mt-4">Aceptar</button>

		</form:form>
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
	 
</body>
</html>