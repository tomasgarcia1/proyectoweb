package ar.edu.unlam.tallerweb1.servicios;

import java.util.ArrayList;
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
	 * Estas comidas se almacenan en una lista de comidas, las cuales se agregan a la variable pedido.
	 * Se calcula el importe total del pedido creado.
	 * Se devuelve una variable de tipo Pedido.
	 */
	@Override
	public Pedido generarPedidoPorRestricciones(Long id) {
		Comida desayuno=servicioComida.sugerirDesayunoPorRestricciones(id);
		Comida almuerzo=servicioComida.sugerirAlmuerzoPorRestricciones(id);
		Comida cena=servicioComida.sugerirCenaPorRestricciones(id);
		Pedido pedido=new Pedido();
		List<Comida> comidas=new ArrayList<Comida>();
		comidas.add(desayuno);
		comidas.add(almuerzo);
		comidas.add(cena);
		pedido.setComidas(comidas);
		pedido.setPrecio(calcularImporteTotal(pedido));
		return pedido;
	}
	
	@Override
	public Pedido generarPedidoPorCalorias(Usuario usuario) {
		Comida desayuno=servicioComida.sugerirDesayunoPorCalorias(usuario.getCaloriasDiarias());
		Comida almuerzo=servicioComida.sugerirAlmuerzoPorCalorias(usuario.getCaloriasDiarias());
		Comida cena=servicioComida.sugerirCenaPorCalorias(usuario.getCaloriasDiarias());
		Pedido pedido=new Pedido();
		List<Comida> comidas=new ArrayList<Comida>();
		comidas.add(desayuno);
		comidas.add(almuerzo);
		comidas.add(cena);
		pedido.setComidas(comidas);
		pedido.setPrecio(calcularImporteTotal(pedido));
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
}
