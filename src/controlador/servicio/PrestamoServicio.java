package controlador.servicio;

import controlador.dao.PrestamoDao;
import java.util.List;
import modelo.Prestamo;

/**
 * Clase que mediante una instancia de PrestamoDao, obtiene todos los métodos de
 * la misma y junto a ello los del AdaptadorDao, de esta manera una instancia de
 * PrestamoServicio bastará para realizar todas las operaciones entre un objeto
 * Prestamo y la Base de datos de uso.
 *
 * @author Víctor Andrés Rojas
 * @author Mario Orellana
 */
public class PrestamoServicio {

    private PrestamoDao prestamo = new PrestamoDao();

    /**
     * Método que obtiene una instancia de Prestamo. En caso de no existir se
     * creará un nuevo objeto.
     *
     * @return Devuelve un objeto de tipo Prestamo.
     */
    public Prestamo obtenerPrestamo() {
        return prestamo.getPrestamo();
    }

    /**
     * Método que obtiene una instancia de Prestamo mediante un identificador o
     * clave primaria.
     *
     * @param id Valor del identificador o clave primaria.
     * @return Devuelve un objeto de tipo Prestamo.
     */
    public Prestamo obtenerPrestamo(Long id) {
        return prestamo.obtener(id);
    }

    /**
     * Método que asigna un objeto de tipo Prestamo al campo de la clase
     * PrestamoDao que es del mismo tipo.
     *
     * @param prestamo Instancia Prestamo a asignarse.
     */
    public void fijarPrestamo(Prestamo prestamo) {
        this.prestamo.setPrestamo(prestamo);
    }

    /**
     * Método que guarda o modifica un objeto de tipo Prestamo en la respectiva
     * Base de datos de uso.
     *
     * @return Devuelve un resultado Booleano, en caso de ser verdadero quiere
     * decir que la operación se realizó correctamente.
     */
    public boolean guardar() {
        return prestamo.guardar();
    }

    /**
     * Método que realiza una consulta en la Base de datos y devuelve un listado
     * de objetos Prestamo.
     *
     * @return Devuelve una lista de Tipo Prestamo.
     */
    public List<Prestamo> listar() {
        return prestamo.listar();
    }

    /**
     * Método que retorna una Lista de objetos de Tipo Prestamo. Esta lista se
     * obtiene a través de una consulta (Query) tomando como referencia un
     * String busqueda. Esta búsqueda puede realizarse por: título de Documento,
     * nombres y apellidos de Persona.
     *
     * @param busqueda Palabra clave a ser encontrada.
     * @return Devuelve una lista de objetos de tipo Prestamo.
     */
    public List<Prestamo> listarPrestamoLike(String busqueda) {
        return prestamo.listarPrestamoLike(busqueda);
    }
}
