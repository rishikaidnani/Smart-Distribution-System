/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInterface.Distributor;

import Business.Enterprise.Enterprise;
import Business.Enterprise.NationalDistributorEnterprise;
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
     * Creates new form ManageUserAccount
     */
    private JPanel userProcessContainer;
    private NationalDistributorEnterprise nationalDistributorEnterprise;
    private Network network;

    public ManageUserAccount(JPanel userProcessContainer, NationalDistributorEnterprise nationalDistributorEnterprise, Network network) {
        initComponents();
        this.userProcessContainer = userProcessContainer;
        this.nationalDistributorEnterprise = nationalDistributorEnterprise;
        this.network = network;

        populateOrganizationComboBox();
        populateUserAccountTable();
    }

    public void populateOrganizationComboBox() {
        organizationJComboBox.removeAllItems();

        for (Organization o : nationalDistributorEnterprise.getOrganizationDirectory().getOrganizationList()) {
            organizationJComboBox.addItem(o);
        }
    }

    public void populateUserAccountTable() {
        DefaultTableModel model = (DefaultTableModel) userAccountJTable.getModel();
        model.setRowCount(0);

        for (Organization o : nationalDistributorEnterprise.getOrganizationDirectory().getOrganizationList()) {

            for (UserAccount ua : o.getUserAccountDirectory().getUserAccountList()) {
                Object row[] = new Object[2];
                row[0] = o;
                row[1] = ua;
                model.addRow(row);
            }
        }
    }

    public void reset() {
        personNameJTextField.setText("");
        userNameJTextField.setText("");
        passwordJPasswordField.setText("");
        confirmPasswordJPasswordField.setText("");
        organizationJComboBox.setSelectedIndex(0);
        roleJComboBox.setSelectedIndex(0);
    }

    public void populateRoleComboBox(Organization o) {
        roleJComboBox.removeAllItems();

        for (Role role : o.getSupportedRole()) {
            roleJComboBox.addItem(role);
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

        passwordJPasswordField = new javax.swing.JPasswordField();
        userNameJTextField = new javax.swing.JTextField();
        addUserAccountJLabel = new javax.swing.JLabel();
        roleJComboBox = new javax.swing.JComboBox();
        personNameJTextField = new javax.swing.JTextField();
        confirmPasswordJLabel = new javax.swing.JLabel();
        backJButton = new javax.swing.JButton();
        resetJButton2 = new javax.swing.JButton();
        organizationJLabel = new javax.swing.JLabel();
        removeJButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        userAccountJTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        submitJButton = new javax.swing.JButton();
        userNameJLabel = new javax.swing.JLabel();
        function2JLabel = new javax.swing.JLabel();
        passwordJLabel = new javax.swing.JLabel();
        panelDescriptionJLabel = new javax.swing.JLabel();
        organizationJComboBox = new javax.swing.JComboBox();
        chooseOJLabel = new javax.swing.JLabel();
        confirmPasswordJPasswordField = new javax.swing.JPasswordField();
        roleJLabel = new javax.swing.JLabel();
        personNameJLabel = new javax.swing.JLabel();

        addUserAccountJLabel.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        addUserAccountJLabel.setText("Add User Account");

        roleJComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        roleJComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        confirmPasswordJLabel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        confirmPasswordJLabel.setText("Confirm Password");

        backJButton.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        backJButton.setText("<< Back");
        backJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backJButtonActionPerformed(evt);
            }
        });

        resetJButton2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        resetJButton2.setText("Reset");
        resetJButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetJButton2ActionPerformed(evt);
            }
        });

        organizationJLabel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        organizationJLabel.setText("Organization");

        removeJButton.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        removeJButton.setText("Remove User Account");
        removeJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeJButtonActionPerformed(evt);
            }
        });

        userAccountJTable.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        userAccountJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Organization name", "User Name"
            }
        ));
        userAccountJTable.setRowHeight(20);
        jScrollPane1.setViewportView(userAccountJTable);

        jLabel1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel1.setText("Add Person");

        submitJButton.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        submitJButton.setText("Submit");
        submitJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitJButtonActionPerformed(evt);
            }
        });

        userNameJLabel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        userNameJLabel.setText("User Name");

        function2JLabel.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        function2JLabel.setText("Display of created User Accounts");

        passwordJLabel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        passwordJLabel.setText("Password");

        panelDescriptionJLabel.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        panelDescriptionJLabel.setText("Manage Site Organization Account");

        organizationJComboBox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        organizationJComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        organizationJComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                organizationJComboBoxActionPerformed(evt);
            }
        });

        chooseOJLabel.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        chooseOJLabel.setText("Choose Organization");

        roleJLabel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        roleJLabel.setText("Role");

        personNameJLabel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        personNameJLabel.setText("Person Name");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(backJButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(removeJButton))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 629, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(function2JLabel)
                            .addComponent(panelDescriptionJLabel)
                            .addComponent(chooseOJLabel)
                            .addComponent(addUserAccountJLabel)
                            .addComponent(resetJButton2)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(104, 104, 104)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(userNameJLabel)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(submitJButton)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(passwordJLabel)
                                                .addComponent(confirmPasswordJLabel)
                                                .addComponent(roleJLabel))
                                            .addGap(18, 18, 18)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(roleJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                    .addComponent(confirmPasswordJPasswordField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
                                                    .addComponent(passwordJPasswordField, javax.swing.GroupLayout.Alignment.LEADING)))))))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(227, 227, 227)
                                    .addComponent(userNameJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addGap(40, 40, 40)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(personNameJLabel)
                                            .addGap(44, 44, 44)
                                            .addComponent(personNameJTextField))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(organizationJLabel)
                                            .addGap(52, 52, 52)
                                            .addComponent(organizationJComboBox, 0, 150, Short.MAX_VALUE))))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelDescriptionJLabel)
                .addGap(26, 26, 26)
                .addComponent(chooseOJLabel)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(organizationJLabel)
                    .addComponent(organizationJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(personNameJLabel)
                            .addComponent(personNameJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
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
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(resetJButton2)
                    .addComponent(submitJButton))
                .addGap(24, 24, 24)
                .addComponent(function2JLabel)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(removeJButton)
                    .addComponent(backJButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void backJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backJButtonActionPerformed
        // TODO add your handling code here:
        userProcessContainer.remove(this);
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.previous(userProcessContainer);
    }//GEN-LAST:event_backJButtonActionPerformed

    private void resetJButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetJButton2ActionPerformed
        // TODO add your handling code here:
        reset();
    }//GEN-LAST:event_resetJButton2ActionPerformed

    private void removeJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeJButtonActionPerformed
        // TODO add your handling code here:
        int selectedRow = userAccountJTable.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(null, "Select a row to be deleted!");
            return;
        }

        Organization organization = (Organization) userAccountJTable.getValueAt(selectedRow, 0);
        UserAccount userAccount = (UserAccount) userAccountJTable.getValueAt(selectedRow, 1);
        Person person = userAccount.getPerson();
        organization.getPersonDirectory().removePerson(person);
        organization.getUserAccountDirectory().removeUserAccount(userAccount);
        populateUserAccountTable();
    }//GEN-LAST:event_removeJButtonActionPerformed

    private void submitJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitJButtonActionPerformed
        // TODO add your handling code here:
        Organization o = (Organization) organizationJComboBox.getSelectedItem();

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

        Organization org = (Organization) organizationJComboBox.getSelectedItem();

        Person person = org.getPersonDirectory().addAndCreatePerson(personName);
        UserAccount userAccount = org.getUserAccountDirectory().createAndAddUserAccount(userName, password, role, person);
        if (userAccount == null) {
            JOptionPane.showMessageDialog(null, "User Account for the person exists!");
            return;
        }
        populateUserAccountTable();
        reset();
    }//GEN-LAST:event_submitJButtonActionPerformed

    private void organizationJComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_organizationJComboBoxActionPerformed
        // TODO add your handling code here:
        Organization o = (Organization) organizationJComboBox.getSelectedItem();

        if (o != null) {
            populateRoleComboBox(o);
        }
    }//GEN-LAST:event_organizationJComboBoxActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel addUserAccountJLabel;
    private javax.swing.JButton backJButton;
    private javax.swing.JLabel chooseOJLabel;
    private javax.swing.JLabel confirmPasswordJLabel;
    private javax.swing.JPasswordField confirmPasswordJPasswordField;
    private javax.swing.JLabel function2JLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox organizationJComboBox;
    private javax.swing.JLabel organizationJLabel;
    private javax.swing.JLabel panelDescriptionJLabel;
    private javax.swing.JLabel passwordJLabel;
    private javax.swing.JPasswordField passwordJPasswordField;
    private javax.swing.JLabel personNameJLabel;
    private javax.swing.JTextField personNameJTextField;
    private javax.swing.JButton removeJButton;
    private javax.swing.JButton resetJButton2;
    private javax.swing.JComboBox roleJComboBox;
    private javax.swing.JLabel roleJLabel;
    private javax.swing.JButton submitJButton;
    private javax.swing.JTable userAccountJTable;
    private javax.swing.JLabel userNameJLabel;
    private javax.swing.JTextField userNameJTextField;
    // End of variables declaration//GEN-END:variables
}