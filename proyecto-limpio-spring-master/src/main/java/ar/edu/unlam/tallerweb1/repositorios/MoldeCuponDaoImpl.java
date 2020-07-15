package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.MoldeCupon;

@Repository
@Transactional
public class MoldeCuponDaoImpl implements MoldeCuponDao {

	@Inject
	private SessionFactory sesion;

	@Override
	public void agregarMoldeCupon(MoldeCupon molde) {
		molde.setEstado(true);
		sesion.getCurrentSession().save(molde);
	}

	@Override
	public void actualizarMoldeCupon(MoldeCupon molde) {
		sesion.getCurrentSession().update(molde);

	}

	@Override
	public void eliminarMoldeCupon(MoldeCupon molde) {
		sesion.getCurrentSession().delete(molde);
	}

	@Override
	public MoldeCupon consultarMoldeCuponPorId(Long id) {
		return sesion.getCurrentSession().get(MoldeCupon.class, id);
	}

	@Override
	public List<MoldeCupon> listarMoldes() {
		List<MoldeCupon> moldes = sesion.getCurrentSession().createCriteria(MoldeCupon.class).list();
		return moldes;
	}

	@Override
	public List<MoldeCupon> listarMoldesHabilitados() {
		List<MoldeCupon> moldes = sesion.getCurrentSession().createCriteria(MoldeCupon.class).add(Restrictions.eq("estado", true)).list();
		return moldes;
	}

}
