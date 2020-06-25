package ar.edu.unlam.tallerweb1.controladores;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Suscripcion;
import ar.edu.unlam.tallerweb1.modelo.TipoSuscripcion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioSuscripcion;

@Controller
public class ControladorSuscripcion {

	@Inject
	private ServicioSuscripcion servicioSuscripcion;
	
	@RequestMapping("/suscripciones")
	public ModelAndView verSuscripciones() {
		ModelMap modelo = new ModelMap();
		return new ModelAndView("verSuscripciones", modelo);
	}
	
	@RequestMapping("/obtenerSuscripcion")
	public ModelAndView obtenerSuscripcion(@RequestParam(value = "tipo"
	, required = true) Long id, HttpServletRequest request) {
		
		Usuario user = (Usuario) request.getSession().getAttribute("usuario");
		if(user != null) {
		ModelMap modelo = new ModelMap();
		TipoSuscripcion susc = servicioSuscripcion.obtenerDatosSegunTipo(id);
		Double precio = susc.getPrecio();
		String tipo = susc.getNombre();
		Long idTipo = susc.getId();
		
		if(user.getSuscripcion()!=null) {
			if(user.getSuscripcion().getEstado()) {
				modelo.put("mensaje", "Usted ya posee una suscripcion");
			}
		}
		
		modelo.put("precio", precio);
		modelo.put("tipo", tipo);
		modelo.put("id", idTipo);
		
		return new ModelAndView("obtenerSuscripcion", modelo);
		
		}else {
			return new ModelAndView("redirect:/login");
		}
	}
}
