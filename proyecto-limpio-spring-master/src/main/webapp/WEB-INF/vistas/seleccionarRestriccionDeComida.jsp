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


	<div class="container mt-5 mb-5">
		<h3>Seleccione las restricciones de la comida</h3>
	</div>
	<div class="container mb-4">
		<form:form action="colocarRestriccionAComida" method="GET">
				
			<div class="form-row">

				<div class="form-group col-md-6">
					<label >Nombre</label>
					<input type="text" value="${comida.nombre}" class="form-control" readonly/>
				</div>

				<div class="form-group col-md-6">
					<label>Id</label>
					<input type="text" name="id" value="${comida.id}" class="form-control" readonly/>
				</div>
				
			</div>
			
			<label class="h6">Restricciones</label><br>
			<c:forEach items="${restricciones}" var="restriccion">
					<input type="checkbox" name="restriccion" value="${restriccion.id} required"/>
					<span> ${restriccion.nombre}</span>
					<br>
				</c:forEach>
					
			<button type="submit" class="btn btn-danger mt-4" id="checkBtn">Agregar Restricción</button>

			
		</form:form>
	</div>
	
		<!-- Footer -->
			<%@include file="footer.jsp" %>

	<!-- Placed at the end of the document so the pages load faster -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.0/jquery.min.js"></script>
		
	<script>
		window.jQuery
				|| document
						.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')
	</script>
	<script type="text/javascript">
		$(document).ready(function () {
		    $('#checkBtn').click(function() {
		      checked = $("input[type=checkbox]:checked").length;
		
		      if(!checked) {
		        alert("Por favor seleccionar al menos una restricción");
		        return false;
		      }
		
		    });
		});
		
		</script>  
	<script src="js/bootstrap.min.js" type="text/javascript"></script>

</body>
</html>