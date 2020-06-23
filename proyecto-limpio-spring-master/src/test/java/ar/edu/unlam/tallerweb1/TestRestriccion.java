package ar.edu.unlam.tallerweb1;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.controladores.ControladorPedido;
import ar.edu.unlam.tallerweb1.controladores.ControladorRestriccion;
import ar.edu.unlam.tallerweb1.controladores.ControladorUsuario;
import ar.edu.unlam.tallerweb1.modelo.Comida;
import ar.edu.unlam.tallerweb1.modelo.Restriccion;
import ar.edu.unlam.tallerweb1.modelo.Rol;
import ar.edu.unlam.tallerweb1.modelo.TipoHorario;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioComida;
import ar.edu.unlam.tallerweb1.servicios.ServicioPedido;
import ar.edu.unlam.tallerweb1.servicios.ServicioRestriccion;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;

public class TestRestriccion {
	
	@Test
	public void testMostrarRestriccionesDeUsuario() {
		Usuario user=mock(Usuario.class);
		HttpServletRequest request=mock(HttpServletRequest.class);
		HttpSession sesion=mock(HttpSession.class);
		
		ServicioRestriccion servicioRestriccion=mock(ServicioRestriccion.class);
		ServicioUsuario servicioUsuario=mock(ServicioUsuario.class);
		ServicioComida servicioComida=mock(ServicioComida.class);
		
		ControladorRestriccion controlador=new ControladorRestriccion(servicioRestriccion,servicioUsuario,servicioComida);
		List<Restriccion> restricciones=mock(List.class);	
		
		when(request.getSession()).thenReturn(sesion);
		when(request.getSession().getAttribute("usuario")).thenReturn(user);
		when(servicioRestriccion.listarRestriccionesDeUsuario(user)).thenReturn(restricciones);
		
		ModelAndView model=controlador.x(request);		
		
		assertThat(model.getViewName()).isEqualTo("usuarioConRestricciones");
				
	}
	@Test
	public void testMostrarRestriccionesDeUsuarioInvalido() {
		HttpServletRequest request=mock(HttpServletRequest.class);
		HttpSession sesion=mock(HttpSession.class);
		
		ServicioRestriccion servicioRestriccion=mock(ServicioRestriccion.class);
		ServicioUsuario servicioUsuario=mock(ServicioUsuario.class);
		ServicioComida servicioComida=mock(ServicioComida.class);
		
		ControladorRestriccion controlador=new ControladorRestriccion(servicioRestriccion,servicioUsuario,servicioComida);
		List<Restriccion> restricciones=mock(List.class);
		
		when(request.getSession()).thenReturn(sesion);
		when(request.getSession().getAttribute("usuario")).thenReturn(null);
		when(servicioRestriccion.listarRestriccionesDeUsuario(null)).thenReturn(restricciones);
		
		ModelAndView model=controlador.x(request);		
		
		assertThat(model.getViewName()).isEqualTo("redirect:/home");
				
	}
	@Test
	public void testAsignarRestriccionesAUsuario() {
		Usuario user = mock(Usuario.class);
		HttpServletRequest request=mock(HttpServletRequest.class);
		HttpSession sesion=mock(HttpSession.class);
		
		ServicioRestriccion servicioRestriccion=mock(ServicioRestriccion.class);
		ServicioUsuario servicioUsuario=mock(ServicioUsuario.class);
		ServicioComida servicioComida=mock(ServicioComida.class);
		
		ControladorRestriccion controlador=new ControladorRestriccion(servicioRestriccion,servicioUsuario,servicioComida);
		List<Restriccion> restricciones=mock(List.class);		
		
		when(request.getSession()).thenReturn(sesion);
		when(request.getSession().getAttribute("usuario")).thenReturn(user);
		when(servicioRestriccion.listarRestriccionesDeUsuario(user)).thenReturn(restricciones);
		
		ModelAndView model=controlador.restriccionesUsuario("1,2",request);		
		
		assertThat(model.getViewName()).isEqualTo("usuarioConRestricciones");
				
	}
	@Test
	public void testAsignarRestriccionesAUsuarioConUsuarioInvalido() {
		HttpServletRequest request=mock(HttpServletRequest.class);
		HttpSession sesion=mock(HttpSession.class);
		
		ServicioRestriccion servicioRestriccion=mock(ServicioRestriccion.class);
		ServicioUsuario servicioUsuario=mock(ServicioUsuario.class);
		ServicioComida servicioComida=mock(ServicioComida.class);
		
		ControladorRestriccion controlador=new ControladorRestriccion(servicioRestriccion,servicioUsuario,servicioComida);
		List<Restriccion> restricciones=mock(List.class);		
		Restriccion restriccion=mock(Restriccion.class);
		long id=1;
		when(request.getSession()).thenReturn(sesion);
		when(request.getSession().getAttribute("usuario")).thenReturn(null);
		when(servicioRestriccion.listarRestriccionesDeUsuario(null)).thenReturn(restricciones);
		when(servicioRestriccion.obtenerRestriccionPorId(id)).thenReturn(restriccion);
		
		ModelAndView model=controlador.restriccionesUsuario("1,2",request);		
		
		assertThat(model.getViewName()).isEqualTo("redirect:/home");
				
	}
	@Test
	public void testAsignarRestriccionesAUsuarioConRestriccionesInvalidas() {
		Usuario user=mock(Usuario.class);
		HttpServletRequest request=mock(HttpServletRequest.class);
		HttpSession sesion=mock(HttpSession.class);
		
		ServicioRestriccion servicioRestriccion=mock(ServicioRestriccion.class);
		ServicioUsuario servicioUsuario=mock(ServicioUsuario.class);
		ServicioComida servicioComida=mock(ServicioComida.class);
		
		ControladorRestriccion controlador=new ControladorRestriccion(servicioRestriccion,servicioUsuario,servicioComida);
		List<Restriccion> restricciones=mock(List.class);		
		
		when(request.getSession()).thenReturn(sesion);
		when(request.getSession().getAttribute("usuario")).thenReturn(user);
		when(servicioRestriccion.listarRestriccionesDeUsuario(user)).thenReturn(restricciones);
		
		
		ModelAndView model=controlador.restriccionesUsuario(null,request);		
		
		assertThat(model.getViewName()).isEqualTo("redirect:/home");
				
	}
	@Test
	public void testBuscarComidaPorHorario() {
		Usuario user=mock(Usuario.class);
		HttpServletRequest request=mock(HttpServletRequest.class);
		HttpSession sesion=mock(HttpSession.class);
		
		ServicioRestriccion servicioRestriccion=mock(ServicioRestriccion.class);
		ServicioUsuario servicioUsuario=mock(ServicioUsuario.class);
		ServicioComida servicioComida=mock(ServicioComida.class);
		
		ControladorRestriccion controlador=new ControladorRestriccion(servicioRestriccion,servicioUsuario,servicioComida);
		
		when(request.getSession()).thenReturn(sesion);
		when(request.getSession().getAttribute("usuario")).thenReturn(user);
		when(user.getRol()).thenReturn(Rol.ADMINISTRADOR);
		
		ModelAndView model=controlador.buscarComidaPorHorario(request);		
		
		assertThat(model.getViewName()).isEqualTo("buscarComidaPorHorario");
				
	}
	@Test
	public void testBuscarComidaPorHorarioInvalidoUsuario() {
		Usuario user=mock(Usuario.class);
		HttpServletRequest request=mock(HttpServletRequest.class);
		HttpSession sesion=mock(HttpSession.class);
		
		ServicioRestriccion servicioRestriccion=mock(ServicioRestriccion.class);
		ServicioUsuario servicioUsuario=mock(ServicioUsuario.class);
		ServicioComida servicioComida=mock(ServicioComida.class);
		
		ControladorRestriccion controlador=new ControladorRestriccion(servicioRestriccion,servicioUsuario,servicioComida);
		
		when(request.getSession()).thenReturn(sesion);
		when(request.getSession().getAttribute("usuario")).thenReturn(null);
		when(user.getRol()).thenReturn(Rol.ADMINISTRADOR);
		
		ModelAndView model=controlador.buscarComidaPorHorario(request);		
		
		assertThat(model.getViewName()).isEqualTo("redirect:/home");
	}
	@Test
	public void testBuscarComidaPorHorarioInvalidoRol() {
		Usuario user=mock(Usuario.class);
		HttpServletRequest request=mock(HttpServletRequest.class);
		HttpSession sesion=mock(HttpSession.class);
		
		ServicioRestriccion servicioRestriccion=mock(ServicioRestriccion.class);
		ServicioUsuario servicioUsuario=mock(ServicioUsuario.class);
		ServicioComida servicioComida=mock(ServicioComida.class);
		
		ControladorRestriccion controlador=new ControladorRestriccion(servicioRestriccion,servicioUsuario,servicioComida);
		
		when(request.getSession()).thenReturn(sesion);
		when(request.getSession().getAttribute("usuario")).thenReturn(user);
		when(user.getRol()).thenReturn(Rol.CLIENTE);
		
		ModelAndView model=controlador.buscarComidaPorHorario(request);		
		
		assertThat(model.getViewName()).isEqualTo("redirect:/home");
	}
	@Test
	public void testSeleccionarComida() {
		Usuario user=mock(Usuario.class);
		Comida comida=mock(Comida.class);
		
		HttpServletRequest request=mock(HttpServletRequest.class);
		HttpSession sesion=mock(HttpSession.class);
		
		ServicioRestriccion servicioRestriccion=mock(ServicioRestriccion.class);
		ServicioUsuario servicioUsuario=mock(ServicioUsuario.class);
		ServicioComida servicioComida=mock(ServicioComida.class);
				
		ControladorRestriccion controlador=new ControladorRestriccion(servicioRestriccion,servicioUsuario,servicioComida);
		
		when(request.getSession()).thenReturn(sesion);
		when(request.getSession().getAttribute("usuario")).thenReturn(user);
		when(user.getRol()).thenReturn(Rol.ADMINISTRADOR);	
		
		ModelAndView model=controlador.seleccionarComida(comida,request);		
		
		assertThat(model.getViewName()).isEqualTo("seleccionarComida");
	}
	@Test
	public void testSeleccionarRestriccionComida() {
		Usuario user=mock(Usuario.class);
		Comida comida=mock(Comida.class);
		
		HttpServletRequest request=mock(HttpServletRequest.class);
		HttpSession sesion=mock(HttpSession.class);
		
		ServicioRestriccion servicioRestriccion=mock(ServicioRestriccion.class);
		ServicioUsuario servicioUsuario=mock(ServicioUsuario.class);
		ServicioComida servicioComida=mock(ServicioComida.class);
		
		List<Comida> comidas=mock(List.class);		
		List<Restriccion> restricciones=mock(List.class);		

		ControladorRestriccion controlador=new ControladorRestriccion(servicioRestriccion,servicioUsuario,servicioComida);
		
		when(request.getSession()).thenReturn(sesion);
		when(request.getSession().getAttribute("usuario")).thenReturn(user);
		when(user.getRol()).thenReturn(Rol.ADMINISTRADOR);	
		when(servicioComida.obtenerComidaPorNombre("")).thenReturn(comida);
		when(servicioRestriccion.obtenerRestricciones()).thenReturn(restricciones);
		
		ModelAndView model=controlador.seleccionarRestriccionDeComida(comida,request);		
		
		assertThat(model.getViewName()).isEqualTo("seleccionarRestriccionDeComida");
	}
	
