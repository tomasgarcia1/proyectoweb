package ar.edu.unlam.tallerweb1.controladores;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Comida;
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
	 * Se asigna el usuario que esta activo en la sesion al pedido.
	 * Se agrega al model los datos del pedido para mostrarlos en pantalla como vista previa.
	 */
	@RequestMapping(path="/generarpedido")
	public ModelAndView vistaPedido(HttpServletRequest request) {
		Usuario user=(Usuario)request.getSession().getAttribute("usuario");
		ModelMap model = new ModelMap();
		Pedido nuevoPedido=new Pedido();
		nuevoPedido.setUsuario(user);
		Comida c1=new Comida();
		Comida c2=new Comida();
		Comida c3=new Comida();
		List<Comida> comidas=new ArrayList();
		c1.setId((long) 1);
		c2.setId((long) 2);
		c3.setId((long) 3);
		c1.setPrecio(12.5);
		c2.setPrecio(30.5);
		c3.setPrecio(40.5);
		comidas.add(c1);
		comidas.add(c2);
		comidas.add(c3);
		nuevoPedido.setComidas(comidas);
		nuevoPedido.setPrecio(servicioPedido.calcularImporteTotal(nuevoPedido));
		nuevoPedido.setEstado(Estado.ACEPTADO);
		Long idPedido=servicioPedido.crearPedido(nuevoPedido);
		model.put("id", idPedido);
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
	 * Se muestra por pantalla el numero de pedido, dado por el ID generado en crearPedido().
	 */
	@RequestMapping(path="/pagarpedido", method=RequestMethod.POST)
	public ModelAndView pagarPedido(@ModelAttribute("pedido") Pedido pedido, HttpServletRequest request) {
		Usuario user=(Usuario)request.getSession().getAttribute("usuario");
		ModelMap model = new ModelMap();
		Pedido pedido2=servicioPedido.buscarPedidoPorId(pedido.getId());
		pedido2.setEstado(Estado.PROCESO);
		servicioPedido.actualizarPedido(pedido2);
		model.put("pedido", pedido2);
		return new ModelAndView("pedidoConfirmado", model);
	}
	
	/*
	 * Se recibe por parametro el ID del pedido que queremos cancelar.
	 * Si es distinto de null, se realiza la accion sobre el pedido existente.
	 */
	@RequestMapping(path="/cancelarpedido", method=RequestMethod.POST)
	public ModelAndView cancelarPedidoPorId(@ModelAttribute("pedido") Pedido pedido, HttpServletRequest request) {
		Usuario user=(Usuario)request.getSession().getAttribute("usuario");
		if(servicioPedido.buscarPedidoPorId(pedido.getId())!=null)
			servicioPedido.cancelarPedido(pedido.getId());
			
		return new ModelAndView("redirect:/home");
	}
	
	/*@RequestMapping(path="/mispedidos")
	public ModelAndView listarPedidos(HttpServletRequest request) {
		ModelMap model = new ModelMap();
		
		//model.put("pedidos", listaPedidos);
		return new ModelAndView("misPedidos", model);
	}*/
}
