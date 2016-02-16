/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Organization.Distributor;

import Business.Organization.Organization;
import Business.Role.DistributorCentreRole;
import Business.Role.Role;
import java.util.ArrayList;

/**
 *
 * @author Rishika Idnani
 */
public class DistributionCentre extends Organization {

    private String stateName;
    private int pinCode;

    public DistributionCentre() {
        super(null);
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public int getPinCode() {
        return pinCode;
    }

    public void setPinCode(int pinCode) {
        this.pinCode = pinCode;
    }

    @Override
    public ArrayList<Role> getSupportedRole() {
        ArrayList<Role> roleList = new ArrayList<>();
        roleList.add(new DistributorCentreRole());
        return roleList;
    }

}
