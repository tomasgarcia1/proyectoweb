package ar.edu.unlam.tallerweb1.controladores;

import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Comida;
import ar.edu.unlam.tallerweb1.modelo.Posicion;
import ar.edu.unlam.tallerweb1.modelo.Rol;
import ar.edu.unlam.tallerweb1.modelo.TipoHorario;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioComida;
import ar.edu.unlam.tallerweb1.servicios.ServicioPedido;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;

@Controller
public class ControladorComida {
	@Inject
	private ServicioComida servicioComida;

	@Inject
	private ServicioUsuario servicioUsuario;

	@Inject
	private ServicioPedido servicioPedido;

	// --------ELIMINAR POR ID---------

	@RequestMapping("/eliminarPorId")
	public ModelAndView eliminarPorId(@RequestParam(value = "id", required = true) Long id) {
		servicioComida.borrar(servicioComida.obtenerPorId(id));
		return new ModelAndView("comidaborrada");
	}

	// --------SUGERIR MENU DEL DIA-------

	@RequestMapping("/sugerirMenuDelDia")
	public ModelAndView sugerirMenuDelDia(HttpServletRequest request) {
		Usuario user = (Usuario) request.getSession().getAttribute("usuario");

		if (user != null) {
			Long id = user.getId();
			Double caloriasDiarias = servicioUsuario.obtenerCaloriasPorId(id);

			List<Comida> menu1 = servicioPedido.generarMenusSugeridos(user);
			List<Comida> menu2 = servicioPedido.generarMenusSugeridos(user);
			List<Comida> menu3 = servicioPedido.generarMenusSugeridos(user);
			String idComidas1 = servicioPedido.concatenarIdComidas(menu1);
			String idComidas2 = servicioPedido.concatenarIdComidas(menu2);
			String idComidas3 = servicioPedido.concatenarIdComidas(menu3);
			ModelMap model = new ModelMap();

			for (Comida com : menu1) {
				if (com.getId() == 0L) {
					model.put("error",
							"No se puede hacer un pedido ya que no se encontraron las 3 comidas que necesita");
				}
			}

			model.put("menu1", menu1);
			model.put("menu2", menu2);
			model.put("menu3", menu3);
			model.put("idcomidas1", idComidas1);
			model.put("idcomidas2", idComidas2);
			model.put("idcomidas3", idComidas3);

			return new ModelAndView("sugerirMenuDelDia", model);

		} else {
			return new ModelAndView("redirect:/home");
		}
	}

	// ----------AGREGAR COMIDA--------

	@RequestMapping("/agregarComida")
	public ModelAndView agregarComida(HttpServletRequest request) {
		Usuario user = (Usuario) request.getSession().getAttribute("usuario");
		ModelMap modelo = new ModelMap();

		if (user != null && user.getRol() == Rol.ADMINISTRADOR) {
			Comida comida = new Comida();
			List<TipoHorario> tipoHorario = Arrays.asList(TipoHorario.values());
			modelo.put("comida", comida);
			modelo.put("tipoHorario", tipoHorario);
			return new ModelAndView("agregarComida", modelo);

		} else {
			return new ModelAndView("redirect:/home");
		}
	}

	// ---------AGREGAR COMIDA VALIDACION-------

	@RequestMapping(path = "/agregarComidaValidacion", method = RequestMethod.POST)
	public ModelAndView agregarComidaValidacion(@ModelAttribute("comida") Comida comida) {
		servicioComida.crearComida(comida);
		return new ModelAndView("redirect:/adminInterno");

	}

	// -----------------COMIDAS VISTAS Y PEDIDAS----------------

	@RequestMapping(path = "/mostrarComidasVistasyPedidas")
	public ModelAndView contadorDeComidas(@RequestParam(value = "id", required = true) Long id,
			HttpServletRequest request) {

		Usuario user = (Usuario) request.getSession().getAttribute("usuario");
		if (user != null) {
			ModelMap modelo = new ModelMap();

			Comida comida1 = servicioComida.obtenerPorId(id);
			List<Comida> comidasContador = servicioComida.contadorComida(comida1);
			List<Comida> comidasMasVistas = servicioComida.comidasMasVistas();
			List<Comida> comidasMenosVistas = servicioComida.comidasMenosVistas();
			TreeSet<Comida> comidasMasPedidas = servicioPedido.comidasMasPedidas(user.getId());

			modelo.put("comidasVistas", comidasMasVistas);
			modelo.put("comidasMenosVistas", comidasMenosVistas);
			modelo.put("comidasPedidas", comidasMasPedidas);
			modelo.put("comidasContador", comidasContador);
			modelo.put("comida", comida1);

			return new ModelAndView("/mostrarDetalleComida", modelo);
		} else {
			return new ModelAndView("redirect:/interno");
		}

	}

	// ----------------ELEGIR COMIDAS SEGUN RESTRICCIONES DEL USUARIO---------------

	@RequestMapping(path = "/mostrarComidasEleccion")
	public ModelAndView mostrarComidasEleccionUsuario(HttpServletRequest request) {
		Usuario user = (Usuario) request.getSession().getAttribute("usuario");
		ModelMap modelo = new ModelMap();

		if (user != null) {
			TreeSet<Comida> comidas = servicioComida.listarComidasUsuarioSinRepetir(user.getId());

			modelo.put("comidas", comidas);

			return new ModelAndView("listarElegirComidas", modelo);
		} else {
			return new ModelAndView("redirect:/interno");
		}
	}

}