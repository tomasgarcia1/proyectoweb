package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.CuponDescuento;
import ar.edu.unlam.tallerweb1.modelo.MoldeCupon;

public interface CuponDescuentoDao {

	void agregarCupon(CuponDescuento cupon);

	void actualizarCupon(CuponDescuento cupon);

	void eliminarCupon(CuponDescuento cupon);

	CuponDescuento consultarCuponPorId(Long id);

	List<CuponDescuento> listarCupones();

	List<CuponDescuento> listarCuponesHabilitados();
	
	List<MoldeCupon> listarMoldeCupon();

}
