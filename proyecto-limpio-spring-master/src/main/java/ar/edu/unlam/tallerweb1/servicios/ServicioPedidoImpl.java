package ar.edu.unlam.tallerweb1.servicios;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Comida;
import ar.edu.unlam.tallerweb1.modelo.Pedido;
import ar.edu.unlam.tallerweb1.repositorios.PedidoDao;

@Service
@Transactional
public class ServicioPedidoImpl implements ServicioPedido {

	@Inject
	private PedidoDao pedidoDao;

	@Override
	public Long crearPedido(Pedido pedido) {
		
		return pedidoDao.crearPedido(pedido);
	}
	/*
	 * Se acumulan los precios de cada comida en la variable importe.
	 * Esto da como resultado el importe total del pedido.
	 */
	@Override
	public Double calcularImporteTotal(Pedido pedido) {
		Double importe=0.0;
		for (Comida comidas : pedido.getComidas()) {
			importe+=comidas.getPrecio();
		}
		return importe;
	}

	@Override
	public void cancelarPedido(Long id) {
		pedidoDao.cancelarPedido(id);
	}

	@Override
	public Pedido buscarPedidoPorId(Long id) {
		return pedidoDao.buscarPedidoPorId(id);
	}

}
