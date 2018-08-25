package vista.tablas;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import lombok.Getter;
import lombok.Setter;
import modelo.Cuenta;

import modelo.Persona;

/**
 *
 * @author Víctor Andrés Rojas
 */
public class ModeloTablaUsuario extends AbstractTableModel {

    @Getter
    @Setter
    private List<Persona> lista = new ArrayList<>();
    private List<Cuenta> lista2 = new ArrayList<>();

    public List<Cuenta> getLista2() {
        return lista2;
    }

    public void setLista2(List<Cuenta> lista2) {
        this.lista2 = lista2;
    }

    
    
    @Override
    public int getColumnCount() {
        return 9;
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Persona p = lista.get(rowIndex);
        Cuenta c = lista2.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return p.getNombres();
            case 1:
                return p.getApellidos();
            case 2:
                return p.getDni();
            case 3:
                return p.getCorreo();
            case 4:
                return p.getTelefono();
            case 5:
                return p.getDireccion();
            case 6:
                c.getUsuario();
            case 7:
                if (c.getEstado()) {
                    return "Activa";
                } else {
                    return "Inactiva";
                }
            case 8:
                c.getCreadoEn();
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
                return "Cedula";
            case 3:
                return "Correo";
            case 4:
                return "Telefono";
            case 5:
                return "Direccion";
            case 6:
                return "Usuario";
            case 7:

                return "Estado";

            case 8:
                return "Creado en";
            default:
                return null;
        }
    }
}
