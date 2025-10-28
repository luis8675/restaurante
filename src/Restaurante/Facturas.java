/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Restaurante;

import conexion.CreateConection;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Facturas extends javax.swing.JInternalFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Facturas.class.getName());
    private DefaultTableModel modelo;

    public Facturas() {
        initComponents();

        // Configuración del JInternalFrame
        this.setClosable(true);
        this.setMaximizable(true);
        this.setIconifiable(true);
        this.setResizable(true);

        configurarTabla();
        cargarClientes();
        cargarProductos();
        actualizarTotal(); // para mostrar 0 inicial
    }

    // ------------------ Configuración de tabla ------------------
    private void configurarTabla() {
        modelo = new DefaultTableModel(
                new Object[][]{},
                new String[]{"Factura / Fecha", "Producto", "Cantidad", "Precio", "Subtotal"}
        );
        tblProductos.setModel(modelo);
    }

    // ------------------ Cargar combos ------------------
    private void cargarClientes() {
        try (Connection conn = new CreateConection().getConection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("SELECT cliente_id, nombre FROM clientes")) {

            cmbCliente.removeAllItems();
            while (rs.next()) {
                cmbCliente.addItem(rs.getInt("cliente_id") + " - " + rs.getString("nombre"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar clientes: " + e.getMessage());
        }
    }

    private void cargarProductos() {
        try (Connection conn = new CreateConection().getConection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("SELECT producto_id, nombre_producto, precio FROM productos WHERE disponibilidad = true")) {

            cmbProducto.removeAllItems();
            while (rs.next()) {
                cmbProducto.addItem(rs.getInt("producto_id") + " - " + rs.getString("nombre_producto"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar productos: " + e.getMessage());
        }
    }

    // ------------------ Métodos auxiliares ------------------
    private double obtenerPrecioProducto(String producto) {
        double precio = 0;
        String[] partes = producto.split(" - ");
        int id = Integer.parseInt(partes[0]);

        try (Connection conn = new CreateConection().getConection();
             PreparedStatement ps = conn.prepareStatement("SELECT precio FROM productos WHERE producto_id = ?")) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) precio = rs.getDouble("precio");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al obtener precio: " + e.getMessage());
        }
        return precio;
    }

    private void actualizarTotal() {
        double subtotal = 0;
        for (int i = 0; i < modelo.getRowCount(); i++) {
            subtotal += Double.parseDouble(modelo.getValueAt(i, 4).toString());
        }

        double iva = subtotal * 0.12; // 12% de IVA
        double totalConIVA = subtotal + iva;

        lblIVA.setText("IVA (12%): Q" + String.format("%.2f", iva));
        lblTotal.setText("Total: Q" + String.format("%.2f", totalConIVA));
    }

    private double calcularTotalFactura() {
        double subtotal = 0;
        for (int i = 0; i < modelo.getRowCount(); i++) {
            subtotal += Double.parseDouble(modelo.getValueAt(i, 4).toString());
        }
        return subtotal + (subtotal * 0.12); // total con IVA
    }

    // ------------------ Botones ------------------
    private void agregarProducto() {
        try {
            String productoSeleccionado = (String) cmbProducto.getSelectedItem();
            if (productoSeleccionado == null || txtCantidad.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Seleccione un producto y cantidad válida.");
                return;
            }

            int cantidad = Integer.parseInt(txtCantidad.getText());
            double precio = obtenerPrecioProducto(productoSeleccionado);
            double subtotal = cantidad * precio;

            modelo.addRow(new Object[]{"-", productoSeleccionado, cantidad, precio, subtotal});
            actualizarTotal();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingrese una cantidad numérica válida.");
        }
    }

    private void eliminarProducto() {
        int filaSeleccionada = tblProductos.getSelectedRow();
        if (filaSeleccionada >= 0) {
            modelo.removeRow(filaSeleccionada);
            actualizarTotal();
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un producto para eliminar.");
        }
    }

    private void guardarFactura() {
        Connection conn = null;
        PreparedStatement psFactura = null;
        PreparedStatement psDetalle = null;
        ResultSet rs = null;

        try {
            conn = new CreateConection().getConection();
            conn.setAutoCommit(false);

            String clienteSeleccionado = (String) cmbCliente.getSelectedItem();
            if (clienteSeleccionado == null || modelo.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar un cliente y agregar productos.");
                return;
            }

            int clienteId = Integer.parseInt(clienteSeleccionado.split(" - ")[0]);
            double totalFactura = calcularTotalFactura();

            String sqlFactura = "INSERT INTO facturas (cliente_id, fecha, total, estado) VALUES (?, NOW(), ?, ?) RETURNING factura_id";
            psFactura = conn.prepareStatement(sqlFactura);
            psFactura.setInt(1, clienteId);
            psFactura.setDouble(2, totalFactura);
            psFactura.setString(3, "Emitida");

            rs = psFactura.executeQuery();
            int facturaId = 0;
            if (rs.next()) facturaId = rs.getInt("factura_id");

            String sqlDetalle = "INSERT INTO detalle_factura (factura_id, producto_id, cantidad, precio_unitario) VALUES (?, ?, ?, ?)";
            psDetalle = conn.prepareStatement(sqlDetalle);

            for (int i = 0; i < modelo.getRowCount(); i++) {
                String producto = modelo.getValueAt(i, 1).toString();
                int productoId = Integer.parseInt(producto.split(" - ")[0]);
                int cantidad = Integer.parseInt(modelo.getValueAt(i, 2).toString());
                double precio = Double.parseDouble(modelo.getValueAt(i, 3).toString());

                psDetalle.setInt(1, facturaId);
                psDetalle.setInt(2, productoId);
                psDetalle.setInt(3, cantidad);
                psDetalle.setDouble(4, precio);
                psDetalle.addBatch();
            }

            psDetalle.executeBatch();
            conn.commit();

            JOptionPane.showMessageDialog(this, "Factura guardada correctamente con ID: " + facturaId);
            modelo.setRowCount(0);
            txtCantidad.setText("");
            actualizarTotal();

        } catch (SQLException e) {
            try { if (conn != null) conn.rollback(); } catch (SQLException ex) {}
            JOptionPane.showMessageDialog(this, "Error al guardar factura: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (psFactura != null) psFactura.close();
                if (psDetalle != null) psDetalle.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {}
        }
    }

    private void consultarPedidos() {
        String clienteSeleccionado = (String) cmbCliente.getSelectedItem();
        if (clienteSeleccionado == null) {
            JOptionPane.showMessageDialog(this, "Seleccione un cliente para consultar.");
            return;
        }

        int clienteId = Integer.parseInt(clienteSeleccionado.split(" - ")[0]);
        modelo.setRowCount(0); // limpiar tabla

        String sql = "SELECT f.factura_id, f.fecha, p.nombre_producto, df.cantidad, df.precio_unitario, " +
                     "(df.cantidad * df.precio_unitario) AS subtotal " +
                     "FROM facturas f " +
                     "JOIN detalle_factura df ON f.factura_id = df.factura_id " +
                     "JOIN productos p ON df.producto_id = p.producto_id " +
                     "WHERE f.cliente_id = ? " +
                     "ORDER BY f.fecha ASC, f.factura_id ASC";

        try (Connection conn = new CreateConection().getConection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, clienteId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String facturaFecha = "ID " + rs.getInt("factura_id") + " / " + rs.getTimestamp("fecha");
                String producto = rs.getString("nombre_producto");
                int cantidad = rs.getInt("cantidad");
                double precio = rs.getDouble("precio_unitario");
                double subtotal = rs.getDouble("subtotal");

                modelo.addRow(new Object[]{facturaFecha, producto, cantidad, precio, subtotal});
            }

            actualizarTotal();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al consultar pedidos: " + e.getMessage());
        }
    }

    // ------------------ Componentes ------------------
    @SuppressWarnings("unchecked")
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jLabelClientes = new javax.swing.JLabel();
        cmbCliente = new javax.swing.JComboBox<>();
        jLabelProductos = new javax.swing.JLabel();
        cmbProducto = new javax.swing.JComboBox<>();
        jLabelCantidad = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JTextField();
        lblTotal = new javax.swing.JLabel();
        lblIVA = new javax.swing.JLabel();
        tblProductos = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();

        btnAgregar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnImprimir = new javax.swing.JButton();

        // Configuración interna del JInternalFrame
        setTitle("Pedido/Factura");
        setClosable(true);
        setMaximizable(true);
        setIconifiable(true);
        setResizable(true);

        jTextField1.setEditable(false);
        jTextField1.setFont(new java.awt.Font("Segoe UI Black", 1, 48));
        jTextField1.setText("Gastronomix");

        jLabelClientes.setText("CLIENTES");
        jLabelProductos.setText("PRODUCTOS");
        jLabelCantidad.setText("CANTIDAD");

        lblIVA.setText("IVA: Q0.00");

        jScrollPane1.setViewportView(tblProductos);

        btnAgregar.setText("Agregar Producto");
        btnAgregar.addActionListener(evt -> agregarProducto());

        btnEliminar.setText("Eliminar Producto");
        btnEliminar.addActionListener(evt -> eliminarProducto());

        btnGuardar.setText("Guardar Factura");
        btnGuardar.addActionListener(evt -> guardarFactura());

        btnImprimir.setText("Consultar Pedidos");
        btnImprimir.addActionListener(evt -> consultarPedidos());

        // Layout
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(20)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jTextField1)
                        .addComponent(jLabelClientes)
                        .addComponent(cmbCliente)
                        .addComponent(jLabelProductos)
                        .addComponent(cmbProducto)
                        .addComponent(jLabelCantidad)
                        .addComponent(txtCantidad)
                        .addComponent(jScrollPane1)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(btnAgregar)
                            .addGap(10)
                            .addComponent(btnEliminar)
                            .addGap(10)
                            .addComponent(btnGuardar)
                            .addGap(10)
                            .addComponent(btnImprimir)
                            .addGap(20)
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(lblIVA)
                                .addComponent(lblTotal))))
                    .addGap(20)
                )
        );

        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(10)
                    .addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                    .addGap(10)
                    .addComponent(jLabelClientes)
                    .addGap(5)
                    .addComponent(cmbCliente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(10)
                    .addComponent(jLabelProductos)
                    .addGap(5)
                    .addComponent(cmbProducto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(10)
                    .addComponent(jLabelCantidad)
                    .addGap(5)
                    .addComponent(txtCantidad, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(10)
                    .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                    .addGap(10)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAgregar)
                        .addComponent(btnEliminar)
                        .addComponent(btnGuardar)
                        .addComponent(btnImprimir)
                        .addComponent(lblTotal))
                    .addGap(5)
                    .addComponent(lblIVA)
                    .addGap(20)
                )
        );

        pack();
    }

    // ------------------ Variables ------------------
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnImprimir;
    private javax.swing.JComboBox<String> cmbCliente;
    private javax.swing.JComboBox<String> cmbProducto;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JLabel lblIVA;
    private javax.swing.JLabel jLabelClientes;
    private javax.swing.JLabel jLabelProductos;
    private javax.swing.JLabel jLabelCantidad;
    private javax.swing.JTable tblProductos;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtCantidad;
}
