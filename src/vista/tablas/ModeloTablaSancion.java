package vista.tablas;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import lombok.Getter;
import lombok.Setter;
import modelo.Sancion;

/**
 *
 * @author Víctor Andrés Rojas
 */
public class ModeloTablaSancion extends AbstractTableModel {

    @Getter
    @Setter
    private List<Sancion> lista = new ArrayList<>();

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Sancion p = lista.get(rowIndex);
        if (p.getEstado()) {
            switch (columnIndex) {
                case 0:
                    return p.getPrestamo().getPersona().getNombres() + " " + p.getPrestamo().getPersona().getApellidos();
                case 1:
                    return p.getPrestamo().getPersona().getDni();
                case 2:
                    return p.getFecha();
                case 3:
                    return "$" + p.getMonto();
                default:
                    return null;
            }
        } else {
            return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Persona";
            case 1:
                return "Cédula";
            case 2:
                return "Fecha";
            case 3:
                return "Monto";
            default:
                return null;
        }
    }
}
