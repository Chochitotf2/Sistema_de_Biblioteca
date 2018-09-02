package modelo;

import java.io.Serializable;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Esta clase contiene la información básica de un Bibliotecario.
 *
 * @author Víctor Andrés Rojas
 * @author Mario Orellana
 */
@Getter
@Setter
@Entity
public class Bibliotecario extends Persona implements Serializable {

    @Column(length = 11, name = "seccion")
    private String seccion;

    @Override
    public String toString() {
        return "modelo.Bibliotecario[ id=" + "" + getId() + " ]";
    }
}
