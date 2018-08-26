package vista.tablas;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import lombok.Getter;
import lombok.Setter;
import modelo.Tesis;

/**
 *
 * @author Víctor Andrés Rojas
 */
public class ModeloTablaTesis extends AbstractTableModel {

    @Getter
    @Setter
    private List<Tesis> lista = new ArrayList<>();

    @Override
    public int getColumnCount() {
        return 7;
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Tesis p = lista.get(rowIndex);
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
                return p.getAutores();
            case 4:
                return p.getAsesores();
            case 5:
                return p.getFacultad();
            case 6:
                return p.getFechaPublicacion();
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
                return "Asesores";
            case 5:
                return "Facultad";
            case 6:
                return "Publicado";
            default:
                return null;
        }
    }
}
