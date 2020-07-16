package ar.edu.unlam.tallerweb1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Comida;
import ar.edu.unlam.tallerweb1.modelo.Pedido;
import ar.edu.unlam.tallerweb1.repositorios.ComidaDao;
import ar.edu.unlam.tallerweb1.repositorios.ComidaDaoImpl;
import ar.edu.unlam.tallerweb1.repositorios.PedidoDao;
import ar.edu.unlam.tallerweb1.repositorios.PedidoDaoImpl;

public class TestDaoPedido extends SpringTest {
	private PedidoDao pedidoDao= new PedidoDaoImpl();
	private ComidaDao comidaDao = new ComidaDaoImpl();
	
	@Test
    @Transactional @Rollback
	public void testListarComidasDeUnPedido(){
		this.pedidoDao.setSesion(session().getSessionFactory());
		List<Comida> lista=new ArrayList<Comida>();
		Pedido pedido=new Pedido();
		Comida c1 = new Comida();
    	Comida c2 = new Comida();
    	Comida c3 = new Comida();

		lista.add(c1);
		lista.add(c2);
		lista.add(c3);
		pedido.setComidas(lista);
		this.pedidoDao.crearPedido(pedido);
		List<Comida> listaDao=this.pedidoDao.listarComidasDeUnPedido(pedido);
		
		assertFalse(listaDao.isEmpty());
		assertEquals(listaDao.size(), 3);
	 }
	@Test
    @Transactional @Rollback
    public void testListarPedidos() {
		this.pedidoDao.setSesion(session().getSessionFactory());
		Pedido p1=new Pedido();
		Pedido p2=new Pedido();
		Pedido p3=new Pedido();
		Pedido p4=new Pedido();
		
		this.pedidoDao.crearPedido(p1);
		this.pedidoDao.crearPedido(p2);
		this.pedidoDao.crearPedido(p3);
		this.pedidoDao.crearPedido(p4);
		
		assertFalse(this.pedidoDao.listarPedidos().isEmpty());
	}
	
	@Test
	@Transactional @Rollback
	public void testBuscarPedidoPorId()
	{
		this.pedidoDao.setSesion(session().getSessionFactory());
		Pedido p1=new Pedido();
		Pedido p2=new Pedido();
		Pedido p3=new Pedido();
		Long id1=this.pedidoDao.crearPedido(p1);
		Long id2=this.pedidoDao.crearPedido(p2);
		Long id3=this.pedidoDao.crearPedido(p3);
		
		Pedido encontrado=this.pedidoDao.buscarPedidoPorId(id2);
		assertTrue(encontrado.getId().equals(id2));
	}
}
