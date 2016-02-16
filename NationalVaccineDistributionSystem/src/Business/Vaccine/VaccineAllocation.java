/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Vaccine;

import Business.Enterprise.PHDEnterprise;

/**
 *
 * @author Rishika Idnani
 */
public class VaccineAllocation {

    private PHDEnterprise phdEnterprise;
    private float allocatedVaccineQuantity;

    public PHDEnterprise getPhdEnterprise() {
        return phdEnterprise;
    }

    public void setPhdEnterprise(PHDEnterprise phdEnterprise) {
        this.phdEnterprise = phdEnterprise;
    }

    public float getAllocatedVaccineQuantity() {
        return allocatedVaccineQuantity;
    }

    public void setAllocatedVaccineQuantity(float allocatedVaccineQuantity) {
        this.allocatedVaccineQuantity = allocatedVaccineQuantity;
    }

}
