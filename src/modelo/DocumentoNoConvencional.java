package modelo;

import java.io.Serializable;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Esta clase contiene la información básica de un Documento no Convencional de
 * Biblioteca. La información de este tipo de Documento contiene adicionalmente:
 * un nombre de autor y un tipo de Documento no Convencional.
 *
 * @author Víctor Andrés Rojas
 * @author Mario Orellana
 */
@Getter
@Setter
@Entity
public class DocumentoNoConvencional extends Documento implements Serializable {

    private String autor;
    @Column(length = 30)
    private String tipoNoConvencional;

    @Override
    public String toString() {
        return "modelo.Alumno[ id=" + getId() + " ]";
    }
}
