<%@ page language="java" contentType="text/html;charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Agregar Restriccion</title>
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

			<a class="navbar-brand" href="adminInterno">RECOMIDA</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>

			<div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav ml-auto">
            <li class="nav-item active">
                <a class="nav-link" href="home">Inicio</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="cerrarSesion">Cerrar Sesión</a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Mi cuenta
              </a>
              <div class="dropdown-menu" aria-labelledby="navbarDropdown">
				<a class="dropdown-item" href="mostrarRestriccionesDeUsuario">Mis restricciones</a>                
				<div class="dropdown-divider"></div>
                <a class="dropdown-item" href="#">Mis pedidos</a>
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="#">Imc</a>
            </div>
        </li>
           
    </ul>
</div>
		</div>
	</nav>

	<div class="container mt-5 mb-5">
		<h3>Seleccione comida</h3>
	</div>
	<div class="container mb-4">
		<form:form action="seleccionarRestriccionDeComida" method="POST"
			modelAttribute="comida">

			<h6>Podes ver las restricciones que ya posee cada comida clickeando en el botón con su nombre</h6>
			<c:forEach items="${comidas}" var="c">
			<div class="btn-group dropup">
			  <button type="button" class="btn btn-danger dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
			    ${c.nombre}
			  </button>
			  <div class="dropdown-menu">
			  	<c:forEach items="${c.restricciones}" var="res">
					<a class="dropdown-item">${res.nombre}</a>
				</c:forEach>
			  </div>
			</div>
			</c:forEach>

			
			<div class="form-group mt-4">
				<label for="th" class="h6">Comida a modificar</label>
				<form:select path="nombre" class="form-control" required="required">
					<c:forEach items="${comidas}" var="th">
						<option value="${th.id}">${th.nombre}</option>
					</c:forEach>
				</form:select>
			</div>

			<button type="submit" class="btn btn-danger mt-4">Aceptar</button>

		</form:form>
	</div>

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
						.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')
	</script>
	  <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
  		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>
  		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js" integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1" crossorigin="anonymous"></script>
	
</body>
</html>