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
import ar.edu.unlam.tallerweb1.modelo.CuponDescuento;
import ar.edu.unlam.tallerweb1.modelo.MoldeCupon;
import ar.edu.unlam.tallerweb1.modelo.Posicion;
import ar.edu.unlam.tallerweb1.modelo.Rol;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioCuponDescuento;
import ar.edu.unlam.tallerweb1.servicios.ServicioMP;
import ar.edu.unlam.tallerweb1.servicios.ServicioMoldeCupon;
import ar.edu.unlam.tallerweb1.servicios.ServicioPedido;
import ar.edu.unlam.tallerweb1.servicios.ServicioPosicion;

@Controller
public class ControladorCupones {

	private ServicioMP servicioMP = new ServicioMP();

	private Posicion posicionSucursal = new Posicion(-34.668680, -58.566209, "recomida");

	@Inject
	private ServicioPedido servicioPedido;

	@Inject
	private ServicioPosicion servicioPosicion;

	@Inject
	private ServicioMoldeCupon servicioMoldeCupon;

	@Inject
	private ServicioCuponDescuento servicioCuponDescuento;

	public ServicioMoldeCupon getServicioMoldeCupon() {
		return servicioMoldeCupon;
	}

	public void setServicioMoldeCupon(ServicioMoldeCupon servicioMoldeCupon) {
		this.servicioMoldeCupon = servicioMoldeCupon;
	}

	public ServicioCuponDescuento getServicioCuponDescuento() {
		return servicioCuponDescuento;
	}

	public void setServicioCuponDescuento(ServicioCuponDescuento servicioCuponDescuento) {
		this.servicioCuponDescuento = servicioCuponDescuento;
	}

	// ---------------GENERA PEDIDO CON CUPON--------------

	@RequestMapping(path = "/pedidoConCuponComidas", method = RequestMethod.POST)
	public ModelAndView pedidoConCuponComidas(String id, Long idPosicion, Long idCupon, Double precio,
			HttpServletRequest request) {

		ModelMap model = new ModelMap();

		CuponDescuento cupon = servicioCuponDescuento.consultarCuponPorId(idCupon);

		Posicion posicion = this.servicioPosicion.obtenerPosicionPorId(idPosicion);

		Double distancia = this.servicioPedido.distanciaCoord(this.posicionSucursal.getLatitude(),
				this.posicionSucursal.getLongitude(), posicion.getLatitude(), posicion.getLongitude());

		Double precioViaje = this.servicioPedido.convertirPrecio(distancia);

		List<Comida> comidas = servicioPedido.obtenerComidasConcatenadas(id);

		Double precioFinal = servicioCuponDescuento.calcularImporteConCupon(idCupon, precio);
		Usuario user = (Usuario) request.getSession().getAttribute("usuario");

		Preference preference = servicioMP.checkout(user, precioFinal);

		model.put("preference", preference);
		model.put("id", id);
		model.put("comida", comidas);
		model.put("cupon", cupon);
		model.put("viaje", precioViaje);
		model.put("precioanterior", precio);
		model.put("idPosicion", idPosicion);
		model.put("precio", precioFinal);

		return new ModelAndView("pedidoConCupon", model);

	}

	// ---------PAGAR PEDIDO CON CUPON----------
	/*
	 * @RequestMapping(path = "/pagarPedidoConCupon", method = RequestMethod.GET)
	 * public ModelAndView pagarPedido(@RequestParam(value = "id") String id,
	 * 
	 * @RequestParam(value = "payment_status") String estado, @RequestParam(value =
	 * "idPosicion") Long idPosicion,
	 * 
	 * @RequestParam(value = "idCupon") Long idCupon, HttpServletRequest request) {
	 * ModelMap model = new ModelMap(); Usuario user = (Usuario)
	 * request.getSession().getAttribute("usuario"); Pedido nuevoPedido = new
	 * Pedido(); Posicion posicionCliente =
	 * this.servicioPosicion.obtenerPosicionPorId(idPosicion); CuponDescuento cupon
	 * = servicioCuponDescuento.consultarCuponPorId(idCupon);
	 * 
	 * nuevoPedido = servicioPedido.generarPedidoPorIdComidas(id, posicionCliente,
	 * posicionSucursal); // Estado proveniente de mercado pago if
	 * (estado.equals("approved")) { nuevoPedido.setEstado(Estado.PROCESO); } else {
	 * nuevoPedido.setEstado(Estado.CANCELADO); }
	 * 
	 * nuevoPedido.setUsuario(user); Long idPedido =
	 * servicioPedido.crearPedido(nuevoPedido); nuevoPedido.setId(idPedido);
	 * LocalDate fechahoy = LocalDate.now(); nuevoPedido.setFecha(fechahoy);
	 * servicioPedido.updatePedido(nuevoPedido); Double precio =
	 * nuevoPedido.getPrecio();
	 * servicioCuponDescuento.agregarCuponDescuentoUsuarioSemana(precio, fechahoy,
	 * user.getId()); if (cupon != null) { nuevoPedido.setCuponDescuento(cupon);
	 * cupon.setEstado(false); servicioCuponDescuento.actualizarCupon(cupon); }
	 * 
	 * model.put("pedido", nuevoPedido); return new ModelAndView("pedidoRealizado",
	 * model); }
	 */

