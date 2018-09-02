package vista;

import controlador.servicio.BibliotecarioServicio;
import controlador.servicio.CuentaServicio;
import controlador.servicio.PersonaServicio;
import controlador.servicio.RolServicio;
import it.sauronsoftware.base64.Base64;
import java.util.Date;
import static java.util.stream.Collectors.toList;
import javax.swing.ButtonGroup;
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
        initComponents();
        ButtonGroup g2 = new ButtonGroup();
        g2.add(rd_alumno);
        g2.add(rd_todos);
        g2.add(rd_profe1);
        limpiar();
    }

    private void cargartablabiblo() {
        modeloBiblio.setLista(bS.listar().stream()
                .sorted((a, b) -> b.getCuenta().getEstado().compareTo(a.getCuenta().getEstado())).collect(toList()));
        tabla_bibliotecarios.setModel(modeloBiblio);
        tabla_bibliotecarios.updateUI();
    }

    private void darDeBajaBiblio() {
        int fila = tabla_bibliotecarios.getSelectedRow();
        if (fila >= 0) {
            bS.fijarBibliotecario(modeloBiblio.getLista().get(fila));
            if (bS.obtenerBibliotecario().getCuenta().getEstado()) {
                bS.obtenerBibliotecario().getCuenta().setEstado(false);
                if (bS.guardar()) {
                    mensajeOK("Aviso", "Se a desactivado la cuenta.");
                    limpiar();
                } else {
                    mensajeError("Error", "No se ha podido desactivar la cuenta.");
                }
            } else {
                bS.obtenerBibliotecario().getCuenta().setEstado(true);
                if (bS.guardar()) {
                    mensajeOK("Aviso", "Se a reactivado la cuenta.");
                    limpiar();
                } else {
                    mensajeError("Error", "No se ha podido reactivar la cuenta.");
                }
            }
        } else {
            mensajeError("Advertencia", "Debe seleccionar un Bibliotecario de la tabla.");
        }
    }

    private void darDeBajaUser() {
        int fila = tbl_user.getSelectedRow();
        if (fila >= 0) {
            ps.fijarPersona(modelouser.getLista().get(fila));
            if (ps.obtenerPersona().getCuenta().getEstado()) {
                ps.obtenerPersona().getCuenta().setEstado(false);
                if (ps.guardar()) {
                    mensajeOK("Aviso", "Se a desactivado la cuenta.");
                    limpiar();
                } else {
                    mensajeError("Error", "No se ha podido desactivar la cuenta.");
                }
            } else {
                ps.obtenerPersona().getCuenta().setEstado(true);
                if (ps.guardar()) {
                    mensajeOK("Aviso", "Se a reactivado la cuenta.");
                    limpiar();
                } else {
                    mensajeError("Error", "No se ha podido reactivar la cuenta.");
                }
            }
        } else {
            mensajeError("Advertencia", "Debe seleccionar un Usuario de la tabla.");
        }
    }

    private void cargarTablaUser() {
        modelouser.setLista(ps.listarSinAdministradorUsuarios().stream()
                .sorted((a, b) -> b.getCuenta().getEstado().compareTo(a.getCuenta().getEstado())).collect(toList()));
        tbl_user.setModel(modelouser);
        tbl_user.updateUI();
    }

    private void listarUsuariosTipo() {
        if (rd_todos.isSelected()) {
            cargarTablaUser();
        } else {
            String tipo = (rd_profe1.isSelected()) ? "Profesor" : "Alumno";
            modelouser.setLista(ps.listarUsuariosTipo(tipo));
            tbl_user.setModel(modelouser);
            tbl_user.updateUI();
        }
    }

    private void limpiar() {
        bS.fijarBibliotecario(null);
        ps.fijarPersona(null);
        cs.fijarCuenta(null);
        txt_nombre.setText("");
        txt_apellidos.setText("");
        txt_cedula.setText("");
        txt_cedula.setEditable(true);
        txt_telefono.setText("");
        txt_coreo.setText("");
        txt_dir.setText("");
        txt_usuario.setText("");
        txt_usuario.setEditable(true);
        txt_contraseña.setText("");
        cbx_seccion.setSelectedItem(0);
        cargartablabiblo();
        cargarTablaUser();
        listarUsuariosTipo();
    }

    public void cargarobjeto() {
        cs.obtenerCuenta().setUsuario(txt_usuario.getText().trim());
        cs.obtenerCuenta().setClave(Base64.encode(String.valueOf(txt_contraseña.getPassword())));
        cs.obtenerCuenta().setCreadoEn(new Date());
        cs.obtenerCuenta().setModificadoEn(new Date());
        bS.obtenerBibliotecario().setNombres(txt_nombre.getText().trim());
        bS.obtenerBibliotecario().setApellidos(txt_apellidos.getText().trim());
        bS.obtenerBibliotecario().setDni(txt_cedula.getText().trim());
        bS.obtenerBibliotecario().setTelefono(txt_telefono.getText().trim());
        bS.obtenerBibliotecario().setCorreo(txt_coreo.getText().trim());
        bS.obtenerBibliotecario().setDireccion(txt_dir.getText().trim());
        bS.obtenerBibliotecario().setSeccion(cbx_seccion.getSelectedItem().toString());
        bS.obtenerBibliotecario().setRol(new RolServicio().buscarRol("Bibliotecario"));
        bS.obtenerBibliotecario().setCuenta(cs.obtenerCuenta());
        cs.obtenerCuenta().setPersona(bS.obtenerBibliotecario());
    }

    private void cargarEdicion() {
        int fila = tabla_bibliotecarios.getSelectedRow();
        if (fila >= 0) {
            bS.fijarBibliotecario(modeloBiblio.getLista().get(fila));
            txt_nombre.setText(bS.obtenerBibliotecario().getNombres());
            txt_apellidos.setText(bS.obtenerBibliotecario().getApellidos());
            txt_cedula.setText(bS.obtenerBibliotecario().getDni());
            txt_cedula.setEditable(false);
            txt_telefono.setText(bS.obtenerBibliotecario().getTelefono());
            txt_coreo.setText(bS.obtenerBibliotecario().getCorreo());
            txt_dir.setText(bS.obtenerBibliotecario().getDireccion());
            cbx_seccion.setSelectedItem(bS.obtenerBibliotecario().getSeccion());
            txt_usuario.setEditable(false);
            txt_usuario.setText(bS.obtenerBibliotecario().getCuenta().getUsuario());
            txt_contraseña.setText(bS.obtenerBibliotecario().getCuenta().getClave());
        } else {
            mensajeError("Advertencia", "Debe seleccionar un Bibliotecario de la Tabla.");
        }
    }

    private void registrar() {
        if (!errores()) {
            cargarobjeto();
            if (bS.obtenerBibliotecario().getId() != null) {
                if (bS.guardar()) {
                    mensajeOK("Aviso", "Se ha modificado con éxito.");
                    limpiar();
                    panelSelector.setSelectedIndex(0);
                } else {
                    mensajeError("Error", "Ha ocurrido un error al realizar la modificación.");
                }
            } else {
                if (bS.obtenerBibliotecarioCedula(txt_cedula.getText().trim()) == null) {
                    if (bS.guardar()) {
                        mensajeOK("Aviso", "Se ha registrado con éxito.");
                        limpiar();
                        panelSelector.setSelectedIndex(0);
                    } else {
                        mensajeError("Error", "Ha ocurrido un error al realizar el registro.");
                    }
                } else {
                    mensajeError("Error", "La cédula escrita ya existe.");
                }
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

    private void buscarBibliotecario() {
        if (txtBuscarBibliotecario.getText().trim().length() >= 3) {
            modeloBiblio.setLista(bS.listarBibliotecarioLike(txtBuscarBibliotecario.getText().trim()).stream()
                    .sorted((a, b) -> b.getCuenta().getEstado().compareTo(a.getCuenta().getEstado())).collect(toList()));
            tabla_bibliotecarios.setModel(modeloBiblio);
            tabla_bibliotecarios.updateUI();
        } else {
            cargartablabiblo();
        }
    }

    private void buscarUsuario() {
        if (txt_buscarUser.getText().trim().length() >= 3) {
            if (rd_todos.isSelected()) {
                modelouser.setLista(ps.listarSinAdministradorUsuariosLike(txt_buscarUser.getText().trim()));
            } else {
                String tipo = (rd_profe1.isSelected()) ? "Profesor" : "Alumno";
                modelouser.setLista(ps.listarUsuariosTipoLike(tipo, txt_buscarUser.getText().trim()));
            }
            tbl_user.setModel(modelouser);
            tbl_user.updateUI();
        } else {
            cargarTablaUser();
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
        jLabel7 = new javax.swing.JLabel();
        txtBuscarBibliotecario = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
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
        jLabel17 = new javax.swing.JLabel();
        txt_telefono = new org.edisoncor.gui.textField.TextFieldRound();
        jLabel18 = new javax.swing.JLabel();
        txt_contraseña = new org.edisoncor.gui.passwordField.PasswordFieldRound();
        btnRegistrar = new rojeru_san.RSButtonRiple();
        btnCancelar = new rojeru_san.RSButtonRiple();
        txt_dir = new org.edisoncor.gui.textField.TextFieldRound();
        jLabel4 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        panelReflect5 = new org.edisoncor.gui.panel.PanelReflect();
        jLabel3 = new javax.swing.JLabel();
        txt_buscarUser = new javax.swing.JTextField();
        rd_todos = new javax.swing.JRadioButton();
        rd_alumno = new javax.swing.JRadioButton();
        rd_profe1 = new javax.swing.JRadioButton();
        buttonRect1 = new org.edisoncor.gui.button.ButtonRect();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl_user = new javax.swing.JTable();
        panel1 = new org.edisoncor.gui.panel.Panel();
        jLabel5 = new javax.swing.JLabel();
        buttonTask1 = new org.edisoncor.gui.button.ButtonTask();
        jToggleButton1 = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Panel de Administrador");
        setIconImage(UtilidadesComponente.obtenerIcono());
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(null);

        panelSelector.setBackground(new java.awt.Color(51, 51, 51));
        panelSelector.setForeground(new java.awt.Color(0, 0, 0));
        panelSelector.setColorBackGround(new java.awt.Color(255, 255, 255));
        panelSelector.setColorDeBorde(new java.awt.Color(0, 0, 0));
        panelSelector.setColorDeSegundoBorde(new java.awt.Color(0, 0, 0));
        panelSelector.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        panelSelector.setListColor(new java.awt.Color(51, 51, 51));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(null);

        panelReflect1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panelReflect1.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Cantarell", 1, 15)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Bibliotecarios");
        panelReflect1.add(jLabel1);
        jLabel1.setBounds(0, 0, 590, 20);

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
        jScrollPane1.setBounds(30, 80, 530, 250);

        buttonAeroLeft3.setBackground(new java.awt.Color(148, 169, 169));
        buttonAeroLeft3.setText("Nuevo");
        buttonAeroLeft3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAeroLeft3ActionPerformed(evt);
            }
        });
        panelReflect1.add(buttonAeroLeft3);
        buttonAeroLeft3.setBounds(90, 360, 110, 25);

        buttonAeroRight7.setBackground(new java.awt.Color(148, 169, 169));
        buttonAeroRight7.setText("Dar de baja / Reactivar");
        buttonAeroRight7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAeroRight7ActionPerformed(evt);
            }
        });
        panelReflect1.add(buttonAeroRight7);
        buttonAeroRight7.setBounds(290, 360, 180, 25);

        buttonAero1.setBackground(new java.awt.Color(148, 169, 169));
        buttonAero1.setText("Editar");
        buttonAero1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAero1ActionPerformed(evt);
            }
        });
        panelReflect1.add(buttonAero1);
        buttonAero1.setBounds(210, 360, 73, 25);

        jLabel7.setFont(new java.awt.Font("Cantarell", 1, 15)); // NOI18N
        jLabel7.setText("Buscar :");
        panelReflect1.add(jLabel7);
        jLabel7.setBounds(80, 40, 90, 20);

        txtBuscarBibliotecario.setToolTipText("Nombre, Apellido, Cédula, Correo Electrónico");
        txtBuscarBibliotecario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarBibliotecarioKeyReleased(evt);
            }
        });
        panelReflect1.add(txtBuscarBibliotecario);
        txtBuscarBibliotecario.setBounds(140, 40, 370, 20);

        jPanel2.add(panelReflect1);
        panelReflect1.setBounds(0, 0, 590, 420);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/imagenes/BuscarDocumento.png"))); // NOI18N
        jLabel2.setText("jLabel2");
        jPanel2.add(jLabel2);
        jLabel2.setBounds(360, 160, 510, 330);

        panelSelector.addTab("Bibliotecarios", jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(null);

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
        btnRegistrar.setBounds(250, 360, 110, 40);

        btnCancelar.setBackground(new java.awt.Color(255, 255, 255));
        btnCancelar.setBorder(null);
        btnCancelar.setText("Cancelar");
        btnCancelar.setColorHover(new java.awt.Color(169, 169, 169));
        btnCancelar.setColorText(new java.awt.Color(0, 0, 0));
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel3.add(btnCancelar);
        btnCancelar.setBounds(460, 360, 110, 40);
        jPanel3.add(txt_dir);
        txt_dir.setBounds(80, 170, 490, 30);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/imagenes/LibroFondo.png"))); // NOI18N
        jLabel4.setText("jLabel2");
        jPanel3.add(jLabel4);
        jLabel4.setBounds(240, 170, 510, 330);

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

        txt_buscarUser.setToolTipText("Nombre, Apellido, Cédula, Correo Electrónico");
        txt_buscarUser.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_buscarUserKeyReleased(evt);
            }
        });
        panelReflect5.add(txt_buscarUser);
        txt_buscarUser.setBounds(70, 20, 230, 20);

        rd_todos.setBackground(new java.awt.Color(255, 255, 255));
        rd_todos.setSelected(true);
        rd_todos.setText("Todos");
        rd_todos.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rd_todosItemStateChanged(evt);
            }
        });
        panelReflect5.add(rd_todos);
        rd_todos.setBounds(310, 20, 55, 23);

        rd_alumno.setBackground(new java.awt.Color(255, 255, 255));
        rd_alumno.setText("Alumno");
        rd_alumno.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rd_alumnoItemStateChanged(evt);
            }
        });
        panelReflect5.add(rd_alumno);
        rd_alumno.setBounds(440, 20, 61, 23);

        rd_profe1.setBackground(new java.awt.Color(255, 255, 255));
        rd_profe1.setText("Profesor");
        rd_profe1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rd_profe1ItemStateChanged(evt);
            }
        });
        panelReflect5.add(rd_profe1);
        rd_profe1.setBounds(370, 20, 67, 23);

        jPanel4.add(panelReflect5);
        panelReflect5.setBounds(10, 10, 590, 70);

        buttonRect1.setBackground(new java.awt.Color(148, 169, 169));
        buttonRect1.setText("Dar de baja / Reactivar");
        buttonRect1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRect1ActionPerformed(evt);
            }
        });
        jPanel4.add(buttonRect1);
        buttonRect1.setBounds(210, 360, 180, 30);

        tbl_user.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(tbl_user);

        jPanel4.add(jScrollPane3);
        jScrollPane3.setBounds(10, 90, 560, 260);

        panelSelector.addTab("Usuarios", jPanel4);

        jPanel1.add(panelSelector);
        panelSelector.setBounds(10, 50, 750, 430);

        panel1.setForeground(new java.awt.Color(255, 255, 255));
        panel1.setColorPrimario(new java.awt.Color(204, 204, 204));
        panel1.setColorSecundario(new java.awt.Color(255, 255, 255));
        panel1.setLayout(null);

        jLabel5.setFont(new java.awt.Font("Sitka Subheading", 0, 24)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Administrador");
        panel1.add(jLabel5);
        jLabel5.setBounds(210, 0, 380, 50);

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

        jToggleButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/imagenes/Cerrar.png"))); // NOI18N
        jToggleButton1.setBorder(null);
        jToggleButton1.setContentAreaFilled(false);
        jToggleButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });
        panel1.add(jToggleButton1);
        jToggleButton1.setBounds(735, 0, 20, 20);

        jPanel1.add(panel1);
        panel1.setBounds(0, 0, 760, 50);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(-10, 0, 760, 480);

        setSize(new java.awt.Dimension(750, 476));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        registrar();
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void buttonAeroLeft3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAeroLeft3ActionPerformed
        panelSelector.setSelectedIndex(1);
        limpiar();
    }//GEN-LAST:event_buttonAeroLeft3ActionPerformed

    private void buttonAero1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAero1ActionPerformed
        cargarEdicion();
        panelSelector.setSelectedIndex(1);
    }//GEN-LAST:event_buttonAero1ActionPerformed

    private void buttonTask1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonTask1ActionPerformed
        dispose();
        new FrmInicioSesion().setVisible(true);
    }//GEN-LAST:event_buttonTask1ActionPerformed

    private void buttonAeroRight7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAeroRight7ActionPerformed
        darDeBajaBiblio();
    }//GEN-LAST:event_buttonAeroRight7ActionPerformed

    private void buttonRect1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRect1ActionPerformed
        darDeBajaUser();
    }//GEN-LAST:event_buttonRect1ActionPerformed

    private void rd_todosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rd_todosItemStateChanged
        listarUsuariosTipo();
    }//GEN-LAST:event_rd_todosItemStateChanged

    private void rd_profe1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rd_profe1ItemStateChanged
        listarUsuariosTipo();
    }//GEN-LAST:event_rd_profe1ItemStateChanged

    private void rd_alumnoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rd_alumnoItemStateChanged
        listarUsuariosTipo();
    }//GEN-LAST:event_rd_alumnoItemStateChanged

    private void txtBuscarBibliotecarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarBibliotecarioKeyReleased
        buscarBibliotecario();
    }//GEN-LAST:event_txtBuscarBibliotecarioKeyReleased

    private void txt_buscarUserKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_buscarUserKeyReleased
        buscarUsuario();
    }//GEN-LAST:event_txt_buscarUserKeyReleased

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        limpiar();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jToggleButton1ActionPerformed

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
    private rojeru_san.RSButtonRiple btnCancelar;
    private rojeru_san.RSButtonRiple btnRegistrar;
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
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JToggleButton jToggleButton1;
    private org.edisoncor.gui.panel.Panel panel1;
    private org.edisoncor.gui.panel.PanelReflect panelReflect1;
    private org.edisoncor.gui.panel.PanelReflect panelReflect5;
    private org.edisoncor.gui.tabbedPane.TabbedSelector2 panelSelector;
    private javax.swing.JRadioButton rd_alumno;
    private javax.swing.JRadioButton rd_profe1;
    private javax.swing.JRadioButton rd_todos;
    private javax.swing.JTable tabla_bibliotecarios;
    private javax.swing.JTable tbl_user;
    private javax.swing.JTextField txtBuscarBibliotecario;
    private org.edisoncor.gui.textField.TextFieldRound txt_apellidos;
    private javax.swing.JTextField txt_buscarUser;
    private org.edisoncor.gui.textField.TextFieldRound txt_cedula;
    private org.edisoncor.gui.passwordField.PasswordFieldRound txt_contraseña;
    private org.edisoncor.gui.textField.TextFieldRound txt_coreo;
    private org.edisoncor.gui.textField.TextFieldRound txt_dir;
    private org.edisoncor.gui.textField.TextFieldRound txt_nombre;
    private org.edisoncor.gui.textField.TextFieldRound txt_telefono;
    private org.edisoncor.gui.textField.TextFieldRound txt_usuario;
    // End of variables declaration//GEN-END:variables
}
