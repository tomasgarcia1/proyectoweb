<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Posiciones</title>
    <meta name='viewport' content='initial-scale=1,maximum-scale=1,user-scalable=no' />
    <!-- Load Leaflet from CDN-->
    <link rel="stylesheet" href="https://unpkg.com/leaflet/dist/leaflet.css" />
    <script src="https://unpkg.com/leaflet/dist/leaflet-src.js"></script> 
 
    <!-- Load Esri Leaflet from CDN -->
    <script src="https://unpkg.com/esri-leaflet"></script> 
 
    <!-- Esri Leaflet Geocoder -->
    <link rel="stylesheet" href="https://unpkg.com/esri-leaflet-geocoder/dist/esri-leaflet-geocoder.css">
    <script src="https://unpkg.com/esri-leaflet-geocoder"></script> 
</head>

<body>
 <div id="select-location">
  <select name="location" id="location">
    <option value="init">Selecciona uno...</option>
    <c:forEach items="${posiciones}" var="posicion">
		<option value="${posicion.latitude} , ${posicion.longitude} , ${posicion.id}" id="pos">${posicion.nombre}</option>
	</c:forEach>	
  </select>
  
</div>
<div id="map">

</div>
<form:form action="posicionId" method="POST">
	<input type="hidden"  name="id" id="id" value="0"/>
	
 	<button type="submit" class="btn btn-success my-3">Confirmar ubicacion</button>
	
	<a href="mapa" class="btn btn-danger my-3 ml-5">Cancelar ubicacion</a>
</form:form>
<style>
	#map { height: 600px; width: 100hw; }
select {
  width: 30%;
  font-size: 2rem
} 
	</style>
	<script>
		let map = L.map('map').fitWorld();

L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
  attribution:
    '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
}).addTo(map);


document.getElementById('select-location').addEventListener('change', function(e){
 
	if(e.target.value!="init"){
	  let coords = e.target.value.split(",");
	  
	  var id=coords[2];
	  
	  console.log(id);
	  
	  document.getElementById("id").value=id;
	  
	  console.log("lat: "+coords[0]+" long:"+coords[1]);
	  
	  L.marker(coords).addTo(map);
	  map.flyTo(coords, 18);

  
  }
 

});
	</script>

</body>
</html>