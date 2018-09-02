package controlador.servicio;

import controlador.dao.TesisDao;
import java.util.List;
import modelo.Tesis;

/**
 * Clase que mediante una instancia de TesisDao, obtiene todos los métodos de la
 * misma y junto a ello los del AdaptadorDao, de esta manera una instancia de
 * TesisServicio bastará para realizar todas las operaciones entre un objeto
 * Tesis y la Base de datos de uso.
 *
 * @author Víctor Andrés Rojas
 * @author Mario Orellana
 */
public class TesisServicio {

    private TesisDao tesis = new TesisDao();

    /**
     * Método que obtiene una instancia de Tesis. En caso de no existir se
     * creará un nuevo objeto.
     *
     * @return Devuelve un objeto de tipo Tesis.
     */
    public Tesis obtenerTesis() {
        return tesis.getTesis();
    }

    /**
     * Método que obtiene una instancia de Tesis mediante un identificador o
     * clave primaria.
     *
     * @param id Valor del identificador o clave primaria.
     * @return Devuelve un objeto de tipo Tesis.
     */
    public Tesis obtenerTesis(Long id) {
        return tesis.obtener(id);
    }

    /**
     * Método que asigna un objeto de tipo Tesis al campo de la clase TesisDao
     * que es del mismo tipo.
     *
     * @param tesis Instancia Tesis a asignarse.
     */
    public void fijarTesis(Tesis tesis) {
        this.tesis.setTesis(tesis);
    }

    /**
     * Método que guarda o modifica un objeto de tipo Tesis en la respectiva
     * Base de datos de uso.
     *
     * @return Devuelve un resultado Booleano, en caso de ser verdadero quiere
     * decir que la operación se realizó correctamente.
     */
    public boolean guardar() {
        return tesis.guardar();
    }

    /**
     * Método que realiza una consulta en la Base de datos y devuelve un listado
     * de objetos Tesis.
     *
     * @return Devuelve una lista de Tipo Tesis.
     */
    public List<Tesis> listar() {
        return tesis.listar();
    }

    /**
     * Método que retorna una Lista de objetos de Tipo Tesis. Esta lista se
     * obtiene a través de una consulta (Query) tomando como referencia un
     * String busqueda. Esta búsqueda puede realizarse por: título, código,
     * autor(es), asesor(es), facultad.
     *
     * @param busqueda Palabra clave a ser encontrada.
     * @return Devuelve una lista de objetos de tipo Tesis.
     */
    public List<Tesis> listarTesisLike(String busqueda) {
        return tesis.listarTesisLike(busqueda);
    }
}
