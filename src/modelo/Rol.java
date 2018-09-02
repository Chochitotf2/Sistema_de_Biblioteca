package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Esta clase contiene los distintos roles que un objeto de tipo Persona puede
 * tener en el sistema. Los roles ayudarán a definir los distintos caminos que
 * una persona puede tomar en el sistema.
 *
 * @author Víctor Andrés Rojas
 * @author Mario Orellana
 */
@Getter
@Setter
@Entity
public class Rol implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 25)
    private String nombre;
    @OneToMany(mappedBy = "rol", cascade = CascadeType.ALL)
    private List<Persona> listaPersona = new ArrayList<>();

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rol)) {
            return false;
        }
        Rol other = (Rol) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Rol[ id=" + id + " ]";
    }

}
