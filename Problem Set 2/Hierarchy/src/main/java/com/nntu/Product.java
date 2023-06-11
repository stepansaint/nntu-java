package com.nntu;

import java.math.BigDecimal;

public abstract class Product {

    /*
    * Fields
    */

    private BigDecimal pricePerPackage;
    private int numberOfPackages;

    /*
    * Methods
    */

    /**
     * Calculates the full price of a product.
     * @return full price
     */
    protected final BigDecimal calculateFullPriceOfProduct() {
        if (pricePerPackage == null) {
            return new BigDecimal(0);
        }

        return new BigDecimal(String.valueOf(pricePerPackage))
                .multiply(new BigDecimal(numberOfPackages));
    }

    protected abstract Label sendBackToWholesaler();
    protected abstract Label sendToStorehouse();

    /*
    * Getters and setters
    */

    protected final BigDecimal getPricePerPackage() {
        if (pricePerPackage == null) {
            return new BigDecimal("0");
        }

        return new BigDecimal(String.valueOf(pricePerPackage));
    }

    protected final void setPricePerPackage(BigDecimal pricePerPackage) {
        this.pricePerPackage = new BigDecimal(String.valueOf(pricePerPackage));
    }

    protected final int getNumberOfPackages() {
        return numberOfPackages;
    }

    protected final void setNumberOfPackages(int numberOfPackages) {
        if (numberOfPackages < 0) {
            this.numberOfPackages = Math.abs(numberOfPackages);
        } else {
            this.numberOfPackages = numberOfPackages;
        }
    }
}
