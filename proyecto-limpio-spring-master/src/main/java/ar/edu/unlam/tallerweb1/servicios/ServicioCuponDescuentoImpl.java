package ar.edu.unlam.tallerweb1.servicios;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import ar.edu.unlam.tallerweb1.modelo.CuponDescuento;
import ar.edu.unlam.tallerweb1.modelo.MoldeCupon;
import ar.edu.unlam.tallerweb1.modelo.Pedido;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.CuponDescuentoDao;
import ar.edu.unlam.tallerweb1.repositorios.PedidoDao;
import ar.edu.unlam.tallerweb1.repositorios.UsuarioDao;

@Service
@Transactional
public class ServicioCuponDescuentoImpl implements ServicioCuponDescuento {

	@Inject
	private CuponDescuentoDao cuponDescuentoDao;

	@Inject
	private UsuarioDao usuarioDao;

	@Inject
	private PedidoDao pedidoDao;

	@Override
	public void agregarCupon(CuponDescuento cupon) {
		cuponDescuentoDao.agregarCupon(cupon);
	}

	@Override
	public void actualizarCupon(CuponDescuento cupon) {
		cuponDescuentoDao.actualizarCupon(cupon);
	}

	@Override
	public void eliminarCupon(CuponDescuento cupon) {
		cuponDescuentoDao.eliminarCupon(cupon);
	}

	@Override
	public CuponDescuento consultarCuponPorId(Long id) {
		return cuponDescuentoDao.consultarCuponPorId(id);
	}

	public CuponDescuentoDao getCuponDescuentoDao() {
		return cuponDescuentoDao;
	}

	public void setCuponDescuentoDao(CuponDescuentoDao cuponDescuentoDao) {
		this.cuponDescuentoDao = cuponDescuentoDao;
	}

	public UsuarioDao getUsuarioDao() {
		return usuarioDao;
	}

	public void setUsuarioDao(UsuarioDao usuarioDao) {
		this.usuarioDao = usuarioDao;
	}

	public PedidoDao getPedidoDao() {
		return pedidoDao;
	}

	public void setPedidoDao(PedidoDao pedidoDao) {
		this.pedidoDao = pedidoDao;
	}

	// --------AGREGAR CUPON DE DESCUENTO USUARIO CALCULO SEGUN GASTOS--------
/*
	public void agregarCuponDescuentoUsuarioGastos(Double precioPedido, LocalDate fecha, Long id) {
		Usuario user = usuarioDao.obtenerUsuarioPorId(id);
		Integer cupones = cuponesUsuario(id).size();
		Double gastos = user.getGastos();
		Double importeAux = ((gastos + precioPedido) / 500);
		Double resultado = importeAux - cupones;
		if (resultado >= 1) {
			MoldeCupon molde = valorMoldeAleatorio();
			CuponDescuento cupon = new CuponDescuento();
			cupon.setValor(molde.getValor());
			cupon.setUsuario(user);
			cupon.setEstado(true);
			LocalDate fechavencimiento = fecha.plusDays(5);
			cupon.setFechavencimiento(fechavencimiento);
			agregarCupon(cupon);
		}
	}

	// ---AGREGAR CUPON DE DESCUENTO USUARIO CALCULO ENTRE DOS DIAS EN BASE A
	// GASTOS-----

	public void agregarCuponDescuentoUsuario2Fechas(Double precioPedido, LocalDate fecha, Long id) {
		Usuario user = usuarioDao.obtenerUsuarioPorId(id);
		LocalDate fechahoy = fecha;
		LocalDate fechaayer = fechahoy.minusDays(1);
		LocalDate fechaantesayer = fechahoy.minusDays(2);
		Double gastosUser = user.getGastos();
		Double gastosFinal = gastosUser + precioPedido;
		user.setGastos(gastosFinal);
		usuarioDao.update(user);
		List<Pedido> pedidos = pedidoDao.listarPedidosEntreFechasDeUnUsuario(user, fechaantesayer, fechaayer);
		Double gastosPedido = 0.0;
		for (Pedido pedido : pedidos) {
			gastosPedido += pedido.getPrecio();
		}
		if (gastosPedido >= 400.0) {
			MoldeCupon molde = valorMoldeAleatorio();
			CuponDescuento cupon = new CuponDescuento();
			cupon.setValor(molde.getValor());
			cupon.setUsuario(user);
			cupon.setEstado(true);
			LocalDate fechavencimiento = fechahoy.plusDays(5);
			cupon.setFechavencimiento(fechavencimiento);
			agregarCupon(cupon);
		}
	}

	*/
	// --------AGREGAR CUPON DE DESCUENTO USUARIO CALCULO SEGUN CANT PEDIDOS DE 1
	// SEMANA----------

