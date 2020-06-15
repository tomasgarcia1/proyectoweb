package ar.edu.unlam.tallerweb1.controladores;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
import ar.edu.unlam.tallerweb1.modelo.Posicion;
import ar.edu.unlam.tallerweb1.modelo.Rol;
import ar.edu.unlam.tallerweb1.modelo.Sexo;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioMP;
import ar.edu.unlam.tallerweb1.servicios.ServicioPedido;
import ar.edu.unlam.tallerweb1.servicios.ServicioPosicion;

@Controller
public class ControladorPedido {
	
	private ServicioMP servicioMP= new ServicioMP(); 
	
	private Posicion posicionSucursal=new Posicion(-34.668680,-58.566209);
	
	@Inject
	private ServicioPedido servicioPedido;
	
	@Inject
	private ServicioPosicion servicioPosicion; 
	
	@RequestMapping(path="/mapa")
	public ModelAndView seleccionarUbicacionDelMapa() {
		ModelMap model=new ModelMap();
		
		Double lat=this.posicionSucursal.getLatitude();
		Double lag=this.posicionSucursal.getLongitude();
		
		model.put("lat",lat);
		model.put("lag",lag);
		
		model.addAttribute("posicion", new Posicion());
		return new ModelAndView("mapa",model);
	}
	
	@RequestMapping(path="/mostrar", method = RequestMethod.POST)
	public ModelAndView distanciaDelPedido(@ModelAttribute("posicion")Posicion posicion,HttpServletRequest request) {
		ModelMap model=new ModelMap();
			
		if(posicion.getLatitude()==0 || posicion.getLongitude()==0 ) {
			return new ModelAndView("redirect:/mapa");
		}
		this.servicioPosicion.crearPosicion(posicion);
		
		Double distancia=this.servicioPedido.distanciaCoord(posicionSucursal.getLatitude(), posicionSucursal.getLongitude(), posicion.getLatitude(), posicion.getLongitude());

		Double tiempo=this.servicioPedido.calcularTiempo(distancia);
		
		BigDecimal time = new BigDecimal(tiempo);
		time = time.setScale(0, RoundingMode.HALF_UP);
		 
		Double precio=12*distancia;		
		
		precio=this.servicioPedido.convertirPrecio(precio);
		
		model.put("distancia",(int)(distancia+1));
		model.put("precio",precio);
		model.put("tiempo",time);
		
		model.addAttribute("posicion",posicion);
		
		return new ModelAndView("infoViaje",model);
	}
	
	
	
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
	@RequestMapping(path = "/menuSugerido",method = RequestMethod.POST )
	public ModelAndView irAMenuSugerido(@ModelAttribute("posicion") Posicion posicion, HttpServletRequest request) {
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
		
		model.addAttribute("posicion",posicion);
		
		return new ModelAndView("menuSugerido", model);
	}
	/*
	 * Tiene el mismo funcionamiento que irAMenuSugerido, con la diferencia de que usa el metodo generarComidasPorCalorias,
	 * donde se recibe como parametro el usuario para obtener las calorias diarias.
	 */
	@RequestMapping(path = "/menuCalorias")
	public ModelAndView irAMenuCalorias(@ModelAttribute("posicion") Posicion posicion,HttpServletRequest request) {
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
		
		model.addAttribute("posicion",posicion);
		
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
	public ModelAndView vistaPedido(@ModelAttribute("posicion")Posicion posicion,@RequestParam("idComidas") String idComidas, HttpServletRequest request) {
		ModelMap model = new ModelMap();
		Pedido nuevoPedido=new Pedido();
		
		//calculo distancia a la que esta el user
		Double distancia=this.servicioPedido.distanciaCoord(this.posicionSucursal.getLatitude(), 
				this.posicionSucursal.getLongitude(),posicion.getLatitude(), posicion.getLongitude());
		//le pongo un precio diciendo que cada km sale 12 pesos
		Double precioViaje=12*distancia;		
		//le dejo 2 numeros despues de la coma
		precioViaje=this.servicioPedido.convertirPrecio(precioViaje);
		
		nuevoPedido=servicioPedido.generarPedidoPorIdComidas(idComidas);
		//sumo el precio del pedido con el del precio de viaje
		Double precioFinalPedido=nuevoPedido.getPrecio()+precioViaje;
		//seteo el nuevo precio del pedido
		nuevoPedido.setPrecio(precioFinalPedido);
		
		nuevoPedido.setUbicacionDestino(posicion);
		this.servicioPedido.actualizarPedido(nuevoPedido);
		
		Usuario user=(Usuario)request.getSession().getAttribute("usuario");
		//Mercado pago
		Preference p = servicioMP.checkout(user,nuevoPedido);
		model.put("preference",p);
		
		
		
		String idLista=idComidas;
		model.put("id", idLista);
		model.put("precio", nuevoPedido.getPrecio());
		model.put("comidas", nuevoPedido.getComidas());
		model.put("idPosicion",posicion.getId());
		return new ModelAndView("pedidoPorConfirmar", model);
	}
	/*
	 * Se le envia el pedido creado y seteado anteriormente, y se le otorga el estado de "PAGADO".
	 * Se muestra por pantalla el numero de pedido, dado por el ID generado en generarPedido().
	 */
	@RequestMapping(path="/pagarpedido", method=RequestMethod.GET)
	public ModelAndView pagarPedido(@RequestParam(value="id") String id,@RequestParam(value="payment_status") String estado,@RequestParam(value="idPosicion")Long idPosicion, HttpServletRequest request) {
		ModelMap model = new ModelMap();
		Usuario user=(Usuario)request.getSession().getAttribute("usuario");
		Pedido nuevoPedido=new Pedido();
		
		Posicion posicionCliente=this.servicioPosicion.obtenerPosicionPorId(idPosicion);
		
		
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
		
		nuevoPedido.setUbicacionDestino(posicionCliente);
		
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
	@RequestMapping(path="/actualizarestado", method=RequestMethod.POST)
	public ModelAndView actualizarEstado(String id, String estado, HttpServletRequest request)
	{
		ModelMap model = new ModelMap();
		Pedido pedido=servicioPedido.buscarPedidoPorId(Long.parseLong(id));
		Estado estadoPedido=Enum.valueOf(Estado.class, estado);
		pedido.setEstado(estadoPedido);
		servicioPedido.actualizarPedido(pedido, estadoPedido);
		
		return new ModelAndView("redirect:/verpedidos");
	}
}
