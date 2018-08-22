package controlador.servicio;

import controlador.dao.AlumnoDao;
import java.util.List;
import modelo.Alumno;

/**
 *
 * @author Víctor Andrés Rojas
 */
public class AlumnoServicio {

    private AlumnoDao alumno = new AlumnoDao();

    public Alumno obtenerAlumno() {
        return alumno.getAlumno();
    }

    public Alumno obtenerAlumno(Long id) {
        return alumno.obtener(id);
    }

    public void fijarAlumno(Alumno alumno) {
        this.alumno.setAlumno(alumno);
    }

    public boolean guardar() {
        return alumno.guardar();
    }

    public List<Alumno> listar() {
        return alumno.listar();
    }
}
