
package Restaurante;

import javax.swing.JOptionPane;
import conexion.CreateConection;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.io.File;
import java.sql.DriverManager;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class Clientes extends javax.swing.JInternalFrame {

    CreateConection conexionpostgres = new CreateConection();
    Connection con;

    public Clientes() throws SQLException {
        initComponents();
        con = conexionpostgres.getConection();

        this.setClosable(true);
        this.setMaximizable(true);
        this.setIconifiable(true);
        this.setResizable(true);
        this.setTitle("Gestión de Clientes");
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtCliente_id = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        txtCorreo = new javax.swing.JTextField();
        txtFecha_ingreso = new javax.swing.JTextField();
        txtDireccion = new javax.swing.JTextField();
        BtnRegistrar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnConsultar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnReporte = new javax.swing.JButton(); 

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jTextField1.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18));
        jTextField1.setText("GESTIÓN DE CLIENTES");
        jTextField1.setEditable(false);
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel1.setText("ID");
        jLabel2.setText("Nombre");
        jLabel3.setText("Teléfono");
        jLabel4.setText("Correo");
        jLabel5.setText("Dirección");
        jLabel6.setText("Fecha Ingreso");

        BtnRegistrar.setText("REGISTRAR");
        BtnRegistrar.addActionListener(evt -> BtnRegistrarActionPerformed(evt));

        btnActualizar.setText("ACTUALIZAR");
        btnActualizar.addActionListener(evt -> btnActualizarActionPerformed(evt));

        btnConsultar.setText("CONSULTAR");
        btnConsultar.addActionListener(evt -> btnConsultarActionPerformed(evt));

        btnEliminar.setText("ELIMINAR");
        btnEliminar.addActionListener(evt -> btnEliminarActionPerformed(evt));

        btnReporte.setText("REPORTE");
        btnReporte.addActionListener(evt -> generarReporte());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(40)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1)
                                .addComponent(jLabel2)
                                .addComponent(jLabel3)
                                .addComponent(jLabel4)
                                .addComponent(jLabel5)
                                .addComponent(jLabel6))
                            .addGap(20)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtCliente_id)
                                .addComponent(txtNombre)
                                .addComponent(txtTelefono)
                                .addComponent(txtCorreo)
                                .addComponent(txtDireccion)
                                .addComponent(txtFecha_ingreso, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)))))
                .addGroup(layout.createSequentialGroup()
                    .addGap(50)
                    .addComponent(BtnRegistrar)
                    .addGap(10)
                    .addComponent(btnActualizar)
                    .addGap(10)
                    .addComponent(btnConsultar)
                    .addGap(10)
                    .addComponent(btnEliminar)
                    .addGap(10)
                    .addComponent(btnReporte))
        );

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(20)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(txtCliente_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(5)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(5)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(5)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(5)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(5)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(txtFecha_ingreso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(15)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(BtnRegistrar)
                        .addComponent(btnActualizar)
                        .addComponent(btnConsultar)
                        .addComponent(btnEliminar)
                        .addComponent(btnReporte))
                    .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }

    // ---------------- MÉTODOS DE BOTONES ----------------
    private void BtnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {
        try (Connection conn = new CreateConection().getConection()) {
            String sql = "INSERT INTO Clientes(Nombre,Telefono,Correo,Direccion,Fecha_ingreso) VALUES(?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, txtNombre.getText());
            ps.setInt(2, Integer.parseInt(txtTelefono.getText()));
            ps.setString(3, txtCorreo.getText());
            ps.setString(4, txtDireccion.getText());

            String fecha_ingreso = txtFecha_ingreso.getText();
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
            java.util.Date fechaUtil = sdf.parse(fecha_ingreso);
            java.sql.Date fechaSQL = new java.sql.Date(fechaUtil.getTime());
            ps.setDate(5, fechaSQL);

            JOptionPane.showMessageDialog(this, "Filas insertadas: " + ps.executeUpdate());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al insertar: " + ex.getMessage());
        }
    }

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {
        try (Connection conn = new CreateConection().getConection()) {
            String sql = "UPDATE Clientes SET Nombre=?, Telefono=?, Direccion=?, Correo=?, Fecha_ingreso=? WHERE Cliente_id=?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, txtNombre.getText());
            ps.setInt(2, Integer.parseInt(txtTelefono.getText()));
            ps.setString(3, txtDireccion.getText());
            ps.setString(4, txtCorreo.getText());

            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
            java.util.Date fechaUtil = sdf.parse(txtFecha_ingreso.getText());
            java.sql.Date fechaSQL = new java.sql.Date(fechaUtil.getTime());
            ps.setDate(5, fechaSQL);
            ps.setInt(6, Integer.parseInt(txtCliente_id.getText()));

            JOptionPane.showMessageDialog(this, "Filas actualizadas: " + ps.executeUpdate());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al actualizar: " + ex.getMessage());
        }
    }

    private void btnConsultarActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            int id = Integer.parseInt(txtCliente_id.getText().trim());
            String qry = "SELECT * FROM public.Clientes WHERE Cliente_id = ?";

            try (PreparedStatement ps = con.prepareStatement(qry)) {
                ps.setInt(1, id);

                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        txtNombre.setText(rs.getString("Nombre"));
                        txtTelefono.setText(rs.getString("Telefono"));
                        txtDireccion.setText(rs.getString("Direccion"));
                        txtCorreo.setText(rs.getString("Correo"));
                        txtFecha_ingreso.setText(rs.getString("Fecha_ingreso"));

                        JOptionPane.showMessageDialog(this, "Registro encontrado: " + txtNombre.getText());
                    } else {
                        JOptionPane.showMessageDialog(this, "No se encontró el cliente con ID: " + id);
                    }
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al consultar: " + e.getMessage());
        }
    }

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {
        try (Connection conn = new CreateConection().getConection()) {
            String sql = "DELETE FROM Clientes WHERE Cliente_id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(txtCliente_id.getText()));
            JOptionPane.showMessageDialog(this, "Filas eliminadas: " + ps.executeUpdate());

            txtCliente_id.setText("");
            txtNombre.setText("");
            txtTelefono.setText("");
            txtDireccion.setText("");
            txtCorreo.setText("");
            txtFecha_ingreso.setText("");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al eliminar: " + ex.getMessage());
        }
    }

    // ---------------- MÉTODO DEL REPORTE ----------------
    private void generarReporte() {
        String reportPath = "C:\\Users\\50242\\JaspersoftWorkspace\\MyReports\\jrClientes.jrxml";
        File file = new File(reportPath);

        if (!file.exists()) {
            JOptionPane.showMessageDialog(this, "El archivo no existe: " + reportPath);
            return;
        }

        try {
            Class.forName("org.postgresql.Driver");
            try (Connection con = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/Restaurante", "postgres", "1234")) {

                JasperReport jr = JasperCompileManager.compileReport(reportPath);
                JasperPrint jp = JasperFillManager.fillReport(jr, null, con);
                JasperViewer.viewReport(jp, false);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // ---------------- VARIABLES ----------------
    private javax.swing.JButton BtnRegistrar;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnConsultar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnReporte;
    private javax.swing.JLabel jLabel1, jLabel2, jLabel3, jLabel4, jLabel5, jLabel6;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField txtCliente_id;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtFecha_ingreso;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtTelefono;
}
