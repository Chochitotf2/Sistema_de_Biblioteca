package modelo;

import java.io.Serializable;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Víctor Andrés Rojas
 */
@Getter
@Setter
@Entity
public class Bibliotecario extends Persona implements Serializable {

    @Column(length = 11, name = "seccion")
    private String seccion;

    @Override
    public String toString() {
        return "modelo.Bibliotecario[ id=" +""+ getId() + " ]";
    }
}
