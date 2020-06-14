package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.FetchMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Comida;
import ar.edu.unlam.tallerweb1.modelo.Estado;
import ar.edu.unlam.tallerweb1.modelo.Pedido;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

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
	@Override
	public void actualizarPedido(Pedido pedido) {
		sesion.getCurrentSession().update(pedido);
	}
	@Override
	public List<Pedido> listarPedidosPorUsuario(Usuario usuario) {
		return sesion.getCurrentSession().createCriteria(Pedido.class).
				add(Restrictions.eq("usuario.id", usuario.getId())).list();
	}
	@Override
	public List<Pedido> listarPedidos() {
		return sesion.getCurrentSession().createCriteria(Pedido.class).list();
	}
}
