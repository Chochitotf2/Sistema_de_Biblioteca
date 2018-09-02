package vista;

import controlador.servicio.AlumnoServicio;
import controlador.servicio.CuentaServicio;
import controlador.servicio.PersonaServicio;
import controlador.servicio.RolServicio;
import it.sauronsoftware.base64.Base64;
import java.awt.Color;
import java.util.Date;
import javax.swing.ButtonGroup;
import static vista.utilidades.UtilidadesComponente.*;

/**
 *
 * @author Víctor Andrés Rojas
 * @author Mario Orellana
 */
public class FrmRegistro extends javax.swing.JDialog {

    private PersonaServicio pS = new PersonaServicio();
    private AlumnoServicio aS = new AlumnoServicio();
    private CuentaServicio cS = new CuentaServicio();

    /**
     * Creates new form FrmIniciarSesion
     */
    public FrmRegistro(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        ButtonGroup grupo = new ButtonGroup();
        grupo.add(rdAlumno);
        grupo.add(rdProfesor);
        limpiar();
    }

    private void habilitarCampos() {
        if (rdAlumno.isSelected()) {
            cbxCiclo.setEnabled(true);
            cbxCarrera.setEnabled(true);
            txtParalelo.setEnabled(true);
        } else if (rdProfesor.isSelected()) {
            cbxCiclo.setEnabled(false);
            cbxCarrera.setEnabled(false);
            txtParalelo.setEnabled(false);
            txtParalelo.setBackground(Color.WHITE);
        }
    }

    private void limpiar() {
        pS.fijarPersona(null);
        aS.fijarAlumno(null);
        cS.fijarCuenta(null);
        txtNombre.setText(null);
        txtApellido.setText(null);
        txtUsuario.setText(null);
        txtClave.setText(null);
        txtCorreoElectronico.setText(null);
        txtDni.setText(null);
        txtDireccion.setText(null);
        txtTelefono.setText(null);
        cbxCiclo.setSelectedIndex(0);
        cbxCarrera.setSelectedIndex(0);
        txtParalelo.setText(null);
        habilitarCampos();
    }

    private void cargarObjeto() {
        cS.obtenerCuenta().setUsuario(txtUsuario.getText().trim());
        cS.obtenerCuenta().setClave(String.valueOf(Base64.encode(txtClave.getText().trim())));
        cS.obtenerCuenta().setCreadoEn(new Date());
        cS.obtenerCuenta().setModificadoEn(new Date());
        if (rdAlumno.isSelected()) {
            aS.obtenerAlumno().setNombres(txtNombre.getText().trim());
            aS.obtenerAlumno().setApellidos(txtApellido.getText().trim());
            aS.obtenerAlumno().setCorreo(txtCorreoElectronico.getText().trim());
            aS.obtenerAlumno().setDni(txtDni.getText().trim());
            aS.obtenerAlumno().setDireccion(txtDireccion.getText().trim());
            aS.obtenerAlumno().setTelefono(txtTelefono.getText().trim());
            aS.obtenerAlumno().setCiclo(cbxCiclo.getSelectedItem().toString().toUpperCase());
            aS.obtenerAlumno().setParalelo(txtParalelo.getText().trim().charAt(0));
            aS.obtenerAlumno().setCarrera(cbxCarrera.getSelectedItem().toString());
            aS.obtenerAlumno().setRol(new RolServicio().buscarRol("Alumno"));
            aS.obtenerAlumno().setCuenta(cS.obtenerCuenta());
            cS.obtenerCuenta().setPersona(aS.obtenerAlumno());
        } else if (rdProfesor.isSelected()) {
            pS.obtenerPersona().setNombres(txtNombre.getText().trim());
            pS.obtenerPersona().setApellidos(txtApellido.getText().trim());
            pS.obtenerPersona().setCorreo(txtCorreoElectronico.getText().trim());
            pS.obtenerPersona().setDni(txtDni.getText().trim());
            pS.obtenerPersona().setDireccion(txtDireccion.getText().trim());
            pS.obtenerPersona().setTelefono(txtTelefono.getText().trim());
            pS.obtenerPersona().setRol(new RolServicio().buscarRol("Profesor"));
            pS.obtenerPersona().setCuenta(cS.obtenerCuenta());
            cS.obtenerCuenta().setPersona(pS.obtenerPersona());
        }
    }

