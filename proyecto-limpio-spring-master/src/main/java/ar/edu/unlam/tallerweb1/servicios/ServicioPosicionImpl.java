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
	
	public ServicioPosicionImpl() {
		
	}
	public ServicioPosicionImpl(ServicioPedido servicioPedido2, PosicionDao posicionDao2) {
		this.posicionDao=posicionDao2;
		this.servicioPedido=servicioPedido2;
	}

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
		boolean flag=true;
		for (Posicion posicion : posiciones) {
			if(posicion.getLatitude().equals(pos.getLatitude()) && posicion.getLongitude().equals(pos.getLongitude())) {
				flag=false;
			}
		}		
		return flag;
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
