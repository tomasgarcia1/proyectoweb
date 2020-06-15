package ar.edu.unlam.tallerweb1.controladores;
 
import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;

@Controller
public class ControladorRegistro { 
	@Inject
	private ServicioUsuario servicioUsuario;

	/*@RequestMapping("/registro")
	public ModelAndView irRegistro() {
		ModelMap model = new ModelMap();
		return new ModelAndView("registro", model);
	}

	/*
	 * Controlador para registro de usuario, se reciben por parametro e-mail,
	 * password y rol (momentaneamente). Se valida la existencia del e-mail en un
	 * usuario. Si existe, el usuario no se registra. Se valida el formato del
	 * e-mail. Debe tener un @ y un punto seguido de letras. Se crearon interfaces,
	 * clases de registro y guardado de usuarios en servicios y repositorios.
	 */
	@RequestMapping(path = "/registrar", method = RequestMethod.GET)
	public ModelAndView registrarUsuario(@RequestParam(value = "email", required = true) String email,
			@RequestParam(value = "password", required = true) String password,
			@RequestParam(value = "rol") String rol) {
		ModelMap modeloRegistro = new ModelMap();
		Usuario nuevoUsuario = new Usuario();
		if ((!servicioUsuario.validarExistenciaEmail(email)) && servicioUsuario.validarFormatoEmail(email)) {
			nuevoUsuario.setEmail(email);
			/* se llama al metodo encriptarPassword para guardar en la BD la clave ya encriptada,
			 * esto es para no tener almacenada la clave original.
			 */
			nuevoUsuario.setPassword(servicioUsuario.encriptarPassword(password));
			// nuevoUsuario.setRol(rol);
			Long idGenerado = servicioUsuario.registrarUsuario(nuevoUsuario);
			modeloRegistro.put("user", nuevoUsuario);
			return new ModelAndView("registroCorrecto", modeloRegistro);
		}
		modeloRegistro.put("email", email);
		return new ModelAndView("errorRegistro", modeloRegistro);
	}
}