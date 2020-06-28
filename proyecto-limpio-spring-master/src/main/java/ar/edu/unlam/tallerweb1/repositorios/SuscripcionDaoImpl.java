package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Suscripcion;
import ar.edu.unlam.tallerweb1.modelo.TipoSuscripcion;

@Repository
@Transactional
public class SuscripcionDaoImpl implements SuscripcionDao {

	@Inject
	private SessionFactory sesion;

	@Override
	public TipoSuscripcion obtenerTipoSegunId(Long tipo) {
		return (TipoSuscripcion) sesion.getCurrentSession().get(TipoSuscripcion.class, tipo);
	}
	
	@Override
	public Suscripcion insertarSuscripcion(Suscripcion susc) {
		return (Suscripcion) sesion.getCurrentSession().save(susc);
	}

	
}
