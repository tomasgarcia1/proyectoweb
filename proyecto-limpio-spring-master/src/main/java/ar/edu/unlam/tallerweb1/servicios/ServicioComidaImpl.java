package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

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
	
	@Override
	public Comida sugerirDesayunoPorCalorias(Double caloriasDiarias) {
		Double caloriasDesayuno = caloriasDiarias*0.35;
		List<Comida> desayuno = comidaDao.obtenerComidasSegunCalorias(caloriasDesayuno);
		Integer numeroRandom = (int) (Math.random()*desayuno.size());
		return desayuno.get(numeroRandom);
	}
	
	@Override
	public Comida sugerirAlmuerzoPorCalorias(Double caloriasDiarias) {
		Double caloriasAlmuerzo = caloriasDiarias*0.45;
		List<Comida> almuerzo = comidaDao.obtenerComidasSegunCalorias(caloriasAlmuerzo);
		Integer numeroRandom = (int) (Math.random()*almuerzo.size());
		return almuerzo.get(numeroRandom);
	}
	
	@Override
	public Comida sugerirCenaPorCalorias(Double caloriasDiarias) {
		Double caloriasCena = caloriasDiarias*0.20;
		List<Comida> cena = comidaDao.obtenerComidasSegunCalorias(caloriasCena);
		Integer numeroRandom = (int) (Math.random()*cena.size());
		return cena.get(numeroRandom);
	}

}
