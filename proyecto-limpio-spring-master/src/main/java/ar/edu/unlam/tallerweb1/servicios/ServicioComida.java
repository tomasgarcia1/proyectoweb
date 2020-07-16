package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;
import java.util.TreeSet;

import ar.edu.unlam.tallerweb1.modelo.Comida;
import ar.edu.unlam.tallerweb1.modelo.TipoHorario;


public interface ServicioComida {

	Long crearComida(Comida comida);

	Comida obtenerPorId(Long id);

	void borrar(Comida comida);

	Comida sugerirDesayunoPorRestricciones(Long id);

	Comida sugerirAlmuerzoPorRestricciones(Long id);

	Comida sugerirCenaPorRestricciones(Long id);

	Comida sugerirDesayuno(Double caloriasDiarias, Long id);

	Comida sugerirAlmuerzo(Double caloriasDiarias, Long id);

	Comida sugerirCena(Double caloriasDiarias, Long id);

	List<Comida> obtenerComidas();

	List<Comida> obtenerComidasSegunTipoHorario(TipoHorario tipo);

	List<Comida> listarComidasSegunRestricciones(Long id);
	
	List<Comida> obtenerComidasDeRestriccion(String nombre);

	TreeSet<Comida> listarComidasUsuarioSinRepetir(Long id);

	List<Comida> contadorComida(Comida comida);
	
	List<Comida> comidasMasVistas();
	
	List<Comida> comidasMenosVistas();

	Comida obtenerComidaPorNombre(String nombre);

	void updateComida(Comida comida);

}
