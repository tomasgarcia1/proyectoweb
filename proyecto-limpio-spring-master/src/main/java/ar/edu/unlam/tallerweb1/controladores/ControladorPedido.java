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

@Controller
public class ControladorPedido {
	@Inject
	private ServicioPedido servicioPedido;
	
	/*
	 * Se recibe el usuario activo en la sesion para obtener sus restricciones mediante su ID.
	 * Se generan tres pedidos con la funcion generarPedidoPorRestricciones, tomando en cuenta las restricciones del usuario.
	 * Cada pedido representa una opcion que puede elegir el cliente, solo puede elegir uno.
	 * Estos pedidos se almacenan en la sesion actual bajo la key correspondiente al pedido, esto se hace
	 * para persistir el pedido entre las vistas.
	 * Se insertan en el modelo los pedidos para ser mostrados en la vista menuSugerido.
	 */
	@RequestMapping(path = "/menuSugerido")
	public ModelAndView irAMenuSugerido(HttpServletRequest request) {
		Usuario user=(Usuario)request.getSession().getAttribute("usuario");
		ModelMap model = new ModelMap();
		Pedido pedido1=servicioPedido.generarPedidoPorRestricciones(user.getId());
		Pedido pedido2=servicioPedido.generarPedidoPorRestricciones(user.getId());
		Pedido pedido3=servicioPedido.generarPedidoPorRestricciones(user.getId());
		//HttpSession session = request.getSession(true);
		request.getSession().setAttribute("pedido1", pedido1);
		request.getSession().setAttribute("pedido2", pedido2);
		request.getSession().setAttribute("pedido3", pedido3);
		model.put("pedido1", pedido1);
		model.put("pedido2", pedido2);
		model.put("pedido3", pedido3);
		return new ModelAndView("menuSugerido", model);
	}
	
	/*
	 * Se recibe como parametro un string con la opcion elegida en la vista menuSugerido.
	 * Esta opcion es evaluada para asignarle a la variable nuevoPedido el pedido acorde a lo elegido.
	 * Se asigna el usuario que esta activo en la sesion al pedido.
	 * Se cambia el estado del pedido.
	 * Se agrega el pedido a la base de datos.
	 * Se agrega al model los datos del pedido para mostrarlos en pantalla como vista previa.
	 */
	@RequestMapping(path="/generarpedido", method=RequestMethod.GET)
	public ModelAndView vistaPedido(@RequestParam(value="pedido", required=true)
	String pedido, HttpServletRequest request) {
		Usuario user=(Usuario)request.getSession().getAttribute("usuario");
		ModelMap model = new ModelMap();
		Pedido nuevoPedido=new Pedido();
		switch(pedido)
		{
			case "1":	nuevoPedido=(Pedido)request.getSession().getAttribute("pedido1");
					break;
			case "2":	nuevoPedido=(Pedido)request.getSession().getAttribute("pedido2");
					break;
			case "3":	nuevoPedido=(Pedido)request.getSession().getAttribute("pedido3");
					break;
		}
		nuevoPedido.setUsuario(user);
		nuevoPedido.setEstado(Estado.ACEPTADO);
		Long idPedido=servicioPedido.crearPedido(nuevoPedido);
		model.put("id", idPedido);
		model.put("precio", nuevoPedido.getPrecio());
		model.put("comidas", nuevoPedido.getComidas());
		model.put("pedido", nuevoPedido);
		request.getSession().removeAttribute("pedido1");
		request.getSession().removeAttribute("pedido2");
		request.getSession().removeAttribute("pedido3");
		return new ModelAndView("pedidoPorConfirmar", model);
	}
	
	/*
	 * Se le envia el pedido creado y seteado anteriormente, y se le otorga el estado de "PAGADO".
	 * Se muestra por pantalla el numero de pedido, dado por el ID generado en generarPedido().
	 */
	@RequestMapping(path="/pagarpedido", method=RequestMethod.POST)
	public ModelAndView pagarPedido(@ModelAttribute("pedido") Pedido pedido, HttpServletRequest request) {
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

	/*@RequestMapping("/crearPedido")
	public ModelAndView crearPedido() {
		ModelMap model = new ModelMap();
		Pedido nuevoPedido=new Pedido();
		Long idGenerado = servicioPedido.crearPedido(nuevoPedido);
		model.put("pedido", nuevoPedido);
		return new ModelAndView("pedidoRealizado", model);
	}*/
}
