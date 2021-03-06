/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInterface.PublicHealthDepartment;

import Business.Enterprise.Enterprise;
import Business.Enterprise.PHDEnterprise;
import Business.Enterprise.SiteEnterprise;
import Business.Network.Network;
import java.awt.CardLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Rishika Idnani
 */
public class PublicHealthDepartmentWorkArea extends javax.swing.JPanel {

    /**
     * Creates new form PublicHealthDepartmentWorkArea
     */
    private JPanel userProcessContainer;
    private PHDEnterprise enterprise;
    private String stateName;

    public PublicHealthDepartmentWorkArea(JPanel userProcessContainer, PHDEnterprise enterprise) {
        initComponents();
        this.userProcessContainer = userProcessContainer;
        this.enterprise = enterprise;
        this.stateName = enterprise.getStateName();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        manageAccountJButton = new javax.swing.JButton();
        addSitesJButton = new javax.swing.JButton();
        manageOrganizationJButton = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setText("Public Health Department Work Area");

        manageAccountJButton.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        manageAccountJButton.setText("Manage User Accounts >>");
        manageAccountJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manageAccountJButtonActionPerformed(evt);
            }
        });

        addSitesJButton.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        addSitesJButton.setText("Add Sites >>");
        addSitesJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addSitesJButtonActionPerformed(evt);
            }
        });

        manageOrganizationJButton.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        manageOrganizationJButton.setText("Manage Organization >>");
        manageOrganizationJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manageOrganizationJButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(manageOrganizationJButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(addSitesJButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(manageAccountJButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)))
                .addContainerGap(437, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(38, 38, 38)
                .addComponent(addSitesJButton)
                .addGap(18, 18, 18)
                .addComponent(manageOrganizationJButton)
                .addGap(18, 18, 18)
                .addComponent(manageAccountJButton)
                .addContainerGap(64, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void manageAccountJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manageAccountJButtonActionPerformed
        // TODO add your handling code here:
        ManageUserAccount panel = new ManageUserAccount(userProcessContainer, enterprise);

        userProcessContainer.add("ManageProviderAccount", panel);
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.next(userProcessContainer);
    }//GEN-LAST:event_manageAccountJButtonActionPerformed

    private void addSitesJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addSitesJButtonActionPerformed
        // TODO add your handling code here:
        ManageSites panel = new ManageSites(userProcessContainer, enterprise);

        userProcessContainer.add("ManageSites", panel);
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.next(userProcessContainer);
    }//GEN-LAST:event_addSitesJButtonActionPerformed

    private void manageOrganizationJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manageOrganizationJButtonActionPerformed
        // TODO add your handling code here:
        ManagePhdOrganizations panel = new ManagePhdOrganizations(userProcessContainer, enterprise);

        userProcessContainer.add("ManagePhdOrganization", panel);
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.next(userProcessContainer);
    }//GEN-LAST:event_manageOrganizationJButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addSitesJButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton manageAccountJButton;
    private javax.swing.JButton manageOrganizationJButton;
    // End of variables declaration//GEN-END:variables
}
