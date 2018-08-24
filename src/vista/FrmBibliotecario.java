/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controlador.servicio.DocumentoNoConvencionalServicio;
import controlador.servicio.DocumentoServicio;
import controlador.servicio.LibroServicio;
import controlador.servicio.RevistaServicio;
import controlador.servicio.TesisServicio;
import java.util.Date;
import javax.swing.ButtonGroup;
import vista.tablas.ModeloTablaDocumento;
import static vista.utilidades.UtilidadesComponente.*;

/**
 *
 * @author user
 */
public class FrmBibliotecario extends javax.swing.JDialog {

    private DocumentoServicio dS = new DocumentoServicio();
    private LibroServicio lS = new LibroServicio();
    private RevistaServicio rS = new RevistaServicio();
    private TesisServicio tS = new TesisServicio();
    private DocumentoNoConvencionalServicio dNCS = new DocumentoNoConvencionalServicio();
    private ModeloTablaDocumento modeloDocumento = new ModeloTablaDocumento();

    /**
     * Creates new form FrmBibliotecario
     */
    public FrmBibliotecario(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        ButtonGroup grupo1 = new ButtonGroup();
        grupo1.add(rdTitulo);
        grupo1.add(rdCodigo);
        limpiar();
    }

    private void cargarTabla() {
        modeloDocumento.setLista(dS.listar());
        tblDocumentos.setModel(modeloDocumento);
        tblDocumentos.updateUI();
    }

    private void habilitarCampos() {
        if (cbxTipodeDocumento.getSelectedItem().equals("Libro")) {
            txtAutor.setEnabled(true);
            sprEdicion.setEnabled(true);
            txtAnio.setEnabled(true);
            txtEditorial.setEnabled(true);
            txtIsbn.setEnabled(true);
        } else if (cbxTipodeDocumento.getSelectedItem().equals("Revista")) {
            txtFechaPublicacion.setEnabled(true);
            txtIssn.setEnabled(true);
            txtEditorial.setEnabled(true);
        } else if (cbxTipodeDocumento.getSelectedItem().equals("Tesis")) {
            txtAutor.setEnabled(true);
            txtAsesor.setEnabled(true);
            txtFacultad.setEnabled(true);
            txtFechaPublicacion.setEnabled(true);
        } else if (cbxTipodeDocumento.getSelectedItem().equals("Documento no Convencional")) {
            txtAutor.setEnabled(true);
            cbxTipoNoConvencional.setEnabled(true);
        }
    }

    private void deshabilitarCampos() {
        txtAutor.setEnabled(false);
        sprEdicion.setEnabled(false);
        txtAnio.setEnabled(false);
        txtEditorial.setEnabled(false);
        txtIsbn.setEnabled(false);
        txtFechaPublicacion.setEnabled(false);
        txtIssn.setEnabled(false);
        txtAsesor.setEnabled(false);
        txtFacultad.setEnabled(false);
        cbxTipoNoConvencional.setEnabled(false);
    }

    private void limpiar() {
        dS.fijarDocumento(null);
        lS.fijarLibro(null);
        rS.fijarRevista(null);
        tS.fijarTesis(null);
        dNCS.fijarDocumentoNoConvensional(null);
        cbxTipodeDocumento.setSelectedIndex(0);
        txtTitulo.setText(null);
        txtAutor.setText(null);
        sprEdicion.setValue(0);
        txtAnio.setText("2018");
        txtEditorial.setText(null);
        txtIsbn.setText(null);
        txtFechaPublicacion.setText("01/01/2018");
        txtIssn.setText(null);
        txtAsesor.setText(null);
        txtFacultad.setText(null);
        deshabilitarCampos();
        habilitarCampos();
        codigoDocumento();
        cargarTabla();
    }

    private void codigoDocumento() {
        String tipo = cbxTipodeDocumento.getSelectedItem().toString().substring(0, 4).toUpperCase() + "-";
        for (int i = 0; i < 3; i++) {
            tipo += "0";
        }
        tipo += (dS.listar().size() + 1);
        txtCodigo.setText(tipo);
    }

