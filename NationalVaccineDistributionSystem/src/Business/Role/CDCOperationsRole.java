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
import Business.Organization.CDC.CDCOperationsOrganization;
import Business.Organization.Distributor.DistributionCentre;
import Business.Organization.Organization;
import Business.UserAccount.UserAccount;
import UserInterface.CentreForDiseaseControl.CDCOperationsWorkArea;
import javax.swing.JPanel;

/**
 *
 * @author Rishika Idnani
 */
public class CDCOperationsRole extends Role {

    @Override
    public JPanel createWorkArea(JPanel userProcessContainer, Network network, Enterprise enterprise, Organization organization, UserAccount userAccount, Ecosystem system) {
        CDCEnterprise en = (CDCEnterprise) enterprise;
        CDCOperationsOrganization or = (CDCOperationsOrganization) organization;

        return new CDCOperationsWorkArea(userProcessContainer, en, or, userAccount);
    }

}
