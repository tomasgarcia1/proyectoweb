package ar.edu.unlam.tallerweb1.repositorios;

import org.hibernate.SessionFactory;

import ar.edu.unlam.tallerweb1.modelo.Pedido;
import ar.edu.unlam.tallerweb1.modelo.Posicion;

public interface PosicionDao {
	Long crearPosicion(Posicion posicion);

	Posicion obtenerPosicionPorId(Long id);
	void setSesion(SessionFactory sesion);
}
