/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Organization;

import Business.Person.PersonDirectory;
import Business.Role.Role;
import Business.UserAccount.UserAccountDirectory;
import Business.WorkRequest.WorkQueue;
import java.util.ArrayList;

/**
 *
 * @author Rishika Idnani
 */
public abstract class Organization {

    private String name;
    private int id;
    private int count = 0;
    private UserAccountDirectory userAccountDirectory;
    private PersonDirectory personDirectory;
    private Organization.TypeOfOrganization type;
    private WorkQueue workQueue;

    public Organization(String name) {
        this.name = name;
        count++;
        id = count;
        this.userAccountDirectory = new UserAccountDirectory();
        this.personDirectory = new PersonDirectory();
        this.workQueue = new WorkQueue();
    }

    //Enum class that has list of all types of organizations
    public enum TypeOfOrganization {

        Admin("Admin Organization"),
        SiteOperation("Site Operations Organization"),
        ProviderOrganization("Provider Organization"),
        CdcOperations("CDC Operations Organization"),
        PhdOperations("PHD Operations Organization"),
        DistributionCentre("Distribution Centre Organization");

        private String value;

        private TypeOfOrganization(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserAccountDirectory getUserAccountDirectory() {
        return userAccountDirectory;
    }

    public PersonDirectory getPersonDirectory() {
        return personDirectory;
    }

    public TypeOfOrganization getType() {
        return type;
    }

    public void setType(TypeOfOrganization type) {
        this.type = type;
    }

    public WorkQueue getWorkQueue() {
        return workQueue;
    }

    public void setWorkQueue(WorkQueue workQueue) {
        this.workQueue = workQueue;
    }

    public abstract ArrayList<Role> getSupportedRole();

    @Override
    public String toString() {
        return name;
    }
}
