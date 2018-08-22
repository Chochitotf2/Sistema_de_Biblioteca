package controlador.dao;

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
}
