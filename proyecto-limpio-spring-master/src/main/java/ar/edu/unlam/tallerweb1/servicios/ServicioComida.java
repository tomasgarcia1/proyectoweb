package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Comida;

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

	Comida sugerirDesayuno(Double caloriasDiarias, Long id);

	Comida sugerirAlmuerzo(Double caloriasDiarias, Long id);

	Comida sugerirCena(Double caloriasDiarias, Long id);

}
