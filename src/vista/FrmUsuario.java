/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controlador.servicio.DocumentoNoConvencionalServicio;
import controlador.servicio.DocumentoServicio;
import controlador.servicio.LibroServicio;
import controlador.servicio.PersonaServicio;
import controlador.servicio.PrestamoServicio;
import controlador.servicio.RevistaServicio;
import controlador.servicio.SancionServicio;
import controlador.servicio.TesisServicio;
import controlador.utilidades.Sesion;
import it.sauronsoftware.base64.Base64;
import java.util.Date;
import static java.util.stream.Collectors.toList;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import vista.tablas.ModeloTablaDocumento;
import vista.tablas.ModeloTablaDocumentoNoConvencional;
import vista.tablas.ModeloTablaLibro;
import vista.tablas.ModeloTablaPrestamo;
import vista.tablas.ModeloTablaRevista;
import vista.tablas.ModeloTablaSancion;
import vista.tablas.ModeloTablaTesis;
import static vista.utilidades.UtilidadesComponente.*;

/**
 *
 * @author user
 */
public class FrmUsuario extends javax.swing.JDialog {

    private PersonaServicio perS = new PersonaServicio();
    private DocumentoServicio dS = new DocumentoServicio();
    private PrestamoServicio pS = new PrestamoServicio();
    private SancionServicio sS = new SancionServicio();
    private LibroServicio lS = new LibroServicio();
    private RevistaServicio rS = new RevistaServicio();
    private TesisServicio tS = new TesisServicio();
    private DocumentoNoConvencionalServicio dNCS = new DocumentoNoConvencionalServicio();
    private ModeloTablaDocumento modeloDocumento = new ModeloTablaDocumento();
    private ModeloTablaPrestamo modeloPrestamo = new ModeloTablaPrestamo();
    private ModeloTablaLibro modeloLibro = new ModeloTablaLibro();
    private ModeloTablaRevista modeloRevista = new ModeloTablaRevista();
    private ModeloTablaTesis modeloTesis = new ModeloTablaTesis();
    private ModeloTablaDocumentoNoConvencional modeloDocumentoNoConvencional = new ModeloTablaDocumentoNoConvencional();
    private ModeloTablaSancion modeloSancion = new ModeloTablaSancion();
    private JComboBox tipoPrestamo = new JComboBox();

    /**
     * Creates new form FrmUsuario
     */
    public FrmUsuario(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        lblBienvenida.setText("Bienvenida(o) " + Sesion.getCuenta().getPersona().getNombres());
        limpiar();
        cargarTipoPrestamo();
        cargarDatos();
    }

    private void cargarTablaDocumento() {
        if (cbxTipoDocumento.getSelectedItem().toString().equals("Todos")) {
            modeloDocumento.setLista(dS.listar());
            tblDocumentos.setModel(modeloDocumento);
        } else {
            switch (cbxTipoDocumento.getSelectedItem().toString()) {
                case "Libro":
                    modeloLibro.setLista(lS.listar());
                    tblDocumentos.setModel(modeloLibro);
                    break;
                case "Revista":
                    modeloRevista.setLista(rS.listar());
                    tblDocumentos.setModel(modeloRevista);
                    break;
                case "Tesis":
                    modeloTesis.setLista(tS.listar());
                    tblDocumentos.setModel(modeloTesis);
                    break;
                case "Documento no Convencional":
                    modeloDocumentoNoConvencional.setLista(dNCS.listar());
                    tblDocumentos.setModel(modeloDocumentoNoConvencional);
                    break;
            }
        }
        tblDocumentos.updateUI();
    }

    private void cargarTablaPrestamo() {
        modeloPrestamo.setLista(perS.obtenerPersona().getListaPrestamo());
        tblPrestamo.setModel(modeloPrestamo);
        tblPrestamo.updateUI();
    }

    private void cargarTablaSancion() {
        modeloSancion.setLista(sS.listar().stream()
                .filter(x -> x.getEstado() && x.getPrestamo().getPersona().equals(Sesion.getCuenta().getPersona()))
                .collect(toList()));
        tblSancion.setModel(modeloSancion);
        tblSancion.updateUI();
    }

    private void limpiar() {
        perS.obtenerPersona().setListaPrestamo(pS.listar().stream()
                .filter(x -> x.getPersona().equals(Sesion.getCuenta().getPersona()))
                .collect(toList()));
        perS.fijarPersona(Sesion.getCuenta().getPersona());
        dS.fijarDocumento(null);
        pS.fijarPrestamo(null);
        sS.fijarSancion(null);
        lS.fijarLibro(null);
        rS.fijarRevista(null);
        tS.fijarTesis(null);
        dNCS.fijarDocumentoNoConvensional(null);
        txtCActual.setText(null);
        txtCNueva.setText(null);
        tipoPrestamo.setSelectedItem(null);
        cargarVistaPrevia();
        cargarTablaDocumento();
        cargarTablaPrestamo();
        cargarTablaSancion();
    }

