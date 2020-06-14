package ar.edu.unlam.tallerweb1.controladores;

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

import com.mercadopago.resources.Preference;

import ar.edu.unlam.tallerweb1.modelo.Comida;
import ar.edu.unlam.tallerweb1.modelo.Estado;
import ar.edu.unlam.tallerweb1.modelo.Pedido;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioMP;
import ar.edu.unlam.tallerweb1.servicios.ServicioPedido;

@Controller
public class ControladorPedido {
	
	private ServicioMP servicioMP= new ServicioMP();
	
	@Inject
	private ServicioPedido servicioPedido;
	
	/*
	 * Se recibe el usuario activo en la sesion para obtener sus restricciones mediante su ID.
	 * Se generan tres listas de comida con la funcion generarComidasPorRestricciones, 
	 * tomando en cuenta las restricciones del usuario.
	 * Cada lista representa una opcion de pedido que puede elegir el cliente, solo puede elegir una.
	 * Se genera un String con los ID de las comidas que tiene cada lista.
	 * Se insertan en el modelo las listas para ser mostrados en la vista menuSugerido,
	 * y los Strings se mandan para ser usados como valores en el formulario.
	 * La vista de retorno es el menu con las 3 opciones de sugerencias.
	 */
	@RequestMapping(path = "/menuSugerido")
	public ModelAndView irAMenuSugerido(HttpServletRequest request) {
		Usuario user=(Usuario)request.getSession().getAttribute("usuario");
		ModelMap model = new ModelMap();
		
		List<Comida> opcion1=servicioPedido.generarComidasPorRestricciones(user.getId());
		List<Comida> opcion2=servicioPedido.generarComidasPorRestricciones(user.getId());
		List<Comida> opcion3=servicioPedido.generarComidasPorRestricciones(user.getId());
		String idComidas1=servicioPedido.concatenarIdComidas(opcion1);
		String idComidas2=servicioPedido.concatenarIdComidas(opcion2);
		String idComidas3=servicioPedido.concatenarIdComidas(opcion3);
		
		model.put("comidas1", opcion1);
		model.put("comidas2", opcion2);
		model.put("comidas3", opcion3);
		model.put("idcomidas1", idComidas1);
		model.put("idcomidas2", idComidas2);
		model.put("idcomidas3", idComidas3);
		return new ModelAndView("menuSugerido", model);
	}
	/*
	 * Tiene el mismo funcionamiento que irAMenuSugerido, con la diferencia de que usa el metodo generarComidasPorCalorias,
	 * donde se recibe como parametro el usuario para obtener las calorias diarias.
	 */
	@RequestMapping(path = "/menuCalorias")
	public ModelAndView irAMenuCalorias(HttpServletRequest request) {
		Usuario user=(Usuario)request.getSession().getAttribute("usuario");
		ModelMap model = new ModelMap();
		
		List<Comida> opcion1=servicioPedido.generarComidasPorCalorias(user);
		List<Comida> opcion2=servicioPedido.generarComidasPorCalorias(user);
		List<Comida> opcion3=servicioPedido.generarComidasPorCalorias(user);
		String idComidas1=servicioPedido.concatenarIdComidas(opcion1);
		String idComidas2=servicioPedido.concatenarIdComidas(opcion2);
		String idComidas3=servicioPedido.concatenarIdComidas(opcion3);
		
		model.put("comidas1", opcion1);
		model.put("comidas2", opcion2);
		model.put("comidas3", opcion3);
		model.put("idcomidas1", idComidas1);
		model.put("idcomidas2", idComidas2);
		model.put("idcomidas3", idComidas3);
		return new ModelAndView("menuCalorias", model);
	}
	/*
	 * Se recibe como parametro un String con la opcion elegida en la vista menuSugerido.
	 * Esta opcion es mandada como parametro del metodo generarPedidoPorIdComidas,
	 * que se encarga de generar un pedido que contiene las comidas que eligio el usuario.
	 * Se asigna el usuario que esta activo en la sesion al pedido.
	 * Se cambia el estado del pedido.
	 * Se agrega el pedido a la base de datos.
	 * Se agrega al model los datos del pedido para mostrarlos en pantalla como vista previa.
	 */
	@RequestMapping(path="/generarpedido", method=RequestMethod.POST)
	public ModelAndView vistaPedido(@ModelAttribute("idComidas") String idComidas, HttpServletRequest request) {
		Usuario user=(Usuario)request.getSession().getAttribute("usuario");
		ModelMap model = new ModelMap();
		Pedido nuevoPedido=new Pedido();
		
		nuevoPedido=servicioPedido.generarPedidoPorIdComidas(idComidas);
		nuevoPedido.setUsuario(user);
		nuevoPedido.setEstado(Estado.ACEPTADO);
		Long idPedido=servicioPedido.crearPedido(nuevoPedido);
		//Mercado pago
		Preference p = servicioMP.checkout(user,nuevoPedido);
		model.put("preference",p);
		
		model.put("id", idPedido);
		model.put("precio", nuevoPedido.getPrecio());
		model.put("comidas", nuevoPedido.getComidas());
		model.put("pedido", nuevoPedido);
		return new ModelAndView("pedidoPorConfirmar", model);
	}
	/*
	 * Se le envia el pedido creado y seteado anteriormente, y se le otorga el estado de "PAGADO".
	 * Se muestra por pantalla el numero de pedido, dado por el ID generado en generarPedido().
	 */
	@RequestMapping(path="/pagarpedido", method=RequestMethod.POST)
	public ModelAndView pagarPedido(@ModelAttribute("pedido") Pedido pedido , HttpServletRequest request) {
		ModelMap model = new ModelMap();
		
		Pedido nuevoPedido=servicioPedido.buscarPedidoPorId(pedido.getId());
		nuevoPedido.setEstado(Estado.PROCESO);
		servicioPedido.actualizarPedido(nuevoPedido);
		
		model.put("pedido", nuevoPedido);
		return new ModelAndView("pedidoRealizado", model);
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
	
	
	@RequestMapping(path="/mispedidos")
	public ModelAndView listarPedidos(HttpServletRequest request) {
		ModelMap model = new ModelMap();
		Usuario user=(Usuario)request.getSession().getAttribute("usuario");
		List<Pedido> listaPedidos=servicioPedido.listarPedidosPorUsuario(user);
		model.put("pedidos", listaPedidos);
		return new ModelAndView("listapedidos", model);
	}
	@RequestMapping(path="/detallepedido")
	public ModelAndView verDetallePedido(@RequestParam(value="id", required=true) Long id,HttpServletRequest request)
	{
		ModelMap model = new ModelMap();
		Pedido pedido=servicioPedido.buscarPedidoPorId(id);
		model.put("pedido",pedido);
		model.put("cancelado", Estado.CANCELADO);
		model.put("enviado", Estado.ENVIADO);
		return new ModelAndView("detallepedido", model);
	}
	@RequestMapping(path="/checkout")
	public ModelAndView checkout(HttpServletRequest request)
	{
		Usuario user=(Usuario)request.getSession().getAttribute("usuario");
		ModelMap model = new ModelMap();
		Preference p = servicioMP.checkout(user);
		model.put("preference",p);
		return new ModelAndView("checkout", model);
	}
	
}
