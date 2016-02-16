/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Role;

import Business.Ecosystem;
import Business.Enterprise.CDCEnterprise;
import Business.Enterprise.Enterprise;
import Business.Enterprise.PHDEnterprise;
import Business.Network.Network;
import Business.Organization.Organization;
import Business.Organization.PHD.PhdOperationsOrganization;
import Business.UserAccount.UserAccount;
import UserInterface.PublicHealthDepartment.PhdOperationsWorkArea;
import javax.swing.JPanel;

/**
 *
 * @author Rishika Idnani
 */
public class PhdOperationsRole extends Role {

    private CDCEnterprise cdcEnterprise = null;

    @Override
    public JPanel createWorkArea(JPanel userProcessContainer, Network network, Enterprise enterprise, Organization organization, UserAccount userAccount, Ecosystem system) {
        PHDEnterprise e = (PHDEnterprise) enterprise;
        PhdOperationsOrganization o = (PhdOperationsOrganization) organization;
        
        //for (Network network : system.getNetworkDirectory().getNetworkList()) {
            for (Enterprise en : network.getEnterpriseDirectory().getEnterpriseList()) {
                if (en instanceof CDCEnterprise) {
                    cdcEnterprise = (CDCEnterprise) en;
                    break;
          //      }
            }
        }
        return new PhdOperationsWorkArea(userProcessContainer, e, o, userAccount, cdcEnterprise);
    }

}