    private void cargarVistaPrevia() {
        int fila;
        String vistaPrevia;
        if (cbxTipoDocumento.getSelectedItem().toString().equals("Todos")) {
            btnInformacionDocumento.setVisible(true);
            fila = tblDocumentos.getSelectedRow();
            if (fila >= 0) {
                dS.fijarDocumento(modeloDocumento.getLista().get(fila));
                vistaPrevia = "Título: " + dS.obtenerDocumento().getTitulo()
                        + "\nCódigo: " + dS.obtenerDocumento().getCodigo()
                        + "\nCategoría: " + dS.obtenerDocumento().getTipoDocumento();
                txtTituloDocumento.setText(vistaPrevia);
            } else {
                txtTituloDocumento.setText("Ningun Documento Seleccionado.");
            }
        } else {
            btnInformacionDocumento.setVisible(false);
            switch (cbxTipoDocumento.getSelectedItem().toString()) {
                case "Libro":
                    fila = tblDocumentos.getSelectedRow();
                    if (fila >= 0) {
                        dS.fijarDocumento(modeloLibro.getLista().get(fila));
                        vistaPrevia = "Título: " + dS.obtenerDocumento().getTitulo()
                                + "\nCódigo: " + dS.obtenerDocumento().getCodigo()
                                + "\nCategoría: " + dS.obtenerDocumento().getTipoDocumento();
                        txtTituloDocumento.setText(vistaPrevia);
                    } else {
                        txtTituloDocumento.setText("Ningun Documento Seleccionado.");
                    }
                    break;
                case "Revista":
                    fila = tblDocumentos.getSelectedRow();
                    if (fila >= 0) {
                        dS.fijarDocumento(modeloRevista.getLista().get(fila));
                        vistaPrevia = "Título: " + dS.obtenerDocumento().getTitulo()
                                + "\nCódigo: " + dS.obtenerDocumento().getCodigo()
                                + "\nCategoría: " + dS.obtenerDocumento().getTipoDocumento();
                        txtTituloDocumento.setText(vistaPrevia);
                    } else {
                        txtTituloDocumento.setText("Ningun Documento Seleccionado.");
                    }
                    break;
                case "Tesis":
                    fila = tblDocumentos.getSelectedRow();
                    if (fila >= 0) {
                        dS.fijarDocumento(modeloTesis.getLista().get(fila));
                        vistaPrevia = "Título: " + dS.obtenerDocumento().getTitulo()
                                + "\nCódigo: " + dS.obtenerDocumento().getCodigo()
                                + "\nCategoría: " + dS.obtenerDocumento().getTipoDocumento();
                        txtTituloDocumento.setText(vistaPrevia);
                    } else {
                        txtTituloDocumento.setText("Ningun Documento Seleccionado.");
                    }
                    break;
                case "Documento no Convencional":
                    fila = tblDocumentos.getSelectedRow();
                    if (fila >= 0) {
                        dS.fijarDocumento(modeloDocumentoNoConvencional.getLista().get(fila));
                        vistaPrevia = "Título: " + dS.obtenerDocumento().getTitulo()
                                + "\nCódigo: " + dS.obtenerDocumento().getCodigo()
                                + "\nCategoría: " + dS.obtenerDocumento().getTipoDocumento();
                        txtTituloDocumento.setText(vistaPrevia);
                    } else {
                        txtTituloDocumento.setText("Ningun Documento Seleccionado.");
                    }
                    break;
            }
        }
    }

    private void cargarTipoPrestamo() {
        tipoPrestamo.addItem("Dentro de la Facultad (1 Jornada).");
        tipoPrestamo.addItem("Fuera de la Facultad (2 Días).");
        tipoPrestamo.setSelectedItem(null);
    }

