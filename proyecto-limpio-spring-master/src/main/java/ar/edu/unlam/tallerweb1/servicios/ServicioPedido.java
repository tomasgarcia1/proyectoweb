package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;
import java.util.TreeSet;

import org.springframework.ui.ModelMap;

import ar.edu.unlam.tallerweb1.modelo.Comida;
import ar.edu.unlam.tallerweb1.modelo.Estado;
import ar.edu.unlam.tallerweb1.modelo.Pedido;
import ar.edu.unlam.tallerweb1.modelo.Posicion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

public interface ServicioPedido {
	Long crearPedido(Pedido pedido);

	List<Comida> generarComidasPorRestricciones(Long id);

	Double calcularImporteTotal(List<Comida> comidas, Double precioEnvio);

	Pedido buscarPedidoPorId(Long id);

	void actualizarPedido(Pedido pedido, Estado estado);

	List<Comida> generarMenusSugeridos(Usuario usuario);

	String concatenarIdComidas(List<Comida> comidas);

	List<Comida> obtenerComidasConcatenadas(String idComidas);

	Pedido generarPedidoPorIdComidas(String idComidas, Posicion posicion, Posicion posicionSucursal);

	List<Pedido> listarPedidosPorUsuario(Usuario usuario);

	List<Pedido> listarPedidos();

	Double calcularTiempo(Double distancia);

	Double distanciaCoord(Double lat1, Double lng1, Double lat2, Double lng2);

	Double convertirPrecio(Double precio);

	TreeSet<Comida> comidasMasPedidas(Long id);
	
	List<Comida> listarComidasDeUnPedido(Pedido pedido);
	
	Posicion listarPosicionesDeUnUsuario(Pedido pedido);

	ModelMap generarPreviewPosicion(Posicion posicionSucursal,Posicion posicion);

	Double calcularImporteTotalComidaUnica(Comida comida, Double precioEnvio);
}
