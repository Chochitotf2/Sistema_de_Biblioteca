package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Esta clase contiene la información básica de una Persona. La información de
 * una persona es primordial para identificar los distintos roles que puede
 * cumplir la misma dentro del sistema.
 *
 * @author Víctor Andrés Rojas
 * @author Mario Orellana
 */
@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Persona implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 40)
    private String nombres;
    @Column(length = 40)
    private String apellidos;
    @Column(length = 40)
    private String correo;
    @Column(length = 13, unique = true)
    private String dni;
    private String direccion;
    @Column(length = 15)
    private String telefono;
    @OneToOne(mappedBy = "persona", cascade = CascadeType.ALL)
    private Cuenta cuenta;
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(referencedColumnName = "id", name = "idRol")
    private Rol rol;
    @OneToMany(mappedBy = "persona", cascade = CascadeType.ALL)
    private List<Prestamo> listaPrestamo = new ArrayList<>();

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Persona)) {
            return false;
        }
        Persona other = (Persona) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Persona[ id=" + id + " ]";
    }

}
