package controlador.utilidades;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Víctor Andrés Rojas
 */
public class Utilidades extends StringUtils {

    public static String formatearFecha(Date fecha) {
        String fechaSalida = "";
        try {
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            fechaSalida = formato.format(fecha);
        } catch (Exception e) {
            System.out.println("No se pudo dar formato a la Fecha: " + e);
        }
        return fechaSalida;
    }
}
