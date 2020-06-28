package ar.edu.unlam.tallerweb1.controladores;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ar.edu.unlam.tallerweb1.modelo.Actividad;
import ar.edu.unlam.tallerweb1.modelo.Pedido;
import ar.edu.unlam.tallerweb1.modelo.Posicion;
import ar.edu.unlam.tallerweb1.modelo.Restriccion;
import ar.edu.unlam.tallerweb1.modelo.Sexo;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioPedido;
import ar.edu.unlam.tallerweb1.servicios.ServicioPosicion;
import ar.edu.unlam.tallerweb1.servicios.ServicioRestriccion;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;

@Controller
public class ControladorUsuario {
	@Inject
	private ServicioUsuario servicioUsuario;
	@Inject
	private ServicioRestriccion servicioRestriccion;	
	@Inject
	private ServicioPedido servicioPedido;
	@Inject
	private ServicioPosicion servicioPosicion;
	

	// -----------REGISTRO----------
	@Autowired
	public ControladorUsuario(ServicioUsuario servicioUsuario2, ServicioRestriccion servicioRestriccion2) {
		this.servicioUsuario = servicioUsuario2;
		this.servicioRestriccion = servicioRestriccion2;
	}

	@RequestMapping("/registro")
	public ModelAndView registrar() {
		ModelMap modelo = new ModelMap();
		Usuario usuario = new Usuario();
		List<Actividad> actividades = Arrays.asList(Actividad.values());
		List<Sexo> sexos = Arrays.asList(Sexo.values());
		List<Restriccion> restricciones = servicioRestriccion.obtenerRestricciones();
		modelo.put("restricciones", restricciones);
		modelo.put("usuario", usuario);
		modelo.put("actividades", actividades);
		modelo.put("sexos", sexos);

		return new ModelAndView("registro", modelo);
	}

	// --------REGISTRO VALIDACION--------

	@RequestMapping(path = "/registroValidacion", method = RequestMethod.POST)
	public ModelAndView validarRegistro(@ModelAttribute("usuario") Usuario usuario, String restriccion, RedirectAttributes atributos) {
		List<Restriccion> restricciones=servicioRestriccion.buscarRestriccionesSeleccionadas(restriccion);
		List<String> errores=servicioUsuario.validarUsuario(usuario, restricciones);
		if(errores.isEmpty())
		{
			servicioUsuario.registrarUsuario(usuario, restricciones);
			return new ModelAndView("redirect:/login");
		}
		else 
		{
			atributos.addFlashAttribute("errores",errores);
			return new ModelAndView("redirect:/registro"); 
		} 	        			
	}
	
	
}
