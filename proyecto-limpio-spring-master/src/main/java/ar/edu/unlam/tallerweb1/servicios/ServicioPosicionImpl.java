package ar.edu.unlam.tallerweb1.servicios;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Posicion;
import ar.edu.unlam.tallerweb1.repositorios.PosicionDao;

@Service
@Transactional
public class ServicioPosicionImpl implements ServicioPosicion {
	
	@Inject
	private PosicionDao posicionDao;
	
	@Override
	public Long crearPosicion(Posicion posicion) {		
		return posicionDao.crearPosicion(posicion);
	}

	@Override
	public Posicion obtenerPosicionPorId(Long id) {
		return posicionDao.obtenerPosicionPorId(id);
	}

}
