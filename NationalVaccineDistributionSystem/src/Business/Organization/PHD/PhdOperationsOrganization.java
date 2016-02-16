/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Organization.PHD;

import Business.Organization.Organization;
import Business.Role.PhdOperationsRole;
import Business.Role.Role;
import java.util.ArrayList;

/**
 *
 * @author Rishika Idnani
 */
public class PhdOperationsOrganization extends Organization {

    public PhdOperationsOrganization() {
        super(Organization.TypeOfOrganization.PhdOperations.getValue());
    }

    @Override
    public ArrayList<Role> getSupportedRole() {
        ArrayList<Role> roleList = new ArrayList<>();
        roleList.add(new PhdOperationsRole());
        return roleList;
    }

}
