package controlador.dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import modelo.Persona;

/**
 *
 * @author Víctor Andrés Rojas
 */
public class PersonaDao extends AdaptadorDao<Persona> {

    private Persona persona;

    public PersonaDao() {
        super(Persona.class);
    }

    public Persona getPersona() {
        if (persona == null) {
            persona = new Persona();
        }
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public boolean guardar() {
        boolean estado = false;
        try {
            getManager().getTransaction().begin();
            if (persona.getId() != null) {
                modificar(persona);
            } else {
                guardar(persona);
            }
            getManager().getTransaction().commit();
            estado = true;
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error al Guardar o Modificar Persona: " + e);
        }
        return estado;
    }
       public List<Persona> listarPersonaLike(String busqueda) {
        List<Persona> lista = new ArrayList<>();
        try {
            Query query = getManager().createQuery("SELECT p FROM Persona p WHERE (LOWER(p.nombres) LIKE CONCAT('%', :dato, '%'))"
                    + " OR (LOWER(p.dni) LIKE CONCAT('%', :dato, '%'))");
            query.setParameter("dato", busqueda);
            lista = query.getResultList();
        } catch (Exception e) {
            System.out.println("No se ha podido listar por Busqueda:" + e);
        }
        return lista;
    }

    public List<Persona> listarPersonaTipoLike(String tipo, String busqueda) {
        List<Persona> lista = new ArrayList<>();
        try {
            Query query = getManager()
                    .createQuery("SELECT p FROM Persona p WHERE p.rol = :tipo"
                            + " AND ((LOWER(p.nombres) LIKE CONCAT('%', :dato, '%'))"
                            + " OR (LOWER(p.dni) LIKE CONCAT('%', :dato, '%')))");
            query.setParameter("tipo", tipo);
            query.setParameter("dato", busqueda);
            lista = query.getResultList();
        } catch (Exception e) {
            System.out.println("No se ha podido listar por Tipo-Busqueda: " + e);
        }
        return lista;
    }
}
