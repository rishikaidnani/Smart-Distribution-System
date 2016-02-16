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
import Business.Enterprise.VaccineManufacturerEnterprise;
import Business.Network.Network;
import Business.Organization.Distributor.DistributionCentre;
import Business.Organization.Organization;
import Business.UserAccount.UserAccount;
import UserInterface.Distributor.DistributionCentreWorkArea;
import javax.swing.JPanel;

/**
 *
 * @author Rishika Idnani
 */
public class DistributorCentreRole extends Role {

    private CDCEnterprise cdcEnterprise;

    @Override
    public JPanel createWorkArea(JPanel userProcessContainer, Network network, Enterprise enterprise, Organization organization, UserAccount userAccount, Ecosystem system) {
       // for (Network network : system.getNetworkDirectory().getNetworkList()) {
            for (Enterprise e : network.getEnterpriseDirectory().getEnterpriseList()) {
                if (e instanceof CDCEnterprise) {
                    cdcEnterprise = (CDCEnterprise) e;
                    break;
                }
            }
      //  }

        NationalDistributorEnterprise ndEnterprise = (NationalDistributorEnterprise) enterprise;
        DistributionCentre o = (DistributionCentre) organization;
        return new DistributionCentreWorkArea(userProcessContainer, ndEnterprise, cdcEnterprise, o);
    }

}
