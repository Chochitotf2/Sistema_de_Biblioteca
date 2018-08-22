package controlador.servicio;

import controlador.dao.TesisDao;
import java.util.List;
import modelo.Tesis;

/**
 *
 * @author Chochitotf2
 */
public class TesisServicio {

    private TesisDao tesis = new TesisDao();

    public Tesis obtenerTesis() {
        return tesis.getTesis();
    }

    public Tesis obtenerTesis(Long id) {
        return tesis.obtener(id);
    }

    public void fijarTesis(Tesis tesis) {
        this.tesis.setTesis(tesis);
    }

    public boolean guardar() {
        return tesis.guardar();
    }

    public List<Tesis> listar() {
        return tesis.listar();
    }
}
