/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Enterprise;

import Business.Network.Network;
import java.util.ArrayList;

/**
 *
 * @author Rishika Idnani
 */
public class EnterpriseDirectory {

    private ArrayList<Enterprise> enterpriseList;

    public EnterpriseDirectory() {
        this.enterpriseList = new ArrayList<>();
    }

    public ArrayList<Enterprise> getEnterpriseList() {
        return enterpriseList;
    }

    public Enterprise addAndCreateEnterprise(Enterprise.TypeOfEnterprise type, String name, Network network) {
        Enterprise enterprise = null;
        if (type == Enterprise.TypeOfEnterprise.VaccineManufacturer) {
            for (Enterprise e : enterpriseList) {
                if (e instanceof VaccineManufacturerEnterprise) {
                    if (e.getName().equalsIgnoreCase(name)) {
                        return null;
                    }
                }
            }
        } else {
            for (Enterprise e : enterpriseList) {
                if (e.getTypeOfEnterprise() == type
                        || e.getName().equals(name)) {
                    return null;
                }
            }
        }

        if (type.getValue().equals(Enterprise.TypeOfEnterprise.CDC.getValue())) {
            enterprise = new CDCEnterprise(network);

        } else if (type.getValue().equals(Enterprise.TypeOfEnterprise.NationalDistributor.getValue())) {
            enterprise = new NationalDistributorEnterprise();

        } else if (type.getValue().equals(Enterprise.TypeOfEnterprise.VaccineManufacturer.getValue())) {
            enterprise = new VaccineManufacturerEnterprise();
        }
        enterpriseList.add(enterprise);
        enterprise.setName(name);
        enterprise.setTypeOfEnterprise(type);
        return enterprise;
    }

    public SiteEnterprise addAndCreateSiteEnterprise(String siteType, String name, String stateName, int pinCode) {
        for (Enterprise e : enterpriseList) {
            if (e.getName().equalsIgnoreCase(name)) {
                return null;
            }
        }

        SiteEnterprise enterprise = new SiteEnterprise();
        enterpriseList.add(enterprise);

        enterprise.setStateName(stateName);
        enterprise.setName(name);
        enterprise.setTypeOfSite(siteType);
        enterprise.setPinCode(pinCode);

        return enterprise;
    }

    public Enterprise addAndCreateEnterprise(Enterprise.TypeOfEnterprise type, String name, Network network, String stateName) {

        if (type == Enterprise.TypeOfEnterprise.PHD) {
            for (Enterprise e : enterpriseList) {
                if (e instanceof PHDEnterprise) {
                    PHDEnterprise phdEnterprise = (PHDEnterprise) e;
                    if (phdEnterprise.getStateName().equalsIgnoreCase(stateName)
                            || phdEnterprise.getName().equalsIgnoreCase(name)) {
                        return null;
                    }
                }
            }
        }

        if (type.getValue().equals(Enterprise.TypeOfEnterprise.PHD.getValue())) {
            PHDEnterprise enterprise = new PHDEnterprise(stateName, network);
            enterpriseList.add(enterprise);
            enterprise.setName(name);
            enterprise.setTypeOfEnterprise(type);
            return enterprise;
        }
        return null;
    }

    public Boolean removeEnterprise(String name) {
        for (Enterprise enterprise : enterpriseList) {
            if (enterprise.getName().equals(name)) {
                enterpriseList.remove(enterprise);
                return true;
            }
        }
        return null;
    }
    
    public void removeEnterpriseObject(Enterprise enterprise){
        enterpriseList.remove(enterprise);
    }

}
