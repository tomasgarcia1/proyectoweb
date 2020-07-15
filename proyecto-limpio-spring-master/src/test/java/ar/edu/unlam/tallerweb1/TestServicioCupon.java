package ar.edu.unlam.tallerweb1;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import ar.edu.unlam.tallerweb1.modelo.CuponDescuento;
import ar.edu.unlam.tallerweb1.modelo.Pedido;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.CuponDescuentoDao;
import ar.edu.unlam.tallerweb1.repositorios.PedidoDao;
import ar.edu.unlam.tallerweb1.repositorios.UsuarioDao;
import ar.edu.unlam.tallerweb1.servicios.ServicioCuponDescuentoImpl;

public class TestServicioCupon {
	
	//-----------VERIFICAR VENCIMIENTO CUPON-------------
	
	@Test
	public void verificarVencimientoCupon() {
		CuponDescuento cupon = new CuponDescuento();
		Usuario user =mock(Usuario.class);
		CuponDescuentoDao cD = mock(CuponDescuentoDao.class);
		UsuarioDao uD = mock(UsuarioDao.class);
		PedidoDao pD = mock(PedidoDao.class);
		
		cupon.setEstado(true);
		cupon.setFechavencimiento(LocalDate.now());
		
		List<CuponDescuento> cupones = new LinkedList<>();
		cupones.add(cupon);
		cupon.setUsuario(user);
		
		ServicioCuponDescuentoImpl sCD = new ServicioCuponDescuentoImpl();
		sCD.setCuponDescuentoDao(cD);
		sCD.setUsuarioDao(uD);
		sCD.setPedidoDao(pD);
		
		when(sCD.cuponesUsuarioHabilitados(user.getId())).thenReturn(cupones);
		sCD.vencimientoDeCupon(user.getId());
		
		assertThat(cupon.getEstado()).isEqualTo(false);
		
	}
	
	//-------------VERIFICAR CALCULO DE IMPORTE CON CUPON CORRECTO------------
	
	@Test
	public void testCalculoDeImporteConCupon() {
		CuponDescuento cupon = mock(CuponDescuento.class);
		Pedido pedido = mock(Pedido.class);
		CuponDescuentoDao cD = mock(CuponDescuentoDao.class);
		UsuarioDao uD = mock(UsuarioDao.class);
		PedidoDao pD = mock(PedidoDao.class);
		
		pedido.setPrecio(200.0);
		cupon.setId(1L);
		cupon.setValor(100);
		
		ServicioCuponDescuentoImpl sCD = new ServicioCuponDescuentoImpl();
		sCD.setCuponDescuentoDao(cD);
		sCD.setUsuarioDao(uD);
		sCD.setPedidoDao(pD);
		
		when(sCD.getCuponDescuentoDao().consultarCuponPorId(1L)).thenReturn(cupon);
		
		Double resultado = sCD.calcularImporteConCupon(1L, pedido.getPrecio());
		
		assertThat(resultado == 100.0);
		
	}	
	
}
