package ar.edu.unlam.tallerweb1.servicios;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

import javax.inject.Inject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import ar.edu.unlam.tallerweb1.modelo.Comida;
import ar.edu.unlam.tallerweb1.modelo.Estado;
import ar.edu.unlam.tallerweb1.modelo.Pedido;
import ar.edu.unlam.tallerweb1.modelo.Posicion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.PedidoDao;

@Service
@Transactional
public class ServicioPedidoImpl implements ServicioPedido {

	@Inject
	private PedidoDao pedidoDao;
	@Inject
	private ServicioComida servicioComida;

	// ----------CALCULAR TIEMPO----------
	
	@Override
	public ModelMap generarPreviewPosicion(Posicion posicionSucursal,Posicion posicion) {
		ModelMap model = new ModelMap();
		Double distancia = this.distanciaCoord(posicionSucursal.getLatitude(),
				posicionSucursal.getLongitude(), posicion.getLatitude(), posicion.getLongitude());

		Double tiempo = this.calcularTiempo(distancia);

		BigDecimal time = new BigDecimal(tiempo);
		time = time.setScale(0, RoundingMode.HALF_UP);

		// Double precio=12*distancia;

		Double precio = this.convertirPrecio(distancia);

		model.put("distancia", (int) (distancia + 1));
		model.put("precio", precio);
		model.put("tiempo", time);

		model.addAttribute("posicion", posicion);
		
		return model;
	}
	
	@Override
	public Double calcularTiempo(Double distancia) {
		Double velocidad = (double) (40 * 1000) / 60;
		Double distanciaEnMetros = (double) (distancia * 1000);

		return ((distanciaEnMetros / velocidad) + 1.5);
	}

