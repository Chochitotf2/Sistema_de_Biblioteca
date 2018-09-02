package modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Esta clase contiene los datos de una Sanción adquirida por el Usuario. Una
 * sanción ocurre cuando el Usuario no ha seguido las restricciones detalladas
 * en el préstamo de un documento en el sistema.
 *
 * @author Víctor Andrés Rojas
 * @author Mario Orellana
 */
@Entity
@Getter
@Setter
@Table(name = "Sancion")
public class Sancion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Double monto;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    private Boolean estado = true;
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(referencedColumnName = "id", name = "idPrestamo")
    private Prestamo prestamo;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sancion)) {
            return false;
        }
        Sancion other = (Sancion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Sansion[ id=" + id + " ]";
    }

}
