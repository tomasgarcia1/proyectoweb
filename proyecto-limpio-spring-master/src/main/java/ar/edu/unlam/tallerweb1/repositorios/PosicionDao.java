package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Posicion;

public interface PosicionDao {
	Long crearPosicion(Posicion posicion);

	Posicion obtenerPosicionPorId(Long id);
}
