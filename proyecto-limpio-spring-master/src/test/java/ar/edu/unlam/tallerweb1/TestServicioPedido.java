package ar.edu.unlam.tallerweb1;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.ui.ModelMap;

import ar.edu.unlam.tallerweb1.modelo.Comida;
import ar.edu.unlam.tallerweb1.modelo.Posicion;
import ar.edu.unlam.tallerweb1.servicios.ServicioPedidoImpl;

public class TestServicioPedido {
	
	@Test
	public void testGeneraModelMapPosicion() {
		ServicioPedidoImpl servicioPedido=new ServicioPedidoImpl();		
		Posicion posSuc=new Posicion(1.d,1.d,"sucursal");
		Posicion posicion=new Posicion(2.d,2.d,"casa");
		
		ModelMap model=servicioPedido.generarPreviewPosicion(posSuc, posicion);
		
		assertThat(model.get("posicion")).isEqualTo(posicion);
	}
	 
	@Test
	public void testCalculaTiempo() {
		ServicioPedidoImpl servicioPedido=new ServicioPedidoImpl();		
		Double distancia=20.d;
		Double tiempo=servicioPedido.calcularTiempo(distancia);		
		
		assertThat(tiempo).isNotNull();
	}
	@Test
	public void testDistancia() {
		ServicioPedidoImpl servicioPedido=new ServicioPedidoImpl();		
		Double distancia=servicioPedido.distanciaCoord(112.d, 233.d, 116.d,236.d);
		assertThat(distancia.getClass()).isEqualTo(Double.class);
	}
	@Test
	public void testImporteValido() {
		ServicioPedidoImpl servicioPedido=new ServicioPedidoImpl();	
		Comida c1=new Comida();
		c1.setPrecio(30.d);
		Comida c2=new Comida();
		c2.setPrecio(60.d);
		
		List<Comida> comidas=new ArrayList();
		comidas.add(c1);
		comidas.add(c2);
		
		Double importe=servicioPedido.calcularImporteTotal(comidas, 150.d);
		assertThat(importe).isNotEqualTo(0.0);
	}
	@Test
	public void testImporteInvalido() {
		ServicioPedidoImpl servicioPedido=new ServicioPedidoImpl();	
		List<Comida> comidas=new ArrayList();		
		Double importe=servicioPedido.calcularImporteTotal(comidas, 150.d);
		assertThat(importe).isEqualTo(0.0);
	}
	@Test
	public void testImporteComidaUnica() {
		ServicioPedidoImpl servicioPedido=new ServicioPedidoImpl();	
		Comida c1=new Comida();
		c1.setPrecio(200.d);
		
		Double importe=servicioPedido.calcularImporteTotalComidaUnica(c1, 150.d);
		assertThat(importe).isNotNull();
	}
 
	 
}
