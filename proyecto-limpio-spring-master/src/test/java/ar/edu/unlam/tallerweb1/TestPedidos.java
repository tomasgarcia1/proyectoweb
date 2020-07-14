package ar.edu.unlam.tallerweb1;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.controladores.ControladorPedido;
import ar.edu.unlam.tallerweb1.modelo.Comida;
import ar.edu.unlam.tallerweb1.modelo.Estado;
import ar.edu.unlam.tallerweb1.modelo.Pedido;
import ar.edu.unlam.tallerweb1.modelo.Rol;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioComida;
import ar.edu.unlam.tallerweb1.servicios.ServicioComidaImpl;
import ar.edu.unlam.tallerweb1.servicios.ServicioPedido;
import ar.edu.unlam.tallerweb1.servicios.ServicioPedidoImpl;

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
	@Test
	public void testConcatenacionCorrecta()
	{
		ServicioPedido sp = new ServicioPedidoImpl();
		Comida c1=new Comida();
		Comida c2=new Comida();
		Comida c3=new Comida();
		List<Comida> lista=new ArrayList<Comida>();
		
		c1.setId((long)1);
		c2.setId((long)4);
		c3.setId((long)8);
		lista.add(c1);
		lista.add(c2);
		lista.add(c3);
		
		assertThat(sp.concatenarIdComidas(lista)).isEqualTo("1,4,8");
		
	}
	@Test
	public void testImporteTotal()
	{
		ServicioPedido sp = new ServicioPedidoImpl();
		Comida c1=new Comida();
		Comida c2=new Comida();
		Comida c3=new Comida();
		List<Comida> lista=new ArrayList<Comida>();
		Double envio=15.0;
		
		c1.setPrecio(30.0);
		c2.setPrecio(60.0);
		c3.setPrecio(390.0);
		
		lista.add(c1);
		lista.add(c2);
		lista.add(c3);
		
		assertThat(sp.calcularImporteTotal(lista, envio)).isEqualTo(495.0);
		
	}
	@Test
	public void testActualizarPedido()
	{
		HttpServletRequest request=mock(HttpServletRequest.class);
		ServicioPedido servicioPedido=mock(ServicioPedido.class);
		ControladorPedido controlador=new ControladorPedido();
		controlador.setServicioPedido(servicioPedido);
		Pedido pedido=mock(Pedido.class);
		String id="1";
		String estado="PROCESO";
		Estado estadoPedido=Enum.valueOf(Estado.class, estado);
		
		when(servicioPedido.buscarPedidoPorId(Long.parseLong(id))).thenReturn(pedido);
		ModelAndView model=controlador.actualizarEstado(id, estado, request);
		
		assertThat(model.getViewName()).isEqualTo("redirect:/verpedidos");
		
		verify(servicioPedido, times(1)).actualizarPedido(pedido, estadoPedido);
	}
	/*@Test 
	public void testComidasPorRestricciones()
	{
		ServicioPedido sp = new ServicioPedidoImpl();
		ServicioComida sc = mock(ServicioComida.class);
		Long id=(long) 1;
		Comida desayuno=mock(Comida.class);
		Comida almuerzo=mock(Comida.class);
		Comida cena=mock(Comida.class);
		
		when(sc.sugerirDesayunoPorRestricciones(id)).thenReturn(desayuno);
		when(sc.sugerirAlmuerzoPorRestricciones(id)).thenReturn(almuerzo);
		when(sc.sugerirCenaPorRestricciones(id)).thenReturn(cena);
		
		
	}*/
}
