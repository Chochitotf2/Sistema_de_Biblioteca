package controlador.dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import modelo.Tesis;

/**
 *
 * @author Chochitotf2
 */
public class TesisDao extends AdaptadorDao<Tesis> {

    private Tesis tesis;

    public TesisDao() {
        super(Tesis.class);
    }

    public Tesis getTesis() {
        if (tesis == null) {
            tesis = new Tesis();
        }
        return tesis;
    }

    public void setTesis(Tesis tesis) {
        this.tesis = tesis;
    }

    public boolean guardar() {
        boolean estado = false;
        try {
            getManager().getTransaction().begin();
            if (tesis.getId() != null) {
                modificar(tesis);
            } else {
                guardar(tesis);
            }
            getManager().getTransaction().commit();
            estado = true;
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error al Guardar o Modificar Tesis: " + e);
        }
        return estado;
    }

    public List<Tesis> listarTesisLike(String busqueda) {
        List<Tesis> lista = new ArrayList<>();
        try {
            Query query = getManager().createQuery("SELECT p FROM Tesis p WHERE (LOWER(p.titulo) LIKE CONCAT('%', :dato, '%'))"
                    + " OR (LOWER(p.codigo) LIKE CONCAT('%', :dato, '%'))"
                    + " OR (LOWER(p.autores) LIKE CONCAT('%', :dato, '%'))"
                    + " OR (LOWER(p.asesores) LIKE CONCAT('%', :dato, '%'))"
                    + " OR (LOWER(p.facultad) LIKE CONCAT('%', :dato, '%'))");
            query.setParameter("dato", busqueda);
            lista = query.getResultList();
        } catch (Exception e) {
            System.out.println("No se ha podido listar por Busqueda:" + e);
        }
        return lista;
    }
}
