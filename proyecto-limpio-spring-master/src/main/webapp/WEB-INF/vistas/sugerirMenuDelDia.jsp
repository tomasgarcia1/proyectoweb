<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">

<title>Recomida</title>

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
</head>
<body>
	<%@include file="header.jsp" %>

	<section class="container-sm p-4">
		<h3 class="text-center">Seleccione el menú del que quiere hacer
			un pedido</h3>
		<form method="POST" action="seleccionarUbicacion">
			<h2 class="text-danger">MENÚ 1</h2>
			<span class="h3"> Seleccionar menú </span> <input type="radio"
				name="idComidas" value="${idcomidas1}" />
			<ul class="list-group list-group-horizontal-sm">
				<c:forEach items="${menu1}" var="comida">
					<li class="list-group-item flex-fill">
					<img src="img/${comida.nombre}.jpg"
						class="rounded mx-auto d-block mr-3 mb-2" alt="..."
						style="width: 300px; height: 190px">
						<h4 class="text-danger">${comida.tipoHorario}:</h4>
						<h5>${comida.nombre}</h5>
						<p>${comida.descripcion}</p>
						<h6>
							Precio: <span class="text-danger h5">$ ${comida.precio}</span>
						</h6>
					</li>
				</c:forEach>
			</ul>

			<br>
			<h2 class="text-danger">MENÚ 2</h2>
			<span class="h3"> Seleccionar menú </span> <input type="radio"
				name="idComidas" value="${idcomidas2}" />
			<ul class="list-group list-group-horizontal-sm">
				<c:forEach items="${menu2}" var="comida2">
					<li class="list-group-item flex-fill">
					<img src="img/${comida2.nombre}.jpg"
						class="rounded mx-auto d-block mr-3 mb-2" alt="..."
						style="width: 300px; height: 190px">
						<h4 class="text-danger">${comida2.tipoHorario}:</h4>
						<h5>${comida2.nombre}</h5>
						<p>${comida2.descripcion}</p>
						<h6>
							Precio: <span class="text-danger h5">$ ${comida2.precio}</span>
						</h6>
					</li>
				</c:forEach>
			</ul>

			<br>
			<h2 class="text-danger">MENÚ 3</h2>
			<span class="h3"> Seleccionar menú </span> <input type="radio"
				name="idComidas" value="${idcomidas3}" />
			<ul class="list-group list-group-horizontal-sm">
				<c:forEach items="${menu3}" var="comida3">
					<li class="list-group-item flex-fill">
					<img src="img/${comida3.nombre}.jpg"
						class="rounded mx-auto d-block mr-3 mb-2" alt="..."
						style="width: 300px; height: 190px">
						<h4 class="text-danger">${comida3.tipoHorario}:</h4>
						<h5>${comida3.nombre}</h5>
						<p>${comida3.descripcion}</p>
						<h6>
							Precio: <span class="text-danger h5">$ ${comida3.precio}</span>
						</h6>
					</li>
				</c:forEach>
			</ul>
			<!--  <input type="hidden" name="id" id="id" value="${posicion.id}" /> <input
				type="hidden" name="latitude" id="latitude"
				value="${posicion.latitude}" /> 
				<input type="hidden" name="longitude" id="longitude" value="${posicion.longitude}" />
				<input type="hidden" name="nombre" id="nombre" value="${posicion.nombre}"/>
				-->

			<c:if test="${not empty error}">
				<h4>
					<span class="text-danger mt-5">${error}</span>
				</h4>
				<br>
			</c:if>
			<c:if test="${empty error}">
				<input type="submit" class="btn btn-danger mt-5 btn-block" value="Confirmar">
				<br>
			</c:if>

			<br>
		</form>
	</section>

	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
		integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
		integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
		integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
		crossorigin="anonymous"></script>

</body>
</html>