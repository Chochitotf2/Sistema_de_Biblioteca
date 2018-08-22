package controlador.dao;

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
}
