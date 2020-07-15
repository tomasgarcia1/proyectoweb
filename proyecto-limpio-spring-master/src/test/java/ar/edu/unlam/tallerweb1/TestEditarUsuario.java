package ar.edu.unlam.tallerweb1;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ar.edu.unlam.tallerweb1.controladores.ControladorUsuario;
import ar.edu.unlam.tallerweb1.modelo.Rol;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.UsuarioDao;
import ar.edu.unlam.tallerweb1.repositorios.UsuarioDaoImpl;
import ar.edu.unlam.tallerweb1.servicios.ServicioRestriccion;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuarioImpl;
import ar.edu.unlam.tallerweb1.SpringTest;

public class TestEditarUsuario extends SpringTest{
	
	private UsuarioDao usuarioDao=new UsuarioDaoImpl();
	
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



    @Test
    @Transactional @Rollback
    public void editarUsuarioDao(){
        Usuario usuario = new Usuario();
        this.usuarioDao.setSesion(session().getSessionFactory());
        usuario.setEmail("seba@gmail.com");
        usuario.setPassword("1234");
        Long id =  this.usuarioDao.registrarUsuario(usuario);
        usuario.setId(id);
        usuario.setEmail("tomas@mail.com");
        this.usuarioDao.editarUsuario(usuario);  
        assertThat(this.usuarioDao.obtenerUsuarioPorId(id).getEmail()).isEqualTo("tomas@mail.com");
    }
   
	@Test
	@Transactional @Rollback
	public void verificarConsultaEmailYPassUser(){
	   Usuario user = new Usuario();
	   user.setEmail("juan@gmail.com");
	   user.setPassword("123456Ab");
	
	   this.usuarioDao.setSesion(session().getSessionFactory());
	   
	   this.usuarioDao.registrarUsuario(user);
	   Usuario result = this.usuarioDao.consultarEmailYPassDeUsuario(user);
	   
	   assertThat(result).isEqualTo(user);
	
	}
}
