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

	// ----------TEST OBTENCION DE TIPO HORARIO DE COMIDA-------------

	@Test
	public void verificarObtencionDeTipoHorarioCorrecto() {
		Comida c1 = mock(Comida.class);
		Comida c2 = mock(Comida.class);
		Comida c3 = mock(Comida.class);
		Comida c4 = mock(Comida.class);
		UsuarioDao uD = mock(UsuarioDao.class);
		ComidaDao cD = mock(ComidaDao.class);
		RestriccionDao rD = mock(RestriccionDao.class);

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
			// assertThat(aux.getTipoHorario()).isEqualTo(TipoHorario.CENA);
		}
	}

	@Test
	public void verificarObtencionDeTipoHorarioIncorrecto() {
		Comida c1 = mock(Comida.class);
		Comida c2 = mock(Comida.class);
		Comida c3 = mock(Comida.class);
		Comida c4 = mock(Comida.class);
		UsuarioDao uD = mock(UsuarioDao.class);
		ComidaDao cD = mock(ComidaDao.class);
		RestriccionDao rD = mock(RestriccionDao.class);

		c1.setTipoHorario(TipoHorario.ALMUERZO);
		c2.setTipoHorario(TipoHorario.DESAYUNO);
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

	// --------------TEST OBTENCION DE COMIDA POR NOMBRE---------------

	@Test
	public void verificarObtencionDeComidaPorNombre() {
		Comida c1 = new Comida();
		Comida c2 = new Comida();
		Comida c3 = new Comida();
		Comida c4 = new Comida();
		UsuarioDao uD = mock(UsuarioDao.class);
		ComidaDao cD = mock(ComidaDao.class);
		RestriccionDao rD = mock(RestriccionDao.class);

		c1.setNombre("calabaza");
		c2.setNombre("asado");
		c3.setNombre("pollo");
		c4.setNombre("torta");

		List<Comida> lista = new LinkedList<Comida>();
		lista.add(c1);
		lista.add(c2);
		lista.add(c3);
		lista.add(c4);

		ServicioComidaImpl sC = new ServicioComidaImpl();
		sC.setComidaDao(cD);
		sC.setUsuarioDao(uD);
		sC.setRestriccionDao(rD);

		when(sC.getComidaDao().obtenerComidas()).thenReturn(lista);

		Comida comida = sC.obtenerComidaPorNombre("asado");

		assertThat(comida).isEqualTo(c2);

	}

	// --------------TEST SUGERIR DESAYUNO SEGUN RESTRICCION USUARIO---------------

	@Test
	public void verificarSugerirDesayunoSegunRestriccionUsuario() {
		Usuario user = mock(Usuario.class);
		Comida c1 = new Comida();
		Comida c2 = new Comida();
		Comida c3 = new Comida();
		Restriccion r = mock(Restriccion.class);
		Restriccion r1 = mock(Restriccion.class);
		List<Restriccion> restricciones = new LinkedList<>();
		UsuarioDao uD = mock(UsuarioDao.class);
		ComidaDao cD = mock(ComidaDao.class);
		RestriccionDao rD = mock(RestriccionDao.class);

		user.setId(3L);
		c1.setTipoHorario(TipoHorario.DESAYUNO);
		c2.setTipoHorario(TipoHorario.DESAYUNO);
		c3.setTipoHorario(TipoHorario.CENA);

		restricciones.add(r);
		restricciones.add(r1);

		user.setRestricciones(restricciones);
		c1.setRestricciones(restricciones);
		c2.setRestricciones(restricciones);
		c3.setRestricciones(restricciones);

		List<Comida> lista = new LinkedList<>();
		lista.add(c1);
		lista.add(c2);
		lista.add(c3);

		ServicioComidaImpl sC = new ServicioComidaImpl();
		sC.setComidaDao(cD);
		sC.setUsuarioDao(uD);
		sC.setRestriccionDao(rD);

		when(sC.getUsuarioDao().obtenerUsuarioPorId(3L)).thenReturn(user);
		when(sC.getComidaDao().obtenerComidas()).thenReturn(lista);

		Comida comida = sC.sugerirDesayunoPorRestricciones(3L);

		assertThat(comida.getTipoHorario()).isEqualTo(TipoHorario.DESAYUNO);
	}

	// --------------TEST SUGERIR ALMUERZO SEGUN RESTRICCION USUARIO---------------

	@Test
	public void verificarSugerirAlmuerzoSegunRestriccionUsuario() {
		Usuario user = mock(Usuario.class);
		Comida c1 = new Comida();
		Comida c2 = new Comida();
		Comida c3 = new Comida();
		Restriccion r = mock(Restriccion.class);
		Restriccion r1 = mock(Restriccion.class);
		List<Restriccion> restricciones = new LinkedList<>();
		UsuarioDao uD = mock(UsuarioDao.class);
		ComidaDao cD = mock(ComidaDao.class);
		RestriccionDao rD = mock(RestriccionDao.class);

		user.setId(2L);
		c1.setTipoHorario(TipoHorario.ALMUERZO);
		c2.setTipoHorario(TipoHorario.ALMUERZO);
		c3.setTipoHorario(TipoHorario.DESAYUNO);

		restricciones.add(r);
		restricciones.add(r1);

		user.setRestricciones(restricciones);
		c1.setRestricciones(restricciones);
		c2.setRestricciones(restricciones);
		c3.setRestricciones(restricciones);

		List<Comida> lista = new LinkedList<>();
		lista.add(c1);
		lista.add(c2);
		lista.add(c3);

		ServicioComidaImpl sC = new ServicioComidaImpl();
		sC.setComidaDao(cD);
		sC.setUsuarioDao(uD);
		sC.setRestriccionDao(rD);

		when(sC.getUsuarioDao().obtenerUsuarioPorId(2L)).thenReturn(user);
		when(sC.getComidaDao().obtenerComidas()).thenReturn(lista);

		Comida comida = sC.sugerirAlmuerzoPorRestricciones(2L);

		assertThat(comida.getTipoHorario()).isEqualTo(TipoHorario.ALMUERZO);
	}

	// --------------TEST SUGERIR CENA SEGUN RESTRICCION USUARIO---------------

	@Test
	public void verificarSugerirCenaSegunRestriccionUsuario() {
		Usuario user = mock(Usuario.class);
		Comida c1 = new Comida();
		Comida c2 = new Comida();
		Comida c3 = new Comida();
		Restriccion r = mock(Restriccion.class);
		Restriccion r1 = mock(Restriccion.class);
		List<Restriccion> restricciones = new LinkedList<>();
		UsuarioDao uD = mock(UsuarioDao.class);
		ComidaDao cD = mock(ComidaDao.class);
		RestriccionDao rD = mock(RestriccionDao.class);

		user.setId(2L);
		c1.setTipoHorario(TipoHorario.DESAYUNO);
		c2.setTipoHorario(TipoHorario.CENA);
		c3.setTipoHorario(TipoHorario.CENA);

		restricciones.add(r);
		restricciones.add(r1);

		user.setRestricciones(restricciones);
		c1.setRestricciones(restricciones);
		c2.setRestricciones(restricciones);
		c3.setRestricciones(restricciones);

		List<Comida> lista = new LinkedList<>();
		lista.add(c1);
		lista.add(c2);
		lista.add(c3);

		ServicioComidaImpl sC = new ServicioComidaImpl();
		sC.setComidaDao(cD);
		sC.setUsuarioDao(uD);
		sC.setRestriccionDao(rD);

		when(sC.getUsuarioDao().obtenerUsuarioPorId(1L)).thenReturn(user);
		when(sC.getComidaDao().obtenerComidas()).thenReturn(lista);

		Comida comida = sC.sugerirCenaPorRestricciones(1L);

		assertThat(comida.getTipoHorario()).isEqualTo(TipoHorario.CENA);
	}

	// --------------TEST OBTENCION COMIDAS SEGUN RESTRICCION---------------

	@Test
	public void verificarObtencionComidasSegunRestriccion() {
		Comida c1 = new Comida();
		Comida c2 = new Comida();
		Comida c3 = new Comida();
		Restriccion r = mock(Restriccion.class);
		Restriccion r1 = mock(Restriccion.class);
		List<Restriccion> restricciones = new LinkedList<>();
		UsuarioDao uD = mock(UsuarioDao.class);
		ComidaDao cD = mock(ComidaDao.class);
		RestriccionDao rD = mock(RestriccionDao.class);

		r.setNombre("vegano");
		r1.setNombre("vegetariano");
		
		restricciones.add(r);;
		
		c1.setRestricciones(restricciones);
		c2.setRestricciones(restricciones);
		c3.setRestricciones(restricciones);
		
		List<Comida> lista = new LinkedList<>();
		lista.add(c1);
		lista.add(c2);
		lista.add(c3);

		ServicioComidaImpl sC = new ServicioComidaImpl();
		sC.setComidaDao(cD);
		sC.setUsuarioDao(uD);
		sC.setRestriccionDao(rD);

		when(sC.getRestriccionDao().obtenerRestriccionPorNombre("vegano")).thenReturn(r);
		when(sC.getComidaDao().obtenerComidasPorRestriccion(r)).thenReturn(lista);
		
		List<Comida> comidas  = sC.obtenerComidasDeRestriccion("vegano");
		
		for(Comida comidaAux : comidas) {
			assertThat(comidaAux.getRestricciones()).containsAll(restricciones);
		}
	}
}
		
