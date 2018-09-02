package modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Esta clase contiene la información básica de una Revista de Biblioteca. La
 * información de este tipo de Documento contiene adicionalmente: fecha de
 * publicación, issn y editorial.
 *
 * @author Víctor Andrés Rojas
 * @author Mario Orellana
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
