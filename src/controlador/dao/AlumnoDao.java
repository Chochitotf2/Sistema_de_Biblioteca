package controlador.dao;

import javax.persistence.Query;
import modelo.Alumno;

/**
 * Clase mediante la cual se realiza las operaciones que sean requeridas (A
 * través del AdaptadorDao) entre un objeto Alumno y la Base de datos de uso.
 *
 * @author Víctor Andrés Rojas
 * @author Mario Orellana
 */
public class AlumnoDao extends AdaptadorDao<Alumno> {

    private Alumno alumno;

    /**
     * Constructor por defecto que asigna la clase Alumno al AdaptadorDao, para
     * asi poder realizar las operaciones que este contiene en sus métodos.
     */
    public AlumnoDao() {
        super(Alumno.class);
    }

    /**
     * Método que obtiene una instancia de Alumno. En caso de no existir se
     * creará un nuevo objeto.
     *
     * @return Devuelve un objeto de tipo Alumno.
     */
    public Alumno getAlumno() {
        if (alumno == null) {
            alumno = new Alumno();
        }
        return alumno;
    }

    /**
     * Método que asigna un objeto de tipo Alumno al campo de esta clase que es
     * del mismo tipo.
     *
     * @param alumno Instancia Alumno a asignarse.
     */
    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    /**
     * Método que guarda o modifica un objeto de tipo Alumno en la respectiva
     * Base de datos de uso.
     *
     * @return Devuelve un resultado Booleano, en caso de ser verdadero quiere
     * decir que la operación se realizó correctamente.
     */
    public boolean guardar() {
        boolean estado = false;
        try {
            getManager().getTransaction().begin();
            if (alumno.getId() != null) {
                modificar(alumno);
            } else {
                guardar(alumno);
            }
            getManager().getTransaction().commit();
            estado = true;
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error al Guardar o Modificar Alumno: " + e);
        }
        return estado;
    }

    /**
     * Método que obtiene un Alumno que contenga en su atributo 'cedula' el
     * mismo valor que se le indica en el parámetro del método.
     *
     * @param cedula Valor a ser encontrado dentro de la consulta.
     * @return Devuelve un objeto Alumno en caso de ser encontrado.
     */
    public Alumno obtenerAlumnoCedula(String cedula) {
        Alumno alumnoAux = null;
        try {
            Query query = getManager().createQuery("SELECT p FROM Alumno p where p.dni = :dato");
            query.setParameter("dato", cedula);
            alumnoAux = (Alumno) query.getSingleResult();
        } catch (Exception e) {
        }
        return alumnoAux;
    }
}
