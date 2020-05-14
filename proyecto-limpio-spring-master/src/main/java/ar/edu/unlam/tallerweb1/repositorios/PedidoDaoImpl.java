package ar.edu.unlam.tallerweb1.repositorios;

import javax.inject.Inject;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Pedido;

@Repository
@Transactional
public class PedidoDaoImpl implements PedidoDao {
	@Inject
	private SessionFactory sesion;
	@Override
	public Long realizarPedido(Pedido pedido) {
		
		Long idGenerado=(Long) sesion.getCurrentSession().save(pedido);
		return idGenerado;
	}

}

}

