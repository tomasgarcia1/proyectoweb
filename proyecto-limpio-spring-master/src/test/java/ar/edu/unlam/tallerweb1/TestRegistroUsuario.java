package ar.edu.unlam.tallerweb1;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ar.edu.unlam.tallerweb1.controladores.ControladorUsuario;
import ar.edu.unlam.tallerweb1.modelo.Comida;
import ar.edu.unlam.tallerweb1.modelo.Restriccion;
import ar.edu.unlam.tallerweb1.modelo.TipoHorario;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.ComidaDao;
import ar.edu.unlam.tallerweb1.repositorios.RestriccionDao;
import ar.edu.unlam.tallerweb1.repositorios.UsuarioDao;
import ar.edu.unlam.tallerweb1.repositorios.UsuarioDaoImpl;
import ar.edu.unlam.tallerweb1.servicios.ServicioComidaImpl;
import ar.edu.unlam.tallerweb1.servicios.ServicioRestriccion;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;

import static org.mockito.Mockito.*;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.assertj.core.api.Assertions.*;

public class TestRegistroUsuario extends SpringTest {
	private UsuarioDao usuarioDao=new UsuarioDaoImpl();
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
	 	@Test
	    @Transactional @Rollback
	    public void registrarUsuarioDao(){
	        Usuario usuario = new Usuario();
	        this.usuarioDao.setSesion(session().getSessionFactory());
	        usuario.setEmail("tomas@gmail.com");
	        usuario.setPassword("1234");      
	        this.usuarioDao.registrarUsuario(usuario);  
	        assertThat(usuario.getId()).isNotNull();
	    }
	 	
	 	@Test
	    @Transactional @Rollback
	    public void validarMailDao(){
	        Usuario usuario = new Usuario();
	        this.usuarioDao.setSesion(session().getSessionFactory());
	        usuario.setEmail("tomas@gmail.com");
	        usuario.setPassword("1234");      
	        this.usuarioDao.registrarUsuario(usuario);  
	        assertThat(this.usuarioDao.validarExistenciaEmail("tomas@gmail.com")).isEqualTo(true);
	    }
}