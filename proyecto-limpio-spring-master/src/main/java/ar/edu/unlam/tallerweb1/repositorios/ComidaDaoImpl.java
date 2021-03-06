package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Comida;
import ar.edu.unlam.tallerweb1.modelo.Pedido;
import ar.edu.unlam.tallerweb1.modelo.Restriccion;
import ar.edu.unlam.tallerweb1.modelo.TipoHorario;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

@Repository
@Transactional
public class ComidaDaoImpl implements ComidaDao {
	
	
	public SessionFactory getSesion() {
		return sesion;
	}

	public void setSesion(SessionFactory sesion) {
		this.sesion = sesion;
	}

	@Inject
	private SessionFactory sesion;

	@Override
	public Long crearComida(Comida comida) {
		Long idGenerado = (Long) sesion.getCurrentSession().save(comida);
		return idGenerado;
	}

	public Comida ObtenerPorId(Long id) {
		Comida c = (Comida) sesion.getCurrentSession().get(Comida.class, id);
		return c;
	}

	public void borrar(Comida comida) {
		sesion.getCurrentSession().delete(comida);
	}

	@Override
	public List<Comida> obtenerComidasSegunCalorias(Double calorias) {
		return sesion.getCurrentSession().createCriteria(Comida.class).add(Restrictions.le("calorias", calorias))
				.add(Restrictions.gt("calorias", 50.0)).list();
	}

	@Override
	public List<Comida> obtenerComidas() {
		List<Comida> comidas = sesion.getCurrentSession().createCriteria(Comida.class).list();

		return comidas;
	}

	public List<Comida> obtenerComidasMasVistas() {
		List<Comida> comidasVistas = sesion.getCurrentSession().createCriteria(Comida.class)
				.addOrder(Order.desc("contador")).setMaxResults(5).list();
		return comidasVistas;
	}

	public List<Comida> obtenerComidasMenosVistas() {
		List<Comida> comidasVistas = sesion.getCurrentSession().createCriteria(Comida.class)
				.addOrder(Order.asc("contador")).setMaxResults(5).list();
		return comidasVistas;
	}

	@Override
	public void updateComida(Comida comida) {
		sesion.getCurrentSession().update(comida);
	}

	@Override
	public List<Comida> obtenerComidasPorRestriccion(Restriccion restriccion) {
		List<Comida> comidas = sesion.getCurrentSession().createCriteria(Comida.class)
				.createAlias("restricciones", "rjoin").add(Restrictions.eq("rjoin.id", restriccion.getId())).list();
		return comidas;
	}

}
