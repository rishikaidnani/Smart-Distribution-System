/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInterface.CentreForDiseaseControl;

import Business.Enterprise.CDCEnterprise;
import Business.Enterprise.PHDEnterprise;
import Business.Enterprise.SiteEnterprise;
import Business.Enterprise.VaccineManufacturerEnterprise;
import Business.Order.Order;
import Business.Order.OrderItem;
import Business.Organization.CDC.CDCOperationsOrganization;
import Business.Organization.Organization;
import Business.Organization.PHD.PhdOperationsOrganization;
import Business.UserAccount.UserAccount;
import Business.Vaccine.Vaccine;
import Business.Vaccine.VaccineAllocation;
import Business.WorkRequest.ApprovalRequestToCdc;
import Business.WorkRequest.ManufacturingRequestToManufacturer;
import Business.WorkRequest.ShipmentRequestToNationalDistributor;
import Business.WorkRequest.WorkRequest;
import java.awt.CardLayout;
import java.util.Date;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Rishika Idnani
 */
public class ViewVaccineOrders extends javax.swing.JPanel {

    /**
     * Creates new form ViewVaccineOrders
     */
    private JPanel userProcessContainer;
    private CDCEnterprise enterprise;
    private int totalQuantityOfState;
    private CDCOperationsOrganization organization;
    private UserAccount userAccount;

    public ViewVaccineOrders(JPanel userProcessContainer, CDCEnterprise enterprise, CDCOperationsOrganization organization, UserAccount userAccount) {
        initComponents();
        this.userProcessContainer = userProcessContainer;
        this.enterprise = enterprise;
        this.organization = organization;
        this.userAccount = userAccount;

        populateStateList();
    }

    public int calculateAllocatedVaccine(OrderItem orderItem, String stateName) {
        float allocatedVaccinesToState = 0;

        for (VaccineManufacturerEnterprise enterprise : enterprise.getVaccineManufacturerList()) {
            if (orderItem.getVaccine().getVaccineManufacturer() == enterprise) {
                for (Vaccine vaccineForSale : enterprise.getVaccineInventory().getVaccineInventoryList()) {
                    if (vaccineForSale.getName().equals(orderItem.getVaccine().getName())) {
                        if (vaccineForSale.getBatchAvailability() > 0 && vaccineForSale.getExpiryDate().after(new Date())) {
                            for (VaccineAllocation vaccineAllocation : vaccineForSale.getVaccineAllocationData()) {
                                if (vaccineAllocation.getPhdEnterprise().getStateName().equals(stateName)) {
                                    allocatedVaccinesToState = allocatedVaccinesToState + vaccineAllocation.getAllocatedVaccineQuantity();
                                }
                            }
                        }
                    }
                }
            }
        }
        int allotedToState = (int) Math.floor(allocatedVaccinesToState);
        return allotedToState;// - 1;
    }

    public void populateOrderTable(String stateName) {
        DefaultTableModel model = (DefaultTableModel) orderJTable.getModel();
        model.setRowCount(0);

        for (PHDEnterprise pEnterprise : enterprise.getPhdList()) {
            if (pEnterprise.getStateName().equals(stateName)) {
                for (Organization organization : pEnterprise.getOrganizationDirectory().getOrganizationList()) {
                    if (organization instanceof PhdOperationsOrganization) {
                        for (WorkRequest request : organization.getWorkQueue().getWorkRequestList()) {
                            ApprovalRequestToCdc r = (ApprovalRequestToCdc) request;
                            if (r.getOrderApprovedByCdc().equals("Pending")) {
                                Object row[] = new Object[5];
                                row[0] = r.getSiteEnterprise();
                                row[1] = r.getOrder();
                                row[2] = r;
                                row[3] = r.getSender();
                                row[4] = r.getReceiver();
                                model.addRow(row);
                            }
                        }
                    }
                }
            }
        }
    }

    public void sendManufacturingRequest(Order order) {
        ManufacturingRequestToManufacturer workRequest = new ManufacturingRequestToManufacturer();
        organization.getWorkQueue().addRequest(workRequest);
        workRequest.setSender(userAccount.getPerson());
        workRequest.setOrder(order);
    }

