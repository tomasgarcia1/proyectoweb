package ar.edu.unlam.tallerweb1.controladores;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Actividad;
import ar.edu.unlam.tallerweb1.modelo.Sexo;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;
@Controller
public class ControladorUsuario {
	@Inject
	private ServicioUsuario servicioUsuario;

	//-----------REGISTRO----------
	
	@RequestMapping("/registro") 
	public ModelAndView registrar() {  
		ModelMap modelo = new ModelMap();
		Usuario usuario=new Usuario();
		List<Actividad>actividades=Arrays.asList(Actividad.values());
		List<Sexo>sexos=Arrays.asList(Sexo.values());
		modelo.put("usuario", usuario);
		modelo.put("actividades", actividades);
		modelo.put("sexos", sexos);

		return new ModelAndView("registro", modelo);
	}
	
	//--------REGISTRO VALIDACION--------
	
	@RequestMapping(path = "/registroValidacion", method = RequestMethod.POST)
	public ModelAndView validarRegistro(@ModelAttribute("usuario") Usuario usuario,
			HttpServletRequest request) {
			usuario.setCaloriasDiarias(servicioUsuario.calcularCaloriasDiarias(usuario));
			servicioUsuario.registrarUsuario(usuario);
			
			HttpSession session = request.getSession(true);//abro la sesion
			request.getSession().setAttribute("usuario",usuario); //guardo usuario como key y guardo el user
	       	        			
			return new ModelAndView("redirect:/seleccionarRestricciones");
			
	}

}
