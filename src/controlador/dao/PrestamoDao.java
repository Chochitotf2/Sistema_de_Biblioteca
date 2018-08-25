package controlador.dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import modelo.Prestamo;

/**
 *
 * @author Chochitotf2
 */
public class PrestamoDao extends AdaptadorDao<Prestamo> {

    private Prestamo prestamo;

    public PrestamoDao() {
        super(Prestamo.class);
    }

    public Prestamo getPrestamo() {
        if (prestamo == null) {
            prestamo = new Prestamo();
        }
        return prestamo;
    }

    public void setPrestamo(Prestamo prestamo) {
        this.prestamo = prestamo;
    }

    public boolean guardar() {
        boolean estado = false;
        try {
            getManager().getTransaction().begin();
            if (prestamo.getId() != null) {
                modificar(prestamo);
            } else {
                guardar(prestamo);
            }
            getManager().getTransaction().commit();
            estado = true;
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error al Guardar o Modificar Prestamo: " + e);
        }
        return estado;
    }

    public List<Prestamo> listarPrestamoLike(String busqueda) {
        List<Prestamo> lista = new ArrayList<>();
        try {
            Query query = getManager().createQuery("SELECT p FROM Prestamo p WHERE (LOWER(p.documento.titulo) LIKE CONCAT('%', :dato, '%'))"
                    + " OR (LOWER(p.persona.nombres) LIKE CONCAT('%', :dato, '%'))"
                    + " OR (LOWER(p.persona.apellidos) LIKE CONCAT('%', :dato, '%'))");
            query.setParameter("dato", busqueda);
            lista = query.getResultList();
        } catch (Exception e) {
            System.out.println("No se ha podido listar por Busqueda:" + e);
        }
        return lista;
    }
}
