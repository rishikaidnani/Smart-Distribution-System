/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Person;

import Business.Order.Order;
import java.util.ArrayList;

/**
 *
 * @author Rishika Idnani
 */
public class Provider extends Person {

    private ArrayList<Order> orderedList;
    private ArrayList<Patient> patientList;

    public Provider() {
        orderedList = new ArrayList<Order>();
        patientList = new ArrayList<>();
    }

    public ArrayList<Order> getOrderedList() {
        return orderedList;
    }

    public void setOrderedList(ArrayList<Order> orderedList) {
        this.orderedList = orderedList;
    }

    public ArrayList<Patient> getPatientList() {
        return patientList;
    }

    public void setPatientList(ArrayList<Patient> patientList) {
        this.patientList = patientList;
    }
}