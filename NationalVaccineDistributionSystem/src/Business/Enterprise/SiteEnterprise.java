/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Enterprise;

import Business.Order.Order;
import Business.Role.Role;
import Business.Role.SiteAdmin;
import java.util.ArrayList;

/**
 *
 * @author Rishika Idnani
 */
public class SiteEnterprise extends Enterprise {

    private String typeOfSite;
    private String stateName;
    private ArrayList<Order> orderList;
    private int pinCode;

    public SiteEnterprise() {
        orderList = new ArrayList<>();
    }

    public String getTypeOfSite() {
        return typeOfSite;
    }

    public void setTypeOfSite(String typeOfSite) {
        this.typeOfSite = typeOfSite;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public ArrayList<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(ArrayList<Order> orderList) {
        this.orderList = orderList;
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
        roleList.add(new SiteAdmin());
        return roleList;
    }
}
