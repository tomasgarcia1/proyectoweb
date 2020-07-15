package ar.edu.unlam.tallerweb1.servicios;

import java.time.LocalDate;
import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.CuponDescuento;
import ar.edu.unlam.tallerweb1.modelo.MoldeCupon;

public interface ServicioCuponDescuento {

	Long agregarCupon(CuponDescuento cupon);

	void actualizarCupon(CuponDescuento cupon);

	void eliminarCupon(CuponDescuento cupon);

	CuponDescuento consultarCuponPorId(Long id);

	List<CuponDescuento> cuponesUsuario(Long id);

	List<CuponDescuento> cuponesUsuarioHabilitados(Long id);

	List<MoldeCupon> listarMoldeCupon();

	MoldeCupon valorMoldeAleatorio();

	Double calcularImporteConCupon(Long idCupon, Double precioPedido);

	CuponDescuento agregarCuponDescuentoUsuarioSemana(Double precioPedido, LocalDate fecha, Long id);
	
	void vencimientoDeCupon(Long id); 
	
	// void agregarCuponDescuentoUsuarioGastos(Double precioPedido, Long id);
	
	// void agregarCuponDescuentoUsuario2Fechas(Double precioPedido, LocalDate fecha, Long id);

}
