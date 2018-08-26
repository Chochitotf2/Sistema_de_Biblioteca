package controlador.dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import modelo.DocumentoNoConvencional;

/**
 *
 * @author Chochitotf2
 */
public class DocumentoNoConvencionalDao extends AdaptadorDao<DocumentoNoConvencional> {

    private DocumentoNoConvencional documentoNoConvencional;

    public DocumentoNoConvencionalDao() {
        super(DocumentoNoConvencional.class);
    }

    public DocumentoNoConvencional getDocumentoNoConvencional() {
        if (documentoNoConvencional == null) {
            documentoNoConvencional = new DocumentoNoConvencional();
        }
        return documentoNoConvencional;
    }

    public void setDocumentoNoConvencional(DocumentoNoConvencional documentoNoConvencional) {
        this.documentoNoConvencional = documentoNoConvencional;
    }

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
            System.out.println("No se ha podido listar por Busqueda:" + e);
        }
        return lista;
    }
}
