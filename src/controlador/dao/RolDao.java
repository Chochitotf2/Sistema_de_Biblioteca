package controlador.dao;

import javax.persistence.Query;
import modelo.Rol;

/**
 * Clase mediante la cual se realiza las operaciones que sean requeridas (A
 * través del AdaptadorDao) entre un objeto Rol y la Base de datos de uso.
 *
 * @author Víctor Andrés Rojas
 * @author Mario Orellana
 */
public class RolDao extends AdaptadorDao<Rol> {

    private Rol rol;

    /**
     * Constructor por defecto que asigna la clase Rol al AdaptadorDao, para asi
     * poder realizar las operaciones que este contiene en sus métodos.
     */
    public RolDao() {
        super(Rol.class);
    }

    /**
     * Método que obtiene una instancia de Rol. En caso de no existir se creará
     * un nuevo objeto.
     *
     * @return Devuelve un objeto de tipo Rol.
     */
    public Rol getRol() {
        if (rol == null) {
            rol = new Rol();
        }
        return rol;
    }

    /**
     * Método que asigna un objeto de tipo Rol al campo de esta clase que es del
     * mismo tipo.
     *
     * @param rol Instancia Rol a asignarse.
     */
    public void setRol(Rol rol) {
        this.rol = rol;
    }

    /**
     * Método que guarda o modifica un objeto de tipo Rol en la respectiva Base
     * de datos de uso.
     *
     * @return Devuelve un resultado Booleano, en caso de ser verdadero quiere
     * decir que la operación se realizó correctamente.
     */
    public boolean guardar() {
        boolean estado = false;
        try {
            getManager().getTransaction().begin();
            if (rol.getId() != null) {
                modificar(rol);
            } else {
                guardar(rol);
            }
            getManager().getTransaction().commit();
            estado = true;
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error al Guardar o Modificar Rol: " + e);
        }
        return estado;
    }

    /**
     * Método que obtiene un objeto de tipo Rol que contenga en su atributo
     * 'nombre' el mismo valor que se le indica en el parámetro del método.
     *
     * @param nombre Valor a ser encontrado dentro de la consulta.
     * @return Devuelve un objeto Rol en caso de ser encontrado.
     */
    public Rol buscarRol(String nombre) {
        Rol aux = null;
        try {
            Query query = getManager().createQuery("SELECT r FROM Rol r WHERE r.nombre = :nombres");
            query.setParameter("nombres", nombre);
            aux = (Rol) query.getSingleResult();
        } catch (Exception e) {
            System.out.println("No se ha podido encontrar el Rol por nombre: " + e);
        }
        return aux;
    }
}
