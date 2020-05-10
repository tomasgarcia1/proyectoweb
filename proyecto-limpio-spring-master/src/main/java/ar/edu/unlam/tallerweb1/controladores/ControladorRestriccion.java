package ar.edu.unlam.tallerweb1.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorRestriccion {
	@RequestMapping("/limitantes")
	public ModelAndView irRegistro() {
		ModelMap model = new ModelMap();
		return new ModelAndView("limitantes", model);
	}
}

