package ar.edu.unlam.tallerweb1.controladores;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ar.edu.unlam.tallerweb1.modelo.Actividad;
import ar.edu.unlam.tallerweb1.modelo.Restriccion;
import ar.edu.unlam.tallerweb1.modelo.Sexo;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioRestriccion;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;

@Controller
public class ControladorUsuario {

	private ServicioUsuario servicioUsuario;
	private ServicioRestriccion servicioRestriccion;

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
		List<String> errores=servicioUsuario.validarUsuario(usuario, restriccion);
		
		if(errores.isEmpty())
		{
			servicioUsuario.registrarUsuario(usuario, restriccion);
			return new ModelAndView("redirect:/login");
		}
		else 
		{
			atributos.addFlashAttribute("errores",errores);
			return new ModelAndView("redirect:/registro"); 
		} 	        			
	}
}
