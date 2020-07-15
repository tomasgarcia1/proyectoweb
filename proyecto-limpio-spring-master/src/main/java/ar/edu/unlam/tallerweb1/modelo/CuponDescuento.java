package ar.edu.unlam.tallerweb1.modelo;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class CuponDescuento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Integer valor;
	private Boolean estado;
	private LocalDate fechavencimiento;
	
	public CuponDescuento() {

	}

	public CuponDescuento(int i, int valor2, boolean estado2, int j) {
	}

	@ManyToOne
	private Usuario usuario;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getValor() {
		return valor;
	}

	public void setValor(Integer valor) {
		this.valor = valor;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public LocalDate getFechavencimiento() {
		return fechavencimiento;
	}

	public void setFechavencimiento(LocalDate fechavencimiento) {
		this.fechavencimiento = fechavencimiento;
	}

}
