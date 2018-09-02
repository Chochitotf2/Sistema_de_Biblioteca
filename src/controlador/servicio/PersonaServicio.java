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

    public List<Persona> listarSinAdministradorUsuarios() {
        return persona.listarSinAdministradorUsuarios();
    }

    public List<Persona> listarUsuariosTipo(String tipo) {
        return persona.listarUsuariosTipo(tipo);
    }

    public List<Persona> listarSinAdministradorUsuariosLike(String busqueda) {
        return persona.listarSinAdministradorUsuariosLike(busqueda);
    }

    public List<Persona> listarUsuariosTipoLike(String tipo, String busqueda) {
        return persona.listarUsuariosTipoLike(tipo, busqueda);
    }

    public Persona obtenerPersonaCedula(String cedula) {
        return persona.obtenerPersonaCedula(cedula);
    }
}
