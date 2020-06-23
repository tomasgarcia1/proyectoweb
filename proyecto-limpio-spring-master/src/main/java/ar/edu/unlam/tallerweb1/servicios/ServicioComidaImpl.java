package ar.edu.unlam.tallerweb1.servicios;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Comida;
import ar.edu.unlam.tallerweb1.modelo.Restriccion;
import ar.edu.unlam.tallerweb1.modelo.TipoHorario;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.ComidaDao;
import ar.edu.unlam.tallerweb1.repositorios.RestriccionDao;
import ar.edu.unlam.tallerweb1.repositorios.UsuarioDao;

@Service
@Transactional
public class ServicioComidaImpl implements ServicioComida {
	@Inject
	private ComidaDao comidaDao;

	@Inject
	private UsuarioDao usuarioDao;
	
	@Inject
	private RestriccionDao restriccionDao;

	@Override
	public Long crearComida(Comida comida) {
		return comidaDao.crearComida(comida);
	}

	@Override
	public Comida obtenerPorId(Long id) {
		return comidaDao.ObtenerPorId(id);
	}

	public void borrar(Comida comida) {
		comidaDao.borrar(comida);
	}
	
	
	// --------------------GETTERS Y SETTERS PARA LOS TEST----------------------

	public ComidaDao getComidaDao() {
		return comidaDao;
	}

	public void setComidaDao(ComidaDao comidaDao) {
		this.comidaDao = comidaDao;
	}

	public UsuarioDao getUsuarioDao() {
		return usuarioDao;
	}

	public void setUsuarioDao(UsuarioDao usuarioDao) {
		this.usuarioDao = usuarioDao;
	}
	
	// --------------------SUGERIR COMIDAS POR RESTRICCIONES----------------------


	@Override
	public Comida sugerirDesayunoPorRestricciones(Long id) {
		Usuario user = usuarioDao.obtenerUsuarioPorId(id);
		List<Restriccion> restricciones = user.getRestricciones();
		List<Comida> comidas = comidaDao.obtenerComidas();
		List<Comida> desayuno = new ArrayList<Comida>();
		for (Comida comida1 : comidas) {
			if (comida1.getRestricciones().containsAll(restricciones)
					&& comida1.getTipoHorario() == TipoHorario.DESAYUNO) {
				desayuno.add(comida1);
			}
		}
		Integer numeroRandom = (int) (Math.random() * desayuno.size());
		return desayuno.get(numeroRandom);
	}

	@Override
	public Comida sugerirAlmuerzoPorRestricciones(Long id) {
		Usuario user = usuarioDao.obtenerUsuarioPorId(id);
		List<Restriccion> restricciones = user.getRestricciones();
		List<Comida> comidas = comidaDao.obtenerComidas();
		List<Comida> almuerzo = new ArrayList<Comida>();
		for (Comida comida1 : comidas) {
			if (comida1.getRestricciones().containsAll(restricciones)
					&& comida1.getTipoHorario() == TipoHorario.ALMUERZO) {
				almuerzo.add(comida1);
			}
		}
		Integer numeroRandom = (int) (Math.random() * almuerzo.size());
		return almuerzo.get(numeroRandom);
	}

	@Override
	public Comida sugerirCenaPorRestricciones(Long id) {
		Usuario user = usuarioDao.obtenerUsuarioPorId(id);
		List<Restriccion> restricciones = user.getRestricciones();
		List<Comida> comidas = comidaDao.obtenerComidas();
		List<Comida> cena = new ArrayList<Comida>();
		for (Comida comida1 : comidas) {
			if (comida1.getRestricciones().containsAll(restricciones) && comida1.getTipoHorario() == TipoHorario.CENA) {
				cena.add(comida1);
			}
		}
		Integer numeroRandom = (int) (Math.random() * cena.size());
		return cena.get(numeroRandom);
	}

	// -----------------------GENERAR MENU DEL DIA-----------------------

	@Override
	public Comida sugerirDesayuno(Double caloriasDiarias, Long id) {
		Double caloriasDesayuno = caloriasDiarias * 0.35;

		List<Comida> desayunoCalorias = comidaDao.obtenerComidasSegunCalorias(caloriasDesayuno);
		List<Comida> listaHorario = new LinkedList<Comida>();

		for (Comida comidaAux : desayunoCalorias) {
			if (comidaAux.getTipoHorario() == TipoHorario.DESAYUNO) {
				listaHorario.add(comidaAux);
			}
		}

		Usuario user = usuarioDao.obtenerUsuarioPorId(id);
		List<Restriccion> restricciones = user.getRestricciones();
		List<Comida> desayuno = new LinkedList<Comida>();

		for (Comida comidaAux : listaHorario) {
			if (comidaAux.getRestricciones().containsAll(restricciones)) {
				desayuno.add(comidaAux);
			}
		}

		if (desayuno.isEmpty()) {
			Comida cError = new Comida();
			cError.setId(0L);
			cError.setNombre("No se encontró una comida adecuada");
			return cError;
		}

		Integer numeroRandom = (int) (Math.random() * desayuno.size());
		return desayuno.get(numeroRandom);
	}

