<%@ page language="java" contentType="text/html;charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<html lang="en">
<title>Obtener Cupones</title>
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

	<div class="container -sm p-3">
		<form method="GET" action="mostrarComidasEleccionUsuario">
			<h3 class="text-center display-4">Cupones</h3>
			<hr>
			<div class="row my-5">
				<c:forEach items="${cupones}" var="cupon">
					<div class="col-md-4 mb-4">
						<div class="card" style="width: 23rem;">
							<img class="card-img-top" src="img/cupon.jpg"
								alt="Card image cap" style="height: 180px">
							<div class="card-body">
								<h5 class="card-title text-center text-danger">$ ${cupon.valor}</h5>
								<div class="align-center">
									<a href="agregarCupon?id=${cupon.id}"
										class="btn btn-outline-info btn-block mb-2" role="button"
										aria-pressed="false">Obtener Cupon</a>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
				<br>
			</div>
		</form>
	</div>






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