package controlador.servicio;

import controlador.dao.SancionDao;
import java.util.List;
import modelo.Sancion;

/**
 *
 * @author Chochitotf2
 */
public class SancionServicio {

    private SancionDao sancion = new SancionDao();

    public Sancion obtenerSancion() {
        return sancion.getSancion();
    }

    public Sancion obtenerSancion(Long id) {
        return sancion.obtener(id);
    }

    public void fijarSancion(Sancion sancion) {
        this.sancion.setSancion(sancion);
    }

    public boolean guardar() {
        return sancion.guardar();
    }

    public List<Sancion> listar() {
        return sancion.listar();
    }

    public List<Sancion> listarSancionLike(String busqueda) {
        return sancion.listarSancionLike(busqueda);
    }
}
