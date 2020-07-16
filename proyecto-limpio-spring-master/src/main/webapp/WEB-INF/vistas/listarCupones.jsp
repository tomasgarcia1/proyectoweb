<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Mis Cupones</title>
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
		<table class="table">
			<thead>
				<tr>
					<th scope="col" class="text-danger">ID</th>
					<th scope="col" class="text-danger">Importe</th>
					<th scope="col" class="text-danger">Fecha</th>
					<th scope="col" class="text-danger">Estado</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${cupones}" var="cupon">
					<tr>
						<th scope="row">${cupon.id}</th>
						<td class="h5">$ ${cupon.valor}</td>
						<td>${cupon.fechavencimiento}</td>
						<td><c:if test="${cupon.estado == true}">
								<h5 class="text-success">HABILITADO</h5>
							</c:if> <c:if test="${cupon.estado == false}">
								<h5 class="text-danger">DESHABILITADO</h5>
							</c:if></td>
					</tr>
				</c:forEach>
		</table>
		<a href="home" class="btn btn-outline-danger mt-5 btn-lg">Volver a Home</a>
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