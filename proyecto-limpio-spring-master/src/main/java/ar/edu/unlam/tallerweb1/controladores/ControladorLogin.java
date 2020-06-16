package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Rol;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class ControladorLogin {

	// La anotacion @Autowired indica a Spring que se debe utilizar el contructor como mecanismo de inyección de dependencias,
	// es decir, qeue lo parametros del mismo deben ser un bean de spring y el framewrok automaticamente pasa como parametro
	// el bean correspondiente, en este caso, un objeto de una clase que implemente la interface ServicioLogin,
	// dicha clase debe estar anotada como @Service o @Repository y debe estar en un paquete de los indicados en
	// applicationContext.xml
	@Inject
	private ServicioUsuario servicioLogin;

	//-----------LOGIN------------
	
	@RequestMapping("/login")
	public ModelAndView irALogin() {

		ModelMap modelo = new ModelMap();
		
		Usuario usuario = new Usuario();
		modelo.put("usuario", usuario);
		
		return new ModelAndView("login", modelo);
	}
	
	//----------VALIDAR LOGIN-------------
	
	// Este metodo escucha la URL validar-login siempre y cuando se invoque con metodo http POST
	// El método recibe un objeto Usuario el que tiene los datos ingresados en el form correspondiente y se corresponde con el modelAttribute definido en el
	// tag form:form
	
	@RequestMapping(path = "/validar-login", method = RequestMethod.POST)
	public ModelAndView validarLogin(@ModelAttribute("usuario") Usuario usuario, HttpServletRequest request) {
		ModelMap model = new ModelMap();
		// invoca el metodo consultarUsuario del servicio y hace un redirect a la URL /home, esto es, en lugar de enviar a una vista
		// hace una llamada a otro action a través de la URL correspondiente a ésta
		Usuario usuarioBuscado = servicioLogin.consultarEmailYPassDeUsuario(usuario);
		if (usuarioBuscado != null) {
			request.getSession().setAttribute("usuario",usuarioBuscado);
			request.getSession(true);
			if(usuarioBuscado.getRol() == Rol.CLIENTE) {
			return new ModelAndView("redirect:/interno");
			
			} else {
				return new ModelAndView("redirect:/adminInterno");
				
			}
		} else {
			// si el usuario no existe agrega un mensaje de error en el modelo.
			model.put("error", "Usuario o clave incorrecta");
		}
		return new ModelAndView("login", model);
	}

	//----------------CERRAR SESION------------------
	
	@RequestMapping("/cerrarSesion")
	public ModelAndView cerrarSesion(HttpServletRequest request) {
		request.getSession().invalidate();
		return new ModelAndView("redirect:/home");
	}
	
	
	// Escucha la url /, y redirige a la URL  /login, es lo mismo que si se invoca la url /login directamente.
	@RequestMapping(path = "/", method = RequestMethod.GET)
	public ModelAndView inicio() {
		return new ModelAndView("redirect:/home");
	}
	/*@RequestMapping(path = "/validar-login", method = RequestMethod.POST)
    public ModelAndView validarogin(@ModelAttribute Usuario usuario, HttpServletRequest request) {
        Usuario usuarioBuscado = servicioLogin.consultarUsuario(usuario);
        if (usuarioBuscado != null) {
            
            switch (usuarioBuscado.getRol()) {
            case "admin":
                request.getSession().setAttribute("rol", usuarioBuscado.getRol());
                request.getSession().setAttribute("nombre", "Admin");
                
                
                return new ModelAndView("redirect:/homeAdmin");
            case "operador":
                Long idSucursal = servicioOperador.buscarOperador(usuarioBuscado).getSucursal().getId();
                request.getSession().setAttribute("rol", usuarioBuscado.getRol());
                request.getSession().setAttribute("idSucursal", idSucursal);
                request.getSession().setAttribute("nombre", "Operador"); 
                return new ModelAndView("redirect:/homeOperador/"+idSucursal);
            default: //caso socio
                request.getSession().setAttriute("idSucursal", servicioSocio.buscarSocio(usuarioBuscado).getSucursal().getId());
                request.getSession().setAttribute("nombre", servicioSocio.buscarSocio(usuarioBuscado).getNombre());
                request.getSession().setAttribute("idSocio", servicioSocio.buscarSocio(usuarioBuscado).getIdSocio());
                request.getSession().setAttribute("idPase", servicioSocio.buscarSocio(usuarioBuscado).getPase().getId());
                request.getSession().setAttribute("estado", servicioSocio.getEstadoDeSocioPorCuota(servicioSocio.buscarSocio(usuarioBuscado).getIdSocio()));
                return new ModelAndView("redirect:/home");
            }
            
ModelAndView("redirect:/home");
        
        } else {
            ModelMap model = new ModelMap();
            model.put("error", "Usuario o clave incorrecta");
            return new ModelAndView("login", model);
        }
 
    }xdxdxd
*/	
	
}