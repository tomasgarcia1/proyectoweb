<!DOCTYPE html>
<html lang="en">
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
                <a class="nav-link" href="cerrarSesion">Cerrar Sesión</a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Mi cuenta
              </a>
              <div class="dropdown-menu" aria-labelledby="navbarDropdown">
<a class="dropdown-item" href="mostrarRestriccionesDeUsuario">Mis restricciones</a>                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="#">Mis pedidos</a>
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="#">Imc</a>
            </div>
        </li>
           
    </ul>
</div>
        </div>
    </nav>
     
    <section id="bienvenido" class="my-1">
        <div class="container">
        <div class="jumbotron bg-white">
            <h1 class="display-4">Bienvenido! Gracias por trabajar en Recomida</h1>
         	<hr class="my-2">
            <a class="btn btn-outline-danger btn-lg" href="agregarComida" role="button">Agregar nueva comida</a>
            <a class="btn btn-outline-danger btn-lg" href="buscarComidaPorHorario" role="button">Agregar nueva restricción</a>
            <br><br>
            <a class="btn btn-outline-danger btn-lg" href="verpedidos" role="button">Ver pedidos</a>
          </div>
        </div>
    </section>

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
  </body>
</html>