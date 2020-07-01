package ar.edu.unlam.tallerweb1;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ar.edu.unlam.tallerweb1.controladores.ControladorUsuario;

import ar.edu.unlam.tallerweb1.modelo.Usuario;

import ar.edu.unlam.tallerweb1.servicios.ServicioRestriccion;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;
public class TestEditarUsuario {

	@Test
	public void testEditarUsuarioCorrecto() {
		
		ServicioUsuario servicioUsuario= mock(ServicioUsuario.class);
		Usuario user = new Usuario();
		ServicioRestriccion servicioRestriccion= mock(ServicioRestriccion.class);
		ControladorUsuario cu = new ControladorUsuario(servicioUsuario, servicioRestriccion);
		HttpServletRequest ht = mock(HttpServletRequest.class);
		HttpSession sesionMock = mock(HttpSession.class);
		List<String> errores = mock(List.class);
		RedirectAttributes atributos=mock(RedirectAttributes.class); 
		
		
		when(ht.getSession()).thenReturn(sesionMock);
		when(servicioUsuario.validarUsuarioEditar(user)).thenReturn(errores);
		when(errores.isEmpty()).thenReturn(true);
		

		ModelAndView resultado = cu.editarValidacion(user, atributos,ht);
		
		assertThat(resultado.getViewName()).isEqualTo("redirect:/interno");

	}
	@Test
	public void testEditarUsuarioIncorrecto() {
		
		ServicioUsuario servicioUsuario= mock(ServicioUsuario.class);
		Usuario user = new Usuario();
		ServicioRestriccion servicioRestriccion= mock(ServicioRestriccion.class);
		ControladorUsuario cu = new ControladorUsuario(servicioUsuario, servicioRestriccion);
		HttpServletRequest ht = mock(HttpServletRequest.class);
		HttpSession sesionMock = mock(HttpSession.class);
		List<String> errores = mock(List.class);
		RedirectAttributes atributos=mock(RedirectAttributes.class); 
		
		
		when(ht.getSession()).thenReturn(sesionMock);
		when(servicioUsuario.validarUsuarioEditar(user)).thenReturn(errores);
		when(errores.isEmpty()).thenReturn(false);
		

		ModelAndView resultado = cu.editarValidacion(user, atributos,ht);
		
		assertThat(resultado.getViewName()).isEqualTo("redirect:/editarUsuario");

	}
	
}
