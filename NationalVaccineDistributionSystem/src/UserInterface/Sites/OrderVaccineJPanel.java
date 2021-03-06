/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInterface.Sites;

import Business.Enterprise.CDCEnterprise;
import Business.Enterprise.SiteEnterprise;
import Business.Enterprise.VaccineManufacturerEnterprise;
import Business.Order.Order;
import Business.Order.OrderItem;
import Business.Organization.Sites.ProviderOrganization;
import Business.Person.Person;
import Business.Person.Provider;
import Business.UserAccount.UserAccount;
import Business.Vaccine.Disease;
import Business.Vaccine.Vaccine;
import Business.WorkRequest.ApprovalRequestToPhd;
import java.awt.CardLayout;
import java.sql.Timestamp;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Rishika Idnani
 */
public class OrderVaccineJPanel extends javax.swing.JPanel {

    /**
     * Creates new form OrderVaccineJPanel
     */
    private JPanel userProcessContainer;
    private CDCEnterprise cdcEnterprise;
    private Order order;
    private Boolean isOrderItemPresent = false;
    private SiteEnterprise siteEnterprise;
    private ProviderOrganization providerOrganization;
    private UserAccount userAccount;

    private Date date;
    private String timeStamp;
    private Long currentTime;
    private Timestamp ts;

    public OrderVaccineJPanel(JPanel userProcessContainer, CDCEnterprise cdcEnterprise, SiteEnterprise siteEnterprise, ProviderOrganization providerOrganization, UserAccount userAccount) {
        initComponents();
        this.userProcessContainer = userProcessContainer;
        this.cdcEnterprise = cdcEnterprise;
        this.siteEnterprise = siteEnterprise;
        this.providerOrganization = providerOrganization;
        this.userAccount = userAccount;

        if (order == null) {
            order = new Order();
        }
        populateManufacturerDiseaseComboBox();
        populateOrderTable();
    }

    public void populateManufacturerDiseaseComboBox() {
        manufacturerJComboBox.removeAllItems();
        diseaseJComboBox.removeAllItems();

        for (VaccineManufacturerEnterprise vaccineManufacuter : cdcEnterprise.getVaccineManufacturerList()) {
            manufacturerJComboBox.addItem(vaccineManufacuter);
        }

        for (Disease disease : cdcEnterprise.getDiseaseCatalog().getDiseaseList()) {
            diseaseJComboBox.addItem(disease);
        }
    }

    public void setTimeStamp() {
        this.date = new Date();
        this.currentTime = date.getTime();
        this.ts = new Timestamp(currentTime);
        this.timeStamp = String.valueOf(ts);
    }

    public void populateOrderTable() {
        DefaultTableModel model = (DefaultTableModel) vaccineOrderJTable.getModel();
        model.setRowCount(0);

        for (OrderItem orderItem : order.getOrderItemList()) {
            Object row[] = new Object[4];
            row[0] = orderItem;
            row[1] = orderItem.getVaccine().getDisease().getDiseaseName();
            row[2] = orderItem.getTotalQuantity();
            row[3] = orderItem.getTotalPrice();

            model.addRow(row);
        }

    }

    public void populateVaccineTable(VaccineManufacturerEnterprise vaccineManufacturer) {
        if (vaccineManufacturer != null) {
            DefaultTableModel model = (DefaultTableModel) vaccineJTable.getModel();
            model.setRowCount(0);

            Disease disease = (Disease) diseaseJComboBox.getSelectedItem();

            for (Vaccine vaccine : vaccineManufacturer.getVaccineCatalog().getVaccineList()) {
                if (vaccine.getDisease() == disease) {
                    Object row[] = new Object[4];
                    row[0] = vaccine;
                    row[1] = vaccine.getSerialNo();
                    row[2] = vaccine.getPrice();
                    row[3] = vaccine.getDaysForManufacturing();
                    model.addRow(row);
                }
            }
        }
    }

