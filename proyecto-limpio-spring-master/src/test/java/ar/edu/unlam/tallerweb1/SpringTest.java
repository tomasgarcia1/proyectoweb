package ar.edu.unlam.tallerweb1;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ar.edu.unlam.tallerweb1.modelo.Comida;
import ar.edu.unlam.tallerweb1.modelo.Restriccion;
import ar.edu.unlam.tallerweb1.modelo.TipoHorario;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioComidaImpl;

import static org.assertj.core.api.Assertions.*;
import  static org.mockito.Mockito.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

// Se indica que los test que hereden de esta clase corran con el runner de junit para spring.
@RunWith(SpringJUnit4ClassRunner.class)
// Se indica
@ContextConfiguration(locations = {"/test-applicationContext.xml"})
// Clase base para los test que se pretende que se corran dentro del contexto de spring
public abstract class SpringTest {

    // Tiene inyectado el session factory para que los test que hereden de éste tengan acceso al mismo
    @Inject
    private SessionFactory sessionFactory;

    // Metodo para obtener una sesion de base de datos
    protected Session session() {
        return this.sessionFactory.getCurrentSession();
    }
    
    @Test
    public void verificarCenaSugeridaCorrecta() {
    	Usuario user = mock (Usuario.class);
    	Restriccion vegano = mock (Restriccion.class);
    	Restriccion vegetariano = mock (Restriccion.class);
    	Restriccion celiaco = mock (Restriccion.class);

    	List<Restriccion> rest1 = new LinkedList<>();
    	rest1.add(vegano);
    	rest1.add(celiaco);
    	
    	List<Restriccion> rest2 = new LinkedList<>();
    	rest1.add(vegetariano);
    	rest1.add(celiaco);
    	
    	List<Restriccion> rest3 = new LinkedList<>();
    	rest1.add(vegano);

    	user.setRestricciones(rest1);
    	
    	Comida c1 = mock (Comida.class);
    	Comida c2 = mock (Comida.class);
    	Comida c3 = mock (Comida.class);
    	Comida c4 = mock (Comida.class);
    	
    	c1.setTipoHorario(TipoHorario.CENA);
    	c1.setRestricciones(rest1);
    	c2.setTipoHorario(TipoHorario.CENA);
    	c2.setRestricciones(rest2);
    	c3.setTipoHorario(TipoHorario.ALMUERZO);
    	c3.setRestricciones(rest1);
    	c4.setTipoHorario(TipoHorario.CENA);
    	c4.setRestricciones(rest3);
    	
    	List<Comida> listaC = new LinkedList<>();
    	listaC.add(c1);
    	listaC.add(c2);
    	listaC.add(c3);
    	listaC.add(c4);
    	
    	ServicioComidaImpl sC = new ServicioComidaImpl();
    	
    	when(sC.getComidaDao().obtenerComidasSegunCalorias(600.0)).thenReturn(listaC);
    	when(sC.getUsuarioDao().obtenerUsuarioPorId(1L)).thenReturn(user);
    	
    	assertThat(sC.sugerirCena(3000.0, 1L)).isEqualTo(c1);
    }
    
    @Test
    public void verificarObtencionDeTipoHorarioCorrecto() {
    	Comida c1 = mock (Comida.class);
    	Comida c2 = mock (Comida.class);
    	Comida c3 = mock (Comida.class);
    	Comida c4 = mock (Comida.class);
    	
    	c1.setTipoHorario(TipoHorario.CENA);
    	c2.setTipoHorario(TipoHorario.CENA);
    	c3.setTipoHorario(TipoHorario.ALMUERZO);
    	c4.setTipoHorario(TipoHorario.DESAYUNO);
    	
    	List<Comida> lista1 = new LinkedList<>();
    	lista1.add(c1);
    	lista1.add(c2);
    	
    	List<Comida> lista2 = new LinkedList<>();
    	lista2.add(c1);
    	lista2.add(c2);
    	lista2.add(c3);
    	lista2.add(c4);
    	
    	ServicioComidaImpl sC = new ServicioComidaImpl();
    	
    	when(sC.getComidaDao().obtenerComidas()).thenReturn(lista2);
    	
    	List<Comida> resultado = sC.obtenerComidasSegunTipoHorario(TipoHorario.CENA);
    	
    	for (Comida aux : resultado) {
    		verify(aux.getTipoHorario()).equals(TipoHorario.CENA);
    	}
    	/* 	
    	assertThat(sC.obtenerComidasSegunTipoHorario(TipoHorario.CENA)).isEqualTo(lista1);
    	
    	verify(c1).setTipoHorario(TipoHorario.CENA);*/
    }

}