    public void populateStateList() {
        DefaultListModel model = new DefaultListModel();
        stateJList.removeAll();

        for (PHDEnterprise phdEnterprise : enterprise.getPhdList()) {
            for (Organization organization : phdEnterprise.getOrganizationDirectory().getOrganizationList()) {
                if (organization instanceof PhdOperationsOrganization) {
                    for (WorkRequest workRequest : organization.getWorkQueue().getWorkRequestList()) {
                        Boolean isPresent = false;
                        ApprovalRequestToCdc request = (ApprovalRequestToCdc) workRequest;
                        if (request.getOrderApprovedByCdc().equals("Pending")) {
                            for (int i = 0; i < stateJList.getModel().getSize(); i++) {
                                if (stateJList.getModel().getElementAt(i).equals(request.getState())) {
                                    isPresent = true;
                                    break;
                                }
                            }
                            if (isPresent == false) {
                                model.addElement(request.getState());
                            }
                            stateJList.setModel(model);
                        }
                    }
                }
            }
        }
    }

    public void emptyVaccineTable() {
        DefaultTableModel model = (DefaultTableModel) orderItemJTable.getModel();
        model.setRowCount(0);
    }

    public void populateVaccineTable(Order order, String stateName) {
        DefaultTableModel model = (DefaultTableModel) orderItemJTable.getModel();
        model.setRowCount(0);

        for (OrderItem orderItem : order.getOrderItemList()) {
            Object row[] = new Object[6];
            if (orderItem.getIsOrderItemApprovedByCdc().equals("Pending")) {
                row[0] = orderItem;
                row[1] = orderItem.getTotalQuantity();
                row[2] = orderItem.getVaccine().getVaccineManufacturer().getName();
                row[3] = orderItem.getVaccine().getDisease().getDiseaseName();
                row[4] = orderItem.getIsOrderItemApprovedByCdc();
                if (calculateAllocatedVaccine(orderItem, stateName) > 0) {
                    row[5] = calculateAllocatedVaccine(orderItem, stateName);
                } else {
                    row[5] = "The state has consumed its vaccine quota";
                }
                model.addRow(row);
            }
        }
    }

