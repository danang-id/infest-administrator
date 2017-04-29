/*
 * Copyright 2017 adaMada.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jogjadamai.infest.credentials;

import java.awt.Color;

/**
 *
 * @author adaMada
 */
public class AdministratorCredential extends javax.swing.JFrame {

    /**
     * Creates new form AdministratorCredential
     */
    public AdministratorCredential() {
        initComponents();
    }
    
    protected void verifyPassword(){
        String newPassword = "";
        for (char character : adminConfirmPasswordField.getPassword()) {
            newPassword = newPassword + character;
        }

        String verificationPassword = "";
        for (char character : adminNewPasswordField.getPassword()) {
            verificationPassword = verificationPassword + character;
        }

        if (newPassword.equals(verificationPassword)) {
            verifiedPasswordLabel.setText("Passwords match");
            verifiedPasswordLabel.setForeground(Color.GREEN);
        } else {
            verifiedPasswordLabel.setText("Passwords don't match");
            verifiedPasswordLabel.setForeground(Color.RED);
        }
//        return true;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        adminCurrentPasswordLabel = new javax.swing.JLabel();
        adminCurrentPasswordField = new javax.swing.JPasswordField();
        jSeparator1 = new javax.swing.JSeparator();
        adminNewPasswordField = new javax.swing.JPasswordField();
        adminNewPasswordLabel = new javax.swing.JLabel();
        adminConfirmPasswordLabel = new javax.swing.JLabel();
        adminConfirmPasswordField = new javax.swing.JPasswordField();
        adminChangePasswordButton = new javax.swing.JButton();
        adminCancelButton = new javax.swing.JButton();
        verifiedPasswordLabel = new javax.swing.JLabel();

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Administrator Credentials");
        setAlwaysOnTop(true);
        setResizable(false);
        setType(java.awt.Window.Type.UTILITY);

        adminCurrentPasswordLabel.setText("Current Password");
        adminCurrentPasswordLabel.setName("adminCurrentPasswordLabel"); // NOI18N

        adminCurrentPasswordField.setName("adminCurrentPasswordField"); // NOI18N
        adminCurrentPasswordField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adminCurrentPasswordFieldActionPerformed(evt);
            }
        });

        adminNewPasswordField.setName("adminNewPasswordField"); // NOI18N
        adminNewPasswordField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                newPasswordKeyReleased(evt);
            }
        });

        adminNewPasswordLabel.setText("New Password");
        adminNewPasswordLabel.setName("adminNewPasswordLabel"); // NOI18N

        adminConfirmPasswordLabel.setText("Confirm Password");
        adminConfirmPasswordLabel.setName("adminConfirmPasswordLabel"); // NOI18N

        adminConfirmPasswordField.setName("adminConfirmPasswordField"); // NOI18N
        adminConfirmPasswordField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                adminVerificationKeyReleased(evt);
            }
        });

        adminChangePasswordButton.setText("Change Password");
        adminChangePasswordButton.setName("adminChangePasswordButton"); // NOI18N
        adminChangePasswordButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adminChangePasswordButtonActionPerformed(evt);
            }
        });

        adminCancelButton.setText("Cancel");
        adminCancelButton.setName("adminCancelButton"); // NOI18N
        adminCancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adminCancelButtonActionPerformed(evt);
            }
        });

        verifiedPasswordLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        verifiedPasswordLabel.setText(" ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(adminCurrentPasswordLabel)
                        .addGap(48, 48, 48)
                        .addComponent(adminCurrentPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(adminConfirmPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(adminConfirmPasswordLabel)
                                    .addComponent(adminCancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(adminNewPasswordLabel))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(48, 48, 48)
                                        .addComponent(adminNewPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(adminChangePasswordButton))))
                            .addComponent(verifiedPasswordLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(73, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(adminCurrentPasswordLabel)
                    .addComponent(adminCurrentPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(adminNewPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(adminNewPasswordLabel))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(adminConfirmPasswordLabel)
                    .addComponent(adminConfirmPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(verifiedPasswordLabel)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(adminChangePasswordButton)
                    .addComponent(adminCancelButton))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        adminCurrentPasswordField.getAccessibleContext().setAccessibleName("adminCurrentPasswordField");
        adminNewPasswordField.getAccessibleContext().setAccessibleName("adminNewPasswordField");
        adminConfirmPasswordField.getAccessibleContext().setAccessibleName("adminVerificationPasswordField");
        adminChangePasswordButton.getAccessibleContext().setAccessibleName("changePasswordAdminButton");
        adminCancelButton.getAccessibleContext().setAccessibleName("cancelChangePasswordAdmin");
        verifiedPasswordLabel.getAccessibleContext().setAccessibleName("verifiedPasswordLabel");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void adminCurrentPasswordFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adminCurrentPasswordFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_adminCurrentPasswordFieldActionPerformed

    private void adminVerificationKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_adminVerificationKeyReleased
        verifyPassword();
    }//GEN-LAST:event_adminVerificationKeyReleased

    private void adminChangePasswordButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adminChangePasswordButtonActionPerformed
        
    }//GEN-LAST:event_adminChangePasswordButtonActionPerformed

    private void adminCancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adminCancelButtonActionPerformed
        
    }//GEN-LAST:event_adminCancelButtonActionPerformed

    private void newPasswordKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_newPasswordKeyReleased
        if(adminConfirmPasswordField.getPassword().length == 0){
            verifiedPasswordLabel.setText(" ");
        }else {
            verifyPassword();
        }
        
    }//GEN-LAST:event_newPasswordKeyReleased

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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AdministratorCredential.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdministratorCredential.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdministratorCredential.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdministratorCredential.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdministratorCredential().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected javax.swing.JButton adminCancelButton;
    protected javax.swing.JButton adminChangePasswordButton;
    protected javax.swing.JPasswordField adminConfirmPasswordField;
    private javax.swing.JLabel adminConfirmPasswordLabel;
    protected javax.swing.JPasswordField adminCurrentPasswordField;
    private javax.swing.JLabel adminCurrentPasswordLabel;
    protected javax.swing.JPasswordField adminNewPasswordField;
    private javax.swing.JLabel adminNewPasswordLabel;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel verifiedPasswordLabel;
    // End of variables declaration//GEN-END:variables
}