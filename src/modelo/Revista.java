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
public class Revista extends Documento implements Serializable {

    @Temporal(TemporalType.DATE)
    private Date fechaPublicacion;
    @Column(length = 14)
    private String issn;
    @Column(length = 60)
    private String editorial;

    @Override
    public String toString() {
        return "modelo.Alumno[ id=" + getId() + " ]";
    }
}