    public void cargarobjeto() {
        if (cbxTipodeDocumento.getSelectedItem().equals("Libro")) {
            lS.obtenerLibro().setTitulo(txtTitulo.getText().trim());
            lS.obtenerLibro().setCodigo(txtCodigo.getText().trim());
            lS.obtenerLibro().setTipoDocumento(cbxTipodeDocumento.getSelectedItem().toString());
            lS.obtenerLibro().setAutores(txtAutor.getText().trim());
            lS.obtenerLibro().setEdicion(sprEdicion.getValue().toString());
            lS.obtenerLibro().setAnio(txtAnio.getText().trim());
            lS.obtenerLibro().setEditorial(txtEditorial.getText().trim());
            lS.obtenerLibro().setIsbn(txtIsbn.getText().trim());
        } else if (cbxTipodeDocumento.getSelectedItem().equals("Revista")) {
            rS.obtenerRevista().setTitulo(txtTitulo.getText().trim());
            rS.obtenerRevista().setCodigo(txtCodigo.getText().trim());
            rS.obtenerRevista().setTipoDocumento(cbxTipodeDocumento.getSelectedItem().toString());
            rS.obtenerRevista().setFechaPublicacion(new Date(txtFechaPublicacion.getText()));
            rS.obtenerRevista().setIssn(txtIssn.getText().trim());
            rS.obtenerRevista().setEditorial(txtEditorial.getText().trim());
        } else if (cbxTipodeDocumento.getSelectedItem().equals("Tesis")) {
            tS.obtenerTesis().setTitulo(txtTitulo.getText().trim());
            tS.obtenerTesis().setCodigo(txtCodigo.getText().trim());
            tS.obtenerTesis().setTipoDocumento(cbxTipodeDocumento.getSelectedItem().toString());
            tS.obtenerTesis().setAutores(txtAutor.getText().trim());
            tS.obtenerTesis().setAsesores(txtAsesor.getText().trim());
            tS.obtenerTesis().setFacultad(txtFacultad.getText().trim());
            tS.obtenerTesis().setFechaPublicacion(new Date(txtFechaPublicacion.getText()));
        } else if (cbxTipodeDocumento.getSelectedItem().equals("Documento no Convencional")) {
            dNCS.obtenerDocumentoNoConvensional().setTitulo(txtTitulo.getText().trim());
            dNCS.obtenerDocumentoNoConvensional().setCodigo(txtCodigo.getText().trim());
            dNCS.obtenerDocumentoNoConvensional().setTipoDocumento(cbxTipodeDocumento.getSelectedItem().toString());
            dNCS.obtenerDocumentoNoConvensional().setAutor(txtAutor.getText().trim());
            dNCS.obtenerDocumentoNoConvensional().setTipoNoConvencional(cbxTipoNoConvencional.getSelectedItem().toString());
        }
    }

    private boolean errores() {
        boolean verificador = false;
        if (mostrarError(txtTitulo, null, 'n')) {
            verificador = true;
        }
        if (mostrarError(txtCodigo, null, 'n')) {
            verificador = true;
        }
        if (cbxTipodeDocumento.getSelectedItem().equals("Libro")) {
            if (mostrarError(txtAutor, "El Autor solo puede contener letras", 't')) {
                verificador = true;
            }
            if (mostrarError(txtAnio, null, 'n')) {
                verificador = true;
            }
            if (mostrarError(txtEditorial, null, 'n')) {
                verificador = true;
            }
            if (mostrarError(txtIsbn, null, 'n')) {
                verificador = true;
            }
        } else if (cbxTipodeDocumento.getSelectedItem().equals("Revista")) {
            if (mostrarError(txtFechaPublicacion, null, 'n')) {
                verificador = true;
            }
            if (mostrarError(txtIssn, null, 'n')) {
                verificador = true;
            }
            if (mostrarError(txtEditorial, null, 'n')) {
                verificador = true;
            }
        } else if (cbxTipodeDocumento.getSelectedItem().equals("Tesis")) {
            if (mostrarError(txtAutor, "El Autor solo puede contener letras", 't')) {
                verificador = true;
            }
            if (mostrarError(txtAsesor, "El Asesor solo puede contener letras", 'n')) {
                verificador = true;
            }
            if (mostrarError(txtFacultad, null, 'n')) {
                verificador = true;
            }
            if (mostrarError(txtFechaPublicacion, null, 'n')) {
                verificador = true;
            }
        } else if (cbxTipodeDocumento.getSelectedItem().equals("Documento no Convencional")) {
            if (mostrarError(txtAutor, "El Autor solo puede contener letras", 't')) {
                verificador = true;
            }
        }
        return verificador;
    }

