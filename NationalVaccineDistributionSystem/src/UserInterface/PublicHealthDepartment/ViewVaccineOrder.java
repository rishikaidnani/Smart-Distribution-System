/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInterface.PublicHealthDepartment;

import Business.Enterprise.PHDEnterprise;
import Business.Enterprise.SiteEnterprise;
import Business.Order.Order;
import Business.Order.OrderItem;
import Business.Organization.Organization;
import Business.Organization.PHD.PhdOperationsOrganization;
import Business.Organization.Sites.ProviderOrganization;
import Business.UserAccount.UserAccount;
import Business.WorkRequest.ApprovalRequestToCdc;
import Business.WorkRequest.ApprovalRequestToPhd;
import Business.WorkRequest.WorkRequest;
import java.awt.CardLayout;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Rishika Idnani
 */
public class ViewVaccineOrder extends javax.swing.JPanel {

    /**
     * Creates new form ViewVaccineOrder
     */
    private JPanel userProcessContainer;
    private PHDEnterprise enterprise;
    private PhdOperationsOrganization organization;
    private UserAccount userAccount;

    public ViewVaccineOrder(JPanel userProcessContainer, PHDEnterprise enterprise, PhdOperationsOrganization organization, UserAccount userAccount) {
        initComponents();
        this.userProcessContainer = userProcessContainer;
        this.enterprise = enterprise;
        this.organization = organization;
        this.userAccount = userAccount;
        populateProviderList();
    }

    public void populateProviderList() {
        DefaultListModel model = new DefaultListModel();
        siteJList.removeAll();

        for (SiteEnterprise siteEnterprise : enterprise.getSiteList()) {
            for (Organization organization : siteEnterprise.getOrganizationDirectory().getOrganizationList()) {
                if (organization instanceof ProviderOrganization) {
                    for (WorkRequest workRequest : organization.getWorkQueue().getWorkRequestList()) {
                        if (workRequest instanceof ApprovalRequestToPhd) {
                            ApprovalRequestToPhd request = (ApprovalRequestToPhd) workRequest;
                            Boolean isPresent = false;
                            for (int i = 0; i < siteJList.getModel().getSize(); i++) {
                                if (siteJList.getModel().getElementAt(i) == request.getSiteEnterprise()) {
                                    isPresent = true;
                                    break;
                                }
                            }
                            if (isPresent == false && request.getIsOrderApprovedByPhd().equals("Pending")) {
                                model.addElement(request.getSiteEnterprise());
                            }
                            siteJList.setModel(model);
                        }
                    }
                }
            }
        }
    }

    public void emptyVaccineTable() {
        DefaultTableModel model = (DefaultTableModel) vaccineJTable.getModel();
        model.setRowCount(0);
    }

    public void populateVaccineTable(Order order) {
        DefaultTableModel model = (DefaultTableModel) vaccineJTable.getModel();
        model.setRowCount(0);

        for (OrderItem orderItem : order.getOrderItemList()) {
            Object row[] = new Object[3];
            row[0] = orderItem;
            row[1] = orderItem.getVaccine().getDisease().getDiseaseName();
            row[2] = orderItem.getTotalQuantity();
            model.addRow(row);
        }
    }

    public void populateOrderTable(SiteEnterprise siteEnterpise) {
        DefaultTableModel model = (DefaultTableModel) orderJTable.getModel();
        model.setRowCount(0);

        for (Organization organization : siteEnterpise.getOrganizationDirectory().getOrganizationList()) {
            if (organization instanceof ProviderOrganization) {
                for (WorkRequest workRequest : organization.getWorkQueue().getWorkRequestList()) {
                    if (workRequest instanceof ApprovalRequestToPhd) {
                        ApprovalRequestToPhd request = (ApprovalRequestToPhd) workRequest;
                        if (request.getIsOrderApprovedByPhd().equals("Pending")) {
                            Object row[] = new Object[5];
                            row[0] = siteEnterpise;
                            row[1] = request.getOrder();
                            row[2] = request;
                            row[3] = request.getSender();
                            row[4] = request.getReceiver();
                            model.addRow(row);
                        }
                    }
                }
            }
        }
    }

