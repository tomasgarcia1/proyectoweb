package ar.edu.unlam.tallerweb1;

import org.junit.Test;

import ar.edu.unlam.tallerweb1.modelo.Suscripcion;
import ar.edu.unlam.tallerweb1.modelo.TipoSuscripcion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.SuscripcionDao;
import ar.edu.unlam.tallerweb1.repositorios.UsuarioDao;
import ar.edu.unlam.tallerweb1.servicios.ServicioSuscripcionImpl;

import static org.mockito.Mockito.*;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

public class TestSuscripcion {
/*
	@Test
	public void verificarInserciónDeSuscripcionCorrectaEnUsuario() {
		Usuario user = new Usuario();
		user.setId(1L);
		Suscripcion susc = mock (Suscripcion.class);
		susc.setId(4L);
		SuscripcionDao sD = mock (SuscripcionDao.class);
		UsuarioDao uD = mock (UsuarioDao.class);
		LocalDate hoy = LocalDate.now();
		
		ServicioSuscripcionImpl sS = new ServicioSuscripcionImpl();
		sS.setSuscripcionDao(sD);
		sS.setUsuarioDao(uD);
		
		when(sS.crearSuscripcion(1L, hoy)).thenReturn(4L);
		when(sS.getSuscripcionDao().obtenerSuscripcionSegunId(4L)).thenReturn(susc);
		when(sS.getUsuarioDao().obtenerUsuarioPorId(1L)).thenReturn(user);
		
		sS.insertarSuscripcionEnUsuario(1L, hoy, 1L);
		
		assertThat(user.getSuscripcion().getId()).isEqualTo(susc.getId());
		
	}
	
	@Test
	public void verificarCrearSuscripcionCorrecto() {
		SuscripcionDao sD = mock (SuscripcionDao.class);
		UsuarioDao uD = mock (UsuarioDao.class);
		TipoSuscripcion tS = mock (TipoSuscripcion.class);
		tS.setId(1L);
		LocalDate hoy = LocalDate.now();
		
		ServicioSuscripcionImpl sS = new ServicioSuscripcionImpl();
		sS.setSuscripcionDao(sD);
		sS.setUsuarioDao(uD);
		
		when(sS.obtenerDatosSegunTipo(1L)).thenReturn(tS);
		
		Long resultado = sS.crearSuscripcion(1L, hoy);
		assertThat(resultado).isNotNull();
	}*/
}
