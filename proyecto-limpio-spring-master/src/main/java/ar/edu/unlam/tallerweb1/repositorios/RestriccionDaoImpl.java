package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Restriccion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

@Repository 
@Transactional
public class RestriccionDaoImpl implements RestriccionDao {
	@Inject
	private SessionFactory sesion;

	@Override
	public Long crearRestriccion(Restriccion restriccion) {

		return (Long) sesion.getCurrentSession().save(restriccion);

	}

	@Override
	public void borrarRestriccion(Restriccion restriccion) {

		sesion.getCurrentSession().delete(restriccion);

	}

	@Override
	public Restriccion obtenerRestriccionPorId(Long id) {


		return (Restriccion) sesion.getCurrentSession().get(Restriccion.class, id);
		
	}

	@Override
	public List<Restriccion> obtenerRestricciones() {

		final Session session = sesion.getCurrentSession();
		
		List <Restriccion> r=session.createCriteria(Restriccion.class).list();
		
		return r;
	}
	
	public List<Restriccion> listarRestriccionesDeUsuario(Usuario usuario)
	{
		return sesion.getCurrentSession().createCriteria(Restriccion.class)
				.createAlias("usuarios", "usuariojoin")
				.add(Restrictions.eq("usuariojoin.id", usuario.getId())).list();
	}

}
