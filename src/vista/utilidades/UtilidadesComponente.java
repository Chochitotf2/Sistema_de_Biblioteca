/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.utilidades;

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

    public static boolean campoVacio(JComponent componente, String mensaje) {
        boolean band = true;
        if (componente instanceof JTextComponent) {
            JTextComponent txt = (JTextComponent) componente;
            if (txt.getText().isEmpty()) {
                componente.setBackground(new Color(250, 216, 216));
                componente.setToolTipText(mensaje);
            } else {
                componente.setBackground(Color.WHITE);
                componente.setToolTipText(null);
                band = false;
            }
        }
        return band;
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

    public static boolean esValido(JComponent componente, char tipo) {
        boolean band = false;
        if (componente instanceof JTextComponent) {
            JTextComponent txt = (JTextComponent) componente;
            switch (tipo) {
                case 'm'://Correo Electrónico
                    if (txt.getText().trim().matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
                        band = true;
                    }
                    break;
                case 'c'://Cédula
                    if (txt.getText().trim().length() == 10) {
                        int tercerDigito = Integer.parseInt(txt.getText().trim().substring(2, 3));
                        if (tercerDigito < 6) {
                            int[] coefCedula = {2, 1, 2, 1, 2, 1, 2, 1, 2};
                            int verificador = Integer.parseInt(txt.getText().trim().substring(9, 10));
                            int suma = 0;
                            int digito = 0;
                            for (int i = 0; i < (txt.getText().trim().length() - 1); i++) {
                                digito = Integer.parseInt(txt.getText().trim().substring(i, i + 1)) * coefCedula[i];
                                suma += ((digito % 10) + (digito / 10));
                            }
                            if ((suma % 10 == 0) && (suma % 10 == verificador)) {
                                band = true;
                            } else if ((10 - (suma % 10)) == verificador) {
                                band = true;
                            } else {
                                band = false;
                            }
                        } else {
                            band = false;
                        }
                    } else {
                        band = false;
                    }
                    break;
                case 'e'://Número Entero
                    if (txt.getText().trim().matches("[0-9]*")) {
                        band = true;
                    }
                case 'd'://Número Decimal
                    if (txt.getText().trim().matches("^[0-9]+([,][0-9]+)?$")) {
                        band = true;
                    }
                    break;
                case 't'://Texto
                    if (txt.getText().trim().matches("^[a-zA-Zá-úÁ-ú ]*$")) {
                        band = true;
                    }
                    break;
            }
        }
        return band;
    }
}
