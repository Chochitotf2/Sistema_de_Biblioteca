package controlador.servicio;

import controlador.dao.RolDao;
import java.util.List;
import modelo.Rol;

/**
 * Clase que mediante una instancia de RolDao, obtiene todos los métodos de la
 * misma y junto a ello los del AdaptadorDao, de esta manera una instancia de
 * RolServicio bastará para realizar todas las operaciones entre un objeto Rol y
 * la Base de datos de uso.
 *
 * @author Víctor Andrés Rojas
 * @author Mario Orellana
 */
public class RolServicio {

    private RolDao rol = new RolDao();

    /**
     * Método que obtiene una instancia de Rol. En caso de no existir se creará
     * un nuevo objeto.
     *
     * @return Devuelve un objeto de tipo Rol.
     */
    public Rol obtenerRol() {
        return rol.getRol();
    }

    /**
     * Método que obtiene una instancia de Rol mediante un identificador o clave
     * primaria.
     *
     * @param id Valor del identificador o clave primaria.
     * @return Devuelve un objeto de tipo Rol.
     */
    public Rol obtenerRol(Long id) {
        return rol.obtener(id);
    }

    /**
     * Método que asigna un objeto de tipo Rol al campo de la clase RolDao que
     * es del mismo tipo.
     *
     * @param rol Instancia Rol a asignarse.
     */
    public void fijarRol(Rol rol) {
        this.rol.setRol(rol);
    }

    /**
     * Método que guarda o modifica un objeto de tipo Rol en la respectiva Base
     * de datos de uso.
     *
     * @return Devuelve un resultado Booleano, en caso de ser verdadero quiere
     * decir que la operación se realizó correctamente.
     */
    public boolean guardar() {
        return rol.guardar();
    }

    /**
     * Método que realiza una consulta en la Base de datos y devuelve un listado
     * de objetos Rol.
     *
     * @return Devuelve una lista de Tipo Rol.
     */
    public List<Rol> listar() {
        return rol.listar();
    }

    /**
     * Método que obtiene un objeto de tipo Rol que contenga en su atributo
     * 'nombre' el mismo valor que se le indica en el parámetro del método.
     *
     * @param nombre Valor a ser encontrado dentro de la consulta.
     * @return Devuelve un objeto Rol en caso de ser encontrado.
     */
    public Rol buscarRol(String nombre) {
        return rol.buscarRol(nombre);
    }

    /**
     * Método necesario para crear los distintos roles que son usados en el
     * sistema. Serán creados una sola vez en la Base de datos.
     */
    public void crearRoles() {
        if (listar().isEmpty()) {
            obtenerRol().setNombre("Administrador");
            guardar();
            fijarRol(null);
            obtenerRol().setNombre("Rol");
            guardar();
            fijarRol(null);
            obtenerRol().setNombre("Profesor");
            guardar();
            fijarRol(null);
            obtenerRol().setNombre("Alumno");
            guardar();
            fijarRol(null);
        }
    }
}
