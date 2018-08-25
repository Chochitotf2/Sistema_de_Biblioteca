package controlador.dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import modelo.Documento;

/**
 *
 * @author Chochitotf2
 */
public class DocumentoDao extends AdaptadorDao<Documento> {

    private Documento documento;

    public DocumentoDao() {
        super(Documento.class);
    }

    public Documento getDocumento() {
        if (documento == null) {
            documento = new Documento();
        }
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

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

    public List<Documento> listarDocumentoLike(String busqueda) {
        List<Documento> lista = new ArrayList<>();
        try {
            Query query = getManager().createQuery("SELECT p FROM Documento p WHERE (LOWER(p.titulo) LIKE CONCAT('%', :dato, '%'))"
                    + " OR (LOWER(p.codigo) LIKE CONCAT('%', :dato, '%'))");
            query.setParameter("dato", busqueda);
            lista = query.getResultList();
        } catch (Exception e) {
            System.out.println("No se ha podido listar por Busqueda:" + e);
        }
        return lista;
    }

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
