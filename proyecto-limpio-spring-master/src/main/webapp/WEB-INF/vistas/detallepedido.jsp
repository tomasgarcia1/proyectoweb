<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
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

	<section class="container -sm p-4">
		<!-- ACA VA EL DETALLE PAPA -->
		<h1 class="text-center">Pedido: ${pedido.id}</h1>
		<hr>
		<ul class="list-group list-group-vertical-sm">
			<li class="list-group-item">
				<h4>Datos del usuario</h4>
				<h3 class="text-danger">${pedido.usuario.email}</h3> <br>
				<h4>Datos del pedido</h4>
				<h2 class="text-success">${pedido.estado}</h2> <br> <c:if
					test="${usuario.rol == 'ADMINISTRADOR' && pedido.estado!='ENVIADO' && pedido.estado!='CANCELADO'}">
					<form action="actualizarestado" method="POST">
						<div class="input-group mb-3">
							<div class="input-group-prepend">
								<label class="input-group-text" for="inputGroupSelect01">Actualizar
									Estado</label><input name="id" type="hidden" value="${pedido.id}">
							</div>
							<select class="custom-select" id="inputGroupSelect01"
								name="estado" required="required">
								<option selected>${pedido.estado}</option>
								<c:forEach items="${estados}" var="estado">
									<c:if test="${estado != pedido.estado}">
										<option value="${estado}">${estado}</option>
									</c:if>
								</c:forEach>
							</select><input type="submit" role="button"value="Actualizar">
						</div>
					</form>
				</c:if>
			</li>

		</ul>
		<br>
		<table class="table table-hover">
			<thead>
				<tr>
					<th scope="col">Comida</th>
					<th scope="col">Precio</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${comidas}" var="comida">
					<tr>
						<td><h4 class="blockquote">${comida.nombre}</h4></td>
						<td><h4>${comida.precio}</h4></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<h4>Precio final: $ ${pedido.precio}</h4>
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