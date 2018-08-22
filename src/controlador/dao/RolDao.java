package controlador.dao;

import javax.persistence.Query;
import modelo.Rol;

/**
 *
 * @author Víctor Andrés Rojas
 */
public class RolDao extends AdaptadorDao<Rol> {

    private Rol rol;

    public RolDao() {
        super(Rol.class);
    }

    public Rol getRol() {
        if (rol == null) {
            rol = new Rol();
        }
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

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

    public Rol buscarRol(String rol) {
        Rol aux = null;
        try {
            Query query = getManager().createQuery("SELECT r FROM Rol r WHERE r.nombre = :nombres");
            query.setParameter("nombres", rol);
            aux = (Rol) query.getSingleResult();
        } catch (Exception e) {
            System.out.println("No se ha podido encontrar el Rol por nombre: " + e);
        }
        return aux;
    }
}
