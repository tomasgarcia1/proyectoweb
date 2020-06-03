package ar.edu.unlam.tallerweb1.controladores;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Estado;
import ar.edu.unlam.tallerweb1.modelo.Pedido;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioPedido;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;

@Controller
public class ControladorPedido {
	@Inject
	private ServicioPedido servicioPedido;
	@Inject
	private ServicioUsuario servicioUsuario;
	
	/*@RequestMapping("/crearPedido")
	public ModelAndView crearPedido() {
		ModelMap model = new ModelMap();
		Pedido nuevoPedido=new Pedido();
		Long idGenerado = servicioPedido.crearPedido(nuevoPedido);
		model.put("pedido", nuevoPedido);
		return new ModelAndView("pedidoRealizado", model);
	}*/
	
	/*
	 * Recibe el pedido seleccionado en la lista anteriormente y se le asigna el importe final + el estado de CREADO.
	 * Se agrega al model los datos del pedido para mostrarlos en pantalla como vista previa.
	 */
	@RequestMapping(path="/crearpedido", method = RequestMethod.POST)
	public ModelAndView vistaPedido(@ModelAttribute("pedido")Pedido pedido,
			HttpServletRequest request) {
		Usuario user=(Usuario)request.getSession().getAttribute("usuario");
		ModelMap model = new ModelMap();
		Pedido nuevoPedido=pedido;
		nuevoPedido.setPrecio(servicioPedido.calcularImporteTotal(nuevoPedido));
		nuevoPedido.setEstado(Estado.ACEPTADO);
		model.put("id", nuevoPedido.getId());
		model.put("precio", nuevoPedido.getPrecio());
		model.put("comidas", nuevoPedido.getComidas());
		model.put("pedido", nuevoPedido);
		return new ModelAndView("pedidoPorConfirmar", model);
	}
	
	@RequestMapping(path = "/menuSugerido", method = RequestMethod.GET)
	public ModelAndView irAMenuSugerido() {
		ModelMap modelo = new ModelMap();
		return new ModelAndView("menuSugerido", modelo);
	}
	/*
	 * Se le envia el pedido creado y seteado anteriormente, y se le otorga el estado de "PAGADO".
	 * Se asigna el usuario que esta activo en la sesion al pedido.
	 * Se muestra por pantalla el numero de pedido, dado por el ID generado en crearPedido().
	 */
	@RequestMapping(path="/pagarpedido", method = RequestMethod.POST)
	public ModelAndView pagarPedido(@ModelAttribute("pedido")Pedido pedido, HttpServletRequest request) {
		Usuario user=(Usuario)request.getSession().getAttribute("usuario");
		ModelMap model = new ModelMap();
		pedido.setEstado(Estado.ACEPTADO);
		pedido.setUsuario(user);
		Long idPedido=servicioPedido.crearPedido(pedido);
		model.put("id", idPedido);
		model.put("pedido", pedido);
		
		return new ModelAndView("pedidoConfirmado", model);
	}
	
	/*
	 * Se recibe por parametro el ID del pedido que queremos cancelar.
	 * Si es distinto de null, se realiza la accion sobre el pedido existente.
	 */
	@RequestMapping(path="/cancelarpedido", method = RequestMethod.POST)
	public ModelAndView cancelarPedidoPorId(@RequestParam(value="id", required=true) Long id, HttpServletRequest request) {
		Usuario user=(Usuario)request.getSession().getAttribute("usuario");
		if(servicioPedido.buscarPedidoPorId(id)!=null)
			servicioPedido.cancelarPedido(id);
			
		return new ModelAndView("redirect:/misPedidos");
	}
}
