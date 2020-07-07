<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Agregar Cupon</title>
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
	<form action="pedidoConCuponComidas" method="POST">
		<ul>
			<c:forEach items="${cupones}" var="cupon">
				<li>${cupon.valor}<input type="checkbox" id="${cupon.id}"
					name="idCupon" value="${cupon.id}" />
				</li>
			</c:forEach>
		</ul>

		<input type="hidden" name="id" value="${id}"> <input
			type="hidden" name="idPosicion" value="${idPosicion}"> <input
			type="hidden" name="precio" value="${precioFinal}">
		<button type="submit" class="btn btn-danger">Agregar Cupon</button>
	</form>
</body>
</html>