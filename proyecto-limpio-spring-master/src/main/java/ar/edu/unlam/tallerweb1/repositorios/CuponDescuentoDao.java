package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import org.hibernate.SessionFactory;

import ar.edu.unlam.tallerweb1.modelo.CuponDescuento;
import ar.edu.unlam.tallerweb1.modelo.MoldeCupon;

public interface CuponDescuentoDao {

	Long agregarCupon(CuponDescuento cupon);

	void actualizarCupon(CuponDescuento cupon);

	void eliminarCupon(CuponDescuento cupon);

	CuponDescuento consultarCuponPorId(Long id);

	List<CuponDescuento> listarCupones();

	List<CuponDescuento> listarCuponesHabilitados();
	
	List<MoldeCupon> listarMoldeCupon();

	void setSesion(SessionFactory sessionFactory);

}
