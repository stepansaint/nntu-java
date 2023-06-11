package com.nntu;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Food extends Product {

    /*
    * Fields
    */

    private Date productionDate;
    private int shelfLifeDays;

    /*
    * Methods
    */

    /**
     * Calculates the margin per package of a product.
     * @return margin per one package
     */
    protected String calculateMarginForPackage() {
        if (shelfLifeDays == 0) {
            return "Please provide `shelfLifeDays` for the product.";
        }

        int shortShelfLife = 5;
        int normalShelfLife = 60;

        String lowMargin = "10%";
        String normalMargin = "10% - 20%";
        String highMargin = "20+%";

        if (shelfLifeDays <= shortShelfLife) {
            return lowMargin;
        }
        if (shelfLifeDays <= normalShelfLife) {
            return normalMargin;
        }

        return highMargin;
    }

    /**
     * Send a product back to the wholesaler.
     * Check if there's any problems and response with <code>Label</code>.
     * @return response about sending back
     */
    @Override
    protected Label sendBackToWholesaler() {
        BigDecimal lowerBound = new BigDecimal(10);
        BigDecimal upperBound = new BigDecimal(10_000);

        if (this.getPricePerPackage().compareTo(lowerBound) <= 0) {
            return Label.CHEAP_PRODUCT;
        }
        if (this.getPricePerPackage().compareTo(upperBound) > 0) {
            return Label.EXPENSIVE_PRODUCT;
        }

        return Label.OK;
    }

    /**
     * Send a product to the storehouse.
     * Check if there's any problems and response with <code>Label</code>.
     * @return response about sending
     */
    @Override
    protected Label sendToStorehouse() {
        int lowerBound = 10;
        int upperBound = 100_000;

        if (this.getNumberOfPackages() <= lowerBound) {
            return Label.FEW_PACKAGES;
        }
        if (this.getNumberOfPackages() > upperBound) {
            return Label.MANY_PACKAGES;
        }

        return Label.OK;
    }

    /*
    * Getters and setters
    */

    protected final String getProductionDate() {
        if (productionDate == null) {
            return "Please provide `productionDate` for the product.";
        }

        return new SimpleDateFormat().format(productionDate);
    }

    protected final void setProductionDate(String productionDate) throws ParseException {
        this.productionDate = new SimpleDateFormat("y-M-d H:m:s.S").parse(productionDate);
    }

    protected final float getShelfLifeDays() {
        return shelfLifeDays;
    }

    protected final void setShelfLifeDays(int shelfLifeDays) {
        this.shelfLifeDays = shelfLifeDays;
    }
}
