package controlador.servicio;

import controlador.dao.PersonaDao;
import java.util.List;
import modelo.Persona;

/**
 *
 * @author Víctor Andrés Rojas
 */
public class PersonaServicio {

    private PersonaDao persona = new PersonaDao();

    public Persona obtenerPersona() {
        return persona.getPersona();
    }

    public Persona obtenerPersona(Long id) {
        return persona.obtener(id);
    }

    public void fijarPersona(Persona persona) {
        this.persona.setPersona(persona);
    }

    public boolean guardar() {
        return persona.guardar();
    }

    public List<Persona> listar() {
        return persona.listar();
    }

    public List<Persona> listarPersonaLike(String busqueda) {
        return persona.listarPersonaLike(busqueda);
    }

    public List<Persona> listarPersonaTipoLike(String tipo, String busqueda) {
        return persona.listarPersonaTipoLike(tipo, busqueda);
    }

    public List<Persona> listarSinAdministradorTipo(String tipo) {
        return persona.listarSinAdministradorTipo(tipo);
    }

    public Persona obtenerPersonaCedula(String cedula) {
        return persona.obtenerPersonaCedula(cedula);
    }
}
