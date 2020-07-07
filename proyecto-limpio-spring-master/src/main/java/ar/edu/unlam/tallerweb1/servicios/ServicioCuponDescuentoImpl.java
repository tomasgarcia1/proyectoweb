package ar.edu.unlam.tallerweb1.servicios;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import ar.edu.unlam.tallerweb1.modelo.CuponDescuento;
import ar.edu.unlam.tallerweb1.modelo.MoldeCupon;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.CuponDescuentoDao;
import ar.edu.unlam.tallerweb1.repositorios.UsuarioDao;

@Service
@Transactional
public class ServicioCuponDescuentoImpl implements ServicioCuponDescuento {

	@Inject
	private CuponDescuentoDao cuponDescuentoDao;

	@Inject
	private UsuarioDao usuarioDao;

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

	public void agregarCuponDescuentoUsuario(Double precioPedido, Long id) {
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
			agregarCupon(cupon);
		}
	}

	public MoldeCupon valorMoldeAleatorio() {
		List<MoldeCupon> molde = listarMoldeCupon();
		Integer numeroRandom = (int) (Math.random() * molde.size());
		return molde.get(numeroRandom);
	}

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

	public List<MoldeCupon> listarMoldeCupon() {
		return cuponDescuentoDao.listarMoldeCupon();
	}

	@Override
	public Double calcularImporteConCupon(Long idCupon, Double precioPedido) {
		CuponDescuento cupon = consultarCuponPorId(idCupon);
		Integer valorCupon = cupon.getValor();
		Double resultado = precioPedido - valorCupon;
		if(resultado < 0) {
			resultado=0.0;
			return resultado;
		}
		return resultado;
	}

}
