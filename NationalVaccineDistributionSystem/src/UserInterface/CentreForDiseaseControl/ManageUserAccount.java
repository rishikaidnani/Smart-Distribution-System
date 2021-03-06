/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInterface.CentreForDiseaseControl;

import Business.Enterprise.CDCEnterprise;
import Business.Enterprise.Enterprise;
import Business.Enterprise.PHDEnterprise;
import Business.Enterprise.VaccineManufacturerEnterprise;
import Business.Network.Network;
import Business.Organization.Organization;
import Business.Person.Person;
import Business.Role.Role;
import Business.UserAccount.UserAccount;
import java.awt.CardLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Rishika Idnani
 */
public class ManageUserAccount extends javax.swing.JPanel {

    /**
     * Creates new form ManageProvider
     */
    private JPanel userProcessContainer;
    private CDCEnterprise enterprise;
    private Network network;

    public ManageUserAccount(JPanel userProcessContainer, CDCEnterprise enterprise) {
        initComponents();
        this.userProcessContainer = userProcessContainer;
        this.enterprise = enterprise;
        this.network = enterprise.getNetwork();

        populateChooseComboBox();
        populateOrganizationComboBox();
        populateEnterpriseComboBox();
        populateUserAccountTable();
    }

    public void populateRoleComboBox(Organization o) {
        roleJComboBox.removeAllItems();
        if (o != null) {
            for (Role role : o.getSupportedRole()) {
                roleJComboBox.addItem(role);
            }
        }
    }

    public void populateChooseComboBox() {
        enterpriseOrgJComboBox.removeAllItems();

        enterpriseOrgJComboBox.addItem("Enterprise");
        enterpriseOrgJComboBox.addItem("Organization");
    }

    public void populateOrganizationComboBox() {
        organizationJComboBox.removeAllItems();

        for (Organization o : enterprise.getOrganizationDirectory().getOrganizationList()) {
            organizationJComboBox.addItem(o);
        }
    }

    public void populateEnterpriseComboBox() {
        Network n = enterprise.getNetwork();
        enterpriseJComboBox.removeAllItems();

        for (Enterprise enterprise : n.getEnterpriseDirectory().getEnterpriseList()) {
            if (enterprise instanceof PHDEnterprise
                    || enterprise instanceof VaccineManufacturerEnterprise) {
                enterpriseJComboBox.addItem(enterprise);
            }
        }
    }

    public void populateRoleComboBox(Enterprise enterprise) {
        roleJComboBox.removeAllItems();

        if (enterprise != null) {
            for (Role role : enterprise.getSupportedRole()) {
                roleJComboBox.addItem(role);
            }
        }
    }

    public void reset() {
        personNameJTextField.setText("");
        userNameJTextField.setText("");
        passwordJPasswordField.setText("");
        confirmPasswordJPasswordField.setText("");
        enterpriseJComboBox.setSelectedIndex(0);
        roleJComboBox.setSelectedIndex(0);

    }

    public void populateUserAccountTable() {
        DefaultTableModel model = (DefaultTableModel) userAccountJTable.getModel();
        model.setRowCount(0);

        Network network = enterprise.getNetwork();
        for (Enterprise e : network.getEnterpriseDirectory().getEnterpriseList()) {
            if (e instanceof PHDEnterprise
                    || e instanceof VaccineManufacturerEnterprise) {
                for (UserAccount ua : e.getUserAccountDirectory().getUserAccountList()) {
                    Object row[] = new Object[2];
                    row[0] = e;
                    row[1] = ua;
                    model.addRow(row);
                }
            }
        }

        for (Organization o : enterprise.getOrganizationDirectory().getOrganizationList()) {
            for (UserAccount ua : o.getUserAccountDirectory().getUserAccountList()) {
                Object row[] = new Object[2];
                row[0] = o;
                row[1] = ua;
                model.addRow(row);
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
        addUserAccountJLabel = new javax.swing.JLabel();
        userNameJLabel = new javax.swing.JLabel();
        passwordJLabel = new javax.swing.JLabel();
        confirmPasswordJLabel = new javax.swing.JLabel();
        roleJLabel = new javax.swing.JLabel();
        roleJComboBox = new javax.swing.JComboBox();
        confirmPasswordJPasswordField = new javax.swing.JPasswordField();
        passwordJPasswordField = new javax.swing.JPasswordField();
        userNameJTextField = new javax.swing.JTextField();
        resetJButton = new javax.swing.JButton();
        submitJButton = new javax.swing.JButton();
        function2JLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        userAccountJTable = new javax.swing.JTable();
        removeJButton = new javax.swing.JButton();
        backJButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        enterpriseJLabel = new javax.swing.JLabel();
        enterpriseJComboBox = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        personNameJLabel = new javax.swing.JLabel();
        personNameJTextField = new javax.swing.JTextField();
        organizationJLabel = new javax.swing.JLabel();
        organizationJComboBox = new javax.swing.JComboBox();
        enterpriseOrgJComboBox = new javax.swing.JComboBox();
        removeOrgJButton = new javax.swing.JButton();

        panelDescriptionJLabel.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        panelDescriptionJLabel.setText("Manage User Account");

        addUserAccountJLabel.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        addUserAccountJLabel.setText("Add User Account");

        userNameJLabel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        userNameJLabel.setText("User Name");

        passwordJLabel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        passwordJLabel.setText("Password");

        confirmPasswordJLabel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        confirmPasswordJLabel.setText("Confirm Password");

        roleJLabel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        roleJLabel.setText("Role");

        roleJComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        roleJComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        resetJButton.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        resetJButton.setText("Reset");
        resetJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetJButtonActionPerformed(evt);
            }
        });

