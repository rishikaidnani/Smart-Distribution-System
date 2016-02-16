/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Role;

import Business.Ecosystem;
import Business.Enterprise.CDCEnterprise;
import Business.Enterprise.Enterprise;
import Business.Enterprise.NationalDistributorEnterprise;
import Business.Network.Network;
import Business.Organization.Organization;
import Business.UserAccount.UserAccount;
import UserInterface.Distributor.DistributorWorkArea;
import javax.swing.JPanel;

/**
 *
 * @author Rishika Idnani
 */
public class NationalDistributorAdminRole extends Role {

    private CDCEnterprise cdcEnterprise = null;
    private NationalDistributorEnterprise nationalDistributorEnterprise = null;

    @Override
    public JPanel createWorkArea(JPanel userProcessContainer, Network network, Enterprise enterprise, Organization organization, UserAccount userAccount, Ecosystem system) {
//        for (Network network : system.getNetworkDirectory().getNetworkList()) {
            for (Enterprise e : network.getEnterpriseDirectory().getEnterpriseList()) {
                if (e instanceof CDCEnterprise) {
                    cdcEnterprise = (CDCEnterprise) e;
                    break;
                }
            }
        //}

        nationalDistributorEnterprise = (NationalDistributorEnterprise) enterprise;

        return new DistributorWorkArea(userProcessContainer, nationalDistributorEnterprise, cdcEnterprise, network);
    }
}
