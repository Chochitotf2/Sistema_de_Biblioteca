package controlador.servicio;

import controlador.dao.RevistaDao;
import java.util.List;
import modelo.Revista;

/**
 *
 * @author Chochitotf2
 */
public class RevistaServicio {

    private RevistaDao revista = new RevistaDao();

    public Revista obtenerRevista() {
        return revista.getRevista();
    }

    public Revista obtenerRevista(Long id) {
        return revista.obtener(id);
    }

    public void fijarRevista(Revista revista) {
        this.revista.setRevista(revista);
    }

    public boolean guardar() {
        return revista.guardar();
    }

    public List<Revista> listar() {
        return revista.listar();
    }
}
