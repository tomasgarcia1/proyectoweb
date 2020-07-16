const cordsRecomida={
		latitude:-34.668680,
		longitude:-58.566209
};
  

var mymap = L.map('map').setView([cordsRecomida.latitude,cordsRecomida.longitude],11);	

var searchControl = L.esri.Geocoding.geosearch().addTo(mymap);

var marker = L.marker([cordsRecomida.latitude,cordsRecomida.longitude]).addTo(mymap);

marker.bindPopup("<b>Recomida!</b><br>San justo.").openPopup();

L.tileLayer('https://api.mapbox.com/styles/v1/{id}/tiles/{z}/{x}/{y}?access_token=pk.eyJ1IjoibWFyY29zY2FicmFsIiwiYSI6ImNrYmVoZGEydjBsamUyb253bThiOHZtMG8ifQ.snZzmGQRneUicIBvZyzBmw', {
	attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors, <a href="https://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, Imagery © <a href="https://www.mapbox.com/">Mapbox</a>',
	id: 'mapbox/streets-v11',
	tileSize: 512,
	zoomOffset: -1,
	accessToken: 'pk.eyJ1IjoibWFyY29zY2FicmFsIiwiYSI6ImNrYmVoZGEydjBsamUyb253bThiOHZtMG8ifQ.snZzmGQRneUicIBvZyzBmw',
	worldCopyJump: true
}).addTo(mymap);	




var results = L.layerGroup().addTo(mymap);

var coord=[];
const button = document.getElementById('boton'); 
const botonActivar=document.getElementById('nuevaPos');
const latitude=document.getElementsByName("latitude");
const longitude=document.getElementsByName("longitude");
const nombre=document.getElementsByName("nombre");
const id=document.getElementById("id");
button.disabled = true;

if(botonActivar!=null){
	botonActivar.style.visibility="hidden";
}

searchControl.on("results", function(data) {
    results.clearLayers();
    for (var i = data.results.length - 1; i >= 0; i--) {
        results.addLayer(L.marker(data.results[0].latlng).addTo(mymap).bindPopup(""+data.results[0].text).openPopup());
	   
        
        coord[0]=data.latlng.lat;
        coord[1]=data.latlng.lng;
        
        latitude[0].value = coord[0];
        longitude[0].value = coord[1];
        nombre[0].value = data.results[0].text;
        
        
        if(id!=null){
      	  	id.value =-1;
        }

        if(coord[0]==0 && coord[1]==0){
        	button.disabled = true;   
        }else{
        	button.disabled=false;
        }
                     
    }	
});

const select=document.getElementById('select-location');

if(select!=null){
	document.getElementById('select-location').addEventListener('change', function(e){
		 results.clearLayers();
		if(e.target.value!="-1"){
		  document.getElementsByClassName("geocoder-control")[0].style.visibility="hidden";
		  let coords = e.target.value.split(",");
		  	
			botonActivar.style.visibility="visible";
			
		  var id=coords[2];
		  
		  document.getElementsByName("id")[0].value =id;
		  document.getElementsByName("latitude")[0].value = 0;
	      document.getElementsByName("longitude")[0].value = 0;
	      document.getElementsByName("nombre")[0].value = 0;

	      var x = document.getElementById("location").options[document.getElementById("location").selectedIndex].text;
	      
	     	results.addLayer(L.marker(coords).addTo(mymap).bindPopup(""+x).openPopup());			
		  mymap.flyTo(coords, 11);	  
		  button.disabled=false;
	  }

	});
}

function activar(){
	const botonActivar=document.getElementById('nuevaPos');

	botonActivar.style.visibility="hidden";
	document.getElementsByClassName("geocoder-control")[0].style.visibility="visible";

}