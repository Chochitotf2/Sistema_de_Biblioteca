package vista;

import controlador.servicio.DocumentoNoConvencionalServicio;
import controlador.servicio.DocumentoServicio;
import controlador.servicio.LibroServicio;
import controlador.servicio.PrestamoServicio;
import controlador.servicio.RevistaServicio;
import controlador.servicio.SancionServicio;
import controlador.servicio.TesisServicio;
import vista.tablas.ModeloTablaDocumento;
import vista.tablas.ModeloTablaPrestamo;
import vista.tablas.ModeloTablaSancion;
import static vista.utilidades.UtilidadesComponente.*;
import static controlador.utilidades.Utilidades.*;
import java.util.Date;
import java.awt.Color;

/**
 *
 * @author Víctor Andrés Rojas
 */
public class FrmBibliotecario extends javax.swing.JDialog {
    
    private SancionServicio sS = new SancionServicio();
    private PrestamoServicio pS = new PrestamoServicio();
    private DocumentoServicio dS = new DocumentoServicio();
    private LibroServicio lS = new LibroServicio();
    private RevistaServicio rS = new RevistaServicio();
    private TesisServicio tS = new TesisServicio();
    private DocumentoNoConvencionalServicio dNCS = new DocumentoNoConvencionalServicio();
    private ModeloTablaDocumento modeloDocumento = new ModeloTablaDocumento();
    private ModeloTablaPrestamo modeloPrestamo = new ModeloTablaPrestamo();
    private ModeloTablaSancion modeloSancion = new ModeloTablaSancion();

    /**
     * Creates new form FrmBibliotecario
     */
    public FrmBibliotecario(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        limpiar();
    }
    
    private void cargarTablaDocumento() {
        if (cbxTipoDocumento.getSelectedItem().toString().equals("Todos")) {
            modeloDocumento.setLista(dS.listar());
        } else {
            modeloDocumento.setLista(dS.listarDocumentoTipo(cbxTipoDocumento.getSelectedItem().toString()));
        }
        tblDocumentos.setModel(modeloDocumento);
        tblDocumentos.updateUI();
    }
    
    private void cargarTablaPrestamo() {
        modeloPrestamo.setLista(pS.listar());
        tblPrestamo.setModel(modeloPrestamo);
        tblPrestamo.updateUI();
    }
    
    private void cargarTablaSancion() {
        modeloSancion.setLista(sS.listar());
        tblSanciones.setModel(modeloSancion);
        tblSanciones.updateUI();
    }
    
