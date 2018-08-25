package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author MAYLI
 */
@Getter
@Setter
@Entity
@Table(name = "Documento")
@Inheritance(strategy = InheritanceType.JOINED)
public class Documento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 80)
    private String titulo;
    @Column(length = 15)
    private String codigo;
    @Column(length = 50)
    private String tipoDocumento;
    public Boolean estado = true;
    @OneToMany(mappedBy = "documento", cascade = CascadeType.ALL)
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
        if (!(object instanceof Documento)) {
            return false;
        }
        Documento other = (Documento) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Documento[ id=" + id + " ]";
    }

}
