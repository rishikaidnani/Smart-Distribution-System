/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.WorkRequest;

import Business.Enterprise.SiteEnterprise;
import Business.Person.Patient;
import Business.Person.Provider;
import Business.Vaccine.Vaccine;

/**
 *
 * @author Rishika Idnani
 */
public class BillTheCdc extends WorkRequest {

    private float amount;
    private Provider provider;
    private SiteEnterprise siteEnterprise;
    private Patient patient;
    private Vaccine vaccine;

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public SiteEnterprise getSiteEnterprise() {
        return siteEnterprise;
    }

    public void setSiteEnterprise(SiteEnterprise siteEnterprise) {
        this.siteEnterprise = siteEnterprise;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Vaccine getVaccine() {
        return vaccine;
    }

    public void setVaccine(Vaccine vaccine) {
        this.vaccine = vaccine;
    }

}
