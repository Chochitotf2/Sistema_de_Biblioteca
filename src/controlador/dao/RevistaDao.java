package controlador.dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import modelo.Revista;

/**
 *
 * @author Chochitotf2
 */
public class RevistaDao extends AdaptadorDao<Revista> {

    private Revista revista;

    public RevistaDao() {
        super(Revista.class);
    }

    public Revista getRevista() {
        if (revista == null) {
            revista = new Revista();
        }
        return revista;
    }

    public void setRevista(Revista revista) {
        this.revista = revista;
    }

    public boolean guardar() {
        boolean estado = false;
        try {
            getManager().getTransaction().begin();
            if (revista.getId() != null) {
                modificar(revista);
            } else {
                guardar(revista);
            }
            getManager().getTransaction().commit();
            estado = true;
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error al Guardar o Modificar Revista: " + e);
        }
        return estado;
    }

    public List<Revista> listarRevistaLike(String busqueda) {
        List<Revista> lista = new ArrayList<>();
        try {
            Query query = getManager().createQuery("SELECT p FROM Revista p WHERE (LOWER(p.titulo) LIKE CONCAT('%', :dato, '%'))"
                    + " OR (LOWER(p.codigo) LIKE CONCAT('%', :dato, '%'))"
                    + " OR (LOWER(p.issn) LIKE CONCAT('%', :dato, '%'))"
                    + " OR (LOWER(p.editorial) LIKE CONCAT('%', :dato, '%'))");
            query.setParameter("dato", busqueda);
            lista = query.getResultList();
        } catch (Exception e) {
            System.out.println("No se ha podido listar por Busqueda:" + e);
        }
        return lista;
    }
}
