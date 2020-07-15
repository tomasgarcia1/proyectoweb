package ar.edu.unlam.tallerweb1.servicios;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Actividad;
import ar.edu.unlam.tallerweb1.modelo.Restriccion;
import ar.edu.unlam.tallerweb1.modelo.Rol;
import ar.edu.unlam.tallerweb1.modelo.Sexo;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RestriccionDao;
import ar.edu.unlam.tallerweb1.repositorios.UsuarioDao;

@Service
@Transactional
public class ServicioUsuarioImpl implements ServicioUsuario {
	@Inject
	private UsuarioDao usuarioDao;

	//--------REGISTRAR USUARIO--------
	
	@Override
	public Long registrarUsuario(Usuario usuario, List<Restriccion> restricciones) {
		usuario.setPassword(this.encriptarPassword(usuario.getPassword()));
		usuario.setCaloriasDiarias(this.calcularCaloriasDiarias(usuario));
		usuario.setRol(Rol.CLIENTE);
		usuario.setGastos(0.0);
		usuario.setRestricciones(restricciones);
		return usuarioDao.registrarUsuario(usuario);
	}

	//--------ACTUALIZAR USUARIO--------
	
	@Override
	public void update(Usuario usuario) {
		this.usuarioDao.update(usuario);
	}

	//--------VALIDAR EXIST DE EMAIL--------
	
	@Override
	public Boolean validarExistenciaEmail(String email) {
		return usuarioDao.validarExistenciaEmail(email);
	}
	
	//--------VALIDAR EXIST DE EMAIL--------
	
	@Override
	public Usuario obtenerUsuarioPorId(Long id) {
		return this.usuarioDao.obtenerUsuarioPorId(id);
	}


	//--------VALIDAR FORMATO EMAIL--------
	
	@Override
	public Boolean validarFormatoEmail(String email) {
		String regex = "[^@]+@[^@]+\\.[a-zA-Z]{2,}";

		return Pattern.matches(regex, email);
	}
	
	//--------VALIDAR PASSWORD--------
	public Boolean validarPassword(String password)
	{
		String regex = "^(?=\\w*\\d)(?=\\w*[A-Z])(?=\\w*[a-z])\\S{8,16}$";
		return Pattern.matches(regex, password);
	}
	
	//--------VALIDAR USERNAME--------
	public Boolean validarUsername(String username)
	{
		if(!(username.length()>=3&&username.length()<=12))
			return false;
		return true;
	}
	
	//--------VALIDAR FECHA--------
	private Boolean validarFecha(LocalDate fecNac) {
		LocalDate hoy=LocalDate.now();
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate inicio = LocalDate.parse("1900-01-01", formato);
		if(!(fecNac.isAfter(inicio) && fecNac.isBefore(hoy)))
			return false;
		return true;
	}
	
	//--------VALIDAR EXISTENCIA DE, AL MENOS, UNA RESTRICCION--------
	
	
	//-------ENCRIPTADO DE CONTRASEÑA--------
	
	/*
	 * Se recibe la password por parametro y se la convierte en una cadena de
	 * caracteres generada bajo el algoritmo SHA-256, el cual genera un hash de 64
	 * dígitos hexadecimales.
	 */
	
	@Override
	public String encriptarPassword(String password) {
		return org.apache.commons.codec.digest.DigestUtils.sha256Hex(password);
	}
	
	public List<String> validarUsuario(Usuario usuario, List<Restriccion> restricciones)
	{
		List<String> errores=new LinkedList<String>();
		if(!(this.validarDatosNulos(usuario, restricciones)))
			errores.add("Agregar todos los datos solicitados.");
		else {
			if(this.validarExistenciaEmail(usuario.getEmail()))
				errores.add("Ya existe un usuario con este e-mail.");
			if(!this.validarFormatoEmail(usuario.getEmail()))
				errores.add("El formato del e-mail es invalido.");
			if(!(this.validarPassword(usuario.getPassword())))
				errores.add("Formato de contrasena invalida. Debe tener entre 8 y 16 caracteres, una mayuscula y un numero.");
			if(!(usuario.getAltura()>50 && usuario.getAltura()<260))
				errores.add("Ingrese una altura valida");
			if(!(usuario.getPeso()>25.0&&usuario.getPeso()<600.0))
				errores.add("Hablar con administracion.");
			if(!(this.validarFecha(usuario.getFechaDeNacimiento())))
				errores.add("Ingrese una fecha valida");
			if(restricciones.isEmpty())
				errores.add("Seleccion al menos una restriccion");
			if(!(this.validarUsername(usuario.getUsername()))) 
				errores.add("Formato de username invalido. Debe tener entre 3 y 12 caracteres");
		}
		return errores;
	}
	public List<String> validarUsuarioEditar(Usuario usuario)
	{
		List<String> errores=new LinkedList<String>();
		if(!(this.validarDatosNulosEditar(usuario)))
			errores.add("Agregar todos los datos solicitados.");
		else {
			if(!this.validarFormatoEmail(usuario.getEmail()))
				errores.add("El formato del e-mail es invalido.");
			if(!(this.validarPassword(usuario.getPassword())))
				errores.add("Formato de contrasena invalida. Debe tener entre 8 y 16 caracteres, una mayuscula y un numero.");
			if(!(usuario.getAltura()>50 && usuario.getAltura()<260))
				errores.add("Ingrese una altura valida");
			if(!(usuario.getPeso()>25.0&&usuario.getPeso()<600.0))
				errores.add("Hablar con administracion.");
			if(!(this.validarFecha(usuario.getFechaDeNacimiento())))
				errores.add("Ingrese una fecha valida");
			
			if(!(this.validarUsername(usuario.getUsername()))) 
				errores.add("Formato de username invalido. Debe tener entre 3 y 12 caracteres");
		}
		return errores;
	}
	
