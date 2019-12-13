package tests;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import model.Cart;
import model.Item;
import model.ItemOrder;
import org.junit.Before;
import org.junit.Test;

/**
 * Test the Cart Class.
 * @author Yolanda Xu
 * @version 10 Oct 2019
 */
public class CartTest {
    
    /** Tester for the Cart. */
    private Cart myTestCart;
    
    /** Tester for the Item. */
    private Item myTestItem = new Item("tester", new BigDecimal("10.00"));
    
    /** Tester for the Item. */
    private Item myTestItem2 = new Item("tester2", new BigDecimal("5.00"), 
                                        5, new BigDecimal("20.00"));

    /** Tester for the ItemOrder. */
    private ItemOrder myTestItemOrder = new ItemOrder(myTestItem, 1);
    
    /** Tester for the ItemOrder. */
    private ItemOrder myTestItemOrder2 = new ItemOrder(myTestItem2, 6);
    
    /**
     * Setting up the Cart.
     */
    @Before
    public void setUp() {
        myTestCart = new Cart();
        myTestCart.add(myTestItemOrder);
        myTestCart.add(myTestItemOrder2);
    }

    /**
     * Tests the Cart constructor.
     */
    @Test
    public void testCart() {
        assertEquals(2, myTestCart.getCartSize());
    }

    /**
     * Tests to see if the item order gets added or updated in the cart.
     */
    @Test
    public void testAdd() {
        final ItemOrder testItemOrder3 = new ItemOrder(myTestItem, 5);
        final ItemOrder testItemOrder4 
            = new ItemOrder(new Item("tester4", new BigDecimal("20.00")), 2);
        myTestCart.add(testItemOrder3);
        myTestCart.add(testItemOrder4);
        assertEquals(3, myTestCart.getCartSize());
    }

    /**
     * Tests to see if the cart calculates correctly, including bulk pricing, if applicable.
     */
    @Test
    public void testCalculateTotalWithoutBulk() {
        assertEquals(new BigDecimal("40.00"), myTestCart.calculateTotal());
    }

    /**
     * Tests to see if the cart calculates correctly, including bulk pricing, if applicable.
     */
    @Test
    public void testCalculateTotalWithBulk() {
        myTestCart.setMembership(true);
        assertEquals(new BigDecimal("35.00"), myTestCart.calculateTotal());
        final ItemOrder testItemOrder5 = new ItemOrder(myTestItem2, 4);
        myTestCart.add(testItemOrder5);
        assertEquals(new BigDecimal("30.00"), myTestCart.calculateTotal());
    }
    
    /**
     * Checks to see if the cart is cleared.
     */
    @Test
    public void testClear() {
        myTestCart.clear();
        assertEquals(0, myTestCart.getCartSize());
    }

    /**
     * Tests the accessor for cart size.
     */
    @Test
    public void testGetCartSize() {
        assertEquals(2, myTestCart.getCartSize());
    }

    /**
     * Tests the toString method.
     */
    @Test
    public void testToString() {
        final String expected = "Cart has " + myTestCart.getCartSize() 
            + " unique item orders.";
        assertEquals(expected, myTestCart.toString());
    }

}
