package controlador.dao;

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
}
