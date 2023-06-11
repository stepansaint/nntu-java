package com.nntu;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;

public class Vegetable extends Food implements Contact {

    /*
    * Fields
    */

    private String originCountry;
    private String[] vitaminsInVegetable;

    /*
    * Constructors
    */

    public Vegetable(BigDecimal pricePerPackage, int numberOfPackages) {
        this.setPricePerPackage(pricePerPackage);
        this.setNumberOfPackages(numberOfPackages);
    }

    public Vegetable(BigDecimal pricePerPackage, int numberOfPackages,
                     int shelfLifeDays, String originCountry) {
        this.setPricePerPackage(pricePerPackage);
        this.setNumberOfPackages(numberOfPackages);
        this.setShelfLifeDays(shelfLifeDays);
        this.originCountry = originCountry;
    }

    public Vegetable(BigDecimal pricePerPackage, int numberOfPackages, int shelfLifeDays,
                     String originCountry, String[] vitaminsInVegetable) {
        this.setPricePerPackage(pricePerPackage);
        this.setNumberOfPackages(numberOfPackages);
        this.setShelfLifeDays(shelfLifeDays);
        this.originCountry = originCountry;
        this.vitaminsInVegetable = Arrays.copyOf(vitaminsInVegetable, vitaminsInVegetable.length);
    }

    /*
    * Methods
    */

    public static void main(String[] args) {

        Vegetable carrot = new Vegetable(new BigDecimal(120), 43);
        Vitamins vitaminsInCarrot = carrot.new Vitamins();

    }

    /**
     * @see #checkRottedVegetables(int, int, BigDecimal)
     */
    protected String checkRottedVegetables(int numberOfPackages) {
        int dangerousBoundPackages = 10;

        if (numberOfPackages <= dangerousBoundPackages) {
            return "Everything is OK.";
        }

        return "Maybe, there are some rotted vegetables.";
    }

    /**
     * @see #checkRottedVegetables(int, int, BigDecimal)
     */
    protected String checkRottedVegetables(int numberOfPackages, int shelfLifeDays) {
        if (checkRottedVegetables(numberOfPackages)
                .compareTo("Maybe, there are some rotted vegetables.") == 0) {

            int dangerousBoundShelfLife = 5;

            if (shelfLifeDays <= dangerousBoundShelfLife) {
                return "Everything is OK.";
            }
        }

        return "Maybe, there are some rotted vegetables.";
    }

    /**
     * Check if a specified vegetable is rotted.
     * Returns "Everything is OK" if one of the parameters has passed the verification.
     * @param numberOfPackages a specific number of packages for a product
     * @param shelfLifeDays a shelf life in days for a product
     * @param pricePerPackage a price of a package for a product
     * @return information about rotted vegetables
     */
    protected String checkRottedVegetables(int numberOfPackages, int shelfLifeDays,
                                           BigDecimal pricePerPackage) {
        if (checkRottedVegetables(numberOfPackages, shelfLifeDays)
                .compareTo("Maybe, there are some rotted vegetables.") == 0) {

            BigDecimal dangerousBoundPrice = new BigDecimal(10);

            if (pricePerPackage.compareTo(dangerousBoundPrice) <= 0) {
                return "Everything is OK.";
            }
        }

        return "Maybe, there are some rotted vegetables.";
    }

    /**
     * Find the area code for a specific country.
     * @param originCountry a specific country
     * @return an area code
     */
    @Override
    public String contactFarmer(String originCountry) {
        return "Area code of the farmer: " + Contact.areaCodes.get(originCountry);
    }

    /*
    * Getters and setters
    */

    protected final String getOriginCountry() {
        return originCountry;
    }

    protected final void setOriginCountry(String originCountry) {
        this.originCountry = originCountry;
    }

    protected final String getVitaminsInVegetable() {
        return Arrays.toString(vitaminsInVegetable);
    }

    protected final void setVitaminsInVegetable(String[] vitaminsInVegetable) {
        this.vitaminsInVegetable = vitaminsInVegetable;
    }

    /*
    * Nested classes
    */

    public class Vitamins {

        /* Keep the most important vitamin for a specific vegetable */
        private HashMap<String, String> mostImportantVitamins;

        /**
         * Find the most important vitamin for a specific vegetable.
         * @param vegetable a vegetable to be searched
         * @return the most important vitamin
         */
        protected String findMostImportantVitamin(String vegetable) {
            return mostImportantVitamins.get(vegetable);
        }

        /**
         * Add a vegetable and the most important vitamin for this vegetable.
         * @param vegetable a vegetable
         * @param mostImportantVitamin the most important vitamin
         */
        protected void addVegetableAndMostImportantVitamin(String vegetable, String mostImportantVitamin) {
            mostImportantVitamins.put(vegetable, mostImportantVitamin);
        }

        /**
         * Delete the most important vitamin for a specified vegetable.
         * @param vegetable a vegetable whose value to be deleted
         */
        protected boolean removeMostImportantVitaminFromVegetable(String vegetable) {
            return mostImportantVitamins.remove(vegetable) != null;
        }
    }
}
