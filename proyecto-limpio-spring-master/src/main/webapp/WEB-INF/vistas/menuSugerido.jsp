<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
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


	<section class="container-sm p-4">
		<h2 class="text-center">Seleccionar pedido según gustos</h2>
		<form method="POST" action="seleccionarUbicacion">

			<h3 class="text-danger">MENÚ 1</h3>
			<span class="h5"> Seleccionar menú </span> <input type="radio"
				name="idComidas" value="${idcomidas1}" />

			<ul class="list-group list-group-horizontal-sm">
				<c:forEach items="${comidas1}" var="comida">
					<li class="list-group-item flex-fill"><img
						src="img/${comida.nombre}.jpg"
						class="rounded mx-auto d-block mr-3 mb-2" alt="..."
						style="width: 300px; height: 190px">
						<h4 class="text-danger">${comida.tipoHorario}:</h4>
						<h5>${comida.nombre}</h5>
						<p>${comida.descripcion}</p>
						<h6>
							Precio: <span class="text-danger h5">$ ${comida.precio}</span>
						</h6></li>
				</c:forEach>
			</ul>

			<br>

			<h3 class="text-danger">MENÚ 2</h3>
			<span class="h5"> Seleccionar menú </span> <input type="radio"
				name="idComidas" value="${idcomidas2}" />

			<ul class="list-group list-group-horizontal-sm">
				<c:forEach items="${comidas2}" var="comida2">
					<li class="list-group-item flex-fill"><img
						src="img/${comida2.nombre}.jpg"
						class="rounded mx-auto d-block mr-3 mb-2" alt="..."
						style="width: 300px; height: 190px">
						<h4 class="text-danger">${comida2.tipoHorario}:</h4>
						<h5>${comida2.nombre}</h5>
						<p>${comida2.descripcion}</p>
						<h6>
							Precio: <span class="text-danger h5">$ ${comida2.precio}</span>
						</h6></li>
				</c:forEach>
			</ul>

			<br>

			<h3 class="text-danger">MENÚ 3</h3>
			<span class="h5"> Seleccionar menú </span> <input type="radio"
				name="idComidas" value="${idcomidas3}" />

			<ul class="list-group list-group-horizontal-sm">
				<c:forEach items="${comidas3}" var="comida3">
					<li class="list-group-item flex-fill"><img
						src="img/${comida3.nombre}.jpg"
						class="rounded mx-auto d-block mr-3 mb-2" alt="..."
						style="width: 300px; height: 190px">
						<h4 class="text-danger">${comida3.tipoHorario}:</h4>
						<h5>${comida3.nombre}</h5>
						<p>${comida3.descripcion}</p>
						<h6>
							Precio: <span class="text-danger h5">$ ${comida3.precio}</span>
						</h6></li>
				</c:forEach>
			</ul>

			<input type="hidden" name="id" id="id" value="${posicion.id}" /> <input
				type="hidden" name="latitude" id="latitude"
				value="${posicion.latitude}" /> <input type="hidden"
				name="longitude" id="longitude" value="${posicion.longitude}" /> <input
				type="hidden" name="nombre" id="nombre" value="${posicion.nombre}" />

			<button type="submit" class="btn btn-danger btn-block mt-5 ">Confirmar</button>
		</form>
	</section>

	<br>


	<section class="container -sm p-2">

		<hr class="my-2">
		<br>
		<h4 class="text-center">Tambien podes ver.. ¡Las comidas
			adaptadas a tus gustos más pedidas!</h4>
		<br>
		<div class="container -sm p-2">
			<ul class="list-unstyled">
				<li class="media"><c:forEach items="${comidasmaspedidas}"
						var="comida">
						<img src="img/${comida.nombre}.jpg" class="mr-3" alt="..."
							style="width: 140px; height: 100px; border: 1px black">
						<div class="media-body">
							<h5 class="mt-0 mb-1 text-dark">${comida.nombre}</h5>
							<br> <a
								href="mostrarComidasVistasyPedidas?id=${comida.id}"
								class="btn btn-outline-danger" role="button"
								aria-pressed="false">Ver detalle</a>
						</div>
					</c:forEach></li>
			</ul>
		</div>

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