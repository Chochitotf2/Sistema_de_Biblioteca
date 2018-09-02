package controlador.servicio;

import controlador.dao.RevistaDao;
import java.util.List;
import modelo.Revista;

/**
 * Clase que mediante una instancia de RevistaDao, obtiene todos los métodos de
 * la misma y junto a ello los del AdaptadorDao, de esta manera una instancia de
 * RevistaServicio bastará para realizar todas las operaciones entre un objeto
 * Revista y la Base de datos de uso.
 *
 * @author Víctor Andrés Rojas
 * @author Mario Orellana
 */
public class RevistaServicio {

    private RevistaDao revista = new RevistaDao();

    /**
     * Método que obtiene una instancia de Revista. En caso de no existir se
     * creará un nuevo objeto.
     *
     * @return Devuelve un objeto de tipo Revista.
     */
    public Revista obtenerRevista() {
        return revista.getRevista();
    }

    /**
     * Método que obtiene una instancia de Revista mediante un identificador o
     * clave primaria.
     *
     * @param id Valor del identificador o clave primaria.
     * @return Devuelve un objeto de tipo Revista.
     */
    public Revista obtenerRevista(Long id) {
        return revista.obtener(id);
    }

    /**
     * Método que asigna un objeto de tipo Revista al campo de la clase
     * RevistaDao que es del mismo tipo.
     *
     * @param revista Instancia Revista a asignarse.
     */
    public void fijarRevista(Revista revista) {
        this.revista.setRevista(revista);
    }

    /**
     * Método que guarda o modifica un objeto de tipo Revista en la respectiva
     * Base de datos de uso.
     *
     * @return Devuelve un resultado Booleano, en caso de ser verdadero quiere
     * decir que la operación se realizó correctamente.
     */
    public boolean guardar() {
        return revista.guardar();
    }

    /**
     * Método que realiza una consulta en la Base de datos y devuelve un listado
     * de objetos Revista.
     *
     * @return Devuelve una lista de Tipo Revista.
     */
    public List<Revista> listar() {
        return revista.listar();
    }

    /**
     * Método que retorna una Lista de objetos de Tipo Revista. Esta lista se
     * obtiene a través de una consulta (Query) tomando como referencia un
     * String busqueda. Esta búsqueda puede realizarse por: título, código,
     * issn, editorial.
     *
     * @param busqueda Palabra clave a ser encontrada.
     * @return Devuelve una lista de objetos de tipo Revista.
     */
    public List<Revista> listarRevistaLike(String busqueda) {
        return revista.listarRevistaLike(busqueda);
    }
}
