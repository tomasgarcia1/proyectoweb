package ar.edu.unlam.tallerweb1.repositorios;

import javax.inject.Inject;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Restriccion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

@Repository
@Transactional
public class RestriccionDaoImpl implements RestriccionDao {
	@Inject
	SessionFactory sesion;
	
	@Override
	public Long crearRestriccion(Restriccion restriccion) {
		Long idGenerado= (Long)sesion.getCurrentSession().save(restriccion);
		return idGenerado;
	}

}


