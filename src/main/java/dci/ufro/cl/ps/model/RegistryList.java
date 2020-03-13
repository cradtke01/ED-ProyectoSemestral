package dci.ufro.cl.ps.model;

import java.util.ArrayList;

/**
 * @author Carlos Radtke
 * @version 1.0
 * @since 1.0
 */
public class RegistryList {

    private static ArrayList<Registry> registryList = new ArrayList<>();

    public static ArrayList<Registry> getRegistryList() {
        return registryList;
    }

    public static void setRegistryList(ArrayList<Registry> registryList) {
        RegistryList.registryList = registryList;
    }
}
