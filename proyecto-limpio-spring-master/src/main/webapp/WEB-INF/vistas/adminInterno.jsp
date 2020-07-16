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

	<section id="bienvenido" class="my-1">
		<div class="container">
			<div class="row">
				<div class="col-8">
					<div class="jumbotron bg-white">
						<h1 class="display-4">Bienvenido! Gracias por trabajar en
							Recomida</h1>
						<hr class="my-4">
						<a class="btn btn-outline-danger btn-lg" href="agregarComida"
							role="button">Agregar nueva comida</a> <a
							class="btn btn-outline-danger btn-lg"
							href="buscarComidaPorHorario" role="button">Agregar nueva
							restricción</a> 
							<br>
							<a class="btn btn-outline-danger btn-lg mt-3"
							href="crearMoldeCupon" role="button">Agregar Cupon</a> <br>
							<a class="btn btn-outline-danger btn-lg mt-3"
							href="verpedidos" role="button">Ver pedidos</a> <a
							class="btn btn-outline-danger btn-lg mt-3" href="moldes" role="button">Ver
							Cupones</a>
					</div>
				</div>
				<div class="col-2">
					<img src="img/admin.jpg" class="w-150">
				</div>
			</div>
		</div>
	</section>

	<%@include file="footer.jsp"%>
	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	
</body>
</html>