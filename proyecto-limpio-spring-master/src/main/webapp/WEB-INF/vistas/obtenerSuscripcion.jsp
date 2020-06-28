<%@ page language="java" contentType="text/html;charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


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
	<section class="container mt-5 mb-5 clearfix">
		<h1 class="display-4">Suscripción ${tipo}</h1>
		<div class="row">
			<div class="col-9">
			<p class="lead"> Animate! Por un precio de $${precio} pesos argentinos
			al mes, vas a poder aprovechar de un menú diario de tres comidas completas
			pensadas solo para tu bien y tu disfrute, no te vas a arrepentir.
			Una suscripción en <span class="text-danger">Recomida</span> 
			puede ser cancelada en cualquier momento si no estas satisfecho con nuestros 
			servicios. ¿Qué esperas para sumarte?
			</p>
			
			<c:if test="${not empty mensaje}">
				<h4>
					<span class="text-danger">${mensaje}</span>
				</h4>
				<br>
			</c:if>
			
			<c:if test="${empty mensaje}">			
			<form action="pagarSuscripcion" method="GET" class="d-inline">
				<input type="hidden" name="id" value="${id}">
				<script
					src="https://www.mercadopago.com.ar/integrations/v1/web-payment-checkout.js"
					data-preference-id="${preference.id}">
				</script>
			</form>
				<br>
			</c:if>
			
			</div>
			
			<div class="col border border-danger rounded">
			<p class="lead text-center">Primer pago</p>
			<p class="lead text-center">$ <span class="display-4 text-danger">${precio}</span>/mes</p>
			<p class="text-center">Pesos argentinos</p>
			</div>
		</div>
			
			<hr/>

		<a href="suscripciones"  class="btn btn-outline-danger mt-2">Ver otras suscripciones</a>
		<a href="interno"  class="btn btn-outline-danger mt-2">Por ahora no, gracias</a>

	</section>
	
	<%@include file="footer.jsp" %>
	
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