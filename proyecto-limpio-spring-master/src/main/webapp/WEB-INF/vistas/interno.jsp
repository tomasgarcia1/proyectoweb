<!DOCTYPE html>
<html lang="en">
<head>
<!-- Required meta tags -->
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
<%@include file="header.jsp" %>

	<section id="bienvenido" class="my-1">
		<div class="container">
			<div class="jumbotron bg-white">
				<h1 class="display-4">Bienvenido a Recomida!</h1>
				<p class="lead">La unica aplicacion argentina que te recomienda
					comidita, si te gusta comer animales como un animal o si comes
					pasto tipo vaca</p>
				<hr class="my-2">
				<p>Antes de realizar un pedido, debe informarnos donde se
					encuentra, una vez que realiza esto, puede realizar su pedido según
					sus gustos o calorias</p>
				<a class="btn btn-outline-danger btn-lg" href="elegirPedido" role="button">Realizar pedido</a> 

					class="btn btn-outline-danger btn-lg" href="suscripciones"
					role="button">Suscripciones</a>
			</div>
		</div>
	</section>

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
							<p class="card-text">Ni idea que iria aca, vos haces como que
								escribis una banda, te llevas el parrafo al lugar mas recondito
								de tu casa y sos inimptuable hermano.</p>
							<a href="#" class="btn btn-outline-light">Ver comidas</a>
						</div>
					</div>
				</div>

				<div class="col-md-4">
					<div class="card" style="width: 18rem;">
						<img src="img/f2.jpg" class="card-img-top" alt="...">
						<div class="card-body bg-danger text-light">
							<h5 class="card-title">Comidas vegetarianas</h5>
							<p class="card-text">Ni idea que iria aca, vos haces como que
								escribis una banda, te llevas el parrafo al lugar mas recondito
								de tu casa y sos inimptuable hermano.</p>
							<a href="#" class="btn btn-outline-light">Ver comidas</a>
						</div>
					</div>
				</div>

				<div class="col-md-4">
					<div class="card" style="width: 18rem;">
						<img src="img/f3.jpg" class="card-img-top" alt="...">
						<div class="card-body bg-danger text-light">
							<h5 class="card-title">Comidas veganas</h5>
							<p class="card-text">Ni idea que iria aca, vos haces como que
								escribis una banda, te llevas el parrafo al lugar mas recondito
								de tu casa y sos inimptuable hermano.</p>
							<a href="#" class="btn btn-outline-light">Ver comidas</a>
						</div>
					</div>
				</div>

			</div>
		</div>
	</section>

	<!-- Footer -->
	<%@include file="footer.jsp" %>
	
	<!-- Footer -->


	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
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