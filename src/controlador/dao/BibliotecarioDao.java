package controlador.dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import modelo.Bibliotecario;
import modelo.Persona;

/**
 *
 * @author Víctor Andrés Rojas
 */
public class BibliotecarioDao extends AdaptadorDao<Bibliotecario> {

    private Bibliotecario bibliotecario;

    public BibliotecarioDao() {
        super(Bibliotecario.class);
    }

    public Bibliotecario getBibliotecario() {
        if (bibliotecario == null) {
            bibliotecario = new Bibliotecario();
        }
        return bibliotecario;
    }

    public void setBibliotecario(Bibliotecario bibliotecario) {
        this.bibliotecario = bibliotecario;
    }

    public boolean guardar() {
        boolean estado = false;
        try {
            getManager().getTransaction().begin();
            if (bibliotecario.getId() != null) {
                modificar(bibliotecario);
            } else {
                guardar(bibliotecario);
            }
            getManager().getTransaction().commit();
            estado = true;
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error al Guardar o Modificar Bibliotecario: " + e);
        }
        return estado;
    }

    public List<Bibliotecario> listarBibliotecarioLike(String busqueda) {
        List<Bibliotecario> lista = new ArrayList<>();
        try {
            Query query = getManager().createQuery("SELECT p FROM Bibliotecario p WHERE (LOWER(p.apellidos) LIKE CONCAT('%', :dato, '%'))"
                    + " OR (LOWER(p.nombres) LIKE CONCAT('%', :dato, '%')) OR (LOWER(p.dni) LIKE CONCAT('%', :dato, '%'))"
                    + " OR (LOWER(p.correo) LIKE CONCAT('%', :dato, '%'))");
            query.setParameter("dato", busqueda);
            lista = query.getResultList();
        } catch (Exception e) {
            System.out.println("No se ha podido listar por Busqueda: " + e);
        }
        return lista;
    }

    public Bibliotecario obtenerBibliotecarioCedula(String cedula) {
        Bibliotecario alumnoAux = null;
        try {
            Query q = getManager().createQuery("SELECT p FROM Bibliotecario p where p.dni = :dato");
            q.setParameter("dato", cedula);
            alumnoAux = (Bibliotecario) q.getSingleResult();
        } catch (Exception e) {
        }
        return alumnoAux;
    }
}
