package controlador.dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import modelo.DocumentoNoConvencional;

/**
 * Clase mediante la cual se realiza las operaciones que sean requeridas (A
 * través del AdaptadorDao) entre un objeto DocumentoNoConvencional y la Base de
 * datos de uso.
 *
 * @author Víctor Andrés Rojas
 * @author Mario Orellana
 */
public class DocumentoNoConvencionalDao extends AdaptadorDao<DocumentoNoConvencional> {

    private DocumentoNoConvencional documentoNoConvencional;

    /**
     * Constructor por defecto que asigna la clase DocumentoNoConvencional al
     * AdaptadorDao, para asi poder realizar las operaciones que este contiene
     * en sus métodos.
     */
    public DocumentoNoConvencionalDao() {
        super(DocumentoNoConvencional.class);
    }

    /**
     * Método que obtiene una instancia de DocumentoNoConvencional. En caso de
     * no existir se creará un nuevo objeto.
     *
     * @return Devuelve un objeto de tipo DocumentoNoConvencional.
     */
    public DocumentoNoConvencional getDocumentoNoConvencional() {
        if (documentoNoConvencional == null) {
            documentoNoConvencional = new DocumentoNoConvencional();
        }
        return documentoNoConvencional;
    }

    /**
     * Método que asigna un objeto de tipo DocumentoNoConvencional al campo de
     * esta clase que es del mismo tipo.
     *
     * @param documentoNoConvencional Instancia DocumentoNoConvencional a
     * asignarse.
     */
    public void setDocumentoNoConvencional(DocumentoNoConvencional documentoNoConvencional) {
        this.documentoNoConvencional = documentoNoConvencional;
    }

    /**
     * Método que guarda o modifica un objeto de tipo DocumentoNoConvencional en
     * la respectiva Base de datos de uso.
     *
     * @return Devuelve un resultado Booleano, en caso de ser verdadero quiere
     * decir que la operación se realizó correctamente.
     */
    public boolean guardar() {
        boolean estado = false;
        try {
            getManager().getTransaction().begin();
            if (documentoNoConvencional.getId() != null) {
                modificar(documentoNoConvencional);
            } else {
                guardar(documentoNoConvencional);
            }
            getManager().getTransaction().commit();
            estado = true;
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error al Guardar o Modificar Documento No Convencional: " + e);
        }
        return estado;
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
        List<DocumentoNoConvencional> lista = new ArrayList<>();
        try {
            Query query = getManager().createQuery("SELECT p FROM DocumentoNoConvencional p WHERE (LOWER(p.titulo) LIKE CONCAT('%', :dato, '%'))"
                    + " OR (LOWER(p.codigo) LIKE CONCAT('%', :dato, '%'))"
                    + " OR (LOWER(p.autor) LIKE CONCAT('%', :dato, '%'))"
                    + " OR (LOWER(p.tipoNoConvencional) LIKE CONCAT('%', :dato, '%'))");
            query.setParameter("dato", busqueda);
            lista = query.getResultList();
        } catch (Exception e) {
            System.out.println("No se ha podido listar por Busqueda: " + e);
        }
        return lista;
    }
}
