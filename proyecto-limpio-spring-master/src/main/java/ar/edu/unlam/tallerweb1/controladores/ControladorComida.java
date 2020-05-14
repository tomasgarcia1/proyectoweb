package ar.edu.unlam.tallerweb1.controladores;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Comida;
import ar.edu.unlam.tallerweb1.modelo.Pedido;
import ar.edu.unlam.tallerweb1.servicios.ServicioComida;
import ar.edu.unlam.tallerweb1.servicios.ServicioPedido;

@Controller
public class ControladorComida {
	@Inject
	private ServicioComida servicioComida;
	
	@RequestMapping("/crearComida")
	public ModelAndView crearComida(@RequestParam(value="nombre", required=true) String nombre,
			@RequestParam(value="descripcion", required=true) String descripcion,
			@RequestParam(value="calorias", required=true) Double calorias) {
		ModelMap model = new ModelMap();
		Comida comida=new Comida();
		comida.setNombre(nombre);
		comida.setDescripcion(descripcion);
		comida.setCalorias(calorias);
		Long idGenerado = servicioComida.crearComida(comida);
		model.put("comida", comida);
		return new ModelAndView("nuevaComida", model);
	}	
	@RequestMapping("/eliminarPorId")
	public ModelAndView eliminarPorId(@RequestParam(value="id", required=true) Long id){
		servicioComida.borrar(servicioComida.obtenerPorId(id));
		return new ModelAndView("comidaborrada");
	}

}

}

