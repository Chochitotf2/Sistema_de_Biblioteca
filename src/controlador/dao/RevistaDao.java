package controlador.dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import modelo.Revista;

/**
 * Clase mediante la cual se realiza las operaciones que sean requeridas (A
 * través del AdaptadorDao) entre un objeto Revista y la Base de datos de uso.
 *
 * @author Víctor Andrés Rojas
 * @author Mario Orellana
 */
public class RevistaDao extends AdaptadorDao<Revista> {

    private Revista revista;

    /**
     * Constructor por defecto que asigna la clase Revista al AdaptadorDao, para
     * asi poder realizar las operaciones que este contiene en sus métodos.
     */
    public RevistaDao() {
        super(Revista.class);
    }

    /**
     * Método que obtiene una instancia de Revista. En caso de no existir se
     * creará un nuevo objeto.
     *
     * @return Devuelve un objeto de tipo Revista.
     */
    public Revista getRevista() {
        if (revista == null) {
            revista = new Revista();
        }
        return revista;
    }

    /**
     * Método que asigna un objeto de tipo Revista al campo de esta clase que es
     * del mismo tipo.
     *
     * @param revista Instancia Revista a asignarse.
     */
    public void setRevista(Revista revista) {
        this.revista = revista;
    }

    /**
     * Método que guarda o modifica un objeto de tipo Revista en la respectiva
     * Base de datos de uso.
     *
     * @return Devuelve un resultado Booleano, en caso de ser verdadero quiere
     * decir que la operación se realizó correctamente.
     */
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

    /**
     * Método que retorna una Lista de objetos de Tipo Revista. Esta lista se
     * obtiene a través de una consulta (Query) tomando como referencia un
     * String busqueda. Esta búsqueda puede realizarse por: título, código,
     * issn, editorial.
     *
     * @param busqueda Palabra clave a ser encontrada.
     * @return Devuelve una lista de objetos de tipo Revista.
     */
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
            System.out.println("No se ha podido listar por Busqueda: " + e);
        }
        return lista;
    }
}
