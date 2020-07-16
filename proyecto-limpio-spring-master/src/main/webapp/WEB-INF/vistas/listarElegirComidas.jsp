<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Elegir Comida</title>
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

	<div class="container -sm p-3">
		<form method="GET" action="mostrarComidasEleccionUsuario">
			<h3 class="text-center display-4">Comidas adaptadas a tus Gustos</h3>
			<hr>
			<div class="row my-5">
				<c:forEach items="${comidas}" var="comida">
					<div class="col-md-4 mb-4">
						<div class="card" style="width: 21rem;">
							<img class="card-img-top" src="img/${comida.nombre}.jpg"
								alt="Card image cap" style="height: 220px">
							<div class="card-body">
								<h5 class="card-title text-center text-danger">${comida.nombre}</h5>
								<div class="align-center">
									<a href="mostrarComidasVistasyPedidas?id=${comida.id}"
										class="btn btn-outline-secondary btn-block mb-2" role="button"
										aria-pressed="false">Elegir Comida</a>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</form>	
		<a href="elegirPedido" class="btn btn-danger btn-lg btn-block">Atrás</a>
		
	</div>

	<!-- ---<section class="container -sm p-2">
		<h3 class="text-center">¡Las Comidas Más Vistas en base a Tus Gustos!</h3>
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
									<a href="mostrarComidasVistasyPedidas?id=${comida1.id}"
										class="btn btn-outline-danger" role="button"
										aria-pressed="false">Ver detalle</a>
								</div></li>
						</ul>
						<br>
					</c:forEach></li>
			</ul>
		</div>

		<h3 class="text-center">¡Las Comidas Menos Vistas en base a Tus Gustos!</h3>
		<hr class="my-2">
		<div class="container -sm p-2">
			<ul class="list-group list-group-flush">
				<li class="list-group-item  flex-fill"><c:forEach
						items="${comidasMenosVistas}" var="comida2">

						<ul class="list-unstyled">
							<li class="media"><img src="img/${comida2.nombre}.jpg"
								class="rounded float-left mr-3" alt="..."
								style="width: 170px; height: 100px">
								<div class="media-body">
									<h5 class="mb-1 text-dark">${comida2.nombre}</h5>
									<small class="blockquote">${comida2.contador} views</small> <br>
									<a href="mostrarComidasVistasyPedidas?id=${comida2.id}"
										class="btn btn-outline-danger" role="button"
										aria-pressed="false">Ver detalle</a>
								</div></li>
						</ul>
						<br>
					</c:forEach></li>
			</ul>
		</div>
	</section>
	
	 -->
	<!-- Footer -->
	<%@include file="footer.jsp"%>

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