    private void habilitarCampos() {
        switch (cbxTipodeDocumento.getSelectedItem().toString()) {
            case "Libro":
                txtAutor.setEnabled(true);
                sprEdicion.setEnabled(true);
                txtAnio.setEnabled(true);
                txtEditorial.setEnabled(true);
                txtIsbn.setEnabled(true);
                break;
            case "Revista":
                txtFechaPublicacion.setEnabled(true);
                txtIssn.setEnabled(true);
                txtEditorial.setEnabled(true);
                break;
            case "Tesis":
                txtAutor.setEnabled(true);
                txtAsesor.setEnabled(true);
                txtFacultad.setEnabled(true);
                txtFechaPublicacion.setEnabled(true);
                break;
            case "Documento no Convencional":
                txtAutor.setEnabled(true);
                cbxTipoNoConvencional.setEnabled(true);
                break;
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
    
    private void repintarComponentes() {
        txtTitulo.setBackground(Color.WHITE);
        txtAutor.setBackground(Color.WHITE);
        txtAsesor.setBackground(Color.WHITE);
        txtEditorial.setBackground(Color.WHITE);
        txtFacultad.setBackground(Color.WHITE);
        txtIsbn.setBackground(Color.WHITE);
        txtIssn.setBackground(Color.WHITE);
    }
    
    private void limpiar() {
        sS.fijarSancion(null);
        pS.fijarPrestamo(null);
        dS.fijarDocumento(null);
        lS.fijarLibro(null);
        rS.fijarRevista(null);
        tS.fijarTesis(null);
        dNCS.fijarDocumentoNoConvensional(null);
        cbxTipodeDocumento.setEnabled(true);
        cbxTipodeDocumento.setSelectedIndex(0);
        txtTitulo.setText(null);
        txtAutor.setText(null);
        sprEdicion.setValue(0);
        txtAnio.setText("2000");
        txtEditorial.setText(null);
        txtIsbn.setText(null);
        txtFechaPublicacion.setText("01/01/2000");
        txtIssn.setText(null);
        txtAsesor.setText(null);
        txtFacultad.setText(null);
        cbxTipoNoConvencional.setSelectedIndex(0);
        txtMonto.setText(null);
        txtMontoEdicion.setText(null);
        repintarComponentes();
        deshabilitarCampos();
        habilitarCampos();
        codigoDocumento();
        cargarTablaDocumento();
        cargarTablaPrestamo();
        cargarTablaSancion();
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
        switch (cbxTipodeDocumento.getSelectedItem().toString()) {
            case "Libro":
                lS.obtenerLibro().setTitulo(txtTitulo.getText().trim());
                lS.obtenerLibro().setCodigo(txtCodigo.getText().trim());
                lS.obtenerLibro().setTipoDocumento(cbxTipodeDocumento.getSelectedItem().toString());
                lS.obtenerLibro().setAutores(txtAutor.getText().trim());
                lS.obtenerLibro().setEdicion(sprEdicion.getValue().toString());
                lS.obtenerLibro().setAnio(txtAnio.getText().trim());
                lS.obtenerLibro().setEditorial(txtEditorial.getText().trim());
                lS.obtenerLibro().setIsbn(txtIsbn.getText().trim());
                break;
            case "Revista":
                rS.obtenerRevista().setTitulo(txtTitulo.getText().trim());
                rS.obtenerRevista().setCodigo(txtCodigo.getText().trim());
                rS.obtenerRevista().setTipoDocumento(cbxTipodeDocumento.getSelectedItem().toString());
                rS.obtenerRevista().setFechaPublicacion(new Date(txtFechaPublicacion.getText()));
                rS.obtenerRevista().setIssn(txtIssn.getText().trim());
                rS.obtenerRevista().setEditorial(txtEditorial.getText().trim());
                break;
            case "Tesis":
                tS.obtenerTesis().setTitulo(txtTitulo.getText().trim());
                tS.obtenerTesis().setCodigo(txtCodigo.getText().trim());
                tS.obtenerTesis().setTipoDocumento(cbxTipodeDocumento.getSelectedItem().toString());
                tS.obtenerTesis().setAutores(txtAutor.getText().trim());
                tS.obtenerTesis().setAsesores(txtAsesor.getText().trim());
                tS.obtenerTesis().setFacultad(txtFacultad.getText().trim());
                tS.obtenerTesis().setFechaPublicacion(new Date(txtFechaPublicacion.getText()));
                break;
            case "Documento no Convencional":
                dNCS.obtenerDocumentoNoConvensional().setTitulo(txtTitulo.getText().trim());
                dNCS.obtenerDocumentoNoConvensional().setCodigo(txtCodigo.getText().trim());
                dNCS.obtenerDocumentoNoConvensional().setTipoDocumento(cbxTipodeDocumento.getSelectedItem().toString());
                dNCS.obtenerDocumentoNoConvensional().setAutor(txtAutor.getText().trim());
                dNCS.obtenerDocumentoNoConvensional().setTipoNoConvencional(cbxTipoNoConvencional.getSelectedItem().toString());
                break;
        }
    }
    
    private void cargarEdicion() {
        int fila = tblDocumentos.getSelectedRow();
        if (fila >= 0) {
            dS.fijarDocumento(modeloDocumento.getLista().get(fila));
            txtTitulo.setText(dS.obtenerDocumento().getTitulo());
            txtCodigo.setText(dS.obtenerDocumento().getCodigo());
            cbxTipodeDocumento.setSelectedItem(dS.obtenerDocumento().getTipoDocumento());
            cbxTipodeDocumento.setEnabled(false);
            switch (cbxTipodeDocumento.getSelectedItem().toString()) {
                case "Libro":
                    lS.fijarLibro(lS.obtenerLibro(dS.obtenerDocumento().getId()));
                    txtAutor.setText(lS.obtenerLibro().getAutores());
                    sprEdicion.setValue(Integer.parseInt(lS.obtenerLibro().getEdicion()));
                    txtAnio.setText(lS.obtenerLibro().getAnio());
                    txtEditorial.setText(lS.obtenerLibro().getEditorial());
                    txtIsbn.setText(lS.obtenerLibro().getIsbn());
                    break;
                case "Revista":
                    rS.fijarRevista(rS.obtenerRevista(dS.obtenerDocumento().getId()));
                    txtFechaPublicacion.setText(formatearFecha(rS.obtenerRevista().getFechaPublicacion()));
                    txtIssn.setText(rS.obtenerRevista().getIssn());
                    txtEditorial.setText(rS.obtenerRevista().getEditorial());
                    break;
                case "Tesis":
                    tS.fijarTesis(tS.obtenerTesis(dS.obtenerDocumento().getId()));
                    txtAutor.setText(tS.obtenerTesis().getAutores());
                    txtAsesor.setText(tS.obtenerTesis().getAsesores());
                    txtFacultad.setText(tS.obtenerTesis().getFacultad());
                    txtFechaPublicacion.setText(formatearFecha(tS.obtenerTesis().getFechaPublicacion()));
                    break;
                case "Documento no Convencional":
                    dNCS.fijarDocumentoNoConvensional(dNCS.obtenerDocumentoNoConvensional(dS.obtenerDocumento().getId()));
                    txtAutor.setText(dNCS.obtenerDocumentoNoConvensional().getAutor());
                    cbxTipoNoConvencional.setSelectedItem(dNCS.obtenerDocumentoNoConvensional().getTipoNoConvencional());
                    break;
            }
        } else {
            mensajeError("Advertencia", "Debe seleccionar un Documento de la Tabla.");
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
        switch (cbxTipodeDocumento.getSelectedItem().toString()) {
            case "Libro":
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
                break;
            case "Revista":
                if (mostrarError(txtFechaPublicacion, null, 'n')) {
                    verificador = true;
                }
                if (mostrarError(txtIssn, null, 'n')) {
                    verificador = true;
                }
                if (mostrarError(txtEditorial, null, 'n')) {
                    verificador = true;
                }
                break;
            case "Tesis":
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
                break;
            case "Documento no Convencional":
                if (mostrarError(txtAutor, "El Autor solo puede contener letras", 't')) {
                    verificador = true;
                }
                break;
        }
        return verificador;
    }
    
    private void registrar() {
        if (!errores()) {
            cargarobjeto();
            switch (cbxTipodeDocumento.getSelectedItem().toString()) {
                case "Libro":
                    if (lS.obtenerLibro().getId() != null) {
                        if (lS.guardar()) {
                            mensajeOK("Aviso", "Se ha modificado con éxito.");
                            limpiar();
                        } else {
                            mensajeError("Error", "Ha ocurrido un error al realizar su registro.");
                        }
                    } else {
                        if (lS.guardar()) {
                            mensajeOK("Aviso", "Se ha registrado con éxito.");
                            limpiar();
                        } else {
                            mensajeError("Error", "Ha ocurrido un error al realizar su registro.");
                        }
                    }
                    break;
                case "Revista":
                    if (rS.obtenerRevista().getId() != null) {
                        if (rS.guardar()) {
                            mensajeOK("Aviso", "Se ha modificado con éxito.");
                            limpiar();
                        } else {
                            mensajeError("Error", "Ha ocurrido un error al realizar su registro.");
                        }
                    } else {
                        if (rS.guardar()) {
                            mensajeOK("Aviso", "Se ha registrado con éxito.");
                            limpiar();
                        } else {
                            mensajeError("Error", "Ha ocurrido un error al realizar su registro.");
                        }
                    }
                    break;
                case "Tesis":
                    if (tS.obtenerTesis().getId() != null) {
                        if (tS.guardar()) {
                            mensajeOK("Aviso", "Se ha modificado con éxito.");
                            limpiar();
                        } else {
                            mensajeError("Error", "Ha ocurrido un error al realizar su registro.");
                        }
                    } else {
                        if (tS.guardar()) {
                            mensajeOK("Aviso", "Se ha registrado con éxito.");
                            limpiar();
                        } else {
                            mensajeError("Error", "Ha ocurrido un error al realizar su registro.");
                        }
                    }
                    break;
                case "Documento no Convencional":
                    if (dNCS.obtenerDocumentoNoConvensional().getId() != null) {
                        if (dNCS.guardar()) {
                            mensajeOK("Aviso", "Se ha modificado con éxito.");
                            limpiar();
                        } else {
                            mensajeError("Error", "Ha ocurrido un error al realizar su registro.");
                        }
                    } else {
                        if (dNCS.guardar()) {
                            mensajeOK("Aviso", "Se ha registrado con éxito.");
                            limpiar();
                        } else {
                            mensajeError("Error", "Ha ocurrido un error al realizar su registro.");
                        }
                    }
                    break;
            }
            
        }
    }
    
    private void confirmarDevolucion() {
        int fila = tblPrestamo.getSelectedRow();
        if (fila >= 0) {
            if (rdDevuelto.isSelected()) {
                pS.fijarPrestamo(modeloPrestamo.getLista().get(fila));
                pS.obtenerPrestamo().setEstado(false);
                pS.obtenerPrestamo().getDocumento().setEstado(true);
                if (pS.guardar()) {
                    mensajeOK("Aviso", "Se ha confirmado la Devolución.");
                    limpiar();
                } else {
                    mensajeError("Error", "No se ha podido confirmar la Devolución.");
                }
            } else {
                mensajeError("Advertencia", "Active la casilla para confirmar que se ha devuelto el Documento seleccionado.");
            }
        } else {
            mensajeError("Advertencia", "Debe seleccionar un Documento de la Tabla.");
        }
    }
    
    private void sancionarPersona() {
        int fila = tblPrestamo.getSelectedRow();
        if (fila >= 0) {
            pS.fijarPrestamo(modeloPrestamo.getLista().get(fila));
            sS.obtenerSancion().setPrestamo(pS.obtenerPrestamo());
            sS.obtenerSancion().setFecha(new Date());
            if (!mostrarError(txtMonto, "El Monto solo puede contener números.", 'd')) {
                sS.obtenerSancion().setMonto(Double.valueOf(txtMonto.getText()));
                if (sS.guardar()) {
                    mensajeOK("Aviso", "Se ha generado una nueva Sanción con éxito.");
                    limpiar();
                    panelSelector.setSelectedIndex(3);
                } else {
                    mensajeError("Error", "No se ha podido guardar la Sanción.");
                }
            }
        } else {
            mensajeError("Advertencia", "Debe seleccionar una Persona de la Tabla.");
        }
    }
    
    private void modificarSansion() {
        int fila = tblSanciones.getSelectedRow();
        if (fila >= 0) {
            sS.fijarSancion(modeloSancion.getLista().get(fila));
            sS.obtenerSancion().setFecha(new Date());
            if (!mostrarError(txtMonto, "El Monto solo puede contener números.", 'd')) {
                txtMonto.setText(sS.obtenerSancion().getMonto().toString());
                if (sS.guardar()) {
                    mensajeOK("Aviso", "Se ha modificado el Monto con éxito.");
                    limpiar();
                } else {
                    mensajeError("Error", "No se ha podido modificar la Sanción.");
                }
            }
        } else {
            mensajeError("Advertencia", "Debe seleccionar una Sanción de la Tabla.");
        }
    }
    
    private void confirmarPago() {
        int fila = tblSanciones.getSelectedRow();
        if (fila >= 0) {
            if (chbxSi.isSelected()) {
                sS.fijarSancion(modeloSancion.getLista().get(fila));
                sS.obtenerSancion().setEstado(false);
                if (sS.guardar()) {
                    mensajeOK("Aviso", "Se ha quitado la Sanción con éxito.");
                    limpiar();
                } else {
                    mensajeError("Error", "Ha ocurrido un error al quitar la Sanción.");
                }
            } else {
                mensajeError("Advertencia", "Active la casilla para confirmar que se quitará la Sanción.");
            }
        } else {
            mensajeError("Advertencia", "Debe seleccionar una Sanción de la Tabla.");
        }
    }
    
    private void buscarDocumento() {
        if (txtbusquedaDocumento.getText().trim().length() >= 3) {
            if (cbxTipoDocumento.getSelectedItem().toString().equals("Todos")) {
                modeloDocumento.setLista(dS.listarDocumentoLike(txtbusquedaDocumento.getText().trim()));
            } else {
                modeloDocumento.setLista(dS.listarDocumentoTipoLike(cbxTipoDocumento.getSelectedItem().toString(), txtbusquedaDocumento.getText().trim()));
            }
            tblDocumentos.setModel(modeloDocumento);
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
    
    private void buscarSancion() {
        if (txtBusquedaSanciones.getText().trim().length() >= 3) {
            modeloSancion.setLista(sS.listarSancionLike(txtBusquedaSanciones.getText().trim()));
            tblSanciones.setModel(modeloSancion);
            tblSanciones.updateUI();
        } else {
            cargarTablaSancion();
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
        txtbusquedaDocumento = new javax.swing.JTextField();
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
        btnConfirmar = new javax.swing.JButton();
        rdDevuelto = new javax.swing.JRadioButton();
        btnSancionar = new javax.swing.JButton();
        txtBusquedaPrestamo = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        btnCancelarS = new javax.swing.JButton();
        jLabel27 = new javax.swing.JLabel();
        txtMonto = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblPrestamo = new javax.swing.JTable();
        jLabel28 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jPSansiones = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        btnQuitarSansion = new javax.swing.JButton();
        txtBusquedaSanciones = new javax.swing.JTextField();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblSanciones = new javax.swing.JTable();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        chbxSi = new javax.swing.JCheckBox();
        jLabel32 = new javax.swing.JLabel();
        txtMontoEdicion = new javax.swing.JTextField();
        btnEditarSancion = new javax.swing.JButton();
        btnCancelarS1 = new javax.swing.JButton();
        jLabel33 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
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
        tblDocumentos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDocumentosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblDocumentos);

        jPDocumento.add(jScrollPane1);
        jScrollPane1.setBounds(10, 130, 500, 170);

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Busqueda:");
        jPDocumento.add(jLabel3);
        jLabel3.setBounds(10, 310, 60, 20);

        txtbusquedaDocumento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbusquedaDocumentoKeyReleased(evt);
            }
        });
        jPDocumento.add(txtbusquedaDocumento);
        txtbusquedaDocumento.setBounds(80, 310, 250, 20);

        cbxTipoDocumento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Libro", "Revista", "Tesis", "Documento no Convencional" }));
        cbxTipoDocumento.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cbxTipoDocumento.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxTipoDocumentoItemStateChanged(evt);
            }
        });
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
        btnRegistrarNuevo.setBounds(400, 350, 110, 23);

