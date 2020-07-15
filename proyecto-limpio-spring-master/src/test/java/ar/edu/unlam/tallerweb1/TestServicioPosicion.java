package ar.edu.unlam.tallerweb1;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

import ar.edu.unlam.tallerweb1.modelo.Pedido;
import ar.edu.unlam.tallerweb1.modelo.Posicion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.PosicionDao;
import ar.edu.unlam.tallerweb1.servicios.ServicioPedido;
import ar.edu.unlam.tallerweb1.servicios.ServicioPedidoImpl;
import ar.edu.unlam.tallerweb1.servicios.ServicioPosicionImpl;

public class TestServicioPosicion {	
	
	@Test
	public void testCompararPosicionQueElUsuarioYaTiene() {
		ServicioPosicionImpl servicioPosicion=new ServicioPosicionImpl();		
		List<Posicion> posiciones=new ArrayList<Posicion>();
		Posicion pos1=new Posicion(1.d,1.d,"1");
		Posicion pos2=new Posicion(2.d,2.d,"2");
		Posicion pos3=new Posicion(3.d,3.d,"3");
		posiciones.add(pos1);
		posiciones.add(pos2);
		posiciones.add(pos3);
		Posicion posAEvaluar=new Posicion(3.d,3.d,"4");
		
		boolean x=servicioPosicion.compararPosiciones(posiciones, posAEvaluar);
		
		assertFalse(x);
	}
	@Test
	public void testCompararPosicionQueElUsuarioNoTiene() {		
		ServicioPosicionImpl servicioPosicion=new ServicioPosicionImpl();
		
		List<Posicion> posiciones=new ArrayList<Posicion>();
		Posicion pos1=new Posicion(1.d,1.d,"1");
		Posicion pos2=new Posicion(2.d,2.d,"2");
		Posicion pos3=new Posicion(3.d,3.d,"3");
		posiciones.add(pos1);
		posiciones.add(pos2);
		posiciones.add(pos3);
		Posicion posAEvaluar=new Posicion(4.d,4.d,"4");
		
		boolean x=servicioPosicion.compararPosiciones(posiciones, posAEvaluar);
 		assertTrue(x);			
		 
 	}
	@Test
	public void testObtenerTodasLasPosicionesDeLosPedidosDeUnUsuario() {		
		ServicioPedido servicioPedido=mock(ServicioPedido.class);
		PosicionDao posicionDao=mock(PosicionDao.class);
		ServicioPosicionImpl servicioPosicion=new ServicioPosicionImpl(servicioPedido,posicionDao);
		
		Usuario user=new Usuario();
		ServicioPedidoImpl servicioPedidos=new ServicioPedidoImpl();		
		Pedido pedido=new Pedido();
		long id=1;
		List <Pedido>pedidos=new ArrayList<>();
		
		Posicion p1=new Posicion(1.d,1.d,"1");
		Posicion p2=new Posicion(2.d,2.d,"2");

		Pedido pd1=new Pedido();
		pd1.setUbicacionDestino(p1);
		
		Pedido pd2=new Pedido();
		pd2.setUbicacionDestino(p2);
		
		pedidos.add(pd1);
		pedidos.add(pd2);
		
		when(servicioPedido.crearPedido(pedido)).thenReturn(id);
		when(servicioPedido.listarPedidosPorUsuario(user)).thenReturn(pedidos);
		
		List listaPosiciones=servicioPosicion.obtenerPosicionesDeUnUsuario(user);
		
		assertThat(listaPosiciones).hasSize(2);
  	}
	
}
