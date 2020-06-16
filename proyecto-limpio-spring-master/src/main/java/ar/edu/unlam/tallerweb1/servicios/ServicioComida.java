package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Comida;
import ar.edu.unlam.tallerweb1.modelo.TipoHorario;

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

	List<Comida> obtenerComidas();

	List<Comida> obtenerComidasSegunTipoHorario(TipoHorario tipo);
	
	List<Comida> listarComidasSegunRestricciones(Long id);
	
	Comida obtenerComidaPorNombre(String nombre);

	void updateComida(Comida comida);

}
