/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Vaccine;

import Business.Enterprise.VaccineManufacturerEnterprise;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Rishika Idnani
 */
public class VaccineInventory {

    private ArrayList<Vaccine> vaccineInventoryList;

    public VaccineInventory() {
        vaccineInventoryList = new ArrayList<>();
    }

    public ArrayList<Vaccine> getVaccineInventoryList() {
        return vaccineInventoryList;
    }

    public void setVaccineInventoryList(ArrayList<Vaccine> vaccineInventoryList) {
        this.vaccineInventoryList = vaccineInventoryList;
    }

    public Date getTodaysDate() {
        Date manufacturingDate = new Date();
        return manufacturingDate;
    }

    public Vaccine addVaccineInInventory(Vaccine v, int vaccineAvailability) {
        String name = v.getName();
        Disease disease = v.getDisease();
        float price = v.getPrice();
        int validity = v.getValidity();
        int manufacturingDays = v.getDaysForManufacturing();
        VaccineManufacturerEnterprise manufacturer = v.getVaccineManufacturer();

        Vaccine vaccine = new Vaccine();
        vaccine.setName(name);
        vaccine.setDisease(disease);
        vaccine.setPrice(price);
        vaccine.setValidity(validity);
        vaccine.setDaysForManufacturing(manufacturingDays);
        vaccine.setVaccineManufacturer(manufacturer);
        
        vaccine.setManufacturingBatch(getTodaysDate());
        vaccine.setExpiryDate(getTodaysDate());
        vaccine.setBatchAvailability(vaccineAvailability);
        vaccineInventoryList.add(vaccine);

        return vaccine;
    }
}
