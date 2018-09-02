package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Esta clase contiene la información necesaria de un Préstamo Bibliotecario. La
 * información de un préstamo será útil para tener una referencia entre el
 * documento prestado, y la persona que adquirió dicho documento.
 *
 * @author Víctor Andrés Rojas
 * @author Mario Orellana
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
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(referencedColumnName = "id", name = "idPersona")
    private Persona persona;
    @OneToMany(mappedBy = "prestamo", cascade = CascadeType.ALL)
    private List<Sancion> listaSansion = new ArrayList<>();
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