    private boolean errores() {
        boolean verificador = false;
        if (mostrarError(txtNombre, "El Nombre solo puede contener letras.", 't')) {
            verificador = true;
        }
        if (mostrarError(txtApellido, "El Apellido solo puede contener letras.", 't')) {
            verificador = true;
        }
        if (mostrarError(txtUsuario, null, 'n')) {
            verificador = true;
        }
        if (mostrarError(txtClave, null, 'n')) {
            verificador = true;
        } else if (String.valueOf(txtClave.getPassword()).trim().length() < 6) {
            mensajeError("Seguridad", "Por seguridad se recomienda una contraseña que mínimo contenga 6 caractéres.");
            verificador = true;
        }
        if (mostrarError(txtCorreoElectronico, "El Email ingresado no es válido.", 'm')) {
            verificador = true;
        }
        if (mostrarError(txtDni, "La cédula ingresada no es válida.", 'c')) {
            verificador = true;
        }
        if (mostrarError(txtDireccion, null, 'n')) {
            verificador = true;
        }
        if (mostrarError(txtTelefono, "El Teléfono solo puede contener números.", 'e')) {
            verificador = true;
        }
        if (rdAlumno.isSelected()) {
            if (mostrarError(txtParalelo, "El Paralelo solo puede contener un caracter.", 'u')) {
                verificador = true;
            }
        }
        return verificador;
    }

