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
	padding: 5px;
	display: block;
	position: relative;
	margin: 3px;
	overflow: hidden;
	cursor: pointer;
	-webkit-touch-callout: none;
	-webkit-user-select: none;
	-khtml-user-select: none;
	-moz-user-select: none;
	-ms-user-select: none;
	user-select: none;
	cursor: pointer;
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
	<%@include file="headerSinLogin.jsp" %>


	<div class="container mt-5 mb-5">
		<h3>Registro</h3>
	</div>
	<div class="container">
		<c:if test="${not empty errores}">
			<c:forEach items="${errores}" var="error">
				<h5>
					<span class="text-danger">${error}</span>
				</h5>
			</c:forEach>
		</c:if>
		<form:form action="registroValidacion" method="POST"
			modelAttribute="usuario">

			<div class="form-row">
				<div class="form-group col-md-4">
					<label for="username">Nombre de usuario</label>
					<form:input path="username" id="username" type="text"
						class="form-control" />
				</div>
				<div class="form-group col-md-4">
					<label for="email">Email</label>
					<form:input path="email" id="email" type="email"
						class="form-control" />
				</div>

				
			</div>
			<div class="form-row">
			<div class="form-group col-md-4">
					<label for="password">Contrasena (entre 8 y 16 caracteres,
						al menos una mayuscula y un numero)</label>
					<form:input path="password" type="password" id="password"
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

			<div class="container p-5 mb-2 mt-3">
				<h4 class="text-center">Por ultimo, selecciona tus gustos
					alimentarios, ya que en base a ellos te sugerimos la comida.</h4>
				<br>
				<ul>
					<c:forEach items="${restricciones}" var="restriccion">
						<li>${restriccion.nombre}<input type="checkbox"
							id="${restriccion.id}" name="restriccion" id="restriccion"
							value="${restriccion.id}" /> <label for="${restriccion.id}"><img
								src="img/${restriccion.nombre}.jpg" /></label>
						</li>
					</c:forEach>
				</ul>
			</div>
			<div class="container p-0 mb-3 mt-3">
				<a href="home" class="btn btn-outline-danger btn-lg">Ir a Home</a>
				<button type="submit" class="btn btn-primary btn-lg"
					aria-pressed="false">Registrarse</button>
			</div>
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