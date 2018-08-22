package controlador.dao;

import modelo.Revista;

/**
 *
 * @author Chochitotf2
 */
public class RevistaDao extends AdaptadorDao<Revista> {

    private Revista revista;

    public RevistaDao() {
        super(Revista.class);
    }

    public Revista getRevista() {
        if (revista == null) {
            revista = new Revista();
        }
        return revista;
    }

    public void setRevista(Revista revista) {
        this.revista = revista;
    }

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
}
