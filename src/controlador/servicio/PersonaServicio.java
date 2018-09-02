package controlador.servicio;

import controlador.dao.PersonaDao;
import java.util.List;
import modelo.Persona;

/**
 * Clase que mediante una instancia de PersonaDao, obtiene todos los métodos de
 * la misma y junto a ello los del AdaptadorDao, de esta manera una instancia de
 * PersonaServicio bastará para realizar todas las operaciones entre un objeto
 * Persona y la Base de datos de uso.
 *
 * @author Víctor Andrés Rojas
 * @author Mario Orellana
 */
public class PersonaServicio {

    private PersonaDao persona = new PersonaDao();

    /**
     * Método que obtiene una instancia de Persona. En caso de no existir se
     * creará un nuevo objeto.
     *
     * @return Devuelve un objeto de tipo Persona.
     */
    public Persona obtenerPersona() {
        return persona.getPersona();
    }

    /**
     * Método que obtiene una instancia de Persona mediante un identificador o
     * clave primaria.
     *
     * @param id Valor del identificador o clave primaria.
     * @return Devuelve un objeto de tipo Persona.
     */
    public Persona obtenerPersona(Long id) {
        return persona.obtener(id);
    }

    /**
     * Método que asigna un objeto de tipo Persona al campo de la clase
     * PersonaDao que es del mismo tipo.
     *
     * @param persona Instancia Persona a asignarse.
     */
    public void fijarPersona(Persona persona) {
        this.persona.setPersona(persona);
    }

    /**
     * Método que guarda o modifica un objeto de tipo Persona en la respectiva
     * Base de datos de uso.
     *
     * @return Devuelve un resultado Booleano, en caso de ser verdadero quiere
     * decir que la operación se realizó correctamente.
     */
    public boolean guardar() {
        return persona.guardar();
    }

    /**
     * Método que realiza una consulta en la Base de datos y devuelve un listado
     * de objetos Persona.
     *
     * @return Devuelve una lista de Tipo Persona.
     */
    public List<Persona> listar() {
        return persona.listar();
    }

    /**
     * Método que retorna una Lista de objetos de Tipo Persona. Esta lista se
     * obtiene a través de una consulta (Query) que toma en cuenta solamente a
     * las Personas que cumplen el rol de Alumno y Profesor.
     *
     * @return Devuelve una lista de objetos de tipo Persona.
     */
    public List<Persona> listarSinAdministradorUsuarios() {
        return persona.listarSinAdministradorUsuarios();
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
        return persona.listarUsuariosTipo(tipo);
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
        return persona.listarSinAdministradorUsuariosLike(busqueda);
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
        return persona.listarUsuariosTipoLike(tipo, busqueda);
    }

    /**
     * Método que obtiene un objeto de tipo Persona que contenga en su atributo
     * 'cedula' el mismo valor que se le indica en el parámetro del método.
     *
     * @param cedula Valor a ser encontrado dentro de la consulta.
     * @return Devuelve un objeto Persona en caso de ser encontrado.
     */
    public Persona obtenerPersonaCedula(String cedula) {
        return persona.obtenerPersonaCedula(cedula);
    }
}
