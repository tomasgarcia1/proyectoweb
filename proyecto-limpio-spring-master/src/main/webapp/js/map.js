
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
	accessToken: 'pk.eyJ1IjoibWFyY29zY2FicmFsIiwiYSI6ImNrYmVoZGEydjBsamUyb253bThiOHZtMG8ifQ.snZzmGQRneUicIBvZyzBmw'
}).addTo(mymap);	




var results = L.layerGroup().addTo(mymap);

var coord=[];

searchControl.on("results", function(data) {
    results.clearLayers();
    for (var i = data.results.length - 1; i >= 0; i--) {
    	
        results.addLayer(L.marker(data.results[0].latlng));
        
        console.log(data.latlng.lat+"   "+data.latlng.lng);
        
        coord[0]=data.latlng.lat;
        coord[1]=data.latlng.lng;
        
        document.getElementsByName("latitude")[0].value = coord[0];
        document.getElementsByName("longitude")[0].value = coord[1];
        
    }	
});


