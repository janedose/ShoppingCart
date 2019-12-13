package tests;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import model.Item;
import org.junit.Before;
import org.junit.Test;

/**
 * Test the Item Class.
 * @author Yolanda Xu
 * @version 10 Oct 2019
 */
public class ItemTest {
    
    /** Tester for Item Name. */
    private static final String DEFAULT_NAME = "Test Item";
    
    /** Tester for Item Price. */
    private static final  BigDecimal DEFAULT_PRICE = new BigDecimal("10.00");
    
    /** Tester for Bulk Price. */
    private static final  BigDecimal BULK_PRICE = new BigDecimal("40.00");
    
    /** Tester for Bulk Quantity. */
    private static final int BULK_QUANTITY = 5;
    
    /** A negative BigDecimal. */
    private static final BigDecimal NEGATIVE_BIGDECIMAL = BigDecimal.valueOf(-9.99);
    
    /** Test fixture for non-bulk item. */
    private Item myTestItem;
    
    /** Test fixture for bulk item. */
    private Item myBulkItem;

    /**
     * Setting up test item and test bulk item.
     */
    @Before
    public void setUp() {
        myTestItem = new Item(DEFAULT_NAME, DEFAULT_PRICE);
        myBulkItem = new Item(DEFAULT_NAME, DEFAULT_PRICE, BULK_QUANTITY, BULK_PRICE);
    }

    /**
     * Test the Item constructor. 
     */
    @Test
    public void testItem() {
//        assertEquals("Test the name", DEFAULT_NAME, myTestItem.myName);
        assertEquals("Test the price", DEFAULT_PRICE, myTestItem.getPrice());
        //Test for Zero Price. Should NOT cause exception. 
        new Item(DEFAULT_NAME, BigDecimal.ZERO);
    }
    
    /**
     * Test the Item constructor for bulk item. 
     */
    @Test
    public void testBulkItem() {
        assertEquals("Test the price", DEFAULT_PRICE, myBulkItem.getPrice());
        assertEquals("Test the bulk quantity", BULK_QUANTITY, myBulkItem.getBulkQuantity());
        assertEquals("Test the bulk price", BULK_PRICE, myBulkItem.getBulkPrice());
    }
    
    /**
     * Test for the hash code consistency over time.
     */
    @Test
    public void testHashCodeTime() {
        //Test the SAME object looking for consistency over time. 
        assertEquals(myTestItem.hashCode(), myTestItem.hashCode());
        assertEquals(myBulkItem.hashCode(), myBulkItem.hashCode());
    }
    
    /**
     * Test for the hash code.
     */
    @Test
    public void testHashCode() {
        //Test different objects with the SAME state.
        final Item differentButTheSame = new Item(DEFAULT_NAME, DEFAULT_PRICE);
        final Item differentButTheSameBulk = new Item(DEFAULT_NAME, DEFAULT_PRICE, 
                                                      BULK_QUANTITY, BULK_PRICE);
        assertEquals(myTestItem.hashCode(), differentButTheSame.hashCode());
        assertEquals(myBulkItem.hashCode(), differentButTheSameBulk.hashCode());
    }

    /**
     * Test the accessor for Price.
     */
    @Test
    public void testGetPrice() {
        assertEquals("Test the price", DEFAULT_PRICE, myTestItem.getPrice());
    }

    /**
     * Test the accessor for Bulk Quantity.
     */
    @Test
    public void testGetBulkQuantity() {
        assertEquals("Test the bulk quantity", BULK_QUANTITY, myBulkItem.getBulkQuantity());
    }

    /**
     * Test the accessor for Bulk Price.
     */
    @Test
    public void testGetBulkPrice() {
        assertEquals("Test the bulk price", BULK_PRICE, myBulkItem.getBulkPrice());
    }

    /**
     * Test if item is bulk or not.
     */
    @Test
    public void testIsBulk() {
        assertEquals("Test if bulk", true, myBulkItem.isBulk());
        assertEquals("Test if not bulk", false, myTestItem.isBulk());
    }

    /**
     * Test the toString method.
     */
    @Test
    public void testToString() {
        final String expectedNotBulk = DEFAULT_NAME + ", $" + DEFAULT_PRICE;
        assertEquals(expectedNotBulk, myTestItem.toString());
        
        final String expectedBulk = expectedNotBulk + " (" + BULK_QUANTITY + " for $"
                        + BULK_PRICE + ")";
        assertEquals(expectedBulk, myBulkItem.toString());
    }

