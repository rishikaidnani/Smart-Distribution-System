/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Role;

import Business.Ecosystem;
import Business.Enterprise.CDCEnterprise;
import Business.Enterprise.Enterprise;
import Business.Network.Network;
import Business.Organization.Organization;
import Business.UserAccount.UserAccount;
import UserInterface.CentreForDiseaseControl.CDCWorkArea;
import javax.swing.JPanel;

/**
 *
 * @author Rishika Idnani
 */
public class CDCAdminRole extends Role {

    @Override
    public JPanel createWorkArea(JPanel userProcessContainer, Network network, Enterprise enterprise, Organization organization, UserAccount userAccount, Ecosystem system) {
        return new CDCWorkArea(userProcessContainer, (CDCEnterprise) enterprise);
    }

}
