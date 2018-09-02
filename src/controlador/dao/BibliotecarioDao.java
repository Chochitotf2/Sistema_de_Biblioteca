package controlador.dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import modelo.Bibliotecario;

/**
 * Clase mediante la cual se realiza las operaciones que sean requeridas (A
 * través del AdaptadorDao) entre un objeto Bibliotecario y la Base de datos de
 * uso.
 *
 * @author Víctor Andrés Rojas
 * @author Mario Orellana
 */
public class BibliotecarioDao extends AdaptadorDao<Bibliotecario> {

    private Bibliotecario bibliotecario;

    /**
     * Constructor por defecto que asigna la clase Bibliotecario al
     * AdaptadorDao, para asi poder realizar las operaciones que este contiene
     * en sus métodos.
     */
    public BibliotecarioDao() {
        super(Bibliotecario.class);
    }

    /**
     * Método que obtiene una instancia de Bibliotecario. En caso de no existir
     * se creará un nuevo objeto.
     *
     * @return Devuelve un objeto de tipo Bibliotecario.
     */
    public Bibliotecario getBibliotecario() {
        if (bibliotecario == null) {
            bibliotecario = new Bibliotecario();
        }
        return bibliotecario;
    }

    /**
     * Método que asigna un objeto de tipo Bibliotecario al campo de esta clase
     * que es del mismo tipo.
     *
     * @param bibliotecario Instancia Bibliotecario a asignarse.
     */
    public void setBibliotecario(Bibliotecario bibliotecario) {
        this.bibliotecario = bibliotecario;
    }

    /**
     * Método que guarda o modifica un objeto de tipo Bibliotecario en la
     * respectiva Base de datos de uso.
     *
     * @return Devuelve un resultado Booleano, en caso de ser verdadero quiere
     * decir que la operación se realizó correctamente.
     */
    public boolean guardar() {
        boolean estado = false;
        try {
            getManager().getTransaction().begin();
            if (bibliotecario.getId() != null) {
                modificar(bibliotecario);
            } else {
                guardar(bibliotecario);
            }
            getManager().getTransaction().commit();
            estado = true;
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error al Guardar o Modificar Bibliotecario: " + e);
        }
        return estado;
    }

    /**
     * Método que retorna una Lista de objetos de Tipo Bibliotecario. Esta lista
     * se obtiene a través de una consulta (Query) tomando como referencia un
     * String busqueda.
     *
     * @param busqueda Palabra clave a ser encontrada.
     * @return Devuelve una lista de objetos de tipo Bibliotecario.
     */
    public List<Bibliotecario> listarBibliotecarioLike(String busqueda) {
        List<Bibliotecario> lista = new ArrayList<>();
        try {
            Query query = getManager().createQuery("SELECT p FROM Bibliotecario p WHERE (LOWER(p.apellidos) LIKE CONCAT('%', :dato, '%'))"
                    + " OR (LOWER(p.nombres) LIKE CONCAT('%', :dato, '%')) OR (LOWER(p.dni) LIKE CONCAT('%', :dato, '%'))"
                    + " OR (LOWER(p.correo) LIKE CONCAT('%', :dato, '%'))");
            query.setParameter("dato", busqueda);
            lista = query.getResultList();
        } catch (Exception e) {
            System.out.println("No se ha podido listar por Busqueda: " + e);
        }
        return lista;
    }

    /**
     * Método que obtiene un Bibliotecario que contenga en su atributo 'cedula'
     * el mismo valor que se le indica en el parámetro del método.
     *
     * @param cedula Valor a ser encontrado dentro de la consulta.
     * @return Devuelve un objeto Bibliotecario en caso de ser encontrado.
     */
    public Bibliotecario obtenerBibliotecarioCedula(String cedula) {
        Bibliotecario bibliotecarioAux = null;
        try {
            Query query = getManager().createQuery("SELECT p FROM Bibliotecario p where p.dni = :dato");
            query.setParameter("dato", cedula);
            bibliotecarioAux = (Bibliotecario) query.getSingleResult();
        } catch (Exception e) {
        }
        return bibliotecarioAux;
    }
}
