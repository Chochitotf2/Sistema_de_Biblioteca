package controlador.dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import modelo.Documento;

/**
 * Clase mediante la cual se realiza las operaciones que sean requeridas (A
 * través del AdaptadorDao) entre un objeto Documento y la Base de datos de uso.
 *
 * @author Víctor Andrés Rojas
 * @author Mario Orellana
 */
public class DocumentoDao extends AdaptadorDao<Documento> {

    private Documento documento;

    /**
     * Constructor por defecto que asigna la clase Documento al AdaptadorDao,
     * para asi poder realizar las operaciones que este contiene en sus métodos.
     */
    public DocumentoDao() {
        super(Documento.class);
    }

    /**
     * Método que obtiene una instancia de Documento. En caso de no existir se
     * creará un nuevo objeto.
     *
     * @return Devuelve un objeto de tipo Documento.
     */
    public Documento getDocumento() {
        if (documento == null) {
            documento = new Documento();
        }
        return documento;
    }

    /**
     * Método que asigna un objeto de tipo Documento al campo de esta clase que
     * es del mismo tipo.
     *
     * @param documento Instancia Documento a asignarse.
     */
    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    /**
     * Método que guarda o modifica un objeto de tipo Documento en la respectiva
     * Base de datos de uso.
     *
     * @return Devuelve un resultado Booleano, en caso de ser verdadero quiere
     * decir que la operación se realizó correctamente.
     */
    public boolean guardar() {
        boolean estado = false;
        try {
            getManager().getTransaction().begin();
            if (documento.getId() != null) {
                modificar(documento);
            } else {
                guardar(documento);
            }
            getManager().getTransaction().commit();
            estado = true;
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error al Guardar o Modificar Documento: " + e);
        }
        return estado;
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
        List<Documento> lista = new ArrayList<>();
        try {
            Query query = getManager()
                    .createQuery("SELECT p FROM Documento p WHERE p.tipoDocumento = :tipo");
            query.setParameter("tipo", tipo);
            lista = query.getResultList();
        } catch (Exception e) {
            System.out.println("No se ha podido listar por Tipo: " + e);
        }
        return lista;
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
        List<Documento> lista = new ArrayList<>();
        try {
            Query query = getManager().createQuery("SELECT p FROM Documento p WHERE (LOWER(p.titulo) LIKE CONCAT('%', :dato, '%'))"
                    + " OR (LOWER(p.codigo) LIKE CONCAT('%', :dato, '%'))");
            query.setParameter("dato", busqueda);
            lista = query.getResultList();
        } catch (Exception e) {
            System.out.println("No se ha podido listar por Busqueda: " + e);
        }
        return lista;
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
        List<Documento> lista = new ArrayList<>();
        try {
            Query query = getManager()
                    .createQuery("SELECT p FROM Documento p WHERE p.tipoDocumento = :tipo"
                            + " AND ((LOWER(p.titulo) LIKE CONCAT('%', :dato, '%'))"
                            + " OR (LOWER(p.codigo) LIKE CONCAT('%', :dato, '%')))");
            query.setParameter("tipo", tipo);
            query.setParameter("dato", busqueda);
            lista = query.getResultList();
        } catch (Exception e) {
            System.out.println("No se ha podido listar por Tipo-Busqueda: " + e);
        }
        return lista;
    }

}
