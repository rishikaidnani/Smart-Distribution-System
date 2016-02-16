/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import Business.Network.NetworkDirectory;
import Business.Organization.Organization;
import Business.Role.Role;
import java.util.ArrayList;

/**
 *
 * @author Rishika Idnani
 */
public class Ecosystem extends Organization {

    private static Ecosystem system;
    private NetworkDirectory networkDirectory;

    private Ecosystem() {
        super(null);
        this.networkDirectory = new NetworkDirectory();
    }

    public static Ecosystem getInstance() {

        if (system == null) {
            system = new Ecosystem();
        }
        return system;
    }

    public NetworkDirectory getNetworkDirectory() {
        return networkDirectory;
    }

    @Override
    public ArrayList<Role> getSupportedRole() {
        return null;
    }

}
