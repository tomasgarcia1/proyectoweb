package ar.edu.unlam.tallerweb1.servicios;

import java.time.LocalDate;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Suscripcion;
import ar.edu.unlam.tallerweb1.modelo.TipoSuscripcion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.SuscripcionDao;
import ar.edu.unlam.tallerweb1.repositorios.UsuarioDao;

@Service
@Transactional
public class ServicioSuscripcionImpl implements ServicioSuscripcion{

	@Inject
	SuscripcionDao suscripcionDao;
	
	@Inject
	UsuarioDao usuarioDao;
	
	
	public SuscripcionDao getSuscripcionDao() {
		return suscripcionDao;
	}

	public void setSuscripcionDao(SuscripcionDao suscripcionDao) {
		this.suscripcionDao = suscripcionDao;
	}

	public UsuarioDao getUsuarioDao() {
		return usuarioDao;
	}

	public void setUsuarioDao(UsuarioDao usuarioDao) {
		this.usuarioDao = usuarioDao;
	}

	@Override
	public TipoSuscripcion obtenerDatosSegunTipo(Long tipo) {
		TipoSuscripcion tipoSusc = suscripcionDao.obtenerTipoSegunId(tipo);
		return tipoSusc;
	}
	
	@Override
	public LocalDate calcularFechaVencimiento(LocalDate fechaInicio, Long tipo) {
		LocalDate fechaVencimiento = null;
		if(tipo == 1) {
			fechaVencimiento = fechaInicio.plusMonths(1);
		}else if(tipo == 2) {
			fechaVencimiento = fechaInicio.plusMonths(6);
		}else if(tipo == 3) {
			fechaVencimiento = fechaInicio.plusYears(1);
		}
		
		return fechaVencimiento;
	}
	
	@Override
	public Long crearSuscripcion(Long tipo, LocalDate fechaInicio) {
		TipoSuscripcion tipoSusc = this.obtenerDatosSegunTipo(tipo);
		LocalDate fechaVencimiento = this.calcularFechaVencimiento(fechaInicio, tipo);
		Suscripcion susc = new Suscripcion();
		susc.setEstado(true);
		susc.setFechaInicio(fechaInicio);
		susc.setFechaVencimiento(fechaVencimiento);
		susc.setTipo(tipoSusc);
		return suscripcionDao.insertarSuscripcion(susc);
	}
	
	@Override
	public void insertarSuscripcionEnUsuario(Long tipo, LocalDate fechaInicio, Long id) {
		Long idSusc = this.crearSuscripcion(tipo, fechaInicio);
		Suscripcion susc = this.suscripcionDao.obtenerSuscripcionSegunId(idSusc);
		Usuario user = this.usuarioDao.obtenerUsuarioPorId(id);
		user.setSuscripcion(susc);
		this.usuarioDao.update(user);
	}
	
	@Override
	public Boolean vencerSuscripcion(Long id) {
		Suscripcion susc = this.suscripcionDao.obtenerSuscripcionSegunId(id);
		LocalDate vencim = susc.getFechaVencimiento();
		if(vencim.equals(LocalDate.now())) {
			susc.setEstado(false);
			this.suscripcionDao.updateSuscripcion(susc);
			return true;
		}else {
			return false;
		}
	}
		
}