        btnEditar.setText("Editar");
        btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        jPDocumento.add(btnEditar);
        btnEditar.setBounds(320, 350, 61, 23);

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
        txtAnio.setText("2000");
        jPRegistrar.add(txtAnio);
        txtAnio.setBounds(380, 280, 100, 20);

        sprEdicion.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        jPRegistrar.add(sprEdicion);
        sprEdicion.setBounds(380, 250, 100, 20);

        txtFechaPublicacion.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM))));
        txtFechaPublicacion.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFechaPublicacion.setToolTipText("Formato: dd/MM/yyyy");
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

        cbxTipoNoConvencional.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Película", "Disco de Música", "AudioLibro" }));
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

        btnConfirmar.setText("Confirmar");
        btnConfirmar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmarActionPerformed(evt);
            }
        });
        jPPrestamo.add(btnConfirmar);
        btnConfirmar.setBounds(360, 400, 80, 23);

        rdDevuelto.setText("Devuelto");
        rdDevuelto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        rdDevuelto.setOpaque(false);
        jPPrestamo.add(rdDevuelto);
        rdDevuelto.setBounds(400, 370, 70, 30);

        btnSancionar.setText("Sancionar");
        btnSancionar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSancionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSancionarActionPerformed(evt);
            }
        });
        jPPrestamo.add(btnSancionar);
        btnSancionar.setBounds(80, 400, 110, 23);

        txtBusquedaPrestamo.setToolTipText("La Búsqueda es por Título del Documento");
        txtBusquedaPrestamo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBusquedaPrestamoKeyReleased(evt);
            }
        });
        jPPrestamo.add(txtBusquedaPrestamo);
        txtBusquedaPrestamo.setBounds(80, 310, 250, 20);

        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel26.setText("Documento:");
        jPPrestamo.add(jLabel26);
        jLabel26.setBounds(340, 370, 60, 30);

        btnCancelarS.setText("Cancelar");
        btnCancelarS.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCancelarS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarSActionPerformed(evt);
            }
        });
        jPPrestamo.add(btnCancelarS);
        btnCancelarS.setBounds(210, 400, 80, 23);

        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel27.setText("$");
        jPPrestamo.add(jLabel27);
        jLabel27.setBounds(260, 370, 10, 20);

        txtMonto.setToolTipText("Monto de Multa");
        jPPrestamo.add(txtMonto);
        txtMonto.setBounds(80, 370, 180, 20);

        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel31.setText("Monto:");
        jPPrestamo.add(jLabel31);
        jLabel31.setBounds(10, 370, 60, 20);

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
        jScrollPane6.setViewportView(tblPrestamo);

        jPPrestamo.add(jScrollPane6);
        jScrollPane6.setBounds(10, 130, 500, 170);

        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel28.setText("Busqueda:");
        jPPrestamo.add(jLabel28);
        jLabel28.setBounds(10, 310, 60, 20);
        jPPrestamo.add(jSeparator3);
        jSeparator3.setBounds(40, 350, 420, 20);

        jSeparator4.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPPrestamo.add(jSeparator4);
        jSeparator4.setBounds(320, 360, 10, 60);

        panelSelector.addTab("Préstamos", jPPrestamo);

        jPSansiones.setBackground(new java.awt.Color(255, 255, 255));
        jPSansiones.setLayout(null);

        jPanel11.setBackground(new java.awt.Color(148, 169, 169));
        jPanel11.setLayout(null);

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Sanciones");
        jPanel11.add(jLabel10);
        jLabel10.setBounds(125, 1, 390, 100);

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/imagenes/iconoDocumento.png"))); // NOI18N
        jPanel11.add(jLabel11);
        jLabel11.setBounds(0, 0, 120, 100);

        jPSansiones.add(jPanel11);
        jPanel11.setBounds(0, 9, 600, 110);

        btnQuitarSansion.setText("Quitar Sanción");
        btnQuitarSansion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnQuitarSansion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitarSansionActionPerformed(evt);
            }
        });
        jPSansiones.add(btnQuitarSansion);
        btnQuitarSansion.setBounds(350, 400, 140, 23);

        txtBusquedaSanciones.setToolTipText("La busqueda es por el Nombre de Persona");
        txtBusquedaSanciones.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBusquedaSancionesKeyReleased(evt);
            }
        });
        jPSansiones.add(txtBusquedaSanciones);
        txtBusquedaSanciones.setBounds(80, 310, 250, 20);

        tblSanciones.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane7.setViewportView(tblSanciones);

        jPSansiones.add(jScrollPane7);
        jScrollPane7.setBounds(10, 130, 500, 170);

        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel29.setText("Busqueda:");
        jPSansiones.add(jLabel29);
        jLabel29.setBounds(10, 310, 60, 20);

        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel30.setText("Multa Pagada:");
        jPSansiones.add(jLabel30);
        jLabel30.setBounds(350, 370, 70, 20);
        jPSansiones.add(jSeparator2);
        jSeparator2.setBounds(42, 350, 420, 10);

        chbxSi.setText("Si");
        chbxSi.setOpaque(false);
        jPSansiones.add(chbxSi);
        chbxSi.setBounds(430, 370, 33, 23);

        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel32.setText("Monto:");
        jPSansiones.add(jLabel32);
        jLabel32.setBounds(20, 370, 60, 20);

        txtMontoEdicion.setToolTipText("Monto de Multa");
        jPSansiones.add(txtMontoEdicion);
        txtMontoEdicion.setBounds(90, 370, 180, 20);

        btnEditarSancion.setText("Editar Sanción");
        btnEditarSancion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEditarSancion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarSancionActionPerformed(evt);
            }
        });
        jPSansiones.add(btnEditarSancion);
        btnEditarSancion.setBounds(90, 400, 110, 23);

        btnCancelarS1.setText("Cancelar");
        btnCancelarS1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCancelarS1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarS1ActionPerformed(evt);
            }
        });
        jPSansiones.add(btnCancelarS1);
        btnCancelarS1.setBounds(220, 400, 80, 23);

        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel33.setText("$");
        jPSansiones.add(jLabel33);
        jLabel33.setBounds(270, 370, 10, 20);

        jSeparator5.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPSansiones.add(jSeparator5);
        jSeparator5.setBounds(320, 360, 10, 60);

        panelSelector.addTab("Sanciones", jPSansiones);

        jPanel1.add(panelSelector);
        panelSelector.setBounds(0, 0, 720, 470);

        jPPiedePagina.setLayout(null);

        btnCerrarSesion.setBackground(new java.awt.Color(13, 110, 110));
        btnCerrarSesion.setText("Cerrar Sesion");
        btnCerrarSesion.setColorHover(new java.awt.Color(141, 181, 181));
        btnCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarSesionActionPerformed(evt);
            }
        });
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
        repintarComponentes();
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
        cargarEdicion();
    }//GEN-LAST:event_btnEditarActionPerformed

    private void tblDocumentosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDocumentosMouseClicked
        if (evt.getClickCount() >= 2) {
            panelSelector.setSelectedIndex(1);
            cargarEdicion();
        }
    }//GEN-LAST:event_tblDocumentosMouseClicked

    private void btnConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarActionPerformed
        confirmarDevolucion();
    }//GEN-LAST:event_btnConfirmarActionPerformed

    private void btnSancionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSancionarActionPerformed
        sancionarPersona();
    }//GEN-LAST:event_btnSancionarActionPerformed

    private void btnQuitarSansionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitarSansionActionPerformed
        confirmarPago();
    }//GEN-LAST:event_btnQuitarSansionActionPerformed

    private void btnCancelarSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarSActionPerformed
        limpiar();
    }//GEN-LAST:event_btnCancelarSActionPerformed

    private void btnEditarSancionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarSancionActionPerformed
        modificarSansion();
    }//GEN-LAST:event_btnEditarSancionActionPerformed

    private void btnCancelarS1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarS1ActionPerformed
        limpiar();
    }//GEN-LAST:event_btnCancelarS1ActionPerformed

    private void cbxTipoDocumentoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxTipoDocumentoItemStateChanged
        cargarTablaDocumento();
    }//GEN-LAST:event_cbxTipoDocumentoItemStateChanged

    private void txtbusquedaDocumentoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbusquedaDocumentoKeyReleased
        buscarDocumento();
    }//GEN-LAST:event_txtbusquedaDocumentoKeyReleased

    private void txtBusquedaPrestamoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusquedaPrestamoKeyReleased
        buscarPrestamo();
    }//GEN-LAST:event_txtBusquedaPrestamoKeyReleased

    private void txtBusquedaSancionesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusquedaSancionesKeyReleased
        buscarSancion();
    }//GEN-LAST:event_txtBusquedaSancionesKeyReleased

    private void btnCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarSesionActionPerformed
        this.dispose();
        new FrmInicioSesion().setVisible(true);
    }//GEN-LAST:event_btnCerrarSesionActionPerformed

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
            java.util.logging.Logger.getLogger(FrmBibliotecario.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
            
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmBibliotecario.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
            
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmBibliotecario.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
            
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmBibliotecario.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
    private javax.swing.JButton btnCancelarS;
    private javax.swing.JButton btnCancelarS1;
    private rojeru_san.RSButton btnCerrarSesion;
    private javax.swing.JButton btnConfirmar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEditarSancion;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnQuitarSansion;
    private javax.swing.JButton btnRegistrarNuevo;
    private javax.swing.JButton btnSancionar;
    private javax.swing.JComboBox<String> cbxTipoDocumento;
    private javax.swing.JComboBox<String> cbxTipoNoConvencional;
    private javax.swing.JComboBox<String> cbxTipodeDocumento;
    private javax.swing.JCheckBox chbxSi;
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
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private org.edisoncor.gui.tabbedPane.TabbedSelector2 panelSelector;
    private javax.swing.JRadioButton rdDevuelto;
    private javax.swing.JSpinner sprEdicion;
    private javax.swing.JTable tblDocumentos;
    private javax.swing.JTable tblPrestamo;
    private javax.swing.JTable tblSanciones;
    private javax.swing.JFormattedTextField txtAnio;
    private javax.swing.JTextArea txtAsesor;
    private javax.swing.JTextArea txtAutor;
    private javax.swing.JTextField txtBusquedaPrestamo;
    private javax.swing.JTextField txtBusquedaSanciones;
    private javax.swing.JButton txtCancelar;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtEditorial;
    private javax.swing.JTextField txtFacultad;
    private javax.swing.JFormattedTextField txtFechaPublicacion;
    private javax.swing.JTextField txtIsbn;
    private javax.swing.JTextField txtIssn;
    private javax.swing.JTextField txtMonto;
    private javax.swing.JTextField txtMontoEdicion;
    private javax.swing.JTextField txtTitulo;
    private javax.swing.JTextField txtbusquedaDocumento;
    // End of variables declaration//GEN-END:variables
}
