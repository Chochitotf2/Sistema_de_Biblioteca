package controlador.servicio;

import controlador.dao.LibroDao;
import java.util.List;
import modelo.Libro;

/**
 *
 * @author Chochitotf2
 */
public class LibroServicio {

    private LibroDao libro = new LibroDao();

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

    public List<Libro> listarLibroLike(String busqueda) {
        return libro.listarLibroLike(busqueda);
    }
}