    public void sendRequestToCdc(SiteEnterprise siteEnterprise, Order order) {
        ApprovalRequestToCdc workRequest = new ApprovalRequestToCdc();
        organization.getWorkQueue().addRequest(workRequest);
        workRequest.setSender(userAccount.getPerson());
        workRequest.setSiteEnterprise(siteEnterprise);
        workRequest.setOrder(order);
        workRequest.setState(enterprise.getStateName());
        workRequest.setMessage("Kindly approve my order");
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
        display1JLabel = new javax.swing.JLabel();
        display1JButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        orderJTable = new javax.swing.JTable();
        siteJLabel = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        siteJList = new javax.swing.JList();
        backJButton = new javax.swing.JButton();
        display2JButton = new javax.swing.JButton();
        approveOrderJButton = new javax.swing.JButton();
        rejectOrderJButton = new javax.swing.JButton();
        assignJButton = new javax.swing.JButton();
        functionJLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        vaccineJTable = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setText("View Vaccine Orders");

        display1JLabel.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        display1JLabel.setText("Display of Orders");

        display1JButton.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        display1JButton.setText(">");
        display1JButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                display1JButtonActionPerformed(evt);
            }
        });

        orderJTable.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        orderJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Site Name", "Order TimeStamp", "Request Description", "Provider Name", "Receiver"
            }
        ));
        orderJTable.setRowHeight(25);
        jScrollPane2.setViewportView(orderJTable);

        siteJLabel.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        siteJLabel.setText("Registered Sites");

        siteJList.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jScrollPane3.setViewportView(siteJList);

        backJButton.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        backJButton.setText("<< Back");
        backJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backJButtonActionPerformed(evt);
            }
        });

        display2JButton.setText("Click here!");
        display2JButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                display2JButtonActionPerformed(evt);
            }
        });

        approveOrderJButton.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        approveOrderJButton.setText("Approve Order");
        approveOrderJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                approveOrderJButtonActionPerformed(evt);
            }
        });

        rejectOrderJButton.setText("Reject Order");
        rejectOrderJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rejectOrderJButtonActionPerformed(evt);
            }
        });

        assignJButton.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        assignJButton.setText("Assign to me!");
        assignJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                assignJButtonActionPerformed(evt);
            }
        });

        functionJLabel.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        functionJLabel.setText("Want to see details of the vaccines in the order?");

        vaccineJTable.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        vaccineJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Vaccine Name", "Disease", "Quantity"
            }
        ));
        vaccineJTable.setRowHeight(25);
        jScrollPane1.setViewportView(vaccineJTable);

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setText("Display of Items in the order:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1270, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(functionJLabel)
                                .addGap(18, 18, 18)
                                .addComponent(display2JButton, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(backJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(0, 862, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(siteJLabel)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(display1JButton)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 997, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(display1JLabel)
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(72, 72, 72)
                                .addComponent(assignJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(rejectOrderJButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(approveOrderJButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addComponent(display1JButton))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(siteJLabel)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(display1JLabel)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(assignJButton)
                            .addComponent(approveOrderJButton))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rejectOrderJButton)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(functionJLabel)
                    .addComponent(display2JButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(27, 27, 27)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(backJButton)
                .addGap(21, 21, 21))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void display1JButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_display1JButtonActionPerformed
        // TODO add your handling code here:
        int selectedIndex = siteJList.getSelectedIndex();

        if (selectedIndex < 0) {
            JOptionPane.showMessageDialog(null, "Kindly select an order");
            return;
        }

        SiteEnterprise siteEnterprise = (SiteEnterprise) siteJList.getSelectedValue();

        populateOrderTable(siteEnterprise);
    }//GEN-LAST:event_display1JButtonActionPerformed

    private void backJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backJButtonActionPerformed
        // TODO add your handling code here:
        userProcessContainer.remove(this);
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.previous(userProcessContainer);
    }//GEN-LAST:event_backJButtonActionPerformed

    private void display2JButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_display2JButtonActionPerformed
        // TODO add your handling code here:
        int selectedRow = orderJTable.getSelectedRow();

        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(null, "Kindly select an order");
            return;
        }

        Order order = (Order) orderJTable.getValueAt(selectedRow, 1);
        populateVaccineTable(order);
    }//GEN-LAST:event_display2JButtonActionPerformed

    private void approveOrderJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_approveOrderJButtonActionPerformed
        // TODO add your handling code here:
        int selectedRow = orderJTable.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(null, "Kindly select an order");
            return;
        }
        SiteEnterprise siteEnterprise = (SiteEnterprise) orderJTable.getValueAt(selectedRow, 0);
        Order order = (Order) orderJTable.getValueAt(selectedRow, 1);
        ApprovalRequestToPhd request = (ApprovalRequestToPhd) orderJTable.getValueAt(selectedRow, 2);
        if (request.getReceiver() == null) {
            JOptionPane.showMessageDialog(null, "First assign the request to youself");
            return;
        } else if (request.getReceiver() != userAccount.getPerson()) {
            JOptionPane.showMessageDialog(null, "Request is assigned to someone else");
            return;
        }

        request.setIsOrderApprovedByPhd("Approved");
        sendRequestToCdc(siteEnterprise, order);
        populateOrderTable(siteEnterprise);
        emptyVaccineTable();
    }//GEN-LAST:event_approveOrderJButtonActionPerformed

    private void rejectOrderJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rejectOrderJButtonActionPerformed
        // TODO add your handling code here:
        int selectedRow = orderJTable.getSelectedRow();

        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(null, "Kindly select an order");
            return;
        }
        SiteEnterprise siteEnterprise = (SiteEnterprise) orderJTable.getValueAt(selectedRow, 0);
        ApprovalRequestToPhd request = (ApprovalRequestToPhd) orderJTable.getValueAt(selectedRow, 2);
        if (request.getReceiver() == null) {
            JOptionPane.showMessageDialog(null, "First assign the request to youself");
            return;
        } else if (request.getReceiver() != userAccount.getPerson()) {
            JOptionPane.showMessageDialog(null, "Request is assigned to someone else");
            return;
        }
        request.setIsOrderApprovedByPhd("Rejected");
        populateOrderTable(siteEnterprise);
        emptyVaccineTable();
    }//GEN-LAST:event_rejectOrderJButtonActionPerformed

    private void assignJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_assignJButtonActionPerformed
        // TODO add your handling code here:
        int selectedIndex = siteJList.getSelectedIndex();

        if (selectedIndex < 0) {
            JOptionPane.showMessageDialog(null, "Kindly select an order");
            return;
        }

        SiteEnterprise siteEnterprise = (SiteEnterprise) siteJList.getSelectedValue();

        int selectedRow = orderJTable.getSelectedRow();

        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(null, "Kindly select an order");
            return;
        }

        ApprovalRequestToPhd request = (ApprovalRequestToPhd) orderJTable.getValueAt(selectedRow, 2);
        if (request.getReceiver() == null) {
            request.setReceiver(userAccount.getPerson());
        } else {
            JOptionPane.showMessageDialog(null, "Request is already assigned");
            return;
        }
        populateOrderTable(siteEnterprise);
    }//GEN-LAST:event_assignJButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton approveOrderJButton;
    private javax.swing.JButton assignJButton;
    private javax.swing.JButton backJButton;
    private javax.swing.JButton display1JButton;
    private javax.swing.JLabel display1JLabel;
    private javax.swing.JButton display2JButton;
    private javax.swing.JLabel functionJLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable orderJTable;
    private javax.swing.JButton rejectOrderJButton;
    private javax.swing.JLabel siteJLabel;
    private javax.swing.JList siteJList;
    private javax.swing.JTable vaccineJTable;
    // End of variables declaration//GEN-END:variables
}
