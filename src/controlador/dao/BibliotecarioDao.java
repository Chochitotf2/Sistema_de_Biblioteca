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
    
    
  
}
