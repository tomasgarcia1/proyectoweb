package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity 
public class Posicion {
	  @Id 
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private Long id;  
	  private Double latitude;
	  private Double longitude;
 
	  public Posicion() { 
	  } 

	  public Posicion(Double latitude, Double longitude) {
	    this.latitude = latitude;
	    this.longitude = longitude;
	  }
	  
	  

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}  
	 
	  
}

