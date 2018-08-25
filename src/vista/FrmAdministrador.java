/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controlador.servicio.BibliotecarioServicio;
import controlador.servicio.CuentaServicio;
import controlador.servicio.PersonaServicio;
import static controlador.utilidades.Utilidades.formatearFecha;
import java.awt.Color;
import java.util.Date;
import vista.tablas.ModeloTablaBibliotecario;
import vista.tablas.ModeloTablaUsuario;
import vista.utilidades.UtilidadesComponente;
import static vista.utilidades.UtilidadesComponente.mensajeError;
import static vista.utilidades.UtilidadesComponente.mensajeOK;
import static vista.utilidades.UtilidadesComponente.mostrarError;

/**
 *
 * @author user
 */
public class FrmAdministrador extends javax.swing.JDialog {

    private BibliotecarioServicio bS = new BibliotecarioServicio();
    private PersonaServicio ps = new PersonaServicio();
    private CuentaServicio cs = new CuentaServicio();
    private ModeloTablaBibliotecario modeloBiblio = new ModeloTablaBibliotecario();
    private ModeloTablaUsuario modelouser = new ModeloTablaUsuario();

    /**
     * Creates new form FrmAdministrador
     */
    public FrmAdministrador(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        cargartablabiblo();
        cargarTablaUser();
        initComponents();

    }

    private void cargartablabiblo() {

        modeloBiblio.setLista(bS.listar());
        tabla_bibliotecarios.setModel(modeloBiblio);
        tabla_bibliotecarios.updateUI();
       
    }

    private void cargarTablaUser() {

        modelouser.setLista(ps.listar());
        modelouser.setLista2(cs.listar());
        tabla_bibliotecarios.setModel(modelouser);
        tabla_bibliotecarios.updateUI();
    }

    
    
    private void repintarComponentes() {
        txt_nombre.setBackground(Color.WHITE);
        txt_apellidos.setBackground(Color.WHITE);
        txt_cedula.setBackground(Color.WHITE);
        txt_telefono.setBackground(Color.WHITE);
        txt_coreo.setBackground(Color.WHITE);
        txt_dir.setBackground(Color.WHITE);
        txt_usuario.setBackground(Color.WHITE);
        txt_contraseña.setBackground(Color.WHITE);
    }
    
    private void limpiar(){
        bS.fijarBibliotecario(null);
        cs.fijarCuenta(null);
        txt_nombre.setText("");
        txt_apellidos.setText("");
        txt_cedula.setText("");
        txt_telefono.setText("");
        txt_coreo.setText("");
        txt_dir.setText("");
        txt_usuario.setText("");
        txt_contraseña.setText("");
        cbx_seccion.setSelectedItem(1);
    }

    public void cargarobjeto() {
        bS.obtenerBibliotecario().setNombres(txt_nombre.getText().trim());
        bS.obtenerBibliotecario().setApellidos(txt_apellidos.getText().trim());
        bS.obtenerBibliotecario().setDni(txt_cedula.getText().trim());
        bS.obtenerBibliotecario().setTelefono(txt_telefono.getText().trim());
        bS.obtenerBibliotecario().setCorreo(txt_coreo.getText().trim());
        bS.obtenerBibliotecario().setDireccion(txt_dir.getText().trim());
        bS.obtenerBibliotecario().setSeccion(cbx_seccion.getSelectedItem().toString());
        cs.obtenerCuenta().setUsuario(txt_usuario.getText().trim());
        cs.obtenerCuenta().setClave(txt_contraseña.getText().trim());
    }
    
    private void cargarEdicion() {
        int fila = tabla_bibliotecarios.getSelectedRow();
        if (fila >= 0) {
            bS.fijarBibliotecario(modeloBiblio.getLista().get(fila));
            txt_nombre.setText(bS.obtenerBibliotecario().getNombres());
            txt_apellidos.setText(bS.obtenerBibliotecario().getApellidos());
            txt_cedula.setText(bS.obtenerBibliotecario().getDni());
            txt_telefono.setText(bS.obtenerBibliotecario().getTelefono());
            txt_coreo.setText(bS.obtenerBibliotecario().getCorreo());
            txt_dir.setText(bS.obtenerBibliotecario().getDireccion());
            cbx_seccion.setSelectedItem(bS.obtenerBibliotecario().getSeccion());
        } else {
            mensajeError("Advertencia", "Debe seleccionar un Documento de la Tabla.");
        }
    }
    
