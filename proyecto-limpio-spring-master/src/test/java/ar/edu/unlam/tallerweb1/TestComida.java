package ar.edu.unlam.tallerweb1;

import org.hibernate.SessionFactory;
import org.junit.Test;
import ar.edu.unlam.tallerweb1.modelo.Comida;
import ar.edu.unlam.tallerweb1.modelo.Restriccion;
import ar.edu.unlam.tallerweb1.modelo.TipoHorario;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.ComidaDao;
import ar.edu.unlam.tallerweb1.repositorios.ComidaDaoImpl;
import ar.edu.unlam.tallerweb1.repositorios.RestriccionDao;
import ar.edu.unlam.tallerweb1.repositorios.UsuarioDao;
import ar.edu.unlam.tallerweb1.servicios.ServicioComidaImpl;
import static org.mockito.Mockito.*;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.*;

public class TestComida {
		
	@Inject
	private ComidaDao cD;
    
    @Test
    public void verificarObtencionDeTipoHorarioCorrecto() {
    	Comida c1 = mock (Comida.class);
    	Comida c2 = mock (Comida.class);
    	Comida c3 = mock (Comida.class);
    	Comida c4 = mock (Comida.class);
    	UsuarioDao uD = mock (UsuarioDao.class);
    	ComidaDao cD = mock (ComidaDao.class);
    	RestriccionDao rD= mock (RestriccionDao.class);
    	
    	c1.setTipoHorario(TipoHorario.CENA);
    	c2.setTipoHorario(TipoHorario.CENA);
    	c3.setTipoHorario(TipoHorario.ALMUERZO);
    	c4.setTipoHorario(TipoHorario.DESAYUNO);
    	
    	List<Comida> lista = new LinkedList<>();
    	lista.add(c1);
    	lista.add(c2);
    	lista.add(c3);
    	lista.add(c4);
    	
    	ServicioComidaImpl sC = new ServicioComidaImpl();
    	sC.setComidaDao(cD);
    	sC.setUsuarioDao(uD);
    	sC.setRestriccionDao(rD);
    	
    	when(sC.getComidaDao().obtenerComidas()).thenReturn(lista);
    	
    	List<Comida> resultado = sC.obtenerComidasSegunTipoHorario(TipoHorario.CENA);
    	
    	for (Comida aux : resultado) {
    		verify(aux.getTipoHorario()).equals(TipoHorario.CENA);
    	}
    }
	
    @Test
    public void verificarObtenerComidasSegunCaloriasDAO() {
    	Comida c1 = new Comida();
    	c1.setCalorias(300.0);
    	Comida c2 = new Comida();
    	c2.setCalorias(600.0);
    	Comida c3 = new Comida();
    	c3.setCalorias(200.0);
    	Comida c4 = new Comida();
    	c4.setCalorias(10.0);

    	this.cD.crearComida(c1);
    	this.cD.crearComida(c2);
    	this.cD.crearComida(c3);
    	this.cD.crearComida(c4);
    	
    	List<Comida> lista = this.cD.obtenerComidasSegunCalorias(400.0);
    	
    	verify(lista).contains(c1);
    	verify(lista).contains(c3);
    	verify(lista.contains(c2)).equals(false);
    	verify(lista.contains(c4)).equals(false);
    	
    }
}
