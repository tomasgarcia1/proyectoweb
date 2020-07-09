package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Restriccion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

public interface ServicioUsuario {
	List<String> validarUsuario(Usuario usuario, List<Restriccion> restricciones);
	
	Long registrarUsuario(Usuario usuario, List<Restriccion> restricciones);

	Boolean validarExistenciaEmail(String email);

	Boolean validarFormatoEmail(String email);

	String encriptarPassword(String password);

	Double obtenerCaloriasPorId(Long id);

	Double calcularCaloriasDiarias(Usuario usuario);

	void update(Usuario usuario);

	Usuario consultarEmailYPassDeUsuario(Usuario usuario);
	
	List<String> validarUsuarioEditar(Usuario usuario);
	 
	void editarUsuario(Usuario usuario);

	Usuario obtenerUsuarioPorId(Long id);

}
