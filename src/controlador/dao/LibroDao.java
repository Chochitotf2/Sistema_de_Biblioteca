package controlador.dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import modelo.Libro;

/**
 * Clase mediante la cual se realiza las operaciones que sean requeridas (A
 * través del AdaptadorDao) entre un objeto Libro y la Base de datos de uso.
 *
 * @author Víctor Andrés Rojas
 * @author Mario Orellana
 */
public class LibroDao extends AdaptadorDao<Libro> {

    private Libro libro;

    /**
     * Constructor por defecto que asigna la clase Libro al AdaptadorDao, para
     * asi poder realizar las operaciones que este contiene en sus métodos.
     */
    public LibroDao() {
        super(Libro.class);
    }

    /**
     * Método que obtiene una instancia de Libro. En caso de no existir se
     * creará un nuevo objeto.
     *
     * @return Devuelve un objeto de tipo Libro.
     */
    public Libro getLibro() {
        if (libro == null) {
            libro = new Libro();
        }
        return libro;
    }

    /**
     * Método que asigna un objeto de tipo Libro al campo de esta clase que es
     * del mismo tipo.
     *
     * @param libro Instancia Libro a asignarse.
     */
    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    /**
     * Método que guarda o modifica un objeto de tipo Libro en la respectiva
     * Base de datos de uso.
     *
     * @return Devuelve un resultado Booleano, en caso de ser verdadero quiere
     * decir que la operación se realizó correctamente.
     */
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

    /**
     * Método que retorna una Lista de objetos de Tipo Libro. Esta lista se
     * obtiene a través de una consulta (Query) tomando como referencia un
     * String busqueda. Esta búsqueda puede realizarse por: título, código,
     * autor(es), editorial, isbn.
     *
     * @param busqueda Palabra clave a ser encontrada.
     * @return Devuelve una lista de objetos de tipo Libro.
     */
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
            System.out.println("No se ha podido listar por Busqueda: " + e);
        }
        return lista;
    }
}
