/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Enterprise;

import Business.Network.Network;
import Business.Order.MasterOrderCatalog;
import Business.Role.CDCAdminRole;
import Business.Role.Role;
import Business.Vaccine.DiseaseCatalog;
import java.util.ArrayList;

/**
 *
 * @author Rishika Idnani
 */
public class CDCEnterprise extends Enterprise {

    private Network network;
    private ArrayList<PHDEnterprise> phdList;
    private ArrayList<VaccineManufacturerEnterprise> vaccineManufacturerList;
    private DiseaseCatalog diseaseCatalog;
    private MasterOrderCatalog masterOrderCatalog;

    public CDCEnterprise(Network network) {
        this.phdList = new ArrayList<>();
        this.vaccineManufacturerList = new ArrayList<>();
        this.network = network;
        this.diseaseCatalog = new DiseaseCatalog();
        this.masterOrderCatalog = new MasterOrderCatalog();
        setNetwork(network);
    }

    public MasterOrderCatalog getMasterOrderCatalog() {
        return masterOrderCatalog;
    }

    public void setMasterOrderCatalog(MasterOrderCatalog masterOrderCatalog) {
        this.masterOrderCatalog = masterOrderCatalog;
    }

    public Network getNetwork() {
        return network;
    }

    public void setNetwork(Network network) {
        this.network = network;
    }

    public ArrayList<PHDEnterprise> getPhdList() {
        return phdList;
    }

    public ArrayList<VaccineManufacturerEnterprise> getVaccineManufacturerList() {
        return vaccineManufacturerList;
    }

    public void setVaccineManufacturerList(ArrayList<VaccineManufacturerEnterprise> vaccineManufacturerList) {
        this.vaccineManufacturerList = vaccineManufacturerList;
    }

    public DiseaseCatalog getDiseaseCatalog() {
        return diseaseCatalog;
    }

    public void setDiseaseCatalog(DiseaseCatalog diseaseCatalog) {
        this.diseaseCatalog = diseaseCatalog;
    }

    @Override
    public ArrayList<Role> getSupportedRole() {
        ArrayList<Role> roleList = new ArrayList<>();
        roleList.add(new CDCAdminRole());
        return roleList;
    }

}
