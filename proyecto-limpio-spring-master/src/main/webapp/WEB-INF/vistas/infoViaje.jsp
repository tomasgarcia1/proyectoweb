<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
    <nav class="navbar navbar-expand-lg navbar-dark bg-danger">
        <div class="container">

            <a class="navbar-brand" href="home">RECOMIDA</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
      
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav ml-auto">
            <li class="nav-item active">
                <a class="nav-link" href="home">Inicio</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Menú de comidas</a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Mi cuenta
              </a>
              <div class="dropdown-menu" aria-labelledby="navbarDropdown">
<a class="dropdown-item" href="mostrarRestriccionesDeUsuario">Mis restricciones</a>                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="mispedidos">Mis pedidos</a>
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="#">Imc</a>
            </div>
        </li>
           
    </ul>
</div>
        </div>
    </nav>
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
		<form:form action="menuSugerido" method="POST" modelAttribute="posicion" >
			<input type="hidden"  name="id" id="id" value="${posicion.id}"/>
			<input type="hidden"  name="latitude" id="latitude" value="${posicion.latitude}"/>
			<input type="hidden"  name="longitude" id="longitude" value="${posicion.longitude}"/>			
			<button type="submit" class="btn btn-success">Elegir comidas por gustos</button>
		</form:form>
		
		<form:form action="menuCalorias" method="POST" modelAttribute="posicion" >
			<input type="hidden"  name="id" id="id" value="${posicion.id}"/>
			<input type="hidden"  name="latitude" id="latitude" value="${posicion.latitude}"/>
			<input type="hidden"  name="longitude" id="longitude" value="${posicion.longitude}"/>			
			<button type="submit" class="btn btn-success my-3">Elegir comidas por calorias</button>
		</form:form>
		
	</div>


<!-- Footer Links -->
    <div class="container text-center text-md-left mt-5">
  
      <!-- Grid row -->
      <div class="row mt-3">
  
        <!-- Grid column -->
        <div class="col-md-3 col-lg-4 col-xl-3 mx-auto mb-4">
  
          <!-- Content -->
          <h6 class="text-uppercase font-weight-bold">Recomida</h6>
          <hr class="deep-purple accent-2 mb-4 mt-0 d-inline-block mx-auto" style="width: 60px;">
          <p>Lorem ipsum dolor sit amet,
            consectetur
            adipisicing elit.</p>
  
        </div>
        <!-- Grid column -->
  
        <!-- Grid column -->
        <div class="col-md-2 col-lg-2 col-xl-2 mx-auto mb-4">
  
          <!-- Links -->
          <h6 class="text-uppercase font-weight-bold">Products</h6>
          <hr class="deep-purple accent-2 mb-4 mt-0 d-inline-block mx-auto" style="width: 60px;">
          <p>
            <a href="#!">Wea1</a>
          </p>
          <p>
            <a href="#!">Wea2</a>
          </p>
          <p>
            <a href="#!">Wea3</a>
          </p>
          <p>
            <a href="#!">Wea4</a>
          </p>
  
        </div>
        <!-- Grid column -->
  
        <!-- Grid column -->
        <div class="col-md-3 col-lg-2 col-xl-2 mx-auto mb-4">
  
          <!-- Links -->
          <h6 class="text-uppercase font-weight-bold">Useful links</h6>
          <hr class="deep-purple accent-2 mb-4 mt-0 d-inline-block mx-auto" style="width: 60px;">
          <p>
            <a href="#!">Tu cuenta</a>
          </p>
          <p>
            <a href="#!">Tu suscripcion</a>
          </p>
          <p>
            <a href="#!">Otra cosa</a>
          </p>
          <p>
            <a href="#!">Help</a>
          </p>
  
        </div>
        <!-- Grid column -->
  
        <!-- Grid column -->
        <div class="col-md-4 col-lg-3 col-xl-3 mx-auto mb-md-0 mb-4">
  
          <!-- Links -->
          <h6 class="text-uppercase font-weight-bold">Contacto</h6>
          <hr class="deep-purple accent-2 mb-4 mt-0 d-inline-block mx-auto" style="width: 60px;">
          <p>
            <i class="fas fa-home mr-3"></i> Buenos Aires, Argentina</p>
          <p>
            <i class="fas fa-envelope mr-3"></i> info@lala.com</p>
          <p>
            <i class="fas fa-phone mr-3"></i> + 011 7777 6666</p>
          <p>
            <i class="fas fa-print mr-3"></i> + 011 2424 3332</p>
  
        </div>
        <!-- Grid column -->
  
      </div>
      <!-- Grid row -->
  
    </div>
    <!-- Footer Links -->
  
  </footer>
  <!-- Footer -->


    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
  </body>

</html>