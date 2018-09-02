package controlador.dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import modelo.Prestamo;

/**
 * Clase mediante la cual se realiza las operaciones que sean requeridas (A
 * través del AdaptadorDao) entre un objeto Prestamo y la Base de datos de uso.
 *
 * @author Víctor Andrés Rojas
 * @author Mario Orellana
 */
public class PrestamoDao extends AdaptadorDao<Prestamo> {

    private Prestamo prestamo;

    /**
     * Constructor por defecto que asigna la clase Prestamo al AdaptadorDao,
     * para asi poder realizar las operaciones que este contiene en sus métodos.
     */
    public PrestamoDao() {
        super(Prestamo.class);
    }

    /**
     * Método que obtiene una instancia de Prestamo. En caso de no existir se
     * creará un nuevo objeto.
     *
     * @return Devuelve un objeto de tipo Prestamo.
     */
    public Prestamo getPrestamo() {
        if (prestamo == null) {
            prestamo = new Prestamo();
        }
        return prestamo;
    }

    /**
     * Método que asigna un objeto de tipo Prestamo al campo de esta clase que
     * es del mismo tipo.
     *
     * @param prestamo Instancia Prestamo a asignarse.
     */
    public void setPrestamo(Prestamo prestamo) {
        this.prestamo = prestamo;
    }

    /**
     * Método que guarda o modifica un objeto de tipo Prestamo en la respectiva
     * Base de datos de uso.
     *
     * @return Devuelve un resultado Booleano, en caso de ser verdadero quiere
     * decir que la operación se realizó correctamente.
     */
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

    /**
     * Método que retorna una Lista de objetos de Tipo Prestamo. Esta lista se
     * obtiene a través de una consulta (Query) tomando como referencia un
     * String busqueda. Esta búsqueda puede realizarse por: título de Documento,
     * nombres y apellidos de Persona.
     *
     * @param busqueda Palabra clave a ser encontrada.
     * @return Devuelve una lista de objetos de tipo Prestamo.
     */
    public List<Prestamo> listarPrestamoLike(String busqueda) {
        List<Prestamo> lista = new ArrayList<>();
        try {
            Query query = getManager().createQuery("SELECT p FROM Prestamo p WHERE (LOWER(p.documento.titulo) LIKE CONCAT('%', :dato, '%'))"
                    + " OR (LOWER(p.persona.nombres) LIKE CONCAT('%', :dato, '%'))"
                    + " OR (LOWER(p.persona.apellidos) LIKE CONCAT('%', :dato, '%'))");
            query.setParameter("dato", busqueda);
            lista = query.getResultList();
        } catch (Exception e) {
            System.out.println("No se ha podido listar por Busqueda: " + e);
        }
        return lista;
    }
}