    private void pedirPrestamo() {
        if (!txtTituloDocumento.getText().equals("Ningun Documento Seleccionado.")) {
            if (dS.obtenerDocumento().getEstado()) {
                JOptionPane.showMessageDialog(this, tipoPrestamo, "Escoja un tipo de Préstamo.", JOptionPane.INFORMATION_MESSAGE);
                if (tipoPrestamo.getSelectedItem() != null) {
                    pS.obtenerPrestamo().setDocumento(dS.obtenerDocumento());
                    pS.obtenerPrestamo().setPersona(Sesion.getCuenta().getPersona());
                    if (tipoPrestamo.getSelectedItem().toString().equals("Dentro de la Facultad (1 Jornada).")) {
                        pS.obtenerPrestamo().setFechaEntrega(new Date());
                        pS.obtenerPrestamo().setFechaDevolucion(new Date());
                    } else {
                        pS.obtenerPrestamo().setFechaEntrega(new Date());
                        pS.obtenerPrestamo().setFechaDevolucion(new Date());
                    }
                    pS.obtenerPrestamo().setTipoPrestamo(tipoPrestamo.getSelectedItem().toString());
                    pS.obtenerPrestamo().setEstado(true);
                    dS.obtenerDocumento().setEstado(false);
                    if (pS.guardar()) {
                        mensajeOK("¡Hecho!", "Se ha solicitado su Préstamo con exito.");
                        dS.guardar();
                        panelSelector.setSelectedIndex(1);
                        limpiar();
                    } else {
                        mensajeError("Error", "No se ha podido solicitar su Préstamo.");
                    }
                }
            } else {
                mensajeOK("Aviso", "El Documento no se encuentra disponible.");
            }
        } else {
            mensajeError("Advertencia", "Debe seleccionar un Documento de la Tabla.");
        }
    }

    private void buscarDocumento() {
        if (txtbusquedaDocumento.getText().trim().length() >= 3) {
            if (cbxTipoDocumento.getSelectedItem().toString().equals("Todos")) {
                modeloDocumento.setLista(dS.listarDocumentoLike(txtbusquedaDocumento.getText().trim()));
                tblDocumentos.setModel(modeloDocumento);
            } else {
                switch (cbxTipoDocumento.getSelectedItem().toString()) {
                    case "Libro":
                        modeloLibro.setLista(lS.listarLibroLike(txtbusquedaDocumento.getText().trim()));
                        tblDocumentos.setModel(modeloLibro);
                        break;
                    case "Revista":
                        modeloRevista.setLista(rS.listarRevistaLike(txtbusquedaDocumento.getText().trim()));
                        tblDocumentos.setModel(modeloRevista);
                        break;
                    case "Tesis":
                        modeloTesis.setLista(tS.listarTesisLike(txtbusquedaDocumento.getText().trim()));
                        tblDocumentos.setModel(modeloTesis);
                        break;
                    case "Documento no Convencional":
                        modeloDocumentoNoConvencional.setLista(dNCS.listarDocumentoNoConvencionalLike(txtbusquedaDocumento.getText().trim()));
                        tblDocumentos.setModel(modeloDocumentoNoConvencional);
                        break;
                }
            }
            tblDocumentos.updateUI();
        } else {
            cargarTablaDocumento();
        }
    }

    private void buscarPrestamo() {
        if (txtBusquedaPrestamo.getText().trim().length() >= 3) {
            modeloPrestamo.setLista(pS.listarPrestamoLike(txtBusquedaPrestamo.getText().trim()));
            tblPrestamo.setModel(modeloPrestamo);
            tblPrestamo.updateUI();
        } else {
            cargarTablaPrestamo();
        }
    }

    private void cargarVerInformacion() {
        if (cbxTipoDocumento.getSelectedItem().toString().equals("Todos")) {
            int fila = tblDocumentos.getSelectedRow();
            String informacion = "Información\n";
            if (fila >= 0) {
                dS.fijarDocumento(modeloDocumento.getLista().get(fila));
                informacion += "Título: " + dS.obtenerDocumento().getTitulo();
                informacion += "\nCódigo: " + dS.obtenerDocumento().getCodigo();
                switch (dS.obtenerDocumento().getTipoDocumento()) {
                    case "Libro":
                        lS.fijarLibro(lS.obtenerLibro(dS.obtenerDocumento().getId()));
                        informacion += "\nDatos del Libro:";
                        informacion += "\nAutor(es): " + lS.obtenerLibro().getAutores();
                        informacion += "\nEdición: " + lS.obtenerLibro().getEdicion();
                        informacion += "\nAño: " + lS.obtenerLibro().getAnio();
                        informacion += "\nEditorial: " + lS.obtenerLibro().getEditorial();
                        informacion += "\nISBN: " + lS.obtenerLibro().getIsbn();
                        break;
                    case "Revista":
                        rS.fijarRevista(rS.obtenerRevista(dS.obtenerDocumento().getId()));
                        informacion += "\nDatos de la Revista:";
                        informacion += "\nFecha de Publicación: " + rS.obtenerRevista().getFechaPublicacion();
                        informacion += "\nISSN: " + rS.obtenerRevista().getIssn();
                        informacion += "\nEditorial: " + rS.obtenerRevista().getEditorial();
                        break;
                    case "Tesis":
                        tS.fijarTesis(tS.obtenerTesis(dS.obtenerDocumento().getId()));
                        informacion += "\nDatos de la Tesis:";
                        informacion += "\nAutor(es): " + tS.obtenerTesis().getAutores();
                        informacion += "\nAsesor(es): " + tS.obtenerTesis().getAsesores();
                        informacion += "\nFacultad: " + tS.obtenerTesis().getFacultad();
                        informacion += "\nFecha de Publicación: " + tS.obtenerTesis().getFechaPublicacion();
                        break;
                    case "Documento no Convencional":
                        dNCS.fijarDocumentoNoConvensional(dNCS.obtenerDocumentoNoConvencional(dS.obtenerDocumento().getId()));
                        informacion += "\nDatos del Documento no Convencional:";
                        informacion += "\nAutor: " + dNCS.obtenerDocumentoNoConvencional().getAutor();
                        informacion += "\nTipo: " + dNCS.obtenerDocumentoNoConvencional().getTipoNoConvencional();
                        break;
                }
                txtVerInformacion.setText(informacion);
                JOptionPane.showMessageDialog(this, jScrollPane3, "Más Información", JOptionPane.INFORMATION_MESSAGE);
            } else {
                mensajeError("Advertencia", "Debe seleccionar un Documento de la Tabla.");
            }
        }
    }

