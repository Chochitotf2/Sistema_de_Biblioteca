package controlador.servicio;

import controlador.dao.SancionDao;
import java.util.List;
import modelo.Sancion;

/**
 * Clase que mediante una instancia de SancionDao, obtiene todos los métodos de
 * la misma y junto a ello los del AdaptadorDao, de esta manera una instancia de
 * SancionServicio bastará para realizar todas las operaciones entre un objeto
 * Sancion y la Base de datos de uso.
 *
 * @author Víctor Andrés Rojas
 * @author Mario Orellana
 */
public class SancionServicio {

    private SancionDao sancion = new SancionDao();

    /**
     * Método que obtiene una instancia de Sancion. En caso de no existir se
     * creará un nuevo objeto.
     *
     * @return Devuelve un objeto de tipo Sancion.
     */
    public Sancion obtenerSancion() {
        return sancion.getSancion();
    }

    /**
     * Método que obtiene una instancia de Sancion mediante un identificador o
     * clave primaria.
     *
     * @param id Valor del identificador o clave primaria.
     * @return Devuelve un objeto de tipo Sancion.
     */
    public Sancion obtenerSancion(Long id) {
        return sancion.obtener(id);
    }

    /**
     * Método que asigna un objeto de tipo Sancion al campo de la clase
     * SancionDao que es del mismo tipo.
     *
     * @param sancion Instancia Sancion a asignarse.
     */
    public void fijarSancion(Sancion sancion) {
        this.sancion.setSancion(sancion);
    }

    /**
     * Método que guarda o modifica un objeto de tipo Sancion en la respectiva
     * Base de datos de uso.
     *
     * @return Devuelve un resultado Booleano, en caso de ser verdadero quiere
     * decir que la operación se realizó correctamente.
     */
    public boolean guardar() {
        return sancion.guardar();
    }

    /**
     * Método que realiza una consulta en la Base de datos y devuelve un listado
     * de objetos Sancion.
     *
     * @return Devuelve una lista de Tipo Sancion.
     */
    public List<Sancion> listar() {
        return sancion.listar();
    }

    /**
     * Método que retorna una Lista de objetos de Tipo Sancion. Esta lista se
     * obtiene a través de una consulta (Query) tomando como referencia un
     * String busqueda. Esta búsqueda puede realizarse por: nombres, apellidos y
     * cédula de la Persona.
     *
     * @param busqueda Palabra clave a ser encontrada.
     * @return Devuelve una lista de objetos de tipo Sancion.
     */
    public List<Sancion> listarSancionLike(String busqueda) {
        return sancion.listarSancionLike(busqueda);
    }
}
