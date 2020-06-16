package ar.edu.unlam.tallerweb1.servicios;
import java.io.Console;

import com.mercadopago.MercadoPago;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.Preference;
import com.mercadopago.resources.datastructures.preference.BackUrls;
import com.mercadopago.resources.datastructures.preference.Identification;
import com.mercadopago.resources.datastructures.preference.Item;
import com.mercadopago.resources.datastructures.preference.Payer;

import ar.edu.unlam.tallerweb1.modelo.Comida;
import ar.edu.unlam.tallerweb1.modelo.Pedido;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

public class ServicioMP {

	public Preference checkout(Usuario usuario, Double precio) {
		// Crea un objeto de preferencia
		Preference preferencia=new Preference();
		try {
			// Agrega credenciales
			MercadoPago.SDK.setAccessToken("TEST-2696125203204390-082310-03c3926dd1403c10392a17d99ab313e8-148209369");
			// Crea un ítem en la preferencia
			Preference p=new Preference();
			Item item = new Item();
			item.setTitle("Pedido").setQuantity(1).setUnitPrice(precio.floatValue());	
			p.appendItem(item);
			Payer payer=new Payer();
			payer.setEmail(usuario.getEmail());
			p.setPayer(payer);
			p.setBinaryMode(true);
			preferencia= p.save();
			} catch (MPException e) {
				System. out. println("Execepcion MP\n");
				e.printStackTrace();
			}
			return preferencia;
	}
	
	
}