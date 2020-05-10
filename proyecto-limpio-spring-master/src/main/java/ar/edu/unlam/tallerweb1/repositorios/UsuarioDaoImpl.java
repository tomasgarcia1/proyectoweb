package ar.edu.unlam.tallerweb1.repositorios;

import javax.inject.Inject;

import org.hibernate.SessionFactory;
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
}
