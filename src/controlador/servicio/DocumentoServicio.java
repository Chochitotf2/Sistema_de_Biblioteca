package controlador.servicio;

import controlador.dao.DocumentoDao;
import java.util.List;
import modelo.Documento;

/**
 * Clase que mediante una instancia de DocumentoDao, obtiene todos los métodos
 * de la misma y junto a ello los del AdaptadorDao, de esta manera una instancia
 * de DocumentoServicio bastará para realizar todas las operaciones entre un
 * objeto Documento y la Base de datos de uso.
 *
 * @author Víctor Andrés Rojas
 * @author Mario Orellana
 */
public class DocumentoServicio {

    private DocumentoDao documento = new DocumentoDao();

    /**
     * Método que obtiene una instancia de Documento. En caso de no existir se
     * creará un nuevo objeto.
     *
     * @return Devuelve un objeto de tipo Documento.
     */
    public Documento obtenerDocumento() {
        return documento.getDocumento();
    }

    /**
     * Método que obtiene una instancia de Documento mediante un identificador o
     * clave primaria.
     *
     * @param id Valor del identificador o clave primaria.
     * @return Devuelve un objeto de tipo Documento.
     */
    public Documento obtenerDocumento(Long id) {
        return documento.obtener(id);
    }

    /**
     * Método que asigna un objeto de tipo Documento al campo de la clase
     * DocumentoDao que es del mismo tipo.
     *
     * @param documento Instancia Documento a asignarse.
     */
    public void fijarDocumento(Documento documento) {
        this.documento.setDocumento(documento);
    }

    /**
     * Método que guarda o modifica un objeto de tipo Documento en la respectiva
     * Base de datos de uso.
     *
     * @return Devuelve un resultado Booleano, en caso de ser verdadero quiere
     * decir que la operación se realizó correctamente.
     */
    public boolean guardar() {
        return documento.guardar();
    }

    /**
     * Método que realiza una consulta en la Base de datos y devuelve un listado
     * de objetos Documento.
     *
     * @return Devuelve una lista de Tipo Documento.
     */
    public List<Documento> listar() {
        return documento.listar();
    }

    /**
     * Método que retorna una Lista de objetos de Tipo Documento. Esta lista se
     * obtiene a través de una consulta (Query) tomando como referencia un
     * String tipo, que denota el tipo de Documento.
     *
     * @param tipo Palabra clave a ser encontrada.
     * @return Devuelve una lista de objetos de tipo Documento.
     */
    public List<Documento> listarDocumentoTipo(String tipo) {
        return documento.listarDocumentoTipo(tipo);
    }

    /**
     * Método que retorna una Lista de objetos de Tipo Documento. Esta lista se
     * obtiene a través de una consulta (Query) tomando como referencia un
     * String busqueda. Esta búsqueda puede realizarse por: título, código.
     *
     * @param busqueda Palabra clave a ser encontrada.
     * @return Devuelve una lista de objetos de tipo Documento.
     */
    public List<Documento> listarDocumentoLike(String busqueda) {
        return documento.listarDocumentoLike(busqueda);
    }

    /**
     * Método que retorna una Lista de objetos de Tipo Documento. Esta lista se
     * obtiene a través de una consulta (Query) tomando como referencia un
     * String tipo, que denota el tipo de Documento. Además cuenta con un String
     * busqueda, que filtra Documentos por tipo mediante: título, código.
     *
     * @param tipo Tipo de Documento a ser encontrado.
     * @param busqueda Palabra clave a ser encontrada.
     * @return Devuelve una lista de objetos de tipo Documento.
     */
    public List<Documento> listarDocumentoTipoLike(String tipo, String busqueda) {
        return documento.listarDocumentoTipoLike(tipo, busqueda);
    }
}