    private void cargarDatos() {
        txtEmail.setText(Sesion.getCuenta().getPersona().getCorreo());
        txtTelefono.setText(Sesion.getCuenta().getPersona().getTelefono());
        txtDireccion.setText(Sesion.getCuenta().getPersona().getDireccion());
    }

    private void actualizarDatos() {
        if (!txtEmail.getText().trim().equals(perS.obtenerPersona().getCorreo())
                || !txtTelefono.getText().trim().equals(perS.obtenerPersona().getTelefono())
                || !txtDireccion.getText().trim().equals(perS.obtenerPersona().getDireccion())) {
            if (!mostrarError(txtEmail, "El Email ingresado no es válido.", 'm')
                    && !mostrarError(txtTelefono, "El Teléfono solamente puede contener números.", 'e')
                    && !mostrarError(txtDireccion, null, 'n')) {
                perS.obtenerPersona().setCorreo(txtEmail.getText().trim());
                perS.obtenerPersona().setTelefono(txtTelefono.getText().trim());
                perS.obtenerPersona().setDireccion(txtDireccion.getText().trim());
                perS.obtenerPersona().getCuenta().setModificadoEn(new Date());
                if (perS.guardar()) {
                    mensajeOK("Aviso", "Sus datos han sido actualizados correctamente.");
                    limpiar();
                } else {
                    mensajeError("Error", "No se puedo actualizar sus datos.");
                }
            }
        } else {
            mensajeError("Aviso", "No se ha realizado ningún cambio.");
        }
    }

