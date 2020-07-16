package ar.edu.unlam.tallerweb1.repositorios;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.SessionFactory;

import ar.edu.unlam.tallerweb1.modelo.Comida;
import ar.edu.unlam.tallerweb1.modelo.Pedido;
import ar.edu.unlam.tallerweb1.modelo.Posicion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

public interface PedidoDao {
	Long crearPedido(Pedido pedido);

	Pedido buscarPedidoPorId(Long id);

	void actualizarPedido(Pedido pedido);

	List<Pedido> listarPedidosPorUsuario(Usuario usuario);

	List<Pedido> listarPedidos();
	
	List<Pedido> listarPedidosEntreFechasDeUnUsuario(Usuario usuario, LocalDate fechaanterior, LocalDate fechadespues);
	
	List<Comida> listarComidasDeUnPedido(Pedido pedido);
	
	Posicion listarPosicion(Pedido pedido);

	void setSesion(SessionFactory sessionFactory);
}
