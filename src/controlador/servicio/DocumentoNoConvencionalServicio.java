package controlador.servicio;

import controlador.dao.DocumentoNoConvencionalDao;
import java.util.List;
import modelo.DocumentoNoConvencional;

/**
 * Clase que mediante una instancia de DocumentoNoConvencionalDao, obtiene todos
 * los métodos de la misma y junto a ello los del AdaptadorDao, de esta manera
 * una instancia de DocumentoNoConvencionalServicio bastará para realizar todas
 * las operaciones entre un objeto DocumentoNoConvencional y la Base de datos de
 * uso.
 *
 * @author Víctor Andrés Rojas
 * @author Mario Orellana
 */
public class DocumentoNoConvencionalServicio {

    private DocumentoNoConvencionalDao documentoNoConvencional = new DocumentoNoConvencionalDao();

    /**
     * Método que obtiene una instancia de DocumentoNoConvencional. En caso de
     * no existir se creará un nuevo objeto.
     *
     * @return Devuelve un objeto de tipo DocumentoNoConvencional.
     */
    public DocumentoNoConvencional obtenerDocumentoNoConvencional() {
        return documentoNoConvencional.getDocumentoNoConvencional();
    }

    /**
     * Método que obtiene una instancia de DocumentoNoConvencional mediante un
     * identificador o clave primaria.
     *
     * @param id Valor del identificador o clave primaria.
     * @return Devuelve un objeto de tipo DocumentoNoConvencional.
     */
    public DocumentoNoConvencional obtenerDocumentoNoConvencional(Long id) {
        return documentoNoConvencional.obtener(id);
    }

    /**
     * Método que asigna un objeto de tipo DocumentoNoConvencional al campo de
     * la clase DocumentoNoConvencionalDao que es del mismo tipo.
     *
     * @param documentoNoConvencional Instancia DocumentoNoConvencional a
     * asignarse.
     */
    public void fijarDocumentoNoConvencional(DocumentoNoConvencional documentoNoConvencional) {
        this.documentoNoConvencional.setDocumentoNoConvencional(documentoNoConvencional);
    }

    /**
     * Método que guarda o modifica un objeto de tipo DocumentoNoConvencional en
     * la respectiva Base de datos de uso.
     *
     * @return Devuelve un resultado Booleano, en caso de ser verdadero quiere
     * decir que la operación se realizó correctamente.
     */
    public boolean guardar() {
        return documentoNoConvencional.guardar();
    }

    /**
     * Método que realiza una consulta en la Base de datos y devuelve un listado
     * de objetos DocumentoNoConvencional.
     *
     * @return Devuelve una lista de Tipo DocumentoNoConvencional.
     */
    public List<DocumentoNoConvencional> listar() {
        return documentoNoConvencional.listar();
    }

    /**
     * Método que retorna una Lista de objetos de Tipo DocumentoNoConvencional.
     * Esta lista se obtiene a través de una consulta (Query) tomando como
     * referencia un String busqueda. Esta búsqueda puede realizarse por:
     * título, código, autor, tipo de Documento no Convencional.
     *
     * @param busqueda Palabra clave a ser encontrada.
     * @return Devuelve una lista de objetos de tipo Documento.
     */
    public List<DocumentoNoConvencional> listarDocumentoNoConvencionalLike(String busqueda) {
        return documentoNoConvencional.listarDocumentoNoConvencionalLike(busqueda);
    }
}
