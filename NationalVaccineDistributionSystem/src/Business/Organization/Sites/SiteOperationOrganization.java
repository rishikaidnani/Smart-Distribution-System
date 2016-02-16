/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Organization.Sites;

import Business.Order.OrderItem;
import Business.Organization.Organization;
import Business.Role.Role;
import Business.Role.SiteOperationRole;
import Business.Vaccine.Vaccine;
import java.util.ArrayList;

/**
 *
 * @author Rishika Idnani
 */
public class SiteOperationOrganization extends Organization {

    private ArrayList<OrderItem> vaccineInventory;

    public SiteOperationOrganization() {
        super(Organization.TypeOfOrganization.SiteOperation.getValue());
        vaccineInventory = new ArrayList<>();
    }

    public ArrayList<OrderItem> getVaccineInventory() {
        return vaccineInventory;
    }

    public void setVaccineInventory(ArrayList<OrderItem> vaccineInventory) {
        this.vaccineInventory = vaccineInventory;
    }

    @Override
    public ArrayList<Role> getSupportedRole() {
        ArrayList<Role> roleList = new ArrayList<>();
        roleList.add(new SiteOperationRole());
        return roleList;
    }

}