    private void registrar() {
        if (!errores()) {
            cargarobjeto();
                if (bS.guardar()) {
                    mensajeOK("Aviso", "Se ha registrado con éxito.");
                    limpiar();
                } else {
                    mensajeError("Error", "Ha ocurrido un error al realizar su registro.");
                }
            
            
        }
    }
    
    private boolean errores() {
        boolean verificador = false;
        if (mostrarError(txt_nombre, "El Nombre solo puede contener letras.", 't')) {
            verificador = true;
        }
        if (mostrarError(txt_apellidos, "El Apellido solo puede contener letras.", 't')) {
            verificador = true;
        }
        if (mostrarError(txt_usuario, null, 'n')) {
            verificador = true;
        }
        if (mostrarError(txt_contraseña, null, 'n')) {
            verificador = true;
        }
        if (mostrarError(txt_coreo, "El Email ingresado no es válido.", 'm')) {
            verificador = true;
        }
        if (mostrarError(txt_cedula, "La cédula ingresada no es válida.", 'c')) {
            verificador = true;
        }
        if (mostrarError(txt_dir, null, 'n')) {
            verificador = true;
        }
        if (mostrarError(txt_telefono, "El Teléfono solo puede contener números.", 'e')) {
            verificador = true;
        }
        return verificador;
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
        panelSelector = new org.edisoncor.gui.tabbedPane.TabbedSelector2();
        jPanel2 = new javax.swing.JPanel();
        panelReflect1 = new org.edisoncor.gui.panel.PanelReflect();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_bibliotecarios = new javax.swing.JTable();
        buttonAeroLeft3 = new org.edisoncor.gui.button.ButtonAeroLeft();
        buttonAeroRight7 = new org.edisoncor.gui.button.ButtonAeroRight();
        buttonAero1 = new org.edisoncor.gui.button.ButtonAero();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        panelCurves1 = new org.edisoncor.gui.panel.PanelCurves();
        jLabel6 = new javax.swing.JLabel();
        txt_coreo = new org.edisoncor.gui.textField.TextFieldRound();
        jLabel12 = new javax.swing.JLabel();
        txt_usuario = new org.edisoncor.gui.textField.TextFieldRound();
        jLabel13 = new javax.swing.JLabel();
        txt_apellidos = new org.edisoncor.gui.textField.TextFieldRound();
        jLabel14 = new javax.swing.JLabel();
        txt_nombre = new org.edisoncor.gui.textField.TextFieldRound();
        jLabel15 = new javax.swing.JLabel();
        txt_cedula = new org.edisoncor.gui.textField.TextFieldRound();
        jLabel16 = new javax.swing.JLabel();
        cbx_seccion = new org.edisoncor.gui.comboBox.ComboBoxRectIcon();
        panelCurves2 = new org.edisoncor.gui.panel.PanelCurves();
        jLabel17 = new javax.swing.JLabel();
        txt_telefono = new org.edisoncor.gui.textField.TextFieldRound();
        jLabel18 = new javax.swing.JLabel();
        txt_contraseña = new org.edisoncor.gui.passwordField.PasswordFieldRound();
        btnRegistrar = new rojeru_san.RSButtonRiple();
        txt_dir = new org.edisoncor.gui.textField.TextFieldRound();
        jLabel4 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        panelReflect5 = new org.edisoncor.gui.panel.PanelReflect();
        jLabel3 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        buttonRect1 = new org.edisoncor.gui.button.ButtonRect();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        btnRegistrarse = new rojeru_san.RSButtonRiple();
        panel1 = new org.edisoncor.gui.panel.Panel();
        jLabel5 = new javax.swing.JLabel();
        buttonTask1 = new org.edisoncor.gui.button.ButtonTask();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Panel de Administrador");
        setIconImage(UtilidadesComponente.obtenerIcono());
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(null);

        panelSelector.setBackground(new java.awt.Color(255, 255, 255));
        panelSelector.setForeground(new java.awt.Color(0, 0, 0));
        panelSelector.setColorBackGround(new java.awt.Color(148, 169, 169));
        panelSelector.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(null);

        panelReflect1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panelReflect1.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Cantarell", 1, 15)); // NOI18N
        jLabel1.setText("Bibliotecarios");
        panelReflect1.add(jLabel1);
        jLabel1.setBounds(240, 30, 120, 20);

        tabla_bibliotecarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tabla_bibliotecarios);

