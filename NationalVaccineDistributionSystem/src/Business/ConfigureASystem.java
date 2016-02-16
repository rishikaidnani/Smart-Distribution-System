/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import Business.Person.Person;
import Business.Role.SystemAdminRole;

/**
 *
 * @author Rishika Idnani
 */
public class ConfigureASystem {

    private static Ecosystem system;

    public static Ecosystem initialize() {
        system = Ecosystem.getInstance();

        Person person = system.getPersonDirectory().addAndCreatePerson("Software Owner");

        system.getUserAccountDirectory().createAndAddUserAccount("sysadmin", "sysadmin", new SystemAdminRole(), person);

        return system;
    }
}
