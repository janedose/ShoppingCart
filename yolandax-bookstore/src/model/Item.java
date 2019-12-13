// Finish and comment me!

package model;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Represents a single item for sale. An Item is an immutable object.
 * @author Yolanda Xu
 * @version 9 Oct 2019
 */
public final class Item {

    /**
     * The Item's name.
     */
    private final String myName;
    
    /**
     * The Item's unit price.
     */
    private final BigDecimal myPrice;
    
    /**
     * The Item's bulk quantity.
     */
    private int myBulkQuantity;
    
    /**
     * The Item's bulk price.
     */
    private BigDecimal myBulkPrice;

    /**
     * Constructor for an Item without bulk pricing.
     * @param theName The Item's name.
     * @param thePrice The Item's unit price.
     * @throws IllegalArgumentException if theName is an emptyString or if
     *             thePrice < 0.
     */
    public Item(final String theName, final BigDecimal thePrice) {
        if (Objects.requireNonNull(theName).length() == 0
                        || Objects.requireNonNull(thePrice).compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException();
        }
        myName = theName;
        myPrice = thePrice;
    }

    /**
     * Constructor for an Item with bulk pricing.
     * @param theName The Item's name.
     * @param thePrice The Item's unit price.
     * @param theBulkQuantity The Item's bulk quantity.
     * @param theBulkPrice The Item's bulk price.
     * @throws IllegalArgumentException if theName is an emptyString, thePrice < 0, 
     *          theBulkQuantity < 0, or theBulkPrice < 0.
     */
    public Item(final String theName, final BigDecimal thePrice, final int theBulkQuantity,
                final BigDecimal theBulkPrice) {
        if (Objects.requireNonNull(theName).length() == 0 || theBulkQuantity < 0
            || Objects.requireNonNull(thePrice).compareTo(BigDecimal.ZERO) < 0
            || Objects.requireNonNull(theBulkPrice).compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException();
        }
        myName = theName;
        myPrice = thePrice;
        myBulkQuantity = theBulkQuantity;
        myBulkPrice = theBulkPrice;
    }

    /**
     * The unit price of the item.
     * @return The unit price of the item.
     */
    public BigDecimal getPrice() {
        return myPrice;
    }

    /**
     * The bulk quantity of the item.
     * @return The bulk quantity of the item.
     */
    public int getBulkQuantity() {
        return myBulkQuantity;
    }

    /**
     * The bulk price of the item.
     * @return The bulk price of the item.
     */
    public BigDecimal getBulkPrice() {
        return myBulkPrice;
    }
    
    /**
     * Returns boolean value of whether the item has bulk pricing or not.
     * @return true if there is bulk pricing for the item.
     */
    public boolean isBulk() {
        return myBulkQuantity != 0;
    }

    /**
     * Override toString.
     * @return toString.
     */
    @Override
    public String toString() {
        String start = myName + ", $" + this.getPrice();
        if (this.isBulk()) {
            start = start +  " (" + this.getBulkQuantity() 
                + " for $" + this.getBulkPrice() + ")";
        } 
        return start;
    }

    /**
     * Override equals.
     * @return equals.
     */
    @Override
    public boolean equals(final Object theOther) {
        if (theOther == null || !getClass().equals(theOther.getClass())) { 
            return false;
        } 
        final Item otherItem = (Item) theOther;
        if (isBulk()) {
            return myName.equals(otherItem.myName) && myPrice.equals(otherItem.myPrice)
                            && myBulkQuantity == otherItem.myBulkQuantity
                            && myBulkPrice.equals(otherItem.myBulkPrice);
        } else {
            return myName.equals(otherItem.myName) && myPrice.equals(otherItem.myPrice);
        }
    }

    /**
     * Override the hashCode.
     * @return the hashCode.
     */
    @Override
    public int hashCode() {
        return Objects.hash(myName, myPrice, myBulkQuantity, myBulkPrice);
    }

}
