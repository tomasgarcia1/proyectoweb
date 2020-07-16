package ar.edu.unlam.tallerweb1;

import java.util.List;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Comida;
import ar.edu.unlam.tallerweb1.repositorios.ComidaDao;
import ar.edu.unlam.tallerweb1.repositorios.ComidaDaoImpl;
import static org.assertj.core.api.Assertions.*;

public class TestDaoComida extends SpringTest{

	private ComidaDao comidaDao = new ComidaDaoImpl();
	
		@Test
	    @Transactional @Rollback
	    public void verificarObtenerComidasSegunCalorias(){
	        Comida c1 = new Comida();
	    	c1.setCalorias(300.0);
	    	Comida c2 = new Comida();
	    	c2.setCalorias(600.0);
	    	Comida c3 = new Comida();
	    	c3.setCalorias(200.0);
	    	Comida c4 = new Comida();
	    	c4.setCalorias(10.0);

	        this.comidaDao.setSesion(session().getSessionFactory());

	    	this.comidaDao.crearComida(c1);
	    	this.comidaDao.crearComida(c2);
	    	this.comidaDao.crearComida(c3);
	    	this.comidaDao.crearComida(c4);

	        List<Comida> lista = this.comidaDao.obtenerComidasSegunCalorias(400.0);
	        
	        assertThat(lista).contains(c1);
	        assertThat(lista).contains(c3);
	        assertThat(lista.contains(c2)).isFalse();
	        assertThat(lista.contains(c4)).isFalse();
	    }
}
