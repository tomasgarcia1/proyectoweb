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

import ar.edu.unlam.tallerweb1.modelo.Restriccion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioRestriccion;

@Controller
public class ControladorRestriccion {
	@Inject
	private ServicioRestriccion servicioRestriccion;
	
	@RequestMapping("/restricciones")
	public ModelAndView irRegistro() {
		ModelMap model = new ModelMap();
		return new ModelAndView("restricciones", model);
	}
	
	@RequestMapping(path="/crearrestriccion", method=RequestMethod.GET) 
	public ModelAndView restriccionesUsuario(@RequestParam(value="nombre", required=true) String nombre) {	
		ModelMap model=new ModelMap();
		Restriccion restriccion=new Restriccion();
		restriccion.setNombre(nombre);
		
		Long idGenerado=this.servicioRestriccion.crearRestriccion(restriccion);
		
		model.put("restriccion",restriccion);		
		
		return new ModelAndView("restriccionCreada",model);
	}
	
	@RequestMapping(path="/borrarrestriccion")
	public ModelAndView eliminarRestriccion(@RequestParam(value="id")Long id) {
		ModelMap model=new ModelMap();
		
		Restriccion restriccionEncontrada=this.servicioRestriccion.obtenerRestriccionPorId(id);
		if(restriccionEncontrada!=null) {
			this.servicioRestriccion.borrarRestriccion(restriccionEncontrada);	
			model.put("estado","eliminado");
		}else {
			model.put("estado","no se encontro la restriccion");
		} 		
		return new ModelAndView("borrarRestriccion",model);
	}
	
	@RequestMapping(path="/select")
	public ModelAndView select(){
		ModelMap model=new ModelMap();
		Usuario user=new Usuario();
		user.setEmail("marcos@m.com");
		
		List<Restriccion> restricciones=this.servicioRestriccion.obtenerRestricciones();
		model.put("usuario",user);
		model.put("restricciones",restricciones);		
		return new ModelAndView("listarRestricciones",model);
	}
	
	@RequestMapping(path="/asignarRestricciones",method = RequestMethod.POST) 
	public ModelAndView restriccionesUsuario(@ModelAttribute("usuario") Usuario usuario) {	
		ModelMap model=new ModelMap();
		
		System.out.println(usuario.getEmail());
		
		return null;
	}
}

