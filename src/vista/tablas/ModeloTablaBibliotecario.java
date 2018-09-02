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
                return b.getNombres() + " " + b.getApellidos();
            case 1:
                if (b.getCuenta().getEstado()) {
                    return "Activa";
                } else {
                    return "Inactiva";
                }
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
                return "Bibliotecario";
            case 1:
                return "Cuenta";
            case 2:
                return "Cédula";
            case 3:
                return "Correo Electrónico";
            case 4:
                return "Teléfono";
            case 5:
                return "Dirección";
            case 6:
                return "Sección";
            default:
                return null;
        }
    }
}
