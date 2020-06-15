package ar.edu.unlam.tallerweb1.controladores;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Rol;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

@Controller
public class ControladorHome {
	// Escucha la URL /home por GET, y redirige a una vista.
	@RequestMapping(path = "/home", method = RequestMethod.GET)
	public ModelAndView irAHome() { 
		ModelMap modelo = new ModelMap();
		return new ModelAndView("home", modelo); 
	}

	@RequestMapping(path = "/interno")
	public ModelAndView interno(HttpServletRequest request) {
		Usuario user = (Usuario) request.getSession().getAttribute("usuario");

		if (user != null) {
		ModelMap modelo = new ModelMap();
		return new ModelAndView("interno", modelo);
		}else {
			return new ModelAndView("redirect:/home");
		}
	}
	
	@RequestMapping(path = "/adminInterno")
	public ModelAndView adminInterno(HttpServletRequest request) {
		Usuario user = (Usuario) request.getSession().getAttribute("usuario");

		if (user != null && user.getRol()==Rol.ADMINISTRADOR) {
		ModelMap modelo = new ModelMap();
		return new ModelAndView("adminInterno", modelo);
		}else {
			return new ModelAndView("redirect:/home");
		}
	}
}
