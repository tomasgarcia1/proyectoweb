<%@ page language="java" contentType="text/html;charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Agregar Restriccion</title>
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
	
	<div class="container p-4 mb-5 mt-5">
		<div class="row">
		  	<div class="col-6">
				<h3>${comida.nombre}</h3>
			<ul class="list-group list-group-flush">
				<c:forEach items="${comida.restricciones}" var="restriccion">
					<li class="list-group-item lead">${restriccion.nombre}</li>
				</c:forEach>
			</ul>
			
			<a class="btn btn-outline-danger mt-5" href="adminInterno">Confirmar restricciones</a>
			
		</div>
		
	    <div class="col-1 text-right">
	    	<img src="img/receta.png" style="width:400px; opacity:0.7;">
	    </div>
	    
	</div>

</div>
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