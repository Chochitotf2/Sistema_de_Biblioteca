package controlador.servicio;

import controlador.dao.RolDao;
import java.util.List;
import modelo.Rol;

/**
 *
 * @author Víctor Andrés Rojas
 */
public class RolServicio {

    private RolDao rol = new RolDao();

    public Rol obtenerRol() {
        return rol.getRol();
    }

    public Rol obtenerRol(Long id) {
        return rol.obtener(id);
    }

    public void fijarRol(Rol rol) {
        this.rol.setRol(rol);
    }

    public boolean guardar() {
        return rol.guardar();
    }

    public List<Rol> listar() {
        return rol.listar();
    }

    public Rol buscarRol(String nombre) {
        return rol.buscarRol(nombre);
    }

    public void crearRoles() {
        if (listar().isEmpty()) {
            obtenerRol().setNombre("Administrador");
            guardar();
            fijarRol(null);
            obtenerRol().setNombre("Bibliotecario");
            guardar();
            fijarRol(null);
            obtenerRol().setNombre("Profesor");
            guardar();
            fijarRol(null);
            obtenerRol().setNombre("Alumno");
            guardar();
            fijarRol(null);
        }
    }
}
