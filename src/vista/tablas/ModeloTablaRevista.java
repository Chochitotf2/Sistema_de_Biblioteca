package vista.tablas;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import lombok.Getter;
import lombok.Setter;
import modelo.Revista;

/**
 *
 * @author Víctor Andrés Rojas
 * @author Mario Orellana
 */
public class ModeloTablaRevista extends AbstractTableModel {

    @Getter
    @Setter
    private List<Revista> lista = new ArrayList<>();

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Revista p = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return p.getCodigo();
            case 1:
                return p.getTitulo();
            case 2:
                if (p.getEstado()) {
                    return "Disponible";
                } else {
                    return "No Disponible";
                }
            case 3:
                return p.getFechaPublicacion();
            case 4:
                return p.getEditorial();
            case 5:
                return p.getIssn();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Codigo";
            case 1:
                return "Título";
            case 2:
                return "Estado";
            case 3:
                return "Publicado";
            case 4:
                return "Editorial";
            case 5:
                return "Issn";
            default:
                return null;
        }
    }
}
