package vista.tablas;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import lombok.Getter;
import lombok.Setter;
import modelo.Persona;

/**
 *
 * @author Víctor Andrés Rojas
 */
public class ModeloTablaUsuario extends AbstractTableModel {

    @Getter
    @Setter
    private List<Persona> lista = new ArrayList<>();

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
        Persona p = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                if (p.getCuenta().getEstado()) {
                    return "Activa";
                } else {
                    return "Inactiva";
                }
            case 1:
                return p.getNombres() + " " + p.getApellidos();
            case 2:
                return p.getRol().getNombre();
            case 3:
                return p.getDni();
            case 4:
                return p.getCorreo();
            case 5:
                return p.getTelefono();
            case 6:
                return p.getDireccion();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Cuenta";
            case 1:
                return "Nombres";
            case 2:
                return "Tipo";
            case 3:
                return "Cédula";
            case 4:
                return "Correo";
            case 5:
                return "Teléfono";
            case 6:
                return "Dirección";
            default:
                return null;
        }
    }
}
