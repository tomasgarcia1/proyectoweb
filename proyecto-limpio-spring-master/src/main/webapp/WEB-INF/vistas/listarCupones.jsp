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
		<table class="table table-hover">
			<thead>
				<tr>
					<th scope="col">ID</th>
					<th scope="col">Importe</th>
					<th scope="col">Estado</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${cupones}" var="cupon">
					<tr>
						<th scope="row">${cupon.id}</th>
						<td class="h5">$ ${cupon.valor}</td>
						<td><c:if test="${cupon.estado == true}">
								<h5 class="text-success">HABILITADO</h5>
							</c:if> <c:if test="${cupon.estado == false}">
								<h5 class="text-danger">DESHABILITADO</h5>
							</c:if></td>
					</tr>
				</c:forEach>
		</table>
	</section>

</body>
</html>