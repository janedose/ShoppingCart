// Finish and comment me!

package model;

/**
 * The item order quantity based on each item.
 * @author Yolanda Xu
 * @version 9 Oct 2019
 */
public final class ItemOrder {

    /**
     * The Item.
     */
    private final Item myItem;
    
    /**
     * The Quantity.
     */
    private final int myQuantity;
    
    /**
     * Constructs a new ItemOrder based on the Item and quantity.
     * @param theItem The Item in the ItemOrder.
     * @param theQuantity The quantity of the Item in the order.
     * @throws IllegalArgumentException if theQuantity < 0.
     */
    public ItemOrder(final Item theItem, final int theQuantity) {
        if (theQuantity < 0) {
            throw new IllegalArgumentException();
        }
        myItem = theItem;
        myQuantity = theQuantity;
    }

    /**
     * The Item in the ItemOrder.
     * @return The Item in the ItemOrder.
     */
    public Item getItem() {
        return myItem;
    }

    /**
     * The quantity of the Item in the order.
     * @return The quantity of the Item in the order.
     */
    public int getQuantity() {
        return myQuantity;
    }

    /**
     * Override toString.
     * @return toString.
     */
    @Override
    public String toString() {
        return myQuantity + " of " + myItem.toString();
    }

}
