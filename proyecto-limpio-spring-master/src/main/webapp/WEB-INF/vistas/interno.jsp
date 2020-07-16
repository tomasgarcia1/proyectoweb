
<!DOCTYPE html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="ISO-8859-1">

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">

<title>Recomida!</title>
</head>
<body>

	<%@include file="header.jsp"%>

	<section id="bienvenido" class="my-1">
		<div class="container">
			<div class="jumbotron bg-white">
				<h1 class="display-4">Bienvenido a Recomida!</h1>
			</div>
		</div>
	</section>
	<div class="container" style="margin-top: -4rem">
		<div id="carouselExampleSlidesOnly" class="carousel slide"
			data-ride="carousel">
			<div class="carousel-inner" style="height: 30rem;">
				<div class="carousel-item active">
					<img class="d-block w-100" src="img/f1.jpg" alt="First slide">
				</div>
				<div class="carousel-item">
					<img class="d-block w-100" src="img/f2.jpg" alt="Second slide">
				</div>
				<div class="carousel-item">
					<img class="d-block w-100" src="img/menuSugerido.jpg"
						alt="Third slide">
				</div>
				<div class="carousel-caption d-none d-md-block">
					<h5 class="bg-danger">
						Hacer un <a href="elegirPedido" style="color: lawngreen;">pedido</a>
						con la comida que más te gusta nunca había sido tan fácil
					</h5>
				</div>
			</div>
		</div>
	</div>

	<div class="container mt-3">
		<div class="alert alert-warning alert-dismissible fade show"
			role="alert">
			<strong>¿No estás suscrito?</strong> No dejes pasar el tiempo y <a
				href="suscripciones">conocé ya </a>nuestras suscripciones.
		</div>
	</div>

	<section id="info" class="my-4">
		<div class="container">
			<h3 class="text-center">Elegí las comidas que mas se adapten a
				vos</h3>
			<div class="row my-5">

				<div class="col-md-4">
					<div class="card" style="width: 18rem;">
						<img src="img/f1.jpg" class="card-img-top" alt="...">
						<div class="card-body bg-danger text-light">
							<h5 class="card-title">Comidas balanceadas</h5>
							<p class="card-text">Los chefs de Recomida con la asesoría
								del team de nutricionistas van a armar comidas solo para vos!
								Con un amplio abanico de ingredientes y especias no vas a
								extrañar más los "permitidos".</p>
							<a href="comidas?nombre=variado" class="btn btn-outline-light">Ver
								comidas</a>
						</div>
					</div>
				</div>

				<div class="col-md-4">
					<div class="card" style="width: 18rem;">
						<img src="img/f2.jpg" class="card-img-top" alt="...">
						<div class="card-body bg-danger text-light">
							<h5 class="card-title">Comidas vegetarianas</h5>
							<p class="card-text">En Recomida entendemos el movimiento por
								la liberación animal, asique incluímos infinidad de comidas y
								desayunos deliciosos que no tienen nada que envidiarle a la
								carne!</p>
							<a href="comidas?nombre=vegetariano"
								class="btn btn-outline-light">Ver comidas</a>
						</div>
					</div>
				</div>

				<div class="col-md-4">
					<div class="card" style="width: 18rem;">
						<img src="img/f3.jpg" class="card-img-top" alt="...">
						<div class="card-body bg-danger text-light">
							<h5 class="card-title">Comidas veganas</h5>
							<p class="card-text">¿Quién dijo que los alimentos sin
								ingredientes de origen animal eran feos? En Recomida te
								demostramos que se puede comer rico, saludable, variado y 100%
								vegetal, animate!</p>
							<a href="comidas?nombre=vegano" class="btn btn-outline-light">Ver
								comidas</a>
						</div>
					</div>
				</div>

			</div>
		</div>
	</section>

	<!-- Footer -->
	<%@include file="footer.jsp"%>

	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	
</body>
</html>