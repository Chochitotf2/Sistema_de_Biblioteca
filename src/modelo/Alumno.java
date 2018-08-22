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
public class Alumno extends Persona implements Serializable {

    @Column(length = 40)
    private String carrera;
    @Column(length = 20)
    private String ciclo;
    private Character paralelo;

    @Override
    public String toString() {
        return "modelo.Alumno[ id=" + getId() + " ]";
    }
}
