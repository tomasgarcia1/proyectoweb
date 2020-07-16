<!DOCTYPE html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">

<title>Recomida!</title>
</head>
<body>
	<%@include file="header.jsp"%>
	<section class="container mt-5 mb-5 clearfix">
		<c:if test="${not empty msj}">
		<div class="row">
			<div class="col">
			<h1 class="display-4">${msj}</h1>
			<hr/>
			<a href="suscripciones"  class="btn btn-outline-danger mt-2 float-right">Adquirir una suscripción</a>
			<a href="interno"  class="btn btn-outline-danger mt-2">Volver</a>
			</div>
			<div class="col">
				<img src="img/ohno.jpg" class="w-75 mx-auto d-block my-5" style="opacity:0.7">
			</div>
		</div>
		</c:if>
		
		<c:if test="${empty msj}">
			<div class="row">
				<div class="col">
					<h4 class="display-4">Suscripción ${tipo.nombre}</h4>
					<ul class="list-group list-group-flush">
					  <li class="list-group-item lead">Adquiriste la suscripción el día 
					  <span class="float-right text-danger">${susc.fechaInicio}</span></li>
					  <li class="list-group-item lead">La fecha de vencimiento es
					  <span class="float-right text-danger">${susc.fechaVencimiento}</span></li>
					  <li class="list-group-item lead">El valor de tu suscripción por mes es de
					  <span class="float-right text-danger">$${tipo.precio}</span></li>
					 <li class="list-group-item lead">El estado de tu suscripción es
					 <c:if test="${estado}">
					  <span class="float-right text-danger">Activo</span></li>
					 </c:if>
					 <c:if test="${!estado}">
					  <span class="float-right text-danger">Vencida</span></li>
					 </c:if>			 
					</ul>
					
					<a href="interno"  class="btn btn-outline-danger mt-4">Volver al home</a>
					
					<c:if test="${estado}">
					<button type="button" class="btn btn-outline-dark mt-4 float-right" data-toggle="modal" data-target="#staticBackdrop">
					  Cancelar Suscripción
					</button>
					
					<!-- Modal -->
						<div class="modal fade" id="staticBackdrop" data-backdrop="static" data-keyboard="false" tabindex="-1" role="dialog" aria-labelledby="staticBackdropLabel" aria-hidden="true">
						  <div class="modal-dialog">
						    <div class="modal-content">
						      <div class="modal-header">
						        <h5 class="modal-title" id="staticBackdropLabel">Cancelar una Suscripción</h5>
						        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
						          <span aria-hidden="true">&times;</span>
						        </button>
						      </div>
						      <div class="modal-body">
						        Al cancelar tu suscripción ${tipo.nombre} vas a dejar de disfrutar de los beneficios de un 
						        usuario premium y no vas a tener que abonar los meses restantes de la suscripción. Sin embargo, 
						        el pago del mes actual no se reembolsará.<br>
						        Vas a poder adquirir una nueva suscripción cuando lo desees.
						        <hr/>
						        ¿Estas seguro de cancelar tu suscripción?
						      </div>
						      <div class="modal-footer">
						        <button type="button" class="btn btn-danger" data-dismiss="modal">Volver</button>
						        <a href="cancelarSuscripcion"  class="btn btn-outline-dark float-right">Cancelar suscripción</a>
						      </div>
						    </div>
						  </div>
						</div>
						</c:if>		
					<c:if test="${!estado}">
					<a href="suscripciones"  class="btn btn-outline-danger mt-4 float-right">Adquirir una suscripción</a>
					 </c:if>
				</div>
				
				<div class="col">
					<img src="img/certificado.png" style="opacity: 0.7;">
				</div>
			</div>
		</c:if>
		
	</section>

	<!-- Footer -->
	<%@include file="footer.jsp"%>

	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	
</body>
</html>