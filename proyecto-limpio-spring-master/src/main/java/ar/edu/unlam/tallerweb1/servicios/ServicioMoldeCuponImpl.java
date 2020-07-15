package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import ar.edu.unlam.tallerweb1.modelo.MoldeCupon;
import ar.edu.unlam.tallerweb1.repositorios.MoldeCuponDao;

@Service
@Transactional
public class ServicioMoldeCuponImpl implements ServicioMoldeCupon {
	
	@Inject
	private MoldeCuponDao moldeCuponDao;
	
	@Override
	public void agregarMoldeCupon(MoldeCupon molde) {
		moldeCuponDao.agregarMoldeCupon(molde);
	}

	@Override
	public void actualizarMoldeCupon(MoldeCupon molde) {
		moldeCuponDao.actualizarMoldeCupon(molde);
	}

	@Override
	public void eliminarMoldeCupon(MoldeCupon molde) {
		moldeCuponDao.eliminarMoldeCupon(molde);
	}

	@Override
	public MoldeCupon consultarMoldeCuponPorId(Long id) {
		return moldeCuponDao.consultarMoldeCuponPorId(id);
	}


	@Override
	public List<MoldeCupon> listarMoldes() {
		return moldeCuponDao.listarMoldes();
	}
	
	public List<MoldeCupon> listarMoldesHabilitados() {
		return moldeCuponDao.listarMoldesHabilitados();
	}
	

}
