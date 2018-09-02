package modelo;

import java.io.Serializable;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Esta clase contiene la información básica de un Libro de Biblioteca. La
 * información de este tipo de Documento contiene adicionalmente: uno o varios
 * nombres de autores, una edición, el año de publicación, editorial, y isbn.
 *
 * @author Víctor Andrés Rojas
 * @author Mario Orellana
 */
@Getter
@Setter
@Entity
public class Libro extends Documento implements Serializable {

    private String autores;
    @Column(length = 10)
    private String edicion;
    @Column(length = 6)
    private String anio;
    @Column(length = 70)
    private String editorial;
    @Column(length = 18)
    private String isbn;

    @Override
    public String toString() {
        return "modelo.Alumno[ id=" + getId() + " ]";
    }
}
