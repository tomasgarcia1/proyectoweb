package ar.edu.unlam.tallerweb1.servicios;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Comida;
import ar.edu.unlam.tallerweb1.modelo.Pedido;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.PedidoDao;

@Service
@Transactional
public class ServicioPedidoImpl implements ServicioPedido {

	@Inject
	private PedidoDao pedidoDao;
	@Inject
	private ServicioComida servicioComida;

	@Override
	public Long crearPedido(Pedido pedido) {
		
		return pedidoDao.crearPedido(pedido);
	}
	/*
	 * Se acumulan los precios de cada comida en la variable importe.
	 * Esto da como resultado el importe total del pedido.
	 */
	@Override
	public Double calcularImporteTotal(Pedido pedido) {
		Double importe=0.0;
		for (Comida comidas : pedido.getComidas()) {
			importe+=comidas.getPrecio();
		}
		return importe;
	}

	@Override
	public void cancelarPedido(Long id) {
		pedidoDao.cancelarPedido(id);
	}

	@Override
	public Pedido buscarPedidoPorId(Long id) {
		return pedidoDao.buscarPedidoPorId(id);
	}
	@Override
	public void actualizarPedido(Pedido pedido) {
		pedidoDao.actualizarPedido(pedido);
		
	}
	/*
	 * Recibe por parametro el ID del usuario para buscar sus restricciones.
	 * Crea un objeto de tipo Comida por cada comida del dia, usando los métodos de servicioComida.
	 * Estas comidas se almacenan en una lista de comidas, que sera tomada como valor de retorno
	 */
	@Override
	public List<Comida> generarComidasPorRestricciones(Long id) {
		Comida desayuno=servicioComida.sugerirDesayunoPorRestricciones(id);
		Comida almuerzo=servicioComida.sugerirAlmuerzoPorRestricciones(id);
		Comida cena=servicioComida.sugerirCenaPorRestricciones(id);
		List<Comida> comidas=new ArrayList<Comida>();
		comidas.add(desayuno);
		comidas.add(almuerzo);
		comidas.add(cena);
		return comidas;
	}
	/*
	 * Mismo funcionamiento que generarComidasPorRestricciones, solo que recibe como parametro el usuario.
	 */
	@Override
	public List<Comida> generarComidasPorCalorias(Usuario usuario) {
		Comida desayuno=servicioComida.sugerirDesayunoPorCalorias(usuario.getCaloriasDiarias());
		Comida almuerzo=servicioComida.sugerirAlmuerzoPorCalorias(usuario.getCaloriasDiarias());
		Comida cena=servicioComida.sugerirCenaPorCalorias(usuario.getCaloriasDiarias());
		List<Comida> comidas=new ArrayList<Comida>();
		comidas.add(desayuno);
		comidas.add(almuerzo);
		comidas.add(cena);
		return comidas;
	}
	/*
	 * Recibe una lista de comidas como parametro para extraer el ID de las comidas.
	 * Se crea una variable de tipo StringBuilder llamada idComidas: 
	 * esto se debe a que la clase StringBuilder es una clase mutable, es decir, que puede cambiar su estado.
	 * No se usa un String al principio porque es una clase inmutable.
	 * Se usa un Iterator para recorrer la lista de comidas, y se concatena el ID de la comida a la variable idComidas.
	 * En caso de que todavia haya un objeto en la lista, se concatena una coma para ser usada para separar en posiciones al String.
	 * Se toma como valor de retorno la variable idComidas casteada a String.
	 */
	@Override
	public String concatenarIdComidas(List<Comida> comidas) {
		StringBuilder idComidas=new StringBuilder();
		Iterator<Comida> it = comidas.iterator();
		while (it.hasNext()) {
			Comida comida = it.next();
			idComidas.append(comida.getId().toString());
			;
			if(it.hasNext())
				idComidas.append(",");
		}
		return idComidas.toString();
	}
	/*
	 * Se recibe como parametro el String con los ID de las comidas del pedido seleccionado en el menuSugerido.
	 * Se crea un array de Strings, tomando a la coma como separador de posiciones. Cada posicion es un ID de una comida.
	 * Ejemplo: si tengo "8,12", el array tendra en su primer posicion "8", y en la segunda "12".
	 * Se crea un objeto de tipo Pedido para guardar el pedido, y un ArrayList de comidas para guardar las comidas del pedido.
	 * Se usa un for para recorrer el array de String e ir agregando en el ArrayList cada comida.
	 * Para encontrar la comida, castea el ID (que es de tipo String en el array) a Long, ya que el metodo obtenerPorId recibe
	 * como parametro un Long.
	 * Se guarda el ArrayList en el objeto pedido.
	 * Se calcula el precio del pedido con el metodo calcularImporteTotal.
	 * Se toma como valor de retorno el objeto pedido.
	 */
	@Override
	public Pedido generarPedidoPorIdComidas(String idComidas) {
		String[] arrayComidas=idComidas.split(",");
		Pedido pedido=new Pedido();
		List<Comida> comidas=new ArrayList<Comida>();
		
		for(int i=0;i<arrayComidas.length;i++)
		{
			comidas.add(servicioComida.obtenerPorId(Long.parseLong(arrayComidas[i])));
		}
		pedido.setComidas(comidas);
		pedido.setPrecio(this.calcularImporteTotal(pedido));
		return pedido;
	}
	
	@Override
	public List<Comida> generarMenusSugeridos(Usuario usuario) {
		Comida desayuno=servicioComida.sugerirDesayuno(usuario.getCaloriasDiarias(), usuario.getId());
		Comida almuerzo=servicioComida.sugerirAlmuerzo(usuario.getCaloriasDiarias(), usuario.getId());
		Comida cena=servicioComida.sugerirCena(usuario.getCaloriasDiarias(), usuario.getId());
		List<Comida> comidas=new ArrayList<Comida>();
		comidas.add(desayuno);
		comidas.add(almuerzo);
		comidas.add(cena);
		return comidas;
	}
	@Override
	public List<Pedido> listarPedidosPorUsuario(Usuario usuario) {
		return pedidoDao.listarPedidosPorUsuario(usuario);
	}
}