	public Boolean validarDatosNulosEditar(Usuario usuario)
	{
		if(usuario.getActividad()==null || usuario.getAltura()==null
				|| usuario.getEmail()==null || usuario.getPeso()==null 
				|| usuario.getFechaDeNacimiento()==null
				|| usuario.getPassword()==null || usuario.getSexo()==null
				||usuario.getUsername()==null)
			return false;
		return true;
	}
	
	//----------VALIDAR NULL------------
	public Boolean validarDatosNulos(Usuario usuario, List<Restriccion> restricciones)
	{
		if(usuario.getActividad()==null || usuario.getAltura()==null
				|| usuario.getEmail()==null || usuario.getPeso()==null 
				|| usuario.getFechaDeNacimiento()==null
				|| usuario.getPassword()==null || usuario.getSexo()==null
				|| restricciones == null||usuario.getUsername()==null)
			return false;
		return true;
	}
	//----------CONSULTA EMAIL Y PASS USER------------
	
	@Override
	public Usuario consultarEmailYPassDeUsuario(Usuario usuario) {
		/* Se setea la password encriptada porque esta guardada asi en la DB */
		usuario.setPassword(this.encriptarPassword(usuario.getPassword()));
		return usuarioDao.consultarEmailYPassDeUsuario(usuario);
	}
	
	//---------OBTENER CALORIAS POR ID--------
	
	@Override
	public Double obtenerCaloriasPorId(Long id) {
		Usuario user = usuarioDao.obtenerUsuarioPorId(id);
		return user.getCaloriasDiarias();
	}

	//-------------CALCULAR CALORIAS DIARIAS-----------
	
	@Override
	public Double calcularCaloriasDiarias(Usuario usuario) {
		double ca = 0.0;
		double mb = calcularMB(usuario);
		if (usuario.getActividad().equals(Actividad.SEDENTARIO)) {
			ca = mb * 1.2;
		} else if (usuario.getActividad().equals(Actividad.LEVEMENTEACTIVO)) {
			ca = mb * 1.375;
		} else if (usuario.getActividad().equals(Actividad.MODERADAMENTEACTIVO)) {
			ca = mb * 1.55;
		} else if (usuario.getActividad().equals(Actividad.MUYACTIVO)) {
			ca = mb * 1725;
		} else if (usuario.getActividad().equals(Actividad.HIPERACTIVO)) {
			ca = mb * 1.9;
		}
		return (double) Math.round(ca * 100) / 100;
	}

	//----------CALCULAR MB------------
	
	private static Double calcularMB(Usuario usuario) {
		Double mb = 0.0;
		if (usuario.getSexo() == Sexo.FEMENINO) {
			mb = 655.1 + (9.463 * usuario.getPeso()) + (1.8 * usuario.getAltura())
					- (4.6756 * calcularEdad(usuario.getFechaDeNacimiento()));
		} else {
			mb = 66.473 + (13.751 * usuario.getPeso()) + (5.0033 * usuario.getAltura())
					- (6.7550 * calcularEdad(usuario.getFechaDeNacimiento()));
		}
		return mb;
	}

	//----------CALCULAR EDAD----------
	
	private static long calcularEdad(LocalDate fecNac) {
		/*Calendar calendar = new GregorianCalendar();
		calendar.setTime(fecNac);
		int year = calendar.get(Calendar.YEAR);
		// Add one to month {0 - 11}
		int month = calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);*/
		
		LocalDate nac = fecNac;
		LocalDate ahora = LocalDate.now();
		int fecha = Period.between(nac, ahora).getYears();
		return fecha;
	}
	
	//----------EDITAR USUARIO----------
	
	public void editarUsuario(Usuario usuario) {
		usuario.setCaloriasDiarias(calcularCaloriasDiarias(usuario));
		usuario.setPassword(encriptarPassword(usuario.getPassword()));
		usuario.setSuscripcion(usuarioDao.obtenerUsuarioPorId(usuario.getId()).getSuscripcion());
		usuario.setRol(usuarioDao.obtenerUsuarioPorId(usuario.getId()).getRol());
		usuarioDao.editarUsuario(usuario);
	}
}

