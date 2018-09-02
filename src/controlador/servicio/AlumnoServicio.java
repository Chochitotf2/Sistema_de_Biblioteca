package controlador.servicio;

import controlador.dao.AlumnoDao;
import java.util.List;
import modelo.Alumno;

/**
 * Clase que mediante una instancia de AlumnoDao, obtiene todos los métodos de
 * la misma y junto a ello los del AdaptadorDao, de esta manera una instancia de
 * AlumnoServicio bastará para realizar todas las operaciones entre un objeto
 * Alumno y la Base de datos de uso.
 *
 * @author Víctor Andrés Rojas
 * @author Mario Orellana
 */
public class AlumnoServicio {

    private AlumnoDao alumno = new AlumnoDao();

    /**
     * Método que obtiene una instancia de Alumno. En caso de no existir se
     * creará un nuevo objeto.
     *
     * @return Devuelve un objeto de tipo Alumno.
     */
    public Alumno obtenerAlumno() {
        return alumno.getAlumno();
    }

    /**
     * Método que obtiene una instancia de Alumno mediante un identificador o
     * clave primaria.
     *
     * @param id Valor del identificador o clave primaria.
     * @return Devuelve un objeto de tipo Alumno.
     */
    public Alumno obtenerAlumno(Long id) {
        return alumno.obtener(id);
    }

    /**
     * Método que asigna un objeto de tipo Alumno al campo de la clase AlumnoDao
     * que es del mismo tipo.
     *
     * @param alumno Instancia Alumno a asignarse.
     */
    public void fijarAlumno(Alumno alumno) {
        this.alumno.setAlumno(alumno);
    }

    /**
     * Método que guarda o modifica un objeto de tipo Alumno en la respectiva
     * Base de datos de uso.
     *
     * @return Devuelve un resultado Booleano, en caso de ser verdadero quiere
     * decir que la operación se realizó correctamente.
     */
    public boolean guardar() {
        return alumno.guardar();
    }

    /**
     * Método que realiza una consulta en la Base de datos y devuelve un listado
     * de objetos Alumno.
     *
     * @return Devuelve una lista de Tipo Alumno.
     */
    public List<Alumno> listar() {
        return alumno.listar();
    }

    /**
     * Método que obtiene un objeto de tipo Alumno que contenga en su atributo
     * 'cedula' el mismo valor que se le indica en el parámetro del método.
     *
     * @param cedula Valor a ser encontrado dentro de la consulta.
     * @return Devuelve un objeto Alumno en caso de ser encontrado.
     */
    public Alumno obtenerAlumnoCedula(String cedula) {
        return alumno.obtenerAlumnoCedula(cedula);
    }
}
