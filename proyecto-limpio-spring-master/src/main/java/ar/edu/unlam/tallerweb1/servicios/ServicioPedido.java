package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Pedido;

public interface ServicioPedido {
	Long crearPedido(Pedido pedido);
	Pedido generarPedidoPorRestricciones(Long id);
	Double calcularImporteTotal(Pedido pedido);
	void cancelarPedido(Long id);
	Pedido buscarPedidoPorId(Long id);
	void actualizarPedido(Pedido pedido);
}
