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
public class DocumentoNoConvencional extends Documento implements Serializable {

    private String autor;
    @Column(length = 10)
    private String tipoNoConvencional;

    @Override
    public String toString() {
        return "modelo.Alumno[ id=" + getId() + " ]";
    }
}
