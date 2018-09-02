package vista.tablas;

import controlador.utilidades.Utilidades;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import lombok.Getter;
import lombok.Setter;
import modelo.Sancion;

/**
 *
 * @author Víctor Andrés Rojas
 * @author Mario Orellana
 */
public class ModeloTablaSancion extends AbstractTableModel {

    @Getter
    @Setter
    private List<Sancion> lista = new ArrayList<>();

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Sancion p = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return p.getPrestamo().getPersona().getNombres() + " " + p.getPrestamo().getPersona().getApellidos();
            case 1:
                return p.getPrestamo().getPersona().getDni();
            case 2:
                return Utilidades.formatearFecha(p.getFecha());
            case 3:
                return "$" + p.getMonto();
            case 4:
                if (p.getEstado()) {
                    return "En Deuda";
                } else {
                    return "Pagada";
                }
            default:
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
            case 4:
                return "Estado";
            default:
                return null;
        }
    }
}
