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
		<div class="row">
			<div class="col">
				<h1 class="display-4">Su sucripción fue cancelada</h1>
				<p class="lead">Gracias por haber confiado en <span class="text-danger">Recomida</span>.</p>
			<hr/>
				<a href="suscripciones"  class="btn btn-outline-danger mt-2 float-right">Adquirir otra suscripción</a>
				<a href="interno"  class="btn btn-outline-danger mt-2">Volver</a>
			</div>
		
			<div class="col">
				<img src="img/chau.jpg" class="w-75 mx-auto d-block my-5" style="opacity:0.7">
			</div>
		</div>
	</section>
	
	<!-- Footer -->
	<%@include file="footer.jsp"%>

	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	
</body>
</html>