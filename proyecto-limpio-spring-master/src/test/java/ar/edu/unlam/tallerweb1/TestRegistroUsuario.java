package ar.edu.unlam.tallerweb1;

import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ar.edu.unlam.tallerweb1.controladores.ControladorUsuario;
import ar.edu.unlam.tallerweb1.modelo.Restriccion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioRestriccion;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;

import static org.mockito.Mockito.*;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.assertj.core.api.Assertions.*;


public class TestRegistroUsuario {

	@Test
	public void testQuePruebaRegistroValidacion() {
		//preparación
		ServicioUsuario servicioUsuario= mock(ServicioUsuario.class);
		ServicioRestriccion servicioRestriccion= mock(ServicioRestriccion.class);
		Usuario user = new Usuario();
		ControladorUsuario cu = new ControladorUsuario(servicioUsuario, servicioRestriccion);
		HttpServletRequest ht = mock(HttpServletRequest.class);
		HttpSession sesionMock = mock(HttpSession.class);
		List<String> errores = mock(List.class);
		RedirectAttributes atributos=mock(RedirectAttributes.class); 
		List<Restriccion> restricciones=mock(List.class);
		String restriccion="";
		
		when(ht.getSession()).thenReturn(sesionMock);
		when(servicioRestriccion.buscarRestriccionesSeleccionadas(restriccion)).thenReturn(restricciones);
		when(servicioUsuario.validarUsuario(user, restricciones)).thenReturn(errores);
		when(errores.isEmpty()).thenReturn(true);
		

		//ejecución
		ModelAndView resultado = cu.validarRegistro(user, restriccion, atributos);
		
		//validación
		assertThat(resultado.getViewName()).isEqualTo("redirect:/login");

	}

}