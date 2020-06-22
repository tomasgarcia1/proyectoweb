package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Comida;
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

	public List<Comida> listarComidasDeUnPedido(Pedido pedido)
	{
		return sesion.getCurrentSession().createCriteria(Comida.class)
				.createAlias("pedidos", "pedidosjoin")
				.add(Restrictions.eq("pedidosjoin.id", pedido.getId())).list();
	}
	
}
