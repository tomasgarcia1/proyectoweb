package ar.edu.unlam.tallerweb1;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import com.mercadopago.resources.Preference;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioMP;
public class TestMP {

	ServicioMP mp= new ServicioMP();
	
	@Test
	public void testPreferencia() {
		Usuario u = new Usuario();
		u.setEmail("pepe@mail.com");
		Preference p =mp.checkout(u, 22.3);
		assertThat(p.getPayer().getEmail()).isEqualTo("pepe@mail.com");
	}
}
