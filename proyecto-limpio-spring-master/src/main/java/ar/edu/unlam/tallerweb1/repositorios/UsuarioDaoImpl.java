package ar.edu.unlam.tallerweb1.repositorios;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Usuario;

@Repository
@Transactional
public class UsuarioDaoImpl implements UsuarioDao {
	@Inject
	private SessionFactory sesion;
	@Override
	public Long registrarUsuario(Usuario usuario) {
		Long idGenerado=(Long) sesion.getCurrentSession().save(usuario);
		return idGenerado;
	}
	@Override
	public void update(Usuario usuario) {
		sesion.getCurrentSession().update(usuario);
	}
	@Override
	public Boolean validarExistenciaEmail(String email) {

		// Se obtiene la sesion asociada a la transaccion iniciada en el servicio que invoca a este metodo y se crea un criterio
		// de busqueda de Usuario donde el email y password sean iguales a los del objeto recibido como parametro
		// uniqueResult da error si se encuentran más de un resultado en la busqueda.
		final Session session = sesion.getCurrentSession();
		Usuario usuarioEncontrado = (Usuario) session.createCriteria(Usuario.class)
				.add(Restrictions.eq("email", email)).uniqueResult();
		if(usuarioEncontrado!=null)
			return true;
		return false;
	}
	
	@Override
	public Usuario obtenerUsuarioPorId (Long id) {
		return sesion.getCurrentSession().get(Usuario.class, id);
	}
	
}
