package controlador.dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import modelo.Sancion;

/**
 * Clase mediante la cual se realiza las operaciones que sean requeridas (A
 * través del AdaptadorDao) entre un objeto Sancion y la Base de datos de uso.
 *
 * @author Víctor Andrés Rojas
 * @author Mario Orellana
 */
public class SancionDao extends AdaptadorDao<Sancion> {

    private Sancion sancion;

    /**
     * Constructor por defecto que asigna la clase Sancion al AdaptadorDao, para
     * asi poder realizar las operaciones que este contiene en sus métodos.
     */
    public SancionDao() {
        super(Sancion.class);
    }

    /**
     * Método que obtiene una instancia de Sancion. En caso de no existir se
     * creará un nuevo objeto.
     *
     * @return Devuelve un objeto de tipo Sancion.
     */
    public Sancion getSancion() {
        if (sancion == null) {
            sancion = new Sancion();
        }
        return sancion;
    }

    /**
     * Método que asigna un objeto de tipo Sancion al campo de esta clase que es
     * del mismo tipo.
     *
     * @param sancion Instancia Sancion a asignarse.
     */
    public void setSancion(Sancion sancion) {
        this.sancion = sancion;
    }

    /**
     * Método que guarda o modifica un objeto de tipo Sancion en la respectiva
     * Base de datos de uso.
     *
     * @return Devuelve un resultado Booleano, en caso de ser verdadero quiere
     * decir que la operación se realizó correctamente.
     */
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

    /**
     * Método que retorna una Lista de objetos de Tipo Sancion. Esta lista se
     * obtiene a través de una consulta (Query) tomando como referencia un
     * String busqueda. Esta búsqueda puede realizarse por: nombres, apellidos y
     * cédula de la Persona.
     *
     * @param busqueda Palabra clave a ser encontrada.
     * @return Devuelve una lista de objetos de tipo Sancion.
     */
    public List<Sancion> listarSancionLike(String busqueda) {
        List<Sancion> lista = new ArrayList<>();
        try {
            Query query = getManager().createQuery("SELECT p FROM Sancion p WHERE (LOWER(p.prestamo.persona.apellidos) LIKE CONCAT('%', :dato, '%'))"
                    + " OR (LOWER(p.prestamo.persona.nombres) LIKE CONCAT('%', :dato, '%'))"
                    + " OR (LOWER(p.prestamo.persona.dni) LIKE CONCAT('%', :dato, '%'))");
            query.setParameter("dato", busqueda);
            lista = query.getResultList();
        } catch (Exception e) {
            System.out.println("No se ha podido listar por Busqueda: " + e);
        }
        return lista;
    }
}
