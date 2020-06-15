package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Posicion;

public interface ServicioPosicion {
	Long crearPosicion(Posicion posicion);

	Posicion obtenerPosicionPorId(Long id);
}
