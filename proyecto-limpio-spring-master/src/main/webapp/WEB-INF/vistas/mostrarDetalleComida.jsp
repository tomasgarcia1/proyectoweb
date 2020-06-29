<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Detalle | ${comida.nombre}</title>
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
		<form method="POST" action="seleccionarUbicacionUnicaComida">
		<ul class="list-group list-group-horizontal-sm">
			<li class="list-group-item"><img src="img/${comida.nombre}.jpg"
				alt="..." style="width: 650px; height: 420px"></li>
			<li>
			<li class="list-group-item flex-fill">
				<h3 class="text-center text-danger">${comida.nombre}</h3>
				<hr class="my-2">
				<p class="lead">${comida.descripcion}</p>
				<h1>$ ${comida.precio}</h1> <cite title="Source Title">${comida.calorias}
					kcal</cite> <br>
				<h4 class="text-danger">${comida.tipoHorario}</h4> <span
				class="badge badge-primary">Envío con normalidad</span> <br> <br>
				<p class="blockquote">Aceptamos todos los medios de pago</p> <img
				src="img/pay.png" style="width: 40px; height: 40px;" alt="tarjetas">
				<img src="img/wallet.png" style="width: 40px; height: 40px;"
				alt="efectivo"> <img src="img/bank.png"
				style="width: 40px; height: 40px;" alt="banco"> <br> <br>
				
				
				<input type="hidden" name="id" id="id" value="${posicion.id}"/> <input
				type="hidden" name="latitude" id="latitude"
				value="${posicion.latitude}" /> <input type="hidden"
				name="longitude" id="longitude" value="${posicion.longitude}" /> <input
				type="hidden" name="nombre" id="nombre" value="${posicion.nombre}" />
				<input type="hidden" name="idComidas" value="${comida.id}" />
				<button type="submit" class="btn btn-danger">Comprar</button>
			</li>
		</ul>
		</form>
		<br>
		<section class="container -sm p-2">
			<h3 class="text-center">También podría interesarte las comidas
				más Vistas..</h3>
			<hr class="my-2">
			<div class="container -sm p-2">
				<ul class="list-group list-group-flush">
					<li class="list-group-item  flex-fill"><c:forEach
							items="${comidasVistas}" var="comida1">

							<ul class="list-unstyled">
								<li class="media"><img src="img/${comida1.nombre}.jpg"
									class="rounded float-left mr-3" alt="..."
									style="width: 170px; height: 100px">
									<div class="media-body">
										<h5 class="mb-1 text-dark">${comida1.nombre}</h5>
										<small class="blockquote">${comida1.contador} views</small> <br>
										<a href="mostrarComidasMasVistasyPedidas?id=${comida.id}"
											class="btn btn-outline-danger" role="button"
											aria-pressed="false">Ver detalle</a>
									</div></li>
							</ul>
							<br>
						</c:forEach></li>
				</ul>
			</div>

			<h3 class="text-center">¡Las comidas más Pedidas!</h3>
			<hr class="my-2">
			<br>
			<div class="container -sm p-2">
				<ul class="list-unstyled">
					<li class="media"><c:forEach items="${comidasPedidas}"
							var="comida">
							<img src="img/${comida.nombre}.jpg"
								class=" rounded float-left mr-3" alt="..."
								style="width: 140px; height: 100px; border: 1px black">
							<div class="media-body">
								<h5 class="mt-0 mb-1 text-dark">${comida.nombre}</h5>
								<p></p>
								<a
									href="mostrarComidasMasVistasyPedidas?id=${comida.id}"
									class="btn btn-outline-danger" role="button"
									aria-pressed="false">Ver detalle</a>
							</div>
						</c:forEach></li>
				</ul>
			</div>
		</section>

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