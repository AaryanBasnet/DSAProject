package org.example.GUI.CaculatorGUI.src.main;


import org.example.GUI.CaculatorGUI.src.main.InfixToPostfix;

import javax.swing.JOptionPane;

public class Application extends javax.swing.JFrame {

    public Application() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        resultField = new javax.swing.JTextField();
        caculateResult = new javax.swing.JButton();
        clearField = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        resultField.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N

        caculateResult.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        caculateResult.setText("Calculate");
        caculateResult.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                caculateResultActionPerformed(evt);
            }
        });

        clearField.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 16)); // NOI18N
        clearField.setText("Clear");
        clearField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearFieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(resultField)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(clearField, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(caculateResult, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(resultField, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(caculateResult, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(clearField, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE))
                .addContainerGap(8, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void caculateResultActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_caculateResultActionPerformed
        String infixExpression = resultField.getText();
        try {
            // Convert infix to postfix
            String postfixExpression = InfixToPostfix.infixToPostfix(infixExpression);
            // Evaluate postfix expression
            int result = InfixToPostfix.evaluatePostfix(postfixExpression);
            // Display result in the resultField
            resultField.setText(String.valueOf(result));
        } catch (Exception e) {
            // Display error message in the resultField
            resultField.setText("Error");
        }
    }//GEN-LAST:event_caculateResultActionPerformed

    private void clearFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearFieldActionPerformed
        resultField.setText("");
    }//GEN-LAST:event_clearFieldActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(() -> {
            new Application().setVisible(true);
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton caculateResult;
    private javax.swing.JButton clearField;
    private javax.swing.JTextField resultField;
    // End of variables declaration//GEN-END:variables
}