	// ---------------DISTANCIA-------------
	@Override
	public Double distanciaCoord(Double lat1, Double lng1, Double lat2, Double lng2) {
		Double radioTierra = (double) 6371;// en kilómetros
		Double dLat = Math.toRadians(lat2 - lat1);
		Double dLng = Math.toRadians(lng2 - lng1);
		Double sindLat = Math.sin(dLat / 2);
		Double sindLng = Math.sin(dLng / 2);
		Double va1 = Math.pow(sindLat, 2)
				+ Math.pow(sindLng, 2) * Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2));
		Double va2 = 2 * Math.atan2(Math.sqrt(va1), Math.sqrt(1 - va1));
		Double distancia = radioTierra * va2;
		return distancia;
	}

	// ------------CREAR PEDIDO----------
	@Override
	public Long crearPedido(Pedido pedido) {

		return pedidoDao.crearPedido(pedido);
	}

	// ---------CALCULO DE IMPORTE TOTAL----------
	/*
	 * Se acumulan los precios de cada comida en la variable importe. Esto da como
	 * resultado el importe total del pedido.
	 */
	@Override
	public Double calcularImporteTotal(List<Comida> comidas, Double precioEnvio) {
		Double importe = 0.0;
		for (Comida comida : comidas) {
			importe += comida.getPrecio();
		}
		return importe + precioEnvio;
	}
	
	@Override
	public Double calcularImporteTotalComidaUnica(Comida comida, Double precioEnvio) {
		Double importe = comida.getPrecio();
		return importe + precioEnvio;
	}


	// ---------GENERAR COMIDAS POR RESTRICCIONES----------

	/*
	 * Recibe por parametro el ID del usuario para buscar sus restricciones. Crea un
	 * objeto de tipo Comida por cada comida del dia, usando los métodos de
	 * servicioComida. Estas comidas se almacenan en una lista de comidas, que sera
	 * tomada como valor de retorno
	 */
	@Override
	public List<Comida> generarComidasPorRestricciones(Long id) {
		Comida desayuno = servicioComida.sugerirDesayunoPorRestricciones(id);
		Comida almuerzo = servicioComida.sugerirAlmuerzoPorRestricciones(id);
		Comida cena = servicioComida.sugerirCenaPorRestricciones(id);
		List<Comida> comidas = new ArrayList<Comida>();
		comidas.add(desayuno);
		comidas.add(almuerzo);
		comidas.add(cena);
		return comidas;
	}

	// ----------CONCATENACION ID COMIDAS----------

	/*
	 * Recibe una lista de comidas como parametro para extraer el ID de las comidas.
	 * Se crea una variable de tipo StringBuilder llamada idComidas: esto se debe a
	 * que la clase StringBuilder es una clase mutable, es decir, que puede cambiar
	 * su estado. No se usa un String al principio porque es una clase inmutable. Se
	 * usa un Iterator para recorrer la lista de comidas, y se concatena el ID de la
	 * comida a la variable idComidas. En caso de que todavia haya un objeto en la
	 * lista, se concatena una coma para ser usada para separar en posiciones al
	 * String. Se toma como valor de retorno la variable idComidas casteada a
	 * String.
	 */
	@Override
	public String concatenarIdComidas(List<Comida> comidas) {
		StringBuilder idComidas = new StringBuilder();
		Iterator<Comida> it = comidas.iterator();
		while (it.hasNext()) {
			Comida comida = it.next();
			if (comida.getId() != 0) {
				idComidas.append(comida.getId().toString());
				idComidas.append(",");
			}
		}
		if (idComidas.length() > 0) {
			idComidas.deleteCharAt(idComidas.length() - 1);
		}

		return idComidas.toString();
	}

	// ------------GENERAR MENUS SUGERIDOS-----------

	@Override
	public List<Comida> generarMenusSugeridos(Usuario usuario) {
		Comida desayuno = servicioComida.sugerirDesayuno(usuario.getCaloriasDiarias(), usuario.getId());
		Comida almuerzo = servicioComida.sugerirAlmuerzo(usuario.getCaloriasDiarias(), usuario.getId());
		Comida cena = servicioComida.sugerirCena(usuario.getCaloriasDiarias(), usuario.getId());
		List<Comida> comidas = new ArrayList<Comida>();
		comidas.add(desayuno);
		comidas.add(almuerzo);
		comidas.add(cena);
		return comidas;
	}

	// ----------LISTAR COMIDAS MAS PEDIDAS POR CLIENTES-------------

	@Override
	public TreeSet<Comida> comidasMasPedidas(Long id) {
		List<Comida> comida = servicioComida.listarComidasSegunRestricciones(id);
		List<Comida> comidasmaspedidas = new LinkedList<>();
		TreeSet<Comida> comidaslistar = new TreeSet<Comida>();
		for (Comida c : comida) {
			if (c.getPedidos().size() >= 3) {
				comidasmaspedidas.add(c);
			}
		}
		for (Comida comidaAux : comidasmaspedidas) {
			comidaslistar.add(comidaAux);
		}

		return comidaslistar;
	}

	// ----------OBTENER COMIDAS CONCATENADAS-------------

	public List<Comida> obtenerComidasConcatenadas(String idComidas) {
		String[] arrayComidas = idComidas.split(",");
		List<Comida> comidas = new ArrayList<Comida>();
		for (int i = 0; i < arrayComidas.length; i++) {
			comidas.add(servicioComida.obtenerPorId(Long.parseLong(arrayComidas[i])));
		}
		return comidas;
	}

	// --------------GENERAR PEDIDO POR ID COMIDAS-----------

	/*
	 * Se recibe como parametro el String con los ID de las comidas del pedido
	 * seleccionado en el menuSugerido. Se crea un array de Strings, tomando a la
	 * coma como separador de posiciones. Cada posicion es un ID de una comida.
	 * Ejemplo: si tengo "8,12", el array tendra en su primer posicion "8", y en la
	 * segunda "12". Se crea un objeto de tipo Pedido para guardar el pedido, y un
	 * ArrayList de comidas para guardar las comidas del pedido. Se usa un for para
	 * recorrer el array de String e ir agregando en el ArrayList cada comida. Para
	 * encontrar la comida, castea el ID (que es de tipo String en el array) a Long,
	 * ya que el metodo obtenerPorId recibe como parametro un Long. Se guarda el
	 * ArrayList en el objeto pedido. Se calcula el precio del pedido con el metodo
	 * calcularImporteTotal. Se toma como valor de retorno el objeto pedido.
	 */
	@Override
	public Pedido generarPedidoPorIdComidas(String idComidas, Posicion posicion, Posicion posicionSucursal) {

		Pedido pedido = new Pedido();
		List<Comida> comidas = this.obtenerComidasConcatenadas(idComidas);
		Double distancia = this.distanciaCoord(posicionSucursal.getLatitude(), posicionSucursal.getLongitude(),
				posicion.getLatitude(), posicion.getLongitude());
		Double precioEnvio = this.convertirPrecio(distancia);
		pedido.setComidas(comidas);
		pedido.setPrecio(this.calcularImporteTotal(comidas, precioEnvio));
		pedido.setUbicacionDestino(posicion);
		return pedido;
	}

	@Override
	public Double convertirPrecio(Double distancia) {
		// le pongo un precio diciendo que cada km sale 12 pesos
		Double precio = distancia * 12;
		return Math.rint(precio * 100) / 100;
	}

	@Override
	public void actualizarPedido(Pedido pedido, Estado estado) {
		pedido.setEstado(estado);
		pedidoDao.actualizarPedido(pedido);
	}

	public void updatePedido(Pedido pedido) {
		pedidoDao.actualizarPedido(pedido);
	}

	@Override
	public List<Pedido> listarPedidosPorUsuario(Usuario usuario) {
		return pedidoDao.listarPedidosPorUsuario(usuario);
	}

	@Override
	public List<Pedido> listarPedidos() {
		return pedidoDao.listarPedidos();
	}

	@Override
	public Pedido buscarPedidoPorId(Long id) {
		return pedidoDao.buscarPedidoPorId(id);
	}

	@Override
	public List<Comida> listarComidasDeUnPedido(Pedido pedido) {
		return pedidoDao.listarComidasDeUnPedido(pedido);
	}
	
	@Override
	public Posicion listarPosicionesDeUnUsuario(Pedido pedido) {
		return pedidoDao.listarPosicion(pedido);
	}


	

}
