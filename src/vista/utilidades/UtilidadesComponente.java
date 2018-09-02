package vista.utilidades;

import controlador.utilidades.Utilidades;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.text.JTextComponent;

/**
 * Clase mediante la cual se realiza la implementacion de métodos útiles para
 * algunas funcionalidades de los componentes que hacen parte de las interfaces
 * gráficas del sistema Bibliotecario.
 *
 * @author Víctor Andrés Rojas
 * @author Mario Orellana
 */
public class UtilidadesComponente {

    /**
     * Método que verifica componentes en los cuales se ingresa algún tipo de
     * texto por los distintos usuarios del sistema. Las validaciones son
     * múltiples y en caso de dar algun error, dicho componente de texto se
     * marcará de color rojo en su fondo, y un mensaje de alerta saltará para
     * dar la información de dicho error al Usuario. El método solicita un
     * parametro tipo, que hace referencia al tipo de validación que se quiere
     * implementar. Entre los tipos de validación encontramos:
     * <br>'c': Validar número de cédula.
     * <br>'d': Validar número decimal en formato [##.##].
     * <br>'e': Validar número entero.
     * <br>'m': Validar correo electrónico.
     * <br>'t': Validar solamente Texto.
     * <br>'u': Validar un único caractér.
     * <br>Escribir 'n' o cualquier otro caractér distinto a los mencionados
     * quiere decir que se acepta cualquier tipo de texto escrito.
     * <br>Además es opcional poner un mensaje para mostrar la información del
     * error en el parametro mensaje. También es importante recalcar que si el
     * componente está vacio y se aplica este método, tambien saltará un error
     * con un mensaje de aviso para llenar dicho componente.
     *
     * @param componente Componente a ser verificado.
     * @param mensaje Mensaje de error a mostrarse, en caso de darse dicha
     * situación.
     * @param tipoValidacion Tipo de Validación.
     * @return
     */
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

    /**
     * Método que obtiene un icono para las ventanas del sistema.
     *
     * @return Devuelve el icono del sistema como objeto Image.
     */
    public static Image obtenerIcono() {
        Image icono = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("vista/imagenes/Icono.png"));
        return icono;
    }

    /**
     * Método que muestra un mensaje de error o alerta al usuario mediante una
     * ventana emergente.
     *
     * @param titulo Título de la ventana emergente.
     * @param mensaje Mensaje escrito en la ventana emergente.
     */
    public static void mensajeError(String titulo, String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, titulo, JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Método que muestra un mensaje de validación al usuario mediante una
     * ventana emergente.
     *
     * @param titulo Título de la ventana emergente.
     * @param mensaje Mensaje escrito en la ventana emergente.
     */
    public static void mensajeOK(String titulo, String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, titulo, JOptionPane.INFORMATION_MESSAGE);
    }
}
