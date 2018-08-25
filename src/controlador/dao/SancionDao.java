package controlador.dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import modelo.Sancion;

/**
 *
 * @author Chochitotf2
 */
public class SancionDao extends AdaptadorDao<Sancion> {

    private Sancion sancion;

    public SancionDao() {
        super(Sancion.class);
    }

    public Sancion getSancion() {
        if (sancion == null) {
            sancion = new Sancion();
        }
        return sancion;
    }

    public void setSancion(Sancion sancion) {
        this.sancion = sancion;
    }

    public boolean guardar() {
        boolean estado = false;
        try {
            getManager().getTransaction().begin();
            if (sancion.getId() != null) {
                modificar(sancion);
            } else {
                guardar(sancion);
            }
            getManager().getTransaction().commit();
            estado = true;
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error al Guardar o Modificar Sancion: " + e);
        }
        return estado;
    }

    public List<Sancion> listarSancionLike(String busqueda) {
        List<Sancion> lista = new ArrayList<>();
        try {
            Query query = getManager().createQuery("SELECT p FROM Sancion p WHERE (LOWER(p.prestamo.persona.nombres) LIKE CONCAT('%', :dato, '%'))"
                    + " OR (LOWER(p.prestamo.persona.nombres) LIKE CONCAT('%', :dato, '%'))"
                    + " OR (LOWER(p.prestamo.persona.dni) LIKE CONCAT('%', :dato, '%'))");
            query.setParameter("dato", busqueda);
            lista = query.getResultList();
        } catch (Exception e) {
            System.out.println("No se ha podido listar por Busqueda:" + e);
        }
        return lista;
    }
}
