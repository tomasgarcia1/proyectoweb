package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Pedido;

public interface PedidoDao {
	Long crearPedido(Pedido pedido);
	void cancelarPedido(Long id);
	Pedido buscarPedidoPorId(Long id);
	void actualizarPedido(Pedido pedido);
	
}
