/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Order;

import Business.Person.Person;
import Business.Vaccine.Disease;
import Business.Vaccine.Vaccine;
import java.util.Date;

/**
 *
 * @author Rishika Idnani
 */
public class OrderItem {

    private Vaccine vaccine;
    private float totalPrice;
    private int totalQuantity;
    private Disease disease;
    private Person provider;
    private Date manufacturingBatch;
    private Date expiryDate;

    private String isOrderItemApprovedByCdc;
    private String isOrderItemShippedToDistributionCentre;
    private String isOrderItemShippedToSite;
    private String ifPaid;
    private String ifBad;
    private String ifBadInformed;

    public OrderItem() {
        isOrderItemApprovedByCdc = "Pending";
        isOrderItemShippedToDistributionCentre = "Pending";
        isOrderItemShippedToSite = "Pending";
        ifPaid = "Pending";
        ifBad = "No";
        ifBadInformed = "No";
    }

    public Vaccine getVaccine() {
        return vaccine;
    }

    public void setVaccine(Vaccine vaccine) {
        this.vaccine = vaccine;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public Disease getDisease() {
        return disease;
    }

    public void setDisease(Disease disease) {
        this.disease = disease;
    }

    public void calculateTotalPrice() {
        totalPrice = totalQuantity * (vaccine.getPrice());
    }

    public String getIsOrderItemApprovedByCdc() {
        return isOrderItemApprovedByCdc;
    }

    public void setIsOrderItemApprovedByCdc(String isOrderItemApprovedByCdc) {
        this.isOrderItemApprovedByCdc = isOrderItemApprovedByCdc;
    }

    public String getIsOrderItemShippedToDistributionCentre() {
        return isOrderItemShippedToDistributionCentre;
    }

    public void setIsOrderItemShippedToDistributionCentre(String isOrderItemShippedToDistributionCentre) {
        this.isOrderItemShippedToDistributionCentre = isOrderItemShippedToDistributionCentre;
    }

    public String getIsOrderItemShippedToSite() {
        return isOrderItemShippedToSite;
    }

    public void setIsOrderItemShippedToSite(String isOrderItemShippedToSite) {
        this.isOrderItemShippedToSite = isOrderItemShippedToSite;
    }

    public Person getProvider() {
        return provider;
    }

    public void setProvider(Person provider) {
        this.provider = provider;
    }

    public String getIfPaid() {
        return ifPaid;
    }

    public void setIfPaid(String ifPaid) {
        this.ifPaid = ifPaid;
    }

    @Override
    public String toString() {
        return vaccine.getName();
    }

    public Date getManufacturingBatch() {
        return manufacturingBatch;
    }

    public void setManufacturingBatch(Date manufacturingBatch) {
        this.manufacturingBatch = manufacturingBatch;
    }

    public String getIfBad() {
        return ifBad;
    }

    public void setIfBad(String ifBad) {
        this.ifBad = ifBad;
    }

    public String getIfBadInformed() {
        return ifBadInformed;
    }

    public void setIfBadInformed(String ifBadInformed) {
        this.ifBadInformed = ifBadInformed;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

}
