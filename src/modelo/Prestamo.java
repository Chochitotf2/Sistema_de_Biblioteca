package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author MAYLI
 */
@Entity
@Getter
@Setter
@Table(name = "Prestamo")
public class Prestamo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEntrega;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDevolucion;
    private Boolean estado = true;
    private String tipoPrestamo;
    @OneToMany(mappedBy = "prestamo", cascade = CascadeType.ALL)
    private List<Sansion> listaSansion = new ArrayList<>();
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(referencedColumnName = "id", name = "idDocumento")
    private Documento documento;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Prestamo)) {
            return false;
        }
        Prestamo other = (Prestamo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Prestamo[ id=" + id + " ]";
    }

}
