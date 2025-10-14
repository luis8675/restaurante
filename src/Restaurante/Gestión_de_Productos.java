/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Restaurante;

import conexion.CreateConection;
import java.sql.*;
import javax.swing.*;




public class Gestión_de_Productos extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Gestión_de_Productos.class.getName());
    private PreparedStatement ps;
    private ResultSet rs;

    /**
     * Creates new form Gestión_de_Productos
     */
    public Gestión_de_Productos() {
        initComponents();
        
        cmbDisponibilidad.addItem("Disponible");
        cmbDisponibilidad.addItem("No disponible");

        
        setLocationRelativeTo(null);
        cargarCategorias();
    }

    
private boolean validarCampos() {
    if (txtNombre_producto.getText().isEmpty() || txtPrecio.getText().isEmpty() || 
        txtStock.getText().isEmpty() || cmbCategoria.getSelectedItem() == null || 
        cmbDisponibilidad.getSelectedItem() == null) {
        JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.");
        return false;
    }
    try {
        Double.valueOf(txtPrecio.getText());
        Integer.valueOf(txtStock.getText());
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Precio y stock deben ser numéricos.");
        return false;
    }
    return true;
}
        
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblNombre_usuario = new javax.swing.JLabel();
        lblNombre_completo = new javax.swing.JLabel();
        txtNombre_producto = new javax.swing.JTextField();
        lblEstado = new javax.swing.JLabel();
        btnConsultar = new javax.swing.JButton();
        cmbDisponibilidad = new javax.swing.JComboBox<>();
        lblClave = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        btnActualizar = new javax.swing.JButton();
        lblID = new javax.swing.JLabel();
        btnEliminar = new javax.swing.JButton();
        txtProducto_ID = new javax.swing.JTextField();
        btnInsertar = new javax.swing.JButton();
        lblClave1 = new javax.swing.JLabel();
        txtStock = new javax.swing.JTextField();
        cmbCategoria = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblNombre_usuario.setText("Nombre producto");

        lblNombre_completo.setText("Categoria ID");

        lblEstado.setText("Disponibilidad");

        btnConsultar.setText("Consultar");
        btnConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarActionPerformed(evt);
            }
        });

        cmbDisponibilidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbDisponibilidadActionPerformed(evt);
            }
        });

        lblClave.setText("Precio");

        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        jTextArea1.setRows(5);
        jTextArea1.setText("GESTION DE  PRODUCTOS");
        jTextArea1.setMargin(new java.awt.Insets(1, 100, 1, 1));
        jScrollPane1.setViewportView(jTextArea1);

        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        lblID.setText("Producto ID");

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        txtProducto_ID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtProducto_IDActionPerformed(evt);
            }
        });

        btnInsertar.setText("Insertar");
        btnInsertar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInsertarActionPerformed(evt);
            }
        });

        lblClave1.setText("Stock");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblClave1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblNombre_usuario)
                                    .addComponent(lblID, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtProducto_ID, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNombre_producto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblClave, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblEstado)
                                    .addComponent(lblNombre_completo))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cmbCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(165, 165, 165)
                                        .addComponent(cmbDisponibilidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 48, Short.MAX_VALUE)))))
                        .addGap(155, 155, 155))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnInsertar)
                        .addGap(29, 29, 29)
                        .addComponent(btnConsultar)
                        .addGap(38, 38, 38)
                        .addComponent(btnEliminar)
                        .addGap(26, 26, 26)
                        .addComponent(btnActualizar)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtProducto_ID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblID))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombre_producto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNombre_usuario))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNombre_completo)
                    .addComponent(cmbCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblClave))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblClave1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbDisponibilidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnConsultar)
                    .addComponent(btnInsertar)
                    .addComponent(btnEliminar)
                    .addComponent(btnActualizar))
                .addGap(18, 18, 18))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
        private void cargarCategorias() {
        try (Connection conn = new CreateConection().getConection()) {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM categorias ORDER BY nombre_categoria");
            ResultSet rs = ps.executeQuery();
            cmbCategoria.removeAllItems();
            while (rs.next()) {
                cmbCategoria.addItem(rs.getInt("categoria_id") + " - " + rs.getString("nombre_categoria"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar categorías: " + ex.getMessage());
        }
    }
    
    
    private void btnConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarActionPerformed

    if (txtProducto_ID.getText().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Ingrese el ID del producto a consultar.");
        return;
    }

    try (Connection conn = new CreateConection().getConection()) {
        String sql = "SELECT p.nombre_producto, p.precio, p.stock, p.disponibilidad, " +
                     "c.categoria_id, c.nombre_categoria " +
                     "FROM productos p " +
                     "JOIN categorias c ON p.categoria_id = c.categoria_id " +
                     "WHERE p.producto_id = ?";

        ps = conn.prepareStatement(sql);
        ps.setInt(1, Integer.parseInt(txtProducto_ID.getText()));
        rs = ps.executeQuery();

        if (rs.next()) {
            txtNombre_producto.setText(rs.getString("nombre_producto"));
            txtPrecio.setText(String.valueOf(rs.getDouble("precio")));
            txtStock.setText(String.valueOf(rs.getInt("stock")));

            // Mostrar disponibilidad
            cmbDisponibilidad.setSelectedItem(rs.getBoolean("disponibilidad") ? "Disponible" : "No disponible");

            // Seleccionar categoría en el combo
            String categoriaTexto = rs.getInt("categoria_id") + " - " + rs.getString("nombre_categoria");
            cmbCategoria.setSelectedItem(categoriaTexto);
        } else {
            JOptionPane.showMessageDialog(this, "No se encontró un producto con ese ID.");
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error al consultar producto: " + e.getMessage());
    }
    }//GEN-LAST:event_btnConsultarActionPerformed

    private void cmbDisponibilidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbDisponibilidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbDisponibilidadActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed

    if (!validarCampos()) return; // Validación de campos vacíos o incorrectos

    try (Connection conn = new CreateConection().getConection()) {

        // Extraer el ID de la categoría seleccionada (por ejemplo "1 - Bebidas" → 1)
        String categoriaSeleccionada = cmbCategoria.getSelectedItem().toString();
        int categoriaId = Integer.parseInt(categoriaSeleccionada.split(" - ")[0]);

        // Preparar la sentencia SQL para actualizar
        String sql = "UPDATE productos SET nombre_producto = ?, categoria_id = ?, precio = ?, disponibilidad = ?, stock = ? WHERE producto_id = ?";
        ps = conn.prepareStatement(sql);

        // Asignar valores
        ps.setString(1, txtNombre_producto.getText());
        ps.setInt(2, categoriaId);
        ps.setDouble(3, Double.parseDouble(txtPrecio.getText()));
        ps.setBoolean(4, cmbDisponibilidad.getSelectedItem().toString().equals("Disponible"));
        ps.setInt(5, Integer.parseInt(txtStock.getText()));
        ps.setInt(6, Integer.parseInt(txtProducto_ID.getText())); // ID del producto a actualizar

        // Ejecutar actualización
        int filasActualizadas = ps.executeUpdate();
        if (filasActualizadas > 0) {
            JOptionPane.showMessageDialog(this, "Producto actualizado correctamente.");
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(this, "No se encontró un producto con ese ID.");
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error al actualizar producto: " + e.getMessage());
    }
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
    
        
        if (txtProducto_ID.getText().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Ingrese el ID del producto a eliminar.");
        return;
    }

    int confirmacion = JOptionPane.showConfirmDialog(this,
            "¿Seguro que desea eliminar este producto?",
            "Confirmar eliminación",
            JOptionPane.YES_NO_OPTION);

    if (confirmacion != JOptionPane.YES_OPTION) return;

    try (Connection conn = new CreateConection().getConection()) {
        String sql = "DELETE FROM productos WHERE producto_id = ?";
        ps = conn.prepareStatement(sql);
        ps.setInt(1, Integer.parseInt(txtProducto_ID.getText()));

        int filasEliminadas = ps.executeUpdate();
        if (filasEliminadas > 0) {
            JOptionPane.showMessageDialog(this, "Producto eliminado correctamente.");
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(this, "No se encontró un producto con ese ID.");
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error al eliminar producto: " + e.getMessage());
    }
    
    
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void txtProducto_IDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProducto_IDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProducto_IDActionPerformed

    private void btnInsertarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsertarActionPerformed

    if (!validarCampos()) return;

    try (Connection conn = new CreateConection().getConection()) {
        String categoriaSeleccionada = cmbCategoria.getSelectedItem().toString();
        int categoriaId = Integer.parseInt(categoriaSeleccionada.split(" - ")[0]);

        String sql = "INSERT INTO productos(nombre_producto, categoria_id, precio, disponibilidad, stock) VALUES (?, ?, ?, ?, ?)";
        ps = conn.prepareStatement(sql);
        ps.setString(1, txtNombre_producto.getText());
        ps.setInt(2, categoriaId);
        ps.setDouble(3, Double.parseDouble(txtPrecio.getText()));
        ps.setBoolean(4, cmbDisponibilidad.getSelectedItem().toString().equals("Disponible"));
        ps.setInt(5, Integer.parseInt(txtStock.getText()));

        ps.executeUpdate();
        JOptionPane.showMessageDialog(this, "Producto registrado correctamente.");
        limpiarCampos();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error al insertar producto: " + e.getMessage());
    }
    }//GEN-LAST:event_btnInsertarActionPerformed

private void limpiarCampos() {
    txtProducto_ID.setText("");
    txtNombre_producto.setText("");
    txtPrecio.setText("");
    txtStock.setText("");
    cmbCategoria.setSelectedIndex(-1);
    cmbDisponibilidad.setSelectedIndex(-1);
}


    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new Gestión_de_Productos().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnConsultar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnInsertar;
    private javax.swing.JComboBox<String> cmbCargo;
    private javax.swing.JComboBox<String> cmbCargo1;
    private javax.swing.JComboBox<String> cmbCategoria;
    private javax.swing.JComboBox<String> cmbDisponibilidad;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel lblClave;
    private javax.swing.JLabel lblClave1;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblID;
    private javax.swing.JLabel lblNombre_completo;
    private javax.swing.JLabel lblNombre_usuario;
    private javax.swing.JTextField txtNombre_producto;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JTextField txtProducto_ID;
    private javax.swing.JTextField txtStock;
    // End of variables declaration//GEN-END:variables
}
