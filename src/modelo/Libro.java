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
public class Libro extends Documento implements Serializable {

    private String autores;
    @Column(length = 10)
    private String edicion;
    @Column(length = 4)
    private String anio;
    @Column(length = 60)
    private String editorial;
    @Column(length = 18)
    private String isbn;

    @Override
    public String toString() {
        return "modelo.Alumno[ id=" + getId() + " ]";
    }
}
