/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInterface.Distributor;

import Business.Enterprise.CDCEnterprise;
import Business.Enterprise.NationalDistributorEnterprise;
import Business.Enterprise.SiteEnterprise;
import Business.Enterprise.VaccineManufacturerEnterprise;
import Business.Order.Order;
import Business.Order.OrderItem;
import Business.Organization.Distributor.DistributionCentre;
import Business.Organization.Organization;
import Business.Organization.Sites.SiteOperationOrganization;
import Business.WorkRequest.BillTheProvider;
import Business.WorkRequest.ShipmentToDistributionCentre;
import Business.WorkRequest.ShipmentToSite;
import Business.WorkRequest.WorkRequest;
import java.util.Properties;
//import javax.mail.Message;
//import javax.mail.MessagingException;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
//import java.util.Properties;
//import javax.mail.Message;
//import javax.mail.MessagingException;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Rishika Idnani
 */
public class DistributionCentreWorkArea extends javax.swing.JPanel {

    /**
     * Creates new form DistributionCentreWorkArea
     */
    private JPanel userProcessContainer;
    private NationalDistributorEnterprise ndEnterprise;
    private CDCEnterprise cdcEnterprise;
    private DistributionCentre organization;
    String to = "revatimenon90@gmail.com";
    String from = "ridnani27@gmail.com";
    String host = "localhost";

    public DistributionCentreWorkArea(JPanel userProcessContainer, NationalDistributorEnterprise ndEnterprise, CDCEnterprise cdcEnterprise, DistributionCentre organization) {
        initComponents();
        this.userProcessContainer = userProcessContainer;
        this.ndEnterprise = ndEnterprise;
        this.cdcEnterprise = cdcEnterprise;
        this.organization = organization;
        populateSiteList();
    }

//    public void sendEmail() {
//        Properties properties = System.getProperties();
//        properties.setProperty("mail.smtp.host", host);
//        Session session = Session.getDefaultInstance(properties);
//
//        try {
//            MimeMessage message = new MimeMessage(session);
//            message.setFrom(new InternetAddress(from));
//            message.addRecipient(Message.RecipientType.TO,
//                    new InternetAddress(to));
//            message.setSubject("This is the Subject Line!");
//            message.setText("This is actual message");
//            Transport.send(message);
//            System.out.println("Sent message successfully....");
//        } catch (MessagingException mex) {
//            mex.printStackTrace();
//        }
//    }

    public void populateSiteList() {
        DefaultListModel model = new DefaultListModel();
        siteJList.removeAll();
        for (VaccineManufacturerEnterprise vmEnterprise : cdcEnterprise.getVaccineManufacturerList()) {
            for (WorkRequest workRequest : vmEnterprise.getWorkQueue().getWorkRequestList()) {
                if (workRequest instanceof ShipmentToDistributionCentre) {
                    Boolean isPresent = false;
                    ShipmentToDistributionCentre request = (ShipmentToDistributionCentre) workRequest;
                    int pinCodeOfSite = request.getSiteEnterprise().getPinCode();
                    int pinCodeOfCentre = organization.getPinCode();
                    if (pinCodeOfCentre == 2121) {
                        if (request.getStateName().equals(organization.getStateName())
                                && request.getOrderItem().getIsOrderItemShippedToSite().equals("Pending")
                                && pinCodeOfSite == 2120) {
                            for (int i = 0; i < siteJList.getModel().getSize(); i++) {
                                if (siteJList.getModel().getElementAt(i) == request.getSiteEnterprise()) {
                                    isPresent = true;
                                    break;
                                }
                            }
                            if (isPresent == false) {
                                model.addElement(request.getSiteEnterprise());
                            }
                            siteJList.setModel(model);
                        }
                    } else if (pinCodeOfCentre == 2127) {
                        if (request.getStateName().equals(organization.getStateName())
                                && request.getOrderItem().getIsOrderItemShippedToSite().equals("Pending")
                                && pinCodeOfSite == 2123) {
                            for (int i = 0; i < siteJList.getModel().getSize(); i++) {
                                if (siteJList.getModel().getElementAt(i) == request.getSiteEnterprise()) {
                                    isPresent = true;
                                    break;
                                }
                            }
                            if (isPresent == false) {
                                model.addElement(request.getSiteEnterprise());
                            }
                            siteJList.setModel(model);
                        }
                    } else if (request.getStateName().equals(organization.getStateName())
                            && request.getOrderItem().getIsOrderItemShippedToSite().equals("Pending")) {
                        for (int i = 0; i < siteJList.getModel().getSize(); i++) {
                            if (siteJList.getModel().getElementAt(i) == request.getSiteEnterprise()) {
                                isPresent = true;
                                break;
                            }
                        }
                        if (isPresent == false) {
                            model.addElement(request.getSiteEnterprise());
                        }
                        siteJList.setModel(model);
                    }
                }
            }
        }
    }

