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
<script src="https://unpkg.com/ionicons@5.1.2/dist/ionicons.js"></script>
</head>
<body>
	<%@include file="header.jsp"%>

	<section class="container -sm p-4">
		<!-- ACA VA EL DETALLE PAPA -->
		<h1 class="text-center mb-4">Pedido: ${pedido.id}</h1>

		<table class="table">
			<thead>
				<tr>
					<th scope="col" class="text-danger blockquote">Comida</th>
					<th scope="col" class="text-danger blockquote">Precio</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${comidas}" var="comida">
					<tr>
						<td>
							<li class="media"><img src="img/${comida.nombre}.jpg"
								class="rounded mr-3 mb-2 mt-2"
								style="width: 180px; height: 110px" alt="...">
								<div class="media-body">
									<h5 class="mt-3 mb-2">${comida.nombre}</h5>
									${comida.descripcion}
								</div></li>
						</td>
						<td><h4>$ ${comida.precio}</h4></td>
					</tr>
				</c:forEach>
				<tr>

					<td class="h4 mt-2">Total</td>
					<td class="text-danger h4 mt-2">$ ${pedido.precio}</td>
				</tr>
			</tbody>
		</table>
	</section>



	<section class="container -sm">
		<h3 class="blockquote">Información</h3>
		<hr>
		<ul>
			<li>
				<h5>Cliente:</h5>
				<h4 class="text-danger">${pedido.usuario.email}</h4>
			</li>
			<br>
			<li>
				<h5>Estado:</h5> <c:if test="${pedido.estado =='ENVIADO'}">
					<h4 class="text-primary">${pedido.estado}</h4>
				</c:if> <c:if test="${pedido.estado =='CANCELADO'}">
					<h4 class="text-danger">${pedido.estado}</h4>
				</c:if> <c:if test="${pedido.estado =='ENVIO'}">
					<h4 class="text-secondary">${pedido.estado}</h4>
				</c:if> <c:if test="${pedido.estado =='ACEPTADO'}">
					<h4 class="text-info">${pedido.estado}</h4>
				</c:if> <c:if test="${pedido.estado =='PROCESO'}">
					<h4 class="text-success">${pedido.estado}</h4>
				</c:if>
			</li>
		</ul>

		<c:if
			test="${usuario.rol == 'ADMINISTRADOR' && pedido.estado!='ENVIADO' && pedido.estado!='CANCELADO'}">
			<h5 class="blockquote text-center">Modifique el Estado del
				Pedido</h5>
			<form action="actualizarestado" method="POST">
				<div class="input-group mb-3">
					<div class="input-group-prepend">
						<input name="id" type="hidden" value="${pedido.id}">
					</div>
					<select class="custom-select" id="inputGroupSelect01" name="estado"
						required="required">
						<option selected>${pedido.estado}</option>
						<c:forEach items="${estados}" var="estado">
							<c:if test="${estado != pedido.estado}">
								<option value="${estado}">${estado}</option>
							</c:if>
						</c:forEach>
					</select>
					<button class="btn btn-outline-success">Actualizar</button>
				</div>
			</form>
		</c:if>
		<a href="verpedidos" class="btn btn-danger mt-3 mb-3">Atrás</a>
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