package controlador.dao;

import modelo.Libro;

/**
 *
 * @author Chochitotf2
 */
public class libroDao extends AdaptadorDao<Libro> {

    private Libro libro;

    public libroDao() {
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
}
