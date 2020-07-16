<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Mis Pedidos</title>
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

	<%@include file="header.jsp"%>

	<h5 class="text-center display-4 mt-4">Pedidos</h5>
	<section class="container -xl">
		<!-- ACA VA LA LISTA PAPA -->
		<table class="table table-hover">
			<thead>
				<tr>
					<th scope="col" class="text-danger">ID</th>
					<th scope="col" class="text-danger">Importe</th>
					<th scope="col" class="text-danger">Estado</th>
					<th scope="col" class="text-danger">Fecha</th>
					<c:if test="${usuario.rol == 'ADMINISTRADOR'}">
						<th scope="col" class="text-danger">Usuario</th>
					</c:if>
					<th scope="col" class="text-danger">Detalle</th>
						<th scope="col" class="text-danger">Cancelar</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${pedidos}" var="pedido">
					<tr>
						<th scope="row" class="text-secondary">${pedido.id}</th>
						<td class="h5">$ ${pedido.precio}</td>
						<td><c:if test="${pedido.estado =='ENVIADO'}">
								<h5 class="text-primary">${pedido.estado}</h5>
							</c:if> <c:if test="${pedido.estado =='CANCELADO'}">
								<h5 class="text-danger">${pedido.estado}</h5>
							</c:if> <c:if test="${pedido.estado =='ENVIO'}">
								<h5 class="text-secondary">${pedido.estado}</h5>
							</c:if> <c:if test="${pedido.estado =='ACEPTADO'}">
								<h5 class="text-info">${pedido.estado}</h5>
							</c:if> <c:if test="${pedido.estado =='PROCESO'}">
								<h5 class="text-success">${pedido.estado}</h5>
							</c:if></td>

						<td class="h5 text-secondary">${pedido.fecha}</td>
						<c:if test="${usuario.rol == 'ADMINISTRADOR'}">
							<td>${pedido.usuario.email}</td>
						</c:if>
						<td><a href="detallepedido?id=${pedido.id}">VER DETALLE</a></td>
						<c:if
							test="${pedido.estado != 'CANCELADO' && pedido.estado != 'ENVIADO'}">
							<td><a href="cancelarpedido?id=${pedido.id}">CANCELAR</a></td>
						</c:if>
						<c:if
							test="${pedido.estado != 'ACEPTADO' && pedido.estado != 'ENVIO' && pedido.estado != 'PROCESO'}">
							<td>No se puede cancelar, ya concretó el estado del pedido</td>
						</c:if>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<a href="adminInterno" class="btn btn-lg btn-danger mt-3">Atrás</a> <br>
		<br>
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