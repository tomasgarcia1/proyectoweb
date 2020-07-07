package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.CuponDescuento;
import ar.edu.unlam.tallerweb1.modelo.MoldeCupon;

@Repository
@Transactional
public class CuponDescuentoDaoImpl implements CuponDescuentoDao {

	@Inject
	private SessionFactory session;

	@Override
	public void agregarCupon(CuponDescuento cupon) {
		session.getCurrentSession().save(cupon);
	}

	@Override
	public void actualizarCupon(CuponDescuento cupon) {
		session.getCurrentSession().update(cupon);
	}

	@Override
	public void eliminarCupon(CuponDescuento cupon) {
		session.getCurrentSession().delete(cupon);
	}

	@Override
	public CuponDescuento consultarCuponPorId(Long id) {
		CuponDescuento cupon = (CuponDescuento) session.getCurrentSession().get(CuponDescuento.class, id);
		return cupon;
	}

	@Override
	public List<CuponDescuento> listarCuponesHabilitados() {
		List<CuponDescuento> cupones = session.getCurrentSession().createCriteria(CuponDescuento.class)
				.add(Restrictions.eq("estado", true)).list();
		return cupones;
	}

	@Override
	public List<CuponDescuento> listarCupones() {
		List<CuponDescuento> cupones = session.getCurrentSession().createCriteria(CuponDescuento.class).list();
		return cupones;
	}

	@Override
	public List<MoldeCupon> listarMoldeCupon() {
		List<MoldeCupon> moldeCupon = session.getCurrentSession().createCriteria(MoldeCupon.class).list();
		return moldeCupon;
	}

}
