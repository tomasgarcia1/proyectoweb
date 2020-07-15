package ar.edu.unlam.tallerweb1;

import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;
import ar.edu.unlam.tallerweb1.controladores.ControladorCupones;
import ar.edu.unlam.tallerweb1.modelo.CuponDescuento;
import ar.edu.unlam.tallerweb1.modelo.MoldeCupon;
import ar.edu.unlam.tallerweb1.modelo.Rol;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioCuponDescuento;
import ar.edu.unlam.tallerweb1.servicios.ServicioMoldeCupon;
import static org.mockito.Mockito.*;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import static org.assertj.core.api.Assertions.*;

public class TestCupones {

	@Test
	public void verificarDetalleMoldeCupones() {
		Usuario user = new Usuario();
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpSession sesion = mock(HttpSession.class);

		ServicioMoldeCupon servicioMoldeCupon = mock(ServicioMoldeCupon.class);
		ServicioCuponDescuento servicioCuponDescuento = mock(ServicioCuponDescuento.class);

		user.setRol(Rol.ADMINISTRADOR);
		ControladorCupones cC = new ControladorCupones();
		cC.setServicioCuponDescuento(servicioCuponDescuento);
		cC.setServicioMoldeCupon(servicioMoldeCupon);
		List<MoldeCupon> moldes = new LinkedList<>();

		when(request.getSession()).thenReturn(sesion);
		when(request.getSession().getAttribute("usuario")).thenReturn(user);
		when(servicioMoldeCupon.listarMoldes()).thenReturn(moldes);

		ModelAndView model = cC.detallesMoldesCupon(request);

		assertThat(model.getViewName()).isEqualTo("listarMoldesCupones");
	}

	@Test
	public void verificarListarCupones() {
		Usuario user = mock(Usuario.class);
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpSession sesion = mock(HttpSession.class);

		ServicioCuponDescuento servicioCuponDescuento = mock(ServicioCuponDescuento.class);
		ServicioMoldeCupon servicioMoldeCupon = mock(ServicioMoldeCupon.class);
		ControladorCupones cC = new ControladorCupones();
		cC.setServicioCuponDescuento(servicioCuponDescuento);
		cC.setServicioMoldeCupon(servicioMoldeCupon);
		List<CuponDescuento> cupones = mock(List.class);

		when(request.getSession()).thenReturn(sesion);
		when(request.getSession().getAttribute("usuario")).thenReturn(user);
		when(servicioCuponDescuento.cuponesUsuario(user.getId())).thenReturn(cupones);

		ModelAndView model = cC.miscupones(request);

		assertThat(model.getViewName()).isEqualTo("listarCupones");

	}

}
