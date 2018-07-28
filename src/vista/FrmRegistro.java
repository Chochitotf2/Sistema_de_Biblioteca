/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import vista.utilidades.UtilidadesComponente;

/**
 *
 * @author user
 */
public class FrmRegistro extends javax.swing.JDialog {

    /**
     * Creates new form FrmIniciarSesion
     */
    public FrmRegistro(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtNombre = new rojeru_san.RSMTextFull();
        txtApellido = new rojeru_san.RSMTextFull();
        txtUsuario = new rojeru_san.RSMTextFull();
        txtClave = new rojeru_san.RSMPassView();
        txtCorreoElectronico = new rojeru_san.RSMTextFull();
        txtDni = new rojeru_san.RSMTextFull();
        txtDireccion = new rojeru_san.RSMTextFull();
        txtTelefono = new rojeru_san.RSMTextFull();
        cbxTipoUsuario = new javax.swing.JComboBox<>();
        txtCarrera = new rojeru_san.RSMTextFull();
        txtCiclo = new rojeru_san.RSMTextFull();
        txtParalelo = new rojeru_san.RSMTextFull();
        jSeparator1 = new javax.swing.JSeparator();
        btnRegistrarse = new rojeru_san.RSButtonRiple();
        btnRegresar = new org.edisoncor.gui.button.ButtonAction();
        panelCurves1 = new org.edisoncor.gui.panel.PanelCurves();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Registrarse");
        setIconImage(UtilidadesComponente.obtenerIcono());
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(null);

        jPanel2.setBackground(new java.awt.Color(119, 136, 153));
        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.setLayout(null);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Nuevo Registro");
        jPanel2.add(jLabel5);
        jLabel5.setBounds(130, 10, 210, 30);

        txtNombre.setPlaceholder("Nombre");
        jPanel2.add(txtNombre);
        txtNombre.setBounds(50, 50, 200, 40);

        txtApellido.setBordeColorFocus(new java.awt.Color(169, 169, 169));
        txtApellido.setBotonColor(new java.awt.Color(169, 169, 169));
        txtApellido.setPlaceholder("Apellido");
        jPanel2.add(txtApellido);
        txtApellido.setBounds(252, 50, 200, 40);

        txtUsuario.setPlaceholder("Usuario");
        jPanel2.add(txtUsuario);
        txtUsuario.setBounds(50, 90, 200, 40);

        txtClave.setBordeColorFocus(new java.awt.Color(169, 169, 169));
        txtClave.setBotonColor(new java.awt.Color(169, 169, 169));
        txtClave.setPlaceholder("Contraseña");
        jPanel2.add(txtClave);
        txtClave.setBounds(252, 90, 200, 40);

        txtCorreoElectronico.setPlaceholder("Correo Electrónico");
        jPanel2.add(txtCorreoElectronico);
        txtCorreoElectronico.setBounds(50, 130, 200, 42);

        txtDni.setBordeColorFocus(new java.awt.Color(169, 169, 169));
        txtDni.setBotonColor(new java.awt.Color(169, 169, 169));
        txtDni.setPlaceholder("Cédula");
        jPanel2.add(txtDni);
        txtDni.setBounds(252, 130, 200, 42);

        txtDireccion.setPlaceholder("Dirección");
        jPanel2.add(txtDireccion);
        txtDireccion.setBounds(50, 172, 200, 40);

        txtTelefono.setBordeColorFocus(new java.awt.Color(169, 169, 169));
        txtTelefono.setBotonColor(new java.awt.Color(169, 169, 169));
        txtTelefono.setPlaceholder("Teléfono");
        jPanel2.add(txtTelefono);
        txtTelefono.setBounds(252, 172, 200, 40);

        cbxTipoUsuario.setForeground(new java.awt.Color(0, 112, 192));
        cbxTipoUsuario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel2.add(cbxTipoUsuario);
        cbxTipoUsuario.setBounds(150, 220, 200, 40);

        txtCarrera.setBordeColorFocus(new java.awt.Color(169, 169, 169));
        txtCarrera.setBotonColor(new java.awt.Color(169, 169, 169));
        txtCarrera.setPlaceholder("Carrera");
        jPanel2.add(txtCarrera);
        txtCarrera.setBounds(50, 312, 402, 40);

        txtCiclo.setPlaceholder("Ciclo");
        jPanel2.add(txtCiclo);
        txtCiclo.setBounds(50, 272, 200, 40);

        txtParalelo.setBordeColorFocus(new java.awt.Color(169, 169, 169));
        txtParalelo.setBotonColor(new java.awt.Color(169, 169, 169));
        txtParalelo.setPlaceholder("Paralelo");
        jPanel2.add(txtParalelo);
        txtParalelo.setBounds(252, 272, 200, 40);
        jPanel2.add(jSeparator1);
        jSeparator1.setBounds(150, 380, 200, 10);

        btnRegistrarse.setText("Registrarse");
        jPanel2.add(btnRegistrarse);
        btnRegistrarse.setBounds(160, 390, 170, 40);

        btnRegresar.setText("< Regresar");
        jPanel2.add(btnRegresar);
        btnRegresar.setBounds(10, 10, 120, 30);
        jPanel2.add(panelCurves1);
        panelCurves1.setBounds(0, 0, 490, 450);

        jPanel1.add(jPanel2);
        jPanel2.setBounds(120, 40, 490, 450);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/imagenes/B004.jpg"))); // NOI18N
        jLabel1.setText("jLabel1");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(0, 20, 730, 510);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, -20, 740, 540);

        setSize(new java.awt.Dimension(742, 548));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmRegistro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmRegistro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmRegistro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmRegistro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FrmRegistro dialog = new FrmRegistro(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojeru_san.RSButtonRiple btnRegistrarse;
    private org.edisoncor.gui.button.ButtonAction btnRegresar;
    private javax.swing.JComboBox<String> cbxTipoUsuario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private org.edisoncor.gui.panel.PanelCurves panelCurves1;
    private rojeru_san.RSMTextFull txtApellido;
    private rojeru_san.RSMTextFull txtCarrera;
    private rojeru_san.RSMTextFull txtCiclo;
    private rojeru_san.RSMPassView txtClave;
    private rojeru_san.RSMTextFull txtCorreoElectronico;
    private rojeru_san.RSMTextFull txtDireccion;
    private rojeru_san.RSMTextFull txtDni;
    private rojeru_san.RSMTextFull txtNombre;
    private rojeru_san.RSMTextFull txtParalelo;
    private rojeru_san.RSMTextFull txtTelefono;
    private rojeru_san.RSMTextFull txtUsuario;
    // End of variables declaration//GEN-END:variables
}
