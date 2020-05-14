package ar.edu.unlam.tallerweb1.servicios;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Comida;
import ar.edu.unlam.tallerweb1.repositorios.ComidaDao;

@Service
@Transactional
public class ServicioComidaImpl implements ServicioComida {
	@Inject
	private ComidaDao comidaDao;

	@Override
	public Long crearComida(Comida comida) {
		return comidaDao.crearComida(comida);
	}

	@Override
	public Comida obtenerPorId(Long id) {
		return comidaDao.ObtenerPorId(id);
	}

	public void borrar(Comida comida) {
		comidaDao.borrar(comida);
	}

}
