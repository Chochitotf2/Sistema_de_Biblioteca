package controlador.dao;

import modelo.Tesis;

/**
 *
 * @author Chochitotf2
 */
public class TesisDao extends AdaptadorDao<Tesis> {

    private Tesis tesis;

    public TesisDao() {
        super(Tesis.class);
    }

    public Tesis getTesis() {
        if (tesis == null) {
            tesis = new Tesis();
        }
        return tesis;
    }

    public void setTesis(Tesis tesis) {
        this.tesis = tesis;
    }

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
}
