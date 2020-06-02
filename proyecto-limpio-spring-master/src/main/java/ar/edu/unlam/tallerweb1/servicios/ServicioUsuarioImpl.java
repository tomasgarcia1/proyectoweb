package ar.edu.unlam.tallerweb1.servicios;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Pattern;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Actividad;
import ar.edu.unlam.tallerweb1.modelo.Sexo;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.UsuarioDao;

@Service
@Transactional
public class ServicioUsuarioImpl implements ServicioUsuario {
	@Inject
	private UsuarioDao usuarioDao;
	@Override
	public Long registrarUsuario(Usuario usuario) {
		return usuarioDao.registrarUsuario(usuario);
	}
	@Override
	public Boolean validarExistenciaEmail(String email) {
		return usuarioDao.validarExistenciaEmail(email);
	}
	@Override
	public Boolean validarFormatoEmail(String email) {
		String regex="[^@]+@[^@]+\\.[a-zA-Z]{2,}";
		
		return Pattern.matches(regex, email);
	}
	@Override
	public String encriptarPassword(String password) {
		return org.apache.commons.codec.digest.DigestUtils.sha256Hex(password);
	}
	
	@Override
	public Double obtenerCaloriasPorId(Long id) {
		Usuario user = usuarioDao.obtenerUsuarioPorId(id);
		return user.getCaloriasDiarias();
	}
	@Override
	public Double calcularCaloriasDiarias(Usuario usuario) {
		double ca=0.0;
		double mb= calcularMB(usuario);
		if(usuario.getActividad().equals(Actividad.SEDENTARIO)) {
			ca=mb*1.2;
		}else if(usuario.getActividad().equals(Actividad.LEVEMENTEACTIVO)) {
			ca=mb*1.375;
		}else if(usuario.getActividad().equals(Actividad.MODERADAMENTEACTIVO)) {
			ca=mb*1.55;
		}else if(usuario.getActividad().equals(Actividad.MUYACTIVO)) {
			ca=mb*1725;
		}else if(usuario.getActividad().equals(Actividad.HIPERACTIVO)) {
			ca=mb*1.9;
		}
		return (double)Math.round(ca * 100) / 100;
	}
	private static Double calcularMB(Usuario usuario) {
		Double mb=0.0;
		if(usuario.getSexo()==Sexo.FEMENINO) {
			mb= 655.1 + (9.463 *usuario.getPeso()) + (1.8 * usuario.getAltura()) - (4.6756 * calcularEdad(usuario.getFechaDeNacimiento()));
		}else {
			mb=66.473 + (13.751 *usuario.getPeso()) + (5.0033 * usuario.getAltura()) - (6.7550 * calcularEdad(usuario.getFechaDeNacimiento()));
		}
		return mb;
	}
	private static long calcularEdad(Date fecNac) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(fecNac);
		int year = calendar.get(Calendar.YEAR);
		//Add one to month {0 - 11}
		int month = calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		
		LocalDate nac = LocalDate.of(year, month, day);
		LocalDate ahora = LocalDate.now();
		int fecha=Period.between(nac, ahora).getYears();
		return fecha;
	}
	@Override
	public void update(Usuario usuario) {
		this.usuarioDao.update(usuario);		
	}
}

