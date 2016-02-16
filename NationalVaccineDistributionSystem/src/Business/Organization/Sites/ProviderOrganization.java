/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Organization.Sites;

import Business.Organization.Organization;
import Business.Role.ProviderRole;
import Business.Role.Role;
import java.util.ArrayList;

/**
 *
 * @author Rishika Idnani
 */
public class ProviderOrganization extends Organization {

    public ProviderOrganization() {
        super(null);
    }

    @Override
    public ArrayList<Role> getSupportedRole() {
        ArrayList<Role> roleList = new ArrayList<>();
        roleList.add(new ProviderRole());
        return roleList;
    }
}
