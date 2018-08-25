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
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            fechaSalida = formato.format(fecha);
        } catch (Exception e) {
            System.out.println("No se pudo dar formato a la Fecha: " + e);
        }
        return fechaSalida;
    }

    public static boolean esValido(String dato, char tipo) {
        boolean estado = false;
        switch (tipo) {
            case 'c'://Cédula
                if (dato.trim().length() == 10) {
                    int tercerDigito = Integer.parseInt(dato.trim().substring(2, 3));
                    if (tercerDigito < 6) {
                        int[] coefCedula = {2, 1, 2, 1, 2, 1, 2, 1, 2};
                        int verificador = Integer.parseInt(dato.trim().substring(9, 10));
                        int suma = 0;
                        int digito = 0;
                        for (int i = 0; i < (dato.trim().length() - 1); i++) {
                            digito = Integer.parseInt(dato.trim().substring(i, i + 1)) * coefCedula[i];
                            suma += ((digito % 10) + (digito / 10));
                        }
                        if ((suma % 10 == 0) && (suma % 10 == verificador)) {
                            estado = true;
                        } else if ((10 - (suma % 10)) == verificador) {
                            estado = true;
                        } else {
                            estado = false;
                        }
                    } else {
                        estado = false;
                    }
                } else {
                    estado = false;
                }
                break;
            case 'd'://Número Decimal
                if (dato.trim().matches("^[0-9]+([,][0-9]+)?$")) {
                    estado = true;
                }
                break;
            case 'e'://Número Entero
                if (dato.trim().matches("[0-9]*")) {
                    estado = true;
                }
            case 'm'://Correo Electrónico
                if (dato.trim().matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
                    estado = true;
                }
                break;
            case 't'://Texto
                if (dato.trim().matches("^[a-zA-Zá-úÁ-ú ]*$")) {
                    estado = true;
                }
                break;
            case 'u'://Único caractér
                if (dato.trim().matches("^[a-zA-Z]$")) {
                    estado = true;
                }
                break;
        }
        return estado;
    }
}
