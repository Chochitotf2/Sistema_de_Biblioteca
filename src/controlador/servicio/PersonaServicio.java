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
}