	@Override
	public Comida sugerirAlmuerzo(Double caloriasDiarias, Long id) {
		Double caloriasAlmuerzo = caloriasDiarias * 0.45;

		List<Comida> almuerzoCalorias = comidaDao.obtenerComidasSegunCalorias(caloriasAlmuerzo);
		List<Comida> listaHorario = new LinkedList<Comida>();

		for (Comida comidaAux : almuerzoCalorias) {
			if (comidaAux.getTipoHorario() == TipoHorario.ALMUERZO) {
				listaHorario.add(comidaAux);
			}
		}

		Usuario user = usuarioDao.obtenerUsuarioPorId(id);
		List<Restriccion> restricciones = user.getRestricciones();
		List<Comida> almuerzo = new LinkedList<Comida>();

		for (Comida comidaAux : listaHorario) {
			if (comidaAux.getRestricciones().containsAll(restricciones)) {
				almuerzo.add(comidaAux);
			}
		}
		if (almuerzo.isEmpty()) {
			Comida cError = new Comida();
			cError.setId(0L);
			cError.setNombre("No se encontró una comida adecuada");
			return cError;
		}

		Integer numeroRandom = (int) (Math.random() * almuerzo.size());
		return almuerzo.get(numeroRandom);
	}

	@Override
	public Comida sugerirCena(Double caloriasDiarias, Long id) {
		Double caloriasCena = caloriasDiarias * 0.20;

		List<Comida> desayunoCena = comidaDao.obtenerComidasSegunCalorias(caloriasCena);
		List<Comida> listaHorario = new LinkedList<Comida>();

		for (Comida comidaAux : desayunoCena) {
			if (comidaAux.getTipoHorario() == TipoHorario.CENA) {
				listaHorario.add(comidaAux);
			}
		}

		Usuario user = usuarioDao.obtenerUsuarioPorId(id);
		List<Restriccion> restricciones = user.getRestricciones();
		List<Comida> cena = new LinkedList<Comida>();

		for (Comida comidaAux : listaHorario) {
			if (comidaAux.getRestricciones().containsAll(restricciones)) {
				cena.add(comidaAux);
			}
		}

		if (cena.isEmpty()) {
			Comida cError = new Comida();
			cError.setId(0L);
			cError.setNombre("No se encontró una comida adecuada");
			return cError;
		}

		Integer numeroRandom = (int) (Math.random() * cena.size());
		return cena.get(numeroRandom);
	}

	// --------------OBTENER COMIDAS SEGUN TIPOHORARIO------------

	@Override
	public List<Comida> obtenerComidasSegunTipoHorario(TipoHorario tipo) {
		List<Comida> comidas1 = comidaDao.obtenerComidas();
		List<Comida> comidas2 = new LinkedList();

		for (Comida comidaAux : comidas1) {
			if (comidaAux.getTipoHorario() == tipo) {
				comidaAux.setRestricciones(restriccionDao.obtenerRestriccionPorComida(comidaAux));
				comidas2.add(comidaAux);
			}
		}

		return comidas2;
	}

	// --------------OBTENER COMIDA POR NOMBRE------------

	@Override
	public Comida obtenerComidaPorNombre(String nombre) {
		List<Comida> comidas1 = comidaDao.obtenerComidas();
		for (Comida comidaAux : comidas1) {
			if (comidaAux.getNombre().equals(nombre)) {
				return comidaAux;
			}
		}
		return null;
	}

	// --------------LISTA DE COMIDAS SEGUN RESTRICCIONES ------------

	public List<Comida> listarComidasSegunRestricciones(Long id) {
		Usuario user = usuarioDao.obtenerUsuarioPorId(id);
		List<Restriccion> restricciones = user.getRestricciones();
		List<Comida> comidas = comidaDao.obtenerComidas();
		List<Comida> listacomidas = new ArrayList<Comida>();
		for (Comida comida1 : comidas) {
			if (comida1.getRestricciones().containsAll(restricciones)) {
				listacomidas.add(comida1);
			}
		}
		return listacomidas;
	}

	@Override
	public List<Comida> obtenerComidas() {
		return comidaDao.obtenerComidas();
	}

	public void updateComida(Comida comida) {
		comidaDao.updateComida(comida);
	}

}
