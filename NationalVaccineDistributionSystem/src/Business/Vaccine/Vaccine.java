/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Vaccine;

import Business.Enterprise.VaccineManufacturerEnterprise;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Rishika Idnani
 */
public class Vaccine {

    private String name;
    private float price;
    private static int count = 100;
    private String serialNo;
    private Disease disease;
    private VaccineManufacturerEnterprise vaccineManufacturer;
    private int daysForManufacturing;
    private int validity;

    private int batchAvailability;
    private Date manufacturingBatch;
    private Date expiryDate;

    private ArrayList<VaccineAllocation> vaccineAllocationData;;

    public Vaccine() {
        count++;
        this.serialNo = "VAC" + String.valueOf(count);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public Disease getDisease() {
        return disease;
    }

    public void setDisease(Disease disease) {
        this.disease = disease;
    }

    public int getBatchAvailability() {
        return batchAvailability;
    }

    public void setBatchAvailability(int batchAvailability) {
        this.batchAvailability = batchAvailability;
    }

    public VaccineManufacturerEnterprise getVaccineManufacturer() {
        return vaccineManufacturer;
    }

    public void setVaccineManufacturer(VaccineManufacturerEnterprise vaccineManufacturer) {
        this.vaccineManufacturer = vaccineManufacturer;
    }

    public int getDaysForManufacturing() {
        return daysForManufacturing;
    }

    public void setDaysForManufacturing(int daysForManufacturing) {
        this.daysForManufacturing = daysForManufacturing;
    }

    public Date getManufacturingBatch() {
        return manufacturingBatch;
    }

    public void setManufacturingBatch(Date manufacturingBatch) {
        this.manufacturingBatch = manufacturingBatch;
    }

    public ArrayList<VaccineAllocation> getVaccineAllocationData() {
        return vaccineAllocationData;
    }

    public void setVaccineAllocationData(ArrayList<VaccineAllocation> vaccineAllocationData) {
        this.vaccineAllocationData = vaccineAllocationData;
    }

    public int getValidity() {
        return validity;
    }

    public void setValidity(int validity) {
        this.validity = validity;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date manufacturingDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(manufacturingDate);
        cal.add(Calendar.DATE, validity);
        this.expiryDate = cal.getTime();
    }

    @Override
    public String toString() {
        return name;
    }
}
