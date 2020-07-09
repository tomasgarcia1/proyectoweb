package ar.edu.unlam.tallerweb1.controladores;

import java.time.LocalDate;

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
import ar.edu.unlam.tallerweb1.modelo.Suscripcion;
import ar.edu.unlam.tallerweb1.modelo.TipoSuscripcion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioMP;
import ar.edu.unlam.tallerweb1.servicios.ServicioSuscripcion;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;

@Controller
public class ControladorSuscripcion {

	@Inject
	private ServicioSuscripcion servicioSuscripcion;
	
	private ServicioMP servicioMP = new ServicioMP();
	
	@Inject
	private ServicioUsuario servicioUsuario;
	
	@RequestMapping("/suscripciones")
	public ModelAndView verSuscripciones() {
		ModelMap modelo = new ModelMap();
		return new ModelAndView("verSuscripciones", modelo);
	}
	
	@RequestMapping("/obtenerSuscripcion")
	public ModelAndView obtenerSuscripcion(@RequestParam(value = "tipo"
	, required = true) Long id, HttpServletRequest request) {
		
		Usuario user = (Usuario) request.getSession().getAttribute("usuario");
		if(user != null) {
		ModelMap modelo = new ModelMap();
		TipoSuscripcion susc = servicioSuscripcion.obtenerDatosSegunTipo(id);
		Double precio = susc.getPrecio();
		String tipo = susc.getNombre();
		Long idTipo = susc.getId();
		// Mercado pago
		Preference p = servicioMP.checkout(user, precio);

		if(user.getSuscripcion()!=null) {
			if(user.getSuscripcion().getEstado()) {
				modelo.put("mensaje", "Usted ya posee una suscripcion");
			}
		}
		
		modelo.put("preference", p);
		modelo.put("precio", precio);
		modelo.put("tipo", tipo);
		modelo.put("id", idTipo);
		
		return new ModelAndView("obtenerSuscripcion", modelo);
		
		}else {
			return new ModelAndView("redirect:/login");
		}
	}
	
	@RequestMapping("/pagarSuscripcion")
	public ModelAndView pagarSuscripcion(@RequestParam(value = "id") Long id,
			@RequestParam(value = "payment_status") String estado, HttpServletRequest request) {
		Usuario user = (Usuario) request.getSession().getAttribute("usuario");
		
		if(user != null) {
			ModelMap model = new ModelMap();
			TipoSuscripcion susc = servicioSuscripcion.obtenerDatosSegunTipo(id);
			
			if (estado.equals("approved")) {
				LocalDate fechaInicio = LocalDate.now();
				this.servicioSuscripcion.insertarSuscripcionEnUsuario(id, fechaInicio, user.getId());
				request.getSession().invalidate();
				
				Usuario user2=this.servicioUsuario.obtenerUsuarioPorId(user.getId());
				
				request.getSession(true);
				request.getSession().setAttribute("usuario",user2);
			} else {
				String mensaje = "Su pedido no ha podido ser procesado, intente de nuevo";
				model.put("msj", mensaje);
			}
			
			model.put("tipo", susc.getNombre());
			
			return new ModelAndView("pagarSuscripcion", model);
			
		}else {
			return new ModelAndView("redirect:/login");
		}
	}
	
	@RequestMapping("/miSuscripcion")
	public ModelAndView verMiSuscripcion(HttpServletRequest request) {
		Usuario user = (Usuario) request.getSession().getAttribute("usuario");
		
		if(user != null) {
			ModelMap model = new ModelMap();
			
			if(user.getSuscripcion()!=null) {
				Suscripcion susc = user.getSuscripcion();
				TipoSuscripcion tipo = susc.getTipo();
				model.put("susc", susc);
				model.put("tipo", tipo);
				model.put("estado", susc.getEstado());
				
			}else {
				model.put("msj", "Usted no posee una suscripción");
				
			}
			
			return new ModelAndView("miSuscripcion", model);
		}else {
			return new ModelAndView("redirect:/login");
		}
	}
	
	@RequestMapping(path="/cancelarSuscripcion")
	public ModelAndView cancelarSuscripcion(HttpServletRequest request) {
		Usuario user = (Usuario) request.getSession().getAttribute("usuario");
		
		if(user != null) {
			ModelMap model = new ModelMap();
			if(user.getSuscripcion()!=null) {
			this.servicioSuscripcion.cancelarSuscripcion(user.getId());
			request.getSession().invalidate();
			
			Usuario user2=this.servicioUsuario.obtenerUsuarioPorId(user.getId());
			
			request.getSession(true);
			request.getSession().setAttribute("usuario",user2);
			}
			
			return new ModelAndView("cancelarSuscripcion", model);
		}else {
			return new ModelAndView("redirect:/login");
		}
	}
}
