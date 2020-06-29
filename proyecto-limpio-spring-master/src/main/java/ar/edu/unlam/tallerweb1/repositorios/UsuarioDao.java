package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Usuario;

public interface UsuarioDao {
	Long registrarUsuario(Usuario usuario);

	Boolean validarExistenciaEmail(String email);

	Usuario obtenerUsuarioPorId(Long id);
	
	void update(Usuario usuario);

	Usuario consultarEmailYPassDeUsuario(Usuario usuario);

	void editarUsuario(Usuario usuario);

}
