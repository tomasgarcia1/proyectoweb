<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Confirmar pedido</title>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
<script src="js/bootstrap.min.js" type="text/javascript"></script>
</head>
<body>
	<%@include file="header.jsp"%>

	<div class="container mt-5 mb-4">
		<h3>Información de su pedido</h3>
	</div>
	<div class="container">
		<li style="list-style: none">
			<h4>
				<u>Resumen de menú</u>
			</h4>
			<ul class="list-group list-group-flush">
				<c:forEach items="${comidas}" var="c">
					<li class="list-group-item"><span class="h5">${c.nombre}</span>
						porción individual <span class="h5 text-danger float-right">$
							${c.precio}</span></li>
				</c:forEach>
				<li class="list-group-item"><span class="h5">Envío </span> <span
					class="h5 text-danger float-right">$ ${viaje}</span></li>
			</ul>
			<div class="float-right">
				<span class="h5 m-2">Total: <span class="h4 text-danger">$
						${precio}</span></span>
			</div>
		</li> <br> <br>

		<button id="boton" onclick="mostrarSelect()"
			class="btn btn-danger mb-3">Agregar Cupon</button>

		<div class="container -sm">
			<form action="pedidoConCuponComidas" method="POST" id="form">
				<c:forEach items="${cupones}" var="cupon">
					<c:if test="${cupones == null }">Lo sentimos, usted no tiene cupones disponibles</c:if>
					<div class="input-group-prepend" id="select" style="display: none;">
						<c:if test="${cupones != null}">
							<span>Una vez que seleccionó el cupon, de click en elegir!</span>
							<select class="custom-select" id="select" name="idCupon"
								required="required">
								<option value="${cupon.id}">${cupon.valor}</option>
							</select>

							<input type="hidden" name="idCupon" value="${cupon.id}">
							<input type="hidden" name="id" value="${id}">
							<input type="hidden" name="idPosicion" value="${idPosicion}">
							<input type="hidden" name="precio" value="${precio}">
							<button class="btn btn-outline-success">Elegir</button>
						</c:if>
					</div>
				</c:forEach>
			</form>
		</div>

		<div class="mt-5">
			<form action="pagarpedido" method="GET" class="d-inline">
				<input type="hidden" name="id" value="${id}"> <input
					type="hidden" name="idPosicion" value="${idPosicion}"> <input
					type="hidden" name="idCupon" value="-1">
				<script
					src="https://www.mercadopago.com.ar/integrations/v1/web-payment-checkout.js"
					data-preference-id="${preference.id}">
					
				</script>
			</form>
			<a href="interno" class="btn btn-danger m-3">Cancelar</a>

		</div>
	</div>

	<!-- Footer -->
	<%@include file="footer.jsp"%>
	<!-- Placed at the end of the document so the pages load faster -->

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script
		src="proyectoweb/proyecto-limpio-spring-master/src/main/webapp/js/jquery-1.11.3.min.js"></script>

	<script>
		window.jQuery
				|| document
						.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')
	</script>

	<script type="text/javascript">
		function mostrarSelect() {
			$("#select").show();
		}
	</script>

</body>

</html>

