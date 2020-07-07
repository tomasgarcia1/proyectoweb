package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.CuponDescuento;
import ar.edu.unlam.tallerweb1.modelo.MoldeCupon;

public interface ServicioCuponDescuento {

	void agregarCupon(CuponDescuento cupon);

	void actualizarCupon(CuponDescuento cupon);

	void eliminarCupon(CuponDescuento cupon);

	CuponDescuento consultarCuponPorId(Long id);

	List<CuponDescuento> cuponesUsuario(Long id);
	
	List<CuponDescuento> cuponesUsuarioHabilitados(Long id);

	List<MoldeCupon> listarMoldeCupon();
	
	MoldeCupon valorMoldeAleatorio();
	
	Double calcularImporteConCupon(Long idCupon, Double precioPedido);

	void agregarCuponDescuentoUsuario(Double precioPedido, Long id);

}
