package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Restriccion;

public interface RestriccionDao {
	Long crearRestriccion(Restriccion restriccion);
	Restriccion obtenerRestriccionPorId(Long id);
	void borrarRestriccion(Restriccion restriccion);
}