    private void actualizarClave() {
        if (!mostrarError(txtCActual, null, 'n') && !mostrarError(txtCNueva, null, 'n')) {
            if (String.valueOf(txtCActual.getPassword()).equals(Base64.decode(perS.obtenerPersona().getCuenta().getClave()))
                    && !String.valueOf(txtCNueva.getPassword()).equals(Base64.decode(perS.obtenerPersona().getCuenta().getClave()))) {
                perS.obtenerPersona().getCuenta().setClave(Base64.encode(String.valueOf(txtCNueva.getPassword())));
                perS.obtenerPersona().getCuenta().setModificadoEn(new Date());
                if (perS.guardar()) {
                    mensajeOK("Aviso", "Su Contraseña se actualizó correctamente.");
                    limpiar();
                } else {
                    mensajeError("Error", "No se puedo actualizar su contraseña.");
                }
            } else {
                mensajeError("Aviso", "No se ha realizado ningún cambio en la clave.");
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

        jScrollPane3 = new javax.swing.JScrollPane();
        txtVerInformacion = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        panelEncabezado = new org.edisoncor.gui.panel.Panel();
        lblBienvenida = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btnCerrarSesion = new rojeru_san.RSButtonRiple();
        btnCerrarVentana = new rojeru_san.RSButton();
        panelSelector = new org.edisoncor.gui.tabbedPane.TabbedSelector2();
        jPDocumento = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDocumentos = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        txtbusquedaDocumento = new javax.swing.JTextField();
        cbxTipoDocumento = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtTituloDocumento = new javax.swing.JTextArea();
        btnInformacionDocumento = new rojeru_san.RSButton();
        btnPedir = new rojeru_san.RSButton();
        lblFondo = new javax.swing.JLabel();
        jPPrestamo = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblPrestamo = new javax.swing.JTable();
        txtBusquedaPrestamo = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        btnPedirNuevo = new rojeru_san.RSButton();
        btnVerSanciones = new rojeru_san.RSButton();
        lblFondo1 = new javax.swing.JLabel();
        jPSansiones = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblSancion = new javax.swing.JTable();
        lblAvisoSancion = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        lblFondo2 = new javax.swing.JLabel();
        jPDatos = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnActualizarDatos = new rojeru_san.RSButton();
        txtEmail = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        txtCNueva = new rojeru_san.RSMPassView();
        txtCActual = new rojeru_san.RSMPassView();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnActualizarContraseña = new rojeru_san.RSButton();
        lblFondo3 = new javax.swing.JLabel();

        txtVerInformacion.setEditable(false);
        txtVerInformacion.setColumns(25);
        txtVerInformacion.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtVerInformacion.setLineWrap(true);
        txtVerInformacion.setRows(5);
        txtVerInformacion.setWrapStyleWord(true);
        jScrollPane3.setViewportView(txtVerInformacion);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Panel de Usuario");
        setIconImage(obtenerIcono());
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(null);

        jPanel1.setLayout(null);

        panelEncabezado.setBackground(new java.awt.Color(255, 255, 255));
        panelEncabezado.setForeground(new java.awt.Color(119, 136, 153));
        panelEncabezado.setColorPrimario(new java.awt.Color(164, 175, 187));
        panelEncabezado.setColorSecundario(new java.awt.Color(240, 240, 240));
        panelEncabezado.setLayout(null);

        lblBienvenida.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblBienvenida.setForeground(new java.awt.Color(51, 51, 51));
        lblBienvenida.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblBienvenida.setText("Bienvenido Usuario");
        panelEncabezado.add(lblBienvenida);
        lblBienvenida.setBounds(140, 0, 300, 50);

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/imagenes/Libros.png"))); // NOI18N
        panelEncabezado.add(jLabel7);
        jLabel7.setBounds(0, 0, 140, 80);

        btnCerrarSesion.setText("Cerrar Sesión");
        btnCerrarSesion.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarSesionActionPerformed(evt);
            }
        });
        panelEncabezado.add(btnCerrarSesion);
        btnCerrarSesion.setBounds(140, 40, 120, 20);

