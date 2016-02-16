/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Person;

import java.util.ArrayList;

/**
 *
 * @author Rishika Idnani
 */
public class PersonDirectory {

    private ArrayList<Person> personList;

    public PersonDirectory() {
        this.personList = new ArrayList<>();
    }

    public ArrayList<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(ArrayList<Person> personList) {
        this.personList = personList;
    }

    public Person addAndCreatePerson(String name) {
        for (Person person : personList) {
            if (person.getName().equalsIgnoreCase(name)) {
                return null;
            }
        }
        Person person = new Person();
        personList.add(person);
        person.setName(name);
        return person;
    }

    public Provider addAndCreateProvider(String name) {
        Provider provider = new Provider();
        personList.add(provider);
        provider.setName(name);
        return provider;
    }

    public Patient addAndCreatePatient(String name) {
        for (Person person : personList) {
            if (person.getName().equalsIgnoreCase(name)) {
                return null;
            }
        }
        Patient patient = new Patient();
        personList.add(patient);
        patient.setName(name);
        return patient;
    }

    public void removePerson(Person person) {
        personList.remove(person);
    }
}
