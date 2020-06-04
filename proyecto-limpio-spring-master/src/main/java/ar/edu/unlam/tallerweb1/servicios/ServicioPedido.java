package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Pedido;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

public interface ServicioPedido {
	Long crearPedido(Pedido pedido);
	Pedido generarPedidoPorRestricciones(Long id);
	Pedido generarPedidoPorCalorias(Usuario usuario);
	Double calcularImporteTotal(Pedido pedido);
	void cancelarPedido(Long id);
	Pedido buscarPedidoPorId(Long id);
	void actualizarPedido(Pedido pedido);
}
