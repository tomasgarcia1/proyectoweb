package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Posicion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

public interface ServicioPosicion {
	Long crearPosicion(Posicion posicion);

	Posicion obtenerPosicionPorId(Long id);

	boolean compararPosiciones(List<Posicion> posiciones, Posicion pos);
	List<Posicion> obtenerPosicionesDeUnUsuario(Usuario user);
}