    public void sendApprovalRequest() {
        ApprovalRequestToPhd request = new ApprovalRequestToPhd();
        providerOrganization.getWorkQueue().addRequest(request);

        request.setSender(userAccount.getPerson());
        request.setOrder(order);
        request.setMessage("Kindly approve my vaccine");
        request.setSiteEnterprise(siteEnterprise);
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
        chooseManuJLabel = new javax.swing.JLabel();
        manufacturerJComboBox = new javax.swing.JComboBox();
        chooseDiseaseJLabel = new javax.swing.JLabel();
        diseaseJComboBox = new javax.swing.JComboBox();
        chooseVaccineJLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        vaccineJTable = new javax.swing.JTable();
        jSpinner = new javax.swing.JSpinner();
        addToCartJButton = new javax.swing.JButton();
        orderSummaryJLabel = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        vaccineOrderJTable = new javax.swing.JTable();
        checkOutJButton = new javax.swing.JButton();
        backJButton = new javax.swing.JButton();
        modifyQuantityJLabel = new javax.swing.JLabel();
        modifyJTextField = new javax.swing.JTextField();
        modifyJButton = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        contractJLabel = new javax.swing.JLabel();

        panelDescriptionJLabel.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        panelDescriptionJLabel.setText("Order Vaccines");

        chooseManuJLabel.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        chooseManuJLabel.setText("Choose Manufacturer:");

        manufacturerJComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        manufacturerJComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        manufacturerJComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manufacturerJComboBoxActionPerformed(evt);
            }
        });

        chooseDiseaseJLabel.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        chooseDiseaseJLabel.setText("Choose Disease:");

        diseaseJComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        diseaseJComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        diseaseJComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                diseaseJComboBoxActionPerformed(evt);
            }
        });

        chooseVaccineJLabel.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        chooseVaccineJLabel.setText("Choose Vaccine to order");

        vaccineJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Vaccine Name", "Vaccine Serial Number", "Price", "No. of days for manufacturing"
            }
        ));
        jScrollPane1.setViewportView(vaccineJTable);

        jSpinner.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        addToCartJButton.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        addToCartJButton.setText("Add to Cart");
        addToCartJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addToCartJButtonActionPerformed(evt);
            }
        });

        orderSummaryJLabel.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        orderSummaryJLabel.setText("Order Summary");

        vaccineOrderJTable.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        vaccineOrderJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Vaccine Name", "Disease", "Total Quantity", "Total Price"
            }
        ));
        jScrollPane2.setViewportView(vaccineOrderJTable);

        checkOutJButton.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        checkOutJButton.setText("Proceed to Check Out");
        checkOutJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkOutJButtonActionPerformed(evt);
            }
        });

        backJButton.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        backJButton.setText("<< Back");
        backJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backJButtonActionPerformed(evt);
            }
        });

        modifyQuantityJLabel.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        modifyQuantityJLabel.setText("Modify Quantity");

        modifyJTextField.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        modifyJButton.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        modifyJButton.setText("Modify");
        modifyJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modifyJButtonActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jButton1.setText("Remove");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        contractJLabel.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        contractJLabel.setText("I agree for the manufacturing time it will take for all the ordered vaccines");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(273, 273, 273)
                        .addComponent(jSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(93, 93, 93)
                        .addComponent(addToCartJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(backJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(contractJLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(checkOutJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 657, Short.MAX_VALUE)
                            .addComponent(jScrollPane2)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(panelDescriptionJLabel)
                                .addComponent(chooseVaccineJLabel)
                                .addComponent(orderSummaryJLabel)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(chooseManuJLabel)
                                    .addGap(18, 18, 18)
                                    .addComponent(manufacturerJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(29, 29, 29)
                                    .addComponent(chooseDiseaseJLabel)
                                    .addGap(18, 18, 18)
                                    .addComponent(diseaseJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(modifyQuantityJLabel)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(modifyJButton)
                                            .addGap(0, 0, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(modifyJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))))))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelDescriptionJLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chooseManuJLabel)
                    .addComponent(manufacturerJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chooseDiseaseJLabel)
                    .addComponent(diseaseJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(chooseVaccineJLabel)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addToCartJButton))
                .addGap(18, 18, 18)
                .addComponent(orderSummaryJLabel)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(modifyQuantityJLabel)
                    .addComponent(modifyJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(modifyJButton)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(contractJLabel)
                    .addComponent(checkOutJButton))
                .addGap(27, 27, 27)
                .addComponent(backJButton)
                .addContainerGap(20, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void manufacturerJComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manufacturerJComboBoxActionPerformed
        // TODO add your handling code here:
        VaccineManufacturerEnterprise vaccineManufacturer = (VaccineManufacturerEnterprise) manufacturerJComboBox.getSelectedItem();

        populateVaccineTable(vaccineManufacturer);
    }//GEN-LAST:event_manufacturerJComboBoxActionPerformed

    private void diseaseJComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_diseaseJComboBoxActionPerformed
        // TODO add your handling code here:
        VaccineManufacturerEnterprise vaccineManufacturer = (VaccineManufacturerEnterprise) manufacturerJComboBox.getSelectedItem();

        populateVaccineTable(vaccineManufacturer);
    }//GEN-LAST:event_diseaseJComboBoxActionPerformed

    private void addToCartJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addToCartJButtonActionPerformed
        // TODO add your handling code here:
        int selectedRow = vaccineJTable.getSelectedRow();

        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(null, "Kindly select a vaccine");
            return;
        }

        int quantity = (Integer) jSpinner.getValue();
        if (quantity <= 0) {
            JOptionPane.showMessageDialog(null, "Quantity is bad!");
            return;
        }

        Vaccine vaccine = (Vaccine) vaccineJTable.getValueAt(selectedRow, 0);

        for (int i = 0; i < vaccineOrderJTable.getRowCount(); i++) {
            OrderItem orderItem = (OrderItem) vaccineOrderJTable.getValueAt(i, 0);

            if (orderItem.getVaccine() == vaccine) {
                int newQuantity = quantity + (Integer) vaccineOrderJTable.getValueAt(i, 2);
                orderItem.setTotalQuantity(newQuantity);
                orderItem.setTotalPrice(newQuantity * orderItem.getVaccine().getPrice());
                isOrderItemPresent = true;
            }
        }
        if (isOrderItemPresent == false) {
            OrderItem oi = order.addAndCreateOrderItem(vaccine, quantity);
        }

        populateOrderTable();
    }//GEN-LAST:event_addToCartJButtonActionPerformed

    private void checkOutJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkOutJButtonActionPerformed
        // TODO add your handling code here:
        if (order != null) {
            if (order.getOrderItemList().size() == 0) {
                JOptionPane.showMessageDialog(null, "Cart is empty!");
                return;
            }
            order.setSite(siteEnterprise);
            setTimeStamp();
            this.order.setTimeStamp(timeStamp);
            this.order.setProvider(userAccount.getPerson());
            this.order = cdcEnterprise.getMasterOrderCatalog().addOrder(order);
            siteEnterprise.getOrderList().add(order);
            Person person = userAccount.getPerson();
            Provider provider = (Provider) person;
            provider.getOrderedList().add(order);
            modifyJTextField.setText("");
            JOptionPane.showMessageDialog(null, "You have successfully checked out");
        } else {
            JOptionPane.showMessageDialog(null, "Error in checking out!");
            return;
        }
        sendApprovalRequest();
        order = new Order();
        populateOrderTable();
    }//GEN-LAST:event_checkOutJButtonActionPerformed

    private void modifyJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modifyJButtonActionPerformed
        // TODO add your handling code here:
        int selectedRow = vaccineOrderJTable.getSelectedRow();

        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(null, "Error in checking out!");
            return;
        }

        int newQuantity;
        try {
            newQuantity = Integer.parseInt(modifyJTextField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Enter proper input!");
            return;
        }

        OrderItem orderItem = (OrderItem) vaccineOrderJTable.getValueAt(selectedRow, 0);
        orderItem.setTotalQuantity(newQuantity);
        populateOrderTable();
    }//GEN-LAST:event_modifyJButtonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        int selectedRow = vaccineOrderJTable.getSelectedRow();

        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(null, "Error in checking out!");
            return;
        }
        OrderItem orderItem = (OrderItem) vaccineOrderJTable.getValueAt(selectedRow, 0);
        order.removeOrderItem(orderItem);

        populateOrderTable();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void backJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backJButtonActionPerformed
        // TODO add your handling code here:
        userProcessContainer.remove(this);
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.previous(userProcessContainer);
    }//GEN-LAST:event_backJButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addToCartJButton;
    private javax.swing.JButton backJButton;
    private javax.swing.JButton checkOutJButton;
    private javax.swing.JLabel chooseDiseaseJLabel;
    private javax.swing.JLabel chooseManuJLabel;
    private javax.swing.JLabel chooseVaccineJLabel;
    private javax.swing.JLabel contractJLabel;
    private javax.swing.JComboBox diseaseJComboBox;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSpinner jSpinner;
    private javax.swing.JComboBox manufacturerJComboBox;
    private javax.swing.JButton modifyJButton;
    private javax.swing.JTextField modifyJTextField;
    private javax.swing.JLabel modifyQuantityJLabel;
    private javax.swing.JLabel orderSummaryJLabel;
    private javax.swing.JLabel panelDescriptionJLabel;
    private javax.swing.JTable vaccineJTable;
    private javax.swing.JTable vaccineOrderJTable;
    // End of variables declaration//GEN-END:variables
}