	// --------------CUPONES DEL USUARIO------------------

		@RequestMapping(path = "/miscupones")
		public ModelAndView miscupones(HttpServletRequest request) {
			ModelMap model = new ModelMap();
			Usuario user = (Usuario) request.getSession().getAttribute("usuario");
			if (user != null && user.getRol() == Rol.CLIENTE) {
				List<CuponDescuento> listaCupones = servicioCuponDescuento.cuponesUsuario(user.getId());
				model.put("cupones", listaCupones);
				return new ModelAndView("listarCupones", model);
			} else {
				return new ModelAndView("redirect:/home");
			}
		}
		
	// ------------CREACIÓN MOLDE VALIDACIÓN--------------

	@RequestMapping(path = "/crearMoldeCupon", method = RequestMethod.GET)
	public ModelAndView crearMoldeCupon(HttpServletRequest request) {
		Usuario user = (Usuario) request.getSession().getAttribute("usuario");
		ModelMap model = new ModelMap();

		if (user != null && user.getRol() == Rol.ADMINISTRADOR) {
			MoldeCupon molde = new MoldeCupon();
			model.put("molde", molde);
			return new ModelAndView("crearMoldeCupon", model);
		} else {
			return new ModelAndView("redirect:/home");
		}
	}

	// ---------AGREGAR MOLDE VALIDACION-------

	@RequestMapping(path = "/agregarMoldeCuponValidacion", method = RequestMethod.POST)
	public ModelAndView agregarMoldeCuponValidacion(@ModelAttribute("molde") MoldeCupon molde) {
		servicioMoldeCupon.agregarMoldeCupon(molde);
		return new ModelAndView("redirect:/adminInterno");
	}

	// ---------ELIMINAR MOLDE-------

	@RequestMapping(path = "/eliminarMoldeCupon")
	public ModelAndView eliminarMoldeCupon(@RequestParam(value = "id", required = true) Long id) {
		servicioMoldeCupon.eliminarMoldeCupon(servicioMoldeCupon.consultarMoldeCuponPorId(id));
		return new ModelAndView("redirect:/adminInterno");
	}

	// ---------ACTUALIZAR MOLDE-------

	@RequestMapping(path = "/actualizarMoldeCupon", method = RequestMethod.POST)
	public ModelAndView actualizarMoldeCupon(Long id, String estado) {
		MoldeCupon molde = servicioMoldeCupon.consultarMoldeCuponPorId(id);
		if (estado.equals("HABILITADO")) {
			molde.setEstado(true);
			servicioMoldeCupon.actualizarMoldeCupon(molde);
		}
		if (estado.equals("DESHABILITADO")) {
			molde.setEstado(false);
			servicioMoldeCupon.actualizarMoldeCupon(molde);
		}
		return new ModelAndView("redirect:/adminInterno");
	}

	// ----------MODIFICAR MOLDE------------

	@RequestMapping(path = "/modificarMoldeCupon")
	public ModelAndView modificarMoldeCupon(@RequestParam(value = "id", required = true) Long id,
			HttpServletRequest request) {
		Usuario user = (Usuario) request.getSession().getAttribute("usuario");
		ModelMap model = new ModelMap();

		if (user != null && user.getRol() == Rol.ADMINISTRADOR) {
			MoldeCupon molde = servicioMoldeCupon.consultarMoldeCuponPorId(id);
			model.put("molde", molde);
			return new ModelAndView("detalleMoldeCupon", model);
		}
		return new ModelAndView("redirect:/home");
	}

	// ---------MOLDES LISTADO-------

	@RequestMapping(path = "/moldes")
	public ModelAndView detallesMoldesCupon(HttpServletRequest request) {
		Usuario user = (Usuario) request.getSession().getAttribute("usuario");
		ModelMap model = new ModelMap();

		if (user != null && user.getRol() == Rol.ADMINISTRADOR) {
			List<MoldeCupon> moldes = servicioMoldeCupon.listarMoldes();
			model.put("moldes", moldes);
			return new ModelAndView("listarMoldesCupones", model);
		}
		return new ModelAndView("redirect:/adminInterno");
	}
}
