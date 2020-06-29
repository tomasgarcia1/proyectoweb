<%@ page language="java" contentType="text/html;charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Agregar Comida</title>
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

	<div class="container mt-5 mb-5">
		<h3>Formulario de comida</h3>
	</div>
	<div class="container">
		<form:form action="agregarComidaValidacion" method="POST"
			modelAttribute="comida">

				<div class="form-group">
					<label for="nombre">Nombre</label>
					<form:input path="nombre" id="nombre" type="text"
						class="form-control" required="required"/>
				</div>

				<div class="form-group">
					<label for="descripcion">Descripcion</label>
					<form:textarea path="descripcion" rows="4" cols="50" id="descripcion"
						class="form-control" required="required"/>
				</div>

			<div class="form-group">
				<label for="calorias">Calorias</label>
				<form:input path="calorias" type="number" class="form-control"
					id="calorias" required="required"/>
			</div>

			<div class="form-group">
				<label for="precio">Precio</label>
				<form:input path="precio" type="number" class="form-control" id="precio" required="required"/>
			</div>

			<div class="form-group">
				<label for="th">Tipo de Comida</label>
				<form:select path="tipoHorario" class="form-control" required="required">
					<c:forEach items="${tipoHorario}" var="th">
						<option value="${th}">${th}</option>
					</c:forEach>
				</form:select>
			</div>

			<button type="submit" class="btn btn-danger">Crear Comida</button>

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
	<script src="js/bootstrap.min.js" type="text/javascript"></script>

</body>
</html>