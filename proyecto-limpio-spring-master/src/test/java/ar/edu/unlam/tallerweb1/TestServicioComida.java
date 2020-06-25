package ar.edu.unlam.tallerweb1;

import org.junit.Test;
import ar.edu.unlam.tallerweb1.modelo.Comida;
import ar.edu.unlam.tallerweb1.modelo.Restriccion;
import ar.edu.unlam.tallerweb1.modelo.TipoHorario;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.ComidaDao;
import ar.edu.unlam.tallerweb1.repositorios.RestriccionDao;
import ar.edu.unlam.tallerweb1.repositorios.UsuarioDao;
import ar.edu.unlam.tallerweb1.servicios.ServicioComidaImpl;
import static org.mockito.Mockito.*;

import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class TestServicioComida {
    
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
    	
    	ServicioComidaImpl sC = new ServicioComidaImpl(cD, uD, rD);
    	
    	when(sC.getComidaDao().obtenerComidas()).thenReturn(lista);
    	
    	List<Comida> resultado = sC.obtenerComidasSegunTipoHorario(TipoHorario.CENA);
    	
    	for (Comida aux : resultado) {
    		verify(aux.getTipoHorario()).equals(TipoHorario.CENA);
    		//assertThat(aux.getTipoHorario()).isEqualTo(TipoHorario.CENA);
    	}
    }
	
}
