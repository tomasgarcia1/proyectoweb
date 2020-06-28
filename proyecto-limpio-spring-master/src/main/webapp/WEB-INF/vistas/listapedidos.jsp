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

	<%@include file="header.jsp" %>

	<section>
		<!-- ACA VA LA LISTA PAPA -->
		<table>
			<tr>
				<th>ID</th>
				<th>Importe</th>
				<th>Estado</th>
				<th><c:if test="${usuario.rol == 'ADMINISTRADOR'}">Usuario</c:if></th>
			</tr>
			<c:forEach items="${pedidos}" var="pedido">
				<tr>
					<td>${pedido.id}</td>
					<td>${pedido.precio}</td>
					<td>${pedido.estado}</td>
					<td><c:if test="${usuario.rol == 'ADMINISTRADOR'}">
    					${pedido.usuario.email}
    				</c:if></td>
					<td><a href="detallepedido?id=${pedido.id}">VER DETALLE</a></td>
					<td><c:if
							test="${pedido.estado != 'CANCELADO' && pedido.estado != 'ENVIADO'}">
							<a href="cancelarpedido?id=${pedido.id}">CANCELAR</a>
						</c:if></td>
				</tr>
			</c:forEach>
		</table>
	</section>
	<!-- Footer -->
	<%@include file="footer.jsp" %>

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