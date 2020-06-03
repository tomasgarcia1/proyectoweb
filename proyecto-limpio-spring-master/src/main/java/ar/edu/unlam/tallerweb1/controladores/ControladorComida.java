package ar.edu.unlam.tallerweb1.controladores;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Comida;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioComida;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;

@Controller
public class ControladorComida {
	@Inject
	private ServicioComida servicioComida;

	@Inject
	private ServicioUsuario servicioUsuario;

	@RequestMapping("/crearComida")
	public ModelAndView crearComida(@RequestParam(value = "nombre", required = true) String nombre,
			@RequestParam(value = "descripcion", required = true) String descripcion,
			@RequestParam(value = "calorias", required = true) Double calorias) {
		ModelMap model = new ModelMap();
		Comida comida = new Comida();
		comida.setNombre(nombre);
		comida.setDescripcion(descripcion);
		comida.setCalorias(calorias);
		Long idGenerado = servicioComida.crearComida(comida);
		model.put("comida", comida);
		return new ModelAndView("nuevaComida", model);

	}

	@RequestMapping("/eliminarPorId")
	public ModelAndView eliminarPorId(@RequestParam(value = "id", required = true) Long id) {
		servicioComida.borrar(servicioComida.obtenerPorId(id));
		return new ModelAndView("comidaborrada");
	}

	@RequestMapping("/sugerirComidaPorCalorias")
	public ModelAndView sugerirComidaPorCalorias(HttpServletRequest request) {
		
		Usuario user=(Usuario)request.getSession().getAttribute("usuario");
		
		if(user!=null) {
		Long id = user.getId();

		Double caloriasDiarias = servicioUsuario.obtenerCaloriasPorId(id);

		Comida desayunoSugerido = servicioComida.sugerirDesayunoPorCalorias(caloriasDiarias);
		Comida almuerzoSugerido = servicioComida.sugerirAlmuerzoPorCalorias(caloriasDiarias);
		Comida cenaSugerida = servicioComida.sugerirCenaPorCalorias(caloriasDiarias);

		ModelMap model = new ModelMap();

		model.put("desayuno", desayunoSugerido);
		model.put("almuerzo", almuerzoSugerido);
		model.put("cena", cenaSugerida);

		return new ModelAndView("sugerirComidaPorCalorias", model);
		
		}else{
		return new ModelAndView("redirect:/home");
		}
	}
	
	@RequestMapping("/sugerirComidaPorRestricciones")
	public ModelAndView sugerirComidaPorRestricciones(@RequestParam(value = "id") Long id) {
		
		Comida desayunoSugerido = servicioComida.sugerirDesayunoPorRestricciones(id);
		Comida almuerzoSugerido = servicioComida.sugerirAlmuerzoPorRestricciones(id);
		Comida cenaSugerida = servicioComida.sugerirCenaPorRestricciones(id);
		
		ModelMap model = new ModelMap();

		model.put("desayuno",desayunoSugerido);
		model.put("almuerzo",almuerzoSugerido);
		model.put("cena",cenaSugerida);
	
		return new ModelAndView("sugerirComidaPorRestricciones", model);
	
}
}