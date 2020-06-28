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

	<nav class="navbar navbar-expand-lg navbar-dark bg-danger">
		<div class="container">

			<a class="navbar-brand" href="#">RECOMIDA</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>

			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item active"><a class="nav-link" href="#">Inicio</a>
					</li>
					<li class="nav-item"><a class="nav-link" href="cerrarSesion">Cerrar
							Sesión</a></li>
					<li class="nav-item"><a class="nav-link" href="#">Menú de
							comidas</a></li>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
						role="button" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="false"> Mi cuenta </a>
						<div class="dropdown-menu" aria-labelledby="navbarDropdown">
							<a class="dropdown-item" href="mostrarRestriccionesDeUsuario">Mis
								restricciones</a>
							<div class="dropdown-divider"></div>
							<a class="dropdown-item" href="mispedidos">Mis pedidos</a>
							<div class="dropdown-divider"></div>
							<a class="dropdown-item" href="#">Imc</a>
						</div></li>

				</ul>
			</div>
		</div>
	</nav>

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
	<footer class="page-footer font-small unique-color-dark">

		<div class="bg-danger">
			<div class="container">

				<!-- Grid row-->
				<div class="row py-4 d-flex align-items-center">

					<!-- Grid column -->
					<div
						class="col-md-12 col-lg-12 text-center text-md-left mb-4 mb-md-0">
						<h6 class="mb-0 text-center text-white">Get connected with us
							on social networks!</h6>
					</div>
					<!-- Grid column -->


				</div>
				<!-- Grid column -->

			</div>
			<!-- Grid row-->

		</div>
		</div>


		<!-- Footer Links -->
		<div class="container text-center text-md-left mt-5">

			<!-- Grid row -->
			<div class="row mt-3">

				<!-- Grid column -->
				<div class="col-md-3 col-lg-4 col-xl-3 mx-auto mb-4">

					<!-- Content -->
					<h6 class="text-uppercase font-weight-bold">Recomida</h6>
					<hr class="deep-purple accent-2 mb-4 mt-0 d-inline-block mx-auto"
						style="width: 60px;">
					<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.</p>

				</div>
				<!-- Grid column -->

				<!-- Grid column -->
				<div class="col-md-2 col-lg-2 col-xl-2 mx-auto mb-4">

					<!-- Links -->
					<h6 class="text-uppercase font-weight-bold">Products</h6>
					<hr class="deep-purple accent-2 mb-4 mt-0 d-inline-block mx-auto"
						style="width: 60px;">
					<p>
						<a href="#!">Wea1</a>
					</p>
					<p>
						<a href="#!">Wea2</a>
					</p>
					<p>
						<a href="#!">Wea3</a>
					</p>
					<p>
						<a href="#!">Wea4</a>
					</p>

				</div>
				<!-- Grid column -->

				<!-- Grid column -->
				<div class="col-md-3 col-lg-2 col-xl-2 mx-auto mb-4">

					<!-- Links -->
					<h6 class="text-uppercase font-weight-bold">Useful links</h6>
					<hr class="deep-purple accent-2 mb-4 mt-0 d-inline-block mx-auto"
						style="width: 60px;">
					<p>
						<a href="#!">Tu cuenta</a>
					</p>
					<p>
						<a href="#!">Tu suscripcion</a>
					</p>
					<p>
						<a href="#!">Otra cosa</a>
					</p>
					<p>
						<a href="#!">Help</a>
					</p>

				</div>
				<!-- Grid column -->

				<!-- Grid column -->
				<div class="col-md-4 col-lg-3 col-xl-3 mx-auto mb-md-0 mb-4">

					<!-- Links -->
					<h6 class="text-uppercase font-weight-bold">Contacto</h6>
					<hr class="deep-purple accent-2 mb-4 mt-0 d-inline-block mx-auto"
						style="width: 60px;">
					<p>
						<i class="fas fa-home mr-3"></i> Buenos Aires, Argentina
					</p>
					<p>
						<i class="fas fa-envelope mr-3"></i> info@lala.com
					</p>
					<p>
						<i class="fas fa-phone mr-3"></i> + 011 7777 6666
					</p>
					<p>
						<i class="fas fa-print mr-3"></i> + 011 2424 3332
					</p>

				</div>
				<!-- Grid column -->

			</div>
			<!-- Grid row -->

		</div>
		<!-- Footer Links -->

	</footer>
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