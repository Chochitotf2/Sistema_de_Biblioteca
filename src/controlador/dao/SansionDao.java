package controlador.dao;

import modelo.Sansion;

/**
 *
 * @author Chochitotf2
 */
public class SansionDao extends AdaptadorDao<Sansion> {

    private Sansion sansion;

    public SansionDao() {
        super(Sansion.class);
    }

    public Sansion getSansion() {
        if (sansion == null) {
            sansion = new Sansion();
        }
        return sansion;
    }

    public void setSansion(Sansion sansion) {
        this.sansion = sansion;
    }

    public boolean guardar() {
        boolean estado = false;
        try {
            getManager().getTransaction().begin();
            if (sansion.getId() != null) {
                modificar(sansion);
            } else {
                guardar(sansion);
            }
            getManager().getTransaction().commit();
            estado = true;
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error al Guardar o Modificar Sansion: " + e);
        }
        return estado;
    }
}
