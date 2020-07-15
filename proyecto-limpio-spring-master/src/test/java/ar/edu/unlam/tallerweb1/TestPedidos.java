package ar.edu.unlam.tallerweb1;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ar.edu.unlam.tallerweb1.controladores.ControladorPedido;
import ar.edu.unlam.tallerweb1.modelo.Posicion;
import ar.edu.unlam.tallerweb1.modelo.Rol;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioPedido;
import ar.edu.unlam.tallerweb1.servicios.ServicioPosicion;

public class TestPedidos {
	
	@Test
	public void testVerPedidosAdmin() {
		Usuario user=mock(Usuario.class);
		HttpServletRequest request=mock(HttpServletRequest.class);
		HttpSession sesion=mock(HttpSession.class);
		ServicioPedido servicioPedido=mock(ServicioPedido.class);
		ControladorPedido controlador=new ControladorPedido();
		controlador.setServicioPedido(servicioPedido);
		
		when(request.getSession()).thenReturn(sesion);
		when(request.getSession().getAttribute("usuario")).thenReturn(user);
		when(user.getRol()).thenReturn(Rol.ADMINISTRADOR);
		
		ModelAndView model=controlador.listarPedidosAdmin(request);
		
		assertThat(model.getViewName()).isEqualTo("listapedidos");
		verify(servicioPedido, times(1)).listarPedidos();
	}
	
	@Test
	public void testVerPedidosAdminInvalido() {
		Usuario user=mock(Usuario.class);
		HttpServletRequest request=mock(HttpServletRequest.class);
		HttpSession sesion=mock(HttpSession.class);
		ServicioPedido servicioPedido=mock(ServicioPedido.class);
		ControladorPedido controlador=new ControladorPedido();
		controlador.setServicioPedido(servicioPedido);
		
		when(request.getSession()).thenReturn(sesion);
		when(request.getSession().getAttribute("usuario")).thenReturn(user);
		when(user.getRol()).thenReturn(Rol.CLIENTE);
		
		ModelAndView model=controlador.listarPedidosAdmin(request);
		
		assertThat(model.getViewName()).isEqualTo("redirect:/mispedidos");
		verify(servicioPedido, times(0)).listarPedidos();
	}

}
