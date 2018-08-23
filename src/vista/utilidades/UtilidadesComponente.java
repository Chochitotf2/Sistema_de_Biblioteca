package vista.utilidades;

import controlador.utilidades.Utilidades;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.text.JTextComponent;

/**
 *
 * @author Víctor Andrés Rojas
 */
public class UtilidadesComponente {

    public static boolean mostrarError(JComponent componente, String mensaje, char tipoValidacion) {
        boolean estado = false;
        if (componente instanceof JTextComponent) {
            JTextComponent txt = (JTextComponent) componente;
            if (Utilidades.isEmpty(txt.getText().trim())) {
                componente.setBackground(new Color(250, 216, 216));
                componente.setToolTipText("El campo es requerido.");
                estado = true;
            } else {
                switch (tipoValidacion) {
                    case 'c'://Cédula
                        if (!Utilidades.esValido(txt.getText(), tipoValidacion)) {
                            estado = true;
                        }
                        break;
                    case 'd'://Número Decimal
                        if (!Utilidades.esValido(txt.getText(), tipoValidacion)) {
                            estado = true;
                        }
                        break;
                    case 'e'://Número Entero
                        if (!Utilidades.esValido(txt.getText(), tipoValidacion)) {
                            estado = true;
                        }
                        break;
                    case 'm'://Correo Electrónico
                        if (!Utilidades.esValido(txt.getText(), tipoValidacion)) {
                            estado = true;
                        }
                        break;
                    case 't'://Texto
                        if (!Utilidades.esValido(txt.getText(), tipoValidacion)) {
                            estado = true;
                        }
                        break;
                    case 'u'://Único caractér
                        if (!Utilidades.esValido(txt.getText(), tipoValidacion)) {
                            estado = true;
                        }
                        break;
                }
                if (estado) {
                    componente.setBackground(new Color(250, 216, 216));
                    componente.setToolTipText(mensaje);
                } else {
                    componente.setBackground(Color.WHITE);
                    componente.setToolTipText(null);
                }
            }
        }
        return estado;
    }

    public static Image obtenerIcono() {
        Image icono = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("vista/imagenes/Icono.png"));
        return icono;
    }

    public static void mensajeError(String titulo, String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, titulo, JOptionPane.ERROR_MESSAGE);
    }

    public static void mensajeOK(String titulo, String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, titulo, JOptionPane.INFORMATION_MESSAGE);
    }
}