    public void populateVaccineTable(SiteEnterprise siteEnterprise) {
        DefaultTableModel model = (DefaultTableModel) vaccineJTable.getModel();
        model.setRowCount(0);

        for (VaccineManufacturerEnterprise vmEnterprise : cdcEnterprise.getVaccineManufacturerList()) {
            for (WorkRequest workRequest : vmEnterprise.getWorkQueue().getWorkRequestList()) {
                if (workRequest instanceof ShipmentToDistributionCentre) {
                    ShipmentToDistributionCentre request = (ShipmentToDistributionCentre) workRequest;
                    if (request.getStateName().equals(organization.getStateName())
                            && request.getOrderItem().getIsOrderItemShippedToSite().equals("Pending")) {
                        if (request.getSiteEnterprise() == siteEnterprise) {
                            Object row[] = new Object[4];
                            row[0] = request.getOrderItem();
                            row[1] = request.getOrderItem().getTotalQuantity();
                            row[2] = request.getOrder();
                            row[3] = request.getOrder().getProvider();
                            model.addRow(row);
                        }
                    }
                }
            }
        }
    }

    public void ship(OrderItem orderItem, SiteEnterprise siteEnterprise, Order order) {
        ShipmentToSite workRequest = new ShipmentToSite();
        organization.getWorkQueue().addRequest(workRequest);
        orderItem.setIsOrderItemShippedToSite("Approved");
        workRequest.setSiteEnterprise(siteEnterprise);
        workRequest.setOrderItem(orderItem);
        workRequest.setOrder(order);
    }

    public void addToSiteInventory(SiteEnterprise siteEnterprise, OrderItem orderItem) {
        for (Organization organization : siteEnterprise.getOrganizationDirectory().getOrganizationList()) {
            if (organization instanceof SiteOperationOrganization) {
                SiteOperationOrganization o = (SiteOperationOrganization) organization;
                o.getVaccineInventory().add(orderItem);
            }
        }
    }

    public void billTheProvider(Order order, OrderItem orderItem) {
        BillTheProvider workRequest = new BillTheProvider();
        organization.getWorkQueue().addRequest(workRequest);
        workRequest.setOrder(order);
        workRequest.setOrderItem(orderItem);
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
        displayJLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        vaccineJTable = new javax.swing.JTable();
        shipJButton = new javax.swing.JButton();
        sitesJLabel = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        siteJList = new javax.swing.JList();
        clickHereJButton = new javax.swing.JButton();

        panelDescriptionJLabel.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        panelDescriptionJLabel.setText("Vaccine Delivery Processing");

        displayJLabel.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        displayJLabel.setText("Display of Vaccine to be shipped");

        vaccineJTable.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        vaccineJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Vaccine Name", "Quantity", "Order Date/Time", "Deliver in the name of"
            }
        ));
        vaccineJTable.setRowHeight(25);
        jScrollPane1.setViewportView(vaccineJTable);

        shipJButton.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        shipJButton.setText("Ship to Site");
        shipJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shipJButtonActionPerformed(evt);
            }
        });

        sitesJLabel.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        sitesJLabel.setText("Sites");

        siteJList.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jScrollPane2.setViewportView(siteJList);

        clickHereJButton.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        clickHereJButton.setText("Click here!");
        clickHereJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clickHereJButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 624, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(panelDescriptionJLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(shipJButton)
                                .addComponent(sitesJLabel)
                                .addComponent(jScrollPane2))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(displayJLabel)
                                .addGap(18, 18, 18)
                                .addComponent(clickHereJButton)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelDescriptionJLabel)
                .addGap(18, 18, 18)
                .addComponent(sitesJLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(displayJLabel)
                    .addComponent(clickHereJButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(shipJButton)
                .addContainerGap(27, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void shipJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shipJButtonActionPerformed
        // TODO add your handling code here:
        int selectedRow = vaccineJTable.getSelectedRow();

        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(null, "Kindly select the vaccine to be shipped!");
            return;
        }

        int selectedIndex = siteJList.getSelectedIndex();
        if (selectedIndex < 0) {
            JOptionPane.showMessageDialog(null, "Kindly select a site!");
            return;
        }
        SiteEnterprise siteEnterprise = (SiteEnterprise) siteJList.getSelectedValue();

        OrderItem orderItem = (OrderItem) vaccineJTable.getValueAt(selectedRow, 0);
        Order order = (Order) vaccineJTable.getValueAt(selectedRow, 2);
        orderItem.setProvider(order.getProvider());
        ship(orderItem, siteEnterprise, order);
        addToSiteInventory(siteEnterprise, orderItem);
//        sendEmailToProvider();
        billTheProvider(order, orderItem);
        populateVaccineTable(siteEnterprise);
    }//GEN-LAST:event_shipJButtonActionPerformed

    private void clickHereJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clickHereJButtonActionPerformed
        // TODO add your handling code here:

        int selectedIndex = siteJList.getSelectedIndex();
        if (selectedIndex < 0) {
            JOptionPane.showMessageDialog(null, "Kindly select a site!");
            return;
        }
        SiteEnterprise siteEnterprise = (SiteEnterprise) siteJList.getSelectedValue();
        populateVaccineTable(siteEnterprise);

    }//GEN-LAST:event_clickHereJButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton clickHereJButton;
    private javax.swing.JLabel displayJLabel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel panelDescriptionJLabel;
    private javax.swing.JButton shipJButton;
    private javax.swing.JList siteJList;
    private javax.swing.JLabel sitesJLabel;
    private javax.swing.JTable vaccineJTable;
    // End of variables declaration//GEN-END:variables
}