    private void registrar() {
        if (!errores()) {
            cargarObjeto();
            if (cS.obtenerUsuarioCuenta(txtUsuario.getText().trim()) == null) {
                if (rdAlumno.isSelected()) {
                    if (aS.obtenerAlumnoCedula(txtDni.getText().trim()) == null) {
                        if (aS.guardar()) {
                            mensajeOK("Aviso", "Se ha registrado con éxito.");
                            limpiar();
                            dispose();
                            new FrmInicioSesion().setVisible(true);
                        } else {
                            mensajeError("Error", "Ha ocurrido un error al realizar su registro.");
                        }
                    } else {
                        mensajeError("Error", "La cédula escrita ya existe.");
                    }
                } else if (rdProfesor.isSelected()) {
                    if (pS.obtenerPersonaCedula(txtDni.getText().trim()) == null) {
                        if (pS.guardar()) {
                            mensajeOK("Aviso", "Se ha registrado con éxito.");
                            limpiar();
                            new FrmInicioSesion().setVisible(true);
                        } else {
                            mensajeError("Error", "Ha ocurrido un error al realizar su registro.");
                        }
                    } else {
                        mensajeError("Error", "La cédula escrita ya existe.");
                    }
                }
            } else {
                mensajeError("Error", "El nombre de usuario ya existe. Elija otro.");
            }

        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
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
        txtParalelo = new rojeru_san.RSMTextFull();
        jSeparator1 = new javax.swing.JSeparator();
        btnRegistrarse = new rojeru_san.RSButtonRiple();
        btnRegresar = new org.edisoncor.gui.button.ButtonAction();
        cbxCarrera = new javax.swing.JComboBox<>();
        cbxCiclo = new javax.swing.JComboBox<>();
        rdProfesor = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        rdAlumno = new javax.swing.JRadioButton();
        txtTelefono = new rojeru_san.RSMTextFull();
        panelCurves1 = new org.edisoncor.gui.panel.PanelCurves();
        btnCerrar = new javax.swing.JToggleButton();
        jLabel1 = new javax.swing.JLabel();

        jLabel3.setText("jLabel3");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Registrarse");
        setIconImage(obtenerIcono());
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(null);

        jPanel2.setBackground(new java.awt.Color(80, 80, 80));
        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.setLayout(null);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Nuevo Registro");
        jPanel2.add(jLabel5);
        jLabel5.setBounds(140, 10, 210, 30);

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

        txtParalelo.setBordeColorFocus(new java.awt.Color(169, 169, 169));
        txtParalelo.setBotonColor(new java.awt.Color(169, 169, 169));
        txtParalelo.setPlaceholder("Paralelo");
        jPanel2.add(txtParalelo);
        txtParalelo.setBounds(250, 250, 200, 40);
        jPanel2.add(jSeparator1);
        jSeparator1.setBounds(140, 340, 210, 10);

        btnRegistrarse.setText("Registrarse");
        btnRegistrarse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarseActionPerformed(evt);
            }
        });
        jPanel2.add(btnRegistrarse);
        btnRegistrarse.setBounds(160, 350, 170, 40);

        btnRegresar.setLabel("←Regresar");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });
        jPanel2.add(btnRegresar);
        btnRegresar.setBounds(10, 10, 120, 30);

        cbxCarrera.setForeground(new java.awt.Color(0, 112, 192));
        cbxCarrera.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ingeniería en Sistemas", "Ingeniería en Electrónica y Telecomunicaciones", "Ingeniería Electromecánica", "Ingeniería en Geología Ambiental y Ordenamiento Territorial" }));
        jPanel2.add(cbxCarrera);
        cbxCarrera.setBounds(110, 290, 300, 40);

        cbxCiclo.setForeground(new java.awt.Color(0, 112, 192));
        cbxCiclo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Primero", "Segundo", "Tercero", "Cuarto", "Quinto", "Sexto", "Séptimo", "Octavo", "Noveno", "Décimo" }));
        jPanel2.add(cbxCiclo);
        cbxCiclo.setBounds(50, 250, 200, 40);

        rdProfesor.setForeground(new java.awt.Color(240, 240, 240));
        rdProfesor.setText("Profesor");
        rdProfesor.setOpaque(false);
        rdProfesor.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rdProfesorItemStateChanged(evt);
            }
        });
        jPanel2.add(rdProfesor);
        rdProfesor.setBounds(270, 220, 70, 20);

        jLabel2.setForeground(new java.awt.Color(240, 240, 240));
        jLabel2.setText("Usted es:");
        jPanel2.add(jLabel2);
        jLabel2.setBounds(150, 220, 50, 20);

        rdAlumno.setForeground(new java.awt.Color(240, 240, 240));
        rdAlumno.setSelected(true);
        rdAlumno.setText("Alumno");
        rdAlumno.setOpaque(false);
        rdAlumno.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rdAlumnoItemStateChanged(evt);
            }
        });
        jPanel2.add(rdAlumno);
        rdAlumno.setBounds(200, 220, 70, 20);

        txtTelefono.setBordeColorFocus(new java.awt.Color(169, 169, 169));
        txtTelefono.setBotonColor(new java.awt.Color(169, 169, 169));
        txtTelefono.setPlaceholder("Teléfono");
        jPanel2.add(txtTelefono);
        txtTelefono.setBounds(252, 172, 200, 40);
        jPanel2.add(panelCurves1);
        panelCurves1.setBounds(0, 0, 490, 410);

        jPanel1.add(jPanel2);
        jPanel2.setBounds(50, 20, 490, 410);

        btnCerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/imagenes/Cerrar.png"))); // NOI18N
        btnCerrar.setContentAreaFilled(false);
        btnCerrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });
        jPanel1.add(btnCerrar);
        btnCerrar.setBounds(570, 0, 30, 30);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/imagenes/LibroFondo.jpg"))); // NOI18N
        jLabel1.setText("jLabel1");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(0, 0, 600, 450);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 600, 450);

        setSize(new java.awt.Dimension(600, 449));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        dispose();
        new FrmInicioSesion().setVisible(true);
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void btnRegistrarseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarseActionPerformed
        registrar();
    }//GEN-LAST:event_btnRegistrarseActionPerformed

    private void rdAlumnoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rdAlumnoItemStateChanged
        habilitarCampos();
    }//GEN-LAST:event_rdAlumnoItemStateChanged

    private void rdProfesorItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rdProfesorItemStateChanged
        habilitarCampos();
    }//GEN-LAST:event_rdProfesorItemStateChanged

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnCerrarActionPerformed

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
    private javax.swing.JToggleButton btnCerrar;
    private rojeru_san.RSButtonRiple btnRegistrarse;
    private org.edisoncor.gui.button.ButtonAction btnRegresar;
    private javax.swing.JComboBox<String> cbxCarrera;
    private javax.swing.JComboBox<String> cbxCiclo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private org.edisoncor.gui.panel.PanelCurves panelCurves1;
    private javax.swing.JRadioButton rdAlumno;
    private javax.swing.JRadioButton rdProfesor;
    private rojeru_san.RSMTextFull txtApellido;
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
