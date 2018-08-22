package modelo;

import java.io.Serializable;
import java.util.Date;
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
public class Tesis extends Documento implements Serializable {

    private String autores;
    private String asesores;
    private String facultad;
    @Temporal(TemporalType.DATE)
    private Date fechaPublicacion;

    @Override
    public String toString() {
        return "modelo.Alumno[ id=" + getId() + " ]";
    }
}
