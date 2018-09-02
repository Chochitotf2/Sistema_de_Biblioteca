package controlador.servicio;

import controlador.dao.LibroDao;
import java.util.List;
import modelo.Libro;

/**
 * Clase que mediante una instancia de LibroDao, obtiene todos los
 * métodos de la misma y junto a ello los del AdaptadorDao, de esta manera una
 * instancia de LibroServicio bastará para realizar todas las
 * operaciones entre un objeto Libro y la Base de datos de uso.
 *
 * @author Víctor Andrés Rojas
 * @author Mario Orellana
 */
public class LibroServicio {

    private LibroDao libro = new LibroDao();

    /**
     * Método que obtiene una instancia de Libro. En caso de no existir
     * se creará un nuevo objeto.
     *
     * @return Devuelve un objeto de tipo Libro.
     */
    public Libro obtenerLibro() {
        return libro.getLibro();
    }

    /**
     * Método que obtiene una instancia de Libro mediante un
     * identificador o clave primaria.
     *
     * @param id Valor del identificador o clave primaria.
     * @return Devuelve un objeto de tipo Libro.
     */
    public Libro obtenerLibro(Long id) {
        return libro.obtener(id);
    }

    /**
     * Método que asigna un objeto de tipo Libro al campo de la clase
     * LibroDao que es del mismo tipo.
     *
     * @param libro  Instancia Libro a asignarse.
     */
    public void fijarLibro(Libro libro) {
        this.libro.setLibro(libro);
    }

    /**
     * Método que guarda o modifica un objeto de tipo Libro en la
     * respectiva Base de datos de uso.
     *
     * @return Devuelve un resultado Booleano, en caso de ser verdadero quiere
     * decir que la operación se realizó correctamente.
     */
    public boolean guardar() {
        return libro.guardar();
    }

    /**
     * Método que realiza una consulta en la Base de datos y devuelve un listado
     * de objetos Libro.
     *
     * @return Devuelve una lista de Tipo Libro.
     */
    public List<Libro> listar() {
        return libro.listar();
    }

    /**
     * Método que retorna una Lista de objetos de Tipo Libro. Esta lista se
     * obtiene a través de una consulta (Query) tomando como referencia un
     * String busqueda. Esta búsqueda puede realizarse por: título, código,
     * autor(es), editorial, isbn.
     *
     * @param busqueda Palabra clave a ser encontrada.
     * @return Devuelve una lista de objetos de tipo Libro.
     */
    public List<Libro> listarLibroLike(String busqueda) {
        return libro.listarLibroLike(busqueda);
    }
}
