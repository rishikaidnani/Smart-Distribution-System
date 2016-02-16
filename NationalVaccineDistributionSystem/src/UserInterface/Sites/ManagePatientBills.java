/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInterface.Sites;

import Business.Enterprise.CDCEnterprise;
import Business.Enterprise.SiteEnterprise;
import Business.Order.Order;
import Business.Order.OrderItem;
import Business.Organization.Organization;
import Business.Organization.Sites.ProviderOrganization;
import Business.Organization.Sites.SiteOperationOrganization;
import Business.Person.Patient;
import Business.Person.Provider;
import Business.UserAccount.UserAccount;
import Business.Vaccine.Vaccine;
import Business.WorkRequest.ApprovalRequestToPhd;
import Business.WorkRequest.BillTheCdc;
import java.awt.CardLayout;
import java.sql.Timestamp;
import java.util.Date;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Rishika Idnani
 */
public class ManagePatientBills extends javax.swing.JPanel {

    /**
     * Creates new form ManagePatientBills
     */
    private JPanel userProcessContainer;
    private ProviderOrganization providerOrganization;
    private CDCEnterprise cdcEnterprise;
    private SiteEnterprise siteEnterprise;
    private UserAccount userAccount;
    private Order order;

    private Date date;
    private String timeStamp;
    private Long currentTime;
    private Timestamp ts;

    public ManagePatientBills(JPanel userProcessContainer, ProviderOrganization providerOrganization,
            CDCEnterprise cdcEnterprise, SiteEnterprise siteEnterprise, UserAccount userAccount) {
        initComponents();
        this.userProcessContainer = userProcessContainer;
        this.providerOrganization = providerOrganization;
        this.cdcEnterprise = cdcEnterprise;
        this.siteEnterprise = siteEnterprise;
        this.userAccount = userAccount;

        populatePatientList();
        populateVaccineList();
    }

    public void sendApprovalRequest() {
        ApprovalRequestToPhd request = new ApprovalRequestToPhd();
        providerOrganization.getWorkQueue().addRequest(request);

        request.setSender(userAccount.getPerson());
        request.setOrder(order);
        request.setMessage("Kindly approve my vaccine");
        request.setSiteEnterprise(siteEnterprise);
    }

    public void automaticOrdering(OrderItem oi) {
        Provider provider = (Provider) userAccount.getPerson();
        Vaccine v = oi.getVaccine();
        String vaccineInjectedName = oi.getVaccine().getName();
        int totalAvailableInInventory = 0;
        for (Order order : provider.getOrderedList()) {
            for (OrderItem orderItem : order.getOrderItemList()) {
                if (orderItem.getVaccine().getName().equals(vaccineInjectedName)) {
                    totalAvailableInInventory = orderItem.getTotalQuantity() + totalAvailableInInventory;
                }
            }
        }

        if (totalAvailableInInventory < 20) {
            order = new Order();
            OrderItem newOrderItem = order.addAndCreateOrderItem(v, 20);
            order.setSite(siteEnterprise);
            setTimeStamp();
            order.setTimeStamp(timeStamp);
            order.setProvider(provider);
            order = cdcEnterprise.getMasterOrderCatalog().addOrder(order);
            siteEnterprise.getOrderList().add(order);
            provider.getOrderedList().add(order);
            JOptionPane.showMessageDialog(null, "The vaccine in inventory went below 20. Hence an automatic order has been placed for 20 vaccines");
            sendApprovalRequest();
        }
    }

    public void setTimeStamp() {
        this.date = new Date();
        this.currentTime = date.getTime();
        this.ts = new Timestamp(currentTime);
        this.timeStamp = String.valueOf(ts);
    }

    public void populatePatientList() {
        DefaultListModel model = new DefaultListModel();
        patientJList.removeAll();

        Provider provider = (Provider) userAccount.getPerson();

        for (Patient patient : provider.getPatientList()) {
            model.addElement(patient);
        }
        patientJList.setModel(model);
    }

    public void populateVaccineList() {
        DefaultListModel model = new DefaultListModel();
        vaccineJList.removeAll();
        Provider provider = (Provider) userAccount.getPerson();
        for (Order order : provider.getOrderedList()) {
            for (OrderItem oi : order.getOrderItemList()) {
                if (oi.getIsOrderItemShippedToSite().equals("Approved") && oi.getTotalQuantity() > 0) {
                    Boolean isPresent = false;
                    for (int i = 0; i < vaccineJList.getModel().getSize(); i++) {
                        OrderItem orderItem = (OrderItem) vaccineJList.getModel().getElementAt(i);
                        if (orderItem.getVaccine() == oi.getVaccine()) {
                            isPresent = true;
                            break;
                        }
                    }
                    if (isPresent == false) {
                        model.addElement(oi);
                    }
                    vaccineJList.setModel(model);
                }
            }
        }
    }

