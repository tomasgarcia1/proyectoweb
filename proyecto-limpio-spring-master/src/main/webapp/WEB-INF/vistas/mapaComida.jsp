<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">

<title>Recomida!</title>
<!-- Load Leaflet from CDN-->
<link rel="stylesheet" href="https://unpkg.com/leaflet/dist/leaflet.css" />
<script src="https://unpkg.com/leaflet/dist/leaflet-src.js"></script>

<!-- Load Esri Leaflet from CDN -->
<script src="https://unpkg.com/esri-leaflet"></script>

<!-- Esri Leaflet Geocoder -->
<link rel="stylesheet"
	href="https://unpkg.com/esri-leaflet-geocoder/dist/esri-leaflet-geocoder.css">
<script src="https://unpkg.com/esri-leaflet-geocoder"></script>

<style type="text/css">
#map {
	width: 100%;
	height: 400px;
}
</style>
</head>
<body>

	<%@include file="header.jsp"%>


	<div class="container my-5">
		<h3>Hola! Para realizar un pedido primero ingresa la direccion de
			envio.</h3>

		<c:if test="${not empty posicionesUsuario}">
			<p>Podes elegir una ubicacion elegida anteriormente aqui...</p>
			<div id="select-location">
				<select name="location" id="location">
					<option value="-1">Tus ubicaciones</option>
					<c:forEach items="${posicionesUsuario}" var="posicion">
						<option
							value="${posicion.latitude} , ${posicion.longitude} , ${posicion.id}"
							id="pos">${posicion.nombre}</option>
					</c:forEach>
				</select>

				<button id="nuevaPos" class="btn btn-outline-danger"
					onClick="activar()">Ingresar posicion nueva</button>
			</div>
		</c:if>

		<br>
		<div id="map"></div>

		<form:form action="mostrarDistancia" method="POST"
			modelAttribute="posicion">

			<input type="hidden" name="latitude" id="latitude" value="0" />
			<input type="hidden" name="longitude" id="longitude" value="0" />
			<input type="hidden" name="nombre" id="nombre" value="0" />


			<input type="hidden" name="id" id="id" value="-1" />


			<input type="hidden" name="idComida" value="${idComida}">

			<button type="submit" id="boton" class="btn btn-success my-3">Confirmar
				ubicacion</button>

		</form:form>

	</div>

	<!-- Footer -->
	<%@include file="footer.jsp"%>

	<script src="js/posicionMapa.js"></script>
	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<!-- <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
 -->
</body>
</html>