package ar.edu.unlam.tallerweb1;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

import java.util.LinkedList;
import java.util.List;

import javax.transaction.Transactional;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.google.common.base.Verify;

import ar.edu.unlam.tallerweb1.modelo.CuponDescuento;
import ar.edu.unlam.tallerweb1.modelo.Posicion;
import ar.edu.unlam.tallerweb1.repositorios.CuponDescuentoDao;
import ar.edu.unlam.tallerweb1.repositorios.CuponDescuentoDaoImpl;
import ar.edu.unlam.tallerweb1.repositorios.PosicionDao;
import ar.edu.unlam.tallerweb1.repositorios.PosicionDaoImpl;

public class TestDaoCupon extends SpringTest {
	private CuponDescuentoDao cuponDao = new CuponDescuentoDaoImpl();

	@Test
	@Transactional
	@Rollback
	public void verificarConsultaDeCuponDescuento() {
		CuponDescuento cupon = new CuponDescuento(1, 230, true, 2020 - 05 - 12);
		this.cuponDao.setSesion(session().getSessionFactory());
		this.cuponDao.agregarCupon(cupon);
		CuponDescuento cuponaux = this.cuponDao.consultarCuponPorId(1L);
		assertEquals(cupon, cuponaux);
	}
	
	@Test
	@Transactional
	@Rollback
	public void verificarAgregarCupon() {
		CuponDescuento cupon = new CuponDescuento(1, 230, true, 2020 - 05 - 12);
		this.cuponDao.setSesion(session().getSessionFactory());
		Long id = this.cuponDao.agregarCupon(cupon);
		assertFalse(id.equals(null));
	}
}