        panelReflect1.add(jScrollPane1);
        jScrollPane1.setBounds(30, 80, 530, 260);

        buttonAeroLeft3.setBackground(new java.awt.Color(148, 169, 169));
        buttonAeroLeft3.setText("Nuevo");
        buttonAeroLeft3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAeroLeft3ActionPerformed(evt);
            }
        });
        panelReflect1.add(buttonAeroLeft3);
        buttonAeroLeft3.setBounds(140, 360, 110, 25);

        buttonAeroRight7.setBackground(new java.awt.Color(148, 169, 169));
        buttonAeroRight7.setText("Dar de baja");
        panelReflect1.add(buttonAeroRight7);
        buttonAeroRight7.setBounds(340, 360, 120, 25);

        buttonAero1.setBackground(new java.awt.Color(148, 169, 169));
        buttonAero1.setText("Editar");
        buttonAero1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAero1ActionPerformed(evt);
            }
        });
        panelReflect1.add(buttonAero1);
        buttonAero1.setBounds(260, 360, 73, 25);

        jPanel2.add(panelReflect1);
        panelReflect1.setBounds(0, 0, 590, 420);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/imagenes/ue_biblioteca-mediateca-icono.png"))); // NOI18N
        jLabel2.setText("jLabel2");
        jPanel2.add(jLabel2);
        jLabel2.setBounds(360, 160, 510, 330);

        panelSelector.addTab("Bibliotecarios", jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(null);
        jPanel3.add(panelCurves1);
        panelCurves1.setBounds(0, 0, 0, 0);

        jLabel6.setFont(new java.awt.Font("Copperplate Gothic Light", 0, 11)); // NOI18N
        jLabel6.setText("Telefono:");
        jPanel3.add(jLabel6);
        jLabel6.setBounds(300, 70, 70, 20);
        jPanel3.add(txt_coreo);
        txt_coreo.setBounds(70, 120, 500, 30);

        jLabel12.setFont(new java.awt.Font("Copperplate Gothic Light", 0, 11)); // NOI18N
        jLabel12.setText("Nombre:");
        jPanel3.add(jLabel12);
        jLabel12.setBounds(10, 20, 70, 20);
        jPanel3.add(txt_usuario);
        txt_usuario.setBounds(240, 270, 160, 30);

        jLabel13.setFont(new java.awt.Font("Copperplate Gothic Light", 0, 11)); // NOI18N
        jLabel13.setText("Apellidos:");
        jPanel3.add(jLabel13);
        jLabel13.setBounds(300, 20, 70, 20);
        jPanel3.add(txt_apellidos);
        txt_apellidos.setBounds(370, 20, 200, 30);

        jLabel14.setFont(new java.awt.Font("Copperplate Gothic Light", 0, 11)); // NOI18N
        jLabel14.setText("Seccion:");
        jPanel3.add(jLabel14);
        jLabel14.setBounds(180, 230, 70, 20);
        jPanel3.add(txt_nombre);
        txt_nombre.setBounds(70, 20, 220, 30);

        jLabel15.setFont(new java.awt.Font("Copperplate Gothic Light", 0, 11)); // NOI18N
        jLabel15.setText("Cedula:");
        jPanel3.add(jLabel15);
        jLabel15.setBounds(10, 70, 70, 20);
        jPanel3.add(txt_cedula);
        txt_cedula.setBounds(70, 70, 220, 30);

        jLabel16.setFont(new java.awt.Font("Copperplate Gothic Light", 0, 11)); // NOI18N
        jLabel16.setText("Contraseña:");
        jPanel3.add(jLabel16);
        jLabel16.setBounds(160, 310, 100, 20);

        cbx_seccion.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Matutina", "Vespertina", "" }));
        jPanel3.add(cbx_seccion);
        cbx_seccion.setBounds(240, 230, 160, 20);
        jPanel3.add(panelCurves2);
        panelCurves2.setBounds(0, 0, 120, 0);

        jLabel17.setFont(new java.awt.Font("Copperplate Gothic Light", 0, 11)); // NOI18N
        jLabel17.setText("Correo:");
        jPanel3.add(jLabel17);
        jLabel17.setBounds(10, 120, 70, 20);
        jPanel3.add(txt_telefono);
        txt_telefono.setBounds(370, 70, 200, 30);

        jLabel18.setFont(new java.awt.Font("Copperplate Gothic Light", 0, 11)); // NOI18N
        jLabel18.setText("Usuario:");
        jPanel3.add(jLabel18);
        jLabel18.setBounds(180, 270, 70, 20);
        jPanel3.add(txt_contraseña);
        txt_contraseña.setBounds(240, 310, 160, 30);

        btnRegistrar.setBackground(new java.awt.Color(255, 255, 255));
        btnRegistrar.setBorder(null);
        btnRegistrar.setText("Guardar");
        btnRegistrar.setColorHover(new java.awt.Color(169, 169, 169));
        btnRegistrar.setColorText(new java.awt.Color(0, 0, 0));
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });
        jPanel3.add(btnRegistrar);
        btnRegistrar.setBounds(260, 360, 110, 40);

        txt_dir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_dirActionPerformed(evt);
            }
        });
        jPanel3.add(txt_dir);
        txt_dir.setBounds(80, 170, 490, 30);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/imagenes/ue_biblioteca-mediateca-icono.png"))); // NOI18N
        jLabel4.setText("jLabel2");
        jPanel3.add(jLabel4);
        jLabel4.setBounds(310, 170, 510, 330);

        jLabel19.setFont(new java.awt.Font("Copperplate Gothic Light", 0, 11)); // NOI18N
        jLabel19.setText("Dirección:");
        jPanel3.add(jLabel19);
        jLabel19.setBounds(10, 170, 70, 20);

        panelSelector.addTab("Añadir Bibliotecario", jPanel3);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(null);

        panelReflect5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panelReflect5.setLayout(null);

        jLabel3.setFont(new java.awt.Font("Cantarell", 1, 15)); // NOI18N
        jLabel3.setText("Buscar :");
        panelReflect5.add(jLabel3);
        jLabel3.setBounds(10, 20, 90, 20);
        panelReflect5.add(jTextField2);
        jTextField2.setBounds(70, 20, 280, 20);

        jRadioButton3.setBackground(new java.awt.Color(255, 255, 255));
        jRadioButton3.setText("Profesor");
        panelReflect5.add(jRadioButton3);
        jRadioButton3.setBounds(370, 20, 67, 23);

        jRadioButton4.setBackground(new java.awt.Color(255, 255, 255));
        jRadioButton4.setText("Alumno");
        panelReflect5.add(jRadioButton4);
        jRadioButton4.setBounds(450, 20, 61, 23);

        jPanel4.add(panelReflect5);
        panelReflect5.setBounds(10, 10, 590, 70);

        buttonRect1.setBackground(new java.awt.Color(148, 169, 169));
        buttonRect1.setText("Dar de baja");
        jPanel4.add(buttonRect1);
        buttonRect1.setBounds(240, 360, 111, 30);

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(jTable3);

        jPanel4.add(jScrollPane3);
        jScrollPane3.setBounds(10, 90, 560, 260);

        btnRegistrarse.setBackground(new java.awt.Color(240, 240, 240));
        btnRegistrarse.setBorder(null);
        btnRegistrarse.setText("Regístrate!");
        btnRegistrarse.setColorHover(new java.awt.Color(169, 169, 169));
        btnRegistrarse.setColorText(new java.awt.Color(0, 0, 0));
        btnRegistrarse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarseActionPerformed(evt);
            }
        });
        jPanel4.add(btnRegistrarse);
        btnRegistrarse.setBounds(80, 300, 80, 20);

        panelSelector.addTab("Usuarios", jPanel4);

        jPanel1.add(panelSelector);
        panelSelector.setBounds(10, 50, 750, 430);

        panel1.setForeground(new java.awt.Color(0, 102, 102));
        panel1.setColorPrimario(new java.awt.Color(240, 240, 240));
        panel1.setColorSecundario(new java.awt.Color(148, 169, 169));
        panel1.setLayout(null);

        jLabel5.setFont(new java.awt.Font("Sitka Subheading", 0, 24)); // NOI18N
        jLabel5.setText("Administrador");
        panel1.add(jLabel5);
        jLabel5.setBounds(370, 0, 380, 50);

        buttonTask1.setBackground(new java.awt.Color(148, 169, 169));
        buttonTask1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/imagenes/Iconolgout.png"))); // NOI18N
        buttonTask1.setText("Cerrar Sesion");
        buttonTask1.setDescription("de administrador");
        buttonTask1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonTask1ActionPerformed(evt);
            }
        });
        panel1.add(buttonTask1);
        buttonTask1.setBounds(10, 0, 200, 50);

        jPanel1.add(panel1);
        panel1.setBounds(0, 0, 760, 50);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(-10, 0, 760, 480);

        setSize(new java.awt.Dimension(766, 515));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegistrarseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarseActionPerformed


    }//GEN-LAST:event_btnRegistrarseActionPerformed

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        registrar();
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void buttonAeroLeft3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAeroLeft3ActionPerformed
        panelSelector.setSelectedIndex(1);
        
    }//GEN-LAST:event_buttonAeroLeft3ActionPerformed

    private void buttonAero1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAero1ActionPerformed
        panelSelector.setSelectedIndex(1);
        cargarEdicion();
    }//GEN-LAST:event_buttonAero1ActionPerformed

    private void txt_dirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_dirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_dirActionPerformed

    private void buttonTask1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonTask1ActionPerformed
        dispose();
        new FrmInicioSesion().setVisible(true);
    }//GEN-LAST:event_buttonTask1ActionPerformed

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
            java.util.logging.Logger.getLogger(FrmAdministrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmAdministrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmAdministrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmAdministrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FrmAdministrador dialog = new FrmAdministrador(new javax.swing.JFrame(), true);
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
    private rojeru_san.RSButtonRiple btnRegistrar;
    private rojeru_san.RSButtonRiple btnRegistrarse;
    private org.edisoncor.gui.button.ButtonAero buttonAero1;
    private org.edisoncor.gui.button.ButtonAeroLeft buttonAeroLeft3;
    private org.edisoncor.gui.button.ButtonAeroRight buttonAeroRight7;
    private org.edisoncor.gui.button.ButtonRect buttonRect1;
    private org.edisoncor.gui.button.ButtonTask buttonTask1;
    private org.edisoncor.gui.comboBox.ComboBoxRectIcon cbx_seccion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable3;
    private javax.swing.JTextField jTextField2;
    private org.edisoncor.gui.panel.Panel panel1;
    private org.edisoncor.gui.panel.PanelCurves panelCurves1;
    private org.edisoncor.gui.panel.PanelCurves panelCurves2;
    private org.edisoncor.gui.panel.PanelReflect panelReflect1;
    private org.edisoncor.gui.panel.PanelReflect panelReflect5;
    private org.edisoncor.gui.tabbedPane.TabbedSelector2 panelSelector;
    private javax.swing.JTable tabla_bibliotecarios;
    private org.edisoncor.gui.textField.TextFieldRound txt_apellidos;
    private org.edisoncor.gui.textField.TextFieldRound txt_cedula;
    private org.edisoncor.gui.passwordField.PasswordFieldRound txt_contraseña;
    private org.edisoncor.gui.textField.TextFieldRound txt_coreo;
    private org.edisoncor.gui.textField.TextFieldRound txt_dir;
    private org.edisoncor.gui.textField.TextFieldRound txt_nombre;
    private org.edisoncor.gui.textField.TextFieldRound txt_telefono;
    private org.edisoncor.gui.textField.TextFieldRound txt_usuario;
    // End of variables declaration//GEN-END:variables
}