	public void agregarCuponDescuentoUsuarioSemana(Double precioPedido, LocalDate fecha, Long id) {
		Usuario user = usuarioDao.obtenerUsuarioPorId(id);
		LocalDate fechahoy = fecha;
		LocalDate fechaweekatras = fechahoy.minusWeeks(1);
		Double gastosUser = user.getGastos();
		Double gastosFinal = gastosUser + precioPedido;
		user.setGastos(gastosFinal);
		usuarioDao.update(user);
		List<Pedido> pedidos = pedidoDao.listarPedidosEntreFechasDeUnUsuario(user, fechaweekatras, fechahoy);
		if (pedidos.size() >= 4) {
			MoldeCupon molde = valorMoldeAleatorio();
			CuponDescuento cupon = new CuponDescuento();
			cupon.setValor(molde.getValor());
			cupon.setUsuario(user);
			cupon.setEstado(true);
			LocalDate fechavencimiento = fechahoy.plusDays(5);
			cupon.setFechavencimiento(fechavencimiento);
			agregarCupon(cupon);
		}
	}

	// ------SELECCION DE UN MOLDE ALEATORIO------

	public MoldeCupon valorMoldeAleatorio() {
		List<MoldeCupon> molde = listarMoldeCupon();
		Integer numeroRandom = (int) (Math.random() * molde.size());
		return molde.get(numeroRandom);
	}

	// ------LISTA TODOS LOS CUPONES DEL USUARIO---------

	public List<CuponDescuento> cuponesUsuario(Long id) {
		List<CuponDescuento> cuponesUsuario = new LinkedList<CuponDescuento>();
		List<CuponDescuento> cupones = cuponDescuentoDao.listarCupones();
		for (CuponDescuento cupon : cupones) {
			if (cupon.getUsuario().getId().equals(id)) {
				cuponesUsuario.add(cupon);
			}
		}
		return cuponesUsuario;
	}

	// --------LISTA CUPONES DEL USUARIO HABILITADOS---------

	public List<CuponDescuento> cuponesUsuarioHabilitados(Long id) {
		List<CuponDescuento> cuponeshabilitadosUsuario = new LinkedList<CuponDescuento>();
		List<CuponDescuento> cuponeshabilitados = cuponDescuentoDao.listarCuponesHabilitados();
		for (CuponDescuento cupon : cuponeshabilitados) {
			if (cupon.getUsuario().getId().equals(id)) {
				cuponeshabilitadosUsuario.add(cupon);
			}
		}
		return cuponeshabilitadosUsuario;
	}

	// --------LISTA DE LOS MOLDES DE CUPON---------

	public List<MoldeCupon> listarMoldeCupon() {
		return cuponDescuentoDao.listarMoldeCupon();
	}

	// --------CALCULO DE IMPORTE CON CUPON-----------

	@Override
	public Double calcularImporteConCupon(Long idCupon, Double precioPedido) {
		CuponDescuento cupon = consultarCuponPorId(idCupon);
		Integer valorCupon = cupon.getValor();
		Double resultado = precioPedido - valorCupon;
		if (resultado < 0) {
			resultado = 0.0;
			return resultado;
		}
		return resultado;
	}

	// ---------------VENCIMIENTO DE CUPON-----------------

	@Override
	public void vencimientoDeCupon(Long id) {
		List<CuponDescuento> cupones = cuponesUsuarioHabilitados(id);
		for (CuponDescuento cuponaux : cupones) {
			LocalDate vencimiento = cuponaux.getFechavencimiento();
			if (vencimiento.equals(LocalDate.now())) {
				cuponaux.setEstado(false);
				cuponDescuentoDao.actualizarCupon(cuponaux);
			}
		}
	}

}
