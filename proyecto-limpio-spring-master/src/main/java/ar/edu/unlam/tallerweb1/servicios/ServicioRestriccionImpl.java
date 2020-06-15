package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Restriccion;
import ar.edu.unlam.tallerweb1.repositorios.RestriccionDao;

@Service
@Transactional
public class ServicioRestriccionImpl implements ServicioRestriccion {

	@Inject
	private RestriccionDao restriccionDao;

	@Override
	public Long crearRestriccion(Restriccion restriccion) {
 
		return restriccionDao.crearRestriccion(restriccion);
	}

	@Override
	public Restriccion obtenerRestriccionPorId(Long id) {
		return restriccionDao.obtenerRestriccionPorId(id);
	}

	@Override
	public void borrarRestriccion(Restriccion restriccion) {
		restriccionDao.borrarRestriccion(restriccion);
	}

	@Override
	public List<Restriccion> obtenerRestricciones() {		
		return restriccionDao.obtenerRestricciones();
	}

}
