package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Rol;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioRestriccion;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ControladorLogin {

	// La anotacion @Autowired indica a Spring que se debe utilizar el contructor como mecanismo de inyección de dependencias,
	// es decir, qeue lo parametros del mismo deben ser un bean de spring y el framewrok automaticamente pasa como parametro
	// el bean correspondiente, en este caso, un objeto de una clase que implemente la interface ServicioLogin,
	// dicha clase debe estar anotada como @Service o @Repository y debe estar en un paquete de los indicados en
	// applicationContext.xml
	@Inject
	private ServicioUsuario servicioLogin;
	@Inject
	private ServicioRestriccion servicioRestriccion;

	//-----------LOGIN------------
	
	@RequestMapping("/login")
	public ModelAndView irALogin() {

		ModelMap modelo = new ModelMap();
		
		Usuario usuario = new Usuario();
		modelo.put("usuario", usuario);
		
		return new ModelAndView("login", modelo);
	}
	
	//----------VALIDAR LOGIN-------------
	
	// Este metodo escucha la URL validar-login siempre y cuando se invoque con metodo http POST
	// El método recibe un objeto Usuario el que tiene los datos ingresados en el form correspondiente y se corresponde con el modelAttribute definido en el
	// tag form:form
	
	@RequestMapping(path = "/validar-login", method = RequestMethod.POST)
	public ModelAndView validarLogin(@ModelAttribute("usuario") Usuario usuario, 
			HttpServletRequest request, RedirectAttributes atributo) {
		// invoca el metodo consultarUsuario del servicio y hace un redirect a la URL /home, esto es, en lugar de enviar a una vista
		// hace una llamada a otro action a través de la URL correspondiente a ésta
		Usuario usuarioBuscado = servicioLogin.consultarEmailYPassDeUsuario(usuario);
		if (usuarioBuscado != null) {
			request.getSession(true);
			request.getSession().setAttribute("usuario",usuarioBuscado);
			
			if(usuarioBuscado.getRol() == Rol.CLIENTE) {
				if(servicioRestriccion.listarRestriccionesDeUsuario(usuarioBuscado).isEmpty())
					return new ModelAndView("redirect:/seleccionarRestricciones");
				else
					return new ModelAndView("redirect:/interno");
			} else {
				return new ModelAndView("redirect:/adminInterno");
				
			}
		} else {
			// si el usuario no existe agrega un mensaje de error en el modelo.
			atributo.addFlashAttribute("error", "Usuario o clave incorrecta");
		}
		return new ModelAndView("redirect:/login");
	}

	//----------------CERRAR SESION------------------
	
	@RequestMapping("/cerrarSesion")
	public ModelAndView cerrarSesion(HttpServletRequest request) {
		request.getSession().invalidate();
		return new ModelAndView("redirect:/home");
	}
	
	//----------------REDIRECCIONAR RAIZ A HOME------------------
	
	@RequestMapping(path = "/", method = RequestMethod.GET)
	public ModelAndView inicio(HttpServletRequest request) {
		return new ModelAndView("redirect:/home");
	}
}