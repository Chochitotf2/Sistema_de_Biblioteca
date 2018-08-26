package controlador.dao;

import javax.persistence.Query;
import modelo.Alumno;

/**
 *
 * @author Víctor Andrés Rojas
 */
public class AlumnoDao extends AdaptadorDao<Alumno> {

    private Alumno alumno;

    public AlumnoDao() {
        super(Alumno.class);
    }

    public Alumno getAlumno() {
        if (alumno == null) {
            alumno = new Alumno();
        }
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

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

    public Alumno obtenerAlumnoCedula(String cedula) {
        Alumno alumnoAux = null;
        try {
            Query q = getManager().createQuery("SELECT p FROM Alumno p where p.dni = :dato");
            q.setParameter("dato", cedula);
            alumnoAux = (Alumno) q.getSingleResult();
        } catch (Exception e) {
        }
        return alumnoAux;
    }
}
