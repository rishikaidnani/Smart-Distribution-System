/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Role;

import Business.Ecosystem;
import Business.Enterprise.Enterprise;
import Business.Network.Network;
import Business.Organization.Organization;
import Business.UserAccount.UserAccount;
import UserInterface.SystemAdministration.SystemAdminWorkArea;
import javax.swing.JPanel;

/**
 *
 * @author Rishika Idnani
 */
public class SystemAdminRole extends Role {

    @Override
    public JPanel createWorkArea(JPanel userProcessContainer, Network network, Enterprise enterprise, Organization organization, UserAccount userAccount, Ecosystem system) {
        return new SystemAdminWorkArea(userProcessContainer, system);
    }

}
