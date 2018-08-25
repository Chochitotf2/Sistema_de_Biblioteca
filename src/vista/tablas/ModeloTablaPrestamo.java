package vista.tablas;

import controlador.utilidades.Utilidades;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import lombok.Getter;
import lombok.Setter;
import modelo.Prestamo;

/**
 *
 * @author Víctor Andrés Rojas
 */
public class ModeloTablaPrestamo extends AbstractTableModel {

    @Getter
    @Setter
    private List<Prestamo> lista = new ArrayList<>();

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
        Prestamo p = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return p.getTipoPrestamo();
            case 1:
                return p.getDocumento().getTitulo();
            case 2:
                if (p.getEstado()) {
                    return "En prestamo";
                } else {
                    return "Devuelto";
                }
            case 3:
                return p.getPersona().getNombres() + " " + p.getPersona().getApellidos();
            case 4:
                return Utilidades.formatearFecha(p.getFechaEntrega()) + "|" + Utilidades.formatearFecha(p.getFechaDevolucion());
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Tipo";
            case 1:
                return "Documento";
            case 2:
                return "Estado";
            case 3:
                return "Persona";
            case 4:
                return "Entrega|Devolución";
            default:
                return null;
        }
    }
}
