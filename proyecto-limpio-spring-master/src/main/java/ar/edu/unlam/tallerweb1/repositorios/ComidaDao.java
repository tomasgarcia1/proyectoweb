package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Comida;

public interface ComidaDao {

	Long crearComida(Comida comida);
	Comida ObtenerPorId(Long id);
	void borrar(Comida comida);

}

}

