package controlador.utilidades;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.lang.StringUtils;

/**
 * Clase mediante la cual se realiza la implementacion de métodos útiles para
 * algunas funcionalidades del sistema Bibliotecario.
 *
 * @author Víctor Andrés Rojas
 * @author Mario Orellana
 */
public class Utilidades extends StringUtils {

    /**
     * Método que permite obtener un formato de fecha de forma: yyyy-MM-dd
     * HH:mm.
     *
     * @param fecha Parámetro de tipo Date a formatearse.
     * @return Devuelve un String con el formato especificado.
     */
    public static String formatearFecha(Date fecha) {
        String fechaSalida = "";
        try {
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            fechaSalida = formato.format(fecha);
        } catch (Exception e) {
            System.out.println("No se pudo dar formato a la Fecha: " + e);
        }
        return fechaSalida;
    }

    /**
     * Método que valida campos de texto que por lo general son ingresados por
     * los distintos usuarios del sistema. Las validaciones son múltiples y
     * están realizadas con el uso de expresiones regulares. El método solicita
     * un parametro tipo, que hace referencia al tipo de validación que se
     * quiere implementar. Entre los tipos de validación encontramos:
     * <br>'c': Validar número de cédula.
     * <br>'d': Validar número decimal en formato [##.##].
     * <br>'e': Validar número entero.
     * <br>'m': Validar correo electrónico.
     * <br>'t': Validar solamente Texto.
     * <br>'u': Validar un único caractér.
     * <br>Escribir 'n' o cualquier otro caractér distinto a los mencionados
     * quiere decir que se acepta cualquier tipo de texto escrito.
     *
     *
     * @param dato Texto escrito por cualquier tipo de Usuario.
     * @param tipo Tipo de Validación.
     * @return Devuelve un resultado Booleano, en caso de ser verdadero quiere
     * decir que el texto escrito por el usuario es válido.
     */
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
                if (dato.trim().matches("^[0-9]+([.][0-9]+)?$")) {
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
