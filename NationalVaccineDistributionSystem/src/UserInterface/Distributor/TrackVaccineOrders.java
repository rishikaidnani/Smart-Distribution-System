/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInterface.Distributor;

import Business.Enterprise.CDCEnterprise;
import Business.Enterprise.NationalDistributorEnterprise;
import Business.Enterprise.PHDEnterprise;
import Business.Enterprise.SiteEnterprise;
import Business.Order.Order;
import Business.Order.OrderItem;
import Business.Organization.CDC.CDCOperationsOrganization;
import Business.Organization.Distributor.DistributionCentre;
import Business.Organization.Organization;
import Business.Organization.Sites.ProviderOrganization;
import Business.Person.Person;
import Business.Person.Provider;
import Business.Vaccine.Vaccine;
import Business.WorkRequest.ShipmentRequestToNationalDistributor;
import Business.WorkRequest.WorkRequest;
import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Rishika Idnani
 */
public class TrackVaccineOrders extends javax.swing.JPanel {

    /**
     * Creates new form TrackVaccineOrders
     */
    private JPanel userProcessContainer;
    private CDCEnterprise enterprise;
    private String stateName;
    private NationalDistributorEnterprise nationalDistributorEnterprise;
    private DistributionCentre distributionCentre;

    public TrackVaccineOrders(JPanel userProcessContainer, CDCEnterprise enterprise, NationalDistributorEnterprise nationalDistributorEnterprise) {
        initComponents();
        this.userProcessContainer = userProcessContainer;
        this.enterprise = enterprise;
        this.nationalDistributorEnterprise = nationalDistributorEnterprise;

        populateStateList();
    }

    public DistributionCentre getDistributionCentre(String stateName) {
        for (Organization organization : nationalDistributorEnterprise.getOrganizationDirectory().getOrganizationList()) {
            if (organization instanceof DistributionCentre) {
                DistributionCentre dc = (DistributionCentre) organization;
                if (dc.getStateName().equals(stateName)) {
                    distributionCentre = dc;
                    break;
                }
            }
        }
        return distributionCentre;
    }

    public DistributionCentre getDistributionCentreOfMassa(String stateName, int pincode) {
        for (Organization organization : nationalDistributorEnterprise.getOrganizationDirectory().getOrganizationList()) {
            if (organization instanceof DistributionCentre) {
                DistributionCentre dc = (DistributionCentre) organization;
                if (dc.getStateName().equals(stateName)) {
                    if (pincode == 2120 && dc.getPinCode() == 2121) {
                        distributionCentre = dc;
                        break;
                    } else if (pincode == 2123 && dc.getPinCode() == 2127) {
                        distributionCentre = dc;
                        break;
                    }

                }
            }
        }
        return distributionCentre;
    }

