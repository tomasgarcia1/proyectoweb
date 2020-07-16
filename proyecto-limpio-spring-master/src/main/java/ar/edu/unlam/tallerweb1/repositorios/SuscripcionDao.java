package ar.edu.unlam.tallerweb1.repositorios;

import org.hibernate.SessionFactory;

import ar.edu.unlam.tallerweb1.modelo.Suscripcion;
import ar.edu.unlam.tallerweb1.modelo.TipoSuscripcion;

public interface SuscripcionDao {

	TipoSuscripcion obtenerTipoSegunId(Long tipo);

	Long insertarSuscripcion(Suscripcion susc);

	Suscripcion obtenerSuscripcionSegunId(Long id);

	void updateSuscripcion(Suscripcion susc);

	void eliminarSuscripcion(Suscripcion susc);

	void setSesion(SessionFactory sessionFactory);

}
