package ar.edu.unlam.tallerweb1.controladores;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Pedido;
import ar.edu.unlam.tallerweb1.servicios.ServicioPedido;

@Controller
public class ControladorPedido {
	@Inject
	private ServicioPedido servicioPedido;
	
	@RequestMapping("/crearPedido")
	public ModelAndView crearPedido() {
		ModelMap model = new ModelMap();
		Pedido nuevoPedido=new Pedido();
		Long idGenerado = servicioPedido.crearPedido(nuevoPedido);
		model.put("pedido", nuevoPedido);
		return new ModelAndView("pedidoRealizado", model);
	}
	
	@RequestMapping(path = "/menuSugerido", method = RequestMethod.GET)
	public ModelAndView irAMenuSugerido() {
		ModelMap modelo = new ModelMap();
		return new ModelAndView("menuSugerido", modelo);
	}
}