    public void sendShipmentRequest(SiteEnterprise siteEnterprise, Order order) {
        ShipmentRequestToNationalDistributor workRequest = new ShipmentRequestToNationalDistributor();
        organization.getWorkQueue().addRequest(workRequest);
        workRequest.setSiteEnterprise(siteEnterprise);
        workRequest.setOrder(order);
        workRequest.setState(siteEnterprise.getStateName());
        workRequest.setSender(userAccount.getPerson());
        workRequest.setMessage("Kindly ship this");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelDescriptionJLabel = new javax.swing.JLabel();
        stateJLabel = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        stateJList = new javax.swing.JList();
        displayJButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        orderItemJTable = new javax.swing.JTable();
        display1JLabel = new javax.swing.JLabel();
        backJButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        orderJTable = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        display2JButton = new javax.swing.JButton();
        approveJButton = new javax.swing.JButton();
        rejectJButton = new javax.swing.JButton();
        assignJButton = new javax.swing.JButton();
        rejectOrderJButton = new javax.swing.JButton();

        panelDescriptionJLabel.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        panelDescriptionJLabel.setText("View Vaccine Orders");

        stateJLabel.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        stateJLabel.setText("States");

        stateJList.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jScrollPane3.setViewportView(stateJList);

        displayJButton.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        displayJButton.setText(">");
        displayJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                displayJButtonActionPerformed(evt);
            }
        });

        orderItemJTable.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        orderItemJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Vaccine Name", "Vaccine Quantity", "Manufacturer", "Disease", "Status", "Quantity available for the state"
            }
        ));
        orderItemJTable.setRowHeight(22);
        jScrollPane2.setViewportView(orderItemJTable);

        display1JLabel.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        display1JLabel.setText("Want to check the details of vaccines in the order?");

        backJButton.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        backJButton.setText("<< Back");
        backJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backJButtonActionPerformed(evt);
            }
        });

        orderJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Site Name", "Time Stamp", "Request", "Sender", "Receiver"
            }
        ));
        orderJTable.setRowHeight(22);
        jScrollPane1.setViewportView(orderJTable);

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setText("Display of Orders");

        display2JButton.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        display2JButton.setText("Click here!");
        display2JButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                display2JButtonActionPerformed(evt);
            }
        });

        approveJButton.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        approveJButton.setText("Approve Order");
        approveJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                approveJButtonActionPerformed(evt);
            }
        });

        rejectJButton.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        rejectJButton.setText("Reject Items from the order");
        rejectJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rejectJButtonActionPerformed(evt);
            }
        });

        assignJButton.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        assignJButton.setText("Assign to me!");
        assignJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                assignJButtonActionPerformed(evt);
            }
        });

        rejectOrderJButton.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        rejectOrderJButton.setText("Reject Order");
        rejectOrderJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rejectOrderJButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(230, 230, 230)
                        .addComponent(displayJButton)
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 923, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panelDescriptionJLabel)
                        .addGap(0, 1067, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(289, 289, 289)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(display2JButton, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(assignJButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(approveJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(27, 27, 27)
                                        .addComponent(rejectOrderJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(display1JLabel)
                                    .addComponent(rejectJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(backJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(13, 13, 13)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(stateJLabel)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(1017, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelDescriptionJLabel)
                .addGap(16, 16, 16)
                .addComponent(jLabel2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addComponent(displayJButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(assignJButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(rejectOrderJButton)
                    .addComponent(approveJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(display2JButton)
                    .addComponent(display1JLabel))
                .addGap(11, 11, 11)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(rejectJButton)
                .addGap(27, 27, 27)
                .addComponent(backJButton)
                .addContainerGap(20, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(45, 45, 45)
                    .addComponent(stateJLabel)
                    .addGap(18, 18, 18)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(366, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void displayJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_displayJButtonActionPerformed
        // TODO add your handling code here:
        String stateName = String.valueOf(stateJList.getSelectedValue());

        if (stateName.equals("")) {
            JOptionPane.showMessageDialog(null, "Kindly select a state to view Orders!");
            return;
        }

        populateOrderTable(stateName);
    }//GEN-LAST:event_displayJButtonActionPerformed

    private void backJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backJButtonActionPerformed
        // TODO add your handling code here:
        userProcessContainer.remove(this);
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.previous(userProcessContainer);
    }//GEN-LAST:event_backJButtonActionPerformed

    private void display2JButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_display2JButtonActionPerformed
        // TODO add your handling code here:
        String stateName = String.valueOf(stateJList.getSelectedValue());

        if (stateName.equals("")) {
            JOptionPane.showMessageDialog(null, "Kindly select a state to view Orders!");
            return;
        }

        int selectedRow = orderJTable.getSelectedRow();

        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(null, "Kindly select an order");
            return;
        }

        Order order = (Order) orderJTable.getValueAt(selectedRow, 1);
        populateVaccineTable(order, stateName);
    }//GEN-LAST:event_display2JButtonActionPerformed

    private void approveJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_approveJButtonActionPerformed
        // TODO add your handling code here:
        String stateName = String.valueOf(stateJList.getSelectedValue());

        if (stateName.equals("")) {
            JOptionPane.showMessageDialog(null, "Kindly select a state to view Orders!");
            return;
        }

        int selectedRow1 = orderJTable.getSelectedRow();

        if (selectedRow1 < 0) {
            JOptionPane.showMessageDialog(null, "Kindly select an order");
            return;
        }

        ApprovalRequestToCdc r = (ApprovalRequestToCdc) orderJTable.getValueAt(selectedRow1, 2);
        if (r.getReceiver() == null) {
            JOptionPane.showMessageDialog(null, "Kindly assign the request to yourself");
            return;
        }

        Order order = (Order) orderJTable.getValueAt(selectedRow1, 1);
        SiteEnterprise siteEnteprise = (SiteEnterprise) orderJTable.getValueAt(selectedRow1, 0);

        for (OrderItem oi : order.getOrderItemList()) {
            if (oi.getIsOrderItemApprovedByCdc().equals("Pending")) {
                oi.setIsOrderItemApprovedByCdc("Approved");
                Vaccine vaccine = (Vaccine) oi.getVaccine();
                for (VaccineManufacturerEnterprise enterprise : enterprise.getVaccineManufacturerList()) {
                    if (oi.getVaccine().getVaccineManufacturer() == enterprise) {
                        search:
                        {
                            for (Vaccine vaccineForSale : enterprise.getVaccineInventory().getVaccineInventoryList()) {
                                if (vaccineForSale.getName().equals(vaccine.getName())) {
                                    if (vaccineForSale.getBatchAvailability() > 0 && vaccineForSale.getExpiryDate().after(new Date())) {
                                        for (VaccineAllocation vaccineAllocation : vaccineForSale.getVaccineAllocationData()) {
                                            if (vaccineAllocation.getPhdEnterprise().getStateName().equals(stateName)) {
                                                float newAllotedQuantity = vaccineAllocation.getAllocatedVaccineQuantity() - oi.getTotalQuantity();
                                                vaccineAllocation.setAllocatedVaccineQuantity(newAllotedQuantity);
                                                break search;
                                            }
                                        }
                                    }
                                }
                            }
//                            break;
                        }
                    }
                }
            }

            r.setOrderApprovedByCdc("Attended");
            populateOrderTable(stateName);
        }
        sendShipmentRequest(siteEnteprise, order);
        sendManufacturingRequest(order);
        emptyVaccineTable();
    }//GEN-LAST:event_approveJButtonActionPerformed

    private void rejectJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rejectJButtonActionPerformed
        // TODO add your handling code here:
        String stateName = String.valueOf(stateJList.getSelectedValue());

        if (stateName.equals("")) {
            JOptionPane.showMessageDialog(null, "Kindly select a state to view Orders!");
            return;
        }

        int selectedRow1 = orderJTable.getSelectedRow();

        if (selectedRow1 < 0) {
            JOptionPane.showMessageDialog(null, "Kindly select an order");
            return;
        }
        ApprovalRequestToCdc r = (ApprovalRequestToCdc) orderJTable.getValueAt(selectedRow1, 2);
        Order order = (Order) orderJTable.getValueAt(selectedRow1, 1);
        if (r.getReceiver() == null) {
            JOptionPane.showMessageDialog(null, "Kindly assign the request to yourself");
            return;
        }

        int selectedRow = orderItemJTable.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(null, "Kindly select an order item");
            return;
        }

        OrderItem orderItem = (OrderItem) orderItemJTable.getValueAt(selectedRow, 0);
        orderItem.setIsOrderItemApprovedByCdc("Rejected");

        populateVaccineTable(order, stateName);
    }//GEN-LAST:event_rejectJButtonActionPerformed

    private void assignJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_assignJButtonActionPerformed
        // TODO add your handling code here:
        String stateName = String.valueOf(stateJList.getSelectedValue());

        if (stateName.equals("")) {
            JOptionPane.showMessageDialog(null, "Kindly select a state to view Orders!");
            return;
        }

        int selectedRow = orderJTable.getSelectedRow();

        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(null, "Kindly select an order");
            return;
        }

        WorkRequest request = (WorkRequest) orderJTable.getValueAt(selectedRow, 2);
        if (request.getReceiver() == null) {
            request.setReceiver(userAccount.getPerson());
        } else {
            JOptionPane.showMessageDialog(null, "Request is already assigned");
            return;
        }
        populateOrderTable(stateName);
    }//GEN-LAST:event_assignJButtonActionPerformed

    private void rejectOrderJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rejectOrderJButtonActionPerformed
        // TODO add your handling code here:
        int selectedRow1 = orderJTable.getSelectedRow();

        if (selectedRow1 < 0) {
            JOptionPane.showMessageDialog(null, "Kindly select an order");
            return;
        }

        ApprovalRequestToCdc r = (ApprovalRequestToCdc) orderJTable.getValueAt(selectedRow1, 2);

        if (r.getReceiver() == null) {
            JOptionPane.showMessageDialog(null, "Kindly assign the request to yourself");
            return;
        }

        Order order = (Order) orderJTable.getValueAt(selectedRow1, 1);
        SiteEnterprise siteEnteprise = (SiteEnterprise) orderJTable.getValueAt(selectedRow1, 0);

        for (OrderItem oi : order.getOrderItemList()) {
            oi.setIsOrderItemApprovedByCdc("Rejected");
            String stateName = String.valueOf(stateJList.getSelectedValue());
            r.setOrderApprovedByCdc("Attended");
            populateOrderTable(stateName);
        }
        emptyVaccineTable();
    }//GEN-LAST:event_rejectOrderJButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton approveJButton;
    private javax.swing.JButton assignJButton;
    private javax.swing.JButton backJButton;
    private javax.swing.JLabel display1JLabel;
    private javax.swing.JButton display2JButton;
    private javax.swing.JButton displayJButton;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable orderItemJTable;
    private javax.swing.JTable orderJTable;
    private javax.swing.JLabel panelDescriptionJLabel;
    private javax.swing.JButton rejectJButton;
    private javax.swing.JButton rejectOrderJButton;
    private javax.swing.JLabel stateJLabel;
    private javax.swing.JList stateJList;
    // End of variables declaration//GEN-END:variables
}
