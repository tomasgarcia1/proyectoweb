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
	<%@include file="header.jsp"%>


	<section class="container mt-5 mb-5 clearfix">
		<h1 class="display-3 text-center">Suscripciones</h1>
		<p class="lead text-center mb-5">
			Obtener una suscripción en <span class="text-danger">Recomida</span>
			te va a permitir, por un bajo precio, disfrutar de todos los
			servicios que tenemos para vos. Solo los clientes suscritos tienen
			acceso a la sugerencia de menús distintos cada día según sus
			restricciones de salud, gustos, calorías diarias recomendadas y
			calculadas según tú estilo de vida y tus caracteristicas físicas.
			¿Qué estas esperando para obtener beneficios únicos?
		</p>

		<div class="card-deck">
			<div class="card text-center">
				<div class="card-header h2 text-success">Mensual</div>
				<div class="card-body">
					<div class="mb-2">
						<h1 class="h4">
							$ <span class="display-3">75,00</span>/mes
						</h1>
						<h6>Pesos Argentinos</h6>
						<hr />
					</div>
					<p class="card-text">
						La suscripción mensual se le cobrará una sola vez y deberá ser
						renovada al pasar la fecha de vencimiento.<br> Los pedidos no
						estan incluídos en la suscripción
					</p>
					<form action="obtenerSuscripcion" method="get">
						<input type="hidden" value="1" name="tipo"> <input
							type="submit" class="btn btn-success" value="Suscribirme">
					</form>
				</div>
			</div>

			<div class="card text-center">
				<div class="card-header h2 text-info">Semestral</div>
				<div class="card-body">
					<div class="mb-2">
						<h1 class="h4">
							$ <span class="display-3">65,00</span>/mes
						</h1>
						<h6>Pesos Argentinos</h6>
						<hr />
					</div>
					<p class="card-text">
						La suscripción semestral descontará el precio mensual al momento
						de ser adquirida y, cada vez que se cumpla un mes del pago
						anterior, durante los seis meses activos. Al pasar la fecha de
						vencimiento deberá renovar la suscripción.<br> Los pedidos no
						estan incluídos en la suscripción.
					</p>
					<form action="obtenerSuscripcion" method="get">
						<input type="hidden" value="2" name="tipo"> <input
							type="submit" class="btn btn-info" value="Suscribirme">
					</form>
				</div>
			</div>

			<div class="card text-center">
				<div class="card-header h2 text-danger">Anual</div>
				<div class="card-body">
					<div class="mb-2">
						<h1 class="h4">
							$ <span class="display-3">50,00</span>/mes
						</h1>
						<h6>Pesos Argentinos</h6>
						<hr />
					</div>
					<p class="card-text">
						La suscripción anual descontará el precio mensual al momento de
						ser adquirida y, cada vez que se cumpla un mes del pago anterior,
						durante los doce meses activos. Al pasar la fecha de vencimiento
						deberá renovar la suscripción.<br> Los pedidos no estan
						incluídos en la suscripción.
					</p>
					<form action="obtenerSuscripcion" method="get">
						<input type="hidden" value="3" name="tipo"> <input
							type="submit" class="btn btn-danger" value="Suscribirme">
					</form>
				</div>
			</div>
		</div>

		<a href="interno" class="btn btn-outline-danger mt-5 btn-lg">Por
			ahora no, gracias</a>
	</section>

	<!-- Footer -->
	<%@include file="footer.jsp"%>

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