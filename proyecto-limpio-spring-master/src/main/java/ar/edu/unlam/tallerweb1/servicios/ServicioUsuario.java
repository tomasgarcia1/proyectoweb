package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Usuario;

public interface ServicioUsuario {
	List<String> validarUsuario(Usuario usuario, String restricciones);
	
	Long registrarUsuario(Usuario usuario, String restricciones);

	Boolean validarExistenciaEmail(String email);

	Boolean validarFormatoEmail(String email);

	String encriptarPassword(String password);

	Double obtenerCaloriasPorId(Long id);

	Double calcularCaloriasDiarias(Usuario usuario);

	void update(Usuario usuario);

	Usuario consultarEmailYPassDeUsuario(Usuario usuario);

}