    public String getStateName(OrderItem oi, Order o) {
        Provider provider = (Provider) o.getProvider();
        for (PHDEnterprise phdEnterprise : enterprise.getPhdList()) {
            for (SiteEnterprise sEnterprise : phdEnterprise.getSiteList()) {
                for (Organization organization : sEnterprise.getOrganizationDirectory().getOrganizationList()) {
                    if (organization instanceof ProviderOrganization) {
                        ProviderOrganization po = (ProviderOrganization) organization;
                        for (Person person : po.getPersonDirectory().getPersonList()) {
                            if (person instanceof Provider) {
                                Provider p = (Provider) person;
                                if (p.getName().equals(o.getProvider().getName())) {
                                    stateName = sEnterprise.getStateName();
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        return stateName;
    }

    public void populateStateList() {
        DefaultListModel model = new DefaultListModel();
        stateJList.removeAll();

        for (Organization organization : enterprise.getOrganizationDirectory().getOrganizationList()) {
            if (organization instanceof CDCOperationsOrganization) {
                for (WorkRequest workRequest : organization.getWorkQueue().getWorkRequestList()) {
                    if (workRequest instanceof ShipmentRequestToNationalDistributor) {
                        ShipmentRequestToNationalDistributor request = (ShipmentRequestToNationalDistributor) workRequest;
                        Boolean isPresent = false;
                        if (request.getOrderAttendedByNd().equals("Pending")) {
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelDescriptionJLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        stateJList = new javax.swing.JList();
        selectStateJLabel = new javax.swing.JLabel();
        display1JButton = new javax.swing.JButton();
        selectHospitalJLabel = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        hospitalJList = new javax.swing.JList();
        display2JButton = new javax.swing.JButton();
        displayTableJLabel = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        vaccineJTable = new javax.swing.JTable();
        backJButton = new javax.swing.JButton();
        detailsJLabel = new javax.swing.JLabel();
        clickForTrackJButton = new javax.swing.JButton();
        withManufacturerJTextField = new javax.swing.JTextField();
        withDistributionCentreJTextField = new javax.swing.JTextField();
        withSiteJTextField = new javax.swing.JTextField();

        panelDescriptionJLabel.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        panelDescriptionJLabel.setText("View Orders");

        stateJList.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jScrollPane1.setViewportView(stateJList);

        selectStateJLabel.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        selectStateJLabel.setText("Select State");

        display1JButton.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        display1JButton.setText(">>>");
        display1JButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                display1JButtonActionPerformed(evt);
            }
        });

        selectHospitalJLabel.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        selectHospitalJLabel.setText("Select Hospital/Pharmacy/Clinics");

        hospitalJList.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jScrollPane2.setViewportView(hospitalJList);

        display2JButton.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        display2JButton.setText("Click Here!");
        display2JButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                display2JButtonActionPerformed(evt);
            }
        });

        displayTableJLabel.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        displayTableJLabel.setText("Details of order");

        vaccineJTable.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        vaccineJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Vaccine Name", "Quantity", "Responsible Distribution Centre", "Current Status"
            }
        ));
        vaccineJTable.setRowHeight(25);
        jScrollPane3.setViewportView(vaccineJTable);

        backJButton.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        backJButton.setText("<< Back");
        backJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backJButtonActionPerformed(evt);
            }
        });

        detailsJLabel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        detailsJLabel.setText("Progress of Shipment");

        clickForTrackJButton.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        clickForTrackJButton.setText("Click Here!");
        clickForTrackJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clickForTrackJButtonActionPerformed(evt);
            }
        });

        withManufacturerJTextField.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        withManufacturerJTextField.setText("Manufacturer yet to Ship >>>>>");

        withDistributionCentreJTextField.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        withDistributionCentreJTextField.setText("In Distribution Centre >>>>>");

        withSiteJTextField.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        withSiteJTextField.setText("Reached Site");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panelDescriptionJLabel)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(selectStateJLabel)
                                .addGap(250, 250, 250)
                                .addComponent(selectHospitalJLabel))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(displayTableJLabel)
                                .addGap(38, 38, 38)
                                .addComponent(display2JButton, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(backJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(44, 44, 44)
                                .addComponent(display1JButton)
                                .addGap(71, 71, 71)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(clickForTrackJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(detailsJLabel)
                                .addGap(18, 18, 18)
                                .addComponent(withManufacturerJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(withDistributionCentreJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(withSiteJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 154, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelDescriptionJLabel)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(selectStateJLabel)
                    .addComponent(selectHospitalJLabel))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addComponent(display1JButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane2)
                            .addComponent(jScrollPane1))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(displayTableJLabel)
                    .addComponent(display2JButton))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(detailsJLabel)
                    .addComponent(withManufacturerJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(withDistributionCentreJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(withSiteJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(clickForTrackJButton)
                .addGap(26, 26, 26)
                .addComponent(backJButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void display1JButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_display1JButtonActionPerformed
        // TODO add your handling code here:
        String stateName = String.valueOf(stateJList.getSelectedValue());

        if (stateName.equals("")) {
            JOptionPane.showMessageDialog(null, "Kindly select a state to view Orders!");
            return;
        }

        DefaultListModel model = new DefaultListModel();
        hospitalJList.removeAll();
        for (Organization organization : enterprise.getOrganizationDirectory().getOrganizationList()) {
            if (organization instanceof CDCOperationsOrganization) {
                for (WorkRequest workRequest : organization.getWorkQueue().getWorkRequestList()) {
                    if (workRequest instanceof ShipmentRequestToNationalDistributor) {
                        ShipmentRequestToNationalDistributor request = (ShipmentRequestToNationalDistributor) workRequest;

                        Boolean isPresent = false;
                        if (request.getState().equals(stateName)) {
                            for (int i = 0; i < hospitalJList.getModel().getSize(); i++) {
                                if (hospitalJList.getModel().getElementAt(i) == request.getSiteEnterprise()) {
                                    isPresent = true;
                                    break;
                                }
                            }
                            if (isPresent == false && request.getOrderAttendedByNd().equals("Pending")) {
                                model.addElement(request.getSiteEnterprise());
                            }
                            hospitalJList.setModel(model);
                        }
                    }
                }
            }
        }
    }//GEN-LAST:event_display1JButtonActionPerformed

    private void display2JButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_display2JButtonActionPerformed
        // TODO add your handling code here:
        int selectedValue = hospitalJList.getSelectedIndex();

        if (selectedValue < 0) {
            JOptionPane.showMessageDialog(null, "Kindly select a site!");
            return;
        }
        SiteEnterprise siteEnterprise = (SiteEnterprise) hospitalJList.getSelectedValue();

        DefaultTableModel model = (DefaultTableModel) vaccineJTable.getModel();
        model.setRowCount(0);

        for (Order o : siteEnterprise.getOrderList()) {
            for (OrderItem oi : o.getOrderItemList()) {
                if (oi.getIsOrderItemApprovedByCdc().equals("Approved")) {
                    stateName = getStateName(oi, o);
                    if (stateName.equals("Massachusetts")) {
                        int pinCode = siteEnterprise.getPinCode();
                        distributionCentre = getDistributionCentreOfMassa(stateName, pinCode);
                    } else {
                        distributionCentre = getDistributionCentre(stateName);
                    }
                    Boolean isPresent = false;
                    for (int i = 0; i < vaccineJTable.getRowCount(); i++) {
                        if ((OrderItem) vaccineJTable.getValueAt(i, 0) == oi) {
                            isPresent = true;
                            int newQuantity = oi.getTotalQuantity() + Integer.parseInt(String.valueOf(vaccineJTable.getValueAt(i, 2)));
                            vaccineJTable.setValueAt(newQuantity, i, 2);
                            break;
                        }
                    }
                    if (isPresent == false) {
                        Object row[] = new Object[4];
                        row[0] = oi;
                        row[1] = oi.getTotalQuantity();
                        row[2] = distributionCentre.getName();
                        if (oi.getIsOrderItemShippedToDistributionCentre().equals("Shipped")
                                && oi.getIsOrderItemShippedToSite().equals("Pending")) {
                            row[3] = "Vaccine in Distribution centre";
                        } else if (oi.getIsOrderItemShippedToSite().equals("Approved")) {
                            row[3] = "Vaccine shipped to the site";
                        } else {
                            row[3] = "In process";
                        }
                        model.addRow(row);
                    }
                }
            }
        }
    }//GEN-LAST:event_display2JButtonActionPerformed

    private void backJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backJButtonActionPerformed
        // TODO add your handling code here:
        userProcessContainer.remove(this);
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.previous(userProcessContainer);
    }//GEN-LAST:event_backJButtonActionPerformed

    private void clickForTrackJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clickForTrackJButtonActionPerformed
        // TODO add your handling code here:
        withDistributionCentreJTextField.setBackground(Color.WHITE);
        withManufacturerJTextField.setBackground(Color.WHITE);
        withSiteJTextField.setBackground(Color.WHITE);
        int selectedRow = vaccineJTable.getSelectedRow();

        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(null, "Kindly select a row");
            return;
        }

        OrderItem orderItem = (OrderItem) vaccineJTable.getValueAt(selectedRow, 0);
        if (orderItem.getIsOrderItemShippedToDistributionCentre().equals("Shipped")
                && orderItem.getIsOrderItemShippedToSite().equals("Pending")) {
            withDistributionCentreJTextField.setBackground(Color.GREEN);
            withManufacturerJTextField.setBackground(Color.GREEN);
        } else if (orderItem.getIsOrderItemShippedToSite().equals("Approved")) {
            withDistributionCentreJTextField.setBackground(Color.GREEN);
            withManufacturerJTextField.setBackground(Color.GREEN);
            withSiteJTextField.setBackground(Color.GREEN);
        } else {
            withManufacturerJTextField.setBackground(Color.GREEN);
        }

    }//GEN-LAST:event_clickForTrackJButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backJButton;
    private javax.swing.JButton clickForTrackJButton;
    private javax.swing.JLabel detailsJLabel;
    private javax.swing.JButton display1JButton;
    private javax.swing.JButton display2JButton;
    private javax.swing.JLabel displayTableJLabel;
    private javax.swing.JList hospitalJList;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel panelDescriptionJLabel;
    private javax.swing.JLabel selectHospitalJLabel;
    private javax.swing.JLabel selectStateJLabel;
    private javax.swing.JList stateJList;
    private javax.swing.JTable vaccineJTable;
    private javax.swing.JTextField withDistributionCentreJTextField;
    private javax.swing.JTextField withManufacturerJTextField;
    private javax.swing.JTextField withSiteJTextField;
    // End of variables declaration//GEN-END:variables
}
