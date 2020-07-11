package ar.edu.unlam.tallerweb1;

import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import com.mercadopago.resources.Preference;

import ar.edu.unlam.tallerweb1.controladores.ControladorSuscripcion;
import ar.edu.unlam.tallerweb1.modelo.Suscripcion;
import ar.edu.unlam.tallerweb1.modelo.TipoSuscripcion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.SuscripcionDao;
import ar.edu.unlam.tallerweb1.repositorios.UsuarioDao;
import ar.edu.unlam.tallerweb1.servicios.ServicioMP;
import ar.edu.unlam.tallerweb1.servicios.ServicioSuscripcion;
import ar.edu.unlam.tallerweb1.servicios.ServicioSuscripcionImpl;

import static org.mockito.Mockito.*;

import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.assertj.core.api.Assertions.*;

public class TestSuscripcion {
	
	@Test
	public void verificarControllerDeVerSuscripcion() {
		ControladorSuscripcion cS = new ControladorSuscripcion();
		HttpServletRequest hsr = mock (HttpServletRequest.class);
		HttpSession sesion=mock(HttpSession.class);
    	Usuario user = mock (Usuario.class);

    	when(hsr.getSession()).thenReturn(sesion);
    	when(hsr.getSession().getAttribute("usuario")).thenReturn(user);
    	when(user.getSuscripcion()).thenReturn(null);
    	
    	ModelAndView mv = cS.verMiSuscripcion(hsr);

    	assertThat(mv.getModel().containsKey("msj")).isTrue();
    	
    	
	}
	
	@Test
	public void verificarControllerDeObtenerSuscripcion() {
		ControladorSuscripcion cS = new ControladorSuscripcion();
		HttpServletRequest hsr = mock (HttpServletRequest.class);
		HttpSession sesion=mock(HttpSession.class);
		ServicioSuscripcion sS = mock (ServicioSuscripcion.class);
    	ServicioMP mp = mock (ServicioMP.class);
		
		cS.setServicioMP(mp);
		cS.setServicioSuscripcion(sS);
		
		Suscripcion susc = mock (Suscripcion.class);
		TipoSuscripcion tipo = mock (TipoSuscripcion.class);
    	Usuario user = mock (Usuario.class);
    	Preference p = mock (Preference.class);


    	when(hsr.getSession()).thenReturn(sesion);
    	when(hsr.getSession().getAttribute("usuario")).thenReturn(user);
    	when(mp.checkout(user, 20.0)).thenReturn(p);
    	when(sS.obtenerDatosSegunTipo(1L)).thenReturn(tipo);
    	when(user.getSuscripcion()).thenReturn(susc);
    	when(user.getSuscripcion().getEstado()).thenReturn(false);

    	
    	ModelAndView mv = cS.obtenerSuscripcion(1L, hsr);

    	assertThat(mv.getModel().containsKey("mensaje")).isFalse();
    	
    	
	}

	@Test
	public void verificarVencerSuscripcion() {
		SuscripcionDao sD = mock (SuscripcionDao.class);
		UsuarioDao uD = mock (UsuarioDao.class);
		Suscripcion susc = mock (Suscripcion.class);
		LocalDate fecha = LocalDate.now();
		
		ServicioSuscripcionImpl sS = new ServicioSuscripcionImpl();
		sS.setSuscripcionDao(sD);
		sS.setUsuarioDao(uD);
		
		when(sD.obtenerSuscripcionSegunId(1L)).thenReturn(susc);
		when(susc.getFechaVencimiento()).thenReturn(fecha);
		
		assertThat(sS.vencerSuscripcion(1L)).isTrue();
	}
	
}
