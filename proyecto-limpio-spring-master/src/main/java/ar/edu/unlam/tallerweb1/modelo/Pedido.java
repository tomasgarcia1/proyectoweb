package ar.edu.unlam.tallerweb1.modelo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	//private Datetime fecha;
	private Estado estado;
	private Double precio;
	@ManyToOne(cascade = CascadeType.ALL)
	private Usuario usuario;
	//mappedby indica la relacion bidireccional y tambien permitimos que se tome la config de JoinTable de Comida
	//hay que escribir mappedBy = <nombre de la lista en la otra entity>
	//el error que me tiraba era que ponia Pedido en vez de pedidos porque crei que se referia a la clase no a la lista kajsjsajas
	@ManyToMany(mappedBy = "pedidos")
	private List<Comida> comidas;

	
	public Double getPrecio() {
		return precio;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	public List<Comida> getComidas() {
		return comidas;
	}
	public void setComidas(List<Comida> comidas) {
		this.comidas = comidas;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}
