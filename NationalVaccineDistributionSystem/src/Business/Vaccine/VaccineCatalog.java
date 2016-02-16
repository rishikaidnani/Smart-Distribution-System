/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Vaccine;

import Business.Enterprise.VaccineManufacturerEnterprise;
import java.util.ArrayList;

/**
 *
 * @author Rishika Idnani
 */
public class VaccineCatalog {

    private ArrayList<Vaccine> vaccineList;

    public VaccineCatalog() {
        this.vaccineList = new ArrayList<>();
    }

    public ArrayList<Vaccine> getVaccineList() {
        return vaccineList;
    }

    public void setVaccineList(ArrayList<Vaccine> vaccineList) {
        this.vaccineList = vaccineList;
    }

    public Vaccine addAndCreateVaccine(String vaccineName, int price, Disease disease, int time, int validity, VaccineManufacturerEnterprise enterprise) {
        for (Vaccine vaccine : vaccineList) {
            if (vaccine.getName().equalsIgnoreCase(vaccineName)) {
                return null;
            }
        }

        Vaccine vaccine = new Vaccine();
        vaccine.setName(vaccineName);
        vaccine.setDisease(disease);
        vaccine.setPrice(price);
        vaccine.setValidity(validity);
        vaccine.setDaysForManufacturing(time);        
        vaccine.setVaccineManufacturer(enterprise);
        vaccineList.add(vaccine);
        return vaccine;
    }
    
    public void removeVaccine(Vaccine vaccine){
        vaccineList.remove(vaccine);
    }
}
