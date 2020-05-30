<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Bienvenido al Registro</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous"></head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-danger">
        <div class="container">

            <a class="navbar-brand" href="#">RECOMIDA</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
      
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav ml-auto">
            <li class="nav-item active">
                <a class="nav-link" href="#">Inicio</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Menú de comidas</a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    NombreUsuario
              </a>
              <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                <a class="dropdown-item" href="#">Informacion personal</a>
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="#">Mis pedidos</a>
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="#">Imc</a>
            </div>
        </li>
           
    </ul>
</div>
        </div>
    </nav>
   
   <div class="container mt-5 mb-5">
		<h3>Registro</h3>
   </div>
<div class="container">
<form:form action="registroValidacion" method="POST" modelAttribute="usuario">

  <div class="form-row">
  
    <div class="form-group col-md-6">
      <label for="email">Email</label>
         <form:input path="email" id="email" type="email" class="form-control"/>
    </div>
    
    <div class="form-group col-md-6">
      <label for="password">Contraseña</label>
		<form:input path="password" type="password" id="password" class="form-control"/>    
    </div>
  </div>
  
  <div class="form-group">
    <label for="altura">Altura (en cm)</label>
    <form:input path="altura" type="number" class="form-control" id="altura"/>
  </div>
  
  <div class="form-group">
      <label for="peso">Peso (en kg)</label>
         <form:input path="peso" type="number" class="form-control" id="peso"/>
  </div>
  <div class="form-group">
      <label for="fec">Fecha de nacimiento</label>
         <form:input path="fechaDeNacimiento" type="date" class="form-control" id="fec"/>
  </div>
  
    <div class="form-group">
      <label for="act">Actividad</label>
         <form:select path="actividad" class="form-control">
         <c:forEach items="${actividades}" var="act">
    		<option value="${act}">${act}</option>
		</c:forEach>
         </form:select>
  </div>
  
      <div class="form-group">
      <label for="act">Sexo</label>
         <form:select path="sexo" class="form-control">
         <c:forEach items="${sexos}" var="sexo">
    		<option value="${sexo}">${sexo}</option>
		</c:forEach>
         </form:select>
  </div>
  
  <button type="submit" class="btn btn-primary">Registrarse</button>
  
</form:form>
</div>

 <!-- Footer -->
<footer class="page-footer font-small unique-color-dark">

    <div class="bg-danger">
      <div class="container">
  
        <!-- Grid row-->
        <div class="row py-4 d-flex align-items-center">
  
          <!-- Grid column -->
          <div class="col-md-12 col-lg-12 text-center text-md-left mb-4 mb-md-0">
            <h6 class="mb-0 text-center text-white">Get connected with us on social networks!</h6>
          </div>
          <!-- Grid column -->
  
         
          </div>
          <!-- Grid column -->
  
        </div>
        <!-- Grid row-->
  
      </div>
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
<!-- Placed at the end of the document so the pages load faster -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" ></script>
		<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
		<script src="js/bootstrap.min.js" type="text/javascript"></script>
</body>
</html>