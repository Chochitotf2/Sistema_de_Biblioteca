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

    public List<Persona> listarSinAdministradorUsuarios() {
        List<Persona> lista = new ArrayList<>();
        try {
            Query q = getManager().createQuery("SELECT p FROM Persona p where p.rol.nombre != :admin and p.rol.nombre != :biblio");
            q.setParameter("admin", "Administrador");
            q.setParameter("biblio", "Bibliotecario");
            lista = q.getResultList();
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error al Listar Usuarios: " + e);
        }
        return lista;
    }

    public List<Persona> listarUsuariosTipo(String tipo) {
        List<Persona> lista = new ArrayList<>();
        try {
            Query q = getManager().createQuery("SELECT p FROM Persona p where p.rol.nombre != :admin and p.rol.nombre != :biblio"
                    + " and p.rol.nombre = :tipo");
            q.setParameter("admin", "Administrador");
            q.setParameter("biblio", "Bibliotecario");
            q.setParameter("tipo", tipo);
            lista = q.getResultList();
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error al Listar Usuarios por tipo: " + e);
        }
        return lista;
    }

    public List<Persona> listarSinAdministradorUsuariosLike(String busqueda) {
        List<Persona> lista = new ArrayList<>();
        try {
            Query query = getManager().createQuery("SELECT p FROM Persona p where p.rol.nombre != :admin and p.rol.nombre != :biblio"
                    + " AND ((LOWER(p.apellidos) LIKE CONCAT('%', :dato, '%'))"
                    + " OR (LOWER(p.nombres) LIKE CONCAT('%', :dato, '%')) OR (LOWER(p.dni) LIKE CONCAT('%', :dato, '%'))"
                    + " OR (LOWER(p.correo) LIKE CONCAT('%', :dato, '%')))");
            query.setParameter("admin", "Administrador");
            query.setParameter("biblio", "Bibliotecario");
            query.setParameter("dato", busqueda);
            lista = query.getResultList();
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error al buscar Usuarios: " + e);
        }
        return lista;
    }

    public List<Persona> listarUsuariosTipoLike(String tipo, String busqueda) {
        List<Persona> lista = new ArrayList<>();
        try {
            Query query = getManager().createQuery("SELECT p FROM Persona p where p.rol.nombre != :admin and p.rol.nombre != :biblio"
                    + " and p.rol.nombre = :tipo"
                    + " AND ((LOWER(p.apellidos) LIKE CONCAT('%', :dato, '%'))"
                    + " OR (LOWER(p.nombres) LIKE CONCAT('%', :dato, '%')) OR (LOWER(p.dni) LIKE CONCAT('%', :dato, '%'))"
                    + " OR (LOWER(p.correo) LIKE CONCAT('%', :dato, '%')))");
            query.setParameter("admin", "Administrador");
            query.setParameter("biblio", "Bibliotecario");
            query.setParameter("tipo", tipo);
            query.setParameter("dato", busqueda);
            lista = query.getResultList();
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error al buscar Usuarios por tipo: " + e);
        }
        return lista;
    }

    public Persona obtenerPersonaCedula(String cedula) {
        Persona personaAux = null;
        try {
            Query query = getManager().createQuery("SELECT p FROM Persona p where p.dni = :dato");
            query.setParameter("dato", cedula);
            personaAux = (Persona) query.getSingleResult();
        } catch (Exception e) {
        }
        return personaAux;
    }
}
