package controlador.servicio;

import controlador.dao.SansionDao;
import java.util.List;
import modelo.Sansion;

/**
 *
 * @author Chochitotf2
 */
public class SansionServicio {

    private SansionDao sansion = new SansionDao();

    public Sansion obtenerSansion() {
        return sansion.getSansion();
    }

    public Sansion obtenerSansion(Long id) {
        return sansion.obtener(id);
    }

    public void fijarSansion(Sansion sansion) {
        this.sansion.setSansion(sansion);
    }

    public boolean guardar() {
        return sansion.guardar();
    }

    public List<Sansion> listar() {
        return sansion.listar();
    }
}
