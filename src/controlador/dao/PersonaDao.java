package controlador.dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import modelo.Persona;

/**
 * Clase mediante la cual se realiza las operaciones que sean requeridas (A
 * través del AdaptadorDao) entre un objeto Persona y la Base de datos de uso.
 *
 * @author Víctor Andrés Rojas
 * @author Mario Orellana
 */
public class PersonaDao extends AdaptadorDao<Persona> {

    private Persona persona;

    /**
     * Constructor por defecto que asigna la clase Persona al AdaptadorDao, para
     * asi poder realizar las operaciones que este contiene en sus métodos.
     */
    public PersonaDao() {
        super(Persona.class);
    }

    /**
     * Método que obtiene una instancia de Persona. En caso de no existir se
     * creará un nuevo objeto.
     *
     * @return Devuelve un objeto de tipo Persona.
     */
    public Persona getPersona() {
        if (persona == null) {
            persona = new Persona();
        }
        return persona;
    }

    /**
     * Método que asigna un objeto de tipo Persona al campo de esta clase que es
     * del mismo tipo.
     *
     * @param persona Instancia Persona a asignarse.
     */
    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    /**
     * Método que guarda o modifica un objeto de tipo Persona en la respectiva
     * Base de datos de uso.
     *
     * @return Devuelve un resultado Booleano, en caso de ser verdadero quiere
     * decir que la operación se realizó correctamente.
     */
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

    /**
     * Método que retorna una Lista de objetos de Tipo Persona. Esta lista se
     * obtiene a través de una consulta (Query) que toma en cuenta solamente a
     * las Personas que cumplen el rol de Alumno y Profesor.
     *
     * @return Devuelve una lista de objetos de tipo Persona.
     */
    public List<Persona> listarSinAdministradorUsuarios() {
        List<Persona> lista = new ArrayList<>();
        try {
            Query query = getManager().createQuery("SELECT p FROM Persona p where p.rol.nombre != :admin and p.rol.nombre != :biblio");
            query.setParameter("admin", "Administrador");
            query.setParameter("biblio", "Bibliotecario");
            lista = query.getResultList();
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error al Listar Usuarios: " + e);
        }
        return lista;
    }

    /**
     * Método que retorna una Lista de objetos de Tipo Persona. Esta lista se
     * obtiene a través de una consulta (Query) tomando como referencia un
     * String tipo, que denota el tipo de Persona, puede ser Alumno o Profesor.
     *
     * @param tipo Palabra clave a ser encontrada.
     * @return Devuelve una lista de objetos de tipo Persona.
     */
    public List<Persona> listarUsuariosTipo(String tipo) {
        List<Persona> lista = new ArrayList<>();
        try {
            Query query = getManager().createQuery("SELECT p FROM Persona p where p.rol.nombre != :admin and p.rol.nombre != :biblio"
                    + " and p.rol.nombre = :tipo");
            query.setParameter("admin", "Administrador");
            query.setParameter("biblio", "Bibliotecario");
            query.setParameter("tipo", tipo);
            lista = query.getResultList();
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error al Listar Usuarios por tipo: " + e);
        }
        return lista;
    }

    /**
     * Método que retorna una Lista de objetos de Tipo Persona. Esta lista se
     * obtiene a través de una consulta (Query) que toma en cuenta solamente a
     * las Personas que cumplen el rol de Alumno y Profesor. Además se solicita
     * un String busqueda. Esta búsqueda puede realizarse por: nombres,
     * apellidos, cédula, correo.
     *
     * @param busqueda Palabra clave a ser encontrada.
     * @return Devuelve una lista de objetos de tipo Persona.
     */
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

    /**
     * Método que retorna una Lista de objetos de Tipo Persona. Esta lista se
     * obtiene a través de una consulta (Query) tomando como referencia un
     * String tipo, que denota el tipo de Persona, puede ser Alumno o Profesor.
     * Además cuenta con un String busqueda, que filtra Personas por tipo
     * mediante: nombres, apellidos, cédula, correo.
     *
     * @param tipo Tipo de Persona a ser encontrado.
     * @param busqueda Palabra clave a ser encontrada.
     * @return Devuelve una lista de objetos de tipo Persona.
     */
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

    /**
     * Método que obtiene un objeto de tipo Persona que contenga en su atributo
     * 'cedula' el mismo valor que se le indica en el parámetro del método.
     *
     * @param cedula Valor a ser encontrado dentro de la consulta.
     * @return Devuelve un objeto Persona en caso de ser encontrado.
     */
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
