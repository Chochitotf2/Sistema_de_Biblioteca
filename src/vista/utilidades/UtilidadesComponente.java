/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.utilidades;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.text.JTextComponent;

/**
 *
 * @author Víctor Andrés Rojas
 */
public class UtilidadesComponente {

//    public static boolean mostrarError(JComponent componente, String mensaje, char tipoValidacion) {
//        boolean band = false;
//        switch (tipoValidacion) {
//            case 'r':
//                if (componente instanceof JTextComponent) {
//                    JTextComponent txt = (JTextComponent) componente;
//                    if (Utilidades.isEmpty(txt.getText())) {
//                    componente.setBorder(BorderFactory.createLineBorder(Color.RED));
//                    componente.setToolTipText(mensaje);
//                } else {
//                    componente.setBorder(BorderFactory.createLineBorder(Color.WHITE));
//                    componente.setToolTipText(null);
//                    band = true;
//                }
//        }
//    }
//
//    return band ;
//}
    public static void mensajeError(String titulo, String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, titulo, JOptionPane.ERROR_MESSAGE);
    }

    public static void mensajeOK(String titulo, String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, titulo, JOptionPane.INFORMATION_MESSAGE);
    }

    public static Image obtenerIcono() {
        Image icono = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("vista/imagenes/Icono.png"));
        return icono;
    }
    
    public static boolean validarCorreo(String correo) {
        String patron = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(patron);
        Matcher matcher = pattern.matcher(correo);
        return matcher.matches();
    }
}
