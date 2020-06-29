<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
          <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
 <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

    <title>Recomida!</title>
  </head>
  <body>
   <%@include file="header.jsp" %>

<body>
	<div class="container my-5">
		<div class="row">
			<div class="col-md-4">
				<div class="card bg-light mb-3" style="max-width: 18rem;">
				  <div class="card-header bg-danger text-white">Distancia.</div>
					  <div class="card-body">
					    <h5 class="card-title">${distancia} Km</h5>
					    <p class="card-text">Esta es la distancia de la ubicacion que indicó.</p>
					  </div>
				</div>
			</div>
			
			<div class="col-md-4">
				<div class="card bg-light mb-3" style="max-width: 18rem;">
					 <div class="card-header bg-danger text-white">Precio.</div>
					  <div class="card-body">
					    <h5 class="card-title">$ ${precio}</h5>
					    <p class="card-text">Este es el precio del envio de su pedido.</p>
					</div>
				</div>
			</div>
			
			<div class="col-md-4">
			<div class="card bg-light mb-3" style="max-width: 18rem;">
			  <div class="card-header bg-danger text-white">Tiempo.</div>
				  <div class="card-body">
				    <h5 class="card-title">El envio llega en ${tiempo} minutos.</h5>
				    <p class="card-text">El tiempo puede variar.</p>
				  </div>
			</div>
			</div>
		</div>
		
			<form:form action="generarpedidoUnicaComida" method="POST" modelAttribute="posicion" >
				<input type="hidden"  name="id" id="id" value="${posicion.id}"/>
				<input type="hidden"  name="latitude" id="latitude" value="${posicion.latitude}"/>
				<input type="hidden"  name="longitude" id="longitude" value="${posicion.longitude}"/>	
				<input type="hidden"  name="nombre" id="nombre" value="${posicion.nombre}"/>
				<input type="hidden"  name="idComida" id="idComida" value="${idComida}"/>
					
				<button type="submit" class="btn btn-success">Realizar pedido</button>
			</form:form>
		
	</div>

<!-- Footer Links -->
	<%@include file="footer.jsp" %>

  <!-- Footer -->

    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
	<script>
		$("[data-toggle=tooltip]").tooltip();
	</script>

</body>

</html>