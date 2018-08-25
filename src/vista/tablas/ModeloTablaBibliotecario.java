package vista.tablas;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import lombok.Getter;
import lombok.Setter;
import modelo.Bibliotecario;

/**
 *
 * @author Víctor Andrés Rojas
 */
public class ModeloTablaBibliotecario extends AbstractTableModel {

    @Getter
    @Setter
    private List<Bibliotecario> lista = new ArrayList<>();

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
        Bibliotecario b = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return b.getNombres();
            case 1:
                return b.getApellidos();
            case 2:
                return b.getDni();
            case 3:
                return b.getCorreo();
            case 4:
                return b.getTelefono();
            case 5:
                return b.getDireccion();
            case 6:
                b.getSeccion();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Nombres";
            case 1:
                return "Apellidos";
            case 2:
                return "DNI";
            case 3:
                return "Correo";
            case 4:
                return "Telefono";
            case 5:
                return "Direccion";
            case 6:
                return "Seccion";
            default:
                return null;
        }
    }
}
