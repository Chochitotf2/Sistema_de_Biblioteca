package controlador;

import controlador.servicio.CuentaServicio;
import controlador.servicio.RolServicio;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Esta clase Conexión se compone de los elementos necesarios para enviar los
 * datos del programa a su respectiva Base de datos de uso
 *
 * @author Víctor Andrés Rojas
 * @author Mario Orellana
 */
public class Conexion {

    /**
     * Esta instancia EntityManager ayuda a definir los métodos que se utilizan
     * para interactuar con el contexto de persistencia. Se puede utilizar para
     * crear y eliminar instancias persistentes entidad, para encontrar
     * entidades por su clave primaria, y para consultar sobre las entidades.
     */
    private static EntityManager manager;
    /**
     * Este campo contiene el nombre de la unidad de Persistencia usada en el
     * sistema.
     */
    private static final String NOMBREPERSISTENCIA = "Sistema_de_BibliotecaPU";

    /**
     * Método que tiene como objetivo crear y devolver un EntityManagerFactory
     * para la unidad de persistencia nombrada. Un EntityManagerFactory es único
     * y es con el que se gestiona todas las entidades
     *
     * @return Devuelve un EntityManagerFactory para la unidad de persistencia
     * nombrada.
     */
    public static EntityManagerFactory sesion() {
        return Persistence.createEntityManagerFactory(NOMBREPERSISTENCIA);
    }

    /**
     * Método que sirve obtener un EntityManager, útil para gestionar un
     * conjunto de entidades u objetos.
     *
     * @return Devuelve una instancia EntityManager.
     */
    public static EntityManager getManager() {
        if (manager == null) {
            manager = sesion().createEntityManager();
        }
        return manager;
    }

    public static void main(String[] args) {
        new RolServicio().crearRoles();
        new CuentaServicio().crearCuentaAdministrador();
    }
}
