<%@ page language="java" contentType="text/html;charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<html lang="en">
<title>Recomida</title>
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
		<h3>Editar usuario</h3>
	</div>
	<div class="container">
		<form:form action="editarValidacion" method="POST"
			modelAttribute="usuario">

			<div class="form-row">
			<form:input path="id" hidden= "true" />				
				<div class="form-group col-md-4">
					<label for="username">Nombre de usuario</label>
					<form:input path="username" id="username" type="text"
						class="form-control" />
				</div>
				<div class="form-group col-md-6">
					<label for="email">Email</label>
					<form:input path="email" id="email" type="email"
						class="form-control" value="" />
				</div>
			</div>
			<div class="form-row">
				<div class="form-group col-md-6">
					<label for="password">Contrasena (entre 8 y 16 caracteres,
						al menos una mayuscula y un numero)</label>
					<input name="password" type="password" id="password"
						class="form-control" />
				</div>
			</div>

			<div class="form-group">
				<label for="altura">Altura (en cm)</label>
				<form:input path="altura" type="number" class="form-control"
					id="altura" />
			</div>

			<div class="form-group">
				<label for="peso">Peso (en kg)</label>
				<form:input path="peso" type="number" class="form-control" id="peso" />
			</div>
			<div class="form-group">
				<label for="fec">Fecha de nacimiento</label>
				<form:input path="fechaDeNacimiento" type="date"
					class="form-control" id="fec" />
			</div>

			<div class="form-group">
				<label for="act">Actividad</label>
				<form:select path="actividad" class="form-control">
					<c:forEach items="${actividades}" var="act">
						<option value="${act}">${act}</option>
					</c:forEach>
				</form:select>
			</div>

			<div class="form-group">
				<label for="act">Sexo</label>
				<form:select path="sexo" class="form-control">
					<c:forEach items="${sexos}" var="sexo">
						<option value="${sexo}">${sexo}</option>
					</c:forEach>
				</form:select>
			</div>

			
			<div class="container p-0 mb-3 mt-3">
				<button type="submit" class="btn btn-primary btn-lg"
					aria-pressed="false">Aceptar</button>
			</div>
		</form:form>
		<c:if test="${not empty errores}">
			<c:forEach items="${errores}" var="error">
				<h4>
					<span class="text-danger">${error}</span>
				</h4>
				<br>
			</c:forEach>
		</c:if>
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