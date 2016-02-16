/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Role;

import Business.Ecosystem;
import Business.Enterprise.CDCEnterprise;
import Business.Enterprise.Enterprise;
import Business.Enterprise.SiteEnterprise;
import Business.Network.Network;
import Business.Organization.Organization;
import Business.UserAccount.UserAccount;
import UserInterface.Sites.SiteWorkArea;
import javax.swing.JPanel;

/**
 *
 * @author Rishika Idnani
 */
public class SiteAdmin extends Role {

    private CDCEnterprise cdcEnterprise = null;
    private SiteEnterprise siteEnterprise = null;

    @Override
    public JPanel createWorkArea(JPanel userProcessContainer, Network network, Enterprise enterprise, Organization organization, UserAccount userAccount, Ecosystem system) {

        for (Enterprise e : network.getEnterpriseDirectory().getEnterpriseList()) {
            if (e instanceof CDCEnterprise) {
                cdcEnterprise = (CDCEnterprise) e;
                break;
            }
        }
        siteEnterprise = (SiteEnterprise) enterprise;
        return new SiteWorkArea(userProcessContainer, cdcEnterprise, siteEnterprise, network);
    }
}
