package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Comida;
import ar.edu.unlam.tallerweb1.modelo.Pedido;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

public interface PedidoDao {
	Long crearPedido(Pedido pedido);

	void cancelarPedido(Long id);

	Pedido buscarPedidoPorId(Long id);

	void actualizarPedido(Pedido pedido);

	List<Pedido> listarPedidosPorUsuario(Usuario usuario);

	List<Pedido> listarPedidos();
}
