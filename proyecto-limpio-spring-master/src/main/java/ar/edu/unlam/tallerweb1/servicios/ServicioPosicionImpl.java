package ar.edu.unlam.tallerweb1.servicios;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Pedido;
import ar.edu.unlam.tallerweb1.modelo.Posicion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.PosicionDao;

@Service
@Transactional
public class ServicioPosicionImpl implements ServicioPosicion {
	
	@Inject
	private PosicionDao posicionDao;
	@Inject
	private ServicioPedido servicioPedido;
	
	@Override
	public Long crearPosicion(Posicion posicion) {		
		return posicionDao.crearPosicion(posicion);
	}

	@Override
	public Posicion obtenerPosicionPorId(Long id) {
		return posicionDao.obtenerPosicionPorId(id);
	}

	@Override
	public boolean compararPosiciones(List<Posicion> posiciones, Posicion pos) {
		for (Posicion posicion : posiciones) {
			if(posicion.getLatitude()==pos.getLatitude() && posicion.getLongitude()==pos.getLongitude()) {
				return false;
			}
		}		
		return true;
	}
	@Override
	public List<Posicion> obtenerPosicionesDeUnUsuario(Usuario user) {
		List<Pedido> pedidosDeUsuarios=this.servicioPedido.listarPedidosPorUsuario(user);
		List<Posicion>posicionesDelUsuario=new ArrayList();	
		
		for (Pedido pedido : pedidosDeUsuarios) {
			boolean resultado=compararPosiciones(posicionesDelUsuario, pedido.getUbicacionDestino());
			if(resultado!=false) {
				posicionesDelUsuario.add(pedido.getUbicacionDestino());
			}

		}
		
		return posicionesDelUsuario;
	}
}
