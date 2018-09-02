package vista.tablas;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import lombok.Getter;
import lombok.Setter;
import modelo.DocumentoNoConvencional;

/**
 *
 * @author Víctor Andrés Rojas
 * @author Mario Orellana
 */
public class ModeloTablaDocumentoNoConvencional extends AbstractTableModel {

    @Getter
    @Setter
    private List<DocumentoNoConvencional> lista = new ArrayList<>();

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
        DocumentoNoConvencional p = lista.get(rowIndex);
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
                return p.getAutor();
            case 4:
                return p.getTipoNoConvencional();
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
                return "Autores";
            case 4:
                return "Tipo";
            default:
                return null;
        }
    }
}
