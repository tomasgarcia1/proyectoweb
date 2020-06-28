<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Pedido</title>
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

	<div class="container mb-5 mt-5">

		<div class="container mb-5 mt-5 clearfix">
			<p class="h3">Excelente! Tu pedido ya se encuentra en <span class="text-lowercase">${pedido.estado}</span></p>
			<p class="h5">Nuestros chefs se están poniendo en marcha para preparar tu menú a tiempo. Gracias por elegir 
			<span class="text-danger">Recomida</span>, buen provecho!</p>
			<img src="img/gracias2.jpg" class="w-50 mx-auto d-block" style="opacity:0.7">
			
		</div>
		
		<a class="btn btn-danger btn-block" href="interno" role="button">Volver al home</a>
	</div>
		
		
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