package ar.edu.unlam.tallerweb1.modelo;

import java.util.Comparator;

public class OrdenPorContador implements Comparator<Comida> {

	@Override
	public int compare(Comida comida1, Comida comida2) {

		return comida1.getContador().compareTo(comida2.getContador());

	}

}
