/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Enterprise;

import Business.Network.Network;
import Business.Organization.Organization;
import Business.Organization.OrganizationDirectory;

/**
 *
 * @author Rishika Idnani
 */
public abstract class Enterprise extends Organization {

    private OrganizationDirectory organizationDirectory;
    private Enterprise.TypeOfEnterprise typeOfEnterprise;

    public Enterprise() {
        super(null);
        this.organizationDirectory = new OrganizationDirectory();
    }

    public enum TypeOfEnterprise {

        PHD("Public Health Department"),
        CDC("Centre for Disease Control"),
        NationalDistributor("National Distributor"),
        VaccineManufacturer("Vaccine Manufacturer"),
        Site("Site Enterprise");

        private String value;

        private TypeOfEnterprise(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    public OrganizationDirectory getOrganizationDirectory() {
        return organizationDirectory;
    }

    public TypeOfEnterprise getTypeOfEnterprise() {
        return typeOfEnterprise;
    }

    public void setTypeOfEnterprise(TypeOfEnterprise typeOfEnterprise) {
        this.typeOfEnterprise = typeOfEnterprise;
    }
}
