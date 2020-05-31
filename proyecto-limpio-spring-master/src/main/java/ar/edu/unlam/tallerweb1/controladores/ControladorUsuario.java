package ar.edu.unlam.tallerweb1.controladores;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

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
	@RequestMapping(path = "/registroValidacion", method = RequestMethod.POST)
	public ModelAndView validarRegistro(@ModelAttribute("usuario") Usuario usuario) {
			usuario.setCaloriasDiarias(servicioUsuario.calcularCaloriasDiarias(usuario));
			servicioUsuario.registrarUsuario(usuario);
			ModelMap modelo = new ModelMap();
			modelo.put("usuario", usuario);
		return new ModelAndView("registroValidado", modelo);//aca deberia redirigir a la seleccion de restricciones
	}
}