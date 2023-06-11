package com.nntu;

import java.util.HashMap;

public interface Contact {

    /* Keep area codes for specific countries */
    HashMap<String, String> areaCodes = new HashMap<>();

    /**
     * Add a specific area code for a specific country in this interface.
     * @param country a specific country
     * @param areaCode a specific area code for a county
     */
    static void addAreaCode(String country, String areaCode) {
        areaCodes.put(country, areaCode);
    }

    /**
     * Delete an area code of a specific country.
     * Returns true if there was a specific country in this interface.
     * Returns false if there wasn't a specific country.
     * @param country a specific country
     * @return success of deleting
     */
    static boolean deleteAreaCode(String country) {
        return areaCodes.remove(country) != null;
    }

    String contactFarmer(String originCountry);
}