        btnCerrarVentana.setBackground(new java.awt.Color(164, 175, 187));
        btnCerrarVentana.setBorder(null);
        btnCerrarVentana.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/imagenes/cerrarVentana.png"))); // NOI18N
        btnCerrarVentana.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarVentanaActionPerformed(evt);
            }
        });
        panelEncabezado.add(btnCerrarVentana);
        btnCerrarVentana.setBounds(760, 0, 40, 30);

        jPanel1.add(panelEncabezado);
        panelEncabezado.setBounds(0, 0, 800, 80);

        panelSelector.setBackground(new java.awt.Color(234, 234, 235));
        panelSelector.setForeground(new java.awt.Color(102, 102, 102));
        panelSelector.setColorBackGround(new java.awt.Color(234, 234, 235));
        panelSelector.setColorDeBorde(new java.awt.Color(185, 185, 185));
        panelSelector.setColorDeSegundoBorde(new java.awt.Color(185, 185, 185));
        panelSelector.setDoubleBuffered(true);
        panelSelector.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        panelSelector.setListColor(new java.awt.Color(0, 0, 0));
        panelSelector.setSelectionColor(new java.awt.Color(234, 234, 235));

        jPDocumento.setBackground(new java.awt.Color(255, 255, 255));
        jPDocumento.setLayout(null);

        jPanel5.setBackground(new java.awt.Color(119, 136, 153));
        jPanel5.setLayout(null);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Documentos");
        jPanel5.add(jLabel4);
        jLabel4.setBounds(125, 1, 390, 100);

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/imagenes/IconoDocumento.png"))); // NOI18N
        jPanel5.add(jLabel5);
        jLabel5.setBounds(0, 0, 120, 100);

        jPDocumento.add(jPanel5);
        jPanel5.setBounds(0, 9, 660, 110);

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
        tblDocumentos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDocumentosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblDocumentos);

        jPDocumento.add(jScrollPane1);
        jScrollPane1.setBounds(10, 130, 640, 170);

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Documento:");
        jPDocumento.add(jLabel3);
        jLabel3.setBounds(10, 360, 60, 20);

        txtbusquedaDocumento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbusquedaDocumentoKeyReleased(evt);
            }
        });
        jPDocumento.add(txtbusquedaDocumento);
        txtbusquedaDocumento.setBounds(80, 310, 390, 20);

        cbxTipoDocumento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Libro", "Revista", "Tesis", "Documento no Convencional" }));
        cbxTipoDocumento.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cbxTipoDocumento.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxTipoDocumentoItemStateChanged(evt);
            }
        });
        jPDocumento.add(cbxTipoDocumento);
        cbxTipoDocumento.setBounds(480, 310, 170, 20);

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Busqueda:");
        jPDocumento.add(jLabel9);
        jLabel9.setBounds(10, 310, 60, 20);

        txtTituloDocumento.setEditable(false);
        txtTituloDocumento.setColumns(20);
        txtTituloDocumento.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtTituloDocumento.setLineWrap(true);
        txtTituloDocumento.setRows(5);
        txtTituloDocumento.setWrapStyleWord(true);
        jScrollPane4.setViewportView(txtTituloDocumento);

        jPDocumento.add(jScrollPane4);
        jScrollPane4.setBounds(80, 340, 230, 60);

        btnInformacionDocumento.setBackground(new java.awt.Color(255, 79, 25));
        btnInformacionDocumento.setText("Ver Información");
        btnInformacionDocumento.setColorHover(new java.awt.Color(75, 185, 236));
        btnInformacionDocumento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInformacionDocumentoActionPerformed(evt);
            }
        });
        jPDocumento.add(btnInformacionDocumento);
        btnInformacionDocumento.setBounds(470, 350, 140, 40);

        btnPedir.setBackground(new java.awt.Color(93, 100, 127));
        btnPedir.setText("Pedir");
        btnPedir.setColorHover(new java.awt.Color(75, 185, 236));
        btnPedir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPedirActionPerformed(evt);
            }
        });
        jPDocumento.add(btnPedir);
        btnPedir.setBounds(320, 350, 140, 40);

        lblFondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/imagenes/LibroFondo.png"))); // NOI18N
        jPDocumento.add(lblFondo);
        lblFondo.setBounds(264, 114, 400, 330);

        panelSelector.addTab("Documentos", jPDocumento);

        jPPrestamo.setBackground(new java.awt.Color(255, 255, 255));
        jPPrestamo.setLayout(null);

        jPanel7.setBackground(new java.awt.Color(119, 136, 153));
        jPanel7.setLayout(null);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Mis Préstamos");
        jPanel7.add(jLabel6);
        jLabel6.setBounds(125, 1, 390, 100);

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/imagenes/Prestamo.png"))); // NOI18N
        jPanel7.add(jLabel8);
        jLabel8.setBounds(0, 0, 120, 100);

        jPPrestamo.add(jPanel7);
        jPanel7.setBounds(0, 9, 660, 110);

        tblPrestamo.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane8.setViewportView(tblPrestamo);

        jPPrestamo.add(jScrollPane8);
        jScrollPane8.setBounds(10, 180, 640, 210);

        txtBusquedaPrestamo.setToolTipText("La busqueda es por el Título del Documento");
        txtBusquedaPrestamo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBusquedaPrestamoKeyReleased(evt);
            }
        });
        jPPrestamo.add(txtBusquedaPrestamo);
        txtBusquedaPrestamo.setBounds(80, 150, 340, 20);

        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel30.setText("Busqueda:");
        jPPrestamo.add(jLabel30);
        jLabel30.setBounds(10, 150, 60, 20);

        btnPedirNuevo.setBackground(new java.awt.Color(93, 100, 127));
        btnPedirNuevo.setText("Nuevo Pedido");
        btnPedirNuevo.setColorHover(new java.awt.Color(75, 185, 236));
        btnPedirNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPedirNuevoActionPerformed(evt);
            }
        });
        jPPrestamo.add(btnPedirNuevo);
        btnPedirNuevo.setBounds(20, 400, 140, 40);

        btnVerSanciones.setBackground(new java.awt.Color(0, 160, 119));
        btnVerSanciones.setText("Sanciones");
        btnVerSanciones.setColorHover(new java.awt.Color(75, 185, 236));
        btnVerSanciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerSancionesActionPerformed(evt);
            }
        });
        jPPrestamo.add(btnVerSanciones);
        btnVerSanciones.setBounds(510, 400, 140, 40);

        lblFondo1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/imagenes/LibroFondo.png"))); // NOI18N
        jPPrestamo.add(lblFondo1);
        lblFondo1.setBounds(264, 114, 400, 330);

        panelSelector.addTab("Mis Préstamos", jPPrestamo);

        jPSansiones.setBackground(new java.awt.Color(255, 255, 255));
        jPSansiones.setLayout(null);

        jPanel11.setBackground(new java.awt.Color(119, 136, 153));
        jPanel11.setLayout(null);

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Sanciones");
        jPanel11.add(jLabel11);
        jLabel11.setBounds(125, 1, 390, 100);

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/imagenes/Advertencia.png"))); // NOI18N
        jPanel11.add(jLabel12);
        jLabel12.setBounds(0, 0, 120, 100);

        jPSansiones.add(jPanel11);
        jPanel11.setBounds(0, 9, 660, 110);

        tblSancion.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane7.setViewportView(tblSancion);

        jPSansiones.add(jScrollPane7);
        jScrollPane7.setBounds(10, 140, 640, 230);

        lblAvisoSancion.setBackground(new java.awt.Color(255, 255, 255));
        lblAvisoSancion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAvisoSancion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/imagenes/MasInformacion.png"))); // NOI18N
        lblAvisoSancion.setToolTipText("");
        jPSansiones.add(lblAvisoSancion);
        lblAvisoSancion.setBounds(560, 380, 90, 60);

        jScrollPane2.setToolTipText("");

        jTextPane1.setEditable(false);
        jTextPane1.setText("Tenga en cuenta que en caso de contar\ncon alguna sanción, deberá acercarse a\nla Biblioteca lo más pronto posible.");
        jTextPane1.setAutoscrolls(false);
        jScrollPane2.setViewportView(jTextPane1);

        jPSansiones.add(jScrollPane2);
        jScrollPane2.setBounds(380, 380, 270, 60);

        lblFondo2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/imagenes/LibroFondo.png"))); // NOI18N
        jPSansiones.add(lblFondo2);
        lblFondo2.setBounds(264, 114, 400, 330);

        panelSelector.addTab("Sanciones", jPSansiones);

        jPDatos.setBackground(new java.awt.Color(255, 255, 255));
        jPDatos.setLayout(null);

        jPanel12.setBackground(new java.awt.Color(119, 136, 153));
        jPanel12.setLayout(null);

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Mis Datos");
        jPanel12.add(jLabel13);
        jLabel13.setBounds(125, 1, 390, 100);

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/imagenes/EditarPerfil.png"))); // NOI18N
        jPanel12.add(jLabel14);
        jLabel14.setBounds(0, 0, 120, 100);

        jPDatos.add(jPanel12);
        jPanel12.setBounds(0, 9, 660, 110);

        jPanel2.setLayout(null);

        btnActualizarDatos.setText("Actualizar Datos");
        btnActualizarDatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarDatosActionPerformed(evt);
            }
        });
        jPanel2.add(btnActualizarDatos);
        btnActualizarDatos.setBounds(50, 230, 210, 40);

        txtEmail.setToolTipText("La busqueda es por el Título del Documento");
        jPanel2.add(txtEmail);
        txtEmail.setBounds(10, 60, 280, 30);

        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel31.setText("Correo Electónico:");
        jPanel2.add(jLabel31);
        jLabel31.setBounds(10, 30, 280, 30);

        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel32.setText("Dirección:");
        jPanel2.add(jLabel32);
        jLabel32.setBounds(10, 90, 280, 30);

        txtDireccion.setToolTipText("La busqueda es por el Título del Documento");
        jPanel2.add(txtDireccion);
        txtDireccion.setBounds(10, 120, 280, 30);

        txtTelefono.setToolTipText("La busqueda es por el Título del Documento");
        jPanel2.add(txtTelefono);
        txtTelefono.setBounds(10, 180, 280, 30);

        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel33.setText("Teléfono:");
        jPanel2.add(jLabel33);
        jLabel33.setBounds(10, 150, 280, 30);

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel2.add(jSeparator1);
        jSeparator1.setBounds(320, 10, 10, 260);

        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel34.setText("Contraseña Actual:");
        jPanel2.add(jLabel34);
        jLabel34.setBounds(350, 50, 210, 40);

        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel35.setText("Nueva Contraseña:");
        jPanel2.add(jLabel35);
        jLabel35.setBounds(350, 130, 210, 40);

        txtCNueva.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtCNueva.setPlaceholder("Nueva contraseña");
        jPanel2.add(txtCNueva);
        txtCNueva.setBounds(350, 170, 210, 40);

        txtCActual.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtCActual.setPlaceholder("Contaseña Actual");
        jPanel2.add(txtCActual);
        txtCActual.setBounds(350, 90, 210, 40);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Actualizar Datos");
        jPanel2.add(jLabel1);
        jLabel1.setBounds(10, 0, 280, 30);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Actualizar Contraseña");
        jPanel2.add(jLabel2);
        jLabel2.setBounds(350, 20, 210, 30);

        btnActualizarContraseña.setText("Actualizar");
        btnActualizarContraseña.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarContraseñaActionPerformed(evt);
            }
        });
        jPanel2.add(btnActualizarContraseña);
        btnActualizarContraseña.setBounds(360, 230, 200, 40);

        jPDatos.add(jPanel2);
        jPanel2.setBounds(20, 130, 620, 280);

        lblFondo3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/imagenes/LibroFondo.png"))); // NOI18N
        jPDatos.add(lblFondo3);
        lblFondo3.setBounds(264, 114, 400, 330);

        panelSelector.addTab("Mi Cuenta", jPDatos);

        jPanel1.add(panelSelector);
        panelSelector.setBounds(0, 80, 800, 460);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 800, 540);

        setSize(new java.awt.Dimension(800, 540));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCerrarVentanaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarVentanaActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnCerrarVentanaActionPerformed

    private void cbxTipoDocumentoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxTipoDocumentoItemStateChanged
        cargarTablaDocumento();
        cargarVistaPrevia();
    }//GEN-LAST:event_cbxTipoDocumentoItemStateChanged

    private void tblDocumentosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDocumentosMouseClicked
        if (evt.getClickCount() >= 2) {
            cargarVerInformacion();
        } else {
            cargarVistaPrevia();
        }
    }//GEN-LAST:event_tblDocumentosMouseClicked

    private void btnInformacionDocumentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInformacionDocumentoActionPerformed
        cargarVerInformacion();
    }//GEN-LAST:event_btnInformacionDocumentoActionPerformed

    private void txtbusquedaDocumentoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbusquedaDocumentoKeyReleased
        buscarDocumento();
    }//GEN-LAST:event_txtbusquedaDocumentoKeyReleased

    private void btnPedirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPedirActionPerformed
        pedirPrestamo();
    }//GEN-LAST:event_btnPedirActionPerformed

    private void btnPedirNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPedirNuevoActionPerformed
        panelSelector.setSelectedIndex(0);
    }//GEN-LAST:event_btnPedirNuevoActionPerformed

    private void btnVerSancionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerSancionesActionPerformed
        panelSelector.setSelectedIndex(2);
    }//GEN-LAST:event_btnVerSancionesActionPerformed

    private void txtBusquedaPrestamoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusquedaPrestamoKeyReleased
        buscarPrestamo();
    }//GEN-LAST:event_txtBusquedaPrestamoKeyReleased

    private void btnCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarSesionActionPerformed
        dispose();
        new FrmInicioSesion().setVisible(true);
    }//GEN-LAST:event_btnCerrarSesionActionPerformed

    private void btnActualizarDatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarDatosActionPerformed
        actualizarDatos();
    }//GEN-LAST:event_btnActualizarDatosActionPerformed

    private void btnActualizarContraseñaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarContraseñaActionPerformed
        actualizarClave();
    }//GEN-LAST:event_btnActualizarContraseñaActionPerformed

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
            java.util.logging.Logger.getLogger(FrmUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FrmUsuario dialog = new FrmUsuario(new javax.swing.JFrame(), true);
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
    private rojeru_san.RSButton btnActualizarContraseña;
    private rojeru_san.RSButton btnActualizarDatos;
    private rojeru_san.RSButtonRiple btnCerrarSesion;
    private rojeru_san.RSButton btnCerrarVentana;
    private rojeru_san.RSButton btnInformacionDocumento;
    private rojeru_san.RSButton btnPedir;
    private rojeru_san.RSButton btnPedirNuevo;
    private rojeru_san.RSButton btnVerSanciones;
    private javax.swing.JComboBox<String> cbxTipoDocumento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPDatos;
    private javax.swing.JPanel jPDocumento;
    private javax.swing.JPanel jPPrestamo;
    private javax.swing.JPanel jPSansiones;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JLabel lblAvisoSancion;
    private javax.swing.JLabel lblBienvenida;
    private javax.swing.JLabel lblFondo;
    private javax.swing.JLabel lblFondo1;
    private javax.swing.JLabel lblFondo2;
    private javax.swing.JLabel lblFondo3;
    private org.edisoncor.gui.panel.Panel panelEncabezado;
    private org.edisoncor.gui.tabbedPane.TabbedSelector2 panelSelector;
    private javax.swing.JTable tblDocumentos;
    private javax.swing.JTable tblPrestamo;
    private javax.swing.JTable tblSancion;
    private javax.swing.JTextField txtBusquedaPrestamo;
    private rojeru_san.RSMPassView txtCActual;
    private rojeru_san.RSMPassView txtCNueva;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextArea txtTituloDocumento;
    private javax.swing.JTextArea txtVerInformacion;
    private javax.swing.JTextField txtbusquedaDocumento;
    // End of variables declaration//GEN-END:variables
}
