package modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Esta clase contiene la información básica de una Tésis de Biblioteca. La
 * información de este tipo de Documento contiene adicionalmente: uno o varios
 * nombres de autores, uno o varios nombres de asesores de tesis, el nombre de
 * la facultad y fecha de publicación
 *
 * @author Víctor Andrés Rojas
 * @author Mario Orellana
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
