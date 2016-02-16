/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Network;

import java.util.ArrayList;

/**
 *
 * @author Rishika Idnani
 */
public class NetworkDirectory {

    private ArrayList<Network> networkList;

    public NetworkDirectory() {
        this.networkList = new ArrayList<Network>();
    }

    public ArrayList<Network> getNetworkList() {
        return networkList;
    }

    public void setNetworkList(ArrayList<Network> networkList) {
        this.networkList = networkList;
    }

    public Network addAndCreateNetwork(String name) {
        for (Network network : networkList) {
            if (name.equalsIgnoreCase(network.getName())) {
                 return null;
            }
        }

        Network network = new Network();
        networkList.add(network);
        network.setName(name);
        return network;
    }

    public Boolean removeNetwork(String networkName) {
        for (Network network : networkList) {
            if (network.getName().equals(networkName)) {
                networkList.remove(network);
                return true;
            }
        }
        return null;
    }

    public Network getNetworkInstance(String networkName) {
        for (Network network : networkList) {
            if (network.getName().equals(networkName)) {
                return network;
            }
        }
        return null;
    }

}