    private void registrar() {
        if (!errores()) {
            cargarobjeto();
            if (cbxTipodeDocumento.getSelectedItem().equals("Libro")) {
                if (lS.guardar()) {
                    mensajeOK("Aviso", "Se ha registrado con éxito.");
                    limpiar();
                } else {
                    mensajeError("Error", "Ha ocurrido un error al realizar su registro.");
                }
            } else if (cbxTipodeDocumento.getSelectedItem().equals("Revista")) {
                if (rS.guardar()) {
                    mensajeOK("Aviso", "Se ha registrado con éxito.");
                    limpiar();
                } else {
                    mensajeError("Error", "Ha ocurrido un error al realizar su registro.");
                }
            } else if (cbxTipodeDocumento.getSelectedItem().equals("Tesis")) {
                if (tS.guardar()) {
                    mensajeOK("Aviso", "Se ha registrado con éxito.");
                    limpiar();
                } else {
                    mensajeError("Error", "Ha ocurrido un error al realizar su registro.");
                }
            } else if (cbxTipodeDocumento.getSelectedItem().equals("Documento no Convencional")) {
                if (dNCS.guardar()) {
                    mensajeOK("Aviso", "Se ha registrado con éxito.");
                    limpiar();
                } else {
                    mensajeError("Error", "Ha ocurrido un error al realizar su registro.");
                }
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

        jPanel1 = new javax.swing.JPanel();
        panelSelector = new org.edisoncor.gui.tabbedPane.TabbedSelector2();
        jPDocumento = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDocumentos = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        rdCodigo = new javax.swing.JRadioButton();
        rdTitulo = new javax.swing.JRadioButton();
        cbxTipoDocumento = new javax.swing.JComboBox<>();
        btnRegistrarNuevo = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        jPRegistrar = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cbxTipodeDocumento = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        txtTitulo = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtEditorial = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txtFacultad = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        txtIssn = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtAsesor = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtAutor = new javax.swing.JTextArea();
        txtAnio = new javax.swing.JFormattedTextField();
        sprEdicion = new javax.swing.JSpinner();
        txtFechaPublicacion = new javax.swing.JFormattedTextField();
        txtIsbn = new javax.swing.JTextField();
        txtCancelar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        cbxTipoNoConvencional = new javax.swing.JComboBox<>();
        jPPrestamo = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jButton9 = new javax.swing.JButton();
        jRadioButton11 = new javax.swing.JRadioButton();
        jRadioButton12 = new javax.swing.JRadioButton();
        jButton10 = new javax.swing.JButton();
        jComboBox5 = new javax.swing.JComboBox<>();
        jRadioButton13 = new javax.swing.JRadioButton();
        jTextField10 = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jPSansiones = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jButton11 = new javax.swing.JButton();
        jRadioButton14 = new javax.swing.JRadioButton();
        jRadioButton15 = new javax.swing.JRadioButton();
        jButton12 = new javax.swing.JButton();
        jComboBox6 = new javax.swing.JComboBox<>();
        jRadioButton16 = new javax.swing.JRadioButton();
        jTextField11 = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        jPPiedePagina = new javax.swing.JPanel();
        btnCerrarSesion = new rojeru_san.RSButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Panel De Bibliotecario");
        getContentPane().setLayout(null);

        jPanel1.setLayout(null);

        panelSelector.setBackground(new java.awt.Color(255, 255, 255));
        panelSelector.setForeground(new java.awt.Color(102, 102, 102));
        panelSelector.setColorBackGround(new java.awt.Color(255, 255, 255));
        panelSelector.setColorDeBorde(new java.awt.Color(185, 185, 185));
        panelSelector.setColorDeSegundoBorde(new java.awt.Color(185, 185, 185));
        panelSelector.setDoubleBuffered(true);
        panelSelector.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        panelSelector.setListColor(new java.awt.Color(0, 0, 0));

        jPDocumento.setBackground(new java.awt.Color(255, 255, 255));
        jPDocumento.setLayout(null);

        jPanel5.setBackground(new java.awt.Color(148, 169, 169));
        jPanel5.setLayout(null);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Documentos");
        jPanel5.add(jLabel4);
        jLabel4.setBounds(125, 1, 390, 100);

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/imagenes/iconoDocumento.png"))); // NOI18N
        jPanel5.add(jLabel5);
        jLabel5.setBounds(0, 0, 120, 100);

        jPDocumento.add(jPanel5);
        jPanel5.setBounds(0, 9, 600, 110);

        tblDocumentos.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblDocumentos);

        jPDocumento.add(jScrollPane1);
        jScrollPane1.setBounds(10, 130, 500, 170);

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Busqueda:");
        jPDocumento.add(jLabel3);
        jLabel3.setBounds(10, 310, 60, 20);
        jPDocumento.add(jTextField1);
        jTextField1.setBounds(80, 310, 250, 20);

        rdCodigo.setText("Código");
        rdCodigo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        rdCodigo.setOpaque(false);
        jPDocumento.add(rdCodigo);
        rdCodigo.setBounds(140, 330, 60, 23);

        rdTitulo.setText("Título");
        rdTitulo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        rdTitulo.setOpaque(false);
        jPDocumento.add(rdTitulo);
        rdTitulo.setBounds(80, 330, 60, 23);

        cbxTipoDocumento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Libro", "Revista", "Tesis", "Documento no Convencional" }));
        cbxTipoDocumento.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPDocumento.add(cbxTipoDocumento);
        cbxTipoDocumento.setBounds(340, 310, 170, 20);

        btnRegistrarNuevo.setText("Registrar Nuevo");
        btnRegistrarNuevo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRegistrarNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarNuevoActionPerformed(evt);
            }
        });
        jPDocumento.add(btnRegistrarNuevo);
        btnRegistrarNuevo.setBounds(160, 380, 110, 23);

        btnEditar.setText("Editar");
        btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        jPDocumento.add(btnEditar);
        btnEditar.setBounds(80, 380, 61, 23);

        panelSelector.addTab("Documentos", jPDocumento);

        jPRegistrar.setBackground(new java.awt.Color(255, 255, 255));
        jPRegistrar.setLayout(null);

        jPanel7.setBackground(new java.awt.Color(148, 169, 169));
        jPanel7.setLayout(null);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Administrar Documento");
        jPanel7.add(jLabel6);
        jLabel6.setBounds(125, 1, 390, 100);

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/imagenes/iconoDocumento.png"))); // NOI18N
        jPanel7.add(jLabel7);
        jLabel7.setBounds(0, 0, 120, 100);

        jPRegistrar.add(jPanel7);
        jPanel7.setBounds(0, 9, 600, 110);

        cbxTipodeDocumento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Libro", "Revista", "Tesis", "Documento no Convencional" }));
        cbxTipodeDocumento.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxTipodeDocumentoItemStateChanged(evt);
            }
        });
        jPRegistrar.add(cbxTipodeDocumento);
        cbxTipodeDocumento.setBounds(120, 140, 280, 20);

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel13.setText("Tipo de Documento:");
        jPRegistrar.add(jLabel13);
        jLabel13.setBounds(10, 140, 100, 20);
        jPRegistrar.add(txtTitulo);
        txtTitulo.setBounds(80, 180, 250, 20);

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel14.setText("Título:");
        jPRegistrar.add(jLabel14);
        jLabel14.setBounds(10, 180, 60, 20);

        txtCodigo.setEditable(false);
        jPRegistrar.add(txtCodigo);
        txtCodigo.setBounds(80, 210, 250, 20);

        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel15.setText("Código:");
        jPRegistrar.add(jLabel15);
        jLabel15.setBounds(10, 210, 60, 20);

        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel16.setText("Autor(es):");
        jPRegistrar.add(jLabel16);
        jLabel16.setBounds(10, 250, 60, 20);
        jPRegistrar.add(jSeparator1);
        jSeparator1.setBounds(20, 240, 480, 10);

        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel17.setText("Edición:");
        jPRegistrar.add(jLabel17);
        jLabel17.setBounds(310, 250, 60, 20);

        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel18.setText("Año:");
        jPRegistrar.add(jLabel18);
        jLabel18.setBounds(310, 280, 60, 20);
        jPRegistrar.add(txtEditorial);
        txtEditorial.setBounds(80, 330, 240, 20);

        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel19.setText("Editorial:");
        jPRegistrar.add(jLabel19);
        jLabel19.setBounds(10, 330, 60, 20);
        jPRegistrar.add(txtFacultad);
        txtFacultad.setBounds(80, 360, 240, 20);

        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel20.setText("Isbn:");
        jPRegistrar.add(jLabel20);
        jLabel20.setBounds(10, 390, 60, 20);

        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel21.setText("Tipo");
        jPRegistrar.add(jLabel21);
        jLabel21.setBounds(390, 360, 30, 20);

        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel22.setText("Issn:");
        jPRegistrar.add(jLabel22);
        jLabel22.setBounds(10, 420, 60, 20);

        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel23.setText("Facultad:");
        jPRegistrar.add(jLabel23);
        jLabel23.setBounds(10, 360, 60, 20);

        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel24.setText("Asesor(es):");
        jPRegistrar.add(jLabel24);
        jLabel24.setBounds(10, 290, 60, 20);
        jPRegistrar.add(txtIssn);
        txtIssn.setBounds(80, 420, 240, 20);

        txtAsesor.setColumns(20);
        txtAsesor.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        txtAsesor.setLineWrap(true);
        txtAsesor.setRows(5);
        txtAsesor.setWrapStyleWord(true);
        jScrollPane2.setViewportView(txtAsesor);

        jPRegistrar.add(jScrollPane2);
        jScrollPane2.setBounds(80, 290, 240, 30);

        txtAutor.setColumns(20);
        txtAutor.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        txtAutor.setLineWrap(true);
        txtAutor.setRows(5);
        txtAutor.setWrapStyleWord(true);
        jScrollPane3.setViewportView(txtAutor);

        jPRegistrar.add(jScrollPane3);
        jScrollPane3.setBounds(80, 250, 240, 30);

        txtAnio.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("yyyy"))));
        txtAnio.setText("2018");
        jPRegistrar.add(txtAnio);
        txtAnio.setBounds(380, 280, 100, 20);

        sprEdicion.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        jPRegistrar.add(sprEdicion);
        sprEdicion.setBounds(380, 250, 100, 20);

        txtFechaPublicacion.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("d/MM/yyyy"))));
        txtFechaPublicacion.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFechaPublicacion.setText("01/01/2018");
        jPRegistrar.add(txtFechaPublicacion);
        txtFechaPublicacion.setBounds(350, 330, 130, 20);
        jPRegistrar.add(txtIsbn);
        txtIsbn.setBounds(80, 390, 240, 20);

        txtCancelar.setText("Cancelar");
        txtCancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        txtCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCancelarActionPerformed(evt);
            }
        });
        jPRegistrar.add(txtCancelar);
        txtCancelar.setBounds(410, 410, 80, 23);

        btnGuardar.setText("Guardar");
        btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPRegistrar.add(btnGuardar);
        btnGuardar.setBounds(330, 410, 71, 23);

        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel25.setText("Fecha de Publicación");
        jPRegistrar.add(jLabel25);
        jLabel25.setBounds(350, 310, 110, 20);

        cbxTipoNoConvencional.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Película", "Disco de música", "Audiolibro" }));
        jPRegistrar.add(cbxTipoNoConvencional);
        cbxTipoNoConvencional.setBounds(350, 380, 130, 20);

        panelSelector.addTab("Administrar Documento", jPRegistrar);

        jPPrestamo.setBackground(new java.awt.Color(255, 255, 255));
        jPPrestamo.setLayout(null);

        jPanel9.setBackground(new java.awt.Color(148, 169, 169));
        jPanel9.setLayout(null);

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Préstamos");
        jPanel9.add(jLabel8);
        jLabel8.setBounds(125, 1, 390, 100);

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/imagenes/iconoDocumento.png"))); // NOI18N
        jPanel9.add(jLabel9);
        jLabel9.setBounds(0, 0, 120, 100);

        jPPrestamo.add(jPanel9);
        jPanel9.setBounds(0, 9, 600, 110);

        jButton9.setText("Editar");
        jButton9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPPrestamo.add(jButton9);
        jButton9.setBounds(90, 380, 61, 23);

        jRadioButton11.setText("Título");
        jRadioButton11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jRadioButton11.setOpaque(false);
        jPPrestamo.add(jRadioButton11);
        jRadioButton11.setBounds(80, 340, 60, 23);

        jRadioButton12.setText("Tipo de Documento");
        jRadioButton12.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jRadioButton12.setOpaque(false);
        jPPrestamo.add(jRadioButton12);
        jRadioButton12.setBounds(140, 340, 120, 23);

        jButton10.setText("Registrar Nuevo");
        jButton10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPPrestamo.add(jButton10);
        jButton10.setBounds(170, 380, 110, 23);

        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPPrestamo.add(jComboBox5);
        jComboBox5.setBounds(340, 310, 170, 20);

        jRadioButton13.setText("Código");
        jRadioButton13.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jRadioButton13.setOpaque(false);
        jPPrestamo.add(jRadioButton13);
        jRadioButton13.setBounds(260, 340, 60, 23);
        jPPrestamo.add(jTextField10);
        jTextField10.setBounds(80, 310, 250, 20);

        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel26.setText("Busqueda:");
        jPPrestamo.add(jLabel26);
        jLabel26.setBounds(10, 310, 60, 20);

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane6.setViewportView(jTable4);

        jPPrestamo.add(jScrollPane6);
        jScrollPane6.setBounds(10, 130, 500, 170);

        panelSelector.addTab("Préstamos", jPPrestamo);

        jPSansiones.setBackground(new java.awt.Color(255, 255, 255));
        jPSansiones.setLayout(null);

        jPanel11.setBackground(new java.awt.Color(148, 169, 169));
        jPanel11.setLayout(null);

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Sansiones");
        jPanel11.add(jLabel10);
        jLabel10.setBounds(125, 1, 390, 100);

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/imagenes/iconoDocumento.png"))); // NOI18N
        jPanel11.add(jLabel11);
        jLabel11.setBounds(0, 0, 120, 100);

        jPSansiones.add(jPanel11);
        jPanel11.setBounds(0, 9, 600, 110);

        jButton11.setText("Editar");
        jButton11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPSansiones.add(jButton11);
        jButton11.setBounds(90, 380, 61, 23);

        jRadioButton14.setText("Título");
        jRadioButton14.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jRadioButton14.setOpaque(false);
        jPSansiones.add(jRadioButton14);
        jRadioButton14.setBounds(80, 340, 60, 23);

        jRadioButton15.setText("Tipo de Documento");
        jRadioButton15.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jRadioButton15.setOpaque(false);
        jPSansiones.add(jRadioButton15);
        jRadioButton15.setBounds(140, 340, 120, 23);

        jButton12.setText("Registrar Nuevo");
        jButton12.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPSansiones.add(jButton12);
        jButton12.setBounds(170, 380, 110, 23);

        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPSansiones.add(jComboBox6);
        jComboBox6.setBounds(340, 310, 170, 20);

        jRadioButton16.setText("Código");
        jRadioButton16.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jRadioButton16.setOpaque(false);
        jPSansiones.add(jRadioButton16);
        jRadioButton16.setBounds(260, 340, 60, 23);
        jPSansiones.add(jTextField11);
        jTextField11.setBounds(80, 310, 250, 20);

        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel27.setText("Busqueda:");
        jPSansiones.add(jLabel27);
        jLabel27.setBounds(10, 310, 60, 20);

        jTable5.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane7.setViewportView(jTable5);

        jPSansiones.add(jScrollPane7);
        jScrollPane7.setBounds(10, 130, 500, 170);

        panelSelector.addTab("Sansiones", jPSansiones);

        jPanel1.add(panelSelector);
        panelSelector.setBounds(0, 0, 720, 470);

        jPPiedePagina.setLayout(null);

        btnCerrarSesion.setBackground(new java.awt.Color(13, 110, 110));
        btnCerrarSesion.setText("Cerrar Sesion");
        btnCerrarSesion.setColorHover(new java.awt.Color(141, 181, 181));
        jPPiedePagina.add(btnCerrarSesion);
        btnCerrarSesion.setBounds(530, 10, 150, 40);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("Bienvenido Bibliotecario");
        jPPiedePagina.add(jLabel1);
        jLabel1.setBounds(60, 10, 300, 40);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/imagenes/Icono.png"))); // NOI18N
        jPPiedePagina.add(jLabel2);
        jLabel2.setBounds(0, 0, 60, 60);

        jPanel1.add(jPPiedePagina);
        jPPiedePagina.setBounds(0, 470, 720, 60);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 720, 530);

        setSize(new java.awt.Dimension(736, 569));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cbxTipodeDocumentoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxTipodeDocumentoItemStateChanged
        deshabilitarCampos();
        habilitarCampos();
        if (dS.obtenerDocumento().getId() == null) {
            codigoDocumento();
        }
    }//GEN-LAST:event_cbxTipodeDocumentoItemStateChanged

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        registrar();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void txtCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCancelarActionPerformed
        limpiar();
    }//GEN-LAST:event_txtCancelarActionPerformed

    private void btnRegistrarNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarNuevoActionPerformed
        panelSelector.setSelectedIndex(1);
    }//GEN-LAST:event_btnRegistrarNuevoActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        panelSelector.setSelectedIndex(1);
    }//GEN-LAST:event_btnEditarActionPerformed

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
            java.util.logging.Logger.getLogger(FrmBibliotecario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmBibliotecario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmBibliotecario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmBibliotecario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FrmBibliotecario dialog = new FrmBibliotecario(new javax.swing.JFrame(), true);
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
    private rojeru_san.RSButton btnCerrarSesion;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnRegistrarNuevo;
    private javax.swing.JComboBox<String> cbxTipoDocumento;
    private javax.swing.JComboBox<String> cbxTipoNoConvencional;
    private javax.swing.JComboBox<String> cbxTipodeDocumento;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JComboBox<String> jComboBox6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPDocumento;
    private javax.swing.JPanel jPPiedePagina;
    private javax.swing.JPanel jPPrestamo;
    private javax.swing.JPanel jPRegistrar;
    private javax.swing.JPanel jPSansiones;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JRadioButton jRadioButton11;
    private javax.swing.JRadioButton jRadioButton12;
    private javax.swing.JRadioButton jRadioButton13;
    private javax.swing.JRadioButton jRadioButton14;
    private javax.swing.JRadioButton jRadioButton15;
    private javax.swing.JRadioButton jRadioButton16;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable5;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private org.edisoncor.gui.tabbedPane.TabbedSelector2 panelSelector;
    private javax.swing.JRadioButton rdCodigo;
    private javax.swing.JRadioButton rdTitulo;
    private javax.swing.JSpinner sprEdicion;
    private javax.swing.JTable tblDocumentos;
    private javax.swing.JFormattedTextField txtAnio;
    private javax.swing.JTextArea txtAsesor;
    private javax.swing.JTextArea txtAutor;
    private javax.swing.JButton txtCancelar;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtEditorial;
    private javax.swing.JTextField txtFacultad;
    private javax.swing.JFormattedTextField txtFechaPublicacion;
    private javax.swing.JTextField txtIsbn;
    private javax.swing.JTextField txtIssn;
    private javax.swing.JTextField txtTitulo;
    // End of variables declaration//GEN-END:variables
}
