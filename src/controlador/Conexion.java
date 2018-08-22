package controlador;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Chochitotf2
 */
public class Conexion {

    private static EntityManager manager;
    private static final String NOMBREPERSISTENCIA = "Sistema_de_BibliotecaPU";

    public static EntityManagerFactory sesion() {
        return Persistence.createEntityManagerFactory(NOMBREPERSISTENCIA);
    }

    public static EntityManager getManager() {
        if (manager == null) {
            manager = sesion().createEntityManager();
        }
        return manager;
    }

    public static void main(String[] args) {

    }
}
