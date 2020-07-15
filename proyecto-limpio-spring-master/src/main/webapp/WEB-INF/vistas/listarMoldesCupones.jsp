<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Molde Cupon</title>
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

	<h5 class="text-center display-4 mt-4">Cupones</h5>
	<section class="container -sm">
		<table class="table">
			<thead>
				<tr>
					<th scope="col" class="text-danger blockquote">ID</th>
					<th scope="col" class="text-danger blockquote">Valor</th>
					<th scope="col" class="text-danger blockquote">Estado</th>
					<th scope="col" class="text-danger blockquote">Modificar</th>
					<th scope="col" class="text-danger blockquote">Eliminar</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${moldes}" var="molde">
					<tr>
						<td>${molde.id}</td>
						<td class="text-danger h5">$ ${molde.valor}</td>
						<td><c:if test="${molde.estado == true}">
								<h5 class="text-success">HABILITADO</h5>
							</c:if> <c:if test="${molde.estado == false}">
								<h5 class="text-danger">DESHABILITADO</h5>
							</c:if></td>
						<td><a href="modificarMoldeCupon?id=${molde.id}">MODIFICAR</a></td>
						<td><a href="eliminarMoldeCupon?id=${molde.id}">ELIMINAR</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<a href="adminInterno" class="btn btn-danger mt-3 mb-3">Atrás</a>
	</section>
</body>

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

</html>