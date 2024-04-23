/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import controller.RoomController;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Room;

public class ViewRooms extends javax.swing.JFrame {

    RoomController roomController;

    public ViewRooms() throws SQLException {
    initComponents();
    setLocationRelativeTo(this);
    setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);

    roomController = new RoomController();

    DefaultTableModel model = (DefaultTableModel) tblHabitaciones.getModel();
    // Cambiar los nombres de las columnas
    model.setColumnIdentifiers(new Object[]{"ID", "Número de Habitación", "Tipo de Habitación", "Precio por Noche", "Detalles de Amenidades", "fechaEntrada", "fechaSalida"});

    tblHabitaciones.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jTable1MouseClicked(evt);
        }
    });
}

public void cargarHabitacionesEnTabla() {
    DefaultTableModel model = (DefaultTableModel) tblHabitaciones.getModel();
    model.setRowCount(0);

    try {
        List<Room> habitaciones = roomController.getAllRooms();
        for (Room habitacion : habitaciones) {
            model.addRow(new Object[]{habitacion.getId(), habitacion.getRoomNumber(), habitacion.getRoomType(), habitacion.getPricePerNight(), habitacion.getAmenitiesDetails()});
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Error al cargar las habitaciones: " + ex.getMessage());
    }
}

public void mostrarHabitacionesDisponibles(List<Room> habitaciones, LocalDate fechaEntrada, LocalDate fechaSalida) {
    DefaultTableModel model = (DefaultTableModel) tblHabitaciones.getModel();
    model.setRowCount(0);

    for (Room habitacion : habitaciones) {
        model.addRow(new Object[]{habitacion.getId(), habitacion.getRoomNumber(), habitacion.getRoomType(), habitacion.getPricePerNight(), habitacion.getAmenitiesDetails()});
    }
}

private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {
    int selectedRow = tblHabitaciones.getSelectedRow();
    if (selectedRow != -1) {
        int roomId = (int) tblHabitaciones.getValueAt(selectedRow, 0);
        int roomNumber = (int) tblHabitaciones.getValueAt(selectedRow, 1);
        String roomType = (String) tblHabitaciones.getValueAt(selectedRow, 2);
        double pricePerNight = (double) tblHabitaciones.getValueAt(selectedRow, 3);
        String amenitiesDetails = (String) tblHabitaciones.getValueAt(selectedRow, 4);
        LocalDate fechaEntrada = LocalDate.parse(tblHabitaciones.getValueAt(selectedRow, 5).toString());
        LocalDate fechaSalida = LocalDate.parse(tblHabitaciones.getValueAt(selectedRow, 6).toString());

        try {
            boolean disponibilidad = roomController.verificarDisponibilidad(roomId, fechaEntrada, fechaSalida);
            if (disponibilidad) {
                txtId.setText(String.valueOf(roomId));
                txtRoomNumber.setText(String.valueOf(roomNumber));
                txtRoomType.setText(roomType);
                txtPricePerNight.setText(String.valueOf(pricePerNight));
                txtAmenitiesDetails.setText(amenitiesDetails);
                txtFechaEntrada.setText(fechaEntrada.toString());
                txtFechaSalida.setText(fechaSalida.toString());
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
        txtRoomNumber = new javax.swing.JTextField();
        txtRoomType = new javax.swing.JTextField();
        txtFechaEntrada = new javax.swing.JTextField();
        txtFechaSalida = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtPricePerNight = new javax.swing.JTextField();
        txtAmenitiesDetails = new javax.swing.JTextField();
        txtId = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jCheckBoxMenuItem2 = new javax.swing.JCheckBoxMenuItem();

        jMenuItem1.setText("jMenuItem1");

        jCheckBoxMenuItem1.setSelected(true);
        jCheckBoxMenuItem1.setText("jCheckBoxMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));

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

        tblHabitaciones.setBackground(new java.awt.Color(204, 204, 204));
        tblHabitaciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Id ", "Id Hotel", "Fecha de entrada", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblHabitaciones);

        btnReservar.setBackground(new java.awt.Color(204, 204, 204));
        btnReservar.setFont(new java.awt.Font("Trebuchet MS", 1, 10)); // NOI18N
        btnReservar.setText("Reservar");
        btnReservar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReservarActionPerformed(evt);
            }
        });

        txtRoomNumber.setEditable(false);

        txtRoomType.setEditable(false);

        txtFechaEntrada.setEditable(false);

        txtFechaSalida.setEditable(false);

        jLabel1.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel1.setText("ID");

        jLabel2.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel2.setText("ID Hotel");

        jLabel3.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel3.setText("Fecha de entrada");

        jLabel4.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel4.setText("Fecha de salida");

        txtPricePerNight.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPricePerNightActionPerformed(evt);
            }
        });

        txtId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 533, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(btnReservar)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(txtRoomNumber)
                            .addComponent(txtFechaEntrada)
                            .addComponent(jLabel4)
                            .addComponent(txtFechaSalida)
                            .addComponent(txtRoomType))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtPricePerNight, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtAmenitiesDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(37, 37, 37))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtRoomNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtPricePerNight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtRoomType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(txtAmenitiesDetails, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFechaEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFechaSalida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnReservar))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 23, Short.MAX_VALUE))
        );

        jMenuBar1.setBackground(new java.awt.Color(204, 204, 204));

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
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
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
    int selectedRow = tblHabitaciones.getSelectedRow();
    if (selectedRow != -1) {
        int roomId = (int) tblHabitaciones.getValueAt(selectedRow, 0);
        LocalDate fechaEntrada = LocalDate.parse(tblHabitaciones.getValueAt(selectedRow, 5).toString());
        LocalDate fechaSalida = LocalDate.parse(tblHabitaciones.getValueAt(selectedRow, 6).toString());

        try {
            boolean disponibilidad = roomController.verificarDisponibilidad(roomId, fechaEntrada, fechaSalida);
            if (disponibilidad) {
                txtId.setText(String.valueOf(roomId));
                txtRoomNumber.setText(String.valueOf(tblHabitaciones.getValueAt(selectedRow, 1)));
                txtRoomType.setText(String.valueOf(tblHabitaciones.getValueAt(selectedRow, 2)));
                txtPricePerNight.setText(String.valueOf(tblHabitaciones.getValueAt(selectedRow, 3)));
                txtAmenitiesDetails.setText(String.valueOf(tblHabitaciones.getValueAt(selectedRow, 4)));
                txtFechaEntrada.setText(fechaEntrada.toString());
                txtFechaSalida.setText(fechaSalida.toString());

                // Aquí realizamos la reserva
                roomController.reservarHabitacion(roomId, fechaEntrada, fechaSalida);

                JOptionPane.showMessageDialog(this, "Reserva realizada con éxito.");

                // Ocultamos el botón después de reservar
                btnReservar.setVisible(false);
            } else {
                btnReservar.setVisible(false);
                JOptionPane.showMessageDialog(this, "La habitación no está disponible para las fechas seleccionadas.");
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al verificar la disponibilidad de la habitación: " + ex.getMessage());
        }
    }



    }//GEN-LAST:event_btnReservarActionPerformed

    private void txtPricePerNightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPricePerNightActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPricePerNightActionPerformed

    private void txtIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdActionPerformed
    public JTable getTblHabitaciones() {
        return tblHabitaciones;
    }
private void limpiarCampos() {
        txtRoomNumber.setText("");
        txtRoomType.setText("");
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblHabitaciones;
    private javax.swing.JTextField txtAmenitiesDetails;
    private javax.swing.JTextField txtFechaEntrada;
    private javax.swing.JTextField txtFechaSalida;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtPricePerNight;
    private javax.swing.JTextField txtRoomNumber;
    private javax.swing.JTextField txtRoomType;
    // End of variables declaration//GEN-END:variables
}
