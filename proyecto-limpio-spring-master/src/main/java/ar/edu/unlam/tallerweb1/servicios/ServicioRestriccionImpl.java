package ar.edu.unlam.tallerweb1.servicios;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Restriccion;
import ar.edu.unlam.tallerweb1.repositorios.RestriccionDao;

@Service
@Transactional
public class ServicioRestriccionImpl implements ServicioRestriccion {
	
	@Inject
	RestriccionDao restriccionDao;
	
	@Override
	public Long crearRestriccion(Restriccion restriccion) {
		 
		return this.restriccionDao.crearRestriccion(restriccion);
	}

}
