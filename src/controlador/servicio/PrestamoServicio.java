package controlador.servicio;

import controlador.dao.PrestamoDao;
import java.util.List;
import modelo.Prestamo;

/**
 *
 * @author Chochitotf2
 */
public class PrestamoServicio {

    private PrestamoDao prestamo = new PrestamoDao();

    public Prestamo obtenerPrestamo() {
        return prestamo.getPrestamo();
    }

    public Prestamo obtenerPrestamo(Long id) {
        return prestamo.obtener(id);
    }

    public void fijarPrestamo(Prestamo prestamo) {
        this.prestamo.setPrestamo(prestamo);
    }

    public boolean guardar() {
        return prestamo.guardar();
    }

    public List<Prestamo> listar() {
        return prestamo.listar();
    }

    public List<Prestamo> listarPrestamoLike(String busqueda) {
        return prestamo.listarPrestamoLike(busqueda);
    }
}
