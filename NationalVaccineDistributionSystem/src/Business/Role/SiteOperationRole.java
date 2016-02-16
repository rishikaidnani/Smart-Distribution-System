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
import Business.Enterprise.SiteEnterprise;
import Business.Network.Network;
import Business.Organization.Distributor.DistributionCentre;
import Business.Organization.Organization;
import Business.Organization.Sites.SiteOperationOrganization;
import Business.UserAccount.UserAccount;
import UserInterface.Sites.SiteOperationsWorkArea;
import javax.swing.JPanel;

/**
 *
 * @author Rishika Idnani
 */
public class SiteOperationRole extends Role {

    private SiteOperationOrganization siteOperationOrganization = null;
    private CDCEnterprise cdcEnterprise = null;
    private SiteEnterprise siteEnterprise = null;
    private NationalDistributorEnterprise nde = null;

    @Override
    public JPanel createWorkArea(JPanel userProcessContainer, Network network, Enterprise enterprise, Organization organization, UserAccount userAccount, Ecosystem system) {
        siteOperationOrganization = (SiteOperationOrganization) organization;

        for (Enterprise e : network.getEnterpriseDirectory().getEnterpriseList()) {
            if (e instanceof CDCEnterprise) {
                cdcEnterprise = (CDCEnterprise) e;
                break;
            }
        }
        siteEnterprise = (SiteEnterprise) enterprise;

        for (Enterprise e : network.getEnterpriseDirectory().getEnterpriseList()) {
            if (e instanceof NationalDistributorEnterprise) {
                nde = (NationalDistributorEnterprise) e;
            }
        }
        return new SiteOperationsWorkArea(userProcessContainer, siteOperationOrganization, cdcEnterprise, siteEnterprise, nde);
    }

}
