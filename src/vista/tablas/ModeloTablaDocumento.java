package vista.tablas;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import lombok.Getter;
import lombok.Setter;
import modelo.Documento;

/**
 *
 * @author Víctor Andrés Rojas
 * @author Mario Orellana
 */
public class ModeloTablaDocumento extends AbstractTableModel {

    @Getter
    @Setter
    private List<Documento> lista = new ArrayList<>();

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
        Documento p = lista.get(rowIndex);
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
                return p.getTipoDocumento();
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
                return "Tipo";
            default:
                return null;
        }
    }
}
