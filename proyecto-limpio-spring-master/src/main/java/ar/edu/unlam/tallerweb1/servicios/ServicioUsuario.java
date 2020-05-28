package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Usuario;

public interface ServicioUsuario {
	Long registrarUsuario(Usuario usuario);

	Boolean validarExistenciaEmail(String email);

	Boolean validarFormatoEmail(String email);

	String encriptarPassword(String password);
	Double obtenerCaloriasPorId(Long id);
}
