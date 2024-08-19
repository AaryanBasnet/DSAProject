package org.example.GUI.DeliveryRoute.src.gui;

/**
 *
 * @author shahi
 */
public class DestinationItem extends javax.swing.JPanel {
    @SuppressWarnings("unchecked")
    
    public DestinationItem() {
       initComponents();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        priorityLevel = new javax.swing.JButton();
        destinationCity = new javax.swing.JLabel();

        priorityLevel.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        priorityLevel.setText("1");
        priorityLevel.setBorderPainted(false);
        priorityLevel.setFocusPainted(false);
        priorityLevel.setFocusable(false);
        priorityLevel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                priorityLevelActionPerformed(evt);
            }
        });

        destinationCity.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        destinationCity.setText("Nepalgunj");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(priorityLevel, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(destinationCity, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(priorityLevel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                    .addComponent(destinationCity, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void priorityLevelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_priorityLevelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_priorityLevelActionPerformed

    public void setPriorityLevel(String priority) {
        priorityLevel.setText(priority);
    }

    public void setDestinationCity(String city) {
        destinationCity.setText("Destination: " + city);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel destinationCity;
    private javax.swing.JButton priorityLevel;
    // End of variables declaration//GEN-END:variables
}
