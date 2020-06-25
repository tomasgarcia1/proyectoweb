package ar.edu.unlam.tallerweb1;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ar.edu.unlam.tallerweb1.modelo.Comida;
import ar.edu.unlam.tallerweb1.modelo.Restriccion;
import ar.edu.unlam.tallerweb1.modelo.TipoHorario;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.ComidaDao;
import ar.edu.unlam.tallerweb1.repositorios.RestriccionDao;
import ar.edu.unlam.tallerweb1.repositorios.UsuarioDao;
import ar.edu.unlam.tallerweb1.servicios.ServicioComidaImpl;

import static org.assertj.core.api.Assertions.*;
import  static org.mockito.Mockito.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

// Se indica que los test que hereden de esta clase corran con el runner de junit para spring.
@RunWith(SpringJUnit4ClassRunner.class)
// Se indica
@ContextConfiguration(locations = {"/test-applicationContext.xml"})
// Clase base para los test que se pretende que se corran dentro del contexto de spring
public abstract class SpringTest {

    // Tiene inyectado el session factory para que los test que hereden de éste tengan acceso al mismo
    @Inject
    private SessionFactory sessionFactory;

    // Metodo para obtener una sesion de base de datos
    protected Session session() {
        return this.sessionFactory.getCurrentSession();
    }

}
