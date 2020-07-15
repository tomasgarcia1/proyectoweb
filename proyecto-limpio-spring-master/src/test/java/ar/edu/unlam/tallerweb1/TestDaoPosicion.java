package ar.edu.unlam.tallerweb1;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import javax.transaction.Transactional;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import ar.edu.unlam.tallerweb1.modelo.Posicion;
import ar.edu.unlam.tallerweb1.repositorios.PosicionDao;
import ar.edu.unlam.tallerweb1.repositorios.PosicionDaoImpl;

public class TestDaoPosicion extends SpringTest{
	private PosicionDao posicionDao=new PosicionDaoImpl();
	
    @Test
    @Transactional @Rollback
    public void testCrearPosicion(){
        Posicion posicion= new Posicion(3333.d,123.d,"casa");
        this.posicionDao.setSesion(session().getSessionFactory());
        Long id=this.posicionDao.crearPosicion(posicion);     
        assertFalse(id.equals(null));
    }
    @Test
    @Transactional @Rollback
    public void testObtenerPosicionPorId(){
        Posicion posicion= new Posicion(3333.d,123.d,"casa");
        this.posicionDao.setSesion(session().getSessionFactory());
        Long id=this.posicionDao.crearPosicion(posicion);     
        
        Posicion posicionObtenida=this.posicionDao.obtenerPosicionPorId(id);
        System.out.println(posicionObtenida.getNombre());
        assertTrue(id.equals(posicionObtenida.getId()));
    }
 
}
