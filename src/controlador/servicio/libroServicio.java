package controlador.servicio;

import controlador.dao.libroDao;
import java.util.List;
import modelo.Libro;

/**
 *
 * @author Chochitotf2
 */
public class libroServicio {

    private libroDao libro = new libroDao();

    public Libro obtenerLibro() {
        return libro.getLibro();
    }

    public Libro obtenerLibro(Long id) {
        return libro.obtener(id);
    }

    public void fijarLibro(Libro libro) {
        this.libro.setLibro(libro);
    }

    public boolean guardar() {
        return libro.guardar();
    }

    public List<Libro> listar() {
        return libro.listar();
    }
}
