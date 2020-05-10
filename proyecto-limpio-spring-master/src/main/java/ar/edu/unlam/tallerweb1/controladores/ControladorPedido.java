package ar.edu.unlam.tallerweb1.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorPedido {
	
	@RequestMapping("/crearPedido")
	public ModelAndView crearPedido() {
		ModelMap model = new ModelMap();
		return new ModelAndView("crearpedido", model);
	}
}
