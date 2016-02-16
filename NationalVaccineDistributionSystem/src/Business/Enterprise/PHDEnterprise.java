/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Enterprise;

import Business.Network.Network;
import Business.Role.PHDAdminRole;
import Business.Role.Role;
import Business.Vaccine.Vaccine;
import java.util.ArrayList;

/**
 *
 * @author Rishika Idnani
 */
public class PHDEnterprise extends Enterprise {

    private Network network;
    private String stateName;
    private ArrayList<SiteEnterprise> siteList;
    private float allocatedPercentile;
    private float allocatedQuantity;

    public PHDEnterprise(String stateName, Network network) {
        this.siteList = new ArrayList<>();
        this.stateName = stateName;
        this.network = network;
    }

    public String getStateName() {
        return stateName;
    }

    public Network getNetwork() {
        return network;
    }

    public void setNetwork(Network network) {
        this.network = network;
    }

    public ArrayList<SiteEnterprise> getSiteList() {
        return siteList;
    }

    public void setSiteList(ArrayList<SiteEnterprise> siteList) {
        this.siteList = siteList;
    }

    public float setAllocatedPercentileForState(String stateName) {
        if (stateName.equals("California")) {
            allocatedPercentile = 20;
        } else if (stateName.equals("Florida")) {
            allocatedPercentile = 10;
        } else if (stateName.equals("Illinois")) {
            allocatedPercentile = 5;
        } else if (stateName.equals("Maryland")) {
            allocatedPercentile = 5;
        } else if (stateName.equals("Massachusetts")) {
            allocatedPercentile = 30;
        } else if (stateName.equals("New York")) {
            allocatedPercentile = 30;
        }
        return allocatedPercentile;
    }

    public float getAllocatedPercentile() {
        return allocatedPercentile;
    }

    public void setAllocatedPercentile(float allocatedPercentile) {
        this.allocatedPercentile = allocatedPercentile;
    }

    public float getAllocatedQuantity() {
        return allocatedQuantity;
    }

    public void setAllocatedQuantity(float allocatedQuantity) {
        this.allocatedQuantity = allocatedQuantity;
    }

    @Override
    public ArrayList<Role> getSupportedRole() {
        ArrayList<Role> roleList = new ArrayList<>();
        roleList.add(new PHDAdminRole());
        return roleList;
    }

}