	@Test
	public void testColocarRestriccionAComida() {
		Usuario user=mock(Usuario.class);
		Comida comida=mock(Comida.class);
		Restriccion restriccion=mock(Restriccion.class);
		
		HttpServletRequest request=mock(HttpServletRequest.class);
		HttpSession sesion=mock(HttpSession.class);
		
		ServicioRestriccion servicioRestriccion=mock(ServicioRestriccion.class);
		ServicioUsuario servicioUsuario=mock(ServicioUsuario.class);
		ServicioComida servicioComida=mock(ServicioComida.class);
		
		List<Comida> comidas=mock(List.class);		
		List<Restriccion> restricciones=mock(List.class);		
		Long id=(long) 3;
		ControladorRestriccion controlador=new ControladorRestriccion(servicioRestriccion,servicioUsuario,servicioComida);
		
		when(request.getSession()).thenReturn(sesion);
		when(request.getSession().getAttribute("usuario")).thenReturn(user);
		when(user.getRol()).thenReturn(Rol.ADMINISTRADOR);	
		when(servicioComida.obtenerPorId(id)).thenReturn(comida);
		when(servicioRestriccion.obtenerRestriccionPorId(id)).thenReturn(restriccion);
		
		ModelAndView model=controlador.seleccionarRestriccionDeComida("2", id, request);
		
		assertThat(model.getViewName()).isEqualTo("listarComidaConRestricciones");
	}
}
