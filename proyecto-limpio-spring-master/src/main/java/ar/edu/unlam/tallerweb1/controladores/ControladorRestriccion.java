package ar.edu.unlam.tallerweb1.controladores;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Restriccion;
import ar.edu.unlam.tallerweb1.servicios.ServicioRestriccion;

@Controller
public class ControladorRestriccion {
	@Inject
	private ServicioRestriccion servicioRestriccion;

	@RequestMapping("/restricciones")
	public ModelAndView irRegistro() {
		ModelMap model = new ModelMap();
		return new ModelAndView("restricciones", model);
	}

	@RequestMapping(path = "/crearrestriccion", method = RequestMethod.GET)
	public ModelAndView restriccionesUsuario(@RequestParam(value = "nombre", required = true) String nombre) {
		ModelMap model = new ModelMap();
		Restriccion restriccion = new Restriccion();
		restriccion.setNombre(nombre);

		Long idGenerado = this.servicioRestriccion.crearRestriccion(restriccion);

		model.put("restriccion", restriccion);

		return new ModelAndView("restriccionCreada", model);
	}

	@RequestMapping(path = "/borrarrestriccion")
	public ModelAndView eliminarRestriccion(@RequestParam(value = "id") Long id) {
		ModelMap model = new ModelMap();

		Restriccion restriccionEncontrada = this.servicioRestriccion.obtenerRestriccionPorId(id);
		if (restriccionEncontrada != null) {
			this.servicioRestriccion.borrarRestriccion(restriccionEncontrada);
			model.put("estado", "eliminado");
		} else {
			model.put("estado", "no se encontro la restriccion");
		}

		return new ModelAndView("borrarRestriccion", model);
	}

}
