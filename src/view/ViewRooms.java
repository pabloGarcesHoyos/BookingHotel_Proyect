/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import com.toedter.calendar.JDateChooser;
import controller.RoomController;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Room;

public class ViewRooms extends javax.swing.JFrame {

    RoomController roomController;
    VistaHotelesRegistrados vistaHotelesRegistrados;

    public ViewRooms() throws SQLException {
        initComponents();
        setLocationRelativeTo(this);
        setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);

        roomController = new RoomController();

        // Agregar las columnas a la tabla
        DefaultTableModel model = (DefaultTableModel) tblHabitaciones.getModel();
        model.addColumn("id");
        model.addColumn("id hotel");
        model.addColumn("fecha de entrada");
        model.addColumn("fecha de salida");

        // Agregar evento para detectar clics en la tabla
        tblHabitaciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
    }

    public void cargarHabitacionesEnTabla() {
        DefaultTableModel model = (DefaultTableModel) tblHabitaciones.getModel();
        model.setRowCount(0); // Limpiar la tabla antes de cargar los datos

        try {
            List<Room> habitaciones = roomController.getAllRooms();
            for (Room habitacion : habitaciones) {
                model.addRow(new Object[]{habitacion.getId(), habitacion.getHotelId(), "", ""}); // Agregar filas vacías para las fechas de entrada y salida
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar las habitaciones: " + ex.getMessage());
        }
    }

    public void mostrarHabitacionesDisponibles(List<Room> habitaciones, LocalDate fechaEntrada, LocalDate fechaSalida) {
        DefaultTableModel model = (DefaultTableModel) tblHabitaciones.getModel();
        model.setRowCount(0); // Limpiar la tabla antes de cargar los datos

        for (Room habitacion : habitaciones) {
            model.addRow(new Object[]{habitacion.getId(), habitacion.getHotelId(), fechaEntrada, fechaSalida});
        }
    }

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {
        int selectedRow = tblHabitaciones.getSelectedRow();
        if (selectedRow != -1) {
            tblHabitaciones.setRowSelectionInterval(selectedRow, selectedRow);
            int roomId = (int) tblHabitaciones.getValueAt(selectedRow, 0);
            int hotelId = (int) tblHabitaciones.getValueAt(selectedRow, 1);
            LocalDate fechaEntrada = (LocalDate) tblHabitaciones.getValueAt(selectedRow, 2);
            LocalDate fechaSalida = (LocalDate) tblHabitaciones.getValueAt(selectedRow, 3);

            txtId.setText(String.valueOf(roomId));
            txtIdHotel.setText(String.valueOf(hotelId));
            txtFechaEntrada.setText(fechaEntrada.toString());
            txtFechaSalida.setText(fechaSalida.toString());

            try {
                boolean disponibilidad = roomController.verificarDisponibilidad(roomId, fechaEntrada, fechaSalida);
                if (disponibilidad) {
                    btnReservar.setVisible(true);
                } else {
                    btnReservar.setVisible(false);
                    JOptionPane.showMessageDialog(this, "La habitación no está disponible para las fechas seleccionadas.");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error al verificar la disponibilidad de la habitación: " + ex.getMessage());
            }
        }
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jCheckBoxMenuItem1 = new javax.swing.JCheckBoxMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHabitaciones = new javax.swing.JTable();
        btnReservar = new javax.swing.JButton();
        txtId = new javax.swing.JTextField();
        txtIdHotel = new javax.swing.JTextField();
        txtFechaEntrada = new javax.swing.JTextField();
        txtFechaSalida = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jCheckBoxMenuItem2 = new javax.swing.JCheckBoxMenuItem();

        jMenuItem1.setText("jMenuItem1");

        jCheckBoxMenuItem1.setSelected(true);
        jCheckBoxMenuItem1.setText("jCheckBoxMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(51, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 32, Short.MAX_VALUE)
        );

        tblHabitaciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Id ", "Id Hotel", "Fecha de entrada", "Fecha de salida"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblHabitaciones);

        btnReservar.setText("Reservar");
        btnReservar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReservarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 552, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtIdHotel, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                        .addComponent(btnReservar))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFechaEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFechaSalida, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addComponent(btnReservar))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(txtIdHotel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(19, 19, 19)
                        .addComponent(txtFechaEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtFechaSalida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 23, Short.MAX_VALUE))
        );

        jMenu1.setText("Menu");

        jCheckBoxMenuItem2.setSelected(true);
        jCheckBoxMenuItem2.setText("Atras");
        jCheckBoxMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jCheckBoxMenuItem2);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jCheckBoxMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItem2ActionPerformed
        
        try {
            VistaHotelesRegistrados hoteles = new VistaHotelesRegistrados();
            hoteles.setVisible(true);
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(ViewRooms.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jCheckBoxMenuItem2ActionPerformed

    private void btnReservarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReservarActionPerformed
    try {
            int roomId = Integer.parseInt(txtId.getText());
            LocalDate fechaEntrada = LocalDate.parse(txtFechaEntrada.getText());
            LocalDate fechaSalida = LocalDate.parse(txtFechaSalida.getText());

            boolean disponibilidad = roomController.verificarDisponibilidad(roomId, fechaEntrada, fechaSalida);
            if (!disponibilidad) {
                JOptionPane.showMessageDialog(this, "La habitación no está disponible para las fechas seleccionadas.");
                return;
            }

            boolean reservaExitosa = roomController.realizarReserva(roomId, fechaEntrada, fechaSalida);
            if (reservaExitosa) {
                JOptionPane.showMessageDialog(this, "Reserva realizada exitosamente.");
                DefaultTableModel model = (DefaultTableModel) tblHabitaciones.getModel();
                model.removeRow(tblHabitaciones.getSelectedRow());
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo realizar la reserva. Por favor, intente nuevamente.");
            }

        } catch (NumberFormatException | SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al realizar la reserva: " + ex.getMessage());
        
}
    
    }//GEN-LAST:event_btnReservarActionPerformed

    /**
     * @param args the command line arguments
     */
    
    public JTable getTblHabitaciones() {
        return tblHabitaciones;
    }
private void limpiarCampos() {
        txtId.setText("");
        txtIdHotel.setText("");
        txtFechaEntrada.setText("");
        txtFechaSalida.setText("");
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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ViewRooms.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewRooms.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewRooms.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewRooms.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new ViewRooms().setVisible(true);
                } catch (SQLException ex) {
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnReservar;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblHabitaciones;
    private javax.swing.JTextField txtFechaEntrada;
    private javax.swing.JTextField txtFechaSalida;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtIdHotel;
    // End of variables declaration//GEN-END:variables
}
