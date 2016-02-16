/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Role;

import Business.Ecosystem;
import Business.Enterprise.CDCEnterprise;
import Business.Enterprise.Enterprise;
import Business.Enterprise.VaccineManufacturerEnterprise;
import Business.Network.Network;
import Business.Organization.Organization;
import Business.UserAccount.UserAccount;
import UserInterface.VaccineManufacturer.AddVaccines;
import UserInterface.VaccineManufacturer.VaccineManufacturerWorkArea;
import javax.swing.JPanel;

/**
 *
 * @author Rishika Idnani
 */
public class VaccineManufacturerAdminRole extends Role {

    private CDCEnterprise cdcEnterprise = null;
    private VaccineManufacturerEnterprise vaccineEnterprise = null;

    @Override
    public JPanel createWorkArea(JPanel userProcessContainer, Network network, Enterprise enterprise, Organization organization, UserAccount userAccount, Ecosystem system) {

       // for (Network network : system.getNetworkDirectory().getNetworkList()) {
            for (Enterprise e : network.getEnterpriseDirectory().getEnterpriseList()) {
                if (e instanceof CDCEnterprise) {
                    cdcEnterprise = (CDCEnterprise) e;
                    break;
                }
          //  }
        }

        vaccineEnterprise = (VaccineManufacturerEnterprise) enterprise;
        return new VaccineManufacturerWorkArea(userProcessContainer, vaccineEnterprise, cdcEnterprise);
    }

}
