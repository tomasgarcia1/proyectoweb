package ar.edu.unlam.tallerweb1.controladores;

import java.util.Arrays;
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
import ar.edu.unlam.tallerweb1.modelo.Rol;
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
		List<Comida> comidasPedidas = servicioPedido.listarComidasPedidas(user.getId());
		String idComidas1=servicioPedido.concatenarIdComidas(opcion1);
		String idComidas2=servicioPedido.concatenarIdComidas(opcion2);
		String idComidas3=servicioPedido.concatenarIdComidas(opcion3);
		
		model.put("comidas1", opcion1);
		model.put("comidas2", opcion2);
		model.put("comidas3", opcion3);
		model.put("idcomidas1", idComidas1);
		model.put("idcomidas2", idComidas2);
		model.put("idcomidas3", idComidas3);
		model.put("comidasPedidas", comidasPedidas);
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
		ModelMap model = new ModelMap();
		Pedido nuevoPedido=new Pedido();
		nuevoPedido=servicioPedido.generarPedidoPorIdComidas(idComidas);
		
		Usuario user=(Usuario)request.getSession().getAttribute("usuario");
		//Mercado pago
		Preference p = servicioMP.checkout(user,nuevoPedido);
		model.put("preference",p);
		
		String idLista=idComidas;
		model.put("id", idLista);
		model.put("precio", nuevoPedido.getPrecio());
		model.put("comidas", nuevoPedido.getComidas());
		return new ModelAndView("pedidoPorConfirmar", model);
	}
	/*
	 * Se le envia el pedido creado y seteado anteriormente, y se le otorga el estado de "PAGADO".
	 * Se muestra por pantalla el numero de pedido, dado por el ID generado en generarPedido().
	 */
	@RequestMapping(path="/pagarpedido", method=RequestMethod.GET)
	public ModelAndView pagarPedido(/*@ModelAttribute("id")*/@RequestParam(value="id") String id,@RequestParam(value="payment_status") String estado, HttpServletRequest request) {
		ModelMap model = new ModelMap();
		Usuario user=(Usuario)request.getSession().getAttribute("usuario");
		Pedido nuevoPedido=new Pedido();
		
		nuevoPedido=servicioPedido.generarPedidoPorIdComidas(id);
		//Estado proveniente de mercado pago
		if(estado.equals("approved")){
		nuevoPedido.setEstado(Estado.PROCESO);
		}else {
			nuevoPedido.setEstado(Estado.CANCELADO);
		}
		nuevoPedido.setUsuario(user);
		Long idPedido=servicioPedido.crearPedido(nuevoPedido);
		nuevoPedido.setId(idPedido);
		
		model.put("pedido", nuevoPedido);
		return new ModelAndView("pedidoRealizado", model);
	}
	/*
	 * Se recibe por parametro el ID del pedido que queremos cancelar.
	 * Si es distinto de null, se realiza la accion sobre el pedido existente.
	 */
	@RequestMapping(path="/cancelarpedido", method=RequestMethod.GET)
	public ModelAndView cancelarPedidoPorId(@RequestParam(value="id", required=true) String id, HttpServletRequest request) {
		if(servicioPedido.buscarPedidoPorId(Long.parseLong(id))!=null)
			servicioPedido.cancelarPedido(Long.parseLong(id));
			
		return new ModelAndView("redirect:/home");
	}
	
	
	@RequestMapping(path="/mispedidos")
	public ModelAndView listarPedidos(HttpServletRequest request) {
		ModelMap model = new ModelMap();
		Usuario user=(Usuario)request.getSession().getAttribute("usuario");
		List<Pedido> listaPedidos=servicioPedido.listarPedidosPorUsuario(user);
		model.put("pedidos", listaPedidos);
		model.put("usuario", user);
		return new ModelAndView("listapedidos", model);
	}
	
	@RequestMapping(path="/detallepedido")
	public ModelAndView verDetallePedido(@RequestParam(value="id", required=true) Long id,HttpServletRequest request)
	{
		ModelMap model = new ModelMap();
		Pedido pedido=servicioPedido.buscarPedidoPorId(id);
		Usuario user=(Usuario)request.getSession().getAttribute("usuario");
		List<Estado> estados=Arrays.asList(Estado.values());
		
		model.put("pedido",pedido);
		model.put("usuario", user);
		model.put("estados",estados);
		return new ModelAndView("detallepedido", model);
	}

	@RequestMapping(path="/verpedidos")
	public ModelAndView listarPedidosAdmin(HttpServletRequest request) {
		ModelMap model = new ModelMap();
		Usuario user=(Usuario)request.getSession().getAttribute("usuario");
		if(user.getRol().equals(Rol.ADMINISTRADOR))
		{
			List<Pedido> listaPedidos=servicioPedido.listarPedidos();
			model.put("pedidos", listaPedidos);
			model.put("usuario", user);
			return new ModelAndView("listapedidos", model);
		}
		else
		{
			return new ModelAndView("redirect:/mispedidos");
		}
	}

}
