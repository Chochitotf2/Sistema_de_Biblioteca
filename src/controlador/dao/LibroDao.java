package controlador.dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import modelo.Libro;

/**
 *
 * @author Chochitotf2
 */
public class LibroDao extends AdaptadorDao<Libro> {

    private Libro libro;

    public LibroDao() {
        super(Libro.class);
    }

    public Libro getLibro() {
        if (libro == null) {
            libro = new Libro();
        }
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public boolean guardar() {
        boolean estado = false;
        try {
            getManager().getTransaction().begin();
            if (libro.getId() != null) {
                modificar(libro);
            } else {
                guardar(libro);
            }
            getManager().getTransaction().commit();
            estado = true;
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error al Guardar o Modificar Libro: " + e);
        }
        return estado;
    }

    public List<Libro> listarLibroLike(String busqueda) {
        List<Libro> lista = new ArrayList<>();
        try {
            Query query = getManager().createQuery("SELECT p FROM Libro p WHERE (LOWER(p.titulo) LIKE CONCAT('%', :dato, '%'))"
                    + " OR (LOWER(p.codigo) LIKE CONCAT('%', :dato, '%'))"
                    + " OR (LOWER(p.autores) LIKE CONCAT('%', :dato, '%'))"
                    + " OR (LOWER(p.editorial) LIKE CONCAT('%', :dato, '%'))"
                    + " OR (LOWER(p.isbn) LIKE CONCAT('%', :dato, '%'))");
            query.setParameter("dato", busqueda);
            lista = query.getResultList();
        } catch (Exception e) {
            System.out.println("No se ha podido listar por Busqueda:" + e);
        }
        return lista;
    }
}
