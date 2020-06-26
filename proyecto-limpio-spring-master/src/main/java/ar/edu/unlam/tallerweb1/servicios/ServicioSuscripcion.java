package ar.edu.unlam.tallerweb1.servicios;

import java.time.LocalDate;

import ar.edu.unlam.tallerweb1.modelo.Suscripcion;
import ar.edu.unlam.tallerweb1.modelo.TipoSuscripcion;

public interface ServicioSuscripcion {

	TipoSuscripcion obtenerDatosSegunTipo(Long tipo);

	LocalDate calcularFechaVencimiento(LocalDate fechaInicio, Long tipo);

	Suscripcion crearSuscripcion(Long tipo, LocalDate fechaInicio);

	void insertarSuscripcionEnUsuario(Long tipo, LocalDate fechaInicio, Long id);

	Boolean vencerSuscripcion(Long id);

}
