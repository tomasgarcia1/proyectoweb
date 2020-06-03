package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Comida;
import ar.edu.unlam.tallerweb1.modelo.Restriccion;

public interface ServicioComida {

	Long crearComida(Comida comida);

	Comida obtenerPorId(Long id);

	void borrar(Comida comida);

	Comida sugerirDesayunoPorCalorias(Double caloriasDiarias);

	Comida sugerirAlmuerzoPorCalorias(Double caloriasDiarias);

	Comida sugerirCenaPorCalorias(Double caloriasDiarias);

	Comida sugerirDesayunoPorRestricciones(Long id);

	Comida sugerirAlmuerzoPorRestricciones(Long id);

	Comida sugerirCenaPorRestricciones(Long id);

}