        submitJButton.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        submitJButton.setText("Submit");
        submitJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitJButtonActionPerformed(evt);
            }
        });

        function2JLabel.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        function2JLabel.setText("Display of created User Accounts");

        userAccountJTable.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        userAccountJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Enterprise/Organization", "User Name"
            }
        ));
        userAccountJTable.setRowHeight(20);
        jScrollPane1.setViewportView(userAccountJTable);

        removeJButton.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        removeJButton.setText("Remove Enterprise Account");
        removeJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeJButtonActionPerformed(evt);
            }
        });

        backJButton.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        backJButton.setText("<< Back");
        backJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backJButtonActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel3.setText("Choose Enterprise / Organization");

        enterpriseJLabel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        enterpriseJLabel.setText("Enterprise");

        enterpriseJComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        enterpriseJComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        enterpriseJComboBox.setEnabled(false);
        enterpriseJComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enterpriseJComboBoxActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel1.setText("Add Person");

        personNameJLabel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        personNameJLabel.setText("Person Name");

        organizationJLabel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        organizationJLabel.setText("Organization");

        organizationJComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        organizationJComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        organizationJComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                organizationJComboBoxActionPerformed(evt);
            }
        });

        enterpriseOrgJComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        enterpriseOrgJComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enterpriseOrgJComboBoxActionPerformed(evt);
            }
        });

        removeOrgJButton.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        removeOrgJButton.setText("Remove Organization Account");
        removeOrgJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeOrgJButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 856, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panelDescriptionJLabel)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(enterpriseOrgJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel1)
                            .addComponent(resetJButton)
                            .addComponent(addUserAccountJLabel)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(104, 104, 104)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(personNameJLabel)
                                            .addGap(44, 44, 44)
                                            .addComponent(personNameJTextField))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(confirmPasswordJLabel)
                                                .addComponent(roleJLabel)
                                                .addComponent(userNameJLabel)
                                                .addComponent(passwordJLabel))
                                            .addGap(18, 18, 18)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(passwordJPasswordField, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                                .addComponent(confirmPasswordJPasswordField)
                                                .addComponent(roleJComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(userNameJTextField)))
                                        .addComponent(submitJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(enterpriseJLabel)
                                        .addGap(43, 43, 43)
                                        .addComponent(enterpriseJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(116, 116, 116)
                                        .addComponent(organizationJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(function2JLabel)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(383, 383, 383)
                                .addComponent(organizationJLabel)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(backJButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(removeOrgJButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(removeJButton, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelDescriptionJLabel)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(enterpriseOrgJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(enterpriseJLabel)
                    .addComponent(enterpriseJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(organizationJLabel)
                    .addComponent(organizationJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(personNameJLabel)
                    .addComponent(personNameJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(addUserAccountJLabel)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(userNameJLabel)
                    .addComponent(userNameJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passwordJLabel)
                    .addComponent(passwordJPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(confirmPasswordJLabel)
                    .addComponent(confirmPasswordJPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(roleJLabel)
                    .addComponent(roleJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(resetJButton)
                    .addComponent(submitJButton))
                .addGap(18, 18, 18)
                .addComponent(function2JLabel)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(removeOrgJButton)
                    .addComponent(backJButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(removeJButton)
                .addContainerGap(23, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void resetJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetJButtonActionPerformed
        // TODO add your handling code here:
        reset();
    }//GEN-LAST:event_resetJButtonActionPerformed

    private void submitJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitJButtonActionPerformed
        // TODO add your handling code here:
        Enterprise e = null;
        Organization o = null;
        if (enterpriseJComboBox.isEnabled() == true) {
            e = (Enterprise) enterpriseJComboBox.getSelectedItem();
        } else if (organizationJComboBox.isEnabled() == true) {
            o = (Organization) organizationJComboBox.getSelectedItem();
        }

        String userName = userNameJTextField.getText();
        String password = String.valueOf(passwordJPasswordField.getPassword());
        String confirmPassword = String.valueOf(confirmPasswordJPasswordField.getPassword());
        Role role = (Role) roleJComboBox.getSelectedItem();
        String personName = personNameJTextField.getText();

        if (personName.trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Enter the name!");
            return;
        }

        if (userName.trim().equals("")
                || password.trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Kindly fill proper inputs!");
            return;
        }

        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(null, "Password does not match!");
            return;
        }

        if (enterpriseJComboBox.isEnabled() == true) {

//Check at the enterprise level  
            for (Enterprise enterprise : network.getEnterpriseDirectory().getEnterpriseList()) {
                Boolean isUserNameUsed = enterprise.getUserAccountDirectory().checkUserAccountAvailability(userName);
                if (isUserNameUsed == true) {
                    JOptionPane.showMessageDialog(null, "User Name Already in use!");
                    return;
                }
            }

//Check at the organization level      
            for (Enterprise enterprise : network.getEnterpriseDirectory().getEnterpriseList()) {
                for (Organization org : enterprise.getOrganizationDirectory().getOrganizationList()) {
                    Boolean isUserNameUsed = org.getUserAccountDirectory().checkUserAccountAvailability(userName);
                    if (isUserNameUsed == true) {
                        JOptionPane.showMessageDialog(null, "User Name Already in use!");
                        return;
                    }
                }
            }

            int numberOfCreatedUserAccount = e.getUserAccountDirectory().getUserAccountList().size();

            if (numberOfCreatedUserAccount != 1) {
                Person person = e.getPersonDirectory().addAndCreatePerson(personName);
                UserAccount ua = e.getUserAccountDirectory().createAndAddUserAccount(userName, password, role, person);
            } else {
                JOptionPane.showMessageDialog(null, "User Account already created for the mentioned role and person!");
                return;
            }

        } else if (organizationJComboBox.isEnabled() == true) {

//Check at the enterprise level            
            for (Enterprise enterprise : network.getEnterpriseDirectory().getEnterpriseList()) {
                Boolean isUserNameUsed = enterprise.getUserAccountDirectory().checkUserAccountAvailability(userName);
                if (isUserNameUsed == true) {
                    JOptionPane.showMessageDialog(null, "User Name Already in use!");
                    return;
                }
            }

//Check at the organization level            
            for (Enterprise enterprise : network.getEnterpriseDirectory().getEnterpriseList()) {
                for (Organization org : enterprise.getOrganizationDirectory().getOrganizationList()) {
                    Boolean isUserNameUsed = org.getUserAccountDirectory().checkUserAccountAvailability(userName);
                    if (isUserNameUsed == true) {
                        JOptionPane.showMessageDialog(null, "User Name Already in use!");
                        return;
                    }
                }
            }

            Person person = o.getPersonDirectory().addAndCreatePerson(personName);
            if (person == null) {
                JOptionPane.showMessageDialog(null, "Person already exists!");
                return;
            }
            o.getUserAccountDirectory().createAndAddUserAccount(userName, password, role, person);
        }
        populateUserAccountTable();
        reset();
    }//GEN-LAST:event_submitJButtonActionPerformed

    private void removeJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeJButtonActionPerformed
        // TODO add your handling code here:
        int selectedRow = userAccountJTable.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(null, "Select a row to be deleted!");
            return;
        }
        Enterprise enterprise;
        try {
            enterprise = (Enterprise) userAccountJTable.getValueAt(selectedRow, 0);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Select an enterprise!");
            return;
        }
        UserAccount userAccount = (UserAccount) userAccountJTable.getValueAt(selectedRow, 1);
        enterprise.getUserAccountDirectory().removeUserAccount(userAccount);
        Person person = userAccount.getPerson();
        enterprise.getPersonDirectory().removePerson(person);
        populateUserAccountTable();
    }//GEN-LAST:event_removeJButtonActionPerformed

    private void backJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backJButtonActionPerformed
        // TODO add your handling code here:
        userProcessContainer.remove(this);
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.previous(userProcessContainer);
    }//GEN-LAST:event_backJButtonActionPerformed

    private void enterpriseJComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enterpriseJComboBoxActionPerformed
        // TODO add your handling code here:
        organizationJComboBox.setEnabled(false);
        Enterprise enterprise = (Enterprise) enterpriseJComboBox.getSelectedItem();

        if (enterprise != null) {
            populateRoleComboBox(enterprise);
        }
    }//GEN-LAST:event_enterpriseJComboBoxActionPerformed

    private void organizationJComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_organizationJComboBoxActionPerformed
        // TODO add your handling code here:
        enterpriseJComboBox.setEditable(false);

        Organization o = (Organization) organizationJComboBox.getSelectedItem();

        if (o != null) {
            populateRoleComboBox(o);
        }
    }//GEN-LAST:event_organizationJComboBoxActionPerformed

    private void enterpriseOrgJComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enterpriseOrgJComboBoxActionPerformed
        // TODO add your handling code here:
        String selectOption = String.valueOf(enterpriseOrgJComboBox.getSelectedItem());

        if (selectOption.equals("Enterprise")) {
            enterpriseJComboBox.setEnabled(true);
            organizationJComboBox.setEnabled(false);
        } else {
            organizationJComboBox.setEnabled(true);
            enterpriseJComboBox.setEnabled(false);
        }
    }//GEN-LAST:event_enterpriseOrgJComboBoxActionPerformed

    private void removeOrgJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeOrgJButtonActionPerformed
        // TODO add your handling code here:
        int selectedRow = userAccountJTable.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(null, "Select a row to be deleted!");
            return;
        }
        Boolean orgFound = false;
        Organization organization = (Organization) userAccountJTable.getValueAt(selectedRow, 0);
        for (Organization o : enterprise.getOrganizationDirectory().getOrganizationList()) {
            if (o.getName().equals(organization.getName())) {
                orgFound = true;
                break;
            }
        }

        if (orgFound == false) {
            JOptionPane.showMessageDialog(null, "Select an organization");
            return;
        }

        UserAccount userAccount = (UserAccount) userAccountJTable.getValueAt(selectedRow, 1);
        organization.getUserAccountDirectory().removeUserAccount(userAccount);
        Person person = userAccount.getPerson();
        organization.getPersonDirectory().removePerson(person);

        populateUserAccountTable();
    }//GEN-LAST:event_removeOrgJButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel addUserAccountJLabel;
    private javax.swing.JButton backJButton;
    private javax.swing.JLabel confirmPasswordJLabel;
    private javax.swing.JPasswordField confirmPasswordJPasswordField;
    private javax.swing.JComboBox enterpriseJComboBox;
    private javax.swing.JLabel enterpriseJLabel;
    private javax.swing.JComboBox enterpriseOrgJComboBox;
    private javax.swing.JLabel function2JLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox organizationJComboBox;
    private javax.swing.JLabel organizationJLabel;
    private javax.swing.JLabel panelDescriptionJLabel;
    private javax.swing.JLabel passwordJLabel;
    private javax.swing.JPasswordField passwordJPasswordField;
    private javax.swing.JLabel personNameJLabel;
    private javax.swing.JTextField personNameJTextField;
    private javax.swing.JButton removeJButton;
    private javax.swing.JButton removeOrgJButton;
    private javax.swing.JButton resetJButton;
    private javax.swing.JComboBox roleJComboBox;
    private javax.swing.JLabel roleJLabel;
    private javax.swing.JButton submitJButton;
    private javax.swing.JTable userAccountJTable;
    private javax.swing.JLabel userNameJLabel;
    private javax.swing.JTextField userNameJTextField;
    // End of variables declaration//GEN-END:variables
}
