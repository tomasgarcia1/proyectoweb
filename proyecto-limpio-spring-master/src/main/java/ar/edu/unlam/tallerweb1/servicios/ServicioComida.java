package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Comida;

public interface ServicioComida {

	Long crearComida(Comida comida);
	Comida obtenerPorId(Long id);
	void borrar(Comida comida);


}

}

