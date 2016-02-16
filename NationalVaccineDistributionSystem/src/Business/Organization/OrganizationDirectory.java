/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Organization;

import Business.Organization.CDC.CDCOperationsOrganization;
import Business.Organization.Distributor.DistributionCentre;
import Business.Organization.PHD.PhdOperationsOrganization;
import Business.Organization.Sites.ProviderOrganization;
import Business.Organization.Sites.SiteOperationOrganization;
import java.util.ArrayList;

/**
 *
 * @author Rishika Idnani
 */
public class OrganizationDirectory {

    private ArrayList<Organization> organizationList;

    public OrganizationDirectory() {
        this.organizationList = new ArrayList<>();
    }

    public ArrayList<Organization> getOrganizationList() {
        return organizationList;
    }

    public void setOrganizationList(ArrayList<Organization> organizationList) {
        this.organizationList = organizationList;
    }

    public Organization addOrganization(Organization.TypeOfOrganization type, String organizationName) {
        Organization organization = null;

        for (Organization o : organizationList) {
            if (o.getType() == type || o.getName().equalsIgnoreCase(organizationName)) {
                return null;
            }
        }

        if (type.getValue().equals(Organization.TypeOfOrganization.SiteOperation.getValue())) {
            organization = new SiteOperationOrganization();
        } else if (type.getValue().equals(Organization.TypeOfOrganization.CdcOperations.getValue())) {
            organization = new CDCOperationsOrganization();
        } else if (type.getValue().equals(Organization.TypeOfOrganization.PhdOperations.getValue())) {
            organization = new PhdOperationsOrganization();
        } else if (type.getValue().equals(Organization.TypeOfOrganization.ProviderOrganization.getValue())) {
            organization = new ProviderOrganization();
        }
        organization.setName(organizationName);
        organization.setType(type);
        organizationList.add(organization);
        return organization;
    }

    public DistributionCentre addDistributionCentre(Organization.TypeOfOrganization type, String stateName, int pinCode, String organizationName) {

        for (Organization organization : organizationList) {
            if (organization instanceof DistributionCentre) {
                DistributionCentre o = (DistributionCentre) organization;
                if (o.getName().equalsIgnoreCase(organizationName)) {
                    return null;
                }
                if (o.getStateName().equals(stateName)
                        && o.getPinCode() == pinCode) {
                    return null;
                }
            }
        }

        DistributionCentre organization = new DistributionCentre();

        organization.setName(organizationName);
        organization.setType(type);
        organization.setStateName(stateName);
        organization.setPinCode(pinCode);
        organizationList.add(organization);
        return organization;
    }

    public void removeOrganization(Organization organization) {
        organizationList.remove(organization);
    }
}
