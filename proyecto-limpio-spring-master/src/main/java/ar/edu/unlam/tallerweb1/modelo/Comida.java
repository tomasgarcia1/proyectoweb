package ar.edu.unlam.tallerweb1.modelo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

@Entity
public class Comida {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	private String descripcion;
	private Double calorias;	
	private Double precio;

	
	//esta anotation es opcional pero sirve para manejar mejor la tabla n:n
	@JoinTable(
			//nombre de la tabla n:n en la bdd
			name = "pedidos_comidas",
			//nombre de la fk de comida
			joinColumns = @JoinColumn(name = "fk_comida"),
			//nombre de la fk de pedido
			inverseJoinColumns = @JoinColumn(name = "fk_pedido")
			)
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Pedido> pedidos;
	
	@JoinTable(
			name = "comidas_tipos",
			joinColumns = @JoinColumn(name = "fk_comida"),
			inverseJoinColumns = @JoinColumn(name = "fk_tipo")
			)
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Tipo> tipos;
	
	@ManyToMany(mappedBy = "comidas")
	private List<Restriccion> restricciones;
	
	public List<Tipo> getTipos() {
		return tipos;
	}
	public void setTipo(List<Tipo> tipos) {
		this.tipos = tipos;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Double getCalorias() {
		return calorias;
	}

	public void setCalorias(Double calorias) {
		this.calorias = calorias;
	}
	public Double getPrecio() {
		return precio;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}
	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
	
	
}