    /** 
     * Test case for .equals looking only at the cases where a Non-Item object is passed. 
     */
    @Test
    public void testEqualsFalseNonItemCases() {
        //Test for false in .equals(null)
        assertNotEquals(myTestItem, null);
        
        //Test for false in .equals(Other Class type)
        assertNotEquals(myTestItem, DEFAULT_NAME);
        assertNotEquals(myTestItem, DEFAULT_PRICE);
    }
    
    /** 
     * Test case for .equals looking only at the false cases. 
     */
    @Test
    public void testEqualsFalseCases() {
        final Item differentNameSamePrice = new Item("Different", DEFAULT_PRICE);
        final Item differentPriceSameName = new Item(DEFAULT_NAME, new BigDecimal("99.99"));
        final Item differentNameAndPrice = new Item("Different", new BigDecimal("99.99"));
        assertNotEquals(myTestItem, differentNameSamePrice);
        assertNotEquals(myTestItem, differentPriceSameName);
        assertNotEquals(myTestItem, differentNameAndPrice);
    }

    /** 
     * Test case for .equals looking only at the true cases. 
     */
    @Test
    public void testEqualsTrueCases() {
        //Test the SAME object
        assertEquals(myTestItem, myTestItem);
        
        //Test different objects with the SAME state.
        final Item differentButTheSame = new Item(DEFAULT_NAME, DEFAULT_PRICE);
        assertEquals(myTestItem, differentButTheSame);
    }
    
    /** 
     * Test case for .equals looking only at the true cases for bulk items. 
     */
    @Test
    public void testEqualsTrueCasesBulk() {
        final Item differentButTheSameBulk = new Item(DEFAULT_NAME, DEFAULT_PRICE, 
                                                      BULK_QUANTITY, BULK_PRICE);
        assertEquals(myBulkItem, differentButTheSameBulk);
    }
    
    /**
     * Test the Item constructor for a NullPointerException when sending a null name. 
     */
    @Test (expected = NullPointerException.class)
    public void testItemNameNPE() {
        new Item(null, BigDecimal.TEN);
    }
    
    /**
     * Test the Item constructor for a NullPointerException when sending a null price. 
     */
    @Test (expected = NullPointerException.class)
    public void testItemPriceNPE() {
        new Item(DEFAULT_NAME, null);
    }
    
    /**
     * Test the Item constructor for a IllegalArgumentException when sending a negative price. 
     */
    @Test (expected = IllegalArgumentException.class)
    public void testItemPriceIAE() {
        new Item(DEFAULT_NAME, NEGATIVE_BIGDECIMAL);
    }
    
    /**
     * Test the Item constructor for a IllegalArgumentException when sending an empty String. 
     */
    @Test (expected = IllegalArgumentException.class)
    public void testItemNameIAE() {
        new Item("", BigDecimal.ONE);
    }

    /**
     * Test the Bulk Item constructor for a IllegalArgumentException when sending 
     * a negative price. 
     */
    @Test (expected = IllegalArgumentException.class)
    public void testBulkItemPriceIAE() {
        new Item(DEFAULT_NAME, NEGATIVE_BIGDECIMAL, BULK_QUANTITY, BULK_PRICE);
    }
    
    /**
     * Test the Bulk Item constructor for a IllegalArgumentException when sending 
     * an empty String. 
     */
    @Test (expected = IllegalArgumentException.class)
    public void testBulkItemNameIAE() {
        new Item("", BigDecimal.ONE, BULK_QUANTITY, BULK_PRICE);
    }
    
    /**
     * Test the Bulk Item constructor for a IllegalArgumentException when sending 
     * a negative bulk price. 
     */
    @Test (expected = IllegalArgumentException.class)
    public void testBulkItemBulkPriceIAE() {
        new Item(DEFAULT_NAME, BigDecimal.ONE, BULK_QUANTITY, NEGATIVE_BIGDECIMAL);
    }
    /**
     * Test the Item constructor for a IllegalArgumentException when sending 
     * a negative bulk quantity. 
     */
    @Test (expected = IllegalArgumentException.class)
    public void testBulkItemBulkQuantityIAE() {
        new Item(DEFAULT_NAME, BigDecimal.ONE, -1, BULK_PRICE);
    }

}
