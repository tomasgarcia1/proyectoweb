package ar.edu.unlam.tallerweb1.repositorios;

import javax.inject.Inject;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Estado;
import ar.edu.unlam.tallerweb1.modelo.Pedido;

@Repository
@Transactional
public class PedidoDaoImpl implements PedidoDao {
	@Inject
	private SessionFactory sesion;
	@Override
	public Long crearPedido(Pedido pedido) {
		
		Long idGenerado=(Long) sesion.getCurrentSession().save(pedido);
		return idGenerado;
	}
	/*
	 * Se recibe por parametro el ID del pedido que se quiere cancelar.
	 * Cuando se encuentra el pedido, se procede a modificar el estado del mismo a "CANCELADO".
	 * Se actualiza el registro con el valor modificado.
	 */
	@Override
	public void cancelarPedido(Long id) {
		Pedido pedidoPorActualizar=sesion.getCurrentSession().get(Pedido.class, id);
		pedidoPorActualizar.setEstado(Estado.CANCELADO);
		sesion.getCurrentSession().update(pedidoPorActualizar);
	}
	@Override
	public Pedido buscarPedidoPorId(Long id) {
		return sesion.getCurrentSession().get(Pedido.class, id);
	}
}
