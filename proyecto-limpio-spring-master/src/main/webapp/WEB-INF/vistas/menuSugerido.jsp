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


	<section class="container p-4">
		<h3 class="text-center">Seleccionar pedido según gustos</h3>
		<form method="POST" action="seleccionarUbicacion">
			<!--  <h3>Usuario: ${usuario.email}</h3>-->

			<span class="h5">Menú 1</span> <input type="radio" name="idComidas"
				value="${idcomidas1}" />


			<ul class="list-group list-group-flush">
				<li class="list-group-item  flex-fill"><c:forEach
						items="${comidas1}" var="comida">

						<ul class="list-unstyled">
							<li class="media"><img src="img/${comida.nombre}.jpg"
								class="mr-3" alt="..." style="width: 170px; height: 100px">
								<div class="media-body">
									<h5 class="text-danger">${comida.tipoHorario}</h5>
									<h6 class="mt-0 mb-1">${comida.nombre}</h6>
									<span>${comida.descripcion}</span>
									<p>Precio: $ ${comida.precio}</p>
								</div></li>
						</ul>
					</c:forEach></li>
			</ul>
			<br> <span class="h5">Menú 2</span> <input type="radio"
				name="idComidas" value="${idcomidas2}" />

			<ul class="list-group list-group-flush">
				<li class="list-group-item  flex-fill"><c:forEach
						items="${comidas2}" var="comida2">

						<ul class="list-unstyled">
							<li class="media"><img src="img/${comida2.nombre}.jpg"
								class="mr-3" alt="..." style="width: 170px; height: 100px">
								<div class="media-body">
									<h5 class="text-danger">${comida2.tipoHorario}</h5>
									<h6 class="mt-0 mb-1">${comida2.nombre}</h6>
									<span>${comida2.descripcion}</span>
									<p>Precio: $ ${comida2.precio}</p>
								</div></li>
						</ul>
					</c:forEach></li>
			</ul>
			<br> <span class="h5">Menú 3</span> <input type="radio"
				name="idComidas" value="${idcomidas3}" />

			<ul class="list-group list-group-flush">
				<li class="list-group-item  flex-fill"><c:forEach
						items="${comidas3}" var="comida3">

						<ul class="list-unstyled">
							<li class="media"><img src="img/${comida3.nombre}.jpg"
								class="mr-3" alt="..." style="width: 170px; height: 100px">
								<div class="media-body">
									<h5 class="text-danger">${comida3.tipoHorario}</h5>
									<h6 class="mt-0 mb-1">${comida3.nombre}</h6>
									<span>${comida3.descripcion}</span>
									<p>Precio: $ ${comida3.precio}</p>
								</div></li>
						</ul>
					</c:forEach></li>
			</ul>
			<input type="hidden" name="id" id="id" value="${posicion.id}" /> <input
				type="hidden" name="latitude" id="latitude"
				value="${posicion.latitude}" /> <input type="hidden"
				name="longitude" id="longitude" value="${posicion.longitude}" /> <input
				type="hidden" name="nombre" id="nombre" value="${posicion.nombre}" />

			<button type="submit" class="btn btn-success mt-5">Confirmar

				gustos</button>
		</form>
	</section>

	<br>

	<section>
		<h3 class="text-center">También podes ver.. ¡Las comidas
			adaptadas a tus gustos más pedidas!</h3>
		<br>
		<div class="container -sm p-2">
			<ul class="list-unstyled">
				<li class="media"><c:forEach items="${comidasmaspedidas}"
						var="comida">
						<img src="img/${comida.nombre}.jpg" class="mr-3" alt="..."
							style="width: 140px; height: 100px; border: 1px black">
						<div class="media-body">
							<h5 class="mt-0 mb-1">${comida.nombre}</h5>
							<br> <a
								href="mostrarComidasMasVistasyPedidas?id=${comida.id}"
								class="btn btn-primary" role="button" aria-pressed="false">Ver
								detalle</a>
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