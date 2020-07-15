package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.MoldeCupon;

public interface MoldeCuponDao {

	void agregarMoldeCupon(MoldeCupon molde);

	void actualizarMoldeCupon(MoldeCupon molde);

	void eliminarMoldeCupon(MoldeCupon molde);

	MoldeCupon consultarMoldeCuponPorId(Long id);
	
	List<MoldeCupon> listarMoldes();

	List<MoldeCupon> listarMoldesHabilitados();
}
