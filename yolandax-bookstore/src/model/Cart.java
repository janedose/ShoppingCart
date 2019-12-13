// Finish and comment me!

package model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * The whole cart, which includes each item order.
 * @author Yolanda Xu
 * @version 9 Oct 2019
 */
public class Cart {

    /**
     * The list of item orders.
     */
    private List<ItemOrder> myShoppingList;
    
    /**
     * The customer's membership.
     */
    private boolean myMembership;
    
    /**
     * Constructs a new cart based on item orders.
     */
    public Cart() {
        myShoppingList = new ArrayList<ItemOrder>();
    }
    
    /**
     * Adds an ItemOrder to the cart.
     * @param theOrder The item order.
     */
    public void add(final ItemOrder theOrder) {
        for (final ItemOrder eachItemOrder : myShoppingList) {
            if (theOrder.getItem().equals(eachItemOrder.getItem())) {
                myShoppingList.remove(eachItemOrder);
                break;
            }
        }
        myShoppingList.add(theOrder);
    }

    /**
     * Sets the store membership if applicable.
     * @param theMembership Boolean value to see if customer has store membership.
     */
    public void setMembership(final boolean theMembership) {
        myMembership = theMembership;
    }

    /**
     * Calculates the total price of the cart based on store membership, if applicable.
     * @return the cart value.
     */
    public BigDecimal calculateTotal() {
        BigDecimal cartPrice = BigDecimal.ZERO;
        if (!myMembership) {
            for (final ItemOrder eachItemOrder : myShoppingList) {
                cartPrice = cartPrice.add(eachItemOrder.getItem().getPrice().
                     multiply(new BigDecimal(eachItemOrder.getQuantity())));
            }
        }
        if (myMembership) {
            for (final ItemOrder eachItemOrder : myShoppingList) {
                if (!eachItemOrder.getItem().isBulk() || eachItemOrder.getQuantity() 
                    < eachItemOrder.getItem().getBulkQuantity()) {
                    cartPrice = cartPrice.add(eachItemOrder.getItem().getPrice().
                        multiply(new BigDecimal(eachItemOrder.getQuantity())));
                } else {
                    final int quantity = eachItemOrder.getQuantity();
                    final int bulkQuantity = eachItemOrder.getItem().getBulkQuantity();
                    final int leftOverQuantity = quantity % bulkQuantity;
                    cartPrice = cartPrice.add(eachItemOrder.getItem().getBulkPrice().
                        multiply(new BigDecimal(quantity / bulkQuantity)));
                    cartPrice = cartPrice.add(eachItemOrder.getItem().getPrice().
                        multiply(new BigDecimal(leftOverQuantity)));
                }
            }
        }
        return cartPrice.setScale(2, RoundingMode.HALF_EVEN);
    }
    
    /**
     * Clears the cart.
     */
    public void clear() {
        myShoppingList = new ArrayList<ItemOrder>();
    }
    
    /**
     * Get the size of the cart.
     * @return the cart size.
     */
    public int getCartSize() {
        return myShoppingList.size();
    }

    /**
     * Override toString.
     * @return toString.
     */
    @Override
    public String toString() {
        return "Cart has " + this.getCartSize() + " unique item orders.";
    }

}
