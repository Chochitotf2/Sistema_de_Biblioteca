/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import javax.swing.UIManager;
import vista.utilidades.UtilidadesComponente;

/**
 *
 * @author user
 */
public class FrmUsuario extends javax.swing.JDialog {

    /**
     * Creates new form FrmUsuario
     */
    public FrmUsuario(java.awt.Frame parent, boolean modal) {
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
        cbxTipoBuscar = new javax.swing.JComboBox<>();
        txtBuscar = new javax.swing.JTextField();
        btnBusquedaAvanzada = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblResultados = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();
        btnInformacion = new javax.swing.JButton();
        btnSolicitar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblPrestamos = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblMultas = new javax.swing.JTable();
        btnCerrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Panel de Usuario");
        setIconImage(UtilidadesComponente.obtenerIcono());
        setPreferredSize(new java.awt.Dimension(0, 0));
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(169, 169, 169));
        jPanel1.setLayout(null);

        jPanel2.setBackground(new java.awt.Color(84, 125, 138));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Documentos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(230, 231, 233))); // NOI18N
        jPanel2.setLayout(null);

        cbxTipoBuscar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todo", "Título", "Autor" }));
        jPanel2.add(cbxTipoBuscar);
        cbxTipoBuscar.setBounds(30, 30, 70, 20);
        jPanel2.add(txtBuscar);
        txtBuscar.setBounds(110, 30, 250, 20);

        btnBusquedaAvanzada.setForeground(new java.awt.Color(230, 231, 233));
        btnBusquedaAvanzada.setText("Búsqueda Avanzada");
        btnBusquedaAvanzada.setBorder(null);
        btnBusquedaAvanzada.setBorderPainted(false);
        btnBusquedaAvanzada.setContentAreaFilled(false);
        jPanel2.add(btnBusquedaAvanzada);
        btnBusquedaAvanzada.setBounds(360, 30, 110, 20);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(240, 240, 240));
        jLabel1.setText("Resultados de Busqueda");
        jPanel2.add(jLabel1);
        jLabel1.setBounds(170, 70, 140, 14);

        tblResultados.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblResultados);

        jPanel2.add(jScrollPane1);
        jScrollPane1.setBounds(10, 90, 460, 120);
        jPanel2.add(jSeparator1);
        jSeparator1.setBounds(10, 220, 460, 2);

        btnInformacion.setText("Ver Información");
        jPanel2.add(btnInformacion);
        btnInformacion.setBounds(120, 230, 120, 20);

        btnSolicitar.setText("Solicitar");
        jPanel2.add(btnSolicitar);
        btnSolicitar.setBounds(260, 230, 73, 20);

        jPanel1.add(jPanel2);
        jPanel2.setBounds(10, 10, 480, 260);

        jPanel3.setBackground(new java.awt.Color(85, 111, 122));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Préstamos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(230, 231, 233))); // NOI18N
        jPanel3.setLayout(null);

        tblPrestamos.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tblPrestamos);

        jPanel3.add(jScrollPane2);
        jScrollPane2.setBounds(10, 20, 460, 110);

        jPanel1.add(jPanel3);
        jPanel3.setBounds(10, 270, 480, 150);

        jPanel4.setBackground(new java.awt.Color(121, 128, 134));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Multas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(230, 231, 233))); // NOI18N
        jPanel4.setLayout(null);

        tblMultas.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(tblMultas);

        jPanel4.add(jScrollPane3);
        jScrollPane3.setBounds(10, 20, 460, 100);

        jPanel1.add(jPanel4);
        jPanel4.setBounds(10, 420, 480, 140);

        btnCerrar.setText("Cerrar");
        jPanel1.add(btnCerrar);
        btnCerrar.setBounds(220, 570, 63, 23);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 500, 600);

        setSize(new java.awt.Dimension(516, 639));
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
    private javax.swing.JButton btnBusquedaAvanzada;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnInformacion;
    private javax.swing.JButton btnSolicitar;
    private javax.swing.JComboBox<String> cbxTipoBuscar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable tblMultas;
    private javax.swing.JTable tblPrestamos;
    private javax.swing.JTable tblResultados;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}