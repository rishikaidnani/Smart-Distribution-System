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
import javax.swing.JPanel;

/**
 *
 * @author Rishika Idnani
 */
public abstract class Role {

    public enum TypeOfRole {

        Admin("Admin Role"),
        CDCAdmin("CDC Admin Role"),
        NationalDistributorAdmin("National Distributor Admin Role"),
        SiteAdmin("SiteAdminRole");

        private String value;

        private TypeOfRole(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

    }

    public abstract JPanel createWorkArea(JPanel userProcessContainer, Network network, Enterprise enterprise, Organization organization, 
            UserAccount userAccount, Ecosystem system);

    public String toString() {
        return this.getClass().getSimpleName();

    }
}