    public void billTheCdc(float amount, Patient patient, Vaccine vaccine) {
        BillTheCdc workRequest = new BillTheCdc();
        providerOrganization.getWorkQueue().addRequest(workRequest);
        workRequest.setAmount(amount);
        workRequest.setSiteEnterprise(siteEnterprise);
        workRequest.setProvider((Provider) userAccount.getPerson());
        workRequest.setPatient(patient);
        workRequest.setVaccine(vaccine);
        workRequest.setMessage("Find the bill for the patient");
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
        patientsJLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        patientJList = new javax.swing.JList();
        vaccineJLabel = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        vaccineJList = new javax.swing.JList();
        proceedJButton = new javax.swing.JButton();
        backJButton = new javax.swing.JButton();

        panelDescriptionJLabel.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        panelDescriptionJLabel.setText("Manage Patient Bills");

        patientsJLabel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        patientsJLabel.setText("List of Patients");

        patientJList.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jScrollPane1.setViewportView(patientJList);

        vaccineJLabel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        vaccineJLabel.setText("List of Vaccines");

        vaccineJList.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jScrollPane2.setViewportView(vaccineJList);

        proceedJButton.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        proceedJButton.setText("Proceed and Bill");
        proceedJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                proceedJButtonActionPerformed(evt);
            }
        });

        backJButton.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        backJButton.setText("<< Back");
        backJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backJButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelDescriptionJLabel)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(patientsJLabel)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(backJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(45, 45, 45)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(vaccineJLabel)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(63, 63, 63)
                                .addComponent(proceedJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelDescriptionJLabel)
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(patientsJLabel)
                    .addComponent(vaccineJLabel))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(proceedJButton)
                    .addComponent(backJButton))
                .addContainerGap(59, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void proceedJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_proceedJButtonActionPerformed
        // TODO add your handling code here:
        Patient patient = (Patient) patientJList.getSelectedValue();
        if (patient == null) {
            JOptionPane.showMessageDialog(null, "Kindly select a patient!");
            return;
        }

        OrderItem oi = (OrderItem) vaccineJList.getSelectedValue();
        if (oi == null) {
            JOptionPane.showMessageDialog(null, "Kindly select a vaccine!");
            return;
        }

        String insuranceInformation = patient.getInsuranceInformation();

        if (insuranceInformation.equals("Private Insurance Coverage")) {
            //private insurance will pay the bill
        } else if (insuranceInformation.equals("Under Insured")) {
            float coveragePercentile = patient.getCoveragePercentile();
            float difference = oi.getVaccine().getPrice() - ((coveragePercentile / 100) * oi.getVaccine().getPrice());
            billTheCdc(difference, patient, oi.getVaccine());
        } else if (insuranceInformation.equals("No Insurance")) {
            float amount = Float.parseFloat(String.valueOf(oi.getVaccine().getPrice()));
            billTheCdc(amount, patient, oi.getVaccine());
        }
//Subtracting from Providers inventory        
        int newQuantity = oi.getTotalQuantity() - 1;
        oi.setTotalQuantity(newQuantity);

////Subtracting from Sites inventory
//        for (Organization organization : siteEnterprise.getOrganizationDirectory().getOrganizationList()) {
//            if (organization instanceof SiteOperationOrganization) {
//                SiteOperationOrganization o = (SiteOperationOrganization) organization;
//                for (OrderItem orderItem : o.getVaccineInventory()) {
//                    if (orderItem == oi) {
////                        int nQuantity = oi.getTotalQuantity() - 1;
//                        oi.setTotalQuantity(nQuantity);
//                    }
//                }
//            }
//        }

        automaticOrdering(oi);
    }//GEN-LAST:event_proceedJButtonActionPerformed

    private void backJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backJButtonActionPerformed
        // TODO add your handling code here:
        userProcessContainer.remove(this);
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.previous(userProcessContainer);
    }//GEN-LAST:event_backJButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backJButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel panelDescriptionJLabel;
    private javax.swing.JList patientJList;
    private javax.swing.JLabel patientsJLabel;
    private javax.swing.JButton proceedJButton;
    private javax.swing.JLabel vaccineJLabel;
    private javax.swing.JList vaccineJList;
    // End of variables declaration//GEN-END:variables
}
