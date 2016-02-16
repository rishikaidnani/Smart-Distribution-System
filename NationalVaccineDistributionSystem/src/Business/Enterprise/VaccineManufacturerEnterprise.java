/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Enterprise;

import Business.Role.Role;
import Business.Role.VaccineManufacturerAdminRole;
import Business.Vaccine.Vaccine;
import Business.Vaccine.VaccineCatalog;
import Business.Vaccine.VaccineInventory;
import java.util.ArrayList;

/**
 *
 * @author Rishika Idnani
 */
public class VaccineManufacturerEnterprise extends Enterprise {

    private VaccineCatalog vaccineCatalog;
    private VaccineInventory vaccineInventory;

    public VaccineManufacturerEnterprise() {
        vaccineCatalog = new VaccineCatalog();
        vaccineInventory = new VaccineInventory();
    }

    public VaccineCatalog getVaccineCatalog() {
        return vaccineCatalog;
    }

    public void setVaccineCatalog(VaccineCatalog vaccineCatalog) {
        this.vaccineCatalog = vaccineCatalog;
    }

    public VaccineInventory getVaccineInventory() {
        return vaccineInventory;
    }

    public void setVaccineInventory(VaccineInventory vaccineInventory) {
        this.vaccineInventory = vaccineInventory;
    }

    @Override
    public ArrayList<Role> getSupportedRole() {
        ArrayList<Role> roleList = new ArrayList<>();
        roleList.add(new VaccineManufacturerAdminRole());
        return roleList;
    }

}
