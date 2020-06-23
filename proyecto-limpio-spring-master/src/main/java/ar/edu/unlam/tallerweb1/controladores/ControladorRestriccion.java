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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Comida;
import ar.edu.unlam.tallerweb1.modelo.Restriccion;
import ar.edu.unlam.tallerweb1.modelo.Rol;
import ar.edu.unlam.tallerweb1.modelo.TipoHorario;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioComida;
import ar.edu.unlam.tallerweb1.servicios.ServicioRestriccion;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;

@Controller
public class ControladorRestriccion {
	@Inject 
	private ServicioRestriccion servicioRestriccion;
	@Inject
	private ServicioUsuario servicioUsuario;
	@Inject
	private ServicioComida servicioComida;
	
	//--------IR A RESTRICCIONES--------
	
	@RequestMapping("/restricciones")
	public ModelAndView irRegistro() {
		ModelMap model = new ModelMap();
		return new ModelAndView("restricciones", model);
	}
	
	//----------CREAR RESTRICCION------------
	
	@RequestMapping(path="/crearrestriccion", method=RequestMethod.GET) 
	public ModelAndView crestriccionesUsuario(@RequestParam(value="nombre", required=true) String nombre) {	
		ModelMap model=new ModelMap();
		Restriccion restriccion=new Restriccion();
		restriccion.setNombre(nombre);
		
		Long idGenerado=this.servicioRestriccion.crearRestriccion(restriccion);
		
		model.put("restriccion",restriccion);		
		
		return new ModelAndView("restriccionCreada",model);
	}
	
	//---------ELIMINAR RESTRICCION----------
	
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
	
	//------------SELECCIONAR RESTRICCIONES----------
	
	@RequestMapping(path="/seleccionarRestricciones")
	public ModelAndView select(HttpServletRequest request){
		ModelMap model=new ModelMap();		
		List<Restriccion> restricciones=this.servicioRestriccion.obtenerRestricciones();
		
		model.put("restricciones",restricciones);		
		return new ModelAndView("listarRestricciones",model);
	}
	
	
	//-------------MUESTRA RESTRICCIONES DEL USUARIO------------
	
	@RequestMapping(path="/mostrarRestriccionesDeUsuario")
	public ModelAndView x(HttpServletRequest request){
		Usuario user=(Usuario)request.getSession().getAttribute("usuario");
		if(user!=null) {
			return new ModelAndView("usuarioConRestricciones");
	        }   
		return new ModelAndView("redirect:/home"); 
	}
	
	//------------ASIGNAR RESTRICCIONES AL USUARIO----------
	@RequestMapping(path="/asignarRestricciones",method = RequestMethod.GET) 
	public ModelAndView restriccionesUsuario(@RequestParam(value="restriccion", required=false)
	String restriccion,HttpServletRequest request){
		ModelMap model=new ModelMap();
		List<Restriccion> restricciones=new ArrayList<Restriccion>();
		
		Usuario user=(Usuario)request.getSession().getAttribute("usuario");
		if(user!=null && restriccion!=null) {
			char [] array = restriccion.replace(",", "").toCharArray();
	        for (int i = 0; i < array.length; i++) {            
	            Restriccion restrict=this.servicioRestriccion.obtenerRestriccionPorId((long)Character.getNumericValue(array[i]));
				 if(restrict!=null) {
					 restricciones.add(restrict);
				 }
	        }      
	        user.setRestricciones(restricciones);
	        
	        this.servicioUsuario.update(user);
	        
	        model.put("usuario",user);
	        return new ModelAndView("usuarioConRestricciones",model);
		}
        
       /* cerrar sesion
         * request.getSession().invalidate();
        request.setAttribute("usuario", null);   */        
		return new ModelAndView("redirect:/home");
	}

	
	//----------BUSCAR COMIDA POR HORARIO-----------
	
	@RequestMapping(path="/buscarComidaPorHorario")
	public ModelAndView buscarComidaPorHorario(HttpServletRequest request) {
		Usuario user = (Usuario) request.getSession().getAttribute("usuario");
		ModelMap modelo = new ModelMap();
		
		if (user != null && user.getRol()==Rol.ADMINISTRADOR) {
			Comida comida = new Comida();
			List<TipoHorario>tipoHorario=Arrays.asList(TipoHorario.values());
			
			modelo.put("comida", comida);
			modelo.put("tipoHorario", tipoHorario);

			return new ModelAndView("buscarComidaPorHorario", modelo);
			
		}else {
			return new ModelAndView("redirect:/home");
		}
	}
	
	//-------SELECCIONAR COMIDA-----------
	
	@RequestMapping(path="/seleccionarComida", method = RequestMethod.POST)
	public ModelAndView seleccionarComida(@ModelAttribute("comida") Comida comida, HttpServletRequest request) {
		Usuario user = (Usuario) request.getSession().getAttribute("usuario");
		ModelMap modelo = new ModelMap();
		
		if (user != null && user.getRol()==Rol.ADMINISTRADOR) {
			TipoHorario tipo = comida.getTipoHorario();
			List<Comida> comidas = servicioComida.obtenerComidasSegunTipoHorario(tipo);
			
			Comida c = new Comida();
			
			modelo.put("comida", c);
			modelo.put("comidas", comidas);

			return new ModelAndView("seleccionarComida", modelo);
			
		}else {
			return new ModelAndView("redirect:/home");
		}
	}
	
	//-----------SELECCIONAR RESTRICCION DE COMIDA---------
	
	@RequestMapping(path="/seleccionarRestriccionDeComida", method = RequestMethod.POST)
	public ModelAndView seleccionarRestriccionDeComida(@ModelAttribute("comida") Comida comida, HttpServletRequest request) {
		Usuario user = (Usuario) request.getSession().getAttribute("usuario");
		ModelMap modelo = new ModelMap();
		
		if (user != null && user.getRol()==Rol.ADMINISTRADOR) {
			Comida c = servicioComida.obtenerComidaPorNombre(comida.getNombre());
			List<Restriccion> restricciones=this.servicioRestriccion.obtenerRestricciones();
			
			modelo.put("comida", c);
			modelo.put("restricciones", restricciones);

			return new ModelAndView("seleccionarRestriccionDeComida", modelo);
			
		}else {
			return new ModelAndView("redirect:/home");
		}
	}
	
	//-----------COLOCAR RESTRICCION A COMIDA--------------
	
	@RequestMapping(path="/colocarRestriccionAComida", method = RequestMethod.GET)
	public ModelAndView seleccionarRestriccionDeComida(@RequestParam(value="restriccion") String restriccion,
			@RequestParam(value="id") Long id, HttpServletRequest request) {
		
		Usuario user = (Usuario) request.getSession().getAttribute("usuario");
		ModelMap modelo = new ModelMap();
		
		
		if (user != null && user.getRol()==Rol.ADMINISTRADOR && restriccion!=null) {
			List<Restriccion> restricciones=new ArrayList<Restriccion>();
			Comida comida = servicioComida.obtenerPorId(id);
			
			char [] array = restriccion.replace(",", "").toCharArray();
	        for (int i = 0; i < array.length; i++) {            
	            Restriccion r=this.servicioRestriccion.obtenerRestriccionPorId((long)Character.getNumericValue(array[i]));
				 if(r!=null) {
					 restricciones.add(r);
				 }
	        }
	        
	        comida.setRestricciones(restricciones);
	        this.servicioComida.updateComida(comida);
	        
	        modelo.put("comida", comida);

			return new ModelAndView("listarComidaConRestricciones", modelo);
			
		}else {
			return new ModelAndView("redirect:/home");
		}
	}
}
