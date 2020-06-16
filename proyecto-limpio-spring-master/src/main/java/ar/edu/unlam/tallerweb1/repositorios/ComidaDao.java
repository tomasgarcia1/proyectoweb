package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Comida;
import ar.edu.unlam.tallerweb1.modelo.TipoHorario;

public interface ComidaDao {

	Long crearComida(Comida comida);

	Comida ObtenerPorId(Long id);

	void borrar(Comida comida);

	List<Comida> obtenerComidasSegunCalorias(Double calorias);

	List<Comida> obtenerComidas();
	
	void updateComida(Comida comida);

}
