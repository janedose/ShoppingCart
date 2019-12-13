package tests;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import model.Item;
import model.ItemOrder;
import org.junit.Before;
import org.junit.Test;

/**
 * Test the ItemOrder Class.
 * @author Yolanda Xu
 * @version 10 Oct 2019
 */
public class ItemOrderTest {
    
    /** Tester for Quantity. */
    private static final int QUANTITY = 5;
    
    /** Tester for the Item. */
    private Item myTestItem = new Item("tester", new BigDecimal("10.00"));
    
    /** Tester for the ItemOrder. */
    private ItemOrder myTestItemOrder;

    /**
     * Setting up the item order.
     */
    @Before
    public void setUp() {
        myTestItemOrder = new ItemOrder(myTestItem, QUANTITY);
    }

    /**
     * Tests the ItemOrder constructor.
     */
    @Test
    public void testItemOrder() {
        assertEquals("Test the item", myTestItem, myTestItemOrder.getItem());
        assertEquals("Test the quantity", QUANTITY, myTestItemOrder.getQuantity());
    }

    /**
     * Tests IAE for negative quantities.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testQuantityIAE() {
        new ItemOrder(myTestItem, -1);
    }

    /**
     * Tests the accessor for Item.
     */
    @Test
    public void testGetItem() {
        assertEquals("Test get item", myTestItem, myTestItemOrder.getItem());
    }

    /**
     * Tests the accessor for Quantity.
     */
    @Test
    public void testGetQuantity() {
        assertEquals("Test get quantity", QUANTITY, myTestItemOrder.getQuantity());
    }

    /**
     * Tests the toString method.
     */
    @Test
    public void testToString() {
        final String expected = QUANTITY + " of " + myTestItem.toString();
        assertEquals(expected, myTestItemOrder.toString());
    }

}
