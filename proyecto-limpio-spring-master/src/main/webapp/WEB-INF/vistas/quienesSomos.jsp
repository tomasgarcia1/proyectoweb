<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Quiénes Somos</title>
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

			<a class="navbar-brand" href="home">RECOMIDA</a>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item"><a class="nav-link" href="home">Home</a></li>
					<li class="nav-item"><a class="nav-link" href="suscripciones">Suscripciones</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<h3 class="display-4 text-center p-3">¿Quiénes Somos?</h3>

	<section class="container -sm mb-5">
		<hr>
		<blockquote class="blockquote">
			<p>Somos un equipo dedicado a la salud y a la comida, queriendo
				ayudar a cada vez más personas a desentenderse del problema de
				elegir qué comer en cada momento del día.</p>
		</blockquote>

		<div class="container" style="margin-top: 6">
			<div id="carouselExampleSlidesOnly" class="carousel slide"
				data-ride="carousel">
				<div class="carousel-inner" style="height: 35rem;">
					<div class="carousel-item active">
						<img class="d-block w-100" src="img/comidaSlide.jpeg"
							alt="First slide">
					</div>
					<div class="carousel-item">
						<img class="d-block w-100" src="img/nutricionistas.jpg"
							alt="Second slide">
					</div>
					<div class="carousel-item">
						<img class="d-block w-100" src="img/chefs.jpg" alt="Third slide">
					</div>
				</div>
			</div>
		</div>
		<br>
		<h5 class="text-danger">Historia</h5>
		<hr>
		<blockquote class="blockquote">
			<p>Nos hemos dado cuenta que, gran parte de la población en la
				actualidad pone en segundo plano lo sano y nutritivo y lo reemplaza
				por lo rico y rápido. Por eso mismo, hace 4 meses decidimos hacer
				este gran emprendimiento. Para demostrar que mis neuronas ya no
				pueden redactar esto, si alguien lo sigue se lo agradecería.</p>
		</blockquote>

		<h5 class="text-danger">Objetivo</h5>
		<hr>
		<blockquote class="blockquote">
			<p class="mb-2">En Recomida intentamos junto a un grupo de
				especialistas en nutrición y un team de chefs, que la comida ya no
				sea un peso, sino que sea lo que te ayude a mejorar tu vida y tu
				cuerpo en tan solo un click.</p>
		</blockquote>

		<a href="registro" class="btn btn-danger btn-lg btn-block">Quiero
			ser parte de Recomida!</a>

	</section>
	<!-- Footer -->
	<%@include file="footer.jsp"%>

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