package ar.edu.unlam.tallerweb1.repositorios;

import javax.inject.Inject;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Posicion;

@Repository
@Transactional
public class PosicionDaoImpl implements PosicionDao {
	@Inject
	private SessionFactory sesion;
	
	
	@Override
	public Long crearPosicion(Posicion posicion) {
		
		return (Long)sesion.getCurrentSession().save(posicion);
	} 
	
	@Override
	public Posicion obtenerPosicionPorId(Long id) {

		return (Posicion) sesion.getCurrentSession().get(Posicion.class, id);
	}

}
