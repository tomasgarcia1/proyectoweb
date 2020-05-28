package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Comida;

@Repository
@Transactional
public class ComidaDaoImpl implements ComidaDao {
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
	public List<Comida> obtenerComidasSegunCalorias (Double calorias){
		return sesion.getCurrentSession().createCriteria(Comida.class)
				.add(Restrictions.le("calorias", calorias))
				.list();
	}

}
