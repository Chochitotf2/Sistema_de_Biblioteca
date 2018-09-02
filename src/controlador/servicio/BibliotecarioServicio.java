package controlador.servicio;

import controlador.dao.BibliotecarioDao;
import java.util.List;
import modelo.Bibliotecario;

/**
 *
 * @author Víctor Andrés Rojas
 */
public class BibliotecarioServicio {

    private BibliotecarioDao bibliotecario = new BibliotecarioDao();

    public Bibliotecario obtenerBibliotecario() {
        return bibliotecario.getBibliotecario();
    }

    public Bibliotecario obtenerBibliotecario(Long id) {
        return bibliotecario.obtener(id);
    }

    public void fijarBibliotecario(Bibliotecario bibliotecario) {
        this.bibliotecario.setBibliotecario(bibliotecario);
    }

    public boolean guardar() {
        return bibliotecario.guardar();
    }

    public List<Bibliotecario> listar() {
        return bibliotecario.listar();
    }

    public List<Bibliotecario> listarBibliotecarioLike(String busqueda) {
        return bibliotecario.listarBibliotecarioLike(busqueda);
    }

    public Bibliotecario obtenerBibliotecarioCedula(String cedula) {
        return bibliotecario.obtenerBibliotecarioCedula(cedula);
    }
}
