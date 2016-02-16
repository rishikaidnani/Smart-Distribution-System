/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Person;

/**
 *
 * @author Rishika Idnani
 */
public class Patient extends Person {

    private String insuranceInformation;
    private Provider primaryDoctor;
    private float coveragePercentile;

    public String getInsuranceInformation() {
        return insuranceInformation;
    }

    public void setInsuranceInformation(String insuranceInformation) {
        this.insuranceInformation = insuranceInformation;
    }

    public Provider getPrimaryDoctor() {
        return primaryDoctor;
    }

    public void setPrimaryDoctor(Provider primaryDoctor) {
        this.primaryDoctor = primaryDoctor;
    }

    public float getCoveragePercentile() {
        return coveragePercentile;
    }

    public void setCoveragePercentile(float coveragePercentile) {
        this.coveragePercentile = coveragePercentile;
    }

}
