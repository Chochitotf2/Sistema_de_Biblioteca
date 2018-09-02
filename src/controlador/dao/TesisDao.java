package controlador.dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import modelo.Tesis;

/**
 * Clase mediante la cual se realiza las operaciones que sean requeridas (A
 * través del AdaptadorDao) entre un objeto Tesis y la Base de datos de uso.
 *
 * @author Víctor Andrés Rojas
 * @author Mario Orellana
 */
public class TesisDao extends AdaptadorDao<Tesis> {

    private Tesis tesis;

    /**
     * Constructor por defecto que asigna la clase Tesis al AdaptadorDao, para
     * asi poder realizar las operaciones que este contiene en sus métodos.
     */
    public TesisDao() {
        super(Tesis.class);
    }

    /**
     * Método que obtiene una instancia de Tesis. En caso de no existir se
     * creará un nuevo objeto.
     *
     * @return Devuelve un objeto de tipo Tesis.
     */
    public Tesis getTesis() {
        if (tesis == null) {
            tesis = new Tesis();
        }
        return tesis;
    }

    /**
     * Método que asigna un objeto de tipo Tesis al campo de esta clase que es
     * del mismo tipo.
     *
     * @param tesis Instancia Tesis a asignarse.
     */
    public void setTesis(Tesis tesis) {
        this.tesis = tesis;
    }

    /**
     * Método que guarda o modifica un objeto de tipo Tesis en la respectiva
     * Base de datos de uso.
     *
     * @return Devuelve un resultado Booleano, en caso de ser verdadero quiere
     * decir que la operación se realizó correctamente.
     */
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

    /**
     * Método que retorna una Lista de objetos de Tipo Tesis. Esta lista se
     * obtiene a través de una consulta (Query) tomando como referencia un
     * String busqueda. Esta búsqueda puede realizarse por: título, código,
     * autor(es), asesor(es), facultad.
     *
     * @param busqueda Palabra clave a ser encontrada.
     * @return Devuelve una lista de objetos de tipo Tesis.
     */
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
            System.out.println("No se ha podido listar por Busqueda: " + e);
        }
        return lista;
    }
}
