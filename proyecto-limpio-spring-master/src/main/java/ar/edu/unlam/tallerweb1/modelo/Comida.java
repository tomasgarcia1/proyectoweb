package ar.edu.unlam.tallerweb1.modelo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

@Entity
public class Comida implements Comparable<Comida> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	private String descripcion;
	private Double calorias;
	private Double precio;
	private TipoHorario tipoHorario;

	// mappedby indica la relacion bidireccional y tambien permitimos que se tome la
	// config de JoinTable de Comida
	// hay que escribir mappedBy = <nombre de la lista en la otra entity>
	// el error que me tiraba era que ponia Pedido en vez de pedidos porque crei que
	// se referia a la clase no a la lista kajsjsajas
	@ManyToMany(mappedBy = "comidas")
	private List<Pedido> pedidos;

	@JoinTable(name = "comidas_tipos", joinColumns = @JoinColumn(name = "fk_comida"), inverseJoinColumns = @JoinColumn(name = "fk_tipo"))
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Tipo> tipos;

	@JoinTable(name = "comidas_restricciones", joinColumns = @JoinColumn(name = "fk_comida"), inverseJoinColumns = @JoinColumn(name = "fk_restriccion"))
	@ManyToMany(cascade = CascadeType.ALL)
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

	public List<Restriccion> getRestricciones() {
		return restricciones;
	}

	public void setRestricciones(List<Restriccion> restricciones) {
		this.restricciones = restricciones;
	}

	public TipoHorario getTipoHorario() {
		return tipoHorario;
	}

	public void setTipoHorario(TipoHorario tipoHorario) {
		this.tipoHorario = tipoHorario;
	}

	@Override
	public int compareTo(Comida comida) {
		int resultado = 0;
		if (comida.getNombre().compareTo(this.nombre) == 0) {
			resultado = 0;
		} else if (comida.getNombre().compareTo(this.nombre) > 0) {
			resultado = -1;
		} else if (comida.getNombre().compareTo(this.nombre) < 0) {
			resultado = 1;
		}
		return resultado;
	}


}
